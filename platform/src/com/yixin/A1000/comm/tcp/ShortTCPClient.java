/*
 * Project platform
 *
 * Class T.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2012-8-29 下午02:25:09
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2012 亿鑫新能源 版权所有
 */
package com.yixin.A1000.comm.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.yixin.A1000.comm.exception.ClosedConnectionException;
import com.yixin.A1000.comm.exception.ReceiveDataException;
import com.yixin.A1000.comm.exception.SendDataException;
import com.yixin.A1000.comm.exception.TimeOutException;

/**
 * TCP的短连接类
 * 
 * @version v1.0.0
 * @author 
 */
public class ShortTCPClient {

	// ==================================================================================================================
	// 属性
	/** 日志记录器 */
	private static Logger logger = Logger.getLogger(ShortTCPClient.class);

	/** 日志记录器：打印发送与接收的16进制信息 */
	private static Logger socketLogger = Logger.getLogger("socketLogger");

	private static int RECV_BUFF_SIZE = 1024 * 4;// 接收缓冲区大小

	/** 服务器地址 */
	private InetSocketAddress serverAddress;

	/** 与服务器间的连接通道 */
	private SocketChannel socketChannel;

	// ==================================================================================================================
	// 构造方法
	/**
	 * 构造函数。<br />
	 * 默认使用1024 * 4 byte大小的接收缓冲区。
	 * 
	 * @param serverAddress
	 *            服务器地址
	 */
	public ShortTCPClient(InetSocketAddress serverAddress) {
		this.serverAddress = serverAddress;
	}

	/**
	 * 构造函数
	 * 
	 * @param serverAddress
	 *            服务器地址
	 * @param recvBuffSize
	 *            接收缓冲区的大小。单位：byte
	 */
	public ShortTCPClient(InetSocketAddress serverAddress, int recvBuffSize) {
		this.serverAddress = serverAddress;
		RECV_BUFF_SIZE = recvBuffSize;
	}

	// ==================================================================================================================
	// 方法
	/**
	 * 向TCP服务器发送字节流数据（只发送模式）。<br />
	 * 向TCP服务器发送完数据data后，立即关闭连接，不进行数据的接收。<br />
	 * <br />
	 * 处理流程如下：<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;1、创建连接 &nbsp;&nbsp;&nbsp;&nbsp;2、发送数据
	 * &nbsp;&nbsp;&nbsp;&nbsp;3、关闭连接
	 * 
	 * @param data
	 *            要进行发送的字节流数据
	 * @exception ClosedConnectionException
	 *                与服务器间未建立连接，或者连接已经断开时，抛出此异常
	 * @exception SendDataException
	 *                向服务器发送数据发生错误时，抛出此异常
	 */
	public void sendByteStream(byte[] data) {
		this.connect();
		this.send(data, false);
		this.close();
	}

	/**
	 * 向TCP服务器发送字节流数据（发送-接收模式）。<br />
	 * 向TCP服务器发送数据data，然后接收服务器的一条响应后关闭连接，并返回所接收到的数据。<br />
	 * <br />
	 * 处理流程如下：<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;1、创建连接 &nbsp;&nbsp;&nbsp;&nbsp;2、发送数据
	 * &nbsp;&nbsp;&nbsp;&nbsp;3、接收数据 &nbsp;&nbsp;&nbsp;&nbsp;4、关闭连接
	 * 
	 * @param data
	 *            要进行发送的字节流数据
	 * @param timeout
	 *            超时时间，单位：ms
	 * @exception ClosedConnectionException
	 *                与服务器间未建立连接，或者连接已经断开时，抛出此异常
	 * @exception SendDataException
	 *                向服务器发送数据发生错误时，抛出此异常
	 * @exception ReceiveDataException
	 *                当从服务器接收数据发生错误时，抛出此异常
	 * @exception TimeOutException
	 *                在timeout时间内未收到服务器的响应时，抛出此异常
	 * @return 接收到的数据
	 */
	public byte[] sendByteStream(byte[] data, long timeout) {
		this.connect();// 创建连接
		this.send(data, false);
		byte[] result = this.recevice(timeout, false);// 接收数据
		this.close();// 关闭连接
		return result;
	}

