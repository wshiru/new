/*
 * Project platform
 *
 * Classname CmaParameterServiceImpl.java
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
package com.yixin.A1000.setting.service.impl;



import java.util.Iterator;
import java.util.List;

import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.setting.dao.DeviceParameterDao;
import com.yixin.A1000.setting.model.DeviceParameter;
import com.yixin.A1000.setting.service.DeviceParameterService;


public class DeviceParameterServiceImpl implements DeviceParameterService {
	
	/**参数DAO操作接口*/
	private DeviceParameterDao deviceParameterDao;
	
   
	public void setDeviceParameterDao(DeviceParameterDao deviceParameterDao) {
		this.deviceParameterDao = deviceParameterDao;
	}

	
	@Override
	public void addDeviceParams(List<DeviceParameter> deviceParamsList) {
		Iterator<DeviceParameter>  it  = deviceParamsList.iterator();
		while ( it.hasNext()) {
			DeviceParameter  info = it.next();
			this.deviceParameterDao.save(info);
		}
	}
	
	@Override
	public void editDeviceParameter(DeviceParameter deviceParameter) {
		this.deviceParameterDao.update(deviceParameter);	
	}
	
	@Override
	public void addDeviceParameter(DeviceParameter deviceParameter) {
		this.deviceParameterDao.save(deviceParameter);
	}


	@Override
	public DeviceParameter getDeviceParameter(String id) {
		return this.deviceParameterDao.findById(id);
	}

	@Override
	public DeviceParameter getLastConfigCmaParams(CmaInfo cmaInfo) {
		return this.deviceParameterDao.getLastConfigCmaParams(cmaInfo);
	}

	@Override
	public DeviceParameter getLastConfigSensorParams(Sensor sensor) {
		return  this.deviceParameterDao.getLastConfigSensorParams(sensor);
	}
	
}
