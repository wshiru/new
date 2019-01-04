/*
 * Project platform
 *
 * Classname InsulatorMonsoonWarningConditionAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-22 15:35
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.web;

import com.yixin.A1000.warning.conditions.InsulatorMonsoonWarningConditions;
import com.yixin.A1000.warning.constant.WarningDictNames;

/**
 * 绝缘子串风偏告警条件Action类
 * 
 * @version v1.0.0
 * @author 
 */
public class InsulatorMonsoonWarningConditionAction extends BaseMonsoonWarningConditionAction{

	/** 序列号版本号 */
	private static final long serialVersionUID = 3073518798342436384L;

	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	@Override
	public void prepare() throws Exception {
		if(null != InsulatorMonsoonWarningConditions.CONDT_WINDANGLE)
			windAngle = InsulatorMonsoonWarningConditions.CONDT_WINDANGLE.getThreshold();
		if(null != InsulatorMonsoonWarningConditions.CONDT_ANGLE)
			angle = InsulatorMonsoonWarningConditions.CONDT_ANGLE.getThreshold();
		if(null != InsulatorMonsoonWarningConditions.CONDT_MINCLEARANCEPARAMS)
			minClearanceParams = InsulatorMonsoonWarningConditions.CONDT_MINCLEARANCEPARAMS.getThreshold();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.warning.web.WarningConditionAction#save()
	 */
	public String save() {
		try {
			InsulatorMonsoonWarningConditions.setThresholds(windAngle, angle, minClearanceParams);
		} catch (Exception e) {
			setFailureMessage("保存失败");
			return INPUT;
		}
		addSaveLog(WarningDictNames.INSULATORMONSOON_WARNING_DICT_NAME);
		return super.save();
	}
}
