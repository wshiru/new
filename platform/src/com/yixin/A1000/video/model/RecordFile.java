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

/**
 * 录像文件
 * 
 * @version v1.0.0
 * @author 梁立全
 */

@Entity
@Table(name = "T_RecordFiles")
public class RecordFile {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -4692156433455947L;

	/** 录像计划Id */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String recordFileId;
	
	/** 视频服务器ID */
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH }, optional = true)
	@JoinColumn(name="videoServerId")
	private VideoServer videoServer;	
	
	/** 文件名 */
	private String fileName;
	
	/** 文件大小 */
	private Integer fileSize;
	
	/** 文件文件类型 */
	private Integer fileType;
	
	/** 开始录像时间 */
	private Date startTime;
	
	/** 结束录像时间 */
	private Date endTime;
	
	/** 创建时间 */
	private Date createTime;

	public String getRecordFileId() {
		return recordFileId;
	}

	public void setRecordFileId(String recordFileId) {
		this.recordFileId = recordFileId;
	}

	public VideoServer getVideoServer() {
		return videoServer;
	}

	public void setVideoServer(VideoServer videoServer) {
		this.videoServer = videoServer;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
	}
		 
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
