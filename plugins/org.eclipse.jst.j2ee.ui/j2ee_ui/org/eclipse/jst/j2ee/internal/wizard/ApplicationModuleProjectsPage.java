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
 * Created on Nov 5, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.application.operations.AddArchiveProjectsToEARDataModel;
import org.eclipse.jst.j2ee.application.operations.DefaultModuleProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.EARProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.wst.common.frameworks.internal.ui.WTPWizardPage;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class ApplicationModuleProjectsPage extends WTPWizardPage {
	private Button selectAllButton;
	private Button deselectAllButton;
	private Button newModuleButton;
	private CheckboxTableViewer moduleProjectsViewer;
	private boolean ignoreCheckedState = false;

	/**
	 * @param model
	 * @param pageName
	 */
	protected ApplicationModuleProjectsPage(EARProjectCreationDataModel model, String pageName) {
		super(model, pageName);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_PROJECT_MODULES_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_PROJECT_MODULES_PG_DESC));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.J2EEWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.J2EEWizardPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		Composite modulesGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		modulesGroup.setLayout(layout);
		setInfopopID(IJ2EEUIContextIds.NEW_EAR_ADD_MODULES_PAGE);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		modulesGroup.setLayoutData(gridData);
		createModuleProjectOptions(modulesGroup);
		createButtonsGroup(modulesGroup);
		return modulesGroup;
	}

	/**
	 * @param modulesGroup
	 */
	private void createModuleProjectOptions(Composite modulesGroup) {
		moduleProjectsViewer = CheckboxTableViewer.newCheckList(modulesGroup, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gData = new GridData(GridData.FILL_BOTH);
		gData.widthHint = 200;
		gData.heightHint = 80;
		moduleProjectsViewer.getControl().setLayoutData(gData);
		AvailableModuleProjectsProvider provider = new AvailableModuleProjectsProvider((EARProjectCreationDataModel) model);
		moduleProjectsViewer.setContentProvider(provider);
		moduleProjectsViewer.setLabelProvider(new WorkbenchLabelProvider());
		setCheckedItemsFromModel();
		moduleProjectsViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				if (!ignoreCheckedState) {
					getModulesModel().setProperty(AddArchiveProjectsToEARDataModel.MODULE_LIST, getCheckedElementsAsList());
				}
			}
		});
		TableLayout tableLayout = new TableLayout();
		moduleProjectsViewer.getTable().setLayout(tableLayout);
		moduleProjectsViewer.getTable().setHeaderVisible(false);
		moduleProjectsViewer.getTable().setLinesVisible(false);
		moduleProjectsViewer.setSorter(null);
	}

	/**
	 *  
	 */
	private void setCheckedItemsFromModel() {
		List projects = (List) getModulesModel().getProperty(AddArchiveProjectsToEARDataModel.MODULE_LIST);
		moduleProjectsViewer.setCheckedElements(projects.toArray());
	}

	private void refreshModules() {
		moduleProjectsViewer.refresh();
		setCheckedItemsFromModel();
	}

	protected List getCheckedElementsAsList() {
		Object[] elements = moduleProjectsViewer.getCheckedElements();
		List list;
		if (elements == null || elements.length == 0)
			list = Collections.EMPTY_LIST;
		else
			list = Arrays.asList(elements);
		return list;
	}

	protected void createButtonsGroup(org.eclipse.swt.widgets.Composite parent) {
		Composite buttonGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 4;
		buttonGroup.setLayout(layout);
		buttonGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		selectAllButton = new Button(buttonGroup, SWT.PUSH);
		selectAllButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_PROJECT_MODULES_PG_SELECT));
		selectAllButton.addListener(SWT.Selection, this);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.heightHint = 22;
		gd.widthHint = 120;
		selectAllButton.setLayoutData(gd);
		deselectAllButton = new Button(buttonGroup, SWT.PUSH);
		deselectAllButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_PROJECT_MODULES_PG_DESELECT));
		deselectAllButton.addListener(SWT.Selection, this);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.heightHint = 22;
		gd.widthHint = 120;
		deselectAllButton.setLayoutData(gd);
		newModuleButton = new Button(buttonGroup, SWT.PUSH);
		newModuleButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_PROJECT_MODULES_PG_NEW));
		newModuleButton.addListener(SWT.Selection, this);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.heightHint = 22;
		gd.widthHint = 120;
		newModuleButton.setLayoutData(gd);
	}

	/**
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(Event)
	 */
	public void handleEvent(Event evt) {
		if (evt.widget == selectAllButton)
			handleSelectAllButtonPressed();
		else if (evt.widget == deselectAllButton)
			handleDeselectAllButtonPressed();
		else if (evt.widget == newModuleButton)
			handleNewModuleButtonPressed();
		else
			super.handleEvent(evt);
	}

	/**
	 *  
	 */
	private void handleNewModuleButtonPressed() {
		DefaultModuleProjectCreationDataModel aModel = createNewModuleModel();
		DefaultModuleProjectCreationWizard wizard = new DefaultModuleProjectCreationWizard(aModel);
		WizardDialog dialog = new WizardDialog(getShell(), wizard);
		dialog.create();
		if (dialog.open() != IDialogConstants.CANCEL_ID) {
			setNewModules(aModel);
			refreshModules();
		}
		validatePage();
	}

	/**
	 * @param model
	 */
	private void setNewModules(DefaultModuleProjectCreationDataModel defaultModel) {
		List newProjects = new ArrayList();
		collectNewProjects(defaultModel, newProjects);
		AddArchiveProjectsToEARDataModel modModel = ((EARProjectCreationDataModel) model).getAddModulesToEARDataModel();
		List selectedProjects = (List) modModel.getProperty(AddArchiveProjectsToEARDataModel.MODULE_LIST);
		newProjects.addAll(selectedProjects);
		modModel.setProperty(AddArchiveProjectsToEARDataModel.MODULE_LIST, newProjects);
	}

	/**
	 * @param defaultModel
	 * @param newProjects
	 */
	private void collectNewProjects(DefaultModuleProjectCreationDataModel defaultModel, List newProjects) {
		collectProject(defaultModel.getEjbModel(), newProjects);
		collectProject(defaultModel.getWebModel(), newProjects);
		collectProject(defaultModel.getClientModel(), newProjects);
		collectProject(defaultModel.getJCAModel(), newProjects);
	}

	private void collectProject(J2EEProjectCreationDataModel projModel, List newProjects) {
		if (projModel != null) {
			IProject project = projModel.getTargetProject();
			if (project != null && project.exists())
				newProjects.add(project);
		}
	}

	private DefaultModuleProjectCreationDataModel createNewModuleModel() {
		DefaultModuleProjectCreationDataModel defaultModel = new DefaultModuleProjectCreationDataModel();
		String earName = model.getStringProperty(EditModelOperationDataModel.PROJECT_NAME);
		Object j2eeVersion = model.getProperty(EARProjectCreationDataModel.EAR_VERSION);
		Object serverTargetID = model.getStringProperty(J2EEProjectCreationDataModel.SERVER_TARGET_ID);
		defaultModel.setProperty(DefaultModuleProjectCreationDataModel.BASE_NAME, earName);
		defaultModel.setProperty(DefaultModuleProjectCreationDataModel.J2EE_VERSION, j2eeVersion);
		defaultModel.setProperty(ServerTargetDataModel.RUNTIME_TARGET_ID, serverTargetID);
		return defaultModel;
	}

	/**
	 *  
	 */
	private void handleDeselectAllButtonPressed() {
		ignoreCheckedState = true;
		try {
			moduleProjectsViewer.setAllChecked(false);
			getModulesModel().setProperty(AddArchiveProjectsToEARDataModel.MODULE_LIST, null);
		} finally {
			ignoreCheckedState = false;
		}
	}

	/**
	 *  
	 */
	private void handleSelectAllButtonPressed() {
		ignoreCheckedState = true;
		try {
			moduleProjectsViewer.setAllChecked(true);
			getModulesModel().setProperty(AddArchiveProjectsToEARDataModel.MODULE_LIST, getCheckedElementsAsList());
		} finally {
			ignoreCheckedState = false;
		}
	}

	private AddArchiveProjectsToEARDataModel getModulesModel() {
		return ((EARProjectCreationDataModel) model).getAddModulesToEARDataModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.J2EEWizardPage#enter()
	 */
	protected void enter() {
		moduleProjectsViewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
		super.enter();
	}
}