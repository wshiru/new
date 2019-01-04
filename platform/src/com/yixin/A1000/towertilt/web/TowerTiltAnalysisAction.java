/*
 * Project platform
 *
 * Classname TowerTiltSamplingAction.java
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

package com.yixin.A1000.towertilt.web;

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
import org.jfree.data.time.RegularTimePeriod;

import com.yixin.A1000.base.utils.SamplingDataType;
import com.yixin.A1000.base.web.BaseSamplingAction;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.util.ContextKeys;
import com.yixin.framework.util.export.ExcelExport;
import com.yixin.framework.util.export.ExcelExportInterface;
import com.yixin.framework.util.jfreechart.JFreeChartFactory;
import com.yixin.framework.util.jfreechart.TimeSeriesChartData;

/**
 * 杆塔倾斜日月年分析曲线类
 * 
 * @version v1.0.0
 * @author 梁立全
 * 
 */
public class TowerTiltAnalysisAction extends
		BaseSamplingAction<TowerTiltSampling> implements ServletRequestAware,ServletResponseAware {

	/** 序列版本ID */
	private static final long serialVersionUID = 578814206067507554L;

	/** 是否显示倾斜度 */
	private boolean showInclination = true;

	/** 是否显示顺线倾斜度 */
	private boolean showGradientAlongLines = true;

	/** 是否显示横向倾斜度 */
	private boolean showLateralTilt = true;

	/** 是否显示顺线倾斜角 */
	private boolean showAngle_x = true;

	/** 是否显示横向倾斜角 */
	private boolean showAngle_y = true;

	
	private   HttpServletResponse  response;


	private Integer StartYearId;
	private Integer StartMonthId;

	private Integer EndYearId;
	private Integer EndMonthId;

	
	public TowerTiltAnalysisAction(){
		
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
	
		Page<TowerTiltSampling> pages = this.samplingService.getPageSamplings(
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

		Hashtable<RegularTimePeriod, Number> giDatasets = null;
		Hashtable<RegularTimePeriod, Number> gaDatasets = null;
		Hashtable<RegularTimePeriod, Number> ltDatasets = null;

		Hashtable<RegularTimePeriod, Number> axDatasets = null;
		Hashtable<RegularTimePeriod, Number> ayDatasets = null;

		boolean showIL = showInclination;// != null && showInclination? true:
											// false;

		boolean showAX = showAngle_x;// !=null && showAngle_x? true: false;

		boolean showAY = showAngle_y;// !=null && showAngle_y? true: false;

		boolean showGA = showGradientAlongLines;// !=null &&
												// showGradientAlongLines? true:
												// false;

		boolean showLT = showLateralTilt;// !=null && showLateralTilt? true:
											// false;

		/* 设置并添加倾斜度数据 */
		if (showIL) {
			TimeSeriesChartData giChartData = new TimeSeriesChartData();
			giChartData.setColor(Color.RED);
			giChartData.setLegendText("倾斜度");
			giChartData.setOrdinateText("倾斜度(mm/m)");
			giChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			giDatasets = new Hashtable<RegularTimePeriod, Number>();
			giChartData.setDatasets(giDatasets);

			chartDatas.add(giChartData);
		}

		/* 设置并添加顺线倾斜度数据 */
		if (showGA) {
			TimeSeriesChartData gaChartData = new TimeSeriesChartData();
			gaChartData.setColor(Color.GREEN);
			gaChartData.setLegendText("顺线倾斜度");
			gaChartData.setOrdinateText("倾斜度(mm/m)");
			gaChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			gaDatasets = new Hashtable<RegularTimePeriod, Number>();
			gaChartData.setDatasets(gaDatasets);

			chartDatas.add(gaChartData);
		}
		/* 设置并添加横向倾斜度数据 */
		if (showLT) {
			TimeSeriesChartData ltChartData = new TimeSeriesChartData();
			ltChartData.setColor(Color.BLUE);
			ltChartData.setLegendText("横向倾斜度");
			ltChartData.setOrdinateText("倾斜度(mm/m)");
			ltChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			ltDatasets = new Hashtable<RegularTimePeriod, Number>();
			ltChartData.setDatasets(ltDatasets);

			chartDatas.add(ltChartData);
		}

		/* 设置并添加顺线倾斜角数据 */
		if (showAX) {
			TimeSeriesChartData axChartData = new TimeSeriesChartData();
			axChartData.setColor(Color.CYAN);
			axChartData.setLegendText("顺线倾斜角");
			axChartData.setOrdinateText("倾斜角(°)");
			axChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
			axDatasets = new Hashtable<RegularTimePeriod, Number>();
			axChartData.setDatasets(axDatasets);
			chartDatas.add(axChartData);
		}

		/* 设置并添加横向倾斜角数据 */
		if (showAY) {
			TimeSeriesChartData ayChartData = new TimeSeriesChartData();
			ayChartData.setColor(Color.MAGENTA);
			ayChartData.setLegendText("横向倾斜角");
			ayChartData.setOrdinateText("倾斜角(°)");
			ayChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_RIGHT);
			ayDatasets = new Hashtable<RegularTimePeriod, Number>();
			ayChartData.setDatasets(ayDatasets);
			chartDatas.add(ayChartData);
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
		List<TowerTiltSampling> ttSamplings = this.samplingService.getSamplings(sensor, beginTime, endTime);

		Iterator<TowerTiltSampling> iterator = ttSamplings.iterator();
		while (iterator.hasNext()) {
			TowerTiltSampling towerTiltSampling = iterator.next();

			Date samplingTime = towerTiltSampling.getSamplingTime();
			Double gi = towerTiltSampling.getInclination();
			Double ga = towerTiltSampling.getGradientAlongLines();
			Double lt = towerTiltSampling.getLateralTilt();
			Double ax = towerTiltSampling.getAngleX();
			Double ay = towerTiltSampling.getAngleY();

			Day t = new Day(samplingTime);

			if (showIL) {
				if (null != gi)
					giDatasets.put(t, gi);
			}

			if (showGA) {
				if (null != ga)
					gaDatasets.put(t, ga);
			}

			if (showLT) {
				if (null != lt)
					ltDatasets.put(t, lt);
			}

			if (showAX) {
				if (null != ax)
					axDatasets.put(t, ax);
			}

			if (showAY) {
				if (null != ay)
					ayDatasets.put(t, ay);
			}

		}


		String fname = String.format("%s-%s",sensor.getSensorCode(),titlename);
		
		// 生成图形
		this.chart = JFreeChartFactory.createTimeSeriesChart("杆塔倾斜"+titlename+"分析曲线图",
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
		
		List<TowerTiltSampling> ttSamplings = this.samplingService.getSamplings(sensor, beginTime, endTime);

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
			final Iterator<TowerTiltSampling> it= ttSamplings.iterator();
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
						TowerTiltSampling p = it.next();
						ls = new ArrayList<String>();
						ExcelExport.addObject(ls, p.getSamplingTime());
						ExcelExport.addObject(ls, p.getInclination());
						ExcelExport.addObject(ls, p.getGradientAlongLines());
						ExcelExport.addObject(ls, p.getLateralTilt());
						ExcelExport.addObject(ls, p.getAngleX());
						ExcelExport.addObject(ls, p.getAngleY()); 
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
	
	
	public boolean isShowInclination() {
		return showInclination;
	}

	public void setShowInclination(boolean showInclination) {
		this.showInclination = showInclination;
	}

	public boolean isShowGradientAlongLines() {
		return showGradientAlongLines;
	}

	public void setShowGradientAlongLines(boolean showGradientAlongLines) {
		this.showGradientAlongLines = showGradientAlongLines;
	}

	public boolean isShowLateralTilt() {
		return showLateralTilt;
	}

	public void setShowLateralTilt(boolean showLateralTilt) {
		this.showLateralTilt = showLateralTilt;
	}

	public boolean isShowAngle_x() {
		return showAngle_x;
	}

	public void setShowAngle_x(boolean showAngle_x) {
		this.showAngle_x = showAngle_x;
	}

	public boolean isShowAngle_y() {
		return showAngle_y;
	}

	public void setShowAngle_y(boolean showAngle_y) {
		this.showAngle_y = showAngle_y;
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
