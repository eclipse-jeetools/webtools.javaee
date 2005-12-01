/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.project.facet.JavaUtilityProjectCreationOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EjbClientProjectCreationOperation
	extends JavaUtilityProjectCreationOperation
	implements IEjbClientProjectCreationDataModelProperties{

	public EjbClientProjectCreationOperation(IDataModel model) {
		super(model);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return super.execute(monitor, info);
	}
}
