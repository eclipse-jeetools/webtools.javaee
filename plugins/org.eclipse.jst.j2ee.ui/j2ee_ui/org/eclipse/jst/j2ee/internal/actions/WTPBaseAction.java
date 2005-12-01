/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jul 7, 2004
 * 
 * TODO To change the template for this generated file go to Window - Preferences - Java - Code
 * Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.actions;


import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.plugin.ErrorDialog;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate2;
import org.eclipse.ui.IWorkbenchWindow;



public abstract class WTPBaseAction extends Action implements IActionDelegate2 {
	private final static String ERROR_OCCURRED_TITLE = J2EEUIMessages.getResourceString("ERROR_OCCURRED_TITLE"); //$NON-NLS-1$
	private final static String ERROR_OCCURRED_MESSAGE = J2EEUIMessages.getResourceString("ERROR_OCCURRED_MESSAGE"); //$NON-NLS-1$

	protected IStructuredSelection selection = null;

	protected IWorkbenchWindow getWorkbenchWindow() {
		return J2EEUIPlugin.getPluginWorkbench().getActiveWorkbenchWindow();
	}

	public void setSelection(IStructuredSelection selection) {
		this.selection = selection;
	}

	public void run() {
		Shell shell = getWorkbenchWindow().getShell();
		if (null == selection) {
			ISelection autoselection = getWorkbenchWindow().getSelectionService().getSelection();
			if (autoselection instanceof IStructuredSelection)
				this.selection = (IStructuredSelection) autoselection;
		}

		try {
			primRun(shell);
			this.selection = null;
		} catch (Throwable t) {
			ErrorDialog.openError(shell, ERROR_OCCURRED_TITLE, ERROR_OCCURRED_MESSAGE, t, 0, false);
		}

	}

	protected abstract void primRun(Shell shell);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate2#dispose()
	 */
	public void dispose() {
		//dispose
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate2#init(org.eclipse.jface.action.IAction)
	 */
	public void init(IAction action) {
		//init
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection aSelection) {
		setSelection((IStructuredSelection) aSelection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate2#runWithEvent(org.eclipse.jface.action.IAction,
	 *      org.eclipse.swt.widgets.Event)
	 */
	public void runWithEvent(IAction action, Event event) {
		run();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		run();
	}

	/**
	 * @return Returns the selection.
	 */
	protected IStructuredSelection getSelection() {
		return selection;
	}
}
