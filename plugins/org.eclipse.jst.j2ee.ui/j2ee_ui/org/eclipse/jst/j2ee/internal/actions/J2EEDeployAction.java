/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.deploy.DeployerRegistry;
import org.eclipse.jst.j2ee.internal.deploy.J2EEDeployOperation;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.common.frameworks.internal.WTPResourceHandler;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.server.core.IRuntime;

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
			final IStructuredSelection deploySelection = selection;
			Job deployJob = new Job("Deploy") {
				protected IStatus run(IProgressMonitor monitor) {
					IStatus result = null;
					J2EEDeployOperation op = new J2EEDeployOperation(deploySelection.toArray());
					try {
						result = op.execute(monitor, null);
					} catch (Exception e) {
						result = new Status(IStatus.ERROR, WTPCommonPlugin.PLUGIN_ID, IStatus.ERROR, WTPResourceHandler.getString("27"), e); //$NON-NLS-1$
						Logger.getLogger().logError(e);
					} finally {
						
					}
					return result;
				}
			};
			
			
			try {
				deployJob.setUser(true);
				deployJob.schedule();
			} catch (Exception e) {
				//Ignore
			}
			
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
				IRuntime runtime = J2EEProjectUtilities.getServerRuntime(proj);
				if (proj == null || runtime == null)
					return false;
				List visitors = reg.getDeployModuleExtensions(module, runtime);
				if (!visitors.isEmpty())
					return true;
			}
			return false;
		} catch (CoreException e) {
			System.out.println("Deploy Action recovering from problem verifying enablement."); //$NON-NLS-1$
			e.printStackTrace();
		}
		return false;
	}

}
