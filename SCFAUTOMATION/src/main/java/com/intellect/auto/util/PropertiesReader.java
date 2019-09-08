package com.intellect.auto.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//import org.selenium.testing.automation.utility.Constants;

public class PropertiesReader {
	private static Properties prop = new Properties();
	public String getPropValues() throws IOException {
		String propFileName = "Constants.CONFIGPATH";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		return propFileName;
	}
	
	public static String getValue(String key){
		if(prop != null){
			return prop.getProperty(key);
		}else{
			PropertiesReader p = new PropertiesReader();
			try {
				p.getPropValues();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
}
