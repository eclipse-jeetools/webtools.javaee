/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.xml;



import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class J2EEXmlDtDEntityResolver implements org.xml.sax.EntityResolver {

	/** All the dtds that this resolver knows about; import strategies register these
	 * at startup */ 
	protected static Map supportedDtDs;
	static {
		registerDtD("http://www.w3.org/2001/xml.xsd", "xml.xsd");  //$NON-NLS-1$ //$NON-NLS-2$
		registerDtD("XMLSchema.dtd", "XMLSchema.dtd"); //$NON-NLS-1$ //$NON-NLS-2$
		registerDtD("datatypes.dtd", "datatypes.dtd"); //$NON-NLS-1$ //$NON-NLS-2$
	}
	public static J2EEXmlDtDEntityResolver INSTANCE = new J2EEXmlDtDEntityResolver();
/**
 * EjbXmlEntityResolver constructor comment.
 */
public J2EEXmlDtDEntityResolver() {
	super();
}
public static Map getSupportedDtDs() {
	if (supportedDtDs == null)
		supportedDtDs = new HashMap();
	return supportedDtDs;
}
/**
 * Maps the system id for the dtd to a local id to be retrieved loaded from the class path
 */
public static void registerDtD(String systemID, String localID) { 
    //Make sure local file exists on classpath first.
	
	ClassLoader loader = J2EEXmlDtDEntityResolver.class.getClassLoader();
	URL url = null;
	if (loader == null) {	    
		url = ClassLoader.getSystemResource(localID);
	} else {	    
		url = loader.getResource(localID);
	}
	if (url == null) {
		return;
	}
	getSupportedDtDs().put(systemID, localID);
	getSupportedDtDs().put(getShortName(systemID), localID);
}
/**
 * for a system id with a URL that begins with "http://java.sun.com/", check to see if that is a recognized dtd;
 * if so, load the dtd from the class path using the value of the registered dtd.
 *
 * @return an Input source on a locally resolved dtd, or null of the systemid does not start with "http://java.sun.com/"
 * 
 * @throws SAXException with a nested NotSupportedException if the dtd is not recognized
 * @throws FileNotFoundException if the resolved dtd cannot be loaded from the classpath
 */
public org.xml.sax.InputSource resolveEntity(String publicId, String systemId) throws java.io.IOException, org.xml.sax.SAXException {
	String localResourceName = null;
	boolean isJavaSytemId = false;
	if (shouldBeRegistered(systemId)) {
		localResourceName = (String)getSupportedDtDs().get(systemId);
		isJavaSytemId = true;	
	} else {
		String shortName = getShortName(systemId);
		localResourceName = (String)getSupportedDtDs().get(shortName);
		if (localResourceName != null)
			systemId = localResourceName;
	}
		
	if (localResourceName == null) {
		return null;
	} 
	ClassLoader loader = getClass().getClassLoader();
	URL url = null;
	if (loader == null) {	    
		url = ClassLoader.getSystemResource(localResourceName);
	} else {	    
		url = loader.getResource(localResourceName);
	}
	
	
	if (url == null) {
		String message = J2EEXMLResourceHandler.getString("dtd_not_found_EXC_", (new Object[] {localResourceName}));//$NON-NLS-1$ = "Could not parse xml because the resolved resource "{0}" could not be found in classpath"
		throw new java.io.FileNotFoundException(message);
	}
	
	InputSource result = new InputSource(url.toString());
	result.setPublicId(publicId);	
	// force the encoding to be UTF8
	result.setEncoding("UTF-8"); //$NON-NLS-1$
	
	return result;
}
protected boolean shouldBeRegistered(String systemId) {
	
	return systemId.startsWith(J2EEConstants.JAVA_SUN_COM_URL) 
		|| systemId.startsWith(J2EEConstants.WWW_W3_ORG_URL)
		|| systemId.startsWith(J2EEConstants.WWW_IBM_COM_URL);
}

/**
 * Returns the filename from the uri, or the segment after the last occurrence of a separator
 */
private static String getShortName(String uri) {
	String tempURI = uri.replace('\\', '/');
	while (tempURI.endsWith("/")) //$NON-NLS-1$
		tempURI = tempURI.substring(0, tempURI.length()-1);
	int lastIndex = tempURI.lastIndexOf('/');
	if (lastIndex == -1)
		return uri;
	return uri.substring(lastIndex+1, tempURI.length());
}
}



 
