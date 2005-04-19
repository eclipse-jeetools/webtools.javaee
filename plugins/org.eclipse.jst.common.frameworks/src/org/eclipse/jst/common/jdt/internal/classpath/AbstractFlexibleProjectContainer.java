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

package org.eclipse.jst.common.jdt.internal.classpath;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IFlexibleProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public abstract class AbstractFlexibleProjectContainer

    implements IClasspathContainer

{
    protected final IPath path;
    protected final IProject project;
    protected final String component;

    public AbstractFlexibleProjectContainer( final IPath path,
                                             final IProject project )
    {
        this.path = path;
        this.project = project;
        this.component = path.segment( 1 );
    }
    
    public int getKind()
    {
        return K_APPLICATION;
    }

    public IPath getPath()
    {
        return this.path;
    }
    
    protected static final class PathType
    {
        private final String type;
        
        private PathType( final String type )
        {
            this.type = type;
        }
        
        public static final PathType 
            LIB_DIRECTORY = new PathType( "lib" ),
            CLASSES_DIRECTORY = new PathType( "classes" );
    }

    protected IClasspathEntry[] getClasspathEntries( final String component,
                                                     final IPath[] paths,
                                                     final PathType[] types )
    {
        final ArrayList cp = new ArrayList();
        
        if( ! isFlexibleProject( this.project ) )
        {
            final IClasspathEntry[] array = new IClasspathEntry[ cp.size() ];
            return (IClasspathEntry[]) cp.toArray( array );
        }
        
        final IFlexibleProject fp 
            = ComponentCore.createFlexibleProject( this.project );
        
        final IVirtualComponent vc = fp.getComponent( component );
        
        for( int i = 0; i < paths.length; i++ )
        {
            final IVirtualFolder vf = vc.getFolder( paths[ i ] );
            
            if( types[ i ] == PathType.LIB_DIRECTORY )
            {
                final IVirtualResource[] contents;
                
                try
                {
                    contents = vf.members();
                }
                catch( CoreException e )
                {
                    // TODO: Should this be reported somehow?
                    continue;
                }

                for( int j = 0; j < contents.length; j++ )
                {
					// No need to fetch the IResource here. Could use getWorkspaceRelativePath();
                    final IResource r = contents[ j ].getUnderlyingResource();
                    final IPath p = r.getFullPath();
                    final File f = r.getLocation().toFile();
                    final String fname = f.getName().toLowerCase();
                    
                    if( f.isFile() && fname.endsWith( ".jar" ) )
                    {
                        cp.add( JavaCore.newLibraryEntry( p, null, null ) );
                    }
                }
            }
            else
            {
                final IFolder[] uf = vf.getUnderlyingFolders();
                
                for( int j = 0; j < uf.length; j++ )
                {
                    final IPath p = uf[ j ].getFullPath();
                    
                    if( ! isSourceDirectory( p ) )
                    {
                        cp.add( JavaCore.newLibraryEntry( p, null, null ) );
                    }
                }
            }
        }
            
        final IClasspathEntry[] array = new IClasspathEntry[ cp.size() ];
        return (IClasspathEntry[]) cp.toArray( array );
    }
    
    private boolean isFlexibleProject( final IProject project )
    {
        try
        {
            final String nature 
                = "org.eclipse.wst.common.modulecore.ModuleCoreNature";
            
            return project.getNature( nature ) != null;
        }
        catch( CoreException e )
        {
            return false;
        }
    }
    
    private boolean isSourceDirectory( final IPath path )
    {
        try
        {
            final IJavaProject jproject = JavaCore.create( this.project );
            final IClasspathEntry[] cp = jproject.getRawClasspath();
            
            for( int i = 0; i < cp.length; i++ )
            {
                final IClasspathEntry cpe = cp[ i ];
                
                if( cpe.getEntryKind() == IClasspathEntry.CPE_SOURCE &&
                    cpe.getPath().equals( path ) )
                {
                    return true;
                }
            }
            
            return false;
        }
        catch( JavaModelException e )
        {
            // TODO: Handle this better.
            return false;
        }
    }

}
