/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/


package org.eclipse.jst.j2ee.ejb.annotation.internal.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.MessageDrivenBeanDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.EnterpriseBeanClassDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.SessionBeanDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;



public class AddEjbOperation extends WTPOperation {
	/**
	 * @param dataModel
	 */
	public AddEjbOperation(EnterpriseBeanClassDataModel dataModel) {
		super(dataModel);
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		createEjb(monitor);
		
	}

	private void createEjb(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		
		EnterpriseBeanClassDataModel ejbCommonDataModel =  (EnterpriseBeanClassDataModel)this.getOperationDataModel();
		if( "SessionBean".equals(ejbCommonDataModel.getStringProperty(EnterpriseBeanClassDataModel.EJB_TYPE))){
			SessionBeanDataModel ejbModel = (SessionBeanDataModel)ejbCommonDataModel;
			(new AddSessionBeanOperation(ejbModel)).execute(monitor);
		}else if( "MessageDrivenBean".equals(ejbCommonDataModel.getStringProperty(EnterpriseBeanClassDataModel.EJB_TYPE))){
			MessageDrivenBeanDataModel ejbModel = (MessageDrivenBeanDataModel)ejbCommonDataModel;
			(new AddMessageDrivenBeanOperation(ejbModel)).execute(monitor);
		}
	}

}