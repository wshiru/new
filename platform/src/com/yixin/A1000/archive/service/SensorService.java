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
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.service;

import java.util.Collection;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.model.SensorQueryModel;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;

/**
 * 监测装置业务逻辑接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface SensorService {

	/**
	 * 新增监测装置。当”监测装置编码”已经存在时，抛出BusinessException异常。
	 * 
	 * @param sensor
	 *            要添加的监测装置
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_SENSOR_SENSORCODE_ALREADYEXISTS-”
	 *                监测装置编码”已经存在
	 */
	public abstract void addSensor(Sensor sensor);

	/**
	 * 修改监测装置。当”监测装置编码”已经存在时，抛出BusinessException异常。
	 * 
	 * @param sensor
	 *            要进行修改的监测装置
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_SENSOR_SENSORCODE_ALREADYEXISTS-”
	 *                监测装置编码”已经存在
	 */
	public abstract void editSensor(Sensor sensor);

	/**
	 * 删除监测装置。当存在数据依赖于该监测装置时，抛出BusinessException异常。
	 * 
	 * 
	 * @param sensor
	 *            要进行删除的监测装置
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_SENSOR_EXISTSDATA-存在杆塔依赖于该监测装置
	 */
	public abstract void deleteSensor(Sensor sensor);

	/**
	 * 删除监测装置。当存在数据依赖于列表中任一监测装置时，抛出BusinessException异常。
	 * 
	 * @param sensors
	 *            要进行删除的监测装置列表
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_SENSOR_EXISTSDATA-存在杆塔依赖于该监测装置
	 */
	public abstract void deleteSensors(Collection<Sensor> sensors);

	/**
	 * 根据ID查找监测装置
	 * 
	 * @param id
	 *            要查找监测装置的ID
	 * @return 监测装置
	 */
	public abstract Sensor getSensor(String id);

	/**
	 * 返回所有监测装置
	 * 
	 * 
	 * @return 监测装置列表
	 */
	public abstract List<Sensor> getAllSensors();

	/**
	 * 返回第pageNo页的监测装置
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 监测装置列表
	 */
	public abstract Page<Sensor> getPageSensors(long pageNo, long pageSize);

	/**
	 * 根据条件查找监测装置
	 * 
	 * @param sensorQueryModel
	 *            查询模型。在这里定义要过滤的条件，以及比较机制。各条件间使用“and”进行连接。
	 * @return 查找到的监测装置列表
	 */
	public abstract List<Sensor> getAllSensors(SensorQueryModel sensorQueryModel);

	/**
	 * 根据条件查找第pageNo页的监测装置
	 * 
	 * @param sensorQueryModel
	 *            查询模型。在这里定义要过滤的条件，以及比较机制。各条件间使用“and”进行连接。
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 查找到的监测装置列表
	 */
	public abstract Page<Sensor> getPageSensors(SensorQueryModel sensorQueryModel, long pageNo, long pageSize);
	
	
	
	/**
	 * 获取监测装置信息
	 * @param sensorCode 监测装置编码
	 * @return
	 */
	public abstract Sensor getSensorbySensorCode(String sensorCode);
	
}
