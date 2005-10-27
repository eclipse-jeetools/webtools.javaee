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

import java.util.Set;

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
		}
		return super.getDefaultProperty(propertyName);
	}

}
