/*
 * Project platform
 *
 * Classname WireTemperatureSamplingAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 上午8:50
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */

package com.yixin.A1000.wiretemperature.web;


import java.awt.Color;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;


import org.jfree.chart.axis.AxisLocation;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;


import com.yixin.A1000.base.web.BaseSamplingAction;
import com.yixin.A1000.wiretemperature.model.WireTemperatureSampling;
import com.yixin.framework.util.jfreechart.JFreeChartFactory;
import com.yixin.framework.util.jfreechart.TimeSeriesChartData;

/**
 * 导线温度Action类
 * @version v1.0.0
 * @author 
 *
 */
public class WireTemperatureSamplingAction extends BaseSamplingAction<WireTemperatureSampling> {

	/** 序列版本ID  */
	private static final long serialVersionUID = -6141541347663415763L;
		
	/** 是否显示线温1  */
	private Boolean showLineTemperature1 = true;
	/** 是否显示线温2  */
	private Boolean showLineTemperature2 = true;
	
	/**
	 * 获取是否显示线温1
	 * @return
	 */
	public Boolean getShowLineTemperature1() {
		return showLineTemperature1;
	}
	/**
	 * 设置是否显示线温1
	 * @return
	 */
	public void setShowLineTemperature1(Boolean showLineTemperature1) {
		this.showLineTemperature1 = showLineTemperature1;
	}
	/**
	 * 获取是否显示线温2
	 * @return
	 */	
	public Boolean getShowLineTemperature2() {
		return showLineTemperature2;
	}
	/**
	 * 设置是否显示线温2
	 * @return
	 */
	public void setShowLineTemperature2(Boolean showLineTemperature2) {
		this.showLineTemperature2 = showLineTemperature2;
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
	public String chart(){		
				
		Hashtable<RegularTimePeriod, Number> lt1Datasets = null;
		Hashtable<RegularTimePeriod, Number> lt2Datasets = null;
		
		boolean showLT1 = showLineTemperature1 !=null && showLineTemperature1? true: false;
		boolean showLT2 = showLineTemperature2 !=null && showLineTemperature2? true: false;
		
		/* 设置并添加线温1数据 */
		if(showLT1){
			TimeSeriesChartData lt1ChartData = new TimeSeriesChartData();
			lt1ChartData.setColor(Color.GREEN);
			lt1ChartData.setLegendText("线温1");
			lt1ChartData.setOrdinateText("线温1(℃)");
			lt1ChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			lt1Datasets = new Hashtable<RegularTimePeriod, Number>();
			lt1ChartData.setDatasets(lt1Datasets);

			chartDatas.add(lt1ChartData);
		}
		/* 设置并添加线温2数据 */
		if(showLT2){
			TimeSeriesChartData lt2ChartData = new TimeSeriesChartData();
			lt2ChartData.setColor(Color.BLUE);
			lt2ChartData.setLegendText("线温2");
			lt2ChartData.setOrdinateText("线温2(℃)");
			lt2ChartData.setOrdinateLocation(showLT1? AxisLocation.BOTTOM_OR_RIGHT : AxisLocation.BOTTOM_OR_LEFT);
			lt2Datasets = new Hashtable<RegularTimePeriod, Number>();
			lt2ChartData.setDatasets(lt2Datasets);
			
			chartDatas.add(lt2ChartData);
		}
		
		/* 填充数据内容*/
		List<WireTemperatureSampling> wtSamplings = this.samplingService.getSamplings(sensor, beginTime, endTime);		
		Iterator<WireTemperatureSampling> iterator = wtSamplings.iterator();		
		while (iterator.hasNext()) {
			WireTemperatureSampling sampling = (WireTemperatureSampling) iterator.next();
			Minute t = new Minute(sampling.getSamplingTime());
			if(showLT1){
				Double lt1 = sampling.getLineTemperature1();
				if(null != lt1)
					lt1Datasets.put(t, lt1);
			}
			if(showLT2){
				Double lt2 = sampling.getLineTemperature2();
				if(null != lt2)
					lt2Datasets.put(t, lt2);
			}
		}
		
		//生成图形
		this.chart = JFreeChartFactory.createTimeSeriesChart("导线温度曲线图", createTimespanSubTitles(), "采样时间", true, chartDatas);
		
		return super.chart();			
	}
}
