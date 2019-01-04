/*
 * Project platform
 *
 * Classname BusinessConstants.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-07 10：37
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
 * 业务常量
 * 
 * @version v1.0.0
 * @author 
 */
public class BusinessConstants extends ConfigurableConstants {

	static {
		init("/business-config.properties"); //初始化配置文件属性
	}
	
	/** 监测代理心跳时间间隔，单位为分钟 */
	public static final long CMA_HEARTBEAT_INTERVAL = Long.parseLong(getProperty("CMAHeartBeatInterval", "5"));
	
	/** 最新报警时间跨度，单位为天  */
	public static final int LATEST_WARNING_SPAN = Integer.parseInt(getProperty("latestWarningSpan", "3"));

}
