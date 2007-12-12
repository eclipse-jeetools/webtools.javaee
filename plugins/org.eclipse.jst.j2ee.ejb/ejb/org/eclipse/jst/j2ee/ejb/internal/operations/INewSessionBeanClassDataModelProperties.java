/*******************************************************************************
 * Copyright (c) 2007 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;

public interface INewSessionBeanClassDataModelProperties extends INewJavaClassDataModelProperties, IAnnotationsDataModel {
	
	public static final String USE_EXISTING_CLASS = "NewBeansClassDataModel.USE_EXISTING_CLASS"; //$NON-NLS-1$
	
	//Beans Properties
	public static final String BUSSNESINTERFACE_LIST = "INewBeansClassDataModelProperties.BUSSNESINTERFACE_LIST"; //$NON-NLS-1$
	public static final String EJB_NAME = "INewBeansClassDataModelProperties.EJB_NAME"; //$NON-NLS-1$
	public static final String REMOTE_BI = "INewBeansClassDataModelProperties.REMOTE_BI"; //$NON-NLS-1$
	public static final String LOCAL_BI = "INewBeansClassDataModelProperties.LOCAL_BI"; //$NON-NLS-1$
	public static final String STATE_TYPE = "INewBeansClassDataModelProperties.STATE_TYPE"; //$NON-NLS-1$
	
	public static final String LOCAL_HOME = "INewBeansClassDataModelProperties.LOCAL_HOME"; //$NON-NLS-1$
	public static final String REMOTE_HOME = "INewBeansClassDataModelProperties.REMOTE_HOME"; //$NON-NLS-1$
	public static final String MAPPED_NAME = "INewBeansClassDataModelProperties.MAPPED_NAME"; //$NON-NLS-1$
	public static final String TRANSACTION_TYPE = "INewBeansClassDataModelProperties.TRANSACTION_TYPE"; //$NON-NLS-1$
}
