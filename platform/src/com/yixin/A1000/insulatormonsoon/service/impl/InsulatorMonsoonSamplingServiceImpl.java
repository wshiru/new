/*
 * Project platform
 *
 * Classname InsulatorMonsoonSamplingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 15:19
 *
 * ModifiedBy 
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public InsulatorMonsoonSampling getLastInsulatorMonsoon(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.insulatormonsoon.service.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.impl.BaseSamplingServiceImpl;
import com.yixin.A1000.insulatormonsoon.model.InsulatorMonsoonSampling;
import com.yixin.A1000.insulatormonsoon.service.InsulatorMonsoonSamplingService;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.DateProperty;

/**
 * 绝缘子串风偏业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class InsulatorMonsoonSamplingServiceImpl extends BaseSamplingServiceImpl<InsulatorMonsoonSampling> implements InsulatorMonsoonSamplingService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.wire.service.WireSagSamplingService#addWireSagSampling
	 * (com.yixin.ca2000.wire.model.WireSagSampling)
	 */
	@Override
	public void addInsulatorMonsoonSampling(InsulatorMonsoonSampling insulatorMonsoonSampling) {
		if (null != insulatorMonsoonSampling)
			this.samplingDao.save(insulatorMonsoonSampling);
	}


	@Override
	public void addInsulatorMonsoonSampling(List<InsulatorMonsoonSampling> insulatorMonsoonSamplings) {
		Iterator<InsulatorMonsoonSampling> it = insulatorMonsoonSamplings.iterator();
		while (it.hasNext()) {
			InsulatorMonsoonSampling insulatorMonsoonSampling = it.next();
			this.addInsulatorMonsoonSampling(insulatorMonsoonSampling);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.insulatormonsoon.service.InsulatorMonsoonSamplingService
	 * #getLastInsulatorMonsoon(com.yixin.ca2000.archive.model.Sensor)
	 */
	@Override
	public InsulatorMonsoonSampling getLastInsulatorMonsoon(Sensor sensor) {
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
		Collection<InsulatorMonsoonSampling> results = this.samplingDao.getPageByProperties(map,
				dateProperty, 1, 1).getRecords();
		return (results.size() > 0) ? results.iterator().next() : null;
	}
}
