/*
 * Project platform
 *
 * Classname WeatherContions.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-22 9:58
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
 * 微气象数据告警条件
 * 
 * @version v1.0.0
 * @author 
 */
public class WeatherWarningConditions extends WarningConditions{
	
	/** 气温告警条件字段名 */
	private final static String TEMPERATURE_FIELD = "temperature";
	/** 湿度告警条件字段名 */
	private final static String HUMIDITY_FIELD = "humidity";
	/** 风速告警条件字段名 */
	private final static String WINDSPEED_FIELD = "windSpeed";
//	/** 风向告警条件字段名 */
//	private final static String WINDDIRECTION_FIELD = "windDirection";
	/** 气压告警条件字段名 */
	private final static String AIRPRESSURE_FIELD = "airPressure";
	/** 降雨量告警条件字段名 */
	private final static String DAILYRAINFALL_FIELD = "dailyRainFall";
//	/** 光照辐射告警条件字段名 */
//	private final static String RADIATIONINTENSITY_FIELD = "radiationIntensity";
	
	/** 气温最大默认值 */
	private final static double DEFAULT_MAX_TEMPERATURE = 50.0; //单位: 摄氏度
	/** 气温最小默认值 */
	private final static double DEFAULT_MIN_TEMPERATURE = -10.0;
	/** 湿度最大默认值 */
	private final static double DEFAULT_MAX_HUMIDITY = 70;  //单位: 比值
	/** 湿度最小默认值 */
	private final static double DEFAULT_MIN_HUMIDITY = 10;
	/** 风速最大默认值 */
	private final static double DEFAULT_MAX_WINDSPEED = 50.0; //单位: km/h
	/** 气压最大默认值 */
	private final static double DEFAULT_MAX_AIRPRESSURE = 10000; //单位: hPa百帕
	/** 降雨量最大默认值 */
	private final static double DEFAULT_MAX_DAILYRAINFALL = 500; //单位: mm/24h
	
	/** 告警类型 */
	private static WarningDict warningDict;
	
