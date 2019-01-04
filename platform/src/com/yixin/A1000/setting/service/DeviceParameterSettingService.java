/*
 * Project platform
 *
 * Classname CmaParameterSettingService.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-07-
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
import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.framework.system.model.User;


/**
 * 设备参数操作（下发、抄读）业务接口
 * 
 * @version v1.0.0
 * @author 詹朝轮

 */
public interface DeviceParameterSettingService {
	
	
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

	
	/**
	 * 取得未下发的"召测任务"的监测代理列表
	 * 
	 * @param   CmaInfo 
	 *            监测代理
	 * @return 未下发的召测任务 STATE 为0 
	 */
	public abstract  List<TaskConfig> getUnfinishedReadTaskConfig(CmaInfo cmaInfo);
	
	
	/**
	 * 取得未下发的“召测任务”的监测装置列表
	 * 
	 * @param   sensor  
	 *            监测装置
	 * @return 未下发的召测任务 STATE 为0 
	 */
	public abstract  List<TaskConfig> getUnfinishedReadTaskConfig(Sensor sensor);
	
	
	/**
	 * 取得未下发的"参数设置任务"的监测代理列表
	 * @param cmaInfo   监测代理
	 * @return  
	 */
	public abstract  List<TaskConfig> getUnfinishedWriteTaskConfig(CmaInfo cmaInfo);
	

	
	/**取得未下发的“召测任务”的监测装置列表
	 * 
	 * @param sensor 参数设置任务
	 * @return   STATE 为0 
	 */
	public abstract  List<TaskConfig> getUnfinishedWriteTaskConfig(Sensor sensor);
	
	
	/**
	 * 下发CMA参数
	 * @param deviceParameter
	 */
	public abstract  void  WriteCmaPareter(User user,DeviceParameter deviceParameter);
	
   
	/**
    * 下发监测装置参数
    * @param deviceParameter
    */
	public abstract  void  WriteSensorPareter(User user,DeviceParameter deviceParameter);
	

    /**
     * 读取CMA参数 
     * @param cmaInfo
     */
	public abstract  void  ReadCmaPareter(User user,CmaInfo cmaInfo);
	
	
	/**
	 * 读取监测装置参数
	 * @param sensor
	 */
	public abstract  void  ReadSensorPareter(User user,Sensor sensor);

	
	
	
}
