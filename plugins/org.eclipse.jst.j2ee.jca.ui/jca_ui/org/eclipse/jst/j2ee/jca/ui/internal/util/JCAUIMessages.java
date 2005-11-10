/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.jca.ui.internal.util;

import org.eclipse.osgi.util.NLS;


public class JCAUIMessages extends NLS {

	private static final String BUNDLE_NAME = "jca_ui";//$NON-NLS-1$

	private JCAUIMessages() {
		// Do not instantiate
	}

	public static String connector_export_action_label_ui_;
	public static String _9;
	public static String _8;
	public static String _7;
	public static String _6;
	public static String _5;
	public static String _4;
	public static String _3;
	public static String _2;
	public static String _10;
	public static String _1;
	public static String _0;

	static {
		NLS.initializeMessages(BUNDLE_NAME, JCAUIMessages.class);
	}

	public static final String IMAGE_LOAD_ERROR = _0;
	public static final String JCA_MODULE_WIZ_TITLE = _1;
	public static final String JCA_VERSION_LBL = _2;
	public static final String JCA_MODULE_MAIN_PG_TITLE = _3;
	public static final String JCA_MODULE_MAIN_PG_DESC = _4;
	public static final String JCA_IMPORT_MAIN_PG_DESC = _5;
	public static final String JCA_IMPORT_MAIN_PG_TITLE = _6;
	public static final String JCA_FILE_LBL = _7;
	public static final String JCA_MODULE_LBL = _8;
	public static final String JCA_EXPORT_MAIN_PG_TITLE = _9;
	public static final String JCA_EXPORT_MAIN_PG_DESC = _10;
}