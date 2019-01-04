/*
 * Project platform
 *
 * Classname WeatherSamplingService
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 13:41
 *
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.weather.service;

import java.util.Collection;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.weather.model.WeatherParameter;

/**
 * 微气象参数服务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface WeatherParameterService{
	/**
	 * 添加参数
	 * @param weatherParameter
	 */
	public abstract void addWeatherParameter(WeatherParameter weatherParameter);
	
	/**
	 * 修改参数
	 * @param weatherParameter
	 */
	public abstract void editWeatherParameter(WeatherParameter weatherParameter);
	
	/**
	 * 删除参数
	 * @param weatherParameter
	 */
	public abstract void deleteWeatherParameter(WeatherParameter weatherParameter);
	
	/**
	 * 删除多项参数
	 * @param weatherParameter
	 */
	public abstract void deleteWeatherParameter(Collection<WeatherParameter> weatherParameter);

	/**
	 * 根据ID得到参数项
	 * @param id
	 * @return
	 */
	public abstract WeatherParameter  getWeatherParameter(String id);
	
	/**
	 * Sensor
	 * @param sensor
	 * @return
	 */
	public abstract WeatherParameter getWeatherParameterBySensor(Sensor sensor);
	
	/**
	 * 得到所有参数
	 * @return
	 */
	public abstract List<WeatherParameter> getAllWeatherParameters();


	

}
