/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

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
 * @since WTP 1.0
 */
public abstract class J2EEComponentImportDataModelProvider extends J2EEArtifactImportDataModelProvider implements IJ2EEModuleImportDataModelProperties{


	public String[] getPropertyNames() {
		return combineProperties(super.getPropertyNames(), new String[]{EXTENDED_IMPORT_FACTORY});
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
			// } else if (propertyName.equals(PROJECT_NAME)) {
			// IProject project = getProject();
			// if (null != project && project.exists()) {
			// String[] requiredNatures = (String[])
			// getJ2EEModuleCreationDataModel().getProjectDataModel().getProperty(ProjectCreationDataModel.PROJECT_NATURES);
			// if (null != requiredNatures) {
			// try {
			// for (int i = 0; i < requiredNatures.length; i++) {
			// if (!project.hasNature(requiredNatures[i])) {
			// return doSet;
			// }
			// }
			// } catch (CoreException e) {
			// return doSet;
			// }
			// IRuntime target = ServerCore.getProjectProperties(project).getRuntimeTarget();
			// if (null != target) {
			// setProperty(SERVER_TARGET_ID, target.getId());
			// }
			// setBooleanProperty(ADD_TO_EAR, false);
			// }
			// }
			// getJ2EEModuleCreationDataModel().setBooleanProperty(J2EEArtifactCreationDataModelOld.IS_ENABLED,
			// null == project || !project.exists());
			// } else if (propertyName.equals(PRESERVE_PROJECT_METADATA)) {
			// JavaProjectCreationDataModel jdm =
			// getJ2EEModuleCreationDataModel().getJavaProjectCreationDataModel();
			// jdm.setBooleanProperty(JavaProjectCreationDataModel.CREATE_SOURCE_FOLDERS,
			// !getBooleanProperty(BINARY));
		}
		return set;
	}

	protected ModuleFile getModuleFile() {
		return (ModuleFile) getArchiveFile();
	}
}