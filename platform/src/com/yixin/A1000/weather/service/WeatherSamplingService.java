/*
 * Project platform
 *
 * Classname WeatherSamplingService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 15:45
 *
 * ModifiedBy 
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public abstract WeatherSampling getLastWeather(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.weather.service;

import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.BaseSamplingService;
import com.yixin.A1000.weather.model.WeatherSampling;

/**
 * 微气象业务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface WeatherSamplingService extends BaseSamplingService<WeatherSampling>{

	/**
	 * 添加微气象
	 * @param WeatherSampling
	 */
	public abstract void addWeatherSampling(WeatherSampling WeatherSampling);

	/**
	 * 添加微气象  多个
	 * @param WeatherSamplings
	 */
	public abstract void addWeatherSampling(List<WeatherSampling> weatherSamplings);

	/***
	 * 取得最后一次采样的数据，不存在则返回null。
	 *
	 * @param sensor 监测装置
	 * @return 最后一次采样的数据，不存在则返回null。
	 */
	public abstract WeatherSampling getLastWeather(Sensor sensor);
}
