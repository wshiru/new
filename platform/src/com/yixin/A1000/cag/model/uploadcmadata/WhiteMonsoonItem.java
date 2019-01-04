/**
 * 
 */
package com.yixin.A1000.cag.model.uploadcmadata;

import java.util.ArrayList;
import java.util.List;

/**
 * 上传监测数据：相间风偏数据项名称。用于
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
 * 中的&lt;attr /&gt;标签中name属性的值
 * 
 * @author 2zc
 * 
 */
public class WhiteMonsoonItem extends BaseUploadCMADataItem {

	/** 风偏角 */
	public static final String WINDAGE_YAW_ANGLE = "Windage_Yaw_Angle";

	/** 偏斜角 */
	public static final String DEFLECTION_ANGLE = "Deflection_Angle";

	/** 最小电气间隙 */
	public static final String LEAST_CLEARANCE = "Least_Clearance";

	/** 数据项名称集 */
	public static final List<String> items = new ArrayList<String>();

	static {
		items.add(WINDAGE_YAW_ANGLE);
		items.add(DEFLECTION_ANGLE);
		items.add(LEAST_CLEARANCE);
	}

	/**
	 * 构造方法
	 * 
	 * @param code
	 *            数据项名称
	 * @param value
	 *            数据项值
	 */
	public WhiteMonsoonItem(String code, String value, String alarm) {
		this.code = code;
		this.value = value;
		this.alarm = alarm;
		if (WINDAGE_YAW_ANGLE.equals(this.code)) { // 风偏角
			this.name = "风偏角";
			this.valueType = Double.class;
			this.minInclusive = null;
			this.maxInclusive = null;
			this.min = null;
			this.max = null;
			this.pos = 2;
			this.unit = "°";
			this.desc = "精确到小数点后2位";
		} else if (DEFLECTION_ANGLE.equals(this.code)) { // 偏斜角
			this.name = "偏斜角";
			this.valueType = Double.class;
			this.minInclusive = 0;
			this.maxInclusive = 360;
			this.min = null;
			this.max = null;
			this.pos = 2;
			this.unit = "°";
			this.desc = "精确到小数点后2位";
		} else if (LEAST_CLEARANCE.equals(this.code)) { // 最小电气间隙
			this.name = "最小电气间隙";
			this.valueType = Double.class;
			this.minInclusive = null;
			this.maxInclusive = null;
			this.min = null;
			this.max = null;
			this.pos = 3;
			this.unit = "";
			this.desc = "精确到小数点后3位";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yixin.ca2000.cag.model.uploadcmadata.BaseUploadCMADataItem#
	 * validateParamExist()
	 */
	@Override
	public boolean validateParamExist() {
		return items.contains(this.code) ? true : false;
	}
}
