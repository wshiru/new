/*
 * Project platform
 *
 * Classname TowerTiltSamplingService.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-6-23 15:30
 *
 * ModifiedBy 

 * ModifyAt 2011-07-08 14:45
 * ModifyDescription 1、新增public abstract TowerTiltSampling getLastTowerTilt(Sensor sensor);
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.towertilt.service;

import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.service.BaseSamplingService;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;

/**
 * 杆塔倾斜业务接口
 * 
 * @version v1.0.0
 * @author 

 */
public interface TowerTiltSamplingService extends BaseSamplingService<TowerTiltSampling>{

	/**
	 * 添加杆塔倾斜
	 * 
	 * @param towerTiltSampling
	 */
	public abstract void addTowerTiltSampling(TowerTiltSampling towerTiltSampling);


	/**
	 * 添加杆塔倾斜 多条
	 * 
	 * @param towerTiltSamplings
	 */
	public abstract void addTowerTiltSampling(List<TowerTiltSampling> towerTiltSamplings);

	/***
	 * 取得最后一次采样的数据，不存在则返回null。

	 * 
	 * @param sensor
	 *            监测装置
	 * @return 最后一次采样的数据，不存在则返回null。

	 */
	public abstract TowerTiltSampling getLastTowerTilt(Sensor sensor);

}
