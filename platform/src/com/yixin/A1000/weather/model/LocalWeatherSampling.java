package com.yixin.A1000.weather.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * 本地天气采样表

 * @author 梁立全

 *
 *
 */
@Entity
@Table(name = "T_LocalweatherSampling")
public class LocalWeatherSampling implements Serializable {
	
	/* fields */
	private static final long serialVersionUID = 7451217813567841027L;

	/* fields */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String localWeatherSamplingId;	//ID
	
	private Date   acquisitionTime;     /*日期 每天一条记录*/

	private String temperature_low;  /*最低气温*/
    private String temperature_hight;  /*最高气温*/
    private String weather;        /*预计天气*/
    private Integer humidity;     /*湿度*/
    private Integer airPressure;    /*气压*/
    private String  windDirection;   /*风向*/
    private Integer windPower;     /*风力 单位：级*/   
    private String  windDesc;  /*风力风向描述*/
    private String startIcon;    /*天气开始 图片*/
    private String  endIcon;   /*天气结束 图片*/
    private String liveWeather;   /*天气实况*/
    private String  exponent;  /*天气指数*/         
    private String uvIntensity;    /*紫外线强度*/ 
    private String airQuality;     /*空气质量*/
      
	
	public String getTemperature_low() {
		return temperature_low;
	}
	public void setTemperature_low(String temperature_low) {
		this.temperature_low = temperature_low;
	}
	public String getTemperature_hight() {
		return temperature_hight;
	}
	public void setTemperature_hight(String temperature_hight) {
		this.temperature_hight = temperature_hight;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public Integer getHumidity() {
		return humidity;
	}
	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}
	public Integer getAirPressure() {
		return airPressure;
	}
	public void setAirPressure(Integer airPressure) {
		this.airPressure = airPressure;
	}
	public String getWindDirection() {
		return windDirection;
	}
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	public Integer getWindPower() {
		return windPower;
	}
	public void setWindPower(Integer windPower) {
		this.windPower = windPower;
	}
	public String getWindDesc() {
		return windDesc;
	}
	public void setWindDesc(String windDesc) {
		this.windDesc = windDesc;
	}
	public String getStartIcon() {
		return startIcon;
	}
	public void setStartIcon(String startIcon) {
		this.startIcon = startIcon;
	}
	public String getEndIcon() {
		return endIcon;
	}
	public void setEndIcon(String endIcon) {
		this.endIcon = endIcon;
	}
	public String getLiveWeather() {
		return liveWeather;
	}
	public void setLiveWeather(String liveWeather) {
		this.liveWeather = liveWeather;
	}
	public String getExponent() {
		return exponent;
	}
	public void setExponent(String exponent) {
		this.exponent = exponent;
	}
	public String getUvIntensity() {
		return uvIntensity;
	}
	public void setUvIntensity(String uvIntensity) {
		this.uvIntensity = uvIntensity;
	}
	public String getAirQuality() {
		return airQuality;
	}
	public void setAirQuality(String airQuality) {
		this.airQuality = airQuality;
	}
	public Date getAcquisitionTime() {
		return acquisitionTime;
	}
	public void setAcquisitionTime(Date acquisitionTime) {
		this.acquisitionTime = acquisitionTime;
	} 
	
    public String getLocalWeatherSamplingId() {
			return localWeatherSamplingId;
	}
	
    public void setLocalWeatherSamplingId(String localWeatherSamplingId) {
			this.localWeatherSamplingId = localWeatherSamplingId;
	}
		

}
