/*
 * Project platform
 *
 * Classname MonsoonWarningConditions.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-26 10：38
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */package com.yixin.A1000.warning.conditions;


/**
 * 风偏告警条件基类
 * 
 * @version v1.0.0
 * @author 
 */
public class MonsoonWarningConditions extends WarningConditions {
	/** 风偏角告警条件字段名 */
	protected final static String WINDANGLE_FIELD = "windAngle";
	/** 倾斜角告警条件字段名 */
	protected final static String ANGLE_FIELD = "angle";
	/** 最小电气间隙参数告警条件字段名 */
	protected final static String MINCLEARANCEPARAMS_FIELD = "minClearanceParams";
	
	/** 风偏角最大默认值 */
	protected final static double DEFAULT_WINDANGLE = 30.0; //单位: 度
	/** 倾斜角最大默认值 */
	protected final static double DEFAULT_ANGLE = 30.0; //单位: 度
	/** 最小电气间隙参数最大默认值 */
	protected final static double DEFAULT_MINCLEARANCEPARAMS = 150.0; //单位: mm

}
