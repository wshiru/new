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

import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.setting.dao.CmaParamsDao;
import com.yixin.A1000.setting.dao.TaskConfigDao;
import com.yixin.A1000.setting.model.CmaParams;
import com.yixin.A1000.setting.model.ProtocolCmdSubType;
import com.yixin.A1000.setting.model.ProtocolCmdType;
import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.A1000.setting.service.CmaParamsService;
import com.yixin.A1000.setting.service.CmaParamsSettingService;
import com.yixin.A1000.setting.service.TaskConfigService;
import com.yixin.framework.system.model.User;

/**
 * 监测代理参数下发查询业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class CmaParamsSettingServiceImpl implements CmaParamsSettingService {

	/** 监测代理DAO接口 */
	private CmaParamsDao cmaParamsDao;

	/** 任务DAO接口 **/
	private TaskConfigDao taskConfigDao;

	/** 监测代理业务接口 */
	private CmaParamsService cmaParamsService;

	/** 作务业务接口 **/
	private TaskConfigService taskConfigService;

	/**
	 * 设置 监测代理DAO接口
	 * 
	 * @param cmaParamsDao
	 *            监测代理DAO接口
	 */
	public void setCmaParamsDao(CmaParamsDao cmaParamsDao) {
		this.cmaParamsDao = cmaParamsDao;
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
	 * 设置 监测代理业务接口
	 * 
	 * @param cmaParamsService
	 *            监测代理业务接口
	 */
	public void setCmaParamsService(CmaParamsService cmaParamsService) {
		this.cmaParamsService = cmaParamsService;
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
	 * com.yixin.ca2000.setting.service.SettingService#getCmaParams(com.yixin
	 * .ca2000.archive.model.Cma)
	 */
	@Override
	public CmaParams getDbCmaParams(CmaInfo cma) {
		return this.cmaParamsService.getConfirmCmaParams(cma.getCmaCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.setting.service.CmaParamsSettingService#readCmaParams
	 * (com.yixin.framework.system.model.User,
	 * com.yixin.ca2000.setting.model.CmaParams)
	 */
	@Override
	public void readCmaParams(User user, CmaInfo cma) {

		/* 更新任务列表中未执行的任务 */
		Map<String, Object> unfinishedTaskConfgMap = new HashMap<String, Object>();
		unfinishedTaskConfgMap.put("cmaCode", cma.getCmaCode());
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
		taskConfig.setCmaCode(cma.getCmaCode());
		taskConfig.setState(0);
		taskConfig.setUser(user);
		this.taskConfigService.addTaskConfig(taskConfig);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.setting.service.CmaParamsSettingService#
	 * getUnfinishedReadTaskConfig(com.yixin.ca2000.archive.model.CmaInfo)
	 */
	@Override
	public TaskConfig getUnfinishedReadTaskConfig(CmaInfo cma) {
		Map<String, Object> unfinishedTaskConfgMap = new HashMap<String, Object>();
		unfinishedTaskConfgMap.put("cmaCode", cma.getCmaCode());
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
	 * com.yixin.ca2000.setting.service.SettingService#addCmaParams(com.yixin
	 * .ca2000.setting.model.CmaParams)
	 */
	@Override
	public void addCmaParams(User user, CmaParams cmaParams) {
		cmaParams.setState(0);
		Date createTime = new Date();

		/* 更新任务列表中未执行的任务 */
		Map<String, Object> unfinishedTaskConfgMap = new HashMap<String, Object>();
		unfinishedTaskConfgMap.put("cmaCode", cmaParams.getCmaInfo().getCmaCode());
		unfinishedTaskConfgMap.put("cmdType", ProtocolCmdType.SETCONFIG);
		unfinishedTaskConfgMap.put("state", 0);
		List<TaskConfig> unfinishedTaskConfgList = this.taskConfigDao.getAllByProperties(unfinishedTaskConfgMap);
		for (TaskConfig task : unfinishedTaskConfgList) {
			task.setState(2);
		}
		this.taskConfigDao.updateAll(unfinishedTaskConfgList);

		/* 更新参数表中未下发的任务 */
		Map<String, Object> unfinishedParamMap = new HashMap<String, Object>();
		unfinishedParamMap.put("cmaInfo", cmaParams.getCmaInfo());
		unfinishedParamMap.put("state", 0);
		List<CmaParams> unfinishedParamList = this.cmaParamsDao.getAllByProperties(unfinishedParamMap);
		for (CmaParams cp : unfinishedParamList) {
			cp.setState(2);
		}
		this.cmaParamsDao.updateAll(unfinishedParamList);

		/* 新增下发任务 */
		cmaParams.setCreateTime(createTime);
		this.cmaParamsService.addCmaParams(cmaParams);
		TaskConfig taskConfig = new TaskConfig();
		taskConfig.setCmdType(ProtocolCmdType.SETCONFIG);
		taskConfig.setCreateTime(createTime);
		taskConfig.setCmaCode(cmaParams.getCmaInfo().getCmaCode());
		taskConfig.setState(0);
		taskConfig.setSubCmdType(ProtocolCmdSubType.NORMAL);
		taskConfig.setUser(user);
		this.taskConfigService.addTaskConfig(taskConfig);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.setting.service.SettingService#
	 * getCmaParamsUnfinishedTasks(com.yixin.ca2000.archive.model.Cma)
	 */
	@Override
	public Map<String, Object> getCmaParamsUnfinishedTasks(CmaInfo cma) {
		Map<String, Object> taskConfgMap = new HashMap<String, Object>();
		taskConfgMap.put("cmaCode", cma.getCmaCode());
		taskConfgMap.put("state", 0);
		TaskConfig taskConfg = null;
		List<TaskConfig> taskConfigs = this.taskConfigDao.getAllByProperties(taskConfgMap);
		if (taskConfigs.size() > 0) {
			taskConfg = taskConfigs.get(0);
		}

		Map<String, Object> cmaParamsMap = new HashMap<String, Object>();
		cmaParamsMap.put("cmaInfo", cma);
		cmaParamsMap.put("state", 0);
		CmaParams cmaParam = null;
		List<CmaParams> cmaParams = this.cmaParamsDao.getAllByProperties(cmaParamsMap);
		if (cmaParams.size() > 0) {
			cmaParam = cmaParams.get(0);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskConfig", taskConfg);
		result.put("cmaParams", cmaParam);
		return result;
	}
}
