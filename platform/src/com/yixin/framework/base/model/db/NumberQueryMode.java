/*
 * Project platform
 *
 * Class NumberQueryMode.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-22 上午09:09:23
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.base.model.db;

/**
 * 数字类型所使用的比较机制。即使用运算符“>”、“<”、“=”、“<>”、“>=”、“<=”等进行比较。
 * 
 * @version v1.0.0
 * @author 
 */
public enum NumberQueryMode {

	/** 使用等号“=”进行比较查找 */
	EQ,

	/** 使用不等于“<>”进行比较查找 */
	NEQ,

	/** 使用大于号“>”进行比较查找 */
	GT,

	/** 使用小于号“<”进行比较查找 */
	LT,

	/** 使用大于等于号号“>=”进行比较查找 */
	GE,

	/** 使用小于等于号“<=”进行比较查找 */
	LE,
	
	/** 使用“IS”进行查找，空判断。即使用“IS NULL” */
	IS,
	
	/** 使用“IS NOT”进行查找，非空判断。即使用“IS NOT NULL” */
	ISNOT
}