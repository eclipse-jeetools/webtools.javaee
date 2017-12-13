/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/


package org.eclipse.jst.j2ee.ejb.annotation.internal.operations;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBeanClassDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AddEjbOperation extends AbstractDataModelOperation {
	/**
	 * @param dataModel
	 */
	public AddEjbOperation(IDataModel dataModel) {
		super(dataModel);
	}
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		createEjb(monitor);
		return OK_STATUS;
	}
	
	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	private void createEjb(IProgressMonitor monitor) throws ExecutionException {
		if( "SessionBean".equals(getDataModel().getStringProperty(IEnterpriseBeanClassDataModelProperties.EJB_TYPE))){ //$NON-NLS-1$
			(new AddSessionBeanOperation(getDataModel())).execute(monitor, null);
		}else if( "MessageDrivenBean".equals(getDataModel().getStringProperty(IEnterpriseBeanClassDataModelProperties.EJB_TYPE))){ //$NON-NLS-1$
			(new AddMessageDrivenBeanOperation(getDataModel())).execute(monitor, null);
		}
	}

}