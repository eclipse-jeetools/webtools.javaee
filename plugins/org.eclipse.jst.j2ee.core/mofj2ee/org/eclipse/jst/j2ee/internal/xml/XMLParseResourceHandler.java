/*******************************************************************************
 * Copyright (c) 2000, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.xml;

import org.eclipse.osgi.util.NLS;

public final class XMLParseResourceHandler extends NLS {

	private static final String BUNDLE_NAME = "xmlparse";//$NON-NLS-1$

	private XMLParseResourceHandler() {
		// Do not instantiate
	}

	public static String method_invoke_failed_EXC_;
	public static String failed_to_load_EXC_;
	public static String method_not_found_EXC_;
	public static String failed_instantiating_EXC_;
	public static String parse_exception_occured_EXC_;

	static {
		NLS.initializeMessages(BUNDLE_NAME, XMLParseResourceHandler.class);
	}
}