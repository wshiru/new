/*
 * Project platform
 *
 * Class TowerTypeService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-21 下午06:21:07
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

import com.yixin.A1000.archive.model.Tower;
import com.yixin.A1000.archive.model.TowerType;
import com.yixin.A1000.archive.model.TowerTypeQueryModel;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;

/**
 * 杆塔类型业务逻辑接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface TowerTypeService {

	/**
	 * 新增杆塔类型。当”杆塔类型编号”已经存在时，抛出BusinessException异常。
	 * 
	 * @param towerType
	 *            要添加的杆塔类型
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.
	 *                ARCHIVE_TOWERTYPE_TOWERTYPECODE_ALREADYEXISTS-”杆塔类型编号”已经存在
	 */
	public abstract void addTowerType(TowerType towerType);

	/**
	 * 修改杆塔类型。当”杆塔类型编号”已经存在时，抛出BusinessException异常。
	 * 
	 * @param towerType
	 *            要进行修改的杆塔类型
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.
	 *                ARCHIVE_TOWERTYPE_TOWERTYPECODE_ALREADYEXISTS-”杆塔类型编号”已经存在
	 */
	public abstract void editTowerType(TowerType towerType);

	/**
	 * 删除杆塔类型。当存在杆塔依赖于该杆塔类型时，抛出BusinessException异常。
	 * 
	 * 
	 * @param towerType
	 *            要进行删除的杆塔类型
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_TOWERTYPE_EXISTSTOWERS-
	 *                存在杆塔依赖于该杆塔类型
	 */
	public abstract void deleteTowerType(TowerType towerType);

	/**
	 * 删除杆塔类型。当存在杆塔依赖于列表中任一杆塔类型时，抛出BusinessException异常。
	 * 
	 * @param towerTypes
	 *            要进行删除的杆塔类型列表
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_TOWERTYPE_EXISTSTOWERS-
	 *                存在杆塔依赖于该杆塔类型
	 */
	public abstract void deleteTowerTypes(Collection<TowerType> towerTypes);

	/**
	 * 根据ID查找杆塔类型
	 * 
	 * @param id
	 *            要查找杆塔类型的ID
	 * @return 杆塔类型
	 */
	public abstract TowerType getTowerType(String id);

	/**
	 * 返回所有杆塔类型
	 * 
	 * 
	 * @return 杆塔类型列表
	 */
	public abstract List<TowerType> getAllTowerTypes();

	/**
	 * 返回第pageNo页的杆塔类型
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 杆塔类型列表
	 */
	public abstract Page<TowerType> getPageTowerTypes(long pageNo, long pageSize);

	/**
	 * 根据条件查找杆塔类型
	 * 
	 * @param towerTypeQueryModel
	 *            查询模型。在这里定义要过滤的条件，以及比较机制。各条件间使用“and”进行连接。
	 * @return 查找到的杆塔类型列表
	 */
	public abstract List<TowerType> getAllTowerTypes(TowerTypeQueryModel towerTypeQueryModel);

	/**
	 * 根据条件查找第pageNo页的杆塔类型
	 * 
	 * @param towerTypeQueryModel
	 *            查询模型。在这里定义要过滤的条件，以及比较机制。各条件间使用“and”进行连接。
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 查找到的杆塔类型列表
	 */
	public abstract Page<TowerType> getPageTowerTypes(TowerTypeQueryModel towerTypeQueryModel, long pageNo,
			long pageSize);

	/**
	 * 取得杆塔类型towerType下的所有杆塔
	 * 
	 * 
	 * @param towerType
	 *            杆塔类型
	 * @return 杆塔类型towerType下的所有杆塔
	 */
	public abstract List<Tower> getAllTowers(TowerType towerType);

	/**
	 * 取得杆塔类型towerType下第pageNo页的杆塔
	 * 
	 * @param towerType
	 *            杆塔类型
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 杆塔类型towerType下第pageNo页的杆塔
	 */
	public abstract Page<Tower> getPageTowers(TowerType towerType, long pageNo, long pageSize);
}
