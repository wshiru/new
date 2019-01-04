/*
 * Project platform
 *
 * Class TowerAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-24 上午09:31:42
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
import com.opensymphony.xwork2.Preparable;
import com.yixin.A1000.archive.model.ArchiveErrorCode;
import com.yixin.A1000.archive.model.Line;
import com.yixin.A1000.archive.model.Tower;
import com.yixin.A1000.archive.model.TowerQueryModel;
import com.yixin.A1000.archive.model.TowerType;
import com.yixin.A1000.archive.service.LineService;
import com.yixin.A1000.archive.service.TowerService;
import com.yixin.A1000.archive.service.TowerTypeService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.db.ObjectQueryMode;
import com.yixin.framework.base.model.db.StringQueryMode;
import com.yixin.framework.exception.BusinessException;

/**
 * 杆塔管理子模块Sction处理类
 * 
 * @version v1.0.0
 * @author 
 */
public class TowerAction extends ActionSupport implements ServletRequestAware, Preparable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = 599560215767015429L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 杆塔业务接口 */
	private TowerService towerService;

	/** 线路业务接口 */
	private LineService lineService;

	/** 杆塔类型业务接口 */
	private TowerTypeService towerTypeService;

	/** 杆塔信息。保存新增杆塔提交的数据，及保存到request域的杆塔信息等 */
	private Tower tower;

	/** 杆塔ID，在修改、删除时用到 */
	private String id;

	/** 查询模型 */
	private TowerQueryModel queryModel = new TowerQueryModel();

	/**
	 * 获取 杆塔信息。保存新增杆塔提交的数据，及保存到request域的杆塔信息等
	 * 
	 * @return 杆塔信息
	 */
	public Tower getTower() {
		return tower;
	}

	/**
	 * 设置 杆塔信息。保存新增杆塔提交的数据，及保存到request域的杆塔信息等
	 * 
	 * @param tower
	 *            杆塔信息
	 */
	public void setTower(Tower tower) {
		this.tower = tower;
	}

	/**
	 * 获取 杆塔ID，在修改、删除时用到
	 * 
	 * @return 杆塔ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 杆塔ID，在修改、删除时用到
	 * 
	 * @param id
	 *            杆塔ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 查询模型
	 * 
	 * @return 查询模型
	 */
	public TowerQueryModel getQueryModel() {
		return queryModel;
	}

	/**
	 * 设置 查询模型
	 * 
	 * @param queryModel
	 *            查询模型
	 */
	public void setQueryModel(TowerQueryModel queryModel) {
		this.queryModel = queryModel;
	}

	/**
	 * 设置 杆塔业务接口
	 * 
	 * @param towerService
	 *            杆塔业务接口
	 */
	public void setTowerService(TowerService towerService) {
		this.towerService = towerService;
	}

	/**
	 * 设置 线路业务接口
	 * 
	 * @param lineService
	 *            线路业务接口
	 */
	public void setLineService(LineService lineService) {
		this.lineService = lineService;
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
	 * 杆塔列表
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
		if (null != this.queryModel.getLine() && null == this.queryModel.getLine().getLineId()) {
			this.queryModel.setLine(null);
		} else if (null != this.queryModel.getLine() && null != this.queryModel.getLine().getLineId()) {
			this.queryModel.setLine(this.lineService.getLine(this.queryModel.getLine().getLineId()));
			this.queryModel.setLineQueryMode(ObjectQueryMode.EQ);
		}
		if (null != this.queryModel.getTowerType() && null == this.queryModel.getTowerType().getTowerTypeId()) {
			this.queryModel.setTowerType(null);
		} else if (null != this.queryModel.getTowerType() && null != this.queryModel.getTowerType().getTowerTypeId()) {
			this.queryModel.setTowerType(this.towerTypeService.getTowerType(this.queryModel.getTowerType()
					.getTowerTypeId()));
			this.queryModel.setTowerTypeQueryMode(ObjectQueryMode.EQ);
		}
		if (null == this.queryModel.getTowerCode() || 0 == this.queryModel.getTowerCode().trim().length()) {
			this.queryModel.setTowerCode(null);
		}
		this.queryModel.setTowerCodeQueryMode(StringQueryMode.LIKE);
		Page<Tower> page = this.towerService.getPageTowers(this.queryModel, pageNo, pageSize);
		request.setAttribute("page", page);
		this.initData();
		return SUCCESS;
	}

	/**
	 * 跳转到新增杆塔页面
	 * 
	 * @return 结果页面
	 */
	public String add() {
		this.initData();
		return SUCCESS;
	}

	/**
	 * 保存新增杆塔信息
	 * 
	 * @return 结果页面
	 */
	public String addSave() {
		this.initData();
		if (null == this.tower.getLine() || null == this.tower.getLine().getLineId()) {
			request.setAttribute("errorMessage", "请先选择线路");
			return INPUT;
		}
		Line l = this.lineService.getLine(this.tower.getLine().getLineId());
		if (l == null) {
			request.setAttribute("errorMessage", "线路不存在");
			return INPUT;
		}

		if (null == this.tower.getTowerType() || null == this.tower.getTowerType().getTowerTypeId()) {
			request.setAttribute("errorMessage", "请先选择杆塔类型");
			return INPUT;
		}
		TowerType ty = this.towerTypeService.getTowerType(this.tower.getTowerType().getTowerTypeId());
		if (ty == null) {
			request.setAttribute("errorMessage", "杆塔类型不存在");
			return INPUT;
		}
		try {
			this.tower.setLine(l);
			this.tower.setTowerType(ty);
			this.towerService.addTower(this.tower);
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			case ArchiveErrorCode.ARCHIVE_TOWER_TOWERCODE_ALREADYEXISTS:
				this.request.setAttribute(TowerAction.ERROR_MESSAGE_ATTR_NAME, "杆塔编号已经存在");
				break;
			}
			return INPUT;
		}
		return this.list();
	}

	/**
	 * 跳转到修改杆塔页面
	 * 
	 * @return 结果页面
	 */
	public String edit() {
		this.initData();
		this.tower = this.towerService.getTower(this.id);
		if (null == this.tower) {
			request.setAttribute(TowerAction.ERROR_MESSAGE_ATTR_NAME, "杆塔不存在，可能已经被删除");
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 保存修改杆塔信息
	 * 
	 * @return 结果页面
	 */
	public String editSave() {
		this.initData();
		Tower towerObj = this.towerService.getTower(this.tower.getTowerId());
		if (null == towerObj) {
			request.setAttribute(TowerAction.ERROR_MESSAGE_ATTR_NAME, "杆塔不存在，可能已经被删除");
			return INPUT;
		} else {
			if (null == this.tower.getLine() || null == this.tower.getLine().getLineId()) {
				request.setAttribute("errorMessage", "请先选择线路");
				return INPUT;
			}
			Line l = this.lineService.getLine(this.tower.getLine().getLineId());
			if (l == null) {
				request.setAttribute("errorMessage", "所属线路不存在");
				return INPUT;
			}

			if (null == this.tower.getTowerType() || null == this.tower.getTowerType().getTowerTypeId()) {
				request.setAttribute("errorMessage", "请先选择杆塔类型");
				return INPUT;
			}
			TowerType ty = this.towerTypeService.getTowerType(this.tower.getTowerType().getTowerTypeId());
			if (ty == null) {
				request.setAttribute("errorMessage", "杆塔类型不存在");
				return INPUT;
			}
			towerObj.setAddress(this.tower.getAddress());
			towerObj.setLatitude(this.tower.getLatitude());
			towerObj.setLine(this.lineService.getLine(this.tower.getLine().getLineId()));
			towerObj.setLongitude(this.tower.getLongitude());
			towerObj.setTowerCode(this.tower.getTowerCode());
			towerObj.setTowerDesc(this.tower.getTowerDesc());
			towerObj.setTowerHeight(this.tower.getTowerHeight());
			this.tower.setLine(l);
			this.tower.setTowerType(ty);
			towerObj.setTowerType(this.towerTypeService.getTowerType(this.tower.getTowerType().getTowerTypeId()));
			this.tower = towerObj;
			try {
				this.towerService.editTower(this.tower);
				request.setAttribute(TowerAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
			} catch (BusinessException ex) {
				switch (ex.getErrorCode()) {
				case ArchiveErrorCode.ARCHIVE_TOWER_TOWERCODE_ALREADYEXISTS:
					this.request.setAttribute(TowerAction.ERROR_MESSAGE_ATTR_NAME, "杆塔编号已经存在");
					break;
				}
				return INPUT;
			}
		}
		return this.list();
	}

	/**
	 * 跳转到杆塔详细信息页面
	 * 
	 * @return 结果页面
	 */
	public String detail() {
		this.tower = this.towerService.getTower(this.id);
		if (null == this.tower) {
			request.setAttribute(TowerAction.ERROR_MESSAGE_ATTR_NAME, "杆塔不存在，可能已经被删除");
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 删除杆塔
	 * 
	 * @return 结果页面
	 */
	public String delete() {
		this.tower = this.towerService.getTower(this.id);
		if (null == this.tower) {
			request.setAttribute(TowerAction.ERROR_MESSAGE_ATTR_NAME, "杆塔不存在，可能已经被删除");
			return INPUT;
		}
		try {
			this.towerService.deleteTower(this.tower);
			request.setAttribute(TowerAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			case ArchiveErrorCode.ARCHIVE_TOWER_EXISTSSENSORS:
				request.setAttribute(TowerAction.ERROR_MESSAGE_ATTR_NAME, "存在监测装置依赖于该杆塔，请先删除该杆塔下的监测装置");
				break;
			}
		}
		return this.list();
	}

	public void prepare() throws Exception {
	}

	public void prepareEditSave() throws Exception {
		this.initData();
	}

	public void prepareAddSave() throws Exception {
		this.initData();
	}

	/**
	 * 初始化杆塔类型、线路等信息
	 * 
	 */
	private void initData() {
		request.setAttribute("towerTypes", this.towerTypeService.getAllTowerTypes());
		request.setAttribute("lines", this.lineService.getAllLines());
	}
}
