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

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Holder;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.yixin.A1000.archive.model.ArchiveErrorCode;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.comm.CommService;
import com.yixin.A1000.video.model.VideoServer;
import com.yixin.A1000.video.service.VideoServerService;
import com.yixin.A1000.video.webservice.CameraProxyPortType;
import com.yixin.A1000.video.webservice.impl.CameraProxy;
import com.yixin.framework.exception.BusinessException;

/**
 * 视频服务器模块Sction处理类
 * 
 * @version v1.0.0
 * @author 
 */
public class VideoPlayAction extends ActionSupport implements ServletRequestAware, Preparable {

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
	
	private String loginID;
	
	private String channelID;
	
	private Integer presetIndex;
	
	private Integer ptzCmd;

	
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
	 * @param 配置 登录ID
	 */
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	/**
	 * @return 读取 登录ID
	 */
	public String getLoginID() {
		return loginID;
	}
	
	/**
	 * @param 通道号
	 */
	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}

	/**
	 * @return 通道号
	 */
	public String getChannelID() {
		return channelID;
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

	public String list(){
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
	
	public String login(){
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String msg = String.format("%s,%s",userName,password);
		System.out.println(msg);
		CameraProxy service = new CameraProxy();
		CameraProxyPortType portType = service.getCameraProxy();
		loginID = portType.login(userName, password);
		  
		request.setAttribute(VideoPlayAction.SUCCESS_MESSAGE_ATTR_NAME, "登录成功！返回："+loginID);
		
		return list();
	}
	
	public String logout(){
		CameraProxy service = new CameraProxy();
		CameraProxyPortType portType = service.getCameraProxy();
		int ret  = portType.logout(loginID);
		if(0 == ret){
			request.setAttribute(VideoPlayAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功！返回："+String.valueOf(ret));	
		}else{
			request.setAttribute(VideoPlayAction.ERROR_MESSAGE_ATTR_NAME, "操作失败！返回："+String.valueOf(ret));
		}
		return list();
	}
	
	public String ptzControlStart(){
		list();
		CameraProxy service = new CameraProxy();
		CameraProxyPortType portType = service.getCameraProxy();
		int ptzCmd = Integer.valueOf(request.getParameter("ptzCmd")) ;
		request.setAttribute("ptzCmd", ptzCmd);
		int ret  = portType.ptzControl(loginID, Integer.valueOf(channelID), ptzCmd, 0);
		if(0 == ret){
			request.setAttribute(VideoPlayAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功！返回："+String.valueOf(ret));	
		}else{
			request.setAttribute(VideoPlayAction.ERROR_MESSAGE_ATTR_NAME, "操作失败！返回："+String.valueOf(ret));
		}		
		
		return SUCCESS;
	}
	

	public String ptzControlStop(){
		list();
		CameraProxy service = new CameraProxy();
		CameraProxyPortType portType = service.getCameraProxy();
		int ptzCmd = Integer.valueOf(request.getParameter("ptzCmd")) ;
		request.setAttribute("ptzCmd", ptzCmd);
		int ret  = portType.ptzControl(loginID, Integer.valueOf(channelID), ptzCmd, 1);
		if(0 == ret){
			request.setAttribute(VideoPlayAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功！返回："+String.valueOf(ret));	
		}else{
			request.setAttribute(VideoPlayAction.ERROR_MESSAGE_ATTR_NAME, "操作失败！返回："+String.valueOf(ret));
		}		
		return SUCCESS;
	}
	
	public String ptzPresetCall(){
		list();
		CameraProxy service = new CameraProxy();
		CameraProxyPortType portType = service.getCameraProxy();
		int presetIndex = Integer.valueOf(request.getParameter("presetIndex")) ;
		request.setAttribute("presetIndex", presetIndex);
		int ret  = portType.ptzPreset(loginID,  Integer.valueOf(channelID), 3, presetIndex);
		if(0 == ret){
			request.setAttribute(VideoPlayAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功！返回："+String.valueOf(ret));	
		}else{
			request.setAttribute(VideoPlayAction.ERROR_MESSAGE_ATTR_NAME, "操作失败！返回："+String.valueOf(ret));
		}		
		return SUCCESS;
	}
	
	public String photograph(){
		list();
		CameraProxy service = new CameraProxy();
		CameraProxyPortType portType = service.getCameraProxy();
		int presetIndex = Integer.valueOf(request.getParameter("presetIndex")) ;
		request.setAttribute("presetIndex", presetIndex);
		String ftpPath = request.getParameter("ftpPath");
		request.setAttribute("ftpPath", ftpPath);
		
		int ret  = portType.photograph(loginID,  Integer.valueOf(channelID), presetIndex,ftpPath);
		if(0 == ret){
			request.setAttribute(VideoPlayAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功！返回："+String.valueOf(ret));	
		}else{
			request.setAttribute(VideoPlayAction.ERROR_MESSAGE_ATTR_NAME, "操作失败！返回："+String.valueOf(ret));
		}		
		return SUCCESS;
	}
		
	
	public String ptzPresetSet(){
		list();
		CameraProxy service = new CameraProxy();
		CameraProxyPortType portType = service.getCameraProxy();
		int presetIndex = Integer.valueOf(request.getParameter("presetIndex")) ;
		request.setAttribute("presetIndex", presetIndex);
		int ret  = portType.ptzPreset(loginID,  Integer.valueOf(channelID), 1, presetIndex);		
		if(0 == ret){
			request.setAttribute(VideoPlayAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功！返回："+String.valueOf(ret));	
		}else{
			request.setAttribute(VideoPlayAction.ERROR_MESSAGE_ATTR_NAME, "操作失败！返回："+String.valueOf(ret));
		}		
		return SUCCESS;
	}			
	
	public String powerControl(){
		int openTime = this.videoServer.getOpenTime();
		list();
		this.videoServer.setOpenTime(openTime);
		CameraProxy service = new CameraProxy();
		CameraProxyPortType portType = service.getCameraProxy();
		
		int ret  = portType.powerControl(loginID,  Integer.valueOf(channelID), openTime);		
		if(0 == ret){
			request.setAttribute(VideoPlayAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功！返回："+String.valueOf(ret));	
		}else{
			request.setAttribute(VideoPlayAction.ERROR_MESSAGE_ATTR_NAME, "操作失败！返回："+String.valueOf(ret));
		}		
		return SUCCESS;
	}

	public String queryAlarmEvent(){		
		list();
		Holder<String> alarmEventList = new Holder<String>();
		Holder<Integer> result = new Holder<Integer>();
		String startTime = request.getParameter("startTime1");
		String endTime = request.getParameter("endTime1");	
		if(null == startTime || null == endTime){
			request.setAttribute(VideoPlayAction.ERROR_MESSAGE_ATTR_NAME, "请输入查询时间！");
			return SUCCESS;
		}
		request.setAttribute("startTime1", startTime);
		request.setAttribute("endTime1", endTime);
		CameraProxy service = new CameraProxy();
		CameraProxyPortType portType = service.getCameraProxy();
		portType.queryAlarmEvent(loginID,  Integer.valueOf(channelID), startTime, endTime, alarmEventList, result);		
		if(0 == result.value){
			String retString = alarmEventList.value;
			request.setAttribute(VideoPlayAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功！返回："+String.valueOf(result.value));	
			request.setAttribute("retString", retString);
		}else{
			request.setAttribute(VideoPlayAction.ERROR_MESSAGE_ATTR_NAME, "操作失败！返回："+String.valueOf(result.value));
		}
		
		return SUCCESS;
	}	
	
	public String queryPowerStatus(){
		list();
		Holder<Integer> powerStatus = new Holder<Integer>();
		Holder<Integer> result = new Holder<Integer>();
		CameraProxy service = new CameraProxy();
		CameraProxyPortType portType = service.getCameraProxy();
		portType.queryPowerStatus(loginID,  Integer.valueOf(channelID), powerStatus, result);		
		if(0 == result.value){
			if(powerStatus.value == 0){
				request.setAttribute("retString", "未上电");	
			}else{
				request.setAttribute("retString", "已上电");
			}			
			request.setAttribute(VideoPlayAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功！返回："+String.valueOf(result.value));	
			
		}else{
			request.setAttribute(VideoPlayAction.ERROR_MESSAGE_ATTR_NAME, "操作失败！返回："+String.valueOf(result.value));
		}
		
		return SUCCESS;

	}	
	
	public String queryOnlineStatus(){
		list();
		Holder<Integer> onlineStatus = new Holder<Integer>();
		Holder<Integer> result = new Holder<Integer>();
		CameraProxy service = new CameraProxy();
		CameraProxyPortType portType = service.getCameraProxy();
		portType.queryOnlineStatus(loginID,  Integer.valueOf(channelID), onlineStatus, result);		
		if(0 == result.value){
			if(onlineStatus.value == 0){
				request.setAttribute("retString", "离线");	
			}else{
				request.setAttribute("retString", "在线");
			}			
			request.setAttribute(VideoPlayAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功！返回："+String.valueOf(result.value));	
			
		}else{
			request.setAttribute(VideoPlayAction.ERROR_MESSAGE_ATTR_NAME, "操作失败！返回："+String.valueOf(result.value));
		}
		
		return SUCCESS;
	}	
	
	public String queryBatteryVoltage(){
		list();
		Holder<Float> voltage = new Holder<Float>();
		Holder<Integer> result = new Holder<Integer>();
		CameraProxy service = new CameraProxy();
		CameraProxyPortType portType = service.getCameraProxy();
		portType.queryBatteryVoltage(loginID,  Integer.valueOf(channelID), voltage, result);		
		if(0 == result.value){			
			
			request.setAttribute("retString", "电源电压："+String.valueOf(voltage.value));						
			request.setAttribute(VideoPlayAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功！返回："+String.valueOf(result.value));
		}else{
			request.setAttribute(VideoPlayAction.ERROR_MESSAGE_ATTR_NAME, "操作失败！返回："+String.valueOf(result.value));
		}		
		return SUCCESS;
	}	
	
	 	
	public String queryRealPlayRtspUrl(){
		list();
		Holder<String> rtspUrl = new Holder<String>();
		Holder<Integer> result = new Holder<Integer>();
		CameraProxy service = new CameraProxy();
		CameraProxyPortType portType = service.getCameraProxy();
		portType.queryRealPlayRtspUrl(loginID,  Integer.valueOf(channelID), rtspUrl, result);		
		if(0 == result.value){			
			request.setAttribute("retString", "实时播放URL："+rtspUrl.value);						
			request.setAttribute(VideoPlayAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功！返回："+String.valueOf(result.value));
		}else{
			request.setAttribute(VideoPlayAction.ERROR_MESSAGE_ATTR_NAME, "操作失败！返回："+String.valueOf(result.value));
		}		
		return SUCCESS;
	}		
	
	public String queryRecordFile(){
		list();
		Holder<String> recordFileInfoList = new Holder<String>();
		Holder<Integer> result = new Holder<Integer>();
		String startTime = request.getParameter("startTime2");
		String endTime = request.getParameter("endTime2");	
		if(null == startTime || null == endTime){
			request.setAttribute(VideoPlayAction.ERROR_MESSAGE_ATTR_NAME, "请输入查询时间！");
			return SUCCESS;
		}
		request.setAttribute("startTime2", startTime);
		request.setAttribute("endTime2", endTime);
		CameraProxy service = new CameraProxy();
		CameraProxyPortType portType = service.getCameraProxy();
		portType.queryRecordFile(loginID,  Integer.valueOf(channelID), startTime, endTime, recordFileInfoList, result);		
		if(0 == result.value){
			String retString = recordFileInfoList.value;
			request.setAttribute(VideoPlayAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功！返回："+String.valueOf(result.value));	
			request.setAttribute("retString", retString);
		}else{
			request.setAttribute(VideoPlayAction.ERROR_MESSAGE_ATTR_NAME, "操作失败！返回："+String.valueOf(result.value));
		}
		
		return SUCCESS;
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
			request.setAttribute(VideoPlayAction.ERROR_MESSAGE_ATTR_NAME, "提交数据错误！");
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
			this.request.setAttribute(VideoPlayAction.SUCCESS_MESSAGE_ATTR_NAME, "保存成功！");
		}catch(BusinessException ex){
			switch (ex.getErrorCode()) {
			case ArchiveErrorCode.ARCHIVE_VIDEOSERVER_SENSOR_ALREADYEXISTS:
				this.request.setAttribute(VideoPlayAction.ERROR_MESSAGE_ATTR_NAME, "视频服务器已经存在");
				break;
			}
		}					 
		return SUCCESS;
	}
	 
 

	public void prepare() throws Exception {
		
	}

	/**
	 * @param presetIndex the presetIndex to set
	 */
	public void setPresetIndex(Integer presetIndex) {
		this.presetIndex = presetIndex;
	}

	/**
	 * @return the presetIndex
	 */
	public Integer getPresetIndex() {
		return presetIndex;
	}

	/**
	 * @param ptzCmd the ptzCmd to set
	 */
	public void setPtzCmd(Integer ptzCmd) {
		this.ptzCmd = ptzCmd;
	}

	/**
	 * @return the ptzCmd
	 */
	public Integer getPtzCmd() {
		return ptzCmd;
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
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "提交数据错误！");
			return INPUT;			
		}
		if(videoServer.getVideoServerCode() == null || "".equals(videoServer.getVideoServerCode())){
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请输入视频设备编码！");
			return INPUT;			
		}
		
		if(videoServer.getOpenTime() == null || videoServer.getOpenTime()<0){
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请打开时间的值错误，必需大于或等于0！");
			return INPUT;			
		}		
		try{
			
		
			if(!commService.openVideoPower( sensor.getSensorCode(), videoServer.getOpenTime())){
				this.request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "电源控制失败！");
			}else{
				this.request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "电源控制成功！");
			}
		}catch(Exception e){
			this.request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "电源控制出错！");
		}
						
		return SUCCESS;
	}
 
}
