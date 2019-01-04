/*
 * Project platform
 *
 * Classname WireTemperatureSampling.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-22 下午 16：50
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.wiretemperature.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yixin.A1000.base.model.BaseSampling;

/**
 * 导线温度实体类
 * 
 * @version v1.0.0
 * @author 
 *
 */
@Entity
@Table(name="T_WireTemperatureSampling")
public class WireTemperatureSampling extends BaseSampling{
	
	private static final long serialVersionUID = -4533798256946498290L;

	/** 导线温度ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String wireTemperatureSamplingId;
	
	/** 线温1 */
	private Double lineTemperature1;
	
	/** 线温2 */
	private Double lineTemperature2;


	/**
	 * 获取导线温度ID
	 * @return
	 */
	public String getWireTemperatureSamplingId() {
		return wireTemperatureSamplingId;
	}
	/**
	 * 设置导线温度ID
	 * @return
	 */
	public void setWireTemperatureSamplingId(String wireTemperatureSamplingId) {
		this.wireTemperatureSamplingId = wireTemperatureSamplingId;
	}

	/**
	 * 获取线温1
	 * @return
	 */
	public Double getLineTemperature1() {
		return lineTemperature1;
	}
	/**
	 * 设置线温1
	 * @return
	 */
	public void setLineTemperature1(Double lineTemperature1) {
		this.lineTemperature1 = lineTemperature1;
	}
	/**
	 * 获取线温2
	 * @return
	 */
	public Double getLineTemperature2() {
		return lineTemperature2;
	}
	/**
	 * 设置线温2
	 * @return
	 */
	public void setLineTemperature2(Double lineTemperature2) {
		this.lineTemperature2 = lineTemperature2;
	}
}
