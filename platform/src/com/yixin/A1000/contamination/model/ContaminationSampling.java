/*
 * Project platform
 *
 * Classname IceThincknessSampling.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 14:23
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.contamination.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.yixin.A1000.base.model.BaseSampling;

/**
 * 污秽度监测数据实体类
 * 
 * @version v1.0.0
 * @author 
 * 
 */
@Entity
@Table(name = "T_ContaminationSampling")
public class ContaminationSampling extends BaseSampling {

	private static final long serialVersionUID = -7477522134211305L;

	/** 污秽度监测数据ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String contaminationSamplingId;

	/** 报警标志**/
	private Integer alarmFlag;
	
	/** 等值附盐密度，即盐密 **/
	private Double esdd;

	/** 等值附盐密度，即盐密 是否为告警数据 */
	@Transient
	private boolean isEsddWarningData;
	
	/** 不溶物密度，即灰密 */
	private Double nsdd;

	/** 不溶物密度，即灰密 是否为告警数据 */
	@Transient
	private boolean isNsddWarningData;

	/** 日最高温度 */
	private Double dailyMaxTemperature;

	/** 日最高温度 是否为告警数据 */
	@Transient
	private boolean isDailyMaxTemperatureWarningData;

	/** 日最低温度 **/
	private Double dailyMinTemperature;

	/** 日最低温度 是否为告警数据 */
	@Transient
	private boolean isDailyMinTemperatureWarningData;

	/** 日最大湿度 **/
	private Double dailyMaxHumidity;

	/** 日最大湿度 是否为告警数据 */
	@Transient
	private boolean isDailyMaxHumidityWarningData;
	
	/** 日最小湿度 **/
	private Double dailyMinHumidity;

	/** 日最小湿度 是否为告警数据 */
	@Transient
	private boolean isDailyMinHumidityWarningData;

	public String getIceThincknessSamplingId() {
		return contaminationSamplingId;
	}

	public void setIceThincknessSamplingId(String contaminationSamplingId) {
		this.contaminationSamplingId = contaminationSamplingId;
	}

	public Integer getAlarmFlag() {
		return alarmFlag;
	}

	public void setAlarmFlag(Integer alarmFlag) {
		this.alarmFlag = alarmFlag;
	}

	public Double getEsdd() {
		return esdd;
	}

	public void setEsdd(Double esdd) {
		this.esdd = esdd;
	}

	public boolean isEsddWarningData() {
		return isEsddWarningData;
	}

	public void setEsddWarningData(boolean isEsddWarningData) {
		this.isEsddWarningData = isEsddWarningData;
	}

	public Double getNsdd() {
		return nsdd;
	}

	public void setNsdd(Double nsdd) {
		this.nsdd = nsdd;
	}

	public boolean isNsddWarningData() {
		return isNsddWarningData;
	}

	public void setNsddWarningData(boolean isNsddWarningData) {
		this.isNsddWarningData = isNsddWarningData;
	}

	public Double getDailyMaxTemperature() {
		return dailyMaxTemperature;
	}

	public void setDailyMaxTemperature(Double dailyMaxTemperature) {
		this.dailyMaxTemperature = dailyMaxTemperature;
	}

	public boolean isDailyMaxTemperatureWarningData() {
		return isDailyMaxTemperatureWarningData;
	}

	public void setDailyMaxTemperatureWarningData(
			boolean isDailyMaxTemperatureWarningData) {
		this.isDailyMaxTemperatureWarningData = isDailyMaxTemperatureWarningData;
	}

	public Double getDailyMinTemperature() {
		return dailyMinTemperature;
	}

	public void setDailyMinTemperature(Double dailyMinTemperature) {
		this.dailyMinTemperature = dailyMinTemperature;
	}

	public boolean isDailyMinTemperatureWarningData() {
		return isDailyMinTemperatureWarningData;
	}

	public void setDailyMinTemperatureWarningData(
			boolean isDailyMinTemperatureWarningData) {
		this.isDailyMinTemperatureWarningData = isDailyMinTemperatureWarningData;
	}

	public Double getDailyMaxHumidity() {
		return dailyMaxHumidity;
	}

	public void setDailyMaxHumidity(Double dailyMaxHumidity) {
		this.dailyMaxHumidity = dailyMaxHumidity;
	}

	public boolean isDailyMaxHumidityWarningData() {
		return isDailyMaxHumidityWarningData;
	}

	public void setDailyMaxHumidityWarningData(boolean isDailyMaxHumidityWarningData) {
		this.isDailyMaxHumidityWarningData = isDailyMaxHumidityWarningData;
	}

	public Double getDailyMinHumidity() {
		return dailyMinHumidity;
	}

	public void setDailyMinHumidity(Double dailyMinHumidity) {
		this.dailyMinHumidity = dailyMinHumidity;
	}

	public boolean isDailyMinHumidityWarningData() {
		return isDailyMinHumidityWarningData;
	}

	public void setDailyMinHumidityWarningData(boolean isDailyMinHumidityWarningData) {
		this.isDailyMinHumidityWarningData = isDailyMinHumidityWarningData;
	}	

	 

 
	/** 备用1 **/
	//private String reserve1;

	/** 备用2 **/
	//private String reserve2;


}
