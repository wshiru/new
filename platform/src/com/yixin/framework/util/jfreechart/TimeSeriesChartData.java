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
import java.util.Hashtable;

import org.jfree.chart.axis.AxisLocation;
import org.jfree.data.time.RegularTimePeriod;

/**
 * 绘制JFreeChart时间曲线图时所需要的数据
 * 
 * @version v1.0
 * @author 

 */
public class TimeSeriesChartData {

	/** 纵坐标文本 */
	private String ordinateText = "";

	/** 纵坐标位置。默认为AxisLocation.BOTTOM_OR_LEFT */
	private AxisLocation ordinateLocation = AxisLocation.BOTTOM_OR_LEFT;

	/** 图例文本 */
	private String legendText = "";

	/** 曲线的颜色。包括曲线、纵坐标文本、纵坐标刻度、图例等。默认为黑色 */
	private Color color = new Color(0, 0, 0);

	/** 图例与曲线是否显示。true-显示；false-不显示 */
	private boolean visible = true;

	/** 纵坐标轴是否可见（纵坐标轴 = 纵坐标轴线条 + 纵坐标轴标尺值 + 纵坐标轴标尺）。true-显示；false-不显示 */
	private boolean ordinateVisible = true;

	/** 纵坐标轴线条是否可见。true-显示；false-不显示 */
	private boolean ordinateLineVisible = true;

	/** 纵坐标轴标尺刻度是否显示。true-显示；false-不显示 */
	private boolean ordinateMarksVisible = true;

	/** 纵坐标轴标尺值是否显示。true-显示；false-不显示 */
	private boolean ordinateLabelsVisible = true;

	/** 曲线的数据集 */
	private Hashtable<RegularTimePeriod, Number> datasets = new Hashtable<RegularTimePeriod, Number>();

	/**
	 * 返回纵坐标文本

	 * 
	 * @return 纵坐标文本

	 */
	public String getOrdinateText() {
		return this.ordinateText;
	}

	/**
	 * 设置纵坐标文本

	 * 
	 * @param ordinateText
	 *            纵坐标文本

	 */
	public void setOrdinateText(String ordinateText) {
		this.ordinateText = ordinateText;
	}

	/**
	 * 返回纵坐标位置。默认为AxisLocation.BOTTOM_OR_LEFT
	 * 
	 * @return 纵坐标位置。默认为AxisLocation.BOTTOM_OR_LEFT
	 */
	public AxisLocation getOrdinateLocation() {
		return this.ordinateLocation;
	}

	/**
	 * 设置纵坐标位置。默认为AxisLocation.BOTTOM_OR_LEFT
	 * 
	 * @param ordinateLocation
	 *            纵坐标位置。默认为AxisLocation.BOTTOM_OR_LEFT
	 */
	public void setOrdinateLocation(AxisLocation ordinateLocation) {
		this.ordinateLocation = ordinateLocation;
	}

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
	 * 返回纵坐标轴是否可见（纵坐标轴 = 纵坐标轴线条 + 纵坐标轴标尺值 + 纵坐标轴标尺）。true-显示；false-不显示

	 * 
	 * @return 纵坐标轴是否可见（纵坐标轴 = 纵坐标轴线条 + 纵坐标轴标尺值 + 纵坐标轴标尺）。true-显示；false-不显示

	 */
	public boolean isOrdinateVisible() {
		return this.ordinateVisible;
	}

	/**
	 * 设置纵坐标轴是否可见（纵坐标轴 = 纵坐标轴线条 + 纵坐标轴标尺值 + 纵坐标轴标尺）。true-显示；false-不显示

	 * 
	 * @param ordinateVisible
	 *            纵坐标轴是否可见（纵坐标轴 = 纵坐标轴线条 + 纵坐标轴标尺值 + 纵坐标轴标尺）。true-显示；false-不显示

	 */
	public void setOrdinateVisible(boolean ordinateVisible) {
		this.ordinateVisible = ordinateVisible;
	}

	/**
	 * 返回纵坐标轴线条是否可见。true-显示；false-不显示

	 * 
	 * @return 纵坐标轴线条是否可见。true-显示；false-不显示

	 */
	public boolean isOrdinateLineVisible() {
		return this.ordinateLineVisible;
	}

	/**
	 * 设置纵坐标轴线条是否可见。true-显示；false-不显示

	 * 
	 * @param ordinateLineVisible
	 *            纵坐标轴线条是否可见。true-显示；false-不显示

	 */
	public void setOrdinateLineVisible(boolean ordinateLineVisible) {
		this.ordinateLineVisible = ordinateLineVisible;
	}

	/**
	 * 返回纵坐标轴标尺刻度是否显示。true-显示；false-不显示

	 * 
	 * @return 纵坐标轴标尺刻度是否显示。true-显示；false-不显示

	 */
	public boolean isOrdinateMarksVisible() {
		return this.ordinateMarksVisible;
	}

	/**
	 * 设置纵坐标轴标尺刻度是否显示。true-显示；false-不显示

	 * 
	 * @param ordinateMarksVisible
	 *            纵坐标轴标尺刻度是否显示。true-显示；false-不显示

	 */
	public void setOrdinateMarksVisible(boolean ordinateMarksVisible) {
		this.ordinateMarksVisible = ordinateMarksVisible;
	}

	/**
	 * 返回纵坐标轴标尺值是否显示。true-显示；false-不显示

	 * 
	 * @return 纵坐标轴标尺值是否显示。true-显示；false-不显示

	 */
	public boolean isOrdinateLabelsVisible() {
		return this.ordinateLabelsVisible;
	}

	/**
	 * 设置纵坐标轴标尺值是否显示。true-显示；false-不显示

	 * 
	 * @param ordinateLabelsVisible
	 *            纵坐标轴标尺值是否显示。true-显示；false-不显示

	 */
	public void setOrdinateLabelsVisible(boolean ordinateLabelsVisible) {
		this.ordinateLabelsVisible = ordinateLabelsVisible;
	}

	/**
	 * 返回曲线的数据集
	 * 
	 * @return 曲线的数据集
	 */
	public Hashtable<RegularTimePeriod, Number> getDatasets() {
		return this.datasets;
	}

	/**
	 * 设置曲线的数据集
	 * 
	 * @param datasets
	 *            曲线的数据集
	 */
	public void setDatasets(Hashtable<RegularTimePeriod, Number> datasets) {
		this.datasets = datasets;
	}
}
