/*
 * Project platform
 *
 * Class VideoPlatformService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-21 下午04:33:08
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.video.service;

import java.util.Collection;
import java.util.List;

import com.yixin.A1000.archive.model.Tower;
import com.yixin.A1000.video.model.VideoPlatform;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.system.model.Dictionary;

/**
 * 视频平台业务逻辑接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface VideoPlatformService {

	/**
	 * 新增视频平台。当“视频平台编号”或”视频平台名称“已经存在时，抛出BusinessException异常。
	 * 
	 * @param videoPlatform
	 *            要添加的视频平台
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ARCHIVE_VIDEOPLATFORM_NAME_ALREADYEXISTS-”视频平台名称
	 *                ”已经存在
	 */
	public abstract void addVideoPlatform(VideoPlatform videoPlatform);

	/**
	 * 修改视频平台。当“视频平台编号”或”视频平台名称“已经存在时，抛出BusinessException异常。
	 * 
	 * @param videoPlatform
	 *            要进行修改的视频平台
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ARCHIVE_VIDEOPLATFORM_NAME_ALREADYEXISTS-”视频平台名称
	 *                ”已经存在
	 */
	public abstract void editVideoPlatform(VideoPlatform videoPlatform);

	/**
	 * 删除视频平台。当存在杆塔依赖于该视频平台时，抛出BusinessException异常。
	 * 
	 * 
	 * @param videoPlatform
	 *            要进行删除的视频平台
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 */
	public abstract void deleteVideoPlatform(VideoPlatform videoPlatform);

	/**
	 * 删除视频平台。当存在杆塔依赖于列表中任一视频平台时，抛出BusinessException异常。
	 * 
	 * @param videoPlatforms
	 *            要进行删除的视频平台列表
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 */
	public abstract void deleteVideoPlatforms(Collection<VideoPlatform> videoPlatforms);

	/**
	 * 根据ID查找视频平台
	 * 
	 * @param id
	 *            要查找视频平台的ID
	 * @return 视频平台
	 */
	public abstract VideoPlatform getVideoPlatform(String id);

	/**
	 * 返回所有视频平台
	 * 
	 * 
	 * @return 视频平台列表
	 */
	public abstract List<VideoPlatform> getAllVideoPlatforms();

	/**
	 * 返回第pageNo页的视频平台
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 视频平台列表
	 */
	public abstract Page<VideoPlatform> getPageVideoPlatforms(long pageNo, long pageSize);


}
