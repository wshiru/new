/*
 * Project platform
 *
 * Classname WeatherSamplingAction.java
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

package com.yixin.A1000.weather.web;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jfree.chart.axis.AxisLocation;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;

import com.yixin.A1000.base.web.BaseSamplingAction;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.A1000.weather.service.WeatherAnalysisService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.util.jfreechart.JFreeChartFactory;
import com.yixin.framework.util.jfreechart.TimeSeriesChartData;

/**
 * 微气象Action类
 * 
 * @version v1.0.0
 * @author
 * 
 */
public class WeatherAnalysisAction extends BaseSamplingAction<WeatherSampling> {

	/** 序列版本ID */
	private static final long serialVersionUID = -2765013632008468034L;

	/** 最大风速 */
	protected Boolean showMaxWindSpeed = false;

	/** 是否显示气温 */
	protected Boolean showTemperature = true;
	/** 是否显示湿度 */
	protected Boolean showHumidity = true;
	/** 是否显示气压 */
	protected Boolean showAirPressure = false;
	/** 是否显示日降雨量 */
	protected Boolean showDailyRainfall = true;
	/** 降水强度 **/
	protected Boolean showPrecipitationIntensity = false;
	/** 是否显示光辐射度 */
	protected Boolean showRadiationIntensity = false;
	

	/** 数据分析服务接口 */
	protected WeatherAnalysisService weatherAnalysisService;

	/** 设置 数据分析服务接口 */
	public void setWeatherAnalysisService(
			WeatherAnalysisService weatherAnalysisService) {
		this.weatherAnalysisService = weatherAnalysisService;
	}

	public Boolean getShowMaxWindSpeed() {
		return showMaxWindSpeed;
	}

	public void setShowMaxWindSpeed(Boolean showMaxWindSpeed) {
		this.showMaxWindSpeed = showMaxWindSpeed;
	}

 

	/**
	 * 获取是否显示气温
	 * 
	 * @return
	 */
	public Boolean getShowTemperature() {
		return showTemperature;
	}

	/**
	 * 设置是否显示气温
	 * 
	 * @return
	 */
	public void setShowTemperature(Boolean showTemperature) {
		this.showTemperature = showTemperature;
	}

	/**
	 * 获取是否显示湿度
	 * 
	 * @return
	 */
	public Boolean getShowHumidity() {
		return showHumidity;
	}

	/**
	 * 设置是否显示湿度
	 * 
	 * @return
	 */
	public void setShowHumidity(Boolean showHumidity) {
		this.showHumidity = showHumidity;
	}

	/**
	 * 获取是否显示气压
	 * 
	 * @return
	 */
	public Boolean getShowAirPressure() {
		return showAirPressure;
	}

	/**
	 * 设置是否显示气压
	 * 
	 * @return
	 */
	public void setShowAirPressure(Boolean showAirPressure) {
		this.showAirPressure = showAirPressure;
	}

	/**
	 * 获取是否显示日降雨量
	 * 
	 * @return
	 */
	public Boolean getShowDailyRainfall() {
		return showDailyRainfall;
	}

	/**
	 * 设置是否显示日降雨量
	 * 
	 * @return
	 */
	public void setShowDailyRainfall(Boolean showDailyRainfall) {
		this.showDailyRainfall = showDailyRainfall;
	}

	/**
	 * 获取是否显示降水强度
	 * 
	 * @return
	 */
	public Boolean getShowPrecipitationIntensity() {
		return showPrecipitationIntensity;
	}

	/**
	 * 设置是否显示降水强度
	 * 
	 * @return
	 */
	public void setShowPrecipitationIntensity(Boolean showPrecipitationIntensity) {
		this.showPrecipitationIntensity = showPrecipitationIntensity;
	}
	
	/**
	 * 获取是否显示光辐射度
	 * 
	 * @return
	 */
	public Boolean getShowRadiationIntensity() {
		return showRadiationIntensity;
	}

	/**
	 * 设置是否显示光辐射度
	 * 
	 * @return
	 */
	public void setShowRadiationIntensity(Boolean showRadiationIntensity) {
		this.showRadiationIntensity = showRadiationIntensity;
	}	

