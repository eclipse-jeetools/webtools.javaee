/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.project.facet;

import java.util.Hashtable;
import java.util.Set;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.wst.common.componentcore.datamodel.FacetInstallDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;

public class JavaFacetInstallDataModelProvider extends FacetInstallDataModelProvider implements IJavaFacetInstallDataModelProperties {

	public JavaFacetInstallDataModelProvider() {
		super();
	}

	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(SOURCE_FOLDER_NAME);
		return propertyNames;
	}

	public Object getDefaultProperty(String propertyName) {
		if (FACET_ID.equals(propertyName)) {
			return IModuleConstants.JST_JAVA;
		} else if (FACET_VERSION.equals(propertyName)) {
		   Hashtable javaOptions = JavaCore.getOptions();
		   String jdtVersion = (String)javaOptions.get(JavaCore.COMPILER_COMPLIANCE);
		   if (jdtVersion.startsWith("1.3")) { //$NON-NLS-1$
		    return JavaFacetUtils.JAVA_13;
		   } else if (jdtVersion.startsWith("1.4")) { //$NON-NLS-1$
		    return JavaFacetUtils.JAVA_14;
		   }
		   return JavaFacetUtils.JAVA_50;			
		} else if (SOURCE_FOLDER_NAME.equals(propertyName)) {
			return "src";
		}
		return super.getDefaultProperty(propertyName);
	}

}
