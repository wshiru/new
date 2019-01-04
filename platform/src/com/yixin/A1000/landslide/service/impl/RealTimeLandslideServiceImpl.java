/*
 * Project platform
 *
 * Class RealTimeTowerTiltServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-7-8 下午04:45:13
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.landslide.service.impl;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.comm.CommService;
import com.yixin.A1000.landslide.model.LandslideParameter;
import com.yixin.A1000.landslide.model.LandslideParameterDetail;
import com.yixin.A1000.landslide.model.LandslideSampling;
import com.yixin.A1000.landslide.model.LandslideSamplingDetail;
import com.yixin.A1000.landslide.service.LandslideParameterService;
import com.yixin.A1000.landslide.service.RealTimeLandslideService;

/**
 * 地质滑坡数据召测业务实现类
 * 
 * 
 * @version v1.0.0
 * @author 
 */
public class RealTimeLandslideServiceImpl implements RealTimeLandslideService {

	/** 通讯服务类 */
	private CommService commService;
	
	/**
	 * 设置 通讯服务类
	 * 
	 * @param commService
	 *            通讯服务类
	 */
	public void setCommService(CommService commService) {
		this.commService = commService;
	}	

	@Override
	public LandslideSampling samplingLandslide(Sensor sensor) {	
		return this.commService.landslide(sensor.getSensorCode());
	}
}
