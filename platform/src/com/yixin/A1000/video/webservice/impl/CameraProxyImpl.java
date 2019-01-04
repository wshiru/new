package com.yixin.A1000.video.webservice.impl;

/**
queryOnlineStatus ：返回摄像头在线在状态，返回0时，可以查看实时视频
queryPowerStatus： 返回1时，可以控制摄像头上线或下线，否刚不能控制，即返回0是调用powerControl才会成功，其它状态调用powerControl一定是失败的。
powerControl：打开或是关闭电源，powerSpan为打开电源的时间，单位为分钟，为0时关闭电源，其它为打开电源
 */

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.ws.Holder;

import net.sf.json.JSONArray;

import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.comm.CommService;
import com.yixin.A1000.monitor.model.OnlineDeviceStatus;
import com.yixin.A1000.setting.model.SensorHeartbeatInfo;
import com.yixin.A1000.setting.service.SensorHeartbeatInfoService;
import com.yixin.A1000.video.model.RecordFile;
import com.yixin.A1000.video.model.VideoServer;
import com.yixin.A1000.video.service.RealTimeVideoService;
import com.yixin.A1000.video.service.RecordFileService;
import com.yixin.A1000.video.service.VideoServerService;
import com.yixin.A1000.video.service.impl.Ftp;
import com.yixin.A1000.warning.model.Warning;
import com.yixin.A1000.warning.service.WarningService;
import com.yixin.framework.base.model.Page;

@javax.jws.WebService(endpointInterface = "com.yixin.A1000.video.webservice.CameraProxyPortType", targetNamespace = "http://tempuri.org/api.xsd/CameraProxy.wsdl", serviceName = "CameraProxy", portName = "CameraProxy")
public class CameraProxyImpl {

	private class RecordFileInfos{
		public  List<Map<String , Object>> recordFileInfoList;

		public void setRecordFileInfoList(List<Map<String , Object>> recordFileInfoList) {
			this.recordFileInfoList = recordFileInfoList;
		}
		
		public List<Map<String , Object>> getRecordFileInfoList() {
			return recordFileInfoList;
		}
		
	}
	
	
	public class AlarmEvent{
		private List<Map<String , Object>> alarmEventList;

		public void setAlarmEventList(List<Map<String , Object>> alarmEventList) {
			this.alarmEventList = alarmEventList;
		}

		public List<Map<String , Object>> getAlarmEventList() {
			return alarmEventList;
		}
	}
	
	private static int SUCCESS = 0;
	private static int ERROR = -1;
	private static SensorService sensorServiceBeen;
	private static VideoServerService videoServerServiceBeen;
	private static RecordFileService recordFileServiceBeen;
	private static RealTimeVideoService realTimeVideoServiceBeen;
	private static CommService commServiceBeen;	
	private static SensorHeartbeatInfoService sensorHeartbeatInfoServiceBeen;
	
	private static WarningService warningServiceBeen;

