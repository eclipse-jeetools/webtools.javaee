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
 * Created on Dec 4, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jst.j2ee.application.internal.operations.J2EEArtifactImportDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleImportDataModel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.help.WorkbenchHelp;


/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public abstract class J2EEModuleImportPage extends J2EEImportPage {
	/**
	 * @param model
	 * @param pageName
	 */
	public J2EEModuleImportPage(J2EEArtifactImportDataModel model, String pageName) {
		super(model, pageName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = super.createTopLevelComposite(parent);
		WorkbenchHelp.setHelp(composite, getInfopopID());
		createServerEarAndStandaloneGroup(composite);
		createAnnotationsStandaloneGroup(composite);
		return composite;
	}

	/**
	 * @param composite
	 */
	protected void createAnnotationsStandaloneGroup(Composite composite) {
		// DO nohing by default
	}

	/**
	 * @param composite
	 */
	private void createServerEarAndStandaloneGroup(Composite composite) {
		//TODO use flexible project stuff
		//new ServerEarAndStandaloneGroup(composite, getJ2EEModuleImportDataModel().getJ2EEModuleCreationDataModel());
	}

	/**
	 *  
	 */
	private J2EEModuleImportDataModel getJ2EEModuleImportDataModel() {
		return (J2EEModuleImportDataModel) model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[]{J2EEArtifactImportDataModel.FILE_NAME, J2EEArtifactImportDataModel.PROJECT_NAME, J2EEArtifactImportDataModel.OVERWRITE_PROJECT, J2EEArtifactImportDataModel.DELETE_BEFORE_OVERWRITE_PROJECT, J2EEModuleImportDataModel.EAR_NAME, J2EEArtifactImportDataModel.SERVER_TARGET_ID, J2EEModuleImportDataModel.ADD_TO_EAR};
	}

}