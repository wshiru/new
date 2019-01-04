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
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.setting.service;

import java.util.List;

import com.yixin.A1000.setting.model.CmaParams;


public interface CmaParamsService {


	/**
	 * 获取某个监测代理的参数

	 * @param cmaInfo
	 * @return
	 */
//	public   abstract  CmaParams  getCmaParams(CmaInfo cmaInfo);
	
    /**
     * 获取所有监测代理参数列表

     * @return
     */
	public abstract List<CmaParams> getAllCmaParams();
	
    
	/** 
	 * 修改监测代理参数(存在时修改，不存在时新增)
	 * @param cmaParamsList 
	 */
	public abstract   void editCmaParams(List<CmaParams> cmaParamsList);
	
	

	/** 
	 * 修改监测代理参数  单个
	 * @param cmaParamsList 
	 */
	public abstract   void editCmaParams(CmaParams cmaParams);
	
	
	/** 
	 * 增加监测代理参数  单个
	 * @param cmaParamsList 
	 */
	public abstract   void addCmaParams(CmaParams cmaParams);
	

	/**
	 * 根据监测代理编码获取已下发或已确认的监测代理参数
	 * @param CmaCode 监测代理编码
	 * @return
	 */
	public abstract   CmaParams getConfirmCmaParams(String cmaCode);
	
	

	/**
	 * 根据监测代理编码获取未下发的监测代理参数
	 * @param CmaCode 监测代理编码
	 * @return
	 */
	public abstract   CmaParams getUnrecognizedCmaParams(String cmaCode);
	

	
	
}
