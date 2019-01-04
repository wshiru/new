/*
 * Project platform
 *
 * Class ProtocoControlValidator.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-9-5 下午02:33:07
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.comm.protocol.validator;

import com.yixin.A1000.comm.exception.ProtocolException;
import com.yixin.A1000.comm.exception.TCPServerException;
import com.yixin.A1000.comm.protocol.ProtocolErrorCode;

/**
 * 通信协议 验证类
 * 
 * @version v1.0.0
 * @author 
 */
public class ProtocolValidator {

	/**
	 * 验证回应数据格式
	 *
	 * @param data
	 * @return
	 */
	public byte[] validate(byte[] data) {
		String str = new String(data);
		String[] strs = str.split(",");
		if (strs.length < 1) {// 协议格式错误：协议长度不足
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100001);
		}	
		
		String frameFlag = strs[0];
		if ("FAIL".equalsIgnoreCase(frameFlag)) {
			if (strs.length < 2) {// 协议格式错误：协议长度不足
				throw new ProtocolException(ProtocolErrorCode.ERROR_0100001);
			}
			throw new TCPServerException(new String(data, 0, data.length - 1));
		} else if ("OK".equalsIgnoreCase(frameFlag)) {
			byte[] header = "OK,".getBytes();
			return this.arraycopy(data, header.length, data, 0, data.length - header.length);
		} else {
			throw new ProtocolException(ProtocolErrorCode.ERROR_0100001);
		}
	}
	
	/**
	 * 自动根据要复制的数据大小，调整结果数据的大小，并返回
	 * 
	 * @param src
	 *            源数组
	 * @param srcPos
	 *            源数组中的起始位置
	 * @param dest
	 *            目标数组
	 * @param destPos
	 *            目标数据中的起始位置
	 * @param length
	 *            要复制的数组元素的数量
	 * @return
	 */
	protected byte[] arraycopy(byte[] src, int srcPos, byte[] dest, int destPos, int length) {
		byte[] result = new byte[destPos + length]; // 调整数据的大小
		System.arraycopy(dest, 0, result, 0, destPos); // 将dest复制到result中
		System.arraycopy(src, srcPos, result, destPos, length); // 将src添加到result中
		return result;
	}
}
