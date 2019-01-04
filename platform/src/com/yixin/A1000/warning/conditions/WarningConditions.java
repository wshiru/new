/*
 * Project platform
 *
 * Classname WarningConditions.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-22 10：10
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.conditions;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yixin.A1000.warning.service.WarningConditionService;
import com.yixin.A1000.warning.service.WarningDictService;

/**
 * 告警条件基类
 * 
 * @version v1.0.0
 * @author 
 */
public class WarningConditions {

	/** 告警条件服务名  */
	private final static String WARNING_CONDITION_SERVICE_NAME = "warningConditionService";
	/** 告警条件服务名  */
	private final static String WARNING_DICT_SERVICE_NAME = "warningDictService";
	
	/** 告警条件服务  */
	protected static WarningConditionService warningConditionService;
	/** 告警类型服务 */
	protected static WarningDictService warningDictService;


	static {
		//初始化告警条件服务、告警类型服务	
		try {
			ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml", 
					"/com/yixin/ca2000/warning/config/warning-applicationContext.xml" });
			warningConditionService = (WarningConditionService)ctx.getBean(WARNING_CONDITION_SERVICE_NAME);	
			warningDictService = (WarningDictService)ctx.getBean(WARNING_DICT_SERVICE_NAME);
		} catch (Exception e) {
			System.out.println("告警Spring配置文件加载失败：" + e.getMessage());
		}
	}

}
