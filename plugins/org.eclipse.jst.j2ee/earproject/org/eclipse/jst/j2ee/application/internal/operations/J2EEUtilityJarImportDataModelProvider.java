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
/*
 * Created on Dec 15, 2003
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.Set;

import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.datamodel.properties.IJavaUtilityJarImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public final class J2EEUtilityJarImportDataModelProvider extends J2EEArtifactImportDataModelProvider implements IJavaUtilityJarImportDataModelProperties {

	public static String J2EE_UTILITY_JAR_IMPORT_DMP_ID = "j2eeUtilityJarImportDataModelProvider";

	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(EAR_COMPONENT_NAME);
		propertyNames.add(EAR_PROJECT_NAME);
		return propertyNames;
	}

	protected Archive openArchive(String uri) throws OpenFailureException {
		return null;
	}

	protected int getType() {
		return 0;
	}

	public IDataModelOperation getDefaultOperation() {
		return new J2EEUtilityJarImportOperationNew(model);
	}

	protected IDataModel createJ2EEComponentCreationDataModel() {
		return DataModelFactory.createDataModel(new JavaComponentCreationDataModelProvider());
	}

	public String getID() {

		return J2EE_UTILITY_JAR_IMPORT_DMP_ID;
	}
}
