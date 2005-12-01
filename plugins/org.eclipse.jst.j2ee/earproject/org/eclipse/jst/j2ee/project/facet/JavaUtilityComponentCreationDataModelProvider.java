/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project.facet;


import java.util.Set;

import org.eclipse.jst.j2ee.datamodel.properties.IUtilityJavaComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

/**
 * 
 * @deprecated
 * @see UtilityProjectCreationDataModelProvider
 */

public class JavaUtilityComponentCreationDataModelProvider
	extends JavaComponentCreationDataModelProvider implements IUtilityJavaComponentCreationDataModelProperties{

	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
			propertyNames.add(EAR_PROJECT_NAME);
		return propertyNames;
	}
	
	public IDataModelOperation getDefaultOperation() {
		return new JavaUtilityComponentCreationFacetOperation(model);
	}

}
