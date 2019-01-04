/*
 * Project platform
 *
 * Class SensorServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-6-22 下午02:11:15
 *
 * ModifiedBy 

 * ModifyAt 2011-07-12 16：30

 * ModifyDescription 添加Add、Delete和Set功能接口实现

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.archive.service.impl;


import java.util.Collection;
import java.util.List;

import com.yixin.A1000.archive.dao.SensorFunctionDao;
import com.yixin.A1000.archive.model.SensorFunction;
import com.yixin.A1000.archive.model.SensorType;
import com.yixin.A1000.archive.service.SensorFunctionService;
import com.yixin.framework.system.model.AuthResource;

/**
 * 监测类型功能业务逻辑具体实现
 * 
 * @version v1.0.0
 * @author 梁立全

 */
public class SensorFunctionServiceImpl implements SensorFunctionService {
    
	/** 监测类型功能DAO接口 **/
	private  SensorFunctionDao  sensorFunctionDao;
	
	/**
	 * 设置监测类型功能DAO接口
	 * @param sensorFunctionDao
	 */
	public void setSensorFunctionDao(SensorFunctionDao sensorFunctionDao) {
		this.sensorFunctionDao = sensorFunctionDao;
	}

	@Override
	public List<SensorFunction> getAllSensorFunctions(SensorType sensorType) {
		return this.sensorFunctionDao.getAllByProperty("sensorType", sensorType);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.archive.service.SensorFunctionService#addSensorFunction(com.yixin.ca2000.archive.model.SensorType, com.yixin.framework.system.model.AuthResource)
	 */
	@Override
	public void addSensorFunction(SensorFunction sensorFunction) {
		this.sensorFunctionDao.save(sensorFunction);	
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.archive.service.SensorFunctionService#DeleteSensorFunction(com.yixin.ca2000.archive.model.SensorFunction)
	 */
	@Override
	public void deleteSensorFunction(SensorFunction sensorFunction) {
		this.sensorFunctionDao.delete(sensorFunction);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.archive.service.SensorFunctionService#setSensorFunctions(com.yixin.ca2000.archive.model.SensorType, java.util.Collection)
	 */
	@Override
	public void setSensorFunctions(SensorType sensorType, Collection<AuthResource> authResources) {
		List<SensorFunction> sensorFunctions = this.getAllSensorFunctions(sensorType);
		if(null != authResources && authResources.size() == 0){ //全部删除
			deleteSensorFunctions(sensorFunctions);
		}else {
			for(SensorFunction sensorFunction : sensorFunctions){
				AuthResource authResource = sensorFunction.getAuthResource();
				if(!authResources.contains(authResource)) //删除当前未选
					this.deleteSensorFunction(sensorFunction);
				else 
					authResources.remove(authResource);	//移除相同项
			}
			//添加新增项
			for(AuthResource authResource : authResources){	 
				SensorFunction sensorFunction = new SensorFunction();
				sensorFunction.setAuthResource(authResource);
				sensorFunction.setSensorType(sensorType);
				
				this.addSensorFunction(sensorFunction);
			}
		}
			
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.archive.service.SensorFunctionService#deleteSensorFunctions(java.util.Collection)
	 */
	@Override
	public void deleteSensorFunctions(Collection<SensorFunction> sensorFunctions) {
		if(null != sensorFunctions ){
			for(SensorFunction sensorFunction : sensorFunctions)
				this.deleteSensorFunction(sensorFunction);
		}
	}
	
}
