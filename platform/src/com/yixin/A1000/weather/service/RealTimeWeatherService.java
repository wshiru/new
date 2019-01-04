/*
 * Project platform
 *
 * Class RealTimeWeatherService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-8 下午02:49:18
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.weather.service;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.framework.system.model.User;


/**
 * 视频实时控制业务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface RealTimeWeatherService {

	/**
	 * 新增微气象数据的召测任务
	 * 
	 * @param sensor
	 *            监测装置
	 */
	public abstract void addRealTimeTask(User user, Sensor sensor);
	
	public abstract WeatherSampling samplingWeather(Sensor sensor);
		
}
