/*
 * Project platform
 *
 * Classname OperationLogger.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-25 10:40
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.system.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yixin.framework.login.model.LoginInfo;
import com.yixin.framework.login.web.LoginAction;
import com.yixin.framework.system.model.Log;
import com.yixin.framework.system.service.LogService;

/**
 * 操作日志工具类
 * 
 * @version v1.0.0
 * @author 
 */
public class OperationLogger {

	/** 日志服务  final */
	private final static LogService logService;
	
	/** Spring日志服务名  */
	private final static String LOG_SERVICE_NAME = "logService";
	 
	static {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml", 
				"/com/yixin/framework/system/config/system-applicationContext.xml" });
		logService = (LogService)ctx.getBean(LOG_SERVICE_NAME);
	}
	 


	/**
	 * 添加操作日志
	 * @param session
	 * 			当前session
	 * @param type
	 * 			操作类型
	 * @param remark
	 * 			操作内容
	 */
	public static void addLog(HttpServletRequest request, OperationType type, String remark) {
		LoginInfo loginInfo = (LoginInfo)request.getSession(true).getAttribute(LoginAction.SESSION_LOGIN_INFO);
		if(null != loginInfo){
			Log log = new Log(new Date(), type.getValue(), loginInfo.getUser(), remark, request.getRemoteAddr());
			logService.addLog(log);
		}
	}
}
