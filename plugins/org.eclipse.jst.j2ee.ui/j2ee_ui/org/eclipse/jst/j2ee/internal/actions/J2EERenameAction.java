/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.actions;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.ui.actions.SelectionDispatchAction;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.common.internal.util.CommonUtil;
import org.eclipse.jst.j2ee.internal.dialogs.J2EERenameDialog;
import org.eclipse.jst.j2ee.internal.dialogs.J2EERenameUIConstants;
import org.eclipse.jst.j2ee.internal.dialogs.RenameModuleDialog;
import org.eclipse.jst.j2ee.internal.plugin.CommonEditorUtility;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.rename.RenameOptions;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.RenameResourceAction;
import org.eclipse.ui.actions.SelectionListenerAction;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class J2EERenameAction extends SelectionDispatchAction implements J2EERenameUIConstants {

	protected Shell shell;
	//Used for EAR rename
	protected Set referencedProjects;
	protected List modules;
	protected RenameOptions options;
	// added for IRefactoringAction behavior
	protected ISelectionProvider provider = null;
//	protected RenameModuleOperation renameModuleOperation = null;

	/**
	 * Constructor for RenameModuleAction.
	 * 
	 * @param text
	 */
	public J2EERenameAction(IWorkbenchSite site, Shell parent) {
		super(site);
		setText(RENAME);
		shell = parent;
	}

	public J2EERenameAction(IWorkbenchSite site, ISelectionProvider newProvider) {
		super(site);
		setText(RENAME);
		shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		provider = newProvider;
	}

	protected void reset() {
//		if (renameModuleOperation != null) {
//			renameModuleOperation.release();
//			renameModuleOperation = null;
//		}
		referencedProjects = null;
		modules = null;
		options = null;
	}

	/**
	 * @see SelectionListenerAction#updateSelection(IStructuredSelection)
	 */
	protected void updateSelection(IStructuredSelection selection) {
		super.update(selection);
	}

	/**
	 * @see SelectionDispatchAction#selectionChanged(ISelection)
	 */
	public void selectionChanged(ISelection selection) {
		if (selection instanceof IStructuredSelection)
			setEnabledFromSelection((IStructuredSelection) selection);
		else
			super.selectionChanged(selection);
	}

	protected void setEnabledFromSelection(IStructuredSelection selection) {
		if (selection == null) {
			setEnabled(false);
		} else {
			if (selection.toList().size() != 1) {
				setEnabled(false);
			} else {
				setEnabled(getEnableStateBasedOnSelection(selection));
			}
		}
	}

	protected boolean getEnableStateBasedOnSelection(IStructuredSelection selection) {
		if (selection.isEmpty())
			return false;
		return isSelectionAllDDRoots() || isSelectionAllApplications();
	}

	protected boolean isSelectionAllDDRoots() {
		IStructuredSelection sel = (StructuredSelection) getSelection();
		Iterator it = sel.iterator();
		while (it.hasNext()) {
			Object o = it.next();
			//TODO check for j2ee workbench module selection
			if (!CommonUtil.isDeploymentDescriptorRoot(o, false) /*&& !isJ2EEProject(o)*/)
				return false;
		}
		return true;
	}

	protected boolean isSelectionAllApplications() {
		IStructuredSelection sel = (StructuredSelection) getSelection();
		Iterator it = sel.iterator();
		while (it.hasNext()) {
			Object o = it.next();
			if (!(o instanceof Application) && !isJ2EEApplicationProject(o))
				return false;
		}
		return true;
	}


	protected List getModules() {
		if (modules == null) {
			modules = new ArrayList();
			IStructuredSelection sel = (StructuredSelection) getSelection();
			Iterator iterator = sel.iterator();
			WorkbenchComponent module = null;
			Object o = null;
			while (iterator.hasNext()) {
				o = iterator.next();
				if (o instanceof WorkbenchComponent) {
					modules.add(o);
				} else if (o instanceof EObject) {
					EObject obj = (EObject) o;
					IProject project = ProjectUtilities.getProject(obj);
					StructureEdit moduleCore = null;
					try {
						moduleCore = StructureEdit.getStructureEditForRead(project);
						URI uri = obj.eResource().getURI();
						ComponentResource[] resources = moduleCore.findResourcesBySourcePath(uri);
						for (int i=0; i<resources.length; i++) {
							module = resources[i].getComponent();
							if (module !=null)
								break;
						}
						if (module == null)
							throw new RuntimeException(J2EEUIMessages.getResourceString("Project_should_not_be_null_1_ERROR_")); //$NON-NLS-1$
						modules.add(module);
					} catch (UnresolveableURIException e) {
						//Ignore
					} finally {
						if (moduleCore !=null)
							moduleCore.dispose();
					}
				} else {
					throw new RuntimeException(J2EEUIMessages.getResourceString("Non-project_in_selection_2_ERROR_")); //$NON-NLS-1$
				}
			}
		}
		return modules;
	}

	/**
	 * @see IAction#run()
	 */
	public void run() {
		try {
			List localModules = getModules();
			if (localModules.size() != 1)
				return;
			WorkbenchComponent module = (WorkbenchComponent) localModules.get(0);
			J2EERenameDialog dlg = null;

			// if all we are doing is renaming an EAR, let the base platform do it
			if (isSelectionAllApplications()) {
				RenameResourceAction action = new RenameResourceAction(shell);
				action.selectionChanged(new StructuredSelection(module));
				action.run();
			} else {
				String contextRoot = ""; //$NON-NLS-1$
				//TODO add context root to the module model
				//contextRoot = module.getServerContextRoot();
				dlg = new RenameModuleDialog(shell, module.getName(), contextRoot);
				dlg.open();
				if (dlg.getReturnCode() == Window.CANCEL)
					return;

				options = dlg.getRenameOptions();
				if (options != null)
					options.setSelectedProjects(localModules);

				if (!(ensureEditorsSaved() && validateState()))
					return;
				renameProjectsIfNecessary();
				renameMetadataIfNecessary();
				presentStatusIfNeccessary();
			}
		} finally {
			reset();
		}
	}


	private boolean ensureEditorsSaved() {
		return CommonEditorUtility.promptToSaveAllDirtyEditors();
	}

	protected Set getReferencedProjects() {
		if (referencedProjects == null)
			computeReferencedProjects();
		return referencedProjects;
	}

	protected void computeReferencedProjects() {
		getModules();
		referencedProjects = new HashSet();
		for (int i = 0; i < modules.size(); i++) {
			//WorkbenchComponent module = (WorkbenchComponent) modules.get(i);
			//TODO fix up code here for modules instead of projects
//			EARNatureRuntime runtime = EARNatureRuntime.getRuntime(project);
//			if (runtime == null)
//				continue;
//			EAREditModel editModel = runtime.getEarEditModelForRead(this);
//			try {
//				referencedProjects.addAll(editModel.getModuleMappedProjects());
//			} finally {
//				editModel.releaseAccess(this);
//			}
		}
	}

	protected void renameMetadataIfNecessary() {
		if (!shouldRenameMetaData())
			return;
//		RenameModuleOperation op = getRenameModuleOperation();
//		IRunnableWithProgress runnable = WTPUIPlugin.getRunnableWithProgress(op);
//		ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(shell);
//
//		try {
//			monitorDialog.run(false, false, runnable);
//		} catch (InvocationTargetException e) {
//			handleException(e);
//		} catch (InterruptedException e) {
//			//Ignore
//		}
	}

	protected boolean shouldRenameMetaData() {
		if (renameProjectsFailedOrCancelled())
			return false;

		return primShouldRenameMetaData();
	}

	protected boolean renameProjectsFailedOrCancelled() {
		if (options == null || !options.shouldRenameProjects())
			return false;
		return renamedProjectsExist();
	}

	protected boolean renamedProjectsExist() {
		List renamedProjects = options.getAllProjectsToRename();
		for (int i = 0; i < renamedProjects.size(); i++) {
			IProject project = (IProject) renamedProjects.get(i);
			if (project.exists())
				return true;
		}
		return false;
	}

	protected void renameProjectsIfNecessary() {
		if (options == null || !options.shouldRenameProjects())
			return;
		J2EERenameResourceAction action = new J2EERenameResourceAction(shell);
		action.setNewName(options.getNewName());
		IStructuredSelection sel = new StructuredSelection(options.getAllProjectsToRename());
		action.selectionChanged(sel);
		action.run();

		// only web projects should have a context root
		String newContextRoot = options.getNewContextRoot();
		if (newContextRoot != null && options.shouldRenameProjects()) {
			//WorkbenchComponent module = (WorkbenchComponent) getModules().get(0);
			try {
				// TODO add server context root to the module model
				//module.setServerContextRoot(newContextRoot);		               		
			} catch (Throwable t) {
				//Ignore
			}
		}
	}

	public void handleException(InvocationTargetException e) {
		Logger.getLogger().logError(e);
		IStatus status = J2EEPlugin.newErrorStatus(IStatus.ERROR, RENAME_ERROR, e);
		ErrorDialog.openError(shell, RENAME_ERROR, RENAME_NOT_COMPLETED, status);
	}

	/**
	 * Update the action's enable state according to the current selection of the used selection
	 * provider.
	 */
	public void update() {
		IStructuredSelection selection = null;

		if (provider != null) {
			selection = (IStructuredSelection) provider.getSelection();
			super.update(selection);
		} else {
			selection = (IStructuredSelection) getSelection();

			if (selection == null) {
				setEnabled(false);
			} else {
				updateSelection(selection);
			}
		}
	}

	protected boolean isJ2EEApplicationProject(Object o) {
		if (o instanceof IProject) {
			IProject project = (IProject) o;
			IVirtualComponent component = ComponentCore.createComponent(project);
				if (IModuleConstants.JST_EAR_MODULE.equals(component.getComponentTypeId()))
					return true;
			}
		return false;
	}

	protected boolean validateState() {
		if (!primShouldRenameMetaData())
			return true;

//		IValidateEditListener listener = new ValidateEditListener(null, getRenameModuleOperation().getRenameEditModel());
//		listener.setShell(shell);
//		return listener.validateState().isOK();
		return false;
	}

	protected boolean primShouldRenameMetaData() {
		return options != null && (options.shouldRenameModules() || options.shouldRenameModuleDependencies());
	}

//	protected RenameModuleOperation getRenameModuleOperation() {
//		if (renameModuleOperation == null) {
//			renameModuleOperation = new RenameModuleOperation(options);
//		}
//		return renameModuleOperation;
//	}

	protected void presentStatusIfNeccessary() {
		IStatus status = null;

//		if (renameModuleOperation != null) {
//			status = renameModuleOperation.getStatus();
//		}

		if (status == null || status.isOK())
			return;

		ErrorDialog.openError(shell, null, null, status, IStatus.ERROR);
	}
}