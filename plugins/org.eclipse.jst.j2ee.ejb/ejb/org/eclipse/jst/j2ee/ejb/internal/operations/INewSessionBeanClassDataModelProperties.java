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
	
	/**
	 * Optional, List of BusinessInterface objects property containing all the
	 * qualified names of business interfaces the new session bean class should
	 * implement.
	 * 
	 * @see BusinessInterface
	 */
	public static final String BUSINESS_INTERFACES = "INewSessionBeanClassDataModelProperties.BUSINESS_INTERFACES"; //$NON-NLS-1$
	
	/**
	 * Required, String property of the EJB name for the session bean. 
	 */
	public static final String EJB_NAME = "INewSessionBeanClassDataModelProperties.EJB_NAME"; //$NON-NLS-1$

	/**
	 * Optional, String property of the EJB mapped name for the session bean. 
	 */
	public static final String MAPPED_NAME = "INewSessionBeanClassDataModelProperties.MAPPED_NAME"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate a remote
	 * business interface. The default is false.
	 */
	public static final String REMOTE = "INewSessionBeanClassDataModelProperties.REMOTE"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate a local
	 * business interface. The default is true.
	 */
	public static final String LOCAL = "INewSessionBeanClassDataModelProperties.LOCAL"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate a EJB 2.x
	 * compatible local home and components interfaces. The default is false.
	 */
	public static final String LOCAL_HOME = "INewSessionBeanClassDataModelProperties.LOCAL_HOME"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate a EJB 2.x
	 * compatible remote home and components interfaces. The default is false.
	 */
	public static final String REMOTE_HOME = "INewSessionBeanClassDataModelProperties.REMOTE_HOME"; //$NON-NLS-1$
	
	/**
	 * Required, Integer property that determines the state type of the session
	 * bean.
	 * 
	 * @see NewSessionBeanClassDataModelProvider#STATE_TYPE_STATELESS_INDEX
	 * @see NewSessionBeanClassDataModelProvider#STATE_TYPE_STATEFUL_INDEX
	 */
	public static final String STATE_TYPE = "INewSessionBeanClassDataModelProperties.STATE_TYPE"; //$NON-NLS-1$
	
	/**
	 * Required, Integer property that determines the transaction type of the session
	 * bean.
	 * 
	 * @see NewSessionBeanClassDataModelProvider#TRANSACTION_TYPE_CONTAINER_INDEX
	 * @see NewSessionBeanClassDataModelProvider#TRANSACTION_TYPE_BEAN_INDEX
	 */
	public static final String TRANSACTION_TYPE = "INewSessionBeanClassDataModelProperties.TRANSACTION_TYPE"; //$NON-NLS-1$
	
}
