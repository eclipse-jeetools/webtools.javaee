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



import java.io.InputStream;

import org.eclipse.jst.j2ee.internal.WrappedRuntimeException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 * Insert the type's description here.
 * Creation date: (02/21/01 4:34:13 PM)
 * @author: Administrator
 */
public class XmlDocumentReader extends GeneralXmlDocumentReader {
/**
 * XmlDocumentReader constructor comment.
 */
public XmlDocumentReader(InputSource source) {
	super(source);
	setFlagDefaults();
}
/**
 * XmlDocumentReader constructor comment.
 */
public XmlDocumentReader(InputSource source, EntityResolver resolver, ErrorHandler handler) {
	super(source, resolver, handler);
	setFlagDefaults();
}
/**
 * Insert the method's description here.
 * Creation date: (03/19/01 10:13:26 AM)
 * @return org.xml.sax.EntityResolver
 */
public org.xml.sax.EntityResolver createDefaultEntityResolver() {
	return J2EEXmlDtDEntityResolver.INSTANCE;
}

protected void handleException(String aMessage, Throwable anException) {
	if (anException instanceof SAXParseException) 
		handleException(aMessage, (SAXParseException) anException);
	else
		super.handleException(aMessage, anException);
}
protected void handleException(String aMessage, SAXParseException parseException) {
	primHandleException(J2EEXMLResourceHandler.getString("parse_exception_occured_EXC_", (new Object[] {new Integer(parseException.getLineNumber()), new Integer(parseException.getColumnNumber()) })), parseException);  //$NON-NLS-1$
	//$NON-NLS-1$ = "An Exception occurred while parsing xml: {0} Line #: {1} :Column #: "
}
protected void primHandleException(String aMessage, Throwable anException) {
	if (anException instanceof Exception)
		throw new WrappedRuntimeException(aMessage, (Exception)anException);
	super.primHandleException(aMessage, anException);
}
protected void setFlagDefaults() {
	setAllowJavaEncodings(true);
	setValidate(true);
}
   public static DocumentType readDocumentType(InputStream in, String uri) {
    	InputSource source = new InputSource(in);
    	GeneralXmlDocumentReader aReader =  new XmlDocumentReader(source);
    	aReader.setValidate(false);
    	Document aDocument = aReader.parseDocument();
    	if (aDocument != null)
    		return aDocument.getDoctype();
    	return null;
    }
    	
}


