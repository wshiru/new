
/*
 * Project platform
 *
 * Classname WarningDictService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-05 16:25
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */package com.yixin.A1000.warning.service;

import java.util.List;

import com.yixin.A1000.warning.model.WarningDict;

 /**
 * 告警类型服务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface WarningDictService {

	/**
	 * 添加告警类型
	 * @param warningDict
	 */
	public abstract void addWarningDict(WarningDict warningDict);
	/**
	 * 更新告警类型
	 * @param warningDict
	 */
	public abstract void updateWarningDict(WarningDict warningDict);
	/**
	 * 删除告警类型
	 * @param warningDict
	 */
	public abstract void deleteWarningDict(WarningDict warningDict);
	/**
	 * 获取告警类型
	 * @param id
	 * 			告警类型ID
	 * @return
	 */
	public abstract WarningDict getWarningDict(String id);
	/**
	 * 根据告警类型名称获取告警类型（告警类型名称唯一）
	 * @param name
	 * 			告警类型名称
	 * @return
	 */
	public abstract WarningDict getWarningDictByName(String name);
	
	/**
	 * 获取全部告警类型
	 * @return
	 */
	public abstract List<WarningDict> getAllWarningDicts();
}
