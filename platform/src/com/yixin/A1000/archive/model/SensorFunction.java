/*
 * Project platform
 *
 * Class Sensor.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-6-21 下午02:33:00
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.archive.model;


import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yixin.framework.system.model.AuthResource;

/**
 * 监测装置类型功能
 * 
 * @version v1.0.0
 * @author 梁立全

 */
@Entity
@Table(name = "T_SensorFunction")
public class SensorFunction implements Serializable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -2967196483754652169L;


	/** 监测装置类型功能Id */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String sensorFunctionId;


	/** 所属监测类型 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "sensorTypeId")
	private SensorType sensorType = new SensorType();

	
	/** 权限资源 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "authResourceId")
	private AuthResource authResource = new AuthResource();

	/**
	 * 返回监测类型
	 * @return
	 */
	public SensorType getSensorType() {
		return sensorType;
	}


	/**
	 * 设置监测类型
	 * @param sensorType
	 */
	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}


	/**
	 * 返回权限资源
	 * @return
	 */
	public AuthResource getAuthResource() {
		return authResource;
	}


	/**
	 * 设置权限资源
	 * @param authResource
	 */
	public void setAuthResource(AuthResource authResource) {
		this.authResource = authResource;
	}

	public String getSensorFunctionId() {
		return sensorFunctionId;
	}


	public void setSensorFunctionId(String sensorFunctionId) {
		this.sensorFunctionId = sensorFunctionId;
	}
	


	
}
