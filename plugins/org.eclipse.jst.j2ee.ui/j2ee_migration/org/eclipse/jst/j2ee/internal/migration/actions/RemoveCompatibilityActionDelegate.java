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
 * Created on Apr 27, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.migration.actions;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.common.util.CommonUtil;
import org.eclipse.jst.j2ee.internal.actions.AbstractActionDelegate;
import org.eclipse.jst.j2ee.internal.migration.RemoveCompatibilityOperation;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.common.migration.CompatibilityUtils;
import org.eclipse.wst.common.ui.WTPUIPlugin;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author nagrawal
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class RemoveCompatibilityActionDelegate extends AbstractActionDelegate {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.ui.actions.AbstractActionDelegate#isSupportedAction(java.lang.Object)
	 */
	ProgressMonitorDialog progressDialog = null;

	protected boolean isSupportedAction(Object element) {
		return CommonUtil.isDeploymentDescriptorRoot(element, true);
	}


	public void selectionChanged(org.eclipse.jface.action.IAction action, org.eclipse.jface.viewers.ISelection aSelection) {
		if (aSelection != null) {
			IStructuredSelection structuredSelection = (IStructuredSelection) aSelection;
			IProject project = ProjectUtilities.getProject(structuredSelection.getFirstElement());
			if (project != null) {
				boolean CompatibilityMode = CompatibilityUtils.getCompatibilityMode(project);
				action.setEnabled(CompatibilityMode);
				this.selection = aSelection;
			}
		}
	}


	/*
	 */
	protected void primRun(Shell shell) {
		if (MessageDialog.openQuestion(null, J2EEMigrationUIResourceHandler.getString("RemoveCompatibilityActionDelegate_UI_0"), //$NON-NLS-1$
					J2EEMigrationUIResourceHandler.getString("RemoveCompatibilityActionDelegate_UI_1")) == true) { //$NON-NLS-1$
			IStructuredSelection sel = getStructuredSelection();
			IProject project = ProjectUtilities.getProject(sel.getFirstElement());

			ProgressMonitorDialog dlg = new ProgressMonitorDialog(null);
			RemoveCompatibilityOperation operation = new RemoveCompatibilityOperation(project);
			try {
				dlg.run(true, false, WTPUIPlugin.getRunnableWithProgress(operation));
			} catch (InvocationTargetException e) {
				Logger.getLogger().logError(e);

			} catch (InterruptedException e) {
				Logger.getLogger().logError(e);
			}
		}
	}
}