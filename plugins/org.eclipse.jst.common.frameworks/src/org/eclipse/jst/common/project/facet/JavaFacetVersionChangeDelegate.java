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

package org.eclipse.jst.common.project.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jst.common.project.facet.core.ClasspathHelper;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class JavaFacetVersionChangeDelegate 

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
            monitor.beginTask( "", 1 );
        }
        
        try
        {
            // Find the version that's currently installed.
            
            final IFacetedProject fproj
                = ProjectFacetsManager.create( project );

            final IProjectFacetVersion oldver
                = fproj.getInstalledVersion( fv.getProjectFacet() );
            
            // Reset the classpath. 
            
            ClasspathHelper.removeClasspathEntries( project, oldver );
            
            if( ! ClasspathHelper.addClasspathEntries( project, fv ) )
            {
                // TODO: Support the no runtime case.
                // ClasspathHelper.addClasspathEntries( project, fv, <something> );
            }
            
            // Reset the compiler level.
            
            JavaFacetUtils.setCompilerLevel( project, fv );
            
            // Schedule a clean build of the project.
            
            final String msg 
                = NLS.bind( Resources.buildingMsg, project.getName() );
            
            final Job buildJob = new Job( msg ) 
            {
                public IStatus run( final IProgressMonitor monitor) 
                {
                    try
                    {
                        project.build( IncrementalProjectBuilder.FULL_BUILD,
                                       monitor );
                    }
                    catch( CoreException e )
                    {
                        return e.getStatus();
                    }
                    
                    return Status.OK_STATUS;
                }
            };
             
            buildJob.setRule( ResourcesPlugin.getWorkspace().getRoot() );
            buildJob.schedule();
            
            if( monitor != null )
            {
                monitor.worked( 1 );
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

    private static final class Resources
    
        extends NLS
        
    {
        public static String buildingMsg;
        
        static
        {
            initializeMessages( JavaFacetVersionChangeDelegate.class.getName(), 
                                Resources.class );
        }
    }

}
