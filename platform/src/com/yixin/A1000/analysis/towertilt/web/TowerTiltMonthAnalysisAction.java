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
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;

import com.yixin.A1000.analysis.towertilt.service.TowerTiltAnalysisService;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.web.BaseSamplingAction;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.util.ContextKeys;
import com.yixin.framework.util.jfreechart.JFreeChartFactory;
import com.yixin.framework.util.jfreechart.TimeSeriesChartData;

/**
 * 杆塔倾斜月分析曲线类
 * 
 * @version v1.0.0
 * @author 梁立全
 * 
 */
public class TowerTiltMonthAnalysisAction extends
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

	private Integer StartYearId;
	private Integer StartMonthId;

	private Integer EndYearId;
	private Integer EndMonthId;
	
	private   HttpServletResponse  response;

	

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

	private TowerTiltAnalysisService towerTiltAnalysisService;

	public void setTowerTiltAnalysisService(
			TowerTiltAnalysisService towerTiltAnalysisService) {
		this.towerTiltAnalysisService = towerTiltAnalysisService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.web.BaseAction#list()
	 */
	@Override
	public String list() {

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

		if (this.StartYearId == null) {
			this.StartYearId = cal.get(Calendar.YEAR);
		}
		if (this.StartMonthId == null) {
			this.StartMonthId = cal.get(Calendar.MONTH) + 1;
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

		if (null != beginTime && null != endTime && beginTime.after(endTime)) {
			request.setAttribute("page", new Page<Map<String, Object>>(0, 20,
					0, new ArrayList<Map<String, Object>>()));
			setErrorMsg("开始时间不能大于结束时间");
			return INPUT;
		}

		Page<Map<String, Object>> pages = this.towerTiltAnalysisService
				.getMonthTowerTiltSamplings(sensor, beginTime, endTime,
						getPageNo(), getPageSize());
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
			giChartData.setOrdinateText("倾斜度(°)");
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
			gaChartData.setOrdinateText("顺线倾斜度(°)");
			gaChartData
					.setOrdinateLocation(showIL ? AxisLocation.BOTTOM_OR_RIGHT
							: AxisLocation.BOTTOM_OR_LEFT);
			gaDatasets = new Hashtable<RegularTimePeriod, Number>();
			gaChartData.setDatasets(gaDatasets);

			chartDatas.add(gaChartData);
		}
		/* 设置并添加横向倾斜度数据 */
		if (showLT) {
			TimeSeriesChartData ltChartData = new TimeSeriesChartData();
			ltChartData.setColor(Color.BLUE);
			ltChartData.setLegendText("横向倾斜度");
			ltChartData.setOrdinateText("横向倾斜度(°)");
			ltChartData
					.setOrdinateLocation(showIL || showGA ? AxisLocation.BOTTOM_OR_RIGHT
							: AxisLocation.BOTTOM_OR_LEFT);
			ltDatasets = new Hashtable<RegularTimePeriod, Number>();
			ltChartData.setDatasets(ltDatasets);

			chartDatas.add(ltChartData);
		}

		/* 设置并添加顺线倾斜角数据 */
		if (showAX) {
			TimeSeriesChartData axChartData = new TimeSeriesChartData();
			axChartData.setColor(Color.CYAN);
			axChartData.setLegendText("顺线倾斜角");
			axChartData.setOrdinateText("顺线倾斜角");
			axChartData
					.setOrdinateLocation(showIL || showGA ? AxisLocation.BOTTOM_OR_RIGHT
							: AxisLocation.BOTTOM_OR_LEFT);
			axDatasets = new Hashtable<RegularTimePeriod, Number>();
			axChartData.setDatasets(axDatasets);
			chartDatas.add(axChartData);
		}

		/* 设置并添加横向倾斜角数据 */
		if (showAY) {
			TimeSeriesChartData ayChartData = new TimeSeriesChartData();
			ayChartData.setColor(Color.MAGENTA);
			ayChartData.setLegendText("横向倾斜角");
			ayChartData.setOrdinateText("横向倾斜角");
			ayChartData
					.setOrdinateLocation(showIL || showGA ? AxisLocation.BOTTOM_OR_RIGHT
							: AxisLocation.BOTTOM_OR_LEFT);
			ayDatasets = new Hashtable<RegularTimePeriod, Number>();
			ayChartData.setDatasets(ayDatasets);
			chartDatas.add(ayChartData);
		}

		/* 填充数据内容 */
		List<Map<String, Object>> ttSamplings = this.towerTiltAnalysisService.getMonthTowerTiltSamplings(sensor, beginTime, endTime);

		
		Iterator<Map<String, Object>> iterator = ttSamplings.iterator();
		while (iterator.hasNext()) {
			Map<String, Object> map = iterator.next();

			Date samplingTime = (Date) map.get("samplingTime");

			Double gi = (Double) map.get("inclination");
			Double ga = (Double) map.get("gradientAlongLines");
			Double lt = (Double) map.get("lateralTilt");
			Double ax = (Double) map.get("angle_x");
			Double ay = (Double) map.get("angle_y");

			Month t = new Month(samplingTime);

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

		// 生成图形
		this.chart = JFreeChartFactory.createTimeSeriesChart("杆塔倾斜月数据分析曲线图",
				createTimespanSubTitles(), "采样时间", true, chartDatas);

		return "monthfxChart";
	}

	
	public String exportExcel()
	{
		
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

		if (this.StartYearId == null) {
			this.StartYearId = cal.get(Calendar.YEAR);
		}
		if (this.StartMonthId == null) {
			this.StartMonthId = cal.get(Calendar.MONTH) + 1;
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

		if (null != beginTime && null != endTime && beginTime.after(endTime)) {
			request.setAttribute("page", new Page<Map<String, Object>>(0, 20,
					0, new ArrayList<Map<String, Object>>()));
			setErrorMsg("开始时间不能大于结束时间");
			return INPUT;
		}

		List<Map<String, Object>> ttSamplings = this.towerTiltAnalysisService.getMonthTowerTiltSamplings(sensor, this.beginTime, this.endTime);

		
	
		if ( ttSamplings.size() ==0 ){
			request.setAttribute("page", new Page<Map<String, Object>>(0, 20,
					0, new ArrayList<Map<String, Object>>()));	
			request.setAttribute("errorMessage", "无数据导出");	
			return INPUT;
		}		
		

		Sensor newSensor = this.towerTiltAnalysisService.getSensor(sensor.getSensorId());
		
		String fname = String.format("%s-月数据",newSensor.getSensorCode());
		
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
		      	
		    boolean ex = this.towerTiltAnalysisService.exportExcel(ContextKeys.MONTH_EXPORT, newSensor, beginTime, endTime, ttSamplings, os,null,ContextKeys.NOTHING);
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

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

}
