/*
 * Project platform
 *
 * Classname BaseServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-07 10:57
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.base.service.impl;

import java.util.Date;

import com.yixin.framework.base.model.DataOrder;
import com.yixin.framework.base.model.DateProperty;

/**
 * 服务（业务接口）实现基类
 * 
 * @version v1.0.0
 * @author 
 */
public class BaseServiceImpl {
	
	/**
	 * 生成时间属性
	 * @param beginTime
	 * 				开始时间
	 * @param endTime
	 * 				结束时间
	 * @return
	 */
	protected DateProperty createDateProperty(String propertyName, Date beginTime, Date endTime, 
												DataOrder dataOrder){
		DateProperty dProperty = new DateProperty(propertyName, beginTime, endTime);
		dProperty.setDataOrder(dataOrder);
		return dProperty;
	}
}
