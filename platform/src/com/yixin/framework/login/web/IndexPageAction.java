/*
 * Project platform
 *
 * Classname LoginAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-2 下午02:19:13
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.login.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yixin.A1000.archive.model.Line;
import com.yixin.A1000.archive.model.Picture;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.model.SensorType;
import com.yixin.A1000.archive.model.Tower;
import com.yixin.A1000.archive.service.LineService;
import com.yixin.A1000.archive.service.PictureService;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.archive.service.SensorTypeService;
import com.yixin.A1000.archive.service.TowerService;
import com.yixin.A1000.landslide.model.LandslideParameter;
import com.yixin.A1000.landslide.model.LandslideParameterDetail;
import com.yixin.A1000.landslide.model.LandslideSampling;
import com.yixin.A1000.landslide.model.LandslideSamplingDetail;
import com.yixin.A1000.landslide.service.LandslideParameterService;
import com.yixin.A1000.landslide.service.LandslideSamplingService;
import com.yixin.A1000.monitor.model.OnlineDeviceStatus;
import com.yixin.A1000.monitor.service.OnlineDeviceStatusService;
import com.yixin.A1000.towertilt.model.TowerTiltParameter;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;
import com.yixin.A1000.towertilt.service.TowerTiltParameterService;
import com.yixin.A1000.towertilt.service.TowerTiltSamplingService;
import com.yixin.A1000.video.model.RecordFile;
import com.yixin.A1000.video.service.RecordFileService;
import com.yixin.A1000.warning.service.WarningService;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.A1000.weather.service.WeatherSamplingService;
import com.yixin.framework.base.model.Page;
import com.yixin.framework.login.model.DataItem;
import com.yixin.framework.login.model.GroupPage;
import com.yixin.framework.login.model.MainPageInfo;

/**
 * TODO(类的描述信息)
 * 
 * @version v1.0.0
 * @author
 */
