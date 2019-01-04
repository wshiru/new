/*
 * Project platform
 *
 * Class RequestType.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-6 下午03:44:37
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.comm.protocol;

/**
 * 请求类型枚举类
 * 
 * @version v1.0.0
 * @author 
 */
public enum RequestType {

	/** 查询 */
	UP("0x00"), // 
	/** 配置 */
	DOWN("0x01"); // 

	/** 16进制码 */
	private String hexCode;

	/**
	 * 构造方法
	 * 
	 * @param hexCode
	 *            16进制码
	 */
	private RequestType(String hexCode) {
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
