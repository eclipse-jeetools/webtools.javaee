/*******************************************************************************
 * Copyright (c) 2004, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.util.internal;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.DefaultHandler;


public final class XMLRootHandler extends DefaultHandler implements LexicalHandler {
	/**
	 * An exception indicating that the parsing should stop. This is usually
	 * triggered when the top-level element has been found.
	 * 
	 * @since 3.0
	 */
	private class StopParsingException extends SAXException {
		/**
		 * All serializable objects should have a stable serialVersionUID
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * Constructs an instance of <code>StopParsingException</code> with a
		 * <code>null</code> detail message.
		 */
		public StopParsingException() {
			super((String) null);
		}
	}

	private String dtdName = null;

	private String dtdPublicID = null;

	private String dtdSystemID = null;

	/**
	 * This is the name of the top-level element found in the XML file. This
	 * member variable is <code>null</code> unless the file has been parsed
	 * successful to the point of finding the top-level element.
	 */
	private String elementFound = null;

	/**
	 * These are the attributes of the top-level element found in the XML file.
	 * This member variable is <code>null</code> unless the file has been
	 * parsed successful to the point of finding the top-level element.
	 */
	private Attributes attributesFound = null;

	public String getDtdName() {
		return dtdName;
	}

	public String getDtdPublicID() {
		return dtdPublicID;
	}

	public String getDtdSystemID() {
		return dtdSystemID;
	}

	public String getRootName() {
		return elementFound;
	}

	public Attributes getRootAttributes() {
		return attributesFound;
	}

	public XMLRootHandler() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.ext.LexicalHandler#comment(char[], int, int)
	 */
	@Override
	public final void comment(final char[] ch, final int start, final int length) {
		// Not interested.
	}

	/**
	 * Creates a new SAX parser for use within this instance.
	 * 
	 * @return The newly created parser.
	 * 
	 * @throws ParserConfigurationException
	 *             If a parser of the given configuration cannot be created.
	 * @throws SAXException
	 *             If something in general goes wrong when creating the parser.
	 * @throws SAXNotRecognizedException
	 *             If the <code>XMLReader</code> does not recognize the
	 *             lexical handler configuration option.
	 * @throws SAXNotSupportedException
	 *             If the <code>XMLReader</code> does not support the lexical
	 *             handler configuration option.
	 */
	private final SAXParser createParser(SAXParserFactory parserFactory) throws ParserConfigurationException, SAXException, SAXNotRecognizedException, SAXNotSupportedException {
		// Initialize the parser.
		final SAXParser parser = parserFactory.newSAXParser();
		final XMLReader reader = parser.getXMLReader();
		reader.setProperty("http://xml.org/sax/properties/lexical-handler", this); //$NON-NLS-1$
		// disable DTD validation (bug 63625)
		try {
			// be sure validation is "off" or the feature to ignore DTD's will
			// not apply
			reader.setFeature("http://xml.org/sax/features/validation", false); //$NON-NLS-1$
			reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false); //$NON-NLS-1$
		} catch (SAXNotRecognizedException e) {
			// not a big deal if the parser does not recognize the features
		} catch (SAXNotSupportedException e) {
			// not a big deal if the parser does not support the features
		}
		return parser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.ext.LexicalHandler#endCDATA()
	 */
	@Override
	public final void endCDATA() {
		// Not interested.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.ext.LexicalHandler#endDTD()
	 */
	@Override
	public final void endDTD() {
		// Not interested.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.ext.LexicalHandler#endEntity(java.lang.String)
	 */
	@Override
	public final void endEntity(final String name) {
		// Not interested.
	}

	public boolean parseContents(InputSource contents) throws IOException, ParserConfigurationException, SAXException {
		// Parse the file into we have what we need (or an error occurs).
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			if (factory == null)
				return false;
			final SAXParser parser = createParser(factory);
			// to support external entities specified as relative URIs (see bug
			// 63298)
			contents.setSystemId("/"); //$NON-NLS-1$
			parser.parse(contents, this);
		} catch (StopParsingException e) {
			// Abort the parsing normally. Fall through...
		}
		return true;
	}

	/*
	 * Resolve external entity definitions to an empty string. This is to speed
	 * up processing of files with external DTDs. Not resolving the contents of
	 * the DTD is ok, as only the System ID of the DTD declaration is used.
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#resolveEntity(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
		return new InputSource(new StringReader("")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.ext.LexicalHandler#startCDATA()
	 */
	@Override
	public final void startCDATA() {
		// Not interested.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.ext.LexicalHandler#startDTD(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public final void startDTD(final String name, final String publicId, final String systemId) throws SAXException {
		dtdName = name;
		dtdPublicID = publicId;
		dtdSystemID = systemId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String,
	 *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public final void startElement(final String uri, final String elementName, final String qualifiedName, final Attributes attributes) throws SAXException {
		elementFound = elementName == null || elementName.length() == 0 ? qualifiedName: elementName;
		if(elementFound.indexOf(':') != -1){
			elementFound = elementFound.substring(elementFound.indexOf(':')+1);
		}
		attributesFound = attributes;
		throw new StopParsingException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.ext.LexicalHandler#startEntity(java.lang.String)
	 */
	@Override
	public final void startEntity(final String name) {
		// Not interested.
	}

}
