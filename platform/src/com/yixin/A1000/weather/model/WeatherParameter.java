/*
 * Project platform
 *
 * Classname WeatherSampling.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 14:31
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.weather.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.base.model.BaseSampling;


/**
 * 微气象参数实体类
 * 
 * @version v1.0.0
 * @author 
 *
 */
@Entity
@Table(name="T_WeatherParams")
public class WeatherParameter{

	private static final long serialVersionUID = -8861324524768726L;

	/** 微气象参数ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String weatherParamsId;
    	
	/** 监测装置ID */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name="sensorId")
	private Sensor sensor;
	 
	/** 安装高度(米) */
	private Double mountingHeight;
	
	/** 设计高度(米) */
	private Double designHeight;
	
	/** 地面状况系数(风速用) */
	private Double windCoefficient;

	public String getWeatherParamsId() {
		return weatherParamsId;
	}

	public void setWeatherParamsId(String weatherParamsId) {
		this.weatherParamsId = weatherParamsId;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Double getMountingHeight() {
		return mountingHeight;
	}

	public void setMountingHeight(Double mountingHeight) {
		this.mountingHeight = mountingHeight;
	}

	public Double getDesignHeight() {
		return designHeight;
	}

	public void setDesignHeight(Double designHeight) {
		this.designHeight = designHeight;
	}

	public Double getWindCoefficient() {
		return windCoefficient;
	}

	public void setWindCoefficient(Double windCoefficient) {
		this.windCoefficient = windCoefficient;
	}

 
	
}
