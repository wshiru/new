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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.yixin.A1000.archive.model.ArchiveErrorCode;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.video.dao.VideoServerDao;
import com.yixin.A1000.video.model.VideoServer;
import com.yixin.A1000.video.service.VideoServerService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;

/**
 * 视频服务器 逻辑具体实现
 * 
 * @version v1.0.0
 * @author 

 */
public class VideoServerServiceImpl implements VideoServerService {

	private VideoServerDao videoServerDao;
	
	public void setVideoServerDao(VideoServerDao videoServerDao){
		this.videoServerDao = videoServerDao;
	}
	
	@Override
	public void addVideoServer(VideoServer videoServer) {
		List<VideoServer> list = this.videoServerDao.getAllByProperty("sensor", videoServer.getSensor());
		if(list.size()>0){
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_VIDEOSERVER_SENSOR_ALREADYEXISTS);
		}
		this.videoServerDao.save(videoServer);
	}

	@Override
	public void editVideoServer(VideoServer videoServer) {
		List<VideoServer> list = this.videoServerDao.getAllByProperty("sensor", videoServer.getSensor());
		Iterator<VideoServer> iterator = list.iterator();
		while(iterator.hasNext()){
			VideoServer v = (VideoServer)iterator.next();
			if(!v.getVideoServerId().equals(videoServer.getVideoServerId())){
				throw new BusinessException(ArchiveErrorCode.ARCHIVE_VIDEOSERVER_SENSOR_ALREADYEXISTS);			
			}
		}
		this.videoServerDao.save(videoServer);
	}

	@Override
	public void deleteVideoServer(VideoServer videoServer) {
		this.videoServerDao.delete(videoServer);
	}

	@Override
	public void deleteVideoServers(Collection<VideoServer> videoServers) {
		this.videoServerDao.deleteAll(videoServers);
	}

	@Override
	public VideoServer getVideoServer(Integer id) {
		return this.videoServerDao.findById(id);
	}

	@Override
	public List<VideoServer> getAllVideoServers() {
		return this.videoServerDao.getAll();
	}

	@Override
	public Page<VideoServer> getPageVideoServers(long pageNo, long pageSize) {
		return this.videoServerDao.getPage(pageNo, pageSize);
	}

	@Override
	public VideoServer getVideoServerBySensor(Sensor sensor) {
		List<VideoServer> list = this.videoServerDao.getAllByProperty("sensor", sensor);
		if(list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public VideoServer getVideoServerByDeviceCode(String deviceCode) {
		List<VideoServer> list = this.videoServerDao.getAllByProperty("videoServerCode", deviceCode);
		if(list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	
}
