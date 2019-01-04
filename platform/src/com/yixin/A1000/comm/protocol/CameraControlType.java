/*
 * Project platform
 *
 * Class PacketType.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-8-30 上午11:39:34
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.comm.protocol;

/**
 * 报文类型枚举类
 * 
 * @version v1.0.0
 * @author 
 */
public enum CameraControlType {

	/*
	1	0	打开摄像机电源
	2	所需预置点	摄像机调节到指定预置点
	3	0	向上调节1个单位
	4	0	向下调节1个单位
	5	0	向左调节1个单位
	6	0	向右调节1个单位
	7	0	焦距向远方调节1个单位
	8	0	焦距向近处调节1个单位
	9	所需设置预置点	保存当前位置为某预置点
	10	关闭摄像机电源	
	13	打开摄像机电源	打开摄像机电源
	*/
	OPEN_POWER_EX("13"),

	// WORKSTATUS_XXX1("0xE6"), // 心跳数据报
	// WORKSTATUS_XXX2("0xE7"), // 基本信息报
	// WORKSTATUS_XXX3("0xE8"), // 工作状态报
	// WORKSTATUS_XXX4("0xE9"); // 故障信息报
	// WORKSTATUS_("0xEA～0xFF"); // 其他报文预留字段
	   ERROR("0xFF");
	/** 16进制码 */
	private String hexCode;

	/**
	 * 构造方法
	 * 
	 * @param hexCode
	 *            16进制码
	 */
	private CameraControlType(String hexCode) {
		this.hexCode = hexCode;
	}

	/**
	 * 获取 16进制码
	 * 
	 * @return 16进制码
	 */
	public String getHexCode() {
		return hexCode;
	}
}
