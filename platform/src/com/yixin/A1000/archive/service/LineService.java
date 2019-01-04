/*
 * Project platform
 *
 * Class LineService.java
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
package com.yixin.A1000.archive.service;

import java.util.Collection;
import java.util.List;

import com.yixin.A1000.archive.model.Line;
import com.yixin.A1000.archive.model.LineQueryModel;
import com.yixin.A1000.archive.model.Tower;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.system.model.Dictionary;

/**
 * 线路业务逻辑接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface LineService {

	/**
	 * 新增线路。当“线路编号”或”线路名称“已经存在时，抛出BusinessException异常。
	 * 
	 * @param line
	 *            要添加的线路
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_LINECODE_ALREADYEXISTS-“线路编号
	 *                ”已经存在<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_LINENAME_ALREADYEXISTS-”线路名称
	 *                ”已经存在
	 */
	public abstract void addLine(Line line);

	/**
	 * 修改线路。当“线路编号”或”线路名称“已经存在时，抛出BusinessException异常。
	 * 
	 * @param line
	 *            要进行修改的线路
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_LINECODE_ALREADYEXISTS-“线路编号
	 *                ”已经存在<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_LINENAME_ALREADYEXISTS-”线路名称
	 *                ”已经存在
	 */
	public abstract void editLine(Line line);

	/**
	 * 删除线路。当存在杆塔依赖于该线路时，抛出BusinessException异常。
	 * 
	 * 
	 * @param line
	 *            要进行删除的线路
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_EXISTSTOWERS-存在杆塔依赖于该线路
	 */
	public abstract void deleteLine(Line line);

	/**
	 * 删除线路。当存在杆塔依赖于列表中任一线路时，抛出BusinessException异常。
	 * 
	 * @param lines
	 *            要进行删除的线路列表
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_LINE_EXISTSTOWERS-存在杆塔依赖于该线路
	 */
	public abstract void deleteLines(Collection<Line> lines);

	/**
	 * 根据ID查找线路
	 * 
	 * @param id
	 *            要查找线路的ID
	 * @return 线路
	 */
	public abstract Line getLine(String id);

	/**
	 * 返回所有线路
	 * 
	 * 
	 * @return 线路列表
	 */
	public abstract List<Line> getAllLines();

	/**
	 * 返回第pageNo页的线路
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 线路列表
	 */
	public abstract Page<Line> getPageLines(long pageNo, long pageSize);

	/**
	 * 根据条件查找线路
	 * 
	 * @param lineQueryModel
	 *            查询模型。在这里定义要过滤的条件，以及比较机制。各条件间使用“and”进行连接。
	 * @return 查找到的线路列表
	 */
	public abstract List<Line> getAllLines(LineQueryModel lineQueryModel);

	/**
	 * 根据条件查找第pageNo页的线路
	 * 
	 * @param lineQueryModel
	 *            查询模型。在这里定义要过滤的条件，以及比较机制。各条件间使用“and”进行连接。
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 查找到的线路列表
	 */
	public abstract Page<Line> getPageLines(LineQueryModel lineQueryModel, long pageNo, long pageSize);

	/**
	 * 取得线路line下的所有杆塔
	 * 
	 * 
	 * @param line
	 *            线路
	 * @return 线路line下的所有杆塔
	 */
	public abstract List<Tower> getAllTowers(Line line);

	/**
	 * 取得线路line下第pageNo页的杆塔
	 * 
	 * @param line
	 *            线路
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 线路line下第pageNo页的杆塔
	 */
	public abstract Page<Tower> getPageTowers(Line line, long pageNo, long pageSize);

	/**
	 * 返回线路电压的所有可选值
	 * 
	 * @return 线路电压的所有可选值
	 */
	public abstract List<Dictionary> getAllVoltageLevels();
}