	private static Date StringToDate(String dateStr, String formatStr) {
		DateFormat dd = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public void setSensorService(SensorService sensorService) {
		sensorServiceBeen = sensorService;
	}

	public SensorService getSensorService() {
		return sensorServiceBeen;
	}

	public void setVideoServerService(VideoServerService videoServerService) {
		videoServerServiceBeen = videoServerService;
	}

	public VideoServerService getVideoServerService() {
		return videoServerServiceBeen;
	}

	public void setRecordFileService(RecordFileService recordFileService) {
		recordFileServiceBeen = recordFileService;
	}

	public RecordFileService getRecordFileService() {
		return recordFileServiceBeen;
	}

	public void setRealTimeVideoService(
			RealTimeVideoService realTimeVideoService) {
		realTimeVideoServiceBeen = realTimeVideoService;
	}

	public RealTimeVideoService getRealTimeVideoService() {
		return realTimeVideoServiceBeen;
	}

	public void setWarningService(WarningService warningService) {
		warningServiceBeen = warningService;
	}
	
	public void setCommService(CommService commService) {
		commServiceBeen = commService;
	}
	
	public void setSensorHeartbeatInfoService(SensorHeartbeatInfoService sensorHeartbeatInfoService) {
		sensorHeartbeatInfoServiceBeen = sensorHeartbeatInfoService;
	}		

	public String login(String userName, String password) {
		return "1";
	}

	public int logout(String loginID) {
		return SUCCESS;
	}

	public int ptzControl(String loginID, int channelID, int ptzCmd, int isStop) {
		//String id = String.valueOf(channelID);
		VideoServer videoServer = getVideoServerService().getVideoServer(channelID);
		if (videoServer == null) {
			return ERROR;
		} else {
			VCIConnector connector = VCIConnectionPool.createVCIConnector();
			try {
				connector.PTZControl(videoServer.getSensor().getSensorCode(),
						videoServer.getVideoServerCode(),
						videoServer.getCameraCode(), ptzCmd, isStop);
				VCIConnectionPool.freeVCIConnector(connector);
				return SUCCESS;
			} catch (VCIConnectorException ve) {
				return ERROR;
			}
		}
	}

	public int ptzPreset(String loginID, int channelID, int ptzPresetCmd,
			int presetIndex) {
		VideoServer videoServer = getVideoServerService().getVideoServer(channelID);
		if (videoServer == null) {
			return ERROR;
		} else {
			VCIConnector connector = VCIConnectionPool.createVCIConnector();
			try {
				connector.PTZPreset(videoServer.getSensor().getSensorCode(),
						videoServer.getVideoServerCode(),
						videoServer.getCameraCode(), ptzPresetCmd, presetIndex);
				VCIConnectionPool.freeVCIConnector(connector);
				return SUCCESS;
			} catch (VCIConnectorException ve) {
				return ERROR;
			}
		}
	}
	
	public int photograph(String loginID, int channelID, int presetIndex,
			String ftpPath) {
		String filename = "";
		String remotefile = "";
		String[] sl = ftpPath.split("//");
 
				
		VideoServer videoServer = getVideoServerService().getVideoServer(channelID);
		if (videoServer == null) {
			return ERROR;
		} else {
			VCIConnector connector = VCIConnectionPool.createVCIConnector();
			URL url;
			try {
		 
				url = new URL(ftpPath);
	 
				//先生成本地文件
				SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");				
				filename  = String.format("%s\\%s-%s-%s-%d.bmp", VCIConnectionPool.ftpLocatePath,
						videoServer.getVideoServerCode(),
						videoServer.getCameraCode(),presetIndex,formatDate.format(new Date()));
						
				//filename  = String.format("%s\\%s", VCIConnectionPool.ftpLocatePath,
				//				"aa.jpg");						
						
				//转到预置位
				connector.PTZPreset(videoServer.getSensor().getSensorCode(),
						videoServer.getVideoServerCode(),
						videoServer.getCameraCode(), 3, presetIndex);
				//拍照
				connector.SnapPicture(videoServer.getSensor().getSensorCode(),
						videoServer.getVideoServerCode(),
						videoServer.getCameraCode(), filename);
				VCIConnectionPool.freeVCIConnector(connector);
				
				//这里再上传文件到服务器
				Ftp ftp =new Ftp();								
				if(VCIConnectionPool.ftpUseUrlPath==1){
					String s = url.getFile();
					s = s.substring(0, s.lastIndexOf("/"));
					ftp.connectServer( url.getHost(), url.getPort(),
							url.getUserInfo().split(":")[0],url.getUserInfo().split(":")[1],
							s);					
				}else
				{
					ftp.connectServer(VCIConnectionPool.ftpIp, VCIConnectionPool.ftpPort,
						VCIConnectionPool.ftpUser, VCIConnectionPool.ftpPassword, VCIConnectionPool.ftpRemotePath);
				}
				//上传文件
				ftp.upload(filename, url.getFile());
				ftp.closeConnect();
				return SUCCESS;
			} catch (VCIConnectorException ve) {
				return ERROR;
			} catch (MalformedURLException e) {
				return ERROR;
			}
		}
	}
	

	public int powerControl(String loginID, int channelID, int powerSpan) {
		VideoServer videoServer = getVideoServerService().getVideoServer(channelID);
		if (videoServer == null) {
			return ERROR;
		} else {
			try{
				if (realTimeVideoServiceBeen.openPower(videoServer.getSensor(),
						powerSpan)) {
					return SUCCESS;
				} else {
					return ERROR;
				}	
			}catch(Exception ex){
				return ERROR;
			}
			
		}
	}

	public void queryAlarmEvent(String loginID, int channelID,
			String startTime, String endTime, Holder<String> alarmEventList,
			Holder<Integer> result) {
		VideoServer videoServer = getVideoServerService().getVideoServer(channelID);
		if (videoServer == null) {
			result.value = ERROR;
			return;
		} else {

			//Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
			List<Map<String, Object>> mapValueList = new ArrayList<Map<String, Object>>();
			AlarmEvent alarmEvent = new AlarmEvent(); 
			Date st = StringToDate(startTime, "yyyy-MM-dd hh:mm:ss");
			Date et = StringToDate(endTime, "yyyy-MM-dd hh:mm:ss");

			List<Warning> list = warningServiceBeen
					.getWarningsBySensor(null, null, videoServer.getSensor()
							.getSensorCode(), st, et, null);

			Iterator<Warning> iterator = list.iterator();
			while (iterator.hasNext()) {
				Warning warning = iterator.next();
				/*
				 * mapValueList.put("code",
				 * warning.getWarningDict().getWarningDictId());
				 * mapValueList.put("dataType", warning.getFileSize());
				 * mapValueList.put("value", warning.getCreateTime());
				 * mapValueList.put("time", warning.getCreateTime());
				 */
				
			}
			//map.put("alarmEventList", mapValueList);
			alarmEvent.setAlarmEventList(mapValueList);
			JSONArray ja = JSONArray.fromObject(alarmEvent);
			System.out.println(ja.toString());
			alarmEventList.value = ja.toString();
			result.value = SUCCESS;
			return;
		}
	}

	public void queryPowerStatus(String loginID, int channelID,
			Holder<Integer> powerStatus, Holder<Integer> result) {
		
		VideoServer videoServer = getVideoServerService().getVideoServer(channelID);
		if (videoServer == null) {
			result.value = ERROR;
			return;
		} else {
			List<OnlineDeviceStatus> list=  commServiceBeen.getOnlineDeviceStatus();
			Iterator<OnlineDeviceStatus> iterator = list.iterator();
			while(iterator.hasNext()){
				OnlineDeviceStatus device = iterator.next();
				if(device.getSensor()!=null && videoServer.getSensor() != null)
				{
					if( videoServer.getSensor().getSensorCode().equals(device.getSensor().getSensorCode())){
						powerStatus.value = 1;
						result.value = SUCCESS;
						return ;
					}
				}
			}			 
			powerStatus.value = 0;
			result.value = SUCCESS;
			return ;			
		}		
		
	}

	public void queryOnlineStatus(String loginID, int channelID,
			Holder<Integer> onlineStatus, Holder<Integer> result) {
		VideoServer videoServer = getVideoServerService().getVideoServer(channelID);
		if (videoServer == null) {
			result.value = ERROR;
			return;
		} else {
			VCIConnector connector = VCIConnectionPool.createVCIConnector();
			try {
				connector.PowerStatus(videoServer.getSensor().getSensorCode(),
						videoServer.getVideoServerCode(),
						videoServer.getCameraCode());
				VCIConnectionPool.freeVCIConnector(connector);
				onlineStatus.value = 1;
				result.value = SUCCESS;
				return ;
			} catch (VCIConnectorException ve) {
				onlineStatus.value = 0;
				result.value = SUCCESS;				
				return ;
			}		 						
		}			
	}

	public void queryBatteryVoltage(String loginID, int channelID,
			Holder<Float> voltage, Holder<Integer> result) {
		VideoServer videoServer = getVideoServerService().getVideoServer(channelID);
		if (videoServer == null) {
			result.value = ERROR;
			return;
		} else {
			Page<SensorHeartbeatInfo> page = sensorHeartbeatInfoServiceBeen.getPageSensorHeartbeatInfos(videoServer.getSensor() , null,null,1,1);
			float f = Float.valueOf(0);
			Iterator<SensorHeartbeatInfo> iterator = page.getRecords().iterator();
			while(iterator.hasNext()){
				SensorHeartbeatInfo info =  iterator.next();
				double d=info.getBatteryvoltage(); 
				voltage.value = (float)d;
				result.value = SUCCESS;
				return;
			}
			voltage.value = f;
			result.value = ERROR;
			return;
		}
	}
	
	public void queryRealPlayRtspUrl(String loginID, int channelID,
			Holder<String> rtspUrl, Holder<Integer> result) {		
		VideoServer videoServer = getVideoServerService().getVideoServer(
				channelID);		
		if (videoServer == null) {
			result.value = ERROR;
			return;
		} else {
			CR7WebServer webserver = CR7WebServer.getCR7WebServer();
			if(VCIConnectionPool.readRtspUrlType==1){
				rtspUrl.value = webserver.getRtspUrl(videoServer.getVideoServerCode(), videoServer.getCameraCode());
			}else{				
				rtspUrl.value = videoServer.getRtspUrl();
			}
			result.value = SUCCESS;
			return;
		}
	}

	public void queryRecordFile(String loginID, int channelID,
			String startTime, String endTime,
			Holder<String> recordFileInfoList, Holder<Integer> result) {

		VideoServer videoServer = getVideoServerService().getVideoServer(channelID);
		if (videoServer == null) {
			result.value = ERROR;
			return;
		} else {
			VCIConnectionPool.readConfig();
			List<Map<String, Object>> valueList = new ArrayList<Map<String , Object>>();
			
			RecordFileInfos recordFileInfos = new RecordFileInfos();
			
			Date st = StringToDate(startTime, "yyyy-MM-dd hh:mm:ss");
			Date et = StringToDate(endTime, "yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			List<RecordFile> list = recordFileServiceBeen.getRecordFiles(
					videoServer.getSensor(), 0, st, et);
			System.out.println(list.size());
			Iterator<RecordFile> iterator = list.iterator();
 
			while (iterator.hasNext()) {
				RecordFile recordFile = iterator.next();
				Map<String, Object> mapValueList = new HashMap<String, Object>();	
				mapValueList.put("fileName", VCIConnectionPool.videoFileUrl + recordFile.getFileName().replace("\\", "/"));
				mapValueList.put("size", recordFile.getFileSize());
				mapValueList.put("startTime", formatDate.format(recordFile.getStartTime()));
				mapValueList.put("endTime", formatDate.format(recordFile.getEndTime()));
				mapValueList.put("RtspUrl", VCIConnectionPool.rtspUrl + recordFile.getFileName().replace("\\", "/").replace(".avi", ".264"));
				valueList.add(mapValueList);
			}
			recordFileInfos.setRecordFileInfoList(valueList);			
			JSONArray ja = JSONArray.fromObject(valueList);
			System.out.println(ja.toString());
			recordFileInfoList.value = ja.toString();
			result.value = SUCCESS;
			return;
		}
	}

}