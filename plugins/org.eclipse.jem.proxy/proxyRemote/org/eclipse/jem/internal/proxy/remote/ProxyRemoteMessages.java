package org.eclipse.jem.internal.proxy.remote;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ProxyRemoteMessages.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:56:10 $ 
 */

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ProxyRemoteMessages {

	private static final String BUNDLE_NAME =
		"org.eclipse.jem.internal.proxy.remote.messages";	//$NON-NLS-1$
	public final static String 
		ERROR_NO_OUTPUT_FOLDER = "NO_OUTPUT_FOLDER_EXC_", //$NON-NLS-1$
		
		VM_TERMINATED = "VM_TERMINATED_INFO_", //$NON-NLS-1$
		VM_TERMINATED_LINE1 = "VM_TERMINATED_LINE1", //$NON-NLS-1$
		VM_TERMINATED_LINE2 = "VM_TERMINATED_LINE2",		 //$NON-NLS-1$
		VM_TERMINATED_LINE3 = "VM_TERMINATED_LINE3", //$NON-NLS-1$
		
		REMOTE_VM_TRACE_HEADER = "REMOTE_VM_TRACE_INFO_", //$NON-NLS-1$
		REMOVE_VM_LOCAL_TRACE_HEADER = "REMOTE_VM_LOCAL_TRACE_INFO_"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE =
		ResourceBundle.getBundle(BUNDLE_NAME);

	private ProxyRemoteMessages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}