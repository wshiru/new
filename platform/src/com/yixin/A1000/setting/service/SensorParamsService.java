/*
 * Project platform
 *
 * Class TowerService.java
 * 
 * Version 1.0.0
 * 
 * Creator 梁立全

 * CreateAt 2011-6-21 下午07:33:27
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.setting.service;

import java.util.List;


import com.yixin.A1000.setting.model.SensorParams;


public interface SensorParamsService {

	
	/** 
     * 获取所有监测装置参数

     * @return
     */
	public abstract List<SensorParams> getAllSensorParams();
	
	
	/**
	 * 根据监测装置编码获取已下发或已确认的监测装置参数
	 * @param sensor 监测装置编码
	 * @return
	 */
	public	abstract  SensorParams  getConfirmSensorParams(String sensorCode);
	

	/**
	 * 根据未下发的监测装置参数
	 * @param sensor 监测装置编码
	 * @return
	 */
	public	abstract  SensorParams  getUnrecognizedSensorParams(String sensorCode);

	

	
	/** 
	 * 修改监测装置参数（存在时修改，不存在的增加）
	 * @param cmaParamsList 
	 */
	public	abstract   void editSensorParams(List<SensorParams> sensorParamsList);
	
	

	/** 
	 * 修改监测装置参数  单个
	 * @param cmaParamsList 
	 */
	public abstract   void editSensorParams(SensorParams sensorParams);


	/** 
	 * 增加监测装置参数  单个
	 * @param cmaParamsList 
	 */
	public abstract   void addSensorParams(SensorParams sensorParams);

	
}
