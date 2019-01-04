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

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.BaseSamplingService;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.framework.base.model.Page;

/**
 * 微气象业务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface WeatherAnalysisService extends BaseSamplingService<WeatherSampling>{

	 

	// 日分析数据
	public abstract List<Map<String, Object>> getAllDayWeatyerAnalysis(
			Sensor sensor, Date beginTime, Date endTime);

	public abstract Page<Map<String, Object>> getPageDayWeatyerAnalysis(
			Sensor sensor, Date beginTime, Date endTime, final long pageNo,
			long pageSize);
}
