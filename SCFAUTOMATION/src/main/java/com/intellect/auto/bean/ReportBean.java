package com.intellect.auto.bean;

import java.util.ArrayList;

public class ReportBean {
	
	
	private String project;
	private String module;
	private String testcaseID;
	private boolean result; 
	private String testCaseDesc;
	private String userParam;
	
	
	public String getUserParam() {
		return userParam;
	}
	public void setUserParam(String userParam) {
		this.userParam = userParam;
	}
	public String getTestCaseDesc() {
		return testCaseDesc;
	}
	public void setTestCaseDesc(String testCaseDesc) {
		this.testCaseDesc = testCaseDesc;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	private String title;
	
	
	private ArrayList<VerifyBean> list = new  ArrayList<>();
	
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getTestcaseID() {
		return testcaseID;
	}
	public void setTestcaseID(String testcaseID) {
		this.testcaseID = testcaseID;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public ArrayList<VerifyBean> getList() {
		return list;
	}
	public void setList(ArrayList<VerifyBean> list) {
		this.list = list;
	}
		
}
