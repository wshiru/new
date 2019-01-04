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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.yixin.A1000.archive.model.CmaInfo;


/**
 * 监测代理心跳信息模型
 * @author 梁立全

 *
 */
@Entity
@Table(name="T_CmaHeartbeatInfo")
public class CmaHeartbeatInfo {
	
	/** 监测代理心跳信息ID */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String cmaHeartbeatInfoId;

	/** 所属监测代理 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "cmaInfoId")
	private CmaInfo cmaInfo = new CmaInfo();
	
	/** IP地址 **/
	private String ipAddress;
	
	/** 当前时间 **/
	private Date curtime;
	
	/**  电压 **/
	private  Double batteryvoltage;
	
	/** 工作温度 **/
	private  Double operationtemperature;
	
	/**浮充状态  0：充电 1：放电 **/
	private  Integer floatingcharge;
	
	/** 入库时间 */
	private Date createTime;
	
	/**
	 * 反回CMA心跳信息Id
	 * @return
	 */
	public String getCmaHeartbeatInfoId() {
		return cmaHeartbeatInfoId;
	}

	/**
	 * 设置CMA心跳信息Id
	 * @param cmaHeartbeatInfoId
	 */
	public void setCmaHeartbeatInfoId(String cmaHeartbeatInfoId) {
		this.cmaHeartbeatInfoId = cmaHeartbeatInfoId;
	}

	/**
	 * 返回所属CMA
	 * @return
	 */
	public CmaInfo getCmaInfo() {
		return cmaInfo;
	}

	/**
	 * 设置所属CMA
	 * @param cmaInfo
	 */
	public void setCmaInfo(CmaInfo cmaInfo) {
		this.cmaInfo = cmaInfo;
	}
	
    /**
     * 返回IP地址
     * @return
     */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * 设置IP地址
	 * @param ipAddres
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * 返回CMA当前时间
	 * @return
	 */
	public Date getCurtime() {
		return curtime;
	}

	/**
	 * 设置 CMA当前时间
	 * @param curtime
	 */
	public void setCurtime(Date curtime) {
		this.curtime = curtime;
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
		
}
