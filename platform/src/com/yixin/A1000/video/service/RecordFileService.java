/*
 * Project platform
 *
 * Class RecordFileService.java
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
import java.util.Date;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.video.model.RecordFile;
import com.yixin.A1000.video.model.VideoServer;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;

/**
 * 录像文件业务逻辑接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface RecordFileService {

	/**
	 * 新增录像文件。当“录像文件编号”或”录像文件名称“已经存在时，抛出BusinessException异常。
	 * 
	 * @param RecordFile
	 *            要添加的录像文件
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_LINECODE_ALREADYEXISTS-“录像文件编号
	 *                ”已经存在<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_LINENAME_ALREADYEXISTS-”录像文件名称
	 *                ”已经存在
	 */
	public abstract void addRecordFile(RecordFile recordFile);

	/**
	 * 修改录像文件。当“录像文件编号”或”录像文件名称“已经存在时，抛出BusinessException异常。
	 * 
	 * @param RecordFile
	 *            要进行修改的录像文件
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_LINECODE_ALREADYEXISTS-“录像文件编号
	 *                ”已经存在<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_LINENAME_ALREADYEXISTS-”录像文件名称
	 *                ”已经存在
	 */
	public abstract void editRecordFile(RecordFile recordFile);

	/**
	 * 删除录像文件。当存在杆塔依赖于该录像文件时，抛出BusinessException异常。
	 * 
	 * 
	 * @param RecordFile
	 *            要进行删除的录像文件
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_EXISTSTOWERS-存在杆塔依赖于该录像文件
	 */
	public abstract void deleteRecordFile(RecordFile recordFile);

	/**
	 * 删除录像文件。当存在杆塔依赖于列表中任一录像文件时，抛出BusinessException异常。
	 * 
	 * @param RecordFiles
	 *            要进行删除的录像文件列表
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_EXISTSTOWERS-存在杆塔依赖于该录像文件
	 */
	public abstract void deleteRecordFiles(Collection<RecordFile> recordFiles);

	/**
	 * 根据ID查找录像文件
	 * 
	 * @param id
	 *            要查找录像文件的ID
	 * @return 录像文件
	 */
	public abstract RecordFile getRecordFile(String id);

	/**
	 * 返回所有录像文件
	 * 
	 * 
	 * @return 录像文件列表
	 */
	public abstract List<RecordFile> getAllRecordFiles();

	/**
	 * 返回第pageNo页的录像文件
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 录像文件列表
	 */
	public abstract Page<RecordFile> getPageRecordFiles(long pageNo, long pageSize);
	
	/**
	 * 根据视频服务器返回第pageNo页的录像文件
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 录像文件列表
	 */
	public abstract Page<RecordFile> getPageRecordFiles(VideoServer videoServer ,Integer filetype, Date beginTime,Date endTime, long pageNo, long pageSize);

	/**
	 * 根据视频服务器返回 录像文件
	 * @param sensor   视频服务器
	 */
	public abstract List<RecordFile> getRecordFileByVideoServer(VideoServer videoServer);
	
	/**
	 * 根据传感器返回第pageNo页的录像文件
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 录像文件列表
	 */
	public abstract Page<RecordFile> getPageRecordFiles(Sensor sensor ,Integer filetype,Date beginTime,Date endTime, long pageNo, long pageSize);
	
	/**
	 * 根据监测装置，时间，文件类型，查询录像文件列表。
	 * @param sensor
	 * @param filetype
	 * @param beginTime
	 * @param endTime
	 * @return 录像文件列表
	 */
	public abstract List<RecordFile> getRecordFiles(Sensor sensor ,Integer filetype,Date beginTime,Date endTime);
	
		

}
