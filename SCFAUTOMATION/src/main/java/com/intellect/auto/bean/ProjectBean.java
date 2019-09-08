package com.intellect.auto.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.intellect.auto.util.Config;
import com.intellect.auto.util.Constants;
import com.intellect.auto.util.Util;
import com.intellect.auto.validation.Validator;
import com.sun.jna.platform.win32.LMAccess.LOCALGROUP_INFO_1;

public class ProjectBean {
	 
	private static Logger Log = Logger.getLogger("app");
	private String project;
	private String version;
	private String datOfBuild;
	private String environment;
	private String ip;
	private TreeSet<TestCaseBean> testCaseSet = new TreeSet<>();
	private Map<String, ObjectData> objMap = new HashMap<>();
	private Map<String, String> objectIdMap = new HashMap<>();	
	private Map<String, TestCaseValues> inputValues = new HashMap<>();
 	private Map<String, KeyWordDef> functionDefMap = new HashMap<>(); // redundent
 	private List<TestCaseDefinition> tcdList;
 	private Map<String, String> envValues = new HashMap<>();
	private Map<String, String> tcInput = new HashMap<>();
	private List<KeyWordDef> list = new ArrayList<>();
	private Map<String, String> indFMap = new HashMap<>();
	private Map<String, String> teseCaseDef = new HashMap<>();
	private Map<String, Map<String, String>> configMap = new HashMap<>();	
	static ProjectBean bean = null;
	private HSSFWorkbook workbook;
 	
 	public ProjectBean(HSSFWorkbook workbook) {
		super();
		this.workbook = workbook;
	}

	public ProjectBean() {
		 
	}

 	public List<TestCaseDefinition> getTcdList() {
		return tcdList;
	}

	public void setTcdList(List<TestCaseDefinition> tcdList) {
		this.tcdList = tcdList;
	}

	Map<String, Map<String, String>> navigationMap;
 	
 	public Map<String, Map<String, String>> getNavigationMap() {
		return navigationMap;
	}

	public void setNavigationMap(Map<String, Map<String, String>> navigationMap) {
		this.navigationMap = navigationMap;
	}

	private Map<String, KeyWordDef> keyWordDef = new HashMap<>();
 	
	public Map<String, KeyWordDef> getKeyWordDef() {
		return keyWordDef;
	}

	public void setKeyWordDef(Map<String, KeyWordDef> keyWordDef) {
		this.keyWordDef = keyWordDef;
	}

	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	private HashMap<String, TestCaseBean> testCaseMap = new HashMap<String, TestCaseBean>();
	
	
	public Map<String, TestCaseValues> getInputValues() {
		return inputValues;
	}

	public void setInputValues(Map<String, TestCaseValues> inputValues) {
		this.inputValues = inputValues;
	}

	public Map<String, String> getObjectIdMap() {
		return objectIdMap;
	}

	public void setObjectIdMap(Map<String, String> objectIdMap) {
		this.objectIdMap = objectIdMap;
	}

	public Map<String, String> getTeseCaseDef() {
		return teseCaseDef;
	}
	
	public void setTeseCaseDef(Map<String, String> teseCaseDef) {
		this.teseCaseDef = teseCaseDef;
	}
 
	public Map<String, Map<String, String>> getConfigMap() {
		return configMap;
	}

	public void setConfigMap(Map<String, Map<String, String>> configMap) {
		this.configMap = configMap;
	}
	
	public Map<String, String> getEnvValues() {
		return envValues;
	}
	
	public void setEnvValues(Map<String, String> envValues) {
		this.envValues = envValues;
	}
	
	public Map<String, KeyWordDef> getFunctionDefMap() {
		return functionDefMap;
	}
	
	public void setFunctionDefMap(Map<String, KeyWordDef> functionDefMap) {
		this.functionDefMap = functionDefMap;
	}
	
	public Map<String, ObjectData> getObjMap() {
		return objMap;
	}
	
