
/*
 * Project platform
 *
 * Classname WarningConditionAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-25 10：13
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */package com.yixin.A1000.warning.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.yixin.framework.base.web.BaseAction;
import com.yixin.framework.system.util.OperationLogger;
import com.yixin.framework.system.util.OperationType;

 /**
 * 告警条件Action基类
 * 
 * @version v1.0.0
 * @author 
 */
public class BaseWarningConditionAction extends ActionSupport implements ServletRequestAware, Preparable{

	/** 序列版本ID  */
	private static final long serialVersionUID = 1343879958754040723L;
	
	/** request请求对象  */
	protected HttpServletRequest request;
	
	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	@Override
	public void prepare() throws Exception {
	
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 保存设置
	 * @return
	 */
	public String save(){
		setSuccessMessage("保存成功！");
		return SUCCESS;
	}
	
	/**
	 * 设置成功消息
	 * @param message
	 */
	protected void setSuccessMessage(String message){
		request.setAttribute(BaseAction.SUCCESS_MESSAGE, message);
	}
	
	/**
	 * 设置错误消息
	 * @param message
	 */
	protected void setFailureMessage(String message){
		request.setAttribute(BaseAction.ERROR_MESSAGE, message);
	}
	
	/**
	 * 添加保存日志
	 * @param warningDict
	 */
	protected void addSaveLog(String warningDict) {
		OperationLogger.addLog(request, OperationType.SET_WARNING_THRESHOLD, "设置" + warningDict + "阀值");
	}
}
