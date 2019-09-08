package com.intellect.auto.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellect.auto.bean.Event;
//import com.intellect.trade.hooks.Hook;
import com.intellect.auto.bean.KeyWordDef;
import com.intellect.auto.bean.ObjectData;
import com.intellect.auto.bean.ProjectBean;
import com.intellect.auto.bean.ReportBean;
import com.intellect.auto.bean.TestCaseBean;
import com.intellect.auto.bean.TestCaseDefinition;
import com.intellect.auto.bean.TestCaseValues;
import com.intellect.auto.bean.VerifyBean;
import com.intellect.auto.validation.Validator;


 

public class Util {
	
	
	public static String dynamicValue = "";
	public static ProjectBean projectBean;
	
	public static List<KeyWordDef> list = new ArrayList<>();
	public static Map<String, String> objMap = new HashMap<>();
	public static Map<String, String> indFMap = new HashMap<>();
	public static StringBuffer sb = new StringBuffer();
	
	private static Logger Log1 = Logger.getLogger("app");
	private static Logger Log = Logger.getLogger(Util.class.getName());
	static{
//		try {		
//			ProjectBean pBean = ProjectBean.getProjrctBeanNEW();
//			for(TestCaseBean tBean : pBean.getTestCaseSet()){
//				Log1.debug(" TID::  "+tBean.getTestCaseID());
//			}
//			sb.append(pBean.getTestCaseSet());
//			 writefile(sb.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public static boolean readExcel(String excelFilePath) throws IOException{
		/*FileInputStream file;
		try {
			file = new FileInputStream(new File(excelFilePath));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			sb.append(" START \n ");
			TreeSet<TestCaseBean> set = getTestCases(workbook.getSheet("EXECUTION_SET"));
			ProjectBean bean = getProjectBean(workbook.getSheet("PROJECTINFO"));
			
			objMap = getObjectValues(workbook.getSheet("ObjectData"));
			Map<String, KeyWordDef> functionDefMap = readTestCaseDef(workbook.getSheet("FUNCTIONAL_FUNCTIONDEF"));
			functionDefMap.putAll(readTestCaseDef(workbook.getSheet("OPERATIONAL_FUNCTIONDEF")));
			
			Map<String, String> envValues = readEnvdata(workbook.getSheet("ENVCONFIG"));
			HashMap<String, List<Event>> map = getEvents(workbook.getSheet(Constants.TESTEVENTS), functionDefMap);
			indFMap = readEnvdata(workbook.getSheet("FIELD_VALUE_MAP"));
			
			Map<String, String> tValues    =  readTestCaseValues(workbook.getSheet("TestCaseValues"), envValues);
			Map<String, String> objValues    =  readTestCaseValues(workbook.getSheet("ObjectValues"), envValues);
			
			sb.append("\n  indFMap:::   "+indFMap);
			sb.append("\n  objValues::: Before "+objValues);
			tValues.putAll(objValues);
			sb.append("\n  objValues::: After  "+tValues);
		 //	Map<String, TestCaseValues> tcMap    = NEWUTIL.readTestCaseValues(workbook.getSheet("TestCaseValues"), envValues);
			
			System.out.println("    testCaseValues  "+tcMap);
			sb.append("\n   testCaseValues ***************8 "+tcMap);
			  
			
		  for(TestCaseBean tb: set){
			 // System.out.println("   IIIIIIIIIIIIIIII "+map.get(tb.getTestCaseID()));
			  if(map.get(tb.getTestCaseID()) !=null){
				 updateWithValues(map.get(tb.getTestCaseID()), tValues, tcMap);
			  }
				  
			  //System.out.println("  map.get(tb.getTestCaseID() "+map.get(tb.getTestCaseID()));
			  if(map.get(tb.getTestCaseID()) !=null){
			   tb.setEventList(map.get(tb.getTestCaseID()));
			   List<Event> e1 =  tb.getEventList();
			   bean.getTestCaseMap().put(tb.getTestCaseID(), tb);
			  }
		  }
		  bean.setTestCaseSet(set);
		  Set<TestCaseBean> s = bean.getTestCaseSet();
		  projectBean = bean;
		  System.out.println("  #######  STRUCTURE ");
		  
		  System.out.println(" TESTCASES ");
		  
		  for(TestCaseBean T: projectBean.getTestCaseSet()){
			  System.out.println(" TID:: "+T.getTestCaseID());
			 
			  List<Event> e1= T.getEventList();
			  if(e1 !=null){
			  for(Event E: e1){
				  System.out.println(" EVENT "+E.getEvent());
			  }
			  }	
		  }
		   
		} catch (FileNotFoundException e) {
			 
			e.printStackTrace();
		}*/
				return true;
	}
	public static Map<String, KeyWordDef> readTestCaseDef(HSSFSheet sheet) {
		Log.debug("LOGER ADDED ");
		Map<String, KeyWordDef> keywordDefMap = new HashMap<>();
		Iterator<Row> iterator = sheet.iterator();
		Row nextRow1 = iterator.next();		 
		boolean b = false;
		//HashMap<String, String> map = new HashMap<>();
		String keyword = "";
		KeyWordDef tcd = new KeyWordDef();
		int i =0;
		List<Event> list = new ArrayList<Event>();
		while (iterator.hasNext()) {
				Row nextRow = iterator.next();	
				i = 0;				
				if(!keyword.equals("")  && !keyword.equals(getStringValue(nextRow.getCell(0)))  && getStringValue(nextRow.getCell(0)).length() > 0){
					sb.append("\n ADDING EVENT ::::::::LISTSIZE "+keyword+"  "+list.size());
					tcd.setEventList(list);
					list = new ArrayList<Event>();
					if(!keyword.equals("")){
						keywordDefMap.put(keyword, tcd);
					}		
					tcd = new KeyWordDef();
				}
				tcd.setSubTask(getStringValue(nextRow.getCell(i++)));
				if(getStringValue(nextRow.getCell(0)).length() !=0){
					keyword = getStringValue(nextRow.getCell(0));
				}
				
				Event event = new Event();
				event.setEvent(getStringValue(nextRow.getCell(i++)));
				sb.append("\n ADDING EVENT ::::::::LISTSIZE EVENT  KW "+keyword+" EV  "+event.getEvent().length());
				//event.setText(getStringValue(nextRow.getCell(i++)));
				//event.setType(getStringValue(nextRow.getCell(i++)));
				//event.setxPath(getStringValue(nextRow.getCell(i++)));
				event.setElement(getStringValue(nextRow.getCell(i++)));
				//event.setAction(getStringValue(nextRow.getCell(i++)));
				//event.setSeq(getIntValue(nextRow.getCell(i++)));
				//event.setElementType(getStringValue(nextRow.getCell(i++)));
				event.setValue(getStringValue(nextRow.getCell(i++)));
				event.setWaitTime(getIntValue(nextRow.getCell(i++)));
				//if(! (event.getEvent() == null)){
					//if((event.getEvent().length() > 0)){
					 sb.append("\n ADDING EVENT ::::::::999999999999999999   "+event);
				      list.add(event);
			//	}
				//}
				
		}
		// display(list);
		tcd.setEventList(list);
		keywordDefMap.put(keyword, tcd);
		//sb.append("\n ADDING EVENT :::::::: KKKKKKKKKKKKKKKK "+keywordDefMap);
	 	return keywordDefMap;
	}

	
	public static Map<String, KeyWordDef> readTestCaseDef1(HSSFSheet sheet) {
		Map<String, KeyWordDef> keywordDefMap = new HashMap<>();
		Iterator<Row> iterator = sheet.iterator();
		Row nextRow1 = iterator.next();		 
		boolean b = false;
		HashMap<String, String> map = new HashMap<>();
		String keyword = "";
		KeyWordDef tcd = new KeyWordDef();
		int i =0;
		List<Event> list = new ArrayList<Event>();
		while (iterator.hasNext()) {
				Row nextRow = iterator.next();	
				i = 0;				
				if(!keyword.equals("")  && !keyword.equals(getStringValue(nextRow.getCell(0)))  && getStringValue(nextRow.getCell(0)).length() > 0){
					tcd.setValues(map);
					tcd.setEventList(list);
					list = new ArrayList<Event>();
					if(!keyword.equals("")){
						keywordDefMap.put(keyword, tcd);
					}		
					tcd = new KeyWordDef();
				}
				tcd.setSubTask(getStringValue(nextRow.getCell(i++)));
				if(getStringValue(nextRow.getCell(0)).length() !=0){
					keyword = getStringValue(nextRow.getCell(0));
					//sb.append("\n ADDING TO FUNCTION DEF ZZZZZZZZZZZZZZZZZZZZZ :::  "+keyword+"  "+list.size());	
				}
				Event event = new Event();
				event.setEvent(getStringValue(nextRow.getCell(i++)));
				event.setText(getStringValue(nextRow.getCell(i++)));
				event.setType(getStringValue(nextRow.getCell(i++)));
				//event.setxPath(getStringValue(nextRow.getCell(i++)));
				event.setElement(getStringValue(nextRow.getCell(i++)));
				//Util.sb.append("\n  OBJ:::::::::: PATH::: "+event.getxPath()+ objMap);
//				if(objMap.get(event.getxPath()) !=null){
//					Util.sb.append("\n  OBJ::::::::::  "+event.getxPath()+"  OBJ VAL  "+objMap.get(event.getxPath()));
//					event.setElement(objMap.get(event.getxPath()));
//				}else{
//					event.setElement(event.getxPath());
//				}
				event.setAction(getStringValue(nextRow.getCell(i++)));
				event.setSeq(getIntValue(nextRow.getCell(i++)));
				event.setValue(getStringValue(nextRow.getCell(i++)));
				event.setWaitTime(getIntValue(nextRow.getCell(i++)));
				list.add(event);
				//sb.append("\n ADDING EVENT :::::::: "+event);
		}
		// display(list);
		tcd.setEventList(list);
		keywordDefMap.put(keyword, tcd);
		//sb.append("\n ADDING EVENT :::::::: KKKKKKKKKKKKKKKK "+keywordDefMap);
	 	return keywordDefMap;
	}
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(PropertiesReader.getValue("Constants.DB_IP_ADDRESS"),
				PropertiesReader.getValue("Constants.DB_USER_NAME" ),
				PropertiesReader.getValue("Constants.DB_PASSWORD"));
		 
	}
	
	public static void main(String args[]){
		
	}
	
	public List<TestCaseBean>readData(String filePath, String fileName) throws FileNotFoundException{
		ArrayList<TestCaseBean> list = new ArrayList<TestCaseBean>();
		File file =    new File(filePath+"\\"+fileName);
		FileInputStream fis = new FileInputStream(file);
		HSSFWorkbook wb = new HSSFWorkbook();
		int noOfSheets = wb.getNumberOfSheets();
	    for(int i =0; i< noOfSheets; i++){
	    	HSSFSheet sheet = wb.getSheetAt(i);
	    	HSSFRow row;
		    HSSFCell cell;
	    }
		return list;
	}
	
	public static boolean switchWindowByTitle(WebDriver driver, String windowName){
		Set<String> windowHandles1 = driver.getWindowHandles();
		//System.out.println(windowHandles1);
		int count = windowHandles1.size();
		
		Log.debug("LOGER ADDED ");
		System.out.println("Total window "+count+"  windowName  "+windowName);
//		if(count == 1){
//			
//			return false;
//		}
		
		for (String window: windowHandles1) {
			 driver.switchTo().window(window);
			 System.out.println("current url is: " + driver.getCurrentUrl()+"   "+driver.getTitle()); 		 
		        if(driver.getTitle().equals(windowName)){
		        	String url = driver.getCurrentUrl();
		        	System.out.println("SWITCHEED TO "+windowName+"  current url is: " + url);  
		        	break;
		        }
		}
		return true;
	}
	
	//http://10.10.7.250:20023/BPS/IwAppControllerServlet?&FetchData=false&ResultPage=IwMainScreen.jsp&ActionId=21&UserId=ADMIN_MAKER1&ProductId=2321&KeyColumns=PRODUCT_ROOT,ENTITYID,COUNTRY_CODE,PRODUCT_CODE,PRIMARY_PARTY&KeyValues=1,2,AT,SELFIN,BUY&CopyFrom=null&RefBulkFlag=N&SecurityNote=E3NG-U88R-P3X1-HG9R-SKJO-55IL-0D74-B3E7
