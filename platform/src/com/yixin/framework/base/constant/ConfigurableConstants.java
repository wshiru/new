/*
 * Project platform
 *
 * Classname ConfigurableConstants.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-07-07 10:20
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.base.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件常量类
 * 
 * @version v1.0.0
 * @author 
 */
public class ConfigurableConstants {

	/** 属性集对象 */
    protected static Properties p = new Properties();

    /**
     * 初始化，加载属性文件
     * @param propertyFileName
     */
    protected static void init(String propertyFileName) {
        InputStream in = null;
        try {
            in = ConfigurableConstants.class.getResourceAsStream(propertyFileName);
            if (in != null)
                p.load(in);
        } catch (Exception e) {
        	System.out.println("加载文件 " + propertyFileName + "中的常量失败");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                	System.out.println("出错，文件路径：" +  propertyFileName);
                }
            }
        }
    }

    /**
     * 获取属性值
     * @param key
     * 			键名
     * @return
     */
    public static String getProperty(String key){
    	return p.getProperty(key);
    }
    /**
     * 获取属性值
     * @param key
     * 			键名
     * @param defaultValue
     * 			属性默认值
     * @return
     */
    public static String getProperty(String key, String defaultValue) {
        return p.getProperty(key, defaultValue); 
    }
}
