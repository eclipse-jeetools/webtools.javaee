/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;

public interface INewWebClassDataModelProperties extends INewJavaClassDataModelProperties, IAnnotationsDataModel {

	/**
	 * Required, String property of the display name for the filter
	 */
	public static final String DISPLAY_NAME = "NewServletClassDataModel.DISPLAY_NAME"; //$NON-NLS-1$
	
	/**
	 * Optional, String property of the description info for the filter
	 */
	public static final String DESCRIPTION = "NewServletClassDataModel.DESCRIPTION"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether or not to generate a new java class.
	 * The default is false.
	 */
	public static final String USE_EXISTING_CLASS = "NewServletClassDataModel.USE_EXISTING_CLASS"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether or not to generate artifacts in web.xml
	 */
	public static final String REGISTER_IN_WEB_XML = "NewServletClassDataModel.REGISTER_IN_WEB_XML"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether or not to generate artifacts in web.xml
	 */
	public static final String JAVA_EE_VERSION = "NewServletClassDataModel.JAVA_EE_VERSION"; //$NON-NLS-1$
	
	
}
