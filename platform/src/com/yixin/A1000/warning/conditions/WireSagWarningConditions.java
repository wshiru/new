/*
 * Project platform
 *
 * Classname WireSagWarningContions.java
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
 * 导线弧垂数据告警条件
 * 
 * @version v1.0.0
 * @author 
 */
public class WireSagWarningConditions extends WarningConditions{
	
	/** 弧垂告警条件字段名 */
	private final static String SAG_FIELD = "sag";
	/** 对地距离告警条件字段名 */
	private final static String GROUNDDISTANCE_FIELD = "groundDistance";
	
	/** 弧垂最大默认值 */
	private final static double DEFAULT_SAG = 1.0; //单位: m
	/** 对地距离最大默认值 */
	private final static double DEFAULT_GROUNDDISTANCE = 50.0; //单位: m
	
	/** 告警类型 */
	private static WarningDict warningDict;
	
	/** 弧垂告警条件 */
	public static WarningCondition CONDT_SAG;
	/** 对地距离告警条件 */
	public static WarningCondition CONDT_GROUNDDISTANCE;
	
	static {
		if(null != warningConditionService && null !=warningDictService){
			//初始化告警条件
			warningDict = warningDictService.getWarningDictByName(WarningDictNames.WIRESAG_WARNING_DICT_NAME);
			if(null == warningDict){
				/* 添加告警类型默认值 */
				warningDict = new WarningDict();
				warningDict.setCode(WarningDictCodes.WIRESAG_WARNING_CODE);
				warningDict.setName(WarningDictNames.WIRESAG_WARNING_DICT_NAME);
				warningDictService.addWarningDict(warningDict);
				warningDict = warningDictService.getWarningDictByName(WarningDictNames.WIRESAG_WARNING_DICT_NAME);
			}
			
			List<WarningCondition> sagConditions = warningConditionService.getWarningConditions(warningDict, SAG_FIELD);
			CONDT_SAG = sagConditions.size()>0 ? sagConditions.get(0) : null;
			if(null == CONDT_SAG){
				/* 添加默认弧垂告警条件 */
				CONDT_SAG = new WarningCondition();
				CONDT_SAG.setWarningDict(warningDict);
				CONDT_SAG.setFieldName(SAG_FIELD);
				CONDT_SAG.setCompare(CompareType.OVER.getValue());
				CONDT_SAG.setThreshold(DEFAULT_SAG);
				CONDT_SAG = warningConditionService.updateWarningCondition(CONDT_SAG);
			}
			
			List<WarningCondition> gdConditions = warningConditionService.getWarningConditions(warningDict, GROUNDDISTANCE_FIELD);
			CONDT_GROUNDDISTANCE = gdConditions.size()>0 ? gdConditions.get(0) : null;
			if(null == CONDT_GROUNDDISTANCE){
				/* 添加默认对地距离告警条件 */
				CONDT_GROUNDDISTANCE = new WarningCondition();
				CONDT_GROUNDDISTANCE.setWarningDict(warningDict);
				CONDT_GROUNDDISTANCE.setFieldName(GROUNDDISTANCE_FIELD);
				CONDT_GROUNDDISTANCE.setCompare(CompareType.OVER.getValue());
				CONDT_GROUNDDISTANCE.setThreshold(DEFAULT_GROUNDDISTANCE);
				CONDT_GROUNDDISTANCE = warningConditionService.updateWarningCondition(CONDT_GROUNDDISTANCE);
			}
		}
	}
	
	/**
	 * 设置告警条件阀值
	 * @param sag
	 * 			弧垂
	 * @param groundDistance
	 * 			对地距离
	 */
	public static void setThresholds(Double sag, Double groundDistance){
		if(null != sag){
			CONDT_SAG.setThreshold(sag);
			warningConditionService.updateWarningCondition(CONDT_SAG);
		}
		if(null != groundDistance){
			CONDT_GROUNDDISTANCE.setThreshold(groundDistance);
			warningConditionService.updateWarningCondition(CONDT_GROUNDDISTANCE);
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
