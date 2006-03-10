/******************************************************************************
 * Copyright (c) 2005-2006 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.j2ee.internal.web.classpath;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.common.jdt.internal.classpath.FlexibleProjectContainer;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.osgi.util.NLS;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class WebAppLibrariesContainer 
    
    extends FlexibleProjectContainer
    
{
    private static final IPath[] paths 
        = new IPath[] { new Path( "WEB-INF/lib" ), 
                        new Path( "WEB-INF/classes" ) };
    
    private static final PathType[] types 
        = new PathType[] { PathType.LIB_DIRECTORY, PathType.CLASSES_DIRECTORY };
    
    public static final String CONTAINER_ID 
        = "org.eclipse.jst.j2ee.internal.web.container";
    
    public WebAppLibrariesContainer( final IPath path,
                                     final IJavaProject jproject )
    {
         super( path, jproject, getProject( path, jproject), paths, types );
    }
    
    public String getDescription()
    {
        if( this.owner.getProject() != this.project )
        {
            return NLS.bind( Resources.labelWithProject, this.project.getName() );
        }
        else
        {
            return Resources.label;
        }
    }
    
    public void install()
    {
        final IJavaProject[] projects = new IJavaProject[] { this.owner };
        final IClasspathContainer[] conts = new IClasspathContainer[] { this };

        try
        {
            JavaCore.setClasspathContainer( path, projects, conts, null );
        }
        catch( JavaModelException e )
        {
            WebPlugin.log( e );
        }
    }
    
    public void refresh()
    {
        ( new WebAppLibrariesContainer( this.path, this.owner ) ).install();
    }
    
    private static final IProject getProject( final IPath path,
                                              final IJavaProject jproject )
    {
        if( path.segmentCount() == 1 )
        {
            return jproject.getProject();
        }
        else
        {
            final String name = path.segment( 1 );
            return ResourcesPlugin.getWorkspace().getRoot().getProject( name );
        }
    }
    
    private static final class Resources
    
        extends NLS
        
    {
        public static String label;
        public static String labelWithProject;
        
        static
        {
            initializeMessages( WebAppLibrariesContainer.class.getName(), 
                                Resources.class );
        }
    }
    
}
