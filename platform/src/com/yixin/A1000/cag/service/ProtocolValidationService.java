/*
 * Project ca2000
 *
 * Class ProtocolValidationService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午09:50:36
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.service;

import com.yixin.A1000.cag.model.ValidateResult;

/**
 * 协议校验业务逻辑接口。进行协议的输入/输出格式的校验。
 * 
 * @version v1.0.0
 * @author 
 */
public interface ProtocolValidationService {

	/**
	 * 上传心跳信息：校验其输入参数是否正确。
	 * 
	 * @param strXMLParams
	 *            要进行校验的XML字符串
	 * @return 校验后的结果
	 */
	public abstract ValidateResult validateUploadCMAHeartbeatInfoRequestXML(String strXMLParams);

	/**
	 * 上传监测数据：校验其输入参数是否正确。
	 * 
	 * @param strXMLParams
	 *            要进行校验的XML字符串
	 * @return 校验后的结果
	 */
	public abstract ValidateResult validateUploadCMADataRequestXML(String strXMLParams);

	/**
	 * 上传静态图片数据：校验其输入参数是否正确。
	 * 
	 * @param strXMLParams
	 *            要进行校验的XML字符串
	 * @return 校验后的结果
	 */
	public abstract ValidateResult validateUploadCMAImageRequestXML(byte[] imageByte, String strXMLParams);

	/**
	 * 上传配置信息：校验其输入参数是否正确。
	 * 
	 * @param strXMLParams
	 *            要进行校验的XML字符串
	 * @return 校验后的结果
	 */
	public abstract ValidateResult validateUploadCMAConfigRequestXML(String strXMLParams);

	/**
	 * 下发控制指令：校验其输入参数是否正确。
	 * 
	 * @param strXMLParams
	 *            要进行校验的XML字符串
	 * @return 校验后的结果
	 */
	public abstract ValidateResult validateDownloadCAGCtrlRequestXML(String strXMLParams);

	/**
	 * 获取最新版本：校验其输入参数是否正确。
	 * 
	 * @param strXMLParams
	 *            要进行校验的XML字符串
	 * @return 校验后的结果
	 */
	public abstract ValidateResult validateDownloadCMALatestVersionRequestXML(String strXMLParams);

	/**
	 * 获取历史版本：校验其输入参数是否正确。
	 * 
	 * @param strXMLParams
	 *            要进行校验的XML字符串
	 * @return 校验后的结果
	 */
	public abstract ValidateResult validateDownloadCMAHistoryVersionRequestXML(String strXMLParams);

	/**
	 * 获取更新文件：校验其输入参数是否正确。
	 * 
	 * @param strXMLParams
	 *            要进行校验的XML字符串
	 * @return 校验后的结果
	 */
	public abstract ValidateResult validateDownloadCMAUpdateFileRequestXML(String strXMLParams);
}
