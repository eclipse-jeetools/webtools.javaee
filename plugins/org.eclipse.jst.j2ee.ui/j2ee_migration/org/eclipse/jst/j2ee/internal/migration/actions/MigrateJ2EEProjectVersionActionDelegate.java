/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.migration.actions;


import java.util.List;

import org.eclipse.jst.j2ee.internal.migration.AbstractJ2EEMigrationOperation;


public class MigrateJ2EEProjectVersionActionDelegate extends J2EEMigrationActionDelegate {

	/**
	 * Constructor for MigrateProjectStructureActionDelegate.
	 */
	public MigrateJ2EEProjectVersionActionDelegate() {
		super();
	}


	protected AbstractJ2EEMigrationOperation createMigrationOperation(List projects) {
		//	J2EEComposedMigrationOperation composed = new J2EEComposedMigrationOperation();
		//	composed.addRunnable(new J2EEProjectStructureMigrationOperation(projects));
		//	composed.addRunnable(new J2EEProjectMetadataMigrationOperation(projects,
		// IJ2EEMigrationConstants.J2EE_VERSION_1_3));
		//	return composed;
		return null;
	}

	//TODO Uncomment this method only if dependent Validation fails to run after migration
	//	protected IRunnableWithProgress createRunnableWithProgress(AbstractJ2EEMigrationOperation op,
	// List projects) {
	//		IRunnableWithProgress basic = super.createRunnableWithProgress(op, projects);
	//		IRunnableWithProgress[] earValidations =
	// EARValidationHelper.getEARValidationOperations(projects);
	//		if (earValidations == null || earValidations.length == 0)
	//			return basic;
	//		WorkspaceModifyComposedOperation composed = new WorkspaceModifyComposedOperation(basic);
	//		for (int i = 0; i < earValidations.length; i++) {
	//			composed.addRunnable(earValidations[i]);
	//		}
	//		return composed;
	//	}


	protected String getConfirmationMessage() {
		return IJ2EEMigrationConstants.CONFIRM_MIGRATE_J2EE_13;
	}


}

