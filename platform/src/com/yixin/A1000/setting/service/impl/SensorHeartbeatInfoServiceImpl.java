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
 * ModifiedBy 

 * ModifyAt 2011-07-06 16：14

 * ModifyDescription 添加查询接口实现，检查是否在线

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.setting.service.impl;



import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.dao.SensorDao;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.common.constant.BusinessConstants;
import com.yixin.A1000.setting.dao.SensorHeartbeatInfoDao;
import com.yixin.A1000.setting.model.SensorHeartbeatInfo;
import com.yixin.A1000.setting.model.SettingErrorCode;
import com.yixin.A1000.setting.service.SensorHeartbeatInfoService;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.service.impl.BaseServiceImpl;
import com.yixin.framework.exception.BusinessException;


/**
 * 监测装置心跳信息业务逻辑接口实现类

 * 
 * @version v1.0.0
 * @author 詹朝轮

 */
public class SensorHeartbeatInfoServiceImpl extends BaseServiceImpl implements SensorHeartbeatInfoService {

	private  SensorHeartbeatInfoDao sensorHeartbeatInfoDao;

	private  SensorDao sensorDao;


	public void setSensorHeartbeatInfoDao(
			SensorHeartbeatInfoDao sensorHeartbeatInfoDao) {
		this.sensorHeartbeatInfoDao = sensorHeartbeatInfoDao;
	}
	public void setSensorDao(SensorDao sensorDao) {
		this.sensorDao = sensorDao;
	}

	
	@Override
	public void addSensorHeartbeatInfo(SensorHeartbeatInfo sensorHeartbeatInfo) {
		Sensor  sensor = null;
		String sensorId = sensorHeartbeatInfo.getSensor().getSensorId();
		if  ( sensorId != null && !sensorId.equals("") ){
			sensor  = this.sensorDao.findById(sensorId);	
		}
		if ( sensor == null ) {
			throw new BusinessException(SettingErrorCode.SETTING_SENSOR_INVALID);
		}
		this.sensorHeartbeatInfoDao.save(sensorHeartbeatInfo);	
	}

	@Override
	public void deleteSensorHeartbeatInfo(
			SensorHeartbeatInfo sensorHeartbeatInfo) {
		this.sensorHeartbeatInfoDao.delete(sensorHeartbeatInfo);
	}

	@Override
	public void deleteSensorHeartbeatInfos(
			Collection<SensorHeartbeatInfo> sensorHeartbeatInfos) {
		this.sensorHeartbeatInfoDao.deleteAll(sensorHeartbeatInfos);
	}

	@Override
	public List<SensorHeartbeatInfo> getAllSensorHeartbeatInfos() {
		return this.sensorHeartbeatInfoDao.getAll();
	}
	
	
	@Override
	public void addSensorHeartbeatInfo(
			List<SensorHeartbeatInfo> sensorHeartbeatInfos) {
		Iterator<SensorHeartbeatInfo> it = sensorHeartbeatInfos.iterator();
		while ( it.hasNext() ){
			SensorHeartbeatInfo  info = it.next();
			this.addSensorHeartbeatInfo(info);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.setting.service.SensorHeartbeatInfoService#getSensorHeartbeatInfos(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<SensorHeartbeatInfo> getSensorHeartbeatInfos(Sensor sensor,
			Date beginTime, Date endTime) {
		return this.sensorHeartbeatInfoDao.getAllByProperties(createPropertiesMap(sensor), 
				createDateProperty("createTime", beginTime, endTime, DataOrder.DESC));
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.setting.service.SensorHeartbeatInfoService#getPageSensorHeartbeatInfos(java.lang.String, java.util.Date, java.util.Date, long, long)
	 */
	@Override
	public Page<SensorHeartbeatInfo> getPageSensorHeartbeatInfos(
			Sensor sensor, Date beginTime, Date endTime, long pageNo,
			long pageSize) {
		return this.sensorHeartbeatInfoDao.getPageByProperties(createPropertiesMap(sensor), 
				createDateProperty("createTime", beginTime, endTime, DataOrder.DESC), pageNo, pageSize);
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.setting.service.SensorHeartbeatInfoService#checkOnline(com.yixin.ca2000.archive.model.Sensor)
	 */
	@Override
	public boolean checkOnline(Sensor sensor) {
		List<SensorHeartbeatInfo> sensorHeartbeatInfos = this.getSensorHeartbeatInfos(sensor, 
				null, null);
		if(null != sensorHeartbeatInfos && sensorHeartbeatInfos.size() > 0){
			SensorHeartbeatInfo sensorHeartbeatInfo = sensorHeartbeatInfos.get(0);
			Date createTime = sensorHeartbeatInfo.getCreateTime();
			if(null != createTime){
				Date nowDate = new Date();
				long diffMins = (nowDate.getTime() - createTime.getTime())/(1000*60);
				long interval = BusinessConstants.CMA_HEARTBEAT_INTERVAL;
				if(diffMins < interval && sensorHeartbeatInfo.getStatus() == 1)
					return true;
			}
		}
			
		return false;
	}
	
	/**
	 * 生成属性映射集合
	 * @param sensor
	 * @return
	 */
	protected Map<String, Object> createPropertiesMap(Sensor sensor){
		Map<String, Object> map = null;
		if (null != sensor ) {
			map = new HashMap<String, Object>();
			if(null != sensor.getSensorId() && !sensor.getSensorId().isEmpty())
				map.put("sensor", sensor);
			else if (null !=sensor.getBySensorCode() && !sensor.getBySensorCode().trim().isEmpty()) {
				map.put("sensor.sensorCode", sensor.getBySensorCode());
			}
		}
		return map;
	}
	
}
