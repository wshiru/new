/*
 * Project platform
 *
 * Classname CmaParameterService.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-10-24
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.setting.service;

import java.util.List;

import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.setting.model.DeviceParameter;

/**
 *  设备参数服务接口
 * 
 * @version v1.0.0
 * @author 

 */
public interface DeviceParameterService {

	/**
	 * 根据ID获取参数值

	 * @param id
	 * @return
	 */
	public abstract   DeviceParameter getDeviceParameter(String id);
	

	/** 
	 * 新增设备参数
	 * @param deviceParamsList 
	 */
	public abstract void addDeviceParams(List<DeviceParameter> deviceParamsList);
	

	/** 
	 * 修改设备参数  单个
	 * @param deviceParameter 
	 */
	public abstract void editDeviceParameter(DeviceParameter deviceParameter);

	
	/** 
	 * 增加设备参数  单个
	 * @param deviceParameter 
	 */
	public abstract void addDeviceParameter(DeviceParameter deviceParameter);
	
	
	
	/**
	 * 获取某个CMA最后一次上报参数的数据（作为同步） State：状态为2：已执行的
	 * @param cmaInfo 监测代理
	 * @return
	 */
	public abstract DeviceParameter getLastConfigCmaParams(CmaInfo cmaInfo);
	
	
	/**
	 * 获取某个监测装置最后一次上报参数的数据（作为同步） State：状态为2：已执行的
	 * @param sensor 监测装置
	 * @return
	 */
	public abstract DeviceParameter getLastConfigSensorParams(Sensor sensor);
	
    	
	
}
