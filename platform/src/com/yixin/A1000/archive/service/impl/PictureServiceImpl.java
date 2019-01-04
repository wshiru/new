/*
 * Project platform
 *
 * Class TowerTypeServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-22 下午02:31:49
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.dao.PictureDao;
import com.yixin.A1000.archive.model.Picture;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.PictureService;
import com.yixin.framework.base.model.Page;

/**
 * 安装图片业务逻辑具体实现
 * 
 * @version v1.0.0
 * @author 
 */
public class PictureServiceImpl implements PictureService {


	/** 安装图片DAO接口对象 */
	private PictureDao pictureDao;

	/**
	 * 设置 安装图片DAO接口对象
	 * 
	 * @param towerTypeDao
	 *            安装图片DAO接口对象
	 */
	public void setPictureDao(PictureDao pictureDao) {
		this.pictureDao = pictureDao;
	}

	@Override
	public void addPicture(Picture picture) {
		this.pictureDao.save(picture);		
	}

	@Override
	public void editPicture(Picture picture) {
		this.pictureDao.update(picture);
	}

	@Override
	public void deletePicture(Picture picture) {
		this.pictureDao.delete(picture);
	}

	@Override
	public void deletePictures(Collection<Picture> pictures) {
		this.pictureDao.deleteAll(pictures);
	}

	@Override
	public Picture getPicture(String id) {		
		return this.pictureDao.findById(id);
	}

	@Override
	public List<Picture> getAllPictures() {
		return this.pictureDao.getAll();
	}

	@Override
	public List<Picture> getAllPictures(Sensor sensor) {
		return this.pictureDao.getAllByProperty("sensor", sensor);
	}

	@Override
	public Page<Picture> getPagePictures(Sensor sensor, long pageNo,
			long pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(sensor == null){
			return null;
		}else{
			map.put("sensor", sensor);
		}			
		return this.pictureDao.getPageByProperties(map, pageNo, pageSize);
	}

	@Override
	public Picture getDefaultShowPicture(Sensor sensor, Integer showType) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(sensor == null){
			return null;
		}else{
			map.put("sensor", sensor);
			map.put("defaultShow", showType);
		}			
		List<Picture> pictures = this.pictureDao.getAllByProperties(map);
		if(pictures.size() > 0){
			return pictures.get(0);
		}		
		map.clear();
		map.put("sensor", sensor);
		pictures = this.pictureDao.getAllByProperties(map);
		if(pictures.size() > 0){
			return pictures.get(0);
		}else{
			return null;
		}		
	}
}
