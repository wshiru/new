/*
 * Project platform
 *
 * Class CmaInfoService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-21 下午05:57:09
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

import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.CmaInfoQueryModel;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;

/**
 * 监测代理业务逻辑接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface CmaInfoService {

	/**
	 * 新增监测代理。当”监测代理编码”已经存在时，抛出BusinessException异常。
	 * 
	 * @param cmaInfo
	 *            要添加的监测代理
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_CMAINFO_CMACODE_ALREADYEXISTS-”
	 *                监测代理编码”已经存在
	 */
	public abstract void addCmaInfo(CmaInfo cmaInfo);

	/**
	 * 修改监测代理。当”监测代理编码”已经存在时，抛出BusinessException异常。
	 * 
	 * @param cmaInfo
	 *            要进行修改的监测代理
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_CMAINFO_CMACODE_ALREADYEXISTS-”
	 *                监测代理编码”已经存在
	 */
	public abstract void editCmaInfo(CmaInfo cmaInfo);

	/**
	 * 删除监测代理。当存在监测装置依赖于该监测代理时，抛出BusinessException异常。
	 * 
	 * @param cmaInfo
	 *            要进行删除的监测代理
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_CMAINFO_EXISTSSENSORS-
	 *                存在监测装置依赖于该监测代理
	 */
	public abstract void deleteCmaInfo(CmaInfo cmaInfo);

	/**
	 * 删除监测代理。当存在监测装置依赖于列表中任一监测代理时，抛出BusinessException异常。
	 * 
	 * @param cmaInfos
	 *            要进行删除的监测代理列表
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_CMAINFO_EXISTSSENSORS-
	 *                存在监测装置依赖于该监测代理
	 */
	public abstract void deleteCmaInfos(Collection<CmaInfo> cmaInfos);

	/**
	 * 根据ID查找监测代理
	 * 
	 * @param id
	 *            要查找监测代理的ID
	 * @return 监测代理
	 */
	public abstract CmaInfo getCmaInfo(String id);

	/**
	 * 返回所有监测代理
	 * 
	 * 
	 * @return 监测代理列表
	 */
	public abstract List<CmaInfo> getAllCmaInfos();

	/**
	 * 返回第pageNo页的监测代理
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 监测代理列表
	 */
	public abstract Page<CmaInfo> getPageCmaInfos(long pageNo, long pageSize);

	/**
	 * 根据条件查找监测代理
	 * 
	 * @param cmaInfoQueryModel
	 *            查询模型。在这里定义要过滤的条件，以及比较机制。各条件间使用“and”进行连接。
	 * @return 查找到的监测代理列表
	 */
	public abstract List<CmaInfo> getAllCmaInfos(CmaInfoQueryModel cmaInfoQueryModel);

	/**
	 * 根据条件查找第pageNo页的监测代理
	 * 
	 * @param cmaInfoQueryModel
	 *            查询模型。在这里定义要过滤的条件，以及比较机制。各条件间使用“and”进行连接。
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 查找到的监测代理列表
	 */
	public abstract Page<CmaInfo> getPageCmaInfos(CmaInfoQueryModel cmaInfoQueryModel, long pageNo, long pageSize);

	/**
	 * 取得监测代理cmaInfo下的所有监测装置
	 * 
	 * 
	 * @param cmaInfo
	 *            监测代理
	 * @return 监测代理cmaInfo下的所有监测装置
	 */
	public abstract List<Sensor> getAllSensors(CmaInfo cmaInfo);

	/**
	 * 取得监测代理cmaInfo下第pageNo页的监测装置
	 * 
	 * @param cmaInfo
	 *            监测代理
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 监测代理cmaInfo下第pageNo页的监测装置
	 */
	public abstract Page<Sensor> getPageSensors(CmaInfo cmaInfo, long pageNo, long pageSize);
	
	
	/**
	 * 根据CMA编码得到cma信息
	 * @param cmaCode CMA唯一标识编码(17位）
	 * @return
	 */
	public abstract  CmaInfo  getCmaInfobyCmaCode(String cmaCode);
	
	
}
