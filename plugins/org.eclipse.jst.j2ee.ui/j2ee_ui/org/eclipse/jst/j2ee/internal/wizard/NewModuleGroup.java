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
/*
 * Created on Nov 3, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.DoNotUseMeThisWillBeDeletedPost15;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelSynchHelper;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;

/**
 * This has been slated for removal post WTP 1.5. Do not use this class/interface
 * 
 * @deprecated
 */
public class NewModuleGroup implements IJ2EEComponentCreationDataModelProperties, DoNotUseMeThisWillBeDeletedPost15{
	
	private IDataModel model;
	protected Text moduleNameText = null;
	protected Button newButton = null;
	protected Text serverTargetText;
	private DataModelSynchHelper synchHelper;
//	private Composite parentComposite;
	
	private static final int SIZING_TEXT_FIELD_WIDTH = 305;
//	private static final String NEW_LABEL_UI = J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_THREE_DOTS_E); //$NON-NLS-1$
	private static final String MODULE_NAME_UI = J2EEUIMessages.getResourceString(J2EEUIMessages.MODULE_NAME); //$NON-NLS-1$
	
	/**
	 * @param parent
	 * @param style
	 */
	public NewModuleGroup(Composite parent, int style, IDataModel model, DataModelSynchHelper syncHelper) {
		this.model = model;
//		this.parentComposite = parent;
		synchHelper = syncHelper;
		buildComposites(parent);
	}

	/**
	 * Create the controls within this composite
	 */
	public void buildComposites(Composite parent) {
		//createProjectNameGroup(parent);
		//initializeProjectList();
        createModuleGroup(parent);
        //addSeperator(parent,3);
		//createServerTargetComposite(parent);
	}
	
	/**
	 * 
	 *
	 */
//	public void initializeProjectList() {
//		IProject[] workspaceProjects = ProjectUtilities.getAllProjects();
//		List items = new ArrayList();
//		for (int i=0; i<workspaceProjects.length; i++) {
//			IProject project = workspaceProjects[i];
//			try {
//				if (project.hasNature(IModuleConstants.MODULE_NATURE_ID)) {
//					items.add(project.getName());
//				}
//			} catch (CoreException ce) {
//				//Ignore
//			}
//		}
//		String[] names = new String[items.size()];
//		for (int i=0; i<items.size(); i++) {
//			names[i]= (String) items.get(i);
//		}
//		//model.IgnorePropertyChanges(true);
//		projectNameCombo.setItems(names);
//		//model.setIgnorePropertyChanges(false);
//		
//		if (!model.isPropertySet(PROJECT_NAME) || model.getStringProperty(PROJECT_NAME).length()==0) {
//			IProject selectedProject = getSelectedProject();
//			if (selectedProject!=null) {
//				projectNameCombo.setText(selectedProject.getName());
//				model.setProperty(PROJECT_NAME,selectedProject.getName());
//			}
//			else if (names.length>0) {
//				projectNameCombo.setText(names[0]);
//				model.setProperty(PROJECT_NAME,names[0]);
//			}
//		} else {
//			projectNameCombo.add(model.getStringProperty(PROJECT_NAME));
//			projectNameCombo.setText(model.getStringProperty(PROJECT_NAME));
//		}
//	}

	/**
	 *  
	 */
//	private void createProjectNameGroup(Composite parent) {
//		// set up project name label
//		Label projectNameLabel = new Label(parent, SWT.NONE);
//		projectNameLabel.setText(PROJECT_NAME_UI);
//		GridData data = new GridData();
//		projectNameLabel.setLayoutData(data);
//		// set up project name entry field
//		projectNameCombo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
//		data = new GridData(GridData.FILL_HORIZONTAL);
//		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
//		projectNameCombo.setLayoutData(data);
//		projectNameCombo.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent e) {
//				IProject project = ProjectUtilities.getProject(projectNameCombo.getText());
//				IRuntime runtime = ServerCore.getProjectProperties(project).getRuntimeTarget();
//				if (runtime != null)
//					serverTargetText.setText(runtime.getName());
//			}
//		});
//		newButton = new Button(parent, SWT.NONE);
//		newButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		newButton.setText(NEW_LABEL_UI);
//		newButton.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent e) {
//				handleNewProjectSelected();
//			}
//		});
//		synchHelper.synchCombo(projectNameCombo, PROJECT_NAME, new Control[]{projectNameLabel});
//	}
	
