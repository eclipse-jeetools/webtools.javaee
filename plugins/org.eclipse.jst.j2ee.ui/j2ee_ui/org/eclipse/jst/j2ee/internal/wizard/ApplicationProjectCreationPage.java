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
 * Created on Oct 31, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jst.j2ee.application.operations.J2EEApplicationCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEArtifactCreationDataModel;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class ApplicationProjectCreationPage extends J2EEProjectCreationPage {

	protected Combo serverTargetCombo;

	/**
	 * @param model
	 * @param pageName
	 */
	protected ApplicationProjectCreationPage(J2EEApplicationCreationDataModel model, String pageName) {
		super(model, pageName);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_PROJECT_MAIN_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_PROJECT_MAIN_PG_DESC));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEProjectCreationPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		setInfopopID(IJ2EEUIContextIds.NEW_EAR_WIZARD_P1);
		return super.createTopLevelComposite(parent);
	}

	/**
	 * @param composite
	 */
	protected void createVersionComposite(Composite parent) {
		String label = J2EEUIMessages.getResourceString(J2EEUIMessages.J2EE_VERSION_LBL);
		createVersionComposite(parent, label, J2EEApplicationCreationDataModel.APPLICATION_VERSION);
	}

	protected void createServerTargetComposite(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.TARGET_SERVER_LBL));
		serverTargetCombo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
		serverTargetCombo.setLayoutData((new GridData(GridData.FILL_HORIZONTAL)));
		//        if (!org.eclipse.jst.j2ee.internal.internal.plugin.J2EEPlugin.hasDevelopmentRole()) {
		//            serverTargetCombo.setEnabled(false);
		//        }
		Button newServerTargetButton = new Button(parent, SWT.NONE);
		newServerTargetButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		newServerTargetButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_THREE_DOTS_E));
		newServerTargetButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				J2EEProjectCreationPage.launchNewRuntimeWizard(getShell(), ((J2EEApplicationCreationDataModel) model).getServerTargetDataModel());
			}
		});
		Control[] deps = new Control[]{label, newServerTargetButton};
		synchHelper.synchCombo(serverTargetCombo, ServerTargetDataModel.RUNTIME_TARGET_ID, deps);
	}

	protected void addToAdvancedComposite(Composite parent) {
		super.addToAdvancedComposite(parent);
		createServerTargetComposite(parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.J2EEWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[]{EditModelOperationDataModel.PROJECT_NAME, J2EEArtifactCreationDataModel.PROJECT_LOCATION, J2EEApplicationCreationDataModel.APPLICATION_VERSION, J2EEArtifactCreationDataModel.SERVER_TARGET_ID};
	}
}