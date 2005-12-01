/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.earcreation.EarComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelSynchHelper;

/**
 * 
 */
public class ServerEarAndStandaloneGroup implements IJ2EEComponentCreationDataModelProperties {
	
	private Button newEAR;
	private Combo earCombo;
	private Label earLabel;
	private Button addToEAR;
	private IDataModel model;
	private DataModelSynchHelper synchHelper;
	private Composite parentComposite;

	/**
	 *  
	 */
	public ServerEarAndStandaloneGroup(Composite parent, IDataModel model, DataModelSynchHelper helper) {
		this.model = model;
		this.parentComposite = parent;
		synchHelper = helper;
		buildComposites(parent);
	}

	/**
	 * 
	 * @param parent
	 */
	public void buildComposites(Composite parent) {
		createEarAndStandaloneComposite(parent);
	}

	/**
	 * @param parent
	 */
	protected void createEarAndStandaloneComposite(Composite parent) {

		if (model.getBooleanProperty(UI_SHOW_EAR_SECTION)) {

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
			synchHelper.synchCheckbox(addToEAR, ADD_TO_EAR, null);
			
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
					//do nothing
				}

			});

			Control[] deps = new Control[]{earLabel, newEAR};
			synchHelper.synchCombo(earCombo, EAR_COMPONENT_NAME, deps);
		}
	}

	/**
	 *  
	 */
	protected void handleNewEarSelected() {
		IDataModel moduleModel = model;
        IDataModel earModel = DataModelFactory.createDataModel(new EarComponentCreationDataModelProvider());
		earModel.setIntProperty(COMPONENT_VERSION, moduleModel.getIntProperty(IJ2EEComponentCreationDataModelProperties.COMPONENT_VERSION));
		earModel.setProperty(COMPONENT_NAME, moduleModel.getProperty(IJ2EEComponentCreationDataModelProperties.EAR_COMPONENT_NAME));
		EARComponentCreationWizard earWizard = new EARComponentCreationWizard(earModel);
		WizardDialog dialog = new WizardDialog(parentComposite.getShell(), earWizard);
		if (Window.OK == dialog.open()) {
			moduleModel.setProperty(EAR_COMPONENT_NAME, earModel.getProperty(COMPONENT_NAME));
		}
	}

	public void dispose() {
		model.removeListener(synchHelper);
		model.dispose();
		synchHelper = null;
		model = null;
	}
}
