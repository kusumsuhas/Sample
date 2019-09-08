package com.intellect.trade.pageobj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	private static WebElement element = null;

	 public static WebElement loginBtn(WebDriver driver){
		 WebElement element = driver.findElement(By.xpath("//img[@src='images/indeximages/Login_button.gif']"));
		 System.out.println(" DATA ");
		 driver.getCurrentUrl();
		 return element;
	 }

	 public static WebElement lnk_LogOut(WebDriver driver){
	    element = driver.findElement(By.id("account_logout"));
	    return element;
	 }
}
