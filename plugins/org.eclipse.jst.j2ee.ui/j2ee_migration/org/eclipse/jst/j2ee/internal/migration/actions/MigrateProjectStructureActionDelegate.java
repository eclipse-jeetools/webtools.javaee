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


public class MigrateProjectStructureActionDelegate extends J2EEMigrationActionDelegate {

	/**
	 * Constructor for MigrateProjectStructureActionDelegate.
	 */
	public MigrateProjectStructureActionDelegate() {
		super();
	}


	/**
	 * @see org.eclipse.jst.j2ee.internal.migration.actions.J2EEMigrationActionDelegate#createMigrationOperation(List)
	 */
	protected AbstractJ2EEMigrationOperation createMigrationOperation(List projects) {
		//return new J2EEProjectStructureMigrationOperation(projects);
		return null;
	}

	protected String getConfirmationMessage() {
		return IJ2EEMigrationConstants.CONFIRM_MIGRATE_PROJECT_STRUCTURE;
	}
}