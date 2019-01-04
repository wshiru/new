/*
 * Project ca2000
 *
 * Class ValidateResult.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午11:18:44
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 校验信息对象。保存校验后的信息。
 * 
 * @version v1.0.0
 * @author 
 */
public class ValidateResult {

	/**
	 * I2接口协议：表示返回XML格式
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
	 * 中的&lt;result /&gt;标签的code属性
	 */
	private int code;

	/**
	 * I2接口协议：表示返回XML格式
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
	 * 中的&lt;error /&gt;标签的errorcode属性
	 */
	private ErrorCode errorCode;

	/**
	 * I2接口协议：表示返回XML格式
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
	 * 中的&lt;attr /&gt;标签的name属性
	 */
	private ErrorExtendParam errorExtendParam;

	/**
	 * I2接口协议：表示返回XML格式
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
	 * 中的&lt;attr /&gt;标签的value属性
	 */
	private String value;

	/** 此次交互的cma编码 */
	private List<String> cmaCodes = new ArrayList<String>();

	/** 此次交互的监测装置编码 */
	private List<String> sensorCodes = new ArrayList<String>();

	/**
	 * 私有构造方法
	 * 
	 */
	private ValidateResult() {
	}

	/**
	 * 私有构造方法
	 * 
	 * @param code
	 *            I2接口协议：表示返回XML格式
	 * 
	 *            <pre style="background-color:lightgray; width:0; overflow:display; border:1px solid gray;">
	 * &lt;response&gt;
	 * 	&lt;result code=""&gt;
	 * 		&lt;error errorcode=""&gt;
	 * 			&lt;attr name="" value="" /&gt;
	 * 		&lt;/error&gt;
	 * 	&lt;/result&gt;
	 * &lt;/response&gt;
	 * </pre>
	 * 
	 *            中的&lt;result /&gt;标签的code属性
	 */
	private ValidateResult(int code) {
		this.code = code;
	}

	/**
	 * 生成一个校验成功的结果对象。
	 * 
	 * @param cmaCodes
	 *            此次交互的所有监测代理编码
	 * @param. sensorCodes 此次交互的所有监测装置编码
	 * @return 保存成功信息的结果对象
	 */
	public static ValidateResult getSucceedValidateResult(List<String> cmaCodes, List<String> sensorCodes) {
		ValidateResult result = new ValidateResult(0);
		if (null != cmaCodes) {
			result.setCmaCodes(cmaCodes);
		}
		if (null != sensorCodes) {
			result.setSensorCodes(sensorCodes);
		}
		return result;
	}

	/**
	 * 生成一个校验失败的对象。
	 * 
	 * @param errorCode
	 *            I2接口协议：表示返回XML格式
	 * 
	 *            <pre style="background-color:lightgray; width:0; overflow:display; border:1px solid gray;">
	 * &lt;response&gt;
	 * 	&lt;result code=""&gt;
	 * 		&lt;error errorcode=""&gt;
	 * 			&lt;attr name="" value="" /&gt;
	 * 		&lt;/error&gt;
	 * 	&lt;/result&gt;
	 * &lt;/response&gt;
	 * </pre>
	 * 
	 *            中的&lt;error /&gt;标签的errorcode属性
	 * @param errorExtendParam
	 *            I2接口协议：表示返回XML格式
	 * 
	 *            <pre style="background-color:lightgray; width:0; overflow:display; border:1px solid gray;">
	 * &lt;response&gt;
	 * 	&lt;result code=""&gt;
	 * 		&lt;error errorcode=""&gt;
	 * 			&lt;attr name="" value="" /&gt;
	 * 		&lt;/error&gt;
	 * 	&lt;/result&gt;
	 * &lt;/response&gt;
	 * </pre>
	 * 
	 *            中的&lt;attr /&gt;标签的name属性
	 * @param value
	 *            I2接口协议：表示返回XML格式
	 * 
	 *            <pre style="background-color:lightgray; width:0; overflow:display; border:1px solid gray;">
	 * &lt;response&gt;
	 * 	&lt;result code=""&gt;
	 * 		&lt;error errorcode=""&gt;
	 * 			&lt;attr name="" value="" /&gt;
	 * 		&lt;/error&gt;
	 * 	&lt;/result&gt;
	 * &lt;/response&gt;
	 * </pre>
	 * 
	 *            中的&lt;attr /&gt;标签的value属性
	 * @return 保存失败信息的结果对象
	 */
	public static ValidateResult getFailedValidateResult(ErrorCode errorCode, ErrorExtendParam errorExtendParam,
			String value) {
		ValidateResult validateResult = new ValidateResult(1);
		validateResult.setErrorCode(errorCode);
		validateResult.setErrorExtendParam(errorExtendParam);
		validateResult.setValue(value);
		return validateResult;
	}

	/**
	 * 获取 I2接口协议：表示返回XML格式
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
	 * 中的&lt;result /&gt;标签的code属性
	 * 
	 * @return code的值
	 */
	public int getCode() {
		return this.code;
	}

	/**
	 * 设置 I2接口协议：表示返回XML格式
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
	 * 中的&lt;result /&gt;标签的code属性
	 * 
	 * @param code
	 *            要进行设置的值
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 获取 I2接口协议：表示返回XML格式
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
	 * 中的&lt;error /&gt;标签的errorcode属性
	 * 
	 * @return errorCode的值
	 */
	public ErrorCode getErrorCode() {
		return this.errorCode;
	}

	/**
	 * 设置 I2接口协议：表示返回XML格式
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
	 * 中的&lt;error /&gt;标签的errorcode属性
	 * 
	 * @param errorCode
	 *            要进行设置的值
	 */
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 获取 I2接口协议：表示返回XML格式
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
	 * 中的&lt;attr /&gt;标签的name属性
	 * 
	 * @return errorExtendParam的值
	 */
	public ErrorExtendParam getErrorExtendParam() {
		return this.errorExtendParam;
	}

	/**
	 * 设置 I2接口协议：表示返回XML格式
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
	 * 中的&lt;attr /&gt;标签的name属性
	 * 
	 * @param errorExtendParam
	 *            要进行设置的值
	 */
	public void setErrorExtendParam(ErrorExtendParam errorExtendParam) {
		this.errorExtendParam = errorExtendParam;
	}

	/**
	 * 获取 I2接口协议：表示返回XML格式
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
	 * 中的&lt;attr /&gt;标签的value属性
	 * 
	 * @return value属性的值
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * 设置 I2接口协议：表示返回XML格式
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
	 * 中的&lt;attr /&gt;标签的value属性
	 * 
	 * @param value
	 *            要进行设置的值
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 设置 此次交互的cma编码
	 * 
	 * @param cmaCodes
	 *            此次交互的cma编码
	 */
	public void setCmaCodes(List<String> cmaCodes) {
		this.cmaCodes = cmaCodes;
	}

	/**
	 * 获取 此次交互的cma编码
	 * 
	 * @return 此次交互的cma编码
	 */
	public List<String> getCmaCodes() {
		return this.cmaCodes;
	}

	/**
	 * 设置 此次交互的监测装置编码
	 * 
	 * @param sensorCodes
	 *            此次交互的监测装置编码
	 */
	public void setSensorCodes(List<String> sensorCodes) {
		this.sensorCodes = sensorCodes;
	}

	/**
	 * 获取 此次交互的监测装置编码
	 * 
	 * @return 此次交互的监测装置编码
	 */
	public List<String> getSensorCodes() {
		return this.sensorCodes;
	}
}
