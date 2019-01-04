/*
 * Project platform
 *
 * Classname WireTemperatureWarningConditionAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-25 15:25
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.web;

import com.yixin.A1000.warning.conditions.WireSagWarningConditions;
import com.yixin.A1000.warning.constant.WarningDictNames;

/**
 * 导线弧垂告警条件Action类
 * 
 * @version v1.0.0
 * @author 
 */
public class WireSagWarningConditionAction extends BaseWarningConditionAction{

	/** 序列号版本号 */
	private static final long serialVersionUID = 1687274690080072383L;

	/** 弧垂阀值 */
	private Double sag;
	/** 对地距离阀值 */
	private Double groundDistance;

	/**
	 * @param sag the sag to set
	 */
	public void setSag(Double sag) {
		this.sag = sag;
	}

	/**
	 * @param groundDistance the groundDistance to set
	 */
	public void setGroundDistance(Double groundDistance) {
		this.groundDistance = groundDistance;
	}

	/**
	 * @return the groundDistance
	 */
	public Double getGroundDistance() {
		return groundDistance;
	}

	/**
	 * @return the sag
	 */
	public Double getSag() {
		return sag;
	}

	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	@Override
	public void prepare() throws Exception {
		if(null != WireSagWarningConditions.CONDT_SAG)
			sag = WireSagWarningConditions.CONDT_SAG.getThreshold();
		if(null != WireSagWarningConditions.CONDT_GROUNDDISTANCE)
			groundDistance = WireSagWarningConditions.CONDT_GROUNDDISTANCE.getThreshold();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.web.WarningConditionAction#save()
	 */
	public String save() {
		try {
			WireSagWarningConditions.setThresholds(sag, groundDistance);
		} catch (Exception e) {
			setFailureMessage("保存失败！");
			return INPUT;
		}
		addSaveLog(WarningDictNames.WIRESAG_WARNING_DICT_NAME);
		return super.save();
	}
}
