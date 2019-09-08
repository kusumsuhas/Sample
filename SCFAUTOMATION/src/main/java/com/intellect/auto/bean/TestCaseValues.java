package com.intellect.auto.bean;

import java.util.List;
import java.util.Map;

public class TestCaseValues {
	
	private String keyWord;
	private Map<String, Map<String, String>> values;
	
	
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	 
	public Map<String, Map<String, String>> getValues() {
		return values;
	}
	public void setValues(Map<String, Map<String, String>> values) {
		this.values = values;
	}
	 
}
