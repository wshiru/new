/*
 * Project platform
 *
 * Classname WireSagSampling.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 上午 11：50
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.wiresag.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yixin.A1000.base.model.BaseSampling;

/**
 * 导线弧垂实体类
 * 
 * @version v1.0.0
 * @author 
 *
 */
@Entity
@Table(name="T_WireSagSampling")
public class WireSagSampling extends BaseSampling{

	private static final long serialVersionUID = -5606178814870958905L;

	/** 导线温度ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String wireSagSamplingId;
	
	/** 弧垂 */
	private Double sag;
	
	/** 对地距离 */
	private Double groundDistance;
	
	/**
	 * 获取导线弧垂ID
	 * @return
	 */
	public String getWireSagSamplingId() {
		return wireSagSamplingId;
	}
	/**
	 * 设置导线弧垂ID
	 * @param wireSagSamplingId
	 */
	public void setWireSagSamplingId(String wireSagSamplingId) {
		this.wireSagSamplingId = wireSagSamplingId;
	}

	/**
	 * 获取弧垂
	 * @return
	 */
	public Double getSag() {
		return sag;
	}
	/**
	 * 设置弧垂
	 * @param sag
	 */
	public void setSag(Double sag) {
		this.sag = sag;
	}
	/**
	 * 获取对地距离
	 * @return
	 */
	public Double getGroundDistance() {
		return groundDistance;
	}
	/**
	 * 设置对地距离
	 * @param groundDistance
	 */
	public void setGroundDistance(Double groundDistance) {
		this.groundDistance = groundDistance;
	}

}
