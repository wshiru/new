/*
 * Project platform
 *
 * Class SensorTypeAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-24 上午08:34:26
 *
 * ModifiedBy 
 * ModifyAt 2011-07-12 14：20
 * ModifyDescription 添加功能配置
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yixin.A1000.archive.model.ArchiveErrorCode;
import com.yixin.A1000.archive.model.SensorFunction;
import com.yixin.A1000.archive.model.SensorType;
import com.yixin.A1000.archive.model.SensorTypeQueryModel;
import com.yixin.A1000.archive.service.SensorFunctionService;
import com.yixin.A1000.archive.service.SensorTypeService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.base.model.QueryProperty;
import com.yixin.framework.base.model.db.NumberQueryMode;
import com.yixin.framework.base.model.db.StringQueryMode;
import com.yixin.framework.exception.BusinessException;
import com.yixin.framework.system.dao.AuthResourceDao;
import com.yixin.framework.system.model.AuthResource;

/**
 * 监测类型管理子模块Sction处理类
 * 
 * @version v1.0.0
 * @author 
 */
public class SensorTypeAction extends ActionSupport implements ServletRequestAware {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -6393185104998667399L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 监测类型业务接口 */
	private SensorTypeService sensorTypeService;

	/** 监测类型信息。保存新增监测类型提交的数据，及保存到request域的监测类型信息等 */
	private SensorType sensorType;

	/** 监测类型ID，在修改、删除时用到 */
	private String id;

	/** 查询模型 */
	private SensorTypeQueryModel queryModel = new SensorTypeQueryModel();


	/** 全部功能资源集键名 */
	private static String ALL_AUTHRESOURCE = "allAuths";
	/** 已有功能资源ID集键名 */
	private static String OWENED_AUTHRESOURCE = "ownedAuthIds";	
	
	/** 权限功能ID列表  */
	private String[] authResourceIds = new String[0]; 
	/** 权限资源DAO  */
	private AuthResourceDao authResourceDao;
	/** 监测装置功能服务 */
	private SensorFunctionService sensorFunctionService;
	
	/**
	 * 获取权限ID列表
	 * @return
	 */
	public String[] getAuthResourceIds() {
		return authResourceIds;
	}
	/**
	 * 设置权限ID列表
	 * @param authResourceIds
	 */
	public void setAuthResourceIds(String[] authResourceIds) {
		this.authResourceIds = authResourceIds;
	}
	/**
	 * 设置权限资源DAO
	 * @param authResourceDao
	 */
	public void setAuthResourceDao(AuthResourceDao authResourceDao) {
		this.authResourceDao = authResourceDao;
	}
	/**
	 * 设置监测装置功能服务	
	 * @param sensorFunctionService
	 */
	public void setSensorFunctionService(SensorFunctionService sensorFunctionService) {
		this.sensorFunctionService = sensorFunctionService;
	}
	
	
	
	/**
	 * 获取 监测类型信息。保存新增监测类型提交的数据，及保存到request域的监测类型信息等
	 * 
	 * @return 监测类型信息
	 */
	public SensorType getSensorType() {
		return sensorType;
	}

