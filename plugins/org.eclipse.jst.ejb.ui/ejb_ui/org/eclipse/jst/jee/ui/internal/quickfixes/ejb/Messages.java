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
package org.eclipse.jst.jee.ui.internal.quickfixes.ejb;

import org.eclipse.osgi.util.NLS;


public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jst.jee.ui.internal.quickfixes.ejb.messages"; //$NON-NLS-1$
	

	private Messages() {
		// Do not instantiate
	}
	
	public static String CREATE_NEW_SESSION_BEAN;
	public static String CREATE_NEW_MDB;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
