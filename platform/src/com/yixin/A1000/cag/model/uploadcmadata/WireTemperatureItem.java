/**
 * 
 */
package com.yixin.A1000.cag.model.uploadcmadata;

import java.util.ArrayList;
import java.util.List;

/**
 * 上传监测数据：导线温度数据项名称。用于
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
public class WireTemperatureItem extends BaseUploadCMADataItem {

	/** 线温1 */
	public static final String LINE_TEMPERATURE1 = "Line_Temperature1";

	/** 线温2 */
	public static final String LINE_TEMPERATURE2 = "Line_Temperature2";

	/** 数据项名称集 */
	public static final List<String> items = new ArrayList<String>();

	static {
		items.add(LINE_TEMPERATURE1);
		items.add(LINE_TEMPERATURE2);
	}

	/**
	 * 构造方法
	 * 
	 * @param code
	 *            数据项名称
	 * @param value
	 *            数据项值
	 */
	public WireTemperatureItem(String code, String value, String alarm) {
		this.code = code;
		this.value = value;
		this.alarm = alarm;
		if (LINE_TEMPERATURE1.equals(this.code)) { // 线温1
			this.name = "线温1";
			this.valueType = Double.class; 
			this.minInclusive = null;
			this.maxInclusive = null; 
			this.min = null;
			this.max = null;
			this.pos = 1;
			this.unit = "℃";
			this.desc = "精确到小数点后1位";
		} else if (LINE_TEMPERATURE2.equals(this.code)) { // 线温2
			this.name = "线温2"; 
			this.valueType = Double.class;
			this.minInclusive = null;
			this.maxInclusive = null;
			this.min = null;
			this.max = null;
			this.pos = 1;
			this.unit = "℃";
			this.desc = "精确到小数点后1位";
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
