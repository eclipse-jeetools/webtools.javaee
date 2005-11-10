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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jem.util.logger.proxy.Logger;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
/**
 * Insert the type's description here.
 * Creation date: (4/11/2001 11:58:50 AM)
 * @author: Administrator
 */
public class GeneralXmlDocumentReader
{
    protected InputSource inputSource;
    protected EntityResolver entityResolver;
    protected ErrorHandler errorHandler;
    protected boolean doValidate = false;
    protected boolean doAllowJavaEncodings = false;

/**
 * GeneralXmlDocumentReader constructor comment.
 */
    public GeneralXmlDocumentReader() 
    {
        super();
    }
/**
 * GeneralXmlDocumentReader constructor comment.
 */
    public GeneralXmlDocumentReader(InputSource source) 
    {
        super();
        setInputSource(source);
    }
/**
 * GeneralXmlDocumentReader constructor comment.
 */
    public GeneralXmlDocumentReader(InputSource source, EntityResolver resolver, ErrorHandler handler) 
    {
        super();
        setInputSource(source);
        setEntityResolver(resolver);
        setErrorHandler(handler);
    }
/**
 * Insert the method's description here.
 * Creation date: (4/11/2001 12:04:18 PM)
 * @return org.xml.sax.EntityResolver
 */
    protected org.xml.sax.EntityResolver createDefaultEntityResolver() 
    {
        return null;
    }
/**
 * Insert the method's description here.
 * Creation date: (4/11/2001 12:04:18 PM)
 * @return org.xml.sax.ErrorHandler
 */
    protected org.xml.sax.ErrorHandler createDefaultErrorHandler() 
    {
        return new ErrorHandler() {
        	/* (non-Javadoc)
			 * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
			 */
			public void error(SAXParseException exception) throws SAXException {
				throw exception;
			}
			/* (non-Javadoc)
			 * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
			 */
			public void fatalError(SAXParseException exception) throws SAXException {
				throw exception;
			}
			/* (non-Javadoc)
			 * @see org.xml.sax.ErrorHandler#warning(org.xml.sax.SAXParseException)
			 */
			public void warning(SAXParseException exception) throws SAXException {
				Logger.getLogger().logWarning(exception);
			}
        };
    }
    protected javax.xml.parsers.DocumentBuilder createNewDOMParser() 
    {
        try
        {
            javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
            setFeatures(dbf);
            return dbf.newDocumentBuilder();

        }
        catch (Exception ie)
        {
            handleException(J2EEXMLResourceHandler.getString(XMLParseResourceHandler.failed_instantiating_EXC_, (new Object[] {"Failed to instantiate parser"})), ie); //$NON-NLS-1$ //$NON-NLS-2$ = "Failed instantiating: "
        }
        return null;
    }
/**
 * Insert the method's description here.
 * Creation date: (4/11/2001 12:01:34 PM)
 * @return boolean
 */
    public boolean doAllowJavaEncodings() 
    {
        return doAllowJavaEncodings;
    }
/**
 * Insert the method's description here.
 * Creation date: (4/11/2001 12:01:34 PM)
 * @return boolean
 */
    public boolean doValidate() 
    {
        return doValidate;
    }
/**
 * Insert the method's description here.
 * Creation date: (4/11/2001 1:34:14 PM)
 * @return java.lang.ClassLoader
 */
    public java.lang.ClassLoader getClassLoader() 
    {
        ClassLoader cl = getClass().getClassLoader();
        if (cl == null)
            cl = Thread.currentThread().getContextClassLoader();
        if (cl == null)
            cl = ClassLoader.getSystemClassLoader();

        return cl;
    }
    public Document getDocument() 
    {
        return parseDocument();
    }
    
    protected Document getDocument(javax.xml.parsers.DocumentBuilder domParser) 
    {
        return domParser.newDocument();
    }


/**
 * Insert the method's description here.
 * Creation date: (4/11/2001 12:04:18 PM)
 * @return org.xml.sax.EntityResolver
 */
    public org.xml.sax.EntityResolver getEntityResolver() 
    {
        if (entityResolver == null)
            entityResolver = createDefaultEntityResolver();
        return entityResolver;
    }
/**
 * Insert the method's description here.
 * Creation date: (4/11/2001 12:04:18 PM)
 * @return org.xml.sax.ErrorHandler
 */
    public org.xml.sax.ErrorHandler getErrorHandler() 
    {
        if (errorHandler == null)
        {
            errorHandler = createDefaultErrorHandler();
        }
        return errorHandler;
    }

/**
 * Insert the method's description here.
 * Creation date: (4/11/2001 12:04:18 PM)
 * @return org.xml.sax.InputSource
 */
    public org.xml.sax.InputSource getInputSource() 
    {
        return inputSource;
    }
    
