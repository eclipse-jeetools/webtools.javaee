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
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.applicationclient.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorNatureRuntime;
import org.eclipse.jst.j2ee.internal.migration.actions.IJ2EEMigrationConstants;
import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntime;
import org.eclipse.jst.j2ee.migration.J2EEMigrationStatus;
import org.eclipse.jst.j2ee.migration.J2EESpecificationMigrationConstants;
import org.eclipse.wst.common.framework.operation.IOperationHandler;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

public class J2EEMigrationOperation extends AbstractJ2EEMigrationOperation implements IJ2EEMigrationConstants {
	//There is either one or many
	protected J2EEMigrationConfig migrationConfig;
	protected IProgressMonitor monitor;
	protected Integer projectMetaWeight = null;
	protected Integer projectStructureWeight = null;

	public J2EEMigrationOperation(J2EEMigrationConfig config, IOperationHandler handler) {
		super(config, handler);
		migrationConfig = config;
	}

	public J2EEMigrationOperation() {
		super();
	}

	protected int computeProjectStructureMigrationWeight() {
		return computeWeight(false);
	}

	protected int computeVersionMigrationWeight() {
		return computeWeight(true);
	}

	protected int computeWeight(boolean testForComplex) {
		if (migrationConfig != null && !migrationConfig.isComposed())
			return primComputeMigrationWeight(migrationConfig, testForComplex);
		int weight = 0;
		List configs = ((ComposedMigrationConfig) migrationConfig).withAllSelectedChildren();
		for (int i = 0; i < configs.size(); i++) {
			weight += primComputeMigrationWeight((J2EEMigrationConfig) configs.get(i), testForComplex);
		}
		return weight;
	}

	/**
	 * @param composed
	 * @return
	 */
	private boolean containsEAR(J2EEMigrationConfig config) {
		IProject project = config.getTargetProject();
		if (project != null)
			return EARNatureRuntime.hasRuntime(project);
		return false;
	}

	/**
	 * @param childConfig
	 * @return
	 */
	private J2EEProjectMetadataMigrationOperation createModuleMetadataMigrationOp(J2EEMigrationConfig childConfig) {
		IProject moduleProject = childConfig.getTargetProject();
		String targetVersion = getSelectedJ2EEVersion();
		if (EJBNatureRuntime.hasRuntime(moduleProject)) {
			EJBProjectMetadataMigrationOperation op = new EJBProjectMetadataMigrationOperation(childConfig, targetVersion, operationHandler);
			return op;
		} else if (J2EEWebNatureRuntime.hasRuntime(moduleProject)) {
			WebProjectMetadataMigrationOperation op = new WebProjectMetadataMigrationOperation(childConfig, targetVersion, operationHandler);
			return op;
		} else if (ApplicationClientNatureRuntime.hasRuntime(moduleProject)) {
			AppClientMetadataMigrationOperation op = new AppClientMetadataMigrationOperation(childConfig, targetVersion, operationHandler);
			return op;
		} else if (ConnectorNatureRuntime.hasRuntime(moduleProject)) {
			ConnectorProjectMetadataMigrationOperation op = new ConnectorProjectMetadataMigrationOperation(childConfig, targetVersion, operationHandler);
			return op;
		}
		return null;
	}

	/**
	 * @param childConfig
	 */
	private J2EEProjectStructureMigrationOperation createModuleStructureMigrationOp(J2EEMigrationConfig childConfig) {
		IProject moduleProject = childConfig.getTargetProject();
		if (EJBNatureRuntime.hasRuntime(moduleProject)) {
			EJBProjectStructureMigrationOperation op = new EJBProjectStructureMigrationOperation(childConfig, operationHandler);
			return op;
		} else if (J2EEWebNatureRuntime.hasRuntime(moduleProject)) {
			WebProjectStructureMigrationOperation op = new WebProjectStructureMigrationOperation(childConfig, operationHandler);
			return op;
		} else if (ApplicationClientNatureRuntime.hasRuntime(moduleProject)) {
			AppClientProjectStructureMigrationOperation op = new AppClientProjectStructureMigrationOperation(childConfig, operationHandler);
			return op;
		}
		return null;
	}

	/**
	 * Method execProjectStructure.
	 */
	private void execProjectStructure() throws InvocationTargetException, InterruptedException {
		J2EEProjectStructureMigrationOperation op = new J2EEProjectStructureMigrationOperation(migrationConfig, operationHandler);
		ComposedMigrationConfig composed = (ComposedMigrationConfig) migrationConfig;
		List childModels = composed.getChildren();
		if (!childModels.isEmpty()) {
			J2EEComposedMigrationOperation composedOp = new J2EEComposedMigrationOperation(composed, operationHandler);
			if (containsEAR(migrationConfig) && migrationConfig.shouldMigrateProjectStructure())
				composedOp.addRunnable(op);
			for (int i = 0; i < childModels.size(); i++) {
				J2EEMigrationConfig childConfig = (J2EEMigrationConfig) childModels.get(i);
				if (childConfig.shouldMigrateProjectStructure() && childConfig.isSelected())
					composedOp.addRunnable(createModuleStructureMigrationOp(childConfig));
			}
			if (composedOp.nestedOperations != null && !composedOp.nestedOperations.isEmpty())
				executeNestedOperation(monitor, composedOp, getProjectStructureWeight());
		} else {
			executeNestedOperation(monitor, op, getProjectStructureWeight());
		}
	}

