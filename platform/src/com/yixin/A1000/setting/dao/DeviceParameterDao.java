/*
 * Project platform
 *
 * Classname CmaParameterDao.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-10-21 16:28
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.setting.dao;


import java.util.List;

import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.setting.model.DeviceParameter;
import com.yixin.framework.base.dao.BaseDao;

/**
 * 设备参数表数据操作DAO
 * 
 * @version v1.0.0
 * @author 

 */
public interface DeviceParameterDao extends BaseDao<DeviceParameter, String>{

	
	/**
     * 获取所有监测代理参数列表

     *
     * @return
     */
	public abstract  List<DeviceParameter> getAllCmaParams();
	
	

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
