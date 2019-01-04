/*
 * Project platform
 *
 * Class VideoServerService.java
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

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.video.model.VideoServer;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;

/**
 * 视频服务器业务逻辑接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface VideoServerService {

	/**
	 * 新增视频服务器。当“视频服务器编号”或”视频服务器名称“已经存在时，抛出BusinessException异常。
	 * 
	 * @param videoServer
	 *            要添加的视频服务器
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_LINECODE_ALREADYEXISTS-“视频服务器编号
	 *                ”已经存在<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_LINENAME_ALREADYEXISTS-”视频服务器名称
	 *                ”已经存在
	 */
	public abstract void addVideoServer(VideoServer videoServer);

	/**
	 * 修改视频服务器。当“视频服务器编号”或”视频服务器名称“已经存在时，抛出BusinessException异常。
	 * 
	 * @param videoServer
	 *            要进行修改的视频服务器
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_LINECODE_ALREADYEXISTS-“视频服务器编号
	 *                ”已经存在<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_LINENAME_ALREADYEXISTS-”视频服务器名称
	 *                ”已经存在
	 */
	public abstract void editVideoServer(VideoServer videoServer);

	/**
	 * 删除视频服务器。当存在杆塔依赖于该视频服务器时，抛出BusinessException异常。
	 * 
	 * 
	 * @param videoServer
	 *            要进行删除的视频服务器
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_EXISTSTOWERS-存在杆塔依赖于该视频服务器
	 */
	public abstract void deleteVideoServer(VideoServer videoServer);

	/**
	 * 删除视频服务器。当存在杆塔依赖于列表中任一视频服务器时，抛出BusinessException异常。
	 * 
	 * @param videoServers
	 *            要进行删除的视频服务器列表
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_EXISTSTOWERS-存在杆塔依赖于该视频服务器
	 */
	public abstract void deleteVideoServers(Collection<VideoServer> videoServers);

	/**
	 * 根据ID查找视频服务器
	 * 
	 * @param id
	 *            要查找视频服务器的ID
	 * @return 视频服务器
	 */
	public abstract VideoServer getVideoServer(Integer id);

	/**
	 * 返回所有视频服务器
	 * 
	 * 
	 * @return 视频服务器列表
	 */
	public abstract List<VideoServer> getAllVideoServers();

	/**
	 * 返回第pageNo页的视频服务器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 视频服务器列表
	 */
	public abstract Page<VideoServer> getPageVideoServers(long pageNo, long pageSize);

	/**
	 * 返回根据监测装置视频服务器
	 * @param sensor   监测装置
	 */
	public abstract VideoServer getVideoServerBySensor(Sensor sensor);
	
	/**
	 * 根据摄像机ID查询视频服务器
	 * @param cameraCode
	 * @return
	 */
	public abstract VideoServer getVideoServerByDeviceCode(String deviceCode);
}
