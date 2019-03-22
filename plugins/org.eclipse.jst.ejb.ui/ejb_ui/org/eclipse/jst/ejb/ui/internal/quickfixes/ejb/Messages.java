/*******************************************************************************
 * Copyright (c) 2010 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.quickfixes.ejb;

import org.eclipse.osgi.util.NLS;


public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jst.ejb.ui.internal.quickfixes.ejb.messages"; //$NON-NLS-1$
	

	private Messages() {
		// Do not instantiate
	}
	
	public static String CREATE_NEW_SESSION_BEAN;
	public static String CREATE_NEW_MDB;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
