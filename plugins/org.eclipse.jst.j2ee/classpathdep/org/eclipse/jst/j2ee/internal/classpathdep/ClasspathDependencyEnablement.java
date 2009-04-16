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

public class ClasspathDependencyEnablement {

	/**
	 * This flag is used to control the enablement of the Classpath Dependency
	 * functionality.  The default value is true which enables this functionality.
	 * Setting this value to false will disable the functionality.
	 */
	private static boolean allowClasspathComponentDependnecy = true;
	
	public static void setAllowClasspathComponentDependency(boolean allow){
		allowClasspathComponentDependnecy = allow;
	}
	
	public static boolean isAllowClasspathComponentDependency(){
		return allowClasspathComponentDependnecy;
	}
	
}
