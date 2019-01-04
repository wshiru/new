/*
 * Project platform
 *
 * Classname ContaminationSamplingAction.java
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

package com.yixin.A1000.contamination.web;

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
import com.yixin.A1000.contamination.model.ContaminationSampling;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.util.ContextKeys;
import com.yixin.framework.util.export.ExcelExport;
import com.yixin.framework.util.export.ExcelExportInterface;
import com.yixin.framework.util.jfreechart.JFreeChartFactory;
import com.yixin.framework.util.jfreechart.TimeSeriesChartData;

 

/**
 * 污秽度监测日月年分析曲线类
 * 
 * @version v1.0.0
 * @author 梁立全
 * 
 */
public class ContaminationAnalysisAction extends
		BaseSamplingAction<ContaminationSampling> implements ServletRequestAware,ServletResponseAware {

	/** 序列版本ID */
	private static final long serialVersionUID = 578814206067507554L;

	/** 是否显示盐密 */
	private boolean showEsdd = true;
	
	/** 是否显示灰密 */
	private boolean showNsdd = true;

	/** 是否显示日最高温度 */
	private boolean showDailyMaxTemperature = true;

	/** 是否显示日最低温度 */
	private boolean showDailyMinTemperature = true;

	/** 是否显示日最大湿度 */
	private boolean showDailyMaxHumidity = true;
	
	/** 是否显示日最小湿度 */
	private boolean showDailyMinHumidity = true;	

	
	private   HttpServletResponse  response;


	private Integer StartYearId;
	private Integer StartMonthId;

	private Integer EndYearId;
	private Integer EndMonthId;

	
	public ContaminationAnalysisAction(){
		
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
	
		Page<ContaminationSampling> pages = this.samplingService.getPageSamplings(
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

		Hashtable<RegularTimePeriod, Number> esddDatasets = null;
		Hashtable<RegularTimePeriod, Number> nsddDatasets = null;
		
		Hashtable<RegularTimePeriod, Number> maxTDatasets = null;
		Hashtable<RegularTimePeriod, Number> minTDatasets = null;
		Hashtable<RegularTimePeriod, Number> maxHDatasets = null;
		Hashtable<RegularTimePeriod, Number> minHDatasets = null;


		int chartCount = 0 ;
		/* 设置并添加等盐密数据 */
		if (showEsdd) {
			TimeSeriesChartData esddChartData = new TimeSeriesChartData();
			esddChartData.setColor(Color.RED);
			esddChartData.setLegendText("盐密");
			esddChartData.setOrdinateText("盐密(mg/cm2)");
			//giChartData.setOrdinateLocation((chartCount % 2 ==0) ? AxisLocation.BOTTOM_OR_LEFT : AxisLocation.BOTTOM_OR_LEFT);
			esddChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			esddDatasets = new Hashtable<RegularTimePeriod, Number>();
			esddChartData.setDatasets(esddDatasets);
			chartCount++;
			chartDatas.add(esddChartData);
		}

		/* 设置并添加灰密数据 */
		if (showNsdd) {
			TimeSeriesChartData nsddChartData = new TimeSeriesChartData();
			nsddChartData.setColor(Color.GREEN);
			nsddChartData.setLegendText("灰密");
			nsddChartData.setOrdinateText("灰密(mg/cm2)");			
			nsddChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			nsddDatasets = new Hashtable<RegularTimePeriod, Number>();
			nsddChartData.setDatasets(nsddDatasets);

			chartDatas.add(nsddChartData);
		}
		/* 设置并添加日最高温度数据 */
		if (showDailyMaxTemperature) {
			TimeSeriesChartData maxTChartData = new TimeSeriesChartData();
			maxTChartData.setColor(Color.BLUE);
			maxTChartData.setLegendText("日最高温度");
			maxTChartData.setOrdinateText("温度(℃)");			
			maxTChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
			maxTDatasets = new Hashtable<RegularTimePeriod, Number>();
			maxTChartData.setDatasets(maxTDatasets);

			chartDatas.add(maxTChartData);
		}

		/* 设置并添加日最低温度数据 */
		if (showDailyMinTemperature) {
			TimeSeriesChartData minTChartData = new TimeSeriesChartData();
			minTChartData.setColor(Color.CYAN);
			minTChartData.setLegendText("日最低温度");
			minTChartData.setOrdinateText("温度(℃)");			
			minTChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
			minTDatasets = new Hashtable<RegularTimePeriod, Number>();
			minTChartData.setDatasets(minTDatasets);
			chartDatas.add(minTChartData);
		}

		/* 设置并添加日最大湿度数据 */
		if (showDailyMaxHumidity) {
			TimeSeriesChartData maxHChartData = new TimeSeriesChartData();
			maxHChartData.setColor(Color.MAGENTA);
			maxHChartData.setLegendText("日最大湿度");
			maxHChartData.setOrdinateText("湿度(％RH)");			
			maxHChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
			maxHDatasets = new Hashtable<RegularTimePeriod, Number>();
			maxHChartData.setDatasets(maxHDatasets);
			chartDatas.add(maxHChartData);
		}
		
		/* 设置并添加日最小湿度数据 */
		if (showDailyMinHumidity) {
			TimeSeriesChartData minHChartData = new TimeSeriesChartData();
			minHChartData.setColor(Color.MAGENTA);
			minHChartData.setLegendText("日最小湿度");
			minHChartData.setOrdinateText("湿度(％RH)");			
			minHChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
			minHDatasets = new Hashtable<RegularTimePeriod, Number>();
			minHChartData.setDatasets(minHDatasets);
			chartDatas.add(minHChartData);
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
		List<ContaminationSampling> ttSamplings = this.samplingService
				.getSamplings(sensor, beginTime, endTime);

		Iterator<ContaminationSampling> iterator = ttSamplings.iterator();
		while (iterator.hasNext()) {
			ContaminationSampling sampling = (ContaminationSampling) iterator.next();
			Minute t = new Minute(sampling.getSamplingTime());
			
			if (showEsdd) {
				Double gi = sampling.getEsdd();
				if (null != gi)
					esddDatasets.put(t, gi);
			}

			if (showNsdd) {
				Double te = sampling.getNsdd();
				if (null != te)
					nsddDatasets.put(t, te);
			}

			if (showDailyMaxTemperature) {
				Double td = sampling.getDailyMaxTemperature();
				if (null != td)
					maxTDatasets.put(t, td);
			}

			if (showDailyMinTemperature) {
				Double wya = sampling.getDailyMinTemperature();
				if (null != wya)
					minTDatasets.put(t, wya);
			}

			if (showDailyMaxHumidity) {
				Double da = sampling.getDailyMaxHumidity();
				if (null != da)
					maxHDatasets.put(t, da);
			}
			
			if (showDailyMinHumidity) {
				Double da = sampling.getDailyMinHumidity();
				if (null != da)
					minHDatasets.put(t, da);
			}			
			 
		}


		String fname = String.format("%s-%s",sensor.getSensorCode(),titlename);
		
		// 生成图形
		this.chart = JFreeChartFactory.createTimeSeriesChart("污秽度监测"+titlename+"分析曲线图",
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
		
		List<ContaminationSampling> ttSamplings = this.samplingService.getSamplings(sensor, beginTime, endTime);

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
			final Iterator<ContaminationSampling> it= ttSamplings.iterator();
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
						ContaminationSampling p = it.next();
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
	
	
	public boolean isShowEsdd() {
		return showEsdd;
	}

	public void setShowEsdd(boolean showEsdd) {
		this.showEsdd = showEsdd;
	}

	public boolean isShowNsdd() {
		return showNsdd;
	}

	public void setShowNsdd(boolean showNsdd) {
		this.showNsdd = showNsdd;
	}

	public boolean isShowDailyMaxTemperature() {
		return showDailyMaxTemperature;
	}

	public void setShowDailyMaxTemperature(boolean showDailyMaxTemperature) {
		this.showDailyMaxTemperature = showDailyMaxTemperature;
	}

	public boolean isShowDailyMinTemperature() {
		return showDailyMinTemperature;
	}

	public void setShowDailyMinTemperature(boolean showDailyMinTemperature) {
		this.showDailyMinTemperature = showDailyMinTemperature;
	}

	public boolean isShowDailyMaxHumidity() {
		return showDailyMaxHumidity;
	}

	public void setShowDailyMaxHumidity(boolean showDailyMaxHumidity) {
		this.showDailyMaxHumidity = showDailyMaxHumidity;
	}

	public boolean isShowDailyMinHumidity() {
		return showDailyMinHumidity;
	}

	public void setShowDailyMinHumidity(boolean showDailyMinHumidity) {
		this.showDailyMinHumidity = showDailyMinHumidity;
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
