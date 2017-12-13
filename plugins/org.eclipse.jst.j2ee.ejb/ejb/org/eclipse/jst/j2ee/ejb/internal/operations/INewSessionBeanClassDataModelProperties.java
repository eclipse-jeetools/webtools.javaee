/*******************************************************************************
 * Copyright (c) 2007, 2014 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 * Roberto Sanchez, rsanchez@mx1.ibm.com - Add remote and local annotations to bean class
 * IBM - Async and Non-persistent support
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

public interface INewSessionBeanClassDataModelProperties extends INewEnterpriseBeanClassDataModelProperties {
	
	public static final String REMOTE_BUSINESS_INTERFACE = "INewSessionBeanClassDataModelProperties.REMOTE_BUSINESS_INTERFACE"; //$NON-NLS-1$
	
	public static final String LOCAL_BUSINESS_INTERFACE = "INewSessionBeanClassDataModelProperties.LOCAL_BUSINESS_INTERFACE"; //$NON-NLS-1$

	public static final String REMOTE_HOME_INTERFACE = "INewSessionBeanClassDataModelProperties.REMOTE_HOME_INTERFACE"; //$NON-NLS-1$
	
	public static final String LOCAL_HOME_INTERFACE = "INewSessionBeanClassDataModelProperties.LOCAL_HOME_INTERFACE"; //$NON-NLS-1$
	
	public static final String LOCAL_COMPONENT_INTERFACE = "INewSessionBeanClassDataModelProperties.LOCAL_COMPONENT_INTERFACE"; //$NON-NLS-1$
	
	public static final String REMOTE_COMPONENT_INTERFACE = "INewSessionBeanClassDataModelProperties.REMOTE_COMPONENT_INTERFACE"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate a remote
	 * business interface. The default is false.
	 */
	public static final String REMOTE = "INewSessionBeanClassDataModelProperties.REMOTE"; //$NON-NLS-1$

	/**
	 * Optional, boolean property used to specify whether to generate a local
	 * business interface. The default is true for EJB 3.0 projects, otherwise -
	 * false.
	 */
	public static final String LOCAL = "INewSessionBeanClassDataModelProperties.LOCAL"; //$NON-NLS-1$

	/**
	 * Optional, boolean property used to specify whether to generate a
	 * no-interface client view. The default is true for EJB 3.1 projects and
	 * later, otherwise - false.
	 * 
	 * @since 3.2
	 */
	public static final String NO_INTERFACE = "INewSessionBeanClassDataModelProperties.NO_INTERFACE"; //$NON-NLS-1$
	
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
	 * Required, String property that determines the state type of the session
	 * bean. Valid values are the string representation of the
	 * <code>StateType<code> enumeration.
	 * 
	 * @see StateType
	 */
	public static final String STATE_TYPE = "INewSessionBeanClassDataModelProperties.STATE_TYPE"; //$NON-NLS-1$
	
	public static final String BUSINESS_INTERFACE_ANNOTATION_LOCATION = "INewSessionBeanClassDataModelProperties.BUSINESS_INTERFACE_ANNOTATION_LOCATION"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether add the Asynchronous annotation to the class definition or not. 
	 * The default value is false.
	 * 
	 * @since 3.6
	 */
	public static final String ASYNC = "INewSessionBeanClassDataModelProperties.ASYNC"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to define the timer as non-persistent or not.
	 * The default value is false.
	 * 
	 * @since 3.6
	 */
	public static final String NON_PERSISTENT = "INewSessionBeanClassDataModelProperties.NON_PERSISTENT"; //$NON-NLS-1$
	
}
