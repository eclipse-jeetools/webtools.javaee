/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.project.facet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.wizard.J2EEComponentFacetCreationWizardPage;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public class EjbProjectFirstPage extends J2EEComponentFacetCreationWizardPage {

	@Override
	protected String getModuleFacetID() {
		return J2EEProjectUtilities.EJB;
	}

	public EjbProjectFirstPage(IDataModel model, String pageName) {
		super(model, pageName);
		setTitle(EJBUIMessages.EJB_PROJECT_MAIN_PG_TITLE);
		setDescription(EJBUIMessages.EJB_PROJECT_MAIN_PG_DESC);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EJB_PROJECT_WIZARD_BANNER));
		setInfopopID(IJ2EEUIContextIds.NEW_EJB_WIZARD_P1);
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
		    facets.add(primaryFacetVersion);
		    
		    if(primaryFacetVersion == IJ2EEFacetConstants.EJB_32)
		    {
		        facets.add(JavaFacet.VERSION_1_7);
		    }
		    else if(primaryFacetVersion == IJ2EEFacetConstants.EJB_31)
		    {
		        facets.add(JavaFacet.VERSION_1_6);
		    }
		    else if(primaryFacetVersion == IJ2EEFacetConstants.EJB_30)
		    {
		        facets.add(JavaFacet.VERSION_1_5);
		    }
		    else if(primaryFacetVersion == IJ2EEFacetConstants.EJB_21)
		    {
		        facets.add(JavaFacet.VERSION_1_4);
		    }
		    else if(primaryFacetVersion == IJ2EEFacetConstants.EJB_20 || 
		    		primaryFacetVersion == IJ2EEFacetConstants.EJB_11)
		    {
		        facets.add(JavaFacet.VERSION_1_3);
		    }
	    }
	    return Collections.unmodifiableSet( facets );
	}
}
