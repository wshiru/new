/*
 * Project platform
 *
 * Class BaseUploadCMADataItem.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-16 上午11:04:49
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.model.uploadcmadata;

import java.util.regex.Pattern;

import com.yixin.A1000.cag.model.XMLBusinessKeys;

/**
 * TODO(类的描述信息)
 * 
 * @version v1.0.0
 * @author 
 */
public abstract class BaseUploadCMADataItem {

	/** 数据项名称 */
	protected String code;

	/** 数据项中文名称 */
	protected String name;

	/** 数据项值 */
	protected String value;

	/** 值value的类型 */
	protected Class<?> valueType;

	/** 告警属性 */
	protected String alarm;

	/** 值域：最小值（包含） */
	protected Number minInclusive;

	/** 值域：最大值（包含） */
	protected Number maxInclusive;

	/** 值域：最小值（不包含） */
	protected Number min;

	/** 值域：最大值（不包含） */
	protected Number max;

	/** 小数点位数 */
	protected int pos;

	/** 单位 */
	protected String unit;

	/** 描述 */
	protected String desc;

	/**
	 * 构造方法
	 * 
	 */
	protected BaseUploadCMADataItem() {
	}

	/**
	 * 值域校验。true - 校验成功； false - 校验失败；
	 * 
	 * @return true - 校验成功； false - 校验失败；
	 */
	protected boolean validateRange() {
		boolean result = true;
		if (null != this.min && Double.parseDouble(this.value) <= this.min.doubleValue()) {
			result = false;
		} else if (null != max && Double.parseDouble(this.value) >= this.max.doubleValue()) {
			result = false;
		} else if (null != this.minInclusive && Double.parseDouble(this.value) < this.minInclusive.doubleValue()) {
			result = false;
		} else if (null != maxInclusive && Double.parseDouble(this.value) > this.maxInclusive.doubleValue()) {
			result = false;
		}
		return result;
	}

	/**
	 * 格式校验。true - 校验成功； false - 校验失败；
	 * 
	 * @return true - 校验成功； false - 校验失败；
	 */
	protected boolean validateDataFormat() {
		return this.validateDecimalFormat(this.value.toString(), this.pos);
	}

	/**
	 * 使用正则表达式校验str是否满足数字格式.
	 * 
	 * @param str
	 *            要进行校验的数字型字符串
	 * @param pos
	 *            数字的小数点位数
	 * @return true - 成功; false - 失败;
	 */
	protected boolean validateDecimalFormat(String str, int pos) {
		String regex = "-?(\\d)+";
		if (pos > 0) {
			regex = regex + "-?(.(\\d{1," + pos + "}))?";
		}
		return Pattern.matches(regex, str);
	}

	/**
	 * 0102002 调用服务方法出错，数据无法转换至对应格式
	 * 
	 * @return true - 校验成功；false - 校验失败；
	 */
	public boolean validateConversion() {
		if (null == this.valueType) {
			return false;
		}
		boolean result = true;
		try {
			if (this.valueType == Integer.class) {
				Integer.parseInt(this.value);
			} else if (this.valueType == Double.class) {
				Double.parseDouble(this.value);
			} else if (this.valueType == Float.class) {
				Float.parseFloat(this.value);
			} else if (this.valueType == Long.class) {
				Long.parseLong(this.value);
			} else if (this.valueType == Short.class) {
				Short.parseShort(this.value);
			} else if (this.valueType == Byte.class) {
				Byte.parseByte(this.value);
			}
		} catch (NumberFormatException ex) {
			result = false;
		}
		return result;
	}

	/**
	 * 0103001 调用服务方法出错，监测类型与监测参数不一致
	 * 
	 * @return true - 校验成功；false - 校验失败；
	 */
	// public boolean validateParamBelongToItemType() {
	// boolean result = true;
	// return result;
	// }

	/**
	 * 0103002 调用服务方法出错，监测类型不存在
	 * 
	 * @return true - 校验成功；false - 校验失败；
	 */
	// public boolean validateItemTypeExist() {
	// boolean result = true;
	// return result;
	// }
	/**
	 * 0103003 调用服务方法出错，监测参数不存在
	 * 
	 * @return true - 校验成功；false - 校验失败；
	 */
	public abstract boolean validateParamExist();

	/**
	 * 0103004 调用服务方法出错，监测参数缺失
	 * 
	 * @return true - 校验成功；false - 校验失败；
	 */
	// public boolean validateParamMissing() {
	// boolean result = true;
	// return result;
	// }

	/**
	 * 0103005 调用服务方法出错，数据不符合业务规范。只校验了值域、小数位数、alarm属性值。如果有其他校验，请重写该方法。
	 * 
	 * @return true - 校验成功；false - 校验失败；
	 */
	public boolean validateBusiness() {
		//this.validateDataFormat() && this.validateRange() &&
		return  this.validateAlarn();
	}

	/**
	 * 校验上传监测数据的alarm属性
	 * 
	 * @return 校验成功返回 null,否则返回ValidateResult对象
	 */
	protected boolean validateAlarn() {
		return (XMLBusinessKeys.UPLOADCMADATA_ATTR_ALARM_TRUE.equals(this.alarm) || XMLBusinessKeys.UPLOADCMADATA_ATTR_ALARM_FALSE
				.equals(this.alarm)) ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (null == this.min && null == this.max && null == this.minInclusive && null == this.maxInclusive) {
			sb.append("[未规定]");
		} else {
			if (null != this.min) {
				sb.append("(" + this.min);
			}
			if (null != this.max) {
				sb.append("," + this.max + ")");
			}
			if (null != this.minInclusive) {
				sb.append("[" + this.minInclusive);
			}
			if (null != this.maxInclusive) {
				sb.append("," + this.maxInclusive + "]");
			}
		}
		return "name=[" + this.code + "][" + this.name + "]；value=[" + this.value + "]；alarm=[" + this.alarm + "]；值域"
				+ sb.toString() + "；单位[" + this.unit + "]；" + this.desc + "；";
	}
}
