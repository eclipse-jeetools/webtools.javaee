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
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

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
		// Attempt to change the runtime for the specified module                
		J2EEFacetRuntimeChangedDelegate.updateProjectRuntime( earProject, moduleProject, submon( monitor, 1 ));
	}

    public static void updateModuleProjectRuntime( final IProject earProject,
                                                   final Set moduleProjects,
                                                   final IProgressMonitor monitor )
    
        throws CoreException
        
    {
		// Attempt to change the runtime of the referenced module projects.
		J2EEFacetRuntimeChangedDelegate.updateProjectRuntime( earProject, moduleProjects, monitor);
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
				// Cascade this runtime change to projects referenced by this project

                // Compile the list of projects referenced by this ear project.
                final Set<IProject> moduleProjects = new HashSet<IProject>();
                
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

        		// Attempt to change the runtime of the referenced module projects.
				J2EEFacetRuntimeChangedDelegate.updateProjectRuntime( project, moduleProjects,
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
}
