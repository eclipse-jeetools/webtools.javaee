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

public interface INewServletClassDataModelProperties extends INewJavaClassDataModelProperties, IAnnotationsDataModel {
	/**
	 * Optional, boolean property used to specify whether to generate the init method.
	 * The default is false.
	 */
	public static final String INIT = "NewServletClassDataModel.INIT"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate the doPost method.
	 * The default is true.
	 */
	public static final String DO_POST = "NewServletClassDataModel.DO_POST"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate the destroy method.
	 * The default is false.
	 */
	public static final String DESTROY = "NewServletClassDataModel.DESTROY"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate the toString method.
	 * The default is false.
	 */
	public static final String TO_STRING = "NewServletClassDataModel.TO_STRING"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate the doPut method.
	 * The default is false.
	 */
	public static final String DO_PUT = "NewServletClassDataModel.DO_PUT"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate the doGet method.
	 * The default is true.
	 */
	public static final String DO_GET = "NewServletClassDataModel.DO_GET"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate the getServletInfo method.
	 * The default is false.
	 */
	public static final String GET_SERVLET_INFO = "NewServletClassDataModel.GET_SERVLET_INFO"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate the doDelete method.
	 * The default is false.
	 */
	public static final String DO_DELETE = "NewServletClassDataModel.DO_DELETE"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to determine if building JSP or servlet.
	 * The default is true.
	 */
	public static final String IS_SERVLET_TYPE = "NewServletClassDataModel.IS_SERVLET_TYPE"; //$NON-NLS-1$
	
	/**
	 * Optional, List property used to cache all the init params defined on the servlet.
	 */
	public static final String INIT_PARAM = "NewServletClassDataModel.INIT_PARAM"; //$NON-NLS-1$
	
	/**
	 * Optional, List propety used to cache all the servlet mappings for this servlet on the web application.
	 */
	public static final String URL_MAPPINGS = "NewServletClassDataModel.URL_MAPPINGS"; //$NON-NLS-1$

	/**
	 * Required, String property of the display name for the servlet
	 */
	public static final String DISPLAY_NAME = "NewServletClassDataModel.DISPLAY_NAME"; //$NON-NLS-1$
	
	/**
	 * Optional, String property of the description info for the servlet
	 */
	public static final String DESCRIPTION = "NewServletClassDataModel.DESCRIPTION"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether or not to gen a new java class.
	 * The default is false.
	 */
	public static final String USE_EXISTING_CLASS = "NewServletClassDataModel.USE_EXISTING_CLASS"; //$NON-NLS-1$
	
	/**
	 * Do not set! The javajet template file used in creating the annotated servlet template class
	 */
	public static final String TEMPLATE_FILE = "NewServletClassDataModel.TEMPLATE_FILE"; //$NON-NLS-1$
	
	/**
	 * Do not set! The javajet template file used in creating the non-annotated servlet template class
	 */
	public static final String NON_ANNOTATED_TEMPLATE_FILE = "NewServletClassDataModel.NON_ANNOTATED_TEMPLATE_FILE"; //$NON-NLS-1$
	
}
