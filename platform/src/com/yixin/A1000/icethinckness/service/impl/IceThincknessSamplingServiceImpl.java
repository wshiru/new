/*
 * Project platform
 *
 * Classname IceThincknessSamplingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 15:35
 *
 * ModifiedBy 
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public IceThincknessSampling getLastIceThinckness(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.icethinckness.service.impl;

import java.util.Iterator;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.impl.BaseSamplingServiceImpl;
import com.yixin.A1000.icethinckness.dao.IceThincknessSamplingDao;
import com.yixin.A1000.icethinckness.model.IceThincknessSampling;
import com.yixin.A1000.icethinckness.service.IceThincknessSamplingService;

/**
 * 覆冰监测业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class IceThincknessSamplingServiceImpl extends BaseSamplingServiceImpl<IceThincknessSampling> implements IceThincknessSamplingService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.wire.service.WireSagSamplingService#addWireSagSampling
	 * (com.yixin.ca2000.wire.model.WireSagSampling)
	 */
	@Override
	public void addIceThincknessSampling(IceThincknessSampling IceThincknessSampling) {
		if (null != IceThincknessSampling)
			this.samplingDao.save(IceThincknessSampling);
		 
	}


	@Override
	public void addIceThincknessSampling(List<IceThincknessSampling> iceThincknessSamplings) {
		Iterator<IceThincknessSampling> it = iceThincknessSamplings.iterator();
		while (it.hasNext()) {
			IceThincknessSampling iceThincknessSampling = it.next();
			this.addIceThincknessSampling(iceThincknessSampling);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.towertilt.service.IceThincknessSamplingService#getLastIceThinckness
	 * (com.yixin.ca2000.archive.model.Sensor)
	 */
	@Override
	public IceThincknessSampling getLastIceThinckness(Sensor sensor) {
		IceThincknessSamplingDao iceThincknessSamplingDao = (IceThincknessSamplingDao) samplingDao;
		return iceThincknessSamplingDao.getLastIceThincknessSampling(sensor);
	}
}
