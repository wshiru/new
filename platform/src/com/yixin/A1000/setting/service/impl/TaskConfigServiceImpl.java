/*
 * Project platform
 * Class TowerService.java
 * Version 1.0.0
 * Creator 梁立全
 * CreateAt 2011-6-21 下午07:33:27
 *
 * ModifiedBy 
 * ModifyAt 
 *          1、新增public List<TaskConfig> getAllTaskConfigs(TaskConfigQueryModel taskConfigQueryModel);
 *          2、新增public Page<TaskConfig> getPageTaskConfigs(TaskConfigQueryModel taskConfigQueryModel, long pageNo, long pageSize)；
 *          3、新增private Collection<QueryProperty> createQuery(TaskConfigQueryModel taskConfigQueryModel);
 * ModifyDescription 2011-7-4 下午02:33:27
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.setting.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.dao.CmaInfoDao;
import com.yixin.A1000.archive.dao.SensorDao;
import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.model.UpgradeFile;
import com.yixin.A1000.cag.model.CmdType;
import com.yixin.A1000.setting.dao.DeviceParameterDao;
import com.yixin.A1000.setting.dao.SensorParamsDao;
import com.yixin.A1000.setting.dao.TaskConfigDao;
import com.yixin.A1000.setting.model.DeviceParameter;
import com.yixin.A1000.setting.model.DeviceParameterDetail;
import com.yixin.A1000.setting.model.ProtocolCmdType;
import com.yixin.A1000.setting.model.SensorParams;
import com.yixin.A1000.setting.model.SettingErrorCode;
import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.A1000.setting.model.TaskConfigQueryModel;
import com.yixin.A1000.setting.service.DeviceParameterService;
import com.yixin.A1000.setting.service.TaskConfigService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.QueryProperty;
import com.yixin.framework.exception.BusinessException;


/**
 * 参数任务信息业务逻辑接口实现类
 * 
 * @version v1.0.0
 * @author 詹朝轮
 */
public class TaskConfigServiceImpl implements TaskConfigService {

	/** 作务DAO接口 **/
	private TaskConfigDao taskConfigDao;

	/** 监测装置DAO接口 */
	private SensorDao sensorDao;

	/** 监测代理DAO接口 */
	private CmaInfoDao cmaInfoDao;

	/** 监测装置参数DAO接口 */
	private SensorParamsDao sensorParamsDao;

	/** 监测代理参数DAO接口 */
	private DeviceParameterDao deviceParameterDao;
	
	//参数接口
	private DeviceParameterService deviceParameterService;
	

	/**
	 * 设置 作务DAO接口
	 * 
	 * @param taskConfigDao
	 *            作务DAO接口
	 */
	public void setTaskConfigDao(TaskConfigDao taskConfigDao) {
		this.taskConfigDao = taskConfigDao;
	}

	/**
	 * 设置 监测装置DAO接口
	 * 
	 * @param sensorDao
	 *            监测装置DAO接口
	 */
	public void setSensorDao(SensorDao sensorDao) {
		this.sensorDao = sensorDao;
	}

	/**
	 * 设置 监测代理DAO接口
	 * 
	 * @param cmaInfoDao
	 *            监测代理DAO接口
	 */
	public void setCmaInfoDao(CmaInfoDao cmaInfoDao) {
		this.cmaInfoDao = cmaInfoDao;
	}

	/**
	 * 设置 监测装置参数DAO接口
	 * 
	 * @param sensorParamsDao
	 *            监测装置参数DAO接口
	 */
	public void setSensorParamsDao(SensorParamsDao sensorParamsDao) {
		this.sensorParamsDao = sensorParamsDao;
	}
	

	@Override
	public void addTaskConfig(TaskConfig taskConfig) {
		this.taskConfigDao.save(taskConfig);
	}

	public void setDeviceParameterDao(DeviceParameterDao deviceParameterDao) {
		this.deviceParameterDao = deviceParameterDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.setting.service.TaskConfigService#getTaskConfig(java
	 * .lang.String)
	 */
	@Override
	public TaskConfig getTaskConfig(String id) {
		return this.taskConfigDao.findById(id);
	}

