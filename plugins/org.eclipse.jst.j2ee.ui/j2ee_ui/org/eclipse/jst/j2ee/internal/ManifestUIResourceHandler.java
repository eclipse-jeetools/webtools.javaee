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

public final class ManifestUIResourceHandler extends NLS {

	private static final String BUNDLE_NAME = "manifest_ui";//$NON-NLS-1$

	private ManifestUIResourceHandler() {
		// Do not instantiate
	}

	public static String Up_1;
	public static String Down_2;
	public static String Select_All_3;
	public static String Deselect_All_4;
	public static String ERROR_READING_MANIFEST_DIALOG_TITLE;
	public static String SAVE_MANIFEST_WITH_ERROR;
	public static String ERROR_READING_MANIFEST_DIALOG_MESSAGE_EDITOR;
	public static String ERROR_READING_MANIFEST_DIALOG_MESSAGE_PROP_PAGE;
	public static String MANIFEST_PROBLEM_1;
	public static String MANIFEST_PROBLEM_2;
	public static String MANIFEST_PROBLEM_3;
	public static String MANIFEST_PROBLEM_4;
	public static String An_internal_error_occurred_ERROR_;
	public static String Project_name__UI_;
	public static String EAR_Project_Name__UI__UI_;
	public static String Available_dependent_JARs__UI_;
	public static String Manifest_Class_Path__UI_;
	public static String JAR_Module_UI_;
	public static String Project_UI_;
	public static String EAR_Modules;
	public static String EAR_Modules_Desc;
	public static String J2EE_Modules;
	public static String J2EE_Modules_Desc;
	public static String Web_Libraries;
	public static String Web_Libraries_Desc;
	public static String EAR_Module_Dep_Error;
	public static String Jar_Dep_One_Module_Error;
	public static String Web_Lib_Error;
	public static String REDIRECT_TEXT_EDITOR_UI_;
	public static String Error_Checking_Project_Facets;
	public static String WEB_LIB_LIST_DESCRIPTION;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ManifestUIResourceHandler.class);
	}
}