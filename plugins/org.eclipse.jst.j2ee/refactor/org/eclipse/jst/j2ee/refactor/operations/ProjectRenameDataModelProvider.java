/*******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
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

import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class ProjectRenameDataModelProvider extends	ProjectRefactoringDataModelProvider{
	
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#getPropertyNames()
	 */
	@Override
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
        propertyNames.add(ORIGINAL_PROJECT_METADATA);
        propertyNames.add(RESOURCE_DELTA);
		return propertyNames;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#getDefaultOperation()
	 */
	@Override
	public IDataModelOperation getDefaultOperation() {
		return new ProjectRenameOperation(model);
	}
}
