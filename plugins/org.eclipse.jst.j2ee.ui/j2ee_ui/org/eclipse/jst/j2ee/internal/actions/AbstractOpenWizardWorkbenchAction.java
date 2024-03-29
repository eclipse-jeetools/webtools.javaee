/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.actions;


import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;


public abstract class AbstractOpenWizardWorkbenchAction extends AbstractOpenWizardAction implements org.eclipse.ui.IWorkbenchWindowActionDelegate {
	protected AbstractOpenWizardWorkbenchAction() {
		//Default
	}

	public AbstractOpenWizardWorkbenchAction(IWorkbench workbench, String label, Class[] activatedOnTypes, boolean acceptEmptySelection) {
		super(workbench, label, null, acceptEmptySelection);
	}

	public AbstractOpenWizardWorkbenchAction(IWorkbench workbench, String label, boolean acceptEmptySelection) {
		super(workbench, label, null, acceptEmptySelection);
	}

	/**
	 * @see AbstractOpenWizardAction#dispose
	 */
	@Override
	public void dispose() {
		// do nothing.
		setWorkbench(null);
	}

	/**
	 * @see AbstractOpenWizardAction#init
	 */
	@Override
	public void init(IWorkbenchWindow window) {
		setWorkbench(window.getWorkbench());
	}

	/**
	 * @see IActionDelegate#run
	 */
	@Override
	public void run(IAction action) {
		run();
	}

	/**
	 * @see IActionDelegate#selectionChanged
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// do nothing. Action doesn't depend on selection.
	}
}
