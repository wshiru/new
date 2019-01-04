/*
 * Project platform
 *
 * Class UpgradeFileQueryModel.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-13 下午02:42:51
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
import com.yixin.framework.base.model.db.ObjectQueryMode;
import com.yixin.framework.base.model.db.StringQueryMode;
import com.yixin.framework.system.model.User;

/**
 * 升级文件的查询模型。定义查询时所需要的条件以及比较机制。
 * 
 * @version v1.0.0
 * @author 
 */
public class UpgradeFileQueryModel {

	/** 监测类型。如果为null，则表示该项不作限制。否则采用sensorTypeQueryMode指定的比较机制。默认为null。 */
	private SensorType sensorType;

	/** 监测类型的比较机制 */
	private ObjectQueryMode sensorTypeQueryMode;

	/** 文件路径。如果为null，则表示该项不作限制。否则采用pathQueryMode指定的比较机制。默认为null。 */
	private String path;

	/** 文件路径的比较机制 */
	private StringQueryMode pathQueryMode;

	/** 文件名称。如果为null，则表示该项不作限制。否则采用fileNameQueryMode指定的比较机制。默认为null。 */
	private String fileName;

	/** 文件名称的比较机制 */
	private StringQueryMode fileNameQueryMode;

	/** 文件后缀。如果为null，则表示该项不作限制。否则采用fileExtensionQueryMode指定的比较机制。默认为null。 */
	private String fileExtension;

	/** 文件后缀的比较机制 */
	private StringQueryMode fileExtensionQueryMode;

	/** 版本号。如果为null，则表示该项不作限制。否则采用versionQueryMode指定的比较机制。默认为null。 */
	private String version;

	/** 版本号的比较机制 */
	private StringQueryMode versionQueryMode;

	/** 版本发布时间。如果为null，则表示该项不作限制。否则采用releaseTimeQueryMode指定的比较机制。默认为null。 */
	private Date releaseTime;

	/** 版本发布时间的比较机制 */
	private DateQueryMode releaseTimeQueryMode;

	/** 上传时间。如果为null，则表示该项不作限制。否则采用uploadTimeQueryMode指定的比较机制。默认为null。 */
	private Date uploadTime;

	/** 上传时间的比较机制 */
	private DateQueryMode uploadTimeQueryMode;

	/** 上传用户。如果为null，则表示该项不作限制。否则采用userQueryMode指定的比较机制。默认为null。 */
	private User user;

	/** 上传用户的比较机制 */
	private ObjectQueryMode userQueryMode;

	/** 描述。如果为null，则表示该项不作限制。否则采用upgradeFileDescQueryMode指定的比较机制。默认为null。 */
	private String upgradeFileDesc;

	/** 描述的比较机制 */
	private StringQueryMode upgradeFileDescQueryMode;

	/**
	 * 获取 监测类型。如果为null，则表示该项不作限制。否则采用sensorTypeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 监测类型。如果为null，则表示该项不作限制。否则采用sensorTypeQueryMode指定的比较机制。默认为null。
	 */
	public SensorType getSensorType() {
		return this.sensorType;
	}

