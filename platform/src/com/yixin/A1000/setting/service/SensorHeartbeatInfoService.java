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

 * ModifyAt 2011-07-06 15：52

 * ModifyDescription 添加查询接口，检查是否在线接口

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.setting.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.setting.model.SensorHeartbeatInfo;
import com.yixin.framework.base.model.Page;


/**
 * 监测装置心跳信息业务逻辑接口
 * 
 * @version v1.0.0
 * @author 詹朝轮

 */
public interface SensorHeartbeatInfoService {

	/**
	 * 新增监测装置心跳信息
	 * @param CmaHeartbeatInfo
	 */
	public   abstract   void addSensorHeartbeatInfo(SensorHeartbeatInfo sensorHeartbeatInfo);
		
    

	/**
	 * 新增监测装置心跳信息 多个
	 * @param CmaHeartbeatInfo
	 */
	public   abstract   void addSensorHeartbeatInfo(List<SensorHeartbeatInfo> sensorHeartbeatInfos);
	
	
    /**
     * 	 * 删除监测装置心跳信息  --单个
     * @param cmaHeartbeatInfo
     */
	public abstract void deleteSensorHeartbeatInfo(SensorHeartbeatInfo sensorHeartbeatInfo);

	
	/**
	   	 * 删除监测装置心跳信息  --多个
	 * @param cmaHeartbeatInfos
	 */
	public abstract void deleteSensorHeartbeatInfos(Collection<SensorHeartbeatInfo> sensorHeartbeatInfos);

	
	/**
	 * 返回所有监测装置心跳信息

	 * 
	 * 
	 * @return 信息列表
	 */
	public abstract List<SensorHeartbeatInfo> getAllSensorHeartbeatInfos();

	/**
	 * 查询监测装置心跳信息
	 * @param sensorCode
	 * 			监测装置编码，可为null
	 * @param beginTime
	 * 				开始时间，可为null
	 * @param endTime
	 * 				结束时间，可为null
	 * @return
	 */
	public abstract List<SensorHeartbeatInfo> getSensorHeartbeatInfos(Sensor sensor, Date beginTime, Date endTime);

	/**
	 * 查询监测装置心跳信息分页数据
	 * @param sensorCode
	 * 			监测装置编码，可为null
	 * @param beginTime
	 * 				开始时间，可为null
	 * @param endTime
	 * 				结束时间，可为null
	 * @param pageNo
	 * 				页码
	 * @param pageSize
	 * 				页面大小
	 * @return
	 */
	public abstract Page<SensorHeartbeatInfo> getPageSensorHeartbeatInfos(Sensor sensor, Date beginTime, Date endTime,
			long pageNo, long pageSize);
	/**
	 * 判断监测装置是否在线
	 * @param sensor
	 * @return
	 */
	public abstract boolean checkOnline(Sensor sensor);
}
