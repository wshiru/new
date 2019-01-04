package com.yixin.A1000.cag.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

/**
 * CAG过滤器
 * 
 * @author 梁立全
 */
public class CagFilter extends StrutsPrepareAndExecuteFilter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String currentUrl = request.getRequestURI();
		String targetUrl = currentUrl.substring(currentUrl.indexOf("/", 1) + 1);

		// 开放I2服务 wsdl: http://[计算机IP]:[端口]/platform/cag/CagService?wsdl
		if ("cag/CagService".equals(targetUrl)) {
			chain.doFilter(req, res);
		} else {
			// 使用Struts默认过滤器
			super.doFilter(req, res, chain);
		}
	}
}
