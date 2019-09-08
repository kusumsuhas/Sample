package com.intellect.auto.bean;

import java.lang.reflect.Field;

public class TestCaseDefinition {

	
	private String testcaseId;
	private String tcDesc;
	private String keyWord;
	private String input;
	private String output;
	private String preReq;
	private String postReq;
	
	
	private int iterationcount;;
	public int getIterationcount() {
		return iterationcount;
	}
	public void setIterationcount(int iterationcount) {
		this.iterationcount = iterationcount;
	}
	private String retain;
	private String captuValueType;
	private String fetchData;
	private String userParam1;
	private String userParam3;
	
	
	public String getUserParam3() {
		return userParam3;
	}
	public void setUserParam3(String userParam3) {
		this.userParam3 = userParam3;
	}
	public String getUserParam1() {
		return userParam1;
	}
	public void setUserParam1(String userParam1) {
		this.userParam1 = userParam1;
	}
	private String userParam2;
	
	
	
	
	public String getUserParam2() {
		return userParam2;
	}
	public void setUserParam2(String userParam2) {
		this.userParam2 = userParam2;
	}
	public String getFetchData() {
		return fetchData;
	}
	public void setFetchData(String fetchData) {
		this.fetchData = fetchData;
	}
	private String inputTestDataParam; 

	
	public String getInputTestDataParam() {
		return inputTestDataParam;
	}
	public void setInputTestDataParam(String inputTestDataParam) {
		this.inputTestDataParam = inputTestDataParam;
	}
	public String getCaptuValueType() {
		return captuValueType;
	}
	public void setCaptuValueType(String captuValueType) {
		this.captuValueType = captuValueType;
	}
	public String getPreReq() {
		return preReq;
	}
	public String getRetain() {
		return retain;
	}
	public void setRetain(String retain) {
		this.retain = retain;
	}
	 
	public void setPreReq(String preReq) {
		this.preReq = preReq;
	}
	public String getPostReq() {
		return postReq;
	}
	public void setPostReq(String postReq) {
		this.postReq = postReq;
	}
	private String testStep;
	
	public String getTestStep() {
		return testStep;
	}
	public void setTestStep(String testStep) {
		this.testStep = testStep;
	}
	public String getTestcaseId() {
		return testcaseId;
	}
	public void setTestcaseId(String testcaseId) {
		this.testcaseId = testcaseId;
	}
	public String getTcDesc() {
		return tcDesc;
	}
	public void setTcDesc(String tcDesc) {
		this.tcDesc = tcDesc;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getParam1() {
		return param1;
	}
	public void setParam1(String param1) {
		this.param1 = param1;
	}
	public String getParam2() {
		return param2;
	}
	public void setParam2(String param2) {
		this.param2 = param2;
	}
	public String getParam3() {
		return param3;
	}
	public void setParam3(String param3) {
		this.param3 = param3;
	}
	private String action;
	private String param1;
	private String param2;
	private String param3;
	 
	
	public String toString() {
		  StringBuilder result = new StringBuilder();
		  String newLine = System.getProperty("line.separator");

		  result.append( this.getClass().getName() );
		  result.append( " Object {" );
		  result.append(newLine);

		  //determine fields declared in this class only (no fields of superclass)
		  Field[] fields = this.getClass().getDeclaredFields();

		  //print field names paired with their values
		  for ( Field field : fields  ) {
		    result.append("  ");
		    try {
		      result.append( field.getName() );
		      result.append("	: ");
		      //requires access to private field:
		      result.append( field.get(this) );
		    } catch ( IllegalAccessException ex ) {
		      System.out.println(ex);
		    }
		    result.append(newLine);
		  }
		  result.append("}");
		  return result.toString();
		}
	 

}
