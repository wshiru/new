/*
 * Project platform
 *
 * Class UpdateServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-14 下午04:00:19
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.setting.service.impl;

import java.util.Date;
import java.util.List;

import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.model.UpgradeFile;
import com.yixin.A1000.setting.dao.TaskConfigDao;
import com.yixin.A1000.setting.model.ProtocolCmdType;
import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.A1000.setting.service.TaskConfigService;
import com.yixin.A1000.setting.service.UpdateService;
import com.yixin.framework.system.model.User;

/**
 * 远程升级业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class UpdateServiceImpl implements UpdateService {

	/** 任务DAO接口 **/
	private TaskConfigDao taskConfigDao;

	/** 作务业务接口 **/
	private TaskConfigService taskConfigService;

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
	 * com.yixin.ca2000.setting.service.UpdateService#addUpdateTask(com.yixin
	 * .framework.system.model.User, com.yixin.ca2000.archive.model.Sensor)
	 */
	@Override
	public void addUpdateTask(User user, Sensor sensor, UpgradeFile upgradeFile) {
		Date createTime = new Date();

		/* 更新任务列表中未执行的任务 */
		List<TaskConfig> unfinishedTaskConfgList = this.taskConfigService.getAllUnfinishedUpdateTasks(sensor);
		for (TaskConfig task : unfinishedTaskConfgList) {
			task.setState(2);
		}
		this.taskConfigDao.updateAll(unfinishedTaskConfgList);

		/* 新增下发任务 */
		TaskConfig taskConfig = new TaskConfig();
		taskConfig.setCreateTime(createTime);
		taskConfig.setSensorCode(sensor.getSensorCode());
		taskConfig.setState(0);
		taskConfig.setUser(user);
		taskConfig.setUpgradeFile(upgradeFile);
		this.taskConfigService.addTaskConfig(taskConfig);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.setting.service.UpdateService#addUpdateTask(com.yixin
	 * .framework.system.model.User, com.yixin.ca2000.archive.model.CmaInfo)
	 */
	@Override
	public void addUpdateTask(User user, CmaInfo cmaInfo, UpgradeFile upgradeFile) {
		Date createTime = new Date();

		/* 更新任务列表中未执行的任务 */
		List<TaskConfig> unfinishedTaskConfgList = this.taskConfigService.getAllUnfinishedUpdateTasks(cmaInfo);
		for (TaskConfig task : unfinishedTaskConfgList) {
			task.setState(2);
		}
		this.taskConfigDao.updateAll(unfinishedTaskConfgList);

		/* 新增下发任务 */
		TaskConfig taskConfig = new TaskConfig();
		taskConfig.setCmdType(ProtocolCmdType.UPGRADE);
		taskConfig.setCreateTime(createTime);
		taskConfig.setCmaCode(cmaInfo.getCmaCode());
		taskConfig.setState(0);
		taskConfig.setUser(user);
		taskConfig.setUpgradeFile(upgradeFile);
		this.taskConfigService.addTaskConfig(taskConfig);
	}
}
