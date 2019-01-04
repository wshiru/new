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
public enum FrameType {

	/** 监测数据报（监测装置上位机） */
	SAMPLING_UP("0x01"), // 
	/** 数据响应报（上位机监测装置） */
	SAMPLING_DOWN("0x02"), // 
	/** 控制响应报（监测装置上位机） */
	CONTROL_UP("0x04"), // 
	/** 控制数据报（上位机监测装置） */
	CONTROL_DOWN("0x03"), // 
	/** 远程图像数据报（监测装置上位机） */
	IMAGE_UP("0x05"), // 
	/** 远程图像控制报 */
	IMAGE_DOWN("0x06"), // 
	/** 工作状态报（监测装置上位机） */
	WORKSTATUS_UP("0x07"), // 
	/** 工作状态响应报（上位机监测装置） */
	WORKSTATUS_DOWN("0x08"), // 
	/** 同步数据（两个监测系统的数据同步） */
	SYNC_DATA("0x09"); // 

	/** 16进制码 */
	private String hexCode;

	/**
	 * 构造方法
	 * 
	 * @param hexCode
	 *            16进制码
	 */
	private FrameType(String hexCode) {
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
