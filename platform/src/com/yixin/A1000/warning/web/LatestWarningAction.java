
/*
 * Project platform
 *
 * Classname LatestWarningAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-08 14:39
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */package com.yixin.A1000.warning.web;

import java.util.Date;

import com.yixin.A1000.common.constant.BusinessConstants;
import com.yixin.A1000.warning.model.Warning;
import com.yixin.A1000.warning.service.WarningService;
import com.yixin.framework.base.web.BaseAction;

/**
 * 最新告警Action类
 * 
 * @version v1.0.0
 * @author 
 */
public class LatestWarningAction extends BaseAction<Warning> {

	/** 序列版本ID  */
	private static final long serialVersionUID = -1298524014427879324L;
	
	/** 告警服务  */
	private WarningService warningService;
	
	/** 最近报警天数 */
	private final int warningDays = BusinessConstants.LATEST_WARNING_SPAN;


	/**
	 * 设置告警服务
	 * @return
	 */
	public void setWarningService(WarningService warningService) {
		this.warningService = warningService;
	}
	
	/**
	 * 获取最近报警天数
	 * @return
	 */
	public int getWarningDays() {
		return warningDays;
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#list()
	 */
	@Override
	public String list() {	
		Date endTime = new Date();
		Date beginTime = new Date(endTime.getTime() - warningDays * 24 * 60 * 60 * 1000);
		page = this.warningService.getPageWarningsByCMA(null, null, null, beginTime, endTime, getPageNo(), getPageSize());
		return super.list();
	}
	
}
