/*
 * Project platform
 *
 * Classname WhiteMonsoonSampling.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 14：10
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.whitemonsoon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yixin.A1000.base.model.BaseSampling;

/**
 * 相间风偏实体类
 * 
 * @version v1.0.0
 * @author 
 *
 */
@Entity
@Table(name="T_WhiteMonsoonSampling")
public class WhiteMonsoonSampling extends BaseSampling{

	private static final long serialVersionUID = -8440766165694105208L;

	/** 相间风偏ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String whiteMonsoonSamplingId;
	
	/** 风偏角 */
	private Double windAngle;
	
	/** 倾斜角 */
	private Double angle;
	
	/** 最小电气间隙参数 */
	private Double minClearanceParams;
	
	
	/**
	 * 获取相间风偏ID
	 * @return
	 */
	public String getWhiteMonsoonSamplingId() {
		return whiteMonsoonSamplingId;
	}
	/**
	 * 设置相间风偏ID
	 * @param wireSagSamplingId
	 */
	public void setWhiteMonsoonSamplingId(String whiteMonsoonSamplingId) {
		this.whiteMonsoonSamplingId = whiteMonsoonSamplingId;
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