	@Override
	public List<TaskConfig> getNotIssuedTaskConfig() {
		return this.taskConfigDao.getAllByProperty("state", 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.setting.service.TaskConfigService#
	 * getAllCmaNotIssuedTaskConfigByCmaCodes(java.util.List)
	 */
	@Override
	public List<TaskConfig> getAllCmaNotIssuedTaskConfigByCmaCodes(List<String> cmaCodes) {
		return this.taskConfigDao.getAllCmaNotIssuedTaskConfigByCmaCodes(cmaCodes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.setting.service.TaskConfigService#
	 * getAllSensorNotIssuedTaskConfigBySensorCodes(java.util.List)
	 */
	@Override
	public List<TaskConfig> getAllSensorNotIssuedTaskConfigBySensorCodes(List<String> sensorCodes) {
		return this.taskConfigDao.getAllSensorNotIssuedTaskConfigBySensorCodes(sensorCodes);
	}
	

	@Override
	public List<TaskConfig> getTaskConfig(Integer state, String DataType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cmdType", DataType);
		map.put("state", state);
		return this.taskConfigDao.getAllByProperties(map);
	}

	@Override
	public void updateAllIssuedAndIsReadTask(List<TaskConfig> taskConfigs) {
		Iterator<TaskConfig> it = taskConfigs.iterator();
		while (it.hasNext()) {
			TaskConfig info = it.next();
			info.setExecuteTime(new Date()); // 更改执行时间
			info.setState(3); // 更改任务状态为已执行成功

			this.taskConfigDao.update(info);
		}

	}

	@Override
	public void updateAlWriteTaskConfig(List<TaskConfig> taskConfigs) {
		Iterator<TaskConfig> it = taskConfigs.iterator();
		while (it.hasNext()) {
			TaskConfig info = it.next();
			info.setExecuteTime(new Date()); // 更改执行时间
			info.setState(1); // 更改任务状态为已下发

			this.taskConfigDao.update(info);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.setting.service.TaskConfigService#getAllTaskConfigs(
	 * com.yixin.ca2000.setting.model.TaskConfigQueryModel)
	 */
	@Override
	public List<TaskConfig> getAllTaskConfigs(TaskConfigQueryModel taskConfigQueryModel) {
		Collection<QueryProperty> queryProperties = this.createQuery(taskConfigQueryModel);
		return this.taskConfigDao.getAll(queryProperties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.setting.service.TaskConfigService#getPageTaskConfigs
	 * (com.yixin.ca2000.setting.model.TaskConfigQueryModel, long, long)
	 */
	@Override
	public Page<TaskConfig> getPageTaskConfigs(TaskConfigQueryModel taskConfigQueryModel, long pageNo, long pageSize) {
		Collection<QueryProperty> queryProperties = this.createQuery(taskConfigQueryModel);
		return this.taskConfigDao.getPage(queryProperties, pageNo, pageSize);
	}

	/**
	 * 根据taskConfigQueryModel创建查询属性集
	 * 
	 * @param taskConfigQueryModel
	 *            任务查询模型
	 * @return 组成的查询属性集
	 */
	private Collection<QueryProperty> createQuery(TaskConfigQueryModel taskConfigQueryModel) {
		Collection<QueryProperty> queryProperties = new ArrayList<QueryProperty>();
		if (null != taskConfigQueryModel.getState() && -1 != taskConfigQueryModel.getState()) {
			QueryProperty qp = new QueryProperty("state", taskConfigQueryModel.getState(),
					taskConfigQueryModel.getStateQueryMode());
			queryProperties.add(qp);
		}
		if (null != taskConfigQueryModel.getCmaCode()) {
			QueryProperty qp = new QueryProperty("cmaCode", taskConfigQueryModel.getCmaCode(),
					taskConfigQueryModel.getCmaCodeQueryMode());
			queryProperties.add(qp);
		}
		if (null != taskConfigQueryModel.getCmdType()) {
			QueryProperty qp = new QueryProperty("cmdType", taskConfigQueryModel.getCmdType(),
					taskConfigQueryModel.getCmdTypeQueryMode());
			queryProperties.add(qp);
		}
		if (null != taskConfigQueryModel.getCreateTime()) {
			QueryProperty qp = new QueryProperty("createTime", taskConfigQueryModel.getCreateTime(),
					taskConfigQueryModel.getCreateTimeQueryMode(), taskConfigQueryModel.getCreateTimeQueryMode()
							.getEnd());
			queryProperties.add(qp);
		}
		if (null != taskConfigQueryModel.getExecuteTime()) {
			QueryProperty qp = new QueryProperty("executeTime", taskConfigQueryModel.getExecuteTime(),
					taskConfigQueryModel.getExecuteTimeQueryMode(), taskConfigQueryModel.getExecuteTimeQueryMode()
							.getEnd());
			queryProperties.add(qp);
		}
		if (null != taskConfigQueryModel.getSensorCode()) {
			QueryProperty qp = new QueryProperty("sensorCode", taskConfigQueryModel.getSensorCode(),
					taskConfigQueryModel.getSensorCodeQueryMode());
			queryProperties.add(qp);
		}
		if (null != taskConfigQueryModel.getSubCmdType()) {
			QueryProperty qp = new QueryProperty("subCmdType", taskConfigQueryModel.getSubCmdType(),
					taskConfigQueryModel.getSubCmdTypeQueryMode());
			queryProperties.add(qp);
		}
		if (null != taskConfigQueryModel.getUser()) {
			QueryProperty qp = new QueryProperty("user", taskConfigQueryModel.getUser(),
					taskConfigQueryModel.getUserQueryMode());
			queryProperties.add(qp);
		}
		return queryProperties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.setting.service.TaskConfigService#getTaskConfigDetail
	 * (java.lang.String)
	 */
	@Override
	public Map<String, Object> getTaskConfigDetail(String id) {
		TaskConfig taskConfig = this.taskConfigDao.findById(id);
		return this.getTaskConfigDetail(taskConfig);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.setting.service.TaskConfigService#getTaskConfigDetail
	 * (com.yixin.ca2000.setting.model.TaskConfig)
	 */
	@Override
	public Map<String, Object> getTaskConfigDetail(TaskConfig taskConfig) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("taskConfig", taskConfig);
		
		String  paramId = taskConfig.getDeviceParameterId();
		List<DeviceParameterDetail>  details = new ArrayList<DeviceParameterDetail>();
		
		if ( null != paramId ){
			DeviceParameter deviceParameter = this.deviceParameterService.getDeviceParameter(paramId);
			if ( null != deviceParameter ){
				if ( null != deviceParameter.getDeviceParameterDetails()){
					details =  deviceParameter.getDeviceParameterDetails();
				}
			}
		}
		result.put("paramDetails", details);
		
		UpgradeFile  upgradeFile = taskConfig.getUpgradeFile();
		result.put("upgradeFile", upgradeFile);
		

		return result;
	}

	public void setDeviceParameterService(
			DeviceParameterService deviceParameterService) {
		this.deviceParameterService = deviceParameterService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.setting.service.TaskConfigService#cancel(com.yixin.ca2000
	 * .setting.model.TaskConfig)
	 */
	@Override
	public void cancelTaskConfig(TaskConfig taskConfig) {
		if (1 == taskConfig.getState()) {
			throw new BusinessException(SettingErrorCode.TASKCONFIG_ALREADY_SEND);
		}
		if (3 == taskConfig.getState()) {
			throw new BusinessException(SettingErrorCode.TASKCONFIG_ALREADY_FINISHED);
		}
		taskConfig.setState(2);
		if (ProtocolCmdType.SETCONFIG.equalsIgnoreCase(taskConfig.getCmdType())) { // 写配置交互，则更改相应的数据表中的状态
			if (null != taskConfig.getSensorCode()) { // 如果是监测装置写配置，则更改监测装置参数状态
				Map<String, Object> map = new HashMap<String, Object>();
				Sensor sensor = this.sensorDao.findById(taskConfig.getSensorCode());
				map.put("sensor", sensor);
				List<SensorParams> sensorParams = this.sensorParamsDao.getAllByProperties(map);
				for (SensorParams param : sensorParams) {
					param.setState(2);
				}
				this.sensorParamsDao.updateAll(sensorParams);
			} else if (null != taskConfig.getCmaCode()) { // 如果是监测代理写配置，则更改监测代理参数状态
				Map<String, Object> map = new HashMap<String, Object>();
				CmaInfo cmaInfo = this.cmaInfoDao.findById(taskConfig.getCmaCode());
				map.put("cmaInfo", cmaInfo);
				List<DeviceParameter> cmaParams = this.deviceParameterDao.getAllByProperties(map);
				for (DeviceParameter param : cmaParams) {
					param.setState(2);
				}
				this.deviceParameterDao.updateAll(cmaParams);
			}
		}
		this.taskConfigDao.update(taskConfig);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.setting.service.TaskConfigService#getAllUnfinishedUpdateTask
	 * (com.yixin.ca2000.archive.model.UpgradeFile)
	 */
	@Override
	public List<TaskConfig> getAllUnfinishedUpdateTasks(UpgradeFile upgradeFile) {
		Map<String, Object> unfinishedTaskConfgMap = new HashMap<String, Object>();
		unfinishedTaskConfgMap.put("upgradeFile", upgradeFile);
		unfinishedTaskConfgMap.put("state", 0);
		return this.taskConfigDao.getAllByProperties(unfinishedTaskConfgMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.setting.service.TaskConfigService#getAllUnfinishedUpdateTask
	 * (com.yixin.ca2000.archive.model.Sensor)
	 */
	@Override
	public List<TaskConfig> getAllUnfinishedUpdateTasks(Sensor sensor) {
		Map<String, Object> unfinishedTaskConfgMap = new HashMap<String, Object>();
		unfinishedTaskConfgMap.put("sensorCode", sensor.getSensorCode());
		unfinishedTaskConfgMap.put("state", 0);
		return this.taskConfigDao.getAllByProperties(unfinishedTaskConfgMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.setting.service.TaskConfigService#getAllUnfinishedUpdateTask
	 * (com.yixin.ca2000.archive.model.CmaInfo)
	 */
	@Override
	public List<TaskConfig> getAllUnfinishedUpdateTasks(CmaInfo cmaInfo) {
		Map<String, Object> unfinishedTaskConfgMap = new HashMap<String, Object>();
		unfinishedTaskConfgMap.put("cmaCode", cmaInfo.getCmaCode());
		unfinishedTaskConfgMap.put("state", 0);
		return this.taskConfigDao.getAllByProperties(unfinishedTaskConfgMap);
	}

	@Override
	public void updateTaskConfig(TaskConfig taskConfig) {
		this.taskConfigDao.update(taskConfig);
	}

	@Override
	public void updateSensorTaskConfig(String deviceParameterId, Sensor sensor) {
        //获取已下发的传感器参数
		Map<String, Object> finishedTaskConfgMap = new HashMap<String, Object>();
		finishedTaskConfgMap.put("sensorCode", sensor.getSensorCode());
		finishedTaskConfgMap.put("cmdType", CmdType.GETCONFIG.getCode());
		finishedTaskConfgMap.put("state", 1);
		List<TaskConfig>  taskConfigs =  this.taskConfigDao.getAllByProperties(finishedTaskConfgMap);
		if ( null != taskConfigs ){
			Iterator<TaskConfig> it = taskConfigs.iterator();
			while  (it.hasNext()){
				TaskConfig taskConfig =  it.next();
				taskConfig.setState(3);//改为状态为已执行成功
				taskConfig.setDeviceParameterId(deviceParameterId);//记录对应上传的参数ID
				taskConfig.setExecuteTime(new Date());
				this.taskConfigDao.update(taskConfig);
			}
		}
		
	}

	@Override
	public void updateCmaInfoTaskConfig(String deviceParameterId,
			CmaInfo cmaInfo) {
		 //获取已下发的监测装置参数
		Map<String, Object> finishedTaskConfgMap = new HashMap<String, Object>();
		finishedTaskConfgMap.put("cmaCode", cmaInfo.getCmaCode());
		finishedTaskConfgMap.put("cmdType", CmdType.GETCONFIG.getCode());
		finishedTaskConfgMap.put("state", 1);
		
		List<TaskConfig>  taskConfigs =  this.taskConfigDao.getAllByProperties(finishedTaskConfgMap);
		if ( null != taskConfigs ){
			Iterator<TaskConfig> it = taskConfigs.iterator();
			while  (it.hasNext()){
				TaskConfig taskConfig =  it.next();
				taskConfig.setState(3);//改为状态为已执行成功
				taskConfig.setDeviceParameterId(deviceParameterId);  //记录对应上传的参数ID
				taskConfig.setExecuteTime(new Date());
				this.taskConfigDao.update(taskConfig);
			}
		}
	}
}
