/*
 * Project platform
 *
 * Class ProtocolException.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-5 上午11:45:22
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.comm.exception;

/**
 * TCP服务器返回的异常信息
 * 
 * @version v1.0.0
 * @author 
 */
public class TCPServerException extends RuntimeException {

	/** 类的序列化版本ID} */
	private static final long serialVersionUID = -3788988141764254815L;

	private String message;

	public TCPServerException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public String toString() {
		return this.message;
	}
}
