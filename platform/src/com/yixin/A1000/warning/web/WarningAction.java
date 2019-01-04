
/*
 * Project platform
 *
 * Classname WarningAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-05 16:00
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.warning.web;

import java.util.Date;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.LineService;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.archive.service.TowerService;
import com.yixin.A1000.warning.model.Warning;
import com.yixin.A1000.warning.model.WarningQueryModel;
import com.yixin.A1000.warning.service.WarningDictService;
import com.yixin.A1000.warning.service.WarningService;
import com.yixin.framework.base.web.BaseAction;

/**
 * 告警Action类
 * 
 * @version v1.0.0
 * @author 
 */
public class WarningAction extends BaseAction<Warning> {

	/** 序列版本ID  */
	private static final long serialVersionUID = 724875716008306770L;
	/** 告警类型列表键名  */
	private static final String WARNINGDICT_LIST = "warningDictList";
	
	/** 告警服务  */
	private WarningService warningService;
	/** 告警类型服务  */
	private WarningDictService warningDictService;
	
	/** 告警查询模型  */
	private WarningQueryModel warningQueryModel = new WarningQueryModel();
	
	/** 线路接口 */
	private LineService lineService;
	
	
	/** 选择了传感器（监测装置） */
	private Sensor sensor;
	
	/** 监测装置接口 */
	private SensorService sensorService;
	

	
	/**
	 * 设置 监测装置接口
	 * @param sensorService
	 */
	public void setSensorService(SensorService sensorService){
		this.sensorService = sensorService;
	}

	/**
	 * 设置 线路接口
	 * @param lineService
	 */
	
	public void setLineService(LineService lineService) {
		this.lineService = lineService;
	}

	/**
	 * 获取告警查询模型
	 * @return
	 */
	public WarningQueryModel getWarningQueryModel() {
		return warningQueryModel;
	}
	/**
	 * 设置告警查询模型
	 * @return
	 */
	public void setWarningQueryModel(WarningQueryModel warningQueryModel) {
		this.warningQueryModel = warningQueryModel;
	}
	/**
	 * 设置告警服务
	 * @return
	 */
	public void setWarningService(WarningService warningService) {
		this.warningService = warningService;
	}
	/**
	 * 设置告警类型服务
	 * @return
	 */	
	public void setWarningDictService(WarningDictService warningDictService) {
		this.warningDictService = warningDictService;
	}
	/**
	 * 在主菜单中调用的有监测装置ID参数
	 * @param id
	 */
	public void setId(String id){
		if(id != null){
			sensor = sensorService.getSensor(id);
		}
	}
	/**
	 * 查询时有这ID参数
	 * @param sensorId
	 */
	public void setSensorId(String sensorId){
		if(sensorId != null){
			sensor = sensorService.getSensor(sensorId);
		}			
	}
	
	/**
	 * 查询时有这ID参数
	 * @param sensorId
	 */
	public String getSensorId(){
		if(sensor  != null){
			return sensor.getSensorId();
		}			
		else{
			return null;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#list()
	 */
	@Override
	public String list() {
		
		if(warningQueryModel == null){
			page = this.warningService.getPageWarnings(getPageNo(), getPageSize());
		}else {
			 
			Date beginTime =  warningQueryModel.getBeginTime();
			Date endTime = warningQueryModel.getEndTime();
			if(null != beginTime && null != endTime && beginTime.after(endTime)){
				setErrorMsg("开始时间不能大于结束时间");
				//beginTime = null;
				//endTime = null;
			}
			String sensorCode = warningQueryModel.getSensorCode();
			
			if(null != sensorCode && !sensorCode.trim().isEmpty()){
				page = this.warningService.getPageWarningsBySensor(warningQueryModel.getLineId(), warningQueryModel.getWarningDict(),
						sensorCode, beginTime, endTime, null,getPageNo(), getPageSize());
			}else if(sensor != null){
				request.setAttribute("sensor", sensor);
				page = this.warningService.getPageWarningsBySensor(warningQueryModel.getLineId(),warningQueryModel.getWarningDict(),
						sensor.getSensorCode(),beginTime, endTime,null, getPageNo(), getPageSize());
			}
			else {
				String cmaCode = warningQueryModel.getCmaCode();
				cmaCode = cmaCode == null || cmaCode.trim().isEmpty()? null: cmaCode;
				page = this.warningService.getPageWarningsByCMA(warningQueryModel.getLineId(),warningQueryModel.getWarningDict(),
						cmaCode, beginTime, endTime, getPageNo(), getPageSize());
			}			
		}		
		return super.list();
	}
	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#initData()
	 */
	@Override
	protected void initData() {
		request.setAttribute("linesList", this.lineService.getAllLines());
		request.setAttribute(WARNINGDICT_LIST, this.warningDictService.getAllWarningDicts());
	}

}
