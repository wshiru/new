/*
 * Project platform
 *
 * Class SensorType.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-21 下午02:31:38
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



/**
 * 监测类型
 * 
 * @version v1.0.0
 * @author 
 */
@Entity
@Table(name = "T_SensorType")
public class SensorType implements Serializable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -4207740637530084754L;

	/** 监测类型Id */
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	
	private String sensorTypeId;

	/** 监测类型编号 */
	@OrderBy("sensorTypeCode")
	private String sensorTypeCode;

	/** 监测类型名称 */
	private String sensorTypeName;

	/** 描述 */
	private String sensorDesc;

//	
//	/** 监测类型功能列表**/
//	@OneToMany(mappedBy="sensorType")
//	private  List<SensorFunction>  sensorFunctionList = new ArrayList<SensorFunction>();

	
	/**
	 * 获取 监测类型Id
	 * 
	 * @return 监测类型Id
	 */
	
	public String getSensorTypeId() {
		return this.sensorTypeId;
	}

	/**
	 * 设置 监测类型Id
	 * 
	 * @param sensorTypeId
	 *            监测类型Id
	 */
	public void setSensorTypeId(String sensorTypeId) {
		this.sensorTypeId = sensorTypeId;
	}

	/**
	 * 获取 监测类型编号
	 * 
	 * @return 监测类型编号
	 */
	public String getSensorTypeCode() {
		return this.sensorTypeCode;
	}

	/**
	 * 设置 监测类型编号
	 * 
	 * @param sensorTypeCode
	 *            监测类型编号
	 */
	public void setSensorTypeCode(String sensorTypeCode) {
		this.sensorTypeCode = sensorTypeCode;
	}

	/**
	 * 获取 监测类型名称
	 * 
	 * @return 监测类型名称
	 */
	public String getSensorTypeName() {
		return this.sensorTypeName;
	}

	/**
	 * 设置 监测类型名称
	 * 
	 * @param sensorTypeName
	 *            监测类型名称
	 */
	public void setSensorTypeName(String sensorTypeName) {
		this.sensorTypeName = sensorTypeName;
	}

	/**
	 * 获取 描述
	 * 
	 * @return 描述
	 */
	public String getSensorDesc() {
		return this.sensorDesc;
	}

	/**
	 * 设置 描述
	 * 
	 * @param sensorDesc
	 *            描述
	 */
	public void setSensorDesc(String sensorDesc) {
		this.sensorDesc = sensorDesc;
	}
	
//	/**
//	 * 返回监测类型功能列表
//	 * @return
//	 */
//	public List<SensorFunction> getSensorFunctionList() {
//		return sensorFunctionList;
//	}
//
//	
//	/**
//	 * 设置监测类型功能列表
//	 * @param sensorFunctionList
//	 */
//	public void setSensorFunctionList(List<SensorFunction> sensorFunctionList) {
//		this.sensorFunctionList = sensorFunctionList;
//	}
	
}
