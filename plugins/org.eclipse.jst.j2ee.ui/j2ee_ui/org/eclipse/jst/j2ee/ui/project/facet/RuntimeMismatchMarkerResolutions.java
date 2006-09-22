/******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.j2ee.ui.project.facet;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.ErrorDialog;
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
    public IMarkerResolution[] getResolutions( final IMarker marker )
    {
        return new IMarkerResolution[] 
        { 
            new Resolution( marker, marker.getAttribute( "runtime1", null ) ), 
            new Resolution( marker, marker.getAttribute( "runtime2", null ) ) 
        };
    }
    
    private static final class Resolution
    
        implements IMarkerResolution
        
    {
        private final IMarker marker;
        private final String runtimeName;
        
        public Resolution( final IMarker marker,
                           final String runtimeName )
        {
            this.marker = marker;
            this.runtimeName = runtimeName;
        }
        
        public String getLabel()
        {
            return NLS.bind( Resources.useSameRuntime, this.runtimeName );
        }

        public void run( final IMarker marker )
        {
            final IRuntime runtime 
                = RuntimeManager.getRuntime( this.runtimeName );
            
            try
            {
                setRuntime( this.marker.getResource().getProject(), runtime );
                
                final String pjname 
                    = this.marker.getAttribute( "moduleProject", null );
                
                final IWorkspace ws = ResourcesPlugin.getWorkspace();
                final IProject pj = ws.getRoot().getProject( pjname );
                
                setRuntime( pj, runtime );
            }
            catch( CoreException e )
            {
                ErrorDialog.openError( null, Resources.errorDialogTitle,
                                       Resources.errorDialogMessage,
                                       e.getStatus() );
            }
        }
        
        private void setRuntime( final IProject proj,
                                 final IRuntime runtime )
        
            throws CoreException
            
        {
            final IFacetedProject fproj = ProjectFacetsManager.create( proj );
            
            if( ! fproj.getRuntime().equals( runtime ) )
            {
                fproj.setRuntime( runtime, null );
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
