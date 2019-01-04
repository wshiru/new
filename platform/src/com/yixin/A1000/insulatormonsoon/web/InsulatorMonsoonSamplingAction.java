/*
 * Project platform
 *
 * Classname InsulatorMonsoonSamplingAction.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-23 16:09
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */

package com.yixin.A1000.insulatormonsoon.web;

import java.util.Iterator;
import java.util.List;

import org.jfree.data.time.Minute;

import com.yixin.A1000.base.web.MonsoonSamplingAction;
import com.yixin.A1000.insulatormonsoon.model.InsulatorMonsoonSampling;
import com.yixin.framework.util.jfreechart.JFreeChartFactory;

/**
 * 绝缘子串风偏Action类
 * @version v1.0.0
 * @author 
 *
 */
public class InsulatorMonsoonSamplingAction extends MonsoonSamplingAction<InsulatorMonsoonSampling> {

	/** 序列版本ID  */
	private static final long serialVersionUID = 6200408515125510277L;

	/*
	 * (non-Javadoc)
	 * @see com.yixin.framework.base.web.BaseAction#list()
	 */
	@Override
	public String list() {
		page = this.samplingService.getPageSamplings(
					sensor, beginTime, endTime, getPageNo(), getPageSize());
		return super.list();
	}

	/*
	 * (non-Javadoc)
	 * @see com.yixin.ca2000.base.web.BaseSamplingAction#chart()
	 */
	@Override
	public String chart() {
		
		//初始化曲线图数据
		initChartDatas();
		
		/* 填充数据内容*/
		List<InsulatorMonsoonSampling> iMonsoonSamplings = this.samplingService.getSamplings(sensor, beginTime, endTime);	
		Iterator<InsulatorMonsoonSampling> iterator = iMonsoonSamplings.iterator();		
		while (iterator.hasNext()) {
			InsulatorMonsoonSampling sampling = (InsulatorMonsoonSampling) iterator.next();
			Minute t = new Minute(sampling.getSamplingTime());
			int chatDataCount = 0;
			if(isShowWA()){
				Double wa = sampling.getWindAngle();
				if(null != wa)
					chartDatas.get(chatDataCount).getDatasets().put(t, wa);
				chatDataCount ++;
			}
			if(isShowA()){
				Double angle = sampling.getAngle();
				if(null != angle)
					chartDatas.get(chatDataCount).getDatasets().put(t, angle);
				chatDataCount ++;
			}
			if(isShowMCP()){
				Double mcp = sampling.getMinClearanceParams();
				if(mcp != null)
					chartDatas.get(chatDataCount).getDatasets().put(t, mcp);
			}
		}
		
		//生成曲线图
		this.chart = JFreeChartFactory.createTimeSeriesChart("绝缘子串风偏曲线图", createTimespanSubTitles(), "采样时间", true, chartDatas);
		
		return super.chart();
	}
	
	
}
