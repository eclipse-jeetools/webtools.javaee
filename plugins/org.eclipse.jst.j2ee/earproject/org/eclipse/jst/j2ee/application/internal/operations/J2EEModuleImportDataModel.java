/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.ExtendedImportFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;

/**
 * This dataModel is a common super class used to import J2EE Modules.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public abstract class J2EEModuleImportDataModel extends J2EEArtifactImportDataModel {

	/**
	 * nested
	 */
	public static final String EAR_PROJECT = J2EEModuleCreationDataModelOld.EAR_PROJECT_NAME;
	public static final String ADD_TO_EAR = J2EEModuleCreationDataModelOld.ADD_TO_EAR;

	public static final String EXTENDED_IMPORT_FACTORY = "J2EEModuleImportDataModel.EXTENDED_IMPORT_FACTORY"; //$NON-NLS-1$

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(EXTENDED_IMPORT_FACTORY);
	}

	private int getModuleSpecVersion() {
		if (!isSet(EXTENDED_IMPORT_FACTORY)) {
			return ArchiveUtil.getFastSpecVersion(getModuleFile());
		}
		ExtendedImportFactory factory = (ExtendedImportFactory) getProperty(EXTENDED_IMPORT_FACTORY);
		return factory.getSpecVersion(getModuleFile());
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		if (propertyName.equals(PROJECT_NAME)) {
			IProject project = ProjectCreationDataModel.getProjectHandleFromProjectName((String) propertyValue);
			getJ2EEModuleCreationDataModel().setBooleanProperty(J2EEArtifactCreationDataModelOld.IS_ENABLED, null == project || !project.exists());
		}
		boolean doSet = super.doSetProperty(propertyName, propertyValue);
		if (doSet && (propertyName.equals(J2EEArtifactImportDataModel.FILE_NAME) || (propertyName.equals(J2EEArtifactImportDataModel.FILE)))) {
			J2EEModuleCreationDataModelOld moduleDM = getJ2EEModuleCreationDataModel();
			if (getModuleFile() != null) {
				moduleDM.setIntProperty(J2EEModuleCreationDataModelOld.J2EE_VERSION, getModuleSpecVersion());
				notifyValidValuesChange(PROJECT_NAME);
			}
		} else if (propertyName.equals(PROJECT_NAME)) {
			IProject project = getProject();
			if (null != project && project.exists()) {
				String[] requiredNatures = (String[]) getJ2EEModuleCreationDataModel().getProjectDataModel().getProperty(ProjectCreationDataModel.PROJECT_NATURES);
				if (null != requiredNatures) {
					try {
						for (int i = 0; i < requiredNatures.length; i++) {
							if (!project.hasNature(requiredNatures[i])) {
								return doSet;
							}
						}
					} catch (CoreException e) {
						return doSet;
					}
					IRuntime target = ServerCore.getProjectProperties(project).getRuntimeTarget();
					if (null != target) {
						setProperty(SERVER_TARGET_ID, target.getId());
					}
					setBooleanProperty(ADD_TO_EAR, false);
				}
			}
			getJ2EEModuleCreationDataModel().setBooleanProperty(J2EEArtifactCreationDataModelOld.IS_ENABLED, null == project || !project.exists());
		} else if (propertyName.equals(PRESERVE_PROJECT_METADATA)) {
			JavaProjectCreationDataModel jdm = getJ2EEModuleCreationDataModel().getJavaProjectCreationDataModel();
			jdm.setBooleanProperty(JavaProjectCreationDataModel.CREATE_SOURCE_FOLDERS, !getBooleanProperty(BINARY));
		}
		return doSet;
	}

	public final J2EEModuleCreationDataModelOld getJ2EEModuleCreationDataModel() {
		return (J2EEModuleCreationDataModelOld) getJ2eeArtifactCreationDataModel();
	}

	protected final int getJ2EEVersion() {
		return getJ2EEModuleCreationDataModel().getJ2EEVersion();
	}
}