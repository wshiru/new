/*
 * Project platform
 *
 * Class HistoryVersion.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-20 下午03:07:51
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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 历史版本
 * 
 * @version v1.0.0
 * @author 
 */
@Entity
@Table(name = "T_HistoryVersion")
public class HistoryVersion implements Serializable {

	/** 类的序列化版本ID} */
	private static final long serialVersionUID = -7062087071577897917L;

	/** 历史版本Id */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String historyVersionId;

	/** 监测代理编码 */
	private String cmaCode;

	/** 监测装置编码 */
	private String sensorCode;

	/** 升级文件 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "upgradeFileId")
	private UpgradeFile upgradeFile;

	/** 文件名称。包括后缀。如“weather-1.0.2.bin */
	private String fileName;

	/** 版本号。如“1.0.2” */
	private String version;

	/** 版本发布时间 */
	private Date releaseTime;

	/** 更新时间 */
	private Date updateTime;

	/** 是否是最新版本。0-非最新版本；1-最新版本 */
	private Integer isLatestVersion;

	/**
	 * 获取 历史版本Id
	 * 
	 * @return 历史版本Id
	 */
	public String getHistoryVersionId() {
		return this.historyVersionId;
	}

	/**
	 * 设置 历史版本Id
	 * 
	 * @param historyVersionId
	 *            历史版本Id
	 */
	public void setHistoryVersionId(String historyVersionId) {
		this.historyVersionId = historyVersionId;
	}

	/**
	 * 获取 监测代理编码
	 * 
	 * @return 监测代理编码
	 */
	public String getCmaCode() {
		return this.cmaCode;
	}

	/**
	 * 设置 监测代理编码
	 * 
	 * @param cmaCode
	 *            监测代理编码
	 */
	public void setCmaCode(String cmaCode) {
		this.cmaCode = cmaCode;
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
	 * 获取 升级文件
	 * 
	 * @return 升级文件
	 */
	public UpgradeFile getUpgradeFile() {
		return this.upgradeFile;
	}

	/**
	 * 设置 升级文件
	 * 
	 * @param upgradeFile
	 *            升级文件
	 */
	public void setUpgradeFile(UpgradeFile upgradeFile) {
		this.upgradeFile = upgradeFile;
	}

	/**
	 * 获取 文件名称。包括后缀。如“weather-1.0.2.bin
	 * 
	 * @return 文件名称。包括后缀。如“weather-1.0.2.bin
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * 设置 文件名称。包括后缀。如“weather-1.0.2.bin
	 * 
	 * @param fileName
	 *            文件名称。包括后缀。如“weather-1.0.2.bin
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 获取 版本号。如“1.0.2”
	 * 
	 * @return 版本号。如“1.0.2”
	 */
	public String getVersion() {
		return this.version;
	}

	/**
	 * 设置 版本号。如“1.0.2”
	 * 
	 * @param version
	 *            版本号。如“1.0.2”
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * 获取 版本发布时间
	 * 
	 * @return 版本发布时间
	 */
	public Date getReleaseTime() {
		return this.releaseTime;
	}

	/**
	 * 设置 版本发布时间
	 * 
	 * @param releaseTime
	 *            版本发布时间
	 */
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	/**
	 * 获取 更新时间
	 * 
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 设置 更新时间
	 * 
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取 是否是最新版本。0-非最新版本；1-最新版本
	 * 
	 * @return 是否是最新版本。0-非最新版本；1-最新版本
	 */
	public Integer getIsLatestVersion() {
		return this.isLatestVersion;
	}

	/**
	 * 设置 是否是最新版本。0-非最新版本；1-最新版本
	 * 
	 * @param isLatestVersion
	 *            是否是最新版本。0-非最新版本；1-最新版本
	 */
	public void setIsLatestVersion(Integer isLatestVersion) {
		this.isLatestVersion = isLatestVersion;
	}
}
