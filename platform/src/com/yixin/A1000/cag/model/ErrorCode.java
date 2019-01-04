/*
 * Project ca2000
 *
 * Class ErrorCode.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午10:55:58
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.model;

/**
 * I2接口协议：WEB SERVICE服务方法返回的错误类型
 * 
 * @version v1.0.0
 * @author 
 */
public enum ErrorCode {

	/** xml校验错误：输入参数xml过大 */
	ERROR_0100001("0100001"),

	/** xml校验错误：参数不符合规范 */
	ERROR_0100002("0100002"),

	/** ID校验错误：ID不合法 */
	ERROR_0101001("0101001"),

	/** ID校验错误：ID不存在 */
	ERROR_0101002("0101002"),

	/** ID校验错误：CMA与所辖状态监测装置不匹配 */
	ERROR_0101003("0101003"),

	/** ID校验错误：状态监测装置与被监测设备不匹配 */
	ERROR_0101004("0101004"),

	/** 数据格式错误：时间格式错误 */
	ERROR_0102001("0102001"),

	/** 数据格式错误：数据无法转换至对应格式 */
	ERROR_0102002("0102002"),

	/** 数据内容错误：监测类型与监测参数不一致 */
	ERROR_0103001("0103001"),

	/** 数据内容错误：监测类型不存在 */
	ERROR_0103002("0103002"),

	/** 数据内容错误：监测参数不存在 */
	ERROR_0103003("0103003"),

	/** 数据内容错误：监测参数缺失 */
	ERROR_0103004("0103004"),

	/** 数据内容错误：数据不符合业务规范 */
	ERROR_0103005("0103005"),

	/** 静态图片上传错误：静态图片片段过大 */
	ERROR_0104001("0104001"),

	/** 静态图片上传错误：静态图片片段损坏 */
	ERROR_0104002("0104002"),

	/** 静态图片上传错误：静态图片片段丢失 */
	ERROR_0104003("0104003"),

	/** 静态图片上传错误：静态图片校验不成功 */
	ERROR_0104004("0104004"),

	/** 未知错误类型：调用方法失败 */
	ERROR_99xxxxx("99xxxxx");

	/** 错误代码 */
	private String code;

	/**
	 * 构造方法
	 * 
	 * @param code
	 *            错误代码
	 */
	private ErrorCode(String code) {
		this.code = code;
	}

	/**
	 * 获取 错误代码
	 * 
	 * @return 错误代码
	 */
	public String getCode() {
		return this.code;
	}
}