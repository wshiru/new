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
package com.yixin.A1000.video.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yixin.A1000.archive.model.Sensor;

/**
 * 视频平台
 * 
 * @version v1.0.0
 * @author 梁立全
 */

@Entity
@Table(name = "T_VideoPlatform")
public class VideoPlatform {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -469215165789457847L;

	/** 服务器配置Id */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String videoPlatformId;

	/** 服务器名称 */
	private String name;

	/** 服务器地址 */
	private String platformHost;
	
	/** 端口 */
	private Integer platformPort;
	
	/** 服务器用户名*/
	private String userName;
	
	/** 服务器密码*/
	private String password;
	
	/** DSC地址*/
	private String gatewayHost;
	
	/** DSC端口*/
	private Integer gatewayPort;		

	/** 是否正在使用网闸 */
	private Integer usedGateway;
	
	/** 调试模式 */
	private Integer usedDebug;

	public String getVideoPlatformId() {
		return videoPlatformId;
	}

	public void setVideoPlatformId(String videoPlatformId) {
		this.videoPlatformId = videoPlatformId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlatformHost() {
		return platformHost;
	}

	public void setPlatformHost(String platformHost) {
		this.platformHost = platformHost;
	}

	public Integer getPlatformPort() {
		return platformPort;
	}

	public void setPlatformPort(Integer platformPort) {
		this.platformPort = platformPort;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public String getGatewayHost() {
		return gatewayHost;
	}

	public void setGatewayHost(String gatewayHost) {
		this.gatewayHost = gatewayHost;
	}

	public Integer getGatewayPort() {
		return gatewayPort;
	}

	public void setGatewayPort(Integer gatewayPort) {
		this.gatewayPort = gatewayPort;
	}

	/**
	 * @param usedGateway the usedGateway to set
	 */
	public void setUsedGateway(Integer usedGateway) {
		this.usedGateway = usedGateway;
	}

	/**
	 * @return the usedGateway
	 */
	public Integer getUsedGateway() {
		return usedGateway;
	}

	/**
	 * @param usedDebug the usedDebug to set
	 */
	public void setUsedDebug(Integer usedDebug) {
		this.usedDebug = usedDebug;
	}

	/**
	 * @return the usedDebug
	 */
	public Integer getUsedDebug() {
		return usedDebug;
	}	
	 
	
}
