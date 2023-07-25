/******************************************************************************
 * Copyright (c) 2005-2019 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Konstantin Komissarchik
 ******************************************************************************/

package org.eclipse.jst.j2ee.ui.project.facet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;
import org.eclipse.wst.common.project.facet.core.runtime.RuntimeManager;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class RuntimeMismatchMarkerResolutions

    implements IMarkerResolutionGenerator
    
{
    private static final String ATTR_RUNTIME1 = "runtime1"; //$NON-NLS-1$
    private static final String ATTR_RUNTIME2 = "runtime2"; //$NON-NLS-1$
    private static final String ATTR_MODULE_PROJECT = "moduleProject"; //$NON-NLS-1$
    
    @Override
	public IMarkerResolution[] getResolutions( final IMarker marker )
    {
        final List resolutions = new ArrayList( 2 );
        
        try
        {
            final IProject earProject = marker.getResource().getProject();
            final IFacetedProject earFacetedProject = ProjectFacetsManager.create( earProject );
            final IProject modProject = getModuleProject( marker );
            final IFacetedProject modFacetedProject = ProjectFacetsManager.create( modProject );
            
            for( Iterator itr = getRuntimes( marker ).iterator(); itr.hasNext(); )
            {
                final IRuntime runtime = (IRuntime) itr.next();
                
                if( earFacetedProject.isTargetable( runtime ) && 
                    modFacetedProject.isTargetable( runtime ) )
                {
                    final Resolution resolution
                        = new Resolution( earFacetedProject, modFacetedProject, runtime );
                    
                    resolutions.add( resolution );
                }
            }
        }
        catch( CoreException e )
        {
            J2EEUIPlugin.logError( -1, null, e );
        }
        
        IMarkerResolution[] array = new IMarkerResolution[ resolutions.size() ];
        resolutions.toArray( array );
        
        return array;
    }
    
    private static IProject getModuleProject( final IMarker marker )
    {
        final IWorkspace ws = ResourcesPlugin.getWorkspace();
        final String moduleProjectName = marker.getAttribute( ATTR_MODULE_PROJECT, null );
        return ws.getRoot().getProject( moduleProjectName );
    }
    
    private static List getRuntimes( final IMarker marker )
    {
        final List runtimes = new ArrayList();
        
        IRuntime r = getRuntimeByName( marker.getAttribute( ATTR_RUNTIME1, null ) );
        if( r != null ) runtimes.add( r );
        
        r = getRuntimeByName( marker.getAttribute( ATTR_RUNTIME2, null ) );
        if( r != null ) runtimes.add( r );
        
        return runtimes;
    }
    
    private static IRuntime getRuntimeByName( final String name )
    {
        if( RuntimeManager.isRuntimeDefined( name ) )
        {
            return RuntimeManager.getRuntime( name );
        }
        return null;
    }
    
    private static final class Resolution
    
        implements IMarkerResolution
        
    {
        private final IFacetedProject earProject;
        private final IFacetedProject moduleProject;
        private final IRuntime runtime;
        
        public Resolution( final IFacetedProject earProject,
                           final IFacetedProject moduleProject,
                           final IRuntime runtime )
        {
            this.earProject = earProject;
            this.moduleProject = moduleProject;
            this.runtime = runtime;
        }
        
        @Override
		public String getLabel()
        {
            return NLS.bind( Resources.useSameRuntime, this.runtime.getLocalizedName() );
        }

        @Override
		public void run( final IMarker marker )
        {
            try
            {
                setRuntime( this.earProject, this.runtime );
                setRuntime( this.moduleProject, this.runtime );
            }
            catch( CoreException e )
            {
                ErrorDialog.openError( null, Resources.errorDialogTitle,
                                       Resources.errorDialogMessage, e.getStatus() );
            }
        }
        
        private void setRuntime( final IFacetedProject fproj,
                                 final IRuntime runtime )
        
            throws CoreException
            
        {
            final IRuntime currentPrimaryRuntime = fproj.getPrimaryRuntime();
            
            if( currentPrimaryRuntime != null && 
                ! currentPrimaryRuntime.getName().equals( runtime.getName() ) )
            {
                if( ! fproj.isTargeted( runtime ) )
                {
                    fproj.addTargetedRuntime( runtime, null );
                }
                
                fproj.setPrimaryRuntime( runtime, null );
            }
        }
    }
    
    private static final class Resources
    
        extends NLS
        
    {
        public static String useSameRuntime;
        public static String errorDialogTitle;
        public static String errorDialogMessage;
        
        static
        {
            initializeMessages( RuntimeMismatchMarkerResolutions.class.getName(), 
                                Resources.class );
        }
    }
    
}
