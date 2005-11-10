/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal;

import org.eclipse.osgi.util.NLS;

public final class MOFJ2EEResourceHandler extends NLS {

	private static final String BUNDLE_NAME = "mofj2ee";//$NON-NLS-1$

	private MOFJ2EEResourceHandler() {
		// Do not instantiate
	}

	public static String Stack_trace_of_nested_exce;
	public static String ERROR;
	public static String WARNING;
	public static String INFO;
	public static String DEFAULT_COMPLETED_STATUS_MSG;
	public static String DEFAULT_NOT_NEEDED_STATUS_MSG;
	public static String DEFAULT_NOT_POSSIBLE_STATUS_MSG;
	public static String DEFAULT_ERROR_STATUS_MSG;
	public static String REMOVED_LOCAL_CLIENT_MSG;
	public static String REMOVED_ACCESS_INTENTS_MSG;
	public static String REMOVED_ISOLATION_LEVELS_MSG;
	public static String CONVERTED_FINDER_MSG;
	public static String UNNAMED_EJB;
	public static String CONVERTED_QUERY_DESCRIPTION;
	public static String MIGRATED_DEFAULT_DATASOURCE_JAR_MSG;
	public static String MIGRATED_DEFAULT_DATASOURCE_MSG;
	public static String J2EE_VERSION_NULL_ERROR;
	public static String J2EE_VERSION_PROXY_ERROR;

	static {
		NLS.initializeMessages(BUNDLE_NAME, MOFJ2EEResourceHandler.class);
	}

	public static String getString(String key, Object[] args) {
		return NLS.bind(key, args);
	}
}