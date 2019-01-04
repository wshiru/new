package com.yixin.framework.login.model;

import java.io.Serializable;

import com.yixin.A1000.archive.model.Sensor;

/**
 * 分组页的显示类
 * 
 * @author 梁立全

 * 2010 06 25
 */

public class DataItem implements Serializable {

	private static final long serialVersionUID = 2216558438814L;
	
	private String name;
	
	private String value;
	
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	 
	
}
