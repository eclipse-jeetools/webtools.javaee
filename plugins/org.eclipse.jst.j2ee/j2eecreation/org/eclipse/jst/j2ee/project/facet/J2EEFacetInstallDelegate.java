/*******************************************************************************
 * Copyright (c) 2003, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project.facet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.UIContextDetermination;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.IAddComponentToEnterpriseApplicationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.ManifestFileCreationAction;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;
import org.eclipse.wst.common.project.facet.core.FacetedProjectFramework;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectWorkingCopy;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntimeComponent;
import org.eclipse.wst.web.internal.facet.RuntimePresetMappingRegistry;

public abstract class J2EEFacetInstallDelegate {

	protected static void addToClasspath(final IJavaProject jproj, final IClasspathEntry entry) throws CoreException {
		final IClasspathEntry[] existingEntries = jproj.getRawClasspath();
		for(IClasspathEntry existingEntry : existingEntries){
			if(existingEntry.getPath().equals(entry.getPath())){  // see bug 423742
				return;
			}
		}
		final IClasspathEntry[] updated = new IClasspathEntry[existingEntries.length + 1];
		System.arraycopy(existingEntries, 0, updated, 0, existingEntries.length);
		updated[existingEntries.length] = entry;
		jproj.setRawClasspath(updated, null);
	}

    protected void installEARFacet( final String j2eeVersionText, 
                                    final String earProjectName, 
                                    final IRuntime runtime, 
                                    final IProgressMonitor monitor )
    {
		IProject project = ProjectUtilities.getProject(earProjectName); 
		if( project.exists())
			return;
		
		IFacetedProjectWorkingCopy fpjwc = null;
		try
		{	
			fpjwc = FacetedProjectFramework.createNewProject();		
		
		
			fpjwc.setProjectName( earProjectName );
			
			if( runtime != null )
			{
			    fpjwc.setTargetedRuntimes( Collections.singleton( runtime ) );
			}
			
			fpjwc.setFixedProjectFacets( Collections.singleton( IJ2EEFacetConstants.ENTERPRISE_APPLICATION_FACET ) );
			fpjwc.setSelectedPreset( FacetedProjectFramework.DEFAULT_CONFIGURATION_PRESET_ID );
			
			if( j2eeVersionText != null )
			{
			    final IProjectFacetVersion defaultEarFacetVersion
			        = fpjwc.getProjectFacetVersion( IJ2EEFacetConstants.ENTERPRISE_APPLICATION_FACET );
			    
			    if( ! defaultEarFacetVersion.getVersionString().equals( j2eeVersionText ) )
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
	    		                IJ2EEFacetConstants.ENTERPRISE_APPLICATION_FACET.getId(), 
	    		                j2eeVersionText 
	    		            );
	    		            
	    		            if( presetId != null )
	    		            {
	    		                break;
	    		            }
	    		        }
			        }
			        
	                final IProjectFacetVersion earFacetVersion
	                    = IJ2EEFacetConstants.ENTERPRISE_APPLICATION_FACET.getVersion( j2eeVersionText );
	            
	                // Note that the next call is necessary even if a preset is going to be selected 
	                // later since it allows the dynamic preset to adjust for the ear facet version.
	                
	                fpjwc.setProjectFacets( Collections.singleton( earFacetVersion ) );
	                
			        if( presetId != null )
			        {
			            fpjwc.setSelectedPreset( presetId );
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
	
    protected void createManifest(IProject project, IContainer aFolder, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
    	
        IFile file = aFolder.getFile(new Path(J2EEConstants.MANIFEST_URI));
        if (file.exists()) return;
        try {
            ManifestFileCreationAction.createManifestFile(file, project);
        } catch (CoreException e) {
            J2EEPlugin.logError(e);
        } catch (IOException e) {
            J2EEPlugin.logError(e);
        }
    }

    protected void addToEar(IVirtualComponent earComp, IVirtualComponent j2eeComp, String moduleURI ){
		final IDataModel dataModel = DataModelFactory.createDataModel(new AddComponentToEnterpriseApplicationDataModelProvider());
		Map map = (Map)dataModel.getProperty(IAddComponentToEnterpriseApplicationDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP);
		map.put(j2eeComp, moduleURI);
		
		dataModel.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComp);
			
		List modList = (List) dataModel.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
		modList.add(j2eeComp);
		dataModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, modList);
		try {
			dataModel.getDefaultOperation().execute(null, null);
		} catch (ExecutionException e) {
			J2EEPlugin.logError(e);
		}
    }
    
    protected void installAndAddModuletoEAR( String j2eeVersionText,
    			String				earProjectName,
    			IRuntime			runtime,
    			IProject			moduleProject,
    			String				moduleURI,
    			IProgressMonitor	monitor ){
    	
		installEARFacet(j2eeVersionText,
					earProjectName,
					runtime,
					monitor);
		
		final IVirtualComponent c = ComponentCore.createComponent( moduleProject );
		final IProject earProject = ProjectUtilities.getProject( earProjectName );
		final IVirtualComponent earComp = ComponentCore.createComponent( earProject );
		
		if( UIContextDetermination.getCurrentContext() == UIContextDetermination.HEADLESS_CONTEXT ){
			boolean isValidating = RendererFactory.getDefaultRendererFactory().isValidating();
			try{
				if( isValidating ){
					RendererFactory.getDefaultRendererFactory().setValidating(false);
				}
				addToEar( earComp, c, moduleURI );
			}finally{
				RendererFactory.getDefaultRendererFactory().setValidating(isValidating);
			}
		}
		else
		 addToEar( earComp, c, moduleURI );
    }
    
    /**
     * This method will set the output property on the model element for the given component.
     * 
     * @param model
     * @param component
     */
    protected void setOutputFolder(IDataModel model, IVirtualComponent component) {
		IJavaProject javaProject = JavaCore.create(component.getProject());
		IPath currentDefaultOutput = null;
		try {
			currentDefaultOutput = javaProject.getOutputLocation();
			component.setMetaProperty("java-output-path", currentDefaultOutput.toString() ); //$NON-NLS-1$			
		} catch (JavaModelException e) {
			org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
		}
	}
 
}
