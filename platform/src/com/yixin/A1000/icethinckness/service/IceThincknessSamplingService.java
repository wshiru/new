/*
 * Project platform
 *
 * Classname IceThincknessSamplingService.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-6-23 15:30
 *
 * ModifiedBy 

 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public abstract IceThincknessSampling getLastIceThinckness(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.icethinckness.service;

import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.BaseSamplingService;
import com.yixin.A1000.icethinckness.model.IceThincknessSampling;

/**
 * 覆冰监测业务接口
 * 
 * @version v1.0.0
 * @author 

 */
public interface IceThincknessSamplingService extends BaseSamplingService<IceThincknessSampling>{

	/**
	 * 添加覆冰监测
	 * 
	 * @param iceThincknessSampling
	 */
	public abstract void addIceThincknessSampling(IceThincknessSampling iceThincknessSampling);


	/**
	 * 添加覆冰监测 多条
	 * 
	 * @param iceThincknessSamplings
	 */
	public abstract void addIceThincknessSampling(List<IceThincknessSampling> iceThincknessSamplings);

	/***
	 * 取得最后一次采样的数据，不存在则返回null。

	 * 
	 * @param sensor
	 *            监测装置
	 * @return 最后一次采样的数据，不存在则返回null。

	 */
	public abstract IceThincknessSampling getLastIceThinckness(Sensor sensor);

}
