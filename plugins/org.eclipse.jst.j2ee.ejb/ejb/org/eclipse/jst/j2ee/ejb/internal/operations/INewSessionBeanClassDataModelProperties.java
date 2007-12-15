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
	
	public static final String BUSINESSINTERFACES = "INewSessionBeanClassDataModelProperties.BUSINESSINTERFACES"; //$NON-NLS-1$
	public static final String EJB_NAME = "INewSessionBeanClassDataModelProperties.EJB_NAME"; //$NON-NLS-1$
	public static final String REMOTE = "INewSessionBeanClassDataModelProperties.REMOTE"; //$NON-NLS-1$
	public static final String LOCAL = "INewSessionBeanClassDataModelProperties.LOCAL"; //$NON-NLS-1$
	public static final String STATE_TYPE = "INewSessionBeanClassDataModelProperties.STATE_TYPE"; //$NON-NLS-1$
	
	public static final String LOCAL_HOME = "INewSessionBeanClassDataModelProperties.LOCAL_HOME"; //$NON-NLS-1$
	public static final String REMOTE_HOME = "INewSessionBeanClassDataModelProperties.REMOTE_HOME"; //$NON-NLS-1$
	public static final String MAPPED_NAME = "INewSessionBeanClassDataModelProperties.MAPPED_NAME"; //$NON-NLS-1$
	public static final String TRANSACTION_TYPE = "INewSessionBeanClassDataModelProperties.TRANSACTION_TYPE"; //$NON-NLS-1$
}
