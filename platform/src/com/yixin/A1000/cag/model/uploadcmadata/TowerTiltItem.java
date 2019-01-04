/**
 * 
 */
package com.yixin.A1000.cag.model.uploadcmadata;

import java.util.ArrayList;
import java.util.List;

/**
 * 上传监测数据：杆塔倾斜数据项名称。用于
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
public class TowerTiltItem extends BaseUploadCMADataItem {

	/** 倾斜度 */
	public static final String INCLINATION = "Inclination";

	/** 顺线倾斜度 */
	public static final String INCLINATION_X = "Inclination_X";

	/** 横向倾斜度 */
	public static final String INCLINATION_Y = "Inclination_Y";

	/** 顺线倾斜角 */
	public static final String ANGLE_X = "Angle_X";

	/** 横向倾斜角 */
	public static final String ANGLE_Y = "Angle_Y";

	/** 数据项名称集 */
	public static final List<String> items = new ArrayList<String>();

	static {
		items.add(INCLINATION);
		items.add(INCLINATION_X);
		items.add(INCLINATION_Y);
		items.add(ANGLE_X);
		items.add(ANGLE_Y);
	}

	/**
	 * 构造方法
	 * 
	 * @param code
	 *            数据项名称
	 * @param value
	 *            数据项值
	 */
	public TowerTiltItem(String code, String value, String alarm) {
		this.code = code;
		this.value = value;
		this.alarm = alarm;
		if (INCLINATION.equals(this.code)) { // 倾斜度
			this.name = "倾斜度";
			this.valueType = Double.class;
			this.minInclusive = null;
			this.maxInclusive = null;
			this.min = null;
			this.max = null;
			this.pos = 1;
			this.unit = "mm/m";
			this.desc = "精确到小数点后1位";
		} else if (INCLINATION_X.equals(this.code)) { // 顺线倾斜度
			this.name = "顺线倾斜度";
			this.valueType = Double.class;
			this.minInclusive = null;
			this.maxInclusive = null;
			this.min = null;
			this.max = null;
			this.pos = 1;
			this.unit = "mm/m";
			this.desc = "精确到小数点后1位";
		} else if (INCLINATION_Y.equals(this.code)) { // 横向倾斜度
			this.name = "横向倾斜度";
			this.valueType = Double.class;
			this.minInclusive = null;
			this.maxInclusive = null;
			this.min = null;
			this.max = null;
			this.pos = 1;
			this.unit = "mm/m";
			this.desc = "精确到小数点后1位";
		} else if (ANGLE_X.equals(this.code)) { // 顺线倾斜角
			this.name = "顺线倾斜角";
			this.valueType = Double.class;
			this.minInclusive = null;
			this.maxInclusive = null;
			this.min = null;
			this.max = null;
			this.pos = 2;
			this.unit = "°";
			this.desc = "精确到小数点后2位";
		} else if (ANGLE_Y.equals(this.code)) { // 横向倾斜角
			this.name = "横向倾斜角";
			this.valueType = Double.class;
			this.minInclusive = null;
			this.maxInclusive = null;
			this.min = null;
			this.max = null;
			this.pos = 2;
			this.unit = "°";
			this.desc = "精确到小数点后2位";
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
