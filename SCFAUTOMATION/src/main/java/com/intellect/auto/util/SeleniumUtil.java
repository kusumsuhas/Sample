package com.intellect.auto.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtil {
	public static WebDriver driver;
	public static WebDriverWait wait = null;
	
 public static  WebDriver getDriver(){
	System.out.println(" CALLING UTIL ");
	 try{
		if(driver == null){
			if(Config.BROWSER.equals("IE")){
			       driver =  getIEDriver();
			}
			if(Config.BROWSER.equals("CHROME")){
			       driver =  getChromeDriver();
			}
		}
		  
	 }catch(Exception e){
		 e.printStackTrace();
	 }
		return driver;
 }

 private static  WebDriver getIEDriver(){
	 System.setProperty("webdriver.ie.driver", "E:\\Trainings\\Selenium\\Drivers\\IEDriverServer_x64_3.13.0\\IEDriverServer.exe");
	 InternetExplorerOptions options = Util.getOptions();
	 driver = new InternetExplorerDriver(options); 
	 driver.manage().window().maximize();
	 return driver;
}

 private static  WebDriver getChromeDriver(){
	 System.setProperty("webdriver.chrome.driver", "D:\\Learning\\Selinium\\chromedriver.exe");
	 driver = new ChromeDriver();
	 driver.manage().window().maximize();	 
	 return driver;
}

 public static WebDriverWait getWait(){
	if(wait == null){
		wait = new WebDriverWait(driver, 60);
	}
	return wait;
}
	
 public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
	 TakesScreenshot scrShot =((TakesScreenshot)webdriver);
     File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
     File DestFile=new File(fileWithPath);
     FileUtils.copyFile(SrcFile, DestFile);           
    }
}
