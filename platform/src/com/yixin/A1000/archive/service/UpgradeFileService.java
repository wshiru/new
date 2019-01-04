/*
 * Project platform
 *
 * Class UpgradeFileService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-13 下午02:57:18
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

import com.yixin.A1000.archive.model.UpgradeFile;
import com.yixin.A1000.archive.model.UpgradeFileQueryModel;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.exception.BusinessException;

/**
 * 升级文件业务逻辑接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface UpgradeFileService {

	/**
	 * 新增升级文件。当相同后缀与版本号的升级文件已经存在时，抛出BusinessException异常。
	 * 
	 * @param upgradeFile
	 *            要添加的升级文件
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_UPGRADEFILE_VERSION_ALREADYEXISTS
	 *                -相同后缀与版本号的升级文件已经存在
	 */
	public abstract void addUpgradeFile(UpgradeFile upgradeFile);

	/**
	 * 修改升级文件。当相同后缀与版本号的升级文件已经存在时，抛出BusinessException异常。
	 * 
	 * @param upgradeFile
	 *            要进行修改的升级文件
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_UPGRADEFILE_VERSION_ALREADYEXISTS
	 *                -相同后缀与版本号的升级文件已经存在
	 */
	public abstract void editUpgradeFile(UpgradeFile upgradeFile);

	/**
	 * 删除升级文件。当存在未下发/已下发的任务依赖于该升级文件时，抛出BusinessException异常。
	 * 
	 * @param upgradeFile
	 *            要进行删除的升级文件
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_UPGRADEFILE_EXISTUNFINISHEDTASK-
	 *                存在未下发/已下发的任务依赖于该升级文件
	 */
	public abstract void deleteUpgradeFile(UpgradeFile upgradeFile);

	/**
	 * 删除升级文件。当存在未下发/已下发的任务列表中依赖于任一升级文件时，抛出BusinessException异常。
	 * 
	 * @param upgradeFiles
	 *            要进行删除的升级文件列表
	 * @exception BusinessException
	 * <br />
	 *                说明：errorCode-错误描述<br />
	 *                ArchiveErrorCode.ARCHIVE_UPGRADEFILE_EXISTUNFINISHEDTASK-
	 *                存在未下发/已下发的任务依赖于该升级文件
	 */
	public abstract void deleteUpgradeFiles(Collection<UpgradeFile> upgradeFiles);

	/**
	 * 根据ID查找升级文件
	 * 
	 * @param id
	 *            要查找升级文件的ID
	 * @return 升级文件
	 */
	public abstract UpgradeFile getUpgradeFile(String id);

	/**
	 * 返回所有升级文件
	 * 
	 * 
	 * @return 升级文件列表
	 */
	public abstract List<UpgradeFile> getAllUpgradeFiles();

	/**
	 * 返回第pageNo页的升级文件
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 升级文件列表
	 */
	public abstract Page<UpgradeFile> getPageUpgradeFiles(long pageNo, long pageSize);

	/**
	 * 根据条件查找升级文件
	 * 
	 * @param upgradeFileQueryModel
	 *            查询模型。在这里定义要过滤的条件，以及比较机制。各条件间使用“and”进行连接。
	 * @return 查找到的升级文件列表
	 */
	public abstract List<UpgradeFile> getAllUpgradeFiles(UpgradeFileQueryModel upgradeFileQueryModel);

	/**
	 * 根据条件查找第pageNo页的升级文件
	 * 
	 * @param upgradeFileQueryModel
	 *            查询模型。在这里定义要过滤的条件，以及比较机制。各条件间使用“and”进行连接。
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            页大小
	 * @return 查找到的升级文件列表
	 */
	public abstract Page<UpgradeFile> getPageUpgradeFiles(UpgradeFileQueryModel upgradeFileQueryModel, long pageNo,
			long pageSize);

}
