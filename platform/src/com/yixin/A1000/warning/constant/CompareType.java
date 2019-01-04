/*
 * Project platform
 *
 * Classname CompareType.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-25 17：51
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.constant;

/**
 * 比较类型枚举
 * 
 * @version v1.0.0
 * @author 
 */
public enum CompareType {
	
	/** 等于 */
	EQUAL(0),
	
	/** 大于 */
	OVER(1),
	
	/** 小于 */
	UNDER(2),
	
	/** 大于等于 */
	OVEROREQUAL(3),
	
	/** 小于等于 */
	UNDEROREQUAL(4);
	
	private final int type;
	
	private CompareType (int type){
		this.type = type;
	}

	public int getValue(){
		return type;
	}
}
