/*
 * Project platform
 *
 * Class UpdateAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-14 下午02:30:08
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.setting.web;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.CmaInfoQueryModel;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.model.SensorQueryModel;
import com.yixin.A1000.archive.model.UpgradeFile;
import com.yixin.A1000.archive.service.CmaInfoService;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.archive.service.UpgradeFileService;
import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.A1000.setting.service.TaskConfigService;
import com.yixin.A1000.setting.service.UpdateService;
import com.yixin.framework.base.model.db.NumberQueryMode;
import com.yixin.framework.login.model.LoginInfo;
import com.yixin.framework.login.web.LoginAction;
import com.yixin.framework.system.model.User;

/**
 * 远程升级Action类
 * 
 * @version v1.0.0
 * @author 
 */
public class UpdateAction extends ActionSupport implements ServletRequestAware, Preparable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -2470736765368029315L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 远程升级业务接口 */
	private UpdateService updateService;

	/** 监测代理业务逻辑接口 */
	private CmaInfoService cmaInfoService;

	/** 监测装置业务逻辑接口 */
	private SensorService sensorService;

	/** 升级文件业务逻辑接口 */
	private UpgradeFileService upgradeFileService;

	/** 任务业务接口 */
	private TaskConfigService taskConfigService;

	/** 选择的操作类型：0 - 监测代理；1 - 监测装置 */
	private Integer deviceType = 0;

	/** 监测代理ID：对监测代理操作时使用 */
	private String cmaInfoId;

	/** 监测装置ID：对监测装置操作时使用 */
	private String sensorId;

	/** 升级文件 */
	private UpgradeFile upgradeFile;

	/** 是否取消未完成的升级任务 */
	private Collection<String> isCancelUnfinishedUpdateTask;

	/**
	 * 获取 选择的操作类型：0 - 监测代理；1 - 监测装置
	 * 
	 * @return 选择的操作类型：0 - 监测代理；1 - 监测装置
	 */
	public Integer getDeviceType() {
		return this.deviceType;
	}

	/**
	 * 设置 选择的操作类型：0 - 监测代理；1 - 监测装置
	 * 
	 * @param deviceType
	 *            选择的操作类型：0 - 监测代理；1 - 监测装置
	 */
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * 获取 监测代理ID：对监测代理操作时使用
	 * 
	 * @return 监测代理ID：对监测代理操作时使用
	 */
	public String getCmaInfoId() {
		return this.cmaInfoId;
	}

	/**
	 * 设置 监测代理ID：对监测代理操作时使用
	 * 
	 * @param cmaInfoId
	 *            监测代理ID：对监测代理操作时使用
	 */
	public void setCmaInfoId(String cmaInfoId) {
		this.cmaInfoId = cmaInfoId;
	}

	/**
	 * 获取 监测装置ID：对监测装置操作时使用
	 * 
	 * @return 监测装置ID：对监测装置操作时使用
	 */
	public String getSensorId() {
		return this.sensorId;
	}

	/**
	 * 设置 监测装置ID：对监测装置操作时使用
	 * 
	 * @param sensorId
	 *            监测装置ID：对监测装置操作时使用
	 */
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	/**
	 * 获取 升级文件
	 * 
	 * @return 升级文件
	 */
	public UpgradeFile getUpgradeFile() {
		return this.upgradeFile;
	}

	/**
	 * 设置 升级文件
	 * 
	 * @param upgradeFile
	 *            升级文件
	 */
	public void setUpgradeFile(UpgradeFile upgradeFile) {
		this.upgradeFile = upgradeFile;
	}

	/**
	 * 获取 是否取消未完成的升级任务
	 * 
	 * @return 是否取消未完成的升级任务
	 */
	public Collection<String> getIsCancelUnfinishedUpdateTask() {
		return this.isCancelUnfinishedUpdateTask;
	}

	/**
	 * 设置 是否取消未完成的升级任务
	 * 
	 * @param isCancelUnfinishedUpdateTask
	 *            是否取消未完成的升级任务
	 */
	public void setIsCancelUnfinishedUpdateTask(Collection<String> isCancelUnfinishedUpdateTask) {
		this.isCancelUnfinishedUpdateTask = isCancelUnfinishedUpdateTask;
	}

	/**
	 * 设置 远程升级业务接口
	 * 
	 * @param updateService
	 *            远程升级业务接口
	 */
	public void setUpdateService(UpdateService updateService) {
		this.updateService = updateService;
	}

	/**
	 * 设置 监测代理业务逻辑接口
	 * 
	 * @param cmaInfoService
	 *            监测代理业务逻辑接口
	 */
	public void setCmaInfoService(CmaInfoService cmaInfoService) {
		this.cmaInfoService = cmaInfoService;
	}

	/**
	 * 设置 监测装置业务逻辑接口
	 * 
	 * @param sensorService
	 *            监测装置业务逻辑接口
	 */
	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	/**
	 * 设置 升级文件业务逻辑接口
	 * 
	 * @param upgradeFileService
	 *            升级文件业务逻辑接口
	 */
	public void setUpgradeFileService(UpgradeFileService upgradeFileService) {
		this.upgradeFileService = upgradeFileService;
	}

	/**
	 * 设置 任务业务接口
	 * 
	 * @param taskConfigService
	 *            任务业务接口
	 */
	public void setTaskConfigService(TaskConfigService taskConfigService) {
		this.taskConfigService = taskConfigService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 跳转至远程升级页面
	 * 
	 * @return 结果页面
	 */
	public String update() {
		return SUCCESS;
	}

	/**
	 * 保存升级任务
	 * 
	 * @return 结果页面
	 */
	public String updateSave() {
		User user = ((LoginInfo) request.getSession().getAttribute(LoginAction.SESSION_LOGIN_INFO)).getUser();
		if (null == this.checkFile()) {
			return INPUT;
		}
		if (0 == this.deviceType) {
			CmaInfo cmaInfo = this.checkCmaInfo();
			if (null == cmaInfo) {
				return INPUT;
			}
			if (!this.isCancelUnfinishedUpdateTask.contains("true")) {
				List<TaskConfig> unfinishedUpdateTasks = this.taskConfigService.getAllUnfinishedUpdateTasks(cmaInfo);
				if (unfinishedUpdateTasks.size() > 0) {
					request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "该监测代理已经存在未完成任务");
					request.setAttribute("unfinishedUpdateTasks", unfinishedUpdateTasks);
				}
			}
			this.updateService.addUpdateTask(user, cmaInfo, this.upgradeFile);
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作成功");
			return SUCCESS;
		} else if (1 == this.deviceType) {
			Sensor sensor = this.checkSensor();
			if (null == sensor) {
				return INPUT;
			}
			if (!this.isCancelUnfinishedUpdateTask.contains("true")) {
				List<TaskConfig> unfinishedUpdateTasks = this.taskConfigService.getAllUnfinishedUpdateTasks(sensor);
				if (unfinishedUpdateTasks.size() > 0) {
					request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "该监测装置已经存在未完成任务");
					request.setAttribute("unfinishedUpdateTasks", unfinishedUpdateTasks);
				}
			}
			this.updateService.addUpdateTask(user, sensor, this.upgradeFile);
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "操作成功");
			return SUCCESS;

		} else {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "装置类型不正确");
			return INPUT;
		}
	}

	// /**
	// * 检查是否已经选择要操作的对象
	// *
	// * @return
	// */
	// private boolean check() {
	// if (null == this.sensorId || 0 == this.sensorId.trim().length()) {
	// if (null == this.cmaInfoId || 0 == this.cmaInfoId.trim().length()) {
	// request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先选择要进行操作的对象");
	// return false;
	// }
	// }
	// return true;
	// }

	private Sensor checkSensor() {
		if (null == this.sensorId || 0 == this.sensorId.trim().length()) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先选择要进行操作的监测装置");
			return null;
		}
		Sensor sensor = this.sensorService.getSensor(this.sensorId);
		if (null == sensor) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "监测装置不存在，可能已经被删除");
			return null;
		}
		return sensor;
	}

	private CmaInfo checkCmaInfo() {
		if (null == this.cmaInfoId || 0 == this.cmaInfoId.trim().length()) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先选择要进行操作的监测代理");
			return null;
		}
		CmaInfo cmaInfo = this.cmaInfoService.getCmaInfo(this.cmaInfoId);
		if (null == cmaInfo) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "监测代理不存在，可能已经被删除");
			return null;
		}
		return cmaInfo;
	}

	/**
	 * 检查有没有选择升级文件。
	 * 
	 * @return
	 */
	private UpgradeFile checkFile() {
		if (null == this.upgradeFile.getUpgradeFileId() || 0 == this.upgradeFile.getUpgradeFileId().trim().length()) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先选择升级文件");
			return null;
		}
		UpgradeFile file = this.upgradeFileService.getUpgradeFile(this.upgradeFile.getUpgradeFileId());
		if (null == file) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "升级文件不存在，可能已经被删除");
			return null;
		}
		this.upgradeFile = file;
		return this.upgradeFile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	@Override
	public void prepare() throws Exception {
		//过滤停用
		SensorQueryModel sensorQueryModel = new SensorQueryModel();
		sensorQueryModel.setState(1);
		sensorQueryModel.setStateQueryMode(NumberQueryMode.EQ);
		request.setAttribute("sensors", this.sensorService.getAllSensors(sensorQueryModel));
		
		//过滤停用
		CmaInfoQueryModel cmaInfoQueryModel = new CmaInfoQueryModel();
		cmaInfoQueryModel.setState(1);
		cmaInfoQueryModel.setStateQueryMode(NumberQueryMode.EQ);
		request.setAttribute("cmaInfos", this.cmaInfoService.getAllCmaInfos(cmaInfoQueryModel));
		
		request.setAttribute("upgradeFiles", this.upgradeFileService.getAllUpgradeFiles());
	}
}
