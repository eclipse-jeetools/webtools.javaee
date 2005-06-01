/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.jdt.internal.integration;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

/**
 * This dataModel is a common super class used for creation of WTP Components.
 * 
 * This class (and all its fields and methods) is likely to change during the
 * WTP 1.0 milestones as the new project structures are adopted. Use at your own
 * risk.
 * 
 * @since WTP 1.0
 */
public  class JavaProjectMigrationDataModelProvider extends AbstractDataModelProvider implements IJavaProjectMigrationDataModelProperties{

	public void init() {
		super.init();
	}

	public String[] getPropertyNames() {
		return new String[]{PROJECT_NAME};
	}

	public void propertyChanged(DataModelEvent event) {
		if (event.getFlag() == DataModelEvent.VALUE_CHG) {
			event.getDataModel();
		}
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		return true;
	}

	public Object getDefaultProperty(String propertyName) {
		return super.getDefaultProperty(propertyName);
	}

	public IStatus validate(String propertyName) {

		return OK_STATUS;
	}
	
	public IDataModelOperation getDefaultOperation(){
		return new JavaProjectMigrationOperation(model);
	}

}
