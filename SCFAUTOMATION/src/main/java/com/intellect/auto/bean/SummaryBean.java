package com.intellect.auto.bean;

public class SummaryBean {
	private String testCaseId;
	private String desc;
	private String module;
	private String subModule;
	private String result;
	private String tcdexecTime;
	private String totalExecTime;
	
	public String getTotalExecTime() {
		return totalExecTime;
	}
	public void setTotalExecTime(String totalExecTime) {
		this.totalExecTime = totalExecTime;
	}
	public String getTestEvidenceLink() {
		return testEvidenceLink;
	}
	public void setTestEvidenceLink(String testEvidenceLink) {
		this.testEvidenceLink = testEvidenceLink;
	}
	public String getDetailLink() {
		return detailLink;
	}
	public void setDetailLink(String detailLink) {
		this.detailLink = detailLink;
	}
	private String testEvidenceLink;
	private String detailLink;
	
	
	public String getTestCaseId() {
		return testCaseId;
	}
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getSubModule() {
		return subModule;
	}
	public void setSubModule(String subModule) {
		this.subModule = subModule;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTcdexecTime() {
		return tcdexecTime;
	}
	public void setTcdexecTime(String tcdexecTime) {
		this.tcdexecTime = tcdexecTime;
	}
	

}
