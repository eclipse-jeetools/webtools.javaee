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
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.util.Set;

import org.eclipse.jst.j2ee.project.facet.JavaUtilityProjectCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class EjbClientProjectCreationDataModelProvider
	extends JavaUtilityProjectCreationDataModelProvider
	implements IEjbClientProjectCreationDataModelProperties{

	public EjbClientProjectCreationDataModelProvider() {
		super();
	}
	
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(EJB_PROJECT_NAME);
		propertyNames.add(CLIENT_URI);
		return propertyNames;
	}
	
	public IDataModelOperation getDefaultOperation() {
		return new EjbClientProjectCreationOperation(model);
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(CLIENT_URI)){
			String projectName = model.getStringProperty(EJB_PROJECT_NAME);
			return projectName + "Client.jar"; //$NON-NLS-1$ 
		}
		if (propertyName.equals(PROJECT_NAME)){
			String projectName = model.getStringProperty(EJB_PROJECT_NAME);
			return projectName + "Client"; //$NON-NLS-1$ 
		}
		return super.getDefaultProperty(propertyName);
	}
	
}
