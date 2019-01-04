/*
 * Project platform
 *
 * Classname WarningConditionServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-06 10:22
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.warning.constant.CompareType;
import com.yixin.A1000.warning.dao.WarningConditionDao;
import com.yixin.A1000.warning.model.WarningCondition;
import com.yixin.A1000.warning.model.WarningDict;
import com.yixin.A1000.warning.service.WarningConditionService;

/**
 * 告警条件服务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class WarningConditionServiceImpl implements WarningConditionService {

	/** 告警条件DAO */
	private WarningConditionDao warningConditionDao;
		
	/**
	 * 设置告警条件DAOS
	 * @param warningConditionDao
	 */
	public void setWarningConditionDao(WarningConditionDao warningConditionDao) {
		this.warningConditionDao = warningConditionDao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningConditionService#getWarningCondition(java.lang.String)
	 */
	@Override
	public WarningCondition getWarningCondition(String id) {
		return this.warningConditionDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningConditionService#getWarningCondition(com.yixin.ca2000.warning.model.WarningDict, java.lang.String)
	 */
	@Override
	public List<WarningCondition> getWarningConditions(WarningDict warningDict,
			String fieldName) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != warningDict)
			map.put("warningDict", warningDict);
		if(null != fieldName)
			map.put("fieldName", fieldName);
		return this.warningConditionDao.getAllByProperties(map);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningConditionService#fitWarningCondition(com.yixin.ca2000.warning.model.WarningDict, java.lang.String, java.lang.Double)
	 */
	@Override
	public boolean fitWarningCondition(WarningDict warningDict,
			String fieldName, Double value) {
		List<WarningCondition> wConditions = this.getWarningConditions(warningDict, fieldName);
		for(WarningCondition wCondition : wConditions){
			if(wCondition.fit(value))
				return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningConditionService#updateWarningCondition(com.yixin.ca2000.warning.model.WarningCondition)
	 */
	@Override
	public WarningCondition updateWarningCondition(WarningCondition warningCondition) {
		return null == warningCondition? null: this.warningConditionDao.update(warningCondition);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.service.WarningConditionService#getWarningCondition(com.yixin.ca2000.warning.model.WarningDict, java.lang.String, com.yixin.ca2000.warning.constant.CompareType)
	 */
	@Override
	public WarningCondition getWarningCondition(WarningDict warningDict,
			String fieldName, CompareType compareType) {
		
		if(null != warningDict && null != fieldName && null != compareType){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("warningDict", warningDict);
			map.put("fieldName", fieldName);
			map.put("compare", compareType.getValue());
			List<WarningCondition> conditions = this.warningConditionDao.getAllByProperties(map);
			return conditions.size() > 0 ? conditions.get(0) : null;
		}
		return null;		
	}

}
