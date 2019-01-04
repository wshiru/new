/*
 * Project platform
 *
 * Classname WireSagSamplingAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 15:57
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */

package com.yixin.A1000.wiresag.web;

import java.awt.Color;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.axis.AxisLocation;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;

import com.yixin.A1000.base.web.BaseSamplingAction;
import com.yixin.A1000.wiresag.model.WireSagSampling;
import com.yixin.framework.util.jfreechart.JFreeChartFactory;
import com.yixin.framework.util.jfreechart.TimeSeriesChartData;

/**
 * 导线弧垂Action类
 * @version v1.0.0
 * @author 
 *
 */
public class WireSagSamplingAction extends BaseSamplingAction<WireSagSampling> {

	/** 序列版本ID  */
	private static final long serialVersionUID = -7666444023334693550L;
	
	/** 是否显示弧垂曲线 */
	private Boolean showSag = true;
	/** 是否显示对地距离曲线  */
	private Boolean showGroundDistance = true;

	
	/**
	 * 获取是否显示弧垂曲线
	 * @return
	 */
	public Boolean getShowSag() {
		return showSag;
	}
	/**
	 * 设置是否显示弧垂曲线
	 * @return
	 */
	public void setShowSag(Boolean showSag) {
		this.showSag = showSag;
	}
	/**
	 * 获取是否显示对地距离曲线
	 * @return
	 */
	public Boolean getShowGroundDistance() {
		return showGroundDistance;
	}
	/**
	 * 设置是否显示对地距离曲线
	 * @return
	 */
	public void setShowGroundDistance(Boolean showGroundDistance) {
		this.showGroundDistance = showGroundDistance;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#list()
	 */
	@Override
	public String list() {
		page = this.samplingService.getPageSamplings(
					sensor, beginTime, endTime, getPageNo(), getPageSize());
		return super.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.base.web.BaseSamplingAction#chart()
	 */
	@Override
	public String chart() {
		
		Hashtable<RegularTimePeriod, Number> sagDatasets = null;
		Hashtable<RegularTimePeriod, Number> gdDatasets = null;
		
		boolean showSg = showSag !=null && showSag? true: false;
		boolean showGD = showGroundDistance !=null && showGroundDistance? true: false;
		
		/* 设置并添加弧垂数据 */
		if(showSg){
			TimeSeriesChartData sagChartData = new TimeSeriesChartData();
			sagChartData.setColor(Color.GREEN);
			sagChartData.setLegendText("弧垂");
			sagChartData.setOrdinateText("弧垂(m)");
			sagChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			sagDatasets = new Hashtable<RegularTimePeriod, Number>();
			sagChartData.setDatasets(sagDatasets);

			chartDatas.add(sagChartData);
		}
		/* 设置并添加对地距离数据 */
		if(showGD){
			TimeSeriesChartData gdChartData = new TimeSeriesChartData();
			gdChartData.setColor(Color.BLUE);
			gdChartData.setLegendText("对地距离");
			gdChartData.setOrdinateText("对地距离(m)");
			gdChartData.setOrdinateLocation(showSg? AxisLocation.BOTTOM_OR_RIGHT : AxisLocation.BOTTOM_OR_LEFT);
			gdDatasets = new Hashtable<RegularTimePeriod, Number>();
			gdChartData.setDatasets(gdDatasets);
			
			chartDatas.add(gdChartData);
		}
		
		/* 填充数据内容*/
		List<WireSagSampling> sagSamplings = this.samplingService.getSamplings(sensor, beginTime, endTime);		
		Iterator<WireSagSampling> iterator = sagSamplings.iterator();		
		while (iterator.hasNext()) {
			WireSagSampling sampling = (WireSagSampling) iterator.next();
			Minute t = new Minute(sampling.getSamplingTime());
			if(showSg){
				Double sag = sampling.getSag();
				if(null != sag)
					sagDatasets.put(t, sag);
			}
			if(showGD){
				Double gd = sampling.getGroundDistance();
				if(null != gd)
					gdDatasets.put(t, gd);
			}
		}
		
		//生成图形
		this.chart = JFreeChartFactory.createTimeSeriesChart("导线弧垂曲线图", createTimespanSubTitles(), "采样时间", true, chartDatas);
		
		return super.chart();
	}
	
	
}
