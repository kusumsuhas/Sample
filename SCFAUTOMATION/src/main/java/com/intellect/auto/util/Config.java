package com.intellect.auto.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Config {
	private static Logger Log = Logger.getLogger("app");
	public static final String BROWSER;
	static{
		Properties configProp = readFile();
		BROWSER = configProp.getProperty("BROWSER");
		System.out.println(" BROWSER "+BROWSER);
		Log.debug(" BROWSER IS  "+BROWSER);
	}
  
	public static Properties readFile(){
		 
		
		Properties configProp = new Properties();
		try {
			configProp.load(new FileInputStream(Constants.CONFIG_FILE_PATH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
		return configProp;
		
	}
	
	
	 
	
	
}
