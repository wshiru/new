package com.yixin.framework.login.model;

import java.io.Serializable;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.model.SensorType;

/**
 * 分组页的显示类
 * 
 * @author 梁立全

 * 2010 06 25
 */

public class MainPageInfo implements Serializable {

	private static final long serialVersionUID = 51623423431132356L;
	
	/** 传感器 类型**/
	private SensorType sensorType;
	
	
	/** 显示在线率等信息*/
	private String message;
 
	
	/** 数据项**/
	private List<GroupPage> groupPage;


	public SensorType getSensorType() {
		return sensorType;
	}


	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public List<GroupPage> getGroupPage() {
		return groupPage;
	}


	public void setGroupPage(List<GroupPage> groupPage) {
		this.groupPage = groupPage;
	}
	 
	
	
}
