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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.internal.migration.actions.IJ2EEMigrationConstants;
import org.eclipse.jst.j2ee.migration.J2EEMigrationStatus;
import org.eclipse.jst.j2ee.migration.J2EESpecificationMigrationConstants;
import org.eclipse.wst.common.framework.operation.IOperationHandler;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * Operation to migrate any jaxrpcmap descriptors from J2EE 1.3 to 1.4
 */
public class JaxrpcmapMetadataMigrationOperation extends J2EEProjectMetadataMigrationOperation implements IJ2EEMigrationConstants {
	public JaxrpcmapMetadataMigrationOperation(J2EEMigrationConfig config, String aTargetVersion, IOperationHandler handler) {
		super(config, aTargetVersion, handler);
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		progressMonitor = monitor;
		if (migrationConfigs == null && migrationConfigs.isEmpty() && targetVersion == null)
			return;
		monitor.beginTask(MIGRATING_J2EE_VERSION, migrationConfigs.size() * 3);
		try {
			migrateProjectVersions();
		} finally {
			monitor.done();
		}
	}

	protected void migrateProjectVersion(J2EEMigrationConfig config) {
		IProject project = config.getTargetProject();
		// if project is binary, migration is not possible
		if (ProjectUtilities.isBinaryProject(project)) {
			appendStatus(J2EEMigrationStatus.WARNING, format(BINARY_MIGRATION_FAILED, project.getName()));
			return;
		}
		tagRootAsMigrating();
		try {
			progressMonitor.subTask(project.getName());
			migrateDeploymentDescriptor();
			progressMonitor.worked(1);
		} catch (Exception e) {
			Logger.getLogger().logError(e);
			appendStatus(J2EEMigrationStatus.ERROR, e.toString());
			appendStatus(J2EEMigrationStatus.ERROR, format(J2EE_VERSION_FAILED, project.getName()));
		} finally {
			removeRootMigrationTag();
		}
		currentConfig = null;
	}

	/**
	 * Method migrateDeploymentDescriptor.
	 */
	protected void migrateDeploymentDescriptor() throws CoreException {
		XMLResource resource = getDeploymentDescriptorResource();
		if (resource == null)
			appendStatus(new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, resource, format(NOT_NEEDED_DEPLOYMENT_DESC_MIG, getProjectName())));

		JaxrpcmapSpecificationMigrator migrator = new JaxrpcmapSpecificationMigrator(resource, targetVersion, currentConfig.isComplex());

		boolean isTargetVersion13 = migrator.getVersion().equals(J2EESpecificationMigrationConstants.J2EE_VERSION_1_3);

		if (!isTargetVersion13)
			migrateDDTo14(migrator);
	}


	private void migrateDDTo14(JaxrpcmapSpecificationMigrator migrator) throws CoreException {
		J2EEMigrationStatus status14 = migrator.migrateTo14();
		if (status != null)
			appendStatus(status14);
	}
}