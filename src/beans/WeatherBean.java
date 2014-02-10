package beans;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class WeatherBean {
	private String cityName;
	private String tempValue;
	private String tempMin;
	private String tempMax;
	private ArrayList<String> test;

    // test

	public String getCityName() {
		return cityName;
	}
	public String getTempMax() {
		return tempMax;
	}
	public String getTempMin() {
		return tempMin;
	}
	public String getTempValue() {
		return tempValue;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public void setTempMax(String tempMax) {
		this.tempMax = tempMax;
	}
	public void setTempMin(String tempMin) {
		this.tempMin = tempMin;
	}
	public void setTempValue(String tempValue) {
		this.tempValue = tempValue;
	}
	public ArrayList<String> getTest() {
		return test;
	}
	public void setTest(ArrayList<String> test) {
		this.test = test;
	}
}
