package com.se.trade.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
	private static WebElement element = null;

	 public static WebElement loginBtn(WebDriver driver){
		// driver.switchTo().frame(1);
	   // element = driver.findElement(By.id("account"));
		// WebElement temp = driver.findElement(By.xpath("images/indeximages/Login_button.gif"));
		 
		
		 List<WebElement>	list1 =	 driver.findElements(By.xpath("//*"));
			
			for(WebElement el: list1){
				 System.out.println(" DATA "+el.findElement(By.tagName(el.getTagName())));
			}
		 
		 
		  WebElement element = driver.findElement(By.xpath("//img[@src='images/indeximages/Login_button.gif']"));
		 
		 // WebElement element  =
			//	  driver.findElement(By.xpath("/html[1]/body[1]/FORM[1]/table[2]/tr[1]/td[2]/table[1]/tr[2]/td[1]/table[1]/tr[4]/td[3]/a[1]/img[1][@src='images/indeximages/Login_button.gif']"));

		// WebElement element = driver.findElement(By.name("'ApplicationName'")) ;
		 
		// WebElement element1 = driver.findElement(By.xpath("html[1]/body[1]/FORM[1]/table[2]/tr[1]/td[2]/table[1]/tr[2]/td[1]/table[1]/tr[4]/td[3]/a[1]/img[1][@src='images/indeximages/Login_button.gif']"));
			//html[1]/body[1]/FORM[1]/table[2]/tr[1]/td[2]/table[1]/tr[2]/td[1]/table[1]/tr[4]/td[3]/a[1]/img[1][@src='images/indeximages/Login_button.gif]
	//	 WebElement element1 = driver.findElement(By.tagName("html[1]/body[1]/FORM[1]/table[2]/tr[1]/td[2]/table[1]/tr[2]/td[1]/table[1]/tr[4]/td[3]/a[1]/img"));
		
		 
		 
		// WebElement element1  = driver.findElement(By.xpath("option/[@value='Workflow]"));
		  
		// WebElement mySelectElement = driver.findElement(By.name("ApplicationName")) ;
		  
		//  WebElement mySelectElement = driver.findElement(By.xpath("select/[@name='ApplicationName']")) ;
				 //driver.findElement(By.xpath("select/[@name='ApplicationName']"));
		 
		 
		 //Select dropdown= new Select(mySelectElement);
		 //dropdown.selectByVisibleText("Workflow");
		// dropdown.deselectAll();
		 //List<WebElement>	list  = dropdown.getAllSelectedOptions();
		 
//		 for(WebElement el: list){
//			 System.out.println(" DATA "+el.getText());
//		}
//		 
		 
		 
		//html[1]/body[1]/FORM[1]/table[2]/tr[1]/td[2]/table[1]/tr[2]/td[1]/table[1]/tr[4]/td[3]/a[1]/img[1][@src='images/indeximages/Login_button.gif]

		//html[1]/body[1]/FORM[1]/table[2]/tr[1]/td[2]/table[1]/tr[2]/td[1]/table[1]/tr[4]/td[3]/a[1]/img[1][@src='images/indeximages/Login_button.gif]
		// images/wf/ArrowLeft.jpg"
		// WebElement element = driver.findElement(By.xpath("/HTML/BODY/FORM/TABLE[2]/TBODY/TR/TD[2]/TABLE/TBODY/TR[2]/TD/TABLE/TBODY/TR[4]"));
		// System.out.println(" DATA ");
		 
		 ///images/wf/ArrowLeft.jpg"
		// driver.
		// WebElement element[] = 
		 
		// WebElement element = driver.findElement(By.xpath("//HTML/BODY/form"));
	//	 driver.getCurrentUrl();
		 // /HTML/BODY/FORM/TABLE[2]
		 //WebElement element = driver.findElement(By.xpath("/"));
			 
		 
		// driver.switchTo().frame(1);
		// System.out.println("TEXT::::::"+obj.getClass().getName());
		// System.out.println(obj.toString());
		// System.out.println(" DATA  1 ");
		 //System.out.println(" 1 "+driver.getCurrentUrl());
		// System.out.println(" 2 "+driver.getPageSource());
		// System.out.println(" 3 "+element.getText());
		// images/indeximages/Login_button.gif
		// temp.click();
		// driver.
		  
	    return element;

	    }

	 public static WebElement lnk_LogOut(WebDriver driver){

	    element = driver.findElement(By.id("account_logout"));

	 return element;

	    }


}