    protected void handleException(String aMessage, Throwable anException) 
    {
        if (anException instanceof InvocationTargetException)
        {
            InvocationTargetException invocationEx =
            (InvocationTargetException) anException;
            if (invocationEx.getTargetException() != null)
                handleException(aMessage, invocationEx.getTargetException());
        }
        else
            primHandleException(aMessage, anException);
    }
    
    protected Document parse(javax.xml.parsers.DocumentBuilder domParser, InputSource is) 
    {
        try
        {
            return domParser.parse(is);
        }
        catch (org.xml.sax.SAXException ex)
        {
            handleException(J2EEXMLResourceHandler.getString(XMLParseResourceHandler.failed_instantiating_EXC_, (new Object[] {"SAX Parser error"})), ex); //$NON-NLS-1$ //$NON-NLS-2$ = "Failed instantiating: "

        }
        catch (java.io.IOException ex)
        {
            handleException(J2EEXMLResourceHandler.getString(XMLParseResourceHandler.failed_instantiating_EXC_, (new Object[] {"IO Exception on input stream"})), ex); //$NON-NLS-1$ //$NON-NLS-2$ = "Failed instantiating: "
        }
        return null;
    }


    public Document parseDocument() 
    {
    	ClassLoader prevClassLoader = Thread.currentThread().getContextClassLoader();
    	try {
    		Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
			javax.xml.parsers.DocumentBuilder parser = createNewDOMParser();
        	setEntityResolver(parser);
        	setErrorHandler(parser);
        	return parse(parser, getInputSource());
	} finally {
      Thread.currentThread().setContextClassLoader(prevClassLoader);
	}
    	
    	
    	
    	
        
    }
    
    protected void primHandleException(String aMessage, Throwable anException) 
    {
        org.eclipse.jem.util.logger.proxy.Logger.getLogger().logError(anException);
        throw new RuntimeException(aMessage);

    }
/**
 * Insert the method's description here.
 * Creation date: (4/11/2001 12:01:34 PM)
 * @param newAllowJavaEncodings boolean
 */
    public void setAllowJavaEncodings(boolean newAllowJavaEncodings) 
    {
        doAllowJavaEncodings = newAllowJavaEncodings;
    }
    
    protected void setEntityResolver(javax.xml.parsers.DocumentBuilder domParser) 
    {
        if (getEntityResolver() != null)
        {
            domParser.setEntityResolver(getEntityResolver());
        }
    }


/**
 * Insert the method's description here.
 * Creation date: (4/11/2001 12:04:18 PM)
 * @param newEntityResolver org.xml.sax.EntityResolver
 */
    public void setEntityResolver(org.xml.sax.EntityResolver newEntityResolver) 
    {
        entityResolver = newEntityResolver;
    }
    

    protected void setErrorHandler(javax.xml.parsers.DocumentBuilder domParser) 
    {
        if (getErrorHandler() != null)
        {
            domParser.setErrorHandler(getErrorHandler());
        }
    }


/**
 * Insert the method's description here.
 * Creation date: (4/11/2001 12:04:18 PM)
 * @param newErrorHandler org.xml.sax.ErrorHandler
 */
    public void setErrorHandler(org.xml.sax.ErrorHandler newErrorHandler) 
    {
        errorHandler = newErrorHandler;
    }

    protected void setFeatures(javax.xml.parsers.DocumentBuilderFactory domParser) 
    {
        try {
			domParser.setValidating(doValidate());
	        domParser.setAttribute("http://apache.org/xml/features/allow-java-encodings", new Boolean(doAllowJavaEncodings())); //$NON-NLS-1$
	        domParser.setExpandEntityReferences(true);
		} catch(IllegalArgumentException e) {
			//Ignore
		}
    }


/**
 * Insert the method's description here.
 * Creation date: (4/11/2001 12:04:18 PM)
 * @param newInputSource org.xml.sax.InputSource
 */
    public void setInputSource(org.xml.sax.InputSource newInputSource) 
    {
        inputSource = newInputSource;
    }
/**
 * Insert the method's description here.
 * Creation date: (4/11/2001 12:01:34 PM)
 * @param newValidate boolean
 */
    public void setValidate(boolean newValidate) 
    {
        doValidate = newValidate;
    }
}


