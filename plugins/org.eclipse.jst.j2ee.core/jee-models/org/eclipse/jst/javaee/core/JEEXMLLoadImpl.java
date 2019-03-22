/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core;

import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.impl.XMLLoadImpl;
import org.xml.sax.helpers.DefaultHandler;

public class JEEXMLLoadImpl extends XMLLoadImpl {

	@Override
	protected DefaultHandler makeDefaultHandler() {
		
		 return new JEESAXXMLHandler(resource, helper, options);
	}

	public JEEXMLLoadImpl(XMLHelper helper) {
		super(helper);
	}

}
