/*
 * Project ca2000
 *
 * Class ProtocolService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午09:39:04
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.service;

/**
 * 协议处理业务接口。用于处理协议部分，并生成相应的回复。
 * 
 * @version v1.0.0
 * @author 
 */
public interface ProtocolService {

	/**
	 * 返回上传心跳信息的回复XML信息
	 * 
	 * @param strXMLParams
	 *            请求的XML格式参数
	 * @return 上传心跳信息的回复XML信息
	 */
	public abstract String getUploadCMAHeartbeatInfoReplyXML(String strXMLParams);

	/**
	 * 返回上传监测数据的回复XML信息
	 * 
	 * @param strXMLParams
	 *            请求的XML格式参数
	 * @return 上传监测数据的回复XML信息
	 */
	public abstract String getUploadCMADataReplyXML(String strXMLParams);

	/**
	 * 返回上传静态图片数据的回复XML信息
	 * 
	 * @param imageByte
	 *            图像数据
	 * @param strXMLParams
	 *            请求的XML格式参数
	 * @return 上传静态图片数据的回复XML信息
	 */
	public abstract String getUploadCMAImageReplyXML(byte[] imageByte, String strXMLParams);

	/**
	 * 返回上传配置信息的回复XML信息
	 * 
	 * @param strXMLParams
	 *            请求的XML格式参数
	 * @return 上传配置信息的回复XML信息
	 */
	public abstract String getUploadCMAConfigReplyXML(String strXMLParams);

	/**
	 * 返回下发控制指令的回复XML信息
	 * 
	 * @param strXMLParams
	 *            请求的XML格式参数
	 * @return 下发控制指令的回复XML信息
	 */
	public abstract String getDownloadCAGCtrlReplyXML(String strXMLParams);

	/**
	 * 返回获取最新版本的回复XML信息
	 * 
	 * @param strXMLParams
	 *            请求的XML格式参数
	 * @return 获取最新版本的回复XML信息
	 */
	public abstract String getDownloadCMALatestVersionReplyXML(String strXMLParams);

	/**
	 * 返回获取历史版本的回复XML信息
	 * 
	 * @param strXMLParams
	 *            请求的XML格式参数
	 * @return 获取历史版本的回复XML信息
	 */
	public abstract String getDownloadCMAHistoryVersionReplyXML(String strXMLParams);

	/**
	 * 返回获取更新文件的回复XML信息
	 * 
	 * @param strXMLParams
	 *            请求的XML格式参数
	 * @return 获取更新文件的回复XML信息
	 */
	public abstract String getDownloadCMAUpdateFileReplyXML(String strXMLParams);
}
