/**
 * 
 */
package com.yixin.A1000.cag.model.uploadcmadata;

import java.util.ArrayList;
import java.util.List;

/**
 * 上传监测数据：导线弧垂数据项名称。用于
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
public class WireSagItem extends BaseUploadCMADataItem {

	/** 导线弧垂 */
	public static final String CONDUCTOR_SAG = "Conductor_Sag";

	/** 导线对地距离 */
	public static final String TOGROUND_DISTANCE = "ToGround_Distance";

	/** 数据项名称集 */
	public static final List<String> items = new ArrayList<String>();

	static {
		items.add(CONDUCTOR_SAG);
		items.add(TOGROUND_DISTANCE);
	}

	/**
	 * 构造方法
	 * 
	 * @param code
	 *            数据项名称
	 * @param value
	 *            数据项值
	 */
	public WireSagItem(String code, String value, String alarm) {
		this.code = code;
		this.value = value;
		this.alarm = alarm;
		if (CONDUCTOR_SAG.equals(this.code)) { // 导线弧垂
			this.name = "导线弧垂";
			this.valueType = Double.class;
			this.minInclusive = null;
			this.maxInclusive = null;
			this.min = null;
			this.max = null;
			this.pos = 3;
			this.unit = "m";
			this.desc = "精确到小数点后3位";
		} else if (TOGROUND_DISTANCE.equals(this.code)) { // 导线对地距离
			this.name = "导线对地距离";
			this.valueType = Double.class;
			this.minInclusive = 0;
			this.maxInclusive = 360;
			this.min = null;
			this.max = null;
			this.pos = 3;
			this.unit = "m";
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
