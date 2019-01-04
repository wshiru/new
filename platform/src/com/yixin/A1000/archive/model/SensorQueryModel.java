/*
 * Project platform
 *
 * Class SensorQueryModel.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-22 上午11:15:57
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.model;

import java.util.Date;

import com.yixin.framework.base.model.db.DateQueryMode;
import com.yixin.framework.base.model.db.NumberQueryMode;
import com.yixin.framework.base.model.db.ObjectQueryMode;
import com.yixin.framework.base.model.db.StringQueryMode;

/**
 * 监测装置的查询模型。定义查询时所需要的条件以及比较机制。
 * 
 * @version v1.0.0
 * @author 
 */
public class SensorQueryModel {

	/** 所在杆塔。如果为null，则表示该项不作限制。否则采用towerQueryMode指定的比较机制。默认为null。 */
	private Tower tower;

	/** 所在杆塔的比较机制 */
	private ObjectQueryMode towerQueryMode;

	/** 所属监测类型。如果为null，则表示该项不作限制。否则采用sensorTypeQueryMode指定的比较机制。默认为null。 */
	private SensorType sensorType;

	/** 所属监测类型的比较机制 */
	private ObjectQueryMode sensorTypeQueryMode;

	/** 采集监测代理。如果为null，则表示该项不作限制。否则采用cmaInfoQueryMode指定的比较机制。默认为null。 */
	private CmaInfo cmaInfo;

	/** 采集监测代理的比较机制 */
	private ObjectQueryMode cmaInfoQueryMode;

	/** 监测装置编码。如果为null，则表示该项不作限制。否则采用sensorCodeQueryMode指定的比较机制。默认为null。 */
	private String sensorCode;

	/** 监测装置编码的比较机制 */
	private StringQueryMode sensorCodeQueryMode;

	/** 被监测装置编码。如果为null，则表示该项不作限制。否则采用bySensorCodeQueryMode指定的比较机制。默认为null。 */
	private String bySensorCode;

	/** 被监测装置编码的比较机制 */
	private StringQueryMode bySensorCodeQueryMode;

	/** 生产厂商。如果为null，则表示该项不作限制。否则采用manuFacturerQueryMode指定的比较机制。默认为null。 */
	private String manuFacturer;

	/** 生产厂商的比较机制 */
	private StringQueryMode manuFacturerQueryMode;

	/** 出厂日期。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。 */
	private Date productionDate;

	/**
	 * 出厂日期的比较机制。<b>注意：</b>当使用 <code>DateQueryMode.BETWEEN</code> 或
	 * <code>DateQueryMode.NOTBETWEEN</code> 时，需要设置开始时间与结束时间
	 */
	private DateQueryMode productionDateQueryMode;

	/** 出厂编号。如果为null，则表示该项不作限制。否则采用identificationNumberQueryMode指定的比较机制。默认为null。 */
	private String identificationNumber;

	/** 出厂编号的比较机制 */
	private StringQueryMode identificationNumberQueryMode;

	/** 安装位置。如果为null，则表示该项不作限制。否则采用installSiteQueryMode指定的比较机制。默认为null。 */
	private String installSite;

	/** 安装位置的比较机制 */
	private StringQueryMode installSiteQueryMode;

	/** 状态。如果为null，则表示该项不作限制。否则采用stateQueryMode指定的比较机制。默认为null。 */
	private Integer state;
	
	/** 状态的比较机制 */
	private NumberQueryMode stateQueryMode;

	/** 描述。如果为null，则表示该项不作限制。否则采用sensorDescQueryMode指定的比较机制。默认为null。 */
	private String sensorDesc;
	/** 描述的比较机制 */
	private StringQueryMode sensorDescQueryMode;

	/**
	 * 获取 所在杆塔。如果为null，则表示该项不作限制。否则采用towerQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 所在杆塔。如果为null，则表示该项不作限制。否则采用towerQueryMode指定的比较机制。默认为null。
	 */
	public Tower getTower() {
		return this.tower;
	}

	/**
	 * 设置 所在杆塔。如果为null，则表示该项不作限制。否则采用towerQueryMode指定的比较机制。默认为null。
	 * 
	 * @param tower
	 *            所在杆塔。如果为null，则表示该项不作限制。否则采用towerQueryMode指定的比较机制。默认为null。
	 */
	public void setTower(Tower tower) {
		this.tower = tower;
	}

