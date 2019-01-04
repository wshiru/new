/*
 * Project platform
 *
 * Classname ErrorCode.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-5-13 上午 10:50
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.exception;

/**
 * 错误代码类
 * @version 1.0.0
 * @author 
 *
 */
public class ErrorCode {
	
	/** 资源未找到  */
	public final static int NOT_FOUND = 404;
	/** 数据冲突（已存在相同数据）  */
	public final static int CONFLICT = 409;
	/** 数据处于被使用状态（有关联）  */
	public final static int IN_USE = 406;
	/** 资源未找到  */
	public final static int NULL_VALUE = 490;
}
