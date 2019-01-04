/*
 * Project platform
 *
 * Class ArchiveErrorCode.java
 * 
 * Version 1.0.0
 * 
 * Creator 梁立全

 * CreateAt 2011-6-21 下午04:47:58
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.setting.model;

/**
 * 参数设置模块业务错误代码
 * 
 * @version v1.0.0
 * @autho  詹朝轮

 */
public class SettingErrorCode {

	/* ------------------------------------------------------- */
	/* CMA心跳信息管理：180010000-120019999 */
	/* ------------------------------------------------------- */
	/** 新增/修改：“监测代理”无效*/
	public static final int SETTING_CMAINFO_INVALID = 120010000;

	/* ------------------------------------------------------- */
	/* 监测装置（传感器）管理：180020000-120029999 */
	/* ------------------------------------------------------- */
	/** 新增/修改：“监测装置”无效*/
	public static final int SETTING_SENSOR_INVALID = 120010000;

	/* ------------------------------------------------------- */
	/* 监测装置（传感器）管理：180020000-120029999 */
	/* ------------------------------------------------------- */
	/** 任务已下发，但未执行成功 */
	public static final int TASKCONFIG_ALREADY_SEND = 130010000;
	/** 任务已下发，并已执行成功 */
	public static final int TASKCONFIG_ALREADY_FINISHED = 130010001;
	
}