	/**
	 * 设置 监测类型。如果为null，则表示该项不作限制。否则采用sensorTypeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param sensorType
	 *            监测类型。如果为null，则表示该项不作限制。否则采用sensorTypeQueryMode指定的比较机制。默认为null。
	 */
	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}

	/**
	 * 获取 监测类型的比较机制
	 * 
	 * @return 监测类型的比较机制
	 */
	public ObjectQueryMode getSensorTypeQueryMode() {
		return this.sensorTypeQueryMode;
	}

	/**
	 * 设置 监测类型的比较机制
	 * 
	 * @param sensorTypeQueryMode
	 *            监测类型的比较机制
	 */
	public void setSensorTypeQueryMode(ObjectQueryMode sensorTypeQueryMode) {
		this.sensorTypeQueryMode = sensorTypeQueryMode;
	}

	/**
	 * 获取 文件路径。如果为null，则表示该项不作限制。否则采用pathQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 文件路径。如果为null，则表示该项不作限制。否则采用pathQueryMode指定的比较机制。默认为null。
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * 设置 文件路径。如果为null，则表示该项不作限制。否则采用pathQueryMode指定的比较机制。默认为null。
	 * 
	 * @param path
	 *            文件路径。如果为null，则表示该项不作限制。否则采用pathQueryMode指定的比较机制。默认为null。
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 获取 文件路径的比较机制
	 * 
	 * @return 文件路径的比较机制
	 */
	public StringQueryMode getPathQueryMode() {
		return this.pathQueryMode;
	}

	/**
	 * 设置 文件路径的比较机制
	 * 
	 * @param pathQueryMode
	 *            文件路径的比较机制
	 */
	public void setPathQueryMode(StringQueryMode pathQueryMode) {
		this.pathQueryMode = pathQueryMode;
	}

	/**
	 * 获取 文件名称。如果为null，则表示该项不作限制。否则采用fileNameQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 文件名称。如果为null，则表示该项不作限制。否则采用fileNameQueryMode指定的比较机制。默认为null。
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * 设置 文件名称。如果为null，则表示该项不作限制。否则采用fileNameQueryMode指定的比较机制。默认为null。
	 * 
	 * @param fileName
	 *            文件名称。如果为null，则表示该项不作限制。否则采用fileNameQueryMode指定的比较机制。默认为null。
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 获取 文件名称的比较机制
	 * 
	 * @return 文件名称的比较机制
	 */
	public StringQueryMode getFileNameQueryMode() {
		return this.fileNameQueryMode;
	}

	/**
	 * 设置 文件名称的比较机制
	 * 
	 * @param fileNameQueryMode
	 *            文件名称的比较机制
	 */
	public void setFileNameQueryMode(StringQueryMode fileNameQueryMode) {
		this.fileNameQueryMode = fileNameQueryMode;
	}

	/**
	 * 获取 文件后缀。如果为null，则表示该项不作限制。否则采用fileExtensionQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 文件后缀。如果为null，则表示该项不作限制。否则采用fileExtensionQueryMode指定的比较机制。默认为null。
	 */
	public String getFileExtension() {
		return this.fileExtension;
	}

	/**
	 * 设置 文件后缀。如果为null，则表示该项不作限制。否则采用fileExtensionQueryMode指定的比较机制。默认为null。
	 * 
	 * @param fileExtension
	 *            文件后缀。如果为null，则表示该项不作限制。否则采用fileExtensionQueryMode指定的比较机制。
	 *            默认为null。
	 */
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	/**
	 * 获取 文件后缀的比较机制
	 * 
	 * @return 文件后缀的比较机制
	 */
	public StringQueryMode getFileExtensionQueryMode() {
		return this.fileExtensionQueryMode;
	}

	/**
	 * 设置 文件后缀的比较机制
	 * 
	 * @param fileExtensionQueryMode
	 *            文件后缀的比较机制
	 */
	public void setFileExtensionQueryMode(StringQueryMode fileExtensionQueryMode) {
		this.fileExtensionQueryMode = fileExtensionQueryMode;
	}

	/**
	 * 获取 版本号。如果为null，则表示该项不作限制。否则采用versionQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 版本号。如果为null，则表示该项不作限制。否则采用versionQueryMode指定的比较机制。默认为null。
	 */
	public String getVersion() {
		return this.version;
	}

	/**
	 * 设置 版本号。如果为null，则表示该项不作限制。否则采用versionQueryMode指定的比较机制。默认为null。
	 * 
	 * @param version
	 *            版本号。如果为null，则表示该项不作限制。否则采用versionQueryMode指定的比较机制。默认为null。
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * 获取 版本号的比较机制
	 * 
	 * @return 版本号的比较机制
	 */
	public StringQueryMode getVersionQueryMode() {
		return this.versionQueryMode;
	}

	/**
	 * 设置 版本号的比较机制
	 * 
	 * @param versionQueryMode
	 *            版本号的比较机制
	 */
	public void setVersionQueryMode(StringQueryMode versionQueryMode) {
		this.versionQueryMode = versionQueryMode;
	}

	/**
	 * 获取 版本发布时间。如果为null，则表示该项不作限制。否则采用releaseTimeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 版本发布时间。如果为null，则表示该项不作限制。否则采用releaseTimeQueryMode指定的比较机制。默认为null。
	 */
	public Date getReleaseTime() {
		return this.releaseTime;
	}

	/**
	 * 设置 版本发布时间。如果为null，则表示该项不作限制。否则采用releaseTimeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param releaseTime
	 *            版本发布时间。如果为null，则表示该项不作限制。否则采用releaseTimeQueryMode指定的比较机制。
	 *            默认为null。
	 */
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	/**
	 * 获取 版本发布时间的比较机制
	 * 
	 * @return 版本发布时间的比较机制
	 */
	public DateQueryMode getReleaseTimeQueryMode() {
		return this.releaseTimeQueryMode;
	}

	/**
	 * 设置 版本发布时间的比较机制
	 * 
	 * @param releaseTimeQueryMode
	 *            版本发布时间的比较机制
	 */
	public void setReleaseTimeQueryMode(DateQueryMode releaseTimeQueryMode) {
		this.releaseTimeQueryMode = releaseTimeQueryMode;
	}

	/**
	 * 获取 上传时间。如果为null，则表示该项不作限制。否则采用uploadTimeQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 上传时间。如果为null，则表示该项不作限制。否则采用uploadTimeQueryMode指定的比较机制。默认为null。
	 */
	public Date getUploadTime() {
		return this.uploadTime;
	}

	/**
	 * 设置 上传时间。如果为null，则表示该项不作限制。否则采用uploadTimeQueryMode指定的比较机制。默认为null。
	 * 
	 * @param uploadTime
	 *            上传时间。如果为null，则表示该项不作限制。否则采用uploadTimeQueryMode指定的比较机制。默认为null。
	 */
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	/**
	 * 获取 上传时间的比较机制
	 * 
	 * @return 上传时间的比较机制
	 */
	public DateQueryMode getUploadTimeQueryMode() {
		return this.uploadTimeQueryMode;
	}

	/**
	 * 设置 上传时间的比较机制
	 * 
	 * @param uploadTimeQueryMode
	 *            上传时间的比较机制
	 */
	public void setUploadTimeQueryMode(DateQueryMode uploadTimeQueryMode) {
		this.uploadTimeQueryMode = uploadTimeQueryMode;
	}

	/**
	 * 获取 上传用户。如果为null，则表示该项不作限制。否则采用userQueryMode指定的比较机制。默认为null
	 * 
	 * @return 上传用户。如果为null，则表示该项不作限制。否则采用userQueryMode指定的比较机制。默认为null
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * 设置 上传用户。如果为null，则表示该项不作限制。否则采用userQueryMode指定的比较机制。默认为null
	 * 
	 * @param user
	 *            上传用户。如果为null，则表示该项不作限制。否则采用userQueryMode指定的比较机制。默认为null
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 获取 上传用户的比较机制
	 * 
	 * @return 上传用户的比较机制
	 */
	public ObjectQueryMode getUserQueryMode() {
		return this.userQueryMode;
	}

	/**
	 * 设置 上传用户的比较机制
	 * 
	 * @param userQueryMode
	 *            上传用户的比较机制
	 */
	public void setUserQueryMode(ObjectQueryMode userQueryMode) {
		this.userQueryMode = userQueryMode;
	}

	/**
	 * 获取 描述。如果为null，则表示该项不作限制。否则采用upgradeFileDescQueryMode指定的比较机制。默认为null。
	 * 
	 * @return 描述。如果为null，则表示该项不作限制。否则采用upgradeFileDescQueryMode指定的比较机制。默认为null。
	 */
	public String getUpgradeFileDesc() {
		return this.upgradeFileDesc;
	}

	/**
	 * 设置 描述。如果为null，则表示该项不作限制。否则采用upgradeFileDescQueryMode指定的比较机制。默认为null。
	 * 
	 * @param upgradeFileDesc
	 *            描述。如果为null，则表示该项不作限制。否则采用upgradeFileDescQueryMode指定的比较机制。
	 *            默认为null。
	 */
	public void setUpgradeFileDesc(String upgradeFileDesc) {
		this.upgradeFileDesc = upgradeFileDesc;
	}

	/**
	 * 获取 描述的比较机制
	 * 
	 * @return 描述的比较机制
	 */
	public StringQueryMode getUpgradeFileDescQueryMode() {
		return this.upgradeFileDescQueryMode;
	}

	/**
	 * 设置 描述的比较机制
	 * 
	 * @param upgradeFileDescQueryMode
	 *            描述的比较机制
	 */
	public void setUpgradeFileDescQueryMode(StringQueryMode upgradeFileDescQueryMode) {
		this.upgradeFileDescQueryMode = upgradeFileDescQueryMode;
	}
}
