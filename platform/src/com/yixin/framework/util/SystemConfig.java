/*
 * Project platform
 *
 * Classname SystemConfig.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-07-08 10：13
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.framework.util;

import com.yixin.framework.base.constant.ConfigurableConstants;

/**
 * 系统配置属性

 * 
 * @version v1.0.0
 * @author 

 */
public class SystemConfig extends ConfigurableConstants {
	
	static {
		init("/system-config.properties");
	}
	
	/** 分页大小 */
	public static final long PAGE_SIZE = Long.parseLong(getProperty("PageSize", "20"));
	
//	/** 系统标题 */
//	public static final String SYSTEM_TITLE = getProperty("system.name");
//	/** 系统样式 */
//	public static final String SYSTEM_THEME = getProperty("system.theme", "green");
	
	/** 升级文件存放的目录 */
	public static final String SYSTEM_UPGRADE_ROOT = getProperty("system.upgrade.root");
	
	/** # 校时：正偏移量（以CAG系统当前时间为基准）。即当（CMA上传时间  - 系统当前时间> 该偏移量）时，将向CMA发送校时命令。单位：秒 */
	public static final int FIXTIME_POSITIVE_OFFSET;
	/** # 校时：负偏移量（以CAG系统当前时间为基准）。即当（系统当前时间 - CMA上传时间 > 该偏移量）时，将向CMA发送校时命令。单位：秒 */
	public static final int FIXTIME_NEGATIVE_OFFSET;	
	static {
		int positive = 30;
		int negative = 30;
		try {
			positive = Integer.parseInt(getProperty("fixtime.positive.offset"));
			negative = Integer.parseInt(getProperty("fixtime.positive.offset"));
		} catch(Exception ex) {
			// 无需处理，自动使用默认值

		}
		FIXTIME_POSITIVE_OFFSET = positive;
		FIXTIME_NEGATIVE_OFFSET = negative;
	}
}
