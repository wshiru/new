/*
 * Project platform
 *
 * Class HistoryVersionService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-21 上午09:19:42
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

import com.yixin.A1000.archive.model.HistoryVersion;
import com.yixin.framework.base.model.Page;

/**
 * 历史版本业务逻辑接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface HistoryVersionService {

	/**
	 * 新增历史版本。并将重新设置最新版本的标记。
	 * 
	 * @param historyVersion
	 *            要添加的历史版本
	 */
	public abstract void addHistoryVersion(HistoryVersion historyVersion);

	/**
	 * 删除历史版本。
	 * 
	 * 
	 * @param historyVersion
	 *            要进行删除的历史版本
	 */
	public abstract void deleteHistoryVersion(HistoryVersion historyVersion);

	/**
	 * 当存在杆塔依赖于列表中任一历史版本时，抛出BusinessException异常。
	 * 
	 * @param historyVersions
	 *            要进行删除的历史版本列表
	 */
	public abstract void deleteHistoryVersions(Collection<HistoryVersion> historyVersions);

	/**
	 * 根据ID查找历史版本
	 * 
	 * @param id
	 *            要查找历史版本的ID
	 * @return 历史版本
	 */
	public abstract HistoryVersion getHistoryVersion(String id);

	/***
	 * 根据监测代理编码查找最新版本信息
	 * 
	 * @param cmaCode
	 *            要操作的监测代理编码
	 * @return 监测代理的最新版本信息
	 */
	public abstract HistoryVersion getLastestHistoryVersionByCmaCode(String cmaCode);

	/**
	 * 根据监测装置编码查找最新版本信息
	 * 
	 * @param sensorCode
	 *            要操作的监测装置编码
	 * @return 监测装置的最新版本信息
	 */
	public abstract HistoryVersion getLastestHistoryVersionBySensorCode(String sensorCode);

	/**
	 * 根据监测代理编码查找所有历史版本
	 * 
	 * @param cmaCode
	 *            要操作的监测代理编码
	 * @return 历史版本列表
	 */
	public abstract List<HistoryVersion> getAllHistoryVersionsByCmaCode(String cmaCode);

	/**
	 * 根据监测装置编码查找所有历史版本
	 * 
	 * @param sensorCode
	 *            要操作的监测装置编码
	 * @return 历史版本列表
	 */
	public abstract List<HistoryVersion> getAllHistoryVersionsBySensorCode(String sensorCode);

	/**
	 * 根据监测代理编码查找第pageNo页的历史版本
	 * 
	 * @param cmaCode
	 *            要操作的监测代理编码
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 历史版本列表
	 */
	public abstract Page<HistoryVersion> getPageHistoryVersionsByCmaCode(String cmaCode, long pageNo, long pageSize);

	/**
	 * 根据监测装置编码查找第pageNo页的历史版本
	 * 
	 * @param sensorCode
	 *            要操作的监测装置编码
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 历史版本列表
	 */
	public abstract Page<HistoryVersion> getPageHistoryVersionsBySensorCode(String sensorCode, long pageNo,
			long pageSize);
}
