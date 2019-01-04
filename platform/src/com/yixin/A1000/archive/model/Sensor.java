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
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 监测装置
 * 
 * @version v1.0.0
 * @author 
 */
@Entity
@Table(name = "T_Sensor")
public class Sensor implements Serializable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = 7414201779541830591L;

	/** 监测装置Id */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String sensorId;

	/** 所在杆塔 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "towerId")
	private Tower tower = new Tower();

	/** 所属监测类型 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "sensorTypeId")
	private SensorType sensorType = new SensorType();

	/** 采集监测代理 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "cmaInfoId")
	private CmaInfo cmaInfo = new CmaInfo();

	/** 监测装置编码 */
	@OrderBy("sensorCode ASC")
	private String sensorCode;

	/** 被监测装置编码 */
	private String bySensorCode;

	/** 生产厂商 */
	private String manuFacturer;

	/** 出厂日期 */
	private Date productionDate;

	/** 出厂编号 */
	private String identificationNumber;

	/** 安装位置 */
	private String installSite;

	/** 状态。通过CmaInfo.State枚举的getValue()访求来取得值。 */
	private Integer state;

	/** 描述 */
	private String sensorDesc;

	/**
	 * 获取 监测装置Id
	 * 
	 * @return 监测装置Id
	 */
	public String getSensorId() {
		return this.sensorId;
	}

	/**
	 * 设置 监测装置Id
	 * 
	 * @param sensorId
	 *            监测装置Id
	 */
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	/**
	 * 获取 所在杆塔
	 * 
	 * @return 所在杆塔
	 */
	public Tower getTower() {
		return this.tower;
	}

	/**
	 * 设置 所在杆塔
	 * 
	 * @param tower
	 *            所在杆塔
	 */
	public void setTower(Tower tower) {
		this.tower = tower;
	}

	/**
	 * 获取 所属监测类型
	 * 
	 * @return 所属监测类型
	 */
	public SensorType getSensorType() {
		return this.sensorType;
	}

	/**
	 * 设置 所属监测类型
	 * 
	 * @param sensorType
	 *            所属监测类型
	 */
	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}

	/**
	 * 获取 采集监测代理
	 * 
	 * @return 采集监测代理
	 */
	public CmaInfo getCmaInfo() {
		return this.cmaInfo;
	}

	/**
	 * 设置 采集监测代理
	 * 
	 * @param cmaInfo
	 *            采集监测代理
	 */
	public void setCmaInfo(CmaInfo cmaInfo) {
		this.cmaInfo = cmaInfo;
	}

	/**
	 * 获取 监测装置编码
	 * 
	 * @return 监测装置编码
	 */
	public String getSensorCode() {
		return this.sensorCode;
	}

	/**
	 * 设置 监测装置编码
	 * 
	 * @param sensorCode
	 *            监测装置编码
	 */
	public void setSensorCode(String sensorCode) {
		this.sensorCode = sensorCode;
	}

	/**
	 * 获取 被监测装置编码
	 * 
	 * @return 被监测装置编码
	 */
	public String getBySensorCode() {
		return this.bySensorCode;
	}

	/**
	 * 设置 被监测装置编码
	 * 
	 * @param bySensorCode
	 *            被监测装置编码
	 */
	public void setBySensorCode(String bySensorCode) {
		this.bySensorCode = bySensorCode;
	}

	/**
	 * 获取 生产厂商
	 * 
	 * @return 生产厂商
	 */
	public String getManuFacturer() {
		return this.manuFacturer;
	}

	/**
	 * 设置 生产厂商
	 * 
	 * @param manuFacturer
	 *            生产厂商
	 */
	public void setManuFacturer(String manuFacturer) {
		this.manuFacturer = manuFacturer;
	}

	/**
	 * 获取 出厂日期
	 * 
	 * @return 出厂日期
	 */
	public Date getProductionDate() {
		return this.productionDate;
	}

	/**
	 * 设置 出厂日期
	 * 
	 * @param productionDate
	 *            出厂日期
	 */
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	/**
	 * 获取 出厂编号
	 * 
	 * @return 出厂编号
	 */
	public String getIdentificationNumber() {
		return this.identificationNumber;
	}

	/**
	 * 设置 出厂编号
	 * 
	 * @param identificationNumber
	 *            出厂编号
	 */
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	/**
	 * 获取 安装位置
	 * 
	 * @return 安装位置
	 */
	public String getInstallSite() {
		return this.installSite;
	}

	/**
	 * 设置 安装位置
	 * 
	 * @param installSite
	 *            安装位置
	 */
	public void setInstallSite(String installSite) {
		this.installSite = installSite;
	}

	/**
	 * 获取 状态。通过CmaInfo.State枚举的getValue()访求来取得值。
	 * 
	 * @return 状态。通过CmaInfo.State枚举的getValue()访求来取得值。
	 */
	public Integer getState() {
		return this.state;
	}

	/**
	 * 设置 状态。通过CmaInfo.State枚举的getValue()访求来取得值。
	 * 
	 * @param state
	 *            状态。通过CmaInfo.State枚举的getValue()访求来取得值。
	 */
	public void setState(Integer state) {
		this.state = state;
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

	/**
	 * 状态。对应的int值通过调用getValue*(获得。
	 * 
	 * @version v1.0.0
	 * @author 
	 */
	public enum State {

		/** 状态：停用 */
		DISABLE(0),

		/** 状态：启用 */
		ENABLE(1);

		/** 对应的int值 */
		private int value;

		/**
		 * 构造方法
		 * 
		 * @param value
		 */
		State(int value) {
			this.value = value;
		}

		/**
		 * 获取对应的int值
		 * 
		 * @return value 对应的int值
		 */
		public int getValue() {
			return this.value;
		}
	}
}
