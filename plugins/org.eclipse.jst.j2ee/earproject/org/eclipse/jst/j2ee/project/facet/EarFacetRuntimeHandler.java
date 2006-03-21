/******************************************************************************
 * Copyright (c) 2006 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.j2ee.project.facet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

/** 
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class EarFacetRuntimeHandler 
{
    /**
     * Private constructor. This class is not meant to be instantiated.
     */
    
    private EarFacetRuntimeHandler() {}
    
    public static void updateModuleProjectRuntime( final IProject earProject,
                                                   final IProject moduleProject,
                                                   final IProgressMonitor monitor )
    
        throws CoreException
        
    {
        if( monitor != null )
        {
            monitor.beginTask( "", 1 );
        }
        
        try
        {
            final IFacetedProject earFacetedProject
                = ProjectFacetsManager.create( earProject );
        
            final IRuntime earRuntime = earFacetedProject.getRuntime();
    
            final IFacetedProject moduleFacetedProject 
                = ProjectFacetsManager.create( moduleProject );
            
            if( moduleFacetedProject != null && 
                ! equals( earRuntime, moduleFacetedProject.getRuntime() ) )
            {
                boolean supports = true;
                
                if( earRuntime != null )
                {
                    for( Iterator itr = moduleFacetedProject.getProjectFacets().iterator(); 
                         itr.hasNext(); )
                    {
                        final IProjectFacetVersion fver 
                            = (IProjectFacetVersion) itr.next();
                        
                        if( ! earRuntime.supports( fver ) )
                        {
                            supports = false;
                            break;
                        }
                    }
                }
                
                if( supports )
                {
                    moduleFacetedProject.setRuntime( earRuntime, submon( monitor, 1 ) );
                }
            }
        }
        finally
        {
            if( monitor != null )
            {
                monitor.done();
            }
        }
    }

    public static void updateModuleProjectRuntime( final IProject earProject,
                                                   final Set moduleProjects,
                                                   final IProgressMonitor monitor )
    
        throws CoreException
        
    {
        if( monitor != null )
        {
            monitor.beginTask( "", moduleProjects.size() );
        }
        
        try
        {
            for( Iterator itr = moduleProjects.iterator(); itr.hasNext(); )
            {
                final IProject moduleProject = (IProject) itr.next();
                
                updateModuleProjectRuntime( earProject, moduleProject, 
                                            submon( monitor, 1 ) );
            }
        }
        finally
        {
            if( monitor != null )
            {
                monitor.done();
            }
        }
    }
    
    public static final class RuntimeChangedDelegate
    
        implements IDelegate
        
    {
        public void execute( final IProject project, 
                             final IProjectFacetVersion fv, 
                             final Object cfg, 
                             final IProgressMonitor monitor ) 
        
            throws CoreException 
            
        {
            if( monitor != null ) 
            {
                monitor.beginTask( "", 10 ); //$NON-NLS-1$
            }
    
            try 
            {
                // Compile the list of projects referenced by this ear project.
                
                final Set moduleProjects = new HashSet();
                
                final IVirtualComponent earvc 
                    = ComponentCore.createComponent( project );
                
                final IVirtualReference[] vrefs = earvc.getReferences();
                
                for( int i = 0; i < vrefs.length; i++ )
                {
                    final IVirtualReference vref = vrefs[ i ];
                    final IVirtualComponent vc = vref.getReferencedComponent();
                    
                    moduleProjects.add( vc.getProject() );
                }
                
                if( monitor != null )
                {
                    monitor.worked( 1 );
                }
                
                // Attempt to change the runtime for each of the referenced projects.
                
                updateModuleProjectRuntime( project, moduleProjects, 
                                            submon( monitor, 9 ) );
            }
            finally 
            {
                if( monitor != null ) 
                {
                    monitor.done();
                }
            }
        }
    }

    private static IProgressMonitor submon( final IProgressMonitor parent,
                                            final int ticks )
    {
        return ( parent == null ? null : new SubProgressMonitor( parent, ticks ) );
    }
    
    private static boolean equals( final Object obj1,
                                   final Object obj2 )
    {
        if( obj1 == obj2 )
        {
            return true;
        }
        else if( obj1 == null || obj2 == null )
        {
            return false;
        }
        else
        {
            return obj1.equals( obj2 );
        }
    }

    
    
}
