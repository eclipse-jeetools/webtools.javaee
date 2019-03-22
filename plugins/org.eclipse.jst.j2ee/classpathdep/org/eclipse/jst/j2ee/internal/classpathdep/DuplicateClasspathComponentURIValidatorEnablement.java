/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.classpathdep;

import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.JavaEEPreferencesInitializer;


public class DuplicateClasspathComponentURIValidatorEnablement {

	/**
	 * This flag is used to control the enablement of the legacy Duplicate Classpath Component URI validation.
	 * The default value is true which enables this validation.
	 * Setting this value to false will disable this validation.
	 */
	private static boolean validateDuplicateClasspathComponentURI;
	private static boolean hasBeenSet = false;
	
	public static void setValidateDuplicateClasspathComponentURI(boolean validate){
		validateDuplicateClasspathComponentURI = validate;
		hasBeenSet = true;
	}
	
	public static boolean shouldValidateDuplicateClasspathComponentURI(){
		if(!hasBeenSet) {
			if(!ClasspathDependencyEnablement.isAllowClasspathComponentDependency()) {
				validateDuplicateClasspathComponentURI = false;
			} else {
				validateDuplicateClasspathComponentURI = J2EEPlugin.getDefault().getJ2EEPreferences().getBoolean(JavaEEPreferencesInitializer.Keys.VALIDATE_DUPLICATE_CLASSPATH_COMPONENT_URI);
			}
			hasBeenSet = true;
		}
		return validateDuplicateClasspathComponentURI;
	}
	
}
