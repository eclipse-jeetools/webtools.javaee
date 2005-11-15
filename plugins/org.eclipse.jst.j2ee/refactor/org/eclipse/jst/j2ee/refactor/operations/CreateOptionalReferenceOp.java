/*******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * rfrost@bea.com - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.refactor.operations;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsOp;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * Extension of <code>CreateReferenceComponentsOp</code> that supports the optional creation of
 * the .component reference. 
 */
public class CreateOptionalReferenceOp extends CreateReferenceComponentsOp {

	private final boolean _createReferences;
	
	public CreateOptionalReferenceOp(IDataModel model, boolean createReferences) {
		super(model);
		_createReferences = createReferences;
	}
	
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if (_createReferences) {
			addReferencedComponents(monitor);
		}
		addProjectReferences();
		return OK_STATUS;
	}
	
}
