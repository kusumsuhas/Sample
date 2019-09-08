package com.intellect.auto.bean;

import java.lang.reflect.Field;

public class Event implements Comparable<Event>, Cloneable{
	private int seq;
	private String action;
	private String value;
	private String xPath;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((captuValueType == null) ? 0 : captuValueType.hashCode());
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		result = prime * result + ((elementType == null) ? 0 : elementType.hashCode());
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((fetchData == null) ? 0 : fetchData.hashCode());
		result = prime * result + ((functionName == null) ? 0 : functionName.hashCode());
		result = prime * result + ((input == null) ? 0 : input.hashCode());
		result = prime * result + ((inputTestData == null) ? 0 : inputTestData.hashCode());
		result = prime * result + priority;
		result = prime * result + ((retain == null) ? 0 : retain.hashCode());
		result = prime * result + seq;
		result = prime * result + ((subTask == null) ? 0 : subTask.hashCode());
		result = prime * result + ((testcaseId == null) ? 0 : testcaseId.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((userParam1 == null) ? 0 : userParam1.hashCode());
		result = prime * result + ((userParam2 == null) ? 0 : userParam2.hashCode());
		result = prime * result + ((userParam3 == null) ? 0 : userParam3.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + (int) (waitTime ^ (waitTime >>> 32));
		result = prime * result + ((xPath == null) ? 0 : xPath.hashCode());
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
		Event other = (Event) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (captuValueType == null) {
			if (other.captuValueType != null)
				return false;
		} else if (!captuValueType.equals(other.captuValueType))
			return false;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.equals(other.element))
			return false;
		if (elementType == null) {
			if (other.elementType != null)
				return false;
		} else if (!elementType.equals(other.elementType))
			return false;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (fetchData == null) {
			if (other.fetchData != null)
				return false;
		} else if (!fetchData.equals(other.fetchData))
			return false;
		if (functionName == null) {
			if (other.functionName != null)
				return false;
		} else if (!functionName.equals(other.functionName))
			return false;
		if (input == null) {
			if (other.input != null)
				return false;
		} else if (!input.equals(other.input))
			return false;
		if (inputTestData == null) {
			if (other.inputTestData != null)
				return false;
		} else if (!inputTestData.equals(other.inputTestData))
			return false;
		if (priority != other.priority)
			return false;
		if (retain == null) {
			if (other.retain != null)
				return false;
		} else if (!retain.equals(other.retain))
			return false;
		if (seq != other.seq)
			return false;
		if (subTask == null) {
			if (other.subTask != null)
				return false;
		} else if (!subTask.equals(other.subTask))
			return false;
		if (testcaseId == null) {
			if (other.testcaseId != null)
				return false;
		} else if (!testcaseId.equals(other.testcaseId))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (userParam1 == null) {
			if (other.userParam1 != null)
				return false;
		} else if (!userParam1.equals(other.userParam1))
			return false;
		if (userParam2 == null) {
			if (other.userParam2 != null)
				return false;
		} else if (!userParam2.equals(other.userParam2))
			return false;
		if (userParam3 == null) {
			if (other.userParam3 != null)
				return false;
		} else if (!userParam3.equals(other.userParam3))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (waitTime != other.waitTime)
			return false;
		if (xPath == null) {
			if (other.xPath != null)
				return false;
		} else if (!xPath.equals(other.xPath))
			return false;
		return true;
	}

	private String event;
	private String text;
	private String type;
	private long waitTime;
	private String subTask;
	private String element;
	private String functionName;
	private String input;
	private int priority;
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	private String userParam1;
	private String userParam2;
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
	public String getUserParam2() {
		return userParam2;
	}
	public void setUserParam2(String userParam2) {
		this.userParam2 = userParam2;
	}
	private String retain;
	private String captuValueType;
	
	private String inputTestData;
	private String fetchData;
	
	
	private String elementType;
	
	public String getElementType() {
		return elementType;
	}
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	public String getInputTestData() {
		return inputTestData;
	}
	public void setInputTestData(String inputTestData) {
		this.inputTestData = inputTestData;
	}
	public String getFetchData() {
		return fetchData;
	}
	public void setFetchData(String fetchData) {
		this.fetchData = fetchData;
	}
	public String getRetain() {
		return retain;
	}
	public void setRetain(String retain) {
		this.retain = retain;
	}
	public String getCaptuValueType() {
		return captuValueType;
	}
	public void setCaptuValueType(String captuValueType) {
		this.captuValueType = captuValueType;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getSubTask() {
		return subTask;
	}
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	public void setSubTask(String subTask) {
		this.subTask = subTask;
	}
	public long getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(long waitTime) {
		this.waitTime = waitTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	private String testcaseId;
	
	public String getTestcaseId() {
		return testcaseId;
	}
	public void setTestcaseId(String testcaseId) {
		this.testcaseId = testcaseId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getxPath() {
		return xPath;
	}
	public void setxPath(String xPath) {
		this.xPath = xPath;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	 
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public int compareTo(Event obj) {
		return this.priority - obj.priority; 
		//return 0;
	}
	
	public String toString() {
		return " EVENT "+this.event+"  Element "+this.element+" ELEMENT TYPE:::"+this.elementType+ "  VALUE "+this.value+" ACTION "+this.action+"  PARAM1 "+this.userParam1+"   "+this.getText()+" param2  "+this.getUserParam2()+" FD:: "+this.getFetchData()+" INPUT  "+this.getInputTestData()+" UP:: "+this.getUserParam3()+"  PRIORITY:: "+this.priority;
//	return  "SEQ "+this.seq+" TESTCASE "+this.testcaseId+" KW "+this.subTask+" EVENTS "+this.event
//			+" ELEMENT "+this.element+" VAL "+this.value+" FD "+this.fetchData+" CV  "+this.captuValueType+" SUBTASK  "+this.getSubTask();
	}
	
	public String toString1() {
		  StringBuilder result = new StringBuilder();
		  String newLine = System.getProperty("line.separator");

		  //result.append( this.getClass().getName() );
		 // result.append( " Object {" );
		 // result.append(newLine);

		  //determine fields declared in this class only (no fields of superclass)
		  Field[] fields = this.getClass().getDeclaredFields();

		  //print field names paired with their values
		  for ( Field field : fields  ) {
			  
			  if(field.getName().equals("waitTime") || field.getName().equals("testcaseId") || field.getName().equals("functionName")
					  || field.getName().equals("subTask") || field.getName().equals("text")    ){
				  continue;
			  }
		    result.append("  ");
		    try {
		      result.append( field.getName() );
		      result.append(": ");
		      //requires access to private field:
		      result.append( field.get(this) );
		    } catch ( IllegalAccessException ex ) {
		      System.out.println(ex);
		    }
		   // result.append(newLine);
		  }
		  result.append("}");

		  return result.toString();
		}

	public Event clone() throws CloneNotSupportedException{
		Event ee = (Event) super.clone();
		
		return ee;
		
	}

}
