/*
 * Project platform
 *
 * Class ProtocolValidationServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午09:58:17
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;

import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.CmaInfoService;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.cag.model.ErrorCode;
import com.yixin.A1000.cag.model.ErrorExtendParam;
import com.yixin.A1000.cag.model.ValidateResult;
import com.yixin.A1000.cag.model.XMLBusinessKeys;
import com.yixin.A1000.cag.model.uploadcmadata.InsulatorMonsoonItem;
import com.yixin.A1000.cag.model.uploadcmadata.MonitoringTypeCode;
import com.yixin.A1000.cag.model.uploadcmadata.TowerTiltItem;
import com.yixin.A1000.cag.model.uploadcmadata.WeatherItem;
import com.yixin.A1000.cag.model.uploadcmadata.WhiteMonsoonItem;
import com.yixin.A1000.cag.model.uploadcmadata.WireSagItem;
import com.yixin.A1000.cag.model.uploadcmadata.WireTemperatureItem;
import com.yixin.A1000.cag.service.ProtocolValidationService;
import com.yixin.framework.util.xml.XMLUtil;

/**
 * 协议校验业务具体实现类。进行协议的输入/输出格式的校验。
 * 
 * @version v1.0.0
 * @author 
 */
public class ProtocolValidationServiceImpl implements ProtocolValidationService {

	/** 日志记录器 */
	private static final Logger logger = Logger.getLogger("protocolParseLogger");

	// ---------------------------------------------------------------------------------------
	// XML Schema 流对象

	/** 上传心跳信息：输入参数的schema对象流 */
	private static final byte[] uploadCMAHeartbeatInfoByte;

	/** 上传监测数据 ：输入参数的schema对象流 */
	private static final byte[] uploadCMADataByte;

	/** 上传静态图片数据：输入参数的schema对象流 */
	private static final byte[] uploadCMAImageByte;

	/** 上传配置信息：输入参数的schema对象流 */
	private static final byte[] uploadCMAConfigByte;

	/** 下发控制指令：输入参数的schema对象流 */
	private static final byte[] downloadCAGCtrlByte;

	/** 获取最新版本：输入参数的schema对象流 */
	private static final byte[] downloadCMALatestVersionByte;

	/** 获取历史版本：输入参数的schema对象流 */
	private static final byte[] downloadCMAHistoryVersionByte;

	/** 获取更新文件：输入参数的schema对象流 */
	private static final byte[] downloadCMAUpdateFileByte;

	// ---------------------------------------------------------------------------------------
	// 属性
	/** 监测类型编码集 */
	private static List<String> monitoringTypeCodes = new ArrayList<String>();

	/** 上传心跳信息 类型 */
	private static final int UPLOAD_CMA_HEARTBEATINFO = 1;

	/** 上传监测数据 类型 */
	private static final int UPLOAD_CMA_DATA = 2;

	/** 上传静态图片数据 类型 */
	private static final int UPLOAD_CMA_IMAGE = 3;

	/** 上传配置信息 类型 */
	private static final int UPLOAD_CMA_CONFIG = 4;

	/** 下发控制指令 类型 */
	private static final int DOWNLOAD_CAG_CTRL = 5;

	/** 获取最新版本 类型 */
	private static final int DOWNLOAD_CMA_LATESTVERSION = 6;

	/** 获取历史版本 类型 */
	private static final int DOWNLOAD_CMA_HISTORYVERSION = 7;

	/** 获取更新文件 类型 */
	private static final int DOWNLOAD_CMA_UPDATEFILE = 8;

	/** cma信息业务服务接口 **/
	private CmaInfoService cmaInfoService;

	/** 监测装置业力接口 **/
	private SensorService  sensorService;

	
	static {
		InputStream uploadCMAHeartbeatInfoRequestSchema = ProtocolValidationServiceImpl.class.getClassLoader().getResourceAsStream(
				"com/yixin/A1000/cag/model/schema/request/uploadCMAHeartbeatInfo-request.xsd");
		InputStream uploadCMADataRequestSchema = ProtocolValidationServiceImpl.class.getClassLoader().getResourceAsStream(
				"com/yixin/A1000/cag/model/schema/request/uploadCMAData-request.xsd");
		InputStream uploadCMAImageRequestSchema = ProtocolValidationServiceImpl.class.getClassLoader().getResourceAsStream(
				"com/yixin/A1000/cag/model/schema/request/uploadCMAImage-request.xsd");
		InputStream uploadCMAConfigRequestSchema = ProtocolValidationServiceImpl.class.getClassLoader().getResourceAsStream(
				"com/yixin/A1000/cag/model/schema/request/uploadCMAConfig-request.xsd");
		InputStream downloadCAGCtrlRequestSchema = ProtocolValidationServiceImpl.class.getClassLoader().getResourceAsStream(
				"com/yixin/A1000/cag/model/schema/request/downloadCAGCtrl-request.xsd");
		InputStream downloadCMALatestVersionRequestSchema = ProtocolValidationServiceImpl.class.getClassLoader()
				.getResourceAsStream("com/yixin/A1000/cag/model/schema/request/downloadCMALatestVersion-request.xsd");
		InputStream downloadCMAHistoryVersionRequestSchema = ProtocolValidationServiceImpl.class.getClassLoader()
				.getResourceAsStream("com/yixin/A1000/cag/model/schema/request/downloadCMAHistoryVersion-request.xsd");
		InputStream downloadCMAUpdateFileRequestSchema = ProtocolValidationServiceImpl.class.getClassLoader().getResourceAsStream(
				"com/yixin/A1000/cag/model/schema/request/downloadCMAUpdateFile-request.xsd");
		
		uploadCMAHeartbeatInfoByte = getFileByte(uploadCMAHeartbeatInfoRequestSchema);
		uploadCMADataByte = getFileByte(uploadCMADataRequestSchema);
		uploadCMAImageByte = getFileByte(uploadCMAImageRequestSchema);
		uploadCMAConfigByte = getFileByte(uploadCMAConfigRequestSchema);
		downloadCAGCtrlByte = getFileByte(downloadCAGCtrlRequestSchema);
		downloadCMALatestVersionByte = getFileByte(downloadCMALatestVersionRequestSchema);
		downloadCMAHistoryVersionByte = getFileByte(downloadCMAHistoryVersionRequestSchema);
		downloadCMAUpdateFileByte = getFileByte(downloadCMAUpdateFileRequestSchema);
	}

	static {
		for (MonitoringTypeCode item : MonitoringTypeCode.values()) {
			monitoringTypeCodes.add(item.getCode());
		}
	}

	// ---------------------------------------------------------------------------------------
	// setters and getters

	/**
	 * 设置 cma信息业务服务接口
	 * 
	 * @param cmaInfoService
	 *            cma信息业务服务接口
	 */
	public void setCmaInfoService(CmaInfoService cmaInfoService) {
		this.cmaInfoService = cmaInfoService;
	}

	/**
	 * 设置 监测装置业力接口
	 * 
	 * @param sensorService
	 *            监测装置业力接口
	 */
	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	// ---------------------------------------------------------------------------------------
	// methods
	// ---------------------------------------------------------------------------------------
	// 上传心跳信息

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.cag.service.ProtocolValidationService#
	 * validateUploadCMAHeartbeatInfoRequestXML(java.lang.String)
	 */
	@Override
	public ValidateResult validateUploadCMAHeartbeatInfoRequestXML(String strXMLParams) {
		Document doc = XMLUtil.parseToXML(strXMLParams); // 转换成XML

		if ( null == doc ){
			logger.error("XML格式错误，无法转换.");
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0100002, null, null);
		}
		
		/* xml校验 */
		ValidateResult xmlValidationResult = this.validateXML(strXMLParams, uploadCMAHeartbeatInfoByte,
				UPLOAD_CMA_HEARTBEATINFO);
		if (null != xmlValidationResult) {
			return xmlValidationResult;
		}

		/* ID校验 */
		ValidateResult idValidationResult = this.validateUploadCMAHeartbeatInfoRequestXMLId(doc);
		if (null != idValidationResult) {
			return idValidationResult;
		}

		/* 数据格式校验 */
		ValidateResult dataFormatValidationResult = this.validateUploadCMAHeartbeatInfoRequestXMLDataFormat(doc);
		if (null != dataFormatValidationResult) {
			return dataFormatValidationResult;
		}

		/* 数据内容校验 */
		ValidateResult dataContentValidationResult = this.validateUploadCMAHeartbeatInfoRequestXMLDataContent(doc);
		if (null != dataContentValidationResult) {
			return dataContentValidationResult;
		}
		
		/* 静态图片上传校验 */

		/* 未知错误类型校验 */

		/* 获取此次交互的所有监测代理编码 */
		List<String> cmaCodes = this.getUploadCMAHeartbeatInfoCmaCodes(doc);

		/* 获取此次交互的所有监测装置编码 */
		List<String> sensorCodes = this.getUploadCMAHeartbeatInfoSensorCodes(doc);
	
