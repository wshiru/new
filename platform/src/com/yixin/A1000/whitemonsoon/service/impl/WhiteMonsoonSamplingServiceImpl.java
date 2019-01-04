/*
 * Project platform
 *
 * Classname WhiteMonsoonSamplingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 15:08
 *
 * ModifiedBy 
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public WhiteMonsoonSampling getLastWhiteMonsoon(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.whitemonsoon.service.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.impl.BaseSamplingServiceImpl;
import com.yixin.A1000.whitemonsoon.model.WhiteMonsoonSampling;
import com.yixin.A1000.whitemonsoon.service.WhiteMonsoonSamplingService;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.DateProperty;
/**
 * 相间风偏业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class WhiteMonsoonSamplingServiceImpl extends BaseSamplingServiceImpl<WhiteMonsoonSampling> implements WhiteMonsoonSamplingService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.wire.service.WireSagSamplingService#addWireSagSampling
	 * (com.yixin.ca2000.wire.model.WireSagSampling)
	 */
	@Override
	public void addWhiteMonsoonSampling(WhiteMonsoonSampling whiteMonsoonSampling) {
		if (null != whiteMonsoonSampling)
			this.samplingDao.save(whiteMonsoonSampling);
	}

	@Override
	public void addWhiteMonsoonSampling(List<WhiteMonsoonSampling> whiteMonsoonSamplings) {
		Iterator<WhiteMonsoonSampling> it = whiteMonsoonSamplings.iterator();
		while (it.hasNext()) {
			WhiteMonsoonSampling whiteMonsoonSampling = it.next();
			this.addWhiteMonsoonSampling(whiteMonsoonSampling);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.whitemonsoon.service.WhiteMonsoonSamplingService#
	 * getLastWhiteMonsoon(com.yixin.ca2000.archive.model.Sensor)
	 */
	@Override
	public WhiteMonsoonSampling getLastWhiteMonsoon(Sensor sensor) {
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
		Collection<WhiteMonsoonSampling> results = this.samplingDao.getPageByProperties(map, dateProperty,
				1, 1).getRecords();
		return (results.size() > 0) ? results.iterator().next() : null;
	}
}
