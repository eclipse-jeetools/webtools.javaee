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

import java.text.MessageFormat;
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
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.componentcore.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.internal.deploy.DeployerRegistry;
import org.eclipse.jst.j2ee.internal.deploy.J2EEDeployOperation;
import org.eclipse.jst.j2ee.internal.dialogs.RuntimeSelectionDialog;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
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

		if (checkEnabled(shell)) {
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
		super.selectionChanged(action, aSelection);
		action.setEnabled(true);
	}

	
	public boolean checkEnabled(Shell shell) {
		try {
			DeployerRegistry reg = DeployerRegistry.instance();
			Object[] modules = selection.toArray();
			for (int i = 0; i < modules.length; i++) {
				Object object = modules[i];
				if (object instanceof EObject) {
					object = ProjectUtilities.getProject(object);
				}
				if (!(object instanceof IProject)) {
					continue;
				}
				IVirtualComponent component = ComponentCore.createComponent((IProject) object);
				EnterpriseArtifactEdit edit = null;
				try {
					edit = (EnterpriseArtifactEdit) ComponentUtilities.getArtifactEditForRead(component);
					if (edit == null)
						continue;
					EObject module = edit.getDeploymentDescriptorRoot();
					IProject proj = (IProject) object;
					if (proj == null) {
						displayMessageDialog(J2EEUIMessages.getResourceString("DEPLOY_PROJECT_NOT_FOUND"), shell);
						return false;
					}
					IRuntime runtime = J2EEProjectUtilities.getServerRuntime(proj);
					if (runtime == null) {
						String message = MessageFormat.format(J2EEUIMessages.getResourceString("DEPLOY_RUNTIME_NOT_FOUND"), new String[] { proj.getName() });
						RuntimeSelectionDialog selectionDialog = new RuntimeSelectionDialog( shell,
								J2EEUIMessages.getResourceString("DEPLOY_DIALOG_TITLE"),
								null /* default image */, message,
								MessageDialog.ERROR,
								new String[] { IDialogConstants.OK_LABEL }, 0, proj);
						selectionDialog.open();
						runtime = J2EEProjectUtilities.getServerRuntime(proj);
						if (runtime == null)
							return false;
					}
					List visitors = reg.getDeployModuleExtensions(module, runtime);
					if (visitors.isEmpty()) {
						displayMessageDialog( MessageFormat.format( J2EEUIMessages.getResourceString("DEPLOY_PROJECT_NOT_SUPPORTED"),
																				new String[] { proj.getName() }), shell);
						return false;
					}
				} finally {
					if (edit != null)
						edit.dispose();
				}
			}
			return true;
		} catch (CoreException e) {
			J2EEPlugin.log(4, -1, "Deploy Action recovering from problem verifying enablement.", e); //$NON-NLS-1$
			e.printStackTrace();
		}
		return false;
	}
	
	private void displayMessageDialog(String message, Shell shell) {
		 String title = J2EEUIMessages.getResourceString("DEPLOY_DIALOG_TITLE");
		 MessageDialog dialog = new MessageDialog(shell, 
				 								title, 
				 								null /* default image */, 
				 								message, 
				 								MessageDialog.ERROR, 
				 								new String[] { IDialogConstants.OK_LABEL }, 0) ;
	     dialog.open();
	}

}
