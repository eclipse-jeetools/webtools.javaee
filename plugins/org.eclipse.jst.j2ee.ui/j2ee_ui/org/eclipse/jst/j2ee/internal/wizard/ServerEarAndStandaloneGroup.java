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

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.application.operations.EARProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;
import org.eclipse.wst.internal.common.frameworks.ui.WTPDataModelSynchHelper;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class ServerEarAndStandaloneGroup {
	private Button newEAR;
	private Combo earCombo;
	private Label earLabel;
	private Button addToEAR;
	protected Combo serverTargetCombo;
	private J2EEProjectCreationDataModel model;
	//	constants
	//private static final int SIZING_TEXT_FIELD_WIDTH = 305;
	private WTPDataModelSynchHelper synchHelper;

	/**
	 *  
	 */
	public ServerEarAndStandaloneGroup(Composite parent, J2EEProjectCreationDataModel model) {
		this.model = model;
		synchHelper = new WTPDataModelSynchHelper(model);
		buildComposites(parent);
	}

	public void buildComposites(Composite parent) {
		createServerTargetComposite(parent);
		createEarAndStandaloneComposite(parent);
	}

	/**
	 * @param parent
	 */
	protected void createEarAndStandaloneComposite(Composite parent) {

		if (model.getBooleanProperty(J2EEModuleCreationDataModel.UI_SHOW_EAR_SECTION)) {

			Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 3;
			separator.setLayoutData(gd);

			new Label(parent, SWT.NONE); //pad

			// Create Add to EAR checkbox
			addToEAR = new Button(parent, SWT.CHECK);
			addToEAR.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.LINK_MODULETO_EAR_PROJECT));
			addToEAR.setSelection(true);
			gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 2;
			addToEAR.setLayoutData(gd);
			synchHelper.synchCheckbox(addToEAR, J2EEModuleCreationDataModel.ADD_TO_EAR, null);
			addToEAR.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					handleAddToEarSelection();
				}

				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});

			// Create EAR Group
			earLabel = new Label(parent, SWT.NONE);
			earLabel.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_PROJECT_FOR_MODULE_CREATION));

			earCombo = new Combo(parent, SWT.NONE);
			earCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

			newEAR = new Button(parent, SWT.NONE);
			newEAR.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_THREE_DOTS_W));
			newEAR.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			newEAR.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					handleNewEarSelected();
				}

				public void widgetDefaultSelected(SelectionEvent e) {
				}

			});

			Control[] deps = new Control[]{earLabel, newEAR};
			synchHelper.synchCombo(earCombo, J2EEModuleCreationDataModel.EAR_PROJECT_NAME, J2EEModuleCreationDataModel.EAR_PROJECT_NAME, deps);

		}

	}

	/**
	 *  
	 */
	protected void handleAddToEarSelection() {

		boolean selection = addToEAR.getSelection();
		earLabel.setEnabled(selection);
		earCombo.setEnabled(selection);
		newEAR.setEnabled(selection);

	}

	/**
	 *  
	 */
	protected void handleNewEarSelected() {

		J2EEModuleCreationDataModel moduleModel = getJ2EEModuleCreationDataModel();
		EARProjectCreationDataModel earModel = new EARProjectCreationDataModel();
		earModel.setIntProperty(EARProjectCreationDataModel.EAR_VERSION, moduleModel.getJ2EEVersion());
		earModel.setProperty(EditModelOperationDataModel.PROJECT_NAME, moduleModel.getProperty(J2EEModuleCreationDataModel.EAR_PROJECT_NAME));
		earModel.setProperty(J2EEProjectCreationDataModel.SERVER_TARGET_ID, moduleModel.getProperty(J2EEProjectCreationDataModel.SERVER_TARGET_ID));
		earModel.setBooleanProperty(EARProjectCreationDataModel.UI_SHOW_FIRST_PAGE_ONLY, true);
		ApplicationProjectWizard earWizard = new ApplicationProjectWizard(earModel);
		WizardDialog dialog = new WizardDialog(getShell(), earWizard);
		if (Window.OK == dialog.open()) {
			moduleModel.notifyUpdatedEARs();
			moduleModel.setProperty(J2EEModuleCreationDataModel.EAR_PROJECT_NAME, earModel.getProperty(EditModelOperationDataModel.PROJECT_NAME));
		}

	}

	/**
	 *  
	 */
	private Shell getShell() {
		return serverTargetCombo.getShell();
	}

	/**
	 * @return
	 */
	protected J2EEModuleCreationDataModel getJ2EEModuleCreationDataModel() {
		return (J2EEModuleCreationDataModel) model;
	}

	protected void createServerTargetComposite(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.TARGET_SERVER_LBL));
		serverTargetCombo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
		serverTargetCombo.setLayoutData((new GridData(GridData.FILL_HORIZONTAL)));
		//        if (!org.eclipse.jst.j2ee.plugin.J2EEPlugin.hasDevelopmentRole()) {
		//            serverTargetCombo.setEnabled(false);
		//        }
		Button newServerTargetButton = new Button(parent, SWT.NONE);
		newServerTargetButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		newServerTargetButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_THREE_DOTS_E));
		newServerTargetButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				J2EEProjectCreationPage.launchNewRuntimeWizard(getShell(), getJ2EEModuleCreationDataModel().getServerTargetDataModel());
			}
		});
		Control[] deps = new Control[]{label, newServerTargetButton};
		synchHelper.synchCombo(serverTargetCombo, ServerTargetDataModel.RUNTIME_TARGET_NAME, ServerTargetDataModel.RUNTIME_TARGET_ID, deps);
	}

	public void dispose() {
		model.removeListener(synchHelper);
		model.dispose();
		synchHelper = null;
		model = null;
	}
}