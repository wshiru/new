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
package com.yixin.A1000.landslide.dao;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.landslide.model.LandslideSampling;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.framework.base.dao.BaseDao;

/**
 * 杆塔倾斜主表DAO接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface LandslideSamplingDao extends BaseDao<LandslideSampling, String> {
	/**
	 * 取得最后一次采集的数据
	 * @param sensor
	 * @return
	 */
	public abstract LandslideSampling getLastLandslideSampling(Sensor sensor);
}