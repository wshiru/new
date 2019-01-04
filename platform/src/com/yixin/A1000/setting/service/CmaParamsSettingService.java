/*
 * Project platform
 *
 * Class SettingService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-4 上午09:26:25
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.setting.service;

import java.util.Map;

import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.setting.model.CmaParams;
import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.framework.system.model.User;

/**
 * 监测代理参数下发查询业务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface CmaParamsSettingService {

	/**
	 * 从数据库读取最后一次与监测代理同步的数据
	 * 
	 * @param cma
	 *            监测代理
	 * @return 监测代理参数
	 */
	public abstract CmaParams getDbCmaParams(CmaInfo cma);

	/**
	 * 一个监测代理参数的召测任务
	 * 
	 * @param user
	 *            操作的用户
	 * @param cma
	 *            监测代理
	 */
	public abstract void readCmaParams(User user, CmaInfo cma);

	/**
	 * 取得监测代理cma下未完成的召测任务
	 * 
	 * @param cma
	 *            监测代理
	 * @return 未完成的召测任务
	 */
	public abstract TaskConfig getUnfinishedReadTaskConfig(CmaInfo cma);

	/**
	 * 新增一个监测代理参数的下发任务
	 * 
	 * @param user
	 *            操作的用户
	 * @param cmaParams
	 *            监测代理参数
	 */
	public abstract void addCmaParams(User user, CmaParams cmaParams);

	/**
	 * 取得监测代理cma下未完成的下发任务
	 * 
	 * @param cma
	 *            监测代理
	 * @return 未完成的下发任务。Map&lt;String, Object&gt;<br />
	 *         taskConfig - 任务信息。TaskConfig类型<br />
	 *         cmaParams - 监测代理参数。CmaParams类型
	 */
	public Map<String, Object> getCmaParamsUnfinishedTasks(CmaInfo cma);
}
