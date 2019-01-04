/*
 * Project platform
 *
 * Class ContextKeys.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-20 上午11:58:33
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.util;

/**
 * TODO(类的描述信息)
 * 
 * @version v1.0.0
 * @author 
 */
public class ContextKeys {
	
	/** WEB目录的物理根路径。如：“C:/webroot” */
	public static String WEB_PHYSICAL_ROOT_PATH = "C:";
	
	/** 项目类型 **/
	public static Integer PROJECT_TYPE = 0;
	
	/** 项目名称 **/
	public static String PROJECT_NAME = "输电线路运行状态在线监测系统";

	
	public static final int  NOTHING = -1;
	public static final int  TIME_EXPORT = 0;
	public static final int  DAY_EXPORT = 1;
	public static final int  MONTH_EXPORT = 2;
	public static final int  YEAR_EXPORT = 3;
	public static final int  EXTRE_EXPORT = 4; //极值统计
	
	
	
}
