/*******************************************************************************

 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.project.facet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.j2ee.internal.wizard.J2EEComponentFacetCreationWizardPage;
import org.eclipse.jst.j2ee.web.project.facet.IWebFragmentProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetUtils;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectWorkingCopy;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public class WebFragmentProjectFirstPage extends J2EEComponentFacetCreationWizardPage {

	protected WebAppSelectionPanel warPanel;
	public WebFragmentProjectFirstPage(IDataModel dataModel, String pageName) {
		super(dataModel, pageName);
		setTitle(Resources.pageTitle);
		setDescription(Resources.pageDescription);
	}

	private static final class Resources extends NLS {
		public static String pageTitle;
		public static String pageDescription;
		static {
			initializeMessages(WebFragmentProjectFirstPage.class.getName(), Resources.class);
		}
	}
	
	@Override
	protected Composite createTopLevelComposite(Composite parent) {
		setShouldAddEARComposite(false);
        final Composite top = super.createTopLevelComposite(parent);
		createWarComposite(top);
        createWorkingSetGroupPanel(top, new String[] { RESOURCE_WORKING_SET, JAVA_WORKING_SET });
		return top;
	}

	private void createWarComposite(Composite top) 
	{
	    final IFacetedProjectWorkingCopy fpjwc
	        = (IFacetedProjectWorkingCopy) this.model.getProperty( FACETED_PROJECT_WORKING_COPY );
	    
	    final String moduleFacetId = getModuleTypeID();
	    final IProjectFacet moduleFacet = ProjectFacetsManager.getProjectFacet( moduleFacetId );
	    final IFacetedProject.Action action = fpjwc.getProjectFacetAction( moduleFacet );
	    
		warPanel = new WebAppSelectionPanel( (IDataModel) action.getConfig(), top );
	}
	
	@Override
	protected String[] getValidationPropertyNames() 
	{
		String[] superProperties = super.getValidationPropertyNames();
		List list = Arrays.asList(superProperties);
		ArrayList arrayList = new ArrayList();
		arrayList.addAll( list );
		arrayList.add( IWebFragmentProjectCreationDataModelProperties.WAR_PROJECT_NAME );
		arrayList.add( IWebFragmentProjectCreationDataModelProperties.ADD_TO_WAR );
		return (String[])arrayList.toArray( new String[0] );	
	}

	@Override
	protected String getModuleFacetID() {
		return IModuleConstants.JST_WEBFRAGMENT_MODULE;
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
		    
		    if(primaryFacetVersion == WebFacetUtils.WEBFRAGMENT_40)
		    {
		        facets.add(JavaFacet.VERSION_1_8);
		    }
		    else if(primaryFacetVersion == WebFacetUtils.WEBFRAGMENT_31)
		    {
		        facets.add(JavaFacet.VERSION_1_7);
		    }
		    else if(primaryFacetVersion == WebFacetUtils.WEBFRAGMENT_30)
		    {
		        facets.add(JavaFacet.VERSION_1_6);
		    }
	    }
	    return Collections.unmodifiableSet( facets );
	}
}
