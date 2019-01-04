/*
 * Project platform
 *
 * Class PacketType.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-8-30 上午11:39:34
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.comm.protocol;

/**
 * 报文类型枚举类
 * 
 * @version v1.0.0
 * @author 
 */
public enum PacketType {

	SAMPLING_WEATHER("0x01"), // 气象环境类数据报
	// SAMPLING_("0x02～0x0B"), // 气象类数据报预留字段
	SAMPLING_TOWERTILT("0x0C"), // 杆塔倾斜数据报
	// SAMPLING_("0x0D～0x1D"), // 杆塔类数据报预留字段
	// SAMPLING_("0x1E"), // 导地线微风振动特征量数据报
	// SAMPLING_("0x1F"), // 导地线微风振动波形信号数据报
	// SAMPLING_("0x20"), // 导线弧垂数据报
	// SAMPLING_("0x21"), // 导线温度数据报
	SAMPLING_ICETHINCKNESS("0x22"), // 覆冰及不均衡张力差数据报
	// SAMPLING_("0x23"), // 导线风偏数据报
	// SAMPLING_("0x24"), // 导地线舞动特征量数据报
	// SAMPLING_("0x25"), // 导地线舞动轨迹数据报
	// SAMPLING_("0x26～0x46"), // 导地线类数据报预留字段
	// SAMPLING_("0x47～0x5B"), // 金具类数据报预留字段
	SAMPLING_CONTAMINATION("0x5C"), // 现场污秽度数据报
	// SAMPLING_("0x5E～0x6E"), // 绝缘子类数据报预留字段
	// SAMPLING_("0x6F～0x82"), // 杆塔基础类数据报预留字段
	// SAMPLING_("0x83～0x96"), // 附属设施类数据报预留字段
	// SAMPLING_("0x97～0xA0"), // 通道环境类数据报预留字段
	SAMPLING_LANDSLIDE("0x5D"), // 地质滑坡数据报
	SAMPLING_ALLTYPE("0xFF"), // 地质滑坡数据报
	
	CONTROL_DEVICE_TIME("0xA1"), // 监测装置时间查询/设置
	CONTROL_NETWORK_ADAPTER("0xA2"), // 监测装置网络适配器查询/设置
	CONTROL_DATA("0xA3"), // 上级设备请求数据
	CONTROL_SAMPLING_PARAM("0xA4"), // 监测装置采样参数查询/设置
	CONTROL_MODEL_PARAM("0xA5"), // 模型参数配置信息查询/设置
	CONTROL_ALARM_PARAM("0xA6"), // 报警阈值查询/设置
	CONTROL_MASTER_STATION("0xA7"), // 监测装置指向上位机的信息查询/设置
	// CONTROL_XXX8("0xA8"), // 基本信息查询/设置
	// CONTROL_XXX9("0xA9"), // 远程升级数据报：软件数据报
	// CONTROL_XXX0("0xAA"), // 远程升级数据报：软件数据报下发结束
	// CONTROL_XXXa("0xAB"), // 远程升级数据报：软件数据报补包数据
	CONTROL_DEVICE_ID("0xAC"), // 装置ID查询/设置
	CONTROL_DEVICE_RESET("0xAD"), // 装置复位
	// CONTROL_WAKEUP("0xAE"), // 装置苏醒时间设置
	CONTROL_WEATHER("0xAF"), // 气象参数
	CONTROL_TOWERTILT("0xB0"), // 杆塔倾斜参数
	// CONTROL_("0xB1"), // 导地线微风振动参数
	// CONTROL_("0xB2"), // 导线弧垂参数
	// CONTROL_("0xB3"), // 导线温度参数
	CONTROL_ICETHINCKNESS("0xB4"), // 覆冰参数
	// CONTROL_("0xB5"), // 导线风偏参数
	// CONTROL_("0xB6"), // 导地线舞动参数
	CONTROL_CONTAMINATION("0xB7"), // 现场污秽度参数
	CONTROL_LANDSLIDE("0xB8"), // 地质灾害参数

	// CONTROL_("0xB8～0xC8"), // 控制数据报预留字段

	// IMAGE_XXX1("0xC9"), // 图像采集参数设置
	// IMAGE_XXX2("0xCA"), // 拍照时间表设置
	// IMAGE_XXX3("0xCB"), // 手动请求拍摄照片
	// IMAGE_XXX4("0xCC"), // 采集装置请求上送照片
	// IMAGE_XXX5("0xCD"), // 远程图像数据报
	// IMAGE_XXX6("0xCE"), // 远程图像数据上送结束标记
	// IMAGE_XXX7("0xCF"), // 远程图像补包数据下发
	   IMAGE_CONTROL("0xD0"), // 摄像机远程调节
	// IMAGE_XXX9("0xD1"), // 启动 / 终止摄像视频传输
	// IMAGE_XXX0("0xD2"), // 设置状态监测装置保存的服务器地址
	// IMAGE_XXXm("0xD3"), // 终止状态监测装置与服务器的连接
	// IMAGE_XXXn("0xD4"), // 请求/返回/通知状态监测装置基本信息
	// IMAGE_("0xD5～0xE5"), // 远程图像数据报预留字段

	// WORKSTATUS_XXX1("0xE6"), // 心跳数据报
	// WORKSTATUS_XXX2("0xE7"), // 基本信息报
	// WORKSTATUS_XXX3("0xE8"), // 工作状态报
	// WORKSTATUS_XXX4("0xE9"); // 故障信息报
	// WORKSTATUS_("0xEA～0xFF"); // 其他报文预留字段
	   ERROR("0xFF");
	/** 16进制码 */
	private String hexCode;

	/**
	 * 构造方法
	 * 
	 * @param hexCode
	 *            16进制码
	 */
	private PacketType(String hexCode) {
		this.hexCode = hexCode;
	}

	/**
	 * 获取 16进制码
	 * 
	 * @return 16进制码
	 */
	public String getHexCode() {
		return hexCode;
	}
}
