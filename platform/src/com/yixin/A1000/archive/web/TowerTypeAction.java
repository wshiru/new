/*
 * Project platform
 *
 * Class TowerTypeAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-24 上午09:16:37
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
import com.yixin.A1000.archive.model.TowerType;
import com.yixin.A1000.archive.model.TowerTypeQueryModel;
import com.yixin.A1000.archive.service.TowerTypeService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.db.StringQueryMode;
import com.yixin.framework.exception.BusinessException;

/**
 * 杆塔类型管理子模块Sction处理类
 * 
 * @version v1.0.0
 * @author 
 */
public class TowerTypeAction extends ActionSupport implements ServletRequestAware {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = 4537312560867743990L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 杆塔类型业务接口 */
	private TowerTypeService towerTypeService;

	/** 杆塔类型信息。保存新增杆塔类型提交的数据，及保存到request域的杆塔类型信息等 */
	private TowerType towerType;

	/** 杆塔类型ID，在修改、删除时用到 */
	private String id;

	/** 查询模型 */
	private TowerTypeQueryModel queryModel = new TowerTypeQueryModel();

	/**
	 * 获取 杆塔类型信息。保存新增杆塔类型提交的数据，及保存到request域的杆塔类型信息等
	 * 
	 * @return 杆塔类型信息
	 */
	public TowerType getTowerType() {
		return towerType;
	}

	/**
	 * 设置 杆塔类型信息。保存新增杆塔类型提交的数据，及保存到request域的杆塔类型信息等
	 * 
	 * @param towerType
	 *            杆塔类型信息
	 */
	public void setTowerType(TowerType towerType) {
		this.towerType = towerType;
	}

	/**
	 * 获取 杆塔类型ID，在修改、删除时用到
	 * 
	 * @return 杆塔类型ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 杆塔类型ID，在修改、删除时用到
	 * 
	 * @param id
	 *            杆塔类型ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 查询模型
	 * 
	 * @return 查询模型
	 */
	public TowerTypeQueryModel getQueryModel() {
		return queryModel;
	}

	/**
	 * 设置 查询模型
	 * 
	 * @param queryModel
	 *            查询模型
	 */
	public void setQueryModel(TowerTypeQueryModel queryModel) {
		this.queryModel = queryModel;
	}

	/**
	 * 设置 杆塔类型业务接口
	 * 
	 * @param towerTypeService
	 *            杆塔类型业务接口
	 */
	public void setTowerTypeService(TowerTypeService towerTypeService) {
		this.towerTypeService = towerTypeService;
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
	 * 杆塔类型列表
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
		if (null == this.queryModel.getTowerTypeCode() || 0 == this.queryModel.getTowerTypeCode().trim().length()) {
			this.queryModel.setTowerTypeCode(null);
		}
		if (null == this.queryModel.getTowerTypeDesc() || 0 == this.queryModel.getTowerTypeDesc().trim().length()) {
			this.queryModel.setTowerTypeDesc(null);
		}
		if (null == this.queryModel.getTowerTypeName() || 0 == this.queryModel.getTowerTypeName().trim().length()) {
			this.queryModel.setTowerTypeName(null);
		}
		this.queryModel.setTowerTypeCodeQueryMode(StringQueryMode.LIKE);
		this.queryModel.setTowerTypeDescQueryMode(StringQueryMode.LIKE);
		this.queryModel.setTowerTypeNameQueryMode(StringQueryMode.LIKE);
		Page<TowerType> page = this.towerTypeService.getPageTowerTypes(this.queryModel, pageNo, pageSize);
		request.setAttribute("page", page);
		return SUCCESS;
	}

	/**
	 * 跳转到新增杆塔类型页面
	 * 
	 * @return 结果页面
	 */
	public String add() {
		return SUCCESS;
	}

	/**
	 * 保存新增杆塔类型信息
	 * 
	 * @return 结果页面
	 */
	public String addSave() {
		try {
			this.towerTypeService.addTowerType(towerType);
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			case ArchiveErrorCode.ARCHIVE_TOWERTYPE_TOWERTYPECODE_ALREADYEXISTS:
				this.request.setAttribute(TowerTypeAction.ERROR_MESSAGE_ATTR_NAME, "杆塔类型编号已经存在");
				break;
			}
			return INPUT;
		}
		return this.list();
	}

	/**
	 * 跳转到修改杆塔类型页面
	 * 
	 * @return 结果页面
	 */
	public String edit() {
		this.towerType = this.towerTypeService.getTowerType(this.id);
		if (null == this.towerType) {
			request.setAttribute(TowerTypeAction.ERROR_MESSAGE_ATTR_NAME, "杆塔类型不存在，可能已经被删除");
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 保存修改杆塔类型信息
	 * 
	 * @return 结果页面
	 */
	public String editSave() {
		TowerType towerTypeObj = this.towerTypeService.getTowerType(this.towerType.getTowerTypeId());
		if (null == towerTypeObj) {
			request.setAttribute(TowerTypeAction.ERROR_MESSAGE_ATTR_NAME, "杆塔类型不存在，可能已经被删除");
			return INPUT;
		} else {
			towerTypeObj.setTowerTypeCode(this.towerType.getTowerTypeCode());
			towerTypeObj.setTowerTypeDesc(this.towerType.getTowerTypeDesc());
			towerTypeObj.setTowerTypeName(this.towerType.getTowerTypeName());
			this.towerType = towerTypeObj;
			try {
				this.towerTypeService.editTowerType(this.towerType);
				request.setAttribute(TowerTypeAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
			} catch (BusinessException ex) {
				switch (ex.getErrorCode()) {
				case ArchiveErrorCode.ARCHIVE_TOWERTYPE_TOWERTYPECODE_ALREADYEXISTS:
					this.request.setAttribute(TowerTypeAction.ERROR_MESSAGE_ATTR_NAME, "杆塔类型编号已经存在");
					break;
				}
				return INPUT;
			}
		}
		return this.list();
	}

	/**
	 * 跳转到杆塔类型详细信息页面
	 * 
	 * @return 结果页面
	 */
	public String detail() {
		this.towerType = this.towerTypeService.getTowerType(this.id);
		if (null == this.towerType) {
			request.setAttribute(TowerTypeAction.ERROR_MESSAGE_ATTR_NAME, "杆塔类型不存在，可能已经被删除");
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 删除杆塔类型
	 * 
	 * @return 结果页面
	 */
	public String delete() {
		this.towerType = this.towerTypeService.getTowerType(this.id);
		if (null == this.towerType) {
			request.setAttribute(TowerTypeAction.ERROR_MESSAGE_ATTR_NAME, "杆塔类型不存在，可能已经被删除");
			return INPUT;
		}
		try {
			this.towerTypeService.deleteTowerType(this.towerType);
			request.setAttribute(TowerTypeAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			case ArchiveErrorCode.ARCHIVE_TOWERTYPE_EXISTSTOWERS:
				request.setAttribute(TowerTypeAction.ERROR_MESSAGE_ATTR_NAME, "存在杆塔依赖于该杆塔类型，请先删除该杆塔类型下的杆塔");
				break;
			}
		}
		return this.list();
	}
}