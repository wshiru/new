/*
 * Project ca2000
 *
 * Class Base64Util.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午09:35:42
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.util.base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.misc.BASE64Decoder;

/**
 * Base64编码、解码的工具类
 * 
 * @version v1.0.0
 * @author 
 */
public class Base64Util {

	/**
	 * 将文件编码成Base64。当指定文件为null时，返回空字符串。
	 * 
	 * @param file
	 *            要进行编码的文件
	 * @return Base64编码后的字符串
	 * @exception RuntimeException
	 *                产生FileNotFoundException异常时
	 * @exception RuntimeException
	 *                产生IOException异常时
	 */
	public static String encodeBase64FromFile(File file) {
		if (null == file) {
			return "";
		}
		byte[] preFileByte = new byte[20480]; // 预分配存放文件的字节数组
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			int fileByteLen = 0; // 文件字节的实际长度
			int len = -1;
			byte[] b = new byte[1024];
			while ((len = is.read(b)) != -1) {
				fileByteLen += len;

				/* 以2倍长度，自增预分配存放文件的字节数组 */
				if (preFileByte.length < fileByteLen) {
					byte[] temp = new byte[preFileByte.length * 2];
					System.arraycopy(preFileByte, 0, temp, 0, preFileByte.length);
					preFileByte = temp;
				}
				System.arraycopy(b, 0, preFileByte, fileByteLen - len, len);
			}
			byte[] fileByte = new byte[fileByteLen]; // 文件的实际字节
			System.arraycopy(preFileByte, 0, fileByte, 0, fileByteLen);
			preFileByte = fileByte;
		} catch (FileNotFoundException ex) {
			throw new RuntimeException(ex);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException ex) {
					throw new RuntimeException(ex.getCause());
				}
			}
		}
		return new String(org.apache.xmlbeans.impl.util.Base64.encode(preFileByte));
	}

	/**
	 * 对字符串进行Base64解码。如果参数为null时，则返回null。
	 * 
	 * @param base64Str
	 *            要进行解码的字符串
	 * @return 解码后的字节数组
	 * @exception RuntimeException
	 *                解码过程中产生IOException异常
	 */
	public static byte[] decodeBase64ToBytes(String base64Str) {
		if (null == base64Str) {
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(base64Str);
			return b;
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
}
