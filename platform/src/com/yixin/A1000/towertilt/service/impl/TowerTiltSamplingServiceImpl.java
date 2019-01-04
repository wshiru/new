/*
 * Project platform
 *
 * Classname TowerTiltSamplingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 15:35
 *
 * ModifiedBy 
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public TowerTiltSampling getLastTowerTilt(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.towertilt.service.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.impl.BaseSamplingServiceImpl;
import com.yixin.A1000.towertilt.dao.TowerTiltSamplingDao;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;
import com.yixin.A1000.towertilt.service.TowerTiltSamplingService;
import com.yixin.A1000.weather.dao.WeatherSamplingDao;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.DateProperty;

/**
 * 杆塔倾斜业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class TowerTiltSamplingServiceImpl extends BaseSamplingServiceImpl<TowerTiltSampling> implements TowerTiltSamplingService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.wire.service.WireSagSamplingService#addWireSagSampling
	 * (com.yixin.ca2000.wire.model.WireSagSampling)
	 */
	@Override
	public void addTowerTiltSampling(TowerTiltSampling TowerTiltSampling) {
		if (null != TowerTiltSampling)
			this.samplingDao.save(TowerTiltSampling);
		 
	}


	@Override
	public void addTowerTiltSampling(List<TowerTiltSampling> towerTiltSamplings) {
		Iterator<TowerTiltSampling> it = towerTiltSamplings.iterator();
		while (it.hasNext()) {
			TowerTiltSampling towerTiltSampling = it.next();
			this.addTowerTiltSampling(towerTiltSampling);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.towertilt.service.TowerTiltSamplingService#getLastTowerTilt
	 * (com.yixin.ca2000.archive.model.Sensor)
	 */
	@Override
	public TowerTiltSampling getLastTowerTilt(Sensor sensor) {
		TowerTiltSamplingDao towerTiltSamplingDao = (TowerTiltSamplingDao) samplingDao;
		return towerTiltSamplingDao.getLastTowerTiltSampling(sensor);
	}
}