	/**
	 * @return
	 */
//	private IProject getSelectedProject() {
//		IWorkbenchWindow window = Workbench.getInstance().getActiveWorkbenchWindow();
//		if (window == null)
//			return null;
//		ISelection selection = window.getSelectionService().getSelection();
//		if (selection == null || !(selection instanceof StructuredSelection))
//			return null;
//		StructuredSelection stucturedSelection = (StructuredSelection) selection;
//		Object obj = stucturedSelection.getFirstElement();
//		if (obj instanceof IProject)
//			return (IProject) obj;
//		return null;
//	}
//	
	/**
	 * 
	 *
	 */
//	private void handleNewProjectSelected() {
//		IDataModel projModel = DataModelFactory.createDataModel(new FlexibleJavaProjectCreationDataModelProvider());
//		FlexibleProjectCreationWizard newProjectWizard = new FlexibleProjectCreationWizard(projModel);
//		WizardDialog dialog = new WizardDialog(parentComposite.getShell(), newProjectWizard);
//		if (Window.OK == dialog.open()) {
//			String newProjectName = projModel.getStringProperty(PROJECT_NAME);
//			projectNameCombo.add(newProjectName);
//			projectNameCombo.setText(newProjectName);
//			IProject project = ProjectUtilities.getProject(projectNameCombo.getText());
//			IRuntime runtime = ServerCore.getProjectProperties(project).getRuntimeTarget();
//			if (runtime != null)
//				serverTargetText.setText(runtime.getName());
//		}
//	}
	
	/**
	 * 
	 * @param parent
	 */
	private void createModuleGroup(Composite parent) {
		GridData data = new GridData();
		// Add the module name label
		Label moduleNameLabel = new Label(parent, SWT.NONE);
		moduleNameLabel.setText(MODULE_NAME_UI);
		// Add the module name entry field
		moduleNameText = new Text(parent, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		moduleNameText.setLayoutData(data);
		synchHelper.synchText(moduleNameText,COMPONENT_NAME,new Control[] {});
		new Label(parent,SWT.NONE);
	}

	/**
	 * 
	 *
	 */
	public void dispose() {
		if (synchHelper != null) {
			if (model != null)
				model.removeListener(synchHelper);
			synchHelper.dispose();
		}
		model = null;
	}
	
	/**
	 * 
	 * @return
	 */
//	public String getProjectName() {
//		return projectNameCombo.getText();
//	}
	
	/**
	 * 
	 * @param parent
	 * @param hSpan
	 */
	public void addSeperator(Composite parent, int hSpan) {
		Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = hSpan;
		separator.setLayoutData(gd);
	}
	
	/**
	 * 
	 * @param parent
	 */
	protected void createServerTargetComposite(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.TARGET_RUNTIME_LBL));
		serverTargetText = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
		serverTargetText.setLayoutData((new GridData(GridData.FILL_HORIZONTAL)));
		new Label(parent, SWT.NONE);
		String projectName = moduleNameText.getText();
		if (projectName!=null && projectName.length()!=0) {
			IProject project = ProjectUtilities.getProject(projectName);
			if (project !=null) {
				IRuntime runtime = ServerCore.getProjectProperties(project).getRuntimeTarget();
				if (runtime != null){
					serverTargetText.setText(runtime.getName());
					synchHelper.synchText(serverTargetText, RUNTIME_TARGET_ID, null);
				}	
			}
		}
	}
}
