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
package org.eclipse.jst.j2ee.applicationclient.internal.creation;

import org.eclipse.osgi.util.NLS;

public final class AppClientCreationResourceHandler extends NLS {

	private static final String BUNDLE_NAME = "appclientcreation";//$NON-NLS-1$

	private AppClientCreationResourceHandler() {
		// Do not instantiate
	}

	public static String Error_occured_getting_appl_ERROR_;
	public static String Creating_Application_Clien_UI_;
	public static String Error_occured_loading_appl_UI_;
	public static String Not_an_Application_Client_project_ERROR_;

	static {
		NLS.initializeMessages(BUNDLE_NAME, AppClientCreationResourceHandler.class);
	}
}