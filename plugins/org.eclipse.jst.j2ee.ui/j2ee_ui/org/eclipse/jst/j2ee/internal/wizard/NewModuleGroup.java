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
 * Created on Nov 3, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EECreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.ui.FlexibleProjectCreationWizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.ui.WTPDataModelSynchHelper;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 *
 */
public class NewModuleGroup {
	
	private J2EEComponentCreationDataModel model;
	protected Combo projectNameCombo = null;
	protected Text moduleNameText = null;
	protected Button newButton = null;
	protected Text serverTargetText;
	private WTPDataModelSynchHelper synchHelper;
	private Composite parentComposite;
	
	private static final int SIZING_TEXT_FIELD_WIDTH = 305;
	private static final String PROJECT_NAME = J2EEUIMessages.getResourceString(J2EEUIMessages.MODULES_DEPENDENCY_PAGE_TABLE_PROJECT)+ ":"; //$NON-NLS-1$
	private static final String NEW_LABEL = J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_THREE_DOTS_E); //$NON-NLS-1$
	private static final String MODULE_NAME = J2EEUIMessages.getResourceString(J2EEUIMessages.MODULE_NAME); //$NON-NLS-1$
	
	/**
	 * @param parent
	 * @param style
	 */
	public NewModuleGroup(Composite parent, int style, J2EEComponentCreationDataModel model) {
		this.model = model;
		this.parentComposite = parent;
		synchHelper = new WTPDataModelSynchHelper(model);
		buildComposites(parent);
	}

	/**
	 * Create the controls within this composite
	 */
	public void buildComposites(Composite parent) {
		createProjectNameGroup(parent);
		projectNameCombo.setFocus();
		initializeProjectList();
		createServerTargetComposite(parent);
		addSeperator(parent,3);
		createModuleGroup(parent);
	}
	
	private void initializeProjectList() {
		IProject[] workspaceProjects = ProjectUtilities.getAllProjects();
		List items = new ArrayList();
		for (int i=0; i<workspaceProjects.length; i++) {
			IProject project = workspaceProjects[i];
			try {
				if (project.hasNature(IModuleConstants.MODULE_NATURE_ID)) {
					items.add(project.getName());
				}
			} catch (CoreException ce) {
				//Ignore
			}
		}
		String[] names = new String[items.size()];
		for (int i=0; i<items.size(); i++) {
			names[i]= (String) items.get(i);
		}
		projectNameCombo.setItems(names);
		if (names.length>0) {
			projectNameCombo.setText(names[0]);
			model.setProperty(J2EECreationDataModel.PROJECT_NAME,names[0]);
		}
	}

	/**
	 *  
	 */
	private void createProjectNameGroup(Composite parent) {
		// set up project name label
		Label projectNameLabel = new Label(parent, SWT.NONE);
		projectNameLabel.setText(PROJECT_NAME);
		GridData data = new GridData();
		projectNameLabel.setLayoutData(data);
		// set up project name entry field
		projectNameCombo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		projectNameCombo.setLayoutData(data);
		projectNameCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IProject project = ProjectUtilities.getProject(projectNameCombo.getText());
				IRuntime runtime = ServerCore.getProjectProperties(project).getRuntimeTarget();
				if (runtime != null)
					serverTargetText.setText(runtime.getName());
			}
		});
		newButton = new Button(parent, SWT.NONE);
		newButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		newButton.setText(NEW_LABEL);
		newButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleNewProjectSelected();
			}
		});
		synchHelper.synchCombo(projectNameCombo, J2EECreationDataModel.PROJECT_NAME, new Control[]{projectNameLabel});
	}
	
	private void handleNewProjectSelected() {
		J2EEProjectCreationDataModel projModel = new J2EEProjectCreationDataModel();
		FlexibleProjectCreationWizard newProjectWizard = new FlexibleProjectCreationWizard(projModel);
		WizardDialog dialog = new WizardDialog(parentComposite.getShell(), newProjectWizard);
		if (Window.OK == dialog.open()) {
			String newProjectName = projModel.getStringProperty(J2EEProjectCreationDataModel.PROJECT_NAME);
			projectNameCombo.add(newProjectName);
			projectNameCombo.setText(newProjectName);
		}
	}
	
	private void createModuleGroup(Composite parent) {
		GridData data = new GridData();
		// Add the module name label
		Label moduleNameLabel = new Label(parent, SWT.NONE);
		moduleNameLabel.setText(MODULE_NAME);
		// Add the module name entry field
		moduleNameText = new Text(parent, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		moduleNameText.setLayoutData(data);
		synchHelper.synchText(moduleNameText,J2EECreationDataModel.MODULE_NAME,new Control[] {});
		new Label(parent,SWT.NONE);
		
	}

	public void dispose() {
		if (synchHelper != null) {
			model.removeListener(synchHelper);
			synchHelper.dispose();
		}
		model = null;
	}
	
	public String getProjectName() {
		return projectNameCombo.getText();
	}
	
	public void addSeperator(Composite parent, int hSpan) {
		Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = hSpan;
		separator.setLayoutData(gd);
	}
	
	protected void createServerTargetComposite(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.TARGET_SERVER_LBL));
		serverTargetText = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
		serverTargetText.setLayoutData((new GridData(GridData.FILL_HORIZONTAL)));
		new Label(parent, SWT.NONE);
	}
}