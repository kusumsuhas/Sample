package com.intellect.auto.util;

 





import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import com.intellect.auto.bean.Event;


 

public class DisplayData {
	public static int count = 50;
	
	public static int seqNo = 1;
   public static HashMap<String, String> data1 =  new  HashMap<String, String>();
   public static HashMap<String, String> data2 =  new  HashMap<String, String>();
   public static HashMap<String, String> data3 =  new  HashMap<String, String>();
   public static TreeSet<String> set = new TreeSet<String>();
   private static String currentClass="";
   private static String previousClass="";
   
   private static ArrayList<String> classList = new ArrayList<String>();
   public static StringBuilder sb = new StringBuilder();
   
   
   public static boolean getString(Object object, String obj){
	   
	   
	   getData(object, 2);
		 for(String s: data3.keySet()){
		 
			 System.out.println(s+"  "+obj+"."+s+"()");
		 }
	   return true;
	   
   }
	public static void main(String[] args) {
		Event E = new Event();
		E.setAction("AAAAAA");
		 		getData(new Event(), 2);
		 for(String s: data3.keySet()){
		//	 logData(s,data1.get(s),data2.get(s));
			 System.out.println("  "+s);
		 }
		 
		 getString(new Event(), "e");
		 
		//System.out.println("*************** "+getClassList()  +"  "+data2);
		  
	}
	
	public static String getCurrentClass() {
		return currentClass;
	}
	public static void setCurrentClass(String currentClass) {
		DisplayData.currentClass = currentClass;
	}
	public static String getPreviousClass() {
		return previousClass;
	}
	public static void setPreviousClass(String previousClass) {
		DisplayData.previousClass = previousClass;
	}
	public static ArrayList<String> getClassList() {
		return classList;
	}
	public static void setClassList(ArrayList<String> classList) {
		DisplayData.classList = classList;
	}

	 
	
	public static Set getObjects(Class c) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		
		TreeSet<String> set = new  TreeSet<String>();
		Class cls = c.getClass(); 
		 Method[] methods = cls.getDeclaredMethods();
		 
		 for(Method m: methods){
			 if(m.getName().contains("get")){
			 Method m1 = cls.getDeclaredMethod(m.getName());
			 Object obj = m1.invoke(c);
			 //if()
			 
			 }
		 }
		 
