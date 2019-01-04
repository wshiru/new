/*
 * Project platform
 *
 * Classname WeatherWarningConditionAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-22 14:05
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.web;

import com.yixin.A1000.warning.conditions.WeatherWarningConditions;
import com.yixin.A1000.warning.constant.WarningDictNames;

/**
 * 微气象告警条件Action类
 * 
 * @version v1.0.0
 * @author 
 */
public class WeatherWarningConditionAction extends BaseWarningConditionAction{

	/** 序列号版本号 */
	private static final long serialVersionUID = 4284296164785587758L;

	/** 气温最大阀值 */
	private Double maxTemperature;
	/** 气温最小阀值 */
	private Double minTemperature;
	/** 湿度最大阀值 */
	private Double maxHumidity;
	/** 湿度最小阀值 */
	private Double minHumidity;
	/** 风速阀值 */
	private Double windSpeed;
//	/** 风向阀值 */
//	private Double windDirection;
	/** 气压阀值 */
	private Double airPressure;
	/** 雨量阀值 */
	private Double dailyRainFall;
//	/** 光照辐射度阀值 */
//	private Double radiationIntensity;

	/**
	 * @param maxTemperature the maxTemperature to set
	 */
	public void setMaxTemperature(Double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	/**
	 * @return the maxTemperature
	 */
	public Double getMaxTemperature() {
		return maxTemperature;
	}

	/**
	 * @param minTemperature the minTemperature to set
	 */
	public void setMinTemperature(Double minTemperature) {
		this.minTemperature = minTemperature;
	}

	/**
	 * @return the minTemperature
	 */
	public Double getMinTemperature() {
		return minTemperature;
	}

	/**
	 * @param maxHumidity the maxHumidity to set
	 */
	public void setMaxHumidity(Double maxHumidity) {
		this.maxHumidity = maxHumidity;
	}

	/**
	 * @return the maxHumidity
	 */
	public Double getMaxHumidity() {
		return maxHumidity;
	}

	/**
	 * @param minHumidity the minHumidity to set
	 */
	public void setMinHumidity(Double minHumidity) {
		this.minHumidity = minHumidity;
	}

	/**
	 * @return the minHumidity
	 */
	public Double getMinHumidity() {
		return minHumidity;
	}

	/**
	 * @return the windSpeed
	 */
	public Double getWindSpeed() {
		return windSpeed;
	}

	/**
	 * @param windSpeed the windSpeed to set
	 */
	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

//	/**
//	 * @return the windDirection
//	 */
//	public Double getWindDirection() {
//		return windDirection;
//	}
//
//	/**
//	 * @param windDirection the windDirection to set
//	 */
//	public void setWindDirection(Double windDirection) {
//		this.windDirection = windDirection;
//	}

	/**
	 * @return the airPressure
	 */
	public Double getAirPressure() {
		return airPressure;
	}

	/**
	 * @param airPressure the airPressure to set
	 */
	public void setAirPressure(Double airPressure) {
		this.airPressure = airPressure;
	}

	/**
	 * @return the dailyRainFall
	 */
	public Double getDailyRainFall() {
		return dailyRainFall;
	}

	/**
	 * @param dailyRainFall the dailyRainFall to set
	 */
	public void setDailyRainFall(Double dailyRainFall) {
		this.dailyRainFall = dailyRainFall;
	}

//	/**
//	 * @return the radiationIntensity
//	 */
//	public Double getRadiationIntensity() {
//		return radiationIntensity;
//	}
//
//	/**
//	 * @param radiationIntensity the radiationIntensity to set
//	 */
//	public void setRadiationIntensity(Double radiationIntensity) {
//		this.radiationIntensity = radiationIntensity;
//	}

	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	@Override
	public void prepare() throws Exception {
		if(null != WeatherWarningConditions.CONDT_TEMPERATURE_MAX)
			maxTemperature = WeatherWarningConditions.CONDT_TEMPERATURE_MAX.getThreshold();
		if(null != WeatherWarningConditions.CONDT_TEMPERATURE_MIN)
			minTemperature = WeatherWarningConditions.CONDT_TEMPERATURE_MIN.getThreshold();
		if(null != WeatherWarningConditions.CONDT_HUMIDITY_MAX)
			maxHumidity = WeatherWarningConditions.CONDT_HUMIDITY_MAX.getThreshold();
		if(null != WeatherWarningConditions.CONDT_HUMIDITY_MIN)
			minHumidity = WeatherWarningConditions.CONDT_HUMIDITY_MIN.getThreshold();
		if(null != WeatherWarningConditions.CONDT_WINDSPEED)
			windSpeed = WeatherWarningConditions.CONDT_WINDSPEED.getThreshold();
//		if(null != WeatherWarningConditions.CONDT_WINDDIRECTION)
//			windDirection = WeatherWarningConditions.CONDT_WINDDIRECTION.getThreshold();
		if(null != WeatherWarningConditions.CONDT_AIRPRESSURE)
			airPressure = WeatherWarningConditions.CONDT_AIRPRESSURE.getThreshold();
		if(null != WeatherWarningConditions.CONDT_DAILYRAINFALL)
			dailyRainFall = WeatherWarningConditions.CONDT_DAILYRAINFALL.getThreshold();
//		if(null != WeatherWarningConditions.CONDT_RADIATIONINTENSITY)
//			radiationIntensity = WeatherWarningConditions.CONDT_RADIATIONINTENSITY.getThreshold();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.web.WarningConditionAction#save()
	 */
	public String save() {
		try {
			WeatherWarningConditions.setThresholds(maxTemperature, minTemperature, maxHumidity, 
					minHumidity, windSpeed, airPressure, dailyRainFall);
		} catch (Exception e) {
			setFailureMessage("保存失败");
			return INPUT;
		}
		addSaveLog(WarningDictNames.WEATHER_WARNING_DICT_NAME);
		return super.save();
	}
}
