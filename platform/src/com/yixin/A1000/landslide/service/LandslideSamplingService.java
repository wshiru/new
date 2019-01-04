/*
 * Project platform
 *
 * Classname LandslideSamplingService
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 13:41
 *
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.landslide.service;

import java.util.Date;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.BaseSamplingService;
import com.yixin.A1000.landslide.model.LandslideSampling;
import com.yixin.A1000.landslide.model.LandslideSamplingDetail;

/**
 * 地质滑坡监测服务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface LandslideSamplingService extends BaseSamplingService<LandslideSampling>{
	public abstract List<LandslideSamplingDetail> getSamplingLandslideDetail(LandslideSampling landslideSampling);
	public abstract List<LandslideSamplingDetail> getSamplingLandslideDetail(Sensor sensor,Integer pointNo,Date beginDate,Date endDate);
	public abstract LandslideSampling getLastLandslideDetail(Sensor sensor);	

}
