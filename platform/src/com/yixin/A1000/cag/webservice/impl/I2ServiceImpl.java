/*
 * Project ca2000
 *
 * Class I2ServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午09:31:34
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.webservice.impl;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yixin.A1000.cag.service.ProtocolService;
import com.yixin.A1000.cag.webservice.I2Service;

/**
 * I2 WEB SERVICE 服务的具体实现
 * 
 * @version v1.0.0
 * @author 
 */
public class I2ServiceImpl implements I2Service {

	/** 日志记录器 */
	private Logger logger = Logger.getLogger("socketLogger");

	/** Spring上下文对象 */
	private static ApplicationContext cx = null;

	/**
	 * 获取ProtocolService对象
	 * 
	 * @return
	 */
	private static ProtocolService getProtocolService() {
		if (cx == null) {

			/*
			 * 注意：由于ProtocolServiceInterceptor使用了AOP捕捉所有异常cag-applicationContext.
			 * xml必须放在事务声明applicationContext.xml之前
			 */
			cx = new ClassPathXmlApplicationContext(new String[] {
					"/com/yixin/framework/system/config/system-applicationContext.xml",
					"/com/yixin/A1000/archive/config/archive-applicationContext.xml",
					"/com/yixin/A1000/setting/config/setting-applicationContext.xml",
					"/com/yixin/A1000/comm/config/comm-applicationContext.xml",
					"/com/yixin/A1000/warning/config/warning-applicationContext.xml",
					"/com/yixin/A1000/insulatormonsoon/config/insulatorMonsoon-applicationContext.xml",
					"/com/yixin/A1000/towertilt/config/towerTilt-applicationContext.xml",
					"/com/yixin/A1000/weather/config/weather-applicationContext.xml",
					"/com/yixin/A1000/whitemonsoon/config/whiteMonsoon-applicationContext.xml",
					"/com/yixin/A1000/wiresag/config/wireSag-applicationContext.xml",
					"/com/yixin/A1000/wiretemperature/config/wireTemperature-applicationContext.xml",
					"/com/yixin/A1000/cag/config/cag-applicationContext.xml", "applicationContext.xml" });
		}
	//	cx.get
		ProtocolService parserService = (ProtocolService) cx.getBean("protocolService");
		return parserService;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.cag.service.I2Service#uploadCMAHeartbeatInfo(java.lang
	 * .String)
	 */
	@Override
	public String uploadCMAHeartbeatInfo(String strXMLParams) {
		logger.debug("收到CMA上传心跳信息请求:\r\n" + strXMLParams);
		String replyXml = getProtocolService().getUploadCMAHeartbeatInfoReplyXML(strXMLParams);
		logger.debug("回复CMA上传心跳信息请求:\r\n" + replyXml);
		return replyXml;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.cag.service.I2Service#uploadCMAData(java.lang.String)
	 */
	@Override
	public String uploadCMAData(String strXMLParams) {
		logger.debug("收到CMA上传监测数据请求:\r\n" + strXMLParams);
		String replyXml = getProtocolService().getUploadCMADataReplyXML(strXMLParams);
		logger.debug("回复CMA上传监测数据请求:\r\n" + replyXml);
		return replyXml;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.cag.service.I2Service#uploadCMAImage(byte[],
	 * java.lang.String)
	 */
	@Override
	public String uploadCMAImage(byte[] imageByte, String strXMLParams) {
		logger.debug("收到CMA上传静态图片数据请求:\r\n" + strXMLParams);
		String replyXml = getProtocolService().getUploadCMAImageReplyXML(imageByte, strXMLParams);
		logger.debug("回复CMA上传静态图片数据请求:\r\n" + replyXml);
		return strXMLParams;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.cag.service.I2Service#uploadCMAConfig(java.lang.String)
	 */
	@Override
	public String uploadCMAConfig(String strXMLParams) {
		logger.debug("收到CMA上传配置信息请求:\r\n" + strXMLParams);
		String replyXml = getProtocolService().getUploadCMAConfigReplyXML(strXMLParams);
		logger.debug("回复CMA上传配置信息请求:\r\n" + replyXml);
		return replyXml;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.cag.service.I2Service#downloadCAGCtrl(java.lang.String)
	 */
	@Override
	public String downloadCAGCtrl(String strXMLParams) {
		logger.debug("收到CMA下发控制指令请求:\r\n" + strXMLParams);
		String replyXml = getProtocolService().getDownloadCAGCtrlReplyXML(strXMLParams);
		logger.debug("回复CMA下发控制指令请求:\r\n" + replyXml);
		return strXMLParams;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.cag.service.I2Service#downloadCMALatestVersion(java.
	 * lang.String)
	 */
	@Override
	public String downloadCMALatestVersion(String strXMLParams) {
		logger.debug("收到CMA获取最新版本请求:\r\n" + strXMLParams);
		String replyXml = getProtocolService().getDownloadCMALatestVersionReplyXML(strXMLParams);
		logger.debug("回复CMA获取最新版本请求:\r\n" + replyXml);
		return replyXml;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.cag.service.I2Service#downloadCMAHistoryVersion(java
	 * .lang.String)
	 */
	@Override
	public String downloadCMAHistoryVersion(String strXMLParams) {
		logger.debug("收到CMA获取历史版本请求:\r\n" + strXMLParams);
		String replyXml = getProtocolService().getDownloadCMAHistoryVersionReplyXML(strXMLParams);
		logger.debug("回复CMA获取历史版本请求:\r\n" + replyXml);
		return replyXml;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.cag.service.I2Service#downloadCMAUpdateFile(java.lang
	 * .String)
	 */
	@Override
	public String downloadCMAUpdateFile(String strXMLParams) {
		logger.debug("收到CMA获取更新文件请求:\r\n" + strXMLParams);
		String replyXml = getProtocolService().getDownloadCMAUpdateFileReplyXML(strXMLParams);
		logger.debug("回复CMA获取更新文件请求:\r\n" + replyXml);
		return replyXml;
	}
}