	/**
	 * 获取 所在杆塔的比较机制
	 * 
	 * @return 所在杆塔的比较机制
	 */
	public ObjectQueryMode getTowerQueryMode() {
		return this.towerQueryMode;
	}

	/**
	 * 设置 所在杆塔的比较机制
	 * 
	 * @param towerQueryMode
	 *            所在杆塔的比较机制
	 */
	public void setTowerQueryMode(ObjectQueryMode towerQueryMode) {
		this.towerQueryMode = towerQueryMode;
	}

	/**
	 * 获取 所属监测类型。如果为null，则表示该项不作限制。否则采用sensorTypeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 所属监测类型。如果为null，则表示该项不作限制。否则采用sensorTypeQueryMode指定的比较机制。默认为null。
	 */
	public SensorType getSensorType() {
		return this.sensorType;
	}

	/**
	 * 设置 所属监测类型。如果为null，则表示该项不作限制。否则采用sensorTypeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param sensorType
	 *            所属监测类型。如果为null，则表示该项不作限制。否则采用sensorTypeQueryMode指定的比较机制。
	 *            默认为null。
	 */
	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}

	/**
	 * 获取 所属监测类型的比较机制
	 * 
	 * @return 所属监测类型的比较机制
	 */
	public ObjectQueryMode getSensorTypeQueryMode() {
		return this.sensorTypeQueryMode;
	}

	/**
	 * 设置 所属监测类型的比较机制
	 * 
	 * @param sensorTypeQueryMode
	 *            所属监测类型的比较机制
	 */
	public void setSensorTypeQueryMode(ObjectQueryMode sensorTypeQueryMode) {
		this.sensorTypeQueryMode = sensorTypeQueryMode;
	}

	/**
	 * 获取 采集监测代理。如果为null，则表示该项不作限制。否则采用cmaInfoQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 采集监测代理。如果为null，则表示该项不作限制。否则采用cmaInfoQueryMode指定的比较机制。默认为null。
	 */
	public CmaInfo getCmaInfo() {
		return this.cmaInfo;
	}

	/**
	 * 设置 采集监测代理。如果为null，则表示该项不作限制。否则采用cmaInfoQueryMode指定的比较机制。默认为null。
	 * 
	 * @param cmaInfo
	 *            采集监测代理。如果为null，则表示该项不作限制。否则采用cmaInfoQueryMode指定的比较机制。默认为null。
	 */
	public void setCmaInfo(CmaInfo cmaInfo) {
		this.cmaInfo = cmaInfo;
	}

	/**
	 * 获取 采集监测代理的比较机制
	 * 
	 * @return 采集监测代理的比较机制
	 */
	public ObjectQueryMode getCmaInfoQueryMode() {
		return this.cmaInfoQueryMode;
	}

	/**
	 * 设置 采集监测代理的比较机制
	 * 
	 * @param cmaInfoQueryMode
	 *            采集监测代理的比较机制
	 */
	public void setCmaInfoQueryMode(ObjectQueryMode cmaInfoQueryMode) {
		this.cmaInfoQueryMode = cmaInfoQueryMode;
	}

	/**
	 * 获取 监测装置编码。如果为null，则表示该项不作限制。否则采用sensorCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 监测装置编码。如果为null，则表示该项不作限制。否则采用sensorCodeQueryMode指定的比较机制。默认为null。
	 */
	public String getSensorCode() {
		return this.sensorCode;
	}

	/**
	 * 设置 监测装置编码。如果为null，则表示该项不作限制。否则采用sensorCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param sensorCode
	 *            监测装置编码。如果为null，则表示该项不作限制。否则采用sensorCodeQueryMode指定的比较机制。
	 *            默认为null。
	 */
	public void setSensorCode(String sensorCode) {
		this.sensorCode = sensorCode;
	}

	/**
	 * 获取 监测装置编码的比较机制
	 * 
	 * @return 监测装置编码的比较机制
	 */
	public StringQueryMode getSensorCodeQueryMode() {
		return this.sensorCodeQueryMode;
	}

	/**
	 * 设置 监测装置编码的比较机制
	 * 
	 * @param sensorCodeQueryMode
	 *            监测装置编码的比较机制
	 */
	public void setSensorCodeQueryMode(StringQueryMode sensorCodeQueryMode) {
		this.sensorCodeQueryMode = sensorCodeQueryMode;
	}

	/**
	 * 获取 被监测装置编码。如果为null，则表示该项不作限制。否则采用bySensorCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 
	 *         被监测装置编码。如果为null，则表示该项不作限制。否则采用bySensorCodeQueryMode指定的比较机制。默认为null
	 *         。
	 */
	public String getBySensorCode() {
		return this.bySensorCode;
	}

	/**
	 * 设置 被监测装置编码。如果为null，则表示该项不作限制。否则采用bySensorCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param bySensorCode
	 *            被监测装置编码。如果为null，则表示该项不作限制。否则采用bySensorCodeQueryMode指定的比较机制。
	 *            默认为null。
	 */
	public void setBySensorCode(String bySensorCode) {
		this.bySensorCode = bySensorCode;
	}

	/**
	 * 获取 被监测装置编码的比较机制
	 * 
	 * @return 被监测装置编码的比较机制
	 */
	public StringQueryMode getBySensorCodeQueryMode() {
		return this.bySensorCodeQueryMode;
	}

	/**
	 * 设置 被监测装置编码的比较机制
	 * 
	 * @param bySensorCodeQueryMode
	 *            被监测装置编码的比较机制
	 */
	public void setBySensorCodeQueryMode(StringQueryMode bySensorCodeQueryMode) {
		this.bySensorCodeQueryMode = bySensorCodeQueryMode;
	}

	/**
	 * 获取 生产厂商。如果为null，则表示该项不作限制。否则采用manuFacturerQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 生产厂商。如果为null，则表示该项不作限制。否则采用manuFacturerQueryMode指定的比较机制。默认为null。
	 */
	public String getManuFacturer() {
		return this.manuFacturer;
	}

	/**
	 * 设置 生产厂商。如果为null，则表示该项不作限制。否则采用manuFacturerQueryMode指定的比较机制。默认为null。
	 * 
	 * @param manuFacturer
	 *            生产厂商。如果为null，则表示该项不作限制。否则采用manuFacturerQueryMode指定的比较机制。
	 *            默认为null。
	 */
	public void setManuFacturer(String manuFacturer) {
		this.manuFacturer = manuFacturer;
	}

	/**
	 * 获取 生产厂商的比较机制
	 * 
	 * @return 生产厂商的比较机制
	 */
	public StringQueryMode getManuFacturerQueryMode() {
		return this.manuFacturerQueryMode;
	}

	/**
	 * 设置 生产厂商的比较机制
	 * 
	 * @param manuFacturerQueryMode
	 *            生产厂商的比较机制
	 */
	public void setManuFacturerQueryMode(StringQueryMode manuFacturerQueryMode) {
		this.manuFacturerQueryMode = manuFacturerQueryMode;
	}

	/**
	 * 获取 出厂日期。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 出厂日期。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 */
	public Date getProductionDate() {
		return this.productionDate;
	}

	/**
	 * 设置 出厂日期。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param productionDate
	 *            出厂日期。如果为null，则表示该项不作限制。否则采用lineCodeQueryMode指定的比较机制。默认为null。
	 */
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	/**
	 * 获取 出厂日期的比较机制。注意：当使用 DateQueryMode.BETWEEN 或 DateQueryMode.NOTBETWEEN
	 * 时，需要设置开始时间与结束时间
	 * 
	 * @return 出厂日期的比较机制。注意：当使用 DateQueryMode.BETWEEN 或 DateQueryMode.NOTBETWEEN
	 *         时，需要设置开始时间与结束时间
	 */
	public DateQueryMode getProductionDateQueryMode() {
		return this.productionDateQueryMode;
	}

	/**
	 * 设置 出厂日期的比较机制。注意：当使用 DateQueryMode.BETWEEN 或 DateQueryMode.NOTBETWEEN
	 * 时，需要设置开始时间与结束时间
	 * 
	 * @param productionDateQueryMode
	 *            出厂日期的比较机制。注意：当使用 DateQueryMode.BETWEEN 或
	 *            DateQueryMode.NOTBETWEEN 时，需要设置开始时间与结束时间
	 */
	public void setProductionDateQueryMode(DateQueryMode productionDateQueryMode) {
		this.productionDateQueryMode = productionDateQueryMode;
	}

	/**
	 * 获取
	 * 出厂编号。如果为null，则表示该项不作限制。否则采用identificationNumberQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 
	 *         出厂编号。如果为null，则表示该项不作限制。否则采用identificationNumberQueryMode指定的比较机制。默认为null
	 *         。
	 */
	public String getIdentificationNumber() {
		return this.identificationNumber;
	}

	/**
	 * 设置
	 * 出厂编号。如果为null，则表示该项不作限制。否则采用identificationNumberQueryMode指定的比较机制。默认为null。
	 * 
	 * @param identificationNumber
	 *            出厂编号。如果为null，则表示该项不作限制。
	 *            否则采用identificationNumberQueryMode指定的比较机制。默认为null。
	 */
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	/**
	 * 获取 出厂编号的比较机制
	 * 
	 * @return 出厂编号的比较机制
	 */
	public StringQueryMode getIdentificationNumberQueryMode() {
		return this.identificationNumberQueryMode;
	}

	/**
	 * 设置 出厂编号的比较机制
	 * 
	 * @param identificationNumberQueryMode
	 *            出厂编号的比较机制
	 */
	public void setIdentificationNumberQueryMode(StringQueryMode identificationNumberQueryMode) {
		this.identificationNumberQueryMode = identificationNumberQueryMode;
	}

	/**
	 * 获取 安装位置。如果为null，则表示该项不作限制。否则采用installSiteQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 安装位置。如果为null，则表示该项不作限制。否则采用installSiteQueryMode指定的比较机制。默认为null。
	 */
	public String getInstallSite() {
		return this.installSite;
	}

	/**
	 * 设置 安装位置。如果为null，则表示该项不作限制。否则采用installSiteQueryMode指定的比较机制。默认为null。
	 * 
	 * @param installSite
	 *            安装位置。如果为null，则表示该项不作限制。否则采用installSiteQueryMode指定的比较机制。默认为null
	 *            。
	 */
	public void setInstallSite(String installSite) {
		this.installSite = installSite;
	}

	/**
	 * 获取 安装位置的比较机制
	 * 
	 * @return 安装位置的比较机制
	 */
	public StringQueryMode getInstallSiteQueryMode() {
		return this.installSiteQueryMode;
	}

	/**
	 * 设置 安装位置的比较机制
	 * 
	 * @param installSiteQueryMode
	 *            安装位置的比较机制
	 */
	public void setInstallSiteQueryMode(StringQueryMode installSiteQueryMode) {
		this.installSiteQueryMode = installSiteQueryMode;
	}

	/**
	 * 获取 状态。如果为null，则表示该项不作限制。否则采用stateQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 状态。如果为null，则表示该项不作限制。否则采用stateQueryMode指定的比较机制。默认为null。
	 */
	public Integer getState() {
		return this.state;
	}

	/**
	 * 设置 状态。如果为null，则表示该项不作限制。否则采用stateQueryMode指定的比较机制。默认为null。
	 * 
	 * @param state
	 *            状态。如果为null，则表示该项不作限制。否则采用stateQueryMode指定的比较机制。默认为null。
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 获取 状态的比较机制
	 * 
	 * @return 状态的比较机制
	 */
	public NumberQueryMode getStateQueryMode() {
		return this.stateQueryMode;
	}

	/**
	 * 设置 状态的比较机制
	 * 
	 * @param stateQueryMode
	 *            状态的比较机制
	 */
	public void setStateQueryMode(NumberQueryMode stateQueryMode) {
		this.stateQueryMode = stateQueryMode;
	}

	/**
	 * 获取 描述。如果为null，则表示该项不作限制。否则采用sensorDescQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 描述。如果为null，则表示该项不作限制。否则采用sensorDescQueryMode指定的比较机制。默认为null。
	 */
	public String getSensorDesc() {
		return this.sensorDesc;
	}

	/**
	 * 设置 描述。如果为null，则表示该项不作限制。否则采用sensorDescQueryMode指定的比较机制。默认为null。
	 * 
	 * @param sensorDesc
	 *            描述。如果为null，则表示该项不作限制。否则采用sensorDescQueryMode指定的比较机制。默认为null。
	 */
	public void setSensorDesc(String sensorDesc) {
		this.sensorDesc = sensorDesc;
	}

	/**
	 * 获取 描述的比较机制
	 * 
	 * @return 描述的比较机制
	 */
	public StringQueryMode getSensorDescQueryMode() {
		return this.sensorDescQueryMode;
	}

	/**
	 * 设置 描述的比较机制
	 * 
	 * @param sensorDescQueryMode
	 *            描述的比较机制
	 */
	public void setSensorDescQueryMode(StringQueryMode sensorDescQueryMode) {
		this.sensorDescQueryMode = sensorDescQueryMode;
	}
}
