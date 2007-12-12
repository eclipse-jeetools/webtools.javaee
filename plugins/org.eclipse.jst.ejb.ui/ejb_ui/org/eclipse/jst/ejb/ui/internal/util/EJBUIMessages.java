/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.util;

import org.eclipse.osgi.util.NLS;


public class EJBUIMessages extends NLS {
	
	private static final String BUNDLE_NAME = "ejb_ui";//$NON-NLS-1$

	private EJBUIMessages() {
		// Do not instantiate
	}

	public static String KEY_0;
	public static String KEY_1;
	public static String KEY_2;
	public static String KEY_3;
	public static String KEY_4;
	public static String KEY_5;
	public static String KEY_6;
	public static String KEY_7;
	public static String KEY_8;
	public static String KEY_9;
	public static String KEY_10;
	public static String KEY_11;
	public static String KEY_12;
	public static String KEY_13;
	public static String KEY_14;
	public static String KEY_15;
	public static String KEY_16;
	public static String KEY_17;
	public static String KEY_18;
	public static String KEY_19;
	public static String KEY_20;
	public static String KEY_21;
	public static String KEY_22;
	public static String KEY_23;
	public static String KEY_24;
	public static String KEY_25;
	public static String KEY_26;
	public static String KEY_27;
	public static String KEY_28;
	public static String GroupedEJBJarItemProvider_UI_0;
	public static String GroupedEJBJarItemProvider_UI_1;
	public static String GroupedEJBJarItemProvider_UI_2;
	public static String NewEJBModuleAction_UI_0;
	public static String pageTitle;
	public static String pageDescription;
	public static String configFolderLabel;
	public static String IMAGE_ENTERPRISE_BEAN_WIZ;
	
	//common
	public static String NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC;
	public static String NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC;
	
	//EnterpriseBeans
	public static String NO_EJB_PROJECTS;
	public static String ADD_BEANS_WIZARD_PAGE_TITLE;
	public static String STATE_TYPE_LABEL;
	public static String CREATEBUSSINESINTERFACE;
	public static String REMOTE_BUSSINES_INTERFACE;
	public static String LOCAL_BUSSINES_INTERFACE;
	public static String EJB_NAME;
	public static String CONTAINER;
	public static String BEAN;
	public static String STATELESS;
	public static String STATEFUL;
	public static String chooseInterface;
	public static String addAs;
	public static String HOMECOMPONENTINTERFACE;
	public static String MAPPED_NAME;
	public static String TRANSACTION_TYPE;
	public static String BUSSINESS_INTERFACE;
	public static String ADD_INTERFACES;
	public static String REMOVE_INTERFACES;
	public static String ADD_SESSION_BEAN_WIZARD_PAGE_DESC;
	

	static {
		NLS.initializeMessages(BUNDLE_NAME, EJBUIMessages.class);
	}
	
	public static final String IMAGE_LOAD_ERROR = KEY_0;
	public static final String EJB_PROJECT_WIZ_TITLE = KEY_1;
	public static final String EJB_VERSION_LBL = KEY_2;
	public static final String EJB_PROJECT_MAIN_PG_TITLE = KEY_3;
	public static final String EJB_PROJECT_MAIN_PG_DESC = KEY_4;
	public static final String EJB_IMPORT_MAIN_PG_DESC = KEY_5;
	public static final String EJB_IMPORT_MAIN_PG_TITLE = KEY_6;
	public static final String EJB_JAR_FILE_LBL = KEY_7;
	public static final String EJB_PROJECT_LBL = KEY_8;
	public static final String EJB_EXPORT_MAIN_PG_TITLE = KEY_9;
	public static final String EJB_EXPORT_MAIN_PG_DESC = KEY_10;
	public static final String EJB_Client_Title = KEY_11;
	public static final String EJB_Client_Desc = KEY_12;
	public static final String Client_JAR_URI = KEY_13;
	public static final String Project_name = KEY_14;
	public static final String Project_location = KEY_15;
	public static final String EJB_Project = KEY_16;
	public static final String DELETE_BEAN_ONLY = KEY_17;
	public static final String Delete_Bean_Classes_UI = KEY_18;
	public static final String Delete_Access_Bean = KEY_19;
	public static final String Delete_Deployed_Code = KEY_20;
	public static final String Select_All_UI = KEY_21;
	public static final String Deselect_All_UI = KEY_22;
	public static final String The_selected_Enterprise_Be = KEY_23;
	public static final String Delete = KEY_24;
	public static final String Cannot_delete_Enterprise_B = KEY_25;
	public static final String Cannot_delete_bean = KEY_26;
	public static final String Delete_Enterprise_Beans = KEY_27;
	public static final String Delete_Options_Query = KEY_27;
	public static final String NewEJBModuleAction = NewEJBModuleAction_UI_0;
	public static final String ENTERPRISE_BEAN_WIZ = IMAGE_ENTERPRISE_BEAN_WIZ;
}
