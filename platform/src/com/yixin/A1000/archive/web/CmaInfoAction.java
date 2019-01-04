/*
 * Project platform
 *
 * Class CmaInfoAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 下午05:00:23
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yixin.A1000.archive.model.ArchiveErrorCode;
import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.CmaInfoQueryModel;
import com.yixin.A1000.archive.service.CmaInfoService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.db.NumberQueryMode;
import com.yixin.framework.base.model.db.StringQueryMode;
import com.yixin.framework.exception.BusinessException;

/**
 * 监测代理管理子模块Sction处理类
 * 
 * @version v1.0.0
 * @author 
 */
public class CmaInfoAction extends ActionSupport implements ServletRequestAware {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = 4167738885788339959L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 监测代理业务接口 */
	private CmaInfoService cmaInfoService;

	/** 监测代理信息。保存新增监测代理提交的数据，及保存到request域的监测代理信息等 */
	private CmaInfo cmaInfo;

	/** 监测代理ID，在修改、删除时用到 */
	private String id;

	/** 查询模型 */
	private CmaInfoQueryModel queryModel = new CmaInfoQueryModel();

	/**
	 * 获取 监测代理信息。保存新增监测代理提交的数据，及保存到request域的监测代理信息等
	 * 
	 * @return 监测代理信息
	 */
	public CmaInfo getCmaInfo() {
		return cmaInfo;
	}

	/**
	 * 设置 监测代理信息。保存新增监测代理提交的数据，及保存到request域的监测代理信息等
	 * 
	 * @param cmaInfo
	 *            监测代理信息
	 */
	public void setCmaInfo(CmaInfo cmaInfo) {
		this.cmaInfo = cmaInfo;
	}

	/**
	 * 获取 监测代理ID，在修改、删除时用到
	 * 
	 * @return 监测代理ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 监测代理ID，在修改、删除时用到
	 * 
	 * @param id
	 *            监测代理ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 查询模型
	 * 
	 * @return 查询模型
	 */
	public CmaInfoQueryModel getQueryModel() {
		return queryModel;
	}

	/**
	 * 设置 查询模型
	 * 
	 * @param queryModel
	 *            查询模型
	 */
	public void setQueryModel(CmaInfoQueryModel queryModel) {
		this.queryModel = queryModel;
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
	 * 监测代理列表
	 * 
	 * @return 结果页面
	 */
	public String list() {
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
		if (null == this.queryModel.getCmaCode() || 0 == this.queryModel.getCmaCode().trim().length()) {
			this.queryModel.setCmaCode(null);
		}
		if (null == this.queryModel.getCmaName() || 0 == this.queryModel.getCmaName().trim().length()) {
			this.queryModel.setCmaName(null);
		}
		if (null == this.queryModel.getCmaModel() || 0 == this.queryModel.getCmaModel().trim().length()) {
			this.queryModel.setCmaModel(null);
		}
		this.queryModel.setCmaCodeQueryMode(StringQueryMode.LIKE);
		this.queryModel.setCmaNameQueryMode(StringQueryMode.LIKE);
		this.queryModel.setCmaModelQueryMode(StringQueryMode.LIKE);
		this.queryModel.setStateQueryMode(NumberQueryMode.EQ);
		Page<CmaInfo> page = this.cmaInfoService.getPageCmaInfos(this.queryModel, pageNo, pageSize);
		request.setAttribute("page", page);
		return SUCCESS;
	}

	/**
	 * 跳转到新增监测代理页面
	 * 
	 * @return 结果页面
	 */
	public String add() {
		return SUCCESS;
	}

	/**
	 * 保存新增监测代理信息
	 * 
	 * @return 结果页面
	 */
	public String addSave() {
		try {
			this.cmaInfoService.addCmaInfo(cmaInfo);
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			case ArchiveErrorCode.ARCHIVE_CMAINFO_CMACODE_ALREADYEXISTS:
				this.request.setAttribute(CmaInfoAction.ERROR_MESSAGE_ATTR_NAME, "监测代理编号已经存在");
				break;
			}
			return INPUT;
		}
		return this.list();
	}

	/**
	 * 跳转到修改监测代理页面
	 * 
	 * @return 结果页面
	 */
	public String edit() {
		this.cmaInfo = this.cmaInfoService.getCmaInfo(this.id);
		if (null == this.cmaInfo) {
			request.setAttribute(CmaInfoAction.ERROR_MESSAGE_ATTR_NAME, "监测代理不存在，可能已经被删除");
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 保存修改监测代理信息
	 * 
	 * @return 结果页面
	 */
	public String editSave() {
		CmaInfo cmaInfoObj = this.cmaInfoService.getCmaInfo(this.cmaInfo.getCmaInfoId());
		if (null == cmaInfoObj) {
			request.setAttribute(CmaInfoAction.ERROR_MESSAGE_ATTR_NAME, "监测代理不存在，可能已经被删除");
			return INPUT;
		} else {
			cmaInfoObj.setCmaCode(this.cmaInfo.getCmaCode());
			cmaInfoObj.setCmaDesc(this.cmaInfo.getCmaDesc());
			cmaInfoObj.setCmaModel(this.cmaInfo.getCmaModel());
			cmaInfoObj.setCmaName(this.cmaInfo.getCmaName());
			cmaInfoObj.setInstallDate(this.cmaInfo.getInstallDate());
			cmaInfoObj.setState(this.cmaInfo.getState());
			this.cmaInfo = cmaInfoObj;
			try {
				this.cmaInfoService.editCmaInfo(this.cmaInfo);
				request.setAttribute(CmaInfoAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
			} catch (BusinessException ex) {
				switch (ex.getErrorCode()) {
				case ArchiveErrorCode.ARCHIVE_CMAINFO_CMACODE_ALREADYEXISTS:
					this.request.setAttribute(CmaInfoAction.ERROR_MESSAGE_ATTR_NAME, "监测代理编号已经存在");
					break;
				}
				return INPUT;
			}
		}
		return this.list();
	}

	/**
	 * 跳转到监测代理详细信息页面
	 * 
	 * @return 结果页面
	 */
	public String detail() {
		this.cmaInfo = this.cmaInfoService.getCmaInfo(this.id);
		if (null == this.cmaInfo) {
			request.setAttribute(CmaInfoAction.ERROR_MESSAGE_ATTR_NAME, "监测代理不存在，可能已经被删除");
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 删除监测代理
	 * 
	 * @return 结果页面
	 */
	public String delete() {
		this.cmaInfo = this.cmaInfoService.getCmaInfo(this.id);
		if (null == this.cmaInfo) {
			request.setAttribute(CmaInfoAction.ERROR_MESSAGE_ATTR_NAME, "监测代理不存在，可能已经被删除");
			return INPUT;
		}
		try {
			this.cmaInfoService.deleteCmaInfo(this.cmaInfo);
			request.setAttribute(CmaInfoAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			case ArchiveErrorCode.ARCHIVE_CMAINFO_EXISTSSENSORS:
				request.setAttribute(CmaInfoAction.ERROR_MESSAGE_ATTR_NAME, "存在监测装置依赖于该监测代理，请先删除该监测代理下的监测装置");
				break;
			}
		}
		return this.list();
	}
}
