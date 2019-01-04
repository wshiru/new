/*
 * Project platform
 *
 * Classname RoleAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-15 下午 15:19
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.web;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.yixin.framework.base.web.BaseAction;
import com.yixin.framework.system.model.Log;
import com.yixin.framework.system.service.LogService;

/**
 * 操作日志Action类
 * @version v1.0.0
 * @author 
 *
 */
public class LogAction extends BaseAction<Log> {

	/** 序列版本ID */
	private static final long serialVersionUID = 1997517529793014014L;
	
	/** 操作用户编码查询字段 */
	private String queryOperatorCode;
	/** 操作用户名称查询字段 */
	private String queryOperatorName;
	/** 操作类型查询字段 */
	private String queryOperateType;
	/** 操作IP查询字段 */
	private String queryIpAddress;
	/** 操作说明查询字段 */
	private String queryRemark;
	/** 操作时间查询字段起始时间 */
	private Date queryBeginTime;
	/** 操作时间查询字段终止时间 */
	private Date queryEndTime;
	/** 删除时间字段 */
	private Date removeTime;
	
	/** 日志服务 */
	private LogService logService;
	
	/**
	 * @param logService the logService to set
	 */
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	public String getQueryOperatorCode() {
		return queryOperatorCode;
	}
	public void setQueryOperatorCode(String queryOperatorCode) {
		this.queryOperatorCode = queryOperatorCode;
	}
	public String getQueryOperatorName() {
		return queryOperatorName;
	}
	public void setQueryOperatorName(String queryOperatorName) {
		this.queryOperatorName = queryOperatorName;
	}
	public String getQueryOperateType() {
		return queryOperateType;
	}
	public void setQueryOperateType(String queryOperateType) {
		this.queryOperateType = queryOperateType;
	}
	public String getQueryIpAddress() {
		return queryIpAddress;
	}
	public void setQueryIpAddress(String queryIpAddress) {
		this.queryIpAddress = queryIpAddress;
	}
	public String getQueryRemark() {
		return queryRemark;
	}
	public void setQueryRemark(String queryRemark) {
		this.queryRemark = queryRemark;
	}
	public Date getQueryBeginTime() {
		return queryBeginTime;
	}
	public void setQueryBeginTime(Date queryBeginTime) {
		this.queryBeginTime = queryBeginTime;
	}
	public Date getQueryEndTime() {
		return queryEndTime;
	}
	public void setQueryEndTime(Date queryEndTime) {
		this.queryEndTime = queryEndTime;
	}
	public Date getRemoveTime() {
		return removeTime;
	}
	public void setRemoveTime(Date removeTime) {
		this.removeTime = removeTime;
	}
		
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#list()
	 */
	@Override
	public String list() {
		if(null != queryBeginTime && null !=queryEndTime && queryBeginTime.after(queryEndTime))
			setErrorMsg("开始时间不能大于结束时间");
		if(null != queryOperateType && queryOperateType.trim().isEmpty())
			queryOperateType = null;

		Calendar d = Calendar.getInstance();
		d.add(Calendar.DAY_OF_MONTH, -7);
		Date beginTime = d.getTime();
		Date endTime = new Date();
		if (null == this.queryBeginTime && null == this.queryEndTime) {
			// 上面已经初始化
		} else  if (null == this.queryBeginTime) {
			beginTime = this.queryEndTime;
			endTime = this.queryEndTime;
		} else if (null == this.queryEndTime || this.queryBeginTime.after(this.queryEndTime)) {
			beginTime = this.queryBeginTime;
			endTime = this.queryBeginTime;
		} else {
			beginTime = this.queryBeginTime;
			endTime = this.queryEndTime;		
		}	
		Calendar begin = Calendar.getInstance();
		begin.setTime(beginTime);
		begin.set(begin.get(Calendar.YEAR), begin.get(Calendar.MONTH), begin.get(Calendar.DATE), 0, 0, 0);
		Calendar end = Calendar.getInstance();
		end.setTime(endTime);
		end.set(end.get(Calendar.YEAR), end.get(Calendar.MONTH), end.get(Calendar.DATE), 23, 59, 59);
		this.queryBeginTime = begin.getTime();
		this.queryEndTime = end.getTime();
		
		page = this.logService.getPageLogs(queryBeginTime, queryEndTime, queryOperatorCode, 
							queryOperatorName, queryIpAddress, queryOperateType, queryRemark, 
							getPageNo(), getPageSize());
		return super.list();
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#delete()
	 */
	@Override
	public String delete() {
		if(null == removeTime){
			setErrorMsg("请选择或输入指定日期");		
		}
		List<Log> LogList = this.logService.getAllLogs(this.removeTime);
		int count = LogList.size();
		if(count > 0){
			this.logService.deleteLog(LogList);
			setSuccessMsg("已成功删除" + count + "条日志");
			
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			this.addLog(OperationTypes.DELETE_DATA, "删除" + sdf.format(this.removeTime) + "之前的日志");
		}
		else {
			setErrorMsg("没有可删除的日志");
		}
		return this.list();
	}

}
