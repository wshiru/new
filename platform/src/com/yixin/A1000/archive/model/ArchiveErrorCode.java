/*
 * Project platform
 *
 * Class ArchiveErrorCode.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-21 下午04:47:58
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.archive.model;

/**
 * 档案管理模块业务错误代码
 * 
 * @version v1.0.0
 * @author 
 */
public class ArchiveErrorCode {

	/* ------------------------------------------------------- */
	/* 线路管理：110010000-110019999 */
	/* ------------------------------------------------------- */
	/** 新增/修改：“线路编号”已经存在 */
	public static final int ARCHIVE_LINE_LINECODE_ALREADYEXISTS = 110010000;

	/** 新增/修改：”线路名称”已经存在 */
	public static final int ARCHIVE_LINE_LINENAME_ALREADYEXISTS = 110010001;

	/** 删除：存在杆塔依赖于该线路 */
	public static final int ARCHIVE_LINE_EXISTSTOWERS = 110010002;

	/* ------------------------------------------------------- */
	/* 杆塔类型管理：110020000-110029999 */
	/* ------------------------------------------------------- */
	/** 新增/修改：”杆塔类型编号”已经存在 */
	public static final int ARCHIVE_TOWERTYPE_TOWERTYPECODE_ALREADYEXISTS = 110020000;

	/** 删除：存在杆塔依赖于该杆塔类型 */
	public static final int ARCHIVE_TOWERTYPE_EXISTSTOWERS = 110020001;

	/* ------------------------------------------------------- */
	/* 杆塔管理：110030000-110039999 */
	/* ------------------------------------------------------- */
	/** 新增/修改：”杆塔编号”已经存在 */
	public static final int ARCHIVE_TOWER_TOWERCODE_ALREADYEXISTS = 110030000;

	/** 删除：存在监测装置依赖于该杆塔 */
	public static final int ARCHIVE_TOWER_EXISTSSENSORS = 110030001;

	/* ------------------------------------------------------- */
	/* 监测代理管理：110040000-110049999 */
	/* ------------------------------------------------------- */
	/** 新增/修改：”监测代理编码”已经存在 */
	public static final int ARCHIVE_CMAINFO_CMACODE_ALREADYEXISTS = 110040000;

	/** 删除：存在监测装置依赖于该监测代理 */
	public static final int ARCHIVE_CMAINFO_EXISTSSENSORS = 110030001;

	/* ------------------------------------------------------- */
	/* 监测类型管理：110050000-110059999 */
	/* ------------------------------------------------------- */
	/** 新增/修改：”监测类型编码”已经存在 */
	public static final int ARCHIVE_SENSORTYPE_SENSORTYPECODE_ALREADYEXISTS = 110050000;

	/** 删除：存在监测装置依赖于该监测类型 */
	public static final int ARCHIVE_SENSORTYPE_EXISTSSENSORS = 110050001;

	/* ------------------------------------------------------- */
	/* 监测装置管理：110060000-110069999 */
	/* ------------------------------------------------------- */
	/** 新增/修改：”监测装置编码”已经存在 */
	public static final int ARCHIVE_SENSOR_SENSORCODE_ALREADYEXISTS = 110060000;

	/** 删除：存在数据依赖于该监测装置 */
	public static final int ARCHIVE_SENSOR_EXISTSDATA = 110060001;

	/* ------------------------------------------------------- */
	/* 升级文件管理：110070000-110079999 */
	/* ------------------------------------------------------- */
	/** 新增/修改：相同后缀与版本号的升级文件已经存在 */
	public static final int ARCHIVE_UPGRADEFILE_VERSION_ALREADYEXISTS = 110070000;
	
	/** 删除：存在未下发/已下发的任务依赖于该升级文件 */
	public static final int ARCHIVE_UPGRADEFILE_EXISTUNFINISHEDTASK = 110070001;
	
	/* ------------------------------------------------------- */
	/* 视频平台管理：110080000-110089999 */
	/* ------------------------------------------------------- */
	/** 新增/修改：“视频平台名称”已经存在 */
	public static final int ARCHIVE_VIDEOPLATFORM_NAME_ALREADYEXISTS = 110080000;
	
	/* ------------------------------------------------------- */
	/* 视频平台管理：110090000-110099999 */
	/* ------------------------------------------------------- */
	/** 新增/修改：“视频服务器”已经存在 */
	public static final int ARCHIVE_VIDEOSERVER_SENSOR_ALREADYEXISTS = 110090000;	
	
}
