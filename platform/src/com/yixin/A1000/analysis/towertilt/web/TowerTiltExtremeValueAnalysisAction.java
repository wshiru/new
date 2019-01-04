package com.yixin.A1000.analysis.towertilt.web;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Year;

import com.yixin.A1000.analysis.towertilt.service.TowerTiltAnalysisService;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.web.BaseSamplingAction;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.util.ContextKeys;
import com.yixin.framework.util.jfreechart.JFreeChartFactory;
import com.yixin.framework.util.jfreechart.TimeSeriesChartData;

/**
 * 极值统计
 * 
 * @version v1.0.0
 * @author 梁立全
 * 
 */
public class TowerTiltExtremeValueAnalysisAction extends
		BaseSamplingAction<TowerTiltSampling>  implements ServletRequestAware,ServletResponseAware {

	private static final long serialVersionUID = -9119672883262578082L;

	private TowerTiltAnalysisService towerTiltAnalysisService;

	// 数据类型
	public static final String[] SelectDataType = { "日数据", "月数据", "年数据" };

	// 数据项
	public static final String[][] DataTypeFileds = { { "inclination", "倾斜度" },
			{ "gradientAlongLines", "顺线倾斜度 " }, { "lateralTilt", "横向倾斜度 " },
			{ "angle_x", "顺线倾斜角" }, { "angle_y", "横向倾斜角" } };

	private Integer DataTypeId;
	private String FiledId;

	private Integer StartYearId;
	private Integer EndYearId;

	private Integer StartMonthId;
	private Integer EndMonthId;

	
	private   HttpServletResponse  response;

	
	private String getFiledName(String id) {

		String ret = null;
		for (String[] arr : DataTypeFileds) {
			if (arr[0].equals(id)) {
				ret = arr[1];
				break;
			}
		}
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.web.BaseAction#list()
	 */
	@Override
	public String list() {

		List<Map<String, Object>> AnalysisTypeList = new ArrayList<Map<String, Object>>();

		int id = 0;
		for (String arr : SelectDataType) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("DataTypeId", id);
			map.put("DataTypeName", arr);
			AnalysisTypeList.add(map);
			id++;
		}

		request.setAttribute("DataType", AnalysisTypeList);

		List<Map<String, Object>> Fileds = new ArrayList<Map<String, Object>>();
		for (String[] arr0 : DataTypeFileds) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("FiledId", arr0[0]);
			map.put("FiledName", arr0[1]);
			Fileds.add(map);
		}
		request.setAttribute("Fileds", Fileds);

		List<Map<String, Object>> Years = new ArrayList<Map<String, Object>>();
		Calendar cal = Calendar.getInstance();
		int endYear = cal.get(Calendar.YEAR) + 1;
		int startYear = endYear - 5;

		// 年份
		for (int i = startYear; i < endYear; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("YearId", i);
			map.put("YearName", String.valueOf(i) + "年");
			Years.add(map);
		}

		request.setAttribute("Years", Years);

		// 月份
		List<Map<String, Object>> Months = new ArrayList<Map<String, Object>>();
		for (int j = 1; j < 13; j++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("MonthId", j);
			map.put("MonthName", String.valueOf(j) + "月");
			Months.add(map);
		}
		request.setAttribute("Months", Months);

		// 初始化参数
		if (this.DataTypeId == null) {
			this.DataTypeId = 0;
		}
		if (this.FiledId == null) {
			this.FiledId = "inclination";
		}

		if (this.StartYearId == null) {
			this.StartYearId = cal.get(Calendar.YEAR);
		}
		if (this.StartMonthId == null) {
			this.StartMonthId = 1;
		}

		if (this.EndYearId == null) {
			this.EndYearId = cal.get(Calendar.YEAR);
		}
		if (this.EndMonthId == null) {
			this.EndMonthId = cal.get(Calendar.MONTH) + 1;
		}

		request.setAttribute("DataFilledName", getFiledName(this.FiledId));

		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");

		// 月数据
		if (this.DataTypeId == 1) {

			String d1 = String.valueOf(this.StartYearId) + "-"
					+ String.valueOf(this.StartMonthId) + "-01";
			String d2 = String.valueOf(this.EndYearId) + "-"
					+ String.valueOf(this.EndMonthId) + "-01";

			try {
				this.beginTime = fd.parse(d1);
				this.endTime = fd.parse(d2);
			} catch (ParseException e) {

			}

		}// 年数据
		else if (this.DataTypeId == 2) {
			String d1 = String.valueOf(this.StartYearId) + "-01-01";
			String d2 = String.valueOf(this.EndYearId) + "-12-01";

			try {
				this.beginTime = fd.parse(d1);
				this.endTime = fd.parse(d2);
			} catch (ParseException e) {
			}
		}

		if (null != beginTime && null != endTime && beginTime.after(endTime)) {
			request.setAttribute("page", new Page<Map<String, Object>>(0, 20,
					0, new ArrayList<Map<String, Object>>()));
			setErrorMsg("开始时间不能大于结束时间");
			return INPUT;
		}

		Page<Map<String, Object>> pages = this.towerTiltAnalysisService
				.getExtremeValueTowerTiltSamplings(sensor, beginTime, endTime,
						getPageNo(), getPageSize(), this.DataTypeId,
						this.FiledId);

		request.setAttribute("page", pages);

		return SUCCESS;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.base.web.BaseSamplingAction#chart()
	 */
	@Override
	public String chart() {

		Hashtable<RegularTimePeriod, Number> giDatasets = null;
		Hashtable<RegularTimePeriod, Number> gaDatasets = null;
		Hashtable<RegularTimePeriod, Number> ltDatasets = null;

		TimeSeriesChartData giChartData = new TimeSeriesChartData();
		giChartData.setColor(Color.RED);
		giChartData.setLegendText("最大值");
		giChartData.setOrdinateText("最大值");
		giChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
		giDatasets = new Hashtable<RegularTimePeriod, Number>();
		giChartData.setDatasets(giDatasets);
		chartDatas.add(giChartData);

		TimeSeriesChartData gaChartData = new TimeSeriesChartData();
		gaChartData.setColor(Color.GREEN);
		gaChartData.setLegendText("最小值");
		gaChartData.setOrdinateText("最小值");
	    gaChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
		gaDatasets = new Hashtable<RegularTimePeriod, Number>();
		gaChartData.setDatasets(gaDatasets);
		chartDatas.add(gaChartData);

		TimeSeriesChartData ltChartData = new TimeSeriesChartData();
		ltChartData.setColor(Color.BLUE);
		ltChartData.setLegendText("平均值");
		ltChartData.setOrdinateText("平均值");
		ltChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);	
		ltDatasets = new Hashtable<RegularTimePeriod, Number>();
		ltChartData.setDatasets(ltDatasets);

		chartDatas.add(ltChartData);

		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");

		/* 填充数据内容 */
		List<Map<String, Object>> ttSamplings = this.towerTiltAnalysisService
				.getExtremeValueTowerTiltSamplings(sensor, beginTime, endTime,
						this.DataTypeId, this.FiledId);

		Iterator<Map<String, Object>> iterator = ttSamplings.iterator();
		while (iterator.hasNext()) {
			Map<String, Object> map = iterator.next();

			String samplingTime = (String) map.get("samplingTime");
			Double maxValue = (Double) map.get("maxValue");
			Double minValue = (Double) map.get("minValue");
			Double avgValue = (Double) map.get("avgValue");

			Date dt = null;
			try {
				if (this.DataTypeId == 0) {

					dt = fd.parse(samplingTime);
					Day t1 = new Day(dt);

					if (null != maxValue) {
						giDatasets.put(t1, maxValue);
					}

					if (null != minValue) {
						gaDatasets.put(t1, minValue);
					}

					if (null != avgValue) {
						ltDatasets.put(t1, avgValue);
					}

				} else if (this.DataTypeId == 1) {
					dt = fd.parse(samplingTime + "-01");
					Month t2 = new Month(dt);

					if (null != maxValue) {
						giDatasets.put(t2, maxValue);
					}

					if (null != minValue) {
						gaDatasets.put(t2, minValue);
					}

					if (null != avgValue) {
						ltDatasets.put(t2, avgValue);
					}

				} else if (this.DataTypeId == 2) {
					dt = fd.parse(samplingTime + "-01-01");
					Year t3 = new Year(dt);

					if (null != maxValue) {
						giDatasets.put(t3, maxValue);
					}

					if (null != minValue) {
						gaDatasets.put(t3, minValue);
					}

					if (null != avgValue) {
						ltDatasets.put(t3, avgValue);
					}

				}

			} catch (ParseException e) {

			}

		}

		String tile = this.getFiledName(this.FiledId) + "分析曲线图";

		// 生成图形
		this.chart = JFreeChartFactory.createTimeSeriesChart(tile,
				createTimespanSubTitles(), "采样时间", true, chartDatas);

		return  "extremeValuefxChart";
	}

	
	public String exportExcel()
	{
		
		
		List<Map<String, Object>> AnalysisTypeList = new ArrayList<Map<String, Object>>();

		int id = 0;
		for (String arr : SelectDataType) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("DataTypeId", id);
			map.put("DataTypeName", arr);
			AnalysisTypeList.add(map);
			id++;
		}

		request.setAttribute("DataType", AnalysisTypeList);

		List<Map<String, Object>> Fileds = new ArrayList<Map<String, Object>>();
		for (String[] arr0 : DataTypeFileds) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("FiledId", arr0[0]);
			map.put("FiledName", arr0[1]);
			Fileds.add(map);
		}
		request.setAttribute("Fileds", Fileds);

		List<Map<String, Object>> Years = new ArrayList<Map<String, Object>>();
		Calendar cal = Calendar.getInstance();
		int endYear = cal.get(Calendar.YEAR) + 1;
		int startYear = endYear - 5;

		// 年份
		for (int i = startYear; i < endYear; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("YearId", i);
			map.put("YearName", String.valueOf(i) + "年");
			Years.add(map);
		}

		request.setAttribute("Years", Years);

		// 月份
		List<Map<String, Object>> Months = new ArrayList<Map<String, Object>>();
		for (int j = 1; j < 13; j++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("MonthId", j);
			map.put("MonthName", String.valueOf(j) + "月");
			Months.add(map);
		}
		request.setAttribute("Months", Months);

		// 初始化参数
		if (this.DataTypeId == null) {
			this.DataTypeId = 0;
		}
		if (this.FiledId == null) {
			this.FiledId = "inclination";
		}

		if (this.StartYearId == null) {
			this.StartYearId = cal.get(Calendar.YEAR);
		}
		if (this.StartMonthId == null) {
			this.StartMonthId = 1;
		}

		if (this.EndYearId == null) {
			this.EndYearId = cal.get(Calendar.YEAR);
		}
		if (this.EndMonthId == null) {
			this.EndMonthId = cal.get(Calendar.MONTH) + 1;
		}

		request.setAttribute("DataFilledName", getFiledName(this.FiledId));

		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");

		int  exDataType = ContextKeys.DAY_EXPORT;
		
		// 月数据
		if (this.DataTypeId == 1) {

			exDataType = ContextKeys.MONTH_EXPORT;
			
			String d1 = String.valueOf(this.StartYearId) + "-"
					+ String.valueOf(this.StartMonthId) + "-01";
			String d2 = String.valueOf(this.EndYearId) + "-"
					+ String.valueOf(this.EndMonthId) + "-01";

			try {
				this.beginTime = fd.parse(d1);
				this.endTime = fd.parse(d2);
			} catch (ParseException e) {

			}

		}// 年数据
		else if (this.DataTypeId == 2) {
			
			exDataType = ContextKeys.YEAR_EXPORT;
			
			String d1 = String.valueOf(this.StartYearId) + "-01-01";
			String d2 = String.valueOf(this.EndYearId) + "-12-01";

			try {
				this.beginTime = fd.parse(d1);
				this.endTime = fd.parse(d2);
			} catch (ParseException e) {
			}
		}

		if (null != beginTime && null != endTime && beginTime.after(endTime)) {
			request.setAttribute("page", new Page<Map<String, Object>>(0, 20,
					0, new ArrayList<Map<String, Object>>()));
			setErrorMsg("开始时间不能大于结束时间");
			return INPUT;
		}

	
		
		List<Map<String, Object>> ttSamplings = this.towerTiltAnalysisService.getExtremeValueTowerTiltSamplings(sensor, beginTime, endTime,
				this.DataTypeId, this.FiledId);
		
		if ( ttSamplings.size() ==0 ){
			request.setAttribute("page", new Page<Map<String, Object>>(0, 20,
					0, new ArrayList<Map<String, Object>>()));	
			request.setAttribute("errorMessage", "无数据导出");	
			return INPUT;
		}		
		
		Sensor newSensor = this.towerTiltAnalysisService.getSensor(sensor.getSensorId());
	
		String filedname = getFiledName(this.FiledId);
		
		String dts = "";
		if (  exDataType  == ContextKeys.DAY_EXPORT )
			dts ="日";
		else if ( exDataType  == ContextKeys.MONTH_EXPORT )
			dts ="月";
		else if ( exDataType  == ContextKeys.YEAR_EXPORT )
			dts ="年";

		String fname = String.format("%s-%s极值%s数据",newSensor.getSensorCode(),filedname,dts);
	
		try {
			fname = new String( fname.getBytes("gb2312"),"ISO8859-1" );
		} catch (UnsupportedEncodingException e1) {
		
		}
	
		
		try {
			//取得输出流
			OutputStream os = response.getOutputStream();
			//清空输出流
			response.reset();
			//设定输出文件头
			response.setHeader("Content-disposition", "attachment; filename=" + fname + ".xls");
			//定义输出类型
			response.setContentType("application/msexcel;charset=gbk");
		
			
		    boolean ex = this.towerTiltAnalysisService.exportExcel(ContextKeys.EXTRE_EXPORT, newSensor, beginTime, endTime, ttSamplings, os,filedname,exDataType);
		    if (  ex  ){
		       request.setAttribute("succMessage","成功导到EXCEL文件!");
		    } 
		    else
		    {
		     request.setAttribute("errorMessage", "文件导出失败");
		    }
		     
		} catch (IOException e) {
			
		}
		
		return SUCCESS;
	}
	
	
	public Integer getDataTypeId() {
		return DataTypeId;
	}

	public void setDataTypeId(Integer dataTypeId) {
		DataTypeId = dataTypeId;
	}

	public String getFiledId() {
		return FiledId;
	}

	public void setFiledId(String filedId) {
		FiledId = filedId;
	}

	public Integer getStartYearId() {
		return StartYearId;
	}

	public void setStartYearId(Integer startYearId) {
		StartYearId = startYearId;
	}

	public Integer getEndYearId() {
		return EndYearId;
	}

	public void setEndYearId(Integer endYearId) {
		EndYearId = endYearId;
	}

	public Integer getStartMonthId() {
		return StartMonthId;
	}

	public void setStartMonthId(Integer startMonthId) {
		StartMonthId = startMonthId;
	}

	public Integer getEndMonthId() {
		return EndMonthId;
	}

	public void setEndMonthId(Integer endMonthId) {
		EndMonthId = endMonthId;
	}

	public void setTowerTiltAnalysisService(
			TowerTiltAnalysisService towerTiltAnalysisService) {
		this.towerTiltAnalysisService = towerTiltAnalysisService;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response  = response;	
	}

}
