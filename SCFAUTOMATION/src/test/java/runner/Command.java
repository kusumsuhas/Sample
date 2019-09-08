package runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.intellect.auto.MessageBoxThread;
import com.intellect.auto.bean.Event;
import com.intellect.auto.bean.ReportBean;
import com.intellect.auto.bean.TestCaseBean;
import com.intellect.auto.bean.VerifyBean;
import com.intellect.auto.record.VideoReord;
import com.intellect.auto.util.Constants;
import com.intellect.auto.util.ExcelToHtml;
import com.intellect.auto.util.SeleniumUtil;
import com.intellect.auto.util.Util;
import com.intellect.auto.validation.Validator;

import stepdef.SCFLoginSD;
import org.apache.log4j.Logger;
public class Command {
	List<Event> eventList = new ArrayList<Event>();
	Logger log = Logger.getLogger("app"); 
	public static HSSFWorkbook  workbook = new HSSFWorkbook();
	WebDriver driver = null;
	TestCaseBean testCase = null;
	public Command(List<Event> eventList, WebDriver driver) {
		super();
		this.eventList = eventList;
		this.driver = driver;
	}
	public Command(TestCaseBean testCase, List<Event> eventList, WebDriver driver) {
		super();
		this.eventList = eventList;
		this.driver = driver;
		this.testCase = testCase;
		
	}
	
	StringBuffer sb = new StringBuffer();
	public String execute(){
		int i = 0;
		boolean flag = false;
		Map<String, Map<String, String>> initialMap = new HashMap<>();
		boolean startCapture = false;
		boolean cflag = false;
		Map<String, String> map = new HashMap<>();
		String subTask1 = "";
		boolean subTask = true;
		String key = "";
		String mapKey = "";
		WebElement element = null;
		boolean startFetch = false;
		HashMap hMap = new HashMap<String, String>();
		ArrayList<ReportBean> list = new ArrayList<>();
		String testCaseResult = "PASS";
		boolean scrennCapture = false;
	//	VideoReord videoReord = new VideoReord();
//        try {
//			videoReord.startRecording(testCase.getTestCaseID());
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}		
        
		 long L1 = System.currentTimeMillis(); 
		 log.debug("EVENT::"+eventList.size());
		 
