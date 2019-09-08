package com.intellect.auto.bean;

import java.util.List;
import java.util.Map;

public class TestCaseBean implements Comparable<TestCaseBean>{
	@Override
	public String toString() {
		return "TestCaseBean [module=" + module + ", subModule=" + subModule + ", priority=" + priority + ", product="
				+ product + ", operation=" + operation + ", testCaseDesc=" + testCaseDesc + ", tcdef=" + tcdef
				+ ", testSuiteID=" + testSuiteID + ", testCaseID=" + testCaseID + ", testDataHandle=" + testDataHandle
				+ ", testType=" + testType + ", validator=" + validator + ", tcdexecTime=" + tcdexecTime
				+ ", totalExecTime=" + totalExecTime + ", detailLink=" + detailLink + ", evidenceLink=" + evidenceLink
				+ ", result=" + result + ", eventList=" + eventList + ", execute=" + execute + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((subModule == null) ? 0 : subModule.hashCode());
		result = prime * result + ((testCaseDesc == null) ? 0 : testCaseDesc.hashCode());
		result = prime * result + ((testCaseID == null) ? 0 : testCaseID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestCaseBean other = (TestCaseBean) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (subModule == null) {
			if (other.subModule != null)
				return false;
		} else if (!subModule.equals(other.subModule))
			return false;
		if (testCaseDesc == null) {
			if (other.testCaseDesc != null)
				return false;
		} else if (!testCaseDesc.equals(other.testCaseDesc))
			return false;
		if (testCaseID == null) {
			if (other.testCaseID != null)
				return false;
		} else if (!testCaseID.equals(other.testCaseID))
			return false;
		return true;
	}

	private String module;
	private String subModule;
	private int priority;
	private String product;
	private String operation;
	private String testCaseDesc;
	private KeyWordDef tcdef;
	private String testSuiteID;
	private String testCaseID;
	private String testDataHandle;
	private String testType;
	private Map validator; 
	
	private String tcdexecTime;
	public String getTcdexecTime() {
		return tcdexecTime;
	}
	public void setTcdexecTime(String tcdexecTime) {
		this.tcdexecTime = tcdexecTime;
	}
	public String getTotalExecTime() {
		return totalExecTime;
	}
	public void setTotalExecTime(String totalExecTime) {
		this.totalExecTime = totalExecTime;
	}
	public String getDetailLink() {
		return detailLink;
	}
	public void setDetailLink(String detailLink) {
		this.detailLink = detailLink;
	}
	public String getEvidenceLink() {
		return evidenceLink;
	}
	public void setEvidenceLink(String evidenceLink) {
		this.evidenceLink = evidenceLink;
	}

	private String totalExecTime;
	private String detailLink;
	private String evidenceLink;
	
	
	
	private String result;
	
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Map getValidator() {
		return validator;
	}
	public void setValidator(Map validator) {
		this.validator = validator;
	}
	public KeyWordDef getTcdef() {
		return tcdef;
	}
	public void setTcdef(KeyWordDef tcdef) {
		this.tcdef = tcdef;
	}

	
	
	public String getSubModule() {
		return subModule;
	}
	public void setSubModule(String subModule) {
		this.subModule = subModule;
	}

	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}

	private List<Event> eventList;
	public List<Event> getEventList() {
		return eventList;
	}
	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}

	private boolean execute;
	public boolean isExecute() {
		return execute;
	}
	public void setExecute(boolean execute) {
		this.execute = execute;
	}

	 
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	 
	public String getProduct() {
		return product;
	}
	public String getTestCaseDesc() {
		return testCaseDesc;
	}
	public void setTestCaseDesc(String testCaseDesc) {
		this.testCaseDesc = testCaseDesc;
	}
	 
	public String getTestDataHandle() {
		return testDataHandle;
	}
	public String getTestSuiteID() {
		return testSuiteID;
	}
	public void setTestSuiteID(String testSuiteID) {
		this.testSuiteID = testSuiteID;
	}
	public String getTestCaseID() {
		return testCaseID;
	}
	public void setTestCaseID(String testCaseID) {
		this.testCaseID = testCaseID;
	}
	public void setTestDataHandle(String testDataHandle) {
		this.testDataHandle = testDataHandle;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	 
	 
	 
	@Override
	public int compareTo(TestCaseBean obj) {
		return this.priority - obj.priority;
	} 
}
