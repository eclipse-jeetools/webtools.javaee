/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.provider;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.wst.common.framework.Messages;


public class EJBUIMessages extends Messages {

	public static final String IMAGE_LOAD_ERROR = "0"; //$NON-NLS-1$
	public static final String EJB_PROJECT_WIZ_TITLE = "1"; //$NON-NLS-1$
	public static final String EJB_VERSION_LBL = "2"; //$NON-NLS-1$
	public static final String EJB_PROJECT_MAIN_PG_TITLE = "3"; //$NON-NLS-1$
	public static final String EJB_PROJECT_MAIN_PG_DESC = "4"; //$NON-NLS-1$
	public static final String EJB_IMPORT_MAIN_PG_DESC = "5"; //$NON-NLS-1$
	public static final String EJB_IMPORT_MAIN_PG_TITLE = "6"; //$NON-NLS-1$
	public static final String EJB_JAR_FILE_LBL = "7"; //$NON-NLS-1$
	public static final String EJB_PROJECT_LBL = "8"; //$NON-NLS-1$
	public static final String EJB_EXPORT_MAIN_PG_TITLE = "9"; //$NON-NLS-1$
	public static final String EJB_EXPORT_MAIN_PG_DESC = "10"; //$NON-NLS-1$
	public static final String EJB_Client_Title = "11"; //$NON-NLS-1$
	public static final String EJB_Client_Desc = "12"; //$NON-NLS-1$
	public static final String Client_JAR_URI = "13"; //$NON-NLS-1$
	public static final String Project_name = "14"; //$NON-NLS-1$
	public static final String Project_location = "15"; //$NON-NLS-1$
	public static final String EJB_Project = "16"; //$NON-NLS-1$
	public static final String DELETE_BEAN_ONLY = "17"; //$NON-NLS-1$
	public static final String Delete_Bean_Classes_UI = "18"; //$NON-NLS-1$
	public static final String Delete_Access_Bean = "19"; //$NON-NLS-1$
	public static final String Delete_Deployed_Code = "20"; //$NON-NLS-1$
	public static final String Select_All_UI = "21"; //$NON-NLS-1$
	public static final String Deselect_All_UI = "22"; //$NON-NLS-1$
	public static final String The_selected_Enterprise_Be = "23"; //$NON-NLS-1$
	public static final String Delete = "24"; //$NON-NLS-1$
	public static final String Cannot_delete_Enterprise_B = "25"; //$NON-NLS-1$
	public static final String Cannot_delete_bean = "26"; //$NON-NLS-1$
	public static final String Delete_Enterprise_Beans = "27"; //$NON-NLS-1$
	public static final String Delete_Options_Query = "27"; //$NON-NLS-1$

	private static EJBUIMessages INSTANCE = new EJBUIMessages();

	/**
	 * The constructor.
	 */
	private EJBUIMessages() {
		super();
	}

	/**
	 * Returns the string from the resource bundle, or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		return INSTANCE.doGetResourceString(key);
	}

	public static String getResourceString(String key, Object[] args) {
		return INSTANCE.doGetResourceString(key, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.Messages#initializeBundle()
	 */
	protected void initializeBundle() {
		try {
			resourceBundle = ResourceBundle.getBundle("j2ee_ejb_ui"); //$NON-NLS-1$
		} catch (MissingResourceException x) {
		}
	}

}