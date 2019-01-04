/*
 * Project platform
 *
 * Class TaskConfigAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-7-5 下午03:33:05
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.setting.web;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yixin.A1000.archive.service.CmaInfoService;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.setting.model.ProtocolCmdType;
import com.yixin.A1000.setting.model.SettingErrorCode;
import com.yixin.A1000.setting.model.TaskConfig;
import com.yixin.A1000.setting.model.TaskConfigQueryModel;
import com.yixin.A1000.setting.service.TaskConfigService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.db.DateQueryMode;
import com.yixin.framework.base.model.db.NumberQueryMode;
import com.yixin.framework.base.model.db.ObjectQueryMode;
import com.yixin.framework.base.model.db.StringQueryMode;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.system.service.UserService;

/**
 * 任务管理子模块Sction处理类
 * 
 * @version v1.0.0
 * @author 
 */
public class TaskConfigAction extends ActionSupport implements ServletRequestAware {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = 4107700670187326889L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 任务业务接口 */
	private TaskConfigService taskConfigService;

	/** 监测代理业务接口 */
	private CmaInfoService cmaInfoService;

	/** 监测装置业务接口 */
	private SensorService sensorService;

	/** 用户业务接口 */
	private UserService userService;

	/** 查询模型 */
	private TaskConfigQueryModel queryModel = new TaskConfigQueryModel();

	/** 任务ID。查看详细、取消任务时用到 */
	private String id;

	/** 查询：开始时间 */
	private Date queryBeginTime;

	/** 查询：结束时间 */
	private Date queryEndTime;
	
	/**
	 * 获取 查询模型
	 * 
	 * @return 查询模型
	 */
	public TaskConfigQueryModel getQueryModel() {
		return this.queryModel;
	}

	/**
	 * 设置 查询模型
	 * 
	 * @param queryModel
	 *            查询模型
	 */
	public void setQueryModel(TaskConfigQueryModel queryModel) {
		this.queryModel = queryModel;
	}

