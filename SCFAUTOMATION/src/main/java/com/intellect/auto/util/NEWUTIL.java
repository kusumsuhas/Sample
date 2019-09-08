package com.intellect.auto.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.intellect.auto.bean.Event;
import com.intellect.auto.bean.KeyWordDef;
import com.intellect.auto.bean.TestCaseValues;

public class NEWUTIL {
	public static Map<String, TestCaseValues>  readTestCaseValues(HSSFSheet sheet, Map<String, String> MAP) { 
		Map<String, String> finalMap = new HashMap<String, String> ();
		Map<String, TestCaseValues> tcmap = new HashMap<>();
		Iterator<Row> iterator = sheet.iterator();
		iterator.next();
		int i = 1;
		TestCaseValues tVal = new TestCaseValues();
		Map<String, Map<String, String>> values = new  HashMap<>();
		String keyWord = "";
		while(iterator.hasNext()){
			 Row nextRow = iterator.next();
			 i =1;
			 if(keyWord.equals("") && !Util.getStringValue(nextRow.getCell(0)).equals("")){
				 keyWord = Util.getStringValue(nextRow.getCell(0));
				 System.out.println(" \n  KEYWORD :::::::::: "+keyWord);
				// Util.sb.append(" \n KEYWORD :::::::::: "+keyWord);
				 tVal.setKeyWord(Util.getStringValue(nextRow.getCell(0)));
				 tcmap.put(keyWord, tVal);
			 }
			 String key = nextRow.getCell(i++).getStringCellValue();	  
			 System.out.println("  \n  KEY :::::::::: "+key);
			 String val  = "VALUE";
			 int j =1;
			 HashMap<String, String> map= new HashMap<>();
			 while(nextRow.getCell(i) != null){
				 map.put(val+j, nextRow.getCell(i).getStringCellValue());  
				 i++;
				 j++;
			 }
			 System.out.println("\n"+key+"   MAP :::::::::: "+map+"   "+MAP+"  "+map.get(MAP.get(key)));
//			 if(map.containsKey(MAP.get(key))){
//				 finalMap.put(key, map.get(MAP.get(key)));
//			 }else{
//				 finalMap.put(key, map.get("VALUE1"));
//			 }
			 values.put(key, map);
		 }
		 tVal.setValues(values);
	 	for(String st: tcmap.keySet()){
	 		//Util.sb.append(st+"  MAP:::::::::::::::::::::::: "+tcmap.get(st).getValues());
		}
	 	
	 	//Util.sb.append("\n  MMMMMMMM "+tcmap);
		// System.out.println(" tcmap "+tcmap.get("LoginSCF_BO").getValues());
		return tcmap; 
	}
	
//	public static HashMap<String, List<Event>> getEvents(HSSFSheet sheet, Map<String, KeyWordDef> keywordDefMap, Map<String, String> objMap1) {
	public static HashMap<String, List<Event>> getEvents(HSSFSheet sheet, Map<String, KeyWordDef> keywordDefMap) {
		List<Event> list = new ArrayList<Event>();
		HashMap<String, List<Event>> map = new HashMap<String, List<Event>>();
		Iterator<Row> iterator = sheet.iterator();
		 iterator.next();  iterator.next();  iterator.next();
		 String id = "";
		 int i = 0;
		 while (iterator.hasNext()) {
			 	i=0;
				Row nextRow = iterator.next();	
				Event bean = new Event();
				if(!id.equals("") && !id.equalsIgnoreCase(getStringValue(nextRow.getCell(0)))){
					map.put(id, list);
					list = new ArrayList<Event>();
				}
					bean.setTestcaseId(getStringValue(nextRow.getCell(i++)));
					bean.setSubTask(getStringValue(nextRow.getCell(i++)));
					String event = getStringValue(nextRow.getCell(i++));
					if(event.equals("FUNCTION") && !getStringValue(nextRow.getCell(6)).equals("SKIP") ){
						Util.sb.append("\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+bean.getSubTask());
						 
						List<Event> list1 =  clone(keywordDefMap.get(bean.getSubTask()).getEventList());
						//display(list1);
						List<Event> list2 = new ArrayList<>();
						for(Event EE: list1){
							Event ee = new Event();
							ee.setAction(EE.getAction());
							ee.setElement(EE.getElement());
							ee.setEvent(EE.getEvent());
							ee.setSeq(EE.getSeq());
							ee.setSubTask(bean.getSubTask());
							ee.setType(EE.getType());
							ee.setWaitTime(EE.getWaitTime());
							ee.setxPath(EE.getxPath());
							ee.setValue(EE.getValue());
							ee.setTestcaseId(bean.getTestcaseId());
							ee.setInput(getStringValue(nextRow.getCell(10)));
							//ee.setFunctionName(functionName);
							list2.add(ee);
						}
						//updateXPath(list2, getStringValue(nextRow.getCell(5))); 
						//display(list2);
						list.addAll(list2);
						//display(list);
						continue;
		
					}
					bean.setEvent(event);
					bean.setText(getStringValue(nextRow.getCell(i++)));
					bean.setType(getStringValue(nextRow.getCell(i++)));
					String str = getStringValue(nextRow.getCell(i++));
					bean.setInput(getStringValue(nextRow.getCell(10)));
					bean.setElement( str);
//					if(objMap.get(str) !=null){
//						bean.setElement(objMap.get(str));
//					}else{
//						bean.setElement(str);
//					}
					bean.setAction(getStringValue(nextRow.getCell(i++)));
					bean.setSeq(getIntValue(nextRow.getCell(i++)));
					bean.setValue(getStringValue(nextRow.getCell(i++)));
					bean.setWaitTime((long)getIntValue(nextRow.getCell(i++)));
					id = getStringValue(nextRow.getCell(0));
					list.add(bean);
					//display(list);
					
		}	
		 map.put(id, list);
		 
		
		
		// System.out.println(" READ::  NEWTESTDATA1 "+"   " +"   "+map);
		return map;
	}
	public static boolean display(List<Event> eList){
		System.out.println(" *******************************************");
		for(Event E: eList){
			Util.sb.append(" \n  FUNCTION    "+E);
			//System.out.println(" TASK  EVENT:: "+E.getEvent()+"  XPATH "+E.getxPath()+" VALUE:: "+E.getValue());
		}
		return true;
	}
	
	private static void updateXPath(List<Event> eventList, String stringValue) {
		//stringValue = "stringValue";
		if(eventList != null){
		for(Event event: eventList){
			event.setxPath(stringValue);
		}
		}
	}
	public static String getStringValue(Cell cell){
		if(cell != null){
			return cell.getStringCellValue();
		}
		return "";
	}
	
	public static int getIntValue(Cell cell){
		if(cell != null){
			return (int)cell.getNumericCellValue();
		}
		return 0;
	}
	
	public static boolean getBooleanValue(Cell cell){
		if(cell != null){
			return cell.getBooleanCellValue();
		}
		return false;
	}
	public static<T> List<T> clone(List<T> org){
		List<T> copy = org.stream().collect(Collectors.toList());
		return copy;
		
	}

}