public static boolean switchFrameByName(WebDriver driver, String frameName){
		
	
	List<WebElement> iframes100 = driver.findElements(By.xpath("//frame"));

	System.out.println("  LOOKING FOR FRAME "+driver.getCurrentUrl()+"  "+driver.getTitle());
	 
	System.out.println("  WINDOW LWENNN ::  "+driver.getWindowHandles().size());
	System.out.println("  LOOKING FOR FRAME "+frameName);
	//System.out.println("  LOOKING FOR FRAME "+driver.getPageSource());
	for (WebElement iframe : iframes100) {
		System.out.println(" iframe List  "+iframe.getTagName()+"  "+iframe.getAttribute("name"));
		//sb.append("FRAME:::::::::: "+iframe.getTagName()+"  "+iframe.getAttribute("name")+"\n");
	    // switch to every frame
		if(iframe.getAttribute("name").equals(frameName)){
			System.out.println("  MOVED TO  FRAME "+frameName);
			driver.switchTo().frame(iframe);
			break;
		}
	}
		 
		return true;
	}


public static boolean switchIFrameByName(WebDriver driver, String frameName){
		
	
	//List<WebElement> iframes100 = driver.findElements(By.xpath("//frame"));
	int size= 1;
	//int size = driver.findElements(By.tagName("iframe")).size();
	List<WebElement> iframes100 = driver.findElements(By.tagName("iframe"));

	System.out.println(size+" SIZE:::: LOOKING FOR I FRAME "+driver.getCurrentUrl()+"  "+driver.getTitle());
	 
	System.out.println("  WINDOW LWENNN ::  "+driver.getWindowHandles().size());
	System.out.println("  LOOKING FOR FRAME "+frameName);
	//System.out.println("  LOOKING FOR FRAME "+driver.getPageSource());
	for (WebElement iframe : iframes100) {
		System.out.println(" iframe List  "+iframe.getTagName()+"  "+iframe.getAttribute("name")+"  "+iframe.toString());
		//sb.append("FRAME:::::::::: "+iframe.getTagName()+"  "+iframe.getAttribute("name")+"\n");
	    // switch to every frame
		//if(iframe.getAttribute("name").equals(frameName)){
			System.out.println("  MOVED TO  FRAME "+frameName);
			driver.switchTo().frame(iframe);
			
			switchFrameByName(driver, frameName);
			
			
			//break;
		//}
	}
		 
		return true;
	}

	
	////////////////////////
	public static void switchToMainMenu(WebDriver driver) {
		Set<String> winHandlers = driver.getWindowHandles();
		String str[] = new String[1];
		winHandlers.toArray(str);
		driver.switchTo().window(str[0]);
	}
	
	public static void highLighterMethod(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", element);
		wait(driver, 10000);
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		wait(driver, 10000);
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", element);
		wait(driver, 10000);
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
	}
	
	static public void wait(WebDriver driver, int iSec) {
		driver.manage().timeouts().implicitlyWait(iSec, TimeUnit.SECONDS);
	}
	
	
	public static InternetExplorerOptions getOptions(){
		System.out.println(" CALLING UTIL 21 0");
		 InternetExplorerOptions ieOptions = null; //new InternetExplorerOptions();
		
		
		 System.out.println(" CALLING UTIL 21 ");
		try{
			ieOptions = new InternetExplorerOptions();
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability("pageLoadStrategy", "eager");
        ieCapabilities.setCapability("allowBlockedContent", true);
        ieCapabilities.setCapability("elementScrollBehavior", true);
      //  ieCapabilities.setCapability("ie.ensureCleanSession", true);
        String lEventUrl= "http://10.10.73.19:21141/BPS/"; 
        ieCapabilities.setCapability("initialBrowserUrl", lEventUrl);
        ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
        ieCapabilities.setJavascriptEnabled(true);
        ieCapabilities.setCapability("requireWindowFocus", false);
        ieCapabilities.setCapability("unexpectedAlertBehaviour", UnexpectedAlertBehaviour.DISMISS);
        System.out.println(" CALLING UTIL 2 2");
       
        ieOptions.merge(ieCapabilities);
       // ieOptions.
        ieOptions.introduceFlakinessByIgnoringSecurityDomains();
        ieOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
       // ieOptions.destructivelyEnsureCleanSession();
        ieOptions.enablePersistentHovering();
        ieOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);
        ieOptions.takeFullPageScreenshot();
        ieOptions.withInitialBrowserUrl("about:blank");
        ieOptions.elementScrollTo(ElementScrollBehavior.TOP);
       // ieOptions.c
        System.out.println(" CALLING UTIL 23 ");
		}catch(Exception e){
			e.printStackTrace();
		}
        
        return ieOptions;
      
	}
	///////////////////////
	
	
	
	public static boolean writefile(String content){
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			//String content = "This is the content to write into file\n";

			String FILENAME ="E:\\Temp Eclipse Repository\\SCFAUTOMATION\\TestResults\\Result.txt";
			File f = new File(FILENAME);
			if(!f.exists()){
				f.createNewFile();
			}
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(content);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return true;
	}
	
	
	public static boolean writefile(String content, String fileName){
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			//String content = "This is the content to write into file\n";

			String FILENAME ="D:/GTB_HOME/SCFAUTOMATION/TestResults/DetailReports/"+fileName+".html";
			File f = new File(FILENAME);
			if(!f.exists()){
				f.createNewFile();
			}
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(content);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return true;
	}
	
	
	
	public static Map<String, String> readEnvdata(HSSFSheet sheet) {
		Map<String, String> map = new HashMap<>();
		Iterator<Row> iterator = sheet.iterator();
		//iterator.next();
		while(iterator.hasNext()){
			 Row nextRow = iterator.next();
		     map.put(nextRow.getCell(0).getStringCellValue(), nextRow.getCell(1).getStringCellValue());	 
			 
		}
		// System.out.println("   ENVDATA :::::::::: "+map);
		return map;
	}

	//public static Map<String, TestCaseValues> readTestCaseValues(HSSFSheet sheet, Map<String, String> MAP) {
	
	
	
	//public static Map<String, TestCaseValues> readTestCaseValues1(HSSFSheet sheet) {
	
	public static Map<String, Map<String, String>> readTestCaseValues1(HSSFSheet sheet) {
		//List<TestCaseValues> list = new ArrayList<TestCaseValues>();
		Map<String, TestCaseValues> testcaseVal = new HashMap<String, TestCaseValues>();
		TestCaseValues tcv = new TestCaseValues();
		Map<String, Map<String, String>> values = new HashMap<>();
		Map<String, String> map = new HashMap<>();
		Iterator<Row> iterator = sheet.iterator();
		 iterator.next();
		 List<String> list = new ArrayList<String>();
		 int i = 1;
		 String keyVal = "";
		 
		 int j =1;
		 while(iterator.hasNext()){
			 	 Row nextRow = iterator.next();
			     i = 1;
			     String key = nextRow.getCell(1).getStringCellValue();
			     String keyWord = getStringValue( nextRow.getCell(0));
			     boolean start = false;
			     if(!keyWord.equals("")){
			    	 keyVal = keyWord;
			    	 start = true;
			    	 // Util.sb.append("\n   NEW KEYWORD  "+keyWord);
			    	 list = new ArrayList<String>();
			     }
			     if(start){
			    	 keyVal = keyWord;
			     }
			     //System.out.println( "   MAP :: KEY  "+key+" keyWord "+keyWord+" keyVal  "+keyVal);
			     // Util.sb.append("\n    MAP :: KEY  "+j+++"  "+key+" keyWord "+keyWord+" keyVal  "+keyVal);
				 while(nextRow.getCell(i) != null){
					 if( start){
						 list.add(nextRow.getCell(i).getStringCellValue());
						 // System.out.println( " VALUE "+nextRow.getCell(i).getStringCellValue());
						 i++;
						// start = false;
						 continue;
					 }
					// System.out.println( "   MAP ::::::AAAAAAAAA:::: "+key+"   "+list);
				    if(list.size() >0){
				    	 map.put(list.get(i-1), nextRow.getCell(i).getStringCellValue());
				    	 // System.out.println(list.get(i-1)+ "   MAP :::::: BBBBB "+map);
				    	// Util.sb.append("\n "+"                         "+ list.get(i-1)+ "   MAP :::::: BBBBB "+map);
				    }
					// map.put(list.get(i-1), nextRow.getCell(i).getStringCellValue());
					 i++;
						 
				 }
				 values.put(key, map);
				// tcv.setValues(values);
				 //testcaseVal.put(keyVal, tcv);
				// Util.sb.append("\n "+"  MAP :::::::::: 1111111111111111 "+key+"   "+map);
				 map = new HashMap<>();
				 //System.out.println(keyVal+ "   MAP :::::::::: 1111111111111111 "+key+"   "+map);
				
				 
			 }
			 
		 //System.out.println( "   MAP ::::::::::  123  "+values);
			 
		
		 // Util.sb.append("\n "+"  VALUES ::::::: "+values);
		
		return values;
		
		/*
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
//			 if(keyWord.equals("") && !getStringValue(nextRow.getCell(0)).equals("")){
//				 keyWord = getStringValue(nextRow.getCell(0));
//				 System.out.println("   KEYWORD :::::::::: "+keyWord);
//				 tVal.setKeyWord(getStringValue(nextRow.getCell(0)));
//				 tcmap.put(keyWord, tVal);
//			 }
			 
			 String key = nextRow.getCell(i++).getStringCellValue();
			 System.out.println("   KEY :::::::::: "+key);
			 String val  = "VALUE";
			 int j = 1;
			 //i++;
			 HashMap<String, String> map= new HashMap<>();
			 while(nextRow.getCell(i) != null){
				// nextRow.getCell(i).getStringCellValue()
				 map.put(val+j, nextRow.getCell(i).getStringCellValue());
				  
				 i++;
				 j++;
			 }
			 System.out.println(key+"   MAP :::::::::: "+map);
			 values.put(key, map);
			 
		 }
		 tVal.setValues(values);
	//	tcmap.put(key, tVal);
		return values;*/
	}
	
	
	public static List<TestCaseDefinition> readTcd(HSSFSheet sheet) {
		List<TestCaseDefinition> list = new ArrayList<>();
		Map<String, TestCaseValues> tcmap = new HashMap<>();
		Iterator<Row> iterator = sheet.iterator();
		 iterator.next();
		Map<String, Map<String, String>> values = new  HashMap<>();
		
		List<TestCaseDefinition> list1 = new ArrayList<>();
		boolean iterFlag = false;
		int count = 0;
		int noOfSteps = 0;
		int steps = 0;
		String input[] = {};
		 while(iterator.hasNext()){
			 int i = 0;
			 TestCaseDefinition tcd = new TestCaseDefinition();
			 Row nextRow = iterator.next();
			 if(nextRow.getCell(i) != null && nextRow.getCell(i).getStringCellValue() !=null){
				 Log1.debug(".....................  nextRow.getCell(1)  "+nextRow.getCell(1));
				 tcd.setTestcaseId(nextRow.getCell(i++).getStringCellValue());
				 if(getStringValue(nextRow.getCell(1)).contains(Constants.ITERATE_ROWS)){ 
					 iterFlag = true;
					 String str = getStringValue(nextRow.getCell(1));
					 
					 String st[] = str.split("::");
					 
					 noOfSteps = Integer.parseInt(st[1].trim());
					 for(int p =0; p< st.length; p++){
						 Util.sb.append("\n ITERATE111111     "+st[p]); 
					 }
					 input = getStringValue(nextRow.getCell(3)).split("::");
					 String itr = input[input.length-1];
					 int noOfItr = Integer.parseInt(""+itr.charAt(itr.length()-1));
					 
					 System.out.println("\n  123456789      Validator.itr  "+Validator.itr+"    "+tcd.getTestcaseId());
					 Log1.debug("      Validator.itr  "+Validator.itr+"    "+tcd.getTestcaseId());
					// noOfItr  = Validator.itr.get(tcd.getTestcaseId());
					 
					 //Util.sb.append("\n NO OF ITRATIONS "+noOfItr);
					 
					 input = new String[noOfItr];
					 for(int y=0; y< noOfItr; y++){
						 input[y] = "VALUE"+(y+1);
					 }
					 for(int k =0; k< input.length; k++){
						 Util.sb.append("\n NO OF ITRATIONS "+input[k]);
					 }
					 count = input.length;
					 Util.sb.append("\n ITERATE111111     "+getStringValue(nextRow.getCell(1)));
					 continue;
				 } 
					 
					 tcd.setKeyWord(getStringValue(nextRow.getCell(i++)));
					 tcd.setInput(getStringValue(nextRow.getCell(i++)));
					 tcd.setInputTestDataParam(getStringValue(nextRow.getCell(i++)));
					 
					 tcd.setFetchData(getStringValue(nextRow.getCell(i++)));
					 
					 
					 System.out.println(tcd.getKeyWord()+"   FETCHDATA #############################################  "+tcd.getFetchData());
					 tcd.setUserParam1(getStringValue(nextRow.getCell(i++)));
					 tcd.setUserParam2(getStringValue(nextRow.getCell(i++)));
					 
					 tcd.setUserParam3(getStringValue(nextRow.getCell(i++)));
					 
					 Util.sb.append("\n  MAP:::: NEW   ::::::::: getUserParam1 "+tcd.getUserParam1());
					 if(iterFlag){
						 Util.sb.append("\n ADDING ITERATE TCD   noOfSteps  "+noOfSteps+"  steps "+steps); 
						 list1.add(tcd);
						 steps++;
					 }else{
						 Log1.debug("\n ADDING TCD  **************   "+tcd); 
						 list.add(tcd);
					 }
				 
				 if(steps >= noOfSteps  && iterFlag){
					  
					 Util.sb.append("\n  CALLING addItrTcds::::::::  "+steps+" noOfSteps "+noOfSteps+"  list size "+list.size()+"  list1 size "+list1.size());
					 addItrTcds(list, list1, count, input);
					// Util.sb.append("\n  CALLING addItrTcds::::::::
					 iterFlag = false;
				 }
					
			 
		 }
		 }
			
			
			return list;
	}
	
	private static void addItrTcds(List<TestCaseDefinition> list, List<TestCaseDefinition> list1, int i, String[] input) {
		 
		for(int h = 0; h< i; h++){
			Util.sb.append("\n OUTER  STEPS   "+input[h]);
			TestCaseDefinition tcd1 = new TestCaseDefinition();
			for(TestCaseDefinition tcd: list1){
				tcd1 = new TestCaseDefinition();
				tcd1.setTestcaseId(tcd.getTestcaseId());
				tcd1.setKeyWord(tcd.getKeyWord());
				tcd1.setInput(tcd.getInput());
				tcd1.setUserParam3(tcd.getUserParam3());
				//if(tcd.getKeyWord().equals("FETCH_DATA")){
					
				//} 
					tcd1.setInputTestDataParam(input[h]);
				
				tcd1.setUserParam1(tcd.getUserParam1());
				tcd1.setUserParam2(tcd.getUserParam2());
				tcd1.setFetchData(tcd.getFetchData());
				tcd1.setIterationcount(h+1);
				list.add(tcd1);
				
				
				/*
				 tcd.setTestcaseId(nextRow.getCell(i++).getStringCellValue());
				 tcd.setKeyWord(getStringValue(nextRow.getCell(i++)));
				 tcd.setInput(getStringValue(nextRow.getCell(i++)));
				 tcd.setInputTestDataParam(getStringValue(nextRow.getCell(i++)));
				 tcd.setFetchData(getStringValue(nextRow.getCell(i++)));
				 tcd.setUserParam1(getStringValue(nextRow.getCell(i++)));
				 tcd.setUserParam2(getStringValue(nextRow.getCell(i++)));*/
				
				Util.sb.append("\n INNER STEPS   "+list1.size());
			}
			
		}
		
	}
	public static List<TestCaseDefinition> readTcd1(HSSFSheet sheet) {
		List<TestCaseDefinition> list = new ArrayList<>();
		Map<String, TestCaseValues> tcmap = new HashMap<>();
		Iterator<Row> iterator = sheet.iterator();
		 iterator.next();
		Map<String, Map<String, String>> values = new  HashMap<>();
		
		 while(iterator.hasNext()){
			 int i = 0;
			 TestCaseDefinition tcd = new TestCaseDefinition();
			 Row nextRow = iterator.next();
			 if(nextRow.getCell(i) != null && nextRow.getCell(i).getStringCellValue() !=null){
				 if(nextRow.getCell(i).getStringCellValue().equalsIgnoreCase("TC_1")){
				 tcd.setTestcaseId(nextRow.getCell(i++).getStringCellValue());
				 tcd.setTestStep(""+getIntValue(nextRow.getCell(i++)));
				 tcd.setKeyWord(getStringValue(nextRow.getCell(i++)));
				 tcd.setParam1(getStringValue(nextRow.getCell(i++)));
				 tcd.setParam2(getStringValue(nextRow.getCell(i++)));
				 tcd.setParam3(getStringValue(nextRow.getCell(i++)));
				 tcd.setInput(getStringValue(nextRow.getCell(i++)));
				 tcd.setOutput(getStringValue(nextRow.getCell(i++)));
				 tcd.setAction(getStringValue(nextRow.getCell(i++)));
				 tcd.setPreReq(getStringValue(nextRow.getCell(i++)));
				 tcd.setPostReq(getStringValue(nextRow.getCell(i++)));
				 tcd.setRetain(getStringValue(nextRow.getCell(i++)));
				 tcd.setCaptuValueType(getStringValue(nextRow.getCell(i++)));
				 
				 list.add(tcd);
			 }
		 }
		 }
			
			
			return list;
	}
	public static Map<String, String> readTestCaseValues(HSSFSheet sheet, Map<String, String> MAP) {
		 
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
			 if(keyWord.equals("") && !getStringValue(nextRow.getCell(0)).equals("")){
				 keyWord = getStringValue(nextRow.getCell(0));
				
				 tVal.setKeyWord(getStringValue(nextRow.getCell(0)));
				 tcmap.put(keyWord, tVal);
			 }
			 
			 String key = nextRow.getCell(i++).getStringCellValue();
			  
			 System.out.println("   KEY :::::::::: "+key);
			 String val  = "VALUE";
			 int j =1;
			 HashMap<String, String> map= new HashMap<>();
			 while(nextRow.getCell(i) != null){
				// nextRow.getCell(i).getStringCellValue()
				 map.put(val+j, nextRow.getCell(i).getStringCellValue());
				  
				 i++;
				 j++;
			 }
			 System.out.println(key+"   MAP :::::::::: "+map+"   "+MAP+"  "+map.get(MAP.get(key)));
			 if(map.containsKey(MAP.get(key))){
				 finalMap.put(key, map.get(MAP.get(key)));
			 }else{
				 finalMap.put(key, map.get("VALUE1"));
			 }
			 values.put(key, map);
			 
		 }
		
		 tVal.setValues(values);
	//	tcmap.put(key, t
		 System.out.println(" finalMap "+finalMap);
		return finalMap;
	}

	public static Map<String, ObjectData> getObjectValues(HSSFSheet sheet, int j) {
		Map<String, ObjectData> objMap = new HashMap<>();
		Iterator<Row> iterator = sheet.iterator();
		Row nextRow1 = iterator.next();
		
		while (iterator.hasNext()) {
			int i =0;
				Row nextRow = iterator.next();	
				ObjectData objData = new ObjectData();
				
				objData.setLevel1(getStringValue(nextRow.getCell(i++)));
				objData.setLevel2(getStringValue(nextRow.getCell(i++)));
				objData.setLevel3(getStringValue(nextRow.getCell(i++)));
				objData.setElement(getStringValue(nextRow.getCell(i++)));
				objData.setObjType(getStringValue(nextRow.getCell(i++)));
				objData.setObjRef(getStringValue(nextRow.getCell(i++)));
				
				
				if(objData.getElement() != null && objData.getElement().length() > 0){
				 objMap.put(objData.getElement(), objData);
				// Log1.debug("  MAP::::::::::::::::::::::::::"+objData.getElement());
				}else{
					String key = objData.getLevel1()+"_"+objData.getLevel2()+"_"+objData.getLevel3();
					objData.setElement(key);
					 objMap.put(key, objData);
					 //Log1.debug("  MAP::::::::::::key::::::::::::::"+key);
				}
				
				// Log1.debug("  MAP:::::::::::::::::OBJ DATA:::::::::"+objData);
		}
		//Log1.debug("  MAP::::::::::::::::::::::::::  "+objMap.keySet());
		return objMap;
	}
	
	
	public static Map<String, String> getObjectValuesOLD(HSSFSheet sheet, int j) {
		Map<String, String> objMap = new HashMap<>();
		Iterator<Row> iterator = sheet.iterator();
		Row nextRow1 = iterator.next();
		int i =0;
		while (iterator.hasNext()) {
				Row nextRow = iterator.next();	
				i = 3;
				if(j==1){
				 objMap.put(getStringValue(nextRow.getCell(i++)),  getStringValue(nextRow.getCell(i+1)));
				}else{
					objMap.put(getStringValue(nextRow.getCell(i++)),  getStringValue(nextRow.getCell(i)));
				}
		}
		return objMap;
	}

	//public static void updateWithValues(List<Event> list2, Map<String, String> map, TestCaseValues testCaseValues) {
	
	public static void updateWithValues(List<Event> list2, Map<String, String> map, Map<String, TestCaseValues> map1) {
		
		//sb.append(" \n  &&&&&&&&&&&&&&&&&&&&&  %%%%%%%%%%%%  ");
		
		for(Event e: list2){
			
			sb.append(" \n    "+e);
		}
		sb.append(" \n  &&&&&&&&&&&&&&&&&&&&& ");
		
		sb.append(" \n  AAAAAAAA   "+map);
		
		
		sb.append(" \n  &&&&&&&&&&&&&&&&&&&&& ");
		
		
		sb.append(" \n  BBBBBBBBBBBB  "+map1);
		
		if(map1 == null){
			sb.append(" \n  &&&&&&&&&&&&&&&&&&&&& ");
			return;
		}
		Map<String, Map<String, String>> values= new HashMap<>();
		 String key = "";
	//	 Util.sb.append("\n >>>>>>>>>>>>>>>>>>>>>>  MMMMMMMMMMMM  "+map);
		 for(Event e: list2){
			 Util.sb.append("\n >>>>>> "+e);
			 if(e.getSubTask() == null){
				// Util.sb.append("\n >>>>>>>>>>>>>>>>>>>>>> "+e.getElement());
				 e.setSubTask("A");
			 }
			 if(!key.equals(e.getSubTask())){
				 if(map1.get(e.getSubTask()) != null){
					 values = map1.get(e.getSubTask()).getValues();
					 key =e.getSubTask();
					// Util.sb.append("\n >>>>>>>>>>>>>>>>>>>>>> "+values);
				 }
			 }
			 if(map.containsKey(e.getValue())){
				  e.setValue(map.get(e.getValue()));
			 Util.sb.append("\n >>>>>>>>>>>>>>>>>>>>>> <<<<<<<<<<<< Contains "+e.getValue()+"  "+map.get(e.getValue()));
			 }else{
				 
				sb.append(" \n......%%%%%%%%%%%%%%%%%%555. 1 "+values+"  :: VAL:  "+e.getValue()+"  MAP "+map);
				
				
				 if(values.get(e.getValue()) != null){
				 if(e.getxPath() == null){
					// System.out.println(indFMap+" SETTTTTTTTTTTTTTTTTTTTTT WWWWWWW "+indFMap.get(e.getValue()));
					// sb.append(" \n......%%%%%%%%%%%%%%%%%%555. 1 "+indFMap.get(e.getValue()));
					 e.setxPath(indFMap.get(e.getValue()));
				 }
				 if(values.get(e.getValue()).get(e.getxPath())!=null){
				 if(values.get(e.getValue()).get(e.getxPath()).contains("OBJ::")){
					String str =  values.get(e.getValue()).get(e.getxPath());
					
					//System.out.print( " $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$  "+str+"     ");
					str = str.replaceAll("OBJ::","");
					e.setElement(objMap.get(str));
					sb.append(" \n....... 2 "+objMap.get(str));
					System.out.println( "   "+objMap.get(str)+"     ");
					
					//Util.sb.append("\n >>>>>>>>>>>>>>>>>>>>>> "+objMap.get(str)+"  "+str);
				 }else{
					 e.setValue(values.get(e.getValue()).get(e.getxPath()));
				 }
				 
				 }else{
					 
					 System.out.println(" SETTTTTTTTTTTTTTTTTTTTTT   "+e.getElement()+"     "+e.getValue());
					 e.setElement("");
					 e.setValue(indFMap.get(e.getElement()));
				 }
				 //e.setElement(values.get(e.getValue()).get(e.getxPath()));
				 System.out.println("  e.getValue90 111 ELE "+e.getElement()+"  VAL  "+e.getValue());
			 }
		 }
			 //Util.sb.append("\n <<<<<<<<< "+e);
			// sb.append(" \n  MAP ::::::: 22   &&&&&&&&& VALUE "+e.getValue()+" EVENT:: "+e.getEvent()+" XPATH "+e.getxPath() );
		 }
		//}
	}

	//public static void updateWithObjectValues(List<Event> list2, KeyWordDef testCaseDef) {
	public static void updateWithObjectValues(List<Event> list2, Map<String, String> map) {
		
		System.out.println(" \n MAP :::: "+map+"  EVAL  " );
		 for(Event e: list2){
			 System.out.println(" MAP ::::&&&&&&&&&  ::"+e.getValue()+"##::"+e.getElement());
			 if(map.containsKey(e.getValue())){
				 
				 e.setValue(map.get(e.getValue()));
				 
				 System.out.println(" VALUE SET "+e.getValue()+"  "+map.get(e.getValue()));;
			 }
		 }
		 
	}
	
	public static boolean display(List<Event> eList, String str){
		int i =0;
		for(Event E: eList){
			Log1.debug(str+"*********  "+E);
			i++;
			if( i > 10) break;
			//System.out.println("\n *********  "+E);
			//System.out.println(" TASK  EVENT:: "+E.getEvent()+"  XPATH "+E.getxPath()+" VALUE:: "+E.getValue());
		}
		return true;
	}
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
					
						System.out.println(" keywordDefMap "+keywordDefMap);
						System.out.println("  bean.getSubTask() "+bean.getSubTask());
						//System.out.println("  keywordDefMap.get(bean.getSubTask()) "+keywordDefMap.get(bean.getSubTask()).getEventList());
						
						
						System.out.println(" SIZZZZZZZZ "+keywordDefMap.get(bean.getSubTask()).getEventList().size());
						//	updateEvents(bean.getSubTask()).getEventList(), );
						
						
						List<Event> list1 =  clone(keywordDefMap.get(bean.getSubTask()).getEventList());
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
							//ee.setFunctionName(functionName);
							list2.add(ee);
						}
						updateXPath(list2, getStringValue(nextRow.getCell(5))); 
						display(list2, "");
						list.addAll(list2);
						display(list, "");
						continue;
		
					}
					bean.setEvent(event);
					bean.setText(getStringValue(nextRow.getCell(i++)));
					bean.setType(getStringValue(nextRow.getCell(i++)));
					String str = getStringValue(nextRow.getCell(i++));
					if(objMap.get(str) !=null){
						bean.setElement(objMap.get(str));
					}else{
						bean.setElement(str);
					}
					bean.setAction(getStringValue(nextRow.getCell(i++)));
					bean.setSeq(getIntValue(nextRow.getCell(i++)));
					bean.setValue(getStringValue(nextRow.getCell(i++)));
					bean.setWaitTime((long)getIntValue(nextRow.getCell(i++)));
					id = getStringValue(nextRow.getCell(0));
					list.add(bean);
					display(list, "");
					
		}	
		 map.put(id, list);
		 for(String ss: map.keySet()){
			 ArrayList<Event> list123 = (ArrayList<Event>) map.get(ss);
		 for(Event EE: list123){
			 System.out.println(ss+" EVENT:: :::::::::::::::::::::::: \n"+EE);
		 }
		 }
		
		 System.out.println("\n READ::  NEWTESTDATA1 "+"   " +"   "+map);
		return map;
	}

	private static void updateXPath(List<Event> eventList, String stringValue) {
		if(eventList != null){
		for(Event event: eventList){
			event.setxPath(stringValue);
		}
		}
	}

	public static<T> List<T> clone(List<T> org){
		List<T> copy = org.stream().collect(Collectors.toList());
		return copy;
		
	}
	public static  TreeSet<TestCaseBean>  getTestCases(HSSFSheet sheet) {
		TreeSet<TestCaseBean> set = new TreeSet<TestCaseBean>(); 
		 Iterator<Row> iterator = sheet.iterator();
		 boolean b = false;
		 iterator.next();	
		 int i = 0;
		 while (iterator.hasNext()) {
				i = 0;
				Row nextRow = iterator.next();	
				TestCaseBean bean = new TestCaseBean();
				bean.setModule(getStringValue(nextRow.getCell(i++)));
				bean.setSubModule(getStringValue(nextRow.getCell(i++)));
				bean.setTestCaseDesc(getStringValue(nextRow.getCell(i++)));
				//bean.setTestSuiteID(getStringValue(nextRow.getCell(i++)));
				bean.setTestCaseID(getStringValue(nextRow.getCell(i++)));
				bean.setTestDataHandle(getStringValue(nextRow.getCell(i++)));
				bean.setTestType(getStringValue(nextRow.getCell(i++)));
				
				if(getStringValue(nextRow.getCell(i++)).equals("Y")){
					bean.setExecute(true);
					Log1.debug(" SETTING TRUE");
				}else{
					Log1.debug(" SETTING FALSE");
					bean.setExecute(false);
				}	
				
				
				bean.setPriority(getIntValue(nextRow.getCell(i++)));
				bean.setOperation(getStringValue(nextRow.getCell(i++)));
				
				Log1.debug(" ADDING   "+bean.getTestCaseID());
				Log1.debug(bean);
				if(bean.isExecute()){
					set.add(bean);
				}
				Log1.debug(" ADDING SIZE::  "+set.size()+" AFTER "+bean.getTestCaseID());	
		}
		 
		 Log1.debug(" ADDING SIZE::@@@@@@@@@@@@@@@@@@ "+set);
		return set;
	}

	public static ProjectBean getProjectBean(HSSFSheet sheet){
		ProjectBean bean = new ProjectBean();
		Iterator<Row> iterator = sheet.iterator();
		boolean b = false;
		while (iterator.hasNext()) {
				Row nextRow = iterator.next();	
				bean.setProject(getStringValue(nextRow.getCell(0)));
				bean.setEnvironment(getStringValue(nextRow.getCell(3)));
				bean.setVersion(getStringValue(nextRow.getCell(1)));
				bean.setDatOfBuild(getStringValue(nextRow.getCell(2)));
				bean.setDatOfBuild(getStringValue(nextRow.getCell(4)));		 
		}
		return bean;
	}
	
	public static String getStringValue(Cell cell){
		if(cell != null){
			String str = cell.getStringCellValue();
			if(str != null){
				if(str.startsWith("#")){
					str = str.substring(1);
					return str;
				}
				return str;
			}
			
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
	
	
	
	public static final ObjectMapper OBJECT_MAPPER_SINGLETON = new ObjectMapper();
		public static String  toStringUsingJackson(final Object object) {
		    try {
		        return OBJECT_MAPPER_SINGLETON.writeValueAsString(object);
		    } catch (final JsonProcessingException e) {
		        return String.valueOf(object);
		    }
	}
		public static Map<String, List<String>> getInputFieldsList(HSSFSheet sheet) {
			
			Map<String, List<String>> map1 = new HashMap<>();
			List<String> list = new ArrayList<>();
			Iterator<Row> iterator = sheet.iterator();
			 iterator.next();
			 boolean start = true;
			 String keyWord = "";
			 String key = "";
			 String keyVal = "";
			 while(iterator.hasNext()){
			 	 Row nextRow = iterator.next();
			 	   keyWord = getStringValue( nextRow.getCell(1));
			 	   key = getStringValue( nextRow.getCell(0));
			 	 
			 	// Util.sb.append("\n KKKKKKKKKKKKKKKKKKKKKKKK  "+key+" "+list);
			 	 if(!key.equals("")){
			 		keyVal = key;
			 		 map1.put(keyVal, list);
			 		 if(!start){
			 			 list = new ArrayList<String>();
			 		 }
			 		
			    	 start = false;
			    	// Util.sb.append("\n   NEW KEYWORD  "+keyWord+"  "+list+"  "+map1);
			    	
			     }else{
			    	 
			    	 list.add(keyWord);
			     }
			 	 
			 }
			 map1.put(keyVal, list);
			// Util.sb.append("\n   NEW KEYWORD  **************** "+map1);
			
			return map1;
			 			

		}
		//public static Map<String, Map<String, String>> readTestCaseValues2(HSSFSheet sheet) {
		
		public static Map<String, TestCaseValues>   readTestCaseValues2(HSSFSheet sheet) {
			List<TestCaseValues> tclist = new ArrayList<>();
			
			Map<String, TestCaseValues> testcaseVal = new HashMap<String, TestCaseValues>();
			
			TestCaseValues tcv = new TestCaseValues();
			Map<String, Map<String, String>> values = new HashMap<>();
			Map<String, String> map = new HashMap<>();
			Iterator<Row> iterator = sheet.iterator();
			 iterator.next();
			 List<String> list = new ArrayList<String>();
			 int i = 1;
			 String keyVal = "";
			 String prevKey = ""; 
			 int j = 1;
			 
			 int count = 1;
			 boolean continueflag = false;
			 boolean insertFlag = false;
			 
			 String kw = "";
			 while(iterator.hasNext()){
				 	 Row nextRow = iterator.next();
				     i = 1;
				     String key = getStringValue(nextRow.getCell(1));
				     String keyWord = getStringValue( nextRow.getCell(0));
				     String keyWord1 = "";
				     boolean start = false;
				     
				     if(!keyWord.equals("")){
				    	 keyVal = keyWord;
				    	 start = true;
				    	 list = new ArrayList<String>();
				    	 continueflag = false;
				     }else{
				    	 insertFlag = true;
				    	 prevKey = keyVal;
				     }
				     if(start){
				    	 keyVal = keyWord;
				     }
				     if(keyWord != null && keyWord.length() > 0 && continueflag){
				     keyWord1 = keyWord;
				     }
					 while(nextRow.getCell(i) != null){
						 if( start){
							 list.add(nextRow.getCell(i).getStringCellValue());
							 i++;
							 continue;
						 }else{
							
							 if( i <= list.size()){
							 		continueflag = true;
								   if(nextRow.getCell(i).getCellType() ==0){
										 map.put(list.get(i-1), ""+nextRow.getCell(i).getNumericCellValue());
										 
										 //Log1.debug(" list.get(i-1)  "+list.get(i-1)+"  "+nextRow.getCell(i).getNumericCellValue());
									 }else{
										 map.put(list.get(i-1), nextRow.getCell(i).getStringCellValue());
										// Log1.debug(" list.get(i-1)  "+list.get(i-1)+"  "+nextRow.getCell(i).getStringCellValue());
											
									 }
							 
							 }
							 i++;
						 }
						 
					 }
					 
					 if(key != null && key.length() > 0){
						 
						 if(keyWord != null && keyWord.length() > 0){
							 
							 kw = keyWord;
						 }
						 
						 if(key.equalsIgnoreCase("REFERENCE") || key.equalsIgnoreCase("EXECUTION")){
							 values.put(key, map);
						 }else{
						 values.put(kw+"_"+key, map);
						 }
						// Log1.debug(" key  ***************************** "+key+" keyWord...  "+keyWord+" keyWord1 "+keyWord1); 
			
						 if(keyWord != null  && keyWord.length() > 0 && insertFlag){
							 if(values.keySet().size() > 1){
								 System.out.println(prevKey+"  VALUES :::::::testcaseVal  values::::::: "+values.get("EXECUTION"));
								 List<String> cou = 	getCount(values.get("EXECUTION"));	 
								 Validator.itr.put(prevKey, cou);
								 tcv.setValues(values);
								 tcv.setKeyWord(prevKey);
								 tclist.add(tcv);
								 testcaseVal.put(prevKey, tcv);
								 tcv = new TestCaseValues();
								 values =   new HashMap<>();
							 }
						 }
						 map = new HashMap<>();
					 }
					   
				 }
			
			 System.out.println("\n "+"  VALUES :::::::testcaseVal   "+Validator.itr);
			 Log1.debug(" testcaseVal KEYSET "+testcaseVal.keySet());
			 for(String ST: testcaseVal.get("RELATION_PARAMETER_VIEW_SEARCH").getValues().keySet()){
			 Log1.debug(" testcaseVal KEYSET "+testcaseVal.get("RELATION_PARAMETER_VIEW_SEARCH").getValues().get(ST));
			 }
			 
			return testcaseVal;
			
		}
		private static List<String> getCount(Map<String, String> map) {
			 Set<String> set = map.keySet();
			 List<String> list = new ArrayList<>();
			 System.out.println("\nset:::  "+set);
			 int count =0;
			 for(String s: set){
				 if(s.equalsIgnoreCase("REFERENCE")){
					 continue;
				 }
				 if(s.equalsIgnoreCase("NA")){
					 continue;
				 }
				 
				 if(map.get(s).equalsIgnoreCase("Y")){
					 count++;
					 list.add(s);
				 }
			 }
			//return count;
			 return list;
		}
		public static Map<String, Map<String, String>> getConfigValues(HSSFSheet sheet) {
			Map<String, Map<String, String>> configMap = new HashMap<>();
			Map<String, String> map = new HashMap<>();
			Iterator<Row> iterator = sheet.iterator();
			Row nextRow1 = iterator.next();		 
			boolean b = false;
			boolean start = true;
			//HashMap<String, String> map = new HashMap<>();
			String keyword = "";
			String prevKeyword = "";
			KeyWordDef tcd = new KeyWordDef();
			int i =0;
			List<Event> list = new ArrayList<Event>();
			while (iterator.hasNext()) {
					Row nextRow = iterator.next();	
					i = 0;	
					  String keyWord = getStringValue( nextRow.getCell(0));
					 
					  if(prevKeyword.equals("")){
						  prevKeyword = keyWord;
					  }
					  if(!keyWord.equals("")){
						  if(!start){
							 
							  configMap.put(prevKeyword, map);
							  map = new HashMap<>();
							  prevKeyword = keyWord;
						  }
						  
						  if(start){
							  start = false;
						  }
//						  if(prevKeyword.equals("")){
//							  prevKeyword = keyWord;
//						  }else{
//							  b = true;
//						  }
					  }else{
						  //b = true;
					  }
					   
					  
					  map.put(getStringValue( nextRow.getCell(1)), getStringValue( nextRow.getCell(2)));
					
			}
			 configMap.put(prevKeyword, map);
			 Util.sb.append("\n "+"  VALUES :::::::configMap   "+configMap);
			return configMap;
		}
		public static void filterSkipElements(Map<String, KeyWordDef> keyWordDefMap,
				Map<String, Map<String, String>> configMap) {
			 
			
			for(String key: keyWordDefMap.keySet()){
				
				if(configMap.containsKey(key)){
				Set<String> set = configMap.get(key).keySet();
				 Util.sb.append("\n "+"  VALUES :: "+key);
				 Util.sb.append("\n "+"  VALUES :: "+keyWordDefMap.get(key).getEventList());
				 
				 List<Event> list = keyWordDefMap.get(key).getEventList();
				 if(list != null && list.size() > 0){
				 for(Event e:  list){
					 
					 if(set.contains(e.getElement())){
						// list.remove(e);
						 e.setAction("SKIP");
					 }
				 }
				 }
				}
				 //list.removeIf(x-> set.contains(o))
			//	keyWordDefMap.get(key).getValues().keySet().removeAll(configMap.get(key).keySet());
				
			}
			
		}
		public static Map<String, Map<String, String>> readNavigationIds(HSSFSheet sheet) {
			Map<String, Map<String, String>> configMap = new HashMap<>();
			Map<String, String> map = new HashMap<>();
			Iterator<Row> iterator = sheet.iterator();
			
			ArrayList<String> list1 = getList(iterator.next());
			
			//Row nextRow1 = iterator.next();		 
			boolean b = false;
			boolean start = true;
			//HashMap<String, String> map = new HashMap<>();
			String keyword = "";
			String prevKeyword = "";
			KeyWordDef tcd = new KeyWordDef();
			int i =0;
			List<Event> list = new ArrayList<Event>();
			while (iterator.hasNext()) {
					Row nextRow = iterator.next();	
					i = 0;	
					  String keyWord = getStringValue( nextRow.getCell(0));
					 
					  if(prevKeyword.equals("")){
						  prevKeyword = keyWord;
					  }
					  if(!keyWord.equals("")){
						  if(!start){
							 
							  configMap.put(prevKeyword, map);
							  map = new HashMap<>();
							  prevKeyword = keyWord;
						  }
						  
						  if(start){
							  start = false;
						  }
//						  if(prevKeyword.equals("")){
//							  prevKeyword = keyWord;
//						  }else{
//							  b = true;
//						  }
					  }else{
						  //b = true;
					  }
					  int j = 0;
					  while (nextRow.getCell(j) != null) {
						  if(j < list1.size()){
						  System.out.println(" j "+j);
						  System.out.println(list1+"  "+list1.get(j)+"  "+j);
						  System.out.println("  "+getStringValue( nextRow.getCell(j+1)));
						  map.put( list1.get(j-0), getStringValue( nextRow.getCell(j+1)));
					  j++;
					  }else{
						  break;
					  }
					  }
					
			}
			 configMap.put(prevKeyword, map);
			 Util.sb.append("\n "+"  VALUES :::::::configMap   "+configMap);
			return configMap;
		}
		private static ArrayList<String> getList(Row nextRow) {
			int i =1;
			ArrayList<String> list = new ArrayList<>();
			while (nextRow.getCell(i) != null) {
				 	
				  String keyWord = getStringValue( nextRow.getCell(i));
				  Util.sb.append("\n "+"  VALUES :::::::configMap   "+keyWord);
				  list.add(keyWord);
				  i++;
			}
			Util.sb.append("\n "+"  VALUES :::::::list   "+list.size());
			return list;
		}
		public static void updateNavigationValues(List<Event> list2, Map<String, Map<String, String>> navMap) {
			Util.sb.append("\n "+"  VALUES :::::::list  EEEEEEEEEEEEEEEEEEEE  START navMap"+navMap);
			
			
			int t =0;
			
			 for(Event e: list2){
				Util.sb.append("\n "+"  VALUES :::::::list  EEEEEEEEEEEEEEEEEEEE  START  TTTT  "+t+++" "+e.getUserParam1()+"  "+e);
				 if(e.getUserParam1() !=null){
					// Util.sb.append("\n "+"  VALUES :::::::list  EEEEEEEEEEEEEEEEEEEE  UPDATING "+e.getUserParam1()+"  "+navMap.get(e.getUserParam1()));
					 
					
					 if(e.getValue() != null && e.getValue().length() > 0){
							if(navMap.get(e.getUserParam1()) !=null){				 
								Util.sb.append("\n "+"  VALUES  navMap :: "+e.getValue()+"   "+navMap.get(e.getUserParam1()).get(e.getValue()));
								e.setElement(navMap.get(e.getUserParam1()).get(e.getValue()));
						 }
					 }
				 }
				// Util.sb.append("\n "+"  VALUES :::::::list  EEEEEEEEEEEEEEEEEEEE  START 123 :: "+e);
				 
			 }
			
		}
		public static boolean isDynamic(TestCaseDefinition tcd, Map<String, KeyWordDef> keyWordDefMap) {
		     
			// Util.sb.append("\n "+"  VALUES :::::::list  EEEEEEEEEEEEEEEEEEEEFFFFFFF  isDynamic LIST:: "+tcd.getKeyWord()+"  "+keyWordDefMap.get(tcd.getKeyWord()).getEventList().size());
				
			boolean dynamic = false;
			
			if(keyWordDefMap.get(tcd.getKeyWord()) != null){
			if(keyWordDefMap.get(tcd.getKeyWord()).getEventList().size() == 1){

				Event e = keyWordDefMap.get(tcd.getKeyWord()).getEventList().get(0);
				if(e.getElement() == null || e.getElement().length() ==0 ){
					dynamic = true;
				}
				
			}
			}
		//	 Util.sb.append("\n "+"  VALUES :::::::list  EEEEEEEEEEEEEEEEEEEEFFFFFFF  START AFTER "+tcd.getKeyWord()+"   "+keyWordDefMap.get(tcd.getKeyWord()).getEventList());
			return dynamic;
		}
		
		
		public static Map<String,  String> getMap(String fetchData, String userParam){
			HashMap<String, String> hmap = new HashMap<String, String>();
			Map<String, TestCaseValues> tcList = ProjectBean.getProjrctBeanNEW().getInputValues();
			 Util.sb.append("\n  ::::EVENT:::: "+fetchData+"  "+userParam);
			 System.out.println("\n  ::::EVENT:::: "+fetchData+"  "+userParam);
			Set<String> set = tcList.get(fetchData).getValues().keySet();
			 for(String se: set){
				 System.out.println("\n  ::::EVENT::::123  "+tcList.get(fetchData).getValues().get(se));
				// if(tcList.get(se) != null)
				 //if(tcList.get(se).getValues() != null)
			// Util.sb.append("\n  MAP::::VERIFY  ::::: 123 "+se+"  "+tcList.get(tcd.getFetchData()).getValues().get(se).get(tcd.getInputTestDataParam()));
			 hmap.put(se, tcList.get(fetchData).getValues().get(se).get(userParam));
			 
			// hmap.put(se, tcList.get(tcd.getFetchData()).getValues().get(se).get(tcd.getInputTestDataParam()));
				 
			 }
			 
			 return hmap;
		}
		
		
		
		public static boolean fillData(HSSFSheet sheet, ArrayList<ReportBean> arrayList ){
			
			int j = 1;
			
		
			if(arrayList.size() ==0 ){
				HSSFRow row = sheet.createRow((short) j+9);
				row.createCell(5).setCellValue(" NO RECORDS ");
				return true;
			}
			
			
			HSSFRow row = sheet.createRow((short) j);
			row.createCell(0).setCellValue(arrayList.get(0).getProject());
			j++;
			row = sheet.createRow((short) j);
				
			row.createCell(1).setCellValue(arrayList.get(0).getModule());
			j++;
			row = sheet.createRow((short) j);
			row.createCell(1).setCellValue(arrayList.get(0).getTestcaseID());
			j++;
			
			
			 System.out.println("\n  ::  arrayList "+arrayList.size());
			for(ReportBean rbean: arrayList){
				ArrayList<VerifyBean> list1 = rbean.getList();
				for(VerifyBean issue: list1){
				  row = sheet.createRow((short) j);
				  
				  row.createCell(0).setCellValue(issue.getVerify());
				  row.createCell(1).setCellValue(issue.getResult());
				  row.createCell(2).setCellValue(issue.getActualValue());
				  row.createCell(3).setCellValue(issue.getExpectedValue());
				 j++;
				}
			}
			
			 
			
			 
			 
			
			 			
			return true;
		}
		
//		 public static String getDate(){	
//			 SimpleDateFormat sdf = new SimpleDateFormat("d_MMM_yyyy_HH_mm_ss");
//			 return sdf.format(new Date());
//				
//		 }
		 
		 public static String getDate(String formate){
				 SimpleDateFormat sdf = new SimpleDateFormat(formate);
				 return sdf.format(new Date());	
			 }
}