	/**
	 * 获取 任务ID。查看详细、取消任务时用到
	 * 
	 * @return 任务ID。查看详细、取消任务时用到
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 任务ID。查看详细、取消任务时用到
	 * 
	 * @param id
	 *            任务ID。查看详细、取消任务时用到
	 */
	public void setId(String id) {
		this.id = id;
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

	/**
	 * 设置 监测代理业务接口
	 * 
	 * @param cmaInfoService
	 *            监测代理业务接口
	 */
	public void setCmaInfoService(CmaInfoService cmaInfoService) {
		this.cmaInfoService = cmaInfoService;
	}

	/**
	 * 设置 监测装置业务接口
	 * 
	 * @param sensorService
	 *            监测装置业务接口
	 */
	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	/**
	 * 设置 用户业务接口
	 * 
	 * @param userService
	 *            用户业务接口
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 获取 查询：开始时间
	 *
	 * @return 查询：开始时间
	 */
	public Date getQueryBeginTime() {
		return this.queryBeginTime;
	}

	/**
	 * 设置 查询：开始时间
	 *
	 * @param queryBeginTime 查询：开始时间
	 */
	public void setQueryBeginTime(Date queryBeginTime) {
		this.queryBeginTime = queryBeginTime;
	}

	/**
	 * 获取 查询：结束时间
	 *
	 * @return 查询：结束时间
	 */
	public Date getQueryEndTime() {
		return this.queryEndTime;
	}

	/**
	 * 设置 查询：结束时间
	 *
	 * @param queryEndTime 查询：结束时间
	 */
	public void setQueryEndTime(Date queryEndTime) {
		this.queryEndTime = queryEndTime;
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
	 * 任务列表
	 * 
	 * @return 结果页面
	 */
	public String list() {
		this.initData();
		String pageNoStr = request.getParameter("pn");
		String pageSizeStr = request.getParameter("ps");
		int pageNo = 1;
		int pageSize = 20;
		if (pageNoStr != null && !pageNoStr.equals("")) {
			pageNo = Integer.parseInt(pageNoStr);
		}
		if (pageSizeStr != null && !pageSizeStr.equals("")) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		if (null != this.queryModel.getUser() && null == this.queryModel.getUser().getUserId()) {
			this.queryModel.setUser(null);
		} else if (null != this.queryModel.getUser() && null != this.queryModel.getUser().getUserId()) {
			this.queryModel.setUser(this.userService.getUser(this.queryModel.getUser().getUserId()));
			this.queryModel.setUserQueryMode(ObjectQueryMode.EQ);
		}
		if (null == this.queryModel.getSensorCode() || 0 == this.queryModel.getSensorCode().trim().length()) {
			this.queryModel.setSensorCode(null);
		}
		if (null == this.queryModel.getCmaCode() || 0 == this.queryModel.getCmaCode().trim().length()) {
			this.queryModel.setCmaCode(null);
		}
		if (null == this.queryModel.getCmdType() || 0 == this.queryModel.getCmdType().trim().length()) {
			this.queryModel.setCmdType(null);
		}
		Date beginTime = new Date();
		Date endTime = beginTime;
		if (null == this.queryBeginTime && null == this.queryEndTime) {
			beginTime = new Date();
			endTime = beginTime;
		} else  if (null == this.queryBeginTime) {
			beginTime = this.queryEndTime;
			endTime = this.queryEndTime;
		} else if (null == this.queryEndTime || this.queryBeginTime.after(this.queryEndTime)) {
			beginTime = this.queryBeginTime;
			endTime = this.queryBeginTime;
		} else {
			beginTime = this.queryBeginTime;
			endTime = this.queryEndTime;		
		}	
		Calendar begin = Calendar.getInstance();
		begin.setTime(beginTime);
		begin.set(begin.get(Calendar.YEAR), begin.get(Calendar.MONTH), begin.get(Calendar.DATE), 0, 0, 0);
		Calendar end = Calendar.getInstance();
		end.setTime(endTime);
		end.set(end.get(Calendar.YEAR), end.get(Calendar.MONTH), end.get(Calendar.DATE), 23, 59, 59);
		this.queryBeginTime = begin.getTime();
		this.queryEndTime = end.getTime();
		this.queryModel.setCreateTime(this.queryBeginTime);
		DateQueryMode createTimeQueryMode = DateQueryMode.BETWEEN.init(this.queryBeginTime, this.queryEndTime);
		this.queryModel.setCreateTimeQueryMode(createTimeQueryMode);
		this.queryModel.setSensorCodeQueryMode(StringQueryMode.LIKE);
		this.queryModel.setCmaCodeQueryMode(StringQueryMode.LIKE);
		this.queryModel.setCmdTypeQueryMode(StringQueryMode.EQ);
		this.queryModel.setStateQueryMode(NumberQueryMode.EQ);
		Page<TaskConfig> page = this.taskConfigService.getPageTaskConfigs(this.queryModel, pageNo, pageSize);
		request.setAttribute("page", page);
		return SUCCESS;
	}

	/**
	 * 跳转到任务详细信息页面
	 * 
	 * @return 结果页面
	 */
	public String detail() {
		TaskConfig task = this.checkTaskConfig();
		if (null == task) {
			return this.list();
		}
		request.setAttribute("taskConfigDetail", this.taskConfigService.getTaskConfigDetail(task));
		return SUCCESS;
	}

	/**
	 * 取消任务
	 * 
	 * @return 结果页面
	 */
	public String cancel() {
		TaskConfig task = this.checkTaskConfig();
		if (null == task) {
			return this.list();
		}
		try {
			this.taskConfigService.cancelTaskConfig(task);
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			case SettingErrorCode.TASKCONFIG_ALREADY_SEND:
				request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "任务已下发，无法取消任务");
				break;
			case SettingErrorCode.TASKCONFIG_ALREADY_FINISHED:
				request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "任务已执行完成，无法取消任务");
				break;
			}
		}
		return this.list();
	}

	/**
	 * 检查this.id是否为null或者为空
	 * 
	 * @return 从数据库取得的TaskConfig对象。当this.id == null 或者为空时返回null
	 */
	private TaskConfig checkTaskConfig() {
		if (null == this.id || this.id.equals("")) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请选择操作项后再进行操作");
			return null;
		}
		TaskConfig task = this.taskConfigService.getTaskConfig(this.id);
		if (null == task) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "任务不存在，可能已经被删除");
			return null;
		}
		return task;
	}

	/**
	 * 初始化操作用户、监测代理、监测装置等信息
	 */
	private void initData() {
		request.setAttribute("users", this.userService.getAllUsers());
		request.setAttribute("cmaInfos", this.cmaInfoService.getAllCmaInfos());
		request.setAttribute("sensors", this.sensorService.getAllSensors());
		Map<String, String> cmdTypes = new HashMap<String, String>();
		cmdTypes.put(ProtocolCmdType.GETCONFIG, "读配置交互");
		cmdTypes.put(ProtocolCmdType.SETCONFIG, "写配置交互");
		cmdTypes.put(ProtocolCmdType.ACTIVATE, "控制交互");
		cmdTypes.put(ProtocolCmdType.DEACTIVATE, "休眠");
		cmdTypes.put(ProtocolCmdType.RESEND, "数据重传");
		cmdTypes.put(ProtocolCmdType.REALTIME, "实时数据");
		cmdTypes.put(ProtocolCmdType.UPGRADE, "远程升级");
		request.setAttribute("cmdTypes", cmdTypes);
	}
}
