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
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IMessageDrivenBean;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.MessageDrivenBeanDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.provider.IEJBGenerator;
import org.eclipse.jst.j2ee.ejb.annotation.internal.utility.AnnotationUtilities;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

public class AddMessageDrivenBeanOperation extends WTPOperation {

	/**
	 * @param dataModel
	 */
	public AddMessageDrivenBeanOperation(EnterpriseBeanClassDataModel dataModel) {
		super(dataModel);
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		try {
			IEJBGenerator generator = AnnotationUtilities.findEjbGeneratorByName(this.getOperationDataModel().getStringProperty(EnterpriseBeanClassDataModel.ANNOTATIONPROVIDER));
			MessageDrivenBeanDataModel dataModel = (MessageDrivenBeanDataModel) this.getOperationDataModel();
			IMessageDrivenBean delegate = (IMessageDrivenBean) dataModel.getDelegate();
			generator.generateMessageDriven(delegate,monitor);
		} catch (InvalidRegistryObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}