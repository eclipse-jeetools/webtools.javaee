/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.internal.model;

import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;

public interface IEnterpriseBeanClassDataModelProperties extends INewJavaClassDataModelProperties, IAnnotationsDataModel {

	public static final String EJB_TYPE = "IEjbCommonDataModel.EJB_TYPE"; //$NON-NLS-1$

	public static final String EJB_NAME = "IEjbCommonDataModel.EJB_NAME"; //$NON-NLS-1$

	public static final String JNDI_NAME = "IEjbCommonDataModel.JNDI_NAME"; //$NON-NLS-1$

	public static final String DISPLAY_NAME = "IEjbCommonDataModel.DISPLAY_NAME"; //$NON-NLS-1$

	public static final String DESCRIPTION = "IEjbCommonDataModel.DESCRIPTION"; //$NON-NLS-1$

	public static final String TRANSACTIONTYPE = "IEjbCommonDataModel.TRANSACTIONTYPE"; //$NON-NLS-1$

	public static final String ANNOTATIONPROVIDER = "IEjbCommonDataModel.ANNOTATIONPROVIDER"; //$NON-NLS-1$

	public static final String MODELDELEGATE = "IEjbCommonDataModel.MODELDELEGATE"; //$NON-NLS-1$
	
	public static final String INTERFACES_AS_STRING = "IEjbCommonDataModel.INTERFACES_AS_STRING"; //$NON-NLS-1$
}
