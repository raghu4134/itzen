package javaitzen.drools;

import java.util.List;
import java.util.Map;

public class RoolVO {

	private String stringValue;
	private Double doubleValue;
	private boolean booleanValue;
	private Map<Integer, String> mapValue;
	private List<String> listValue;
	
	public final String getStringValue() {
		return stringValue;
	}
	public final void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public final Double getDoubleValue() {
		return doubleValue;
	}
	public final void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}
	public final Map<Integer, String> getMapValue() {
		return mapValue;
	}
	public final void setMapValue(Map<Integer, String> mapValue) {
		this.mapValue = mapValue;
	}
	public final List<String> getListValue() {
		return listValue;
	}
	public final void setListValue(List<String> listValue) {
		this.listValue = listValue;
	}
	public final boolean isBooleanValue() {
		return booleanValue;
	}
	public final void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}
	
}
