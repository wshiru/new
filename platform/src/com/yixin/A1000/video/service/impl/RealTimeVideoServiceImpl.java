/*
 * Project platform
 *
 * Class LineServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-6-22 下午12:06:55
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.video.service.impl;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.comm.CommService;
import com.yixin.A1000.video.service.RealTimeVideoService;
import com.yixin.A1000.video.service.VideoPlatformService;
import com.yixin.A1000.video.service.VideoServerService;

/**
 * 视频服务器 逻辑具体实现
 * 
 * @version v1.0.0
 * @author 

 */
public class RealTimeVideoServiceImpl implements RealTimeVideoService {

	/** 视频服务器  */
	private VideoServerService videoServerService;
	/** 视频平台 */
	private VideoPlatformService videoPlatformService;
	/** 通讯服务类 */
	private CommService commService;
	
	public void setVideoServerService(VideoServerService videoServerService){
		this.videoServerService = videoServerService;
	}
	
	public void setVideoPlatformService(VideoPlatformService videoPlatformService){
		this.videoPlatformService = videoPlatformService;
	}
	
	public void setCommService(CommService commService){
		this.commService = commService;
	}

	@Override
	public boolean openPower(Sensor sensor, Integer minute) {
		return commService.openVideoPower(sensor.getSensorCode(),minute);
	}
		
}
