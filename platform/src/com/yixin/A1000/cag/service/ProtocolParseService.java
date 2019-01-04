/*
 * Project ca2000
 *
 * Class ProtocolParseService.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午10:01:17
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.service;

import com.yixin.A1000.cag.model.request.DownloadCAGCtrlRequestModel;
import com.yixin.A1000.cag.model.request.DownloadCMAHistoryVersionRequestModel;
import com.yixin.A1000.cag.model.request.DownloadCMALatestVersionRequestModel;
import com.yixin.A1000.cag.model.request.DownloadCMAUpdateFileRequestModel;
import com.yixin.A1000.cag.model.request.UploadCMAConfigRequestModel;
import com.yixin.A1000.cag.model.request.UploadCMADataRequestModel;
import com.yixin.A1000.cag.model.request.UploadCMAHeartbeatInfoRequestModel;
import com.yixin.A1000.cag.model.request.UploadCMAImageRequestModel;

/**
 * 将传入的数据解析成所需要的对象模型。
 * 
 * @version v1.0.0
 * @author 
 */
public interface ProtocolParseService {

	/**
	 * 上传心跳信息：将strXMLParams解析成对应的对象模型。
	 * 
	 * @param strXMLParams
	 *            要进行转换的XML字符串
	 * @return 解析后的对象模型
	 */
	public abstract UploadCMAHeartbeatInfoRequestModel parseUploadCMAHeartbeatInfo(String strXMLParams);

	/**
	 * 上传监测数据：将strXMLParams解析成对应的对象模型。
	 * 
	 * @param strXMLParams
	 *            要进行转换的XML字符串
	 * @return 解析后的对象模型
	 */
	public abstract UploadCMADataRequestModel parseUploadCMAData(String strXMLParams);

	/**
	 * 上传静态图片数据：将strXMLParams解析成对应的对象模型。
	 * 
	 * @param strXMLParams
	 *            要进行转换的XML字符串
	 * @return 解析后的对象模型
	 */
	public abstract UploadCMAImageRequestModel parseUploadCMAImage(byte[] imageByte, String strXMLParams);

	/**
	 * 上传配置信息：将strXMLParams解析成对应的对象模型。
	 * 
	 * @param strXMLParams
	 *            要进行转换的XML字符串
	 * @return 解析后的对象模型
	 */
	public abstract UploadCMAConfigRequestModel parseUploadCMAConfig(String strXMLParams);

	/**
	 * 下发控制指令：将strXMLParams解析成对应的对象模型。
	 * 
	 * @param strXMLParams
	 *            要进行转换的XML字符串
	 * @return 解析后的对象模型
	 */
	public abstract DownloadCAGCtrlRequestModel parseDownloadCAGCtrl(String strXMLParams);

	/**
	 * 获取最新版本：将strXMLParams解析成对应的对象模型。
	 * 
	 * @param strXMLParams
	 *            要进行转换的XML字符串
	 * @return 解析后的对象模型
	 */
	public abstract DownloadCMALatestVersionRequestModel parseDownloadCMALatestVersion(String strXMLParams);

	/**
	 * 获取历史版本：将strXMLParams解析成对应的对象模型。
	 * 
	 * @param strXMLParams
	 *            要进行转换的XML字符串
	 * @return 解析后的对象模型
	 */
	public abstract DownloadCMAHistoryVersionRequestModel parseDownloadCMAHistoryVersion(String strXMLParams);

	/**
	 * 获取更新文件：将strXMLParams解析成对应的对象模型。
	 * 
	 * @param strXMLParams
	 *            要进行转换的XML字符串
	 * @return 解析后的对象模型
	 */
	public abstract DownloadCMAUpdateFileRequestModel parseDownloadCMAUpdateFile(String strXMLParams);
}
