package com.yixin.framework.login.model;

import java.io.Serializable;
import java.util.List;

import com.yixin.A1000.archive.model.Sensor;

/**
 * 分组页的显示类
 * 
 * @author 梁立全

 * 2010 06 25
 */

public class GroupPage implements Serializable {

	private static final long serialVersionUID = 51698746519856L;
	
	/** 传感器 **/
	private Sensor sensor;
	/** 在线状态**/
	private Boolean state;
	/** 告警信息**/
	private String warning;
	
	/** 现场图片 */
	private String pictureFileName1;
	/** 安装方位 */
	private String pictureFileName2;
	
	/** 数据项**/
	private List<DataItem> dataItem;
	
	/** 图片 **/
	private Boolean pictureData;
	 
	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	/**
	 * @param dataItem the dataItem to set
	 */
	public void setDataItem(List<DataItem> dataItem) {
		this.dataItem = dataItem;
	}

	/**
	 * @return the dataItem
	 */
	public List<DataItem> getDataItem() {
		return dataItem;
	}

	/**
	 * @param warning the warning to set
	 */
	public void setWarning(String warning) {
		this.warning = warning;
	}

	/**
	 * @return the warning
	 */
	public String getWarning() {
		return warning;
	}

	/**
	 * @param pictureFileName1 the pictureFileName1 to set
	 */
	public void setPictureFileName1(String pictureFileName1) {
		this.pictureFileName1 = pictureFileName1;
	}

	/**
	 * @return the pictureFileName1
	 */
	public String getPictureFileName1() {
		return pictureFileName1;
	}

	/**
	 * @param pictureFileName2 the pictureFileName2 to set
	 */
	public void setPictureFileName2(String pictureFileName2) {
		this.pictureFileName2 = pictureFileName2;
	}

	/**
	 * @return the pictureFileName2
	 */
	public String getPictureFileName2() {
		return pictureFileName2;
	}

	/**
	 * @param pictureData the pictureData to set
	 */
	public void setPictureData(Boolean pictureData) {
		this.pictureData = pictureData;
	}

	/**
	 * @return the pictureData
	 */
	public Boolean getPictureData() {
		return pictureData;
	}

	 
	 
	
	
	
}
