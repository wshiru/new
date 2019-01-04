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
 * ModifiedBy 

 * ModifyAt 2011-07-06 15:40

 * ModifyDescription 添加入库时间

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */

package com.yixin.A1000.setting.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
 * 监测装置心跳信息模型
 * @author 梁立全

 *
 */
@Entity
@Table(name="T_sensorHeartbeatInfo")
public class SensorHeartbeatInfo {
	
	/** 监测装置心跳信息ID */

    @Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "sensorHeartbeatInfoId")
	private String sensorHeartbeatInfoId;
    
	/** 所属监测装置 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "sensorId")
	private Sensor sensor = new Sensor();
	
	
	/** 总工作时间 **/
	private Double totleWorkingTime;
	/** 本次连续工作时间 **/
	private Double workingTime;
	/**  电压 **/
	private  Double batteryvoltage;
	
	/** 工作温度 **/
	private  Double operationtemperature;
	
	/**浮充状态  0：充电 1：放电 **/
	private  Integer floatingcharge;
	
	/** 心跳状态   0：中断  1：正常  **/
	private  Integer status;
	
	/** 入库时间 */
	private Date createTime;	
    
	/**
	 * 返回监测装置心跳信息ID
	 * @return
	 */
    public String getSensorHeartbeatInfoId() {
		return sensorHeartbeatInfoId;
	}

    /**
     * 设置监测装置心跳信息ID
     * @param sensorHeartbeatInfoId
     */
	public void setSensorHeartbeatInfoId(String sensorHeartbeatInfoId) {
		this.sensorHeartbeatInfoId = sensorHeartbeatInfoId;
	}

	/**
	 * 返回所属监测装置

	 * @return
	 */
	public Sensor getSensor() {
		return sensor;
	}

	/**
	 * 设置所属监测装置

	 * @param sensor
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	
	/**
	 * 返回CMA电压
	 * @return
	 */
	public Double getBatteryvoltage() {
		return batteryvoltage;
	}

	/**
	 * 设置CMA电压
	 * @param batteryvoltage
	 */
	public void setBatteryvoltage(Double batteryvoltage) {
		this.batteryvoltage = batteryvoltage;
	}

	/**
	 * 返回工作温度
	 * @return
	 */
	public Double getOperationtemperature() {
		return operationtemperature;
	}

	/**
	 * 设置工作温度
	 * @param operationtemperature
	 */
	public void setOperationtemperature(Double operationtemperature) {
		this.operationtemperature = operationtemperature;
	}

	/**
	 * 返回浮充状态

	 * @return
	 */
	public Integer getFloatingcharge() {
		return floatingcharge;
	}

	/**
	 * 设置浮充状态

	 * @param floatingcharge
	 */
	public void setFloatingcharge(Integer floatingcharge) {
		this.floatingcharge = floatingcharge;
	}

	/**
	 * 返回心跳状态

	 * @return
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置心跳状态

	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @param 设置 总工作时间
	 */
	public void setTotleWorkingTime(Double totleWorkingTime) {
		this.totleWorkingTime = totleWorkingTime;
	}

	/**
	 * @return the 总工作时间
	 */
	public Double getTotleWorkingTime() {
		return totleWorkingTime;
	}

	/**
	 * @param 本次连续工作时间
	 */
	public void setWorkingTime(Double workingTime) {
		this.workingTime = workingTime;
	}

	/**
	 * @return 本次连续工作时间
	 */
	public Double getWorkingTime() {
		return workingTime;
	}	
	
	
}
