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
package org.eclipse.jst.j2ee.internal.migration;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.wst.common.framework.operation.IOperationHandler;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 *  
 */
public class JaxrpcmapMigrationOperation extends J2EEMigrationOperation {
	public JaxrpcmapMigrationOperation() {
		super();
	}

	public JaxrpcmapMigrationOperation(J2EEMigrationConfig config, IOperationHandler handler) {
		super(config, handler);
		migrationConfig = config;
	}

	/**
	 *  
	 */
	protected void execute(IProgressMonitor aMonitor) throws CoreException, InvocationTargetException, InterruptedException {
		migrationConfig = (J2EEMigrationConfig) getOperationDataModel();
		monitor = aMonitor;
		if (!verifyFilesInSync()) {
			monitor.done();
			return;
		}
		monitor.beginTask("", getTotalWeight()); //$NON-NLS-1$
		boolean autoFlag = ResourcesPlugin.getWorkspace().isAutoBuilding();
		try {
			if (autoFlag)
				ProjectUtilities.turnAutoBuildOff();

			//Perform version migration
			execProjectVersionIfNecessary();

		} finally {
			migrationConfig.dispose();
			if (autoFlag)
				ProjectUtilities.turnAutoBuildOn();
			monitor.done();
		}
	}

	/**
	 * Method execProjectVersionIfNecessary.
	 */
	private void execProjectVersionIfNecessary() throws InvocationTargetException, InterruptedException {
		if (!includesVersionMigration())
			return;
		JaxrpcmapMetadataMigrationOperation op = null;
		op = new JaxrpcmapMetadataMigrationOperation(migrationConfig, getSelectedJ2EEVersion(), operationHandler);
		ComposedMigrationConfig composed = (ComposedMigrationConfig) migrationConfig;
		J2EEComposedMigrationOperation composedOp = new J2EEComposedMigrationOperation(composed, operationHandler);
		List childModels = composed.getChildren();
		if (!childModels.isEmpty()) {
			if (containsEAR(migrationConfig) && migrationConfig.shouldMigrateJ2EEVersion())
				composedOp.addRunnable(op);
			boolean addedChildOperations = false;
			for (int i = 0; i < childModels.size(); i++) {
				J2EEMigrationConfig childConfig = (J2EEMigrationConfig) childModels.get(i);
				if (childConfig.shouldMigrateJ2EEVersion() && childConfig.isSelected()) {
					JaxrpcmapMetadataMigrationOperation childOp = new JaxrpcmapMetadataMigrationOperation(childConfig, getSelectedJ2EEVersion(), operationHandler);
					composedOp.addRunnable(childOp);
					addedChildOperations = true;
				}
			}
			if (addedChildOperations)
				executeNestedOperation(monitor, composedOp, getProjectMetaWeight());
		} else
			executeNestedOperation(monitor, op, getProjectMetaWeight());

	}

	private boolean containsEAR(J2EEMigrationConfig config) {
		IProject project = config.getTargetProject();
		if (project != null)
			return EARNatureRuntime.hasRuntime(project);
		return false;
	}
}