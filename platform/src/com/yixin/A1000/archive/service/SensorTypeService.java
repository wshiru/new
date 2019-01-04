/*
 * Project platform
 *
 * Class SensorTypeService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-21 下午07:42:09
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
import com.yixin.A1000.archive.model.SensorType;
import com.yixin.A1000.archive.model.SensorTypeQueryModel;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;

/**
 * 监测类型业务逻辑接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface SensorTypeService {

	/**
	 * 新增监测类型。当”监测类型编码”已经存在时，抛出BusinessException异常。
	 * 
	 * @param sensorType
	 *            要添加的监测类型
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.
	 *                ARCHIVE_SENSORTYPE_SENSORTYPECODE_ALREADYEXISTS
	 *                -”监测类型编码”已经存在
	 */
	public abstract void addSensorType(SensorType sensorType);

	/**
	 * 修改监测类型。当”监测类型编码”已经存在时，抛出BusinessException异常。
	 * 
	 * @param sensorType
	 *            要进行修改的监测类型
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.
	 *                ARCHIVE_SENSORTYPE_SENSORTYPECODE_ALREADYEXISTS
	 *                -”监测类型编码”已经存在
	 */
	public abstract void editSensorType(SensorType sensorType);

	/**
	 * 删除监测类型。当存在监测装置依赖于该监测类型时，抛出BusinessException异常。
	 * 
	 * 
	 * @param sensorType
	 *            要进行删除的监测类型
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_SENSORTYPE_EXISTSSENSORS-
	 *                存在监测装置依赖于该监测类型
	 */
	public abstract void deleteSensorType(SensorType sensorType);

	/**
	 * 删除监测类型。当存在监测装置依赖于列表中任一监测类型时，抛出BusinessException异常。
	 * 
	 * @param sensorTypes
	 *            要进行删除的监测类型列表
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_SENSORTYPE_EXISTSSENSORS-
	 *                存在监测装置依赖于该监测类型
	 */
	public abstract void deleteSensorTypes(Collection<SensorType> sensorTypes);

	/**
	 * 根据ID查找监测类型
	 * 
	 * @param id
	 *            要查找监测类型的ID
	 * @return 监测类型
	 */
	public abstract SensorType getSensorType(String id);

	/**
	 * 返回所有监测类型
	 * 
	 * 
	 * @return 监测类型列表
	 */
	public abstract List<SensorType> getAllSensorTypes();

	/**
	 * 返回第pageNo页的监测类型
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 监测类型列表
	 */
	public abstract Page<SensorType> getPageSensorTypes(long pageNo, long pageSize);

	/**
	 * 根据条件查找监测类型
	 * 
	 * @param sensorTypeQueryModel
	 *            查询模型。在这里定义要过滤的条件，以及比较机制。各条件间使用“and”进行连接。
	 * @return 查找到的监测类型列表
	 */
	public abstract List<SensorType> getAllSensorTypes(SensorTypeQueryModel sensorTypeQueryModel);

	/**
	 * 根据条件查找第pageNo页的监测类型
	 * 
	 * @param sensorTypeQueryModel
	 *            查询模型。在这里定义要过滤的条件，以及比较机制。各条件间使用“and”进行连接。
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 查找到的监测类型列表
	 */
	public abstract Page<SensorType> getPageSensorTypes(SensorTypeQueryModel sensorTypeQueryModel, long pageNo,
			long pageSize);

	/**
	 * 取得监测类型sensorType下的所有监测装置
	 * 
	 * 
	 * @param sensorType
	 *            监测类型
	 * @return 监测类型sensorType下的所有监测装置
	 */
	public abstract List<Sensor> getAllSensors(SensorType sensorType);

	/**
	 * 取得监测类型sensorType下第pageNo页的监测装置
	 * 
	 * @param sensorType
	 *            监测类型
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 监测类型sensorType下第pageNo页的监测装置
	 */
	public abstract Page<Sensor> getPageSensors(SensorType sensorType, long pageNo, long pageSize);
}
