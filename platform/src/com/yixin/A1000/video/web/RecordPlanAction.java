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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.video.model.RecordPlan;
import com.yixin.A1000.video.model.VideoServer;
import com.yixin.A1000.video.service.RecordPlanService;
import com.yixin.A1000.video.service.VideoServerService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;

/**
 * 视频服务器模块Sction处理类
 * 
 * @version v1.0.0
 * @author 
 */
public class RecordPlanAction extends ActionSupport implements ServletRequestAware, Preparable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -8234235709857L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 视频服务器业务接口 */
	private VideoServerService videoServerService;
	
	/** 视频录像计划业务接口 */
	private RecordPlanService recordPlanService;
	
	/** 监测装置业务接口 */
	private SensorService sensorService;
	
	/** 录像计划信息*/
	private RecordPlan recordPlan;
	
	/** 监测装置信息*/
	private Sensor sensor;

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
	 * 设置 视频录像计划业务接口
	 * @param sensorService
	 */	
	public void setRecordPlanService(RecordPlanService recordPlanService){
		this.recordPlanService = recordPlanService;
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
	
	private Boolean checkSensor(){
		if (null == this.id || 0 == this.id.trim().length()) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先选择要进行操作的对象");
			return false;
		}
		sensor = this.sensorService.getSensor(this.id);
		
		if (null == sensor) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "监测装置不存在，可能已经被删除");
			return false;
		}
		this.videoServer = this.videoServerService.getVideoServerBySensor(sensor);
		if(null == videoServer){
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先配置视频服务器");
			return false;
		}		
		return true;
	}	
	
	public String list(){
		String pageNoStr = request.getParameter("pn");
		String pageSizeStr = request.getParameter("ps");
		int pageNo = 1;
		int pageSize = 20;
		if (pageNoStr != null && !pageNoStr.equals("")) {
			pageNo = Integer.parseInt(pageNoStr);
		}
		if (pageSizeStr != null && !pageSizeStr.equals("")) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		
		if(!checkSensor()){
			return SUCCESS;
		}

		
		Page<RecordPlan> page = this.recordPlanService.getPageRecordPlans(videoServer, pageNo, pageSize);
		
		request.setAttribute("page", page);
		return SUCCESS;
	}
	

	
	public String add(){
		if(!checkSensor()){
			return INPUT;
		}		
		return SUCCESS;
	}
	
	public String addSave(){
		if(!checkSensor()){
			return SUCCESS;
		}				
		if(recordPlan == null){
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "提交数据错误");
			return INPUT;
		}
		String startTime = (String)request.getParameter("startTime");
		if(startTime == null){
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请输入任务开始时间");
			return INPUT;
		}		
		
		
		if(recordPlan.getOperatorType() == null){
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请选择操作类型");
			return INPUT;
		}		
		
		switch (recordPlan.getOperatorType()) {
		case 0:
			if(null == recordPlan.getOpenTime()){
				this.request.setAttribute(RecordPlanAction.ERROR_MESSAGE_ATTR_NAME, "请输入打开电源时间");
				return INPUT;
			}				
			break;
		case 2:
			if(null == recordPlan.getRecordTime()){
				this.request.setAttribute(RecordPlanAction.ERROR_MESSAGE_ATTR_NAME, "请输入录像时间");
				return INPUT;
			}				
			break;
			
		case 4:
			if(null == recordPlan.getPresetting()){
				this.request.setAttribute(RecordPlanAction.ERROR_MESSAGE_ATTR_NAME, "请输入预置位");
				return INPUT;
			}				
			break;				
		}
		
		//取得当前时间
		String s1 = "1970-01-01 "+startTime;
		Date d1 = new Date();

		try {
			SimpleDateFormat fd1 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
			d1 = fd1.parse(s1);
		} catch (ParseException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请任务开始时间格式错误");
			return INPUT;
		}		
		recordPlan.setStartTime(d1);		
		try{
			RecordPlan rp = new RecordPlan();
			rp.setVideoServer(videoServer);
			rp.setStartTime(recordPlan.getStartTime());
			rp.setChannelNo(recordPlan.getChannelNo());
			rp.setOperatorType(recordPlan.getOperatorType());
			rp.setOpenTime(recordPlan.getOpenTime());
			rp.setRecordTime(recordPlan.getRecordTime());
			rp.setPresetting(recordPlan.getPresetting());
			recordPlan = rp;			
			this.recordPlanService.addRecordPlan(recordPlan);						
			this.request.setAttribute(RecordPlanAction.SUCCESS_MESSAGE_ATTR_NAME, "保存成功！");
		}catch(BusinessException ex){
			this.request.setAttribute(RecordPlanAction.ERROR_MESSAGE_ATTR_NAME, "保存失败");
		}	
		
		list();
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
			return INPUT;
		}
		this.recordPlan = this.recordPlanService.getRecordPlan(this.id);
		if (null == recordPlan) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "计划信息不存在，可能已经被删除");
			return SUCCESS;
		}
		this.videoServer = this.recordPlan.getVideoServer();
		Sensor sensor = this.recordPlan.getVideoServer().getSensor();
		if (null == sensor) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "监测装置不存在，可能已经被删除");
			return SUCCESS;
		}
		this.id = sensor.getSensorId();	
		
		
				
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
		if(this.recordPlan == null){
			request.setAttribute(RecordPlanAction.ERROR_MESSAGE_ATTR_NAME, "提交数据错误！");
			return INPUT;			
		}				
		String startTime = (String)request.getParameter("startTime");
		if(startTime == null){
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请输入任务开始时间");
			return INPUT;
		}		
		
		
		
		
		if(recordPlan.getOperatorType() == null){
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请选择操作类型");
			return INPUT;
		}
		
		switch (recordPlan.getOperatorType()) {
			case 0:
				if(null == recordPlan.getOpenTime()){
					this.request.setAttribute(RecordPlanAction.ERROR_MESSAGE_ATTR_NAME, "请输入打开电源时间");
					return INPUT;
				}				
				break;
			case 2:
				if(null == recordPlan.getRecordTime()){
					this.request.setAttribute(RecordPlanAction.ERROR_MESSAGE_ATTR_NAME, "请输入录像时间");
					return INPUT;
				}				
				break;
				
			case 4:
				if(null == recordPlan.getPresetting()){
					this.request.setAttribute(RecordPlanAction.ERROR_MESSAGE_ATTR_NAME, "请输入预置位");
					return INPUT;
				}				
				break;				
		}
		
		//取得当前时间
		String s1 = "1970-01-01 "+startTime;
		Date d1 = new Date();

		try {
			SimpleDateFormat fd1 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
			d1 = fd1.parse(s1);
		} catch (ParseException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请任务开始时间格式错误");
			return INPUT;
		}	
		
		recordPlan.setStartTime(d1);
		
		try{
			RecordPlan rp = this.recordPlanService.getRecordPlan(recordPlan.getRecordPlanId());
			rp.setStartTime(recordPlan.getStartTime());
			rp.setChannelNo(recordPlan.getChannelNo());
			rp.setOperatorType(recordPlan.getOperatorType());
			rp.setOpenTime(recordPlan.getOpenTime());
			rp.setRecordTime(recordPlan.getRecordTime());
			rp.setPresetting(recordPlan.getPresetting());
			recordPlan = rp;
			this.recordPlanService.editRecordPlan(recordPlan);						
			this.request.setAttribute(RecordPlanAction.SUCCESS_MESSAGE_ATTR_NAME, "保存成功！");
		}catch(BusinessException ex){
			this.request.setAttribute(RecordPlanAction.ERROR_MESSAGE_ATTR_NAME, "保存失败");
		}		
		list();
		return SUCCESS;
	}
	 
	public String delete(){
		if (null == this.id || 0 == this.id.trim().length()) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先选择要进行操作的对象");
			return INPUT;
		}
		this.recordPlan = this.recordPlanService.getRecordPlan(this.id);
		if (null == recordPlan) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "计划信息不存在，可能已经被删除");
			return SUCCESS;
		}
		this.videoServer = this.recordPlan.getVideoServer();
		Sensor sensor = this.recordPlan.getVideoServer().getSensor();
		if (null == sensor) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "监测装置不存在，可能已经被删除");
			return SUCCESS;
		}
		this.id = sensor.getSensorId();
		try{
			this.recordPlanService.deleteRecordPlan(recordPlan);
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "删除成功");			
		}
		catch(Exception e){
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "删除操作错误");
		}
				
		list();
		return SUCCESS;
	}
	
	public String copyToAll(){
		if(!checkSensor()){
			return SUCCESS;
		}	
		List<RecordPlan> rpSelf = this.recordPlanService.getRecordPlanByVideoServer(videoServer);
		
		List<RecordPlan> rpAlls = this.recordPlanService.getAllRecordPlans();
		List<RecordPlan> deleteRpAlls = new ArrayList<RecordPlan>();
		Iterator<RecordPlan> iterator = rpAlls.iterator();
		while(iterator.hasNext()){
			RecordPlan rp = iterator.next();
			if(rp.getVideoServer()==null || !videoServer.getVideoServerId().equals(rp.getVideoServer().getVideoServerId())){
				deleteRpAlls.add(rp);
			}			
		}
		if(deleteRpAlls.size()>0){
			this.recordPlanService.deleteRecordPlans(deleteRpAlls);	
		}
		
		
		List<VideoServer> vsAlls = this.videoServerService.getAllVideoServers();
		Iterator<VideoServer> it = vsAlls.iterator();
		rpAlls.clear();
		while(it.hasNext()){
			VideoServer vs = it.next();
			if(!vs.getVideoServerId().equals(this.videoServer.getVideoServerId())){
				//添加
				Iterator<RecordPlan> rpIt = rpSelf.iterator();
				while(rpIt.hasNext()){
					RecordPlan rp1 = rpIt.next();
					RecordPlan rp2 = new RecordPlan();
					rp2.setVideoServer(vs);
					rp2.setStartTime(rp1.getStartTime());
					rp2.setOperatorType(rp1.getOperatorType());
					rp2.setOpenTime(rp1.getOpenTime());
					rp2.setRecordTime(rp1.getRecordTime());
					rp2.setPresetting(rp1.getPresetting());										
					
					this.recordPlanService.addRecordPlan(rp2);
				}				
			}
		}
		
		request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "复制成功");		
		list();
		return SUCCESS;
	}
	
	public void prepare() throws Exception {
		
	}

	/**
	 * @param recordPlan the recordPlan to set
	 */
	public void setRecordPlan(RecordPlan recordPlan) {
		this.recordPlan = recordPlan;
	}

	/**
	 * @return the recordPlan
	 */
	public RecordPlan getRecordPlan() {
		return recordPlan;
	}

 
}
