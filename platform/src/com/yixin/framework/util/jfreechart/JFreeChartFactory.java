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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;


/**
 * JFreeChart曲线图工厂类
 * 
 * @version v1.0
 * @author 
 */
public class JFreeChartFactory {

	/**
	 * 
	 * 绘制时间曲线图
	 * 
	 * @param title
	 *            主标题文本
	 * @param subTitles
	 *            子标题文本（多个子标题）
	 * @param abscissaText
	 *            横坐标文本
	 * @param legend
	 *            是否显示图例。true-显示；false-不显示
	 * @param chartDatas
	 *            曲线数据及相关参数
	 * @exception IllegalArgumentException
	 *                当chartDatas为null时抛出此异常
	 * @return 生成的曲线图
	 */
	public static synchronized JFreeChart createTimeSeriesChart(String title, List<String> subTitles, String abscissaText, boolean legend, List<TimeSeriesChartData> chartDatas) {
		//JFreeChart chart = ChartFactory.createTimeSeriesChart(null, abscissaText, null, null, true, true, false);// 创建一个JFreeChart对象
		JFreeChart chart = ChartFactory.createTimeSeriesChart("s", abscissaText, "test", null, true, true, false);// 创建一个JFreeChart对象
		//JFreeChart chart = ChartFactory.createXYLineChart(title, abscissaText, ordinateText, null, plotOrientation, true, true, false);// 创建一个JFreeChart对象
		XYPlot xyPlot = (XYPlot) chart.getPlot();// 取得JFreeChart对象的XYPlot
		DecimalFormat df = new DecimalFormat("###.##"); // 显示两位小数

		/* 设置曲线图标题 */
		if (null != title) {
			chart.setTitle(title);// 设置主标题
		}
		if (null != subTitles) {
			/* 设置子标题 */

			TextTitle tTitle = new TextTitle();
			for (String subTitle : subTitles) {
				tTitle = new TextTitle(subTitle);
				chart.addSubtitle(tTitle);
			}
		}

		if (null == chartDatas) {
			throw new IllegalArgumentException();
		}

		/* 设置横坐标 */
		DateAxis dateAxis = (DateAxis) xyPlot.getDomainAxis();
		dateAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));// 设置日期轴日期标签的显示格式
		NumberAxis numberAxis = null;		
		String ordinateText="";
		int lastAxisIdx = 0;
		for (int i = 1, len = chartDatas.size(); i <= len; i++) {
			/* 绘制曲线 */

			TimeSeriesChartData charData = chartDatas.get(i - 1);
			TimeSeries timeSeries = new TimeSeries(charData.getLegendText());// 创建曲线对象
			Iterator<RegularTimePeriod> iterator = charData.getDatasets().keySet().iterator();
			while (iterator.hasNext()) {
				/* 将数据绘制进曲线中 */
				RegularTimePeriod period = iterator.next();
				timeSeries.add(period, charData.getDatasets().get(period));
			}
			//if(!ordinateText.equals(charData.getOrdinateText()))
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(timeSeries);// 绑定数据点到指定数据集中
			xyPlot.setDataset(i, dataset);// 绑定数据集到绘图区域
			XYLineAndShapeRenderer itemRenderer = new XYLineAndShapeRenderer();// 声明图例Renderer对象
			xyPlot.setRenderer(i, itemRenderer);// 绑定数据集与图例
			
			
			if(!ordinateText.equals(charData.getOrdinateText())){
				numberAxis = new NumberAxis(charData.getOrdinateText());// 声明纵坐标数字轴对象
				xyPlot.setRangeAxis(i, numberAxis);// 在绘图区域绘制指定曲线的数字轴
				xyPlot.mapDatasetToRangeAxis(i, i);// 绑定数据集到指定的绘图曲线
				lastAxisIdx = i;
			}else{
				//如果和上一个图样的名称相同，就是相同的数据轴的，不显示纵坐标轴了
				xyPlot.mapDatasetToRangeAxis(i, lastAxisIdx);// 绑定数据集到上同一个坐标绘图曲线
			} 
			ordinateText = charData.getOrdinateText();			

			/* 设置曲线 */

			/* 设置图例 */
			xyPlot.getRenderer(i).setSeriesPaint(0, charData.getColor());// 设置图例及曲线显示颜色
			if (charData.isVisible()) {
				xyPlot.getRenderer(i).setBaseSeriesVisible(true);// 设置图例与曲线不显示
			} else {
				xyPlot.getRenderer(i).setBaseSeriesVisible(false);// 设置图例与曲线不显示
				
			}

			/* 设置纵坐标 */
			xyPlot.setRangeAxisLocation(i, charData.getOrdinateLocation());// 设置纵坐标位置			
			numberAxis.setAutoRangeIncludesZero(false);// 取消纵坐标数字轴“强制在自动选择的数据范围中包含0”
			numberAxis.setAutoRangeStickyZero(false);// 是否强制在整个数据轴中包含0，即使0不在数据范围中
			numberAxis.setLabelPaint(charData.getColor());// 设置 纵坐标文字 颜色
			numberAxis.setTickLabelPaint(charData.getColor());// 设置 纵坐标刻度值 颜色
			numberAxis.setAxisLinePaint(charData.getColor());// 设置 纵坐标刻度线 颜色
			numberAxis.setNumberFormatOverride(df);// 指定刻度显示数据格式
			//numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
				
			
			if (charData.isOrdinateLabelsVisible()) {
				numberAxis.setTickLabelsVisible(true);// 纵坐标轴标尺值是否显示
			} else {
				numberAxis.setTickLabelsVisible(false);// 纵坐标轴标尺值是否显示
			}
			if (charData.isOrdinateLineVisible()) {
				numberAxis.setAxisLineVisible(true);// 纵坐标轴线条是否可见
			} else {
				numberAxis.setAxisLineVisible(false);// 纵坐标轴线条是否可见
			}
			if (charData.isOrdinateMarksVisible()) {
				numberAxis.setTickMarksVisible(true);// 纵坐标轴标尺是否显示
			} else {
				numberAxis.setTickMarksVisible(false);// 纵坐标轴标尺是否显示
			}
			if (charData.isOrdinateVisible()) {
				numberAxis.setVisible(true);// 纵坐标轴是否可见（纵坐标轴=纵坐标轴线条+纵坐标轴标尺值+纵坐标轴标尺）
			} else {
				numberAxis.setVisible(false);// 纵坐标轴是否可见（纵坐标轴=纵坐标轴线条+纵坐标轴标尺值+纵坐标轴标尺）
			}
		}
		formatChart(chart);
		chart.getLegend().setVisible(legend);
		xyPlot.getRangeAxis().setVisible(false); // 设置主坐标轴不可见
		xyPlot.getRangeAxis().setAxisLineVisible(false);// 设置主坐标轴线条不可见
		return chart;
	}

	/**
	 * 绘制XY曲线图
	 * 
	 * @param title
	 *            主标题文本
	 * @param subTitles
	 *            子标题文本（多个子标题）
	 * @param abscissaText
	 *            横坐标文本
	 * @param ordinateText
	 *            纵坐标文本
	 * @param legend
	 *            是否显示图例。true-显示；false-不显示
	 * @param chartDatas
	 *            曲线数据及相关参数
	 * @exception IllegalArgumentException
	 *                当chartDatas为null时抛出此异常
	 * @return 生成的曲线图
	 */
	public static JFreeChart createXYLineChart(String title, List<String> subTitles, String abscissaText, String ordinateText, boolean legend, List<XYLineChartData> chartDatas, Boolean leftToRright) {
		
		PlotOrientation plotOrientation = (leftToRright == null || !leftToRright) ?  PlotOrientation.VERTICAL : PlotOrientation.HORIZONTAL;
		
		JFreeChart chart = ChartFactory.createXYLineChart(title, abscissaText, ordinateText, null, plotOrientation, true, true, false);// 创建一个JFreeChart对象
		XYPlot xyPlot = (XYPlot) chart.getPlot();// 取得JFreeChart对象的XYPlot
		if (null != subTitles) {
			/* 设置子标题 */

			TextTitle tTitle = new TextTitle();
			for (String subTitle : subTitles) {
				tTitle = new TextTitle(subTitle);
				chart.addSubtitle(tTitle);
			}
		}

		if (null == chartDatas) {
			throw new IllegalArgumentException();
		}
		
		for (int i = 1, len = chartDatas.size(); i <= len; i++) {
			/* 绘制曲线 */

			XYLineChartData charData = chartDatas.get(i - 1);
			XYSeries xySeries = new XYSeries(charData.getLegendText());// 创建曲线对象
			
			Iterator<Integer> iterator = charData.getDatasets().keySet().iterator();
			while (iterator.hasNext()) {
				/* 将数据绘制进曲线中 */
				Integer idx = iterator.next();
				List<Number> list = charData.getDatasets().get(idx);
				Number x = list.get(0);
				Number y = list.get(1);
				xySeries.add((Double)x, (Double)y);
			}
			XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
			xySeriesCollection.addSeries(xySeries);// 绑定数据点到指定数据集中
			xyPlot.setDataset(i, xySeriesCollection);// 绑定数据集到绘图区域
			XYLineAndShapeRenderer itemRenderer = new XYLineAndShapeRenderer();// 声明图例Renderer对象
			xyPlot.setRenderer(i, itemRenderer);// 绑定数据集与图例

			/* 设置图例 */
			xyPlot.getRenderer(i).setSeriesPaint(0, charData.getColor());// 设置图例及曲线显示颜色
			if (charData.isVisible()) {
				xyPlot.getRenderer(i).setBaseSeriesVisible(true);// 设置图例与曲线不显示
			} else {
				xyPlot.getRenderer(i).setBaseSeriesVisible(false);// 设置图例与曲线不显示
			}
		}		
		formatChart(chart);
		chart.getLegend().setVisible(legend);
		return chart;
	}
	
	/**
	 * 格式化图表的显示样式
	 * 
	 * @param chart
	 */
	private static void formatChart(JFreeChart chart) {
		Font font = new Font("宋体", Font.BOLD, 12);
		XYPlot xyPlot = (XYPlot) chart.getPlot();

		chart.setBackgroundPaint(new Color(255, 255, 255)); // 设置曲线图背景色
		chart.setBorderVisible(false); // 设置曲线图边界线条不可见
		if (null != chart.getTitle()) {
			chart.getTitle().setFont(new Font("黑体", Font.PLAIN, 16)); // 设置标题字体
		}
		xyPlot.setBackgroundPaint(new Color(255, 255, 255));// 设置绘图区域背景色

		/* 设置网络线颜色 */
		xyPlot.setDomainGridlinePaint(Color.LIGHT_GRAY);
		xyPlot.setDomainGridlineStroke(new BasicStroke(1));
		xyPlot.setRangeGridlinePaint(Color.LIGHT_GRAY);
		xyPlot.setRangeGridlineStroke(new BasicStroke(1));

		/* 没有数据时显示的消息 */
		xyPlot.setNoDataMessage("没有可显示的曲线（没有任何采样数据，或者曲线不可见）");
		xyPlot.setNoDataMessageFont(new Font("黑体", Font.PLAIN, 17));
		xyPlot.setNoDataMessagePaint(new Color(255, 0, 0));
		chart.getLegend().setItemFont(font);// 设置图例字体

		
		
		/* 设置X轴的字体 */
		xyPlot.getDomainAxis().setTickLabelFont(font);// X坐标轴标尺值字体
		xyPlot.getDomainAxis().setLabelFont(font);// X坐标轴标题字体
		
		
		Font font1 = new Font("宋体", Font.BOLD, 20);
		
		
		ValueAxis valueAxisY =xyPlot.getRangeAxis(); 
		valueAxisY.setTickLabelFont(new Font("Serif", Font.PLAIN, 10));	
		valueAxisY.setLabelFont(new Font("Serif", Font.PLAIN, 10));
		
		/* 设置Y轴的字体 */
		//xyPlot.getRangeAxis().setTickLabelFont(font1);
		//xyPlot.getRangeAxis().setLabelFont(font1);

		xyPlot.setDomainCrosshairVisible(true);
		xyPlot.setRangeCrosshairVisible(true);

		for (int i = 0, len = xyPlot.getRendererCount(); i < len; i++) {
			XYItemRenderer r = xyPlot.getRenderer(i);
			
			if (r instanceof XYLineAndShapeRenderer) {
				XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
				renderer.setBaseShapesVisible(true);
				renderer.setBaseShapesFilled(true);
				// 设置数据点的样式
				renderer.setSeriesShape(0, new Ellipse2D.Double(-2.0D, -2.0D, 4.0D, 4.0D));
				// 设置曲线的粗细
				renderer.setSeriesStroke(0, new BasicStroke(1));
				renderer.setItemLabelAnchorOffset(0);
				
			}
		}
		xyPlot.setAxisOffset(new RectangleInsets(0, 0, 0, 0));// 设置曲线图与xy轴的距离
		chart.setPadding(new RectangleInsets(0, 0, 0, 0));
		xyPlot.setOutlineVisible(false);
	}
}
