/*
 * Project platform
 *
 * Classname JFreeChartAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-21 13：54
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.base.web;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;

import com.yixin.A1000.common.constant.SystemConstants;
import com.yixin.framework.util.jfreechart.TimeSeriesChartData;


/**
 * JfreeChart制图Action基类
 * 
 * @version v1.0.0
 * @author 
 */
public class JFreeChartAction<T extends Object> extends BaseAction<T> {
	
	/** 序列版本ID */
	private static final long serialVersionUID = 5053386711260264350L;
	
	/** 图形Action返回值  */
	protected static final String CHART = "chart";
	
	/** 开始时间 */
	protected Date beginTime;
	/** 结束时间 */
	protected Date endTime;
	

	/** JFree图形对象  */
	protected JFreeChart chart;

	/** 曲线数据集*/
	protected List<TimeSeriesChartData> chartDatas = new ArrayList<TimeSeriesChartData>();

	
	/** ***/
	public  JFreeChartAction(){
		//Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.DATE, -7); 
		/*  
		SimpleDateFormat fds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
		String s1 = fd.format(new Date()) +" 00:00:00";
		Date  d1 = new  Date();

		try {
			d1 = fds.parse(s1);
		} catch (ParseException e) {
		}
		
		this.beginTime = d1;
		
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1) ;
		endTime = calendar.getTime();
		*/
		 
	}
	
	/**
	 * 获取制图最大记录数
	 * @return
	 */
	public long getMaxChartRecords() {
		return SystemConstants.MAX_CHART_RECORDS;
	}
	
	/**
	 * 获取开始时间
	 * @return
	 */
	public Date getBeginTime() {	
		return beginTime;
	   
	}
	
	/**
	 * 设置开始时间
	 * @return
	 */
	public void setBeginTime(Date beginTime) {
		if ( beginTime == null ){
			beginTime = new Date();
		}
		this.beginTime = beginTime;
		
	}
	
	/**
	 * 获取结束时间
	 * @return
	 */
	public Date getEndTime() {
		return endTime;
	}
	
	/**
	 * 设置结束时间
	 * @param endTime
	 */
	public void setEndTime(Date endTime) {
		if ( endTime == null ){
			endTime = new Date();
		}
		this.endTime = endTime;
	}

	/**
	 * 获取图形 
	 * @return
	 */
	public JFreeChart getChart() {
		return chart;
	}
	
	/**
	 * 生成曲线图
	 * @return
	 */
	public String chart(){
		return CHART;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#list()
	 */
	@Override
	public String list() {	
		if(null != beginTime && null != endTime && beginTime.after(endTime))
			setErrorMsg("开始时间不能大于结束时间");
		return super.list();
	}

	/**
	 * 
	 * 创建Chart副标题
	 * @return
	 */
	protected List<String> createTimespanSubTitles() {
		List<String> subTitles = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(beginTime==null && endTime == null){
			subTitles.add("（全部时间）");
		}
		else if (null != beginTime && null != endTime) {
			subTitles.add("（时间：" + sdf.format(beginTime) + " 至 " + sdf.format(endTime) + "）");
		}
		else if (null != beginTime) {
			subTitles.add("（时间：" + sdf.format(beginTime) + " 至今 ）");
		}
		else {
			subTitles.add("（时间：" + sdf.format(endTime) + " 以前 ）");
		}
		return subTitles;
	}
	
	/**
	 * 调整X轴时间起点
	 */
	protected void adjustMinimumDate() {
		if(this.chart != null){			
			XYPlot xyPlot = (XYPlot) this.chart.getPlot();// 取得JFreeChart对象的XYPlot
			/* 没有数据时，修改显示的时间为 beginTime */
			if(null != xyPlot){			
				DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();
				dateAxis.setMinimumDate(beginTime);
			}
		}
	}
}
