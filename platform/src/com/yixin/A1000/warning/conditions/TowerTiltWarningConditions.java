/*
 * Project platform
 *
 * Classname TowerTiltWarningContions.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-22 11:25
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.conditions;

import java.util.List;

import com.yixin.A1000.warning.constant.CompareType;
import com.yixin.A1000.warning.constant.WarningDictCodes;
import com.yixin.A1000.warning.constant.WarningDictNames;
import com.yixin.A1000.warning.model.WarningCondition;
import com.yixin.A1000.warning.model.WarningDict;

/**
 * 杆塔倾斜数据告警条件
 * 
 * @version v1.0.0
 * @author 
 */
public class TowerTiltWarningConditions extends WarningConditions{
	
	/** 综合倾斜度告警条件字段名 */
	private final static String GENERALINCLINATION_FIELD = "generalInclination";
	/** 顺线倾斜度告警条件字段名 */
	private final static String GRADIENTALONGLINES_FIELD = "gradientAlongLines";
	/** 横向倾斜度告警条件字段名 */
	private final static String LATERALTILT_FIELD = "lateralTilt";
	
	/** 综合倾斜度最大默认值 */
	private final static double DEFAULT_GENERALINCLINATION = 30.0; //单位: 度
	/** 顺线倾斜度最大默认值 */
	private final static double DEFAULT_GRADIENTALONGLINES = 30.0; //单位: 度
	/** 横向倾斜度最大默认值 */
	private final static double DEFAULT_LATERALTILT = 30.0; //单位: 度
	
	/** 告警类型 */
	private static WarningDict warningDict;
	
	/** 综合倾斜度告警条件 */
	public static WarningCondition CONDT_GENERALINCLINATION;
	/** 顺线倾斜度告警条件 */
	public static WarningCondition CONDT_GRADIENTALONGLINES;
	/** 横向倾斜度告警条件 */
	public static WarningCondition CONDT_LATERALTILT;
	
	static {
		if(null != warningConditionService && null !=warningDictService){
			//初始化告警条件
			warningDict = warningDictService.getWarningDictByName(WarningDictNames.TOWERTILT_WARNING_DICT_NAME);
			if(null == warningDict){
				/* 添加告警类型默认值 */
				warningDict = new WarningDict();
				warningDict.setCode(WarningDictCodes.TOWERTILT_WARNING_CODE);
				warningDict.setName(WarningDictNames.TOWERTILT_WARNING_DICT_NAME);
				warningDictService.addWarningDict(warningDict);
				warningDict = warningDictService.getWarningDictByName(WarningDictNames.TOWERTILT_WARNING_DICT_NAME);
			}
			
			List<WarningCondition> gcConditions = warningConditionService.getWarningConditions(warningDict, GENERALINCLINATION_FIELD);
			CONDT_GENERALINCLINATION = gcConditions.size()>0 ? gcConditions.get(0) : null;
			if(null == CONDT_GENERALINCLINATION){ 
				/* 添加默认综合倾斜度告警条件 */
				CONDT_GENERALINCLINATION = new WarningCondition();
				CONDT_GENERALINCLINATION.setWarningDict(warningDict);
				CONDT_GENERALINCLINATION.setFieldName(GENERALINCLINATION_FIELD);
				CONDT_GENERALINCLINATION.setCompare(CompareType.OVER.getValue());
				CONDT_GENERALINCLINATION.setThreshold(DEFAULT_GENERALINCLINATION);
				CONDT_GENERALINCLINATION = warningConditionService.updateWarningCondition(CONDT_GENERALINCLINATION);
			}
			
			List<WarningCondition> gaConditions = warningConditionService.getWarningConditions(warningDict, GRADIENTALONGLINES_FIELD);
			CONDT_GRADIENTALONGLINES = gaConditions.size()>0 ? gaConditions.get(0) : null;
			if(null == CONDT_GRADIENTALONGLINES){ 
				/* 添加默认顺线倾斜度告警条件 */
				CONDT_GRADIENTALONGLINES = new WarningCondition();
				CONDT_GRADIENTALONGLINES.setWarningDict(warningDict);
				CONDT_GRADIENTALONGLINES.setFieldName(GRADIENTALONGLINES_FIELD);
				CONDT_GRADIENTALONGLINES.setCompare(CompareType.OVER.getValue());
				CONDT_GRADIENTALONGLINES.setThreshold(DEFAULT_GRADIENTALONGLINES);
				CONDT_GRADIENTALONGLINES = warningConditionService.updateWarningCondition(CONDT_GRADIENTALONGLINES);
			}
			
			List<WarningCondition> ltConditions = warningConditionService.getWarningConditions(warningDict, LATERALTILT_FIELD);
			CONDT_LATERALTILT = ltConditions.size()>0 ? ltConditions.get(0) : null;
			if(null == CONDT_LATERALTILT){ 
				/* 添加默认横向倾斜度告警条件 */
				CONDT_LATERALTILT = new WarningCondition();
				CONDT_LATERALTILT.setWarningDict(warningDict);
				CONDT_LATERALTILT.setFieldName(LATERALTILT_FIELD);
				CONDT_LATERALTILT.setCompare(CompareType.OVER.getValue());
				CONDT_LATERALTILT.setThreshold(DEFAULT_LATERALTILT);
				CONDT_LATERALTILT = warningConditionService.updateWarningCondition(CONDT_LATERALTILT);
			}
		}
	}
	
	/**
	 * 设置告警条件阀值
	 * @param generalInclination
	 * 			综合倾斜度
	 * @param gradientAlongLines
	 * 			顺线倾斜度
	 * @param lateralTilt
	 * 			横向倾斜度
	 */
	public static void setThresholds(Double generalInclination, Double gradientAlongLines, Double lateralTilt) {
		if(null != generalInclination){
			CONDT_GENERALINCLINATION.setThreshold(generalInclination);
			warningConditionService.updateWarningCondition(CONDT_GENERALINCLINATION);
		}
		
		if(null != gradientAlongLines){
			CONDT_GRADIENTALONGLINES.setThreshold(gradientAlongLines);
			warningConditionService.updateWarningCondition(CONDT_GRADIENTALONGLINES);
		}
		
		if(null != lateralTilt){
			CONDT_LATERALTILT.setThreshold(lateralTilt);
			warningConditionService.updateWarningCondition(CONDT_LATERALTILT);
		}
	}
	
	/**
	 * 
	 * 获取告警类型
	 *
	 * @return
	 */
	public static WarningDict getWarningDict(){
		return warningDict;
	}
}
