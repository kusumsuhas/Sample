package runner;

 
import java.awt.Color;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;

import com.intellect.auto.bean.ProjectBean;
import com.intellect.auto.bean.ReportBean;
import com.intellect.auto.bean.SummaryBean;
import com.intellect.auto.bean.TestCaseBean;
import com.intellect.auto.bean.VerifyBean;
import com.intellect.auto.util.Constants;
import com.intellect.auto.util.ExcelToHtml;
import com.intellect.auto.util.Util;

public class Test {
	
	
	static Map<String, CellStyle> map = new HashMap<>();
	
	static Map<String, Short> colormap = new HashMap<>();
	
	private static Logger Log = Logger.getLogger("app");
	

	
	static Map<String, CellStyle> stylemap = new HashMap<>();
	
	public static boolean fillData(HSSFWorkbook workbook, int rowNum, ArrayList<String> list, String file){
		
		 
         HSSFSheet sheet = workbook.getSheet("Summary");
         Cell cell[] = new Cell[6];
         HSSFRow row = sheet.getRow(rowNum);
        		 if(row == null){
        			 sheet.createRow(rowNum);
        		 }
         
         Font underlineFont = workbook.createFont();
         underlineFont.setUnderline(HSSFFont.U_DOUBLE);
         
         CellStyle style = workbook.createCellStyle();
         style.setFont(underlineFont);
         
         for(int i=0;i< 6;i++){
        	 cell[i] = row.getCell(i);
        	 if(cell[i] == null){
        		 cell[i] = row.createCell(i);
        	 }
        	 cell[i].setCellValue(list.get(i));
         }
         cell[0].setCellStyle(style);
         final Hyperlink href =
                 workbook.getCreationHelper().createHyperlink(HyperlinkType.URL);
                 href.setAddress("file:///D:/GTB_HOME/SCFAUTOMATION/TestResults/DetailReports/"+file);
                 cell[0].setHyperlink((org.apache.poi.ss.usermodel.Hyperlink) href);
          
		return true;
	}
	
