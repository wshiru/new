/*
 * Project platform
 *
 * Class TCPServerBusyException.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-8-29 下午02:07:32
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.comm.exception;

/**
 * 当服务器处理的命令超过指定数目（ContextKeys.MAX_PROCESSING_COMMAND_SIZE）时，抛出此异常
 * 
 * @version v1.0
 * @author 
 */
public class TCPServerBusyException extends RuntimeException {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -7381841560083330525L;
}
