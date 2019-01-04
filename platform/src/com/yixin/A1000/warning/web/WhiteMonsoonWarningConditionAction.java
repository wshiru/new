/*
 * Project platform
 *
 * Classname WhiteMonsoonWarningConditionAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-22 14:05
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.web;

import com.yixin.A1000.warning.conditions.WhiteMonsoonWarningConditions;
import com.yixin.A1000.warning.constant.WarningDictNames;
/**
 * 相间风偏告警条件Action类
 * 
 * @version v1.0.0
 * @author 
 */
public class WhiteMonsoonWarningConditionAction extends BaseMonsoonWarningConditionAction{

	/** 序列号版本号 */
	private static final long serialVersionUID = 8259383931512373612L;

	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	@Override
	public void prepare() throws Exception {
		if(null != WhiteMonsoonWarningConditions.CONDT_WINDANGLE)
			windAngle = WhiteMonsoonWarningConditions.CONDT_WINDANGLE.getThreshold();
		if(null != WhiteMonsoonWarningConditions.CONDT_ANGLE)
			angle = WhiteMonsoonWarningConditions.CONDT_ANGLE.getThreshold();
		if(null != WhiteMonsoonWarningConditions.CONDT_MINCLEARANCEPARAMS)
			minClearanceParams = WhiteMonsoonWarningConditions.CONDT_MINCLEARANCEPARAMS.getThreshold();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.web.WarningConditionAction#save()
	 */
	public String save() {
		try {
			WhiteMonsoonWarningConditions.setThresholds(windAngle, angle, minClearanceParams);
		} catch (Exception e) {
			setFailureMessage("保存失败");
			return INPUT;
		}
		addSaveLog(WarningDictNames.WHITEMONSOON_WARNING_DICT_NAME);
		return super.save();
	}
}
