/*
 * Project platform
 *
 * Classname WarningConditionService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-06 10:11
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.service;

import java.util.List;

import com.yixin.A1000.warning.constant.CompareType;
import com.yixin.A1000.warning.model.WarningCondition;
import com.yixin.A1000.warning.model.WarningDict;

/**
 * 告警条件服务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface WarningConditionService {

	/**
	 * 修改告警条件
	 * @param warningCondition
	 */
	public abstract WarningCondition updateWarningCondition(WarningCondition warningCondition);
	
	/**
	 * 获取告警条件
	 * @param id
	 * 			告警条件ID
	 * @return
	 */
	public abstract WarningCondition getWarningCondition(String id);
	/**
	 * 查询告警条件
	 * @param warningDict
	 * 			告警类型
	 * @param fieldName
	 * 			字段名称
	 * @return
	 */
	public abstract List<WarningCondition> getWarningConditions(WarningDict warningDict, String fieldName);
	
	/**
	 * 获取告警条件
	 * @param warningDict
	 * 			告警类型
	 * @param fieldName
	 * 			字段名称
	 * @param compareType
	 * 			比较类型
	 * @return
	 */
	public abstract WarningCondition getWarningCondition(WarningDict warningDict, String fieldName, CompareType compareType);
	/**
	 * 判断是否满足告警条件
	 * @param warningDict
	 * 			告警类型
	 * @param fieldName
	 * 			字段名称
	 * @param value
	 * 			字段值
	 * @return
	 */
	public abstract boolean fitWarningCondition(WarningDict warningDict, String fieldName, Double value);
}
