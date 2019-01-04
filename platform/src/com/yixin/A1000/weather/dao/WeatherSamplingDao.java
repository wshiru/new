/*
 * Project platform
 *
 * Classname WeatherSamplingDao.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 14：44
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.weather.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.framework.base.dao.BaseDao;
import com.yixin.framework.base.model.Page;

/**
 * 微气象DAO接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface WeatherSamplingDao extends BaseDao<WeatherSampling, String> {
	public abstract List<Map<String, Object>> getAllDayWeatyerAnalysis(
			Sensor sensor, Date beginTime, Date endTime);

	public abstract Page<Map<String, Object>> getPageDayWeatyerAnalysis(
			Sensor sensor, Date beginTime, Date endTime, final long pageNo,
			final long pageSize);
	/**
	 * 取得最后一次采集的数据
	 * @param sensor
	 * @return
	 */
	public abstract WeatherSampling getLastWeatherSampling(Sensor sensor);
}