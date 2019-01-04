﻿/*
 * Project platform
 *
 * Class RealTimeWireSagService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-8 下午04:16:51
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.wiresag.service;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.framework.system.model.User;

/**
 * 导线弧垂数据召测业务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface RealTimeWireSagService {

	/**
	 * 新增导线弧垂数据的召测任务
	 * 
	 * @param sensor
	 *            监测装置
	 */
	public abstract void addRealTimeTask(User user, Sensor sensor);
}
