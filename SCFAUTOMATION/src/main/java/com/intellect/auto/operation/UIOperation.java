package com.intellect.auto.operation;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UIOperation {
	WebDriver driver;
    public UIOperation(WebDriver driver){
        this.driver = driver;
    }
    public void perform(Properties p,String operation,String objectName,String objectType,String value) throws Exception{
        System.out.println(" ABC ");
        switch (operation.toUpperCase()) {
        case "CLICK":
            driver.findElement(this.getObject(p,objectName,objectType)).click();
            break;
        case "SETTEXT":
            driver.findElement(this.getObject(p,objectName,objectType)).sendKeys(value);
            break;
            
        case "GOTOURL":
            driver.get(p.getProperty(value));
            break;
        case "GETTEXT":
            driver.findElement(this.getObject(p,objectName,objectType)).getText();
            break;
        default:
            break;
        }
    }
    
    /**
     * Find element BY using object type and value
     * @param p
     * @param objectName
     * @param objectType
     * @return
     * @throws Exception
     */
    private By getObject(Properties p,String objectName,String objectType) throws Exception{
    	
    	switch(objectType.toUpperCase()){
    	case "XPATH" : 		 return By.xpath(p.getProperty(objectName));
    	case "CLASSNAME":	 return By.className(p.getProperty(objectName));	 	
    	case "NAME":		 return By.name(p.getProperty(objectName));	
    	case "CSS":			 return By.cssSelector(p.getProperty(objectName));
    	case "LINK":			  return By.linkText(p.getProperty(objectName));
    	case "PARTIALLINK":     return By.partialLinkText(p.getProperty(objectName));
    	default:  throw new Exception("Wrong object type");
    	}
    }
}
