/*
 * Project platform
 *
 * Class LineDao.java
 * 
 * Version 1.0.0
 * 
 * Creator 梁立全

 * CreateAt 2011-6-21 下午03:49:01
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.setting.dao;

import java.util.List;

import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.framework.base.dao.BaseDao;

/**
 * 参数任务DAO接口
 * 
 * @author 梁立全
 */
public interface TaskConfigDao extends BaseDao<TaskConfig, String> {

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
}
