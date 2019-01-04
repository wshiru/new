/*
 * Project ca2000
 *
 * Class DownloadCMAUpdateFileRequestModel.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午10:07:40
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.model.request;

/**
 * 获取更新文件模型。存储解析后的信息。
 * 
 * @version v1.0.0
 * @author 
 */
public class DownloadCMAUpdateFileRequestModel {

	/** 监测代理编码 */
	private String cmaCode;

	/** 版本号 */
	private String versionNo;

	/** 文件名称 */
	private String fileName;

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
	 * 获取 版本号
	 * 
	 * @return 版本号
	 */
	public String getVersionNo() {
		return this.versionNo;
	}

	/**
	 * 设置 版本号
	 * 
	 * @param versionNo
	 *            版本号
	 */
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	/**
	 * 获取 文件名称
	 * 
	 * @return 文件名称
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * 设置 文件名称
	 * 
	 * @param fileName
	 *            文件名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
