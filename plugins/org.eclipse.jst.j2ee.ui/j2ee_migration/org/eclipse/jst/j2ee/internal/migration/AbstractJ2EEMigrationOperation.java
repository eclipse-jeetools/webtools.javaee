/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.migration;


import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jst.j2ee.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.migration.J2EEMigrationStatus;
import org.eclipse.wst.common.framework.operation.IHeadlessRunnableWithProgress;
import org.eclipse.wst.common.framework.operation.IOperationHandler;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation;

public abstract class AbstractJ2EEMigrationOperation extends EditModelOperation {

	protected J2EEMigrationStatus status;
	protected IOperationHandler operationHandler;
	protected EjbModuleExtensionHelper modHelper;
	protected IProject currentProject;

	public AbstractJ2EEMigrationOperation(J2EEMigrationConfig config, IOperationHandler opHandler) {
		super(config);
		operationHandler = opHandler;
	}

	public AbstractJ2EEMigrationOperation() {
		super();
	}

	/**
	 * Returns the status.
	 * 
	 * @return J2EEMigrationStatus
	 */
	public J2EEMigrationStatus getMigrationStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 * 
	 * @param status
	 *            The status to set
	 */
	public void setStatus(J2EEMigrationStatus status) {
		this.status = status;
	}

	public String format(String aPattern, String arg1) {
		return format(aPattern, new String[]{arg1});
	}

	protected String format(String aPattern, String arg1, String arg2) {
		return MessageFormat.format(aPattern, new String[]{arg1, arg2});
	}

	protected String format(String aPattern, String arg1, String arg2, String arg3) {
		return MessageFormat.format(aPattern, new String[]{arg1, arg2, arg3});
	}

	protected String format(String aPattern, String[] args) {
		return MessageFormat.format(aPattern, args);
	}

	public void appendStatus(J2EEMigrationStatus aStatus) {
		if (aStatus == null)
			return;
		if (status == null)
			status = aStatus;
		else
			status = status.append(aStatus);
	}

	public void appendStatus(int aSeverity, String message) {
		J2EEMigrationStatus newStatus = new J2EEMigrationStatus(aSeverity, message);
		appendStatus(newStatus);
	}

	protected void executeNestedOperation(IProgressMonitor monitor, AbstractJ2EEMigrationOperation op, int ticks) throws InvocationTargetException, InterruptedException {
		op.run(new SubProgressMonitor(monitor, ticks, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK));
		appendStatus(op.getMigrationStatus());
	}

	protected void executeNestedOperation(IProgressMonitor monitor, IHeadlessRunnableWithProgress op, int ticks) throws InvocationTargetException, InterruptedException {
		op.run(new SubProgressMonitor(monitor, ticks, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK));
	}

	/**
	 * @return-load and return the EJBModuleExtensionsHelper from the WAS extensions plugin
	 */
	protected void initializeModuleExtensionHelper() {
		if (modHelper == null)
			modHelper = IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
	}

	/**
	 * @return Returns the currentProject.
	 */
	public IProject getCurrentProject() {
		return currentProject;
	}

	/**
	 * @param currentProject
	 *            The currentProject to set.
	 */
	public void setCurrentProject(IProject currentProject) {
		this.currentProject = currentProject;
	}
}