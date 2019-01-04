/*
 * Project platform
 *
 * Classname InsulatorMonsoonSampling.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 14：19
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.insulatormonsoon.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yixin.A1000.base.model.BaseSampling;

/**
 * 绝缘子串风偏实体类
 * 
 * @version v1.0.0
 * @author 
 *
 */
@Entity
@Table(name="T_InsulatorMonsoonSampling")
public class InsulatorMonsoonSampling extends BaseSampling {

	private static final long serialVersionUID = -8013542063216135704L;

	/** 绝缘子串风偏ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String insulatorMonsoonSamplingId;
	
	/** 风偏角 */
	private Double windAngle;
	
	/** 倾斜角 */
	private Double angle;
	
	/** 最小电气间隙参数 */
	private Double minClearanceParams;
	
	
	/**
	 * 获取绝缘子串风偏ID
	 * @return
	 */
	public String getInsulatorMonsoonSamplingId() {
		return insulatorMonsoonSamplingId;
	}
	/**
	 * 设置绝缘子串风偏ID
	 * @param wireSagSamplingId
	 */
	public void setInsulatorMonsoonSamplingId(String insulatorMonsoonSamplingId) {
		this.insulatorMonsoonSamplingId = insulatorMonsoonSamplingId;
	}


	/**
	 * 获取风偏角
	 * @return
	 */
	public Double getWindAngle() {
		return windAngle;
	}
	/**
	 * 设置风偏角
	 * @param windAngle
	 */
	public void setWindAngle(Double windAngle) {
		this.windAngle = windAngle;
	}
	/**
	 * 获取倾斜角
	 * @return
	 */
	public Double getAngle() {
		return angle;
	}
	/**
	 * 设置倾斜角
	 * @param angle
	 */
	public void setAngle(Double angle) {
		this.angle = angle;
	}
	/**
	 * 获取最小电气间隙参数
	 * @return
	 */
	public Double getMinClearanceParams() {
		return minClearanceParams;
	}
	/**
	 * 设置最小电气间隙参数
	 * @param minClearanceParams
	 */
	public void setMinClearanceParams(Double minClearanceParams) {
		this.minClearanceParams = minClearanceParams;
	}

}
