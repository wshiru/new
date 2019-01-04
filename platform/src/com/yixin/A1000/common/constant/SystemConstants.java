/*
 * Project platform
 *
 * Classname SystemConstants.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-12 11：37
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.common.constant;

import com.yixin.framework.base.constant.ConfigurableConstants;

/**
 * 系统常量
 * 
 * @version v1.0.0
 * @author 
 */
public class SystemConstants extends ConfigurableConstants {

	static {
		init("/system-config.properties");
	}
	
	/** 制图最大记录条数 */
	public static final long MAX_CHART_RECORDS = Long.parseLong(getProperty("MaxChartRecords", "10000"));

}
