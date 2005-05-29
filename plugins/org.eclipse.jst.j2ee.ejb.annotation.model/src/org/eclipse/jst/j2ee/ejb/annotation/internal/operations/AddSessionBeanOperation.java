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
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.EnterpriseBeanClassDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.ISessionBean;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.Logger;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.SessionBeanDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.provider.IEJBGenerator;
import org.eclipse.jst.j2ee.ejb.annotation.internal.utility.AnnotationUtilities;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;



public class AddSessionBeanOperation extends WTPOperation {
	
	/**
	 * @param dataModel
	 */
	public AddSessionBeanOperation(EnterpriseBeanClassDataModel dataModel) {
		super(dataModel);
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		try {
			IEJBGenerator generator = AnnotationUtilities.findEjbGeneratorByName(this.getOperationDataModel().getStringProperty(EnterpriseBeanClassDataModel.ANNOTATIONPROVIDER));
			SessionBeanDataModel dataModel = (SessionBeanDataModel) this.getOperationDataModel();
			ISessionBean delegate = (ISessionBean) dataModel.getDelegate();
			if(generator != null )
				generator.generateSession(delegate,monitor);
			else{
				//TODO MUST RAISE A WARNING HERE
				Logger.log(Logger.WARNING,"There is no generator");
			}
		} catch (InvalidRegistryObjectException e) {
			Logger.logException(e);
		} catch (ClassNotFoundException e) {
			Logger.logException(e);
		} catch (InstantiationException e) {
			Logger.logException(e);
		} catch (IllegalAccessException e) {
			Logger.logException(e);
		}
		
	}

}