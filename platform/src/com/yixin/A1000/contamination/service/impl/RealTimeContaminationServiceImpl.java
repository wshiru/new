﻿/*
 * Project platform
 *
 * Class RealTimeContaminationServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-7-8 下午04:45:13
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.contamination.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.comm.CommService;
import com.yixin.A1000.contamination.model.ContaminationSampling;
import com.yixin.A1000.contamination.service.RealTimeContaminationService;
import com.yixin.A1000.setting.dao.TaskConfigDao;
import com.yixin.A1000.setting.model.ProtocolCmdSubType;
import com.yixin.A1000.setting.model.ProtocolCmdType;
import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.A1000.setting.service.TaskConfigService;
import com.yixin.framework.system.model.User;

/**
 * 污秽度监测数据召测业务实现类
 * 
 * 
 * @version v1.0.0
 * @author 
 */
public class RealTimeContaminationServiceImpl implements RealTimeContaminationService {

	/** 任务DAO接口 **/
	private TaskConfigDao taskConfigDao;

	/** 作务业务接口 **/
	private TaskConfigService taskConfigService;

	/** 通讯服务类 */
	private CommService commService;

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
	 * 设置 任务业务接口
	 * 
	 * @param taskConfigService
	 *            任务业务接口
	 */
	public void setTaskConfigService(TaskConfigService taskConfigService) {
		this.taskConfigService = taskConfigService;
	}

	/**
	 * 设置 通讯服务类
	 * 
	 * @param commService
	 *            通讯服务类
	 */
	public void setCommService(CommService commService) {
		this.commService = commService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.towertilt.service.RealTimeContaminationService#addRealTimeTask
	 * (com.yixin.framework.system.model.User,
	 * com.yixin.ca2000.archive.model.Sensor)
	 */
	@Override
	public void addRealTimeTask(User user, Sensor sensor) {
		Date createTime = new Date();

		/* 更新任务列表中未执行的任务 */
		Map<String, Object> unfinishedTaskConfgMap = new HashMap<String, Object>();
		unfinishedTaskConfgMap.put("sensorCode", sensor.getSensorCode());
		unfinishedTaskConfgMap.put("cmdType", ProtocolCmdType.REALTIME);
		unfinishedTaskConfgMap.put("state", 0);
		List<TaskConfig> unfinishedTaskConfgList = this.taskConfigDao.getAllByProperties(unfinishedTaskConfgMap);
		for (TaskConfig task : unfinishedTaskConfgList) {
			task.setState(2);
		}
		this.taskConfigDao.updateAll(unfinishedTaskConfgList);

		/* 新增下发任务 */
		TaskConfig taskConfig = new TaskConfig();
		taskConfig.setCmdType(ProtocolCmdType.REALTIME);
		taskConfig.setCreateTime(createTime);
		taskConfig.setSensorCode(sensor.getSensorCode());
		taskConfig.setState(0);
		taskConfig.setSubCmdType(ProtocolCmdSubType.TOWERTILT);
		taskConfig.setUser(user);
		this.taskConfigService.addTaskConfig(taskConfig);
	}

	@Override
	public ContaminationSampling samplingContamination(Sensor sensor) {
		return this.commService.contamination(sensor.getSensorCode());
	}
}
