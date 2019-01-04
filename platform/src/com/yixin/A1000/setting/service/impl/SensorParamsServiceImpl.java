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
package com.yixin.A1000.setting.service.impl;



import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.setting.dao.SensorParamsDao;
import com.yixin.A1000.setting.model.SensorParams;
import com.yixin.A1000.setting.service.SensorParamsService;


/**
 * 监测代理参数业务逻辑接口实现类

 * 
 * @version v1.0.0
 * @author 詹朝轮

 */
public class  SensorParamsServiceImpl  implements SensorParamsService {

	private  SensorParamsDao sensorParamsDao;
	private  SensorService  sensorService;
	
	
	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}


	public void setSensorParamsDao(SensorParamsDao sensorParamsDao) {
		this.sensorParamsDao = sensorParamsDao;
	}

	
	@Override
	public List<SensorParams> getAllSensorParams() {
		return this.sensorParamsDao.getAll();
	}


	@Override
	public void editSensorParams(List<SensorParams> sensorParamsList) {
		Iterator<SensorParams>  it = sensorParamsList.iterator();
		while (it.hasNext()){
			SensorParams  sensorParams = it.next();
			if ( sensorParams.getSensorParamsId() != null ){
				this.editSensorParams(sensorParams);	
			}
			else {
				this.addSensorParams(sensorParams);
			}
			
		}
	}

	
	@Override
	public void editSensorParams(SensorParams sensorParams) {
		this.sensorParamsDao.update(sensorParams);
	}


	@Override
	public void addSensorParams(SensorParams sensorParams) {
		this.sensorParamsDao.save(sensorParams);
	}

	
	@Override
	public SensorParams getConfirmSensorParams(String sensorCode) {
		SensorParams  sensorParams = null;
		Sensor sensor = this.sensorService.getSensorbySensorCode(sensorCode);
		if ( sensor != null ){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sensor", sensor);
			map.put("state", 1);
			
			List<SensorParams> infos = this.sensorParamsDao.getAllByProperties(map);
			if (infos != null ){
				if  ( infos.size() > 0 ){
					sensorParams =  infos.iterator().next();
				}
			}
			
		}
		return sensorParams;
		
	}

	
	@Override
	public SensorParams getUnrecognizedSensorParams(String sensorCode) {
		Sensor sensor = this.sensorService.getSensorbySensorCode(sensorCode);
		if ( sensor != null ){
			SensorParams  sensorParams = null;
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sensor", sensor);
			map.put("state", 0);
			
			List<SensorParams> infos = this.sensorParamsDao.getAllByProperties(map);
			if (infos != null ){
				if  ( infos.size() > 0 ){
					sensorParams =  infos.iterator().next();
				}
			}
			return sensorParams;
		}
		else {
				return null;
		}
	}


	
	

}
