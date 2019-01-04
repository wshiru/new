/*
 * Project ca2000
 *
 * Class XMLBusinessKeys.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午10:58:06
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.model;

/**
 * TODO(类的描述信息)
 * 
 * @version v1.0.0
 * @author 
 */
public class XMLBusinessKeys {

	// ==============================================================================================
	// 上传心跳信息uploadCMAHeartbeatInfo
	/**
	 * 上传心跳信息uploadCMAHeartbeatInfo：
	 * 
	 * <pre style="background-color:lightgray; width:0; overflow:display; border:1px solid gray;">
	 * &lt;request&gt;
	 * 	&lt;cma id=""&gt;
	 * 		&lt;ip /&gt;
	 * 		&lt;curtime /&gt;
	 * 		&lt;batteryvoltage /&gt;
	 * 		&lt;operationtemperature /&gt;
	 * 		&lt;floatingcharge&gt;CHARGE&lt;/floatingcharge&gt;
	 * 	&lt;/cma&gt;
	 * &lt;/request&gt;
	 * </pre>
	 * 
	 * 中&lt;floatingcharge /&gt;的值“CHARGE”
	 */
	public static final String UPLOADCMAHEARTBEATINFO_CMA_FLOATINGCHARGE_CHARGE = "CHARGE";

	/**
	 * 上传心跳信息uploadCMAHeartbeatInfo：
	 * 
	 * <pre style="background-color:lightgray; width:0; overflow:display; border:1px solid gray;">
	 * &lt;request&gt;
	 * 	&lt;cma id=""&gt;
	 * 		&lt;ip /&gt;
	 * 		&lt;curtime /&gt;
	 * 		&lt;batteryvoltage /&gt;
	 * 		&lt;operationtemperature /&gt;
	 * 		&lt;floatingcharge&gt;DISCHARGE&lt;/floatingcharge&gt;
	 * 	&lt;/cma&gt;
	 * &lt;/request&gt;
	 * </pre>
	 * 
	 * 中&lt;floatingcharge /&gt;的值“DISCHARGE”
	 */
	public static final String UPLOADCMAHEARTBEATINFO_CMA_FLOATINGCHARGE_DISCHARGE = "DISCHARGE";

	/**
	 * 上传心跳信息uploadCMAHeartbeatInfo：
	 * 
	 * <pre style="background-color:lightgray; width:0; overflow:display; border:1px solid gray;">
	 * &lt;request&gt;
	 * 	&lt;sensors&gt;
	 * 		&lt;sensor id=""&gt;
	 * 			&lt;status&gt;NORMAL&lt;/status&gt;
	 * 			&lt;batteryvoltage /&gt;
	 * 			&lt;operationtemperature /&gt;
	 * 			&lt;floatingcharge /&gt;
	 * 		&lt;/sensor&gt;
	 * 	&lt;/sensors&gt;
	 * &lt;/request&gt;
	 * </pre>
	 * 
	 * 中&lt;status /&gt;的值“NORMAL”
	 */
	public static final String UPLOADCMAHEARTBEATINFO_SENSOR_STATUS_NORMAL = "NORMAL";

	/**
	 * 上传心跳信息uploadCMAHeartbeatInfo：
	 * 
	 * <pre style="background-color:lightgray; width:0; overflow:display; border:1px solid gray;">
	 * &lt;request&gt;
	 * 	&lt;sensors&gt;
	 * 		&lt;sensor id=""&gt;
	 * 			&lt;status&gt;BREAK&lt;/status&gt;
	 * 			&lt;batteryvoltage /&gt;
	 * 			&lt;operationtemperature /&gt;
	 * 			&lt;floatingcharge /&gt;
	 * 		&lt;/sensor&gt;
	 * 	&lt;/sensors&gt;
	 * &lt;/request&gt;
	 * </pre>
	 * 
	 * 中&lt;status /&gt;的值“BREAK”
	 */
	public static final String UPLOADCMAHEARTBEATINFO_SENSOR_STATUS_BREAK = "BREAK";

	/**
	 * 上传心跳信息uploadCMAHeartbeatInfo：
	 * 
	 * <pre style="background-color:lightgray; width:0; overflow:display; border:1px solid gray;">
	 * &lt;request&gt;
	 * 	&lt;sensors&gt;
	 * 		&lt;sensor id=""&gt;
	 * 			&lt;status /&gt;
	 * 			&lt;batteryvoltage /&gt;
	 * 			&lt;operationtemperature /&gt;
	 * 			&lt;floatingcharge&gt;CHARGE&lt;/floatingcharge&gt;
	 * 		&lt;/sensor&gt;
	 * 	&lt;/sensors&gt;
	 * &lt;/request&gt;
	 * </pre>
	 * 
	 * 中&lt;floatingcharge /&gt;的值“CHARGE”
	 */
	public static final String UPLOADCMAHEARTBEATINFO_SENSOR_FLOATINGCHARGE_CHARGE = "CHARGE";

	/**
	 * 上传心跳信息uploadCMAHeartbeatInfo：
	 * 
	 * <pre style="background-color:lightgray; width:0; overflow:display; border:1px solid gray;">
	 * &lt;request&gt;
	 * 	&lt;sensors&gt;
	 * 		&lt;sensor id=""&gt;
	 * 			&lt;status /&gt;
	 * 			&lt;batteryvoltage /&gt;
	 * 			&lt;operationtemperature /&gt;
	 * 			&lt;floatingcharge&gt;DISCHARGE&lt;/floatingcharge&gt;
	 * 		&lt;/sensor&gt;
	 * 	&lt;/sensors&gt;
	 * &lt;/request&gt;
	 * </pre>
	 * 
	 * 中&lt;floatingcharge /&gt;的值“CHARGE”
	 */
	public static final String UPLOADCMAHEARTBEATINFO_SENSOR_FLOATINGCHARGE_DISCHARGE = "DISCHARGE";

	// ==============================================================================================
	// 上传监测数据uploadCMAData

	/**
	 * 上传心跳信息uploadCMAHeartbeatInfo：
	 * 
	 * <pre style="background-color:lightgray; width:0; overflow:display; border:1px solid gray;">
	 * &lt;request&gt;
	 * 	&lt;sensors&gt;
	 * 		&lt;sensor id=""&gt;
	 * 			&lt;status /&gt;
	 * 			&lt;batteryvoltage /&gt;
	 * 			&lt;operationtemperature /&gt;
	 * 			&lt;floatingcharge&gt;DISCHARGE&lt;/floatingcharge&gt;
	 * 		&lt;/sensor&gt;
	 * 	&lt;/sensors&gt;
	 * &lt;/request&gt;
	 * </pre>
	 * 
	 * 中&lt;floatingcharge /&gt;的值“CHARGE”
	 */
	public static final String UPLOADCMADATA_ATTR_ALARM_TRUE = "TRUE";
	public static final String UPLOADCMADATA_ATTR_ALARM_FALSE = "FALSE";
}