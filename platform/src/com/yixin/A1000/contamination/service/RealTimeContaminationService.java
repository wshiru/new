/*
 * Project platform
 *
 * Class RealTimeContaminationService.java
 * 
 * Version 1.0.0
 * 
 * Creator 

 * CreateAt 2011-7-8 下午04:15:12
 *
 * ModifiedBy 无

 * ModifyAt 无

 * ModifyDescription 无

 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有

 */
package com.yixin.A1000.contamination.service;

import com.sun.mail.iap.ProtocolException;
import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.comm.exception.ClosedConnectionException;
import com.yixin.A1000.comm.exception.SendDataException;
import com.yixin.A1000.comm.exception.TimeOutException;
import com.yixin.A1000.contamination.model.ContaminationSampling;
import com.yixin.framework.system.model.User;

/**
 * 污秽度监测数据召测业务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface RealTimeContaminationService {

	/**
	 * 新增污秽度监测数据的召测任务
	 * 
	 * 
	 * @param sensor
	 *            监测装置
	 */
	public abstract void addRealTimeTask(User user, Sensor sensor);

	/**
	 * 采集污秽度监测数据
	 * 
	 * @param sensor
	 *            监测装置
	 * @exception TimeOutException
	 *                接收数据等待的时间超出所设置值timeout时，抛出该异常
	 * @exception ClosedConnectionException
	 *                与服务器间未建立连接，或者连接已经断开时，抛出该异常
	 * @exception SendDataException
	 *                向服务器发送数据发生错误时，抛出该异常
	 * @exception ProtocolException
	 *                协议处理错误
	 * @see com.yixin.A1000.comm.exception.ProtocolException
	 * @see com.yixin.A1000.comm.protocol.ProtocolErrorCode
	 * @return
	 */
	public abstract ContaminationSampling samplingContamination(Sensor sensor);
}
