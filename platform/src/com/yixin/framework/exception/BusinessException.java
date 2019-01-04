/*
 * Project platform
 *
 * Classname BusinessException.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-5-25 上午11:50:32
 *
 * ModifiedBy 
 * ModifyAt 2011-6-14 下午 17:21
 * ModifyDescription 添加getMessage()重写方法
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.exception;

/**
 * 项目中业务逻辑错误封装类。当处理业务逻辑出现错误时，将错误代码封装到该Runtime异常类中，错误代码被封装到ErrorCodeKeys类中
 * 
 * @version 1.0.0
 * @author 
 */
public class BusinessException extends RuntimeException {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -2150255712563362118L;

	/** 业务逻辑错误代码 */
	private int errorCode;

	/**
	 * 构造一个指定业务逻辑代码的RuntimeException
	 * 
	 * @param errorCode
	 *            业务逻辑错误代码
	 */
	public BusinessException(int errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String getMessage() {
		return "ErrorCode " + errorCode;
	}

	/**
	 * 返回业务逻辑错误代码
	 * 
	 * @return 业务逻辑错误代码
	 */
	public int getErrorCode() {
		return errorCode;
	}
}