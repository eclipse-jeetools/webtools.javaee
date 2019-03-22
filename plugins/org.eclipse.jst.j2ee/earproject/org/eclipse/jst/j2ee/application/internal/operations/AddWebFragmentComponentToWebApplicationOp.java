/*******************************************************************************

 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsOp;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AddWebFragmentComponentToWebApplicationOp extends CreateReferenceComponentsOp {
	public static final String metaInfFolderDeployPath = "/"; //$NON-NLS-1$

	public AddWebFragmentComponentToWebApplicationOp(IDataModel model) {
		super(model);
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if (monitor != null) {
			monitor.beginTask("", 4); //$NON-NLS-1$
		}
		try {
			J2EEComponentClasspathUpdater.getInstance().pauseUpdates();
			IStatus  status = validateEdit();
			if( status.isOK() ){
				status = super.execute(submon(monitor, 1), info);
				if (!status.isOK())
					return Status.CANCEL_STATUS;
			}
			return status;
		} finally {
			if (monitor != null) {
				monitor.done();
			}
			J2EEComponentClasspathUpdater.getInstance().resumeUpdates();
		}
	}


	private static IProgressMonitor submon(final IProgressMonitor parent, final int ticks) {
		return (parent == null ? null : new SubProgressMonitor(parent, ticks));
	}

	@Override
	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return null;
	}

	@Override
	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return null;
	}
}
