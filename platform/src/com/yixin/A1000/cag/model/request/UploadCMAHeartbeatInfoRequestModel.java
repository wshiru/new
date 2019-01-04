/*
 * Project ca2000
 *
 * Class UploadCMAHeartbeatInfoModel.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午10:04:28
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.model.request;

import java.util.ArrayList;
import java.util.List;

import com.yixin.A1000.setting.model.CmaHeartbeatInfo;
import com.yixin.A1000.setting.model.SensorHeartbeatInfo;

/**
 * 上传心跳信息的模型。存储解析后的信息。
 * 
 * @version v1.0.0
 * @author 
 */
public class UploadCMAHeartbeatInfoRequestModel {

	/** 监测代理心跳信息 */
	private CmaHeartbeatInfo cmaHeartbeatInfo;

	/** 监测装置心跳信息 */
	private List<SensorHeartbeatInfo> sensorHeartbeatInfos = new ArrayList<SensorHeartbeatInfo>();

	/**
	 * 获取 监测代理心跳信息
	 * 
	 * @return 监测代理心跳信息
	 */
	public CmaHeartbeatInfo getCmaHeartbeatInfo() {
		return this.cmaHeartbeatInfo;
	}

	/**
	 * 设置 监测代理心跳信息
	 * 
	 * @param cmaHeartbeatInfo
	 *            监测代理心跳信息
	 */
	public void setCmaHeartbeatInfo(CmaHeartbeatInfo cmaHeartbeatInfo) {
		this.cmaHeartbeatInfo = cmaHeartbeatInfo;
	}

	/**
	 * 获取 监测装置心跳信息
	 * 
	 * @return 监测装置心跳信息
	 */
	public List<SensorHeartbeatInfo> getSensorHeartbeatInfos() {
		return this.sensorHeartbeatInfos;
	}

	/**
	 * 设置 监测装置心跳信息
	 * 
	 * @param sensorHeartbeatInfos
	 *            监测装置心跳信息
	 */
	public void setSensorHeartbeatInfos(List<SensorHeartbeatInfo> sensorHeartbeatInfos) {
		this.sensorHeartbeatInfos = sensorHeartbeatInfos;
	}
}
