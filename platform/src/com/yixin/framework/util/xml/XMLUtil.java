/*
 * Project ca2000
 *
 * Class XMLUtil.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午09:33:52
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.util.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

/**
 * XML工具类。
 * 
 * @version v1.0.0
 * @author 
 */
public class XMLUtil {

	/** 日志记录器 */
	private static final Logger logger = Logger.getLogger(XMLUtil.class);

	/**
	 * 将指定的字符串strXMLParams转换成XML格式。转换出错时返回null。
	 * 
	 * @param strXMLParams
	 *            要进行转换的字符串
	 * @return 转换出错时返回null。否则返回Document对象
	 */
	public static Document parseToXML(String strXMLParams) {
		Document doc = null;
		try {	
			doc = DocumentHelper.parseText(strXMLParams.trim()); // 将字符串转换成XML
			doc.setXMLEncoding("UTF-8");
		} catch (DocumentException ex) {
			logger.debug("Could not converte to xml. Caused by:", ex);
			doc = null;
		}
		return doc;
	}

	/**
	 * 根据指定的XML Schema验证XML格式。采用W3C的命名空间XMLConstants.W3C_XML_SCHEMA_NS_URI。
	 * 
	 * @param xsd
	 *            XML Schema文件
	 * @param xml
	 *            要进行验证的XML文件
	 * @return true - 验证成功； false - 验证失败；
	 * @see javax.xml.XMLConstants#W3C_XML_SCHEMA_NS_URI
	 */
	public static boolean validate(File xsd, File xml) {
		boolean flag = true;
		try {
			InputStream is = new FileInputStream(xsd);
			Source schemaFile = new StreamSource(is);
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xml));
		} catch (Exception ex) {
			logger.debug("Validate XML failed. Caused by:", ex);
			flag = false;
		}
		return flag;
	}

	/**
	 * 根据指定的XML Schema验证XML格式。采用W3C的命名空间XMLConstants.W3C_XML_SCHEMA_NS_URI。
	 * 
	 * @param xsd
	 *            XML Schema字符串
	 * @param xml
	 *            要进行验证的XML字符串
	 * @return true - 验证成功； false - 验证失败；
	 * @see javax.xml.XMLConstants#W3C_XML_SCHEMA_NS_URI
	 */
	public static boolean validate(String xsd, String xml) {
		boolean flag = true;
		try {
			InputStream is = new ByteArrayInputStream(xsd.trim().getBytes());
			Source schemaFile = new StreamSource(is);
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new StringReader(xml.trim())));
		} catch (Exception ex) {
			logger.debug("Validate XML failed. Caused by:", ex);
			flag = false;
		}
		return flag;
	}

	/**
	 * 根据指定的XML Schema验证XML格式。采用W3C的命名空间XMLConstants.W3C_XML_SCHEMA_NS_URI。
	 * 
	 * @param xsd
	 *            XML Schema文件
	 * @param xml
	 *            要进行验证的XML字符串
	 * @return true - 验证成功； false - 验证失败；
	 * @see javax.xml.XMLConstants#W3C_XML_SCHEMA_NS_URI
	 */
	public static boolean validate(File xsd, String xml) {
		boolean flag = true;
		try {
			InputStream is = new FileInputStream(xsd);
			Source schemaFile = new StreamSource(is);
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new StringReader(xml.trim())));
		} catch (Exception ex) {
			logger.debug("Validate XML failed. Caused by:", ex);
			flag = false;
		}
		return flag;
	}

	/**
	 * 根据指定的XML Schema验证XML格式。
	 * 
	 * @param strXSD
	 *            XML Schema InputStream流
	 * @param xml
	 *            要进行验证的XML字符串
	 * @return true - 验证成功； false - 验证失败；
	 */
	public static boolean validate(InputStream xsd, String xml) {
		boolean flag = true;
		try {
			Source schemaFile = new StreamSource(xsd);
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new StringReader(xml.trim())));
		} catch (Exception ex) {
			logger.debug("Validate XML failed. Caused by:", ex);
			flag = false;
		}
		return flag;
	}

	/**
	 * 根据指定的XML Schema验证XML格式。采用W3C的命名空间XMLConstants.W3C_XML_SCHEMA_NS_URI。
	 * 
	 * @param xsd
	 *            XML Schema InputStream流
	 * @param strXML
	 *            要进行验证的XML InputStream流
	 * @return true - 验证成功； false - 验证失败；
	 * @see javax.xml.XMLConstants#W3C_XML_SCHEMA_NS_URI
	 */
	public static boolean validate(InputStream xsd, InputStream xml) {
		boolean flag = true;
		try {
			Source schemaFile = new StreamSource(xsd);
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xml));
		} catch (Exception ex) {
			logger.debug("Validate XML failed. Caused by:", ex);
			flag = false;
		}
		return flag;
	}
}
