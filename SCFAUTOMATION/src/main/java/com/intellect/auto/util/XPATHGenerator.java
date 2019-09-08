package com.intellect.auto.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import javax.xml.parsers.*;
 
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class XPATHGenerator extends DefaultHandler{

	
	
	public static void main(String[] args)throws Exception  {
		 
 
        // Create a JAXP SAXParserFactory and configure it
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        spf.setValidating(false);
 
        // Create a JAXP SAXParser
        SAXParser saxParser = spf.newSAXParser();
 
        // Get the encapsulated SAX XMLReader
        XMLReader xmlReader = saxParser.getXMLReader();
 
        // Set the ContentHandler of the XMLReader
        xmlReader.setContentHandler(new XPATHGenerator());
 
        String filename =  "D:\\Learning\\New folder\\File\\html.xml";
        String path = new File(filename).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
 
        // Tell the XMLReader to parse the XML document
        xmlReader.parse("file:"+path);

	}
	
	// map of all encountered tags and their running count
    private Map<String, Integer> xPathCount = new HashMap<String, Integer>();
    // keep track of the succession of elements
    private Stack<String> tags = new Stack<String>();;
 
    /**
     * Construct the XPath expression
     */
    private String getCurrentXPath(String child) {
        String str = "//";
        boolean first = true;
        for (String tag : tags) {
            if (first)
                str = str + tag;
            else
                str = str + "/" + tag;
            str += "[" + xPathCount.get(str) + "]";
            first = false;
        }
        if (child != null) {
            str = str + (first ? "" : "/") + child;
        }
        return str;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String xPath = getCurrentXPath(localName);
        int count = (xPathCount.get(xPath) == null) ? 0 : xPathCount.get(xPath);
        xPathCount.put(xPath, 1 + count);
        tags.push(localName);
 
        System.out.println(xPath +"   "+qName+"  "+attributes.getValue(0));
    }
 
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        tags.pop();
    }

}



