/*
 * Project platform
 *
 * Class SensorAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-24 下午02:11:13
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
import com.yixin.A1000.archive.model.CmaInfo;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.model.SensorQueryModel;
import com.yixin.A1000.archive.model.SensorType;
import com.yixin.A1000.archive.model.Tower;
import com.yixin.A1000.archive.service.CmaInfoService;
import com.yixin.A1000.archive.service.LineService;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.archive.service.SensorTypeService;
import com.yixin.A1000.archive.service.TowerService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.db.NumberQueryMode;
import com.yixin.framework.base.model.db.ObjectQueryMode;
import com.yixin.framework.base.model.db.StringQueryMode;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.util.StringUtil;

/**
 * 监测装置管理子模块Sction处理类
 * 
 * @version v1.0.0
 * @author 
 */
public class SensorAction extends ActionSupport implements ServletRequestAware, Preparable {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -3155961764140604438L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 监测装置业务接口 */
	private SensorService sensorService;

	/** 线路业务接口 */
	private LineService lineService;

	/** 杆塔业务接口 */
	private TowerService towerService;

	/** 监测代理业务接口 */
	private CmaInfoService cmaInfoService;

	/** 监测装置类型业务接口 */
	private SensorTypeService sensorTypeService;

	/** 监测装置信息。保存新增监测装置提交的数据，及保存到request域的监测装置信息等 */
	private Sensor sensor;

	/** 监测装置ID，在修改、删除时用到 */
	private String id;

	/** 查询模型 */
	private SensorQueryModel queryModel = new SensorQueryModel();

	/**
	 * 获取 监测装置信息。保存新增监测装置提交的数据，及保存到request域的监测装置信息等
	 * 
	 * @return 监测装置信息
	 */
	public Sensor getSensor() {
		return sensor;
	}

	/**
	 * 设置 监测装置信息。保存新增监测装置提交的数据，及保存到request域的监测装置信息等
	 * 
	 * @param sensor
	 *            监测装置信息
	 */
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	/**
	 * 获取 监测装置ID，在修改、删除时用到
	 * 
	 * @return 监测装置ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 监测装置ID，在修改、删除时用到
	 * 
	 * @param id
	 *            监测装置ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 查询模型
	 * 
	 * @return 查询模型
	 */
	public SensorQueryModel getQueryModel() {
		return queryModel;
	}

