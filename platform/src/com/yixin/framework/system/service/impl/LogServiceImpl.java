/*
 * Project platform
 *
 * Classname LogServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-5-8 下午
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.NullArgumentException;

import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.DateProperty;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.system.dao.LogDao;
import com.yixin.framework.system.model.Log;
import com.yixin.framework.system.service.LogService;

/**
 * 操作日志业务实现类
 * 
 * @version v1.0.0
 * @author 
 */
public class LogServiceImpl implements LogService {

	/** 操作日志Dao */
	private LogDao logDao;

	/**
	 * 设置操作日志Dao
	 * @param logDao
	 */
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}


	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.LogService#addLog(com.yixin.framework.system.model.Log)
	 */
	public void addLog(Log log) {
		if(log == null){
			throw new NullArgumentException("log");
		}
		this.logDao.save(log);		
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.LogService#getLog(java.lang.String)
	 */
	public Log getLog(String id) {
		return this.logDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.LogService#getAllLogs()
	 */
	public List<Log> getAllLogs() {
		return this.logDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.LogService#getPageLogs(long, long)
	 */
	public Page<Log> getPageLogs(long pageNo, long pageSize) {
		return this.logDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.LogService#getAllLogs(java.util.Date, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String)
	 */
	public List<Log> getAllLogs(Date beginTime, Date endTime, String userCode, String userName, 
									String ipAddress, String operateType, String remark) {				
		return this.logDao.getAllLikeProperties(buildQueryMap(userCode, userName, ipAddress, 
						operateType, remark), buildQueryDateProperty(beginTime, endTime));
			
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.LogService#getPageLogs(java.util.Date, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, int, int)
	 */
	public Page<Log> getPageLogs(Date beginTime, Date endTime, String userCode, String userName, 
			String ipAddress, String operateType, String remark, long pageNo, long pageSize) {				
		return this.logDao.getPageLikeProperties(buildQueryMap(userCode, userName, ipAddress, 
				operateType, remark), buildQueryDateProperty(beginTime, endTime), pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.LogService#deleteLog(java.util.Collection)
	 */
	public void deleteLog(Collection<Log> Log) {
		Iterator<Log> iterator = Log.iterator();
		while( iterator.hasNext())
		{
			Log log = iterator.next();
			this.logDao.delete(log);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.system.service.LogService#getAllLogs(java.util.Date)
	 */
	public List<Log> getAllLogs(Date removeTime) {
		DateProperty dProperty = new DateProperty();
		dProperty.setPropertyName("operateTime");
		dProperty.setEndTime(removeTime);
		dProperty.setDataOrder(DataOrder.DESC);
	    return  this.logDao.getAll(dProperty);
	}
	
	private Map<String, Object> buildQueryMap(String userCode, String userName, 
			String ipAddress, String operateType, String remark) {
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		if(null!=userCode && userCode.trim().length()>0)
			conditionMap.put("user.userCode", userCode);
		if(null != userName && userName.trim().length()>0)
			conditionMap.put("user.userName", userName);
		if(null != ipAddress && ipAddress.trim().length()>0)
			conditionMap.put("ipAddress", ipAddress);
		if(null != operateType)
			conditionMap.put("operateType", operateType);
		if(null != remark&&remark.trim().length()>0)
			conditionMap.put("remark", remark);
		return conditionMap;
	}
	private DateProperty buildQueryDateProperty(Date beginTime, Date endTime) {
		DateProperty dp = new DateProperty("operateTime", beginTime, endTime);
		dp.setDataOrder(DataOrder.DESC);
		return dp;
	}
	
}
