package com.intellect.auto.bean;

public class ObjectData {
	private String level1;
	@Override
	public String toString() {
		return "ObjectData [level1=" + level1 + ", level2=" + level2 + ", level3=" + level3 + ", element=" + element
				+ ", objType=" + objType + ", objRef=" + objRef + "]";
	}
	
	private String level2;
	private String level3;
	private String element;
	private String objType;
	private String objRef;
	
	public String getLevel1() {
		return level1;
	}
	public void setLevel1(String level1) {
		this.level1 = level1;
	}
	public String getLevel2() {
		return level2;
	}
	public void setLevel2(String level2) {
		this.level2 = level2;
	}
	public String getLevel3() {
		return level3;
	}
	public void setLevel3(String level3) {
		this.level3 = level3;
	}
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	public String getObjType() {
		return objType;
	}
	public void setObjType(String objType) {
		this.objType = objType;
	}
	public String getObjRef() {
		return objRef;
	}
	public void setObjRef(String objRef) {
		this.objRef = objRef;
	}
}
