package stepdef;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.intellect.auto.MessageBoxThread;
import com.intellect.auto.bean.Event;
import com.intellect.auto.bean.ProjectBean;
import com.intellect.auto.bean.SummaryBean;
import com.intellect.auto.bean.TestCaseBean;
import com.intellect.auto.util.ExcelToHtml;
import com.intellect.auto.util.ReportUtil;
import com.intellect.auto.util.SeleniumUtil;
import com.intellect.auto.util.Util;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import runner.Command;
import runner.CommandThread;
import runner.Test;

public class SCFLoginSD {

	WebDriver driver = null;
	public static ExtentReports reports;
	public static ExtentTest test;
	private static Logger Log = Logger.getLogger("app");
	public static ExtentHtmlReporter htmlReporter;

	public static void main(String args[]) {
		SCFLoginSD s = new SCFLoginSD();
		try {
			s.open_SCF_Application_and_login();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Given("^Open SCF Application and login$")
	public void open_SCF_Application_and_login() throws Throwable {
		StringBuffer sb = new StringBuffer();
		driver = SeleniumUtil.getDriver();
		driver.get("http://localhost:15014/BPS/");
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//Resources//extentReport.html");
		ReportUtil.configureExtHtmlReport(htmlReporter);
		reports = new ExtentReports();
		ReportUtil.configureExtReport(reports);
		reports.attachReporter(htmlReporter);
		long startTime = System.currentTimeMillis();
		Set<TestCaseBean> set = ProjectBean.getProjrctBeanNEW().getTestCaseSet();
		Thread t1 = new CommandThread(driver, set);
		// CommandThread t1 = new CommandThread(driver, set);
		Thread t2 = new Thread(new MessageBoxThread(t1));
		// t2.setPriority(Thread.MIN_PRIORITY);
		t1.start();
		// t1.run();
		t2.start();
		t1.join();
		long cmleteTime = System.currentTimeMillis();
		long execTime = cmleteTime - startTime + 20000;
		// String et = Test.getTime(execTime);
		// String filename = Test.test(ProjectBean.getProjrctBeanNEW().getTestCaseSet(),
		// et);
		// File file = new File(filename);
		// InputStream in;
		// try {
		// in = new FileInputStream(file);
		// //System.out.println(" HTML " +new ExcelToHtml(in).getHTML());
		//
		// Util.writefile(new ExcelToHtml(in).getHTML(),
		// "TestReport"+"_"+Util.getDate());
		//
		//
		//
		//
		// } catch (FileNotFoundException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// } catch (IOException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
	}

	@When("^I enter the credentials and click on SCF login button$")
	public void i_enter_the_credentials_and_click_on_SCF_login_button() throws Throwable {
		/*
		 * driver.findElement(By.xpath("//input[@name='ArmorTicket']")).clear(); //
		 * driver.findElement(By.xpath("//input[@name='ArmorTicket']")).sendKeys(
		 * "WCHMOD_MAKER1");;
		 * driver.findElement(By.xpath("//input[@name='ArmorTicket']")).sendKeys(
		 * "ADMIN_MAKER1");;
		 * //System.out.println("before login page : "+driver.getPageSource());
		 * driver.findElement(By.xpath(
		 * "//img[@src='images/indeximages/Login_button.gif']")).click();
		 * 
		 * Set<String> allWindows = driver.getWindowHandles();
		 * System.out.println("driver is  : "+allWindows); String currentWindow1 = null;
		 * Thread.sleep(20000); StringBuffer sb = new StringBuffer();
		 * System.out.println(" SLEEP ");
		 * 
		 * sb.append(" LOGIN CLICKED "); Thread.sleep(3000);
		 * 
		 * java.util.Set<java.lang.String> windowHandles = driver.getWindowHandles();
		 * System.out.println(windowHandles); int count = windowHandles.size();
		 * System.out.println("Total window "+count+"   "); String windowTitle =
		 * "Intellect Suite - Enterprise Platform for Boundaryless Banking from Intellect Design Arena"
		 * ; //dataSheet.getRow(2).getCell(6).getStringCellValue(); for (String window:
		 * windowHandles) { driver.switchTo().window(window);
		 * 
		 * System.out.println("current url is: " +
		 * driver.getCurrentUrl()+"   "+driver.getTitle());
		 * 
		 * if(driver.getTitle().equals("Workspace Picklist")){ String url =
		 * driver.getCurrentUrl(); System.out.println("current url is: " + url); break;
		 * }
		 * 
		 * }
		 * 
		 * driver.findElement(By.linkText("Intellect AT")).click(); Thread.sleep(1000);
		 * sb.append(" Intellect AT CLICKED ");
		 * 
		 * windowHandles = driver.getWindowHandles(); System.out.println(windowHandles);
		 * count = windowHandles.size();
		 * System.out.println("Total window "+count+"   "+windowHandles);
		 * sb.append(" SIZE  "+count+"   "); windowTitle = "BPS";
		 * //dataSheet.getRow(2).getCell(6).getStringCellValue(); for (String window:
		 * windowHandles) { driver.switchTo().window(window);
		 * 
		 * System.out.println("current url is: " +
		 * driver.getCurrentUrl()+"   "+driver.getTitle());
		 * sb.append(" URL "+driver.getCurrentUrl()+"   TITLE "+driver.getTitle());
		 * if(driver.getTitle().equals(windowTitle)){ String url =
		 * driver.getCurrentUrl(); System.out.println("current url is: " + url); break;
		 * }
		 * 
		 * } sb.append(driver.getPageSource()+"\n");
		 * sb.append("##################################\n");
		 * 
		 * 
		 * List<WebElement> iframes = driver.findElements(By.xpath("//frame")); // print
		 * your number of frames System.out.println(iframes.size());
		 * 
		 * // you can reach each frame on your site for (WebElement iframe : iframes) {
		 * sb.append("FRAME:::::::::: "+iframe.getTagName()+"  "+iframe.getAttribute(
		 * "name")+"\n"); // switch to every frame
		 * if(iframe.getAttribute("name").equals("MenuFrame")){
		 * driver.switchTo().frame(iframe); break; } // now within the frame you can
		 * navigate like you are used to //
		 * System.out.println(driver.findElement(By.id("tinymce")).getText()); }
		 * 
		 * Thread.sleep(3000);
		 * 
		 * //driver.switchTo().frame("MenuFrame");
		 * //sb.append(driver.getPageSource()+"\n"); //Util.writefile(sb.toString());
		 * //System.out.println("current url is: " + driver.getPageSource()); WebElement
		 * login = driver.findElement(By.id("img_mnuTAB0193")); login.click();
		 * WebElement login1 = driver.findElement(By.id("td_mnuTAB0193Sub253"));
		 * login1.click();
		 */
		// WebElement login = driver.findElement(By.id("img_mnuWF3709"));
		// driver.findElement(By.xpath("//img[@id='img_mnuTAB0193']")).click();
		// <img id="img_mnuTAB0193" style="float: left;" src="images/wf/ArrowLeft.jpg">
		// <img id="img_mnuTAB0193" style="float: left;" src="images/wf/ArrowLeft.jpg">
		// driver.get("http://10.10.7.250:20023/BPS/WFAppController?Event=GetMenu&ApplicationName=BPS&WORKSPACEID=3008");

		/*
		 * for(String currentWindow : allWindows){
		 * 
		 * driver.switchTo().window(currentWindow);
		 * System.out.println("driver is  : "+currentWindow+"  "+driver.getCurrentUrl())
		 * ;
		 * 
		 * currentWindow1 = currentWindow; } driver.switchTo().window(currentWindow1);
		 * // driver.switchTo(). System.out.println("Clicked  : "+currentWindow1);
		 * System.out.println("Clicked title : "+driver.getCurrentUrl()); Set<String>
		 * allWindows1 = driver.getWindowHandles();
		 * 
		 * 
		 * String currentWindow2 = null; for(String currentWindow : allWindows1){
		 * System.out.println("driver is  : "+currentWindow);
		 * driver.switchTo().window(currentWindow); currentWindow2 = currentWindow; }
		 * driver.switchTo().window(currentWindow2);
		 * System.out.println("Clicked  : "+currentWindow2);
		 * System.out.println("Clicked title : "+driver.getPageSource());
		 * driver.findElement(By.linkText("Intellect AT")).click(); Set<String>
		 * allWindows2 = driver.getWindowHandles(); String currentWindow3 = null;
		 * for(String currentWindow : allWindows2){
		 * System.out.println("driver is  : "+currentWindow);
		 * driver.switchTo().window(currentWindow); currentWindow3 = currentWindow; }
		 * driver.switchTo().window(currentWindow3);
		 * System.out.println("Clicked title : "+driver.getCurrentUrl());
		 * driver.switchTo().frame("MenuFrame");
		 * driver.findElement(By.xpath("//img[@id='img_mnuTAB02']")).click();
		 * driver.findElement(By.xpath("//a[@id='A_mnuTAB02Sub3']")).click();
		 */
	}

	@Then("^Login page should direct me to menu page$")
	public void login_page_should_direct_me_to_menu_page() throws Throwable {

	}

	@Then("^expand the Registartion$")
	public void expand_the_Registartion() throws Throwable {

	}

	@Then("^Click SCF$")
	public void click_SCF() throws Throwable {

	}
}