		return set;
		
	}
	public static HashMap<String, String> getData(Object obj, int type){
		Class cls = obj.getClass();
		  
		 Method[] methods = cls.getDeclaredMethods();
		 try {
			return display(methods, cls, obj, type);
		} catch (SecurityException e) {
			 
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			 
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			 
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			 
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			 
			e.printStackTrace();
		}
		 return new HashMap<String, String>();

	}
	public static HashMap<String, String>  display(Method[] methods, Class cls, Object obj1, int type) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
	for(Method m: methods){
		 if(m.getName().startsWith("get")){
		 Method m1 = cls.getDeclaredMethod(m.getName());
		 //System.out.println( " CLASS NAME:  "+cls.getSimpleName()+"    "+obj1.getClass().getSimpleName() +"  "+m1.getName());
		//  sb.append(" CLASS NAME:  "+cls.getSimpleName()+"    "+obj1.getClass().getSimpleName() +"  "+m1.getName()+"\n");
		 Object obj = m1.invoke(obj1);
		 if(obj == null) {
			// logData(m1.getName(), obj);W
			
			 if(type ==1){
				 data1.put(getClassListAsString()+"::"+m1.getName()+"   "+seqNo++, ""+obj);
				 }else{
					// System.out.println(getClassListAsString()+"::"+m1.getName()+"   "+seqNo+++ ""+obj);
					 data2.put(getClassListAsString()+"::::::::::::::::"+m1.getName()+"   "+seqNo++, ""+obj); 
				 }
			 data3.put(m1.getName(), m1.getName());
			 continue;
		 }
		 String ss = obj.getClass().getSimpleName();
		// System.out.println(m1.getName()+" methods NAMES ###################################### "+ss);
		 if(set.contains(ss)){
			// System.out.println( " CLASS NAMES *******************&&&&&&&&&&&&&&&  "+obj.getClass().getSimpleName());
			// data1.put(obj1.getClass().getSimpleName()+"."+ ""+obj.getClass().getSimpleName(), "###########"+obj.getClass().getSimpleName());
			 //data2.put(obj1.getClass().getSimpleName()+"."+ ""+obj.getClass().getSimpleName(), "###########"+obj.getClass().getSimpleName());
			 getClassList().add(obj.getClass().getSimpleName());
			 setCurrentClass(obj.getClass().getSimpleName());
			 Method[] methods12 = obj.getClass().getDeclaredMethods();
			 display(methods12, obj.getClass(), obj , type);
		 }else{
			// logData(m1.getName(), obj);
			 if(type ==1){
			 data1.put(getClassListAsString()+"::"+m1.getName()+"   "+seqNo++, ""+obj);
			 }else{
				 data2.put(getClassListAsString()+"::"+m1.getName()+"   "+seqNo++, ""+obj); 
			 }
		 }
		 }
	 }
	
	getClassList().remove(getCurrentClass());
	 return new HashMap<String, String>();
	}
	private static String getClassListAsString() {
		String st = "";
		 for(String s: getClassList()){
			 st+=s+":";
		 }
		return st;
	}

	public static boolean logData(Object... data){
		Object s = data[0];
		int length = 4;
		if(data[1] != null){
			length = (""+data[1]).length();
		}
		 s = s+getSpace(count-(""+s).length())+data[1]+getSpace(count-length)+data[2]; 
		 System.out.println(s);
		 sb.append(s+"\n");
		return true;
	}
	private static String getSpace(int length){
		String space = "";
		for(int i=0; i< length; i++){
			space +=" ";
		}
		return space;
	}

	
	
   /*public static int count = 30;
   public static HashMap<String, String> data1 =  new  HashMap<String, String>();
   public static HashMap<String, String> data2 =  new  HashMap<String, String>();
   public static HashMap<String, String> data3 =  new  HashMap<String, String>();
   
   
   
   
   
   public static TreeSet<String> set = new TreeSet<String>();
   public static StringBuilder sb = new StringBuilder();
   public static int classCount = 1;
   public static int methodCount = 1;
   
   public static String className = "LC";
	public static void main(String[] args) {
		 
//		 getData(emp, 1);
//		 getData(emp1, 2);
//		 
//		 for(String s: data1.keySet()){
//			 logData(s,data1.get(s),data2.get(s));
//		 }
	}
	
	static{
		set.add("BasicInfo");
		set.add("Terms");
		set.add("ContractDetails");
		set.add("CreditDetails");
		set.add("PendList");
		set.add("Miscellaneous");
		set.add("CommentList");
		set.add("ALData2Save");
		set.add("ProprietaryMsgDtls");
		set.add("PrimaryCustomer");
	}
	
	public static Set getObjects(Class c) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		
		TreeSet<String> set = new  TreeSet<String>();
		Class cls = c.getClass(); 
		 Method[] methods = cls.getDeclaredMethods();
		 
		 for(Method m: methods){
			 if(m.getName().contains("get")){
			 Method m1 = cls.getDeclaredMethod(m.getName());
			 Object obj = m1.invoke(c);
			  
			 
			 }
		 }
		 
		return set;
		
	}
	public static HashMap<String, String> getData(Object obj, int type){
		Class cls = obj.getClass();
		  
		 Method[] methods = cls.getDeclaredMethods();
		 
		 
		 try {
			return display(methods, cls, obj, type);
		} catch (SecurityException e) {
			 
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			 
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			 
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			 
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			 
			e.printStackTrace();
		}
		 return new HashMap<String, String>();

	}
	private static HashMap<String, String>  display(Method[] methods, Class cls, Object obj1, int type) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
	for(Method m: methods){
		 if(m.getName().contains("get")){
		 Method m1 = cls.getDeclaredMethod(m.getName());
		 sb.append(" METHOD NAME   ss   :::::   *******************  "+m.getName()+"\n");
		 Object obj = m1.invoke(obj1);
		 if(obj == null) {
			// logData(m1.getName(), obj);
			 
			 if(type ==1){
				 data1.put(cls.getName()+"###"+m1.getName()+" ::::: "+methodCount++, ""+obj);
				 }else{
					 data2.put(cls.getName()+"###"+m1.getName()+" ::::: "+methodCount++, ""+obj); 
				 }
			 continue;
		 }
		 String ss = obj.getClass().getSimpleName();
		//ystem.out.println(m1.getName()+" methods NAMES  "+ss);
		 sb.append(" CLASS NAME   ss   :::::  " +ss+"  \n ");
		 
		 if(set.contains(ss)){
			 
			// data1.put("CLASS::"+classCount++, ss);
			 Method[] methods12 = obj.getClass().getDeclaredMethods();
			 display(methods12, obj.getClass(), obj , type);
			 
			 
		 }else{
			// logData(m1.getName(), obj);
			 if(type ==1){
				 data1.put(cls.getName()+"###"+m1.getName()+" ::::: "+methodCount++, ""+obj);
			 }else{
				 data2.put(cls.getName()+"###"+m1.getName()+" ::::: "+methodCount++, ""+obj); 
			 }
		 }
		 }
	 }
	 return new HashMap<String, String>();
	}
	public static boolean logData(Object... data){
		Object s = data[0];
		int length = 4;
		if(data[1] != null){
			length = (""+data[1]).length();
		}
		s = s+getSpace(count-(""+s).length())+data[1]+getSpace(count-length)+data[2]; 
		 System.out.println(s);
		 sb.append(s+"\n");
		return true;
	}
	private static String getSpace(int length){
		if(length < 0) return "    ";
		String space = "";
		for(int i=0; i< length; i++){
			space +=" ";
		}
		return space;
	}*/
}



