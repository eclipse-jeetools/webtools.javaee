/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project.facet;

import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

/**
 * This dataModel is a common super class used for creation of WTP Components.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @plannedfor WTP 1.0
 */
public class JavaProjectMigrationDataModelProvider extends AbstractDataModelProvider implements IJavaProjectMigrationDataModelProperties {

	@Override
	public void init() {
		super.init();
	}

	@Override
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(PROJECT_NAME);
		propertyNames.add(ADD_TO_EAR);
		return propertyNames;
	}

	public void propertyChanged(DataModelEvent event) {
		if (event.getFlag() == DataModelEvent.VALUE_CHG) {
			event.getDataModel();
		}
	}

	@Override
	public boolean propertySet(String propertyName, Object propertyValue) {
		return true;
	}

	@Override
	public Object getDefaultProperty(String propertyName) {
		return super.getDefaultProperty(propertyName);
	}

	@Override
	public IStatus validate(String propertyName) {

		return OK_STATUS;
	}

	@Override
	public IDataModelOperation getDefaultOperation() {
		return new JavaProjectMigrationOperation(model);
	}

}
