package com.yixin.A1000.base.utils;

/**
 * 采样数据的数据类型类
 * 
 * @author lianglq
 * 
 */
public enum SamplingDataType {
	/** 实时数据 */
	REAL(0, "实时数据"),
	/** 小时数据 */
	HOUR(1, "小时数据"),
	/** 日数据 */
	DAY(2, "日数据"),
	/** 月数据 */
	MONTH(3, "月数据"),
	/** 年数据 */
	YEAR(4, "年数据");

	private Integer datatype;
	private String name;

	private SamplingDataType(int datatype, String name) {
		this.datatype = datatype;
		this.name = name;
	}

	public Integer getDatatype() {
		return datatype;
	}

	public String getName() {
		return name;
	}

}
