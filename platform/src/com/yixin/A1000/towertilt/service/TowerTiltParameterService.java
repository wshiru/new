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

import java.util.Collection;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.towertilt.model.TowerTiltParameter;

/**
 * 杆塔倾斜参数业务接口
 * 
 * @version v1.0.0
 * @author 

 */
public interface TowerTiltParameterService {

	/**
	 * 添加杆塔倾斜参数
	 * 
	 * @param towerTiltSampling
	 */
	public abstract void addTowerTiltParameter(TowerTiltParameter towerTiltParameter);


	
	/**
	 * 修改杆塔倾斜参数
	 * @param towerTiltParameter
	 */
	public abstract void editTowerTiltParameter(TowerTiltParameter towerTiltParameter);
	
	/**
	 * 删除杆塔倾斜参数
	 * @param towerTiltParameter
	 */
	public abstract void deleteTowerTiltParameter(TowerTiltParameter towerTiltParameter);
	
	/**
	 * 删除多项杆塔倾斜参数
	 * @param towerTiltParameters
	 */
	public abstract void deleteTowerTiltParameter(Collection<TowerTiltParameter> towerTiltParameters);

	/**
	 * 根据ID得到杆塔倾斜参数
	 * @param id
	 * @return
	 */
	public abstract TowerTiltParameter  getTowerTiltParameter(String id);
	
	/**
	 * Sensor
	 * @param sensor
	 * @return
	 */
	public abstract TowerTiltParameter getTowerTiltParameterBySensor(Sensor sensor);
	
	/**
	 * 得到所有杆塔倾斜参数
	 * @return
	 */
	public abstract List<TowerTiltParameter> getAllTowerTiltParameters();



}
