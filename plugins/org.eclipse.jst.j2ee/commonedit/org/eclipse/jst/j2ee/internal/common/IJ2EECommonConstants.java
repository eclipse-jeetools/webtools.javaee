/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.common;


/**
 * @author jialin
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 */
public interface IJ2EECommonConstants {

	//J2EE Preferences
	// J2EE_NAVIGATOR_LINKING_PREFERENCE specifies whether the J2EE Navigator
	// selection should be linked to the active editor.
	// It is a boolean value, and the default is true.
	final static String J2EE_NAVIGATOR_LINKING_PREFERENCE = "org.eclipse.jst.j2ee.ui.preference.j2eeNavigatorLinking"; //$NON-NLS-1$
	final static String J2EE_VERSION_PREFERENCE = "org.eclipse.jst.j2ee.ui.preference.j2eeVersion";//$NON-NLS-1$
	final static String J2EE_VERSION_1_2 = "J2EE_1_2"; //$NON-NLS-1$
	final static String J2EE_VERSION_1_3 = "J2EE_1_3"; //$NON-NLS-1$

	final static String J2EE_VERSION_PREF_LABEL = CommonEditResourceHandler.getString("Select_the_highest_J2EE_version_that_is_to_be_used__UI_"); //$NON-NLS-1$

	final static String SAVE_ON_BINARY_PROJECT_TITLE = CommonEditResourceHandler.getString("SAVE_ON_BINARY_PROJECT_TITLE_UI_"); //$NON-NLS-1$
	final static String SAVE_ON_BINARY_PROJECT_MESSAGE = CommonEditResourceHandler.getString("SAVE_ON_BINARY_PROJECT_MESSAGE_UI_"); //$NON-NLS-1$
}