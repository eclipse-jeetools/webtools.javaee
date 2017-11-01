/*******************************************************************************
 * Copyright (c) 2007, 2015 IBM Corporation and others.
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
	public IProjectFacet ENTERPRISE_APPLICATION_FACET = J2EEFacetConstantsProvider.getProjectFacet(ENTERPRISE_APPLICATION);
	public static final IProjectFacetVersion ENTERPRISE_APPLICATION_12 = J2EEFacetConstantsProvider.getProjectFacetVersion(ENTERPRISE_APPLICATION_FACET, "1.2"); //$NON-NLS-1$
	public static final IProjectFacetVersion ENTERPRISE_APPLICATION_13 = J2EEFacetConstantsProvider.getProjectFacetVersion(ENTERPRISE_APPLICATION_FACET, "1.3"); //$NON-NLS-1$
	public static final IProjectFacetVersion ENTERPRISE_APPLICATION_14 = J2EEFacetConstantsProvider.getProjectFacetVersion(ENTERPRISE_APPLICATION_FACET, "1.4"); //$NON-NLS-1$
	public static final IProjectFacetVersion ENTERPRISE_APPLICATION_50 = J2EEFacetConstantsProvider.getProjectFacetVersion(ENTERPRISE_APPLICATION_FACET, "5.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion ENTERPRISE_APPLICATION_60 = J2EEFacetConstantsProvider.getProjectFacetVersion(ENTERPRISE_APPLICATION_FACET, "6.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion ENTERPRISE_APPLICATION_70 = J2EEFacetConstantsProvider.getProjectFacetVersion(ENTERPRISE_APPLICATION_FACET, "7.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion ENTERPRISE_APPLICATION_80 = J2EEFacetConstantsProvider.getProjectFacetVersion(ENTERPRISE_APPLICATION_FACET, "8.0"); //$NON-NLS-1$
	
	public static final String APPLICATION_CLIENT = IModuleConstants.JST_APPCLIENT_MODULE;
	public IProjectFacet APPLICATION_CLIENT_FACET = J2EEFacetConstantsProvider.getProjectFacet(APPLICATION_CLIENT);
	public static final IProjectFacetVersion APPLICATION_CLIENT_12 = J2EEFacetConstantsProvider.getProjectFacetVersion(APPLICATION_CLIENT_FACET, "1.2"); //$NON-NLS-1$
	public static final IProjectFacetVersion APPLICATION_CLIENT_13 = J2EEFacetConstantsProvider.getProjectFacetVersion(APPLICATION_CLIENT_FACET, "1.3"); //$NON-NLS-1$
	public static final IProjectFacetVersion APPLICATION_CLIENT_14 = J2EEFacetConstantsProvider.getProjectFacetVersion(APPLICATION_CLIENT_FACET, "1.4"); //$NON-NLS-1$
	public static final IProjectFacetVersion APPLICATION_CLIENT_50 = J2EEFacetConstantsProvider.getProjectFacetVersion(APPLICATION_CLIENT_FACET, "5.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion APPLICATION_CLIENT_60 = J2EEFacetConstantsProvider.getProjectFacetVersion(APPLICATION_CLIENT_FACET, "6.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion APPLICATION_CLIENT_70 = J2EEFacetConstantsProvider.getProjectFacetVersion(APPLICATION_CLIENT_FACET, "7.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion APPLICATION_CLIENT_80 = J2EEFacetConstantsProvider.getProjectFacetVersion(APPLICATION_CLIENT_FACET, "8.0"); //$NON-NLS-1$
	
	public static final String EJB = IModuleConstants.JST_EJB_MODULE;
	public IProjectFacet EJB_FACET = J2EEFacetConstantsProvider.getProjectFacet(EJB);
	public static final IProjectFacetVersion EJB_11 = J2EEFacetConstantsProvider.getProjectFacetVersion(EJB_FACET, "1.1"); //$NON-NLS-1$
	public static final IProjectFacetVersion EJB_20 = J2EEFacetConstantsProvider.getProjectFacetVersion(EJB_FACET, "2.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion EJB_21 = J2EEFacetConstantsProvider.getProjectFacetVersion(EJB_FACET, "2.1"); //$NON-NLS-1$
	public static final IProjectFacetVersion EJB_30 = J2EEFacetConstantsProvider.getProjectFacetVersion(EJB_FACET, "3.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion EJB_31 = J2EEFacetConstantsProvider.getProjectFacetVersion(EJB_FACET, "3.1"); //$NON-NLS-1$
	public static final IProjectFacetVersion EJB_32 = J2EEFacetConstantsProvider.getProjectFacetVersion(EJB_FACET, "3.2"); //$NON-NLS-1$

	public static final String DYNAMIC_WEB = IModuleConstants.JST_WEB_MODULE;
	public IProjectFacet DYNAMIC_WEB_FACET = J2EEFacetConstantsProvider.getProjectFacet(DYNAMIC_WEB);
	public static final IProjectFacetVersion DYNAMIC_WEB_22 = J2EEFacetConstantsProvider.getProjectFacetVersion(DYNAMIC_WEB_FACET, "2.2"); //$NON-NLS-1$
	public static final IProjectFacetVersion DYNAMIC_WEB_23 = J2EEFacetConstantsProvider.getProjectFacetVersion(DYNAMIC_WEB_FACET, "2.3"); //$NON-NLS-1$
	public static final IProjectFacetVersion DYNAMIC_WEB_24 = J2EEFacetConstantsProvider.getProjectFacetVersion(DYNAMIC_WEB_FACET, "2.4"); //$NON-NLS-1$
	public static final IProjectFacetVersion DYNAMIC_WEB_25 = J2EEFacetConstantsProvider.getProjectFacetVersion(DYNAMIC_WEB_FACET, "2.5"); //$NON-NLS-1$
	public static final IProjectFacetVersion DYNAMIC_WEB_30 = J2EEFacetConstantsProvider.getProjectFacetVersion(DYNAMIC_WEB_FACET, "3.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion DYNAMIC_WEB_31 = J2EEFacetConstantsProvider.getProjectFacetVersion(DYNAMIC_WEB_FACET, "3.1"); //$NON-NLS-1$
	public static final IProjectFacetVersion DYNAMIC_WEB_40 = J2EEFacetConstantsProvider.getProjectFacetVersion(DYNAMIC_WEB_FACET, "4.0"); //$NON-NLS-1$
	
	public static final String DYNAMIC_WEB_XDOCLET = "jst.web.xdoclet"; //$NON-NLS-1$
	
	public static final String UTILITY = IModuleConstants.JST_UTILITY_MODULE;
	public IProjectFacet UTILITY_FACET = J2EEFacetConstantsProvider.getProjectFacet(UTILITY);
	public static final IProjectFacetVersion UTILITY_FACET_10 = J2EEFacetConstantsProvider.getProjectFacetVersion(UTILITY_FACET, "1.0"); //$NON-NLS-1$ 

	public static final String JCA = IModuleConstants.JST_CONNECTOR_MODULE;
	public IProjectFacet JCA_FACET = J2EEFacetConstantsProvider.getProjectFacet(JCA);
	public static final IProjectFacetVersion JCA_10 = J2EEFacetConstantsProvider.getProjectFacetVersion(JCA_FACET, "1.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion JCA_15 = J2EEFacetConstantsProvider.getProjectFacetVersion(JCA_FACET, "1.5"); //$NON-NLS-1$
	public static final IProjectFacetVersion JCA_16 = J2EEFacetConstantsProvider.getProjectFacetVersion(JCA_FACET, "1.6"); //$NON-NLS-1$
	public static final IProjectFacetVersion JCA_17 = J2EEFacetConstantsProvider.getProjectFacetVersion(JCA_FACET, "1.7"); //$NON-NLS-1$

	public static final String STATIC_WEB = IModuleConstants.WST_WEB_MODULE;

	public static final String JAVA = IModuleConstants.JST_JAVA;
	public static final String WEBFRAGMENT = IModuleConstants.JST_WEBFRAGMENT_MODULE;
	public static final IProjectFacet WEBFRAGMENT_FACET = J2EEFacetConstantsProvider.getProjectFacet(WEBFRAGMENT);
	public static final IProjectFacetVersion WEBFRAGMENT_30 = J2EEFacetConstantsProvider.getProjectFacetVersion(WEBFRAGMENT_FACET, "3.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEBFRAGMENT_31 = J2EEFacetConstantsProvider.getProjectFacetVersion(WEBFRAGMENT_FACET, "3.1"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEBFRAGMENT_40 = J2EEFacetConstantsProvider.getProjectFacetVersion(WEBFRAGMENT_FACET, "4.0"); //$NON-NLS-1$
	

	/**
	 * Class intended to be used by {@link IJ2EEFacetConstants} to retrieve facet and facet version
	 * object during initialization. 
	 */
	static class J2EEFacetConstantsProvider{
		
		/**
		 * Returns the project facet descriptor corresponding to the specified id, or <code>null</code>
		 * if id is not found.  
		 * @param facetId the id of the project facet
		 * @return the project facet descriptor, or <code>null</code> if the id is not found
		 */
		static IProjectFacet getProjectFacet(String facetId){
			try{
				return ProjectFacetsManager.getProjectFacet(facetId);
			}
			catch (IllegalArgumentException e){
				// This happens if the facet does not exist. 
				// Do not log, since ProjectFacetsManager.getProjectFacet(String)
				// already logs an error.
				return null;
			}
		}
		
		/**
		 * Returns the descriptor of the given project facet version.
		 * @param projectFacet the project facet version. If <code>null</code>, this method returns <code>null</code>
		 * @param version the version string
		 * @return the descriptor of the given project facet version, or <code>null</code> if the version is not found
		 */
		static IProjectFacetVersion getProjectFacetVersion(IProjectFacet projectFacet, String version){
			if (projectFacet == null){
				return null;
			}
			return projectFacet.getVersion(version);
		}
	}
	
	
}
