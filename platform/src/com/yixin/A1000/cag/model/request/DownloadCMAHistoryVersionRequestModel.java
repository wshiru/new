/*
 * Project ca2000
 *
 * Class DownloadCMAHistoryVersionRequestModel.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午10:07:32
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.model.request;

/**
 * 获取历史版本的模型。存储解析后的信息。
 * 
 * @version v1.0.0
 * @author 
 */
public class DownloadCMAHistoryVersionRequestModel {

	/** 监测代理编码 */
	private String cmaCode;

	/** 监测装置编码 */
	private String sensorCode;

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
}
