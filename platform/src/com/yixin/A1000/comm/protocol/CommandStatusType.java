/*
 * Project platform
 *
 * Class CommandStatusType.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-6 下午04:24:59
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.comm.protocol;

/**
 * 数据发送状态
 * 
 * @version v1.0.0
 * @author 
 */
public enum CommandStatusType {

	/** 成功 */
	SUCCESS("0xFF"),
	/** 失败 */
	FAIL("0x00");
	
	/** 16进制码 */
	private String hexCode;

	/**
	 * 构造方法
	 * 
	 * @param hexCode
	 *            16进制码
	 */
	private CommandStatusType(String hexCode) {
		this.hexCode = hexCode;
	}

	/**
	 * 获取 16进制码
	 * 
	 * @return 16进制码
	 */
	public String getHexCode() {
		return this.hexCode;
	}
}
