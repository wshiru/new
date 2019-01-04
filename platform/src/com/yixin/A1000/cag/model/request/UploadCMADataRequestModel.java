/*
 * Project ca2000
 *
 * Class UploadCMADataRequestModel.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-8-3 上午10:07:02
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.A1000.cag.model.request;

import java.util.ArrayList;
import java.util.List;

import com.yixin.A1000.insulatormonsoon.model.InsulatorMonsoonSampling;
import com.yixin.A1000.towertilt.model.TowerTiltSampling;
import com.yixin.A1000.weather.model.WeatherSampling;
import com.yixin.A1000.whitemonsoon.model.WhiteMonsoonSampling;
import com.yixin.A1000.wiresag.model.WireSagSampling;
import com.yixin.A1000.wiretemperature.model.WireTemperatureSampling;

/**
 * 上传监测数据的模型。存储解析后的信息。
 * 
 * @version v1.0.0
 * @author 
 */
public class UploadCMADataRequestModel {

	/** 微气象 */
	private List<WeatherSampling> weatherSamplings = new ArrayList<WeatherSampling>();

	/** 导线温度 */
	private List<WireTemperatureSampling> wireTemperatureSamplings = new ArrayList<WireTemperatureSampling>();

	/** 相间风偏 */
	private List<WhiteMonsoonSampling> whiteMonsoonSamplings = new ArrayList<WhiteMonsoonSampling>();

	/** 导线弧垂 */
	private List<WireSagSampling> wireSagSamplings = new ArrayList<WireSagSampling>();

	/** 杆塔倾斜 */
	private List<TowerTiltSampling> towerTiltSamplings = new ArrayList<TowerTiltSampling>();

	/** 绝缘子串风偏 */
	private List<InsulatorMonsoonSampling> insulatorMonsoonSamplings = new ArrayList<InsulatorMonsoonSampling>();

	/**
	 * 获取 微气象
	 * 
	 * @return 微气象
	 */
	public List<WeatherSampling> getWeatherSamplings() {
		return this.weatherSamplings;
	}

	/**
	 * 设置 微气象
	 * 
	 * @param weatherSamplings
	 *            微气象
	 */
	public void setWeatherSamplings(List<WeatherSampling> weatherSamplings) {
		this.weatherSamplings = weatherSamplings;
	}

	/**
	 * 获取 导线温度
	 * 
	 * @return 导线温度
	 */
	public List<WireTemperatureSampling> getWireTemperatureSamplings() {
		return this.wireTemperatureSamplings;
	}

	/**
	 * 设置 导线温度
	 * 
	 * @param wireTemperatureSamplings
	 *            导线温度
	 */
	public void setWireTemperatureSamplings(List<WireTemperatureSampling> wireTemperatureSamplings) {
		this.wireTemperatureSamplings = wireTemperatureSamplings;
	}

	/**
	 * 获取 相间风偏
	 * 
	 * @return 相间风偏
	 */
	public List<WhiteMonsoonSampling> getWhiteMonsoonSamplings() {
		return this.whiteMonsoonSamplings;
	}

	/**
	 * 设置 相间风偏
	 * 
	 * @param whiteMonsoonSamplings
	 *            相间风偏
	 */
	public void setWhiteMonsoonSamplings(List<WhiteMonsoonSampling> whiteMonsoonSamplings) {
		this.whiteMonsoonSamplings = whiteMonsoonSamplings;
	}

	/**
	 * 获取 导线弧垂
	 * 
	 * @return 导线弧垂
	 */
	public List<WireSagSampling> getWireSagSamplings() {
		return this.wireSagSamplings;
	}

	/**
	 * 设置 导线弧垂
	 * 
	 * @param wireSagSamplings
	 *            导线弧垂
	 */
	public void setWireSagSamplings(List<WireSagSampling> wireSagSamplings) {
		this.wireSagSamplings = wireSagSamplings;
	}

	/**
	 * 获取 杆塔倾斜
	 * 
	 * @return 杆塔倾斜
	 */
	public List<TowerTiltSampling> getTowerTiltSamplings() {
		return this.towerTiltSamplings;
	}

	/**
	 * 设置 杆塔倾斜
	 * 
	 * @param towerTiltSamplings
	 *            杆塔倾斜
	 */
	public void setTowerTiltSamplings(List<TowerTiltSampling> towerTiltSamplings) {
		this.towerTiltSamplings = towerTiltSamplings;
	}

	/**
	 * 获取 绝缘子串风偏
	 * 
	 * @return 绝缘子串风偏
	 */
	public List<InsulatorMonsoonSampling> getInsulatorMonsoonSamplings() {
		return this.insulatorMonsoonSamplings;
	}

	/**
	 * 设置 绝缘子串风偏
	 * 
	 * @param insulatorMonsoonSamplings
	 *            绝缘子串风偏
	 */
	public void setInsulatorMonsoonSamplings(List<InsulatorMonsoonSampling> insulatorMonsoonSamplings) {
		this.insulatorMonsoonSamplings = insulatorMonsoonSamplings;
	}
}
