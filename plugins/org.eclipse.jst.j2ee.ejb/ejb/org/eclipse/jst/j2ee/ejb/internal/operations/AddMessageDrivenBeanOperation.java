/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


public class AddMessageDrivenBeanOperation extends AddEnterpriseBeanOperation {

	/**
	 * This is the constructor which should be used when creating the operation.
	 * It will not accept null parameter. It will not return null.
	 * 
	 * @see ArtifactEditProviderOperation#ArtifactEditProviderOperation(IDataModel)
	 * 
	 * @param dataModel
	 * @return AddBeansOperation
	 */
	public AddMessageDrivenBeanOperation(IDataModel dataModel) {
		super(dataModel);
	}

	/**
	 * Subclasses may extend this method to add their own creation of the actual
	 * bean java class. This implementation uses the NewBeanClassOperation
	 * which is a subclass of the NewJavaClassOperation. The
	 * NewMessageDrivenBeanClassOperation will use the same
	 * NewMessageDrivenBeanClassDataModelProvider to retrieve the properties in order to
	 * create the java class accordingly. This method will not return null.
	 * 
	 * @see NewMessageDrivenBeanClassOperation
	 * @see org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassOperation
	 * @see NewMessageDrivenBeanClassDataModelProvider
	 * 
	 * @return String qualified bean class name
	 */
	protected String createBeanClass() {
		// Create bean java class file using the NewBeanClassOperation.
		NewMessageDrivenBeanClassOperation op = new NewMessageDrivenBeanClassOperation(model);
		try {
			op.execute(new NullProgressMonitor(), null);
		} catch (Exception e) {
			Logger.getLogger().log(e);
		}
		// Return the qualified class name of the newly created java class for
		// the bean
		return getQualifiedClassName();
	}

}
