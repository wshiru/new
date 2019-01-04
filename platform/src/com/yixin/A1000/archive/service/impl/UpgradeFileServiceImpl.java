/*
 * Project platform
 *
 * Class UpgradeFileServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-13 下午03:10:26
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.dao.UpgradeFileDao;
import com.yixin.A1000.archive.model.ArchiveErrorCode;
import com.yixin.A1000.archive.model.UpgradeFile;
import com.yixin.A1000.archive.model.UpgradeFileQueryModel;
import com.yixin.A1000.archive.service.UpgradeFileService;
import com.yixin.A1000.setting.dao.TaskConfigDao;
import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.QueryProperty;
import com.yixin.framework.exception.BusinessException;

/**
 * 升级文件业务逻辑具体实现
 * 
 * @version v1.0.0
 * @author 
 */
public class UpgradeFileServiceImpl implements UpgradeFileService {

	/** 升级文件DAO接口 */
	private UpgradeFileDao upgradeFileDao;

	/** 任务DAO接口 */
	private TaskConfigDao taskConfigDao;
	
	/**
	 * 设置 升级文件DAO接口
	 *
	 * @param upgradeFileDao 升级文件DAO接口
	 */
	public void setUpgradeFileDao(UpgradeFileDao upgradeFileDao) {
		this.upgradeFileDao = upgradeFileDao;
	}

	/**
	 * 设置 任务DAO接口
	 *
	 * @param taskConfigDao 任务DAO接口
	 */
	public void setTaskConfigDao(TaskConfigDao taskConfigDao) {
		this.taskConfigDao = taskConfigDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.UpgradeFileService#addUpgradeFile(com
	 * .yixin.ca2000.archive.model.UpgradeFile)
	 */
	@Override
	public void addUpgradeFile(UpgradeFile upgradeFile) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileExtension", upgradeFile.getFileExtension());
		map.put("version", upgradeFile.getVersion());
		List<UpgradeFile> list = this.upgradeFileDao.getAllByProperties(map);
		if (list.size() > 0) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_UPGRADEFILE_VERSION_ALREADYEXISTS);
		}
		this.upgradeFileDao.save(upgradeFile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.UpgradeFileService#editUpgradeFile(com
	 * .yixin.ca2000.archive.model.UpgradeFile)
	 */
	@Override
	public void editUpgradeFile(UpgradeFile upgradeFile) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileExtension", upgradeFile.getFileExtension());
		map.put("version", upgradeFile.getVersion());
		List<UpgradeFile> list = this.upgradeFileDao.getAllByProperties(map);
		for (UpgradeFile u : list) {
			if (!u.getUpgradeFileId().equals(upgradeFile.getUpgradeFileId())) {
				throw new BusinessException(ArchiveErrorCode.ARCHIVE_UPGRADEFILE_VERSION_ALREADYEXISTS);
			}
		}
		this.upgradeFileDao.update(upgradeFile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.UpgradeFileService#deleteUpgradeFile
	 * (com.yixin.ca2000.archive.model.UpgradeFile)
	 */
	@Override
	public void deleteUpgradeFile(UpgradeFile upgradeFile) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 0);
		map.put("upgradeFile", upgradeFile);
		List<TaskConfig> list = this.taskConfigDao.getAllByProperties(map);
		if (list.size() > 0) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_UPGRADEFILE_EXISTUNFINISHEDTASK);
		}
		Map<String, Object> unfinishedMap = new HashMap<String, Object>();
