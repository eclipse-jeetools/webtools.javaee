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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jdt.ui.actions.SelectionDispatchAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.internal.common.util.CommonUtil;
import org.eclipse.jst.j2ee.internal.delete.DeleteModuleOperation;
import org.eclipse.jst.j2ee.internal.delete.DeleteOptions;
import org.eclipse.jst.j2ee.internal.dialogs.DeleteEARDialog;
import org.eclipse.jst.j2ee.internal.dialogs.DeleteModuleDialog;
import org.eclipse.jst.j2ee.internal.dialogs.J2EEDeleteDialog;
import org.eclipse.jst.j2ee.internal.dialogs.J2EEDeleteUIConstants;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.plugin.CommonEditorUtility;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.actions.DeleteResourceAction;
import org.eclipse.ui.actions.SelectionListenerAction;
import org.eclipse.wst.common.frameworks.internal.ui.WTPUIPlugin;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

public class J2EEDeleteAction extends SelectionDispatchAction implements J2EEDeleteUIConstants {

	protected Shell shell;
	//Used for EAR delete
	protected Set referencedProjects;
	protected List projects;
	protected DeleteOptions options;
	// added for IRefactoringAction behavior
	protected ISelectionProvider provider = null;
	protected DeleteModuleOperation deleteModuleOperation;


	/**
	 * Constructor for DeleteModuleAction.
	 * 
	 * @param text
	 */
	public J2EEDeleteAction(IWorkbenchSite site, Shell parent) {
		super(site);
		setText(DELETE);
		shell = parent;
	}

	public J2EEDeleteAction(IWorkbenchSite site, ISelectionProvider newProvider) {
		super(site);
		setText(DELETE);
		shell = J2EEUIPlugin.getActiveWorkbenchWindow().getShell();
		provider = newProvider;
	}

	protected void reset() {
		referencedProjects = null;
		projects = null;
		options = null;
		if (deleteModuleOperation != null) {
			deleteModuleOperation.release();
			deleteModuleOperation = null;
		}
	}

	protected boolean getEnableStateBasedOnSelection(IStructuredSelection selection) {
		if (selection.isEmpty())
			return false;
		return isSelectionApplicable() && isSelectionSomeJ2EE();
	}

	/**
	 * @see SelectionListenerAction#updateSelection(IStructuredSelection)
	 */
	protected void updateSelection(IStructuredSelection selection) {
		update(selection);
	}

	protected boolean isSelectionApplicable() {
		IStructuredSelection sel = (StructuredSelection) getSelection();
		Iterator it = sel.iterator();
		while (it.hasNext()) {
			Object o = it.next();
			if (!isJ2EEModule(o) && !isApplication(o) && !isProjectOrJavaProject(o))
				return false;
		}
		return true;
	}

	protected boolean isSelectionSomeJ2EE() {
		IStructuredSelection sel = (StructuredSelection) getSelection();
		Iterator it = sel.iterator();
		while (it.hasNext()) {
			Object o = it.next();
			if (isJ2EEModule(o) || isApplication(o))
				return true;
		}
		return false;
	}


	protected boolean isSelectionAllDDRoots() {
		IStructuredSelection sel = (StructuredSelection) getSelection();
		Iterator it = sel.iterator();
		while (it.hasNext()) {
			Object o = it.next();
			if (!isJ2EEModule(o))
				return false;
		}
		return true;
	}

	protected boolean isSelectionAllApplications() {
		IStructuredSelection sel = (StructuredSelection) getSelection();
		Iterator it = sel.iterator();
		while (it.hasNext()) {
			Object o = it.next();
			if (!isApplication(o))
				return false;
		}
		return true;
	}

	protected boolean isJ2EEModule(Object o) {
		if (o instanceof WorkbenchComponent) {
			WorkbenchComponent module = (WorkbenchComponent) o;
			String moduleType = module.getComponentType().getModuleTypeId();
			//TODO need to add connector, app client, ear, ejb client
			return moduleType.equals(IModuleConstants.JST_WEB_MODULE) || moduleType.equals(IModuleConstants.JST_EJB_MODULE);
		}
		return CommonUtil.isDeploymentDescriptorRoot(o, false);
	}

	protected boolean isApplication(Object o) {
		return (o instanceof Application) || isJ2EEApplicationProject(o);
	}

	protected boolean isProjectOrJavaProject(Object o) {
		return (o instanceof IProject) || (o instanceof IJavaProject);
	}

	protected List getProjects() {
		if (projects == null) {
			projects = new ArrayList();
			IStructuredSelection sel = (StructuredSelection) getSelection();
			Iterator iterator = sel.iterator();
			IProject project = null;
			Object o = null;
			while (iterator.hasNext()) {
				o = iterator.next();
				if (o instanceof JavaProject)
					o = ((JavaProject) o).getProject();
				if (o instanceof IProject) {
					projects.add(o);
					addEJBClientProjectIfNecessary((IProject) o, projects);
				} else if (o instanceof EObject) {
					EObject obj = (EObject) o;
					project = ProjectUtilities.getProject(obj);
					if (project == null)
						throw new RuntimeException(J2EEUIMessages.getResourceString("Project_should_not_be_null_1_EXC_")); //$NON-NLS-1$
					projects.add(project);
					addEJBClientProjectIfNecessary(project, projects);
				} else {
					throw new RuntimeException(J2EEUIMessages.getResourceString("Non-project_in_selection_2_EXC_")); //$NON-NLS-1$
				}
			}
		}
		return projects;
	}

	/**
	 * @param project
	 * @param localProjects
	 */
	private void addEJBClientProjectIfNecessary(IProject project, List localProjects) {
		EJBNatureRuntime nature = EJBNatureRuntime.getRuntime(project);
		if (nature != null && nature.hasEJBClientJARProject())
			localProjects.add(nature.getEJBClientJARProject());
	}

