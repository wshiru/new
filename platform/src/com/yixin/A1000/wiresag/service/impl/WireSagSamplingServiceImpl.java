/*
 * Project platform
 *
 * Classname WireSagSamplingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 13:56
 *
 * ModifiedBy 
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public abstract WireSagSampling getLastWireSag(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.wiresag.service.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.impl.BaseSamplingServiceImpl;
import com.yixin.A1000.wiresag.model.WireSagSampling;
import com.yixin.A1000.wiresag.service.WireSagSamplingService;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.DateProperty;

/**
 * 导线弧垂业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class WireSagSamplingServiceImpl extends BaseSamplingServiceImpl<WireSagSampling> implements WireSagSamplingService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.wire.service.WireSagSamplingService#addWireSagSampling
	 * (com.yixin.ca2000.wire.model.WireSagSampling)
	 */
	@Override
	public void addWireSagSampling(WireSagSampling wireSagSampling) {
		if (null != wireSagSampling)
			this.samplingDao.save(wireSagSampling);
	}


	@Override
	public void addWireSagSampling(List<WireSagSampling> wireSagSamplings) {
		Iterator<WireSagSampling> it = wireSagSamplings.iterator();
		while (it.hasNext()) {
			WireSagSampling wireSagSampling = it.next();
			this.addWireSagSampling(wireSagSampling);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.wiresag.service.WireSagSamplingService#getLastWireSag
	 * (com.yixin.ca2000.archive.model.Sensor)
	 */
	@Override
	public WireSagSampling getLastWireSag(Sensor sensor) {
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
		Collection<WireSagSampling> results = this.samplingDao.getPageByProperties(map, dateProperty, 1, 1)
				.getRecords();
		return (results.size() > 0) ? results.iterator().next() : null;
	}
}
