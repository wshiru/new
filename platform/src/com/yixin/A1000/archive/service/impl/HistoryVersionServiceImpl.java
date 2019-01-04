/*
 * Project platform
 *
 * Class HistoryVersionServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-21 上午09:37:49
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.dao.HistoryVersionDao;
import com.yixin.A1000.archive.model.HistoryVersion;
import com.yixin.A1000.archive.service.HistoryVersionService;
import com.yixin.framework.base.model.Page;

/**
 * 历史版本业务逻辑具体实现
 * 
 * @version v1.0.0
 * @author 
 */
public class HistoryVersionServiceImpl implements HistoryVersionService {

	/** 历史版本DAO接口 */
	private HistoryVersionDao historyVersionDao;

	/**
	 * 设置 历史版本DAO接口
	 * 
	 * @param historyVersionDao
	 *            历史版本DAO接口
	 */
	public void setHistoryVersionDao(HistoryVersionDao historyVersionDao) {
		this.historyVersionDao = historyVersionDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.HistoryVersionService#addHistoryVersion
	 * (com.yixin.ca2000.archive.model.HistoryVersion)
	 */
	@Override
	public void addHistoryVersion(HistoryVersion historyVersion) {
		HistoryVersion oldLatestHistoryVersion = null;
		if (null != historyVersion.getCmaCode()) {
			oldLatestHistoryVersion = this.getLastestHistoryVersionByCmaCode(historyVersion.getCmaCode());
		} else if (null != historyVersion.getSensorCode()) {
			oldLatestHistoryVersion = this.getLastestHistoryVersionBySensorCode(historyVersion.getSensorCode());
		}
		if (null != oldLatestHistoryVersion) {
			String oldVersion = oldLatestHistoryVersion.getVersion();
			String newVersion = historyVersion.getVersion();
			int result = this.compareVersion(oldVersion, newVersion);
			if (-1 == result) {
				historyVersion.setIsLatestVersion(1);
				oldLatestHistoryVersion.setIsLatestVersion(0);
			} else if (0 == result) {
				historyVersion.setIsLatestVersion(1);
				oldLatestHistoryVersion.setIsLatestVersion(0);
			} else {
				historyVersion.setIsLatestVersion(0);
				oldLatestHistoryVersion.setIsLatestVersion(1);
			}
			this.historyVersionDao.update(oldLatestHistoryVersion);
		} else {
			historyVersion.setIsLatestVersion(1);
		}
		this.historyVersionDao.save(historyVersion);
	}

	/**
	 * 比较v1和v2哪个是最新版本号
	 * 
	 * @param v1
	 *            比较的版本号
	 * @param v2
	 *            比较的版本号
	 * @return -1 - v2版本号较新；0 - 版本号相同； 1 = v1版本号较新
	 */
	private int compareVersion(String v1, String v2) {
		String[] array1 = v1.split("\\.");
		String[] array2 = v2.split("\\.");
		int len = array1.length > array2.length ? array2.length : array1.length;
		try {
			for (int i = 0; i < len; i++) {
				int temp1 = Integer.parseInt(array1[i]);
				int temp2 = Integer.parseInt(array2[i]);
				if (temp1 > temp2) {
					return 1;
				} else if (temp1 < temp2) {
					return -1;
				}
			}
		} catch (NumberFormatException ex) {
			// 版本格式错误，不作处理
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.HistoryVersionService#deleteHistoryVersion
	 * (com.yixin.ca2000.archive.model.HistoryVersion)
	 */
	@Override
	public void deleteHistoryVersion(HistoryVersion historyVersion) {
		this.historyVersionDao.delete(historyVersion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.HistoryVersionService#deleteHistoryVersions
	 * (java.util.Collection)
	 */
	@Override
	public void deleteHistoryVersions(Collection<HistoryVersion> historyVersions) {
		this.historyVersionDao.deleteAll(historyVersions);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.archive.service.HistoryVersionService#getHistoryVersion
	 * (java.lang.String)
	 */
	@Override
	public HistoryVersion getHistoryVersion(String id) {
		return this.historyVersionDao.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.archive.service.HistoryVersionService#
	 * getLastestHistoryVersionByCmaCode(java.lang.String)
	 */
	@Override
	public HistoryVersion getLastestHistoryVersionByCmaCode(String cmaCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cmaCode", cmaCode);
		map.put("isLatestVersion", 1);
		List<HistoryVersion> historyVersions = this.historyVersionDao.getAllByProperties(map);
		return historyVersions.size() > 0 ? historyVersions.get(0) : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.archive.service.HistoryVersionService#
	 * getLastestHistoryVersionBySensorCode(java.lang.String)
	 */
	@Override
	public HistoryVersion getLastestHistoryVersionBySensorCode(String sensorCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sensorCode", sensorCode);
		map.put("isLatestVersion", 1);
		List<HistoryVersion> historyVersions = this.historyVersionDao.getAllByProperties(map);
		return historyVersions.size() > 0 ? historyVersions.get(0) : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.archive.service.HistoryVersionService#
	 * getAllHistoryVersionsByCmaCode(java.lang.String)
	 */
	@Override
	public List<HistoryVersion> getAllHistoryVersionsByCmaCode(String cmaCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cmaCode", cmaCode);
		return this.historyVersionDao.getAllByProperties(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.archive.service.HistoryVersionService#
	 * getAllHistoryVersionsBySensorCode(java.lang.String)
	 */
	@Override
	public List<HistoryVersion> getAllHistoryVersionsBySensorCode(String sensorCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sensorCode", sensorCode);
		return this.historyVersionDao.getAllByProperties(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.archive.service.HistoryVersionService#
	 * getPageHistoryVersionsByCmaCode(java.lang.String, long, long)
	 */
	@Override
	public Page<HistoryVersion> getPageHistoryVersionsByCmaCode(String cmaCode, long pageNo, long pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cmaCode", cmaCode);
		return this.historyVersionDao.getPageByProperties(map, pageNo, pageSize);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.archive.service.HistoryVersionService#
	 * getPageHistoryVersionsBySensorCode(java.lang.String, long, long)
	 */
	@Override
	public Page<HistoryVersion> getPageHistoryVersionsBySensorCode(String sensorCode, long pageNo, long pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sensorCode", sensorCode);
		return this.historyVersionDao.getPageByProperties(map, pageNo, pageSize);
	}
}
