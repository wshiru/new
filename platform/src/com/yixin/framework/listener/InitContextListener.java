/*
 * Project platform
 *
 * Class InitContextListener.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-7-20 上午11:56:15
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.framework.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.yixin.A1000.comm.CommServiceImpl;
import com.yixin.framework.util.ContextKeys;
import com.yixin.framework.util.SystemInfo;

/**
 * 初始化上下文类。加载并配置系统所需要的配置信息。
 * 
 * 
 * @version v1.0.0
 * @author
 */
public class InitContextListener implements ServletContextListener {
	private static final Logger logger = Logger
			.getLogger(InitContextListener.class);

	public void contextDestroyed(ServletContextEvent sce) {

	}
 

	public void contextInitialized(ServletContextEvent sce) {
	
		String theme = new SystemInfo().getSystemTheme();
		String systemName = new SystemInfo().getSystemName();
		Integer systemType = new SystemInfo().getSystemType();
		
		ContextKeys.PROJECT_TYPE = systemType;
		sce.getServletContext().setAttribute("projectType", systemType);		
		
		ContextKeys.PROJECT_NAME = systemName;
		sce.getServletContext().setAttribute("projectName", systemName);
		
		sce.getServletContext().setAttribute("theme", theme);
		ContextKeys.WEB_PHYSICAL_ROOT_PATH = sce.getServletContext()
				.getRealPath("/");
		readCommConfig();		
	}

	private void readCommConfig() {

		String ipAddress = "127.0.0.1";
		int port = 8096;
		long timeout = 45000;
		// 读取配置文件标志：true-成功 false-失败
		boolean flag = true;
		Properties pro = new Properties();
		FileInputStream fis = null;
		try {
			File file = new File(InitContextListener.class.getResource("/")
					.toURI().getPath()
					+ "comm-config.properties");
			fis = new FileInputStream(file);
			pro.load(fis);
			ipAddress = pro.getProperty("comm.tcp.ipAddress");
			try {
				port = Integer.parseInt(pro.getProperty("comm.tcp.port"));
			} catch (NumberFormatException ex) {
				logger.error("读取服务器配置信息失败：端口号格式不正确");
				flag = false;
			}
			try {
				timeout = Long.parseLong(pro.getProperty("comm.tcp.timeout"));
			} catch (NumberFormatException ex) {
				logger.error("读取超时时间失败：超时时间格式不正确。将使用默认超时时间：4500ms");
			}
		} catch (FileNotFoundException ex) {
			logger.error("读取服务器配置信息失败：未找到服务器配置文件");
			flag = false;
		} catch (IOException ex) {
			logger.error("读取服务器配置信息失败：发生IO错误");
			flag = false;
		} catch (URISyntaxException e) {
			logger.error("读取服务器配置信息失败：未找到服务器配置文件");
			flag = false;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					logger.error("关闭服务器配置文件时发生错误。详细信息如下：", e);
				}
			}
		}
		if (!flag) {
			logger.error("读取服务器配置信息失败，将使用默认参数进行连接：ip=" + ipAddress + ":" + port);
		}
		CommServiceImpl.server = new InetSocketAddress(ipAddress, port);
		CommServiceImpl.timeout = timeout;
	}
}