	/**
	 * 向TCP服务器发送字符流数据（只发送模式）。<br />
	 * 向TCP服务器发送完数据data后，立即关闭连接，不进行数据的接收。<br />
	 * <br />
	 * 处理流程如下：<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;1、创建连接 &nbsp;&nbsp;&nbsp;&nbsp;2、发送数据
	 * &nbsp;&nbsp;&nbsp;&nbsp;3、关闭连接
	 * 
	 * @param data
	 *            要进行发送的字符流数据
	 * @exception ClosedConnectionException
	 *                与服务器间未建立连接，或者连接已经断开时，抛出此异常
	 * @exception SendDataException
	 *                向服务器发送数据发生错误时，抛出此异常
	 */
	public void sendCharacterStream(byte[] data) {
		this.connect();
		this.send(data, true);
		this.close();
	}

	/**
	 * 向TCP服务器发送字符流数据（发送-接收模式）。<br />
	 * 向TCP服务器发送数据data，然后接收服务器的一条响应后关闭连接，并返回所接收到的数据。<br />
	 * <br />
	 * 处理流程如下：<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;1、创建连接 &nbsp;&nbsp;&nbsp;&nbsp;2、发送数据
	 * &nbsp;&nbsp;&nbsp;&nbsp;3、接收数据 &nbsp;&nbsp;&nbsp;&nbsp;4、关闭连接
	 * 
	 * @param data
	 *            要进行发送的字符流数据
	 * @param timeout
	 *            超时时间，单位：ms
	 * @exception ClosedConnectionException
	 *                与服务器间未建立连接，或者连接已经断开时，抛出此异常
	 * @exception SendDataException
	 *                向服务器发送数据发生错误时，抛出此异常
	 * @exception ReceiveDataException
	 *                当从服务器接收数据发生错误时，抛出此异常
	 * @exception TimeOutException
	 *                在timeout时间内未收到服务器的响应时，抛出此异常
	 * @return 接收到的数据
	 */
	public byte[] sendCharacterStream(byte[] data, long timeout) {
		this.connect();// 创建连接
		byte[] header = "send ".getBytes();
		byte[] end = { 0x0D, 0x0A };
		header = this.arraycopy(data, 0, header, header.length, data.length);
		data = this.arraycopy(end, 0, header, header.length, 2);
		this.send(data, true);// 发送数据
		byte[] result = this.recevice(timeout, true);// 接收数据
		this.close();// 关闭连接
		return result;
	}
	
	/**
	 * 向TCP服务器发送字符流数据（发送-接收模式）。<br />
	 * 向TCP服务器发送数据data，然后接收服务器的一条响应后关闭连接，并返回所接收到的数据。<br />
	 * <br />
	 * 处理流程如下：<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;1、创建连接 &nbsp;&nbsp;&nbsp;&nbsp;2、发送数据
	 * &nbsp;&nbsp;&nbsp;&nbsp;3、接收数据 &nbsp;&nbsp;&nbsp;&nbsp;4、关闭连接
	 * 
	 * @param data
	 *            要进行发送的字符流数据
	 * @param timeout
	 *            超时时间，单位：ms
	 * @exception ClosedConnectionException
	 *                与服务器间未建立连接，或者连接已经断开时，抛出此异常
	 * @exception SendDataException
	 *                向服务器发送数据发生错误时，抛出此异常
	 * @exception ReceiveDataException
	 *                当从服务器接收数据发生错误时，抛出此异常
	 * @exception TimeOutException
	 *                在timeout时间内未收到服务器的响应时，抛出此异常
	 * @return 接收到的数据
	 */
	// 又一特殊命令
	public byte[] sendSpecificCmd(byte[] data, long timeout) {
		this.connect();// 创建连接
		byte[] end = { 0x0D, 0x0A };
		data = this.arraycopy(end, 0, data, data.length, end.length);
		this.send(data, true);// 发送数据
		byte[] result = this.recevice(timeout, true);// 接收数据
		this.close();// 关闭连接
		return result;
	}

