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
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.EjbCommonDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.MessageDrivenBeanDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.SessionBeanDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;



public class AddEjbOperation extends WTPOperation {
	/**
	 * @param dataModel
	 */
	public AddEjbOperation(EjbCommonDataModel dataModel) {
		super(dataModel);
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		createEjb(monitor);
		
	}

	private void createEjb(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		
		EjbCommonDataModel ejbCommonDataModel =  (EjbCommonDataModel)this.getOperationDataModel();
		if( "SessionBean".equals(ejbCommonDataModel.getStringProperty(EjbCommonDataModel.EJB_TYPE))){
			SessionBeanDataModel ejbModel = (SessionBeanDataModel)ejbCommonDataModel.getNestedModel("SessionBeanDataModel");
			(new AddSessionBeanOperation(ejbModel)).execute(monitor);
		}else if( "MessageDrivenBean".equals(ejbCommonDataModel.getStringProperty(EjbCommonDataModel.EJB_TYPE))){
			MessageDrivenBeanDataModel ejbModel = (MessageDrivenBeanDataModel)ejbCommonDataModel.getNestedModel("MessageDrivenBeanDataModel");
			(new AddMessageDrivenBeanOperation(ejbModel)).execute(monitor);
		}
	}

}