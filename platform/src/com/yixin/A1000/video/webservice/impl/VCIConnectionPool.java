package com.yixin.A1000.video.webservice.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class VCIConnectionPool implements Serializable {
	
	
	private static final long serialVersionUID = -8012947595269885361L;
	
	//private static Map<String , List<VCIConnector>> DSCConnectorMap=new HashMap<String ,List<VCIConnector>>();	
	//private static Map<String , DSCAddress> DSCAddressMap=new HashMap<String ,DSCAddress>();
	
	private static List<VCIConnector> VCIConnectorList = Collections.synchronizedList(new ArrayList<VCIConnector>());
	/** DSC IP地址 **/
	private static String address  = null;
	/** DSC 端口 **/
	private static Integer port = 10000;
	/** 最大连接数 **/
	private static Integer maxConnector = 50;
	/** 最大缓冲连接数 **/
	private static Integer maxBufferConnector = 5;
	/** 连接超时时间(分) **/
	private static Integer connectorTimeOut = 60;
	
	/** 读配置标记 **/
	private static boolean readFlag = false;
	
	/** 视频文件的路径 **/
	public static String videoFileUrl;
	
	/** RTSP的路径 **/
	public static String rtspUrl ;
	
	/** 值为0时从数据库中读取RTSPURL，为1从创世平台中读取URL **/
	public static Integer readRtspUrlType = 1;
	
	/** 创世RTSP服务器的URL */
	public static String cr7WebServerUrl ;
	
	public static String cr7WebServerEpid;
	
	/** 创世RTSP服务器的访问用户名 */
	public static String cr7WebServerUsername ;
	
	/** 创世RTSP服务器的访问密码 */
	public static String cr7WebServerPassword;
	
	//ftp.connectServer(ip, port, user, password, path)
	public static Integer ftpUseUrlPath = 1;
	/** ftp ip */
	public static String ftpIp; 
	/** ftp port */
	public static Integer ftpPort;
	/** ftp user */
	public static String ftpUser;
	/** ftp password */
	public static String ftpPassword;	

	/** ftp 本地文件的存放路径 */
	public static String ftpLocatePath;		
	/** ftp 远程文件的存放路径 */
	public static String ftpRemotePath;	
	
	private static Logger logger = Logger.getLogger(VCIConnectionPool.class);
	
	/** Spring上下文对象 */
	//private static ApplicationContext cx = null;
	
	//public static void initApplicationContext(ApplicationContext applicationContext){
	//	cx = applicationContext;
	//}
	
	public static void readConfig() {

		// 读取配置文件标志：true-成功  false-失败
		if(readFlag){
			return ;
		}			
			
		readFlag = true;
		
		Properties pro = new Properties();
		String filename = VCIConnectionPool.class.getResource("/").getPath() + "vci-config.properties";
		logger.info(filename);
		File file = new File(filename);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			pro.load(fis);
			address = pro.getProperty("vci.address");
			videoFileUrl = pro.getProperty("vci.videoFileUrl");
			rtspUrl = pro.getProperty("vci.rtspUrl");
			
			cr7WebServerUrl = pro.getProperty("vci.cr7WebServerUrl");
			cr7WebServerUsername = pro.getProperty("vci.cr7WebServerUsername");
			cr7WebServerPassword = pro.getProperty("vci.cr7WebServerPassword");
			cr7WebServerEpid = pro.getProperty("vci.cr7WebServerEpid");
			
			try{
				ftpUseUrlPath = Integer.parseInt( pro.getProperty("ftp.UseUrlPath"));
			} catch (NumberFormatException ex) {
				logger.error("读取FTP服务器信息失败：ftp.UseUrlPath格式不正确");
				readFlag = false;
			}
			ftpIp = pro.getProperty("ftp.Ip");
			try{
				ftpPort = 21;
				ftpPort = Integer.parseInt( pro.getProperty("ftp.Port"));
			} catch (NumberFormatException ex) {
				logger.error("读取FTP服务器信息失败：ftp.Port格式不正确");
				readFlag = false;
			}
			
			
			
			ftpUser = pro.getProperty("ftp.User");
			ftpPassword = pro.getProperty("ftp.Password");
			ftpLocatePath = pro.getProperty("ftp.LocatePath");
			ftpRemotePath = pro.getProperty("ftp.RemotePath");
			//ftp.connectServer(ip, port, user, password, path)
			
			
			
			try {
				String s = pro.getProperty("vci.readRtspUrlType");
				readRtspUrlType = Integer.parseInt(s);				
			} catch (NumberFormatException ex) {
				logger.error("读取VCI服务器信息失败：vci.readRtspUrlType格式不正确");
				readFlag = false;
			}			
			try {
				port = Integer.parseInt(pro.getProperty("vci.port"));				
			} catch (NumberFormatException ex) {
				logger.error("读取VCI服务器信息失败：VCI服务器端口号格式不正确");
				readFlag = false;
			}
			try {
				maxConnector = Integer.parseInt(pro.getProperty("vci.maxConnector"));				
			} catch (NumberFormatException ex) {
				logger.error("读取VCI服务器信息失败：最大连接数格式不正确");
				readFlag = false;
			}
			try {
				maxBufferConnector = Integer.parseInt(pro.getProperty("vci.maxBufferConnector"));				
			} catch (NumberFormatException ex) {
				logger.error("读取VCI服务器信息失败：最大缓存连接数不正确");
				readFlag = false;
			}	
			try {
				connectorTimeOut = Integer.parseInt(pro.getProperty("vci.connectorTimeOut"));				
			} catch (NumberFormatException ex) {
				logger.error("读取VCI服务器信息失败：连接超时时间不正确");
				readFlag = false;
			}							
		} catch (FileNotFoundException ex) {
			logger.error("读取VCI服务器信息失败：未找到DSC服务器配置文件");
			readFlag = false;
		} catch (IOException ex) {
			logger.error("读取VCI服务器信息失败：发生IO错误");
			readFlag = false;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	 
	
	/**
	 * 从列表时取出，或是创建一个，如果已超时的要删除。
	 * @return
	 */
	public synchronized static VCIConnector createVCIConnector(){
		logger.info("开始获取连接...");
		VCIConnector conn = null;
		while(VCIConnectorList.size()>0){
			conn = VCIConnectorList.get(0);
			VCIConnectorList.remove(0);
			Date nowTime = new Date();
			long diff=nowTime.getTime()-conn.getCreateDate().getTime();
			//转为分种
			diff = diff / 1000 ;
			if(diff > conn.getConnectionTimeOut()){
				//重读一次
				conn.close();
				conn = null;
			}else{				
				if(!conn.getState()){
					logger.info("连接已断");
					conn = null;				
				}else{
					logger.info("取出连接");
					return conn;
				}
			}
			
		}
		if(conn == null){
			//读配置文件			
			if(!readFlag){
				readConfig();				
				logger.info("readConfig："+address+port);
			}
			//连接连接
			conn = new VCIConnector(address,port,connectorTimeOut);
			logger.info("建立连接");
		}
		
		logger.info("返回连接");
		return conn;
	}
	/**
	 * 放回列表里
	 * @param connector
	 */
	public synchronized static void freeVCIConnector(VCIConnector connector){
		connector.setCreateDate();
		VCIConnectorList.add(connector);	
		logger.info("保存连接");
	}

}


