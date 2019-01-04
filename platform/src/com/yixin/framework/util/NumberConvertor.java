/*
 * Project platform
 *
 * Classname NumberConvertor.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-1 上午10:53:05
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 数字转换工具类。可以在二进制,字节数组,字符,十六进制,BCD编码间转换
 * 
 * @version v1.0.0
 * @author 
 */
public class NumberConvertor {

	/**
	 * 把16进制字符串转换成字节数组
	 * 
	 * @param hex
	 *            要转换的16进制字符串
	 * @return 返回转换后的字节数组
	 */
	public static byte[] hexStringToBytes(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	/**
	 * 把字符c转换成字节表示
	 * 
	 * @param c
	 *            要进行转换的字符
	 * @return 转换后的字节表示
	 */
	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	/**
	 * 把字节数组转换成16进制字符串
	 * 
	 * @param bArray
	 *            要转换的字节数组
	 * @return 返回转换后的16进制字符串
	 * 
	 */
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase() + " ");
		}
		return sb.toString();
	}

	/**
	 * 将字节b转换成16进制字符串
	 * 
	 * @param b
	 *            要转换的字节
	 * @return 返回转换后的16进制字符串
	 */
	public static final String byteToHexString(byte b) {
		StringBuffer sb = new StringBuffer();
		String sTemp = Integer.toHexString(0xFF & b);
		if (sTemp.length() < 2)
			sb.append(0);
		sb.append(sTemp.toUpperCase());
		return sb.toString();
	}

	/**
	 * 把字节数组转换为对象
	 * 
	 * @param bArray
	 *            要转换的字节数组
	 * @return 从流读取的对象
	 * @throws IOException
	 *             任何常规的输入/输出相关的异常
	 * @throws ClassNotFoundException
	 *             找不到序列化对象的类
	 */
	public static final Object bytesToObject(byte[] bArray) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(bArray);
		ObjectInputStream oi = new ObjectInputStream(in);
		Object o = oi.readObject();
		oi.close();
		return o;
	}

	/**
	 * 把可序列化对象转换成字节数组
	 * 
	 * @param s
	 *            要转换的序列化对象
	 * @return 对象的字节表示
	 * @throws IOException
	 *             任何常规的输入/输出相关的异常
	 */
	public static final byte[] objectToBytes(Serializable s) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream ot = new ObjectOutputStream(out);
		ot.writeObject(s);
		ot.flush();
		ot.close();
		return out.toByteArray();
	}

	/**
	 * 把可序列化对象转换成16进制字符串
	 * 
	 * @param s
	 *            要转换的序列化对象
	 * @return 对象的16进制字符串表示
	 * @throws IOException
	 *             任何常规的输入/输出相关的异常
	 */
	public static final String objectToHexString(Serializable s) throws IOException {
		return bytesToHexString(objectToBytes(s));
	}

	/**
	 * 把16进制字符串转换成对象
	 * 
	 * @param hex
	 *            16进制字符串
	 * @return 16进制字符串的对象表示
	 * @throws IOException
	 *             任何常规的输入/输出相关的异常
	 * @throws ClassNotFoundException
	 *             找不到序列化对象的类
	 */
	public static final Object hexStringToObject(String hex) throws IOException, ClassNotFoundException {
		return bytesToObject(hexStringToBytes(hex));
	}

	/**
	 * BCD码转为10进制串(阿拉伯数据)
	 * 
	 * @param bytes
	 *            BCD码
	 * @return 10进制串
	 */
	public static String bcdToString(byte[] bytes) {
		StringBuffer temp = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
			temp.append((byte) (bytes[i] & 0x0f));
		}
		return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
	}

	/**
	 * 10进制串转为BCD码
	 * 
	 * @param asc
	 *            10进制串
	 * @return BCD码
	 */
	public static byte[] stringToBcd(String asc) {
		int len = asc.length();
		int mod = len % 2;
		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}

		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}
		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;
		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}
			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}
			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}

	/**
	 * MD5加密字符串，返回加密后的16进制字符串
	 * 
	 * @param origin
	 *            要加密的字符串
	 * @return 加密后的16进制字符串
	 */
	public static String MD5EncodeToHex(String origin) {
		return bytesToHexString(MD5Encode(origin));
	}

	/**
	 * MD5加密字符串，返回加密后的字节数组
	 * 
	 * @param origin
	 *            要加密的字符串
	 * @return 加密后的字节数组
	 */
	public static byte[] MD5Encode(String origin) {
		return MD5Encode(origin.getBytes());
	}

	/**
	 * MD5加密字节数组，返回加密后的字节数组
	 * 
	 * @param bytes
	 *            要加密的字节数组
	 * @return 加密后的字节数组
	 */
	public static byte[] MD5Encode(byte[] bytes) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			return md.digest(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return new byte[0];
		}
	}
}
