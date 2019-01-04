/*
 * Project platform
 *
 * Class TowerService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-21 下午07:33:27
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

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.model.Tower;
import com.yixin.A1000.archive.model.TowerQueryModel;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;

/**
 * 杆塔业务逻辑接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface TowerService {

	/**
	 * 新增杆塔。当”杆塔编号”已经存在时，抛出BusinessException异常。
	 * 
	 * @param tower
	 *            要添加的杆塔
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_TOWER_TOWERCODE_ALREADYEXISTS-”
	 *                杆塔编号”已经存在
	 */
	public abstract void addTower(Tower tower);

	/**
	 * 修改杆塔。当”杆塔编号”已经存在时，抛出BusinessException异常。
	 * 
	 * @param tower
	 *            要进行修改的杆塔
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_TOWER_TOWERCODE_ALREADYEXISTS-”
	 *                杆塔编号”已经存在
	 */
	public abstract void editTower(Tower tower);

	/**
	 * 删除杆塔。当存在监测装置依赖于该杆塔时，抛出BusinessException异常。
	 * 
	 * 
	 * @param tower
	 *            要进行删除的杆塔
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_TOWER_EXISTSSENSORS-存在监测装置依赖于该杆塔
	 */
	public abstract void deleteTower(Tower tower);

	/**
	 * 删除杆塔。当存在监测装置依赖于列表中任一杆塔时，抛出BusinessException异常。
	 * 
	 * @param towers
	 *            要进行删除的杆塔列表
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_TOWER_EXISTSSENSORS-存在监测装置依赖于该杆塔
	 */
	public abstract void deleteTowers(Collection<Tower> towers);

	/**
	 * 根据ID查找杆塔
	 * 
	 * @param id
	 *            要查找杆塔的ID
	 * @return 杆塔
	 */
	public abstract Tower getTower(String id);

	/**
	 * 返回所有杆塔
	 * 
	 * 
	 * @return 杆塔列表
	 */
	public abstract List<Tower> getAllTowers();

	/**
	 * 返回第pageNo页的杆塔
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 杆塔列表
	 */
	public abstract Page<Tower> getPageTowers(long pageNo, long pageSize);

	/**
	 * 根据条件查找杆塔
	 * 
	 * @param towerQueryModel
	 *            查询模型。在这里定义要过滤的条件，以及比较机制。各条件间使用“and”进行连接。
	 * @return 查找到的杆塔列表
	 */
	public abstract List<Tower> getAllTowers(TowerQueryModel towerQueryModel);

	/**
	 * 根据条件查找第pageNo页的杆塔
	 * 
	 * @param towerQueryModel
	 *            查询模型。在这里定义要过滤的条件，以及比较机制。各条件间使用“and”进行连接。
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 查找到的杆塔列表
	 */
	public abstract Page<Tower> getPageTowers(TowerQueryModel towerQueryModel, long pageNo, long pageSize);

	/**
	 * 取得杆塔tower下的所有监测装置
	 * 
	 * 
	 * @param tower
	 *            杆塔
	 * @return 杆塔tower下的所有监测装置
	 */
	public abstract List<Sensor> getAllSensors(Tower tower);

	/**
	 * 取得杆塔tower下第pageNo页的监测装置
	 * 
	 * @param tower
	 *            杆塔
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 杆塔tower下第pageNo页的监测装置
	 */
	public abstract Page<Sensor> getPageSensors(Tower tower, long pageNo, long pageSize);
	
	
}
