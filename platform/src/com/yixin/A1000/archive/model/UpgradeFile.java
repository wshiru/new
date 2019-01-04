/*
 * Project platform
 *
 * Class UpgradeFile.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-13 下午02:33:09
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

import com.yixin.framework.system.model.User;

/**
 * 升级文件
 * 
 * @version v1.0.0
 * @author 
 */
@Entity
@Table(name = "T_UpgradeFile")
public class UpgradeFile implements Serializable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -6678667433283635892L;

	/** 升级文件Id */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String upgradeFileId;

	/** 监测类型 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "sensorTypeId")
	private SensorType sensorType = new SensorType();

	/** 文件路径。相对WEB根目录。如“/upload/upgrade/” */
	private String path;

	/** 文件名称。不包括后缀。如“weather-1.0.2” */
	private String fileName;

	/** 文件后缀。如“.bin” */
	private String fileExtension;

	/** 版本号。如“1.0.2” */
	private String version;

	/** 版本发布时间 */
	private Date releaseTime;

	/** 上传时间 */
	private Date uploadTime;

	/** 上传用户 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name = "userId")
	private User user = new User();

	/** 描述 */
	private String upgradeFileDesc;

	/**
	 * 获取 升级文件Id
	 * 
	 * @return 升级文件Id
	 */
	public String getUpgradeFileId() {
		return this.upgradeFileId;
	}

	/**
	 * 设置 升级文件Id
	 * 
	 * @param upgradeFileId
	 *            升级文件Id
	 */
	public void setUpgradeFileId(String upgradeFileId) {
		this.upgradeFileId = upgradeFileId;
	}

	/**
	 * 获取 监测类型
	 * 
	 * @return 监测类型
	 */
	public SensorType getSensorType() {
		return this.sensorType;
	}

	/**
	 * 设置 监测类型
	 * 
	 * @param sensorType
	 *            监测类型
	 */
	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}

	/**
	 * 获取 文件路径。相对WEB根目录。如“/upload/upgrade/”
	 * 
	 * @return 文件路径。相对WEB根目录。如“/upload/upgrade/”
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * 设置 文件路径。相对WEB根目录。如“/upload/upgrade/”
	 * 
	 * @param path
	 *            文件路径。相对WEB根目录。如“/upload/upgrade/”
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 获取 文件名称。不包括后缀。如“weather-1.0.2”
	 * 
	 * @return 文件名称。不包括后缀。如“weather-1.0.2”
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * 设置 文件名称。不包括后缀。如“weather-1.0.2”
	 * 
	 * @param fileName
	 *            文件名称。不包括后缀。如“weather-1.0.2”
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 获取 文件后缀。如“.bin”
	 * 
	 * @return 文件后缀。如“.bin”
	 */
	public String getFileExtension() {
		return this.fileExtension;
	}

	/**
	 * 设置 文件后缀。如“.bin”
	 * 
	 * @param fileExtension
	 *            文件后缀。如“.bin”
	 */
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
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
	 * 获取 上传时间
	 * 
	 * @return 上传时间
	 */
	public Date getUploadTime() {
		return this.uploadTime;
	}

	/**
	 * 设置 上传时间
	 * 
	 * @param uploadTime
	 *            上传时间
	 */
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	/**
	 * 获取 上传用户
	 * 
	 * @return 上传用户
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * 设置 上传用户
	 * 
	 * @param user
	 *            上传用户
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 获取 描述
	 * 
	 * @return 描述
	 */
	public String getUpgradeFileDesc() {
		return this.upgradeFileDesc;
	}

	/**
	 * 设置 描述
	 * 
	 * @param upgradeFileDesc
	 *            描述
	 */
	public void setUpgradeFileDesc(String upgradeFileDesc) {
		this.upgradeFileDesc = upgradeFileDesc;
	}
}
