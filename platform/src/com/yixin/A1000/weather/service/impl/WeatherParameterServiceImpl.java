/*
 * Project platform
 *
 * Classname TowerTiltSamplingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.weather.service.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.weather.dao.WeatherParameterDao;
import com.yixin.A1000.weather.model.WeatherParameter;
import com.yixin.A1000.weather.service.WeatherParameterService;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.exception.ErrorCode;
 

/**
 * 微气象参数业务实现类
 * 
 * @version v1.0.0
 * @author 
 */

public class WeatherParameterServiceImpl implements WeatherParameterService {

	private WeatherParameterDao weatherParameterDao;	
	
	public void setWeatherParameterDao(WeatherParameterDao weatherParameterDao){
		this.weatherParameterDao = weatherParameterDao;
	}
	
 

	@Override
	public void addWeatherParameter(WeatherParameter weatherParameter) {
		if(null == weatherParameter.getSensor()){
			throw new BusinessException(ErrorCode.NULL_VALUE);
		}				
		weatherParameterDao.save(weatherParameter);	 
	}

	@Override
	public void editWeatherParameter(WeatherParameter weatherParameter) {
		if(null == weatherParameter.getSensor()){
			throw new BusinessException(ErrorCode.NULL_VALUE);
		}		 
		weatherParameterDao.update(weatherParameter);
	}

	@Override
	public void deleteWeatherParameter(WeatherParameter weatherParameter) {
		weatherParameterDao.delete(weatherParameter);
	}

	@Override
	public void deleteWeatherParameter(
			Collection<WeatherParameter> weatherParameters) {
		weatherParameterDao.deleteAll(weatherParameters);
	}

	@Override
	public WeatherParameter getWeatherParameter(String id) {		
		return weatherParameterDao.findById(id);
	}

	@Override
	public WeatherParameter getWeatherParameterBySensor(Sensor sensor) {
		List<WeatherParameter> list = weatherParameterDao.getAllByProperty("sensor", sensor);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<WeatherParameter> getAllWeatherParameters() {
		return this.weatherParameterDao.getAll();
	}
}
