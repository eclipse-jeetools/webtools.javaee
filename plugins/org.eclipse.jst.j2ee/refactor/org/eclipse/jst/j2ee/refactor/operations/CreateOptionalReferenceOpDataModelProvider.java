/*******************************************************************************
 * Copyright (c) 2006 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * rfrost@bea.com - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.refactor.operations;

import java.util.Set;

import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class CreateOptionalReferenceOpDataModelProvider extends	CreateReferenceComponentsDataModelProvider {

	/**
	 * Property that holds a Boolen which controls whether the component reference is
	 * added/removed.
	 */
	public static final String CREATE_COMPONENT_REF = "IOptionalReferenceProperties.CREATE_COMPONENT_REF"; //$NON-NLS-1$
	/**
	 * Property that holds a Boolen which controls whether the .project reference is
	 * added/removed.
	 */
	public static final String CREATE_PROJECT_REF = "IOptionalReferenceProperties.CREATE_PROJECT_REF"; //$NON-NLS-1$

	
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#getDefaultOperation()
	 */
	@Override
	public IDataModelOperation getDefaultOperation() {
		return new CreateOptionalReferenceOp(model);
	}
	
	@Override
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(CREATE_COMPONENT_REF);
		propertyNames.add(CREATE_PROJECT_REF);
		return propertyNames;
	}

	@Override
	public Object getDefaultProperty(String propertyName) {
		if (CREATE_COMPONENT_REF.equals(propertyName) 
				||CREATE_PROJECT_REF.equals(propertyName)) {
			return Boolean.TRUE;
		} 
		return super.getDefaultProperty(propertyName);
	}
}
