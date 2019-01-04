/*
 * Project platform
 *
 * Class LineAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 上午11:17:05
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.video.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.comm.exception.ProtocolException;
import com.yixin.A1000.comm.exception.TCPServerException;
import com.yixin.A1000.video.model.VideoPlatform;
import com.yixin.A1000.video.model.VideoServer;
import com.yixin.A1000.video.service.RealTimeVideoService;
import com.yixin.A1000.video.service.VideoPlatformService;
import com.yixin.A1000.video.service.VideoServerService;

/**
 * 视频服务器模块Sction处理类
 * 
 * @version v1.0.0
 * @author 
 */
public class RealtimeVideoAction extends ActionSupport implements ServletRequestAware, Preparable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -846423423231657L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 视频平台业务接口 */	private VideoPlatformService videoPlatformService;
	
	
	/** 视频服务器业务接口 */
	private VideoServerService videoServerService;
	
	
	/** 监测装置业务接口 */
	private SensorService sensorService;


	/** 视频平台信息。保存到request域的视频平台信息 */
	private VideoPlatform videoPlatform;
	
	/** 实时视频业务接口 **/
	private RealTimeVideoService realTimeVideoService;
	
	/** 视频服务器信息。保存到request域的视频服务器信息 */
	private VideoServer videoServer;
	

	/** 视频服务器ID，在修改、删除时用到 */
	private String id;

	
	/**
	 * 获取 要进行操作的监测代理ID
	 * 
	 * @return 要进行操作的监测代理ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 要进行操作的监测代理ID
	 * 
	 * @param id
	 *            要进行操作的监测代理ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 获取 视频平台信息。
	 * 
	 * @return 视频平台信息
	 */
	public VideoPlatform getVideoPlatform() {
		return videoPlatform;
	}
	
	/**
	 * 设置 视频平台信息。
	 * 
	 * @param videoServer
	 *            视频平台信息
	 */
	public void setVideoPlatform(VideoPlatform videoPlatform) {
		this.videoPlatform = videoPlatform;
	}	
	
	/**
	 * 获取 视频服务器信息。保存新增视频服务器提交的数据，及保存到request域的视频服务器信息等
	 * 
	 * @return 视频服务器信息
	 */
	public VideoServer getVideoServer() {
		return videoServer;
	}
	
	/**
	 * 设置 视频服务器信息。保存新增视频服务器提交的数据，及保存到request域的视频服务器信息等
	 * 
	 * @param videoServer
	 *            视频服务器信息
	 */
	public void setVideoServer(VideoServer videoServer) {
		this.videoServer = videoServer;
	}
	/**
	 * 设置 视频平台业务接口
	 * 
	 * @param videoServerService
	 *            视频平台业务接口
	 */
	public void setVideoPlatformService(VideoPlatformService videoPlatformService) {
		this.videoPlatformService = videoPlatformService;
	}

	/**
	 * 设置 视频服务器业务接口
	 *  
	 * @param videoServerService
	 *            视频服务器业务接口
	 */
	public void setVideoServerService(VideoServerService videoServerService) {
		this.videoServerService = videoServerService;
	}

	/**
	 * 设置 实时视频业务接口
	 *  
	 * @param realtimeVideoService
	 *            实时视频业务接口
	 */
	public void setRealTimeVideoService(RealTimeVideoService realTimeVideoService) {
		this.realTimeVideoService = realTimeVideoService;
	}
	
	/**
	 * 设置 监测装置业务接口
	 * @param sensorService
	 */
	public void setSensorService(SensorService sensorService){
		this.sensorService = sensorService;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}


	/**
	 * 跳转到修改视频服务器页面
	 * 
	 * @return 结果页面
	 */
	public String list() {
		if (null == this.id || 0 == this.id.trim().length()) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先选择要进行操作的对象");
			return SUCCESS;
		}
		Sensor sensor = this.sensorService.getSensor(this.id);
		if (null == sensor) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "监测装置不存在，可能已经被删除");
			return SUCCESS;
		}
		
		this.videoServer = this.videoServerService.getVideoServerBySensor(sensor);
		
		
		List<VideoPlatform> videoPlatforms = this.videoPlatformService.getAllVideoPlatforms();
		if(videoPlatforms.size() == 0 ){
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "未配置视频平台信息");
		}else{
			this.videoPlatform = videoPlatforms.get(0);
		}				
		return SUCCESS;
	}

	public String openVideoPower(){
		return SUCCESS;
	}

	public String openPower(){
		String mrId = request.getParameter("mRID");
		String openTime = request.getParameter("minute");
		Integer minute = 0;
		String Description = "";
		Integer NUErrorCode = 0;
		try{
			if(mrId == null){
				Description = "设备ID未定义";
				NUErrorCode = -1;
				throw new Exception();
			}
			if(openTime == null){
				Description = "未传时间参数";
				NUErrorCode = -2;
				throw new Exception();
			}
			try{
				minute = Integer.parseInt(openTime);
			}catch(Exception e){
				Description = "时间参数格式错误";
				NUErrorCode = -3;
			}
			VideoServer videoServer = this.videoServerService.getVideoServerByDeviceCode(mrId);
			if(videoServer == null){
				Description = "未找到该设备ID";
				NUErrorCode = -4;
				throw new Exception();
			}
			if(!realTimeVideoService.openPower(videoServer.getSensor(), minute)){
				Description = "电源控制失败";
				NUErrorCode = -5;
				throw new Exception();				
			}
		}catch(ProtocolException pe){
			Description = pe.getMessage();
			NUErrorCode = -6;
		}catch(TCPServerException te){
			Description = "Not OnLine Error";
			NUErrorCode = -7; 	
		}catch(Exception e){
			Description = "Error";
			NUErrorCode = -8; 			
		}

		HttpServletResponse response = ServletActionContext.getResponse(); 
		response.setHeader("Cache-Control", "no-cache");   
		response.setHeader("Content-Disposition","attachment;filename=\"success.xml\"");		
		response.setContentType( "text/html;charset=utf-8" ); 		
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.print( "<Root>" ); 
			pw.print( String.format("<Response Description=\"%s\" NUErrorCode=\"%d\"/>" ,Description,NUErrorCode));			
			pw.print( "</Root>" ); 			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return SUCCESS;
	}
	
	

	public void prepare() throws Exception {
		
	}

 
}