public class IndexPageAction extends ActionSupport implements
		ServletRequestAware {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = -429034561563267L;

	/** request域对象 */
	private HttpServletRequest request;

	/** 在线设备状态接口 **/
	private OnlineDeviceStatusService onlineDeviceStatusService;

	/** 告警服务 */
	private WarningService warningService;

	/** 线路业务接口 **/
	private LineService lineService;

	/** 杆塔业务接口 **/
	private TowerService towerService;

	/** 监测装置类型业务接口 **/
	private SensorTypeService sensorTypeService;

	/** 安装图片业务接口 **/
	private PictureService pictureService;

	/** 监测装置业务接口 **/
	private SensorService sensorService;

	/** 地质监测参数业务接口 **/
	private LandslideParameterService landslideParameterService;

	/** 杆塔倾斜参数业务接口 **/
	private TowerTiltParameterService towerTiltParameterService;

	private TowerTiltSamplingService towerTiltSamplingService;
	private LandslideSamplingService landslideSamplingService;
	private WeatherSamplingService weatherSamplingService;

	/** 录像文件信息 */
	private RecordFileService recordFileService;

	public String mainFrame() {
		Integer onlineCount = 0;
		Integer sensorCount = 0;
		// 在线列表
		List<OnlineDeviceStatus> onlineDevices = this.onlineDeviceStatusService
				.getOnlineDeviceStatus();

		List<MainPageInfo> mainPageInfos = new ArrayList<MainPageInfo>();

		List<SensorType> sensorTypes = this.sensorTypeService
				.getAllSensorTypes();

		Iterator<SensorType> it = sensorTypes.iterator();
		int i = 0;
		while (it.hasNext()) {
			SensorType sensorType = it.next();
			MainPageInfo mainPageInfo = new MainPageInfo();

			List<GroupPage> gruopPages = new ArrayList<GroupPage>();

			request.setAttribute("sensorType", sensorType);
			List<Sensor> sensors = this.sensorTypeService
					.getAllSensors(sensorType);
			Iterator<Sensor> iterator = sensors.iterator();

			Integer l_onlineCount = 0;
			Integer l_sensorCount = 0;

			while (iterator.hasNext()) {
				Sensor sensor = iterator.next();
				sensorCount++;
				l_sensorCount++;


				GroupPage groupPage = getSensorGroupData(sensor, onlineDevices);
				if (onlineDevices == null) {
					// System.out.println("sssssss");
				}
				if (groupPage.getState()) {
					onlineCount++;
					l_onlineCount++;
				}
				gruopPages.add(groupPage);
			}

			String url = String.format(
					"<a ab='%d' href='${Url}' target='_parent'>%s</a>", i++,
					sensorType.getSensorTypeName());

			mainPageInfo.setSensorType(sensorType);
			mainPageInfo.setGroupPage(gruopPages);
			String msg = String.format("总数:%d   在线:%d    在线率:%.2f%%",
					l_sensorCount, l_onlineCount, (double) l_onlineCount
							/ l_sensorCount * 100);
			mainPageInfo.setMessage(msg);

			mainPageInfos.add(mainPageInfo);
		}
		request.setAttribute("list", mainPageInfos);
		String msg;
		if (sensorCount != 0) {
			msg = String.format("总数:%d   在线:%d    在线率:%.2f%%", sensorCount,
					onlineCount, (double) onlineCount / sensorCount * 100);

		} else {
			msg = "无监测设备";
		}
		request.setAttribute("stateMessage", msg);

		return SUCCESS;
		/*
		 * String pageNo = request.getParameter("pn"); String pageSize =
		 * request.getParameter("ps");
		 * 
		 * if (null == pageNo || pageNo.isEmpty()) { pageNo = "1"; }
		 * 
		 * if (null == pageSize || pageSize.isEmpty()) { pageSize =
		 * String.valueOf(SystemConfig.PAGE_SIZE); } // 查询天数 int warningDays =
		 * BusinessConstants.LATEST_WARNING_SPAN; // 开始结束时间 Date endTime = new
		 * Date(); Date beginTime = new Date(endTime.getTime() - warningDays *
		 * 24 * 60 60 * 1000); // 查询 Page<Warning> page =
		 * warningService.getPageWarningsBySensor(null, null, null, beginTime,
		 * endTime, 0, Long.valueOf(pageNo), Long.valueOf(pageSize));
		 * request.setAttribute("pageData", page); return SUCCESS;
		 */
	}

	public String lineGroup() {

		Integer onlineCount = 0;
		Integer sensorCount = 0;
		// 在线列表
		List<OnlineDeviceStatus> onlineDevices = this.onlineDeviceStatusService
				.getOnlineDeviceStatus();

		String lineId = request.getParameter("id");
		if (lineId == null || "".equals(lineId)) {
			return INPUT;
		}
		Line line = this.lineService.getLine(lineId);

		request.setAttribute("line", line);
		List<GroupPage> gruopPages = new ArrayList<GroupPage>();

		List<Tower> towers = this.lineService.getAllTowers(line);

		Iterator<Tower> it = towers.iterator();
		while (it.hasNext()) {
			Tower tower = it.next();

			List<Sensor> sensors = this.towerService.getAllSensors(tower);

			Iterator<Sensor> iterator = sensors.iterator();

			while (iterator.hasNext()) {
				Sensor sensor = iterator.next();
				sensorCount++;
				GroupPage groupPage = getSensorGroupData(sensor, onlineDevices);
				if (groupPage.getState()) {
					onlineCount++;
				}
				gruopPages.add(groupPage);
			}
		}
		request.setAttribute("list", gruopPages);
		if (sensorCount != 0) {
			String msg = String.format("总数:%d   在线:%d    在线率:%.2f%%",
					sensorCount, onlineCount, (double) onlineCount
							/ sensorCount * 100);
			request.setAttribute("stateMessage", msg);
		}
		return SUCCESS;
	}

	public String sensorTypeGroup() {

		Integer onlineCount = 0;
		Integer sensorCount = 0;
		// 在线列表
		List<OnlineDeviceStatus> onlineDevices = this.onlineDeviceStatusService
				.getOnlineDeviceStatus();

		String sensorTypeId = request.getParameter("id");
		if (sensorTypeId == null || "".equals(sensorTypeId)) {
			return INPUT;
		}

		SensorType sensorType = this.sensorTypeService
				.getSensorType(sensorTypeId);

		request.setAttribute("sensorType", sensorType);

		List<Sensor> sensors = this.sensorTypeService.getAllSensors(sensorType);
		Iterator<Sensor> iterator = sensors.iterator();
		List<GroupPage> gruopPages = new ArrayList<GroupPage>();

		while (iterator.hasNext()) {
			Sensor sensor = iterator.next();
			sensorCount++;
			GroupPage groupPage = getSensorGroupData(sensor, onlineDevices);
			if (groupPage.getState()) {
				onlineCount++;
			}
			gruopPages.add(groupPage);
		}
		request.setAttribute("list", gruopPages);
		if (sensorCount != 0) {
			String msg = String.format("总数:%d   在线:%d    在线率:%.2f%%",
					sensorCount, onlineCount, (double) onlineCount
							/ sensorCount * 100);
			request.setAttribute("stateMessage", msg);
		}

		return SUCCESS;
	}

	private GroupPage getSensorGroupData(Sensor sensor,
			List<OnlineDeviceStatus> onlineDevices) {
		
		GroupPage groupPage = new GroupPage();
		groupPage.setSensor(sensor);
		groupPage.setPictureData(false);

		Picture picture = pictureService.getDefaultShowPicture(sensor, 1);

		if (picture == null) {
			groupPage
					.setPictureFileName1("/resource/images/upload/default.png");
		} else {
			groupPage.setPictureFileName1(picture.getFileName());
		}

		picture = pictureService.getDefaultShowPicture(sensor, 2);

		if (picture == null) {
			groupPage
					.setPictureFileName2("/resource/images/upload/default.png");
		} else {
			groupPage.setPictureFileName2(picture.getFileName());
		}

		// 处理在线

		groupPage.setState(false);
		for (OnlineDeviceStatus ods : onlineDevices) {
			if (ods.getSensor() != null
					&& ods.getSensor().getSensorCode()
							.equals(sensor.getSensorCode())) {
				groupPage.setState(true);
				break;
			}
		}

		// 处理报警 和当前值
		// 微气象监测
		groupPage.setWarning("正常");
		SimpleDateFormat fds = new SimpleDateFormat("HH:mm:ss");
		List<DataItem> dataItems = new ArrayList<DataItem>();
		DataItem data;
		if ("A1021".equals(sensor.getSensorType().getSensorTypeCode())) {
			String msg = "";
			WeatherSampling weatherSampling = this.weatherSamplingService
					.getLastWeather(sensor);			
			if (weatherSampling != null) {
				data = new DataItem();
				data.setName("平均风速");
				data.setValue(String.valueOf(weatherSampling
						.getAverageWindSpeed10min()) + "(m/s)");
				dataItems.add(data);

				data = new DataItem();
				data.setName("最大风速");
				data.setValue(String.valueOf(weatherSampling.getMaxWindSpeed())
						+ "(m/s)");
				dataItems.add(data);

				data = new DataItem();
				data.setName("风向");
				data.setValue(weatherSampling.getWindDirectionName());
				dataItems.add(data);

				data = new DataItem();
				data.setName("气温");
				data.setValue(String.valueOf(weatherSampling.getTemperature())
						+ "(℃)");
				dataItems.add(data);

				//data = new DataItem();
				//data.setName("湿度");
				//data.setValue(String.valueOf(weatherSampling.getHumidity())	+ "(%)");
				//dataItems.add(data);

				data = new DataItem();
				data.setName("雨量");
				data.setValue(String.valueOf(weatherSampling.getDailyRainfall())
						+ "(mm)");
				dataItems.add(data);

				data = new DataItem();
				data.setName("降水强度");
				data.setValue(String.valueOf(weatherSampling
						.getPrecipitationIntensity()) + "(mm/min)");
				dataItems.add(data);

				//data = new DataItem();
				//data.setName("采集时间");
				//data.setValue(fds.format(weatherSampling.getSamplingTime()));
				//dataItems.add(data);
			} else {
				if (msg.length() != 0) {
					msg += "<br/>";
				}
				msg += "微气象采集失败";			
			}
			
			TowerTiltParameter towerTiltParameter = this.towerTiltParameterService
					.getTowerTiltParameterBySensor(sensor);
			if (towerTiltParameter != null) {

				if (!(towerTiltParameter.getCurrentXState() == null || 0 == towerTiltParameter
						.getCurrentXState())) {
					msg = String.format("X方向%d级预警",
							towerTiltParameter.getCurrentXState());
				}
				if (!(towerTiltParameter.getCurrentYState() == null || 0 == towerTiltParameter
						.getCurrentYState())) {
					if (msg.length() != 0) {
						msg += "<br/>";
					}
					msg += String.format("Y方向%d级预警",
							towerTiltParameter.getCurrentYState());

				}

			}
			TowerTiltSampling towerTiltSampling = this.towerTiltSamplingService
					.getLastTowerTilt(sensor);
			if (towerTiltSampling != null) {
				/*	
				data = new DataItem();
				data.setName("倾斜度");
				data.setValue(String.valueOf(towerTiltSampling.getInclination())
						+ "(mm/m)");

				dataItems.add(data);

				data = new DataItem();
				data.setName("顺线倾斜度");
				data.setValue(String.valueOf(towerTiltSampling
						.getGradientAlongLines()) + "(mm/m)");

				dataItems.add(data);

				data = new DataItem();
				data.setName("横向倾斜度");
				data.setValue(String.valueOf(towerTiltSampling.getLateralTilt())
						+ "(mm/m)");
				dataItems.add(data);
				 */
				data = new DataItem();
				data.setName("顺线倾斜角");
				data.setValue(String.valueOf(towerTiltSampling.getAngleX())
						+ "(°)");
				dataItems.add(data);
				
				data = new DataItem();
				data.setName("横向倾斜角");
				data.setValue(String.valueOf(towerTiltSampling.getAngleY())
						+ "(°)");
				dataItems.add(data);

				data = new DataItem();
				data.setName("采集时间");
				data.setValue(fds.format(towerTiltSampling.getSamplingTime()));
				dataItems.add(data);
			} else {
				if (msg.length() != 0) {
					msg += "<br/>";
				}
				msg += "杆塔倾斜采集失败";
			}
			if (msg != "") {
				groupPage.setWarning(msg);
			}
		} else if ("A1001".equals(sensor.getSensorType().getSensorTypeCode())) {
			WeatherSampling weatherSampling = this.weatherSamplingService
					.getLastWeather(sensor);
			if (weatherSampling != null) {
				data = new DataItem();
				data.setName("平均风速");
				data.setValue(String.valueOf(weatherSampling
						.getAverageWindSpeed10min()) + "(m/s)");
				dataItems.add(data);

				data = new DataItem();
				data.setName("最大风速");
				data.setValue(String.valueOf(weatherSampling.getMaxWindSpeed())
						+ "(m/s)");
				dataItems.add(data);

				data = new DataItem();
				data.setName("风向");
				data.setValue(weatherSampling.getWindDirectionName());
				dataItems.add(data);

				data = new DataItem();
				data.setName("气温");
				data.setValue(String.valueOf(weatherSampling.getTemperature())
						+ "(℃)");
				dataItems.add(data);

				data = new DataItem();
				data.setName("湿度");
				data.setValue(String.valueOf(weatherSampling.getHumidity())
						+ "(%)");
				dataItems.add(data);

				data = new DataItem();
				data.setName("雨量");
				data.setValue(String.valueOf(weatherSampling.getDailyRainfall())
						+ "(mm)");
				dataItems.add(data);

				data = new DataItem();
				data.setName("降水强度");
				data.setValue(String.valueOf(weatherSampling
						.getPrecipitationIntensity()) + "(mm/min)");
				dataItems.add(data);

				data = new DataItem();
				data.setName("采集时间");
				data.setValue(fds.format(weatherSampling.getSamplingTime()));
				dataItems.add(data);
			} else {
				groupPage.setWarning("采集失败");
			}
			// 杆塔倾斜
		} else if ("A1003".equals(sensor.getSensorType().getSensorTypeCode())) {
			TowerTiltParameter towerTiltParameter = this.towerTiltParameterService
					.getTowerTiltParameterBySensor(sensor);
			String msg = "";
			if (towerTiltParameter != null) {

				if (!(towerTiltParameter.getCurrentXState() == null || 0 == towerTiltParameter
						.getCurrentXState())) {
					msg = String.format("X方向%d级预警",
							towerTiltParameter.getCurrentXState());
				}
				if (!(towerTiltParameter.getCurrentYState() == null || 0 == towerTiltParameter
						.getCurrentYState())) {
					if (msg.length() != 0) {
						msg += "<br/>";
					}
					msg += String.format("Y方向%d级预警",
							towerTiltParameter.getCurrentYState());

				}

			}
			TowerTiltSampling towerTiltSampling = this.towerTiltSamplingService
					.getLastTowerTilt(sensor);
			if (towerTiltSampling != null) {

				data = new DataItem();
				data.setName("倾斜度");
				data.setValue(String.valueOf(towerTiltSampling.getInclination())
						+ "(mm/m)");

				dataItems.add(data);

				data = new DataItem();
				data.setName("顺线倾斜度");
				data.setValue(String.valueOf(towerTiltSampling
						.getGradientAlongLines()) + "(mm/m)");

				dataItems.add(data);

				data = new DataItem();
				data.setName("横向倾斜度");
				data.setValue(String.valueOf(towerTiltSampling.getLateralTilt())
						+ "(mm/m)");
				dataItems.add(data);

				data = new DataItem();
				data.setName("顺线倾斜角");
				data.setValue(String.valueOf(towerTiltSampling.getAngleX())
						+ "(°)");
				dataItems.add(data);

				data = new DataItem();
				data.setName("横向倾斜角");
				data.setValue(String.valueOf(towerTiltSampling.getAngleY())
						+ "(°)");
				dataItems.add(data);

				data = new DataItem();
				data.setName("采集时间");
				data.setValue(fds.format(towerTiltSampling.getSamplingTime()));
				dataItems.add(data);
			} else {
				if (msg.length() != 0) {
					msg += "<br/>";
				}
				msg += "采集失败";
			}
			if (msg != "") {
				groupPage.setWarning(msg);
			}

			// 视频
		} else if ("A1005".equals(sensor.getSensorType().getSensorTypeCode())) {
			groupPage.setPictureData(true);
			Date endTime = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(endTime);
			calendar.add(Calendar.DATE, -3);
			Date beginTime = calendar.getTime();

			Page<RecordFile> recordFiles = recordFileService
					.getPageRecordFiles(sensor, 1, beginTime, endTime, 1, 3);
			Collection<RecordFile> fileList = recordFiles.getRecords();

			Iterator<RecordFile> iterator = fileList.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				RecordFile recordFile = iterator.next();
				data = new DataItem();
				// data.setName("采集时间");
				data.setUrl(recordFile.getFileName());
				dataItems.add(data);
				i++;
				if (i == 3)
					break;
			}

			String msg = "";
			if (msg != "") {
				groupPage.setWarning(msg);
			}
			// 地质灾害监测
		} else if ("A1010".equals(sensor.getSensorType().getSensorTypeCode())) {
			String msg = "";
			LandslideParameter landslideParameter = this.landslideParameterService
					.getLandslideParameterBySensor(sensor);
			if (landslideParameter != null) {
				List<LandslideParameterDetail> details = landslideParameter
						.getLandslideParameterDetails();
				if (details != null) {
					for (int i = 0; i < landslideParameter.getSampleNum(); i++) {
						LandslideParameterDetail detail = details.get(i);
						if (!(detail.getCurrentState() == null || 0 == detail
								.getCurrentState())) {
							if (msg.length() != 0) {
								msg += "<br/>";
							}
							msg += String.format("<br/>监测点%d发生%d级预警",
									detail.getPointNo(),
									detail.getCurrentState());
						}
					}
					if (msg != "") {
						groupPage.setWarning(msg);
					}
				}
			}

			LandslideSampling landslideSampling = this.landslideSamplingService
					.getLastLandslideDetail(sensor);
			if (landslideSampling != null) {
				for (int i = 0; i < landslideSampling.getSampleNum(); i++) {
					LandslideSamplingDetail detail = landslideSampling
							.getLandslideSamplingDetails().get(i);
					data = new DataItem();
					data.setName(String.format("监测点%d深度", detail.getPointNo()));
					data.setValue(String.valueOf(detail.getRelativeDepth())
							+ "(m)");
					dataItems.add(data);

					data = new DataItem();
					data.setName(String.format("监测点%dX倾角", detail.getPointNo()));
					data.setValue(String.valueOf(detail.getAngleX()) + "(°)");
					dataItems.add(data);

					data = new DataItem();
					data.setName(String.format("监测点%dX位移", detail.getPointNo()));
					data.setValue(String.valueOf(detail.getDisplacementX())
							+ "(mm)");
					dataItems.add(data);

				}
				data = new DataItem();
				data.setName("采集时间");
				data.setValue(fds.format(landslideSampling.getSamplingTime()));
				dataItems.add(data);
			} else {

				if (msg.length() != 0) {
					msg += "<br/>";
				}
				msg += "采集失败";
			}
			if (msg != "") {
				groupPage.setWarning(msg);
			}
		}
		
		groupPage.setDataItem(dataItems);
		return groupPage;
	}

	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	public void setWarningService(WarningService warningService) {
		this.warningService = warningService;
	}

	public void setOnlineDeviceStatusService(
			OnlineDeviceStatusService onlineDeviceStatusService) {
		this.onlineDeviceStatusService = onlineDeviceStatusService;
	}

	public void setLineService(LineService lineService) {
		this.lineService = lineService;
	}

	public void setTowerService(TowerService towerService) {
		this.towerService = towerService;
	}

	public void setSensorTypeService(SensorTypeService sensorTypeService) {
		this.sensorTypeService = sensorTypeService;
	}

	public void setLandslideParameterService(
			LandslideParameterService landslideParameterService) {
		this.landslideParameterService = landslideParameterService;
	}

	public void setTowerTiltParameterService(
			TowerTiltParameterService towerTiltParameterService) {
		this.towerTiltParameterService = towerTiltParameterService;
	}

	public void setTowerTiltSamplingService(
			TowerTiltSamplingService towerTiltSamplingService) {
		this.towerTiltSamplingService = towerTiltSamplingService;
	}

	public void setLandslideSamplingService(
			LandslideSamplingService landslideSamplingService) {
		this.landslideSamplingService = landslideSamplingService;
	}

	public void setWeatherSamplingService(
			WeatherSamplingService weatherSamplingService) {
		this.weatherSamplingService = weatherSamplingService;
	}

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	public void setRecordFileService(RecordFileService recordFileService) {
		this.recordFileService = recordFileService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
