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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.yixin.A1000.archive.model.Sensor;

/**
 * 视频服务器
 * 
 * @version v1.0.0
 * @author 梁立全
 */

@Entity
@Table(name = "T_VideoServer")
public class VideoServer {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -4692156433455947L;

	/** 视频服务器Id */
	@Id
	@TableGenerator(name = "tab-store", table = "T_NumIdGenerator", pkColumnName = "code", pkColumnValue = "VideoServerId", valueColumnName = "value", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tab-store")
	private Integer videoServerId;
	
	/** 监测装置ID */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name="sensorId")
	private Sensor sensor;	

	/** 视频服务器名称 */
	private String name;

	/** 视频服务器编码 */
	private String videoServerCode;
	
	/** 摄像机编码*/
	private String cameraCode;	
	
	/** rtspUrl */
	private String rtspUrl;
	
	/** 默认打开时间 */
	private Integer openTime;

	/** 状态*/
	private Integer state;
	
	/**IP地址*/
	private String ip;
	
	/**Port*/
	private Integer port;
	
	/**用户名*/
	private String userName;
	
	/**密码*/
	private String password;
	
	/**摄像机个数*/
	private Integer cameraCount;

	public Integer getVideoServerId() {
		return videoServerId;
	}

	public void setVideoServerId(Integer videoServerId) {
		this.videoServerId = videoServerId;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVideoServerCode() {
		return videoServerCode;
	}

	public void setVideoServerCode(String videoServerCode) {
		this.videoServerCode = videoServerCode;
	}

	public String getCameraCode() {
		return cameraCode;
	}

	public void setCameraCode(String cameraCode) {
		this.cameraCode = cameraCode;
	}
	
	public String getRtspUrl() {
		return rtspUrl;
	}

	public void setRtspUrl(String rtspUrl) {
		this.rtspUrl = rtspUrl;
	}

	public Integer getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Integer openTime) {
		this.openTime = openTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
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

	public Integer getCameraCount() {
		return cameraCount;
	}

	public void setCameraCount(Integer cameraCount) {
		this.cameraCount = cameraCount;
	}	 
	
	
	
}
