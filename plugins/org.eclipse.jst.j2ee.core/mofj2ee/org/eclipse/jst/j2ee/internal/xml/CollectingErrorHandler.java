/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.xml;



import java.util.ArrayList;
import java.util.List;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
/**
 * Insert the type's description here.
 * Creation date: (03/19/01 11:10:38 AM)
 * @author: Administrator
 */
public class CollectingErrorHandler implements ErrorHandler {
	protected List caughtExceptions;
/**
 * CollectingErrorHandler constructor comment.
 */
public CollectingErrorHandler() {
	super();
}
/**
 * @see org.xml.sax.ErrorHandler
 */
@Override
public void error(SAXParseException exception) throws SAXException {
	getCaughtExceptions().add(exception);
}
/**
 * @see org.xml.sax.ErrorHandler
 */
@Override
public void fatalError(SAXParseException exception) throws SAXException {
	getCaughtExceptions().add(exception);
}
public List getCaughtExceptions() {
	if (caughtExceptions == null) {
		caughtExceptions = new ArrayList();
	}
	return caughtExceptions;
}

/**
 * @see org.xml.sax.ErrorHandler
 */
@Override
public void warning(SAXParseException exception) throws SAXException {
	getCaughtExceptions().add(exception);
}
}


