/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;



/*
 * The IConfigurationConstants are the list of tags that are usuable for specifying a web
 * application parameters via XML
 * 
 * @see WebApplicationSpecification for the full DTD
 */

public interface IWebToolingConstants {

	public String TRUE = "true"; //$NON-NLS-1$
	public String FALSE = "false"; //$NON-NLS-1$

	public String HTTP_PATH_PREFIX = "http://"; //$NON-NLS-1$
	public String INDEX_FILE = "index.html"; //$NON-NLS-1$
	public String HTTP_PARAMETER_SEPARATOR = "?"; //$NON-NLS-1$
	public String CONTENT_LENGTH_HEADER_KEY = "Content-Length"; //$NON-NLS-1$

	public String SENTENCE_TERMINATOR = "."; //$NON-NLS-1$

	public String POST_COMMAND = "POST"; //$NON-NLS-1$
	public String GET_COMMAND = "GET"; //$NON-NLS-1$
}