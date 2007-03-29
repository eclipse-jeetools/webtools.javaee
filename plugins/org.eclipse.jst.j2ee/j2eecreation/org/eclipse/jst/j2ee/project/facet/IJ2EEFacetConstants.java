/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project.facet;

import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;

public interface IJ2EEFacetConstants {

	//moduleTypes
	public static final String ENTERPRISE_APPLICATION = IModuleConstants.JST_EAR_MODULE;

	public static final String APPLICATION_CLIENT = IModuleConstants.JST_APPCLIENT_MODULE;

	public static final String EJB = IModuleConstants.JST_EJB_MODULE;

	public static final String DYNAMIC_WEB = IModuleConstants.JST_WEB_MODULE;

	public static final String UTILITY = IModuleConstants.JST_UTILITY_MODULE;

	public static final String JCA = IModuleConstants.JST_CONNECTOR_MODULE;

	public static final String STATIC_WEB = IModuleConstants.WST_WEB_MODULE;

	public static final String JAVA = IModuleConstants.JST_JAVA;
}
