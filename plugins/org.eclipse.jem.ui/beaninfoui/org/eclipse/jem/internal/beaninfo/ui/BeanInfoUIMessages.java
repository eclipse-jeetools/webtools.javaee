/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.ui;
/*
 *  $RCSfile: BeanInfoUIMessages.java,v $
 *  $Revision: 1.3 $  $Date: 2005/02/15 23:02:54 $ 
 */

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class BeanInfoUIMessages {

	private static final String BUNDLE_NAME = "org.eclipse.jem.internal.beaninfo.ui.messages";	//$NON-NLS-1$

	public static final String BUI_ERRORTITLE = "Beaninfo_UI_.errortitle"; //$NON-NLS-1$
	public static final String BUI_ERROR = "Beaninfo_UI_.error";	 //$NON-NLS-1$
	
	public static final String BPP_NOJAVAPROJECT = "BeaninfoPropertiesPage_INFO_.nojavaproject"; //$NON-NLS-1$
	public static final String BPP_CLOSEDPROJECT = "BeaninfoPropertiesPage_INFO_.closedproject"; //$NON-NLS-1$
	
	public static final String BPB_ENABLEBEANINFO = "BeaninfoPathsBlock_UI_.enablebeaninfo"; //$NON-NLS-1$
	
	public static final String BPB_SEARCHPATH_LABEL = "BeaninfoPathsBlock_UI_.searchpath.label";	 //$NON-NLS-1$
	public static final String BPB_SEARCHPATH_UP = "BeaninfoPathsBlock_UI_.searchpath.up.button"; //$NON-NLS-1$
	public static final String BPB_SEARCHPATH_DOWN = "BeaninfoPathsBlock_UI_.searchpath.down.button"; //$NON-NLS-1$
	public static final String BPB_SEARCHPATH_REMOVE = "BeaninfoPathsBlock_UI_.searchpath.remove.button";	 //$NON-NLS-1$
	public static final String BPB_SEARCHPATH_ADD = "BeaninfoPathsBlock_UI_.searchpath.add.button"; //$NON-NLS-1$
	public static final String BPB_SEARCHPATH_TAB_ORDER = "BeaninfoPathsBlock_UI_.serachpath.tab.order"; //$NON-NLS-1$
	
	public static final String BPB_SEARCHPATH_MISSINGPACKAGE_FORMAT = "BeaninfoPathsBlock_WARN_.searchpath.missing.path.format"; //$NON-NLS-1$
	public static final String BPB_SEARCHPATH_OPDESC = "BeaninfoPathsBlock_UI_.searchpath.operationdescription"; //$NON-NLS-1$
	
	public static final String BPB_ADDSEARCHPATH_TITLE = "BeaninfoPathsBlock_UI_.addsearchpath.title"; //$NON-NLS-1$
	public static final String BPB_ADDSEARCHPATH_DESC = "BeaninfoPathsBlock_UI_.addsearchpath.description";		 //$NON-NLS-1$
	
	public static final String BPB_SEARCHPATH_MISSING = "BeaninfoPathsBlock_UI_.warning.EntryMissing"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE =
		ResourceBundle.getBundle(BUNDLE_NAME);

	private BeanInfoUIMessages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
