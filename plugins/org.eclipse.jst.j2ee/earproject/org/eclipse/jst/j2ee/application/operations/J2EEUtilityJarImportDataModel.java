/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
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
package org.eclipse.jst.j2ee.application.operations;

import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

public final class J2EEUtilityJarImportDataModel extends J2EEArtifactImportDataModel {

	public static final String EAR_PROJECT = "J2EEUtilityJarImportDataModel.EAR_PROJECT"; //$NON-NLS-1$

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(EAR_PROJECT);
	}

	protected boolean openArchive(String uri) throws OpenFailureException {
		return false;
	}

	protected J2EEArtifactCreationDataModel createJ2EEProjectCreationDataModel() {
		return new J2EEArtifactCreationDataModel() {
			public void initProjectModel() {
				setProjectDataModel(new JavaProjectCreationDataModel());
			}

			public WTPOperation getDefaultOperation() {
				return null;
			}
		};
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean returnVal = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(PRESERVE_PROJECT_METADATA)) {
			JavaProjectCreationDataModel jdm = (JavaProjectCreationDataModel) getJ2eeArtifactCreationDataModel().getProjectDataModel();
			jdm.setBooleanProperty(JavaProjectCreationDataModel.CREATE_SOURCE_FOLDERS, !getBooleanProperty(BINARY));
		}
		return returnVal;
	}

	protected int getType() {
		return 0;
	}

	public WTPOperation getDefaultOperation() {
		return new J2EEUtilityJarImportOperation(this);
	}
}