	/**
	 * 向TCP服务器发送数据，并打印相关的发送日志<br />
	 * 如果是字符数据，则日志打印字符。否则打印16进制。
	 * 
	 * @param data
	 *            要进行发送的数据
	 * @param isCharacterStream
	 *            true - 字符流数据<br />
	 *            false - 字节流数据
	 * @exception SendDataException
	 *                向服务器发送数据发生错误时，抛出此异常
	 */
	private void send(byte[] data, boolean isCharacterStream) {
		Assert.assertNotNull("Error! [data] could not be null.", data);
		Assert.assertTrue("Error! [data] length must greater than 0.", data.length > 0);
		ByteBuffer buffer = ByteBuffer.allocate(data.length); // 发送缓冲区
		try {
			buffer.clear();// 清空发送缓冲区
			buffer = ByteBuffer.wrap(data);// 将 buff 数组包装到缓冲区中
			socketChannel.write(buffer);// 向服务器写命令
		} catch (Exception ex) {
			this.close();
			logger.error("向服务器写数据时发生错误。详细信息如下：", ex);
			throw new SendDataException();
		}
		if (isCharacterStream) {
			this.printStringLog(data, true);// 打印发送信息
		} else {
			this.printHexLog(data, true); // 打印16进制信息
		}
	}

	/**
	 * 接收TCP服务器响应的数据，并打印相关的发送日志<br />
	 * 如果是字符数据，则日志打印字符。否则打印16进制。
	 * 
	 * @param timeout
	 *            超时时间，单位：ms
	 * @param isCharacterStream
	 *            true - 字符流数据<br />
	 *            false - 字节流数据
	 * @exception ClosedConnectionException
	 *                与服务器间未建立连接，或者连接已经断开时，抛出此异常
	 * @exception ReceiveDataException
	 *                当从服务器接收数据发生错误时，抛出此异常
	 * @return
	 */
	private byte[] recevice(long timeout, boolean isCharacterStream) {
		Assert.assertTrue("Error! [timeout] must greater than 0.", timeout > 0);
		long baseTime = Calendar.getInstance().getTimeInMillis();
		long timeDiff = 0; // 时间差
		int len = -1;
		ByteBuffer recvBuff = ByteBuffer.allocate(RECV_BUFF_SIZE); // 接收缓冲区
		byte[] result = new byte[0];
		try {
			this.checkSocketChannel();
			recvBuff.clear();// 清空缓冲区
			socketChannel.configureBlocking(false);// 设置通道为阻塞
			boolean isEnd = false;
			int count = 0;
			while ((len = socketChannel.read(recvBuff)) >= 0) {
				timeDiff = Calendar.getInstance().getTimeInMillis() - baseTime;
				if (len > 0) {
					result = this.arraycopy(recvBuff.array(), 0, result, count, len);
					if (isCharacterStream) {
						this.printStringLog(result, false);// 打印发送信息
					} else {
						this.printHexLog(result, false); // 打印16进制信息
					}
					
					if (this.containsEnterChar(result) >= 2) {
						result = this.trimEnterChar(result);
						isEnd = true; // 以回车结束，则表示结束
						break;
					}
				}
				recvBuff.clear();// 清空缓冲区
				if (isEnd || timeDiff >= timeout) {
					break;
				}
			}
		} catch (Exception ex) {
			this.close();
			logger.error("向服务器接收数据时发生错误。详细信息如下：", ex);
			throw new ReceiveDataException();
		}
		if (timeDiff >= timeout) {
			this.close();
			throw new TimeOutException();
		}
		return result;
	}

	/**
	 * 创建连接
	 * 
	 * @exception ClosedConnectionException
	 *                与服务器间未建立连接，或者连接已经断开时，抛出此异常
	 */
	private void connect() {
		try {
			socketChannel = SocketChannel.open();// 打开连接通道
			logger.info("正在连接到服务器 - " + this.serverAddress.getAddress().getHostAddress() + ":" + this.serverAddress.getPort());
			boolean b = socketChannel.connect(this.serverAddress);// 连接到服务器
			if (socketChannel.isConnectionPending()) { // 当连接未完成时，先完成连接
				socketChannel.finishConnect();
			}
			if (!b) {
				logger.error("连接服务器失败：未能打开连接");
				this.close();
				throw new ClosedConnectionException();
			}
			checkSocketChannel();
			logger.info("成功连接到服务器 - " + this.serverAddress.getAddress().getHostAddress() + ":" + this.serverAddress.getPort());
			socketChannel.configureBlocking(false);// 设置通道为非阻塞
		} catch (IOException e) {
			logger.error("连接服务器失败。详细信息如下：", e);
			this.close();
			throw new ClosedConnectionException();
		}
	}

