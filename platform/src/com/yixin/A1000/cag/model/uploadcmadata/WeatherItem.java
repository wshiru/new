/**
 * 
 */
package com.yixin.A1000.cag.model.uploadcmadata;

import java.util.ArrayList;
import java.util.List;

/**
 * 上传监测数据：微气象数据项名称。用于
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
public class WeatherItem extends BaseUploadCMADataItem {

	/** 10分钟平均风速 */
	public static final String AVERAGE_WINDSPEED_10MIN = "Average_WindSpeed_10min";

	/** 10分钟平均风向 */
	public static final String AVERAGE_WINDDIRECTION_10MIN = "Average_WindDirection_10min";

	/** 最大风速 */
	public static final String MAX_WINDSPEED = "Max_WindSpeed";

	/** 极大风速 */
	public static final String EXTREME_WINDSPEED = "Extreme_WindSpeed";

	/** 标准风速 */
	public static final String STANDARD_WINDSPEED = "Standard_WindSpeed";

	/** 气温 */
	public static final String AIR_TEMPERATURE = "Air_Temperature";

	/** 湿度 */
	public static final String HUMIDITY = "Humidity";

	/** 气压 */
	public static final String AIR_PRESSURE = "Air_Pressure";

	/** 降雨量 */
	public static final String PRECIPITATION = "Precipitation";

	/** 降水强度 */
	public static final String PRECIPITATION_INTENSITY = "Precipitation_Intensity";

	/** 光辐射强度 */
	public static final String RADIATION_INTENSITY = "Radiation_Intensity";

	/** 数据项名称集 */
	public static final List<String> items = new ArrayList<String>();

	static {
		items.add(AVERAGE_WINDSPEED_10MIN);
		items.add(AVERAGE_WINDDIRECTION_10MIN);
		items.add(MAX_WINDSPEED);
		items.add(EXTREME_WINDSPEED);
		items.add(STANDARD_WINDSPEED);
		items.add(AIR_TEMPERATURE);
		items.add(HUMIDITY);
		items.add(AIR_PRESSURE);
		items.add(PRECIPITATION);
		items.add(PRECIPITATION_INTENSITY);
		items.add(RADIATION_INTENSITY);
	}

	/**
	 * 构造方法
	 * 
	 * @param code
	 *            数据项名称
	 * @param value
	 *            数据项值
	 */
	public WeatherItem(String code, String value, String alarm) {
		this.code = code;
		this.value = value;
		this.alarm = alarm;
		if (AVERAGE_WINDSPEED_10MIN.equals(this.code)) { // 10分钟平均风速
			this.name = "10分钟平均风速";
			this.valueType = Double.class;
			this.minInclusive = null;
			this.maxInclusive = null;
			this.min = null;
			this.max = null;
			this.pos = 1;
			this.unit = "m/s";
			this.desc = "精确到小数点后1位";
		} else if (AVERAGE_WINDDIRECTION_10MIN.equals(this.code)) { // 10分钟平均风向
			this.name = "10分钟平均风向";
			this.valueType = Double.class;
			this.minInclusive = null;
			this.maxInclusive = null;
			this.min = null;
			this.max = null;
			this.pos = 0;
			this.unit = "°";
			this.desc = "精确到个位";
		} else if (MAX_WINDSPEED.equals(this.code)) { // 最大风速
			this.name = "最大风速";
			this.valueType = Double.class;
			this.minInclusive = null;
			this.maxInclusive = null;
			this.min = null;
			this.max = null;
			this.pos = 1;
			this.unit = "m/s";
			this.desc = "精确到小数点后1位";
		} else if (EXTREME_WINDSPEED.equals(this.code)) { // 极大风速
			this.name = "极大风速";
			this.valueType = Double.class;
			this.minInclusive = null;
			this.maxInclusive = null;
			this.min = null;
			this.max = null;
			this.pos = 1;
			this.unit = "m/s";
			this.desc = "精确到小数点后1位";
		} else if (STANDARD_WINDSPEED.equals(this.code)) { // 标准风速
			this.name = "标准风速";
			this.valueType = Double.class;
			this.minInclusive = null;
			this.maxInclusive = null;
			this.min = null;
			this.max = null;
			this.pos = 1;
			this.unit = "m/s";
			this.desc = "精确到小数点后1位";
		} else if (AIR_TEMPERATURE.equals(this.code)) { // 气温
			this.name = "气温";
			this.valueType = Double.class;
			this.minInclusive = null;
			this.maxInclusive = null;
			this.min = null;
			this.max = null;
			this.pos = 1;
			this.unit = "℃";
			this.desc = "精确到小数点后1位";
		} else if (HUMIDITY.equals(this.code)) { // 湿度
			this.name = "湿度";
			this.valueType = Double.class;
			this.minInclusive = 0;
			this.maxInclusive = 100;
			this.min = null;
			this.max = null;
			this.pos = 0;
			this.unit = "%RH";
			this.desc = "精确到个位";
		} else if (AIR_PRESSURE.equals(this.code)) { // 气压
			this.name = "气压";
			this.valueType = Double.class;
			this.minInclusive = 550;
			this.maxInclusive = 1060;
			this.min = null;
			this.max = null;
			this.pos = 1;
			this.unit = "Pa";
			this.desc = "精确到小数点后1位";
		} else if (PRECIPITATION.equals(this.code)) { // 降雨量
			this.name = "降雨量";
			this.valueType = Double.class;
			this.minInclusive = null;
			this.maxInclusive = null;
			this.min = null;
			this.max = null;
			this.pos = 1;
			this.unit = "mm";
			this.desc = "精确到小数点后1位";
		} else if (PRECIPITATION_INTENSITY.equals(this.code)) { // 降水强度
			this.name = "降水强度";
			this.valueType = Double.class;
			this.minInclusive = 0;
			this.maxInclusive = 4;
			this.min = null;
			this.max = null;
			this.pos = 1;
			this.unit = "m/s";
			this.desc = "精确到小数点后1位";
		} else if (RADIATION_INTENSITY.equals(this.code)) { // 光辐射强度
			this.name = "光辐射强度";
			this.valueType = Double.class;
			this.minInclusive = 0;
			this.maxInclusive = 1400;
			this.min = null;
			this.max = null;
			this.pos = 0;
			this.unit = "W/m2";
			this.desc = "精确到个位";
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
