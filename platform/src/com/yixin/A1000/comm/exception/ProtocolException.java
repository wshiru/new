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

import com.yixin.A1000.comm.protocol.ProtocolErrorCode;

/**
 * 协议错误异常
 * 
 * @version v1.0.0
 * @author 
 */
public class ProtocolException extends RuntimeException {
	/** 类的序列化版本ID} */
	private static final long serialVersionUID = -3788988141764254815L;
	private ProtocolErrorCode errorCode;
	private String packetFrame;

	public ProtocolException(ProtocolErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public ProtocolException(ProtocolErrorCode errorCode, String packetFrame) {
		this.errorCode = errorCode;
		this.packetFrame = packetFrame;
	}

	@Override
	public String getMessage() {
		return "ERROR(" + this.errorCode.getCode() + ") - " + this.errorCode.getMessage();
	}

	@Override
	public String toString() {
		return "ERROR(" + this.errorCode.getCode() + ") - " + this.errorCode.getMessage() + "\r\nDATA - " + this.packetFrame;
	}
}
