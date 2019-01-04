/*
 * Project platform
 *
 * Classname WeatherSamplingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 15:45
 *
 * ModifiedBy 
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public WeatherSampling getLastWeather(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.weather.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.impl.BaseSamplingServiceImpl;
import com.yixin.A1000.weather.dao.WeatherSamplingDao;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.A1000.weather.service.WeatherAnalysisService;
import com.yixin.framework.base.model.Page;

/**
 * 微气象业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class WeatherAnalysisServiceImpl extends BaseSamplingServiceImpl<WeatherSampling> implements WeatherAnalysisService {
	
	private WeatherSamplingDao weatherSamplingDao;
	
	@Override
	public List<Map<String, Object>> getAllDayWeatyerAnalysis(Sensor sensor,
			Date beginTime, Date endTime) {
		return weatherSamplingDao.getAllDayWeatyerAnalysis(sensor, beginTime, endTime) ;
	}

	@Override
	public Page<Map<String, Object>> getPageDayWeatyerAnalysis(Sensor sensor,
			Date beginTime, Date endTime, long pageNo, long pageSize) {
		return weatherSamplingDao.getPageDayWeatyerAnalysis(sensor, beginTime, endTime, pageNo, pageSize) ;
	}

	/**
	 * @param weatherSamplingDao the weatherSamplingDao to set
	 */
	public void setWeatherSamplingDao(WeatherSamplingDao weatherSamplingDao) {
		this.weatherSamplingDao = weatherSamplingDao;
	}


	 
}
