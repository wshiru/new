/*
 * Project platform
 *
 * Classname BaseMonsoonWarningConditionAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-26 12：13
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.web;

/**
 * 风偏Action基类
 * 
 * @version v1.0.0
 * @author 
 */
public class BaseMonsoonWarningConditionAction extends BaseWarningConditionAction{

	/** 序列号版本号 */
	private static final long serialVersionUID = 1673509183868687673L;
	

	/** 风偏角阀值 */
	protected Double windAngle;
	/** 倾斜角阀值 */
	protected Double angle;
	/** 最小电气间隙参数阀值 */
	protected Double minClearanceParams;


	/**
	 * @param windAngle the windAngle to set
	 */
	public void setWindAngle(Double windAngle) {
		this.windAngle = windAngle;
	}

	/**
	 * @param angle the angle to set
	 */
	public void setAngle(Double angle) {
		this.angle = angle;
	}

	/**
	 * @param minClearanceParams the minClearanceParams to set
	 */
	public void setMinClearanceParams(Double minClearanceParams) {
		this.minClearanceParams = minClearanceParams;
	}

	/**
	 * @return the minClearanceParams
	 */
	public Double getMinClearanceParams() {
		return minClearanceParams;
	}

	/**
	 * @return the angle
	 */
	public Double getAngle() {
		return angle;
	}

	/**
	 * @return the windAngle
	 */
	public Double getWindAngle() {
		return windAngle;
	}


}
