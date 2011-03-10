/*******************************************************************************
 * Copyright (c) 2011 Oracle and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Ludovic Champenois ludo@java.net
 *******************************************************************************/


package org.eclipse.jst.j2ee.ejb.internal.operations;

import static org.eclipse.jst.j2ee.ejb.internal.operations.INewEnterpriseBeanClassDataModelProperties.EJB_NAME;

import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaEEArtifactClassOperation;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;


@SuppressWarnings("restriction")
public class AddEjbTimerDataModelProvider extends
		NewEnterpriseBeanClassDataModelProvider {

	public static final String SCHEDULE = "AddEjbTimer.SCHEDULE"; //$NON-NLS-1$

	@Override
	public IDataModelOperation getDefaultOperation() {
		return new AddEnterpriseBeanOperation(getDataModel()) {

			@Override
			protected NewJavaEEArtifactClassOperation getNewClassOperation() {
				return new AddEjbTimerClassOperation(getDataModel());
			}
		};
	}

	/**
	 * Subclasses may extend this method to add their own data model's
	 * properties as valid base properties.
	 * 
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#getPropertyNames()
	 */
	@Override
	public Set<String> getPropertyNames() {
		// Add Bean specific properties defined in this data model
		Set<String> propertyNames = super.getPropertyNames();

		propertyNames.add(SCHEDULE);

		return propertyNames;
	}

	/**
	 * Subclasses may extend this method to provide their own default values for
	 * any of the properties in the data model hierarchy. This method does not
	 * accept a null parameter. It may return null.
	 * 
	 * @see NewJavaClassDataModelProvider#getDefaultProperty(String)
	 * @see IDataModelProvider#getDefaultProperty(String)
	 * 
	 * @param propertyName
	 * @return Object default value of property
	 */
	@Override
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(SCHEDULE)) {
			return EJBCreationResourceHandler.timerScheduleDefault;
		}
		
		// Otherwise check super for default value for property
		return super.getDefaultProperty(propertyName);
	}

	@Override
	public IStatus validate(String propertyName) {
		// we need to override to remove the error condition for EJB_NAME
		if (EJB_NAME.equals(propertyName)){
			return null;
		}

		if (SCHEDULE.equals(propertyName)) {
			String value = (String) getProperty(SCHEDULE);
			if (value == null || value.trim().length() == 0) {
				return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.errorTimerScheduleMissing);
			}
		}
		IStatus status = super.validate(propertyName);
		return status;
	}
}
