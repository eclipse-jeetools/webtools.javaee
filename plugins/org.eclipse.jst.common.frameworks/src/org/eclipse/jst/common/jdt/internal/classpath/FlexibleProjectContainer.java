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
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.common.frameworks.CommonFrameworksPlugin;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IFlexibleProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public abstract class FlexibleProjectContainer

    implements IClasspathContainer

{
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
    
    private static IWorkspace workspace;

    static
    {
        // Register the resource listener that will listen for changes to
        // resources relevant to flexible project containers across the
        // workspace and refresh them as necessary.
        
        workspace = ResourcesPlugin.getWorkspace();
        workspace.addResourceChangeListener( new Listener() );
    }

    protected final IPath path;
    protected final IJavaProject owner;
    protected final IProject project;
    protected final String component;
    private final IClasspathEntry[] cpentries;
    private final IPath[] watchlist;
    
    public FlexibleProjectContainer( final IPath path,
                                     final IJavaProject owner,
                                     final IProject project,
                                     final String component,
                                     final IPath[] paths,
                                     final PathType[] types )
    {
        this.path = path;
        this.owner = owner;
        this.project = project;
        this.component = component;
        
        final ArrayList cp = new ArrayList();
        final ArrayList w = new ArrayList();

        if( ! isFlexibleProject( this.project ) )
        {
            // Silently noop if the referenced project is not a flexible
            // project. Should I be doing something else here?
            
            this.cpentries = new IClasspathEntry[ 0 ];
            this.watchlist = new IPath[ 0 ];
            
            return;
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
                    CommonFrameworksPlugin.log( e );
                    continue;
                }

                for( int j = 0; j < contents.length; j++ )
                {
                    final IResource r = contents[ j ].getUnderlyingResource();
                    final IPath p = r.getFullPath();
                    final File f = r.getLocation().toFile();
                    final String fname = f.getName().toLowerCase();
                    
                    if( f.isFile() && fname.endsWith( ".jar" ) )
                    {
                        cp.add( JavaCore.newLibraryEntry( p, null, null ) );
                    }
                }
                
                final IFolder[] folders = vf.getUnderlyingFolders();
                
                for( int j = 0; j < folders.length; j++ )
                {
                    w.add( folders[ j ].getFullPath() );
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
        
        w.add( this.project.getFullPath().append( ".wtpmodules" ) );
            
        this.cpentries = new IClasspathEntry[ cp.size() ];
        cp.toArray( this.cpentries );
        
        this.watchlist = new IPath[ w.size() ];
        w.toArray( this.watchlist );
    }
    
    public int getKind()
    {
        return K_APPLICATION;
    }

    public IPath getPath()
    {
        return this.path;
    }
    
    public IClasspathEntry[] getClasspathEntries()
    {
        return this.cpentries;
    }
    
    public boolean isOutOfDate( final IResourceDelta delta )
    {
        for( int i = 0; i < this.watchlist.length; i++ )
        {
            if( delta.findMember( this.watchlist[ i ] ) != null )
            {
                return true;
            }
        }
        
        return false;
    }
    
    public abstract void refresh();
    
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
            CommonFrameworksPlugin.log( e );
            return false;
        }
    }
    
    private static boolean isJavaProject( final IProject pj )
    {
        try
        {
            return pj.getNature( "org.eclipse.jdt.core.javanature" ) != null;
        }
        catch( CoreException e )
        {
            return false;
        }
    }

    private static boolean isFlexibleProject( final IProject pj )
    {
        try
        {
            final String nature 
                = "org.eclipse.wst.common.modulecore.ModuleCoreNature";
            
            return pj.getNature( nature ) != null;
        }
        catch( CoreException e )
        {
            return false;
        }
    }
    
    private static class Listener
    
        implements IResourceChangeListener
        
    {
        public void resourceChanged( final IResourceChangeEvent event )
        {
            // Locate all of the flexible project containers.
            
            final ArrayList containers = new ArrayList();
            final IProject[] projects = workspace.getRoot().getProjects();
            
            for( int i = 0; i < projects.length; i++ )
            {
                final IProject project = projects[ i ];
                
                try
                {
                    if( isJavaProject( project ) )
                    {
                        final IJavaProject jproj = JavaCore.create( project );
                        final IClasspathEntry[] cpes = jproj.getRawClasspath();
                        
                        for( int j = 0; j < cpes.length; j++ )
                        {
                            final IClasspathEntry cpe = cpes[ j ];
                            
                            if( cpe.getEntryKind() == IClasspathEntry.CPE_CONTAINER )
                            {
                                final IClasspathContainer cont
                                    = JavaCore.getClasspathContainer( cpe.getPath(), jproj );
                                
                                if( cont instanceof FlexibleProjectContainer )
                                {
                                    containers.add( cont );
                                }
                            }
                        }
                    }
                }
                catch( JavaModelException e )
                {
                    CommonFrameworksPlugin.log( e );
                }
            }
            
            // Refresh the containers that are out of date.
            
            final IResourceDelta delta = event.getDelta();
            
            for( int i = 0, n = containers.size(); i < n; i++ )
            {
                final FlexibleProjectContainer c 
                    = (FlexibleProjectContainer) containers.get( i );
                
                if( c.isOutOfDate( delta ) )
                {
                    c.refresh();
                }
            }
        }
    }
    
}
