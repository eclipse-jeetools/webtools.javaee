/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.servlet.ui.project.facet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.wizard.J2EEComponentFacetCreationWizardPage;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetUtils;
import org.eclipse.jst.servlet.ui.IWebUIContextIds;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public class WebProjectFirstPage extends J2EEComponentFacetCreationWizardPage {

	public WebProjectFirstPage(IDataModel model, String pageName) {
		super(model, pageName);
		setTitle(WEBUIMessages.WEB_PROJECT_MAIN_PG_TITLE);
		setDescription(WEBUIMessages.WEB_PROJECT_MAIN_PG_DESC);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.WEB_PROJECT_WIZARD_BANNER));
		setInfopopID(IWebUIContextIds.NEW_DYNAMIC_WEB_PROJECT_PAGE1);
	}

	@Override
	protected String getModuleFacetID() {
		return J2EEProjectUtilities.DYNAMIC_WEB;
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
		    
		    if( primaryFacetVersion == WebFacetUtils.WEB_31 )
		    {
		        facets.add( JavaFacet.VERSION_1_7 );
		    }
		    else if( primaryFacetVersion == WebFacetUtils.WEB_30 )
		    {
		        facets.add( JavaFacet.VERSION_1_6 );
		    }
		    else if(primaryFacetVersion == WebFacetUtils.WEB_25 )
		    {
		        facets.add( JavaFacet.VERSION_1_5 );
		    }
		    else if( primaryFacetVersion == WebFacetUtils.WEB_24 )
		    {
		        facets.add( JavaFacet.VERSION_1_4 );
		    }
		    else if( primaryFacetVersion == WebFacetUtils.WEB_23 || primaryFacetVersion == WebFacetUtils.WEB_22 )
		    {
		        facets.add( JavaFacet.VERSION_1_3 );
		    }
	    }
		return Collections.unmodifiableSet( facets );
	}

}