//		unfinishedMap.put("state", 1);
		unfinishedMap.put("upgradeFile", upgradeFile);
		List<TaskConfig> unfinishedList = this.taskConfigDao.getAllByProperties(unfinishedMap);
		if (unfinishedList.size() > 0) {
			throw new BusinessException(ArchiveErrorCode.ARCHIVE_UPGRADEFILE_EXISTUNFINISHEDTASK);
		}
		this.upgradeFileDao.delete(upgradeFile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.UpgradeFileService#deleteUpgradeFiles
	 * (java.util.Collection)
	 */
	@Override
	public void deleteUpgradeFiles(Collection<UpgradeFile> upgradeFiles) {
		for (UpgradeFile upgradeFile : upgradeFiles) {
			this.deleteUpgradeFile(upgradeFile);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.UpgradeFileService#getUpgradeFile(java
	 * .lang.String)
	 */
	@Override
	public UpgradeFile getUpgradeFile(String id) {
		return this.upgradeFileDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.UpgradeFileService#getAllUpgradeFiles()
	 */
	@Override
	public List<UpgradeFile> getAllUpgradeFiles() {
		return this.upgradeFileDao.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.UpgradeFileService#getPageUpgradeFiles
	 * (long, long)
	 */
	@Override
	public Page<UpgradeFile> getPageUpgradeFiles(long pageNo, long pageSize) {
		return this.upgradeFileDao.getPage(pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.UpgradeFileService#getAllUpgradeFiles
	 * (com.yixin.ca2000.archive.model.UpgradeFileQueryModel)
	 */
	@Override
	public List<UpgradeFile> getAllUpgradeFiles(UpgradeFileQueryModel upgradeFileQueryModel) {
		Collection<QueryProperty> queryProperties = this.createQuery(upgradeFileQueryModel);
		return this.upgradeFileDao.getAll(queryProperties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.UpgradeFileService#getPageUpgradeFiles
	 * (com.yixin.ca2000.archive.model.UpgradeFileQueryModel, long, long)
	 */
	@Override
	public Page<UpgradeFile> getPageUpgradeFiles(UpgradeFileQueryModel upgradeFileQueryModel, long pageNo, long pageSize) {
		Collection<QueryProperty> queryProperties = this.createQuery(upgradeFileQueryModel);
		return this.upgradeFileDao.getPage(queryProperties, pageNo, pageSize);
	}

	/**
	 * 根据upgradeFileQueryModel创建查询属性集
	 * 
	 * @param upgradeFileQueryModel
	 *            升级文件查询模型
	 * @return 组成的查询属性集
	 */
	private Collection<QueryProperty> createQuery(UpgradeFileQueryModel upgradeFileQueryModel) {
		Collection<QueryProperty> queryProperties = new ArrayList<QueryProperty>();
		if (null != upgradeFileQueryModel.getFileExtension()) {
			QueryProperty qp = new QueryProperty("fileExtension", upgradeFileQueryModel.getFileExtension(),
					upgradeFileQueryModel.getFileExtensionQueryMode());
			queryProperties.add(qp);
		}
		if (null != upgradeFileQueryModel.getFileName()) {
			QueryProperty qp = new QueryProperty("fileName", upgradeFileQueryModel.getFileName(),
					upgradeFileQueryModel.getFileNameQueryMode());
			queryProperties.add(qp);
		}
		if (null != upgradeFileQueryModel.getPath()) {
			QueryProperty qp = new QueryProperty("path", upgradeFileQueryModel.getPath(),
					upgradeFileQueryModel.getPathQueryMode());
			queryProperties.add(qp);
		}
		if (null != upgradeFileQueryModel.getReleaseTime()) {
			QueryProperty qp = new QueryProperty("releaseTime", upgradeFileQueryModel.getReleaseTime(),
					upgradeFileQueryModel.getReleaseTimeQueryMode(), upgradeFileQueryModel.getReleaseTimeQueryMode()
							.getEnd());
			queryProperties.add(qp);
		}
		if (null != upgradeFileQueryModel.getSensorType()) {
			QueryProperty qp = new QueryProperty("sensorType", upgradeFileQueryModel.getSensorType(),
					upgradeFileQueryModel.getSensorTypeQueryMode());
			queryProperties.add(qp);
		}
		if (null != upgradeFileQueryModel.getUpgradeFileDesc()) {
			QueryProperty qp = new QueryProperty("upgradeFileDesc", upgradeFileQueryModel.getUpgradeFileDesc(),
					upgradeFileQueryModel.getUpgradeFileDescQueryMode());
			queryProperties.add(qp);
		}
		if (null != upgradeFileQueryModel.getUploadTime()) {
			QueryProperty qp = new QueryProperty("uploadTime", upgradeFileQueryModel.getUploadTime(),
					upgradeFileQueryModel.getUploadTimeQueryMode(), upgradeFileQueryModel.getUploadTimeQueryMode()
							.getEnd());
			queryProperties.add(qp);
		}
		if (null != upgradeFileQueryModel.getUser()) {
			QueryProperty qp = new QueryProperty("user", upgradeFileQueryModel.getUser(),
					upgradeFileQueryModel.getUserQueryMode());
			queryProperties.add(qp);
		}
		if (null != upgradeFileQueryModel.getVersion()) {
			QueryProperty qp = new QueryProperty("version", upgradeFileQueryModel.getVersion(),
					upgradeFileQueryModel.getVersionQueryMode());
			queryProperties.add(qp);
		}
		return queryProperties;
	}
}
