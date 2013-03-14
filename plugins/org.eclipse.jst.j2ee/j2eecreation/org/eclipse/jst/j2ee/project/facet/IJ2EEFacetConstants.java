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
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public interface IJ2EEFacetConstants {

	//moduleTypes
	public static final String ENTERPRISE_APPLICATION = IModuleConstants.JST_EAR_MODULE;
	public IProjectFacet ENTERPRISE_APPLICATION_FACET = ProjectFacetsManager.getProjectFacet(ENTERPRISE_APPLICATION);
	public static final IProjectFacetVersion ENTERPRISE_APPLICATION_12 = ENTERPRISE_APPLICATION_FACET.getVersion("1.2"); //$NON-NLS-1$
	public static final IProjectFacetVersion ENTERPRISE_APPLICATION_13 = ENTERPRISE_APPLICATION_FACET.getVersion("1.3"); //$NON-NLS-1$
	public static final IProjectFacetVersion ENTERPRISE_APPLICATION_14 = ENTERPRISE_APPLICATION_FACET.getVersion("1.4"); //$NON-NLS-1$
	public static final IProjectFacetVersion ENTERPRISE_APPLICATION_50 = ENTERPRISE_APPLICATION_FACET.getVersion("5.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion ENTERPRISE_APPLICATION_60 = ENTERPRISE_APPLICATION_FACET.getVersion("6.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion ENTERPRISE_APPLICATION_70 = ENTERPRISE_APPLICATION_FACET.getVersion("7.0"); //$NON-NLS-1$
	
	public static final String APPLICATION_CLIENT = IModuleConstants.JST_APPCLIENT_MODULE;
	public IProjectFacet APPLICATION_CLIENT_FACET = ProjectFacetsManager.getProjectFacet(APPLICATION_CLIENT);
	public static final IProjectFacetVersion APPLICATION_CLIENT_12 = APPLICATION_CLIENT_FACET.getVersion("1.2"); //$NON-NLS-1$
	public static final IProjectFacetVersion APPLICATION_CLIENT_13 = APPLICATION_CLIENT_FACET.getVersion("1.3"); //$NON-NLS-1$
	public static final IProjectFacetVersion APPLICATION_CLIENT_14 = APPLICATION_CLIENT_FACET.getVersion("1.4"); //$NON-NLS-1$
	public static final IProjectFacetVersion APPLICATION_CLIENT_50 = APPLICATION_CLIENT_FACET.getVersion("5.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion APPLICATION_CLIENT_60 = APPLICATION_CLIENT_FACET.getVersion("6.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion APPLICATION_CLIENT_70 = APPLICATION_CLIENT_FACET.getVersion("7.0"); //$NON-NLS-1$
	
	public static final String EJB = IModuleConstants.JST_EJB_MODULE;
	public IProjectFacet EJB_FACET = ProjectFacetsManager.getProjectFacet(EJB);
	public static final IProjectFacetVersion EJB_11 = EJB_FACET.getVersion("1.1"); //$NON-NLS-1$
	public static final IProjectFacetVersion EJB_20 = EJB_FACET.getVersion("2.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion EJB_21 = EJB_FACET.getVersion("2.1"); //$NON-NLS-1$
	public static final IProjectFacetVersion EJB_30 = EJB_FACET.getVersion("3.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion EJB_31 = EJB_FACET.getVersion("3.1"); //$NON-NLS-1$
	public static final IProjectFacetVersion EJB_32 = EJB_FACET.getVersion("3.2"); //$NON-NLS-1$

	public static final String DYNAMIC_WEB = IModuleConstants.JST_WEB_MODULE;
	public IProjectFacet DYNAMIC_WEB_FACET = ProjectFacetsManager.getProjectFacet(DYNAMIC_WEB);
	public static final IProjectFacetVersion DYNAMIC_WEB_22 = DYNAMIC_WEB_FACET.getVersion("2.2"); //$NON-NLS-1$
	public static final IProjectFacetVersion DYNAMIC_WEB_23 = DYNAMIC_WEB_FACET.getVersion("2.3"); //$NON-NLS-1$
	public static final IProjectFacetVersion DYNAMIC_WEB_24 = DYNAMIC_WEB_FACET.getVersion("2.4"); //$NON-NLS-1$
	public static final IProjectFacetVersion DYNAMIC_WEB_25 = DYNAMIC_WEB_FACET.getVersion("2.5"); //$NON-NLS-1$
	public static final IProjectFacetVersion DYNAMIC_WEB_30 = DYNAMIC_WEB_FACET.getVersion("3.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion DYNAMIC_WEB_31 = DYNAMIC_WEB_FACET.getVersion("3.1"); //$NON-NLS-1$
	
	public static final String DYNAMIC_WEB_XDOCLET = "jst.web.xdoclet"; //$NON-NLS-1$
	
	public static final String UTILITY = IModuleConstants.JST_UTILITY_MODULE;
	public IProjectFacet UTILITY_FACET = ProjectFacetsManager.getProjectFacet(UTILITY);
	public static final IProjectFacetVersion UTILITY_FACET_10 = UTILITY_FACET.getVersion("1.0"); //$NON-NLS-1$ 

	public static final String JCA = IModuleConstants.JST_CONNECTOR_MODULE;
	public IProjectFacet JCA_FACET = ProjectFacetsManager.getProjectFacet(JCA);
	public static final IProjectFacetVersion JCA_10 = JCA_FACET.getVersion("1.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion JCA_15 = JCA_FACET.getVersion("1.5"); //$NON-NLS-1$
	public static final IProjectFacetVersion JCA_16 = JCA_FACET.getVersion("1.6"); //$NON-NLS-1$
	public static final IProjectFacetVersion JCA_17 = JCA_FACET.getVersion("1.7"); //$NON-NLS-1$

	public static final String STATIC_WEB = IModuleConstants.WST_WEB_MODULE;

	public static final String JAVA = IModuleConstants.JST_JAVA;
	public static final String WEBFRAGMENT = IModuleConstants.JST_WEBFRAGMENT_MODULE;
	public static final IProjectFacet WEBFRAGMENT_FACET = ProjectFacetsManager.getProjectFacet(WEBFRAGMENT);
	public static final IProjectFacetVersion WEBFRAGMENT_30 = WEBFRAGMENT_FACET.getVersion("3.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEBFRAGMENT_31 = WEBFRAGMENT_FACET.getVersion("3.1"); //$NON-NLS-1$
	
}
