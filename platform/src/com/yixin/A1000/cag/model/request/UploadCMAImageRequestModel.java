/*
 * Project ca2000
 *
 * Class UploadCMAImageRequestModel.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午10:07:08
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

import com.yixin.A1000.setting.model.CmaParams;
import com.yixin.A1000.setting.model.SensorParams;

/**
 * 上传配置信息的模型。存储解析后的信息。
 * 
 * @version v1.0.0
 * @author 
 */
public class UploadCMAImageRequestModel {

	/** 监测代理参数 */
	private List<CmaParams> cmaParams = new ArrayList<CmaParams>();

	/** 监测装置参数 */
	private List<SensorParams> sensorParams = new ArrayList<SensorParams>();

	/**
	 * 获取 监测代理参数
	 * 
	 * @return 监测代理参数
	 */
	public List<CmaParams> getCmaParams() {
		return this.cmaParams;
	}

	/**
	 * 设置 监测代理参数
	 * 
	 * @param cmaParams
	 *            监测代理参数
	 */
	public void setCmaParams(List<CmaParams> cmaParams) {
		this.cmaParams = cmaParams;
	}

	/**
	 * 获取 监测装置参数
	 * 
	 * @return 监测装置参数
	 */
	public List<SensorParams> getSensorParams() {
		return this.sensorParams;
	}

	/**
	 * 设置 监测装置参数
	 * 
	 * @param sensorParams
	 *            监测装置参数
	 */
	public void setSensorParams(List<SensorParams> sensorParams) {
		this.sensorParams = sensorParams;
	}
}