/*
 * Project platform
 *
 * Class SensorService.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-6-21 下午07:51:14
 *
 * ModifiedBy 

 * ModifyAt 2011-07-12 16：20

 * ModifyDescription 添加Add、Delete和Set功能接口

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.archive.service;



import java.util.Collection;
import java.util.List;

import com.yixin.A1000.archive.model.SensorFunction;
import com.yixin.A1000.archive.model.SensorType;
import com.yixin.framework.system.model.AuthResource;


/**
 * 监测类型功能业务逻辑接口
 * 
 * @version v1.0.0
 * @author 梁立全

 */
public interface SensorFunctionService {

	/**
	 * 获取某监测类型的功能
	 * @param sensorType  监测类型
	 * @return
	 */
	public abstract List<SensorFunction> getAllSensorFunctions(SensorType sensorType);
	
	/**
	 * 添加监测类型功能
	 * @param sensorType
	 * 			监测类型
	 * @param authResource
	 * 			权限功能
	 */
	public abstract void addSensorFunction(SensorFunction sensorFunction);
	
	/**
	 * 删除监测类型功能
	 * @param sensorFunction
	 * 			监测类型功能
	 */
	public abstract void deleteSensorFunction(SensorFunction sensorFunction);
	
	/**
	 * 删除多个监测类型功能
	 * @param sensorFunctions
	 * 			监测类型功能集
	 */
	public abstract void deleteSensorFunctions(Collection<SensorFunction> sensorFunctions);
	
	/**
	 * 设置监测类型的功能
	 * @param sensorType
	 * 			监测类型
	 * @param authResources
	 * 			功能权限集
	 */
	public abstract void setSensorFunctions(SensorType sensorType, Collection<AuthResource> authResources);
	
}
