/*
 * Project platform
 *
 * Class DeviceSettingsAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-6 下午02:55:35
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.landslide.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.yixin.A1000.comm.exception.ClosedConnectionException;
import com.yixin.A1000.comm.exception.ProtocolException;
import com.yixin.A1000.comm.exception.ReceiveDataException;
import com.yixin.A1000.comm.exception.SendDataException;
import com.yixin.A1000.comm.exception.TCPServerException;
import com.yixin.A1000.comm.exception.TimeOutException;

/**
 * 终端参数配置Action类
 * 
 * @version v1.0.0
 * @author 
 */
public class LandslideParameterActionInterceptor extends AbstractInterceptor {
	private static final Logger logger = Logger.getLogger(LandslideParameterActionInterceptor.class);

	/** 类的序列化ID */
	private static final long serialVersionUID = 7311212902853182521L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com
	 * .opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = "success";
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		try {
			result = invocation.invoke();
		} catch (TimeOutException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "服务器未响应");
		} catch (ClosedConnectionException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "与服务器间未建立连接，或者连接已经断开");
		} catch (SendDataException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "向服务器写数据时发生错误");
		} catch (ProtocolException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, e.getMessage());
		} catch (TCPServerException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "服务器返回错误信息：\\r\\n" + e.getMessage());
		} catch (ReceiveDataException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "从服务器接收数据发生错误");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("系统发生内部错误。详细信息如下：" + e.getMessage());
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "系统发生内部错误。详细信息如下：" + e.getMessage());
		}
		return result;
	}

}
