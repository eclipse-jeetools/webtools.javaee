/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.taglib;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntime;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * This class parses tld files and produces TLDs, and Tags, and Attributes
 * 
 * Creation date: (11/7/2001 10:33:15 AM)
 * 
 * @author: Mindaugas Idzelis
 */
public class TLDDigester {
	private InputStream istream;
	private Document document;

	protected final static String PUBLICID_11 = "-//Sun Microsystems, Inc.//DTD JSP Tag Library 1.1//EN"; //$NON-NLS-1$
	protected final static String PUBLICID_12 = "-//Sun Microsystems, Inc.//DTD JSP Tag Library 1.2//EN"; //$NON-NLS-1$
	protected final static String PUBLICID_20 = "http://java.sun.com/xml/ns/j2ee web-jsptaglibrary_2_0.xsd"; //$NON-NLS-1$
	private static ErrorHandler errorHandler;
	private static EntityResolver resolver;


	public TLDDigester(InputStream stream) {
		super();
		this.istream = stream;
		getDocument();
	}

	public void close() {
		if (this.istream != null) {
			try {
				this.istream.close();
			} catch (IOException exc) {
				//Do nothing
			}
		}
	}

	//	public boolean isJSP11() {
	//		if(getDocument() != null){
	//			DocumentType docType = getDocument().getDoctype();
	//			if (docType != null && PUBLICID_11.equals(docType.getPublicId()))
	//				return true;
	//		}
	//		return false;
	//	}
	public String getJSPLevel() {
		if (getDocument() != null) {
			//			xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee
			// web-jsptaglibrary_2_0.xsd"
			//		    version="2.0"
			Element docElement = getDocument().getDocumentElement();
			if (docElement != null && "2.0".equals(docElement.getAttribute("version")) //$NON-NLS-1$ //$NON-NLS-2$
						&& PUBLICID_20.equals(docElement.getAttribute("xsi:schemaLocation"))) //$NON-NLS-1$
				return J2EEWebNatureRuntime.JSPLEVEL_2_0;

			DocumentType docType = getDocument().getDoctype();

			if (docType != null && PUBLICID_12.equals(docType.getPublicId()))
				return J2EEWebNatureRuntime.JSPLEVEL_1_2;

			if (docType != null && PUBLICID_11.equals(docType.getPublicId()))
				return J2EEWebNatureRuntime.JSPLEVEL_1_1;

		}
		return null;
	}

	public String getURI() {
		Node taglibNode = getTaglibNode();
		if (taglibNode != null)
			return getTagValue(taglibNode, "uri"); //$NON-NLS-1$
		return null;
	}

	public String getShortName() {
		Node taglibNode = getTaglibNode();
		String retVal = null;
		if (taglibNode != null) {
			retVal = getTagValue(taglibNode, "shortname"); //$NON-NLS-1$
			if (retVal == null)
				retVal = getTagValue(taglibNode, "short-name"); //$NON-NLS-1$
		}
		return retVal;
	}

	public String getDescription() {
		Node taglibNode = getTaglibNode();
		String retVal = null;
		if (taglibNode != null) {
			retVal = getTagValue(taglibNode, "description"); //$NON-NLS-1$
			if (retVal == null)
				retVal = getTagValue(taglibNode, "info"); //$NON-NLS-1$
		}
		return null;
	}

	protected Document createDocument() {
		Document doc = null;

		InputSource inputSource = new InputSource(this.istream);
		//		ClassLoader prevClassLoader = Thread.currentThread().getContextClassLoader();
		try {
			//			Thread.currentThread().setContextClassLoader(getClass().getClassLoader());

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			try {

				dbf.setValidating(false);

				//					dbf.setAttribute("http://apache.org/xml/features/continue-after-fatal-error",
				// Boolean.TRUE); //$NON-NLS-1$
				//					dbf.setAttribute("http://apache.org/xml/features/nonvalidating/load-dtd-grammar",
				// Boolean.FALSE); //$NON-NLS-1$
				//					dbf.setAttribute("http://apache.org/xml/features/nonvalidating/load-external-dtd",
				// Boolean.FALSE); //$NON-NLS-1$

				DocumentBuilder builder = dbf.newDocumentBuilder();
				builder.setEntityResolver(getEntityResolver());
				builder.setErrorHandler(getNullErrorHandler());
				doc = builder.parse(inputSource);
			} catch (ParserConfigurationException e) {
				//e.printStackTrace();
			} catch (SAXException e) {
				//e.printStackTrace();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		} finally {
			//		Thread.currentThread().setContextClassLoader(prevClassLoader);
		}
		return doc;
	}

	public Document getDocument() {
		if (this.document == null) {
			this.document = createDocument();
		}
		return this.document;
	}

	protected Node getTaglibNode() {
		Document doc = getDocument();
		Node taglibNode = null;
		if (doc != null) {
			taglibNode = findChildNode(doc, "taglib"); //$NON-NLS-1$
		}
		return taglibNode;
	}

	protected Node findChildNode(Node parent, String nodeName) {
		NodeList list = parent.getChildNodes();
		int length = list.getLength();
		for (int i = 0; i < length; ++i) {
			Node curNode = list.item(i);
			if (curNode.getNodeType() == Node.ELEMENT_NODE) {
				Element curElement = (Element) curNode;
				if (curElement.getNodeName().equalsIgnoreCase(nodeName))
					return curElement;
			}
		}
		return null;
	}

	protected String getTagValue(Node node, String tagName) {
		Node childNode = findChildNode(node, tagName);
		if (childNode != null) {
			Node textNode = childNode.getFirstChild();
			if ((textNode != null) && (textNode.getNodeType() == Node.TEXT_NODE)) {
				return removeWhitespace(textNode.getNodeValue());
			}
		}
		return null;
	}

	protected String removeWhitespace(String string) {
		return string.trim().replace(' ', '_');
	}

	/**
	 * Returns an EntityResolver that won't try to load and resolve ANY entities
	 */
	private static EntityResolver getEntityResolver() {
		if (resolver == null) {
			resolver = new EntityResolver() {
				public InputSource resolveEntity(String publicID, String systemID) throws SAXException, IOException {
					InputSource result = null;
					if (result == null) {
						result = new InputSource(new StringReader("")); //$NON-NLS-1$
						result.setPublicId(publicID);
						result.setSystemId(systemID != null ? systemID : "/_" + getClass().getName()); //$NON-NLS-1$
					}
					return result;
				}
			};
		}
		return resolver;
	}

	/**
	 * Returns an ErrorHandler that will not stop the parser on reported errors
	 */
	private static ErrorHandler getNullErrorHandler() {
		if (errorHandler == null) {
			errorHandler = new ErrorHandler() {
				public void error(SAXParseException exception) throws SAXException {
					Logger.getLogger().log(exception);
				}

				public void fatalError(SAXParseException exception) throws SAXException {
					Logger.getLogger().log(exception);
				}

				public void warning(SAXParseException exception) throws SAXException {
					Logger.getLogger().log(exception);
				}
			};
		}
		return errorHandler;
	}
}