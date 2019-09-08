package helper;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.se.trade.page.HomePage;

public class Login {
	
	//public WebDriver driver;

	public boolean login(WebDriver driver){
	 driver.manage().timeouts().pageLoadTimeout(600, TimeUnit.SECONDS);
	
	 WebElement element = driver.findElement(By.xpath("//img[@src='images/indeximages/Login_button.gif']"));	 
	//driver.get("http://10.10.73.19:21141/BPS/WFAppController?Event=GetMenu&ApplicationName=BPS&WORKSPACEID=null");
		
		return true;
	}
	  
}
