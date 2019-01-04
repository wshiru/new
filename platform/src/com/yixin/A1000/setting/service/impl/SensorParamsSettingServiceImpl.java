/*
 * Project platform
 *
 * Class SettingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-4 上午09:34:45
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.setting.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.setting.dao.SensorParamsDao;
import com.yixin.A1000.setting.dao.TaskConfigDao;
import com.yixin.A1000.setting.model.ProtocolCmdSubType;
import com.yixin.A1000.setting.model.ProtocolCmdType;
import com.yixin.A1000.setting.model.SensorParams;
import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.A1000.setting.service.SensorParamsService;
import com.yixin.A1000.setting.service.SensorParamsSettingService;
import com.yixin.A1000.setting.service.TaskConfigService;
import com.yixin.framework.system.model.User;

/**
 * 监测装置参数下发查询业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class SensorParamsSettingServiceImpl implements SensorParamsSettingService {

	/** 监测装置DAO接口 */
	private SensorParamsDao sensorParamsDao;

	/** 任务DAO接口 **/
	private TaskConfigDao taskConfigDao;

	/** 监测装置业务接口 */
	private SensorParamsService sensorParamsService;

	/** 作务业务接口 **/
	private TaskConfigService taskConfigService;

	/**
	 * 设置 监测装置DAO接口
	 * 
	 * @param sensorParamsDao
	 *            监测装置DAO接口
	 */
	public void setSensorParamsDao(SensorParamsDao sensorParamsDao) {
		this.sensorParamsDao = sensorParamsDao;
	}

	/**
	 * 设置 任务DAO接口
	 * 
	 * @param taskConfigDao
	 *            任务DAO接口
	 */
	public void setTaskConfigDao(TaskConfigDao taskConfigDao) {
		this.taskConfigDao = taskConfigDao;
	}

	/**
	 * 设置 监测装置业务接口
	 * 
	 * @param sensorParamsService
	 *            监测装置业务接口
	 */
	public void setSensorParamsService(SensorParamsService sensorParamsService) {
		this.sensorParamsService = sensorParamsService;
	}

	/**
	 * 设置 作务业务接口
	 * 
	 * @param taskConfigService
	 *            作务业务接口
	 */
	public void setTaskConfigService(TaskConfigService taskConfigService) {
		this.taskConfigService = taskConfigService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.setting.service.SettingService#getSensorParams(com.yixin
	 * .ca2000.archive.model.Sensor)
	 */
	@Override
	public SensorParams getDbSensorParams(Sensor sensor) {
		return this.sensorParamsService.getConfirmSensorParams(sensor.getSensorCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.setting.service.SensorParamsSettingService#readSensorParams
	 * (com.yixin.framework.system.model.User,
	 * com.yixin.ca2000.archive.model.Sensor)
	 */
	@Override
	public void readSensorParams(User user, Sensor sensor) {

		/* 更新任务列表中未执行的任务 */
		Map<String, Object> unfinishedTaskConfgMap = new HashMap<String, Object>();
		unfinishedTaskConfgMap.put("sensorCode", sensor.getSensorCode());
		unfinishedTaskConfgMap.put("cmdType", ProtocolCmdType.GETCONFIG);
		unfinishedTaskConfgMap.put("state", 0);
		List<TaskConfig> unfinishedTaskConfgList = this.taskConfigDao.getAllByProperties(unfinishedTaskConfgMap);
		for (TaskConfig task : unfinishedTaskConfgList) {
			task.setState(2);
		}
		this.taskConfigDao.updateAll(unfinishedTaskConfgList);

		/* 新增下发任务 */
		TaskConfig taskConfig = new TaskConfig();
		taskConfig.setCmdType(ProtocolCmdType.GETCONFIG);
		taskConfig.setCreateTime(new Date());
		taskConfig.setSensorCode(sensor.getSensorCode());
		taskConfig.setState(0);
		taskConfig.setUser(user);
		this.taskConfigService.addTaskConfig(taskConfig);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.setting.service.SensorParamsSettingService#
	 * getUnfinishedReadTaskConfig(com.yixin.ca2000.archive.model.Sensor)
	 */
	@Override
	public TaskConfig getUnfinishedReadTaskConfig(Sensor sensor) {
		Map<String, Object> unfinishedTaskConfgMap = new HashMap<String, Object>();
		unfinishedTaskConfgMap.put("sensorCode", sensor.getSensorCode());
		unfinishedTaskConfgMap.put("cmdType", ProtocolCmdType.GETCONFIG);
		unfinishedTaskConfgMap.put("state", 0);
		List<TaskConfig> unfinishedTaskConfgList = this.taskConfigDao.getAllByProperties(unfinishedTaskConfgMap);
		if (unfinishedTaskConfgList.size() > 0) {
			return unfinishedTaskConfgList.get(0);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.setting.service.SettingService#addSensorParams(com.yixin
	 * .ca2000.setting.model.SensorParams)
	 */
	@Override
	public void addSensorParams(User user, SensorParams sensorParams) {
		sensorParams.setState(0);
		Date createTime = new Date();

		/* 更新任务列表中未执行的任务 */
		Map<String, Object> unfinishedTaskConfgMap = new HashMap<String, Object>();
		unfinishedTaskConfgMap.put("sensorCode", sensorParams.getSensor().getSensorCode());
		unfinishedTaskConfgMap.put("cmdType", ProtocolCmdType.SETCONFIG);
		unfinishedTaskConfgMap.put("state", 0);
		List<TaskConfig> unfinishedTaskConfgList = this.taskConfigDao.getAllByProperties(unfinishedTaskConfgMap);
		for (TaskConfig task : unfinishedTaskConfgList) {
			task.setState(2);
		}
		this.taskConfigDao.updateAll(unfinishedTaskConfgList);

		/* 更新参数表中未下发的任务 */
		Map<String, Object> unfinishedParamMap = new HashMap<String, Object>();
		unfinishedParamMap.put("sensor", sensorParams.getSensor());
		unfinishedParamMap.put("state", 0);
		List<SensorParams> unfinishedParamList = this.sensorParamsDao.getAllByProperties(unfinishedParamMap);
		for (SensorParams cp : unfinishedParamList) {
			cp.setState(2);
		}
		this.sensorParamsDao.updateAll(unfinishedParamList);

		/* 新增下发任务 */
		sensorParams.setCreateTime(createTime);
		this.sensorParamsService.addSensorParams(sensorParams);
		TaskConfig taskConfig = new TaskConfig();
		taskConfig.setCmdType(ProtocolCmdType.SETCONFIG);
		taskConfig.setCreateTime(createTime);
		taskConfig.setSensorCode(sensorParams.getSensor().getSensorCode());
		taskConfig.setState(0);
		taskConfig.setSubCmdType(ProtocolCmdSubType.NORMAL);
		taskConfig.setUser(user);
		this.taskConfigService.addTaskConfig(taskConfig);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.setting.service.SettingService#
	 * getSensorParamsUnfinishedTasks(com.yixin.ca2000.archive.model.Sensor)
	 */
	@Override
	public Map<String, Object> getSensorParamsUnfinishedTasks(Sensor sensor) {
		Map<String, Object> taskConfgMap = new HashMap<String, Object>();
		taskConfgMap.put("sensorCode", sensor.getSensorCode());
		taskConfgMap.put("state", 0);
		TaskConfig taskConfg = null;
		List<TaskConfig> taskConfigs = this.taskConfigDao.getAllByProperties(taskConfgMap);
		if (taskConfigs.size() > 0) {
			taskConfg = taskConfigs.get(0);
		}

		Map<String, Object> sensorParamsMap = new HashMap<String, Object>();
		sensorParamsMap.put("sensor", sensor);
		sensorParamsMap.put("state", 0);
		SensorParams sensorParam = null;
		List<SensorParams> sensorParams = this.sensorParamsDao.getAllByProperties(sensorParamsMap);
		if (sensorParams.size() > 0) {
			sensorParam = sensorParams.get(0);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskConfig", taskConfg);
		result.put("sensorParams", sensorParam);
		return result;
	}
}
