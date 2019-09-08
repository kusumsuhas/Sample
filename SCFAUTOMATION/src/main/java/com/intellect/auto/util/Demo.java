package com.intellect.auto.util;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class Demo {

    public static void main(String[] args) throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        XMLReader xr = sp.getXMLReader();

        xr.setContentHandler(new XPATHGen1(xr));
        
        String filename =  "D:\\Learning\\New folder\\File\\html.xml";
        String path = new File(filename).getAbsolutePath();
        
        xr.parse(new InputSource(new FileInputStream(path)));
        
        
        for(String s: XPATHGen1.set){
        	// System.out.println("set.add(\""+s+"\");");
        }
       
    }
}

