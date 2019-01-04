/*
 * Project platform
 *
 * Class MasterStation.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-12 上午10:44:59
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.settings.model;

import com.yixin.A1000.archive.model.Sensor;

/**
 * 上位机信息
 * 
 * @version v1.0.0
 * @author 
 */
public class MasterStation {

	/** 监测装置 */
	private Sensor sensor;
	/** 上位机IP地址 */
	private String ipAddress;
	/** 上位机端口号 */
	private Integer port;
	/** 上位机域名 */
	private String domainName;
	/** 备用 */
	private String reserve;

	/**
	 * 获取 监测装置
	 * 
	 * @return 监测装置
	 */
	public Sensor getSensor() {
		return this.sensor;
	}

	/**
	 * 设置 监测装置
	 * 
	 * @param sensor
	 *            监测装置
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	/**
	 * 获取 上位机IP地址
	 * 
	 * @return 上位机IP地址
	 */
	public String getIpAddress() {
		return this.ipAddress;
	}

	/**
	 * 设置 上位机IP地址
	 * 
	 * @param ipAddress
	 *            上位机IP地址
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * 获取 上位机端口号
	 * 
	 * @return 上位机端口号
	 */
	public Integer getPort() {
		return this.port;
	}

	/**
	 * 设置 上位机端口号
	 * 
	 * @param port
	 *            上位机端口号
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * 获取 上位机域名
	 * 
	 * @return 上位机域名
	 */
	public String getDomainName() {
		return this.domainName;
	}

	/**
	 * 设置 上位机域名
	 * 
	 * @param domainName
	 *            上位机域名
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	/**
	 * 获取 备用
	 * 
	 * @return 备用
	 */
	public String getReserve() {
		return this.reserve;
	}

	/**
	 * 设置 备用
	 * 
	 * @param reserve
	 *            备用
	 */
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
}
