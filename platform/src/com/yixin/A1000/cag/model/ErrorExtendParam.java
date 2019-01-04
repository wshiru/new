/*
 * Project ca2000
 *
 * Class ErrorExtendParam.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午10:57:42
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.model;

/**
 * I2接口协议：<b>返回的错误信息扩展参数类型。</b><br />
 * 表示返回XML格式
 * 
 * <pre style="background-color:lightgray; width:0; overflow:display; border:1px solid gray;">
 * &lt;response&gt;
 * 	&lt;result code=""&gt;
 * 		&lt;error errorcode=""&gt;
 * 			&lt;attr name="" value="" /&gt;
 * 		&lt;/error&gt;
 * 	&lt;/result&gt;
 * &lt;/response&gt;
 * </pre>
 * 
 * 中的&lt;attr /&gt;标签name属性的值
 * 
 * @version v1.0.0
 * @author 
 */
public enum ErrorExtendParam {

	/** CMA的17位编码 */
	CMA_ID("CMA_ID"),

	/** 监测装置的17位编码 */
	SENSOR_ID("Sensor_ID");

	/** name属性的值 */
	private String code;

	/**
	 * 构造方法
	 * 
	 * @param value
	 *            name属性的值
	 */
	private ErrorExtendParam(String code) {
		this.code = code;
	}

	/**
	 * 获取 name属性的值
	 * 
	 * @return name属性的值
	 */
	public String getCode() {
		return this.code;
	}
}