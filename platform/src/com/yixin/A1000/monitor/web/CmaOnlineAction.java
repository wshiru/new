/*
 * Project platform
 *
 * Classname CmaOnlineAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-07 11:50
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.monitor.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.service.CmaInfoService;
import com.yixin.A1000.setting.service.CmaHeartbeatInfoService;

/**
 * 在线CMA查询Action类
 * 
 * @version v1.0.0
 * @author 
 */
public class CmaOnlineAction extends ActionSupport {
	
	/** 序列版本ID */
	private static final long serialVersionUID = 5657657696821775839L;

	/** 在线CMA数据集键名 */
	private static final String ONLINE_CMA_LIST = "OnlineCmaList";
	
	/** CMA业务接口 */
	private CmaInfoService cmaInfoService;
	/** CMA心跳业务接口 */
	private CmaHeartbeatInfoService cmaHeartbeatInfoService;
	
	/** CMA总数 */
	private long totalCount = 0;
	/** CMA在线数 */
	private long onlineCount = 0;
	/** CMA在线率 */
	private String onlineRate = "100";
	
	/** 是否显示在线CMA */
	private Boolean isShowOnline = true;
		

	/**
	 * 获取CMA总数
	 * @return
	 */
	public long getTotalCount() {
		return totalCount;
	}
	/**
	 * 获取CMA在线数
	 * @return
	 */
	public long getOnlineCount() {
		return onlineCount;
	}
	/**
	 * 获取CMA在线率
	 * @return
	 */
	public String getOnlineRate() {
		return onlineRate;
	}
	/**
	 * 获取是否显示在线CMA
	 * @return
	 */
	public Boolean getIsShowOnline() {
		return isShowOnline;
	}
	/**
	 * 设置是否显示在线CMA
	 * @param isShowOnline
	 */
	public void setIsShowOnline(Boolean isShowOnline) {
		this.isShowOnline = isShowOnline;
	}
	
	/**
	 * 设置CMA业务接口
	 * @param cmaInfoService
	 */
	public void setCmaInfoService(CmaInfoService cmaInfoService) {
		this.cmaInfoService = cmaInfoService;
	}
	/**
	 * 设置CMA心跳业务接口
	 * @param cmaHeartbeatInfoService
	 */
	public void setCmaHeartbeatInfoService(
			CmaHeartbeatInfoService cmaHeartbeatInfoService) {
		this.cmaHeartbeatInfoService = cmaHeartbeatInfoService;
	}
	
	/**
	 * 查询在线CMA
	 * @return
	 */
	public String list(){			
		List<CmaInfo> allCMAs = this.cmaInfoService.getAllCmaInfos();
		if(null != allCMAs){
			totalCount = allCMAs.size();
			List<CmaInfo> selectedCMAs = new ArrayList<CmaInfo>();
			for(CmaInfo cma : allCMAs){
				if(isShowOnline.equals(this.cmaHeartbeatInfoService.checkOnline(cma))){
					selectedCMAs.add(cma);
				}
			}
			onlineCount = isShowOnline? selectedCMAs.size():totalCount - selectedCMAs.size();
			if(totalCount != 0){
				DecimalFormat dFormat = new DecimalFormat("#0.00");
				onlineRate = dFormat.format(((double)onlineCount / totalCount) * 100);
			}
			ActionContext.getContext().put(ONLINE_CMA_LIST, selectedCMAs);
		}
		return SUCCESS;
	}
	
}
