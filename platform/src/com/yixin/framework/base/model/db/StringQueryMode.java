/*
 * Project platform
 *
 * Class QueryMode.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-22 上午09:04:27
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.base.model.db;

/**
 * String类型所使用的比较机制。即使用“=”或“LIKE”进行字符串的比较。
 * 
 * @version v1.0.0
 * @author 
 */
public enum StringQueryMode {

	/** 使用“=”进行查找 */
	EQ,

	/** 使用“LIKE”进行查找 */
	LIKE,
	
	/** 使用“IS”进行查找，空判断。即使用“IS NULL” */
	IS,
	
	/** 使用“IS NOT”进行查找，非空判断。即使用“IS NOT NULL” */
	ISNOT
}
