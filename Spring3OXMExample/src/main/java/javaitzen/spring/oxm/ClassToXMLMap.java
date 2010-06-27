package javaitzen.spring.oxm;

import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@SuppressWarnings("restriction")
@XmlRootElement(name="ClassToXMLMap")
@XStreamAlias( "ClassToXMLMap" )
public class ClassToXMLMap {
	private String data;
	private String history;
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getHistory() {
		return history;
	}
	
	public void setHistory(String history) {
		this.history = history;
	}	
}
