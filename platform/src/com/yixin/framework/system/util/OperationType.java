/*
 * Project platform
 *
 * Classname OperationTypes.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-15 上午11:31
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.util;

/**
 * 操作类型类
 * @version v1.0.0
 * @author 
 *
 */
public enum OperationType {

	/** 系统日志 */
	SYSTEM_LOG("10"),
	
	/** 新增档案 */
	ADD_DATA("21"), 
	
	/** 修改档案 */
	EDIT_DATA("22"), 
	
	/** 删除档案 */
	DELETE_DATA("23"),
	
	/** 召测参数 */
	CALL_PARAM("30"),
	
	/** 下发参数 */
	SEND_PARAM("31"),
	
	/** 召测数据 */
	CALL_DATA("40"),
	
	/** 告警设置 */
	SET_WARNING_THRESHOLD("50");
	
	
	private final String value;
	
	private OperationType(String value){
		this.value = value;
	}	
	
	public String getValue(){
		return value;
	}
}
