/**
 * 
 */
package com.yixin.A1000.cag.model.uploadcmadata;

/**
 * 监测类型编码.用于[上传监测数据].
 * 
 * <pre style="background-color:lightgray; width:0; overflow:display; border:1px solid gray;">
 * &lt;request&gt;
 * 	&lt;monitordata cmaid="" datanodenum=""&gt;
 * 		&lt;datanode sensorid=""&gt;
 * 			&lt;type /&gt;
 * 			&lt;equipmentid /&gt;
 * 			&lt;timestamp /&gt;
 * 	  		&lt;attrs&gt;
 * 				&lt;attr name="" value="" alarm="" /&gt;
 * 				&lt;attr name="" value="" alarm="" /&gt;
 * 				...
 * 	  		&lt;attrs /&gt;
 * 		&lt;/datanode&gt;
 * 	&lt;/monitordata&gt;
 * &lt;/request&gt;
 * </pre>
 * 
 * 中的&lt;type /&gt;标签的值
 * 
 * @author 2zc
 * 
 */
public enum MonitoringTypeCode {

	/** 杆塔倾斜 */
	TOWER_TILT("012001"),

	/** 导线弧垂 */
	WIRE_SAG("013001"),

	/** 导线温度 */
	WIRE_TEMPERATURE("013002"),

	/** 微风振动 */
	ASOLIAN_VIBRATION("013003"),

	/** 风偏 */
	MONSOON("01300402"),

	/** 覆冰 */
	ICE("013005"),

	/** 舞动 */
	DANCING("013006"),

	/** 现场污秽度 */
	DEGREEE_OF_SITE_CONTAMINATION("014001"),

	/** 微气象 */
	WEATHER("018001"),

	/** 图像/视频 */
	IMAGE_VIDEO("018002"),

	/** 相间风偏 */
	WHITEMONSOON("013004"),

	/** 绝缘子串风偏 */
	INSULATORMONSOONS("01300401");

	/** 命令类型 */
	private String code;

	/**
	 * 构造方法
	 * 
	 * @param code
	 *            监测类型编码
	 */
	private MonitoringTypeCode(String code) {
		this.code = code;
	}

	/**
	 * 获取 命令类型
	 * 
	 * @return 命令类型
	 */
	public String getCode() {
		return code;
	}
}
