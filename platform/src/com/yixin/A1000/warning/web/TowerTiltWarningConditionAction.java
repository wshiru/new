/*
 * Project platform
 *
 * Classname TowerTiltWarningConditionAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-22 15:39
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.web;

import com.yixin.A1000.warning.conditions.TowerTiltWarningConditions;
import com.yixin.A1000.warning.constant.WarningDictNames;

/**
 * 杆塔倾斜风偏告警条件Action类
 * 
 * @version v1.0.0
 * @author 
 */
public class TowerTiltWarningConditionAction extends BaseWarningConditionAction{

	/** 序列号版本号 */
	private static final long serialVersionUID = 2699738115269782980L;
	
	/** 综合倾斜度阀值 */
	private Double generalInclination;
	/** 顺线倾斜度阀值 */
	private Double gradientAlongLines;
	/** 横向倾斜度阀值 */
	private Double lateralTilt;

	/**
	 * @param generalInclination the generalInclination to set
	 */
	public void setGeneralInclination(Double generalInclination) {
		this.generalInclination = generalInclination;
	}

	/**
	 * @return the generalInclination
	 */
	public Double getGeneralInclination() {
		return generalInclination;
	}

	/**
	 * @param gradientAlongLines the gradientAlongLines to set
	 */
	public void setGradientAlongLines(Double gradientAlongLines) {
		this.gradientAlongLines = gradientAlongLines;
	}

	/**
	 * @return the gradientAlongLines
	 */
	public Double getGradientAlongLines() {
		return gradientAlongLines;
	}

	/**
	 * @param lateralTilt the lateralTilt to set
	 */
	public void setLateralTilt(Double lateralTilt) {
		this.lateralTilt = lateralTilt;
	}

	/**
	 * @return the lateralTilt
	 */
	public Double getLateralTilt() {
		return lateralTilt;
	}

	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	@Override
	public void prepare() throws Exception {
		if(null != TowerTiltWarningConditions.CONDT_GENERALINCLINATION)
			generalInclination = TowerTiltWarningConditions.CONDT_GENERALINCLINATION.getThreshold();
		if(null != TowerTiltWarningConditions.CONDT_GRADIENTALONGLINES)
			gradientAlongLines = TowerTiltWarningConditions.CONDT_GRADIENTALONGLINES.getThreshold();
		if(null != TowerTiltWarningConditions.CONDT_LATERALTILT)
			lateralTilt = TowerTiltWarningConditions.CONDT_LATERALTILT.getThreshold();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.web.WarningConditionAction#save()
	 */
	public String save() {
		try {
			TowerTiltWarningConditions.setThresholds(generalInclination, gradientAlongLines, lateralTilt);
		} catch (Exception e) {
			setFailureMessage("保存失败");
			return INPUT;
		}
		addSaveLog(WarningDictNames.TOWERTILT_WARNING_DICT_NAME);
		return super.save();
	}
}
