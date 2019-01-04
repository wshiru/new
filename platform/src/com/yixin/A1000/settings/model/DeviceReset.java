/*
 * Project platform
 *
 * Class DeviceTime.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-6 下午02:11:24
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
 * 装置复位
 * 
 * @version v1.0.0
 * @author 
 */
public class DeviceReset {
	private Sensor sensor;
	private ResetMode resetMode;

	public enum ResetMode {
		/** 常规复位（重启） */
		NORMAL_MODE,
		/** 复位至升级模式 */
		UPDATE_MODE,
		/** 复位至诊断模式 */
		DIAGNOSE_MODE,
		/** 复位至诊断模式 */
		OTHER;
	}

	public Sensor getSensor() {
		return this.sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public ResetMode getResetMode() {
		return this.resetMode;
	}

	public void setResetMode(ResetMode resetMode) {
		this.resetMode = resetMode;
	}
}
