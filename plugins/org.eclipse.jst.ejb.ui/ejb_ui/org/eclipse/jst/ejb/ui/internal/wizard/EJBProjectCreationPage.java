/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.application.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.AnnotationsStandaloneGroup;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleCreationPage;
import org.eclipse.jst.j2ee.internal.wizard.NewProjectGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class EJBProjectCreationPage extends J2EEModuleCreationPage {
	protected Button addClient;
	protected AnnotationsStandaloneGroup annotationsGroup;

	//	private Button addDefaultBean;

	/**
	 * @param model
	 * @param pageName
	 */
	public EJBProjectCreationPage(EJBModuleCreationDataModel model, String pageName) {
		super(model, pageName);
		setTitle(EJBUIMessages.getResourceString(EJBUIMessages.EJB_PROJECT_MAIN_PG_TITLE));
		setDescription(EJBUIMessages.getResourceString(EJBUIMessages.EJB_PROJECT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EJB_PROJECT_WIZARD_BANNER));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ui.wizard.J2EEModuleCreationPage#addToAdvancedComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected void addToAdvancedComposite(Composite advanced) {
		super.addToAdvancedComposite(advanced);
		createEJBClientGroup(advanced);

		createAnnotationsGroup(advanced);
		//		createDefaultBean(advanced);
	}

	/**
	 * @param advanced
	 */
	//	private void createDefaultBean(Composite advanced) {
	//		addDefaultBean = new Button(advanced, SWT.CHECK);
	//		addDefaultBean.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.CREATE_DEFAULT_SESSION_BEAN));
	//		synchHelper.synchCheckbox(addDefaultBean,
	// EJBModuleCreationDataModel.CREATE_DEFAULT_SESSION_BEAN, null);
	//		GridData gd1 = new GridData(GridData.FILL_HORIZONTAL);
	//		gd1.horizontalSpan = 3;
	//		addDefaultBean.setLayoutData(gd1);
	//
	//	}
	/**
	 * @param advanced
	 */
	private void createAnnotationsGroup(Composite parent) {
		annotationsGroup = new AnnotationsStandaloneGroup(parent, getJ2EEModuleCreationDataModel(), false);
	}

	/**
	 * @param composite
	 */
	private void createEJBClientGroup(Composite parent) {
		// Create Add Client checkbox
		new Label(parent, SWT.NONE);
		addClient = new Button(parent, SWT.CHECK);
		addClient.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.CREATE_EJB_CLIENT_JAR));
		synchHelper.synchCheckbox(addClient, EJBModuleCreationDataModel.CREATE_CLIENT, null);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		addClient.setLayoutData(gd);
	}

	protected String getVersionLabel() {
		return EJBUIMessages.getResourceString(EJBUIMessages.EJB_VERSION_LBL);
	}

	protected void createProjectNameGroup(Composite parent) {
		projectNameGroup = new NewProjectGroup(parent, SWT.NULL, super.getJ2EEProjectCreationDataModel().getProjectDataModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ui.wizard.J2EEModuleCreationPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		String[] names = super.getValidationPropertyNames();
		String[] allNames = new String[names.length + 2];
		System.arraycopy(names, 0, allNames, 0, names.length);
		allNames[names.length] = EJBModuleCreationDataModel.CREATE_CLIENT;
		allNames[names.length + 1] = IAnnotationsDataModel.USE_ANNOTATIONS;
		return allNames;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ui.wizard.J2EEProjectCreationPage#dispose()
	 */
	public void dispose() {
		super.dispose();
		if (annotationsGroup != null)
			annotationsGroup.dispose();
	}

	public void storeDefaultSettings() {
		super.storeDefaultSettings();
		IDialogSettings settings = getDialogSettings();
		if (settings != null)
			settings.put(getAddDefaultBeanKey(), model.getBooleanProperty(EJBModuleCreationDataModel.CREATE_DEFAULT_SESSION_BEAN));
	}


	protected String getAddDefaultBeanKey() {
		return getClass().getName() + "_ADD_DEFAULT_BEAN"; //$NON-NLS-1$
	}

	protected void restoreDefaultSettings() {
		super.restoreDefaultSettings();
		IDialogSettings settings = getDialogSettings();
		if (settings != null)
			model.setBooleanProperty(EJBModuleCreationDataModel.CREATE_DEFAULT_SESSION_BEAN, settings.getBoolean(getAddDefaultBeanKey()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ui.wizard.J2EEProjectCreationPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		setInfopopID(IJ2EEUIContextIds.NEW_EJB_WIZARD_P1);
		return super.createTopLevelComposite(parent);
	}

}