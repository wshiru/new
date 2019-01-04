/*
 * Project platform
 *
 * Class RecordPlanService.java
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

import com.yixin.A1000.video.model.RecordPlan;
import com.yixin.A1000.video.model.VideoServer;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;

/**
 * 视频录像计划业务逻辑接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface RecordPlanService {

	/**
	 * 新增视频录像计划。当“视频录像计划编号”或”视频录像计划名称“已经存在时，抛出BusinessException异常。
	 * 
	 * @param RecordPlan
	 *            要添加的视频录像计划
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_LINECODE_ALREADYEXISTS-“视频录像计划编号
	 *                ”已经存在<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_LINENAME_ALREADYEXISTS-”视频录像计划名称
	 *                ”已经存在
	 */
	public abstract void addRecordPlan(RecordPlan recordPlan);

	/**
	 * 修改视频录像计划。当“视频录像计划编号”或”视频录像计划名称“已经存在时，抛出BusinessException异常。
	 * 
	 * @param RecordPlan
	 *            要进行修改的视频录像计划
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_LINECODE_ALREADYEXISTS-“视频录像计划编号
	 *                ”已经存在<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_LINENAME_ALREADYEXISTS-”视频录像计划名称
	 *                ”已经存在
	 */
	public abstract void editRecordPlan(RecordPlan recordPlan);

	/**
	 * 删除视频录像计划。当存在杆塔依赖于该视频录像计划时，抛出BusinessException异常。
	 * 
	 * 
	 * @param RecordPlan
	 *            要进行删除的视频录像计划
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_EXISTSTOWERS-存在杆塔依赖于该视频录像计划
	 */
	public abstract void deleteRecordPlan(RecordPlan recordPlan);

	/**
	 * 删除视频录像计划。当存在杆塔依赖于列表中任一视频录像计划时，抛出BusinessException异常。
	 * 
	 * @param RecordPlans
	 *            要进行删除的视频录像计划列表
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_EXISTSTOWERS-存在杆塔依赖于该视频录像计划
	 */
	public abstract void deleteRecordPlans(Collection<RecordPlan> recordPlans);

	/**
	 * 根据ID查找视频录像计划
	 * 
	 * @param id
	 *            要查找视频录像计划的ID
	 * @return 视频录像计划
	 */
	public abstract RecordPlan getRecordPlan(String id);

	/**
	 * 返回所有视频录像计划
	 * 
	 * 
	 * @return 视频录像计划列表
	 */
	public abstract List<RecordPlan> getAllRecordPlans();

	/**
	 * 返回第pageNo页的视频录像计划
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 视频录像计划列表
	 */
	public abstract Page<RecordPlan> getPageRecordPlans(long pageNo, long pageSize);
	
	/**
	 * 根据视频服务器 返回第pageNo页的视频录像计划
	 * @param videoServer
	 * 			视频服务器
	 * @param pageNo
	 * 			页码
	 * @param pageSize
	 * 			页大小
	 * @return  
	 * 			视频录像计划列表
	 */	
	public abstract Page<RecordPlan> getPageRecordPlans(VideoServer videoServer, long pageNo, long pageSize);

	/**
	 * 返回根据 视频服务器查询视频录像计划
	 * @param videoServer   视频服务器
	 */
	public abstract List<RecordPlan> getRecordPlanByVideoServer(VideoServer videoServer);
	

}
