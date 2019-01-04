/*
 * Project ca2000
 *
 * Class I2Service.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午09:30:32
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.webservice;

/**
 * I2 WEB SERVICE 服务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface I2Service {

	/**
	 * <b>上传心跳信息。</b><br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;用于心跳交互过程，该方法供CMA调用以发送心跳信息。
	 * CAG返回结果中按交互过程的不同包含不同类型的控制指令和信息。 输入参数和输出参数均为约定格式标准的XML。
	 * 
	 * @param strXMLParams
	 *            xml格式的参数
	 * @return
	 */
	public abstract String uploadCMAHeartbeatInfo(String strXMLParams);

	/**
	 * <b>上传监测数据。</b><br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;用于数据交互过程，该方法供CMA调用以发送监测数据。
	 * CAG返回结果中按交互过程的不同包含不同类型的控制指令和信息。输入参数和输出参数均为约定格式标准的XML。
	 * 
	 * @param strXMLParams
	 *            xml格式的参数
	 * @return
	 */
	public abstract String uploadCMAData(String strXMLParams);

	/**
	 * <b>上传静态图片数据。</b><br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;用于数据交互过程，该方法供CMA调用发送静态图片数据，对于大图片，可采用分段传输模式，
	 * 方法中第一个输入参数为上传的静态图片或静态图片片段的数据
	 * ；第二个参数包含此次上传静态图片或静态图片片段的相关说明信息。CAG返回结果中包含静态图片片段的处理结果。
	 * 
	 * @param imageByte
	 *            Base64编码的图片数据
	 * @param strXMLParams
	 *            xml格式的参数
	 * @return
	 */
	public abstract String uploadCMAImage(byte[] imageByte, String strXMLParams);

	/**
	 * <b>上传配置信息。</b><br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;用于读配置交互过程，该方法供CMA调用发送配置信息。
	 * CAG返回结果中按交互过程的不同包含不同类型的控制指令和信息。输入参数和输出参数均为约定格式标准的XML。
	 * 
	 * @param strXMLParams
	 *            xml格式的参数
	 * @return
	 */
	public abstract String uploadCMAConfig(String strXMLParams);

	/**
	 * <b>下发控制指令。</b><br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;用于控制交互过程，该方法供CAG调用向CMA下发控制指令。CMA返回结果包含命令执行结果。
	 * 输入参数和输出参数均为约定格式标准的XML。
	 * 
	 * @param strXMLParams
	 *            xml格式的参数
	 * @return
	 */
	public abstract String downloadCAGCtrl(String strXMLParams);

	/**
	 * <b>获取最新版本。</b><br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;用于远程更新交互过程，该方法供 CMA 调用向 CAG
	 * 获取最新版本的文件名称。输入输出参数均为约定格式标准的XML。
	 * 
	 * @param strXMLParams
	 *            xml格式的参数
	 * @return
	 */
	public abstract String downloadCMALatestVersion(String strXMLParams);

	/**
	 * <b>获取历史版本。</b><br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;用于远程更新交互过程，该方法供 CMA 调用向 CAG
	 * 获取所有历史版本的文件名称。输入输出参数均为约定格式标准的XML，格式与downloadCMALatestVersion服务方法相同。
	 * 
	 * @param strXMLParams
	 *            xml格式的参数
	 * @return
	 */
	public abstract String downloadCMAHistoryVersion(String strXMLParams);

	/**
	 * <b>获取更新文件。</b><br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;用于心跳交互和远程更新交互过程，该方法供 CMA 调用向 CAG
	 * 获取指定版本和名称的文件内容。输入输出参数均为约定格式标准的XML。
	 * 
	 * @param strXMLParams
	 *            xml格式的参数
	 * @return
	 */
	public abstract String downloadCMAUpdateFile(String strXMLParams);
}
