/*
 * Project platform
 *
 * Classname Warning.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-05 12:16
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.model;

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
 * 告警实体类
 * 
 * @version v1.0.0
 * @author 
 *
 */
@Entity
@Table(name="T_Warning")
public class Warning {
	
	/** 告警ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String warningId;
	
	/** 告警类型 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "warningDictId")
	private WarningDict warningDict;
	
	/** 监测装置 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "sensorID")
	private Sensor sensor;
	
	/** 发生时间 */
	private Date samplingDate;		
	/** 内容 */
	private String warningInfo;
	/** 入库时间 */
	private Date createDate;
	/** 处理状态  1为已处理，其它为未处理 */ 
	private Integer operationState;
	/** 告警等级 */
	private Integer warningLevel;
	/**
	 * 获取告警ID
	 * @return
	 */
	public String getWarningId() {
		return warningId;
	}
	/**
	 * 设置告警ID
	 * @return
	 */
	public void setWarningId(String warningId) {
		this.warningId = warningId;
	}
	/**
	 * 获取告警类型
	 * @return
	 */
	public WarningDict getWarningDict() {
		return warningDict;
	}
	/**
	 * 设置告警类型
	 * @param warningDict
	 */
	public void setWarningDict(WarningDict warningDict) {
		this.warningDict = warningDict;
	}
	/**
	 * 获取监测装置
	 * @return
	 */
	public Sensor getSensor() {
		return sensor;
	}
	/**
	 * 设置监测装置
	 * @param sensor
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	/**
	 * 获取发生时间
	 * @return
	 */
	public Date getSamplingDate() {
		return samplingDate;
	}
	/**
	 * 设置发生时间
	 * @param samplingDate
	 */
	public void setSamplingDate(Date samplingDate) {
		this.samplingDate = samplingDate;
	}
	/**
	 * 获取内容
	 * @return
	 */
	public String getWarningInfo() {
		return warningInfo;
	}
	/**
	 * 设置内容
	 * @param warningInfo
	 */
	public void setWarningInfo(String warningInfo) {
		this.warningInfo = warningInfo;
	}
	/**
	 * 获取入库时间
	 * @return
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置入库时间
	 * @return
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @param 处理状态 to set
	 */
	public void setOperationState(Integer operationState) {
		this.operationState = operationState;
	}
	/**
	 * @return the 处理状态
	 */
	public Integer getOperationState() {
		return operationState;
	}
	/**
	 * @param 设置 告警等级 
	 */
	public void setWarningLevel(Integer warningLevel) {
		this.warningLevel = warningLevel;
	}
	/**
	 * @return 返回 告警等级
	 */
	public Integer getWarningLevel() {
		return warningLevel;
	}

}