	/**
	 * @see IAction#run()
	 */
	public void run() {
		try {
			J2EEDeleteDialog dlg = null;
			if (isSelectionAllApplications())
				dlg = new DeleteEARDialog(shell, getReferencedProjects());
			else if (isSelectionAllDDRoots())
				dlg = new DeleteModuleDialog(shell);
			else {
				if (confirmStandardDelete())
					runResourceDeleteAction();
				return;
			}

			dlg.open();
			if (dlg.getReturnCode() == Window.CANCEL)
				return;
			options = dlg.getDeleteOptions();
			if (options != null)
				options.setSelectedProjects(getProjects());
			else
				return;
			if (!(ensureEditorsSaved() && validateState()))
				return;
			deleteProjectsIfNecessary();
			deleteMetadataIfNecessary();
			presentStatusIfNeccessary();
		} finally {
			reset();
		}
	}

	private boolean ensureEditorsSaved() {
		return CommonEditorUtility.promptToSaveAllDirtyEditors();
	}

	protected boolean confirmStandardDelete() {
		return MessageDialog.openConfirm(shell, DELETE_PROJECTS, CUSTOM_DELETE_MIX_MATCH);
	}

	protected Set getReferencedProjects() {
		if (referencedProjects == null)
			computeReferencedProjects();
		return referencedProjects;
	}

	protected void computeReferencedProjects() {
		getProjects();
		referencedProjects = new HashSet();
		for (int i = 0; i < projects.size(); i++) {
			IProject project = (IProject) projects.get(i);
			EARNatureRuntime runtime = EARNatureRuntime.getRuntime(project);
			if (runtime == null)
				continue;
			EAREditModel editModel = runtime.getEarEditModelForRead(this);
			try {
				referencedProjects.addAll(editModel.getModuleMappedProjects());
			} finally {
				editModel.releaseAccess(this);
			}
		}
	}

	protected DeleteModuleOperation getDeleteModuleOperation() {
		if (deleteModuleOperation == null)
			deleteModuleOperation = new DeleteModuleOperation(options);
		return deleteModuleOperation;
	}

	protected void deleteMetadataIfNecessary() {
		if (!shouldDeleteMetaData())
			return;

		IRunnableWithProgress runnable = WTPUIPlugin.getRunnableWithProgress(getDeleteModuleOperation());
		ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(shell);

		try {
			monitorDialog.run(false, false, runnable);
		} catch (InvocationTargetException e) {
			handleException(e);
		} catch (InterruptedException e) {
			//Ignore
		}
	}

	protected boolean shouldDeleteMetaData() {
		if (deleteProjectsFailedOrCancelled())
			return false;
		return primShouldDeleteMetaData();
	}

	protected boolean primShouldDeleteMetaData() {
		return options != null && (options.shouldDeleteModules() || options.shouldDeleteModuleDependencies());
	}

	protected boolean deleteProjectsFailedOrCancelled() {
		if (options == null || !options.shouldDeleteProjects())
			return false;
		return deletedProjectsExist();
	}

	protected boolean deletedProjectsExist() {
		List deletedProjects = options.getAllProjectsToDelete();
		for (int i = 0; i < deletedProjects.size(); i++) {
			IProject project = (IProject) deletedProjects.get(i);
			if (project.exists())
				return true;
		}
		return false;
	}

	protected void deleteProjectsIfNecessary() {
		if (options.shouldDeleteProjects())
			runResourceDeleteAction();
	}

	protected void runResourceDeleteAction() {
		DeleteResourceAction action = new DeleteResourceAction(shell);
		List localProjects = null;
		if (options == null)
			localProjects = getProjects();
		else
			localProjects = options.getAllProjectsToDelete();
		Iterator it = localProjects.iterator();
		while (it.hasNext()) {
			IProject p = (IProject) it.next();
			if (!p.exists())
				it.remove();
		}
		IStructuredSelection sel = new StructuredSelection(localProjects);
		action.selectionChanged(sel);
		action.run();
	}

	public void handleException(InvocationTargetException e) {
		Logger.getLogger().logError(e);
		IStatus status = J2EEPlugin.newErrorStatus(IStatus.ERROR, DELETE_ERROR, e);
		ErrorDialog.openError(shell, DELETE_ERROR, DELETE_NOT_COMPLETED, status);
	}

	/**
	 * Update the action's enable state according to the current selection of the used selection
	 * provider.
	 */
	public void update() {
		IStructuredSelection selection = null;

		if (provider != null) {
			selection = (IStructuredSelection) provider.getSelection();
			selectionChanged((ISelection) selection);
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
		boolean retVal = false;
		if (o instanceof IProject) {
			IProject project = (IProject) o;
			if (EARNatureRuntime.hasRuntime(project))
				retVal = true;
		}
		return retVal;
	}

	protected void setEnabledFromSelection(IStructuredSelection selection) {
		if (selection == null) {
			setEnabled(false);
		} else {
			setEnabled(getEnableStateBasedOnSelection(selection));
		}
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

	protected boolean validateState() {
		// TODO Fix validateState
		//		if (!primShouldDeleteMetaData())
		//			return true;
		//		
		//		IValidateEditListener listener = new ValidateEditListener(null,
		// getDeleteModuleOperation().getDeleteEditModel());
		//		listener.setShell(shell);
		//		return listener.validateState().isOK();
		return true;
	}

	protected void presentStatusIfNeccessary() {
		IStatus status = null;
		if (deleteModuleOperation != null)
			status = deleteModuleOperation.getStatus();

		if (status == null || status.isOK())
			return;

		ErrorDialog.openError(shell, null, null, status, IStatus.ERROR);
	}
}