/*
 * Project platform
 *
 * Class FrameType.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-8-30 上午11:24:01
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.comm.protocol;

/**
 * 帧类型枚举类
 * 
 * @version v1.0.0
 * @author 
 */
public enum DeviceResetModelType {

	/** 常规复位（重启） */
	NORMAL_MODE("0x00"),
	/** 复位至升级模式 */
	UPDATE_MODE("0x01"),
	/** 复位至诊断模式 */
	DIAGNOSE_MODE("0x02"),
	/** 复位至诊断模式 */
	OTHER("0x03");

	/** 16进制码 */
	private String hexCode;

	/**
	 * 构造方法
	 * 
	 * @param hexCode
	 *            16进制码
	 */
	private DeviceResetModelType(String hexCode) {
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
