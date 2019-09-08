package com.intellect.auto.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;


public class XPATHGen1 extends DefaultHandler {

private String xPath = "/";
private XMLReader xmlReader;
private XPATHGen1 parent;
private StringBuilder characters = new StringBuilder();
private Map<String, Integer> elementNameCount = new HashMap<String, Integer>();

public static Set<String> set = new HashSet<String>();
public static Set<String> attSet = new HashSet<String>();

static{
	
	set.add("a");
	set.add("img");
	set.add("select");
	//set.add("FORM");
	set.add("link");
	//set.add("label");
	//set.add("body");
	set.add("SELECT");
	//set.add("td");
	set.add("div");
	set.add("input");
	//set.add("meta");
	//set.add("table");
	//set.add("tr");
	//set.add("span");
	set.add("option");
	
	
	attSet.add("width");
	attSet.add("height");
	/*attSet.add("width");
	attSet.add("width");
	attSet.add("width");
	attSet.add("width");
	attSet.add("width");*/
	

}
public XPATHGen1(XMLReader xmlReader) {
    this.xmlReader = xmlReader;
}

private XPATHGen1(String xPath, XMLReader xmlReader, XPATHGen1 parent) {
    this(xmlReader);
    this.xPath = xPath;
    this.parent = parent;
}

@Override
public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
    Integer count = elementNameCount.get(qName);
    if(null == count) {
        count = 1;
    } else {
        count++;
    }
    elementNameCount.put(qName, count);
    String childXPath = xPath + "/" + qName + "[" + count + "]";

    int attsLength = atts.getLength();
    for(int x=0; x<attsLength; x++) {
    	 
    	
       // System.out.println(childXPath + "[@" + atts.getQName(x) + "='" + atts.getValue(x) + "']"+"   "+qName);
    	if(set.contains(qName)){
    		if(!atts.getValue(x).contains(("style"))){
    			//if(!atts.getQName(x).contains(("body"))){
    				//System.out.println( atts.getQName(x) + "=============='" + atts.getValue(x) + "']"+"   ");
    			//System.out.println(qName+"/"+ "[@" + atts.getQName(x) + "='" + atts.getValue(x) + "']"+"   "+qName);
    			
    			System.out.println(qName+"/"+ "[@" + atts.getQName(x) + "='" + atts.getValue(x) + "']");
    			//}
    		}
    	}
    }

    XPATHGen1 child = new XPATHGen1(childXPath, xmlReader, this);
    xmlReader.setContentHandler(child);
}

@Override
public void endElement(String uri, String localName, String qName) throws SAXException {
    String value = characters.toString().trim();
    if(value.length() > 0) {
       // System.out.println(xPath + "='" + characters.toString() + "'");
    }
    xmlReader.setContentHandler(parent);
}

@Override
public void characters(char[] ch, int start, int length) throws SAXException {
    characters.append(ch, start, length);
}

}
