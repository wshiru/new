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
package com.yixin.A1000.setting.dao.impl.springhibernate;

import java.util.List;

import com.yixin.A1000.setting.dao.TaskConfigDao;
import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.framework.base.dao.impl.springhibernate.BaseDaoImpl;

/**
 * 参数任务DAO接口
 * 
 * @author 梁立全
 * 
 */
public class TaskConfigDaoImpl extends BaseDaoImpl<TaskConfig, String> implements TaskConfigDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.setting.dao.TaskConfigDao#
	 * getAllCmaNotIssuedTaskConfigByCmaCodes(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskConfig> getAllCmaNotIssuedTaskConfigByCmaCodes(List<String> cmaCodes) {
		StringBuilder queryString = new StringBuilder("from TaskConfig where state=0 ");
		if (cmaCodes.size() > 0) {
			queryString.append(" and (cmaCode=?");
		}
		for (int i = 1, len = cmaCodes.size(); i < len; i++) {
			queryString.append(" or cmaCode=?");
		}
		if (cmaCodes.size() > 0) {
			queryString.append(" )");
			return this.getHibernateTemplate().find(queryString.toString(), cmaCodes.toArray());
		} else {
			return this.getHibernateTemplate().find(queryString.toString());			
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.setting.dao.TaskConfigDao#
	 * getAllSensorNotIssuedTaskConfigBySensorCodes(java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskConfig> getAllSensorNotIssuedTaskConfigBySensorCodes(List<String> sensorCodes) {
		StringBuilder queryString = new StringBuilder("from TaskConfig where state=0 ");
		if (sensorCodes.size() > 0) {
			queryString.append(" and (sensorCode=?");
		}
		for (int i = 1, len = sensorCodes.size(); i < len; i++) {
			queryString.append(" or sensorCode=?");
		}
		if (sensorCodes.size() > 0) {
			queryString.append(" )");
			return this.getHibernateTemplate().find(queryString.toString(), sensorCodes.toArray());
		} else {
			return this.getHibernateTemplate().find(queryString.toString());
		}
	}
}
