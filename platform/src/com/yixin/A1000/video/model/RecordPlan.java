/*
 * Project platform
 *
 * Class OnlineDeviceStatus.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-12 下午03:10:01
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.video.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yixin.A1000.archive.model.Sensor;

/**
 * 视频录像计划
 * 
 * @version v1.0.0
 * @author 梁立全
 */

@Entity
@Table(name = "T_RecordPlan")
public class RecordPlan {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -4692156433455947L;

	/** 录像计划Id */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String recordPlanId;
	
	/** 视频服务器ID */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name="videoServerId")
	private VideoServer videoServer;		

	/** 计划开始执行时间 */
	private Date startTime;
	
	/** 通道号 */
	private Integer channelNo;
	
	/** 操作类型 0录像，1截图*/
	private Integer operatorType;
	
	
	/** 打开电源时间 单位分钟 */
	private Integer openTime;
	
	/** 录像时间 单位分钟 */
	private Integer recordTime;	
	
	/** 载图张数 */
	private Integer pictureNum;
	
	/** 预置位 */
	private Integer presetting;	
	

	public String getRecordPlanId() {
		return recordPlanId;
	}

	public void setRecordPlanId(String recordPlanId) {
		this.recordPlanId = recordPlanId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}

	public Integer getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Integer recordTime) {
		this.recordTime = recordTime;
	}

	public Integer getPictureNum() {
		return pictureNum;
	}

	public void setPictureNum(Integer pictureNum) {
		this.pictureNum = pictureNum;
	}

	/**
	 * @param presetting the presetting to set
	 */
	public void setPresetting(Integer presetting) {
		this.presetting = presetting;
	}

	/**
	 * @return the presetting
	 */
	public Integer getPresetting() {
		return presetting;
	}

	/**
	 * @param videoServer the videoServer to set
	 */
	public void setVideoServer(VideoServer videoServer) {
		this.videoServer = videoServer;
	}

	/**
	 * @return the videoServer
	 */
	public VideoServer getVideoServer() {
		return videoServer;
	}

	/**
	 * @param openTime the openTime to set
	 */
	public void setOpenTime(Integer openTime) {
		this.openTime = openTime;
	}

	/**
	 * @return the openTime
	 */
	public Integer getOpenTime() {
		return openTime;
	}
	/**
	 * @param videoServer the channelNo to set
	 */
	public void setChannelNo(Integer channelNo) {
		this.channelNo = channelNo;
	}
	/**
	 * @return the channelNo
	 */
	public Integer getChannelNo() {
		return channelNo;
	}
	 
	
}
