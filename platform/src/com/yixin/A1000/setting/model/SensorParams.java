/*
 * Project platform
 *
 * Classname Role.java
 * 
 * Version 1.0.0
 * 
 * Creator 梁立全

 * CreateAt 2011-6-8 下午
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */

package com.yixin.A1000.setting.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yixin.A1000.archive.model.Sensor;

/**
 * 监测装置参数
 * 
 * @author 梁立全
 */
@Entity
@Table(name = "T_SensorParams")
public class SensorParams {

	/** ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String sensorParamsId;

	/** 监测装置 **/
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "sensorId")
	private Sensor sensor = new Sensor();

	/** 采样周期时间 **/
	private Integer mainTime;

	/** 采样数 **/
	private Integer sampleCount;

	/** 采样间隔 **/
	private Integer sampleInterval;

	/*** 状态 0：未下发 1：已下发并确认 **/
	private Integer state;

	/** 创建时间。与TaskConfig中的createTime相同 */
	private Date createTime;

	public String getSensorParamsId() {
		return sensorParamsId;
	}

	public void setSensorParamsId(String sensorParamsId) {
		this.sensorParamsId = sensorParamsId;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Integer getMainTime() {
		return mainTime;
	}

	public void setMainTime(Integer mainTime) {
		this.mainTime = mainTime;
	}

	public Integer getSampleCount() {
		return sampleCount;
	}

	public void setSampleCount(Integer sampleCount) {
		this.sampleCount = sampleCount;
	}

	public Integer getSampleInterval() {
		return sampleInterval;
	}

	public void setSampleInterval(Integer sampleInterval) {
		this.sampleInterval = sampleInterval;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 获取 创建时间。与TaskConfig中的createTime相同
	 * 
	 * @return 创建时间。与TaskConfig中的createTime相同
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置 创建时间。与TaskConfig中的createTime相同
	 * 
	 * @param createTime
	 *            创建时间。与TaskConfig中的createTime相同
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
