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
 * Created on Apr 1, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.deploy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPluginResourceHandler;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.internal.emf.utilities.CommandContext;
import org.eclipse.wst.common.internal.emf.utilities.ICommand;
import org.eclipse.wst.common.internal.emf.utilities.ICommandContext;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;

import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class J2EEDeployOperation extends WTPOperation {

	private Object[] selection;
	private List multiStatus = new ArrayList();

	/**
	 *  
	 */
	public J2EEDeployOperation(Object[] deployableObjects) {
		super();
		selection = deployableObjects;
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {

		DeployerRegistry reg = DeployerRegistry.instance();

		List modules = DeployerRegistry.getSelectedModules(selection);
		monitor.beginTask(J2EEPluginResourceHandler.getString("J2EEDeployOperation_UI_0"), modules.size()); //$NON-NLS-1$
		for (int i = 0; i < modules.size(); i++) {

			EObject module = (EObject) modules.get(i);
			IProject proj = ProjectUtilities.getProject(module);
			IRuntime runtime = ServerCore.getProjectProperties(proj).getRuntimeTarget();
			if (runtime == null)
				continue;
			List visitors = reg.getDeployModuleExtensions(module, runtime);
			deploy(visitors, module, monitor);
			monitor.worked(1);
		}

	}

	/**
	 * @param visitors
	 * @param module
	 */
	private void deploy(List visitors, EObject module, IProgressMonitor monitor) {

		IProject proj = ProjectUtilities.getProject(module);
		IStatus main = addMainStatus(proj);
		for (int i = 0; i < visitors.size(); i++) {
			if (!(visitors.get(i) instanceof ICommand))
				continue;
			ICommand dep = (ICommand) visitors.get(i);
			ICommandContext ctx = new CommandContext(monitor, null, module.eResource().getResourceSet());
			dep.init(selection);

			monitor.setTaskName(J2EEPluginResourceHandler.getString("J2EEDeployOperation_1_UI_", new Object[]{proj.getName(), dep.getClass().getName()})); //$NON-NLS-1$
			try {
				dep.execute(proj, null, ctx);
				addOKStatus(dep.getClass().getName(), main);
			} catch (CoreException ex) {
				Logger.getLogger().logError(ex);
				monitor.setCanceled(true);
				Throwable statusException = (ex.getStatus().getException() != null) ? ex.getStatus().getException() : ex;
				addErrorStatus(ex.getStatus(), dep.getClass().getName(), statusException, main);
				continue;
			}
		}

	}

	/**
	 * @param proj
	 * @param name
	 */
	private void addOKStatus(String DeployerName, IStatus main) {

		IStatus statusLocal = new Status(IStatus.OK, " ", IStatus.OK, (J2EEPluginResourceHandler.getString("J2EEDeployOperation_2_UI_", new Object[]{DeployerName})), null); //$NON-NLS-1$ //$NON-NLS-2$		
		addStatus(statusLocal);

	}

	/**
	 * @param exceptionStatus
	 * @param proj
	 * @param name
	 */
	private void addErrorStatus(IStatus exceptionStatus, String DeployerName, Throwable ex, IStatus main) {

		if (exceptionStatus instanceof MultiStatus) {
			IStatus[] stati = ((MultiStatus) exceptionStatus).getChildren();
			for (int i = 0; 1 < stati.length; i++) {
				addErrorStatus(stati[i], DeployerName, stati[i].getException(), main);
			}
		}
		String errorNotes = (ex != null && ex.getMessage() != null) ? ex.getMessage() : main.getMessage();

		String message = J2EEPluginResourceHandler.getString("J2EEDeployOperation_3_UI_", new Object[]{DeployerName, errorNotes}); //$NON-NLS-1$
		IStatus statusLocal = new Status(IStatus.ERROR, J2EEPlugin.getPlugin().getPluginID(), IStatus.ERROR, message, ex); //$NON-NLS-1$

		addStatus(statusLocal);



	}

	private IStatus addMainStatus(IProject proj) {

		IStatus aStatus = new MultiStatus(J2EEPlugin.getPlugin().getPluginID(), IStatus.OK, J2EEPluginResourceHandler.getString("J2EEDeployOperation_4_UI_", new Object[]{proj.getName()}), null); //$NON-NLS-1$

		getMultiStatus().add(aStatus);
		return aStatus;
	}

	/**
	 * @return Returns the multiStatus.
	 */
	public List getMultiStatus() {
		return multiStatus;
	}

	/**
	 * @param multiStatus
	 *            The multiStatus to set.
	 */
	public void setMultiStatus(List multiStatus) {
		this.multiStatus = multiStatus;
	}
}