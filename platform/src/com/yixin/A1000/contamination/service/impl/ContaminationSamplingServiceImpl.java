/*
 * Project platform
 *
 * Classname ContaminationSamplingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 15:35
 *
 * ModifiedBy 
 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public ContaminationSampling getLastContamination(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.contamination.service.impl;

import java.util.Iterator;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.impl.BaseSamplingServiceImpl;
import com.yixin.A1000.contamination.dao.ContaminationSamplingDao;
import com.yixin.A1000.contamination.model.ContaminationSampling;
import com.yixin.A1000.contamination.service.ContaminationSamplingService;

/**
 * 污秽度监测业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class ContaminationSamplingServiceImpl extends BaseSamplingServiceImpl<ContaminationSampling> implements ContaminationSamplingService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.wire.service.WireSagSamplingService#addWireSagSampling
	 * (com.yixin.ca2000.wire.model.WireSagSampling)
	 */
	@Override
	public void addContaminationSampling(ContaminationSampling ContaminationSampling) {
		if (null != ContaminationSampling)
			this.samplingDao.save(ContaminationSampling);
		 
	}


	@Override
	public void addContaminationSampling(List<ContaminationSampling> contaminationSamplings) {
		Iterator<ContaminationSampling> it = contaminationSamplings.iterator();
		while (it.hasNext()) {
			ContaminationSampling contaminationSampling = it.next();
			this.addContaminationSampling(contaminationSampling);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.towertilt.service.ContaminationSamplingService#getLastContamination
	 * (com.yixin.ca2000.archive.model.Sensor)
	 */
	@Override
	public ContaminationSampling getLastContamination(Sensor sensor) {
		ContaminationSamplingDao contaminationSamplingDao = (ContaminationSamplingDao) samplingDao;
		return contaminationSamplingDao.getLastContaminationSampling(sensor);
	}
}
