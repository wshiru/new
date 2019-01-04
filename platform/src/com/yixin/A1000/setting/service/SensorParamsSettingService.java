/*
 * Project platform
 *
 * Class SettingService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-4 上午09:26:25
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.setting.service;

import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.setting.model.SensorParams;
import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.framework.system.model.User;

/**
 * 监测装置参数下发查询业务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface SensorParamsSettingService {

	/**
	 * 从数据库读取最后一次与监测装置同步的数据
	 * 
	 * @param sensor
	 *            监测装置
	 * @return 监测装置参数
	 */
	public abstract SensorParams getDbSensorParams(Sensor sensor);

	/**
	 * 一个监测装置参数的召测任务
	 * 
	 * @param user
	 *            操作的用户
	 * @param sensor
	 *            监测装置
	 */
	public abstract void readSensorParams(User user, Sensor sensor);

	/**
	 * 取得监测装置sensor下未完成的召测任务
	 * 
	 * @param sensor
	 *            监测装置
	 * @return 未完成的召测任务
	 */
	public abstract TaskConfig getUnfinishedReadTaskConfig(Sensor sensor);

	/**
	 * 新增一个监测装置参数的下发任务
	 * 
	 * @param user
	 *            操作的用户
	 * @param sensorParams
	 *            监测装置参数
	 */
	public abstract void addSensorParams(User user, SensorParams sensorParams);

	/**
	 * 取得监测装置sensor下未完成的任务
	 * 
	 * @param sensor
	 *            监测装置
	 * @return 未完成的任务。Map&lt;String, Object&gt;<br />
	 *         taskConfig - 任务信息。TaskConfig类型<br />
	 *         sensorParams - 监测装置参数。SensorParams类型
	 */
	public Map<String, Object> getSensorParamsUnfinishedTasks(Sensor sensor);
}
