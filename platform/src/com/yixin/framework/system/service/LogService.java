/*
 * Project platform
 *
 * Classname LogService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-9 下午
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */

package com.yixin.framework.system.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.yixin.framework.base.model.Page;
import com.yixin.framework.system.model.Log;

/**
 * 操作日志业务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface LogService {

		
    /** Log :批量删除日志信息
     * 
     */	
	public abstract void deleteLog(Collection<Log> Log);
	
	/**
	 * 增加操作日志信息
	 * 
	 * @param operationLog
	 *            要添加的操作日志
	 */
	public abstract void addLog(Log log);

	/**
	 * 根据ID查找操作日志
	 * 
	 * @param id
	 *            要查找操作日志的ID
	 * @return 操作日志信息。当找不到时返回null
	 */
	public abstract Log getLog(String id);

	/**
	 * 获取所有的操作日志信息
	 * 
	 * @return 所有操作日志信息

	 */
	public abstract List<Log> getAllLogs();

	/**
	 * 查询第pageNo页的操作日志列表
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小

	 * @return 返回每pageNo页的操作日志列表
	 */
	public abstract Page<Log> getPageLogs(long pageNo, long pageSize);

	/**
	 * 根据输入以下参数作为条件查询操作日志（String类型参数为模糊查询）
	 * @param beginTime
	 * 			开始时间。如果为null，则表示该项不作限制
	 * @param endTime
	 * 			结束时间。如果为null，则表示该项不作限制
	 * @param userCode
	 * 			用户编号。如果为null，则表示该项不作限制
	 * @param userName
	 * 			用户名称。如果为null，则表示该项不作限制
	 * @param ipAddress
	 * 			IP地址 。如果为null，则表示该项不作限制
	 * @param operateType
	 * 			操作类型。如果为null，则表示该项不作限制
	 * @param remark
	 * 			日志内容。如果为null，则表示该项不作限制
	 * @return	返回符合条件的操作日志列表
	 */
	public abstract List<Log> getAllLogs(Date beginTime, Date endTime, String userCode, String userName, 
											String ipAddress, String operateType, String remark) ;

	
	/**
	 * 根据指定日期，取得该日期之前的日志

	*/
	
	public abstract List<Log> getAllLogs(Date removeTime);
	
	/**
	 * 根据输入以下参数作为条件查询操作日志（String类型参数为模糊查询）
	 * @param beginTime
	 * 			开始时间。如果为null，则表示该项不作限制
	 * @param endTime
	 * 			结束时间。如果为null，则表示该项不作限制
	 * @param userCode
	 * 			用户编号。如果为null，则表示该项不作限制
	 * @param userName
	 * 			用户名称。如果为null，则表示该项不作限制
	 * @param ipAddress
	 * 			IP地址 。如果为null，则表示该项不作限制
	 * @param operateType
	 * 			操作类型。如果为null，则表示该项不作限制
	 * @param remark
	 * 			日志内容。如果为null，则表示该项不作限制
	 * @param pageNo
	 * 			页码
	 * @param pageSize
	 * 			页面大小
	 * @return	返回符合条件的操作日志页数据
	 */
	public abstract Page<Log> getPageLogs(Date beginTime, Date endTime, String userCode, String userName, 
			String ipAddress, String operateType, String remark, long pageNo, long pageSize);
}
