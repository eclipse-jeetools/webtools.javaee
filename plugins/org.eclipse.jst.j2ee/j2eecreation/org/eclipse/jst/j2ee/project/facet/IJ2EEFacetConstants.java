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
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public interface IJ2EEFacetConstants {

	//moduleTypes
	public static final String ENTERPRISE_APPLICATION = IModuleConstants.JST_EAR_MODULE;
	public static final IProjectFacetVersion ENTERPRISE_APPLICATION_12 = ProjectFacetsManager.getProjectFacet(ENTERPRISE_APPLICATION).getVersion("1.2"); //$NON-NLS-1$
	public static final IProjectFacetVersion ENTERPRISE_APPLICATION_13 = ProjectFacetsManager.getProjectFacet(ENTERPRISE_APPLICATION).getVersion("1.3"); //$NON-NLS-1$
	public static final IProjectFacetVersion ENTERPRISE_APPLICATION_14 = ProjectFacetsManager.getProjectFacet(ENTERPRISE_APPLICATION).getVersion("1.4"); //$NON-NLS-1$
	public static final IProjectFacetVersion ENTERPRISE_APPLICATION_50 = ProjectFacetsManager.getProjectFacet(ENTERPRISE_APPLICATION).getVersion("5.0"); //$NON-NLS-1$
	
	
	public static final String APPLICATION_CLIENT = IModuleConstants.JST_APPCLIENT_MODULE;
	public static final IProjectFacetVersion APPLICATION_CLIENT_12 = ProjectFacetsManager.getProjectFacet(APPLICATION_CLIENT).getVersion("1.2"); //$NON-NLS-1$
	public static final IProjectFacetVersion APPLICATION_CLIENT_13 = ProjectFacetsManager.getProjectFacet(APPLICATION_CLIENT).getVersion("1.3"); //$NON-NLS-1$
	public static final IProjectFacetVersion APPLICATION_CLIENT_14 = ProjectFacetsManager.getProjectFacet(APPLICATION_CLIENT).getVersion("1.4"); //$NON-NLS-1$
	public static final IProjectFacetVersion APPLICATION_CLIENT_50 = ProjectFacetsManager.getProjectFacet(APPLICATION_CLIENT).getVersion("5.0"); //$NON-NLS-1$
	
	public static final String EJB = IModuleConstants.JST_EJB_MODULE;
	public static final IProjectFacetVersion EJB_11 = ProjectFacetsManager.getProjectFacet(EJB).getVersion("1.1"); //$NON-NLS-1$
	public static final IProjectFacetVersion EJB_20 = ProjectFacetsManager.getProjectFacet(EJB).getVersion("2.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion EJB_21 = ProjectFacetsManager.getProjectFacet(EJB).getVersion("2.1"); //$NON-NLS-1$
	public static final IProjectFacetVersion EJB_30 = ProjectFacetsManager.getProjectFacet(EJB).getVersion("3.0"); //$NON-NLS-1$

	public static final String DYNAMIC_WEB = IModuleConstants.JST_WEB_MODULE;
	public static final IProjectFacetVersion DYNAMIC_WEB_22 = ProjectFacetsManager.getProjectFacet(DYNAMIC_WEB).getVersion("2.2"); //$NON-NLS-1$
	public static final IProjectFacetVersion DYNAMIC_WEB_23 = ProjectFacetsManager.getProjectFacet(DYNAMIC_WEB).getVersion("2.3"); //$NON-NLS-1$
	public static final IProjectFacetVersion DYNAMIC_WEB_24 = ProjectFacetsManager.getProjectFacet(DYNAMIC_WEB).getVersion("2.4"); //$NON-NLS-1$
	public static final IProjectFacetVersion DYNAMIC_WEB_25 = ProjectFacetsManager.getProjectFacet(DYNAMIC_WEB).getVersion("2.5"); //$NON-NLS-1$
	
	public static final String DYNAMIC_WEB_XDOCLET = "jst.web.xdoclet"; //$NON-NLS-1$
	
	public static final String UTILITY = IModuleConstants.JST_UTILITY_MODULE;

	public static final String JCA = IModuleConstants.JST_CONNECTOR_MODULE;

	public static final String STATIC_WEB = IModuleConstants.WST_WEB_MODULE;

	public static final String JAVA = IModuleConstants.JST_JAVA;
}
