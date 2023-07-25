/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.jst.j2ee.internal.ui.preferences.messages"; //$NON-NLS-1$
	public static String EarModuleDependenciesPropertyPage_0;
	public static String EarModuleDependenciesPropertyPage_1;
	public static String EarModuleDependenciesPropertyPage_2;
	public static String EarModuleDependenciesPropertyPage_3;
	public static String EarModuleDependenciesPropertyPage_LIBDIR;
	public static String EarModuleDependenciesPropertyPage_ERROR_INVALID_LIBDIR;
	public static String EarModuleDependenciesPropertyPage_WARNING_EMPTY_LIB_DIR;
	public static String EarModuleDependencyPageProvider_0;
	public static String WebDependencyPropertyPage_0;
	public static String WebDependencyPropertyPage_1;
	public static String ChildClasspathDependencyDescription;
	public static String ClasspathDependencyFragmentTitle;
	public static String ClasspathDependencyFragmentDescription;
	
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
