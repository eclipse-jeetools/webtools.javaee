/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.jdt.internal.integration;

import java.util.Set;

import org.eclipse.jst.common.project.facet.JavaProjectFacetCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.internal.DoNotUseMeThisWillBeDeletedPost15;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModelProvider;

/**
 * This has been slated for removal post WTP 1.5. Do not use this class/interface
 * 
 * @deprecated
 * 
 * @see JavaProjectFacetCreationDataModelProvider
 */
public class JavaProjectCreationDataModelProvider extends ProjectCreationDataModelProvider implements IJavaProjectCreationProperties, DoNotUseMeThisWillBeDeletedPost15 {


	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(OUTPUT_LOCATION);
		propertyNames.add(SOURCE_FOLDERS);
		propertyNames.add(CLASSPATH_ENTRIES);
		propertyNames.add(CREATE_SOURCE_FOLDERS);
		return propertyNames;
	}

	public IDataModelOperation getDefaultOperation() {
		return new JavaProjectCreationOperation(model);
	}

	public Object getDefaultProperty(String propertyName) {
		// TODO pull these from the java preferences
		if (propertyName.equals(OUTPUT_LOCATION)) {
			return "bin"; //$NON-NLS-1$
		}
		if (propertyName.equals(SOURCE_FOLDERS)) {
			return new String[0];
		}
		if (propertyName.equals(CREATE_SOURCE_FOLDERS))
			return Boolean.TRUE;
		return null;
	}

	
}