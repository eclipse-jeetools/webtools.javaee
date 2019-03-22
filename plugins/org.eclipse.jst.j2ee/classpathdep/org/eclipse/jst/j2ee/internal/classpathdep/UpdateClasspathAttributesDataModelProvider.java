/*******************************************************************************
 * Copyright (c) 2007 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.classpathdep;

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.common.J2EECommonMessages;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * DataModelProvider for the UpdateClasspathAttributesOperation. 
 */
public class UpdateClasspathAttributesDataModelProvider extends AbstractDataModelProvider implements UpdateClasspathAttributesDataModelProperties {

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider#getPropertyNames()
	 */
	@Override
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(PROJECT_NAME);
		propertyNames.add(ENTRIES_WITH_ATTRIBUTE);
		propertyNames.add(ENTRIES_TO_ADD_ATTRIBUTE);
		propertyNames.add(ENTRIES_TO_REMOVE_ATTRIBUTE);
		propertyNames.add(MODIFY_CLASSPATH_COMPONENT_DEPENDENCY);
		return propertyNames;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider#getDefaultProperty(java.lang.String)
	 */
	@Override
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(ENTRIES_WITH_ATTRIBUTE)) {
			return new ArrayList();
		} else if (propertyName.equals(MODIFY_CLASSPATH_COMPONENT_DEPENDENCY)) {
			return Boolean.TRUE;
		}
		return super.getDefaultProperty(propertyName);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider#getDefaultOperation()
	 */
	@Override
	public IDataModelOperation getDefaultOperation() {
		return new UpdateClasspathAttributesOperation(model);
	}
	
	public IProject getProject() {
		return ProjectUtilities.getProject(getStringProperty(PROJECT_NAME));
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider#validate(java.lang.String)
	 */
	@Override
	public IStatus validate(String property) {
		if (property.equals(PROJECT_NAME)) {
			final String projectName = getStringProperty(PROJECT_NAME);
			if (projectName == null) {
				final String msg = J2EECommonMessages.ERR_EMPTY_PROJECT_NAME;
				return WTPCommonPlugin.createErrorStatus(msg);
			}
			
			if (!getProject().isAccessible()) {
				final String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_PROJECT_INACCESSIBLE, new String[]{projectName});
				return WTPCommonPlugin.createErrorStatus(msg);
			}
		} 
		
		return Status.OK_STATUS;
	}
}
