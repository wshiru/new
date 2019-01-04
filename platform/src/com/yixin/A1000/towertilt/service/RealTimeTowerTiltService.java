/*
 * Project platform
 *
 * Class RealTimeTowerTiltService.java
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
package com.yixin.A1000.towertilt.service;

import com.yixin.A1000.archive.model.Sensor;
import com.yixin.A1000.comm.exception.ClosedConnectionException;
import com.yixin.A1000.comm.exception.SendDataException;
import com.yixin.A1000.comm.exception.TimeOutException;
import com.yixin.A1000.comm.protocol.ProtocolErrorCode;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;
import com.yixin.framework.system.model.User;

/**
 * 杆塔倾斜数据召测业务接口
 * 
 * @version v1.0.0
 * @author 
 */
public interface RealTimeTowerTiltService {

	/**
	 * 新增杆塔倾斜数据的召测任务
	 * 
	 * 
	 * @param sensor
	 *            监测装置
	 */
	public abstract void addRealTimeTask(User user, Sensor sensor);

	/**
	 * 采集杆塔倾斜数据
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
	public abstract TowerTiltSampling samplingTowerTilt(Sensor sensor);
}
