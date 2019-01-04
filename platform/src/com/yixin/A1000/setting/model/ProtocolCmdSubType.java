/*
 * Project platform
 *
 * Class ProtocolCmaSubType.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-4 上午10:07:57
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.setting.model;

/**
 * T协议中”命令类型“的“子类型”
 * 
 * @version v1.0.0
 * @author 
 */
public class ProtocolCmdSubType {

	/** 写配置交互：CAG返回普通配置信息供CMA对自身及其管辖传感器的配置信息进行更新 */
	public static final String NORMAL = "NORMAL";

	/** 写配置交互：CAG返回计算模型供CMA对自身的计算模型进行更新 */
	public static final String MODEL = "MODEL";
	
	/** 数据召测：气象 */
	public static final String WEATHER = "WEATHER";
	
	/** 数据召测：导线弧垂 */
	public static final String WIRESEG = "WIRESEG";
	
	/** 数据召测：绝缘子串风偏 */
	public static final String INSULATORMONSOON = null;
	
	/** 数据召测：杆塔倾斜 */
	public static final String TOWERTILT = "TOWERTILT";
	
	/** 数据召测：相间风偏 */
	public static final String WHITEMONSOON = "WHITEMONSOON";
	
	/** 数据召测：导线温度 */ 
	public static final String WIRETEMPERATURE = "WIRETEMPERATURE";
}
