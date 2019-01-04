/*
 * Project platform
 *
 * Class ProtocolServiceImpl.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午11:51:39
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;

import com.yixin.A1000.archive.dao.UpgradeFileDao;
import com.yixin.A1000.archive.model.HistoryVersion;
import com.yixin.A1000.archive.model.UpgradeFile;
import com.yixin.A1000.archive.service.HistoryVersionService;
import com.yixin.A1000.cag.model.CmdType;
import com.yixin.A1000.cag.model.ErrorCode;
import com.yixin.A1000.cag.model.ErrorExtendParam;
import com.yixin.A1000.cag.model.ValidateResult;
import com.yixin.A1000.cag.model.request.DownloadCMAHistoryVersionRequestModel;
import com.yixin.A1000.cag.model.request.DownloadCMALatestVersionRequestModel;
import com.yixin.A1000.cag.model.request.DownloadCMAUpdateFileRequestModel;
import com.yixin.A1000.cag.model.request.UploadCMAConfigRequestModel;
import com.yixin.A1000.cag.model.request.UploadCMADataRequestModel;
import com.yixin.A1000.cag.model.request.UploadCMAHeartbeatInfoRequestModel;
import com.yixin.A1000.cag.service.ProtocolParseService;
import com.yixin.A1000.cag.service.ProtocolService;
import com.yixin.A1000.cag.service.ProtocolValidationService;
import com.yixin.A1000.insulatormonsoon.model.InsulatorMonsoonSampling;
import com.yixin.A1000.insulatormonsoon.service.InsulatorMonsoonSamplingService;
import com.yixin.A1000.setting.dao.TaskConfigDao;
import com.yixin.A1000.setting.model.CmaHeartbeatInfo;
import com.yixin.A1000.setting.model.DeviceParameter;
import com.yixin.A1000.setting.model.DeviceParameterDetail;
import com.yixin.A1000.setting.model.SensorHeartbeatInfo;
import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.A1000.setting.service.CmaHeartbeatInfoService;
import com.yixin.A1000.setting.service.DeviceParameterService;
import com.yixin.A1000.setting.service.SensorHeartbeatInfoService;
import com.yixin.A1000.setting.service.TaskConfigService;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;
import com.yixin.A1000.towertilt.service.TowerTiltSamplingService;
import com.yixin.A1000.warning.conditions.InsulatorMonsoonWarningConditions;
import com.yixin.A1000.warning.conditions.TowerTiltWarningConditions;
import com.yixin.A1000.warning.conditions.WeatherWarningConditions;
import com.yixin.A1000.warning.conditions.WhiteMonsoonWarningConditions;
import com.yixin.A1000.warning.conditions.WireSagWarningConditions;
import com.yixin.A1000.warning.conditions.WireTemperatureWarningConditions;
import com.yixin.A1000.warning.model.Warning;
import com.yixin.A1000.warning.service.WarningService;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.A1000.weather.service.WeatherSamplingService;
import com.yixin.A1000.whitemonsoon.model.WhiteMonsoonSampling;
import com.yixin.A1000.whitemonsoon.service.WhiteMonsoonSamplingService;
import com.yixin.A1000.wiresag.model.WireSagSampling;
import com.yixin.A1000.wiresag.service.WireSagSamplingService;
import com.yixin.A1000.wiretemperature.model.WireTemperatureSampling;
import com.yixin.A1000.wiretemperature.service.WireTemperatureSamplingService;
import com.yixin.framework.util.ContextKeys;
import com.yixin.framework.util.SystemConfig;
import com.yixin.framework.util.base64.Base64Util;

/**
 * 协议处理业务具体实现类。用于处理协议部分，并生成相应的回复。
 * 
 * @version v1.0.0
 * @author 
 */
public class ProtocolServiceImpl implements ProtocolService {

	/** 日志记录器 */
	private static final Logger logger = Logger.getLogger(ProtocolServiceImpl.class);

	/** 协议校验业务逻辑接口 */
	private ProtocolValidationService protocolValidationService;

	/** 将传入的数据解析成所需要的对象模型 */
	private ProtocolParseService protocolParseService;

	/** CMA心跳信息服务接口 **/
	private CmaHeartbeatInfoService cmaHeartbeatInfoService;

	/** 监测装置心跳信息服务接口 **/
	private SensorHeartbeatInfoService sensorHeartbeatInfoService;

	/** 任务管理服务接口 **/
	private TaskConfigService taskConfigService;

	/** CMA参数服务接口 **/
	private DeviceParameterService deviceParameterService;

	public void setDeviceParameterService(
			DeviceParameterService deviceParameterService) {
		this.deviceParameterService = deviceParameterService;
	}

	/** 监测装置服务接口 **/
	//private SensorParamsService sensorParamsService;
	

	/** 升级文件DAO接口 */
	private UpgradeFileDao upgradeFileDao;

	/** 参数任务DAO接口 */
	private TaskConfigDao taskConfigDao;

	/** 历史版本业务逻辑接口 */
	private HistoryVersionService historyVersionService;

	// -----------------------------------------------------------------------------------
	// 数据存储服务

	/** 导线弧垂服务接口 **/
	private WireSagSamplingService wireSagSamplingService;

	/** 导线温度服务接口 **/
	private WireTemperatureSamplingService wireTemperatureSamplingService;

	/** 微气象服务接口 **/
	private WeatherSamplingService weatherSamplingService;

	/** 杆塔倾斜服务接口 **/
	private TowerTiltSamplingService towerTiltSamplingService;

	/** 绝缘子串服务接口 **/
	private InsulatorMonsoonSamplingService insulatorMonsoonSamplingService;

	/** 相间风偏服务接口 **/
	private WhiteMonsoonSamplingService whiteMonsoonSamplingService;

	/** 告警服务接口 */
	private WarningService warningService;

	// -----------------------------------------------------------------------------------
	// setters and getters

	/**
	 * 设置 协议校验业务逻辑接口
	 * 
	 * @param protocolValidationService
	 *            协议校验业务逻辑接口
	 */
	public void setProtocolValidationService(ProtocolValidationService protocolValidationService) {
		this.protocolValidationService = protocolValidationService;
	}

	/**
	 * 设置 将传入的数据解析成所需要的对象模型
	 * 
	 * @param protocolParseService
	 *            将传入的数据解析成所需要的对象模型
	 */
	public void setProtocolParseService(ProtocolParseService protocolParseService) {
		this.protocolParseService = protocolParseService;
	}

	/**
	 * 设置 CMA心跳信息服务接口
	 * 
	 * @param cmaHeartbeatInfoService
	 *            CMA心跳信息服务接口
	 */
	public void setCmaHeartbeatInfoService(CmaHeartbeatInfoService cmaHeartbeatInfoService) {
		this.cmaHeartbeatInfoService = cmaHeartbeatInfoService;
	}