	/**
	 * Method execProjectVersionIfNecessary.
	 */
	private void execProjectVersionIfNecessary() throws InvocationTargetException, InterruptedException {
		if (!includesVersionMigration())
			return;
		J2EEProjectMetadataMigrationOperation op = new J2EEProjectMetadataMigrationOperation(migrationConfig, getSelectedJ2EEVersion(), operationHandler);
		ComposedMigrationConfig composed = (ComposedMigrationConfig) migrationConfig;
		J2EEComposedMigrationOperation composedOp = new J2EEComposedMigrationOperation(composed, operationHandler);
		List childModels = composed.getChildren();
		if (!childModels.isEmpty()) {
			if (containsEAR(migrationConfig) && migrationConfig.shouldMigrateJ2EEVersion())
				composedOp.addRunnable(op);
			for (int i = 0; i < childModels.size(); i++) {
				J2EEMigrationConfig childConfig = (J2EEMigrationConfig) childModels.get(i);
				if (childConfig.shouldMigrateJ2EEVersion() && childConfig.isSelected())
					composedOp.addRunnable(createModuleMetadataMigrationOp(childConfig));
			}
			if (composedOp.nestedOperations != null && !composedOp.nestedOperations.isEmpty())
				executeNestedOperation(monitor, composedOp, getProjectMetaWeight());
		} else
			executeNestedOperation(monitor, op, getProjectMetaWeight());
	}

	/**
	 * @see org.eclipse.jst.j2ee.operations.HeadlessJ2EEOperation#execute(IProgressMonitor)
	 */
	protected void execute(IProgressMonitor aMonitor) throws CoreException, InvocationTargetException, InterruptedException {
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
			execProjectStructure();
			execProjectVersionIfNecessary();
		} finally {
			migrationConfig.dispose();
			if (autoFlag)
				ProjectUtilities.turnAutoBuildOn();
			monitor.done();
		}
	}

	public int getProjectMetaWeight() {
		if (projectMetaWeight == null)
			projectMetaWeight = new Integer(computeVersionMigrationWeight());
		return projectMetaWeight.intValue();
	}

	public int getProjectStructureWeight() {
		if (projectStructureWeight == null)
			projectStructureWeight = new Integer(computeProjectStructureMigrationWeight());
		return projectStructureWeight.intValue();
	}

	/**
	 * @return Returns the selectedJ2EEVersion.
	 */
	public String getSelectedJ2EEVersion() {
		int version = migrationConfig.getIntProperty(J2EEMigrationConfig.J2EE_MIGRATION_VERSION);
		if (version == J2EEVersionConstants.J2EE_1_2_ID)
			return J2EESpecificationMigrationConstants.J2EE_VERSION_1_2;
		if (version == J2EEVersionConstants.J2EE_1_3_ID)
			return J2EESpecificationMigrationConstants.J2EE_VERSION_1_3;
		else if (version == J2EEVersionConstants.J2EE_1_4_ID)
			return J2EESpecificationMigrationConstants.J2EE_VERSION_1_4;
		return J2EEVersionConstants.VERSION_1_4_TEXT;
	}

	protected List getSelectedProjects() {
		if (migrationConfig != null)
			return ((ComposedMigrationConfig) migrationConfig).getAllSelectedProjects();
		return Collections.EMPTY_LIST;
	}

	protected int getTotalWeight() {
		int weight = getProjectMetaWeight();
		if (includesVersionMigration())
			weight += getProjectMetaWeight();
		return weight;
	}

	protected boolean includesVersionMigration() {
		if (migrationConfig != null)
			return migrationConfig.isAnyVersionMigrateable();
		return false;
	}

	protected int primComputeMigrationWeight(J2EEMigrationConfig config, boolean testForComplex) {
		if (config == null || !config.getBooleanProperty(J2EEMigrationConfig.IS_SELECTED))
			return 0;
		return testForComplex && config.isComplex() ? 3 : 1;
	}

	public boolean verifyFilesInSync() {
		boolean inSync = true;
		List projects = getSelectedProjects();
		for (int i = 0; i < projects.size(); i++) {
			IProject proj = (IProject) projects.get(i);
			if (!proj.isSynchronized(IResource.DEPTH_INFINITE)) {
				appendStatus(J2EEMigrationStatus.ERROR, format(FILES_OUT_OF_SYNC, proj.getName()));
				inSync = false;
			}
		}
		return inSync;
	}

}