/*
 * Project platform
 *
 * Classname BaseSampling.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-11 19:10
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.base.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.yixin.A1000.archive.model.Sensor;

/**
 * 采集历史数据基类
 * 
 * @version v1.0.0
 * @author 
 */

@MappedSuperclass
public class BaseSampling implements Serializable{

	private static final long serialVersionUID = 8244983148704917671L;

	/** 监测装置ID */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name="sensorId")
	private Sensor sensor;
	
	/** 采集时间 */
	private Date samplingTime;
	
	/** 采集周期时间 */
	private Date acquisitionTime;
	
	/** 入库时间 */
	private Date createTime;
	/** 数据类型*/
	private Integer dataType;

	
	/**
	 * 获取监测装置
	 * @return
	 */
	public Sensor getSensor() {
		return sensor;
	}
	/**
	 * 设置监测装置
	 * @return
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	/**
	 * 获取采集时间
	 * @return
	 */
	public Date getSamplingTime() {
		return samplingTime;
	}
	/**
	 * 设置采集时间
	 * @return
	 */
	public void setSamplingTime(Date samplingTime) {
		this.samplingTime = samplingTime;
	}
	/**
	 * 获取采集周期时间
	 * @return
	 */
	public Date getAcquisitionTime() {
		return acquisitionTime;
	}
	/**
	 * 设置采集周期时间
	 * @return
	 */
	public void setAcquisitionTime(Date acquisitionTime) {
		this.acquisitionTime = acquisitionTime;
	}
	/**
	 * 获取入库时间
	 * @return
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置入库时间
	 * @return
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	/**
	 * @return the dataType
	 */
	public Integer getDataType() {
		return dataType;
	}
}