	/**
	 * 设置 监测类型信息。保存新增监测类型提交的数据，及保存到request域的监测类型信息等
	 * 
	 * @param sensorType
	 *            监测类型信息
	 */
	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}

	/**
	 * 获取 监测类型ID，在修改、删除时用到
	 * 
	 * @return 监测类型ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 监测类型ID，在修改、删除时用到
	 * 
	 * @param id
	 *            监测类型ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 查询模型
	 * 
	 * @return 查询模型
	 */
	public SensorTypeQueryModel getQueryModel() {
		return queryModel;
	}

	/**
	 * 设置 查询模型
	 * 
	 * @param queryModel
	 *            查询模型
	 */
	public void setQueryModel(SensorTypeQueryModel queryModel) {
		this.queryModel = queryModel;
	}

	/**
	 * 设置 监测类型业务接口
	 * 
	 * @param sensorTypeService
	 *            监测类型业务接口
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
	 * 监测类型列表
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
		if (null == this.queryModel.getSensorDesc() || 0 == this.queryModel.getSensorDesc().trim().length()) {
			this.queryModel.setSensorDesc(null);
		}
		if (null == this.queryModel.getSensorTypeCode() || 0 == this.queryModel.getSensorTypeCode().trim().length()) {
			this.queryModel.setSensorTypeCode(null);
		}
		if (null == this.queryModel.getSensorTypeName() || 0 == this.queryModel.getSensorTypeName().trim().length()) {
			this.queryModel.setSensorTypeName(null);
		}
		this.queryModel.setSensorDescQueryMode(StringQueryMode.LIKE);
		this.queryModel.setSensorTypeCodeQueryMode(StringQueryMode.LIKE);
		this.queryModel.setSensorTypeNameQueryMode(StringQueryMode.LIKE);
		Page<SensorType> page = this.sensorTypeService.getPageSensorTypes(this.queryModel, pageNo, pageSize);
		request.setAttribute("page", page);
		return SUCCESS;
	}

	/**
	 * 跳转到新增监测类型页面
	 * 
	 * @return 结果页面
	 */
	public String add() {
		return SUCCESS;
	}

	/**
	 * 保存新增监测类型信息
	 * 
	 * @return 结果页面
	 */
	public String addSave() {
		try {
			this.sensorTypeService.addSensorType(sensorType);
			request.setAttribute(SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			case ArchiveErrorCode.ARCHIVE_SENSORTYPE_SENSORTYPECODE_ALREADYEXISTS:
				this.request.setAttribute(SensorTypeAction.ERROR_MESSAGE_ATTR_NAME, "监测类型编号已经存在");
				break;
			}
			return INPUT;
		}
		return this.list();
	}

	/**
	 * 跳转到修改监测类型页面
	 * 
	 * @return 结果页面
	 */
	public String edit() {
		this.sensorType = this.sensorTypeService.getSensorType(this.id);
		if (null == this.sensorType) {
			request.setAttribute(SensorTypeAction.ERROR_MESSAGE_ATTR_NAME, "监测类型不存在，可能已经被删除");
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 保存修改监测类型信息
	 * 
	 * @return 结果页面
	 */
	public String editSave() {
		SensorType sensorTypeObj = this.sensorTypeService.getSensorType(this.sensorType.getSensorTypeId());
		if (null == sensorTypeObj) {
			request.setAttribute(SensorTypeAction.ERROR_MESSAGE_ATTR_NAME, "监测类型不存在，可能已经被删除");
			return INPUT;
		} else {
			sensorTypeObj.setSensorDesc(this.sensorType.getSensorDesc());
			sensorTypeObj.setSensorTypeCode(this.sensorType.getSensorTypeCode());
			sensorTypeObj.setSensorTypeName(this.sensorType.getSensorTypeName());
			this.sensorType = sensorTypeObj;
			try {
				this.sensorTypeService.editSensorType(this.sensorType);
				request.setAttribute(SensorTypeAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
			} catch (BusinessException ex) {
				switch (ex.getErrorCode()) {
				case ArchiveErrorCode.ARCHIVE_SENSORTYPE_SENSORTYPECODE_ALREADYEXISTS:
					this.request.setAttribute(SensorTypeAction.ERROR_MESSAGE_ATTR_NAME, "监测类型编号已经存在");
					break;
				}
				return INPUT;
			}
		}
		return this.list();
	}

	/**
	 * 跳转到监测类型详细信息页面
	 * 
	 * @return 结果页面
	 */
	public String detail() {
		this.sensorType = this.sensorTypeService.getSensorType(this.id);
		if (null == this.sensorType) {
			request.setAttribute(SensorTypeAction.ERROR_MESSAGE_ATTR_NAME, "监测类型不存在，可能已经被删除");
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 删除监测类型
	 * 
	 * @return 结果页面
	 */
	public String delete() {
		this.sensorType = this.sensorTypeService.getSensorType(this.id);
		if (null == this.sensorType) {
			request.setAttribute(SensorTypeAction.ERROR_MESSAGE_ATTR_NAME, "监测类型不存在，可能已经被删除");
			return INPUT;
		}
		try {
			Collection<AuthResource> authResources = new HashSet<AuthResource>();
			this.sensorFunctionService.setSensorFunctions(sensorType, authResources);
			this.sensorTypeService.deleteSensorType(this.sensorType);
			request.setAttribute(SensorTypeAction.SUCCESS_MESSAGE_ATTR_NAME, "操作成功");
		} catch (BusinessException ex) {
			switch (ex.getErrorCode()) {
			case ArchiveErrorCode.ARCHIVE_SENSORTYPE_EXISTSSENSORS:
				request.setAttribute(SensorTypeAction.ERROR_MESSAGE_ATTR_NAME, "存在监测装置依赖于该监测类型，请先删除该监测类型下的监测装置");
				break;
			}
		}
		return this.list();
	}
	
	/**
	 * 配置功能项
	 * @return 配置页面
	 */	
	public String modSensorFunction() {
		this.sensorType = this.sensorTypeService.getSensorType(this.id);
		if (null == this.sensorType) {
			request.setAttribute(SensorTypeAction.ERROR_MESSAGE_ATTR_NAME, "监测类型不存在，可能已经被删除");
			return INPUT;
		}
		//已拥有功能
		StringBuilder builder = new StringBuilder();
		List<SensorFunction> ownnedFunctions = this.sensorFunctionService.getAllSensorFunctions(sensorType);
		for(SensorFunction sensorFunction : ownnedFunctions){
			builder.append(sensorFunction.getAuthResource().getAuthResourceId() + ",");
		}	
		if(builder.length() > 1){
			builder.deleteCharAt(builder.length()-1);
		}
		String ownedAuthIds = builder.toString();
		request.setAttribute(OWENED_AUTHRESOURCE, ownedAuthIds);
		
		//所有功能
		QueryProperty queryProperty = new QueryProperty("resourceType", 0, NumberQueryMode.NEQ);
		Collection<QueryProperty> queryProperties = new HashSet<QueryProperty>();
		queryProperties.add(queryProperty);
		
		List<AuthResource> allAuths = this.authResourceDao.getAll(queryProperties);
		
		List<AuthResource> ars = new ArrayList<AuthResource>();
		Iterator<AuthResource>  it = allAuths.iterator();
		while ( it.hasNext() ){
			AuthResource ar = it.next();
			if ( ar.getVisible() == 1 ){
				ars.add(ar);
			}
		}
		
		request.setAttribute(ALL_AUTHRESOURCE, ars);
		
		return "modFunc";
	}
	
	/**
	 * 配置功能项
	 * @return 
	 */
	public String updateSensorFunction() {
		this.sensorType = this.sensorTypeService.getSensorType(this.id);
		if (null == this.sensorType) {
			request.setAttribute(SensorTypeAction.ERROR_MESSAGE_ATTR_NAME, "监测类型不存在，可能已经被删除");
			return INPUT;
		}
		//新功能集合
		Collection<AuthResource> authResources = new HashSet<AuthResource>();
		for(int i=0, len=authResourceIds.length; i<len; i++){
			AuthResource aResource = this.authResourceDao.findById(authResourceIds[i]);
			authResources.add(aResource);
		}
		//设置新功能集合
		this.sensorFunctionService.setSensorFunctions(sensorType, authResources);
		
		request.setAttribute(SensorTypeAction.SUCCESS_MESSAGE_ATTR_NAME, "功能配置成功");
		
		return this.list();
	}
}