/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.plugin;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.wst.common.frameworks.internal.Messages;


public class J2EEUIMessages extends Messages {

	public static final String PROJECT_LOC_LBL = "1"; //$NON-NLS-1$
	public static final String TARGET_SERVER_LBL = "2"; //$NON-NLS-1$
	public static final String J2EE_VERSION_LBL = "3"; //$NON-NLS-1$
	public static final String IMAGE_LOAD_ERROR = "4"; //$NON-NLS-1$
	public static final String APP_PROJECT_WIZ_TITLE = "5"; //$NON-NLS-1$
	public static final String APP_PROJECT_MAIN_PG_TITLE = "6"; //$NON-NLS-1$
	public static final String APP_PROJECT_MAIN_PG_DESC = "7"; //$NON-NLS-1$
	public static final String APP_PROJECT_MODULES_PG_TITLE = "8"; //$NON-NLS-1$
	public static final String APP_PROJECT_MODULES_PG_DESC = "9"; //$NON-NLS-1$
	public static final String APP_PROJECT_MODULES_PG_SELECT = "10"; //$NON-NLS-1$
	public static final String APP_PROJECT_MODULES_PG_DESELECT = "11"; //$NON-NLS-1$
	public static final String APP_PROJECT_MODULES_PG_NEW = "12"; //$NON-NLS-1$
	public static final String EAR_PROJECT_FOR_MODULE_CREATION = "13"; //$NON-NLS-1$
	public static final String NEW_THREE_DOTS_E = "14"; //$NON-NLS-1$
	public static final String NEW_THREE_DOTS_W = "14a"; //$NON-NLS-1$
	public static final String LINK_MODULETO_EAR_PROJECT = "15"; //$NON-NLS-1$
	public static final String NEW_MOD_SEL_PG_TITLE = "16"; //$NON-NLS-1$
	public static final String NEW_MOD_SEL_PG_DESC = "17"; //$NON-NLS-1$
	public static final String NEW_MOD_WIZ_TITLE = "18"; //$NON-NLS-1$
	public static final String NEW_MOD_SEL_PG_DEF_BTN = "19"; //$NON-NLS-1$
	public static final String APP_CLIENT_PROJ_LBL = "20"; //$NON-NLS-1$
	public static final String EJB_PROJ_LBL = "21"; //$NON-NLS-1$
	public static final String WEB_PROJ_LBL = "22"; //$NON-NLS-1$
	public static final String JCA_PROJ_LBL = "23"; //$NON-NLS-1$

	public static final String APP_CLIENT_PROJECT_WIZ_TITLE = "24"; //$NON-NLS-1$
	public static final String APP_CLIENT_VERSION_LBL = "3"; //$NON-NLS-1$
	public static final String APP_CLIENT_PROJECT_MAIN_PG_TITLE = "25"; //$NON-NLS-1$

	public static final String APP_CLIENT_PROJECT_MAIN_PG_DESC = "26"; //$NON-NLS-1$
	public static final String MODULES_DEPENDENCY_PAGE_TITLE = "27"; //$NON-NLS-1$

	public static final String MODULES_DEPENDENCY_PAGE_DESC = "28"; //$NON-NLS-1$
	public static final String MODULES_DEPENDENCY_PAGE_AVAILABLE_JARS = "29"; //$NON-NLS-1$
	public static final String MODULES_DEPENDENCY_PAGE_CLASSPATH = "30"; //$NON-NLS-1$
	public static final String MODULES_DEPENDENCY_PAGE_TABLE_MODULE = "31"; //$NON-NLS-1$
	public static final String MODULES_DEPENDENCY_PAGE_TABLE_PROJECT = "32"; //$NON-NLS-1$
	public static final String NEW_LBL = "33"; //$NON-NLS-1$
	public static final String APP_CLIENT_IMPORT_MAIN_PG_DESC = "34"; //$NON-NLS-1$
	public static final String APP_CLIENT_IMPORT_MAIN_PG_TITLE = "35"; //$NON-NLS-1$
	public static final String APP_CLIENT_IMPORT_FILE_LABEL = "36"; //$NON-NLS-1$

	public static final String APP_CLIENT_IMPORT_PROJECT_LABEL = "37"; //$NON-NLS-1$