		return ValidateResult.getSucceedValidateResult(cmaCodes, sensorCodes);
		
	}

	/**
	 * 上传心跳信息：ID校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	@SuppressWarnings("rawtypes")
	private ValidateResult validateUploadCMAHeartbeatInfoRequestXMLId(Document doc) {
		Element root = doc.getRootElement(); // 取得根节点

		/* 校验<cma />节点 */
		Element cmaNode = (Element) root.element("cma");
		String cmaCode = cmaNode.attributeValue("id");
		if (17 != cmaCode.length()) {
			logger.error("[上传心跳信息] - ID校验错误[ID不合法]：（id[cma]=" + cmaCode + "）。请求XML如下：\r\n" + doc.asXML());
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101001, ErrorExtendParam.CMA_ID, cmaCode);
		}
		CmaInfo cmaInfo = this.cmaInfoService.getCmaInfobyCmaCode(cmaCode);
		if (null == cmaInfo) {
			logger.error("[上传心跳信息] - ID校验错误[ID不存在]：（id[cma]=" + cmaCode + "）。请求XML如下：\r\n" + doc.asXML());
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101002, ErrorExtendParam.CMA_ID, cmaCode);
		}

		/* 校验<sensors />节点 */
		Element sensorsNode = (Element) root.element("sensors");
		for (Iterator iterator = sensorsNode.elementIterator(); iterator.hasNext();) {
			Element sensorNode = (Element) iterator.next();
			String sensorCode = sensorNode.attributeValue("id");
			if (17 != sensorCode.length()) {
				logger.error("[上传心跳信息] - ID校验错误[ID不合法]：（id[sensor]=" + sensorCode + "）。请求XML如下：\r\n" + doc.asXML());
				return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101001, ErrorExtendParam.SENSOR_ID,
						sensorCode);
			}
			Sensor sensor = this.sensorService.getSensorbySensorCode(sensorCode);
			if (null == sensor) {
				logger.error("[上传心跳信息] - ID校验错误[ID不存在]：（id[sensor]=" + sensorCode + "）。请求XML如下：\r\n" + doc.asXML());
				return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101002, ErrorExtendParam.SENSOR_ID,
						sensorCode);
			}
			//if (!cmaCode.equals(sensor.getCmaInfo().getCmaCode())) {
			//	logger.error("[上传心跳信息] - ID校验错误[CMA与所辖状态监测装置]：（id[cma]=" + cmaCode + "；id[sensor]=" + sensorCode
			//			+ ",所属CMA=" + sensor.getCmaInfo().getCmaCode() + "）。请求XML如下：\r\n" + doc.asXML());
			//	return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101003, ErrorExtendParam.SENSOR_ID,
			//			sensorCode);
			//}
		}
		return null;
	}

	/**
	 * 上传心跳信息：数据格式校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	@SuppressWarnings("rawtypes")
	private ValidateResult validateUploadCMAHeartbeatInfoRequestXMLDataFormat(Document doc) {
		Element root = doc.getRootElement(); // 取得根节点

		/* 校验<cma />节点 */
		Element cmaNode = (Element) root.element("cma");
		String cmaCode = cmaNode.attributeValue("id");
		Element cmaCurTimeNode = cmaNode.element("curtime");
		Element cmaBatteryVoltageNode = cmaNode.element("batteryvoltage");
		Element cmaOperationTemperatureNode = cmaNode.element("operationtemperature");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date curTimeDate = sdf.parse(cmaCurTimeNode.getText());
			if (!sdf.format(curTimeDate).equals(cmaCurTimeNode.getText())) {
				logger.error("[上传心跳信息] - 数据格式错误[时间格式错误]：（curtime=" + cmaCurTimeNode.getText() + "）。请求XML如下：\r\n"
						+ doc.asXML());
				return ValidateResult
						.getFailedValidateResult(ErrorCode.ERROR_0102001, ErrorExtendParam.CMA_ID, cmaCode);
			}
			Double.parseDouble(cmaBatteryVoltageNode.getText());
			Double.parseDouble(cmaOperationTemperatureNode.getText());
		} catch (ParseException e) {
			logger.error("[上传心跳信息] - 数据格式错误[数据无法转换至对应格式]：（curtime=" + cmaCurTimeNode.getText() + "）。请求XML如下：\r\n"
					+ doc.asXML());
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0102002, ErrorExtendParam.CMA_ID, cmaCode);
		} catch (NumberFormatException ex) {
			logger.error("[上传心跳信息] - 数据格式错误[数据无法转换至对应格式]：（batteryvoltage[cma]=" + cmaBatteryVoltageNode.getText()
					+ "； operationtemperature[cma]=" + cmaOperationTemperatureNode.getText() + "）。请求XML如下：\r\n"
					+ doc.asXML());
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0102002, ErrorExtendParam.CMA_ID, cmaCode);
		}

		/* 校验<sensors />节点 */
		Element sensorsNode = (Element) root.element("sensors");
		for (Iterator iterator = sensorsNode.elementIterator(); iterator.hasNext();) {
			Element sensorNode = (Element) iterator.next();
			String sensorCode = sensorNode.attributeValue("id");
			Element sensorBatteryVoltageNode = sensorNode.element("batteryvoltage");
			Element sensorOperationTemperatureNode = sensorNode.element("operationtemperature");
			try {
				Double.parseDouble(cmaBatteryVoltageNode.getText());
				Double.parseDouble(cmaOperationTemperatureNode.getText());
			} catch (NumberFormatException ex) {
				logger.error("[上传心跳信息] - 数据格式错误[数据无法转换至对应格式]：（batteryvoltage[sensor]="
						+ sensorBatteryVoltageNode.getText() + "；operationtemperature[sensor]="
						+ sensorOperationTemperatureNode.getText() + "）。请求XML如下：\r\n" + doc.asXML());
				return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0102002, ErrorExtendParam.SENSOR_ID,
						sensorCode);
			}
		}
		return null;
	}

	/**
	 * 上传心跳信息：数据内容校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	@SuppressWarnings("rawtypes")
	private ValidateResult validateUploadCMAHeartbeatInfoRequestXMLDataContent(Document doc) {
		Element root = doc.getRootElement(); // 取得根节点

		/* 校验<cma />节点 */
		Element cmaNode = (Element) root.element("cma");
		String cmaCode = cmaNode.attributeValue("id");
		Element cmaIpNode = cmaNode.element("ip");
		Element cmaBatteryVoltageNode = cmaNode.element("batteryvoltage");
		Element cmaOperationTemperatureNode = cmaNode.element("operationtemperature");
		Element cmaFloatingChargeNode = cmaNode.element("floatingcharge");
		boolean checkIp = Pattern
				.matches(
						"^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$",
						cmaIpNode.getText());
		if (!checkIp
				|| !this.validateDecimalFormat(cmaBatteryVoltageNode.getText(), 2)
				|| !this.validateDecimalFormat(cmaOperationTemperatureNode.getText(), 2)
				|| !(XMLBusinessKeys.UPLOADCMAHEARTBEATINFO_CMA_FLOATINGCHARGE_CHARGE.equals(cmaFloatingChargeNode
						.getText()) || XMLBusinessKeys.UPLOADCMAHEARTBEATINFO_CMA_FLOATINGCHARGE_DISCHARGE
						.equals(cmaFloatingChargeNode.getText()))) {
			logger.error("[上传心跳信息] - 数据内容错误[数据不符合业务规范]：（ip=" + cmaIpNode.getText() + "；batteryvoltage[cma]="
					+ cmaBatteryVoltageNode.getText() + "；operationtemperature[cma]="
					+ cmaOperationTemperatureNode.getText() + "；floatingcharge[cma]=" + cmaFloatingChargeNode.getText()
					+ "）。请求XML如下：\r\n" + doc.asXML());
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103005, ErrorExtendParam.CMA_ID, cmaCode);
		}

		/* 校验<sensors />节点 */
		Element sensorsNode = (Element) root.element("sensors");
		for (Iterator iterator = sensorsNode.elementIterator(); iterator.hasNext();) {
			Element sensorNode = (Element) iterator.next();
			String sensorCode = sensorNode.attributeValue("id");
			Element sensorStatusNode = sensorNode.element("status");
			Element sensorBatteryVoltageNode = sensorNode.element("batteryvoltage");
			Element sensorOperationTemperatureNode = sensorNode.element("operationtemperature");
			Element sensorFloatingChargeNode = sensorNode.element("floatingcharge");
			if (!(XMLBusinessKeys.UPLOADCMAHEARTBEATINFO_SENSOR_STATUS_NORMAL.equals(sensorStatusNode.getText()) || XMLBusinessKeys.UPLOADCMAHEARTBEATINFO_SENSOR_STATUS_BREAK
					.equals(sensorStatusNode.getText()))
					|| !this.validateDecimalFormat(sensorBatteryVoltageNode.getText(), 2)
					|| !this.validateDecimalFormat(sensorOperationTemperatureNode.getText(), 2)
					|| !(XMLBusinessKeys.UPLOADCMAHEARTBEATINFO_SENSOR_FLOATINGCHARGE_CHARGE
							.equals(sensorFloatingChargeNode.getText()) || XMLBusinessKeys.UPLOADCMAHEARTBEATINFO_SENSOR_FLOATINGCHARGE_DISCHARGE
							.equals(sensorFloatingChargeNode.getText()))) {
				logger.error("[上传心跳信息] - 数据内容错误[数据不符合业务规范]：（status[sensor]=" + sensorStatusNode.getText()
						+ "；batteryvoltage[sensor]=" + sensorBatteryVoltageNode.getText()
						+ "；operationtemperature[sensor]=" + sensorOperationTemperatureNode.getText()
						+ "；floatingcharge[sensor]=" + cmaFloatingChargeNode.getText() + "）。请求XML如下：\r\n" + doc.asXML());
				return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103005, ErrorExtendParam.SENSOR_ID,
						sensorCode);
			}
		}
		return null;
	}

	/**
	 * 上传心跳信息：获取此次交互的所有监测代理编码。
	 * 
	 * @param doc
	 *            Document对象
	 * @return 此次交互的所有监测代理
	 */
	private List<String> getUploadCMAHeartbeatInfoCmaCodes(Document doc) {
		Element root = doc.getRootElement(); // 取得根节点

		/* 校验<cma />节点 */
		Element cmaNode = (Element) root.element("cma");
		String cmaCode = cmaNode.attributeValue("id");
		List<String> result = new ArrayList<String>();
		result.add(cmaCode);
		return result;
	}

	/**
	 * 上传心跳信息：获取此次交互的所有监测装置编码。
	 * 
	 * @param doc
	 *            Document对象
	 * @return 此次交互的所有监测代理
	 */
	@SuppressWarnings("rawtypes")
	private List<String> getUploadCMAHeartbeatInfoSensorCodes(Document doc) {
		List<String> result = new ArrayList<String>();
		Element root = doc.getRootElement(); // 取得根节点

		/* 校验<sensors />节点 */
		Element sensorsNode = (Element) root.element("sensors");
		for (Iterator iterator = sensorsNode.elementIterator(); iterator.hasNext();) {
			Element sensorNode = (Element) iterator.next();
			String sensorCode = sensorNode.attributeValue("id");
			result.add(sensorCode);
		}
		return result;
	}

	// ---------------------------------------------------------------------------------------
	// 上传监测数据
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.cag.service.ProtocolValidationService#
	 * validateUploadCMADataRequestXML(java.lang.String)
	 */
	@Override
	public ValidateResult validateUploadCMADataRequestXML(String strXMLParams) {
		Document doc = XMLUtil.parseToXML(strXMLParams); // 转换成XML

		if ( null == doc ){
			logger.error("XML格式错误，无法转换.");
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0100002, null, null);
		}
		
		/* xml校验 */
		ValidateResult xmlValidationResult = this
				.validateXML(strXMLParams, uploadCMADataByte, UPLOAD_CMA_DATA);
		if (null != xmlValidationResult) {
			return xmlValidationResult;
		}

		/* ID校验 */
		ValidateResult idValidationResult = this.validateUploadCMADataRequestXMLId(doc);
		if (null != idValidationResult) {
			return idValidationResult;
		}

		/* 数据格式校验 */
		ValidateResult dataFormatValidationResult = this.validateUploadCMADataRequestXMLDataFormat(doc);
		if (null != dataFormatValidationResult) {
			return dataFormatValidationResult;
		}

		/* 数据内容校验 */
		ValidateResult dataContentValidationResult = this.validateUploadCMADataRequestXMLDataContent(doc);
		if (null != dataContentValidationResult) {
			return dataContentValidationResult;
		}

		/* 静态图片上传校验 */

		/* 未知错误类型校验 */

		/* 获取此次交互的所有监测代理编码 */
		List<String> cmaCodes = this.getUploadCMADataCmaCodes(doc);

		/* 获取此次交互的所有监测装置编码 */
		List<String> sensorCodes = this.getUploadCMADataSensorCodes(doc);

		return ValidateResult.getSucceedValidateResult(cmaCodes, sensorCodes);
	}

	/**
	 * 上传监测数据：ID校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	@SuppressWarnings("rawtypes")
	private ValidateResult validateUploadCMADataRequestXMLId(Document doc) {
		Element root = doc.getRootElement(); // 取得根节点
		Element monitorDataNode = (Element) root.element("monitordata");
		String cmaCode = monitorDataNode.attributeValue("cmaid");

		/* ID校验：ID不合法 */
		if (17 != cmaCode.length()) {
			logger.error("[上传监测数据] - ID校验错误[ID不合法]：（cmaid=" + cmaCode + "）。请求XML如下：\r\n" + doc.asXML());
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101001, ErrorExtendParam.CMA_ID, cmaCode);
		}
		CmaInfo cmaInfo = this.cmaInfoService.getCmaInfobyCmaCode(cmaCode);

		/* ID校验：ID不存在 */
		if (null == cmaInfo) {
			logger.error("[上传监测数据] - ID校验错误[ID不存在]：（cmaid=" + cmaCode + "）。请求XML如下：\r\n" + doc.asXML());
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101002, ErrorExtendParam.CMA_ID, cmaCode);
		}
		for (Iterator ite = monitorDataNode.elementIterator(); ite.hasNext();) {
			Element dataNode = (Element) ite.next();
			String sensorCode = dataNode.attributeValue("sensorid");
			Element equipmentIdNode = dataNode.element("equipmentid");
			if (17 != sensorCode.trim().length() || 17 != equipmentIdNode.getText().trim().length()) {
				logger.error("[上传监测数据] - ID校验错误[ID不合法]：（sensorid=" + sensorCode + "）。请求XML如下：\r\n" + doc.asXML());
				return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101001, ErrorExtendParam.SENSOR_ID,
						sensorCode);
			}
			Sensor sensor = this.sensorService.getSensorbySensorCode(sensorCode);
			if (null == sensor) {
				logger.error("[上传监测数据] - ID校验错误[ID不存在]：（sensorid=" + sensorCode + "）。请求XML如下：\r\n" + doc.asXML());
				return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101002, ErrorExtendParam.SENSOR_ID,
						sensorCode);
			//} else if (!sensor.getCmaInfo().getCmaCode().equals(cmaCode)) {
			//	logger.error("[上传监测数据] - ID校验错误[CMA与所辖状态监测装置不匹配]：（cmaid=" + cmaCode + "；sensorid=" + sensorCode
			//			+ ",所属CMA=" + sensor.getCmaInfo().getCmaCode() + "）。请求XML如下：\r\n" + doc.asXML());
			//	return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101003, ErrorExtendParam.SENSOR_ID,
			//			sensorCode);
			} else if (!sensor.getBySensorCode().equals(equipmentIdNode.getText())) {
				logger.error("[上传监测数据] - ID校验错误[状态监测装置与被监测设备不匹配]：（sensorid=" + sensorCode + "；equipmentid="
						+ equipmentIdNode.getText() + "）。请求XML如下：\r\n" + doc.asXML());
				return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101004, ErrorExtendParam.SENSOR_ID,
						sensorCode);
			}
		}
		return null;
	}

	/**
	 * 上传监测数据：数据格式校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	@SuppressWarnings("rawtypes")
	private ValidateResult validateUploadCMADataRequestXMLDataFormat(Document doc) {
		Element root = doc.getRootElement(); // 取得根节点
		Element monitorDataNode = (Element) root.element("monitordata");
		String cmaCode = monitorDataNode.attributeValue("cmaid");
		String dataNodeNum = monitorDataNode.attributeValue("datanodenum");

		/* 数据格式校验 */
		try {
			Integer.parseInt(dataNodeNum);
		} catch (NumberFormatException ex) {
			logger.error("[上传监测数据] - 数据格式错误[数据无法转换至对应格式]：（datanodenum=" + dataNodeNum + "）。请求XML如下：\r\n" + doc.asXML());
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0102002, ErrorExtendParam.CMA_ID, cmaCode);
		}

		for (Iterator iterator = monitorDataNode.elementIterator(); iterator.hasNext();) {
			Element dataNode = (Element) iterator.next();
			String sensorCode = dataNode.attributeValue("sensorid");
			Element typeNode = dataNode.element("type");
			// Element equipmentIdNode = dataNode.element("equipmentid");
			Element timestampNode = dataNode.element("timestamp");
			Element attrsNode = dataNode.element("attrs");

			/* 数据格式校验 */
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date curTimeDate = sdf.parse(timestampNode.getText());
				if (!sdf.format(curTimeDate).equals(timestampNode.getText())) {
					logger.error("[上传监测数据] - 数据格式错误[时间格式错误]：（timestamp=" + timestampNode.getText() + "）。请求XML如下：\r\n"
							+ doc.asXML());
					return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0102001, ErrorExtendParam.SENSOR_ID,
							sensorCode);
				}
			} catch (ParseException e) {
				logger.error("[上传监测数据] - 数据格式错误[数据无法转换至对应格式]：（timestamp=" + timestampNode.getText() + "）。请求XML如下：\r\n"
						+ doc.asXML());
				return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0102002, ErrorExtendParam.SENSOR_ID,
						sensorCode);
			}
			String type = typeNode.getText();
			if (MonitoringTypeCode.ASOLIAN_VIBRATION.getCode().equals(type)) { // 微风振动

			} else if (MonitoringTypeCode.DANCING.getCode().equals(type)) { // 舞动

			} else if (MonitoringTypeCode.DEGREEE_OF_SITE_CONTAMINATION.getCode().equals(type)) { // 现场污秽度

			} else if (MonitoringTypeCode.ICE.getCode().equals(type)) { // 覆冰

			} else if (MonitoringTypeCode.IMAGE_VIDEO.getCode().equals(type)) { // 图像/视频

			} else if (MonitoringTypeCode.INSULATORMONSOONS.getCode().equals(type)) { // 绝缘子串风偏
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					String alarm = attrNode.attributeValue("alarm");
					InsulatorMonsoonItem item = new InsulatorMonsoonItem(name, value, alarm);
					if (!item.validateConversion()) {
						logger.error("[上传监测数据][绝缘子串风偏] - 数据格式错误[数据无法转换至对应格式]：（" + item.toString() + "）。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0102002,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
				}
			} else if (MonitoringTypeCode.MONSOON.getCode().equals(type)) { // 风偏

			} else if (MonitoringTypeCode.TOWER_TILT.getCode().equals(type)) { // 杆塔倾斜
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					String alarm = attrNode.attributeValue("alarm");
					TowerTiltItem item = new TowerTiltItem(name, value, alarm);
					if (!item.validateConversion()) {
						logger.error("[上传监测数据][杆塔倾斜] - 数据格式错误[数据无法转换至对应格式]：（" + item.toString() + "）。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0102002,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
				}
			} else if (MonitoringTypeCode.WEATHER.getCode().equals(type)) { // 微气象
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					String alarm = attrNode.attributeValue("alarm");
					WeatherItem item = new WeatherItem(name, value, alarm);
					if (!item.validateConversion()) {
						logger.error("[上传监测数据][微气象] - 数据格式错误[数据无法转换至对应格式]：（" + item.toString() + "）。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0102002,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
				}
			} else if (MonitoringTypeCode.WHITEMONSOON.getCode().equals(type)) { // 相间风偏
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					String alarm = attrNode.attributeValue("alarm");
					WhiteMonsoonItem item = new WhiteMonsoonItem(name, value, alarm);
					if (!item.validateConversion()) {
						logger.error("[上传监测数据][相间风偏] - 数据格式错误[数据无法转换至对应格式]：（" + item.toString() + "）。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0102002,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
				}
			} else if (MonitoringTypeCode.WIRE_SAG.getCode().equals(type)) { // 导线弧垂
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					String alarm = attrNode.attributeValue("alarm");
					WireSagItem item = new WireSagItem(name, value, alarm);
					if (!item.validateConversion()) {
						logger.error("[上传监测数据][导线弧垂] - 数据格式错误[数据无法转换至对应格式]：（" + item.toString() + "）。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0102002,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
				}
			} else if (MonitoringTypeCode.WIRE_TEMPERATURE.getCode().equals(type)) { // 导线温度
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					String alarm = attrNode.attributeValue("alarm");
					WireTemperatureItem item = new WireTemperatureItem(name, value, alarm);
					if (!item.validateConversion()) {
						logger.error("[上传监测数据][导线温度] - 数据格式错误[数据无法转换至对应格式]：（" + item.toString() + "）。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0102002,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
				}
			}
		}
		return null;
	}

	/**
	 * 上传监测数据：数据内容校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	/*
	 * 根据2011-10-19的讨论会议决定，不进行“参数缺失”校验一项，且不强制<attr />标签个数。会议大致内容如下：
	 * 项目中“相间风偏”的类型编码，改为与“风偏监测”相同，即“013004”。“绝缘子串风偏”不做。
	 * 2、在错误类型编码中的“数据内容错误”——“0103004 调用服务方法出错，监测参数缺失”不作校验；
	 * 3、各数据类型的数据项不作强制，只上传有采集到的数据项，即<attr />只包含采集到的数据。
	 * 3、读参数配置中，页面上以读取到的参数显示到页面上，不对这些参数进行下发配置处理。
	 */
	@SuppressWarnings("rawtypes")
	private ValidateResult validateUploadCMADataRequestXMLDataContent(Document doc) {
		Element root = doc.getRootElement(); // 取得根节点
		Element monitorDataNode = (Element) root.element("monitordata");
		String cmaCode = monitorDataNode.attributeValue("cmaid");
		String dataNodeNum = monitorDataNode.attributeValue("datanodenum");
		if (Integer.parseInt(dataNodeNum) != monitorDataNode.elements().size()) {
			logger.error("[上传监测数据] - 数据内容错误[数据不符合业务规范]：（datanodenum=" + Integer.parseInt(dataNodeNum)
					+ "与<datanode />数目=" + monitorDataNode.elements().size() + "不相同）。请求XML如下：\r\n" + doc.asXML());
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103005, ErrorExtendParam.CMA_ID, cmaCode);
		}
		for (Iterator iterator = monitorDataNode.elementIterator(); iterator.hasNext();) {
			Element dataNode = (Element) iterator.next();
			String sensorCode = dataNode.attributeValue("sensorid");
			Element typeNode = dataNode.element("type");
			// Element equipmentIdNode = dataNode.element("equipmentid");
			// Element timestampNode = dataNode.element("timestamp");
			Element attrsNode = dataNode.element("attrs");
			String type = typeNode.getText();
			if (!monitoringTypeCodes.contains(type)) {
				logger.error("[上传监测数据] - 数据内容错误[监测类型不存在]：（type=" + type + "）。请求XML如下：\r\n" + doc.asXML());
				return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103002, ErrorExtendParam.SENSOR_ID,
						sensorCode);
			}
			if (MonitoringTypeCode.ASOLIAN_VIBRATION.getCode().equals(type)) { // 微风振动

			} else if (MonitoringTypeCode.DANCING.getCode().equals(type)) { // 舞动

			} else if (MonitoringTypeCode.DEGREEE_OF_SITE_CONTAMINATION.getCode().equals(type)) { // 现场污秽度

			} else if (MonitoringTypeCode.ICE.getCode().equals(type)) { // 覆冰

			} else if (MonitoringTypeCode.IMAGE_VIDEO.getCode().equals(type)) { // 图像/视频

			} else if (MonitoringTypeCode.INSULATORMONSOONS.getCode().equals(type)) { // 绝缘子串风偏
//				if (InsulatorMonsoonItem.items.size() > attrsNode.elements().size()) {
//					logger.error("[上传监测数据][绝缘子串风偏] - 数据内容错误[监测参数缺失]：（应包括：" + InsulatorMonsoonItem.items.toString()
//							+ "）。请求XML如下：\r\n" + doc.asXML());
//					return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103004, ErrorExtendParam.SENSOR_ID,
//							sensorCode);
//				}
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					String alarm = attrNode.attributeValue("alarm");
					InsulatorMonsoonItem item = new InsulatorMonsoonItem(name, value, alarm);
					if (!item.validateParamExist()) {
						logger.error("[上传监测数据][绝缘子串风偏] - 数据内容错误监测参数不存在：" + item.toString() + "）。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103003,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
					if (!item.validateBusiness()) {
						logger.error("[上传监测数据][绝缘子串风偏] - 数据内容错误[数据不符合业务规范]：（" + item.toString() + "）。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103005,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
				}
			} else if (MonitoringTypeCode.MONSOON.getCode().equals(type)) { // 风偏

			} else if (MonitoringTypeCode.TOWER_TILT.getCode().equals(type)) { // 杆塔倾斜
//				if (TowerTiltItem.items.size() > attrsNode.elements().size()) {
//					logger.error("[上传监测数据][杆塔倾斜] - 数据内容错误[监测参数缺失]：（应包括：" + TowerTiltItem.items.toString()
//							+ "）。请求XML如下：\r\n" + doc.asXML());
//					return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103004, ErrorExtendParam.SENSOR_ID,
//							sensorCode);
//				}
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					String alarm = attrNode.attributeValue("alarm");
					TowerTiltItem item = new TowerTiltItem(name, value, alarm);
					if (!item.validateParamExist()) {
						logger.error("[上传监测数据][杆塔倾斜] - 数据内容错误[监测参数不存在]：（" + item.toString() + "）。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103003,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
					if (!item.validateBusiness()) {
						logger.error("[上传监测数据][杆塔倾斜] - 数据内容错误[数据不符合业务规范]：（" + item.toString() + "）。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103005,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
				}
			} else if (MonitoringTypeCode.WEATHER.getCode().equals(type)) { // 微气象
//				if (WeatherItem.items.size() > attrsNode.elements().size()) {
//					logger.error("[上传监测数据][微气象] - 数据内容错误[监测参数缺失]：（应包括：" + WeatherItem.items.toString()
//							+ "）。请求XML如下：\r\n" + doc.asXML());
//					return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103004, ErrorExtendParam.SENSOR_ID,
//							sensorCode);
//				}
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					String alarm = attrNode.attributeValue("alarm");
					WeatherItem item = new WeatherItem(name, value, alarm);
					if (!item.validateParamExist()) {
						logger.error("[上传监测数据][微气象] - 数据内容错误[监测参数不存在]：（" + item.toString() + "）。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103003,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
					if (!item.validateBusiness()) {
						logger.error("[上传监测数据][微气象] - 数据内容错误[数据不符合业务规范]：（" + item.toString() + "）。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103005,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
				}
			} else if (MonitoringTypeCode.WHITEMONSOON.getCode().equals(type)) { // 相间风偏
//				if (WhiteMonsoonItem.items.size() > attrsNode.elements().size()) {
//					logger.error("[上传监测数据][相间风偏] - 数据内容错误[监测参数缺失]：（应包括：" + WhiteMonsoonItem.items.toString()
//							+ "）。请求XML如下：\r\n" + doc.asXML());
//					return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103004, ErrorExtendParam.SENSOR_ID,
//							sensorCode);
//				}
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					String alarm = attrNode.attributeValue("alarm");
					WhiteMonsoonItem item = new WhiteMonsoonItem(name, value, alarm);
					if (!item.validateParamExist()) {
						logger.error("[上传监测数据][相间风偏] - 数据内容错误：监测参数不存在（" + item.toString() + "））。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103003,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
					if (!item.validateBusiness()) {
						logger.error("[上传监测数据][相间风偏] - 数据内容错误[数据不符合业务规范]：（" + item.toString() + "）。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103005,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
				}
			} else if (MonitoringTypeCode.WIRE_SAG.getCode().equals(type)) { // 导线弧垂
//				if (WireSagItem.items.size() > attrsNode.elements().size()) {
//					logger.error("[上传监测数据][导线弧垂] - 数据内容错误[监测参数缺失]：（应包括：" + WireSagItem.items.toString()
//							+ "）。请求XML如下：\r\n" + doc.asXML());
//					return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103004, ErrorExtendParam.SENSOR_ID,
//							sensorCode);
//				}
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					String alarm = attrNode.attributeValue("alarm");
					WireSagItem item = new WireSagItem(name, value, alarm);
					if (!item.validateParamExist()) {
						logger.error("[上传监测数据][导线弧垂] - 数据内容错误[监测参数不存在]：（" + item.toString() + "）。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103003,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
					if (!item.validateBusiness()) {
						logger.error("[上传监测数据][导线弧垂] - 数据内容错误[数据不符合业务规范]：（" + item.toString() + "）。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103005,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
				}
			} else if (MonitoringTypeCode.WIRE_TEMPERATURE.getCode().equals(type)) { // 导线温度
//				if (WireTemperatureItem.items.size() > attrsNode.elements().size()) {
//					logger.error("[上传监测数据][导线温度] - 数据内容错误[监测参数缺失]：（应包括：" + WireTemperatureItem.items.toString()
//							+ "）。请求XML如下：\r\n" + doc.asXML());
//					return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103004, ErrorExtendParam.SENSOR_ID,
//							sensorCode);
//				}
				for (Iterator ite = attrsNode.elementIterator(); ite.hasNext();) {
					Element attrNode = (Element) ite.next();
					String name = attrNode.attributeValue("name");
					String value = attrNode.attributeValue("value");
					String alarm = attrNode.attributeValue("alarm");
					WireTemperatureItem item = new WireTemperatureItem(name, value, alarm);
					if (!item.validateParamExist()) {
						logger.error("[上传监测数据][导线温度] - 数据内容错误[监测参数不存在]：（" + item.toString() + "）。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103003,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
					if (!item.validateBusiness()) {
						logger.error("[上传监测数据][导线温度] - 数据内容错误[数据不符合业务规范]：（" + item.toString() + "）。请求XML如下：\r\n"
								+ doc.asXML());
						return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103005,
								ErrorExtendParam.SENSOR_ID, sensorCode);
					}
				}
			}
		}
		return null;
	}

	/**
	 * 使用正则表达式校验str是否满足数字格式.
	 * 
	 * @param str
	 *            要进行校验的数字型字符串
	 * @param pos
	 *            数字的小数点位数
	 * @return true - 成功; false - 失败;
	 */
	private boolean validateDecimalFormat(String str, int pos) {
		return true;
		/*
		String regex = "-?(\\d)+";
		if (pos > 0) {
			regex = regex + ".(\\d{1," + pos + "})";
		}
		return Pattern.matches(regex, str);
		*/
	}

	/**
	 * 上传监测数据：获取此次交互的所有监测代理编码。
	 * 
	 * @param doc
	 *            Document对象
	 * @return 此次交互的所有监测代理
	 */
	private List<String> getUploadCMADataCmaCodes(Document doc) {
		List<String> result = new ArrayList<String>();
		Element root = doc.getRootElement(); // 取得根节点
		Element monitorDataNode = (Element) root.element("monitordata");
		String cmaCode = monitorDataNode.attributeValue("cmaid");
		result.add(cmaCode);
		return result;
	}

	/**
	 * 上传监测数据：获取此次交互的所有监测装置编码。
	 * 
	 * @param doc
	 *            Document对象
	 * @return 此次交互的所有监测代理
	 */
	@SuppressWarnings("rawtypes")
	private List<String> getUploadCMADataSensorCodes(Document doc) {
		List<String> result = new ArrayList<String>();
		Element root = doc.getRootElement(); // 取得根节点
		Element monitorDataNode = (Element) root.element("monitordata");
		for (Iterator ite = monitorDataNode.elementIterator(); ite.hasNext();) {
			Element dataNode = (Element) ite.next();
			String sensorCode = dataNode.attributeValue("sensorid");
			result.add(sensorCode);
		}
		return result;
	}

	// ---------------------------------------------------------------------------------------
	// 上传静态图片数据
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.cag.service.ProtocolValidationService#
	 * validateUploadCMAImageRequestXML(byte[], java.lang.String)
	 */
	@Override
	public ValidateResult validateUploadCMAImageRequestXML(byte[] imageByte, String strXMLParams) {
		Document doc = XMLUtil.parseToXML(strXMLParams); // 转换成XML

		if ( null == doc ){
			logger.error("XML格式错误，无法转换.");
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0100002, null, null);
		}
		
		/* xml校验 */
		ValidateResult xmlValidationResult = this.validateXML(strXMLParams, uploadCMAImageByte,
				UPLOAD_CMA_IMAGE);
		if (null != xmlValidationResult) {
			return xmlValidationResult;
		}

		/* ID校验 */
		ValidateResult idValidationResult = this.validateUploadCMAImageRequestXMLId(doc);
		if (null != idValidationResult) {
			return idValidationResult;
		}

		/* 数据格式校验 */
		ValidateResult dataFormatValidationResult = this.validateUploadCMAImageRequestXMLDataFormat(doc);
		if (null != dataFormatValidationResult) {
			return dataFormatValidationResult;
		}

		/* 数据内容校验 */
		ValidateResult dataContentValidationResult = this.validateUploadCMAImageRequestXMLDataContent(doc);
		if (null != dataContentValidationResult) {
			return dataContentValidationResult;
		}

		/* 静态图片上传校验 */
		ValidateResult imageDataValidationResult = this.validateUploadCMAImageRequestXMLImageData(doc);
		if (null != imageDataValidationResult) {
			return imageDataValidationResult;
		}

		/* 未知错误类型校验 */

		/* 获取此次交互的所有监测代理编码 */
		List<String> cmaCodes = this.getUploadCMAImageCmaCodes(doc);

		/* 获取此次交互的所有监测装置编码 */
		List<String> sensorCodes = this.getUploadCMAImageSensorCodes(doc);

		return ValidateResult.getSucceedValidateResult(cmaCodes, sensorCodes);
	}

	/**
	 * 上传静态图片数据：ID校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	private ValidateResult validateUploadCMAImageRequestXMLId(Document doc) {
		Element root = doc.getRootElement(); // 取得根节点
		Element commandNode = root.element("command");
		String cmaCode = commandNode.attributeValue("id");

		/* ID校验：ID不合法 */
		if (17 != cmaCode.length()) {
			logger.error("[获取更新文件] - ID校验错误[ID不合法]：（id=" + cmaCode + "）。请求XML如下：\r\n" + doc.asXML());
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101001, ErrorExtendParam.CMA_ID, cmaCode);
		}

		/* ID校验：ID不存在 */
		CmaInfo cmaInfo = this.cmaInfoService.getCmaInfobyCmaCode(cmaCode);
		if (null == cmaInfo) {
			logger.error("[获取更新文件] - ID校验错误[ID不存在]：（id=" + cmaCode + "）。请求XML如下：\r\n" + doc.asXML());
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101002, ErrorExtendParam.CMA_ID, cmaCode);
		}
		return null;
	}

	/**
	 * 上传静态图片数据：数据格式校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	private ValidateResult validateUploadCMAImageRequestXMLDataFormat(Document doc) {
		// TODO 上传静态图片数据：数据格式校验。
		return null;
	}

	/**
	 * 上传静态图片数据：数据内容校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	private ValidateResult validateUploadCMAImageRequestXMLDataContent(Document doc) {
		// TODO 上传静态图片数据：数据内容校验。
		return null;
	}

	/**
	 * 上传静态图片数据：静态图片上传校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	private ValidateResult validateUploadCMAImageRequestXMLImageData(Document doc) {
		// TODO 上传静态图片数据：静态图片上传校验。
		return null;
	}

	/**
	 * 上传静态图片数据：获取此次交互的所有监测代理编码。
	 * 
	 * @param doc
	 *            Document对象
	 * @return 此次交互的所有监测代理
	 */
	private List<String> getUploadCMAImageCmaCodes(Document doc) {
		List<String> result = new ArrayList<String>();
		// TODO 上传监测数据：获取此次交互的所有监测代理编码。
		return result;
	}

	/**
	 * 上传静态图片数据：获取此次交互的所有监测装置编码。
	 * 
	 * @param doc
	 *            Document对象
	 * @return 此次交互的所有监测代理
	 */
	private List<String> getUploadCMAImageSensorCodes(Document doc) {
		List<String> result = new ArrayList<String>();
		// TODO 上传监测数据：获取此次交互的所有监测装置编码。
		return result;
	}

	// ---------------------------------------------------------------------------------------
	// 上传配置信息
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.cag.service.ProtocolValidationService#
	 * validateUploadCMAConfigRequestXML(java.lang.String)
	 */
	@Override
	public ValidateResult validateUploadCMAConfigRequestXML(String strXMLParams) {
		Document doc = XMLUtil.parseToXML(strXMLParams); // 转换成XML
		
		if ( null == doc ){
			logger.error("XML格式错误，无法转换.");
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0100002, null, null);
		}

		/* xml校验 */
		ValidateResult xmlValidationResult = this.validateXML(strXMLParams, uploadCMAConfigByte,
				UPLOAD_CMA_CONFIG);
		if (null != xmlValidationResult) {
			return xmlValidationResult;
		}

		/* ID校验 */
		ValidateResult idValidationResult = this.validateUploadCMAConfigRequestXMLId(doc);
		if (null != idValidationResult) {
			return idValidationResult;
		}

		/* 数据格式校验 */
		ValidateResult dataFormatValidationResult = this.validateUploadCMAConfigRequestXMLDataFormat(doc);
		if (null != dataFormatValidationResult) {
			return dataFormatValidationResult;
		}

		/* 数据内容校验 */
		ValidateResult dataContentValidationResult = this.validateUploadCMAConfigRequestXMLDataContent(doc);
		if (null != dataContentValidationResult) {
			return dataContentValidationResult;
		}

		/* 静态图片上传校验 */

		/* 未知错误类型校验 */

		/* 获取此次交互的所有监测代理编码 */		
		
		List<String> cmaCodes = this.getUploadCMAConfigCmaCodes(doc);

		/* 获取此次交互的所有监测装置编码 */
		List<String> sensorCodes = this.getUploadCMAConfigSensorCodes(doc);

		return ValidateResult.getSucceedValidateResult(cmaCodes, sensorCodes);
		
	}

	/**
	 * 上传配置信息：ID校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	@SuppressWarnings("rawtypes")
	private ValidateResult validateUploadCMAConfigRequestXMLId(Document doc) {
		Element root = doc.getRootElement(); // 取得根节点
		Element configsNode = root.element("configs");
		for (Iterator iterator = configsNode.elementIterator(); iterator.hasNext();) {
			Element configNode = (Element) iterator.next();
			String objId = configNode.attributeValue("objid");
			// String dataAcquireTime =
			// configNode.attributeValue("dataacquiretime");
			// String heartBeatTime =
			// configNode.attributeValue("heartbeattime");
			// String version = configNode.attributeValue("version");
			// String wakeUpReferenceTime =
			// configNode.attributeValue("wakeupreferencetime");
			// String wakeUpCycle = configNode.attributeValue("wakeupcycle");
			// String wakeUpTime = configNode.attributeValue("wakeuptime");

			/* ID校验 */
			if (17 != objId.length()) {
				logger.error("[上传配置信息] - ID校验错误[ID不合法]：（objid=" + objId + "）。请求XML如下：\r\n" + doc.asXML());
				return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101001, ErrorExtendParam.CMA_ID, objId);
			}

			/* 只有监测代理（CMA）才有参数配置功能 */
			CmaInfo cmaInfo = this.cmaInfoService.getCmaInfobyCmaCode(objId);
			
			//监测装置配置参数 暂时加上(测试）
			Sensor sensor = this.sensorService.getSensorbySensorCode(objId);
			
			if (null == cmaInfo && null == sensor) {
				logger.error("[上传配置信息] - ID校验错误[ID不存在]：（objid=" + objId + "）。请求XML如下：\r\n" + doc.asXML());
				return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101002, ErrorExtendParam.CMA_ID, objId);
			}
			
			
		}
		return null;
	}

	/**
	 * 上传配置信息：数据格式校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	@SuppressWarnings("rawtypes")
	private ValidateResult validateUploadCMAConfigRequestXMLDataFormat(Document doc) {
		Element root = doc.getRootElement(); // 取得根节点
		Element configsNode = root.element("configs");
		for (Iterator iterator = configsNode.elementIterator(); iterator.hasNext();) {
			Element configNode = (Element) iterator.next();
			String objId = configNode.attributeValue("objid");
			String dataAcquireTime = configNode.attributeValue("dataacquiretime");
			String heartBeatTime = configNode.attributeValue("heartbeattime");
			// String version = configNode.attributeValue("version");
			String wakeUpReferenceTime = configNode.attributeValue("wakeupreferencetime");
			String wakeUpCycle = configNode.attributeValue("wakeupcycle");
			String wakeUpTime = configNode.attributeValue("wakeuptime");

			/* 数据格式校验 */
			try {
				Integer.parseInt(dataAcquireTime);
				Integer.parseInt(heartBeatTime);
				Integer.parseInt(wakeUpReferenceTime);
				Integer.parseInt(wakeUpCycle);
				Integer.parseInt(wakeUpTime);
			} catch (NumberFormatException ex) {
				logger.error("[上传配置信息] - 数据格式错误[数据无法转换至对应格式]：（dataacquiretime=" + dataAcquireTime + "；heartbeattime="
						+ heartBeatTime + "；wakeupreferencetime=" + wakeUpReferenceTime + "；wakeupcycle=" + wakeUpCycle
						+ "；wakeuptime=" + wakeUpTime + "）。请求XML如下：\r\n" + doc.asXML());
				return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0102002, ErrorExtendParam.CMA_ID, objId);
			}

			for (Iterator ite = configNode.elementIterator(); ite.hasNext();) {
				Element attrNode = (Element) ite.next();
				// String desc = attrNode.attributeValue("desc");
				// String name = attrNode.attributeValue("name");
				// String type = attrNode.attributeValue("type");
				String length = attrNode.attributeValue("length");
				// String unit = attrNode.attributeValue("unit");
				// String range = attrNode.attributeValue("range");
				// String note = attrNode.attributeValue("note");
				// String value = attrNode.attributeValue("value");

				/* 数据格式校验 */
				try {
					Integer.parseInt(length);
				} catch (NumberFormatException ex) {
					logger.error("[上传配置信息] - 数据格式错误[数据无法转换至对应格式]：（length=" + length + "）。请求XML如下：\r\n" + doc.asXML());
					return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0102002, ErrorExtendParam.CMA_ID,
							objId);
				}
			}
		}
		return null;
	}

	/**
	 * 上传配置信息：数据内容校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	@SuppressWarnings("rawtypes")
	private ValidateResult validateUploadCMAConfigRequestXMLDataContent(Document doc) {
		Element root = doc.getRootElement(); // 取得根节点
		Element configsNode = root.element("configs");
		for (Iterator iterator = configsNode.elementIterator(); iterator.hasNext();) {
			Element configNode = (Element) iterator.next();
			String objId = configNode.attributeValue("objid");
			// String dataAcquireTime =
			// configNode.attributeValue("dataacquiretime");
			// String heartBeatTime =
			// configNode.attributeValue("heartbeattime");
			// String version = configNode.attributeValue("version");
			// String wakeUpReferenceTime =
			// configNode.attributeValue("wakeupreferencetime");
			String wakeUpCycle = configNode.attributeValue("wakeupcycle");
			String wakeUpTime = configNode.attributeValue("wakeuptime");

			/* 数据内容校验 */
			int wakeUpCycleData = Integer.parseInt(wakeUpCycle);
			int wakeUpTimeData = Integer.parseInt(wakeUpTime);
			if ((0 != wakeUpCycleData) || (wakeUpTimeData > wakeUpCycleData * 60)) {
				if (0 != 1440 % wakeUpCycleData) {
					logger.error("[上传配置信息] - 数据内容错误[数据不符合业务规范]：（wakeupcycle=" + wakeUpCycle + "）。请求XML如下：\r\n"
							+ doc.asXML());
					return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103005, ErrorExtendParam.CMA_ID,
							objId);
				}
			}

			for (Iterator ite = configNode.elementIterator(); ite.hasNext();) {
				Element attrNode = (Element) ite.next();
				String desc = attrNode.attributeValue("desc");
				String name = attrNode.attributeValue("name");
				String type = attrNode.attributeValue("type");
				// String length = attrNode.attributeValue("length");
				// String unit = attrNode.attributeValue("unit");
				// String range = attrNode.attributeValue("range");
				// String note = attrNode.attributeValue("note");
				// String value = attrNode.attributeValue("value");

				/* 数据内容校验 */
				if (40 < desc.getBytes().length
						|| 20 < name.getBytes().length
						|| !("INT".equals(type) || "FLOAT".equals(type) || "DOUBLE".equals(type) || "STRING"
								.equals(type))) {
					logger.error("[上传配置信息] - 数据内容错误[数据不符合业务规范]：（desc=" + desc + ";type=" + type + "）。请求XML如下：\r\n"
							+ doc.asXML());
					return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0103005, ErrorExtendParam.CMA_ID,
							objId);
				}
			}
		}
		return null;
	}

	/**
	 * 上传配置信息：获取此次交互的所有监测代理编码。
	 * 
	 * @param doc
	 *            Document对象
	 * @return 此次交互的所有监测代理
	 */
	@SuppressWarnings("rawtypes")
	private List<String> getUploadCMAConfigCmaCodes(Document doc) {
		List<String> result = new ArrayList<String>();
		Element root = doc.getRootElement(); // 取得根节点
		Element configsNode = root.element("configs");
		for (Iterator iterator = configsNode.elementIterator(); iterator.hasNext();) {
			Element configNode = (Element) iterator.next();
			String objId = configNode.attributeValue("objid");
			CmaInfo info = cmaInfoService.getCmaInfobyCmaCode(objId);
			//如果是CMA 
			if  ( null != info ){
				result.add(objId);
			}
		}
		return result;
	}

	/**
	 * 上传配置信息：获取此次交互的所有监测装置编码。
	 * 
	 * @param doc
	 *            Document对象
	 * @return 此次交互的所有监测代理
	 */
	@SuppressWarnings("rawtypes")
	private List<String> getUploadCMAConfigSensorCodes(Document doc) {
		
		List<String> result = new ArrayList<String>();
		Element root = doc.getRootElement(); // 取得根节点
		Element configsNode = root.element("configs");
		for (Iterator iterator = configsNode.elementIterator(); iterator.hasNext();) {
			Element configNode = (Element) iterator.next();
			String objId = configNode.attributeValue("objid");
			Sensor info = sensorService.getSensorbySensorCode(objId);
			//如果是监测装置 
			if  ( null != info ){
				result.add(objId);
			}
		}
		return result;
		
	}

	// ---------------------------------------------------------------------------------------
	// 下发控制指令
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.cag.service.ProtocolValidationService#
	 * validateDownloadCAGCtrlRequestXML(java.lang.String)
	 */
	@Override
	public ValidateResult validateDownloadCAGCtrlRequestXML(String strXMLParams) {
		Document doc = XMLUtil.parseToXML(strXMLParams); // 转换成XML

		if ( null == doc ){
			logger.error("XML格式错误，无法转换.");
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0100002, null, null);
		}
		
		/* xml校验 */
		ValidateResult xmlValidationResult = this.validateXML(strXMLParams, downloadCAGCtrlByte,
				DOWNLOAD_CAG_CTRL);
		if (null != xmlValidationResult) {
			return xmlValidationResult;
		}

		/* ID校验 */
		ValidateResult idValidationResult = this.validateDownloadCAGCtrlRequestXMLId(doc);
		if (null != idValidationResult) {
			return idValidationResult;
		}

		/* 数据格式校验 */
		ValidateResult dataFormatValidationResult = this.validateDownloadCAGCtrlRequestXMLDataFormat(doc);
		if (null != dataFormatValidationResult) {
			return dataFormatValidationResult;
		}

		/* 数据内容校验 */
		ValidateResult dataContentValidationResult = this.validateDownloadCAGCtrlRequestXMLDataContent(doc);
		if (null != dataContentValidationResult) {
			return dataContentValidationResult;
		}

		/* 静态图片上传校验 */

		/* 未知错误类型校验 */

		/* 获取此次交互的所有监测代理编码 */
		List<String> cmaCodes = this.getDownloadCAGCtrlCmaCodes(doc);

		/* 获取此次交互的所有监测装置编码 */
		List<String> sensorCodes = this.getDownloadCAGCtrlSensorCodes(doc);

		return ValidateResult.getSucceedValidateResult(cmaCodes, sensorCodes);
	}

	/**
	 * 下发控制指令：ID校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	private ValidateResult validateDownloadCAGCtrlRequestXMLId(Document doc) {
		// TODO 下发控制指令：ID校验。
		return null;
	}

	/**
	 * 下发控制指令：数据格式校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	private ValidateResult validateDownloadCAGCtrlRequestXMLDataFormat(Document doc) {
		// TODO 下发控制指令：数据格式校验。
		return null;
	}

	/**
	 * 下发控制指令：数据内容校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	private ValidateResult validateDownloadCAGCtrlRequestXMLDataContent(Document doc) {
		// TODO 下发控制指令：数据内容校验。
		return null;
	}

	/**
	 * 下发控制指令：获取此次交互的所有监测代理编码。
	 * 
	 * @param doc
	 *            Document对象
	 * @return 此次交互的所有监测代理
	 */
	private List<String> getDownloadCAGCtrlCmaCodes(Document doc) {
		List<String> result = new ArrayList<String>();
		// TODO 下发控制指令：获取此次交互的所有监测代理编码。
		return result;
	}

	/**
	 * 下发控制指令：获取此次交互的所有监测装置编码。
	 * 
	 * @param doc
	 *            Document对象
	 * @return 此次交互的所有监测代理
	 */
	private List<String> getDownloadCAGCtrlSensorCodes(Document doc) {
		List<String> result = new ArrayList<String>();
		// TODO 下发控制指令：获取此次交互的所有监测装置编码。
		return result;
	}

	// ---------------------------------------------------------------------------------------
	// 获取最新版本
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.cag.service.ProtocolValidationService#
	 * validateDownloadCMALatestVersionRequestXML(java.lang.String)
	 */
	@Override
	public ValidateResult validateDownloadCMALatestVersionRequestXML(String strXMLParams) {
		Document doc = XMLUtil.parseToXML(strXMLParams); // 转换成XML

		if ( null == doc ){
			logger.error("XML格式错误，无法转换.");
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0100002, null, null);
		}
		
		/* xml校验 */
		ValidateResult xmlValidationResult = this.validateXML(strXMLParams, downloadCMALatestVersionByte,
				DOWNLOAD_CMA_LATESTVERSION);
		if (null != xmlValidationResult) {
			return xmlValidationResult;
		}

		/* ID校验 */
		ValidateResult idValidationResult = this.validateDownloadCMALatestVersionRequestXMLId(doc);
		if (null != idValidationResult) {
			return idValidationResult;
		}

		/* 数据格式校验 */

		/* 数据内容校验 */

		/* 静态图片上传校验 */

		/* 未知错误类型校验 */

		/* 获取此次交互的所有监测代理编码 */
		List<String> cmaCodes = this.getDownloadCMALatestVersionCmaCodes(doc);

		/* 获取此次交互的所有监测装置编码 */
		List<String> sensorCodes = this.getDownloadCMALatestVersionSensorCodes(doc);

		return ValidateResult.getSucceedValidateResult(cmaCodes, sensorCodes);
	}

	/**
	 * 获取最新版本：ID校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	private ValidateResult validateDownloadCMALatestVersionRequestXMLId(Document doc) {
		Element root = doc.getRootElement(); // 取得根节点
		Element commandNode = root.element("command");
		String cmaCode = commandNode.attributeValue("id");

		/* ID校验：ID不合法 */
		if (17 != cmaCode.length()) {
			logger.error("[获取更新文件] - ID校验错误[ID不合法]：（id=" + cmaCode + "）。请求XML如下：\r\n" + doc.asXML());
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101001, ErrorExtendParam.CMA_ID, cmaCode);
		}

		/* ID校验：ID不存在 */
		CmaInfo cmaInfo = this.cmaInfoService.getCmaInfobyCmaCode(cmaCode);
		if (null == cmaInfo) {
			logger.error("[获取更新文件] - ID校验错误[ID不存在]：（id=" + cmaCode + "）。请求XML如下：\r\n" + doc.asXML());
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101002, ErrorExtendParam.CMA_ID, cmaCode);
		}
		return null;
	}

	/**
	 * 获取最新版本：获取此次交互的所有监测代理编码。
	 * 
	 * @param doc
	 *            Document对象
	 * @return 此次交互的所有监测代理
	 */
	private List<String> getDownloadCMALatestVersionCmaCodes(Document doc) {
		List<String> result = new ArrayList<String>();
		Element root = doc.getRootElement(); // 取得根节点
		Element commandNode = root.element("command");
		String cmaCode = commandNode.attributeValue("id");
		result.add(cmaCode);
		return result;
	}

	/**
	 * 获取最新版本：获取此次交互的所有监测装置编码。
	 * 
	 * @param doc
	 *            Document对象
	 * @return 此次交互的所有监测代理
	 */
	private List<String> getDownloadCMALatestVersionSensorCodes(Document doc) {
		List<String> result = new ArrayList<String>();
		return result;
	}

	// ---------------------------------------------------------------------------------------
	// 获取历史版本
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.cag.service.ProtocolValidationService#
	 * validateDownloadCMAHistoryVersionRequestXML(java.lang.String)
	 */
	@Override
	public ValidateResult validateDownloadCMAHistoryVersionRequestXML(String strXMLParams) {
		Document doc = XMLUtil.parseToXML(strXMLParams); // 转换成XML

		if ( null == doc ){
			logger.error("XML格式错误，无法转换.");
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0100002, null, null);
		}
		
		/* xml校验 */
		ValidateResult xmlValidationResult = this.validateXML(strXMLParams, downloadCMAHistoryVersionByte,
				DOWNLOAD_CMA_HISTORYVERSION);
		if (null != xmlValidationResult) {
			return xmlValidationResult;
		}

		/* ID校验 */
		ValidateResult idValidationResult = this.validateDownloadCMAHistoryVersionRequestXMLId(doc);
		if (null != idValidationResult) {
			return idValidationResult;
		}

		/* 数据格式校验 */

		/* 数据内容校验 */

		/* 静态图片上传校验 */

		/* 未知错误类型校验 */

		/* 获取此次交互的所有监测代理编码 */
		List<String> cmaCodes = this.getDownloadCMAHistoryVersionCmaCodes(doc);

		/* 获取此次交互的所有监测装置编码 */
		List<String> sensorCodes = this.getDownloadCMAHistoryVersionSensorCodes(doc);

		return ValidateResult.getSucceedValidateResult(cmaCodes, sensorCodes);
	}

	/**
	 * 获取历史版本：ID校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	private ValidateResult validateDownloadCMAHistoryVersionRequestXMLId(Document doc) {
		return this.validateDownloadCMALatestVersionRequestXMLId(doc);
	}

	/**
	 * 获取历史版本：获取此次交互的所有监测代理编码。
	 * 
	 * @param doc
	 *            Document对象
	 * @return 此次交互的所有监测代理
	 */
	private List<String> getDownloadCMAHistoryVersionCmaCodes(Document doc) {
		return this.getDownloadCMALatestVersionCmaCodes(doc);
	}

	/**
	 * 获取历史版本：获取此次交互的所有监测装置编码。
	 * 
	 * @param doc
	 *            Document对象
	 * @return 此次交互的所有监测代理
	 */
	private List<String> getDownloadCMAHistoryVersionSensorCodes(Document doc) {
		return this.getDownloadCMALatestVersionSensorCodes(doc);
	}

	// ---------------------------------------------------------------------------------------
	// 获取更新文件
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.cag.service.ProtocolValidationService#
	 * validateDownloadCMAUpdateFileRequestXML(java.lang.String)
	 */
	@Override
	public ValidateResult validateDownloadCMAUpdateFileRequestXML(String strXMLParams) {
		Document doc = XMLUtil.parseToXML(strXMLParams); // 转换成XML

		if ( null == doc ){
			logger.error("XML格式错误，无法转换.");
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0100002, null, null);
		}
		
		/* xml校验 */
		ValidateResult xmlValidationResult = this.validateXML(strXMLParams, downloadCMAUpdateFileByte,
				DOWNLOAD_CMA_UPDATEFILE);
		if (null != xmlValidationResult) {
			return xmlValidationResult;
		}

		/* ID校验 */
		ValidateResult idValidationResult = this.validateDownloadCMAUpdateFileRequestXMLId(doc);
		if (null != idValidationResult) {
			return idValidationResult;
		}

		/* 数据格式校验 */

		/* 数据内容校验 */

		/* 静态图片上传校验 */

		/* 未知错误类型校验 */

		/* 获取此次交互的所有监测代理编码 */
		List<String> cmaCodes = this.getDownloadCMAUpdateFileCmaCodes(doc);

		/* 获取此次交互的所有监测装置编码 */
		List<String> sensorCodes = this.getDownloadCMAUpdateFileSensorCodes(doc);

		return ValidateResult.getSucceedValidateResult(cmaCodes, sensorCodes);
	}

	/**
	 * 获取更新文件：ID校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param doc
	 *            要进行校验Document对象
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	private ValidateResult validateDownloadCMAUpdateFileRequestXMLId(Document doc) {
		Element root = doc.getRootElement(); // 取得根节点
		Element commandNode = root.element("command");
		String cmaCode = commandNode.attributeValue("id");

		/* ID校验：ID不合法 */
		if (17 != cmaCode.length()) {
			logger.error("[获取更新文件] - ID校验错误[ID不合法]：（id=" + cmaCode + "）。请求XML如下：\r\n" + doc.asXML());
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101001, ErrorExtendParam.CMA_ID, cmaCode);
		}

		/* ID校验：ID不存在 */
		CmaInfo cmaInfo = this.cmaInfoService.getCmaInfobyCmaCode(cmaCode);
		if (null == cmaInfo) {
			logger.error("[获取更新文件] - ID校验错误[ID不存在]：（id=" + cmaCode + "）。请求XML如下：\r\n" + doc.asXML());
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0101002, ErrorExtendParam.CMA_ID, cmaCode);
		}
		return null;
	}

	/**
	 * 上传监测数据：获取此次交互的所有监测代理编码。
	 * 
	 * @param doc
	 *            Document对象
	 * @return 此次交互的所有监测代理
	 */
	private List<String> getDownloadCMAUpdateFileCmaCodes(Document doc) {
		List<String> result = new ArrayList<String>();
		Element root = doc.getRootElement(); // 取得根节点
		Element commandNode = root.element("command");
		String cmaCode = commandNode.attributeValue("id");
		result.add(cmaCode);
		return result;
	}

	/**
	 * 上传监测数据：获取此次交互的所有监测装置编码。
	 * 
	 * @param doc
	 *            Document对象
	 * @return 此次交互的所有监测代理
	 */
	private List<String> getDownloadCMAUpdateFileSensorCodes(Document doc) {
		List<String> result = new ArrayList<String>();
		return result;
	}

	/**
	 * XML校验。校验失败时返回ValidateResult对象，否则返回null。
	 * 
	 * @param strXMLParams
	 *            要进行校验的XML字符串
	 * @param b
	 *            输入参数的schema对象流的数组
	 * @param type
	 *            进行校验的类型
	 * @return 校验失败时返回ValidateResult对象，否则返回null。
	 */
	private ValidateResult validateXML(String strXMLParams, byte[] b, int type) {
		String message = "";
		switch (type) {
		case UPLOAD_CMA_HEARTBEATINFO:
			message = "上传心跳信息 ";
			break;
		case UPLOAD_CMA_DATA:
			message = "上传监测数据 ";
			break;
		case UPLOAD_CMA_IMAGE:
			message = "上传静态图片数据 ";
			break;
		case UPLOAD_CMA_CONFIG:
			message = "上传配置信息 ";
			break;
		case DOWNLOAD_CAG_CTRL:
			message = "下发控制指令 ";
			break;
		case DOWNLOAD_CMA_LATESTVERSION:
			message = "获取最新版本 ";
			break;
		case DOWNLOAD_CMA_HISTORYVERSION:
			message = "获取历史版本 ";
			break;
		case DOWNLOAD_CMA_UPDATEFILE:
			message = "获取更新文件 ";
			break;
		}

		/*
		 * dom4j抛出org.xml.sax.SAXParseException: Premature end of file的原因及解决方法：
		 * Problem: Input stream is opened and read bytes from it, passed the
		 * same to document builder to parse method. so it caused the exception
		 * saying premature end of file.
		 * 
		 * Solution: Pass fresh input stream which is opened and not read
		 * anything (bytes) before passing to parse method of DocumentBuilder
		 * object.
		 */
		InputStream bais = new ByteArrayInputStream(b);// 由于dom4j读取InputStream
		if (!XMLUtil.validate(bais, strXMLParams.trim())) {
			logger.error("[" + message + "] - xml校验错误[参数不符合规范]。请求XML如下：\r\n" + strXMLParams);
			return ValidateResult.getFailedValidateResult(ErrorCode.ERROR_0100002, null, null);
		}
		return null;
	}

	private static byte[] getFileByte(InputStream is) {
		byte[] preFileByte = new byte[20480]; // 预分配存放文件的字节数组
		try {
			int fileByteLen = 0; // 文件字节的实际长度
			int len = -1;
			byte[] b = new byte[1024];
			while ((len = is.read(b)) != -1) {
				fileByteLen += len;

				/* 以2倍长度，自增预分配存放文件的字节数组 */
				if (preFileByte.length < fileByteLen) {
					byte[] temp = new byte[preFileByte.length * 2];
					System.arraycopy(preFileByte, 0, temp, 0, preFileByte.length);
					preFileByte = temp;
				}
				System.arraycopy(b, 0, preFileByte, fileByteLen - len, len);
			}
			byte[] fileByte = new byte[fileByteLen]; // 文件的实际字节
			System.arraycopy(preFileByte, 0, fileByte, 0, fileByteLen);
			preFileByte = fileByte;
		} catch (Exception ex) {
			return new byte[0];
		}
		return preFileByte;
	}
}
