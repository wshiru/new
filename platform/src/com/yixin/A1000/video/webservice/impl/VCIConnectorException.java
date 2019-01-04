/*
 * DSCAlarmTypeKeys.java
 * Version 1.0
 * Create on 2009-10-12
 * 
 * Copyright (c) 2009-2010 the casic and original author or authors.
 */
package com.yixin.A1000.video.webservice.impl;

/**
 * 项目中业务逻辑错误封装类。当处理业务逻辑出现错误时，将错误代码封装到该Runtime异常类中，错误代码被封装到ErrorCodeKeys类中
 * 
 * @version 1.0 2009-08-31
 * @author 

 */
public class VCIConnectorException extends RuntimeException {
	private static final long serialVersionUID = -3202020199818916364L;
	
	public static final int ERROR = 0;	
	public static final int ERROR_SEND = 1;
	public static final int ERROR_COMMON = 2;

	/* fields */
	private int errorCode;
	private String errorMessage;

	/* constructors */
	/**
	 * 构造一个指定业务逻辑代码的RuntimeException
	 * 
	 * @param errorCode
	 */
	public VCIConnectorException(int errorCode) {
		this.errorCode = errorCode;
	}
	public VCIConnectorException(String errorMessage) {
		this.errorCode = 0;
		this.errorMessage = errorMessage;
	}
	
	public VCIConnectorException(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}	

	/**
	 * 返回业务逻辑错误代码
	 * 
	 * @return
	 */
	public int getErrorCode() {
		return errorCode;
	}
	
	public String getErrorMessage(){
		return errorMessage;
	}
}