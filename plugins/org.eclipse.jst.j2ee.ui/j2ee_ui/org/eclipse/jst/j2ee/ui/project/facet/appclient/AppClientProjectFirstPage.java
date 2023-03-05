/*******************************************************************************
 * Copyright (c) 2005, 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ui.project.facet.appclient;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.wizard.J2EEComponentFacetCreationWizardPage;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public class AppClientProjectFirstPage extends J2EEComponentFacetCreationWizardPage {

	public AppClientProjectFirstPage(IDataModel dataModel, String pageName) {
		super(dataModel, pageName);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_PROJECT_MAIN_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_PROJECT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.APP_CLIENT_PROJECT_WIZARD_BANNER));
		setInfopopID(IJ2EEUIContextIds.NEW_APPCLIENT_WIZARD_P1);
	}

	@Override
	protected String getModuleFacetID() {
		return J2EEProjectUtilities.APPLICATION_CLIENT;
	}

	@Override
	protected Set<IProjectFacetVersion> getFacetConfiguration( final IProjectFacetVersion primaryFacetVersion )
	{
		IRuntime runtime = (IRuntime)model.getProperty(IFacetProjectCreationDataModelProperties.FACET_RUNTIME);
	    final Set<IProjectFacetVersion> facets = new HashSet<IProjectFacetVersion>( 2 );
	    
	    if(runtime != null) {
	    	facets.addAll(super.getFacetConfiguration(primaryFacetVersion));
	    }
	    else {
		    facets.add( primaryFacetVersion );
	        if (primaryFacetVersion == IJ2EEFacetConstants.APPLICATION_CLIENT_100) {
                  facets.add(JavaFacet.VERSION_11);
	        }
	        else if (primaryFacetVersion == IJ2EEFacetConstants.APPLICATION_CLIENT_80) {
		    		facets.add(JavaFacet.VERSION_1_8);
		    }
		    else if( primaryFacetVersion == IJ2EEFacetConstants.APPLICATION_CLIENT_70)
		    {
		        facets.add( JavaFacet.VERSION_1_7 );
		    }
		    else if( primaryFacetVersion == IJ2EEFacetConstants.APPLICATION_CLIENT_60)
		    {
		        facets.add( JavaFacet.VERSION_1_7 );
		    }
		    else if(primaryFacetVersion == IJ2EEFacetConstants.APPLICATION_CLIENT_50)
		    {
		        facets.add( JavaFacet.VERSION_1_5 );
		    }
		    else if( primaryFacetVersion == IJ2EEFacetConstants.APPLICATION_CLIENT_14)
		    {
		        facets.add( JavaFacet.VERSION_1_4 );
		    }
		    else if( primaryFacetVersion == IJ2EEFacetConstants.APPLICATION_CLIENT_13 ||
		             primaryFacetVersion == IJ2EEFacetConstants.APPLICATION_CLIENT_12 )
		    {
		        facets.add( JavaFacet.VERSION_1_3 );
		    }
	    }
	    return Collections.unmodifiableSet( facets );
	}
}