	/**
	 * 设置 查询模型
	 * 
	 * @param queryModel
	 *            查询模型
	 */
	public void setQueryModel(SensorQueryModel queryModel) {
		this.queryModel = queryModel;
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
	 * 设置 线路业务接口
	 * 
	 * @param lineService
	 *            线路业务接口
	 */
	public void setLineService(LineService lineService) {
		this.lineService = lineService;
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
	 * 设置 监测代理业务接口
	 * 
	 * @param cmaInfoService
	 *            监测代理业务接口
	 */
	public void setCmaInfoService(CmaInfoService cmaInfoService) {
		this.cmaInfoService = cmaInfoService;
	}

	/**
	 * 设置 监测装置类型业务接口
	 * 
	 * @param sensorTypeService
	 *            监测装置类型业务接口
	 */
	public void setSensorTypeService(SensorTypeService sensorTypeService) {
		this.sensorTypeService = sensorTypeService;
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
	 * 监测装置列表
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
		// if (null != this.queryModel.getTower().getLine() && null ==
		// this.queryModel.getTower().getLine().getLineId()) {
		// this.queryModel.getTower().setLine(null);
		// } else if (null != this.queryModel.getTower().getLine() && null !=
		// this.queryModel.getTower().getLine().getLineId()) {
		// this.queryModel.getTower().setLine(this.lineService.getLine(this.queryModel.getTower().getLine().getLineId()));
		// this.queryModel.setLineQueryMode(ObjectQueryMode.EQ);
		// }
		if (null != this.queryModel.getTower() && null == this.queryModel.getTower().getTowerId()) {
			this.queryModel.setTower(null);
		} else if (null != this.queryModel.getTower() && null != this.queryModel.getTower().getTowerId()) {
			this.queryModel.setTower(this.towerService.getTower(this.queryModel.getTower().getTowerId()));
			this.queryModel.setTowerQueryMode(ObjectQueryMode.EQ);
		}
		if (null != this.queryModel.getCmaInfo() && null == this.queryModel.getCmaInfo().getCmaInfoId()) {
			this.queryModel.setCmaInfo(null);
		} else if (null != this.queryModel.getCmaInfo() && null != this.queryModel.getCmaInfo().getCmaInfoId()) {
			this.queryModel.setCmaInfo(this.cmaInfoService.getCmaInfo(this.queryModel.getCmaInfo().getCmaInfoId()));
			this.queryModel.setCmaInfoQueryMode(ObjectQueryMode.EQ);
		}
		if (null != this.queryModel.getSensorType() && null == this.queryModel.getSensorType().getSensorTypeId()) {
			this.queryModel.setSensorType(null);
		} else if (null != this.queryModel.getSensorType() && null != this.queryModel.getSensorType().getSensorTypeId()) {
			this.queryModel.setSensorType(this.sensorTypeService.getSensorType(this.queryModel.getSensorType()
					.getSensorTypeId()));
			this.queryModel.setSensorTypeQueryMode(ObjectQueryMode.EQ);
		}
		if (null == this.queryModel.getSensorCode() || 0 == this.queryModel.getSensorCode().trim().length()) {
			this.queryModel.setSensorCode(null);
		}
		if (null == this.queryModel.getBySensorCode() || 0 == this.queryModel.getBySensorCode().trim().length()) {
			this.queryModel.setBySensorCode(null);
		}
		this.queryModel.setSensorCodeQueryMode(StringQueryMode.LIKE);
		this.queryModel.setBySensorCodeQueryMode(StringQueryMode.LIKE);
		this.queryModel.setStateQueryMode(NumberQueryMode.EQ);
		Page<Sensor> page = this.sensorService.getPageSensors(this.queryModel, pageNo, pageSize);
		request.setAttribute("page", page);
		this.initData();
		return SUCCESS;
	}

	/**
	 * 跳转到新增监测装置页面
	 * 
	 * @return 结果页面
	 */
	public String add() {
		this.initData();
		return SUCCESS;
	}

	/**
	 * 保存新增监测装置信息
	 * 
	 * @return 结果页面
	 */
	public String addSave() {
		this.initData();
		if (!this.checkSaveOrEdit()) {
			return INPUT;
		}
		try {
			this.sensorService.addSensor(this.sensor);
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			case ArchiveErrorCode.ARCHIVE_SENSOR_SENSORCODE_ALREADYEXISTS:
				this.request.setAttribute(SensorAction.ERROR_MESSAGE_ATTR_NAME, "监测装置编码已经存在");
				break;
			}
			return INPUT;
		}
		return this.list();
	}

	/**
	 * 跳转到修改监测装置页面
	 * 
	 * @return 结果页面
	 */
	public String edit() {
		this.initData();
		this.sensor = this.check();
		if (null == this.sensor) {
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 保存修改监测装置信息
	 * 
	 * @return 结果页面
	 */
	public String editSave() {
		this.initData();
		Sensor sensorObj = this.sensorService.getSensor(this.sensor.getSensorId());
		if (null == sensorObj) {
			request.setAttribute(SensorAction.ERROR_MESSAGE_ATTR_NAME, "监测装置不存在，可能已经被删除");
			return INPUT;
		} else {
			if (!this.checkSaveOrEdit()) {
				return INPUT;
			}
			
			sensorObj.setCmaInfo(this.sensor.getCmaInfo());
			sensorObj.setTower(this.sensor.getTower());
			sensorObj.setSensorType(this.sensor.getSensorType());
			sensorObj.setBySensorCode(this.sensor.getBySensorCode());
			sensorObj.setIdentificationNumber(this.sensor.getIdentificationNumber());
			sensorObj.setInstallSite(this.sensor.getInstallSite());
			sensorObj.setManuFacturer(this.sensor.getManuFacturer());
			sensorObj.setProductionDate(this.sensor.getProductionDate());
			sensorObj.setSensorCode(this.sensor.getSensorCode());
			sensorObj.setSensorDesc(this.sensor.getSensorDesc());
			sensorObj.setState(this.sensor.getState());
			this.sensor = sensorObj;
			try {
				this.sensorService.editSensor(this.sensor);
				request.setAttribute(SensorAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
			} catch (BusinessException ex) {
				switch (ex.getErrorCode()) {
				case ArchiveErrorCode.ARCHIVE_SENSOR_SENSORCODE_ALREADYEXISTS:
					this.request.setAttribute(SensorAction.ERROR_MESSAGE_ATTR_NAME, "监测装置编码已经存在");
					break;
				}
				return INPUT;
			}
		}
		return this.list();
	}

	/**
	 * 判断采集监测代理、所在杆塔、所属监测类型等项是否已选中。选中返回true，否则返回false
	 * 
	 * @return 选中返回true，否则返回false
	 */
	private boolean checkSaveOrEdit() {
		if (null == this.sensor.getTower() || !StringUtil.hasLength(this.sensor.getTower().getTowerId())) {
			request.setAttribute(SensorAction.ERROR_MESSAGE_ATTR_NAME, "请先选择所在杆塔");
			return false;
		} else {
			Tower t = this.towerService.getTower(this.sensor.getTower().getTowerId());
			if (null == t) {
				request.setAttribute(SensorAction.ERROR_MESSAGE_ATTR_NAME, "所在杆塔不存在");
				return false;
			}
			this.sensor.setTower(t);
		}
		if (null == this.sensor.getSensorType() || !StringUtil.hasLength(this.sensor.getSensorType().getSensorTypeId())) {
			request.setAttribute(SensorAction.ERROR_MESSAGE_ATTR_NAME, "请先选择所属监测类型");
			return false;
		} else {
			SensorType s = this.sensorTypeService.getSensorType(this.sensor.getSensorType().getSensorTypeId());
			if (null == s) {
				request.setAttribute(SensorAction.ERROR_MESSAGE_ATTR_NAME, "所属监测类型不存在");
				return false;
			}
			this.sensor.setSensorType(s);
		}
		
		
		//屏蔽掉监测代理 
		this.sensor.setCmaInfo(null);
		
		/*if (null == this.sensor.getCmaInfo() || !StringUtil.hasLength(this.sensor.getCmaInfo().getCmaInfoId())) {
			request.setAttribute(SensorAction.ERROR_MESSAGE_ATTR_NAME, "请先选择采集监测代理");
			return false;
		} else {
			CmaInfo c = this.cmaInfoService.getCmaInfo(this.sensor.getCmaInfo().getCmaInfoId());
			if (null == c) {
				request.setAttribute(SensorAction.ERROR_MESSAGE_ATTR_NAME, "采集监测代理不存在");
				return false;
			}
			this.sensor.setCmaInfo(c);
		}
		*/
		
		return true;
	}

	/**
	 * 跳转到监测装置详细信息页面
	 * 
	 * @return 结果页面
	 */
	public String detail() {
		this.sensor = this.check();
		if (null == this.sensor) {
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 删除监测装置
	 * 
	 * @return 结果页面
	 */
	public String delete() {
		this.sensor = this.check();
		if (null == this.sensor) {
			return this.list();
		}
		try {
			this.sensorService.deleteSensor(this.sensor);
			request.setAttribute(SensorAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} catch (Exception ex) {
//			switch (ex.getErrorCode()) {
//			case ArchiveErrorCode.ARCHIVE_TOWER_EXISTSSENSORS:
				request.setAttribute(SensorAction.ERROR_MESSAGE_ATTR_NAME, "请先删除该监测装置的数据。");
//				break;
//			}
		}
		return this.list();
	}

	/**
	 * 检查this.id是否为null或者为空
	 * 
	 * @return 从数据库取得的Sensor对象。当this.id == null 或者为空时返回null
	 */
	private Sensor check() {
		if (null == this.id || this.id.equals("")) {
			request.setAttribute(SensorAction.ERROR_MESSAGE_ATTR_NAME, "请选择操作项后再进行操作");
			return null;
		}
		Sensor s = this.sensorService.getSensor(this.id);
		if (null == s) {
			request.setAttribute(SensorAction.ERROR_MESSAGE_ATTR_NAME, "监测装置不存在，可能已经被删除");
			return null;
		}
		return s;
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
	 * 初始化监测装置类型、线路等信息
	 * 
	 */
	private void initData() {
		request.setAttribute("lines", this.lineService.getAllLines());
		request.setAttribute("towers", this.towerService.getAllTowers());
		//request.setAttribute("cmaInfos", this.cmaInfoService.getAllCmaInfos());
		request.setAttribute("sensorTypes", this.sensorTypeService.getAllSensorTypes());
	}
}
