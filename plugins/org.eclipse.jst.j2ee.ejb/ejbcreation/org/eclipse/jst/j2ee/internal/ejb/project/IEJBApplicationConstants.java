/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project;


/*
 * The IWebApplicationContents are the list of tags that are usuable for specifying a web
 * application parameters via XML
 * 
 * @see WebApplicationSpecification for the full DTD
 */

public interface IEJBApplicationConstants {

	// EJB Application parameters
	String NODE = "node"; //$NON-NLS-1$
	String PORT = "port"; //$NON-NLS-1$
	String CLASSPATH = "classpath"; //$NON-NLS-1$
	String SOURCE_PATH = "sourcepath"; //$NON-NLS-1$
	String META_PATH = "metapath"; //$NON-NLS-1$
	String PATH = "path"; //$NON-NLS-1$
	String EXTENSION = "extension"; //$NON-NLS-1$
	String ERROR_HANDLER = "ErrorHandler"; //$NON-NLS-1$

	//We always use the same value so there is no need to have a proper setting
	String VIRTUAL_HOST_VALUE = "default_host"; //$NON-NLS-1$
}