package runner;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.intellect.auto.bean.Event;
import com.intellect.auto.bean.ProjectBean;
import com.intellect.auto.bean.SummaryBean;
import com.intellect.auto.bean.TestCaseBean;
import com.intellect.auto.util.SeleniumUtil;


import stepdef.SCFLoginSD;

public class CommandThread extends Thread{
	
	WebDriver driver = null;
	Set<TestCaseBean> set = null;
	
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public CommandThread(WebDriver driver, Set<TestCaseBean> set ){
		this.driver = driver;
		this.set = set;
	}
	 public void run() {
		  
		 for(TestCaseBean testCase:	set){	 
				SummaryBean  bean1 = new SummaryBean();
				String STR = testCase.getTestCaseID();
				System.out.println(" EVENT::  GOT TEST CASE "+STR+"  "+ProjectBean.getProjrctBeanNEW().getTestCaseSet().size());
				long l = System.currentTimeMillis();
				List<Event> list = testCase.getEventList();
				System.out.println(" EVENT::  GOT TEST CASE  SIZE "+list.size());
				if(list != null){
//					driver = SeleniumUtil.getDriver();
//					driver.get("http://localhost:15014/BPS/");
					Command cmd = new Command(testCase, list, driver);
					testCase.setResult(cmd.execute());
//					if(testCase.getEventList() != null){
//						Log.debug("  TCD "+testCase.getTestCaseID()+"   "+testCase.getEventList().size());
//					}else{
//						Log.debug("  TCD "+testCase.getTestCaseID()+" NO EVENTS ");
//					}
					//SCFLoginSD.reports.createTest(testCase.getTestCaseDesc() +" is completeed in "+ (System.currentTimeMillis() -l )).pass("TEST PASSED");
				}else{
					System.out.println(" NOT FOUND EVENT LIST ");
				}
			}
		 
		 
	 }

}
