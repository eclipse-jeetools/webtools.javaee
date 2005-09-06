/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.Collection;

import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEModuleImportDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * This dataModel is a common super class used to import J2EE Modules.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @plannedfor WTP 1.0
 */
public abstract class J2EEComponentImportDataModelProvider extends J2EEArtifactImportDataModelProvider implements IJ2EEModuleImportDataModelProperties {


	public Collection getPropertyNames() {
		Collection propertyNames = super.getPropertyNames();
		propertyNames.add(EXTENDED_IMPORT_FACTORY);
		return propertyNames;
	}

	private int getModuleSpecVersion() {
		if (!isPropertySet(EXTENDED_IMPORT_FACTORY)) {
			return ArchiveUtil.getFastSpecVersion(getModuleFile());
		}
		ExtendedImportFactory factory = (ExtendedImportFactory) getProperty(EXTENDED_IMPORT_FACTORY);
		return factory.getSpecVersion(getModuleFile());
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean set = super.propertySet(propertyName, propertyValue);
		if (propertyName.equals(FILE)) {
			IDataModel moduleDM = model.getNestedModel(NESTED_MODEL_J2EE_COMPONENT_CREATION);
			if (getModuleFile() != null) {
				moduleDM.setIntProperty(IJ2EEComponentCreationDataModelProperties.COMPONENT_VERSION, getModuleSpecVersion());
				model.notifyPropertyChange(PROJECT_NAME, IDataModel.VALID_VALUES_CHG);
			}
		}
		return set;
	}

	protected ModuleFile getModuleFile() {
		return (ModuleFile) getArchiveFile();
	}
}