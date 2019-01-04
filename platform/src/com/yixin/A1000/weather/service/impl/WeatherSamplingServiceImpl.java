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

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.impl.BaseSamplingServiceImpl;
import com.yixin.A1000.weather.dao.WeatherSamplingDao;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.A1000.weather.service.WeatherSamplingService;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.DateProperty;

/**
 * 微气象业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class WeatherSamplingServiceImpl extends BaseSamplingServiceImpl<WeatherSampling> implements WeatherSamplingService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.wire.service.WireSagSamplingService#addWireSagSampling
	 * (com.yixin.ca2000.wire.model.WireSagSampling)
	 */
	@Override
	public void addWeatherSampling(WeatherSampling WeatherSampling) {
		if (null != WeatherSampling)
			this.samplingDao.save(WeatherSampling);
	}

	@Override
	public void addWeatherSampling(List<WeatherSampling> weatherSamplings) {
		Iterator<WeatherSampling> it = weatherSamplings.iterator();
		while (it.hasNext()) {
			WeatherSampling weatherSampling = it.next();
			this.addWeatherSampling(weatherSampling);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.weather.service.WeatherSamplingService#getLastWeather
	 * (com.yixin.ca2000.archive.model.Sensor)
	 */
	@Override
	public WeatherSampling getLastWeather(Sensor sensor) {
		WeatherSamplingDao weatherSamplingDao = (WeatherSamplingDao) samplingDao;
		return weatherSamplingDao.getLastWeatherSampling(sensor);
	}
}
