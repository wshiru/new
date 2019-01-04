/*
 * Project platform
 *
 * Classname CmaParameterSettingServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-10-24
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.setting.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.setting.dao.DeviceParameterDao;
import com.yixin.A1000.setting.dao.TaskConfigDao;
import com.yixin.A1000.setting.model.DeviceParameter;
import com.yixin.A1000.setting.model.ProtocolCmdSubType;
import com.yixin.A1000.setting.model.ProtocolCmdType;
import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.A1000.setting.service.DeviceParameterService;
import com.yixin.A1000.setting.service.DeviceParameterSettingService;
import com.yixin.A1000.setting.service.TaskConfigService;
import com.yixin.framework.system.model.User;

public class DeviceParameterSettingServiceImpl implements DeviceParameterSettingService {

	/** 监测代理参数操作DAO接口 */
	private DeviceParameterDao deviceParameterDao;
	
	/** 任务DAO接口 **/
	private TaskConfigDao    taskConfigDao;

	/** 监测代理业务接口 */
	private DeviceParameterService deviceParameterService;

	/** 作务业务接口 **/
	private TaskConfigService taskConfigService;

	
	public void setDeviceParameterDao(DeviceParameterDao deviceParameterDao) {
		this.deviceParameterDao = deviceParameterDao;
	}



	public DeviceParameterDao getDeviceParameterDao() {
		return deviceParameterDao;
	}



	public void setTaskConfigDao(TaskConfigDao taskConfigDao) {
		this.taskConfigDao = taskConfigDao;
	}



	public void setDeviceParameterService(
			DeviceParameterService deviceParameterService) {
		this.deviceParameterService = deviceParameterService;
	}



	public void setTaskConfigService(TaskConfigService taskConfigService) {
		this.taskConfigService = taskConfigService;
	}



	@Override
	public DeviceParameter getLastConfigCmaParams(CmaInfo cmaInfo) {
		return this.deviceParameterDao.getLastConfigCmaParams(cmaInfo);
	}


	@Override
	public DeviceParameter getLastConfigSensorParams(Sensor sensor) {
		return this.deviceParameterDao.getLastConfigSensorParams(sensor);
	}


	//未下发的CMA“召测任务"
	@Override
	public List<TaskConfig> getUnfinishedReadTaskConfig(CmaInfo cmaInfo) {
		
		Map<String, Object> taskConfgMap = new HashMap<String, Object>();
		taskConfgMap.put("cmaCode", cmaInfo.getCmaCode());
		taskConfgMap.put("cmdType", ProtocolCmdType.GETCONFIG);
		taskConfgMap.put("state", 0);		
		return   this.taskConfigDao.getAllByProperties(taskConfgMap);
		
	}



	// 未下发的CMD召测任务
	@Override
	public List<TaskConfig> getUnfinishedReadTaskConfig(Sensor sensor) {

		Map<String, Object> taskConfgMap = new HashMap<String, Object>();
		taskConfgMap.put("sensorCode", sensor.getSensorCode());
		taskConfgMap.put("cmdType", ProtocolCmdType.GETCONFIG);
		taskConfgMap.put("state", 0);		
		return   this.taskConfigDao.getAllByProperties(taskConfgMap);
		
	}


	@Override
	public List<TaskConfig> getUnfinishedWriteTaskConfig(CmaInfo cmaInfo) {
		Map<String, Object> taskConfgMap = new HashMap<String, Object>();
		taskConfgMap.put("cmaCode", cmaInfo.getCmaCode());
		taskConfgMap.put("cmdType", ProtocolCmdType.SETCONFIG);
		taskConfgMap.put("state", 0);		
		return   this.taskConfigDao.getAllByProperties(taskConfgMap);
	}



	@Override
	public List<TaskConfig> getUnfinishedWriteTaskConfig(Sensor sensor) {
		Map<String, Object> taskConfgMap = new HashMap<String, Object>();
		taskConfgMap.put("sensorCode", sensor.getSensorCode());
		taskConfgMap.put("cmdType", ProtocolCmdType.SETCONFIG);
		taskConfgMap.put("state", 0);		
		return   this.taskConfigDao.getAllByProperties(taskConfgMap);
		
	}

	@Override
	public void WriteCmaPareter(User user,DeviceParameter deviceParameter) {
		
	    //将CMA的所有未下发的“下发任务” 状态变更为“取消”
		List<TaskConfig>  taskConfigs = this.getUnfinishedWriteTaskConfig(deviceParameter.getCmaInfo());
		
		for ( TaskConfig taskConfig : taskConfigs){
			taskConfig.setState(2); //取消任务
			this.taskConfigService.updateTaskConfig(taskConfig);

			//将CMA的所有未“下发任务"对应的参数状态变更为“取消”  deviceparameterID关联
			DeviceParameter parameter = this.deviceParameterDao.findById(taskConfig.getDeviceParameterId());
			if ( null != parameter){
				parameter.setState(2);//取消参数
				this.deviceParameterService.editDeviceParameter(parameter);
			}
			
		}
			
		//新增CMA参数任务
		Date createTime = new Date();
		deviceParameter.setCreateTime(createTime);
	    this.deviceParameterService.addDeviceParameter(deviceParameter);
	    
		//新增CMA下发任务
	    TaskConfig taskConfig = new TaskConfig();
	    taskConfig.setDeviceParameterId(deviceParameter.getDeviceParameterId());//UUID
		taskConfig.setCmdType(ProtocolCmdType.SETCONFIG);
		taskConfig.setCreateTime(createTime);
		taskConfig.setCmaCode(deviceParameter.getCmaInfo().getCmaCode());
		taskConfig.setState(0);
		taskConfig.setSubCmdType(ProtocolCmdSubType.NORMAL);
		taskConfig.setUser(user);
		this.taskConfigService.addTaskConfig(taskConfig);
		
	}


	@Override
	public void WriteSensorPareter(User user,DeviceParameter deviceParameter) {
        
		//将Sensor的所有未下发的下发任务 状态变更为“取消”
		List<TaskConfig>  taskConfigs = this.getUnfinishedWriteTaskConfig(deviceParameter.getSensor());
		
		for ( TaskConfig taskConfig : taskConfigs){
			taskConfig.setState(2); //取消任务
			this.taskConfigService.updateTaskConfig(taskConfig);

			//将Sensor的所有未“下发任务"对应的参数状态变更为“取消”  deviceparameterID关联
			DeviceParameter parameter = this.deviceParameterDao.findById(taskConfig.getDeviceParameterId());
			if ( null != parameter){
				parameter.setState(2);//取消参数
				this.deviceParameterService.editDeviceParameter(parameter);
			}
			
		}
		
		//新增Sensor参数
		Date createTime = new Date();
		deviceParameter.setCreateTime(createTime);
	    this.deviceParameterService.addDeviceParameter(deviceParameter);
	    
   	    //新增Sensor下发任务
	    TaskConfig taskConfig = new TaskConfig();
	    taskConfig.setDeviceParameterId(deviceParameter.getDeviceParameterId());
		taskConfig.setCmdType(ProtocolCmdType.SETCONFIG);
		taskConfig.setCreateTime(createTime);
		taskConfig.setSensorCode(deviceParameter.getSensor().getSensorCode());
		taskConfig.setState(0);
		taskConfig.setSubCmdType(ProtocolCmdSubType.NORMAL);
		taskConfig.setUser(user);
		this.taskConfigService.addTaskConfig(taskConfig);
		
	}


	@Override
	public void ReadCmaPareter(User user,CmaInfo cmaInfo) {
		
		//将CMA的所有未下发的召测任务 状态变更为“取消”
		List<TaskConfig>  taskConfigs = this.getUnfinishedReadTaskConfig(cmaInfo);
		
		for ( TaskConfig taskConfig : taskConfigs){
			taskConfig.setState(2); //取消任务
			this.taskConfigService.updateTaskConfig(taskConfig);			
		}
			    
   	    //新增CMA召测任务
	    TaskConfig taskConfig = new TaskConfig();
		taskConfig.setCmdType(ProtocolCmdType.GETCONFIG);
		taskConfig.setCreateTime(new Date());
		taskConfig.setCmaCode(cmaInfo.getCmaCode());
		taskConfig.setState(0);
		taskConfig.setUser(user);
		
		this.taskConfigService.addTaskConfig(taskConfig);
		
	}



	@Override
	public void ReadSensorPareter(User user,Sensor sensor) {
		//将Sensor的所有未下发的召测任务 状态变更为“取消”
		List<TaskConfig>  taskConfigs = this.getUnfinishedReadTaskConfig(sensor);
		
		for ( TaskConfig taskConfig : taskConfigs){
			taskConfig.setState(2); //取消任务
			this.taskConfigService.updateTaskConfig(taskConfig);			
		}
			    
   	    //新增Sensor召测任务
	    TaskConfig taskConfig = new TaskConfig();
		taskConfig.setCmdType(ProtocolCmdType.GETCONFIG);
		taskConfig.setCreateTime(new Date());
		taskConfig.setSensorCode(sensor.getSensorCode());
		taskConfig.setState(0);
		taskConfig.setUser(user);
		this.taskConfigService.addTaskConfig(taskConfig);
		
		
	}

	
	
	

}