	public static String test(TreeSet<TestCaseBean> treeSet, String et) throws IOException {
		String fileName = "";
        try {

            FileInputStream file = new FileInputStream(new File("D:\\GTB_HOME\\SCFAUTOMATION\\src\\main\\resources\\report-summarytemplatelatest.xls"));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
           
                   
            
            HSSFSheet sheet = workbook.getSheet("Summary");
            
            HSSFRow row = sheet.getRow(2);
            
            Cell cell = row.createCell(1);
           // cell.setCellValue(Util.getDate("MM/dd/yy"));

            cell.setCellValue(Util.getDate("d MMM yyyy"));
            
            HSSFRow row1 = sheet.getRow(3);
            
            Cell cell8 = row1.createCell(1);
            cell8.setCellValue(et);

            int rowNum = 22;
            for(TestCaseBean bean: treeSet){
            	 ArrayList<String> aList = new ArrayList<>();
                 aList.add(bean.getTestCaseID());
                 aList.add(bean.getTestCaseDesc());
                 aList.add(bean.getModule());
                 aList.add(bean.getSubModule());
                 aList.add(bean.getResult());
                 
                 aList.add(bean.getTcdexecTime());
                 
                 fillData(workbook, rowNum, aList, bean.getDetailLink()); 
                 rowNum++;
            	
            }

//            ArrayList<String> aList = new ArrayList<>();
//            aList.add("TC_1");
//            aList.add("Some Description");
//            aList.add("MAINTENANCE");
//            aList.add("REL_PARAM_VIEW");
//            aList.add("PASS");
//            fillData(workbook, 22, aList, "TC_1_HTML_30_Oct_2018_19_21_01.html");   
//            
//            ArrayList<String> aList1 = new ArrayList<>();
//            aList1.add("TC_2");
//            aList1.add("Some Description 2");
//            aList1.add("MAINTENANCE");
//            aList1.add("REL_PARAM_ADD");
//            aList1.add("FAIL");
//            
//            fillData(workbook, 23, aList1,"TC_1_HTML_30_Oct_2018_19_21_01.html");       
            
            

            file.close();
            
              fileName = "D:\\GTB_HOME\\SCFAUTOMATION\\TestResults\\TestReport_"+Util.getDate(Constants.d_MMM_yyyy_HH_mm_ss)+"_.xls";

            FileOutputStream outFile =new FileOutputStream(new File(fileName));
            workbook.write(outFile);
            outFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return fileName;
    }

	
	public static String readFile(String path) {
		 
		BufferedReader br = null;
		FileReader fr = null;
		 StringBuffer sb = new StringBuffer();
		try {
			fr = new FileReader(path);
			  br = new BufferedReader(fr);
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine+"\n");
				 //System.out.println(sCurrentLine);;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
		 
		return sb.toString();

		
		 
	}


public static String  getTime(long milliSec){
	
	
	String time = "";
	 
	
	long sec = milliSec/1000;
	System.out.println(". sec "+sec);
	long min = (sec/60);
	System.out.println(". min "+min);
	long sectime = sec%60;
	System.out.println(". sectime "+sectime);
	long hours = min/60;

	System.out.println(". hours "+hours);
	min = min%60;
	
	System.out.println("...H."+hours+"  M "+min+"  "+sectime);
//	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");    
//	Date resultdate = new Date(milliSec);
//	System.out.println(sdf.format(resultdate));
//	return sdf.format(resultdate);
	if(hours > 0){
		if(hours < 9){
			time = "0"+hours+"hours ";
		}else{
			time = ""+hours+"hours ";
		}
	}
	if(min > 0){
		if(min < 9){
			time = time+"0"+min+" min ";
		}else{
			time = time+min+" min ";
		}
	}
	
	if(sectime > 0){
		if(sectime < 9){
			time = time+"0"+sectime+" sec";
		}else{
			time = time+sectime+" sec";
		}
	}
	
	return time;
}
	
	public static void main(String[] args) throws IOException {
		// Log.debug("AAAAAAAAAA");
		
		// Log.fatal("AAAAAAAAAA");
		 
		
		
		
		
		 
		
		// Util.getDate("d MMM yyyy");
				//System.currentTimeMillis();
		//getTime(10000000);

		//test();
		//String path = "D:\\GTB_HOME\\SCFAUTOMATION\\TestResults\\HTMLGEN\\TEMPLATE.txt";
		
		
		/*
		HashMap<String, String> hmap = new HashMap<>();
		hmap.put("VALUE1", "TC_1");
		hmap.put("VALUE2", "REL_PARAM_VIEW");
		hmap.put("VALUE3", "FAIL");
		
		hmap.put("VALUE4", "Verify Relationship Parameter View");
		hmap.put("VALUE4", "PASS");
		
		
		
		String path = "";
		if(hmap.get("VALUE3").equals("FAIL")){
		 path = "D:\\GTB_HOME\\SCFAUTOMATION\\TestResults\\HTMLGEN\\HEADING1.txt";
		}else{
			path = "D:\\GTB_HOME\\SCFAUTOMATION\\TestResults\\HTMLGEN\\HEADINGP1.txt";
		}
		
		String str = readFile(path);
		System.out.println(str);
		System.out.println(">>>>>>>>>");
		for(String ST: hmap.keySet()){
			//str = str.replace(ST, hmap.get(ST));
		}
		System.out.println(str);
		
		path = "D:\\GTB_HOME\\SCFAUTOMATION\\TestResults\\HTMLGEN\\TR1.txt";
		String tr = readFile(path);
		
		
		path = "D:\\GTB_HOME\\SCFAUTOMATION\\TestResults\\HTMLGEN\\HEADING2.txt";
		String heading2 = readFile(path);
		
		path = "D:\\GTB_HOME\\SCFAUTOMATION\\TestResults\\HTMLGEN\\HEADING3.txt";
		String heading3 = readFile(path);
		
		
		System.out.println(str+"\n"+tr+"\n"+heading2+"\n"+heading3);
		
		*/
		/*
	 
		
//		Field[] fields = HSSFColor.class.getDeclaredFields();
//		
//		
//		Object obj[] = HSSFColor.class.getEnumConstants();
//		
//		for(Object ff: obj){
//			System.out.println(ff);
//		}
		
		  HSSFWorkbook  workbook1 = new HSSFWorkbook();
//		  HSSFSheet sheet = workbook1.createSheet("REPORT");
//		  HSSFRow rowhead = sheet.createRow((short) 1); 
//		fillHeading1(workbook1, rowhead);
		
		
		
	
		//createStyleMap(workbook1);
		readExcel();
		
		
		ArrayList<ReportBean> list1  = new ArrayList<>();
		ReportBean bean = new ReportBean();
		
		ArrayList<VerifyBean> list  = new ArrayList<>();
		bean.setTitle("TITLE");
		bean.setTestcaseID("TCD_1");
		bean.setTestCaseDesc("REL_PARAM_VIEW");
		
		VerifyBean verify = new VerifyBean();
		verify.setVerify("relparam.view.eff_date");
		verify.setActualValue("A");
		verify.setExpectedValue("A");
		verify.setResult("PASS");
		
		list.add(verify);
		bean.setList(list);
		list1.add(bean);
		
		String name = "Report"+Math.random();
		
		 name = Util.getDate();
		String filename = "D:/GTB_HOME/SCFAUTOMATION/TestResults/"+name+".xls";
		
		 
		//filename = filename+"TC_1"+Util.getDate()+".xls";
		
		
		HSSFSheet sheet = workbook1.createSheet("REPORT");
		
		HSSFRow rowhead = sheet.createRow((short) 1); 
		HSSFRow rowhead2 = sheet.createRow((short) 3); 
		HSSFRow rowhead3 = sheet.createRow((short) 4); 
		
		//fillHeading1(workbook1, rowhead);
		fillSubHeading(workbook1, rowhead2);
		fillSubHeading1(workbook1, rowhead3);
		
	//	rowhead.getCell(0).setCellValue("TCD_1");
        //  fillData(createSheet(workbook1, "REPORT"), list1);
		
	//	fillData(workbook1, sheet, list1);
		
		
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(filename);
			try {
				workbook1.write(fileOut);
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
		
		File file = new File(filename);
		InputStream in = new FileInputStream(file);
		System.out.println(" HTML " +new ExcelToHtml(in).getHTML());
		
		//File file1 = n
		 
*/
	}

	
public static void fillSubHeading1(HSSFWorkbook workbook, HSSFRow rowhead3) {
	
	CellStyle style3 = workbook.createCellStyle();
	style3.cloneStyleFrom(map.get("CELL3"));
	
	Cell c4 = rowhead3.createCell(1);
	c4.setCellValue("Detail");
	c4.setCellStyle(style3);
	Cell c5 = rowhead3.createCell(2);
	c5.setCellValue("Result");
	c5.setCellStyle(style3);
	Cell c6 = rowhead3.createCell(3);
	c6.setCellValue("Expected");
	c6.setCellStyle(style3);
	Cell c7 = rowhead3.createCell(4);
	c7.setCellValue("Actual");
	c7.setCellStyle(style3);
		
	}


//	private static HSSFSheet createSheet(HSSFWorkbook workbook, String sheetName, short rowNum){
//		
//		
//		
//	}
	
	public  static boolean createStyleMap(HSSFWorkbook workbook){
		CellStyle style1 = workbook.createCellStyle();
		style1.cloneStyleFrom(map.get("CELL1"));
		CellStyle style2 = workbook.createCellStyle();
		style2.cloneStyleFrom(map.get("CELL2"));
		CellStyle style3 = workbook.createCellStyle();
		style3.cloneStyleFrom(map.get("CELL3"));

		CellStyle style4 = workbook.createCellStyle();
		style4.cloneStyleFrom(map.get("CELL4"));
		
		CellStyle style5 = workbook.createCellStyle();
		style5.cloneStyleFrom(map.get("CELL5"));

		stylemap.put("style1", style1);
		stylemap.put("style2", style2);
		stylemap.put("style3", style3);
		stylemap.put("style4", style4);
		stylemap.put("style5", style5);
		
		return true;
		
	}
	
	public static boolean fillHeading(HSSFWorkbook workbook,  HSSFRow rowhead){
		

		CellStyle style1 = workbook.createCellStyle();
		style1.cloneStyleFrom(map.get("CELL1"));
		
		CellStyle style2 = workbook.createCellStyle();
		style2.cloneStyleFrom(map.get("CELL2"));
		
		CellStyle style3 = workbook.createCellStyle();
		style3.cloneStyleFrom(map.get("CELL3"));

		CellStyle style4 = workbook.createCellStyle();
		style4.cloneStyleFrom(map.get("CELL4"));
		
		CellStyle style5 = workbook.createCellStyle();
		style5.cloneStyleFrom(map.get("CELL5"));

		 
		//HSSFColor co = new HSSFColor();
		
		//org.apache.poi.ss.usermodel.Color color =    
		//co.toHSSFColor(color);
		//style5.setFillBackgroundColor(HSSFColor(10,10,10));
		
		Cell C1 = rowhead.createCell(0);
		//C1.setCellValue("TCD_1");
		C1.setCellValue("");
		C1.setCellStyle(style4);
		
		Cell C2 = rowhead.createCell(1);
		C2.setCellValue("");
		C2.setCellStyle(style2);
		//style2.setShrinkToFit(true);
		
		
		
		Cell C31 = rowhead.createCell(2);
		C31.setCellValue("");
		C31.setCellStyle(style2);
		
		
		Cell C32 = rowhead.createCell(3);
		C32.setCellValue("");
		C32.setCellStyle(style2);
		
		Cell C33 = rowhead.createCell(4);
		C33.setCellValue("");
		C33.setCellStyle(style2);
		
		Cell C34 = rowhead.createCell(5);
		C34.setCellValue("");
		C34.setCellStyle(style2);
		
		Cell C35 = rowhead.createCell(6);
		C35.setCellValue("");
		//C35.setCellStyle(style2);
		 
		
	        
	        
		
		//C2.getCellStyle().setFillBackgroundColor(map.get("CELL2").getFillForegroundColor());
	//	C2.getCellStyle().cloneStyleFrom(map.get("CELL2"));
//		style1.setAlignment(HorizontalAlignment.CENTER);
//		Cell C3 = rowhead.createCell(6);
//		C3.setCellValue("ABC");
//		C3.setCellStyle(style1);
		
		
		
		 
		
//		Cell C4 = rowhead.createCell(7);
//		C4.setCellValue("");
//		C4.setCellStyle(style4);
//		
//		Cell C41 = rowhead.createCell(8);
//		C41.setCellValue("");
//		C41.setCellStyle(style5);
		
		
		
		return true;
	}

	
public static boolean fillHeading1(HSSFWorkbook workbook,  HSSFRow rowhead){
		

		CellStyle style1 = workbook.createCellStyle();
		//style1.cloneStyleFrom(map.get("CELL1"));
		
		System.out.println(colormap);;
		style1.setFillBackgroundColor(colormap.get("CELL1"));
		
		
		
		CellStyle style2 = workbook.createCellStyle();
		style2.cloneStyleFrom(map.get("CELL2"));
	//	style2.setFillBackgroundColor(colormap.get("CELL2"));
		
		CellStyle style3 = workbook.createCellStyle();
		//style3.cloneStyleFrom(map.get("CELL3"));

		CellStyle style4 = workbook.createCellStyle();
		//style4.cloneStyleFrom(map.get("CELL4"));
		
		CellStyle style5 = workbook.createCellStyle();
		//style5.cloneStyleFrom(map.get("CELL5"));

		/*
		Cell C1 = rowhead.createCell(0);
		//C1.setCellValue("TCD_1");
		C1.setCellValue("..");
		C1.setCellStyle(map.get("CELL5"));
		
		Cell C2 = rowhead.createCell(1);
		C2.setCellValue("..");
		C2.setCellStyle(style2);
		//style2.setShrinkToFit(true);
		
		
		
		Cell C31 = rowhead.createCell(2);
		C31.setCellValue("");
		C31.setCellStyle(style2);
		
		
		Cell C32 = rowhead.createCell(3);
		C32.setCellValue("");
		C32.setCellStyle(style2);
		
		Cell C33 = rowhead.createCell(4);
		C33.setCellValue("");
		C33.setCellStyle(style2);
		
		Cell C34 = rowhead.createCell(5);
		C34.setCellValue(".");
		C34.setCellStyle(style2);
		*/
		
		//C2.getCellStyle().setFillBackgroundColor(map.get("CELL2").getFillForegroundColor());
	//	C2.getCellStyle().cloneStyleFrom(map.get("CELL2"));
		Cell C3 = rowhead.createCell(6);
		C3.getCellStyle().setFillBackgroundColor(HSSFColor.AQUA.index);
		C3.getCellStyle().setFillForegroundColor(HSSFColor.BLUE.index);
		
		
		Cell C310 = rowhead.createCell(7);
		C310.getCellStyle().setFillBackgroundColor(HSSFColor.BRIGHT_GREEN.index);
		C310.getCellStyle().setFillForegroundColor(HSSFColor.DARK_BLUE.index);
		
		HSSFCell  C41 = rowhead.createCell(8);
		//HSSFCellStyle curStyle = C41.getCellStyle();
      C41.setCellStyle(style2);
		
//        curStyle.setFillPattern(HSSFCellStyle);
//        curStyle.setFillForegroundColor(bgColorIndex);
//
//        curCell.setCellStyle(curStyle);


		//C3.setCellStyle(style1);
		
//		Cell C4 = rowhead.createCell(7);
//		C4.setCellValue("..");
//		C4.setCellStyle(style4);
//		
//		Cell C41 = rowhead.createCell(8);
//		C41.setCellValue("..");
//		C41.setCellStyle(style5);
		
		
		
		return true;
	}

	
	public static boolean fillSubHeading(HSSFWorkbook workbook, HSSFRow rowhead2){
		

		CellStyle style1 = workbook.createCellStyle();
		style1.cloneStyleFrom(map.get("CELL1"));
		CellStyle style2 = workbook.createCellStyle();
		style2.cloneStyleFrom(map.get("CELL2"));
		CellStyle style3 = workbook.createCellStyle();
		style3.cloneStyleFrom(map.get("CELL3"));

		CellStyle style4 = workbook.createCellStyle();
		style4.cloneStyleFrom(map.get("CELL4"));
		
		CellStyle style5 = workbook.createCellStyle();
		style5.cloneStyleFrom(map.get("CELL5"));

		
		Cell C4 = rowhead2.createCell(0);
		C4.setCellValue("VERIFICATION:");
		//C4.setCellStyle(style4);
		
		Cell C5 = rowhead2.createCell(1);
		C5.setCellValue("");
		C5.setCellStyle(style4);
		
		Cell C6 = rowhead2.createCell(2);
		C6.setCellValue("");
		C6.setCellStyle(style4);
		
		Cell C7 = rowhead2.createCell(3);
		C7.setCellValue("");
		C7.setCellStyle(style4);
		
		Cell C8 = rowhead2.createCell(4);
		C8.setCellValue("");
		C8.setCellStyle(style4);
		
		Cell C9 = rowhead2.createCell(5);
		C9.setCellValue("");
		//C9.setCellStyle(style5);
		
		
		 
		
		/*
		
		Cell C91 = rowhead2.createCell(6);
		C91.setCellValue("ABC");
		CellStyle style =  workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
	    style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
	     //style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
	       FillPatternType fp = FillPatternType.SOLID_FOREGROUND;
	       style.setFillPattern(fp);
	        C91.setCellStyle(style);
	        
	        Cell C92 = rowhead2.createCell(7);
			C92.setCellValue("ABCD");
			CellStyle style10 =  workbook.createCellStyle();
			style10.setAlignment(HorizontalAlignment.CENTER);
		    style10.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		
		       FillPatternType fp1 = FillPatternType.SOLID_FOREGROUND;
		       style10.setFillPattern(fp1);
		        C92.setCellStyle(style10);
		        
		        
		        Cell C912 = rowhead2.createCell(8);
				C912.setCellValue("XCABC");
				CellStyle style11 =  workbook.createCellStyle();
				style11.setAlignment(HorizontalAlignment.CENTER);
			    style11.setFillForegroundColor(IndexedColors.LAVENDER.getIndex());
			     
			       FillPatternType fp2 = FillPatternType.SOLID_FOREGROUND;
			       style11.setFillPattern(fp2);
			        C912.setCellStyle(style11);
			        
			        
			        
			        Cell C913 = rowhead2.createCell(9);
					C913.setCellValue("PQABC");
					CellStyle style12 =  workbook.createCellStyle();
					style12.setAlignment(HorizontalAlignment.CENTER);
				    style12.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
				     
				       FillPatternType fp3 = FillPatternType.SOLID_FOREGROUND;
				       style12.setFillPattern(fp3);
				        C913.setCellStyle(style12);
				        
				        
				        Cell C914 = rowhead2.createCell(10);
						C914.setCellValue("PQABqqC");
						CellStyle style123 =  workbook.createCellStyle();
						style123.setAlignment(HorizontalAlignment.CENTER);
					    style123.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
					     
					       FillPatternType fp4 = FillPatternType.SOLID_FOREGROUND;
					       style123.setFillPattern(fp4);
					        C914.setCellStyle(style123);
					        
					        
					        Cell C915 = rowhead2.createCell(11);
							C915.setCellValue("PQABqqC");
							CellStyle style124 =  workbook.createCellStyle();
							style124.setAlignment(HorizontalAlignment.CENTER);
						    style124.setFillForegroundColor(IndexedColors.LIME.getIndex());
						     
						       FillPatternType fp5 = FillPatternType.SOLID_FOREGROUND;
						       style124.setFillPattern(fp5);
						        C915.setCellStyle(style124);
						        
						        
						        Cell C916 = rowhead2.createCell(12);
								C916.setCellValue("PQABqqC");
								CellStyle style1245 =  workbook.createCellStyle();
								style1245.setAlignment(HorizontalAlignment.CENTER);
							    style1245.setFillForegroundColor(IndexedColors.ROSE.index);
							     
							       FillPatternType fp6 = FillPatternType.SOLID_FOREGROUND;
							       style1245.setFillPattern(fp6);
							        C916.setCellStyle(style1245);
							        
							        
							        Cell C917 = rowhead2.createCell(13);
									C917.setCellValue("PQABqqC");
									CellStyle style12456 =  workbook.createCellStyle();
									style12456.setAlignment(HorizontalAlignment.CENTER);
								    style12456.setFillForegroundColor(IndexedColors.RED.index);
								     
								       FillPatternType fp7 = FillPatternType.SOLID_FOREGROUND;
								       style12456.setFillPattern(fp7);
								        C917.setCellStyle(style12456);
							        
							        
							        */
	        
		
		return true;
	}
	

	private static HSSFSheet createSheet(HSSFWorkbook workbook, String sheetName){
		
		HSSFSheet sheet = workbook.createSheet(sheetName);
		System.out.println("  map "+map.keySet());
		CellStyle style1 = workbook.createCellStyle();
		style1.cloneStyleFrom(map.get("CELL1"));
		CellStyle style2 = workbook.createCellStyle();
		style2.cloneStyleFrom(map.get("CELL2"));
		CellStyle style3 = workbook.createCellStyle();
		style3.cloneStyleFrom(map.get("CELL3"));

		CellStyle style4 = workbook.createCellStyle();
		style4.cloneStyleFrom(map.get("CELL4"));
		
		CellStyle style5 = workbook.createCellStyle();
		style5.cloneStyleFrom(map.get("CELL5"));
		
		HSSFRow rowhead = sheet.createRow((short) 1); 
		Cell C1 = rowhead.createCell(0);
		//C1.setCellValue("TCD_1");
		C1.setCellValue("");
		C1.setCellStyle(style3);
		
		//C1.getCellStyle().setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		//cloneStyleFrom(map.get("CELL1"));
		Cell C2 = rowhead.createCell(1);
		C2.setCellValue("");
		C2.setCellStyle(style2);
		//style2.setShrinkToFit(true);
		
		
		Cell C31 = rowhead.createCell(2);
		C31.setCellValue("");
		C31.setCellStyle(style2);
		
		
		Cell C32 = rowhead.createCell(3);
		C32.setCellValue("");
		C32.setCellStyle(style2);
		Cell C33 = rowhead.createCell(4);
		C33.setCellValue("");
		C33.setCellStyle(style2);
		Cell C34 = rowhead.createCell(5);
		C34.setCellValue("");
		C34.setCellStyle(style2);
		
		
		//C2.getCellStyle().setFillBackgroundColor(map.get("CELL2").getFillForegroundColor());
	//	C2.getCellStyle().cloneStyleFrom(map.get("CELL2"));
		Cell C3 = rowhead.createCell(6);
		C3.setCellValue("");
		C3.setCellStyle(style1);
		
		
		CellStyle style12456 =  workbook.createCellStyle();
		style12456.setAlignment(HorizontalAlignment.CENTER);
	  //  style12456.setFillForegroundColor(IndexedColors.RED.index);
	     
	       FillPatternType fp7 = FillPatternType.SOLID_FOREGROUND;
	       style12456.setFillPattern(fp7);
	       C3.setCellStyle(style12456);
	       
	       
		
		
		HSSFRow rowhead2 = sheet.createRow((short) 3); 
		Cell C4 = rowhead2.createCell(0);
		C4.setCellValue("VERIFICATION:");
		//C4.setCellStyle(style4);
		
		Cell C5 = rowhead2.createCell(1);
		C5.setCellValue("..");
		C5.setCellStyle(style4);
		
		Cell C6 = rowhead2.createCell(2);
		C6.setCellValue("..");
		C6.setCellStyle(style4);
		
		Cell C7 = rowhead2.createCell(3);
		C7.setCellValue("..");
		C7.setCellStyle(style4);
		
		Cell C8 = rowhead2.createCell(4);
		C8.setCellValue("..");
		C8.setCellStyle(style4);
		
		Cell C9 = rowhead2.createCell(5);
		C9.setCellValue("..");
		C9.setCellStyle(style5);
		
		
		
		
		
		//C3.getCellStyle().setFillBackgroundColor(map.get("CELL3").getFillBackgroundColor());
		//C3.getCellStyle().cloneStyleFrom(map.get("CELL3"));
		 
 		 
 		
		//rowhead.createCell(0).setCellValue("Project Key");
		
		/*rowhead.createCell(1).setCellValue("Component");
		rowhead.createCell(2).setCellValue("Line");
		rowhead.createCell(3).setCellValue("Rule Key");
		rowhead.createCell(4).setCellValue("Severity");
		
		
		
		rowhead.createCell(5).setCellValue("AUTHOR");
		rowhead.createCell(6).setCellValue("DATE");
		rowhead.createCell(7).setCellValue("REPORTED ON");
		
		rowhead.createCell(8).setCellValue("Message");
		rowhead.createCell(9).setCellValue("Status");
		rowhead.createCell(10).setCellValue("COMMENT");
		*/
		return sheet;
		
	}

	public static boolean fillData(HSSFWorkbook workbook, HSSFSheet sheet, ArrayList<ReportBean> arrayList ){
		
		int j = 5;
		
	
		if(arrayList.size() ==0 ){
			HSSFRow row = sheet.createRow((short) j+9);
			row.createCell(5).setCellValue(" NO RECORDS ");
			return true;
		}
		
		
		HSSFRow row = sheet.createRow((short) j);
//		row.createCell(0).setCellValue(arrayList.get(0).getProject());
//		j++;
//		row = sheet.createRow((short) j);
//			
//		row.createCell(0).setCellValue(arrayList.get(0).getModule());
//		j++;
//		row = sheet.createRow((short) j);
//		row.createCell(0).setCellValue(arrayList.get(0).getTestcaseID());
//		j++;
//		
		
		boolean flag = true;
		for(ReportBean rbean: arrayList){
			
			if(flag){
//				row = sheet.createRow((short) j);
//				row.createCell(0).setCellValue(rbean.getTestcaseID());
//				  row.createCell(1).setCellValue(rbean.getTestCaseDesc());
				  flag = false;
				  j++;
				 // j++;
				//  j++;
				 // j++;
			}
			
			HSSFRow rowhead2 = sheet.createRow((short) j++); 
			HSSFRow rowhead3 = sheet.createRow((short) j++); 
			
			
			Test.fillSubHeading(workbook, rowhead2);
			Test.fillSubHeading1(workbook, rowhead3);
			
			
			ArrayList<VerifyBean> list1 = rbean.getList();
			String result = "PASS";
			for(VerifyBean issue: list1){
				
				
				if(issue.getResult() == null){
					continue;
				}
				if(issue.getResult().equals("FAIL")){
					
					result = "FAIL";
				}
			  row = sheet.createRow((short) j);
			  row.createCell(1).setCellValue(issue.getVerify());
			  row.createCell(2).setCellValue(issue.getResult());
			  row.createCell(3).setCellValue(issue.getActualValue());
			  row.createCell(4).setCellValue(issue.getExpectedValue());
			  
			  
			 j++;
			}
			
			Test.fillsubHeadingData(workbook, rowhead2, rbean, result);
			j++;
		}
		
		 
//		sheet.autoSizeColumn(0);
//		sheet.autoSizeColumn(1);
//		sheet.autoSizeColumn(2);
//		sheet.autoSizeColumn(3);	
		 
		 
		
		 			
		return true;
	}
	
	
	public static boolean readExcel(){
		try { 
		            FileInputStream file = new FileInputStream(new File("E:\\Temp Eclipse Repository\\SCFAUTOMATION\\src\\main\\resources\\report-format-1.xls")); 
		  
		            // Create Workbook instance holding reference to .xlsx file 
		            HSSFWorkbook workbook = new HSSFWorkbook(file); 
		  
		            // Get first/desired sheet from the workbook 
		            HSSFSheet sheet = workbook.getSheetAt(0); 
		  
		            // Iterate through each rows one by one 
		            Iterator<Row> rowIterator = sheet.iterator(); 
		            int I = 1;
		            while (rowIterator.hasNext()) { 
		                Row row = rowIterator.next(); 
		                // For each row, iterate through all the columns 
		                Iterator<Cell> cellIterator = row.cellIterator(); 
		  
		                while (cellIterator.hasNext()) { 
		                    Cell cell = cellIterator.next(); 
		                    // Check the cell type and format accordingly 
		                    switch (cell.getCellType()) { 
		                    case Cell.CELL_TYPE_NUMERIC: 
		                        System.out.print(cell.getNumericCellValue() + "t"); 
		                        break; 
		                    case Cell.CELL_TYPE_STRING: 
		                        System.out.println(cell.getStringCellValue() + ":::"+I); 
		                        CellStyle cs = cell.getCellStyle();
		                       // cs.getF
		                        //System.out.println(" COLOR ::"+cs.getFillBackgroundColorColor());
		                        map.put("CELL"+I, cs);
		                        colormap.put("CELL"+I, cs.getFillBackgroundColor());
		                        
		                        		
		                         
		                        I++;
		                     //  break; 
		                    } 
		                } 
		                System.out.println("");
		                break;
		            } 
		            file.close(); 
		        } 
		        catch (Exception e) { 
		            e.printStackTrace(); 
		        }
		return true;
		    }


	public static void fillHeadingData(HSSFWorkbook workbook, HSSFRow rowhead, ReportBean reportBean, String testCaseResult) {
		rowhead.getCell(0).setCellValue(reportBean.getTestcaseID());
		rowhead.getCell(1).setCellValue(reportBean.getTestCaseDesc());
		
		//HSSFCellStyle style = workbook.createCellStyle();
		
		
		CellStyle style123456 =  workbook.createCellStyle();
		style123456.setAlignment(HorizontalAlignment.CENTER);
	   
	     
	       FillPatternType fp7 = FillPatternType.SOLID_FOREGROUND;
	       style123456.setFillPattern(fp7);
	       
	       
	       
		  
		 if(testCaseResult.equals("PASS")){
			 style123456.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		 }else{
			 style123456.setFillForegroundColor(IndexedColors.RED.getIndex());
		
		 }
		rowhead.getCell(6).setCellValue(testCaseResult);
		
		rowhead.getCell(6).setCellStyle(style123456);

		
	}


	public static void fillsubHeadingData(HSSFWorkbook workbook, HSSFRow rowhead2, ReportBean reportBean, String result) {
		
		rowhead2.getCell(1).setCellValue(reportBean.getUserParam());
		
		short s = 10;
		short s1 = 20;
		
		CellStyle style123456 =  workbook.createCellStyle();
		style123456.setAlignment(HorizontalAlignment.CENTER);
	   
	     
	     //  FillPatternType fp7 = FillPatternType.SOLID_FOREGROUND;
	       //style123456.setFillPattern(fp7);
	       
		
//		if(result.equals("PASS")){
//			//rowhead2.getCell(5).getCellStyle().setFillBackgroundColor(s);
//			rowhead2.getCell(5).getCellStyle().setFillForegroundColor(s);
//		}else{
//			//rowhead2.getCell(5).getCellStyle().setFillBackgroundColor(s1);
//			rowhead2.getCell(5).getCellStyle().setFillForegroundColor(s1);
//		}
		rowhead2.getCell(5).setCellValue(result);
		rowhead2.getCell(5).setCellStyle(style123456);
		
	} 
	
} 



