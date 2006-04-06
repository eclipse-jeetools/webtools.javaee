/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.servertarget.J2EEProjectServerTargetDataModelProvider;
import org.eclipse.jst.j2ee.project.datamodel.properties.IFlexibleJavaProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.datamodel.properties.IJ2EEProjectServerTargetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.FlexibleProjectCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class FlexibleJavaProjectCreationDataModelProvider extends FlexibleProjectCreationDataModelProvider implements IFlexibleJavaProjectCreationDataModelProperties {

	public FlexibleJavaProjectCreationDataModelProvider() {
		super();
	}

	public void init() {
		super.init();
		IDataModel serverTargetModel = DataModelFactory.createDataModel(new J2EEProjectServerTargetDataModelProvider());
		model.addNestedModel(NESTED_MODEL_SERVER_TARGET, serverTargetModel);
	}

	protected void initNestedProjectModel() {
		IDataModel javaProjModel = DataModelFactory.createDataModel(new JavaProjectCreationDataModelProvider());
		model.addNestedModel(NESTED_MODEL_PROJECT_CREATION, javaProjModel);
	}

	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(NESTED_MODEL_SERVER_TARGET);
		propertyNames.add(ADD_SERVER_TARGET);
		propertyNames.add(RUNTIME_TARGET_ID);
		return propertyNames;
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(ADD_SERVER_TARGET)) {
			return Boolean.TRUE;
		}
		return super.getDefaultProperty(propertyName);
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean status = super.propertySet(propertyName, propertyValue);
		if (PROJECT_NAME.equals(propertyName)) {
			IDataModel projModel = model.getNestedModel(NESTED_MODEL_SERVER_TARGET);
			projModel.setProperty(IJ2EEProjectServerTargetDataModelProperties.PROJECT_NAME, propertyValue);
		} else if (RUNTIME_TARGET_ID.equals(propertyName)) {
			IDataModel projModel = model.getNestedModel(NESTED_MODEL_SERVER_TARGET);
			projModel.setProperty(IJ2EEProjectServerTargetDataModelProperties.RUNTIME_TARGET_ID, propertyValue);
		}
		return status;
	}

	public IDataModelOperation getDefaultOperation() {
		return new FlexibleJavaProjectCreationOperation(model);
	}


	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		if (propertyName.equals(RUNTIME_TARGET_ID)) {
			IDataModel serverTargetModel = model.getNestedModel(NESTED_MODEL_SERVER_TARGET);
			return serverTargetModel.getValidPropertyDescriptors(IJ2EEProjectServerTargetDataModelProperties.RUNTIME_TARGET_ID);
		}
		return null;
	}

	public IStatus validate(String propertyName) {
		IStatus status = super.validate(propertyName);
		if (!status.isOK()) {
			return status;
		} else if (propertyName.equals(NESTED_MODEL_SERVER_TARGET)) {
//			IDataModel stModel = model.getNestedModel(NESTED_MODEL_SERVER_TARGET);
//			return stModel.validate();
		}
		return OK_STATUS;
	}
}
