/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.actions;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;


public abstract class AbstractActionDelegate implements org.eclipse.ui.IActionDelegate {
	protected ISelection selection;
	protected boolean allowsMultiSelect = false;
	public static final Class IPROJECT_CLASS = IProject.class;
	private final static String ERROR_OCCURRED_TITLE = J2EEUIMessages.getResourceString("ERROR_OCCURRED_TITLE"); //$NON-NLS-1$
	private final static String ERROR_OCCURRED_MESSAGE = J2EEUIMessages.getResourceString("ERROR_OCCURRED_MESSAGE"); //$NON-NLS-1$

	/**
	 * EditModuleDependencyAction constructor comment.
	 */
	public AbstractActionDelegate() {
		super();
	}

	/**
	 * Applicable only when this action does not allow multi select
	 */
	protected IProject getProject() {
		return getProject(getSelectedElement());
	}

	protected List getProjects() {
		if ((selection == null) || !(selection instanceof IStructuredSelection))
			return Collections.EMPTY_LIST;

		List result = new ArrayList();
		IStructuredSelection struct = (IStructuredSelection) selection;
		Iterator it = struct.iterator();
		while (it.hasNext()) {
			IProject p = getProject(it.next());
			if (p != null)
				result.add(p);
		}
		return result;
	}

	protected IProject getProject(Object element) {

		if (isSupportedAction(element))
			return ProjectUtilities.getProject((EObject) element);
		else if (element instanceof IAdaptable)
			return (IProject) ((IAdaptable) element).getAdapter(IPROJECT_CLASS);
		else
			return null;
	}

	protected IStructuredSelection getStructuredSelection() {
		if ((selection == null) || !(selection instanceof IStructuredSelection))
			return null;
		else if (selection.isEmpty()) {
			selection = J2EEUIPlugin.getCurrentSelection();
		}
		return (IStructuredSelection) selection;
	}

	/*
	 * Only return if there is exactly one item selected
	 */
	protected Object getSelectedElement() {
		IStructuredSelection sel = getStructuredSelection();
		return sel == null ? null : sel.getFirstElement();
	}

	protected IWorkbenchWindow getWorkbenchWindow() {
		return J2EEUIPlugin.getActiveWorkbenchWindow();
	}

	/**
	 * Can the receiver be executed for
	 * 
	 * @element
	 */
	protected abstract boolean isSupportedAction(Object element);

	/**
	 * @deprecated use {@link #primRun(Shell)}
	 */
	protected void primRun(IProject project, Shell shell) {
		//Deprecated
	}

	/**
	 * Subclasses should override this instead of {@link #run(org.eclipse.jface.action.IAction)}
	 */
	protected void primRun(Shell shell) {
		primRun(getProject(), shell);
	}

	/**
	 * Performs this action.
	 * <p>
	 * This method is called when the delegating action has been triggered. Implement this method to
	 * do the actual work.
	 * </p>
	 * 
	 * @param action
	 *            the action proxy that handles the presentation portion of the action
	 */
	public void run(org.eclipse.jface.action.IAction action) {

		Shell shell = getWorkbenchWindow().getShell();
		setActionStateFromProjects(action);
		if (!action.isEnabled())
			MessageDialog.openInformation(shell, J2EEUIMessages.getResourceString("INFORMATION_UI_"), J2EEUIMessages.getResourceString("CHOSEN_OP_NOT_AVAILABLE")); //$NON-NLS-2$ = "The chosen operation is not currently available." //$NON-NLS-1$ = "Information"
		else {
			try {
				primRun(shell);
			} catch (Throwable t) {
				org.eclipse.jst.j2ee.internal.plugin.ErrorDialog.openError(shell, ERROR_OCCURRED_TITLE, ERROR_OCCURRED_MESSAGE, t, 0, false);
			}
		}


	}

	/**
	 * Notifies this action delegate that the selection in the workbench has changed.
	 * <p>
	 * Implementers can use this opportunity to change the availability of the action or to modify
	 * other presentation properties.
	 * </p>
	 * 
	 * @param action
	 *            the action proxy that handles presentation portion of the action
	 * @param aSelection
	 *            the current selection in the workbench
	 */
	public void selectionChanged(org.eclipse.jface.action.IAction action, org.eclipse.jface.viewers.ISelection aSelection) {
		this.selection = aSelection;
		setActionState(action);
	}

	protected void setActionState(IAction action) {
		if (allowsMultiSelect)
			setActionStateForMultiSelect(action);
		else
			setActionStateForSingleSelect(action);
	}

	protected void setActionStateForSingleSelect(IAction action) {
		IStructuredSelection sel = getStructuredSelection();
		if (sel == null || sel.size() != 1) {
			action.setEnabled(false);
			return;
		}
		setActionStateFromProjects(action);
	}

	protected void setActionStateForMultiSelect(IAction action) {
		setActionStateFromProjects(action);
	}

	protected void setActionStateFromProjects(IAction action) {
		IStructuredSelection sel = getStructuredSelection();
		boolean allOk = false;
		if (sel != null && !sel.isEmpty()) {
			allOk = true;
			Iterator it = sel.iterator();
			while (it.hasNext()) {
				Object o = it.next();
				IProject project = getProject(o);
				if (project == null || !project.isOpen()) {
					allOk = false;
					break;
				}
			}
		}
		action.setEnabled(allOk);
	}

	/**
	 * Returns the allowsMultiSelect.
	 * 
	 * @return boolean
	 */
	public boolean allowsMultiSelect() {
		return allowsMultiSelect;
	}


	/**
	 * Sets the allowsMultiSelect.
	 * 
	 * @param allowsMultiSelect
	 *            The allowsMultiSelect to set
	 */
	public void setAllowsMultiSelect(boolean allowsMultiSelect) {
		this.allowsMultiSelect = allowsMultiSelect;
	}


}