/*
 * Project platform
 *
 * Classname StringUtils.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-6-1 下午02:40:10
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;


/**
 * 系统配置信息类

 * @author 梁立全

 *
 */
public class SystemInfo {
	
	/** 系统类型**/
	private Integer  systemType;

	/**系统名称**/
	private String  systemName;
	
	/** 系统风格 显示主题 **/
	private String  systemTheme;
	
	
	/**
	 * 返回系统类型
	 * @return
	 */
	public Integer getSystemType() {
		Integer ret = 1;
		String type = getPropertieFileInfo("system.type");
		if ( !type.equals("") ){
			ret = Integer.valueOf(type);
		}
		this.systemType = ret;
		return systemType;
	}
    
	/**
	 * 设置系统类型
	 * @param systemType
	 */
	public void setSystemType(Integer systemType) {
		this.systemType = systemType;
	}

	/**
	 * 返回系统名称
	 * @return
	 */
	public String getSystemName() {
		String name = getPropertieFileInfo("system.name");
		this.systemName = name;
		return systemName;
	}
	
    
	/**
	 * 设置系统名称
	 * @param systemName
	 */
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	/**
	 * 返回系统主题
	 * @return
	 */
	public String getSystemTheme() {
		
		String theme = getPropertieFileInfo("system.theme");
		if ( theme == null ) {
			theme ="green";
		}
		this.systemTheme = theme;
		return systemTheme;
	}

	/**
	 * 设置系统主题
	 * @param systemTheme
	 */
	public void setSystemTheme(String systemTheme) {
		this.systemTheme = systemTheme;
	}
	
	
	
	/**
     * 获取系统配置文件信息
     * @param propertie
     * @return
     */
	private  String  getPropertieFileInfo(String propertie){
		String info = "";
		Properties pro = new Properties();
		File file;
		try {
			file = new File(this.getClass().getResource("/").toURI().getPath() + "system-config.properties");
		    FileInputStream fis = null;
		    try {
		    	fis = new FileInputStream(file);
		   		pro.load(fis);
		   		info = pro.getProperty(propertie);
		   	} catch (FileNotFoundException ex) {
		   	} catch (IOException ex) {
		      }
		  } catch (URISyntaxException e) {
			
	     }
		return info;
	}
	
	
}
