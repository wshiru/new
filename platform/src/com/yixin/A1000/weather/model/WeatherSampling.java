/*
 * Project platform
 *
 * Classname WeatherSampling.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 14:31
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.weather.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.yixin.A1000.base.model.BaseSampling;


/**
 * 微气象实体类
 * 
 * @version v1.0.0
 * @author 
 *
 */
@Entity
@Table(name="T_WeatherSampling")
public class WeatherSampling extends BaseSampling{

	private static final long serialVersionUID = -8861975401524748366L;

	/** 微气象数据ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String weatherSamplingId;
    
	
	/** 告警标识  **/
	private  Integer alarmFlag;
	
	/** 10分钟平均风速 **/
	private  Double averageWindSpeed10min;
    
	/** 10分钟平均风向  **/
	private  Double averageWindDirection10min;
	
	/** 风向名称  **/
	@Transient
	private  String windDirectionName;
	
	/** 最大风速 **/
	private  Double maxWindSpeed;
	
	/** 极大风速 **/
	private  Double extremeWindSpeed;
	
	/** 标准风速 **/
	private  Double strandrdWindSpeed;
	
	/** 气温 */
	private Double  temperature;
	
	/** 湿度 */
	private Double  humidity;
		
	/** 气压 */
	private Double  airPressure;
	
	/** 雨量 */
	private Double  dailyRainfall;
	
	/** 光辐射度  */
	private Double  radiationIntensity;
	
	/** 降水强度  **/
	private Double  precipitationIntensity;
	
	/** 风速（设备上传未经计算处理的） **/
	private Double  windSpeed;
	
	/** 风向 */
	private Double  windDirection;

	
	public String getWeatherSamplingId() {
		return weatherSamplingId;
	}


	public void setWeatherSamplingId(String weatherSamplingId) {
		this.weatherSamplingId = weatherSamplingId;
	}


	public Integer getAlarmFlag() {
		return alarmFlag;
	}


	public void setAlarmFlag(Integer alarmFlag) {
		this.alarmFlag = alarmFlag;
	}


	public Double getAverageWindSpeed10min() {
		return averageWindSpeed10min;
	}


	public void setAverageWindSpeed10min(Double averageWindSpeed10min) {
		this.averageWindSpeed10min = averageWindSpeed10min;
	}


	public Double getAverageWindDirection10min() {
		return averageWindDirection10min;
	}


	public void setAverageWindDirection10min(Double averageWindDirection10min) {
		this.averageWindDirection10min = averageWindDirection10min;
	}


	public Double getMaxWindSpeed() {
		return maxWindSpeed;
	}


	public void setMaxWindSpeed(Double maxWindSpeed) {
		this.maxWindSpeed = maxWindSpeed;
	}


	public Double getExtremeWindSpeed() {
		return extremeWindSpeed;
	}


	public void setExtremeWindSpeed(Double extremeWindSpeed) {
		this.extremeWindSpeed = extremeWindSpeed;
	}


	public Double getStrandrdWindSpeed() {
		return strandrdWindSpeed;
	}


	public void setStrandrdWindSpeed(Double strandrdWindSpeed) {
		this.strandrdWindSpeed = strandrdWindSpeed;
	}


	public Double getTemperature() {
		return temperature;
	}


	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}


	public Double getHumidity() {
		return humidity;
	}


	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}


	public Double getAirPressure() {
		return airPressure;
	}


	public void setAirPressure(Double airPressure) {
		this.airPressure = airPressure;
	}


	public Double getDailyRainfall() {
		return dailyRainfall;
	}


	public void setDailyRainfall(Double dailyRainfall) {
		this.dailyRainfall = dailyRainfall;
	}


	public Double getRadiationIntensity() {
		return radiationIntensity;
	}


	public void setRadiationIntensity(Double radiationIntensity) {
		this.radiationIntensity = radiationIntensity;
	}


	public Double getPrecipitationIntensity() {
		return precipitationIntensity;
	}


	public void setPrecipitationIntensity(Double precipitationIntensity) {
		this.precipitationIntensity = precipitationIntensity;
	}


	public Double getWindSpeed() {
		return windSpeed;
	}


	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}


	public Double getWindDirection() {
		return windDirection;
	}


	public void setWindDirection(Double windDirection) {
		this.windDirection = windDirection;
	}


	/**
	 * @param 风向名称
	 */
	public void setWindDirectionName(String windDirectionName) {
		this.windDirectionName = windDirectionName;
	}


	/**
	 * @return the 风向名称
	 */
	public String getWindDirectionName() {
		if(null == averageWindDirection10min) return "";
		Double d = averageWindDirection10min + 22.5;
		int wd = d.intValue() /45 ;
		switch(wd){
		case 0: return "北风";
		case 1: return "东北风";
		case 2: return "东风";
		case 3: return "东南风";
		case 4: return "南风";
		case 5: return "西南风";
		case 6: return "西风";
		case 7: return "西北风";
		}		
		return "";
	}


 
	
}