	/** 气温上限告警条件 */
	public static WarningCondition CONDT_TEMPERATURE_MAX;
	/** 气温下限告警条件 */
	public static WarningCondition CONDT_TEMPERATURE_MIN;
	/** 湿度上限告警条件 */
	public static WarningCondition CONDT_HUMIDITY_MAX;
	/** 湿度下限告警条件 */
	public static WarningCondition CONDT_HUMIDITY_MIN;
	/** 风速告警条件 */
	public static WarningCondition CONDT_WINDSPEED;
//	/** 风向告警条件 */
//	public static WarningCondition CONDT_WINDDIRECTION;
	/** 气压告警条件 */
	public static WarningCondition CONDT_AIRPRESSURE;
	/** 降雨量告警条件 */
	public static WarningCondition CONDT_DAILYRAINFALL;
//	/** 光照辐射告警条件 */
//	public static WarningCondition CONDT_RADIATIONINTENSITY;

	
	static { //初始化告警条件
		if(null != warningConditionService && null !=warningDictService){
			warningDict = warningDictService.getWarningDictByName(WarningDictNames.WEATHER_WARNING_DICT_NAME);
			if(null == warningDict){
				/* 添加告警类型默认值 */
				warningDict = new WarningDict();
				warningDict.setCode(WarningDictCodes.WEATHER_WARNING_CODE);
				warningDict.setName(WarningDictNames.WEATHER_WARNING_DICT_NAME);
				warningDictService.addWarningDict(warningDict);
				warningDict = warningDictService.getWarningDictByName(WarningDictNames.WEATHER_WARNING_DICT_NAME);
			}
			
			CONDT_TEMPERATURE_MAX = warningConditionService.getWarningCondition(warningDict, TEMPERATURE_FIELD, CompareType.OVER);	
			if(null == CONDT_TEMPERATURE_MAX){ 
				/* 添加默认最大气温告警条件 */
				CONDT_TEMPERATURE_MAX = new WarningCondition();
				CONDT_TEMPERATURE_MAX.setWarningDict(warningDict);
				CONDT_TEMPERATURE_MAX.setFieldName(TEMPERATURE_FIELD);
				CONDT_TEMPERATURE_MAX.setCompare(CompareType.OVER.getValue());
				CONDT_TEMPERATURE_MAX.setThreshold(DEFAULT_MAX_TEMPERATURE);
				CONDT_TEMPERATURE_MAX = warningConditionService.updateWarningCondition(CONDT_TEMPERATURE_MAX);
			}
			CONDT_TEMPERATURE_MIN = warningConditionService.getWarningCondition(warningDict, TEMPERATURE_FIELD, CompareType.UNDER);	
			if(null == CONDT_TEMPERATURE_MIN){ 
				/* 添加默认最小气温告警条件 */
				CONDT_TEMPERATURE_MIN = new WarningCondition();
				CONDT_TEMPERATURE_MIN.setWarningDict(warningDict);
				CONDT_TEMPERATURE_MIN.setFieldName(TEMPERATURE_FIELD);
				CONDT_TEMPERATURE_MIN.setCompare(CompareType.UNDER.getValue());
				CONDT_TEMPERATURE_MIN.setThreshold(DEFAULT_MIN_TEMPERATURE);
				CONDT_TEMPERATURE_MIN = warningConditionService.updateWarningCondition(CONDT_TEMPERATURE_MIN);
			}
				
			CONDT_HUMIDITY_MAX = warningConditionService.getWarningCondition(warningDict, HUMIDITY_FIELD, CompareType.OVER);
			if(null == CONDT_HUMIDITY_MAX){
				/* 添加默认最大湿度告警条件 */
				CONDT_HUMIDITY_MAX = new WarningCondition();
				CONDT_HUMIDITY_MAX.setWarningDict(warningDict);
				CONDT_HUMIDITY_MAX.setFieldName(HUMIDITY_FIELD);
				CONDT_HUMIDITY_MAX.setCompare(CompareType.OVER.getValue());
				CONDT_HUMIDITY_MAX.setThreshold(DEFAULT_MAX_HUMIDITY);
				CONDT_HUMIDITY_MAX = warningConditionService.updateWarningCondition(CONDT_HUMIDITY_MAX);
			}
			CONDT_HUMIDITY_MIN = warningConditionService.getWarningCondition(warningDict, HUMIDITY_FIELD, CompareType.UNDER);
			if(null == CONDT_HUMIDITY_MIN){
				/* 添加默认最大湿度告警条件 */
				CONDT_HUMIDITY_MIN = new WarningCondition();
				CONDT_HUMIDITY_MIN.setWarningDict(warningDict);
				CONDT_HUMIDITY_MIN.setFieldName(HUMIDITY_FIELD);
				CONDT_HUMIDITY_MIN.setCompare(CompareType.UNDER.getValue());
				CONDT_HUMIDITY_MIN.setThreshold(DEFAULT_MIN_HUMIDITY);
				CONDT_HUMIDITY_MIN = warningConditionService.updateWarningCondition(CONDT_HUMIDITY_MIN);
			}
			
			List<WarningCondition> wsConditions = warningConditionService.getWarningConditions(warningDict, WINDSPEED_FIELD);
			CONDT_WINDSPEED = wsConditions.size() > 0 ? wsConditions.get(0) : null;
			if(null == CONDT_WINDSPEED){
				/* 添加默认风速告警条件 */
				CONDT_WINDSPEED = new WarningCondition();
				CONDT_WINDSPEED.setWarningDict(warningDict);
				CONDT_WINDSPEED.setFieldName(WINDSPEED_FIELD);
				CONDT_WINDSPEED.setCompare(CompareType.OVER.getValue());
				CONDT_WINDSPEED.setThreshold(DEFAULT_MAX_WINDSPEED);
				CONDT_WINDSPEED = warningConditionService.updateWarningCondition(CONDT_WINDSPEED);
			}
			
//			CONDT_WINDDIRECTION = warningConditionService.getWarningCondition(warningDict, WINDDIRECTION_FIELD);
//			if(null == CONDT_WINDDIRECTION){
//				/* 添加默认风向告警条件 */
//				CONDT_WINDDIRECTION =  new WarningCondition();
//				CONDT_WINDDIRECTION.setWarningDict(warningDict);
//				CONDT_WINDDIRECTION.setFieldName(WINDDIRECTION_FIELD);
//				CONDT_WINDDIRECTION.setCompare(1);
//				CONDT_WINDDIRECTION.setThreshold(0.0);
//				CONDT_WINDDIRECTION = warningConditionService.updateWarningCondition(CONDT_WINDDIRECTION);
//			}
			List<WarningCondition> apConditions = warningConditionService.getWarningConditions(warningDict, AIRPRESSURE_FIELD);
			CONDT_AIRPRESSURE = apConditions.size() > 0 ? apConditions.get(0) : null;
			if(null == CONDT_AIRPRESSURE){
				/* 添加默认气压告警条件 */
				CONDT_AIRPRESSURE =  new WarningCondition();
				CONDT_AIRPRESSURE.setWarningDict(warningDict);
				CONDT_AIRPRESSURE.setFieldName(AIRPRESSURE_FIELD);
				CONDT_AIRPRESSURE.setCompare(CompareType.OVER.getValue());
				CONDT_AIRPRESSURE.setThreshold(DEFAULT_MAX_AIRPRESSURE);
				CONDT_AIRPRESSURE = warningConditionService.updateWarningCondition(CONDT_AIRPRESSURE);
			}
			
			List<WarningCondition> drConditions = warningConditionService.getWarningConditions(warningDict, DAILYRAINFALL_FIELD);
			CONDT_DAILYRAINFALL = drConditions.size() > 0 ? drConditions.get(0) : null;
			if(null == CONDT_DAILYRAINFALL){
				/* 添加默认降雨量告警条件 */
				CONDT_DAILYRAINFALL =  new WarningCondition();
				CONDT_DAILYRAINFALL.setWarningDict(warningDict);
				CONDT_DAILYRAINFALL.setFieldName(DAILYRAINFALL_FIELD);
				CONDT_DAILYRAINFALL.setCompare(CompareType.OVER.getValue());
				CONDT_DAILYRAINFALL.setThreshold(DEFAULT_MAX_DAILYRAINFALL);
				CONDT_DAILYRAINFALL = warningConditionService.updateWarningCondition(CONDT_DAILYRAINFALL);
			}
			
//			CONDT_RADIATIONINTENSITY = warningConditionService.getWarningCondition(warningDict, RADIATIONINTENSITY_FIELD);
//			if(null == CONDT_RADIATIONINTENSITY){
//				/* 添加默认光照辐射告警条件 */
//				CONDT_RADIATIONINTENSITY =  new WarningCondition();
//				CONDT_RADIATIONINTENSITY.setWarningDict(warningDict);
//				CONDT_RADIATIONINTENSITY.setFieldName(RADIATIONINTENSITY_FIELD);
//				CONDT_RADIATIONINTENSITY.setCompare(1);
//				CONDT_RADIATIONINTENSITY.setThreshold(0.0);
//				CONDT_RADIATIONINTENSITY = warningConditionService.updateWarningCondition(CONDT_RADIATIONINTENSITY);
//			}
		}
	}
	
