/*
 * Project platform
 *
 * Class ProtocolCmaType.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-4 上午10:04:12
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.setting.model;

/**
 * 协议中的”命令类型“
 * 
 * @version v1.0.0
 * @author 
 */
public class ProtocolCmdType {

	/** 读配置交互 */
	public static final String GETCONFIG = "GETCONFIG";
	
	/** 写配置交互 */
	public static final String SETCONFIG = "SETCONFIG";
	
	/** 控制交互 */
	public static final String ACTIVATE = "ACTIVATE";
	
	/** 数据重传 */
	public static final String RESEND = "RESEND";
	
	/** 休眠 */
	public static final String DEACTIVATE = "DEACTIVATE";
	
	/** 数据召测 */
	public static final String REALTIME = "GETNEWDATA";
	
	/** 远程升级 */
	public static final String UPGRADE = "UPGRADE";
}
