/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.classpathdep;

import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;

/**
 * @deprecated Do not use. The ALLOW_CLASSPATH_DEP preference has been deprecated and its ability to disable dynamic manifest updates will soon be removed.
 */
public class ClasspathDependencyEnablement {

	/**
	 * This flag is used to control the enablement of the Classpath Dependency
	 * functionality.  The default value is true which enables this functionality.
	 * Setting this value to false will disable the functionality.
	 * @deprecated Do not use. The ALLOW_CLASSPATH_DEP preference has been deprecated and its ability to disable dynamic manifest updates will soon be removed.
	 */
	private static boolean allowClasspathComponentDependency = J2EEPlugin.getDefault().getPluginPreferences().getBoolean(J2EEPreferences.Keys.ALLOW_CLASSPATH_DEP);
	
	/**
	 * @param allow
	 * @deprecated Do not use. The ALLOW_CLASSPATH_DEP preference has been deprecated and its ability to disable dynamic manifest updates will soon be removed.
	 */
	public static void setAllowClasspathComponentDependency(boolean allow){
		allowClasspathComponentDependency = allow;
	}
	
	/**
	 * @return
	 * @deprecated Do not use. The ALLOW_CLASSPATH_DEP preference has been deprecated and its ability to disable dynamic manifest updates will soon be removed.
	 */
	public static boolean isAllowClasspathComponentDependency(){
		return allowClasspathComponentDependency;
	}
	
}
