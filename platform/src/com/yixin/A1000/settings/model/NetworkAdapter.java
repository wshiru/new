/*
 * Project platform
 *
 * Class NetworkAdapter.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-6 下午03:49:35
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
 * 状态监测装置网络适配器
 * 
 * @version v1.0.0
 * @author 
 */
public class NetworkAdapter {

	/** 监测装置 */
	private Sensor sensor;
	/** 状态监测装置IP地址 */
	private String ip;
	/** 子网掩码 */
	private String subnetMask;
	/** 网关 */
	private String gateway;
	/** DNS 服务器 */
	private String dnsServer;
	/** 备用 */
	private String reserve;

	public Sensor getSensor() {
		return this.sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSubnetMask() {
		return this.subnetMask;
	}

	public void setSubnetMask(String subnetMask) {
		this.subnetMask = subnetMask;
	}

	public String getGateway() {
		return this.gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getDnsServer() {
		return this.dnsServer;
	}

	public void setDnsServer(String dnsServer) {
		this.dnsServer = dnsServer;
	}

	public String getReserve() {
		return this.reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
}
