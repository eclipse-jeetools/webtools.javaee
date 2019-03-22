/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator.ear;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.dialogs.PropertyDialog;

public class ShowDepPropPageAction implements IObjectActionDelegate
{

	private ISelection sel;	
	
    public void selectionChanged(IAction action, ISelection selection) {
        sel = selection;
    }

    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    }
	  
	public void run(IAction action) {
		if (sel == null){
			return;
		}
		Object selectedObject = ((IStructuredSelection) sel).getFirstElement();
		if (selectedObject instanceof ModulesNode) {
			IProject earProject = ((ModulesNode)selectedObject).getEarProject();
			PreferenceDialog dialog = PropertyDialog.createDialogOn(PlatformUI.getWorkbench().getActiveWorkbenchWindow().
                    getShell(), "org.eclipse.wst.common.componentcore.ui.DeploymentAssemblyPage", earProject); //$NON-NLS-1$
			if (dialog != null) {
				dialog.open();
			}
		}
	}
	
}