	/**
	 * 设置 监测装置心跳信息服务接口
	 * 
	 * @param sensorHeartbeatInfoService
	 *            监测装置心跳信息服务接口
	 */
	public void setSensorHeartbeatInfoService(SensorHeartbeatInfoService sensorHeartbeatInfoService) {
		this.sensorHeartbeatInfoService = sensorHeartbeatInfoService;
	}

	/**
	 * 设置 任务管理服务接口
	 * 
	 * @param taskConfigService
	 *            任务管理服务接口
	 */
	public void setTaskConfigService(TaskConfigService taskConfigService) {
		this.taskConfigService = taskConfigService;
	}


	/**
	 * 设置 监测装置服务接口
	 * 
	 * @param sensorParamsService
	 *            监测装置服务接口
	 */
	//public void setSensorParamsService(SensorParamsService sensorParamsService) {
	//	this.sensorParamsService = sensorParamsService;
	//}

	/**
	 * 设置 升级文件DAO接口
	 * 
	 * @param upgradeFileDao
	 *            升级文件DAO接口
	 */
	public void setUpgradeFileDao(UpgradeFileDao upgradeFileDao) {
		this.upgradeFileDao = upgradeFileDao;
	}

	/**
	 * 设置 参数任务DAO接口
	 * 
	 * @param taskConfigDao
	 *            参数任务DAO接口
	 */
	public void setTaskConfigDao(TaskConfigDao taskConfigDao) {
		this.taskConfigDao = taskConfigDao;
	}

	/**
	 * 设置 历史版本业务逻辑接口
	 * 
	 * @param historyVersionService
	 *            历史版本业务逻辑接口
	 */
	public void setHistoryVersionService(HistoryVersionService historyVersionService) {
		this.historyVersionService = historyVersionService;
	}

	/**
	 * 设置 导线弧垂服务接口
	 * 
	 * @param wireSagSamplingService
	 *            导线弧垂服务接口
	 */
	public void setWireSagSamplingService(WireSagSamplingService wireSagSamplingService) {
		this.wireSagSamplingService = wireSagSamplingService;
	}

	/**
	 * 设置 导线温度服务接口
	 * 
	 * @param wireTemperatureSamplingService
	 *            导线温度服务接口
	 */
	public void setWireTemperatureSamplingService(WireTemperatureSamplingService wireTemperatureSamplingService) {
		this.wireTemperatureSamplingService = wireTemperatureSamplingService;
	}

	/**
	 * 设置 微气象服务接口
	 * 
	 * @param weatherSamplingService
	 *            微气象服务接口
	 */
	public void setWeatherSamplingService(WeatherSamplingService weatherSamplingService) {
		this.weatherSamplingService = weatherSamplingService;
	}

	/**
	 * 设置 杆塔倾斜服务接口
	 * 
	 * @param towerTiltSamplingService
	 *            杆塔倾斜服务接口
	 */
	public void setTowerTiltSamplingService(TowerTiltSamplingService towerTiltSamplingService) {
		this.towerTiltSamplingService = towerTiltSamplingService;
	}

	/**
	 * 设置 绝缘子串服务接口
	 * 
	 * @param insulatorMonsoonSamplingService
	 *            绝缘子串服务接口
	 */
	public void setInsulatorMonsoonSamplingService(InsulatorMonsoonSamplingService insulatorMonsoonSamplingService) {
		this.insulatorMonsoonSamplingService = insulatorMonsoonSamplingService;
	}

	/**
	 * 设置 相间风偏服务接口
	 * 
	 * @param whiteMonsoonSamplingService
	 *            相间风偏服务接口
	 */
	public void setWhiteMonsoonSamplingService(WhiteMonsoonSamplingService whiteMonsoonSamplingService) {
		this.whiteMonsoonSamplingService = whiteMonsoonSamplingService;
	}

	/**
	 * 设置 告警服务接口
	 * 
	 * @param warningService
	 *            告警服务接口
	 */
	public void setWarningService(WarningService warningService) {
		this.warningService = warningService;
	}

	// -----------------------------------------------------------------------------------
	// methods
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.cag.service.ProtocolService#
	 * getUploadCMAHeartbeatInfoReplyXML(java.lang.String)
	 */
	@Override
	public String getUploadCMAHeartbeatInfoReplyXML(String strXMLParams) {
		ValidateResult validateResult = this.protocolValidationService.validateUploadCMAHeartbeatInfoRequestXML(strXMLParams);
		
		String result = this.getFailedValidateReplyXML(validateResult);
		if (null != result) {
			return result;
		}
		UploadCMAHeartbeatInfoRequestModel u = this.protocolParseService.parseUploadCMAHeartbeatInfo(strXMLParams);		
		String replyXML = getUploadCMAHeartbeatInfoSucceedValidateReplyXML(validateResult, u);		
		this.saveUploadCMAHeartbeatInfo(u);
		
		return replyXML;
	}

	/**
	 * 将心跳信息入库
	 * 
	 * @param uploadCMAHeartbeatInfoRequestModel
	 *            心跳信息
	 */
	private void saveUploadCMAHeartbeatInfo(UploadCMAHeartbeatInfoRequestModel uploadCMAHeartbeatInfoRequestModel) {
		if (null != uploadCMAHeartbeatInfoRequestModel) {
			CmaHeartbeatInfo cmaHeartbeatInfo = uploadCMAHeartbeatInfoRequestModel.getCmaHeartbeatInfo();
			if (null != cmaHeartbeatInfo) {
				try {
					this.cmaHeartbeatInfoService.addCmaHeartbeatInfo(cmaHeartbeatInfo);// CMA心跳数据入库
					logger.info("监测代理(CMA)心跳信息入库成功");
				} catch (Exception ex) {
					logger.error("监测代理(CMA)心跳信息入库失败", ex);
				}
			}
			List<SensorHeartbeatInfo> sensorHeartbeatInfos = uploadCMAHeartbeatInfoRequestModel
					.getSensorHeartbeatInfos();
			if (sensorHeartbeatInfos != null) {
				try {
					this.sensorHeartbeatInfoService.addSensorHeartbeatInfo(sensorHeartbeatInfos); // 监测装置心跳入库
					logger.info("监测装置心跳信息入库成功");
				} catch (Exception ex) {
					logger.error("监测装置心跳信息入库失败", ex);
				}
			}
		}
	}

