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
package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.ui.WTPWizard;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.UnresolveableURIException;
import org.eclipse.wst.common.modulecore.WorkbenchModule;
import org.eclipse.wst.common.modulecore.WorkbenchModuleResource;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author jlanuti
 */
public abstract class NewWebWizard extends WTPWizard implements INewWizard {

	/**
	 * @param model
	 */
	public NewWebWizard(WTPOperationDataModel model) {
		super(model);
	}

	/**
	 * Default constructor
	 */
	public NewWebWizard() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected abstract WTPOperationDataModel createDefaultModel();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizard#createOperation()
	 */
	protected abstract WTPOperation createOperation();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		createDefaultModel();
	}

	protected WorkbenchModule getDefaultWebModule() {
		IProject project = null;
		IStructuredSelection selection = getCurrentSelection();
		if (selection != null && selection.getFirstElement() != null) {
			Object selectedObject = selection.getFirstElement();
			if (selectedObject instanceof WorkbenchModule)
				return (WorkbenchModule) selectedObject;
			project = ProjectUtilities.getProject(selectedObject);
			URI uri = null;
			if (selectedObject instanceof Resource)
				uri = ((Resource)selectedObject).getURI();
			if (selectedObject instanceof EObject)
				uri = ((EObject)selectedObject).eResource().getURI();
			if (uri == null)
				return null;
			ModuleCore moduleCore = null;
			WorkbenchModule module = null;
			try {
				moduleCore = ModuleCore.getModuleCoreForRead(project);
				WorkbenchModuleResource[] resources = moduleCore.findWorkbenchModuleResourcesBySourcePath(uri);
				for (int i=0; i<resources.length; i++) {
					module = resources[i].getModule();
					if (module != null)
						return module;
				}
			} catch(UnresolveableURIException e) {
				//Ignore
			} finally {
				if (moduleCore != null)
					moduleCore.dispose();
			}
		}
		
		IProject[] projects = ProjectUtilities.getAllProjects();
		ModuleCore moduleCore = null;
		for (int i = 0; i < projects.length; i++) {
			try {
				moduleCore = ModuleCore.getModuleCoreForRead(projects[i]);
				WorkbenchModule[] modules = moduleCore.findWorkbenchModuleByType(IModuleConstants.JST_WEB_MODULE);
				if (modules.length>0)
					return modules[0];
			} finally {
				if (moduleCore != null)
					moduleCore.dispose();
			}
		}
		return null;
	}

	protected IStructuredSelection getCurrentSelection() {
		IWorkbenchWindow window = J2EEUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow();
		if (window != null) {
			ISelection selection = window.getSelectionService().getSelection();
			if (selection instanceof IStructuredSelection) {
				return (IStructuredSelection) selection;
			}
		}
		return null;
	}

}