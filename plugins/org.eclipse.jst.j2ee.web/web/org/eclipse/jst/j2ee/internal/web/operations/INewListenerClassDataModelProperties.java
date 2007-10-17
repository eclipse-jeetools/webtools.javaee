/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;

public interface INewListenerClassDataModelProperties extends INewJavaClassDataModelProperties, IAnnotationsDataModel {
	
	/**
	 * Optional, boolean property used to specify whether to implement the
	 * <code>javax.servlet.ServletContextListener</code> interface. 
	 * The default is false.
	 */
	public static final String SERVLET_CONTEXT_LISTENER = "NewListenerClassDataModel.SERVLET_CONTEXT_LISTENER"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to implement the
	 * <code>javax.servlet.ServletContextAttributeListener</code> interface. 
	 * The default is false.
	 */
	public static final String SERVLET_CONTEXT_ATTRIBUTE_LISTENER = "NewListenerClassDataModel.SERVLET_CONTEXT_ATTRIBUTE_LISTENER"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to implement the
	 * <code>javax.servlet.http.HttpSessionListener</code> interface. 
	 * The default is false.
	 */
	public static final String HTTP_SESSION_LISTENER = "NewListenerClassDataModel.HTTP_SESSION_LISTENER"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to implement the
	 * <code>javax.servlet.http.HttpSessionAttributeListener</code> interface. 
	 * The default is false.
	 */
	public static final String HTTP_SESSION_ATTRIBUTE_LISTENER = "NewListenerClassDataModel.HTTP_SESSION_ATTRIBUTE_LISTENER"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to implement the
	 * <code>javax.servlet.http.HttpSessionActivationListener</code> interface. 
	 * The default is false.
	 */
	public static final String HTTP_SESSION_ACTIVATION_LISTENER = "NewListenerClassDataModel.HTTP_SESSION_ACTIVATION_LISTENER"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to implement the
	 * <code>javax.servlet.http.HttpSessionBindingListener</code> interface. 
	 * The default is false.
	 */
	public static final String HTTP_SESSION_BINDING_LISTENER = "NewListenerClassDataModel.HTTP_SESSION_BINDING_LISTENER"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to implement the
	 * <code>javax.servlet.ServletRequestListener</code> interface. 
	 * The default is false.
	 */
	public static final String SERVLET_REQUEST_LISTENER = "NewListenerClassDataModel.SERVLET_REQUEST_LISTENER"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to implement the
	 * <code>javax.servlet.ServletRequestAttributeListener</code> interface. 
	 * The default is false.
	 */
	public static final String SERVLET_REQUEST_ATTRIBUTE_LISTENER = "NewListenerClassDataModel.SERVLET_REQUEST_ATTRIBUTE_LISTENER"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether or not to generate a new java class.
	 * The default is false.
	 */
	public static final String USE_EXISTING_CLASS = "NewListenerClassDataModel.USE_EXISTING_CLASS"; //$NON-NLS-1$
	
}
