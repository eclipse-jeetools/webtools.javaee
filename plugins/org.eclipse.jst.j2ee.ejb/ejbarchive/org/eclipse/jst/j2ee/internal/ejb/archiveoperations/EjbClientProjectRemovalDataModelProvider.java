/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
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

import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class EjbClientProjectRemovalDataModelProvider
	extends AbstractDataModelProvider
	implements IEjbClientProjectRemovalDataModelProperties{

	public EjbClientProjectRemovalDataModelProvider() {
		super();
	}
	
	@Override
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add( EJB_PROJECT );
		propertyNames.add( EJB_CLIENT_VIEW_PROJECT );
		propertyNames.add( OP_HANDLER );
		return propertyNames;
	}
	
	@Override
	public IDataModelOperation getDefaultOperation() {
		return new EJBClientJARRemovalOperation(model);
	}

	@Override
	public Object getDefaultProperty(String propertyName) {
		return super.getDefaultProperty(propertyName);
	}

//	private IStatus checkForValidProjectName(String aProjectName) {
//		return ResourcesPlugin.getWorkspace().validateName(aProjectName, IResource.PROJECT);
//	}

}
