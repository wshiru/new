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

 * ModifyAt 2011-07-06 15：50

 * ModifyDescription 添加查询接口，添加是否在线判断

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.setting.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.setting.model.CmaHeartbeatInfo;
import com.yixin.framework.base.model.Page;


/**
 * 监测代理心跳信息业务逻辑接口
 * 
 * @version v1.0.0
 * @author 詹朝轮

 */
public interface CmaHeartbeatInfoService {

	/**
	 * 新增监测代理心跳信息
	 * @param CmaHeartbeatInfo
	 */
	public   abstract   void addCmaHeartbeatInfo(CmaHeartbeatInfo cmaHeartbeatInfo);

	
	/**
	 * 新增监测代理心跳信息  多条
	 * @param cmaHeartbeatInfos
	 */
	public   abstract   void addCmaHeartbeatInfo(List<CmaHeartbeatInfo> cmaHeartbeatInfos);

	

    /**
     * 	 * 删除监测代理心跳信息  --单个
     * @param cmaHeartbeatInfo
     */
	public abstract void deleteCmaHeartbeatInfo(CmaHeartbeatInfo cmaHeartbeatInfo);

	
	/**
	   	 * 删除监测代理心跳信息  --多个
	 * @param cmaHeartbeatInfos
	 */
	public abstract void deleteCmaHeartbeatInfos(Collection<CmaHeartbeatInfo> cmaHeartbeatInfos);
	
	/**
	 * 返回所有监测代理心跳信息

	 * 
	 * 
	 * @return 信息列表
	 */
	public abstract List<CmaHeartbeatInfo> getAllCmaHeartbeatInfos();

	/**
	 * 查询监测代理心跳信息数据
	 * @param cma
	 * 				监测代理，可为null
	 * @param beginTime
	 * 				开始时间，可为null
	 * @param endTime
	 * 				结束时间，可为null
	 * @return
	 */
	public abstract List<CmaHeartbeatInfo> getCmaHeartbeatInfos(CmaInfo cma, Date beginTime, Date endTime);
	
	/**
	 * 查询监测代理心跳信息分页数据
	 * @param cma
	 * 				监测代理，可为null
	 * @param beginTime
	 * 				开始时间，可为null
	 * @param endTime
	 * 				结束时间，可为null
	 * @param pageNo
	 * 				页码
	 * @param pageSize
	 * 				页面大小
	 * @return
	 */
	public abstract Page<CmaHeartbeatInfo> getPageCmaHeartbeatInfos(CmaInfo cma, Date beginTime, Date endTime,
			long pageNo, long pageSize);
	
	/**
	 * 检查CMA是否在线
	 * @param cma
	 * @return
	 */
	public abstract boolean checkOnline(CmaInfo cma);
	
}