	/**
	 * 设置告警条件阀值
	 * @param temperature
	 * 				气温
	 * @param humidity
	 * 				湿度
	 * @param windSpeed
	 * 				风速
	 * @param windDirection
	 * 				风向
	 * @param airePressure
	 * 				气压
	 * @param dailyRainFall
	 * 				降雨量
	 * @param radiationIntensity
	 * 				光照辐射度
	 * 
	 */
	public static void setThresholds(Double maxTemperature, Double minTemperature, Double maxHumidity, 
			Double minHumidity, Double windSpeed, Double airPressure, Double dailyRainFall){
		if(null != maxTemperature){
			CONDT_TEMPERATURE_MAX.setThreshold(maxTemperature);
			warningConditionService.updateWarningCondition(CONDT_TEMPERATURE_MAX);
		}
		if(null != minTemperature){
			CONDT_TEMPERATURE_MIN.setThreshold(minTemperature);
			warningConditionService.updateWarningCondition(CONDT_TEMPERATURE_MIN);
		}		
		if(null != maxHumidity){
			CONDT_HUMIDITY_MAX.setThreshold(maxHumidity);
			warningConditionService.updateWarningCondition(CONDT_HUMIDITY_MAX);
		}
		if(null != minHumidity){
			CONDT_HUMIDITY_MIN.setThreshold(minHumidity);
			warningConditionService.updateWarningCondition(CONDT_HUMIDITY_MIN);
		}		
		if(null != windSpeed){
			CONDT_WINDSPEED.setThreshold(windSpeed);
			warningConditionService.updateWarningCondition(CONDT_WINDSPEED);
		}
		
//		if(null != windDirection){
//			CONDT_WINDDIRECTION.setThreshold(windDirection);
//			warningConditionService.updateWarningCondition(CONDT_WINDDIRECTION);
//		}
		
		if(null != airPressure){
			CONDT_AIRPRESSURE.setThreshold(airPressure);
			warningConditionService.updateWarningCondition(CONDT_AIRPRESSURE);
		}
		
		if(null != dailyRainFall){
			CONDT_DAILYRAINFALL.setThreshold(dailyRainFall);
			warningConditionService.updateWarningCondition(CONDT_DAILYRAINFALL);
		}
		
//		if(null != radiationIntensity){
//			CONDT_RADIATIONINTENSITY.setThreshold(radiationIntensity);
//			warningConditionService.updateWarningCondition(CONDT_RADIATIONINTENSITY);
//		}
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