	public void setObjMap(Map<String, ObjectData> objMap) {
		this.objMap = objMap;
	}
	
	public List<KeyWordDef> getList() {
		return list;
	}
	
	public void setList(List<KeyWordDef> list) {
		this.list = list;
	}
	
	public Map<String, String> getIndFMap() {
		return indFMap;
	}
	
	public void setIndFMap(Map<String, String> indFMap) {
		this.indFMap = indFMap;
	}
	
	public ProjectBean getBean() {
		return bean;
	}
	
	public void setBean(ProjectBean bean) {
		this.bean = bean;
	}
	
	public TreeSet<TestCaseBean> getTestCaseSet() {
		return testCaseSet;
	}
	
	public void setTestCaseSet(TreeSet<TestCaseBean> testCaseSet) {
		this.testCaseSet = testCaseSet;
	}

	public HashMap<String, TestCaseBean> getTestCaseMap() {
		return testCaseMap;
	}
	
	public void setTestCaseMap(HashMap<String, TestCaseBean> testCaseMap) {
		this.testCaseMap = testCaseMap;
	}
	
	public String getProject() {
		return project;
	}
	
	public void setProject(String project) {
		this.project = project;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getDatOfBuild() {
		return datOfBuild;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public void setDatOfBuild(String datOfBuild) {
		this.datOfBuild = datOfBuild;
	}
	
	public String getEnvironment() {
		return environment;
	}
	
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	
	public static ProjectBean getProjrctBeanNEW(){
		if(bean  == null){
			Config config = new Config();
			FileInputStream file;
			try {
				file = new FileInputStream(new File(Constants.EXCELFILEPATH));
				HSSFWorkbook workbook = new HSSFWorkbook(file);	
				bean = new ProjectBean(workbook)
						.readExecusionSet()
						.readObjectData()
						//.readObjectIDData()
						.readConfigData()
						.readkeywordDef()
						.readNavigationMap()
						.readInputValues()
						.readTcds()
						.createEventMap();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bean;
	}
	
	private ProjectBean readExecusionSet() {
		this.setTestCaseSet(Util.getTestCases(this.workbook.getSheet(Constants.EXECUTION_SET)));
		for(TestCaseBean tBean : this.getTestCaseSet()){
			Log.debug(" TID::  "+tBean.getTestCaseID());
		}
		return this;
	}

	private ProjectBean readObjectData() {
		 this.setObjMap(Util.getObjectValues(workbook.getSheet("ObjectData"), 1));
		return this;
	}
	
//	private   ProjectBean readObjectIDData() {
//		// this.setObjectIdMap(Util.getObjectValues(workbook.getSheet("ObjectData"), 2));
//		 return this;
//	}

	private ProjectBean readConfigData() {
		this.setConfigMap(Util.getConfigValues(workbook.getSheet("CONFIG_MAP")));
		return this;
	}
	
	private ProjectBean readkeywordDef() {
		Map<String, KeyWordDef> keyWordDefMap = Util.readTestCaseDef(workbook.getSheet(Constants.APPLICATION_KW_DEF));
		Util.filterSkipElements(keyWordDefMap, this.getConfigMap()); 
		this.setKeyWordDef(keyWordDefMap);
		return this;
	}
	
	private ProjectBean readNavigationMap() {
		Map<String, Map<String, String>> navigationMap = Util.readNavigationIds(workbook.getSheet(Constants.NAVIGATE_VALUES));
		this.setNavigationMap(navigationMap);
		return this;
	}

	private ProjectBean readInputValues() {
		Map<String, TestCaseValues> tcList = Util.readTestCaseValues2(workbook.getSheet(Constants.INPUT_TESTDATA_VALUES));
	 	this.setInputValues(tcList); 	
		return this;
	}
	
	private ProjectBean readTcds() {
		List<TestCaseDefinition> list = new ArrayList<>();
		TreeSet<TestCaseBean> set = this.getTestCaseSet();
		for(TestCaseBean tcBean: set){
			List<TestCaseDefinition> list1 = Util.readTcd(workbook.getSheet(tcBean.getTestCaseID()));
			list.addAll(list1);
		}
		this.setTcdList(list);		
		return this;
	}

	private Event getVerifyEvent(TestCaseDefinition tcd){
		 Event e1 = new Event();
		 e1.setEvent(Constants.VERIFY);
		 e1.setUserParam1(tcd.getFetchData()+"_"+tcd.getUserParam1());
		 e1.setUserParam2(tcd.getFetchData()+"_"+tcd.getUserParam2());
		 e1.setUserParam3(tcd.getUserParam3());
		 
		 return e1;
		
	}
	private ProjectBean createEventMap() {
		List<Event> list100 = new ArrayList<>();
		HashMap<String, List<Event>> eventMap = new HashMap<>();
		String tid  = "";
		boolean firstRec = true; 
		int TESTCOUNT = 1; 
		List<TestCaseDefinition> list1 = this.getTcdList();
		Map<String, KeyWordDef> keyWordDefMap = this.getKeyWordDef();
		Map<String, TestCaseValues> tcList = this.getInputValues();		
		
		for(TestCaseDefinition tcd: list1){		
			Log.debug(TESTCOUNT+"  tid 1 "+ tid+"  "+tcd.getTestcaseId()+"   "+tcd);
			if(firstRec){
				 tid = tcd.getTestcaseId(); 
			}
			firstRec = false;	
			if(!tid.equals(tcd.getTestcaseId())){	
				 eventMap.put(tid, list100);
				 tid = tcd.getTestcaseId(); 
				 list100 = new ArrayList<>();
				 } 
			
				 boolean flag = Util.isDynamic(tcd, keyWordDefMap);	 
				 if(tcd.getKeyWord().equals("VERIFY")){
					 list100.add(getVerifyEvent(tcd));
					 continue;
				 }
				 
				 if(tcd.getKeyWord().equals(Constants.FETCH_DATA)){	 
					 if(tcd.getFetchData() != null && tcd.getFetchData().length() > 0){
//						 Set<String> set = tcList.keySet();
//						 set = tcList.get(tcd.getFetchData()).getValues().keySet();
						 Event e1 = new Event();
						 e1.setAction(Constants.FETCHFROMDATASHEET);
						 e1.setFetchData(tcd.getFetchData());
						 e1.setInputTestData(tcd.getInputTestDataParam());
						 e1.setUserParam1(tcd.getUserParam1());
						 e1.setUserParam2(tcd.getUserParam2());
						 list100.add(e1); 
						 continue;
					 }
					 Set<String> set = tcList.get(tcd.getInput()).getValues().keySet();
					 set.remove("EXECUTION");
					 set.remove("REFERENCE");
					 Event e1 = new Event();
					 e1.setAction("STARTFETCH");
					 e1.setFetchData(tcd.getInput()+"_"+tcd.getUserParam1());
					 list100.add(e1);
					 list100.add(e1);
					 for(String s: set){
						 Event e = new Event();
						 e.setEvent("FETCH");
						 e.setElement(s);
						 e.setType("ID");
						 e.setValue(s);
						 list100.add(e);
						 e.setText(s);
					 }
					 
					 Event e2 = new Event();
					 e2.setAction("STOPFETCH");
					 e2.setFetchData(tcd.getInput()+"_"+tcd.getUserParam1());
					 list100.add(e2);
					 Validator.validaterSet.put(tcd.getFetchData(),set);
					 continue;
				 }
				 
				 if(tcd.getKeyWord().equals("SWITCHFRAME")){
					 Event ev1 = new Event();
					 ev1.setValue("DEFAULT CONTENET");
					 ev1.setElement("DEFAULT CONTENET");
					 ev1.setEvent("DEFAULT CONTENET");
					 ev1.setElementType("DEFAULT CONTENET");
					 list100.add(ev1);
					 Event ev2 = new Event();
					 ev2.setValue("DEFAULT CONTENET");
					 ev2.setElement("maint.search_frame");
					 ev2.setEvent("SWITCHFRAME");
					 ev2.setElementType("DEFAULT CONTENET");
					 list100.add(ev2);
					 Event ev3 = new Event();
					 ev3.setValue("DEFAULT CONTENET");
					 ev3.setElement("maint.iw_top_frame");
					 ev3.setEvent("SWITCHFRAME");
					 ev3.setElementType("DEFAULT CONTENET");
					 list100.add(ev3);
					 Event ev4 = new Event();
					 ev4.setValue("DEFAULT CONTENET");
					 ev4.setElement("maint.bottom_entity_frame");
					 ev4.setEvent("SWITCHFRAME");
					 ev4.setElementType("DEFAULT CONTENET");
					 list100.add(ev4);	 
					 continue;
				 }
				 
				 if(tcd.getKeyWord().equals("SWITCHFRAME1")){
					 Event ev1 = new Event();
					 ev1.setValue("DEFAULT CONTENET");
					 ev1.setElement("maint.detail.middle_frame");
					 ev1.setEvent("SWITCHFRAME");
					 ev1.setElementType("DEFAULT CONTENET");
					 list100.add(ev1);
					 continue; 
				 }
				 
				 if(tcd.getKeyWord().equals("SWITCHFRAME2")){
				 	Event ev1 = new Event();
					 ev1.setValue("DEFAULT CONTENET");
					 ev1.setElement("DEFAULT CONTENET");
					 ev1.setEvent("DEFAULT CONTENET");
					 ev1.setElementType("DEFAULT CONTENET");
					 list100.add(ev1);
					 Event ev2 = new Event();
					 ev2.setValue("DEFAULT CONTENET");
					 ev2.setElement("maint.detail.IwBottomFrame");
					 ev2.setEvent("SWITCHFRAME");
					 ev2.setElementType("DEFAULT CONTENET");
					 list100.add(ev2);
					 continue; 
				 }
				  
				 if(tcd.getKeyWord().equals("SWITCHWINDOW")){
					 Event ev = new Event();
					 ev.setValue(tcd.getUserParam1());
					 ev.setElement("SWITCHWINDOW");
					 ev.setEvent("SWITCHWINDOW");
					 ev.setElementType("SWITCHWINDOW");
					 ev.setWaitTime(20000);
					 list100.add(ev);
					 continue; 
				 }
				 
				 if(tcd.getKeyWord().equals("SCREEN_ACTION")){
					 Event ev = new Event();
					 ev.setValue(tcd.getUserParam1());
					if(Validator.validaterMap1.get(tcd.getUserParam1()) != null && Validator.validaterMap1.get(tcd.getUserParam1()).length() > 0){
					 ev.setElement(Validator.validaterMap1.get(tcd.getUserParam1()));
					}else{
						 ev.setElement(tcd.getUserParam1());
					}
					 ev.setEvent("CLICK_LINKTEXT");
					 ev.setElementType("LINKTEXT");
					 ev.setAction(tcd.getInput()+"  "+tcd.getInputTestDataParam());
					 ev.setWaitTime(10000L);
					 list100.add(ev);
					 continue;
				 }
				 
				 if(flag){
					Map<String, Map<String, String>> m1 =  tcList.get(tcd.getInput()).getValues(); 
					Set<String> set = m1.keySet();
					set.remove("EXECUTION");
					set.remove("REFERENCE");
					for(String s: set){
						Event ev = new Event();
						boolean skip = false;
						if(m1.get(s).get("REFERENCE") != null){
							ev.setElement(tcd.getInput()+"_"+m1.get(s).get("REFERENCE"));
							try{
								if(m1.get(s).get("PRIORITY") != null){
									ev.setPriority((int)Double.parseDouble(m1.get(s).get("PRIORITY")));
								}
							}catch(NumberFormatException ne){
								
							}
							String value = m1.get(ev.getElement()).get(tcd.getInputTestDataParam());
							if(value != null && value.length() > 0){
								ev.setValue(value);
							}else{
								skip = true;
								continue;
							}
							if(m1.get(s).get(Constants.INPUT_TYPE) != null){
								if(m1.get(s).get(Constants.INPUT_TYPE).equals(Constants.TEXTBOX)){
									ev.setEvent(Constants.SENDKEYS);
								}else{
									ev.setEvent(m1.get(s).get(Constants.INPUT_TYPE));
								}	
							}
							ev.setInput(tcd.getInput());
							String key = s.replace(tcd.getInput()+"_", ""); 
							Validator.validaterMap1.put(key, value);
							list100.add(ev);
						}
					 }
					continue;
				 }
				 
				if(keyWordDefMap.get(tcd.getKeyWord()) != null){
					List<Event> list11 = getEvenList(tcd, tcList, keyWordDefMap);
					list100.addAll(list11);
				}
				TESTCOUNT++;
			}
		
			eventMap.put(tid, list100);
			for(String STR:	eventMap.keySet()){
				List<Event> list = eventMap.get(STR);
				if(list != null){
					updateValues(list, tcList);
					Util.updateNavigationValues(list, this.getNavigationMap());
					updateWithObjData(list,this.getObjMap() , this.getObjectIdMap());
				}
			}
			
			for(TestCaseBean tb: this.getTestCaseSet()){
				  if(eventMap.get(tb.getTestCaseID()) !=null){
					  tb.setEventList(eventMap.get(tb.getTestCaseID()));					    
					  this.getTestCaseMap().put(tb.getTestCaseID(), tb);
				  }
			}
			return this;
	}

	
	private static List<Event> getEvenList(TestCaseDefinition tcd, Map<String, TestCaseValues> tcList, Map<String, KeyWordDef> keyWordDefMap) {
		List<Event> list100 = new ArrayList<>();
		for(Event e: keyWordDefMap.get(tcd.getKeyWord()).getEventList()){
			Event ee = new Event();
			ee.setInput(tcd.getInput());
			ee.setInputTestData(tcd.getInputTestDataParam());
			ee.setUserParam1(tcd.getUserParam1());
			ee.setUserParam2(tcd.getUserParam2());
			ee.setEvent(e.getEvent());
			if(tcd.getInput() != null && tcd.getInput().length() > 0){
				ee.setValue(tcd.getInput()+"_"+e.getValue());
			}else{
				ee.setValue(e.getValue());
			}
			ee.setType(e.getType());
			ee.setText(e.getText());
			ee.setAction(e.getAction());
			ee.setFetchData(e.getFetchData());
			ee.setElement(e.getElement());
			ee.setWaitTime(e.getWaitTime());
			list100.add(ee);
		}
		return list100;
	}
	
	private static void updateValues(List<Event> list2, Map<String, TestCaseValues> tcList) {
		for(Event event: list2){
			if(tcList.get(event.getInput()) != null){
				if(tcList.get(event.getInput()).getValues() != null){
					Map<String, String> map = tcList.get(event.getInput()).getValues().get(event.getValue());
						if(map != null){
							String val = map.get(event.getInputTestData());
							if(val != null){
								event.setValue(val);
							} 
						}else{
							event.setValue(event.getValue().replace(event.getInput()+"_", ""));
						}
			
				}
			}
		}
	}
	
private static void updateWithObjData(List<Event> list, Map<String, ObjectData> objMap, Map<String, String> objIdMap ) {
	
	for(Event event: list){	
		if(objMap.get(event.getElement()) !=null){
			ObjectData objData = objMap.get(event.getElement());
		    event.setElement(objData.getObjRef());
		    event.setType(objData.getObjType());
		    event.setElementType(objData.getObjType()); // need to check why we are setting in two places.    
		} 
	}			
				
}
}
