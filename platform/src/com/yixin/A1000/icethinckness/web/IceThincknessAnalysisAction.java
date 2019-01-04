/*
 * Project platform
 *
 * Classname IceThincknessSamplingAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 16:04
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */

package com.yixin.A1000.icethinckness.web;

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
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;

import com.yixin.A1000.base.utils.SamplingDataType;
import com.yixin.A1000.base.web.BaseSamplingAction;
import com.yixin.A1000.icethinckness.model.IceThincknessSampling;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.util.ContextKeys;
import com.yixin.framework.util.export.ExcelExport;
import com.yixin.framework.util.export.ExcelExportInterface;
import com.yixin.framework.util.jfreechart.JFreeChartFactory;
import com.yixin.framework.util.jfreechart.TimeSeriesChartData;

/**
 * 覆冰监测日月年分析曲线类
 * 
 * @version v1.0.0
 * @author 梁立全
 * 
 */
public class IceThincknessAnalysisAction extends
		BaseSamplingAction<IceThincknessSampling> implements ServletRequestAware,ServletResponseAware {

	/** 序列版本ID */
	private static final long serialVersionUID = 578814206067507554L;

	/** 是否显示等值覆冰厚度 */
	private boolean showEqualIceThickness = true;
	
	/** 是否显示综合悬挂载荷 */
	private boolean showTension = true;

	/** 是否显示不均衡张力差 */
	private boolean showTensionDifference = true;

	/** 是否显示绝缘子串风偏角 */
	private boolean showWindageYawAngle = true;

	/** 是否显示绝缘子串偏斜角 */
	private boolean showDeflectionAngle = true;

	
	private   HttpServletResponse  response;


	private Integer StartYearId;
	private Integer StartMonthId;

	private Integer EndYearId;
	private Integer EndMonthId;

	
	public IceThincknessAnalysisAction(){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());		
		calendar.add(Calendar.DATE, -7) ;
				
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
		String s1 = fd.format(calendar.getTime());
		Date  d1 = calendar.getTime();
		 
		try {
			d1 = fd.parse(s1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		beginTime = d1; 
		
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1) ;
		endTime = calendar.getTime();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.web.BaseAction#list()
	 */
 
	private void getMonthParameter(){
		//默认查询12个月值
		List<Map<String, Object>> Years = new ArrayList<Map<String, Object>>();

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int endYear = cal.get(Calendar.YEAR) + 1;
		int startYear = endYear - 15;

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

		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");

		String d1 = String.valueOf(this.StartYearId) + "-"
				+ String.valueOf(this.StartMonthId) + "-01";
		String d2 = String.valueOf(this.EndYearId) + "-"
				+ String.valueOf(this.EndMonthId) + "-01";

		try {
			this.beginTime = fd.parse(d1);
			this.endTime = fd.parse(d2);
		} catch (ParseException e) {
		}		
	}
	
	private void getYearParameter(){
		List<Map<String, Object>> Years = new ArrayList<Map<String, Object>>();

		Calendar cal = Calendar.getInstance();
		int endYear = cal.get(Calendar.YEAR) + 1;
		int startYear = endYear - 15;

		// 年份
		for (int i = startYear; i < endYear; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("YearId", i);
			map.put("YearName", String.valueOf(i) + "年");
			Years.add(map);
		}
		request.setAttribute("Years", Years);

		if (this.StartYearId == null) {
			this.StartYearId = cal.get(Calendar.YEAR) - 5;
		}

		if (this.EndYearId == null) {
			this.EndYearId = cal.get(Calendar.YEAR);
		}

		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
		String d1 = String.valueOf(this.StartYearId) + "-01-01";
		String d2 = String.valueOf(this.EndYearId) + "-12-01";

		try {
			this.beginTime = fd.parse(d1);
			this.endTime = fd.parse(d2);
		} catch (ParseException e) {
		}		
	}
	
	public String dayList() {
		//默认查询一周值
		if(null == beginTime){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, -7) ;
			beginTime = calendar.getTime();
		}
		if(null == endTime){
			endTime = new Date();
		}
		return list(ContextKeys.DAY_EXPORT);
	}
	
	
	public String monthList() {
		getMonthParameter();
		return list(ContextKeys.MONTH_EXPORT);
	}
	
	public String yearList() {
		getYearParameter();		
		return list(ContextKeys.YEAR_EXPORT);
	}
	
	public String list(Integer listType){
		
		if (beginTime.after(endTime)) {
			request.setAttribute("page", new Page<Map<String, Object>>(0, 20,
					0, new ArrayList<Map<String, Object>>()));
			setErrorMsg("开始时间不能大于结束时间");
			return INPUT;
		}

		switch (listType) {
		case ContextKeys.DAY_EXPORT:
				samplingService.setSamplingDataType(SamplingDataType.DAY);
				break;			
		case ContextKeys.MONTH_EXPORT:
				samplingService.setSamplingDataType(SamplingDataType.MONTH);
				break;			
		case ContextKeys.YEAR_EXPORT:
				samplingService.setSamplingDataType(SamplingDataType.YEAR);
				break;			
		default:
			break;
		}		
	
		Page<IceThincknessSampling> pages = this.samplingService.getPageSamplings(
				sensor, beginTime, endTime,
						getPageNo(), getPageSize());
		request.setAttribute("page", pages);

		return SUCCESS;

	}

 
	public String dayChart() {
		return chart(ContextKeys.DAY_EXPORT);
	}
	
	public String monthChart() {
		return chart(ContextKeys.MONTH_EXPORT);
	}
	
	public String yearChart() {
		return chart(ContextKeys.YEAR_EXPORT);
	}
	
	public String chart(Integer chartType){

		this.chartDatas.clear();

		Hashtable<RegularTimePeriod, Number> etDatasets = null;
		Hashtable<RegularTimePeriod, Number> tDatasets = null;
		Hashtable<RegularTimePeriod, Number> tdDatasets = null;

		Hashtable<RegularTimePeriod, Number> wyaDatasets = null;
		Hashtable<RegularTimePeriod, Number> daDatasets = null;

		boolean showET = showEqualIceThickness;// != null && showEqualIceThickness? true:
											// false;

		boolean showT = showTension;// !=null && showTension? true: false;

		boolean showTD = showTensionDifference;// !=null && showTensionDifference? true: false;

		boolean showWYA = showWindageYawAngle;// !=null &&
												// showWindageYawAngle? true:
												// false;

		boolean showDA = showDeflectionAngle;// !=null && showDeflectionAngle? true:
											// false;
		int chartCount = 0 ;
		/* 设置并添加等值覆冰厚度数据 */
		if (showET) {
			TimeSeriesChartData etChartData = new TimeSeriesChartData();
			etChartData.setColor(Color.RED);
			etChartData.setLegendText("等值覆冰厚度");
			etChartData.setOrdinateText("等值覆冰厚度(mm)");
			//giChartData.setOrdinateLocation((chartCount % 2 ==0) ? AxisLocation.BOTTOM_OR_LEFT : AxisLocation.BOTTOM_OR_LEFT);
			etChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			etDatasets = new Hashtable<RegularTimePeriod, Number>();
			etChartData.setDatasets(etDatasets);
			chartCount++;
			chartDatas.add(etChartData);
		}

		/* 设置并添加综合悬挂载荷数据 */
		if (showT) {
			TimeSeriesChartData tChartData = new TimeSeriesChartData();
			tChartData.setColor(Color.GREEN);
			tChartData.setLegendText("综合悬挂载荷");
			tChartData.setOrdinateText("综合悬挂载荷(N)");			
			tChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			tDatasets = new Hashtable<RegularTimePeriod, Number>();
			tChartData.setDatasets(tDatasets);

			chartDatas.add(tChartData);
		}
		/* 设置并添加不均衡张力差数据 */
		if (showTD) {
			TimeSeriesChartData tdChartData = new TimeSeriesChartData();
			tdChartData.setColor(Color.BLUE);
			tdChartData.setLegendText("不均衡张力差");
			tdChartData.setOrdinateText("不均衡张力差(N)");			
			tdChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			tdDatasets = new Hashtable<RegularTimePeriod, Number>();
			tdChartData.setDatasets(tdDatasets);

			chartDatas.add(tdChartData);
		}

		/* 设置并添加绝缘子串风偏角数据 */
		if (showWYA) {
			TimeSeriesChartData wyaChartData = new TimeSeriesChartData();
			wyaChartData.setColor(Color.CYAN);
			wyaChartData.setLegendText("绝缘子串风偏角");
			wyaChartData.setOrdinateText("绝缘子串风偏角(°)");			
			wyaChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
			wyaDatasets = new Hashtable<RegularTimePeriod, Number>();
			wyaChartData.setDatasets(wyaDatasets);
			chartDatas.add(wyaChartData);
		}

		/* 设置并添加绝缘子串偏斜角数据 */
		if (showDA) {
			TimeSeriesChartData daChartData = new TimeSeriesChartData();
			daChartData.setColor(Color.MAGENTA);
			daChartData.setLegendText("绝缘子串偏斜角");
			daChartData.setOrdinateText("绝缘子串偏斜角(°)");			
			daChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
			daDatasets = new Hashtable<RegularTimePeriod, Number>();
			daChartData.setDatasets(daDatasets);
			chartDatas.add(daChartData);
		}


		String titlename = "";
		switch (chartType) {
		case ContextKeys.DAY_EXPORT:
				titlename="日数据";
				samplingService.setSamplingDataType(SamplingDataType.DAY);
				
				//默认查询一周值
				if(null == beginTime){
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(new Date());
					calendar.add(Calendar.DATE, -7) ;
					beginTime = calendar.getTime();
				}
				if(null == endTime){
					endTime = new Date();
				}
				
				break;			
		case ContextKeys.MONTH_EXPORT:
				titlename="月数据";
				samplingService.setSamplingDataType(SamplingDataType.MONTH);
				break;			
		case ContextKeys.YEAR_EXPORT:
				titlename="年数据";
				samplingService.setSamplingDataType(SamplingDataType.YEAR);
				break;			
		default:
			break;
		}		
		
		/* 填充数据内容 */
		List<IceThincknessSampling> ttSamplings = this.samplingService.getSamplings(sensor, beginTime, endTime);

		Iterator<IceThincknessSampling> iterator = ttSamplings.iterator();
		while (iterator.hasNext()) {
			IceThincknessSampling sampling = (IceThincknessSampling) iterator.next();
			Minute t = new Minute(sampling.getSamplingTime());
			 
			if (showET) {
				Double gi = sampling.getEqualIceThickness();
				if (null != gi)
					etDatasets.put(t, gi);
			}

			if (showT) {
				Double te = sampling.getTension();
				if (null != te)
					tDatasets.put(t, te);
			}

			if (showTD) {
				Double td = sampling.getTensionDifference();
				if (null != td)
					tdDatasets.put(t, td);
			}

			if (showWYA) {
				Double wya = sampling.getWindageYawAngle();
				if (null != wya)
					wyaDatasets.put(t, wya);
			}

			if (showDA) {
				Double da = sampling.getDeflectionAngle();
				if (null != da)
					daDatasets.put(t, da);
			}
			 
		}


		String fname = String.format("%s-%s",sensor.getSensorCode(),titlename);
		
		// 生成图形
		this.chart = JFreeChartFactory.createTimeSeriesChart("覆冰监测"+titlename+"分析曲线图",
				createTimespanSubTitles(), "采样时间", true, chartDatas);

		return "chart";

	}

	public String dayExportExcel(){
		return exportExcel(ContextKeys.DAY_EXPORT);
	}
	
	public String monthExportExcel(){
		return exportExcel(ContextKeys.MONTH_EXPORT);
	}
	
	public String yearExportExcel(){
		return exportExcel(ContextKeys.YEAR_EXPORT);
	}

	public String exportExcel(Integer exportType)
	{
		
		if (null != beginTime && null != endTime && beginTime.after(endTime)) {
			request.setAttribute("page", new Page<Map<String, Object>>(0, 20,
					0, new ArrayList<Map<String, Object>>()));
			setErrorMsg("开始时间不能大于结束时间");
			return INPUT;
		}

		String titlename = "";
		switch (exportType) {
		case ContextKeys.DAY_EXPORT:
			titlename="日数据";
			samplingService.setSamplingDataType(SamplingDataType.DAY);
			break;			
		case ContextKeys.MONTH_EXPORT:
			titlename="月数据";
			samplingService.setSamplingDataType(SamplingDataType.MONTH);
			break;			
		case ContextKeys.YEAR_EXPORT:
			titlename="年数据";
			samplingService.setSamplingDataType(SamplingDataType.YEAR);
			break;			
		default:
			break;
		}
		
		List<IceThincknessSampling> ttSamplings = this.samplingService.getSamplings(sensor, beginTime, endTime);

		if ( ttSamplings.size() ==0 ){
			request.setAttribute("page", new Page<Map<String, Object>>(0, 20,
					0, new ArrayList<Map<String, Object>>()));	
			request.setAttribute("errorMessage", "无数据导出");	
			return INPUT;
		}		
		

		String fname = String.format("%s-%s",sensor.getSensorCode(),titlename);
		
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

			ExcelExport ee = new ExcelExport();
			final Iterator<IceThincknessSampling> it= ttSamplings.iterator();
			final String tn = titlename;
			boolean ex = ee.buileExcelStream(os, new ExcelExportInterface(){

				@Override
				public List<String> addTitle(Integer row) {
					String s = "地质滑坡"+tn +";;;;;";
					return ExcelExport.Array2List(s.split(";"));
				}

				@Override
				public List<String> addHead(Integer row) {
					String s = "采集时间;倾斜度;顺线倾斜度;横向倾斜度;顺线倾斜角(°) ;横向倾斜角(°)";
					return ExcelExport.Array2List(s.split(";")); 
				}

				@Override
				public List<String> addRow(Integer row) {
					List<String> ls = null;
					if(it.hasNext()){
						IceThincknessSampling p = it.next();
						ls = new ArrayList<String>();
						/*
						ExcelExport.addObject(ls, p.getSamplingTime());
						ExcelExport.addObject(ls, p.getInclination());
						ExcelExport.addObject(ls, p.getGradientAlongLines());
						ExcelExport.addObject(ls, p.getLateralTilt());
						ExcelExport.addObject(ls, p.getAngleX());
						ExcelExport.addObject(ls, p.getAngleY());
						*/ 
					}				
					return ls;
				}

				@Override
				public List<String> addFoot(Integer row) {
					return null;
				}
				
				
			});
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
	
	
	public boolean isShowEqualIceThickness() {
		return showEqualIceThickness;
	}

	public void setShowEqualIceThickness(boolean showEqualIceThickness) {
		this.showEqualIceThickness = showEqualIceThickness;
	}

	public boolean isShowTension() {
		return showTension;
	}

	public void setShowTension(boolean showTension) {
		this.showTension = showTension;
	}

	public boolean isShowTensionDifference() {
		return showTensionDifference;
	}

	public void setShowTensionDifference(boolean showTensionDifference) {
		this.showTensionDifference = showTensionDifference;
	}

	public boolean isShowWindageYawAngle() {
		return showWindageYawAngle;
	}

	public void setShowWindageYawAngle(boolean showWindageYawAngle) {
		this.showWindageYawAngle = showWindageYawAngle;
	}

	public boolean isShowDeflectionAngle() {
		return showDeflectionAngle;
	}

	public void setShowDeflectionAngle(boolean showDeflectionAngle) {
		this.showDeflectionAngle = showDeflectionAngle;
	}

	public Integer getStartYearId() {
		return StartYearId;
	}

	public void setStartYearId(Integer startYearId) {
		StartYearId = startYearId;
	}

	public Integer getStartMonthId() {
		return StartMonthId;
	}

	public void setStartMonthId(Integer startMonthId) {
		StartMonthId = startMonthId;
	}

	public Integer getEndYearId() {
		return EndYearId;
	}

	public void setEndYearId(Integer endYearId) {
		EndYearId = endYearId;
	}

	public Integer getEndMonthId() {
		return EndMonthId;
	}

	public void setEndMonthId(Integer endMonthId) {
		EndMonthId = endMonthId;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;	
	}
}
