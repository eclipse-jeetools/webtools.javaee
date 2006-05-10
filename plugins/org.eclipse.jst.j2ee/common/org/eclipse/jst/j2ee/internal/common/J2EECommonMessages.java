/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Dec 3, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.common;

import org.eclipse.osgi.util.NLS;


/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class J2EECommonMessages extends NLS {
	private static final String BUNDLE_NAME = "j2ee_common";//$NON-NLS-1$

	private J2EECommonMessages() {
		// Do not instantiate
	}

	public static String ERR_SECURITY_ROLE_EMPTY;
	public static String ERR_SECURITY_ROLE_EXIST;
	public static String ERR_JAVA_CLASS_NAME_EMPTY;
	public static String ERR_JAVA_CLASS_NAME_EXIST;
	public static String ERR_JAVA_CLASS_FILE_NAME_EXIST;
	public static String ERR_JAVA_CLASS_FOLDER_NAME_EMPTY;
	public static String ERR_JAVA_CLASS_FOLDER_PROJECT_NOT_OPEN;
	public static String ERR_JAVA_CLASS_NOT_JAVA_PROJECT;
	public static String ERR_JAVA_CLASS_FOLDER_STATIC_PROJECT;
	public static String ERR_JAVA_CLASS_FOLDER_NOT_SOURCE;
	public static String ERR_JAVA_CLASS_NAME_QUALIFIED;
	public static String ERR_JAVA_CLASS_NAME_INVALID;
	public static String ERR_JAVA_CLASS_NAME_WARNING;
	public static String ERR_JAVA_CLASS_SUPERCLASS_NOT_EXIST;
	public static String ERR_JAVA_CLASS_SUPERCLASS_FINAL;
	public static String ERR_JAVA_PACAKGE_NAME_INVALID;
	public static String ERR_JAVA_PACKAGE_NAME_WARNING;
	public static String ERR_BOTH_FINAL_AND_ABSTRACT;
	public static String WebServiceClientGenerator_UI_0;
	public static String ERR_SECURITY_ROLE_LIST_EMPTY;

	public static String ERR_JAVA_CLASS_FOLDER_NOT_EXIST;

	public static String ERR_EMPTY_MODULE_NAME;
	public static String ERR_EMPTY_PROJECT_NAME;
	public static String ERR_PROJECT_NAME_EXISTS;
	public static String ERR_VERSION_NOT_SUPPORTED;
	public static String ERR_NOT_SUPPORTED;
	public static String ERR_JAVA_CLASS_FOLDER_NOT_ABSOLUTE;

	public static String J2EE_MODULE_CLASSPATH_CONTAINER_NAME;
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, J2EECommonMessages.class);
	}

	public static String getResourceString(String key, Object[] args) {
		return NLS.bind(key, args);
	}
}
