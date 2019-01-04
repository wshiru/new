/*
 * Project platform
 *
 * Classname MonsoonSamplingAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-30 10:26
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */

package com.yixin.A1000.base.web;

import java.awt.Color;
import java.util.Hashtable;

import org.jfree.chart.axis.AxisLocation;
import org.jfree.data.time.RegularTimePeriod;

import com.yixin.A1000.base.model.BaseSampling;
import com.yixin.framework.util.jfreechart.TimeSeriesChartData;

/**
 * 风偏Action类
 * @version v1.0.0
 * @author 
 *
 */
public class MonsoonSamplingAction<T extends BaseSampling> extends BaseSamplingAction<T> {

	/** 序列版本ID  */
	private static final long serialVersionUID = -4448250475171514952L;

	/** 是否显示风偏角 */
	protected Boolean showWindAngle = true;
	/** 是否显示倾斜角  */
	protected Boolean showAngle = true;
	/** 是否显示最小电气间隙参数  */
	protected Boolean showMinClearanceParams = true;
	
	/**
	 * 获取是否显示风偏角
	 * @return
	 */
	public Boolean getShowWindAngle() {
		return showWindAngle;
	}
	/**
	 * 设置是否显示风偏角
	 * @return
	 */
	public void setShowWindAngle(Boolean showWindAngle) {
		this.showWindAngle = showWindAngle;
	}
	/**
	 * 获取是否显示倾斜角
	 * @return
	 */
	public Boolean getShowAngle() {
		return showAngle;
	}
	/**
	 * 设置是否显示倾斜角
	 * @return
	 */
	public void setShowAngle(Boolean showAngle) {
		this.showAngle = showAngle;
	}
	/**
	 * 获取是否显示最小电气间隙参数
	 * @return
	 */
	public Boolean getShowMinClearanceParams() {
		return showMinClearanceParams;
	}
	/**
	 * 设置是否显示最小电气间隙参数
	 * @return
	 */
	public void setShowMinClearanceParams(Boolean showMinClearanceParams) {
		this.showMinClearanceParams = showMinClearanceParams;
	}
	
	/**
	 * 初始化风偏曲线图数据
	 */
	protected void initChartDatas(){
				
		boolean showWA = isShowWA();
		boolean showA = isShowA();
		boolean showMCP = isShowMCP();
		
		/* 设置并添加风偏角数据 */
		if(showWA){
			TimeSeriesChartData waChartData = new TimeSeriesChartData();
			waChartData.setColor(Color.GREEN);
			waChartData.setLegendText("风偏角");
			waChartData.setOrdinateText("风偏角(°)");
			waChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			waChartData.setDatasets(new Hashtable<RegularTimePeriod, Number>());

			chartDatas.add(waChartData);
		}
		/* 设置并添加倾斜角数据 */
		if(showA){
			TimeSeriesChartData wChartData = new TimeSeriesChartData();
			wChartData.setColor(Color.BLUE);
			wChartData.setLegendText("倾斜角");
			wChartData.setOrdinateText("倾斜角(°)");
			wChartData.setOrdinateLocation(showWA? AxisLocation.BOTTOM_OR_RIGHT : AxisLocation.BOTTOM_OR_LEFT);
			wChartData.setDatasets(new Hashtable<RegularTimePeriod, Number>());
			
			chartDatas.add(wChartData);
		}
		/* 设置并添加最小电气间隙参数数据 */
		if(showMCP){
			TimeSeriesChartData mcpChartData = new TimeSeriesChartData();
			mcpChartData.setColor(Color.PINK);
			mcpChartData.setLegendText("最小电气间隙参数");
			mcpChartData.setOrdinateText("最小电气间隙参数(mm)");
			mcpChartData.setOrdinateLocation(showWA || showA? AxisLocation.BOTTOM_OR_RIGHT : AxisLocation.BOTTOM_OR_LEFT);
			mcpChartData.setDatasets(new Hashtable<RegularTimePeriod, Number>());
			
			chartDatas.add(mcpChartData);
		}
	}
	/**
	 * 判断是否显示风偏角
	 * @return
	 */
	protected boolean isShowWA(){
		return showWindAngle !=null && showWindAngle? true: false;
	}
	/**
	 * 判断是否显示倾斜角
	 * @return
	 */
	protected boolean isShowA(){
		return showAngle !=null && showAngle? true: false;
	}
	/**
	 * 判断是否显示最小电气间隙参数
	 * @return
	 */
	protected boolean isShowMCP(){
		return showMinClearanceParams !=null && showMinClearanceParams? true: false;
	}
}
