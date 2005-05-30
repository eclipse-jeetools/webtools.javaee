/*
 * Created on Nov 29, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.tests.performance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestCaseSAXParser extends DefaultHandler {
	private static Map performanceCases = null;
	private static final String TEST_CASE = "testCase";
	private static final String CLASS = "class";
	private static final String METHOD = "method";
	private static final String SHORT_NAME = "shortName";
	private static final String TAGGING = "tagging";
	private static final String METHOD_NAME = "name";
	private static final String GLOBAL = "GLOBAL";
	private static final String LOCAL = "LOCAL";
	private static Class currentClazz = null;
	

	
	public Map doLoad(File file) {
		 performanceCases = new HashMap();
		 DefaultHandler handler = new TestCaseSAXParser();
		 SAXParserFactory factory = SAXParserFactory.newInstance();
		 try {
		  SAXParser saxParser = factory.newSAXParser();
		  saxParser.parse(file , handler );
		 }
		 catch (ParserConfigurationException ex) {
		  System.err.println ("Failed to create SAX parser:" + ex);
		 }
		 catch (SAXException ex) {
		  System.err.println ("SAX parser exceeption:" + ex);
		 }
		 catch (IOException ex) {
		  System.err.println ("IO exeception:" + ex);
		 }
		 catch (IllegalArgumentException ex) {
		  System.err.println ("Invalid file argument" + ex);
		 }
		 return performanceCases;
	 }
	
	 public void startElement(String uri, String localName, 
	  String qualifiedName, Attributes attributes) throws SAXException {
	 	if (qualifiedName.equals(TEST_CASE)) {
		  for (int i = 0; i< attributes.getLength(); i++) {
		    if (attributes.getQName(i).equalsIgnoreCase(CLASS)) {
		    	try {
		    		currentClazz = loadClass(attributes.getValue(i));
					if (performanceCases.get(currentClazz) == null) {
			    		ArrayList methodList = new ArrayList();
			    		performanceCases.put(currentClazz,methodList);
		    		}
				} catch (ClassNotFoundException e) {
					currentClazz = null;
					 System.err.println ("Failed to load class:" + attributes.getValue(i));
					//e.printStackTrace();
				}
		    } // end of if
		  } // end of for
	 	} // end of if TEST_CASE
	 	else if (qualifiedName.equalsIgnoreCase(METHOD)) {
	 		 String methodName = null;
	 		 int tagging = 0;
	 		 String shortName = null;
	 		 
	 		 if (currentClazz == null) return;
	 		 
	 		 for (int i = 0; i< attributes.getLength(); i++) {
	 		    if (attributes.getQName(i).equalsIgnoreCase(METHOD_NAME)) {
	 		    	methodName = attributes.getValue(i);
	 		    } 
	 		    else if (attributes.getQName(i).equalsIgnoreCase(TAGGING)){
	 		    	if (attributes.getValue(i).equalsIgnoreCase(GLOBAL)) {
	 		    		tagging = PerformanceTestCaseWrapper.GLOBAL;
	 		    	} else if (attributes.getValue(i).equalsIgnoreCase(LOCAL)) {
	 		    		tagging = PerformanceTestCaseWrapper.LOCAL;
	 		    	} else {
	 		    		tagging = PerformanceTestCaseWrapper.NONE;
	 		    	}
	 		 	} else if(attributes.getQName(i).equalsIgnoreCase(SHORT_NAME)) {
	 		 		shortName = attributes.getValue(i);
	 			}
	 		 } // end of for
	 		 List methodList = (List) performanceCases.get(currentClazz);
	 		 methodList.add(new PerformanceTestObject(methodName,tagging,shortName));
	 	 }
	 }

	 public void endElement(String uri, String localName, String qualifiedName)
	     throws SAXException {
	    if (qualifiedName.equals(TEST_CASE)) {
	  		currentClazz = null;
	  	}
	 }
	 
	 /**
	 * Loads the test suite class.
	 */
	 private Class loadClass(String className) throws ClassNotFoundException {
		if (className == null) 
			return null;
		return getClassLoader().loadClass(className);
	 }
	 
	 /**
	 * The class loader to be used for loading tests.
	 * Subclasses may override to use another class loader.
	 */
	 protected ClassLoader getClassLoader() {
		return BasePerformanceTestCase.getClassLoader();
	 }
	 
}

