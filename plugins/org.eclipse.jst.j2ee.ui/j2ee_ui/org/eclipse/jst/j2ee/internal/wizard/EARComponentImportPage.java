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
 * Created on Dec 8, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.earcreation.EarComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.project.datamodel.properties.IJ2EEProjectServerTargetDataModelProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.componentcore.datamodel.properties.IComponentCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class EARComponentImportPage extends J2EEImportPage {
	protected Combo serverTargetCombo;

	/**
	 * @param model
	 * @param pageName
	 */
	public EARComponentImportPage(IDataModel dataModel, String pageName) {
		super(dataModel, pageName);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_IMPORT_MAIN_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_IMPORT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_IMPORT_WIZARD_BANNER));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		setInfopopID(IJ2EEUIContextIds.IMPORT_EAR_WIZARD_P1);
		GridLayout layout = new GridLayout(3, false);
		composite.setLayout(layout);
		createFileNameComposite(composite);
		createProjectNameComposite(composite);
		createAnnotationsStandaloneGroup(composite);
		restoreWidgetValues();
		return composite;
	}

	protected IDataModel getNewProjectCreationDataModel() {
		IDataModel earModel = DataModelFactory.createDataModel(new EarComponentCreationDataModelProvider());
		earModel.setIntProperty(IJ2EEComponentCreationDataModelProperties.COMPONENT_VERSION, model.getIntProperty(IJ2EEComponentCreationDataModelProperties.COMPONENT_VERSION));
		earModel.setProperty(IComponentCreationDataModelProperties.PROJECT_NAME, model.getProperty(IComponentCreationDataModelProperties.PROJECT_NAME));
		earModel.setProperty(IComponentCreationDataModelProperties.COMPONENT_NAME, model.getProperty(IComponentCreationDataModelProperties.COMPONENT_NAME));
		return earModel;
	}

	protected String getProjectImportLabel() {
		return J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_IMPORT_PROJECT_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEImportPage#getFileImportLabel()
	 */
	protected String getFileImportLabel() {
		return J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_IMPORT_FILE_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEImportPage#getFilterExpression()
	 */
	protected String[] getFilterExpression() {
		return new String[]{"*.ear"}; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEModuleImportPage#createAnnotationsStandaloneGroup(org.eclipse.swt.widgets.Composite)
	 */
	protected void createAnnotationsStandaloneGroup(Composite composite) {
		// new AnnotationsStandaloneGroup(composite, model, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[]{IJ2EEComponentImportDataModelProperties.FILE_NAME, IJ2EEComponentImportDataModelProperties.PROJECT_NAME, IJ2EEProjectServerTargetDataModelProperties.RUNTIME_TARGET_ID, IAnnotationsDataModel.USE_ANNOTATIONS};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEImportPage#getFileNamesStoreID()
	 */
	protected String getFileNamesStoreID() {
		return "EAR";//$NON-NLS-1$
	}



}