/*
 * Project platform
 *
 * Class PictureService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-21 下午07:42:09
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.service;

import java.util.Collection;
import java.util.List;

import com.yixin.A1000.archive.model.Picture;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;

/**
 * 安装图片业务逻辑接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface PictureService {

	/**
	 * 新增安装图片。
	 * 
	 * @param picture
	 *            要添加的安装图片
	 */
	public abstract void addPicture(Picture picture);

	/**
	 * 修改安装图片。当”安装图片编码”已经存在时，抛出BusinessException异常。
	 * 
	 * @param picture
	 *            要进行修改的安装图片
	 */
	public abstract void editPicture(Picture picture);

	/**
	 * 删除安装图片。当存在监测装置依赖于该安装图片时，抛出BusinessException异常。
	 * 
	 */
	public abstract void deletePicture(Picture picture);

	/**
	 * 删除安装图片。当存在监测装置依赖于列表中任一安装图片时，抛出BusinessException异常。
	 * 
	 * @param pictures
	 *            要进行删除的安装图片列表
	 */
	public abstract void deletePictures(Collection<Picture> pictures);

	/**
	 * 根据ID查找安装图片
	 * 
	 * @param id
	 *            要查找安装图片的ID
	 * @return 安装图片
	 */
	public abstract Picture getPicture(String id);

	/**
	 * 返回所有安装图片
	 * 
	 * 
	 * @return 安装图片列表
	 */
	public abstract List<Picture> getAllPictures();

 
	/**
	 * 取得所有的安装图片
	 * @param sensor
	 * 			监测装置
	 * @return  图片列表
	 */
 
	public abstract List<Picture> getAllPictures(Sensor sensor);	
	
	/**
	 * 返回第pageNo页的安装图片
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 安装图片列表
	 */
	public abstract Page<Picture> getPagePictures(Sensor sensor, long pageNo, long pageSize);
	
	/**
	 * 返回默认的显示图片，当未配置默认的时，显示第一个图片
	 * @param sensor
	 * 		监测装置
	 * @return	图片
	 */
	public abstract Picture getDefaultShowPicture(Sensor sensor,Integer showType);	

	 



 
}
