/*
 * Project platform
 *
 * Classname WireTemperatureWarningConditionAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-25 14:45
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.web;

import com.yixin.A1000.warning.conditions.WireTemperatureWarningConditions;
import com.yixin.A1000.warning.constant.WarningDictNames;

/**
 * 导线温度告警条件Action类
 * 
 * @version v1.0.0
 * @author 
 */
public class WireTemperatureWarningConditionAction extends BaseWarningConditionAction{

	/** 序列号版本号 */
	private static final long serialVersionUID = -32727725433783799L;
	

	/** 线温1阀值 */
	private Double temperature1;
	/** 线温2阀值 */
	private Double temperature2;


	/**
	 * @param temperature1 the temperature1 to set
	 */
	public void setTemperature1(Double temperature1) {
		this.temperature1 = temperature1;
	}

	/**
	 * @return the temperature1
	 */
	public Double getTemperature1() {
		return temperature1;
	}
	

	/**
	 * @param temperature2 the temperature2 to set
	 */
	public void setTemperature2(Double temperature2) {
		this.temperature2 = temperature2;
	}

	/**
	 * @return the temperature2
	 */
	public Double getTemperature2() {
		return temperature2;
	}

	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	@Override
	public void prepare() throws Exception {
		if(null != WireTemperatureWarningConditions.CONDT_LINETEMPERATURE1)
			temperature1 = WireTemperatureWarningConditions.CONDT_LINETEMPERATURE1.getThreshold();
		if(null != WireTemperatureWarningConditions.CONDT_LINETEMPERATURE2)
			temperature2 = WireTemperatureWarningConditions.CONDT_LINETEMPERATURE2.getThreshold();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.web.WarningConditionAction#save()
	 */
	public String save() {
		try {
			WireTemperatureWarningConditions.setThresholds(temperature1, temperature2);
		} catch (Exception e) {
			setFailureMessage("保存失败！");
			return INPUT;
		}
		addSaveLog(WarningDictNames.WIRETEMPERATURE_WARNING_DICT_NAME);
		return super.save();
	}
}