	public WeatherAnalysisAction() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -7) ;

		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
		String s1 = fd.format(calendar.getTime());
		Date d1 = calendar.getTime();

		try {
			d1 = fd.parse(s1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		beginTime = d1;

		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1);
		endTime = calendar.getTime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.framework.base.web.BaseAction#list()
	 */
	@Override
	public String list() {

		Page<Map<String, Object>> pages = this.weatherAnalysisService
				.getPageDayWeatyerAnalysis(sensor, beginTime, endTime,
						getPageNo(), getPageSize());
		request.setAttribute("pageData", pages);
		return SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.base.web.BaseSamplingAction#chart()
	 */
	@Override
	public String chart() {

		Hashtable<RegularTimePeriod, Number> MaxWindSpeedDatasets = null;
		
		Hashtable<RegularTimePeriod, Number> MaxTemperatureDatasets = null;
		Hashtable<RegularTimePeriod, Number> MinTemperatureDatasets = null;
		
		Hashtable<RegularTimePeriod, Number> MaxHumidityDatasets = null;
		Hashtable<RegularTimePeriod, Number> MinHumidityDatasets = null;
		
		Hashtable<RegularTimePeriod, Number> MaxAirPressureDatasets = null;
		Hashtable<RegularTimePeriod, Number> MinAirPressureDatasets = null;
		
		Hashtable<RegularTimePeriod, Number> MaxDailyRainfallDatasets = null;
		
		
		Hashtable<RegularTimePeriod, Number> MaxPrecipitationIntensityDatasets = null;
		Hashtable<RegularTimePeriod, Number> MaxRadiationIntensityDatasets = null;
		
		
		showMaxWindSpeed = showMaxWindSpeed != null ? showMaxWindSpeed : false ;
		showTemperature = showTemperature != null ? showTemperature : false ;
		showHumidity = showHumidity != null ? showHumidity : false ;
		
		showAirPressure = showAirPressure != null ? showAirPressure : false ;
		showDailyRainfall = showDailyRainfall != null ? showDailyRainfall : false ;
		showPrecipitationIntensity = showPrecipitationIntensity != null ? showPrecipitationIntensity : false ;
		showRadiationIntensity = showRadiationIntensity != null ? showRadiationIntensity : false ;		
		

		int countLine = 0; // 统计曲线数

		/* 设置风速数据 */
		if (showMaxWindSpeed) {
			TimeSeriesChartData tChartData = new TimeSeriesChartData();
			tChartData.setColor(Color.BLACK);
			tChartData.setLegendText("最大风速");
			tChartData.setOrdinateText("最大风速(m/s)");
			tChartData.setOrdinateLocation(AxisLocation.BOTTOM_OR_LEFT);
			MaxWindSpeedDatasets = new Hashtable<RegularTimePeriod, Number>();
			tChartData.setDatasets(MaxWindSpeedDatasets);
			chartDatas.add(tChartData);
			countLine++;			
		}
		/* 设置气温数据 */
		if(showTemperature){
			TimeSeriesChartData tChartData = new TimeSeriesChartData();
			tChartData.setColor(Color.BLUE);
			tChartData.setLegendText("最高气温");
			tChartData.setOrdinateText("气温(℃)");
			tChartData.setOrdinateLocation(arrangeLocation(countLine));
			MaxTemperatureDatasets = new Hashtable<RegularTimePeriod, Number>();
			tChartData.setDatasets(MaxTemperatureDatasets);
			chartDatas.add(tChartData);
			
			
			tChartData = new TimeSeriesChartData();
			tChartData.setColor(Color.BLUE);
			tChartData.setLegendText("最低气温");
			tChartData.setOrdinateText("气温(℃)");
			tChartData.setOrdinateLocation(arrangeLocation(countLine));
			MinTemperatureDatasets = new Hashtable<RegularTimePeriod, Number>();
			tChartData.setDatasets(MinTemperatureDatasets);
			chartDatas.add(tChartData);
			countLine++;			
		}
		/* 设置湿度数据 */
		if(showHumidity){
			TimeSeriesChartData tChartData = new TimeSeriesChartData();
			tChartData.setColor(Color.GREEN);
			tChartData.setLegendText("最高湿度");
			tChartData.setOrdinateText("湿度(%)");
			tChartData.setOrdinateLocation(arrangeLocation(countLine));
			MaxHumidityDatasets = new Hashtable<RegularTimePeriod, Number>();
			tChartData.setDatasets(MaxHumidityDatasets);
			chartDatas.add(tChartData);
			
			tChartData = new TimeSeriesChartData();
			tChartData.setColor(Color.GREEN);
			tChartData.setLegendText("最低湿度");
			tChartData.setOrdinateText("湿度(%)");
			tChartData.setOrdinateLocation(arrangeLocation(countLine));
			MinHumidityDatasets = new Hashtable<RegularTimePeriod, Number>();
			tChartData.setDatasets(MinHumidityDatasets);
			chartDatas.add(tChartData);
			countLine++;
			
		}		
		/* 设置并添加气压数据 */
		if (showAirPressure) {
			TimeSeriesChartData tChartData = new TimeSeriesChartData();
			tChartData.setColor(Color.ORANGE);
			tChartData.setLegendText("最高气压");
			tChartData.setOrdinateText("气压(hPa)");
			tChartData.setOrdinateLocation(arrangeLocation(countLine));
			MaxAirPressureDatasets = new Hashtable<RegularTimePeriod, Number>();
			tChartData.setDatasets(MaxAirPressureDatasets);
			chartDatas.add(tChartData);
			
			tChartData = new TimeSeriesChartData();
			tChartData.setColor(Color.ORANGE);
			tChartData.setLegendText("最低气压");
			tChartData.setOrdinateText("气压(hPa)");
			tChartData.setOrdinateLocation(arrangeLocation(countLine));
			MinAirPressureDatasets = new Hashtable<RegularTimePeriod, Number>();
			tChartData.setDatasets(MinAirPressureDatasets);
			chartDatas.add(tChartData);
			countLine++;			
		}
		/* 设置并添加雨量数据 */
		if (showDailyRainfall) {
			TimeSeriesChartData tChartData = new TimeSeriesChartData();
			tChartData.setColor(Color.MAGENTA);
			tChartData.setLegendText("日降雨量");
			tChartData.setOrdinateText("日降雨量(mm)");
			tChartData.setOrdinateLocation(arrangeLocation(countLine));
			MaxDailyRainfallDatasets = new Hashtable<RegularTimePeriod, Number>();
			tChartData.setDatasets(MaxDailyRainfallDatasets);

			chartDatas.add(tChartData);
			countLine++;
		}
		
		/* 设置并添加降水强度数据 */
		if (showPrecipitationIntensity) {
			TimeSeriesChartData tChartData = new TimeSeriesChartData();
			tChartData.setColor(Color.CYAN);
			tChartData.setLegendText("降水强度");
			tChartData.setOrdinateText("降水强度(mm/min)");
			tChartData.setOrdinateLocation(arrangeLocation(countLine));
			MaxPrecipitationIntensityDatasets = new Hashtable<RegularTimePeriod, Number>();
			tChartData.setDatasets(MaxPrecipitationIntensityDatasets);

			chartDatas.add(tChartData);
			countLine++;
		}
		
		/* 设置并添加光辐射度数据 */
		if (showRadiationIntensity) {
			TimeSeriesChartData tChartData = new TimeSeriesChartData();
			tChartData.setColor(Color.CYAN);
			tChartData.setLegendText("光辐射度");
			tChartData.setOrdinateText("光辐射度(W/m2)");
			tChartData.setOrdinateLocation(arrangeLocation(countLine));
			MaxRadiationIntensityDatasets = new Hashtable<RegularTimePeriod, Number>();
			tChartData.setDatasets(MaxRadiationIntensityDatasets);

			chartDatas.add(tChartData);
			countLine++;
		}

		/* 填充数据内容 */
		List<Map<String, Object>> wSamplings = this.weatherAnalysisService
				.getAllDayWeatyerAnalysis(sensor, beginTime, endTime);
		Iterator<Map<String, Object>> iterator = wSamplings.iterator();
		while (iterator.hasNext()) {
			Map<String, Object> sampling = iterator.next();
			Date samplingTime = (Date) sampling.get("samplingTime");
			Minute t = new Minute(samplingTime);

			
			Double max_windspeed = (Double) sampling.get("max_windspeed");
			Double max_temperature = (Double) sampling.get("max_temperature");
			Double min_temperature = (Double) sampling.get("min_temperature");
			Double max_humidity = (Double) sampling.get("max_humidity");
			Double min_humidity = (Double) sampling.get("min_humidity");
			Double max_airpressure = (Double) sampling.get("max_airpressure");
			Double min_airpressure = (Double) sampling.get("min_airpressure");
			Double max_dailyrainfall = (Double) sampling.get("max_dailyrainfall");
			Double max_precipitationintensity = (Double) sampling.get("max_precipitationintensity");
			Double max_radiationintensity = (Double) sampling.get("max_radiationintensity");
			
			
			if (showMaxWindSpeed && null != max_windspeed)
				MaxWindSpeedDatasets.put(t, max_windspeed);
			
			if (showTemperature && null != max_temperature)
				MaxTemperatureDatasets.put(t, max_temperature);
			if (showTemperature && null != min_temperature)
				MinTemperatureDatasets.put(t, min_temperature);
			
			if (showHumidity && null != max_humidity)
				MaxHumidityDatasets.put(t, max_humidity);
			if (showHumidity && null != min_humidity)
				MinHumidityDatasets.put(t, min_humidity);
			
			if (showAirPressure && null != max_airpressure)
				MaxAirPressureDatasets.put(t, max_airpressure);
			if (showAirPressure && null != min_airpressure)
				MinAirPressureDatasets.put(t, min_airpressure);

			if (showDailyRainfall && null != max_dailyrainfall)
				MaxDailyRainfallDatasets.put(t, max_dailyrainfall);

			if (showPrecipitationIntensity && null != max_precipitationintensity)
				MaxPrecipitationIntensityDatasets.put(t, max_precipitationintensity);			 
			
			if (showRadiationIntensity && null != max_radiationintensity)
				MaxRadiationIntensityDatasets.put(t, max_radiationintensity);			 
		}

		// 生成图形
		this.chart = JFreeChartFactory.createTimeSeriesChart("微气象曲线图",
				createTimespanSubTitles(), "采样时间", true, chartDatas);

		return super.chart();
	}

	/**
	 * 安排曲线纵轴位置
	 * 
	 * @param number
	 * @return
	 */
	private AxisLocation arrangeLocation(int number) {
		if (number % 2 == 0)
			return AxisLocation.BOTTOM_OR_LEFT;
		return AxisLocation.BOTTOM_OR_RIGHT;
	}
}
