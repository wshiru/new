/*
 * Project platform
 *
 * Classname WireTemperatureWarningContions.java
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
 * 导线温度数据告警条件
 * 
 * @version v1.0.0
 * @author 
 */
public class WireTemperatureWarningConditions extends WarningConditions{
	
	/** 线温1告警条件字段名 */
	private final static String LINETEMPERATURE1_FIELD = "lineTemperature1";
	/** 线温2告警条件字段名 */
	private final static String LINETEMPERATURE2_FIELD = "lineTemperature2";

	/** 线温最大默认值 */
	private final static double DEFAULT_LINE_TEMPERATURE = 50.0; //单位: 摄氏度

	/** 告警类型 */
	private static WarningDict warningDict;
	
	/** 线温1告警条件 */
	public static WarningCondition CONDT_LINETEMPERATURE1;
	/** 线温2告警条件 */
	public static WarningCondition CONDT_LINETEMPERATURE2;

	
	static {
		if(null != warningConditionService && null !=warningDictService){
			//初始化告警条件
			warningDict = warningDictService.getWarningDictByName(WarningDictNames.WIRETEMPERATURE_WARNING_DICT_NAME);
			if(null == warningDict){
				/* 添加告警类型默认值 */
				warningDict = new WarningDict();
				warningDict.setCode(WarningDictCodes.WIRETEMPERATURE_WARNING_CODE);
				warningDict.setName(WarningDictNames.WIRETEMPERATURE_WARNING_DICT_NAME);
				warningDictService.addWarningDict(warningDict);
				warningDict = warningDictService.getWarningDictByName(WarningDictNames.WIRETEMPERATURE_WARNING_DICT_NAME);
			}
			
			List<WarningCondition> lt1Conditions = warningConditionService.getWarningConditions(warningDict, LINETEMPERATURE1_FIELD);
			CONDT_LINETEMPERATURE1 = lt1Conditions.size()>0 ? lt1Conditions.get(0) : null;
			if(null == CONDT_LINETEMPERATURE1){
				/* 添加默认线温1告警条件 */
				CONDT_LINETEMPERATURE1 = new WarningCondition();
				CONDT_LINETEMPERATURE1.setWarningDict(warningDict);
				CONDT_LINETEMPERATURE1.setFieldName(LINETEMPERATURE1_FIELD);
				CONDT_LINETEMPERATURE1.setCompare(CompareType.OVER.getValue());
				CONDT_LINETEMPERATURE1.setThreshold(DEFAULT_LINE_TEMPERATURE);
				CONDT_LINETEMPERATURE1 = warningConditionService.updateWarningCondition(CONDT_LINETEMPERATURE1);
			}
			
			List<WarningCondition> lt2Conditions = warningConditionService.getWarningConditions(warningDict, LINETEMPERATURE2_FIELD);
			CONDT_LINETEMPERATURE2 = lt2Conditions.size()>0 ? lt2Conditions.get(0) : null;
			if(null == CONDT_LINETEMPERATURE2){
				/* 添加默认线温2告警条件 */
				CONDT_LINETEMPERATURE2 = new WarningCondition();
				CONDT_LINETEMPERATURE2.setWarningDict(warningDict);
				CONDT_LINETEMPERATURE2.setFieldName(LINETEMPERATURE2_FIELD);
				CONDT_LINETEMPERATURE2.setCompare(CompareType.OVER.getValue());
				CONDT_LINETEMPERATURE2.setThreshold(DEFAULT_LINE_TEMPERATURE);
				CONDT_LINETEMPERATURE2 = warningConditionService.updateWarningCondition(CONDT_LINETEMPERATURE2);
			}
		}
	}
	
	/**
	 * 设置告警条件阀值
	 * @param lineTemperature1
	 * 			线温1
	 * @param lineTemperature2
	 * 			线温2
	 */
	public static void setThresholds(Double lineTemperature1, Double lineTemperature2){
		if(null != lineTemperature1){
			CONDT_LINETEMPERATURE1.setThreshold(lineTemperature1);
			warningConditionService.updateWarningCondition(CONDT_LINETEMPERATURE1);
		}
		
		if(null != lineTemperature2){
			CONDT_LINETEMPERATURE2.setThreshold(lineTemperature2);
			warningConditionService.updateWarningCondition(CONDT_LINETEMPERATURE2);
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
