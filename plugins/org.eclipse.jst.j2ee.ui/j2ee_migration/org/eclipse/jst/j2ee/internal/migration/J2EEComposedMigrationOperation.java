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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jst.j2ee.migration.J2EEMigrationMultiStatus;
import org.eclipse.jst.j2ee.migration.J2EEMigrationStatus;
import org.eclipse.wst.common.framework.operation.IOperationHandler;

public class J2EEComposedMigrationOperation extends AbstractJ2EEMigrationOperation {

	protected List nestedOperations;

	public J2EEComposedMigrationOperation(ComposedMigrationConfig config, IOperationHandler handler) {
		super(config, handler);
	}

	public boolean addRunnable(AbstractJ2EEMigrationOperation nestedOp) {
		return getRunnables().add(nestedOp);
	}

	protected void execute(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		if (nestedOperations != null && !nestedOperations.isEmpty()) {
			int size = nestedOperations.size();
			J2EEMigrationMultiStatus multiStatus = new J2EEMigrationMultiStatus();
			initilizeModelMigrationStatus(multiStatus);
			monitor.beginTask("", size); //$NON-NLS-1$
			for (int i = 0; i < nestedOperations.size(); i++) {
				AbstractJ2EEMigrationOperation op = (AbstractJ2EEMigrationOperation) nestedOperations.get(i);
				if (op != null) {
					op.run(new SubProgressMonitor(monitor, 1, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK));
					multiStatus.merge(op.getMigrationStatus());
					setStatus(multiStatus);
				}
			}
		}
	}

	/**
	 *  
	 */
	protected void initilizeModelMigrationStatus(J2EEMigrationStatus multiStatus) {
		Object aStatus = ((J2EEMigrationConfig) getOperationDataModel()).getProperty(J2EEMigrationConfig.MIGRATION_STATUS);
		if (aStatus == null)
			((J2EEMigrationConfig) getOperationDataModel()).setProperty(J2EEMigrationConfig.MIGRATION_STATUS, multiStatus);
	}

	protected List getRunnables() {
		if (nestedOperations == null)
			nestedOperations = new ArrayList(3);
		return nestedOperations;
	}

}