	/**
	 * 关闭连接
	 * 
	 */
	private void close() {
		if (null != this.socketChannel && this.socketChannel.isOpen()) {
			try {
				this.socketChannel.close();
				logger.info("关闭与服务器连接成功 - " + this.serverAddress.getAddress().getHostAddress() + ":" + this.serverAddress.getPort());
			} catch (IOException e) {
				logger.error("关闭与服务器间的连接时发生错误。详细信息如下：", e);
			}
		}
	}

	/**
	 * 检查socketChannel是否已经打开，并已经连接。如果否，则抛出ClosedConnectionException异常。
	 * 
	 * @exception ClosedConnectionException
	 *                无法与服务器建立连接，或者连接已经断开时，抛出此异常
	 */
	private void checkSocketChannel() {
		if (!this.socketChannel.isOpen() || !this.socketChannel.isConnected()) {
			this.close();
			logger.error("无法与服务器建立连接，或者连接已经断开");
			throw new ClosedConnectionException();
		}
	}

	/**
	 * 把字节数组转换成16进制字符串
	 * 
	 * @param bArray
	 *            要转换的字节数组
	 * @return 返回转换后的16进制字符串
	 * 
	 */
	private String bytesToHexString(byte[] bArray) {
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
	 * 打印发送日志（字符串形式）
	 * 
	 * @param data
	 *            要打印的数据
	 * @param isSend
	 *            是否为发送数据<br />
	 *            true - 发送数据<br />
	 *            false - 接收数据
	 */
	private void printStringLog(byte[] data, boolean isSend) {
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sb.append("----------------------------------- " + sdf.format(new Date()) + " -----------------------------------\r\n");
		if (isSend) {
			sb.append("【TYPE】SENDED\r\n");
		} else {
			sb.append("【TYPE】RECEIVED\r\n");
		}
		sb.append("【DATA】" + new String(data));
		socketLogger.info(sb.toString());
	}

	/**
	 * 打印发送日志（16进制形式）
	 * 
	 * @param data
	 *            要打印的数据
	 * @param isSend
	 *            是否为发送数据<br />
	 *            true - 发送数据<br />
	 *            false - 接收数据
	 */
	private void printHexLog(byte[] data, boolean isSend) {
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sb.append("----------------------------------- " + sdf.format(new Date()) + " -----------------------------------\r\n");
		if (isSend) {
			sb.append("【TYPE】RECEIVED\r\n");
		} else {
			sb.append("【TYPE】SENDED\r\n");
		}
		sb.append("【DATA】" + this.bytesToHexString(data));
		socketLogger.info(sb.toString());
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
	private byte[] arraycopy(byte[] src, int srcPos, byte[] dest, int destPos, int length) {
		byte[] result = new byte[destPos + length]; // 调整数据的大小
		System.arraycopy(dest, 0, result, 0, destPos); // 将dest复制到result中
		System.arraycopy(src, srcPos, result, destPos, length); // 将src添加到result中
		return result;
	}

	private int containsEnterChar(byte[] data) {
		int lineCount = 0;
		for (int i = 0, len = data.length; i < len; i++) {
			if (0x0D == data[i] && 0x0A == data[i + 1]) {
				lineCount++;
			}
		}
		return lineCount;
	}

	private byte[] trimEnterChar(byte[] data) {
		List<Byte> array = new ArrayList<Byte>();
		int lineCount = 0;
		for (int i = 0, len = data.length; i < len; i++) {
			if (!(0x0D == data[i] && 0x0A == data[i + 1])) {
				array.add(data[i]);
			} else {
				lineCount++;
				if (1 == lineCount) {
					byte b = 0x2C;
					array.add(new Byte(b)); // 第一行尾添加逗号
				}
				i++; // 因回车换行有两个字节，因此遇到回车换行符时，跳过一次
			}
		}
		byte[] result = new byte[array.size()];
		for (int i = 0, len = array.size(); i < len; i++) {
			result[i] = array.get(i);
		}
		return result;
	}
}
