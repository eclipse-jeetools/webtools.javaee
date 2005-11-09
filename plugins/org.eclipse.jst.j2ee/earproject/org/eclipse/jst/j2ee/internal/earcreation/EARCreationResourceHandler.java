/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation;



import org.eclipse.osgi.util.NLS;

public class EARCreationResourceHandler extends NLS {

	private static final String BUNDLE_NAME = "earcreation";//$NON-NLS-1$

	private EARCreationResourceHandler() {
		// Do not instantiate
	}

	public static String Error_occured_getting_appl_ERROR_;
	public static String Error_occured_getting_appl1_ERROR_;
	public static String Creating__UI_;
	public static String Creating_EAR_Project_UI_;
	public static String The_project_already_exist_ERROR_;
	public static String EARProjectCreationOperatio_ERROR_;
	public static String ABS_PATH_CHANGED_UI_;
	public static String UNSAVED_CHANGES_WARN_;
	public static String PROJECT_MAP_PROBLEMS_ERROR_;
	public static String MODULE_PROJECTS_CLOSED_WARN_;
	public static String Add_Module_Command_Label_UI_;
	public static String Remove_Module_Command_Label_UI_;

	public static String Set_project_UI_;
	public static String Add_Utility_JAR_UI;
	public static String REM_UTIL_JAR_UI;
	public static String PROJ_DESC_UPDATE_ERROR_UI_;
	public static String Not_an_ear_project_UI_;
	public static String EAR_PROJECT_MUST_BE_OPEN_UI_;
	public static String Updating_absolute_paths_UI_;
	public static String DuplicateModuleNames;
	public static String NoModulesSelected;

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
	public static String J2EEUtilityJarListImportOperation_UI_0;
	public static String J2EEUtilityJarListImportOperation_UI_1;
	public static String J2EEUtilityJarListImportOperation_UI_2;
	public static String J2EEUtilityJarListImportDataModel_0;
	public static String J2EEUtilityJarListImportDataModel_Specify_Valid_Project;
	public static String J2EEUtilityJarListImportDataModel_Select_Jar;
	public static String J2EEUtilityJarListImportDataModel_Specify_Linked_Path;
	public static String EARImportDataModel_UI_0;
	public static String EARImportDataModel_UI_1;
	public static String EARImportDataModel_UI_2;
	public static String EARImportDataModel_UI_3;
	public static String ModuleMappingImpl_UI_0;
	public static String UtilityJARMappingImpl_UI_0;
	public static String importWrongType;
	public static String importWrongVersion;
	public static String EAR_PROJECTNAME_SAMEAS_MODULE;

	static {
		NLS.initializeMessages(BUNDLE_NAME, EARCreationResourceHandler.class);
	}

	public static String getString(String key, Object[] args) {
		return NLS.bind(key, args);
	}

	public static final String ADD_MODULE_MODULE_TYPE = KEY_1;
	public static final String ADD_MODULE_MODULE_EXISTS = KEY_2;
	public static final String ADD_MODULE_MODULE_CLOSED = KEY_3;
	public static final String ADD_MODULE_MODULE_NULL = KEY_4;
	public static final String ADD_PROJECT_URI_EXISTS = KEY_5;
	public static final String ADD_PROJECT_NOT_JAVA = KEY_6;
	public static final String ADD_PROJECT_UTIL_URI = KEY_7;
	public static final String ADD_PROJECT_UTIL_MAPPED = KEY_8;
	public static final String ADD_PROJECT_NOT_EAR = KEY_9;
	public static final String ADD_PROJECT_URI_EMPTY = KEY_10;
	public static final String NOT_AN_APP_CLIENT = KEY_11;
	public static final String NOT_AN_EAR = KEY_12;
	public static final String NOT_AN_EJB = KEY_13;
	public static final String NOT_A_RAR = KEY_14;
	public static final String NOT_A_WAR = KEY_15;
	public static final String SERVER_TARGET_NOT_SUPPORT_EAR = KEY_16;
}