package com.intellect.auto.bean;

import java.util.List;
import java.util.Map;

public class KeyWordDef {
	private String testCaseID;
	private String subTask;
	private Map<String, String> values;
	private boolean result;
	private String resultDescription;
	private String testCaseDesc;
	private List<Event> eventList; 
	 
	public List<Event> getEventList() {
		return eventList;
	}
	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}
	 
	public String getTestCaseDesc() {
		return testCaseDesc;
	}
	public void setTestCaseDesc(String testCaseDesc) {
		this.testCaseDesc = testCaseDesc;
	}
	public String getTestCaseID() {
		return testCaseID;
	}
	public void setTestCaseID(String testCaseID) {
		this.testCaseID = testCaseID;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getResultDescription() {
		return resultDescription;
	}
	public void setResultDescription(String resultDescription) {
		this.resultDescription = resultDescription;
	}
	public String getSubTask() {
		return subTask;
	}
	public void setSubTask(String subTask) {
		this.subTask = subTask;
	}
	public Map<String, String> getValues() {
		return values;
	}
	public void setValues(Map<String, String> values) {
		this.values = values;
	}

}
