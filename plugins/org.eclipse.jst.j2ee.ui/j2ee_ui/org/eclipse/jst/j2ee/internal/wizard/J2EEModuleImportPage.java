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

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEModuleImportDataModelProperties;
import org.eclipse.jst.j2ee.project.datamodel.properties.IJ2EEProjectServerTargetDataModelProperties;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


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
	public J2EEModuleImportPage(IDataModel model, String pageName) {
		super(model, pageName);
	}

	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = super.createTopLevelComposite(parent);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(composite, getInfopopID());
		createServerEarAndStandaloneGroup(composite);
		createAnnotationsStandaloneGroup(composite);
		return composite;
	}

	/**
	 * @param composite
	 */
	protected void createAnnotationsStandaloneGroup(Composite composite) {
	}

	/**
	 * @param composite
	 */
	private void createServerEarAndStandaloneGroup(Composite composite) {
		new ServerEarAndStandaloneGroup(composite, getDataModel().getNestedModel(IJ2EEComponentImportDataModelProperties.NESTED_MODEL_J2EE_COMPONENT_CREATION), synchHelper);
	}

	protected String[] getValidationPropertyNames() {
		return new String[]{IJ2EEComponentImportDataModelProperties.FILE_NAME, IJ2EEComponentImportDataModelProperties.PROJECT_NAME, IJ2EEProjectServerTargetDataModelProperties.RUNTIME_TARGET_ID, IJ2EEModuleImportDataModelProperties.EAR_COMPONENT_NAME, IJ2EEModuleImportDataModelProperties.ADD_TO_EAR};
	}

}