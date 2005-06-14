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

package org.eclipse.jst.j2ee.internal.web.classpath;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.common.jdt.internal.classpath.FlexibleProjectContainer;
import org.eclipse.jst.j2ee.internal.web.operations.WebMessages;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class WebAppContainer 
    
    extends FlexibleProjectContainer
    
{
    private static final IPath[] paths 
        = new IPath[] { new Path( "WEB-INF/lib" ), 
                        new Path( "WEB-INF/classes" ) };
    
    private static final PathType[] types 
        = new PathType[] { PathType.LIB_DIRECTORY, PathType.CLASSES_DIRECTORY };
    
    public static final String CONTAINER_ID 
        = "org.eclipse.jst.j2ee.internal.web.container";
    
    public WebAppContainer( final IPath path,
                            final IJavaProject jproject )
    {
         super( path, jproject, jproject.getProject(), path.segment( 1 ), 
                paths, types );
    }
    
    public String getDescription()
    {
        return resource( "WEB_CONT_DESCRIPTION", this.component );
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
        ( new WebAppContainer( this.path, this.owner ) ).install();
    }
    
    public static IClasspathEntry convert( final String component )
    {
        final IPath path = ( new Path( CONTAINER_ID ) ).append( component );
        return JavaCore.newContainerEntry( path );
    }
    
    public static String convert( final IClasspathEntry cpe )
    {
        return cpe.getPath().segment( 1 );
    }
    
    public static boolean check( final IClasspathEntry cpe )
    {
        if( cpe.getEntryKind() == IClasspathEntry.CPE_CONTAINER )
        {
            final IPath p = cpe.getPath();
            
            if( p.segmentCount() >= 1 && 
                p.segment( 0 ).equals( WebAppContainer.CONTAINER_ID ) )
            {
                return true;
            }
        }
        
        return false;
    }
    
    private static String resource( final String key,
                                    final String arg )
    {
        return WebMessages.getResourceString( key, new String[] { arg } );
    }
    
}
