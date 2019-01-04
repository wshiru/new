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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.video.model.RecordFile;
import com.yixin.A1000.video.model.VideoPlatform;
import com.yixin.A1000.video.model.VideoServer;
import com.yixin.A1000.video.service.RecordFileService;
import com.yixin.A1000.video.service.VideoServerService;
import com.yixin.framework.base.model.Page;

/**
 * 视频服务器模块Sction处理类
 * 
 * @version v1.0.0
 * @author
 */
public class PlaybackVideoAction extends ActionSupport implements
		ServletRequestAware, Preparable {

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

	/** 录像文件业务接口 */
	private RecordFileService recordFileService;

	/** 查询条件 */
	private Date beginTime;
	private Date endTime;
	private Integer fileType;

	/** 监测装置业务接口 */
	private SensorService sensorService;

	/** 视频平台信息。保存到request域的视频平台信息 */
	private VideoPlatform videoPlatform;

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
	 * 设置 视频服务器业务接口
	 * 
	 * @param videoServerService
	 *            视频服务器业务接口
	 */
	public void setVideoServerService(VideoServerService videoServerService) {
		this.videoServerService = videoServerService;
	}

	/**
	 * 设置 监测装置业务接口
	 * 
	 * @param sensorService
	 */
	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	/**
	 * @param 设置
	 *            录像文件业务接口
	 */
	public void setRecordFileService(RecordFileService recordFileService) {
		this.recordFileService = recordFileService;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
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
	

	public PlaybackVideoAction() {

		// 初始化时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -7);

		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
		String s1 = fd.format(calendar.getTime());
		Date d1 = calendar.getTime();

		try {
			d1 = fd.parse(s1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		beginTime = d1;

		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1);
		endTime = calendar.getTime();

	}

	/**
	 * 跳转到修改视频服务器页面
	 * 
	 * @return 结果页面
	 */
	public String list() {

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

		if (beginTime == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, -7);

			SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
			String s1 = fd.format(calendar.getTime());
			Date d1 = calendar.getTime();

			try {
				d1 = fd.parse(s1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			beginTime = d1;
		}
		if (endTime == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, 1);
			endTime = calendar.getTime();
		}
		if (null == this.id || 0 == this.id.trim().length()) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先选择要进行操作的对象");
			return SUCCESS;
		}
		Sensor sensor = this.sensorService.getSensor(this.id);
		if (null == sensor) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "监测装置不存在，可能已经被删除");
			return SUCCESS;
		}

		// this.videoServer =
		// this.videoServerService.getVideoServerBySensor(sensor);

		Page<RecordFile> page = this.recordFileService.getPageRecordFiles(
				sensor, fileType, beginTime, endTime, pageNo, pageSize);
		request.setAttribute("page", page);

		return SUCCESS;
	}

	public String main() {		
		int pageNo = 1;
		int pageSize = 20;
		

		if (beginTime == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, -7);

			SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
			String s1 = fd.format(calendar.getTime());
			Date d1 = calendar.getTime();

			try {
				d1 = fd.parse(s1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			beginTime = d1;
		}
		if (endTime == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, 1);
			endTime = calendar.getTime();
		}
		if (null == this.id || 0 == this.id.trim().length()) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先选择要进行操作的对象");
			return SUCCESS;
		}
		Sensor sensor = this.sensorService.getSensor(this.id);
		if (null == sensor) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "监测装置不存在，可能已经被删除");
			return SUCCESS;
		}

		Page<RecordFile> page = this.recordFileService.getPageRecordFiles(
				sensor, 1, beginTime, endTime, pageNo, pageSize);
		request.setAttribute("page", page);

		return SUCCESS;
	}

	public void prepare() throws Exception {

	}

}
