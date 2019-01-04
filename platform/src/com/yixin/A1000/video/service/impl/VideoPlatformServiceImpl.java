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
import com.yixin.A1000.video.dao.VideoPlatformDao;
import com.yixin.A1000.video.model.VideoPlatform;
import com.yixin.A1000.video.service.VideoPlatformService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;

/**
 * 视频平台业务逻辑具体实现
 * 
 * @version v1.0.0
 * @author 

 */
public class VideoPlatformServiceImpl implements VideoPlatformService {

	private VideoPlatformDao videoPlatformDao;
	
	public void setVideoPlatformDao(VideoPlatformDao videoPlatformDao){
		 this.videoPlatformDao = videoPlatformDao;
	}
	
	@Override
	public void addVideoPlatform(VideoPlatform videoPlatform) {
		List<VideoPlatform> list = this.videoPlatformDao.getAllByProperty("name", videoPlatform.getName());
		if (list.size() > 0) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_VIDEOPLATFORM_NAME_ALREADYEXISTS);
		}		 
		this.videoPlatformDao.save(videoPlatform);
	}

	@Override
	public void editVideoPlatform(VideoPlatform videoPlatform) {
		List<VideoPlatform> list = this.videoPlatformDao.getAllByProperty("name", videoPlatform.getName());
		Iterator<VideoPlatform> iterator = list.iterator();
		while (iterator.hasNext()) {
			VideoPlatform l = iterator.next();
			if (!l.getVideoPlatformId().equals(videoPlatform.getVideoPlatformId())) {
				throw new BusinessException(ArchiveErrorCode.ARCHIVE_VIDEOPLATFORM_NAME_ALREADYEXISTS);
			}
		}
		this.videoPlatformDao.save(videoPlatform);
	}

	@Override
	public void deleteVideoPlatform(VideoPlatform videoPlatform) {
		this.videoPlatformDao.delete(videoPlatform);
	}

	@Override
	public void deleteVideoPlatforms(Collection<VideoPlatform> videoPlatforms) {
		this.videoPlatformDao.deleteAll(videoPlatforms);
	}

	@Override
	public VideoPlatform getVideoPlatform(String id) {
		return this.videoPlatformDao.findById(id);
	}

	@Override
	public List<VideoPlatform> getAllVideoPlatforms() {
		return this.videoPlatformDao.getAll();
	}

	@Override
	public Page<VideoPlatform> getPageVideoPlatforms(long pageNo, long pageSize) {
		return videoPlatformDao.getPage(pageNo, pageSize);
	}

}
