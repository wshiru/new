/*
 * Project platform
 * Class TowerService.java
 * Version 1.0.0
 * Creator 梁立全
 * CreateAt 2011-6-21 下午07:33:27
 *
 * ModifiedBy 
 * ModifyAt 
 *          1、新增public abstract List<TaskConfig> getAllTaskConfigs(TaskConfigQueryModel taskConfigQueryModel);
 *          2、新增public abstract Page<TaskConfig> getPageTaskConfigs(TaskConfigQueryModel taskConfigQueryModel, long pageNo, long pageSize)；
 * ModifyDescription 2011-7-4 下午02:33:27
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.setting.service;

import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.model.UpgradeFile;
import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.A1000.setting.model.TaskConfigQueryModel;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;

public interface TaskConfigService {

	/**
	 * 新增监测装置心跳信息
	 * 
	 * @param CmaHeartbeatInfo
	 */
	public abstract void addTaskConfig(TaskConfig taskConfig);

	/**
	 * 根据ID查找任务
	 * 
	 * @param id
	 *            要查找任务的ID
	 * @return 任务
	 */
	public abstract TaskConfig getTaskConfig(String id);

	/**
	 * 返回所有未下发的任务信息
	 * 
	 * @return 信息列表
	 */
	public abstract List<TaskConfig> getNotIssuedTaskConfig();
	
	/**
	 * 获取所有包含cmaCodes的未下发任务信息。
	 * 
	 * @param cmaCodes
	 *            要进行查找的监测代理编码
	 * @return 监测代理编码为cmaCodes中一个的所有未下发任务信息
	 */
	public abstract List<TaskConfig> getAllCmaNotIssuedTaskConfigByCmaCodes(List<String> cmaCodes);

	/**
	 * 获取所有包含sensorCodes的未下发任务信息。
	 * 
	 * @param sensorCodes
	 *            要进行查找的监测装置编码
	 * @return 监测装置编码为sensorCodes中一个的所有未下发任务信息
	 */
	public abstract List<TaskConfig> getAllSensorNotIssuedTaskConfigBySensorCodes(List<String> sensorCodes);

	/**
	 * 根据任务类型、状态查找任务信息
	 * 
	 * @param state
	 *            状态
	 * @param DataType
	 *            任务类型
	 * @return
	 */
	public abstract List<TaskConfig> getTaskConfig(Integer state, String DataType);

	/**
	 * 更新任务信息： 1.状态为已下发、2、执行时间为当前时间
	 * 
	 * @param taskConfigs
	 *            任务列表
	 */
	public abstract void updateAlWriteTaskConfig(List<TaskConfig> taskConfigs);

	/** 更新已下发且为参数读取的任务信息 1.状态改为已执行成功、2、执行时间改为当前时间 **/
	public abstract void updateAllIssuedAndIsReadTask(List<TaskConfig> taskConfigs);

	/**
	 * 根据条件查找任务
	 * 
	 * @param taskConfigQueryModel
	 *            查询模型。在这里定义要过滤的条件，以及比较机制。各条件间使用“and”进行连接。
	 * @return 查找到的任务列表
	 */
	public abstract List<TaskConfig> getAllTaskConfigs(TaskConfigQueryModel taskConfigQueryModel);

	/**
	 * 根据条件查找第pageNo页的任务
	 * 
	 * @param taskConfigQueryModel
	 *            查询模型。在这里定义要过滤的条件，以及比较机制。各条件间使用“and”进行连接。
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 查找到的任务列表
	 */
	public abstract Page<TaskConfig> getPageTaskConfigs(TaskConfigQueryModel taskConfigQueryModel, long pageNo,
			long pageSize);

	/**
	 * 根据ID查找任务的详细信息。
	 * 
	 * @param id
	 *            任务的ID
	 * @return。任务详细信息。Map&lt;String, Object&gt;<br />
	 *                               taskConfig - 任务信息。TaskConfig类型<br />
	 *                               cmaParams -
	 *                               监测代理参数。CmaParams类型。如果该任务是对于监测代理且是写配置时，则包含该数据项<br />
	 *                               sensorParams - 监测装置参数。SensorParams类型。
	 *                               如果该任务是对于监测装置的则包含该数据项<br />
	 */
	public abstract Map<String, Object> getTaskConfigDetail(String id);

	/**
	 * 根据任务查找任务的详细信息。
	 * 
	 * @param taskConfig
	 *            任务对象
	 * @return。任务详细信息。Map&lt;String, Object&gt;<br />
	 *                               taskConfig - 任务信息。TaskConfig类型<br />
	 *                               cmaParams -
	 *                               监测代理参数。CmaParams类型。如果该任务是对于监测代理且是写配置时，则包含该数据项<br />
	 *                               sensorParams - 监测装置参数。SensorParams类型。
	 *                               如果该任务是对于监测装置的则包含该数据项<br />
	 */
	public abstract Map<String, Object> getTaskConfigDetail(TaskConfig taskConfig);

	/**
	 * 取消任务。当任务已经下发，或者已经下发成功时，抛出BusinessException异常。
	 * 
	 * @param taskConfig
	 *            要取消的任务
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                SettingErrorCode.TASKCONFIG_ALREADY_SEND-任务已下发，但未执行成功<br />
	 *                SettingErrorCode.TASKCONFIG_ALREADY_FINISHED-任务已下发，并已执行成功
	 */
	public abstract void cancelTaskConfig(TaskConfig taskConfig);
	
	/**
	 * 取得使用升级文件upgradeFile进行升级未完成的任务列表
	 *
	 * @param upgradeFile 升级文件
	 * @return 使用升级文件upgradeFile进行升级未完成的任务列表
	 */
	public abstract List<TaskConfig> getAllUnfinishedUpdateTasks(UpgradeFile upgradeFile);
	
	/**
	 * 取得监测装置sensor未完成的升级任务
	 *
	 * @param sensor 监测装置
	 * @param upgradeFile 升级文件
	 * @return 监测装置sensor未完成的升级任务
	 */
	public abstract List<TaskConfig> getAllUnfinishedUpdateTasks(Sensor sensor);	

	/**
	 * 取得监测代理cmaInfo未完成的升级任务
	 *
	 * @param cmaInfo 监测代理
	 * @param upgradeFile 升级文件
	 * @return 监测代理cmaInfo未完成的升级任务
	 */
	public abstract List<TaskConfig> getAllUnfinishedUpdateTasks(CmaInfo cmaInfo);
	
	
	public abstract void updateTaskConfig(TaskConfig taskConfig);
	
	
	/**
	 * 更新监测装置 状态为“已下发” 的任务为 "已执行成功"
	 * @param deviceParameterId 上传上来的参数ID
	 * @param sensor 监测装置
	 */
	public abstract void updateSensorTaskConfig(String deviceParameterId,Sensor sensor);


	/**
	 * 
	 * 	/**
	 * 更新监测代理 状态为“已下发” 的任务为 "已执行成功"
	 * @param deviceParameterId  上传上来的参数ID
	 * @param cmaInfo  监测代理
	 */
	public abstract void updateCmaInfoTaskConfig(String deviceParameterId,CmaInfo cmaInfo);

	
	
}