		 boolean b = true;
		 int count = 1;
		 for(Event e: eventList){
			 log.debug("EVENT :::: "+e);
			 
			 if(b) continue;
			 try{
			 count++;
			 MessageBoxThread.count = count;
			 if(count == 210){
				 e.setValue(Util.dynamicValue);
			 }
			 if(count == 150){
				 //Thread t = Thread.currentThread();
				 //System.out.println(" SUSPENDING THREAD "+t.getName()); 
				 MessageBoxThread.suspend = true;
				// t.suspend();
//				 try {
//					//t.sleep(20000L);
//					 System.out.println("EVENT::: thread suspending ");
//					
//					 System.out.println("EVENT::: thread suspendED ");
//					//Thread t1 = new Thread(new MessageBoxThread());
//				
//					
//					//t1.start();
//					 System.out.println("EVENT::: thread started ");
//					//t.join();
//					 System.out.println("EVENT::: thread completed ");
//					//t.resume();
//					
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				 
			 }
			 log.debug(count+" EVENT:::::::::::"+e);
			 System.out.println(count+" EVENT:::::::::::"+e);
			 if(e.getAction() != null){ 
				 if(e.getAction().equals(Constants.SKIP)){
					 continue;
				 } 
			
			 if(e.getAction().equals(Constants.STARTFETCH)){
				 hMap = new HashMap<String, String>();
				 startFetch = true;
				 scrennCapture = true;
				 continue;
			 }
			 
			 if(e.getAction().equals(Constants.STOPFETCH)){
				 startFetch = false;
				 Validator.validaterMap.put(e.getFetchData(), hMap);
				 hMap = new HashMap<String, String>();
				 continue;
			 }
			 if(e.getAction().equals(Constants.FETCHFROMDATASHEET)){
				 Validator.validaterMap.put(e.getFetchData()+"_"+e.getUserParam1(),  Util.getMap(e.getFetchData(), e.getInputTestData()));
				 hMap = new HashMap<String, String>();
				 continue;
			 }
			 }
			switch(e.getEvent()){
			case Constants.VERIFY :
				Set<String> set = Validator.validaterMap.keySet();
				Map<String, String> hMap1 = new HashMap<>();
				Map<String, String> hMap2 = new HashMap<>();
				String ss = "";
				for(String s: set){
					ss = s;
					if(s.contains(e.getUserParam1())){
						hMap1 = Validator.validaterMap.get(s);
					}
					if(s.contains(e.getUserParam2())){
						hMap2 = Validator.validaterMap.get(s);
					}
				}
				
				sb.append("\n*************************************************************** \n");
				sb.append(" VERIFYING "+ss.replace("_DATASET2", "")+"  ");
				ReportBean bean1 = Comapre(hMap1, hMap2);
				if(!bean1.isResult()){
					testCaseResult = Constants.FAIL;
				}
				bean1.setProject("SCF");
				bean1.setModule("MAINTANANCE");
				bean1.setTestCaseDesc(testCase.getTestCaseDesc());
				bean1.setTestcaseID(testCase.getTestCaseID());
				bean1.setUserParam(e.getUserParam3());
					if(bean1.isResult()){
						SCFLoginSD.reports.createTest(ss+" is  failed and completeed").pass("TEST PASSED");
					}else{
						SCFLoginSD.reports.createTest(ss+" is  failed and completeed").fail("TEST FAILED");
					}
			    	SCFLoginSD.reports.flush();
			    	list.add(bean1);
			    	break;
			case Constants.FETCH :   element = findElement(e.getElementType().trim(), e.getElement().trim()); 
			if(element == null) break;
			//if(e.getElement().equals("P3003-10244")){
			if(e.getEvent().equalsIgnoreCase("DROPDOWN")){
				Select select = new Select(element);
				WebElement option = select.getFirstSelectedOption();
				String defaultItem = option.getText();
				System.out.println("YYYYYYYYYYYYY   "+defaultItem +"   "+e.getEvent());
				hMap.put(e.getText(), defaultItem);
				
				
			}else{
			 System.out.println(" :::::::::::: element "+element.getText()+"   "+element.getAttribute("value"));
			 hMap.put(e.getText(), element.getAttribute("value"));
			}
			
			if(scrennCapture){
				try {
					SeleniumUtil.takeSnapShot(driver, Constants.SCREENSHOTS_FOLDER+testCase.getTestCaseID()+"_"+Util.getDate(Constants.d_MMM_yyyy_HH_mm_ss)+"_.png");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				scrennCapture = false;
			}
			 break; 
			case Constants.URL 			:  driver.get(e.getValue());
			 System.out.println(" :::::::::::: URL EXECUTED ");
			
								   			break;
			case  Constants.SENDKEYS 	:  element = findElement(e.getElementType().trim(), e.getElement().trim()); 
											if(element == null) break;
												element.clear(); element.sendKeys(e.getValue());
												break;
			
			case  Constants.CLICK 	:  element = findElement(e.getElementType(), e.getElement());
								element.click();
								break;
			case Constants.CLICK_LINKTEXT         :  element = findElement(e.getElementType(), e.getElement());
			if(element == null) break;
			element.click(); break;
			  
			case Constants.SWITCHWINDOW  :  
				
				boolean flag1 = Util.switchWindowByTitle(driver, e.getValue());  
//				if(!flag){
//					try {
//						Thread.sleep(20000);
//					} catch (InterruptedException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					flag = Util.switchWindowByTitle(driver, e.getValue());  
//				}
				break;
			case Constants.SWITCHFRAME    :  
				 
				Util.switchFrameByName(driver, e.getElement()); 
				  break;	
				  
			case "SWITCHFRAME10"    : 
			case "SWITCHFRAME101"    : 
			case "SWITCHFRAME102"    : 
			case "SWITCHFRAME103"    : 
				 
				//System.out.println(" SOURCE "+driver.getPageSource());
				Util.switchIFrameByName(driver, e.getElement()); 
				  break;	
				  
			case Constants.DROPDOWN:  
				element = findElement(e.getType(), e.getElement()); 
				
				if(element == null) break;
				
				if(element != null){
					System.out.println("  FOUND THE ELEMENT ");
				}else{
					System.out.println("  DID NOT FIND THE ELEMENT ");
				}
				Select dropdown= new Select(element);
				
				 
				dropdown.selectByVisibleText(e.getValue());
				
				
				Actions builder = new Actions(driver);
				//builder.doubleClick().build().perform();
				builder.moveToElement(element, 100, 125).click().build().perform();

				
				break;
				
			case Constants.DEFAULT_CONTENET : driver.switchTo().defaultContent(); break;
				
				
			}
			
			 if(e.getWaitTime() > 0){
	            	
				 System.out.println("WAITING  ::: "+e.getWaitTime()+" for event  "+e.getEvent());
	            	try {
	            		
						Thread.sleep(e.getWaitTime());
					} catch (InterruptedException e1) {
						 
						e1.printStackTrace();
					}
	            }
			
			
		}catch(Exception e1){
			 System.out.println(" ERRRRRRRRRRRR  "+e1.getMessage());
		}
		 }
		
//		try {
//			videoReord.stopRecording();
//		} catch (Exception e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		
		MessageBoxThread.completed = true;
		
		 //Util.writefile(Util.sb.toString());  // D:\GTB_HOME\SCFAUTOMATION\Reports
		
		
		HashMap<String, String> hmap = new HashMap<>();
		
		long longTime = System.currentTimeMillis() -L1;
		if(list.size()> 0){
			hmap.put("VALUE1", list.get(0).getTestcaseID());
			hmap.put("VALUE2", list.get(0).getTestCaseDesc());
			hmap.put("VALUE3", testCaseResult);
			hmap.put("VALUE4", list.get(0).getUserParam());
			hmap.put("VALUE5", Test.getTime(longTime));
			hmap.put("VALUE6", Constants.SCREENRECORDING_FOLDER);
		//	hmap.put("VALUE7", list.get(0).getUserParam());
		}
		
		testCase.setTcdexecTime(Test.getTime(longTime));
		//hmap.put("VALUE4", "Verify Relationship Parameter View");
		
		String filename = Constants.TESTRESULTS_FOLDER;
		Util.writefile(sb.toString());
		
		HSSFSheet sheet = workbook.createSheet("REPORT_"+testCase.getTestCaseID());
		Test.readExcel();
		//Test.createStyleMap();
		HSSFRow rowhead = sheet.createRow((short) 1); 
		HSSFRow rowhead01 = sheet.createRow((short) 2); 
		Cell cell01 = rowhead01.createCell(0);
		cell01.setCellValue(Constants.EXECUTION_TIME);
		
		Cell cell02 = rowhead01.createCell(1);
		cell02.setCellValue(Test.getTime(System.currentTimeMillis() - L1));
		
		HSSFRow rowhead02 = sheet.createRow((short) 3); 
		
		Cell cell03 = rowhead02.createCell(0);
		cell03.setCellValue(Constants.SCREEN_REC_LINK);
		 
		HSSFRow rowhead04 = sheet.createRow((short) 4); 
		Cell cell04 = rowhead04.createCell(0);
		cell04.setCellValue(Constants.SCREEN_SNAP_SHOT_LINK);
		
		Font underlineFont = workbook.createFont();
        underlineFont.setUnderline(HSSFFont.U_DOUBLE);
        CellStyle style = workbook.createCellStyle();
        
        
        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
	     //style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
	       FillPatternType fp = FillPatternType.SOLID_FOREGROUND;
	       style.setFillPattern(fp);
        
        style.setFont(underlineFont);
        cell03.setCellStyle(style);
        cell04.setCellStyle(style);
        
        final Hyperlink href =
                workbook.getCreationHelper().createHyperlink(HyperlinkType.URL);
                href.setAddress(Constants.SCREENRECORDING_FOLDER);
                cell03.setHyperlink((org.apache.poi.ss.usermodel.Hyperlink) href);
                
                final Hyperlink hrefScreenShot =
                        workbook.getCreationHelper().createHyperlink(HyperlinkType.URL);
                		hrefScreenShot.setAddress(Constants.SCREENSHOTS_FOLDER_HREF);
                        
                cell04.setHyperlink((org.apache.poi.ss.usermodel.Hyperlink) hrefScreenShot);
                
		 
		HSSFRow rowhead2 = sheet.createRow((short) 6); 
		HSSFRow rowhead3 = sheet.createRow((short) 7); 
		
		if(list.size() > 0){
		Test.fillHeading(workbook, rowhead);
		Test.fillHeadingData(workbook, rowhead, list.get(0), testCaseResult);
		Test.fillData(workbook, sheet, list);
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.autoSizeColumn(4);
		//Util.fillData(createSheet(workbook, "DATA"), list);
	
		filename = filename+testCase.getTestCaseID()+"_"+Util.getDate(Constants.d_MMM_yyyy_HH_mm_ss)+".xls";	
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(filename);
			try {
				workbook.write(fileOut);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fileOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Your excel file has been generated!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		//String path = "D:\\GTB_HOME\\SCFAUTOMATION\\TestResults\\HTMLGEN\\TEMPLATE.txt";
		String path = Constants.TEMPLATE;
		String html = Test.readFile(path);
		System.out.println("html :::::"+html);
		
		  
		if(hmap.get("VALUE3").equals(Constants.FAIL)){
		 //path = "D:\\GTB_HOME\\SCFAUTOMATION\\TestResults\\HTMLGEN\\HEADING1.txt";
			path = Constants.HEADING1;
		 
		}else{
			//path = "D:\\GTB_HOME\\SCFAUTOMATION\\TestResults\\HTMLGEN\\HEADINGP1.txt";
			path = Constants.HEADING2;
		}
		
		String html1 = Test.readFile(path);
		
		
		System.out.println("html1 :::::"+html1);
		
		path = Constants.EXETIME;
		String exeTime = Test.readFile(path);	
		
		path = Constants.EVIDENCELINK;
		String evidence = Test.readFile(path);	
				
 		
		//path = "D:\\GTB_HOME\\SCFAUTOMATION\\TestResults\\HTMLGEN\\TR1.txt";
		path = Constants.TR1;
		
		 
		String tr = Test.readFile(path);
		System.out.println("tr :::::"+tr);
		
		String data = genearteHtml(hmap, list);
 
		System.out.println("data :::::"+data);
		data = html1+"\n"+exeTime+"\n"+evidence+"\n"+tr+"\n"+data;
		
		
		System.out.println("data :::::"+data);
		html = html.replaceAll("DATA", data);
		
		
		for(String DD: hmap.keySet()){			
			html = html.replace(DD, hmap.get(DD));
		}
		
		System.out.println("html FINAL  :::::"+html);
		
		String fileName = testCase.getTestCaseID()+"_Report_"+Util.getDate(Constants.d_MMM_yyyy_HH_mm_ss);
		testCase.setDetailLink(fileName+".html");
		Util.writefile(html, fileName);
		}
 		return testCaseResult;
	}
	 
	private String genearteHtml(HashMap<String, String> hmap, ArrayList<ReportBean> list) {
		
		String path = "";
		String data ="";
		for(ReportBean rb: list){
		 	path = Constants.HEADING2;
			String str1 = Test.readFile(path);
			str1.replace("VALUE4", hmap.get("VALUE4"));
			path = Constants.HEADING3;
			String str2 = Test.readFile(path);
			path = Constants.TR_DATA;
			String strRows = "";
			String result = Constants.PASS;
			 
		 		
			for(VerifyBean vf : rb.getList()){
				if(vf.getResult().equals(Constants.FAIL)){
					result = Constants.FAIL;
				}

			String str = Test.readFile(path);
			 str = str.replace("VERIFY_FIELD", vf.getVerify());
			 str = str.replace("VERIFY_RESULT", vf.getResult());
			 str = str.replace("VERIFY_DATA1", vf.getExpectedValue());
			 str = str.replace("VERIFY_DATA2", vf.getActualValue());  
			 strRows = strRows +"\n"+str;
			}
			path = Constants.TR1;
			String tr = Test.readFile(path);
			str1 = str1.replace("VALUE5", result);
			data = data + str1+"\n"+str2+"\n"+strRows+"\n"+tr;
			
		}
		return data;
	}
	private ReportBean Comapre(Map<String, String> hMap1, Map<String, String> hMap2) {
		ReportBean bean = new ReportBean();
		ArrayList<VerifyBean> list  = new ArrayList<>();
		Set <String> set = hMap1.keySet();
		System.out.println(" VERIFY  map1 "+hMap1);
		System.out.println(" VERIFY  hmap2 "+hMap2);
		System.out.println("\n************************************************* \n");
		boolean testFlag =  true;
		for(String s: set){
			VerifyBean verify = new VerifyBean();	
			if(hMap2.containsKey(s)){
				String s1 = hMap1.get(s);
				String s2 = hMap2.get(s);
				if(s1!= null){
					if(s2 != null){
						if(s1.equals(s2)){
							System.out.println(" * 	VERIFY "+s+"  PASS  "+" ACTUAL VALUE :: "+s1+" EXPECTED:: "+s2);
							String space = getSpace(s, 30);
							String space1 = getSpace(s1, 30);
							sb.append("\n* 	VERIFY "+s+space+"  PASS  "+" ACTUAL VALUE :: "+s1+space1+" EXPECTED:: "+s2);
							verify.setVerify(s);
							verify.setResult(Constants.PASS);
						}else{
							testFlag = false;
							String space = getSpace(s, 30);
							String space1 = getSpace(s1, 30);
							System.out.println(" \n * 	VERIFY "+s+"  FAIL  "+" ACTUAL VALUE :: "+s1+" EXPECTED:: "+s2);
							sb.append("\n* 	VERIFY "+s+space+"  FAIL  "+" ACTUAL VALUE :: "+s1+space1+" EXPECTED:: "+s2);
							verify.setVerify(s);
							verify.setResult(Constants.FAIL);
						}
						verify.setActualValue(s2);
						verify.setExpectedValue(s1);
					}
					else{
						System.out.println("*  "+s+"  in hMap2 is null");
						Util.sb.append("\n *  "+s+"  in hMap2 is null");
					}
				}else{
					System.out.println("* "+s+"  in hMap1 is null");
					Util.sb.append("\n * "+s+"  in hMap1 is null");
				}
				
			}
			list.add(verify);
		}
		String sss= "";
		for(String s: hMap1.keySet()){
			sss= s;
		break;	
		}
		bean.setResult(testFlag);
		bean.setList(list);
    	return bean;
	}
	private String getSpace(String s, int length) {
		 String space = "";
		for(int i =0; i<( length -s.length()); i++){
			space+=" ";
		}
		return space;
	}
	
	private List<String> check(Map<String, Map<String, String>> initialMap, String string, String string2) {
		Map<String, String> map1 =  initialMap.get(string+"_DEFAULT");
		Map<String, String> map2 =  initialMap.get(string+"_FINAL");
		List<String> list = new ArrayList();
		 for(String key: map1.keySet() ){
			 if(!map1.get(key).equals(map2.get(key))){
				 list.add(key+" is Not cleared in clear button operation");
			 } 
		 }
		return list;
	} 
	private WebElement findElement(String type, String element) {
		WebElement webElement = null;
		try{
		System.out.println("FINDING EVENT::: "+type+"  "+element);
		element = element.trim();
		switch(type){
		case Constants.ID : webElement = SeleniumUtil.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));  break;	
		case Constants.XPATH : webElement = SeleniumUtil.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element))); break; 	
		case Constants.LINKTEXT : webElement = SeleniumUtil.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.linkText(element))); break; 	
		}
		System.out.println("FOUND EVENT::: "+type+"  "+element);
		}catch(Exception e){
			System.out.println("EXCEPTION ::: "+e.getMessage());
		}
		return webElement;
	}
	private static HSSFSheet createSheet(HSSFWorkbook workbook, String sheetName){
		HSSFSheet sheet = workbook.createSheet(sheetName);
		HSSFRow rowhead = sheet.createRow((short) 0);
		return sheet;	
	}
}
