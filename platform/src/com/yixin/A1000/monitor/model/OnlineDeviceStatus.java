/*
 * Project platform
 *
 * Class OnlineDeviceStatus.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-12 下午03:10:01
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.monitor.model;

import java.util.Date;

import com.yixin.A1000.archive.model.Sensor;

/**
 * 在线终端状态
 * 
 * @version v1.0.0
 * @author 
 */
public class OnlineDeviceStatus {

	/** 监测装置 */
	private Sensor sensor;

	/** IP地址 */
	private String ip;

	/** 端口 */
	private Integer port;

	/** 登录时间 */
	private Date logonTime;

	/** 最后活动时间 */
	private Date lastCommTime;

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
	 * 获取 IP地址
	 * 
	 * @return IP地址
	 */
	public String getIp() {
		return this.ip;
	}

	/**
	 * 设置 IP地址
	 * 
	 * @param ip
	 *            IP地址
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 获取 端口
	 * 
	 * @return 端口
	 */
	public Integer getPort() {
		return this.port;
	}

	/**
	 * 设置 端口
	 * 
	 * @param port
	 *            端口
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * 获取 登录时间
	 * 
	 * @return 登录时间
	 */
	public Date getLogonTime() {
		return this.logonTime;
	}

	/**
	 * 设置 登录时间
	 * 
	 * @param logonTime
	 *            登录时间
	 */
	public void setLogonTime(Date logonTime) {
		this.logonTime = logonTime;
	}

	/**
	 * 获取 最后活动时间
	 * 
	 * @return 最后活动时间
	 */
	public Date getLastCommTime() {
		return this.lastCommTime;
	}

	/**
	 * 设置 最后活动时间
	 * 
	 * @param lastCommTime
	 *            最后活动时间
	 */
	public void setLastCommTime(Date lastCommTime) {
		this.lastCommTime = lastCommTime;
	}
}
