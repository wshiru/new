/*
 * Project platform
 *
 * Classname InsulatorMonsoonWarningContions.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-22 11：25
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
 * 绝缘子串风偏数据告警条件
 * 
 * @version v1.0.0
 * @author 
 */
public class InsulatorMonsoonWarningConditions extends MonsoonWarningConditions{
	
	/** 告警类型 */
	private static WarningDict warningDict;
	
	/** 风偏角告警条件 */
	public static WarningCondition CONDT_WINDANGLE;
	/** 倾斜角告警条件 */
	public static WarningCondition CONDT_ANGLE;
	/** 最小电气间隙参数告警条件 */
	public static WarningCondition CONDT_MINCLEARANCEPARAMS;
	
	static {
		if(null != warningConditionService && null !=warningDictService){
			warningDict = warningDictService.getWarningDictByName(WarningDictNames.INSULATORMONSOON_WARNING_DICT_NAME);
			if(null == warningDict){
				/* 添加告警类型默认值 */
				warningDict = new WarningDict();
				warningDict.setCode(WarningDictCodes.INSULATORMONSOON_WARNING_CODE);
				warningDict.setName(WarningDictNames.INSULATORMONSOON_WARNING_DICT_NAME);
				warningDictService.addWarningDict(warningDict);
				warningDict = warningDictService.getWarningDictByName(WarningDictNames.INSULATORMONSOON_WARNING_DICT_NAME);
			}
			
			List<WarningCondition> waConditions = warningConditionService.getWarningConditions(warningDict, WINDANGLE_FIELD);
			CONDT_WINDANGLE = waConditions.size() > 0 ? waConditions.get(0) : null;
			if(null == CONDT_WINDANGLE){
				CONDT_WINDANGLE = new WarningCondition();
				CONDT_WINDANGLE.setWarningDict(warningDict);
				CONDT_WINDANGLE.setFieldName(WINDANGLE_FIELD);
				CONDT_WINDANGLE.setCompare(CompareType.OVER.getValue());
				CONDT_WINDANGLE.setThreshold(DEFAULT_WINDANGLE);
				CONDT_WINDANGLE = warningConditionService.updateWarningCondition(CONDT_WINDANGLE);
			}
			
			List<WarningCondition> aConditions = warningConditionService.getWarningConditions(warningDict, ANGLE_FIELD);
			CONDT_ANGLE = aConditions.size()>0 ? aConditions.get(0) : null;
			if(null == CONDT_ANGLE){
				CONDT_ANGLE = new WarningCondition();
				CONDT_ANGLE.setWarningDict(warningDict);
				CONDT_ANGLE.setFieldName(ANGLE_FIELD);
				CONDT_ANGLE.setCompare(CompareType.OVER.getValue());
				CONDT_ANGLE.setThreshold(DEFAULT_ANGLE);
				CONDT_ANGLE = warningConditionService.updateWarningCondition(CONDT_ANGLE);
			}
			
			List<WarningCondition> mcpConditions = warningConditionService.getWarningConditions(warningDict, MINCLEARANCEPARAMS_FIELD);
			CONDT_MINCLEARANCEPARAMS = mcpConditions.size()>0 ? mcpConditions.get(0) : null;
			if(null == CONDT_MINCLEARANCEPARAMS){
				CONDT_MINCLEARANCEPARAMS = new WarningCondition();
				CONDT_MINCLEARANCEPARAMS.setWarningDict(warningDict);
				CONDT_MINCLEARANCEPARAMS.setFieldName(MINCLEARANCEPARAMS_FIELD);
				CONDT_MINCLEARANCEPARAMS.setCompare(CompareType.OVER.getValue());
				CONDT_MINCLEARANCEPARAMS.setThreshold(DEFAULT_MINCLEARANCEPARAMS);
				CONDT_MINCLEARANCEPARAMS = warningConditionService.updateWarningCondition(CONDT_MINCLEARANCEPARAMS);
			}
		}
	}

	
	/**
	 * 设置告警条件阀值
	 * @param windAngle
	 * 			风偏角
	 * @param angle
	 * 			倾斜角
	 * @param minClearanceParams
	 * 			最小电气间隙参数
	 */
	public static void setThresholds(Double windAngle, Double angle, Double minClearanceParams){
		if(null != windAngle){
			CONDT_WINDANGLE.setThreshold(windAngle);
			warningConditionService.updateWarningCondition(CONDT_WINDANGLE);
		}
		
		if(null != angle){
			CONDT_ANGLE.setThreshold(angle);
			warningConditionService.updateWarningCondition(CONDT_ANGLE);
		}
		
		if(null != minClearanceParams){
			CONDT_MINCLEARANCEPARAMS.setThreshold(minClearanceParams);
			warningConditionService.updateWarningCondition(CONDT_MINCLEARANCEPARAMS);
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
