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
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IMessageDrivenBean;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.Logger;
import org.eclipse.jst.j2ee.ejb.annotation.internal.provider.IEJBGenerator;
import org.eclipse.jst.j2ee.ejb.annotation.internal.utility.AnnotationUtilities;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AddMessageDrivenBeanOperation extends AbstractDataModelOperation {

	/**
	 * @param dataModel
	 */
	public AddMessageDrivenBeanOperation(IDataModel dataModel) {
		super(dataModel);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		try {
			IEJBGenerator generator = AnnotationUtilities.findEjbGeneratorByName(getDataModel().getStringProperty(IEnterpriseBeanClassDataModelProperties.ANNOTATIONPROVIDER));
			IMessageDrivenBean delegate = (IMessageDrivenBean) getDataModel().getProperty(IEnterpriseBeanClassDataModelProperties.MODELDELEGATE);
			if( generator != null )
				generator.generateMessageDriven(delegate,monitor);
			else{
				//TODO MUST RAISE A WARNING HERE
				Logger.log(Logger.WARNING,"There is no generator");
			}
		} catch (Exception e) {
			Logger.logException(e);
		}
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
}