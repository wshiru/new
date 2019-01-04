package com.yixin.A1000.video.webservice.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class VCIConnector implements Serializable {
	private static final long serialVersionUID = -9423481290765L;
	private static Logger logger = Logger.getLogger(VCIConnector.class);

	/** DSC服务器* */
	//private String dscServer;
	
	/** 建议时间 * */
	private Date createDate = new Date();
	/** DSC地址 * */
	private String address;
	/** DSC端口 * */
	private Integer port;
	/** 连接超时时间* */
	private Integer connectionTimeOut;
	/** 状态 */
	
	private Boolean state = false;
	private Socket socket = null;
	
	//private PrintWriter outSocket = null;
	private BufferedWriter outSocket = null;
	
	private BufferedReader inSocket = null;

	public VCIConnector(String address, Integer port, Integer connectionTimeOut) {
		 
		this.address = address;
		this.port = port;
		this.connectionTimeOut = connectionTimeOut;
	}

	public VCIConnector() {
		this("127.0.0.1", 9010, 60*5);
	}
	public Boolean getState(){
		return this.state;
	}
	public void close(){
		try {
			state = false;
			inSocket.close();
			outSocket.close();
			socket.close();			
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}
	
	public void setCreateDate() {
		this.createDate = new Date();
	}	

	public void connect() {
		try {
			logger.info("开始连接DSC...");
			logger.info(String.format("DSC IP:%s;Port:%d...", address, port));
			socket = new Socket(address, port);
			inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream(),"gb2312")); 
			outSocket = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"gb2312"));
			//outSocket = new PrintWriter(socket.getOutputStream());
			//inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			logger.info("连接DSC成功");
			state = true;
		} catch (IOException ex) {
			state = false;
			//close();
			socket = null;
			String errMsg = "连接DSC失败：发生IO错误:" + ex.getMessage();
			logger.error("连接DSC失败：发生IO错误", ex);
			// 设置连接状态为：断开
			throw new VCIConnectorException(VCIConnectorException.ERROR_COMMON,errMsg);
		}

		this.createDate = new Date();
	}	
	
	public String PTZControl(String sensorCode, String VideoServerCode, String CameraCode, Integer ptzCmd,Integer isStop){
		String data;		
		data = String.format("PTZControl %s %s %s %d %d", sensorCode, VideoServerCode,CameraCode , ptzCmd ,isStop);	
		return sendCommand(data);
	}
	
	public String PTZPreset(String sensorCode, String VideoServerCode, String CameraCode, Integer ptzPresetCmd,Integer presetIndex){
		String data;		
		data = String.format("PTZPreset %s %s %s %d %d", sensorCode, VideoServerCode,CameraCode , ptzPresetCmd ,presetIndex);	
		return sendCommand(data);
	}	
	
	public String PowerStatus(String sensorCode, String VideoServerCode, String CameraCode){
		String data;		
		data = String.format("PowerStatus %s %s %s", sensorCode, VideoServerCode,CameraCode);	
		return sendCommand(data);		
	}
	
	public String SnapPicture(String sensorCode, String VideoServerCode, String CameraCode,String filename){
		String data;		
		data = String.format("snappicture %s %s %s %s", sensorCode, VideoServerCode,CameraCode,filename);	
		return sendCommand(data);		
	}
	
	public String sendCommand(String cmd) {
		// 连接
		if (!state) {
			connect();
		}
		logger.info("开始请求对象...");

		try {
			// 发送
			outSocket.write(cmd+"\r\n");			outSocket.flush();
			logger.info("发送:" + cmd);
			// 接收
			String inState = inSocket.readLine();
			while("Wait...".equals(inState)){
				inState = inSocket.readLine();
			}
			logger.info("收到1:" + inState);
			String inLine = inSocket.readLine();
			logger.info("收到2:" + inLine);
			if (!"OK".equals(inState)) {
				throw new VCIConnectorException(VCIConnectorException.ERROR_SEND,inLine);
			}else{
			
			}
			return inLine;
		} catch (IOException ex) {
			String errMsg = "发送失败：发生IO错误," + ex.getMessage();
			logger.error("发送失败：发生IO错误,", ex);
			this.close();
			throw new VCIConnectorException(VCIConnectorException.ERROR_COMMON,errMsg);
		}
	}


	
	public Integer getConnectionTimeOut(){
		return this.connectionTimeOut;
	}
}
