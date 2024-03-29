/*******************************************************************************
 * Copyright (c) 2007, 2023 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;

public interface INewEnterpriseBeanClassDataModelProperties extends INewJavaClassDataModelProperties, IAnnotationsDataModel {
	
		
	/**
	 * Required, String property of the EJB name for the enterprise bean. 
	 */
	public static final String EJB_NAME = "INewEnterpriseBeanClassDataModelProperties.EJB_NAME"; //$NON-NLS-1$

	/**
	 * Optional, String property of the EJB mapped name for the enterprise bean. 
	 */
	public static final String MAPPED_NAME = "INewEnterpriseBeanClassDataModelProperties.MAPPED_NAME"; //$NON-NLS-1$

	/**
	 * Required, String property that determines the transaction type of the
	 * enterprise bean. Valid values are the string representation of the
	 * <code>TransactionType<code> enumeration.
	 * 
	 * @see TransactionType
	 */
	public static final String TRANSACTION_TYPE = "INewEnterpriseBeanClassDataModelProperties.TRANSACTION_TYPE"; //$NON-NLS-1$

	/**
	 * Optional, boolean property used to specify whether to generate jakarta vs. javax packagenames.
	 * The default is true for EJB 4.0 or JEE10 projects, otherwise -
	 * false.
	 */
	public static final String USE_JAKARTA_PACKAGENAME = "INewEnterpriseBeanClassDataModelProperties.USE_JAKARTA_PACKAGENAME"; //$NON-NLS-1$

}
