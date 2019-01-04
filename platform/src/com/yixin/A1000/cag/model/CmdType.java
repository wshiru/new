/*
 * Project ca2000
 *
 * Class CmdType.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午10:57:14
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.model;

/**
 * I2接口协议：命令类型
 * 
 * @version v1.0.0
 * @author 
 */
public enum CmdType {

	/** 读配置交互：CAG获取CMA及其管辖状态监测装置的配置信息 */
	GETCONFIG("GETCONFIG"),

	/** 写配置交互：CAG 返回配置信息供 CMA 对自身及其管辖状态监测装置的配置信息进行更新 */
	SETCONFIG("SETCONFIG"),

	/** 控制交互：CAG要求CMA暂不休眠，等待后续命令 */
	ACTIVATE("ACTIVATE"),

	/** 数据重传：CAG命令CMA重新传送前一次发送的数据 */
	RESEND("RESEND"),

	/** 数据召唤：CAG获取CMA当前最新的监测数据 */
	GETNEWDATA("GETNEWDATA"),

	/** 休眠：CAG命令CMA休眠 */
	DEACTIVATE("DEACTIVATE"),

	/** 控制交互：设置摄像头自动拍照时间表 */
	CAMERA_SETSCHEDULE("CAMERA_SETSCHEDULE"),

	/** 控制交互：请求摄像头即时拍照 */
	CAMERA_TAKEPHOTO("CAMERA_TAKEPHOTO"),

	/** 控制交互：调节摄像头位置角度 */
	CAMERA_REGULATE("CAMERA_REGULATE");

	/** 命令类型 */
	private String code;

	/**
	 * 构造方法
	 * 
	 * @param code
	 *            命令类型
	 */
	private CmdType(String code) {
		this.code = code;
	}

	/**
	 * 获取 命令类型
	 * 
	 * @return 命令类型
	 */
	public String getCode() {
		return code;
	}
}