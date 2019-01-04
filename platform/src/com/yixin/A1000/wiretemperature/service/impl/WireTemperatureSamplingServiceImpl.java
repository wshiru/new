/*
 * Project platform
 *
 * Classname WireTemperatureSamplingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-22 晚上 19:50
 *
 * ModifiedBy 
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public WireTemperatureSampling getLastWireTemperature(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.wiretemperature.service.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.impl.BaseSamplingServiceImpl;
import com.yixin.A1000.wiretemperature.model.WireTemperatureSampling;
import com.yixin.A1000.wiretemperature.service.WireTemperatureSamplingService;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.DateProperty;

/**
 * 导线温度业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class WireTemperatureSamplingServiceImpl extends BaseSamplingServiceImpl<WireTemperatureSampling> implements WireTemperatureSamplingService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.wire.service.WireTemperatureSamplingService#
	 * addWireTemperatureSampling
	 * (com.yixin.ca2000.wire.model.WireTemperatureSampling)
	 */
	@Override
	public void addWireTemperatureSampling(WireTemperatureSampling wireTemperatureSampling) {
		this.samplingDao.save(wireTemperatureSampling);
	}
	

	@Override
	public void addWireTemperatureSampling(List<WireTemperatureSampling> wireTemperatureSamplings) {
		Iterator<WireTemperatureSampling> it = wireTemperatureSamplings.iterator();
		while (it.hasNext()) {
			WireTemperatureSampling wireTemperatureSampling = it.next();
			this.addWireTemperatureSampling(wireTemperatureSampling);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.wiretemperature.service.WireTemperatureSamplingService
	 * #getLastWireTemperature(com.yixin.ca2000.archive.model.Sensor)
	 */
	@Override
	public WireTemperatureSampling getLastWireTemperature(Sensor sensor) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sensor", sensor);
		Calendar endTiime = Calendar.getInstance();
		endTiime.set(endTiime.get(Calendar.YEAR), endTiime.get(Calendar.MONTH), endTiime.get(Calendar.DATE), 23, 59, 59);
		Calendar beginTime = endTiime;
		beginTime.add(Calendar.MONTH, -2);
		beginTime.set(beginTime.get(Calendar.YEAR), beginTime.get(Calendar.MONTH), beginTime.get(Calendar.DATE), 0, 0,
				0);
		DateProperty dateProperty = new DateProperty("samplingTime", beginTime.getTime(), endTiime.getTime());
		dateProperty.setDataOrder(DataOrder.DESC);
		Collection<WireTemperatureSampling> results = this.samplingDao.getPageByProperties(map,
				dateProperty, 1, 1).getRecords();
		return (results.size() > 0) ? results.iterator().next() : null;
	}

}