	public static final String IMPORT_WIZ_TITLE = "38"; //$NON-NLS-1$
	public static final String EAR_IMPORT_MAIN_PG_DESC = "39"; //$NON-NLS-1$
	public static final String EAR_IMPORT_MAIN_PG_TITLE = "40"; //$NON-NLS-1$
	public static final String EAR_IMPORT_FILE_LABEL = "41"; //$NON-NLS-1$
	public static final String OVERWRITE_RESOURCES = "42"; //$NON-NLS-1$
	public static final String EAR_IMPORT_PROJECT_TYPE = "43"; //$NON-NLS-1$
	public static final String EAR_IMPORT_PROJECT_TYPE_BINARY = "44"; //$NON-NLS-1$
	public static final String EAR_IMPORT_PROJECT_TYPE_SOURCE = "45"; //$NON-NLS-1$
	public static final String EAR_IMPORT_PARTIAL_DEVELOPMENT = "46"; //$NON-NLS-1$
	public static final String EAR_IMPORT_DESELECT_ALL_UTIL_BUTTON = "48"; //$NON-NLS-1$
	public static final String EAR_IMPORT_SELECT_ALL_UTIL_BUTTON = "47"; //$NON-NLS-1$
	public static final String EAR_IMPORT_JARS_GROUP = "49"; //$NON-NLS-1$
	public static final String EAR_IMPORT_SELECT_UTIL_JARS_TO_BE_PROJECTS = "50"; //$NON-NLS-1$
	public static final String EAR_IMPORT_PROJECT_PG_DESC = "51"; //$NON-NLS-1$
	public static final String EAR_IMPORT_PROJECT_PG_TITLE = "52"; //$NON-NLS-1$
	public static final String PROJECT_LOCATIONS_GROUP = "53"; //$NON-NLS-1$
	public static final String NEW_PROJECT_GROUP_DESCRIPTION = "54"; //$NON-NLS-1$
	public static final String USE_DEFAULT_ROOT_RADIO = "55"; //$NON-NLS-1$
	public static final String USE_ALTERNATE_ROOT_RADIO = "56"; //$NON-NLS-1$
	public static final String SELECT_DIRECTORY_DLG = "57"; //$NON-NLS-1$
	public static final String EAR_IMPORT_Modules_in_EAR = "58"; //$NON-NLS-1$
	public static final String EAR_IMPORT_New_Project_Name = "59"; //$NON-NLS-1$
	public static final String EAR_IMPORT_FILENAMES = "60"; //$NON-NLS-1$
	public static final String EAR_IMPORT_PROJECTNAMES = "61"; //$NON-NLS-1$
	public static final String J2EE_EXPORT_DESTINATION = "62"; //$NON-NLS-1$
	public static final String J2EE_EXPORT_OVERWRITE_CHECKBOX = "63"; //$NON-NLS-1$
	public static final String J2EE_EXPORT_SOURCE_CHECKBOX = "64"; //$NON-NLS-1$
	public static final String APP_CLIENT_EXPORT_MAIN_PG_TITLE = "65"; //$NON-NLS-1$
	public static final String APP_CLIENT_EXPORT_MAIN_PG_DESC = "66"; //$NON-NLS-1$
	public static final String EXPORT_WIZ_TITLE = "67"; //$NON-NLS-1$
	public static final String EAR_EXPORT_MAIN_PG_TITLE = "68"; //$NON-NLS-1$
	public static final String EAR_EXPORT_MAIN_PG_DESC = "69"; //$NON-NLS-1$
	public static final String EAR_EXPORT_INCLUDE_PROJECT_FILES = "70"; //$NON-NLS-1$
	public static final String EAR_EXPORT_INCLUDE_PROJECT_FILES_DESC = "71"; //$NON-NLS-1$
	public static final String EAR_IMPORT_INCLUDE_PROJECT = "72"; //$NON-NLS-1$
	public static final String EAR_IMPORT_INCLUDE_EAR_PROJECT = "73"; //$NON-NLS-1$
	public static final String EAR_IMPORT_OVERWRITE_NESTED = "74"; //$NON-NLS-1$
	public static final String DELETE_PROJECT = "75"; //$NON-NLS-1$
	public static final String EAR_IMPORT_PROJECT_LABEL = "76"; //$NON-NLS-1$

	//string for migration
	public static final String ERROR_OCCURRED_TITLE = "77"; //$NON-NLS-1$
	public static final String ERROR_OCCURRED_MESSAGE = "78"; //$NON-NLS-1$
	public static final String BINARY_PROJECT = "79"; //$NON-NLS-1$
	public static final String ACTION_CANNOT_BE_PERFORMED_ON_BIN_PROJECT = "80"; //$NON-NLS-1$
	public static final String INFORMATION_UI_ = "81"; //$NON-NLS-1$
	public static final String CHOSEN_OP_NOT_AVAILABLE = "82"; //$NON-NLS-1$

	public static final String CREATE_EJB_CLIENT_JAR = "90"; //$NON-NLS-1$
	public static final String USE_ANNOTATIONS = "91"; //$NON-NLS-1$
	public static final String USE_ANNOTATIONS_SERVLET = "98"; //$NON-NLS-1$
	public static final String ADD_ANNOTATIONS_SUPPORT = "92"; //$NON-NLS-1$
	public static final String BROWSE_LABEL = "93"; //$NON-NLS-1$
	public static final String NAME_LABEL = "94"; //$NON-NLS-1$

	public static final String APP_CLIENT_CREATE_MAIN = "95"; //$NON-NLS-1$
	public static final String CREATE_DEFAULT_SESSION_BEAN = "96"; //$NON-NLS-1$

	public static final String J2EE_UTILITY_JAR_LISTEAR_IMPORT_SELECT_UTIL_JARS_TO_BE_PROJECTS = "97"; //$NON-NLS-1$


	private static final J2EEUIMessages INSTANCE = new J2EEUIMessages();


	/**
	 * Returns the string from the resource bundle, or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		return INSTANCE.doGetResourceString(key);
	}

	public static String getResourceString(String key, Object[] args) {
		return INSTANCE.doGetResourceString(key, args);
	}

	private J2EEUIMessages() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.Messages#initializeBundle()
	 */
	protected void initializeBundle() {
		try {
			resourceBundle = ResourceBundle.getBundle("j2ee_ui"); //$NON-NLS-1$
		} catch (MissingResourceException x) {
		}
	}

}