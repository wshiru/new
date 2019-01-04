/*
 * Project platform
 *
 * Classname WarningDictNames.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-22 11：14
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.constant;

import com.yixin.framework.base.constant.ConfigurableConstants;

/**
 * 告警类型名称
 * 
 * @version v1.0.0
 * @author 
 */
public class WarningDictNames extends ConfigurableConstants{

	static {
		init("/warningDictName-config.properties"); //初始化配置文件属性
	}
	
	/** 微气象告警类型名  */
	public static final String WEATHER_WARNING_DICT_NAME = getProperty("weatherWarningDictName", "微气象告警");
	/** 导线温度告警类型名  */
	public static final String WIRETEMPERATURE_WARNING_DICT_NAME = getProperty("wireTemperatureWarningDictName", "导线温度告警");
	/** 导线弧垂告警类型名  */
	public static final String WIRESAG_WARNING_DICT_NAME = getProperty("wireSagWarningDictName", "导线弧垂告警");
	/** 杆塔倾斜告警类型名  */
	public static final String TOWERTILT_WARNING_DICT_NAME = getProperty("towerTiltWarningDictName", "杆塔倾斜告警");
	/** 相间风偏告警类型名  */
	public static final String WHITEMONSOON_WARNING_DICT_NAME = getProperty("whiteMonsoonWarningDictName", "相间风偏告警");
	/** 绝缘子串风偏告警类型名  */
	public static final String INSULATORMONSOON_WARNING_DICT_NAME = getProperty("insulatorMonsoonWarningDictName", "绝缘子串风偏告警");
}
