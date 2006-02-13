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

package org.eclipse.jst.j2ee.project.facet;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectValidator;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class EarFacetValidator

    implements IFacetedProjectValidator
    
{
    private static final String MARKER_ID 
        = J2EEPlugin.PLUGIN_ID + ".differentRuntimesDetected";
    
    public void validate( final IFacetedProject fproj ) 
    
        throws CoreException
        
    {
        final IVirtualComponent vc 
            = ComponentCore.createComponent( fproj.getProject() );
        
        if( vc == null )
        {
            return;
        }
    
        if( fproj.hasProjectFacet( EARFacetUtils.EAR_FACET ) )
        {
            fproj.getProject().deleteMarkers( MARKER_ID, false, 
                                              IResource.DEPTH_ZERO );
            
            final IVirtualReference[] refs = vc.getReferences();
            
            for( int i = 0; i < refs.length; i++ )
            {
                final IVirtualReference ref = refs[ i ];
                final IVirtualComponent refvc = ref.getReferencedComponent();
                
                if( refvc != null )
                {
                    final IProject refpj = refvc.getProject();
                    validate( fproj, ProjectFacetsManager.create( refpj ) );
                }
            }
        }
        else
        {
            final IVirtualComponent[] referencing 
                = vc.getReferencingComponents();
            
            for( int i = 0; i < referencing.length; i++ )
            {
                final IVirtualComponent refvc = referencing[ i ];
                
                final IFacetedProject reffpj
                    = ProjectFacetsManager.create( refvc.getProject() );
                
                if( reffpj.hasProjectFacet( EARFacetUtils.EAR_FACET ) )
                {
                    validate( reffpj );
                }
            }
        }
    }
    
    private void validate( final IFacetedProject ear,
                           final IFacetedProject module )
    
        throws CoreException
        
    {
        final IRuntime earRuntime = ear.getRuntime();
        final IRuntime moduleRuntime = module.getRuntime();
        
        if( earRuntime == null && moduleRuntime != null )
        {
            // Should this also be a warning?
        }
        else if( earRuntime != null && moduleRuntime == null )
        {
            // Should this also be a warning?
        }
        else if( earRuntime != null && moduleRuntime != null &&
                 ! earRuntime.equals( moduleRuntime ) )
        {
            final String msg
                = NLS.bind( Resources.targettingDifferentRuntimes,
                            module.getProject().getName() );
            
            final IMarker m = ear.createWarningMarker( MARKER_ID, msg );
            
            m.setAttribute( "moduleProject", module.getProject().getName() );
            m.setAttribute( "runtime1", ear.getRuntime().getName() );
            m.setAttribute( "runtime2", module.getRuntime().getName() );
        }
    }
    
    private static final class Resources
    
        extends NLS
        
    {
        public static String targettingDifferentRuntimes;
        
        static
        {
            initializeMessages( EarFacetValidator.class.getName(), 
                                Resources.class );
        }
    }

}
