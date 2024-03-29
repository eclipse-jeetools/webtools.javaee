/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.provider;

import org.eclipse.osgi.util.NLS;


public class EJBUIMessages extends NLS {

	private static final String BUNDLE_NAME = "j2ee_ejb_ui";//$NON-NLS-1$

	private EJBUIMessages() {
		// Do not instantiate
	}

	public static String GroupedEJBJarItemProvider_UI_0;
	public static String GroupedEJBJarItemProvider_UI_1;
	public static String GroupedEJBJarItemProvider_UI_2;

	static {
		NLS.initializeMessages(BUNDLE_NAME, EJBUIMessages.class);
	}
}
