/*
 * Project ca2000
 *
 * Class UploadCMAConfigRequestModel.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午10:07:14
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

import com.yixin.A1000.setting.model.DeviceParameter;


/**
 * 上传配置信息的模型。存储解析后的信息。
 * 
 * @version v1.0.0
 * @author 
 */
public class UploadCMAConfigRequestModel {

	/** 设备参数参数 */
	private List<DeviceParameter> deviceParams = new ArrayList<DeviceParameter>();

	public List<DeviceParameter> getDeviceParams() {
		return deviceParams;
	}

	public void setDeviceParams(List<DeviceParameter> deviceParams) {
		this.deviceParams = deviceParams;
	}
	
}
