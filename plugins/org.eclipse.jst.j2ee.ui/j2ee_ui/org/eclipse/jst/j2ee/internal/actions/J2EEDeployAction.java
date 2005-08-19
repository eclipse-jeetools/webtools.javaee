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
 * Created on Mar 30, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.actions;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jst.j2ee.internal.deploy.DeployerRegistry;
import org.eclipse.jst.j2ee.internal.deploy.J2EEDeployOperation;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class J2EEDeployAction extends BaseAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.ui.actions.BaseAction#primRun(org.eclipse.swt.widgets.Shell)
	 */
	protected void primRun(Shell shell) {

		if (isEnabled()) {
			J2EEDeployOperation op = new J2EEDeployOperation(selection.toArray());
			//IRunnableWithProgress runnable = WTPUIPlugin.getRunnableWithProgress(op);
			//J2EEDeployStatusDialog dialog = new
			// J2EEDeployStatusDialog(shell,op.getMultiStatus());
			try {
				op.execute(new NullProgressMonitor(), null);
			} catch (Exception e) {
				//Ignore
			}
			//TODO fix up job scheduler?
//			WTPOperationJobAdapter jobAdapter = new WTPOperationJobAdapter(op);
//			jobAdapter.setName(J2EEPluginResourceHandler.getString("J2EEDeployOperation_UI_0"));//$NON-NLS-1$
//			IWorkbench workbench = J2EEUIPlugin.getDefault().getWorkbench();
//			IProgressService progressService = workbench.getProgressService();
//			jobAdapter.setPriority(Job.INTERACTIVE);
//			progressService.showInDialog(workbench.getActiveWorkbenchWindow().getShell(), jobAdapter);
//			jobAdapter.schedule();
		}

	}

	/*
	 *  
	 */
	public J2EEDeployAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection aSelection) {
		// TODO Auto-generated method stub
		super.selectionChanged(action, aSelection);
		action.setEnabled(isEnabled());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#isEnabled()
	 */
	public boolean isEnabled() {

		try {
			DeployerRegistry reg = DeployerRegistry.instance();

			List modules = DeployerRegistry.getSelectedModules(selection.toArray());
			for (int i = 0; i < modules.size(); i++) {
				EObject module = (EObject) modules.get(i);
				IProject proj = ProjectUtilities.getProject(module);
				IRuntime runtime = ServerCore.getProjectProperties(proj).getRuntimeTarget();
				if (proj == null || runtime == null)
					return false;
				List visitors = reg.getDeployModuleExtensions(module, runtime);
				if (!visitors.isEmpty())
					return true;
			}
			return false;
		} catch (RuntimeException e) {
			System.out.println("Deploy Action recovering from problem verifying enablement."); //$NON-NLS-1$
			e.printStackTrace();
		}
		return false;
	}

}