/*
 * Project platform
 *
 * Class TowerService.java
 * 
 * Version 1.0.0
 * 
 * Creator 梁立全

 * CreateAt 2011-6-21 下午07:33:27
 *
 * ModifiedBy 

 * ModifyAt 2011-07-06 16：14

 * ModifyDescription 添加查询接口实现

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.setting.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yixin.A1000.archive.dao.CmaInfoDao;
import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.common.constant.BusinessConstants;
import com.yixin.A1000.setting.dao.CmaHeartbeatInfoDao;
import com.yixin.A1000.setting.model.CmaHeartbeatInfo;
import com.yixin.A1000.setting.model.SettingErrorCode;
import com.yixin.A1000.setting.service.CmaHeartbeatInfoService;
import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.service.impl.BaseServiceImpl;
import com.yixin.framework.exception.BusinessException;


/**
 * 监测代理心跳信息业务逻辑接口实现类

 * 
 * @version v1.0.0
 * @author 詹朝轮

 */
public class CmaHeartbeatInfoServiceImpl extends BaseServiceImpl implements CmaHeartbeatInfoService {

	/**
	 * 监测代理心跳信息DAO接口
	 */
	private   CmaHeartbeatInfoDao cmaHeartbeatInfoDao;
	
	/**
	 *  监测代理DAO接口
	 */
	private    CmaInfoDao cmaInfoDao;
	
	/**
	 * 设置  监测代理心跳信息DAO接口
	 * @param cmaHeartbeatInfoDao
	 */
	public void setCmaHeartbeatInfoDao(CmaHeartbeatInfoDao cmaHeartbeatInfoDao) {
		this.cmaHeartbeatInfoDao = cmaHeartbeatInfoDao;
	}
	
	/**
	 * 设置 监测代理DAO接口
	 * @param cmaInfoDao
	 */
	public void setCmaInfoDao(CmaInfoDao cmaInfoDao) {
		this.cmaInfoDao = cmaInfoDao;
	}

	@Override
	public void addCmaHeartbeatInfo(CmaHeartbeatInfo cmaHeartbeatInfo) {
		CmaInfo  cmaInfo = null;
		String cmaInfoId = cmaHeartbeatInfo.getCmaInfo().getCmaInfoId();
		if  ( cmaInfoId != null && !cmaInfoId.equals("") ){
			cmaInfo  = this.cmaInfoDao.findById(cmaInfoId);	
		}
		if ( cmaInfo == null ) {
			throw new BusinessException(SettingErrorCode.SETTING_CMAINFO_INVALID);
		}
		this.cmaHeartbeatInfoDao.save(cmaHeartbeatInfo);	
	}
	
	@Override
	public void deleteCmaHeartbeatInfo(CmaHeartbeatInfo cmaHeartbeatInfo) {
		this.cmaHeartbeatInfoDao.delete(cmaHeartbeatInfo);
	}

	@Override
	public void deleteCmaHeartbeatInfos(
			Collection<CmaHeartbeatInfo> cmaHeartbeatInfos) {
		this.cmaHeartbeatInfoDao.deleteAll(cmaHeartbeatInfos);
	}

	@Override
	public List<CmaHeartbeatInfo> getAllCmaHeartbeatInfos() {
		return this.cmaHeartbeatInfoDao.getAll();
	}

	@Override
	public void addCmaHeartbeatInfo(List<CmaHeartbeatInfo> cmaHeartbeatInfos) {
		
		Iterator<CmaHeartbeatInfo> it = cmaHeartbeatInfos.iterator();
		while ( it.hasNext() ){
			CmaHeartbeatInfo  info = it.next();
			this.addCmaHeartbeatInfo(info);
		}
	
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.setting.service.CmaHeartbeatInfoService#getCmaHeartbeatInfos(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<CmaHeartbeatInfo> getCmaHeartbeatInfos(CmaInfo cma,
			Date beginTime, Date endTime) {
		return this.cmaHeartbeatInfoDao.getAllByProperties(createPropertiesMap(cma), 
				createDateProperty("createTime", beginTime, endTime, DataOrder.DESC));
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.setting.service.CmaHeartbeatInfoService#getPageCmaHeartbeatInfos(java.lang.String, java.util.Date, java.util.Date, long, long)
	 */
	@Override
	public Page<CmaHeartbeatInfo> getPageCmaHeartbeatInfos(CmaInfo cma,
			Date beginTime, Date endTime, long pageNo, long pageSize) {
		return this.cmaHeartbeatInfoDao.getPageByProperties(createPropertiesMap(cma), 
				createDateProperty("createTime", beginTime, endTime, DataOrder.DESC), pageNo, pageSize);
	}
	
	/**
	 * 生成属性映射集合
	 * @param cma
	 * 			监测代理
	 * @return
	 */
	protected Map<String, Object> createPropertiesMap(CmaInfo cma) {
		Map<String, Object> map = null;
		if (null != cma) {
			map = new HashMap<String, Object>();
			if(null != cma.getCmaInfoId() && !cma.getCmaInfoId().isEmpty())
				map.put("cmaInfo", cma);
			else if(null != cma.getCmaCode() && !cma.getCmaCode().trim().isEmpty()){
				map.put("cmaInfo.cmaCode", cma.getCmaCode());
			}
		}
		return map;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.setting.service.CmaHeartbeatInfoService#checkOnline(com.yixin.ca2000.archive.model.CmaInfo)
	 */
	@Override
	public boolean checkOnline(CmaInfo cma) {
		List<CmaHeartbeatInfo> heartbeatInfos = getCmaHeartbeatInfos(cma, null, null);
		if(null != heartbeatInfos && heartbeatInfos.size() > 0){
			CmaHeartbeatInfo cInfo = heartbeatInfos.get(0);
			Date createTime = cInfo.getCreateTime();
			if(null != createTime){
				Date nowDate = new Date();
				long diffMins = (nowDate.getTime() - createTime.getTime())/(1000*60);
				if(diffMins < BusinessConstants.CMA_HEARTBEAT_INTERVAL)
					return true;
			}
		}			
		return false;
	}
    
	
}
