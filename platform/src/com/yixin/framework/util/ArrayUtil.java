/*
 * Project platform
 *
 * Class ArrayUtil.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-21 上午10:49:40
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.util;

/**
 * 数组工具类。
 * 
 * @version v1.0.0
 * @author 
 */
public class ArrayUtil {

	/**
	 * 判断给定的数组array是否为空，或长度大于0；
	 * 
	 * @param array
	 *            要判断的数组
	 * @return <code>true</code> - array不为空，且长度大于0；<br />
	 *         <code>false</code> - array为空，或长度等于00；<br />
	 */
	public static boolean isEmpty(Object[] array) {
		return (null == array || 0 == array.length);
	}
}
