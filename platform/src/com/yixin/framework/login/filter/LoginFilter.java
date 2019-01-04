/*
 * Project platform
 *
 * Class LoginFilter.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-29 上午11:21:18
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.login.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yixin.framework.login.model.LoginInfo;
import com.yixin.framework.login.web.LoginAction;

/**
 * 权限过滤器
 * 
 * @version v1.0.0
 * @author 
 */
public class LoginFilter implements Filter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		// 取得根目录所对应的绝对路径
		String currentUrl = request.getRequestURI();
		String targetUrl = currentUrl.substring(currentUrl.indexOf("/", 1) + 1);
		//System.out.println(currentUrl); 
		//System.out.println(request.getLocalAddr() + request.getLocalPort() + request.getContextPath());
		// 截取到当前文件名用于比较
		HttpSession session = request.getSession(false);
		
		

		// 判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环
		if ("Logon.jspx".equals(targetUrl) || "login.jspx".equals(targetUrl) || "logout.jspx".equals(targetUrl)
				|| "logon.jspx".equals(targetUrl) || "/login.jspx".equals(targetUrl)
				|| "/logout.jspx".equals(targetUrl) || "video/realtimeVideo/openPower.jspx".equals(targetUrl)) {
		} else {
			if (session == null || session.getAttribute(LoginAction.SESSION_LOGIN_INFO) == null) {
				response.sendRedirect(request.getContextPath() + "/login.jspx");

				// 如果session为空表示用户没有登录就重定向到login.do页面
				return;
			}
			LoginInfo loginInfo = (LoginInfo) session.getAttribute(LoginAction.SESSION_LOGIN_INFO);
			Set<String> allowedUrls = loginInfo.getAllowedUrls();
			Set<String> limitUrls = loginInfo.getLimitUrls();
			if (limitUrls.contains(targetUrl) && !allowedUrls.contains(targetUrl)) {
				response.sendRedirect(request.getContextPath() + "/noLimit.jsp");
				return;
			}
		}

		// 加入filter 继续向下执行
		filterChain.doFilter(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
