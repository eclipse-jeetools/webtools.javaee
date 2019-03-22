/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.jst.j2ee.datamodel.properties.IJavaUtilityJarImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.UtilityProjectCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public final class J2EEUtilityJarImportDataModelProvider extends J2EEArtifactImportDataModelProvider implements IJavaUtilityJarImportDataModelProperties {

	public static String J2EE_UTILITY_JAR_IMPORT_DMP_ID = "j2eeUtilityJarImportDataModelProvider"; //$NON-NLS-1$

	@Override
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(EAR_PROJECT_NAME);
		return propertyNames;
	}

	@Override
	protected int getType() {
		return 0;
	}

	@Override
	public IDataModelOperation getDefaultOperation() {
		return new J2EEUtilityJarImportOperationNew(model);
	}

	@Override
	protected IDataModel createJ2EEComponentCreationDataModel() {
		return DataModelFactory.createDataModel(new UtilityProjectCreationDataModelProvider());
	}

	@Override
	public String getID() {

		return J2EE_UTILITY_JAR_IMPORT_DMP_ID;
	}
	
	@Override
	public boolean propertySet(String propertyName, Object propertyValue) {
		 if (EAR_PROJECT_NAME.equals(propertyName)) {
			List nestedModels = new ArrayList(model.getNestedModels());
			IDataModel nestedModel = null;
			for (int i = 0; i < nestedModels.size(); i++) {
				nestedModel = (IDataModel) nestedModels.get(i);
				try {
					nestedModel.setProperty(IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME, propertyValue);
					nestedModel.setProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, true);
				} catch (Exception e) {
					J2EEPlugin.logError(e);
				}
			}
			return true;
		} 
		 return super.propertySet(propertyName, propertyValue);
	}
	
	@Override
	protected void refreshInterpretedSpecVersion(){
		updateJavaFacetVersion();
		
	}
}