	/**
	 * 生成回复的XML。用于上传心跳信息回复、上传监测数据回复、上传配置信息回复等。
	 * 
	 * @param validateResult
	 */
	private String getUploadCMAHeartbeatInfoSucceedValidateReplyXML(ValidateResult validateResult, UploadCMAHeartbeatInfoRequestModel uploadCMAHeartbeatInfoRequestModel) {
		Document doc = DocumentHelper.createDocument();
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");// 设置XML文件的编码格式
		doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response");
		Element resultNode = root.addElement("result");
		resultNode.addAttribute("code", String.valueOf(0));
		Element commandsNode = root.addElement("commands");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		/* 校时命令 */
		if (null != uploadCMAHeartbeatInfoRequestModel) {
			CmaHeartbeatInfo cmaHeartbeatInfo = uploadCMAHeartbeatInfoRequestModel.getCmaHeartbeatInfo();
			if (null != cmaHeartbeatInfo) {
				Date cmaCurtime = cmaHeartbeatInfo.getCurtime(); // CMA当前时间
				Date cagCurtiime = new Date(); // 系统当前时间
				if (cagCurtiime.getTime() - cmaCurtime.getTime() > SystemConfig.FIXTIME_NEGATIVE_OFFSET * 1000
						|| cmaCurtime.getTime() - cagCurtiime.getTime() > SystemConfig.FIXTIME_POSITIVE_OFFSET * 1000) { // 需要校时
					for (String cmaCode : validateResult.getCmaCodes()) {
						Element fixTimeNode = commandsNode.addElement("command");
						fixTimeNode.addAttribute("objid", cmaCode);
						fixTimeNode.addAttribute("type", CmdType.SETCONFIG.getCode());
						Element curtime = fixTimeNode.addElement("action");
						curtime.addAttribute("name", "CURTIME");
						curtime.addAttribute("value", sdf.format(new Date()));
					}
				}
			}
		}

		
		List<TaskConfig> notIssuedCMATaskConfig  = new ArrayList<TaskConfig>();
		List<TaskConfig> notIssuedSensorTaskConfig = new ArrayList<TaskConfig>();
	
		// 与此交交互相关的未下发的监测代理任务
		if ( null != validateResult.getCmaCodes() && validateResult.getCmaCodes().size() > 0 ){
			//notIssuedCMATaskConfig = this.taskConfigService.getAllCmaNotIssuedTaskConfigByCmaCodes(validateResult.getCmaCodes()); 		
		}
		
		// 与此交交互相关的未下发的监测装置任务
		if ( null != validateResult.getSensorCodes() &&  validateResult.getSensorCodes().size()  > 0 ){
			//notIssuedSensorTaskConfig = this.taskConfigService.getAllSensorNotIssuedTaskConfigBySensorCodes(validateResult.getSensorCodes());	
		}
		
		
		// 与此交交互相关的未下发的任务
		List<TaskConfig> notIssuedTaskConfig = new ArrayList<TaskConfig>(); 
		
		if ( notIssuedCMATaskConfig.size() > 0 ){
			notIssuedTaskConfig.addAll(notIssuedCMATaskConfig);		
		}

		if ( notIssuedSensorTaskConfig.size() > 0 ){
			notIssuedTaskConfig.addAll(notIssuedSensorTaskConfig);
			
		}
			
		
		/* 用户任务 */
	//	int count = 0;
		
		for (TaskConfig taskConfig : notIssuedTaskConfig) {
			
			UpgradeFile upgradeFile = taskConfig.getUpgradeFile();
			
			// TODO 更新任务状态  测试时暂时屏蔽掉
			taskConfig.setState(1);
			//执行时间
			taskConfig.setExecuteTime(new Date()); 
			
			if (null != upgradeFile) {   // 如果是升级任务
					
				Element versionsNode = root.element("versions");
				if (null == versionsNode) {
					versionsNode = root.addElement("versions");
				}
				Element versionNode = versionsNode.addElement("version");
				Element timeNode = versionNode.addElement("time");
				try {
					timeNode.setText(sdf.format(upgradeFile.getReleaseTime()));
				} catch (Exception ex) {
					logger.error("获取升级文件版本发布时间时出错。", ex);
				}
				Element versionnoNode = versionNode.addElement("versionno");
				versionnoNode.setText(upgradeFile.getVersion());
				Element filenameNode = versionNode.addElement("filename");
				filenameNode.setText(upgradeFile.getFileName());
				
			} else {
				String cmaCode = taskConfig.getCmaCode();
				String sensorCode = taskConfig.getSensorCode();
				String cmdType = taskConfig.getCmdType().toUpperCase();
				
				String objId = null;
				if (null != cmaCode) { //如果是CMA任务
					objId = cmaCode;
				} else if (null != sensorCode) { //如果是监测装置任务
					objId = sensorCode;
				}
			
				if (null != objId) {
					
					if (CmdType.ACTIVATE.getCode().equals(cmdType)) { // 控制交互：CAG要求CMA暂不休眠，等待后续命令

					} else if (CmdType.CAMERA_REGULATE.getCode().equals(cmdType)) { // 控制交互：调节摄像头位置角度

					} else if (CmdType.CAMERA_SETSCHEDULE.getCode().equals(cmdType)) { // 控制交互：设置摄像头自动拍照时间表

					} else if (CmdType.CAMERA_TAKEPHOTO.getCode().equals(cmdType)) { // 控制交互：请求摄像头即时拍照

					} else if (CmdType.DEACTIVATE.getCode().equals(cmdType)) { // 休眠：CAG命令CMA休眠

					} else if (CmdType.GETCONFIG.getCode().equals(cmdType)) { // 读配置交互：CAG获取CMA及其管辖状态监测装置的配置信息
						Element commandNode = commandsNode.addElement("command");
						commandNode.addAttribute("objid", objId);
						commandNode.addAttribute("type", cmdType);
									
					} else if (CmdType.GETNEWDATA.getCode().equals(cmdType)) { // 数据召唤：CAG获取CMA当前最新的监测数据
						Element commandNode = commandsNode.addElement("command");
						commandNode.addAttribute("objid", objId);
						commandNode.addAttribute("type", cmdType);
					} else if (CmdType.RESEND.getCode().equals(cmdType)) { // 数据重传：CAG命令CMA重新传送前一次发送的数据

					} else if (CmdType.SETCONFIG.getCode().equals(cmdType)) { // 写配置交互：CAG
																				
						//根据参数ID获取参数值
						DeviceParameter  ParamsInfos = this.deviceParameterService.getDeviceParameter(taskConfig.getDeviceParameterId());
			
						//如果已经配置参数,则生成回复的XML
					    if ( null != ParamsInfos ){
					    	
					    	//生成回复下发参数的命令
							Element ele0 = commandsNode.addElement("command");
						
							ele0.addAttribute("objid", objId);
							ele0.addAttribute("type", cmdType);
							//回复下发的参数各属性及值
							for(DeviceParameterDetail cpd : ParamsInfos.getDeviceParameterDetails()){
								int state = 0;
								if ( null != cpd.getState() ) {
									state = cpd.getState();
								}
								if ( 1 == state ){   //  如果是下发标志时
									Element node = ele0.addElement("action");
									node.addAttribute("name", cpd.getName()); 
									node.addAttribute("value", cpd.getValue());	
								}
								
							}
							
							// 更新参数表中的状态为已下发,并保存到数据库   
						    ParamsInfos.setState(1);  //测试时暂时屏蔽掉
						    this.deviceParameterService.editDeviceParameter(ParamsInfos);

					     }	
						
					}
					
				}
			}
		}

		/* 更新任务为已下发状态 */
		if (notIssuedTaskConfig.size() > 0) {
			try {
				// TODO 更新任务状态  测试时暂时屏蔽掉
				this.taskConfigService.updateAlWriteTaskConfig(notIssuedTaskConfig);
				logger.info("更新任务为已下发状态成功.更新任务数：" + notIssuedTaskConfig.size() + "条");
			} catch (Exception ex) {
				logger.info("更新任务为已下发状态失败", ex);
			}
		}
		
		return doc.asXML();
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.cag.service.ProtocolService#getUploadCMADataReplyXML
	 * (java.lang.String)
	 */
	@Override
	public String getUploadCMADataReplyXML(String strXMLParams) {
		ValidateResult validateResult = this.protocolValidationService.validateUploadCMADataRequestXML(strXMLParams);
		String result = this.getFailedValidateReplyXML(validateResult);
		if (null != result) {
			return result;
		}
		String replyXML = getUploadCMAHeartbeatInfoSucceedValidateReplyXML(validateResult, null);
		this.saveUploadCMAData(this.protocolParseService.parseUploadCMAData(strXMLParams));
		return replyXML;
	}

	/**
	 * 将监测数据与告警信息入库
	 * 
	 * @param uploadCMADataRequestModel
	 *            上传的监测数据
	 */
	private void saveUploadCMAData(UploadCMADataRequestModel uploadCMADataRequestModel) {
		List<Warning> warnings = new ArrayList<Warning>();

		// 导线弧垂
		List<WireSagSampling> wireSagSamplings = uploadCMADataRequestModel.getWireSagSamplings();
		if (wireSagSamplings.size() > 0) {
			try {
				this.wireSagSamplingService.addWireSagSampling(wireSagSamplings);
				for (WireSagSampling wireSagSampling : wireSagSamplings) {
					this.generateWireSagSamplingWarning(wireSagSampling, warnings);
				}
				logger.info("导线弧垂数据报入库成功");
			} catch (Exception ex) {
				logger.error("导线弧垂数据报入库失败", ex);
			}
		}

		// 导线温度
		List<WireTemperatureSampling> wireTemperatureSamplings = uploadCMADataRequestModel
				.getWireTemperatureSamplings();
		if (wireTemperatureSamplings.size() > 0) {
			try {
				this.wireTemperatureSamplingService.addWireTemperatureSampling(wireTemperatureSamplings);
				for (WireTemperatureSampling w : wireTemperatureSamplings) {
					this.generateWireTemperatureSamplingWarning(w, warnings);
				}
				logger.info("导线温度数据报入库成功");
			} catch (Exception ex) {
				logger.error("导线温度数据报入库失败", ex);
			}
		}

		// 杆塔倾斜
		List<TowerTiltSampling> towerTiltSamplings = uploadCMADataRequestModel.getTowerTiltSamplings();
		if (towerTiltSamplings.size() > 0) {
			try {
				this.towerTiltSamplingService.addTowerTiltSampling(towerTiltSamplings);
				for (TowerTiltSampling t : towerTiltSamplings) {
					this.generateTowerTiltSamplingWarning(t, warnings);
				}
				logger.info("杆塔倾斜数据报入库成功");
			} catch (Exception ex) {
				logger.error("杆塔倾斜数据报入库失败", ex);
			}
		}

		// 微气象数据
		List<WeatherSampling> weatherSamplings = uploadCMADataRequestModel.getWeatherSamplings();
		if (weatherSamplings.size() > 0) {
			try {
				this.weatherSamplingService.addWeatherSampling(weatherSamplings);
				for (WeatherSampling w : weatherSamplings) {
					this.generateWeatherSamplingWarning(w, warnings);
				}
				logger.info("微气象数据报入库成功");
			} catch (Exception ex) {
				logger.error("微气象数据报入库失败", ex);
			}
		}

		// 绝缘子串风偏
		List<InsulatorMonsoonSampling> insulatorMonsoonSamplings = uploadCMADataRequestModel
				.getInsulatorMonsoonSamplings();
		if (insulatorMonsoonSamplings.size() > 0) {
			try {
				this.insulatorMonsoonSamplingService.addInsulatorMonsoonSampling(insulatorMonsoonSamplings);
				for (InsulatorMonsoonSampling w : insulatorMonsoonSamplings) {
					this.generateInsulatorMonsoonSamplingWarning(w, warnings);
				}
				logger.info("绝缘子串风偏数据报入库成功");
			} catch (Exception ex) {
				logger.error("绝缘子串风偏数据报入库失败", ex);
			}
		}

		// 相间风偏
		List<WhiteMonsoonSampling> whiteMonsoonSamplings = uploadCMADataRequestModel.getWhiteMonsoonSamplings();
		if (whiteMonsoonSamplings.size() > 0) {
			try {
				this.whiteMonsoonSamplingService.addWhiteMonsoonSampling(whiteMonsoonSamplings);
				for (WhiteMonsoonSampling w : whiteMonsoonSamplings) {
					this.generateWhiteMonsoonSamplingWarning(w, warnings);
				}
				logger.info("相间风偏数据报入库成功");
			} catch (Exception ex) {
				logger.error("相间风偏数据报入库失败", ex);
			}
		}
		if (warnings.size() > 0) {
			try {
				this.warningService.addWarnings(warnings);
				logger.info("告警信息入库成功");
			} catch (Exception ex) {
				logger.error("告警信息入库失败", ex);
			}
		}
	}

	/**
	 * 检查数据是否超过告警值。如果超过则加入warnings列表，并生成告警信息。
	 * 
	 * @param wireSagSampling
	 *            要进行检查的数据
	 * @param warnings
	 *            告警信息列表
	 */
	private void generateWireSagSamplingWarning(WireSagSampling wireSagSampling, List<Warning> warnings) {
		boolean isGroundDistanceWarnData = WireSagWarningConditions.CONDT_GROUNDDISTANCE.fit(wireSagSampling
				.getGroundDistance());
		boolean isSagWarnData = WireSagWarningConditions.CONDT_SAG.fit(wireSagSampling.getSag());
		if (isGroundDistanceWarnData || isSagWarnData) {
			Warning warning = new Warning();
			warning.setCreateDate(new Date());
			warning.setSamplingDate(wireSagSampling.getSamplingTime());
			warning.setSensor(wireSagSampling.getSensor());
			warning.setWarningDict(WireSagWarningConditions.getWarningDict());
			StringBuilder warningInfo = new StringBuilder("导线弧垂数据 - ");
			if (isGroundDistanceWarnData) {
				warningInfo.append("[对地距离]");
			} else if (isSagWarnData) {
				warningInfo.append("[弧垂]");
			}
			warningInfo.append("超过阈值。详细：");
			warningInfo.append("对地距离=" + wireSagSampling.getGroundDistance() + "["
					+ WireSagWarningConditions.CONDT_GROUNDDISTANCE.getThreshold() + "]；");
			warningInfo.append("弧垂=" + wireSagSampling.getSag() + "["
					+ WireSagWarningConditions.CONDT_SAG.getThreshold() + "]；");
			warning.setWarningInfo(warningInfo.toString());
			warnings.add(warning);
			logger.warn("告警信息" + warningInfo.toString());
		}
	}

	/**
	 * 检查数据是否超过告警值。如果超过则加入warnings列表，并生成告警信息。
	 * 
	 * @param wireTemperatureSampling
	 *            要进行检查的数据
	 * @param warnings
	 *            告警信息列表
	 */
	private void generateWireTemperatureSamplingWarning(WireTemperatureSampling wireTemperatureSampling,
			List<Warning> warnings) {
		boolean isLine1WarnData = WireTemperatureWarningConditions.CONDT_LINETEMPERATURE1.fit(wireTemperatureSampling
				.getLineTemperature1());
		boolean isLine2WarnData = WireTemperatureWarningConditions.CONDT_LINETEMPERATURE2.fit(wireTemperatureSampling
				.getLineTemperature2());
		if (isLine1WarnData || isLine2WarnData) {
			Warning warning = new Warning();
			warning.setCreateDate(new Date());
			warning.setSamplingDate(wireTemperatureSampling.getSamplingTime());
			warning.setSensor(wireTemperatureSampling.getSensor());
			warning.setWarningDict(WireTemperatureWarningConditions.getWarningDict());
			StringBuilder warningInfo = new StringBuilder("导线温度数据 - ");
			if (isLine1WarnData) {
				warningInfo.append("[线温1]");
			} else if (isLine2WarnData) {
				warningInfo.append("[线温2]");
			}
			warningInfo.append("超过阈值。详细：");
			warningInfo.append("线温1=" + wireTemperatureSampling.getLineTemperature1() + "["
					+ WireTemperatureWarningConditions.CONDT_LINETEMPERATURE1.getThreshold() + "]；");
			warningInfo.append("线温2=" + wireTemperatureSampling.getLineTemperature2() + "["
					+ WireTemperatureWarningConditions.CONDT_LINETEMPERATURE2.getThreshold() + "]；");
			warning.setWarningInfo(warningInfo.toString());
			warnings.add(warning);
			logger.warn("告警信息：" + warningInfo.toString());
		}
	}

	/**
	 * 检查数据是否超过告警值。如果超过则加入warnings列表，并生成告警信息。
	 * 
	 * @param towerTiltSampling
	 *            要进行检查的数据
	 * @param warnings
	 *            告警信息列表
	 */
	private void generateTowerTiltSamplingWarning(TowerTiltSampling towerTiltSampling, List<Warning> warnings) {
		boolean isGeneralInclinationWarnData = TowerTiltWarningConditions.CONDT_GENERALINCLINATION
				.fit(towerTiltSampling.getGeneralInclination());
		boolean isGradientAlongLinesWarnData = TowerTiltWarningConditions.CONDT_GRADIENTALONGLINES
				.fit(towerTiltSampling.getGradientAlongLines());
		boolean isLateralTiltWarnData = TowerTiltWarningConditions.CONDT_LATERALTILT.fit(towerTiltSampling
				.getLateralTilt());
		if (isGeneralInclinationWarnData || isGradientAlongLinesWarnData || isLateralTiltWarnData) {
			Warning warning = new Warning();
			warning.setCreateDate(new Date());
			warning.setSamplingDate(towerTiltSampling.getSamplingTime());
			warning.setSensor(towerTiltSampling.getSensor());
			warning.setWarningDict(TowerTiltWarningConditions.getWarningDict());
			StringBuilder warningInfo = new StringBuilder("杆塔倾斜数据 - ");
			if (isGeneralInclinationWarnData) {
				warningInfo.append("[综合倾斜度]");
			} else if (isGradientAlongLinesWarnData) {
				warningInfo.append("[顺线倾斜度]");
			} else if (isLateralTiltWarnData) {
				warningInfo.append("[横向倾斜度]");
			}
			warningInfo.append("超过阈值。详细：");
			warningInfo.append("综合倾斜度=" + towerTiltSampling.getGeneralInclination() + "["
					+ TowerTiltWarningConditions.CONDT_GENERALINCLINATION.getThreshold() + "]；");
			warningInfo.append("顺线倾斜度=" + towerTiltSampling.getGradientAlongLines() + "["
					+ TowerTiltWarningConditions.CONDT_GRADIENTALONGLINES.getThreshold() + "]；");
			warningInfo.append("横向倾斜度=" + towerTiltSampling.getLateralTilt() + "["
					+ TowerTiltWarningConditions.CONDT_LATERALTILT.getThreshold() + "]；");
			warning.setWarningInfo(warningInfo.toString());
			warnings.add(warning);
			logger.warn("告警信息：" + warningInfo.toString());
		}
	}

	/**
	 * 检查数据是否超过告警值。如果超过则加入warnings列表，并生成告警信息。
	 * 
	 * @param weatherSampling
	 *            要进行检查的数据
	 * @param warnings
	 *            告警信息列表
	 */
	private void generateWeatherSamplingWarning(WeatherSampling weatherSampling, List<Warning> warnings) {
		boolean isAirPressureWarnData = WeatherWarningConditions.CONDT_AIRPRESSURE
				.fit(weatherSampling.getAirPressure());
		boolean isDailyRainfallWarnData = WeatherWarningConditions.CONDT_DAILYRAINFALL.fit(weatherSampling
				.getDailyRainfall());
		boolean isHumidityWarnData = WeatherWarningConditions.CONDT_HUMIDITY_MAX.fit(weatherSampling.getHumidity())
				|| WeatherWarningConditions.CONDT_HUMIDITY_MIN.fit(weatherSampling.getHumidity());
		boolean isTemperatureWarnData = WeatherWarningConditions.CONDT_TEMPERATURE_MAX.fit(weatherSampling
				.getTemperature())
				|| WeatherWarningConditions.CONDT_TEMPERATURE_MIN.fit(weatherSampling.getTemperature());
		boolean isWindSpeedWarnData = WeatherWarningConditions.CONDT_WINDSPEED.fit(weatherSampling.getWindSpeed());
		if (isAirPressureWarnData || isDailyRainfallWarnData || isHumidityWarnData || isTemperatureWarnData
				|| isWindSpeedWarnData) {
			Warning warning = new Warning();
			warning.setCreateDate(new Date());
			warning.setSamplingDate(weatherSampling.getSamplingTime());
			warning.setSensor(weatherSampling.getSensor());
			warning.setWarningDict(WeatherWarningConditions.getWarningDict());
			StringBuilder warningInfo = new StringBuilder("微气象数据 - ");
			if (isAirPressureWarnData) {
				warningInfo.append("[气压]");
			} else if (isDailyRainfallWarnData) {
				warningInfo.append("[雨量]");
			} else if (isHumidityWarnData) {
				warningInfo.append("[湿度]");
			} else if (isTemperatureWarnData) {
				warningInfo.append("[气温]");
			} else if (isWindSpeedWarnData) {
				warningInfo.append("[风速]");
			}
			warningInfo.append("超过阈值。详细：");
			warningInfo.append("气压=" + weatherSampling.getAirPressure() + "["
					+ WeatherWarningConditions.CONDT_AIRPRESSURE.getThreshold() + "]；");
			warningInfo.append("雨量=" + weatherSampling.getDailyRainfall() + "["
					+ WeatherWarningConditions.CONDT_DAILYRAINFALL.getThreshold() + "]；");
			warningInfo.append("湿度=" + weatherSampling.getHumidity() + "["
					+ WeatherWarningConditions.CONDT_HUMIDITY_MIN.getThreshold() + ", "
					+ WeatherWarningConditions.CONDT_HUMIDITY_MAX.getThreshold() + "]；");
			warningInfo.append("气温=" + weatherSampling.getTemperature() + "["
					+ WeatherWarningConditions.CONDT_TEMPERATURE_MIN.getThreshold() + ","
					+ WeatherWarningConditions.CONDT_TEMPERATURE_MAX.getThreshold() + "]；");
			warningInfo.append("风速=" + weatherSampling.getWindSpeed() + "["
					+ WeatherWarningConditions.CONDT_WINDSPEED.getThreshold() + "]；");
			warning.setWarningInfo(warningInfo.toString());
			warnings.add(warning);
			logger.warn("告警信息：" + warningInfo.toString());
		}
	}

	/**
	 * 检查数据是否超过告警值。如果超过则加入warnings列表，并生成告警信息。
	 * 
	 * @param insulatorMonsoonSampling
	 *            要进行检查的数据
	 * @param warnings
	 *            告警信息列表
	 */
	private void generateInsulatorMonsoonSamplingWarning(InsulatorMonsoonSampling insulatorMonsoonSampling,
			List<Warning> warnings) {
		boolean isAngleWarnData = InsulatorMonsoonWarningConditions.CONDT_ANGLE
				.fit(insulatorMonsoonSampling.getAngle());
		boolean isWindAngleWarnData = InsulatorMonsoonWarningConditions.CONDT_WINDANGLE.fit(insulatorMonsoonSampling
				.getWindAngle());
		boolean isMinClearanceParamsWarnData = InsulatorMonsoonWarningConditions.CONDT_MINCLEARANCEPARAMS
				.fit(insulatorMonsoonSampling.getMinClearanceParams());
		if (isAngleWarnData || isWindAngleWarnData || isMinClearanceParamsWarnData) {
			Warning warning = new Warning();
			warning.setCreateDate(new Date());
			warning.setSamplingDate(insulatorMonsoonSampling.getSamplingTime());
			warning.setSensor(insulatorMonsoonSampling.getSensor());
			warning.setWarningDict(InsulatorMonsoonWarningConditions.getWarningDict());
			StringBuilder warningInfo = new StringBuilder("绝缘子串风偏数据 - ");
			if (isAngleWarnData) {
				warningInfo.append("[倾斜角]");
			} else if (isWindAngleWarnData) {
				warningInfo.append("[风偏角]");
			} else if (isMinClearanceParamsWarnData) {
				warningInfo.append("[最小电气间隙参数]");
			}
			warningInfo.append("超过阈值。详细：");
			warningInfo.append("倾斜角=" + insulatorMonsoonSampling.getAngle() + "["
					+ InsulatorMonsoonWarningConditions.CONDT_ANGLE.getThreshold() + "]；");
			warningInfo.append("风偏角=" + insulatorMonsoonSampling.getWindAngle() + "["
					+ InsulatorMonsoonWarningConditions.CONDT_WINDANGLE.getThreshold() + "]；");
			warningInfo.append("最小电气间隙参数=" + insulatorMonsoonSampling.getMinClearanceParams() + "["
					+ InsulatorMonsoonWarningConditions.CONDT_MINCLEARANCEPARAMS.getThreshold() + "]；");
			warning.setWarningInfo(warningInfo.toString());
			warnings.add(warning);
			logger.warn("告警信息：" + warningInfo.toString());
		}
	}

	/**
	 * 检查数据是否超过告警值。如果超过则加入warnings列表，并生成告警信息。
	 * 
	 * @param whiteMonsoonSampling
	 *            要进行检查的数据
	 * @param warnings
	 *            告警信息列表
	 */
	private void generateWhiteMonsoonSamplingWarning(WhiteMonsoonSampling whiteMonsoonSampling, List<Warning> warnings) {
		boolean isAngleWarnData = WhiteMonsoonWarningConditions.CONDT_ANGLE.fit(whiteMonsoonSampling.getAngle());
		boolean isWindAngleWarnData = WhiteMonsoonWarningConditions.CONDT_WINDANGLE.fit(whiteMonsoonSampling
				.getWindAngle());
		boolean isMinClearanceParamsWarnData = WhiteMonsoonWarningConditions.CONDT_MINCLEARANCEPARAMS
				.fit(whiteMonsoonSampling.getMinClearanceParams());
		if (isAngleWarnData || isWindAngleWarnData || isMinClearanceParamsWarnData) {
			Warning warning = new Warning();
			warning.setCreateDate(new Date());
			warning.setSamplingDate(whiteMonsoonSampling.getSamplingTime());
			warning.setSensor(whiteMonsoonSampling.getSensor());
			warning.setWarningDict(WhiteMonsoonWarningConditions.getWarningDict());
			StringBuilder warningInfo = new StringBuilder("相间风偏数据 - ");
			if (isAngleWarnData) {
				warningInfo.append("[倾斜角]");
			} else if (isWindAngleWarnData) {
				warningInfo.append("[风偏角]");
			} else if (isMinClearanceParamsWarnData) {
				warningInfo.append("[最小电气间隙参数]");
			}
			warningInfo.append("超过阈值。详细：");
			warningInfo.append("倾斜角=" + whiteMonsoonSampling.getAngle() + "["
					+ WhiteMonsoonWarningConditions.CONDT_ANGLE.getThreshold() + "]；");
			warningInfo.append("风偏角=" + whiteMonsoonSampling.getWindAngle() + "["
					+ WhiteMonsoonWarningConditions.CONDT_WINDANGLE.getThreshold() + "]；");
			warningInfo.append("最小电气间隙参数=" + whiteMonsoonSampling.getMinClearanceParams() + "["
					+ WhiteMonsoonWarningConditions.CONDT_MINCLEARANCEPARAMS.getThreshold() + "]；");
			warning.setWarningInfo(warningInfo.toString());
			warnings.add(warning);
			logger.warn("告警信息：" + warningInfo.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.cag.service.ProtocolService#getUploadCMAImageReplyXML
	 * (byte[], java.lang.String)
	 */
	@Override
	public String getUploadCMAImageReplyXML(byte[] imageByte, String strXMLParams) {
		// TODO 上传静态图片数据
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");// 设置XML文件的编码格式
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response");
		root.setText("No Supported");
		return doc.asXML();
	}

	
	/*
	 * (non-Javadoc)
	 * 上报配置参数
	 * @see
	 * com.yixin.ca2000.cag.service.ProtocolService#getUploadCMAConfigReplyXML
	 * (java.lang.String)
	 */
	@Override
	public String getUploadCMAConfigReplyXML(String strXMLParams) {
		ValidateResult validateResult = this.protocolValidationService.validateUploadCMAConfigRequestXML(strXMLParams);
		String result = this.getFailedValidateReplyXML(validateResult);
		if (null != result) {
			return result;
		}
		//获取回复的XML
		String replyXML = getUploadCMAHeartbeatInfoSucceedValidateReplyXML(validateResult, null);
		
		this.saveUploadCMAConfigRequestModel(this.protocolParseService.parseUploadCMAConfig(strXMLParams));
		
		return replyXML;
	}

	/**
	 * 将上传的参数入库，并更新相应的任务。
	 * 
	 * @param uploadCMAConfigRequestModel
	 *            上传的参数
	 */
	private void saveUploadCMAConfigRequestModel(UploadCMAConfigRequestModel uploadCMAConfigRequestModel) {

		
		// 设备参数信息入库
		List<DeviceParameter> deviceParams = uploadCMAConfigRequestModel.getDeviceParams();
		if (deviceParams.size() > 0) {
			try {
				this.deviceParameterService.addDeviceParams(deviceParams);
				logger.info("设备参数信息入库成功");
			} catch (Exception ex) {
				logger.error("设备参数信息入库失败", ex);
			}
		}
		
        // 上报数据不对任务表操作，任务表中的任务状态(STATE)应只包含  0：未下发  1：已下发
		// 过滤任务表已下发且为”召测“任务，
		/*List<TaskConfig> configInfos = this.taskConfigService.getTaskConfig(1, "GETCONFIG");
		List<TaskConfig> unfinishedTask = new ArrayList<TaskConfig>();
		Set<String> cmaCodes = new HashSet<String>();
		Set<String> sensorsCodes = new HashSet<String>();
		for (DeviceParameter cmaParam : cmaParamsList) {
			cmaCodes.add(cmaParam.getCmaInfo().getCmaCode());
		}
		
		for (SensorParams sensorParam : sensorParamsList) {
			sensorsCodes.add(sensorParam.getSensor().getSensorCode());
		}
		for (TaskConfig task : configInfos) {
			if (null != task.getCmaCode() && cmaCodes.contains(task.getCmaCode())) {
				unfinishedTask.add(task);
			} else if (null != task.getSensorCode() && sensorsCodes.contains(task.getSensorCode())) {
				unfinishedTask.add(task);
			}
		}

		// 更新其执行时间 为当前时间、任务状态为已经下发成功
		if (unfinishedTask.size() > 0) {
			try {
				// TODO 任务状态设置
				// this.taskConfigService.updateAllIssuedAndIsReadTask(unfinishedTask);
				logger.info("更新召测任务信息状态成功");
			} catch (Exception ex) {
				logger.error("更新召测任务信息状态失败", ex);
			}
		}
		*/
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.cag.service.ProtocolService#getDownloadCAGCtrlReplyXML
	 * (java.lang.String)
	 */
	@Override
	public String getDownloadCAGCtrlReplyXML(String strXMLParams) {
		// TODO 下发控制指令
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");// 设置XML文件的编码格式
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response");
		root.setText("No Supported");
		return doc.asXML();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.cag.service.ProtocolService#
	 * getDownloadCMALatestVersionReplyXML(java.lang.String)
	 */
	@Override
	public String getDownloadCMALatestVersionReplyXML(String strXMLParams) {
		ValidateResult validateResult = this.protocolValidationService
				.validateDownloadCMALatestVersionRequestXML(strXMLParams);
		String result = this.getFailedValidateReplyXML(validateResult);
		if (null != result) {
			return result;
		}
		Document doc = DocumentHelper.createDocument();
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");// 设置XML文件的编码格式
		doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response");
		Element resultNode = root.addElement("result");
		resultNode.addAttribute("code", String.valueOf(0));
		DownloadCMALatestVersionRequestModel downloadCMALatestVersionRequestModel = this.protocolParseService
				.parseDownloadCMALatestVersion(strXMLParams);
		String cmaCode = downloadCMALatestVersionRequestModel.getCmaCode();
		String sensorCode = downloadCMALatestVersionRequestModel.getSensorCode();

		/* versions节点 */
		Element versionsNode = root.addElement("versions");
		String time = "";
		String versionno = "";
		String filename = "";
		HistoryVersion historyVersion = null;
		if (null != cmaCode) {
			historyVersion = this.historyVersionService.getLastestHistoryVersionByCmaCode(cmaCode);
		} else if (null != sensorCode) {
			historyVersion = this.historyVersionService.getLastestHistoryVersionBySensorCode(sensorCode);
		}
		if (null != historyVersion) {
			time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(historyVersion.getReleaseTime());
			versionno = historyVersion.getVersion();
			filename = historyVersion.getFileName();
		}
		Element versionNode = versionsNode.addElement("version");
		versionNode.addElement("time").setText(time);
		versionNode.addElement("versionno").setText(versionno);
		versionNode.addElement("filename").setText(filename);
		return doc.asXML();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.cag.service.ProtocolService#
	 * getDownloadCMAHistoryVersionReplyXML(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getDownloadCMAHistoryVersionReplyXML(String strXMLParams) {
		ValidateResult validateResult = this.protocolValidationService
				.validateDownloadCMAHistoryVersionRequestXML(strXMLParams);
		String result = this.getFailedValidateReplyXML(validateResult);
		if (null != result) {
			return result;
		}
		Document doc = DocumentHelper.createDocument();
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");// 设置XML文件的编码格式
		doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response");
		Element resultNode = root.addElement("result");
		resultNode.addAttribute("code", String.valueOf(0));
		DownloadCMAHistoryVersionRequestModel downloadCMAHistoryVersionRequestModel = this.protocolParseService
				.parseDownloadCMAHistoryVersion(strXMLParams);
		String cmaCode = downloadCMAHistoryVersionRequestModel.getCmaCode();
		String sensorCode = downloadCMAHistoryVersionRequestModel.getSensorCode();

		/* versions节点 */
		Element versionsNode = root.addElement("versions");
		List<HistoryVersion> historyVersions = Collections.EMPTY_LIST;
		if (null != cmaCode) {
			historyVersions = this.historyVersionService.getAllHistoryVersionsByCmaCode(cmaCode);
		} else if (null != sensorCode) {
			historyVersions = this.historyVersionService.getAllHistoryVersionsBySensorCode(sensorCode);
		}
		for (HistoryVersion historyVersion : historyVersions) {
			String time = "";
			String versionno = "";
			String filename = "";
			if (null != historyVersions) {
				time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(historyVersion.getReleaseTime());
				versionno = historyVersion.getVersion();
				filename = historyVersion.getFileName();
			}
			Element versionNode = versionsNode.addElement("version");
			versionNode.addElement("time").setText(time);
			versionNode.addElement("versionno").setText(versionno);
			versionNode.addElement("filename").setText(filename);
		}
		return doc.asXML();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yixin.ca2000.cag.service.ProtocolService#getDownloadCMAUpdateFileReplyXML
	 * (java.lang.String)
	 */
	@Override
	public String getDownloadCMAUpdateFileReplyXML(String strXMLParams) {
		ValidateResult validateResult = this.protocolValidationService
				.validateDownloadCMAUpdateFileRequestXML(strXMLParams);
		String result = this.getFailedValidateReplyXML(validateResult);
		if (null != result) {
			return result;
		}
		Document doc = DocumentHelper.createDocument();
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");// 设置XML文件的编码格式
		doc = DocumentHelper.createDocument();
		Element root = doc.addElement("response");
		Element resultNode = root.addElement("result");
		resultNode.addAttribute("code", String.valueOf(0));
		DownloadCMAUpdateFileRequestModel downloadCMAUpdateFileRequestModel = this.protocolParseService
				.parseDownloadCMAUpdateFile(strXMLParams);

		/* versions节点 */
		Element versionsNode = root.addElement("versions");
		String cmaCode = downloadCMAUpdateFileRequestModel.getCmaCode();
		String time = "";
		String versionNo = downloadCMAUpdateFileRequestModel.getVersionNo();
		String fileName = downloadCMAUpdateFileRequestModel.getFileName();
		String fileeData = "";
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fileName", fileName);
			map.put("version", versionNo);
			List<UpgradeFile> upgradeFiles = this.upgradeFileDao.getAllByProperties(map);
			if (upgradeFiles.size() >= 1) {
				UpgradeFile upgradeFile = upgradeFiles.get(0);
				File file = new File(ContextKeys.WEB_PHYSICAL_ROOT_PATH + upgradeFile.getPath()
						+ upgradeFile.getFileName());
				fileeData = Base64Util.encodeBase64FromFile(file);
				time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(upgradeFile.getReleaseTime());

				/* 更新任务状态为已成功 */
				HistoryVersion historyVersion = new HistoryVersion();
				Map<String, Object> taskMap = new HashMap<String, Object>();
				taskMap.put("upgradeFile", upgradeFile);
				if (null != cmaCode && 0 != cmaCode.trim().length()) {
					taskMap.put("cmaCode", cmaCode);
					historyVersion.setCmaCode(cmaCode);
				}
				List<TaskConfig> taskConfigs = this.taskConfigDao.getAllByProperties(taskMap);
				for (TaskConfig taskConfig : taskConfigs) {
					taskConfig.setState(3);
				}
				this.taskConfigDao.updateAll(taskConfigs);
				historyVersion.setFileName(fileName);
				historyVersion.setReleaseTime(upgradeFile.getReleaseTime());
				historyVersion.setVersion(versionNo);
				historyVersion.setUpdateTime(new Date());
				this.historyVersionService.addHistoryVersion(historyVersion);
			}
		} catch (Exception ex) {
			logger.error("获取升级文件失败", ex);
			return this.getFailedValidateReplyXML(ValidateResult.getFailedValidateResult(ErrorCode.ERROR_99xxxxx,
					ErrorExtendParam.CMA_ID, cmaCode));
		}
		Element versionNode = versionsNode.addElement("version");
		versionNode.addElement("time").setText(time);
		versionNode.addElement("versionno").setText(versionNo);
		versionNode.addElement("filename").setText(fileName);
		versionNode.addElement("filedata").setText(fileeData);
		return doc.asXML();
	}

	/**
	 * 返回校验失败回复的XML。当校验成功时返回null。
	 * 
	 * @param validateResult
	 *            保存校验结果的
	 * @return 校验失败回复的XML。当校验成功时返回null。
	 */
	private String getFailedValidateReplyXML(ValidateResult validateResult) {
		if (0 == validateResult.getCode()) {
			return null;
		}
		Document doc = DocumentHelper.createDocument();
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");// 设置XML文件的编码格式
		Element root = doc.addElement("response");
		Element resultNode = root.addElement("result");
		resultNode.addAttribute("code", String.valueOf(validateResult.getCode()));
		Element errorNode = resultNode.addElement("error");
		errorNode.addAttribute("errorcode", validateResult.getErrorCode().getCode());
		Element attrNode = errorNode.addElement("attr");
		if (null != validateResult.getErrorExtendParam()) {
			attrNode.addAttribute("name", validateResult.getErrorExtendParam().getCode());
		} else {
			attrNode.addAttribute("name", "");
		}
		if (null != validateResult.getErrorExtendParam()) {
			attrNode.addAttribute("value", validateResult.getValue());
		} else {
			attrNode.addAttribute("value", "");
		}
		return doc.asXML();
	}
}
