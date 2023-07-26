package org.eclipse.jst.servlet.ui.internal.actions;

/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.web.datamodel.properties.IConvertStaticWebToDyamnicDataModelProperties;
import org.eclipse.jst.servlet.ui.internal.wizard.ConvertToWebModuleTypeDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.web.ui.internal.Logger;

/**
 * Convert a simple static web project to a J2EE Dynamic Web Project
 */
public class ConvertToWebModuleTypeAction extends Action implements IWorkbenchWindowActionDelegate {

	IStructuredSelection fSelection = null;
	IProject project = null;
	IWorkbenchWindow fWindow;

	/**
	 * ConvertLinksDialog constructor comment.
	 */
	public ConvertToWebModuleTypeAction() {
		super();
	}

	/**
	 * make sure a web project is selected.
	 */
	protected boolean isValidProject(IProject aProject) {
		return JavaEEProjectUtilities.isStaticWebProject(aProject);
	}

	/**
	 * selectionChanged method comment.
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		boolean bEnable = false;
		if (selection instanceof IStructuredSelection) {
			fSelection = (IStructuredSelection) selection;
			bEnable = validateSelected(fSelection);
		}
		((Action) action).setEnabled(bEnable);
	}

	/**
	 * selectionChanged method comment.
	 */
	protected boolean validateSelected(ISelection selection) {
		if (!(selection instanceof IStructuredSelection))
			return false;

		fSelection = (IStructuredSelection) selection;

		Object selectedProject = fSelection.getFirstElement();
		if (!(selectedProject instanceof IProject))
			return false;

		project = (IProject) selectedProject;
		return isValidProject(project);
	}

	@Override
	public void dispose() {
		// Default
	}

	@Override
	public void init(IWorkbenchWindow window) {
		// Default
	}

	@Override
	public void run(IAction action) {
		try {
			IWorkbenchWindow window = PlatformUI.getWorkbench().getWorkbenchWindows()[0];
			ConvertToWebModuleTypeDialog dialog = new ConvertToWebModuleTypeDialog(window.getShell());
			dialog.open();
			if (dialog.getReturnCode() == Window.CANCEL)
				return;
			
			doConvert(ConvertToWebModuleTypeDialog.getSelectedVersion());
			
		} catch (Exception e) {
			Logger.logException(e);
		}
	}
	
	protected void doConvert(String selectedVersion) throws Exception {
		
		IDataModel model = DataModelFactory.createDataModel(IConvertStaticWebToDyamnicDataModelProperties.class);
		model.setProperty(IConvertStaticWebToDyamnicDataModelProperties.PROJECT, project);
		model.setStringProperty(IConvertStaticWebToDyamnicDataModelProperties.CONVERT_TO_WEB_VERSION, ConvertToWebModuleTypeDialog.getSelectedVersion());
		model.getDefaultOperation().execute(new NullProgressMonitor(), null);
		
	}

}
