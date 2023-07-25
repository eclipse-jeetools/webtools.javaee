/*******************************************************************************

 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.web.project.facet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jem.util.UIContextDetermination;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.common.project.facet.core.JavaFacetInstallConfig;
import org.eclipse.jst.j2ee.application.internal.operations.AddWebFragmentComponentToWebApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.IAddWebFragmentComponentToWebApplicationDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences.Keys;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;
import org.eclipse.wst.common.project.facet.core.FacetedProjectFramework;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectWorkingCopy;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntimeComponent;
import org.eclipse.wst.web.internal.facet.RuntimePresetMappingRegistry;

public abstract class WebFacetInstallDelegateForWebFragmentProjects {

	protected static void addToClasspath(final IJavaProject jproj, final IClasspathEntry entry) throws CoreException {
		final IClasspathEntry[] existingEntries = jproj.getRawClasspath();
		for(IClasspathEntry existingEntry : existingEntries){
			if(existingEntry.equals(entry)){
				return;
			}
		}
		final IClasspathEntry[] updated = new IClasspathEntry[existingEntries.length + 1];
		System.arraycopy(existingEntries, 0, updated, 0, existingEntries.length);
		updated[existingEntries.length] = entry;
		jproj.setRawClasspath(updated, null);
	}

    protected void installWARFacet( final String j2eeVersionText, 
                                    final String warProjectName, 
                                    final IRuntime runtime, 
                                    final IProgressMonitor monitor )
    {
		IProject project = ProjectUtilities.getProject(warProjectName); 
		if( project.exists())
			return;
		
		IFacetedProjectWorkingCopy fpjwc = null;
		try
		{	
			fpjwc = FacetedProjectFramework.createNewProject();		
		
		
			fpjwc.setProjectName( warProjectName );
			
			if( runtime != null )
			{
			    fpjwc.setTargetedRuntimes( Collections.singleton( runtime ) );
			}
			ArrayList <IProjectFacet> requiredFacets = new ArrayList<IProjectFacet>();
	        requiredFacets.add(JavaFacetUtils.JAVA_FACET);
	        requiredFacets.add(IJ2EEFacetConstants.DYNAMIC_WEB_FACET);
	        final Collection<IProjectFacet> fixedFacets = requiredFacets;
	        fpjwc.setFixedProjectFacets( new HashSet<IProjectFacet>( fixedFacets ) );
			//fpjwc.setFixedProjectFacets( Collections.singleton( JavaFacetUtils.JAVA_FACET) );
			fpjwc.setSelectedPreset( FacetedProjectFramework.DEFAULT_CONFIGURATION_PRESET_ID );
			
			if( j2eeVersionText != null )
			{
			    final IProjectFacetVersion defaultWarFacetVersion
			        = fpjwc.getProjectFacetVersion( IJ2EEFacetConstants.DYNAMIC_WEB_FACET );
			    
			    if( ! defaultWarFacetVersion.getVersionString().equals( j2eeVersionText ) )
			    {
			        String presetId = null;
			        
			        if( runtime != null )
			        {
	    		        for( IRuntimeComponent rc : runtime.getRuntimeComponents() )
	    		        {
	    		            presetId = RuntimePresetMappingRegistry.INSTANCE.getPresetID
	    		            ( 
	    		                rc.getRuntimeComponentType().getId(),
	    		                rc.getRuntimeComponentVersion().getVersionString(), 
	    		                IJ2EEFacetConstants.DYNAMIC_WEB_FACET.getId(), 
	    		                j2eeVersionText 
	    		            );
	    		            
	    		            if( presetId != null )
	    		            {
	    		                break;
	    		            }
	    		        }
			        }
			        
	                final IProjectFacetVersion warFacetVersion
	                    = IJ2EEFacetConstants.DYNAMIC_WEB_FACET.getVersion( j2eeVersionText );

            
	                
	                // Note that the next call is necessary even if a preset is going to be selected 
	                // later since it allows the dynamic preset to adjust for the war facet version.
	            	ArrayList <IProjectFacetVersion> requiredFacetVersions = new ArrayList<IProjectFacetVersion>();
	            	requiredFacetVersions.add(JavaFacetUtils.JAVA_FACET.getVersion("1.5")); //$NON-NLS-1$
	            	requiredFacetVersions.add(warFacetVersion);
	    	        final Collection<IProjectFacetVersion> fixedFacetVersions = requiredFacetVersions;
	    	        fpjwc.setProjectFacets( new HashSet<IProjectFacetVersion>( fixedFacetVersions ) );
	    	        
	                //fpjwc.setProjectFacets( Collections.singleton( warFacetVersion ) );
	                
			        if( presetId != null )
			        {
			            fpjwc.setSelectedPreset( presetId );
			        }
			    }

			    Set<IFacetedProject.Action> actions = fpjwc.getProjectFacetActions();
			    for(IFacetedProject.Action action: actions){
			    	Object actionConfig = action.getConfig();
			    	if( actionConfig instanceof JavaFacetInstallConfig){
			    		JavaFacetInstallConfig c = (JavaFacetInstallConfig) actionConfig;
			    		c.setDefaultOutputFolder(new Path(J2EEPlugin.getDefault().getJ2EEPreferences().getString(Keys.DYN_WEB_OUTPUT_FOLDER)));
			    	}
			    }
			}
			try
			{
			    fpjwc.commitChanges( null );
			}
			catch( CoreException e )
			{
			    J2EEPlugin.logError( e );
			}
		}finally {
			if(fpjwc != null){
				fpjwc.dispose();
			}
		}
	}

    protected void addToWar(IVirtualComponent warComp, IVirtualComponent j2eeComp, String moduleURI ){
		final IDataModel dataModel = DataModelFactory.createDataModel(new AddWebFragmentComponentToWebApplicationDataModelProvider());
		Map map = (Map)dataModel.getProperty(IAddWebFragmentComponentToWebApplicationDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP);
		map.put(j2eeComp, moduleURI);
		
		dataModel.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, warComp);
			
		List modList = (List) dataModel.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
		modList.add(j2eeComp);
		dataModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, modList);
		dataModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH, "/WEB-INF/lib"); //$NON-NLS-1$
		try {
			dataModel.getDefaultOperation().execute(null, null);
		} catch (ExecutionException e) {
			J2EEPlugin.logError(e);
		}
    }
    
    protected void installAndAddModuletoWAR( String j2eeVersionText,
    			String				warProjectName,
    			IRuntime			runtime,
    			IProject			moduleProject,
    			String				moduleURI,
    			IProgressMonitor	monitor ){
    	
		installWARFacet(j2eeVersionText,
					warProjectName,
					runtime,
					monitor);
		
		final IVirtualComponent c = ComponentCore.createComponent( moduleProject );
		final IProject warProject = ProjectUtilities.getProject( warProjectName );
		final IVirtualComponent warComp = ComponentCore.createComponent( warProject );
		
		if( UIContextDetermination.getCurrentContext() == UIContextDetermination.HEADLESS_CONTEXT ){
			boolean isValidating = RendererFactory.getDefaultRendererFactory().isValidating();
			try{
				if( isValidating ){
					RendererFactory.getDefaultRendererFactory().setValidating(false);
				}
				addToWar( warComp, c, moduleURI );
			}finally{
				RendererFactory.getDefaultRendererFactory().setValidating(isValidating);
			}
		}
		else{
			addToWar( warComp, c, moduleURI );
		}
    }
}
