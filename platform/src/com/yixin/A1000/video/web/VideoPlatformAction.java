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
import com.yixin.A1000.video.model.VideoPlatform;
import com.yixin.A1000.video.service.VideoPlatformService;
import com.yixin.framework.exception.BusinessException;

/**
 * 视频平台模块Sction处理类
 * 
 * @version v1.0.0
 * @author 
 */
public class VideoPlatformAction extends ActionSupport implements ServletRequestAware {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -81546765955486357L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 视频平台业务接口 */
	private VideoPlatformService videoPlatformService;


	/** 视频平台信息。保存新增视频平台提交的数据，及保存到request域的视频平台信息等 */
	private VideoPlatform videoPlatform;

	/** 视频平台ID，在修改、删除时用到 */
	private String id;

	

	/**
	 * 获取 视频平台信息。保存新增视频平台提交的数据，及保存到request域的视频平台信息等
	 * 
	 * @return 视频平台信息
	 */
	public VideoPlatform getVideoPlatform() {
		return videoPlatform;
	}

	/**
	 * 设置 视频平台信息。保存新增视频平台提交的数据，及保存到request域的视频平台信息等
	 * 
	 * @param line
	 *            视频平台信息
	 */
	public void setVideoPlatform(VideoPlatform videoPlatform) {
		this.videoPlatform = videoPlatform;
	}


	/**
	 * 设置 视频平台业务接口
	 * 
	 * @param lineService
	 *            视频平台业务接口
	 */
	public void setVideoPlatformService(VideoPlatformService videoPlatformService) {
		this.videoPlatformService = videoPlatformService;
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
	 * 跳转到修改视频平台页面
	 * 
	 * @return 结果页面
	 */
	public String edit() {	
		List<VideoPlatform> list = this.videoPlatformService.getAllVideoPlatforms();
		if(list.size()>0){
			this.videoPlatform = list.get(0);
		}else{
			this.videoPlatform = new VideoPlatform();
		}		
		return SUCCESS;
	}

	/**
	 * 保存修改视频平台信息
	 * 
	 * @return 结果页面
	 */
	public String editSave() {
		
		if(videoPlatform == null){
			request.setAttribute(VideoPlatformAction.ERROR_MESSAGE_ATTR_NAME, "提交数据错误！");
			return INPUT;			
		}
				
		try{
			//videoPlatform.setUsed(1);
			if(videoPlatform.getVideoPlatformId() == null){
				this.videoPlatformService.addVideoPlatform(videoPlatform);
			}else{
				this.videoPlatformService.editVideoPlatform(videoPlatform);
			}
			this.request.setAttribute(VideoPlatformAction.SUCCESS_MESSAGE_ATTR_NAME, "保存成功！");
		}catch(BusinessException ex){
			switch (ex.getErrorCode()) {
			case ArchiveErrorCode.ARCHIVE_VIDEOPLATFORM_NAME_ALREADYEXISTS:
				this.request.setAttribute(VideoPlatformAction.ERROR_MESSAGE_ATTR_NAME, "视频平台名称已经存在");
				break;
			}
		}				 
		return SUCCESS;
	}


}
