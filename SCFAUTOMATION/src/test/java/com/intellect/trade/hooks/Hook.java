package com.intellect.trade.hooks;

import java.net.MalformedURLException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import com.intellect.auto.util.Util;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook {
	public static WebDriver driver;
	@Before
	/**
	* Delete all cookies at the start of each scenario to avoid
	* shared state between tests
	*/
	public void openBrowser() throws MalformedURLException {
	System.out.println("Called openBrowser");
	
	System.setProperty("webdriver.ie.driver", "D:\\Learning\\Selinium\\31432\\1\\IEDriverServer_win32.exe");
	InternetExplorerOptions options = Util.getOptions();
		driver = new InternetExplorerDriver(options); 
		
	//driver = new ChromeDriver();
//	 System.setProperty("webdriver.ie.driver", "D:\\Learning\\Selinium\\IEDriverServer.exe");
//	 driver =  (WebDriver) new InternetExplorerDriver();
//	  driver.manage().window().maximize();
	//driver.manage().deleteAllCookies();
	}
	@After
	/**
	* Embed a screenshot in test report if test is marked as failed
	*/
	public void embedScreenshot(Scenario scenario) {
		System.out.println("TESTEDr");
		/*
	if(scenario.isFailed()) {
	try {
	scenario.write("Current Page URL is " + driver.getCurrentUrl());
	byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	scenario.embed(screenshot, "image/png");
	} 
	catch (WebDriverException somePlatformsDontSupportScreenshots) {
	System.err.println(somePlatformsDontSupportScreenshots.getMessage());
	} 
	}
	//driver.quit();*/
	}

}
