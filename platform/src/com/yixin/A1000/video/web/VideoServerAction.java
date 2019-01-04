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

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.yixin.A1000.archive.model.ArchiveErrorCode;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.comm.CommService;
import com.yixin.A1000.video.model.RecordPlan;
import com.yixin.A1000.video.model.VideoServer;
import com.yixin.A1000.video.service.RecordPlanService;
import com.yixin.A1000.video.service.VideoServerService;
import com.yixin.framework.exception.BusinessException;

/**
 * 视频服务器模块Sction处理类
 * 
 * @version v1.0.0
 * @author 
 */
public class VideoServerAction extends ActionSupport implements ServletRequestAware, Preparable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -846423423231657L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 视频服务器业务接口 */
	private VideoServerService videoServerService;
	

	
	/** 监测装置业务接口 */
	private SensorService sensorService;
	
	/** 通讯服务类 */
	private CommService commService;	


	/** 视频服务器信息。保存新增视频服务器提交的数据，及保存到request域的视频服务器信息等 */
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
	 * @param line
	 *            视频服务器信息
	 */
	public void setVideoServer(VideoServer videoServer) {
		this.videoServer = videoServer;
	}


	/**
	 * 设置 视频服务器业务接口
	 * 
	 * @param lineService
	 *            视频服务器业务接口
	 */
	public void setVideoServerService(VideoServerService videoServerService) {
		this.videoServerService = videoServerService;
	}
	/**
	 * 设置 监测装置业务接口
	 * @param sensorService
	 */
	public void setSensorService(SensorService sensorService){
		this.sensorService = sensorService;
	}

	/**
	 * 通讯业务接口
	 * @param commService
	 */
	public void setCommService(CommService commService){
		this.commService = commService;
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
	public String edit() {
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
		
		return SUCCESS;
	}

	/**
	 * 保存修改视频服务器信息
	 * 
	 * @return 结果页面
	 */
	public String editSaveHK() {
		return editSave();
	}
	public String editSave() {
		if (null == this.id || 0 == this.id.trim().length()) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先选择要进行操作的对象");
			return SUCCESS;
		}
		Sensor sensor = this.sensorService.getSensor(this.id);
		if (null == sensor) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "监测装置不存在，可能已经被删除");
			return SUCCESS;
		}		
		if(videoServer == null){
			request.setAttribute(VideoServerAction.ERROR_MESSAGE_ATTR_NAME, "提交数据错误！");
			return INPUT;			
		}				
		try{
			videoServer.setSensor(sensor);
			videoServer.setState(1);
			if(videoServer.getVideoServerId() == null){
				this.videoServerService.addVideoServer(videoServer);
				this.videoServer = this.videoServerService.getVideoServerBySensor(sensor);
			}else{
				this.videoServerService.editVideoServer(videoServer);
			}			
			this.request.setAttribute(VideoServerAction.SUCCESS_MESSAGE_ATTR_NAME, "保存成功！");
		}catch(BusinessException ex){
			switch (ex.getErrorCode()) {
			case ArchiveErrorCode.ARCHIVE_VIDEOSERVER_SENSOR_ALREADYEXISTS:
				this.request.setAttribute(VideoServerAction.ERROR_MESSAGE_ATTR_NAME, "视频服务器已经存在");
				break;
			}
		}					 
		return SUCCESS;
	}
	public String openVideoPower(){
		if (null == this.id || 0 == this.id.trim().length()) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先选择要进行操作的对象");
			return SUCCESS;
		}
		Sensor sensor = this.sensorService.getSensor(this.id);
		if (null == sensor) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "监测装置不存在，可能已经被删除");
			return SUCCESS;
		}		
		if(videoServer == null){
			request.setAttribute(VideoServerAction.ERROR_MESSAGE_ATTR_NAME, "提交数据错误！");
			return INPUT;			
		}
		/*
		if(videoServer.getVideoServerCode() == null || "".equals(videoServer.getVideoServerCode())){
			request.setAttribute(VideoServerAction.ERROR_MESSAGE_ATTR_NAME, "请输入视频设备编码！");
			return INPUT;			
		}
		*/
		
		if(videoServer.getOpenTime() == null || videoServer.getOpenTime()<0){
			request.setAttribute(VideoServerAction.ERROR_MESSAGE_ATTR_NAME, "请打开时间的值错误，必需大于或等于0！");
			return INPUT;			
		}		
		try{			
			if(!commService.openVideoPower( sensor.getSensorCode(), videoServer.getOpenTime())){
				this.request.setAttribute(VideoServerAction.SUCCESS_MESSAGE_ATTR_NAME, "电源控制失败！");
			}else{
				this.request.setAttribute(VideoServerAction.SUCCESS_MESSAGE_ATTR_NAME, "电源控制成功！");
			}
		}catch(Exception e){
			this.request.setAttribute(VideoServerAction.SUCCESS_MESSAGE_ATTR_NAME, "电源控制出错！");
		}
						
		return SUCCESS;
	} 

	public void prepare() throws Exception {
		
	}

 
}
