/*
 * Project platform
 *
 * Class RealTimeTowerTiltAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-7-8 下午04:23:44
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.landslide.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.archive.service.SensorService;
import com.yixin.A1000.comm.exception.ClosedConnectionException;
import com.yixin.A1000.comm.exception.ProtocolException;
import com.yixin.A1000.comm.exception.ReceiveDataException;
import com.yixin.A1000.comm.exception.SendDataException;
import com.yixin.A1000.comm.exception.TCPServerException;
import com.yixin.A1000.comm.exception.TimeOutException;
import com.yixin.A1000.landslide.model.LandslideParameter;
import com.yixin.A1000.landslide.model.LandslideParameterDetail;
import com.yixin.A1000.landslide.model.LandslideSampling;
import com.yixin.A1000.landslide.model.LandslideSamplingDetail;
import com.yixin.A1000.landslide.service.LandslideParameterService;
import com.yixin.A1000.landslide.service.RealTimeLandslideService;

/**
 * 地质滑坡数据实时召测的Action处理类
 * 
 * 
 * @version v1.0.0
 * @author 
 */
public class RealTimeLandslideAction extends ActionSupport implements ServletRequestAware {

	/** 类的序列化版本ID */
	private static final long serialVersionUID = 4433270820058276772L;

	/** 用于保存在request域的错误提示信息 */
	private static final String ERROR_MESSAGE_ATTR_NAME = "errorMessage";

	/** 用于保存在request域的成功提示信息 */
	private static final String SUCCESS_MESSAGE_ATTR_NAME = "succMessage";

	/** request域对象 */
	private HttpServletRequest request;

	/** 地质滑坡数据召测业务接口 */
	private RealTimeLandslideService realTimeLandslideService;
	
	/** 参数服务类 */
	private LandslideParameterService landslideParameterService;		

	/** 监测装置业务逻辑接口 */
	private SensorService sensorService;

	
	/** 要进行操作的监测代理ID */
	private String id;

	/**
	 * 获取 要进行操作的监测代理ID
	 * 
	 * @return 要进行操作的监测代理ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 要进行操作的监测代理ID
	 * 
	 * @param id
	 *            要进行操作的监测代理ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 设置 地质滑坡数据召测业务接口
	 * 
	 * @param realTimeLandslideService
	 *            地质滑坡数据召测业务接口
	 */
	public void setRealTimeLandslideService(RealTimeLandslideService realTimeLandslideService) {
		this.realTimeLandslideService = realTimeLandslideService;
	}

	/**
	 * 设置 参数服务类
	 * 
	 * @param commService
	 *            参数服务类
	 */
	public void setLandslideParameterService(LandslideParameterService landslideParameterService) {
		this.landslideParameterService = landslideParameterService;
	}
	
	/**
	 * 设置 监测装置业务逻辑接口
	 * 
	 * @param sensorService
	 *            监测装置业务逻辑接口
	 */
	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
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



	/**
	 * 跳转至数据召测页面
	 * 
	 * 
	 * @return 结果页面
	 */
	public String realTime() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		//填充参数
		
		return SUCCESS;
	}

	/**
	 * 检查所要操作的监测装置是否存在。存在则返回该对象，否则返回null
	 * 
	 * @return 存在则返回Sensor对象，否则返回null
	 */
	private Sensor checkSensor() {
		if (null == this.id || 0 == this.id.trim().length()) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "请先选择要进行操作的对象");
			return null;
		}
		Sensor sensor = this.sensorService.getSensor(this.id);
		if (null == sensor) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "监测装置不存在，可能已经被删除");
			return null;
		}
		return sensor;
	}

	public String samplingLandslide() {
		Sensor sensor = this.checkSensor();
		if (null == sensor) {
			return INPUT;
		}
		try {
			LandslideSampling landslideSampling = this.realTimeLandslideService.samplingLandslide(sensor);
			
			LandslideParameter landslideParameter = landslideParameterService.getLandslideParameterBySensor(sensor);
			landslideSampling.setAllDepth(landslideParameter.getAllDepth());
 
			Double allDepth = landslideParameter.getAllDepth();
			for(LandslideSamplingDetail detail:landslideSampling.getLandslideSamplingDetails()){
				LandslideParameterDetail paramDetail = null;
				for(LandslideParameterDetail d : landslideParameter.getLandslideParameterDetails()){
					if(d.getPointNo().equals(detail.getPointNo())){
						paramDetail = d;
						break;
					}
				}
				if(paramDetail != null){
					//绝对深度
					detail.setRelativeDepth(paramDetail.getRelativeDepth());
					//相对于下一点的深度
					Double  rd = allDepth - paramDetail.getRelativeDepth();
					allDepth = paramDetail.getRelativeDepth();
					//计算
					Double dx = rd*1000*Math.sin(Math.toRadians(detail.getAngleX()));
					Double dy = rd*1000*Math.sin(Math.toRadians(detail.getAngleY()));
					Double d = Math.sqrt(dx*dx+dy*dy);
					detail.setDisplacementX(dx);
					detail.setDisplacementY(dy);
					detail.setDisplacement(d);
				}else{
					landslideSampling.getLandslideSamplingDetails().remove(detail);
					break;
				}
			}			
			
			//把顺序调过来
			List<LandslideSamplingDetail> details = new ArrayList<LandslideSamplingDetail>();
			for(LandslideSamplingDetail d : landslideSampling.getLandslideSamplingDetails()){
				details.add(0,d);
			}			
			landslideSampling.setLandslideSamplingDetails(details);
			
			request.setAttribute("landslide", landslideSampling);
			request.setAttribute("landslideParameter", landslideParameter);
		} catch (TimeOutException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "服务器未响应");
		} catch (ClosedConnectionException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "与服务器间未建立连接，或者连接已经断开");
		} catch (SendDataException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "向服务器写数据时发生错误");
		} catch (ProtocolException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, e.getMessage());
		} catch (TCPServerException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "服务器返回错误信息：\\r\\n" + e.getMessage());
		} catch (ReceiveDataException e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "从服务器接收数据发生错误");
		} catch (Exception e) {
			request.setAttribute(ERROR_MESSAGE_ATTR_NAME, "系统发生内部错误\\r\\n" + e.getMessage());
		}
		return SUCCESS;
	}
}