/*
 * Project platform
 *
 * Class UpdateService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-14 下午03:52:33
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.setting.service;

import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.model.UpgradeFile;
import com.yixin.framework.system.model.User;

/**
 * 远程升级业务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface UpdateService {

	/**
	 * 添加一个监测装置的升级任务
	 * 
	 * @param user
	 *            操作用户
	 * @param sensor
	 *            监测装置
	 * @param upgradeFile
	 *            升级文件
	 */
	public abstract void addUpdateTask(User user, Sensor sensor, UpgradeFile upgradeFile);

	/**
	 * 添加一个监测代理的升级任务
	 * 
	 * @param user
	 *            操作用户
	 * @param sensor
	 *            监测代理
	 * @param upgradeFile
	 *            升级文件
	 */
	public abstract void addUpdateTask(User user, CmaInfo cmaInfo, UpgradeFile upgradeFile);
}
