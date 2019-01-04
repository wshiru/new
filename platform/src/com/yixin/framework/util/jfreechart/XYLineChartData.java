/*
 * Project dolphin
 *
 * Class ChartFactory.java
 * 
 * Version 1.0
 * 
 * Creator 

 * CreateAt Jul 20, 2010 3:02:26 PM
 *
 * ModifiedBy 
 * ModifyAt 
 * ModifyDescription 
 * 
 * Copyright (c) 2009-2010 亿鑫新能源 版权所有

 */
package com.yixin.framework.util.jfreechart;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

/**
 * 绘制JFreeChart XY曲线图时所需要的数据
 * 
 * @version v1.0
 * @author 

 */
public class XYLineChartData {
	
	/** 图例文本 */
	private String legendText = "";

	/** 曲线的颜色。包括曲线、图例等。默认为黑色 */
	private Color color = new Color(0, 0, 0);

	/** 图例与曲线是否显示。true-显示；false-不显示 */
	private boolean visible = true;

	/** 曲线的数据集 */
	private HashMap<Integer, List<Number>> datasets = new HashMap<Integer, List<Number>>();

	/**
	 * 返回图例文本
	 * 
	 * @return 图例文本
	 */
	public String getLegendText() {
		return this.legendText;
	}

	/**
	 * 设置图例文本
	 * 
	 * @param legendText
	 *            图例文本
	 */
	public void setLegendText(String legendText) {
		this.legendText = legendText;
	}

	/**
	 * 返回曲线的颜色。包括曲线、纵坐标文本、纵坐标刻度、图例等。

	 * 
	 * @return 曲线的颜色

	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * 设置曲线的颜色。包括曲线、纵坐标文本、纵坐标刻度、图例等。默认为黑色
	 * 
	 * @param color
	 *            曲线的颜色

	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * 返回图例与曲线是否显示。true-显示；false-不显示

	 * 
	 * @return 图例与曲线是否显示。true-显示；false-不显示

	 */
	public boolean isVisible() {
		return this.visible;
	}

	/**
	 * 设置图例与曲线是否显示。true-显示；false-不显示

	 * 
	 * @param visible
	 *            图例与曲线是否显示。true-显示；false-不显示

	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * 返回曲线的数据集
	 * 
	 * @return 曲线的数据集
	 */
	public HashMap<Integer, List<Number>> getDatasets() {
		return this.datasets;
	}

	/**
	 * 设置曲线的数据集
	 * 
	 * @param datasets
	 *            曲线的数据集
	 */
	public void setDatasets(HashMap<Integer, List<Number>> datasets) {
		this.datasets = datasets;
	}
}
