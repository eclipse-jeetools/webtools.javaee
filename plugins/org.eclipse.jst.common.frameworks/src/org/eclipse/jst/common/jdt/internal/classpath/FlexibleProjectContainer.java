/******************************************************************************
 * Copyright (c) 2005 - 2006 BEA Systems, Inc.
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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IAccessRule;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.common.frameworks.CommonFrameworksPlugin;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public abstract class FlexibleProjectContainer

    implements IClasspathContainer

{
    protected static final class PathType
    {
        public static final PathType 
            LIB_DIRECTORY = new PathType(),
            CLASSES_DIRECTORY = new PathType();
    }
    
    private static ClasspathDecorationsManager decorations; 

    static
    {
        // Register the resource listener that will listen for changes to
        // resources relevant to flexible project containers across the
        // workspace and refresh them as necessary.
        
        final IWorkspace ws = ResourcesPlugin.getWorkspace();
        
        ws.addResourceChangeListener( new Listener(), 
                                      IResourceChangeEvent.POST_CHANGE );
        
        // Read the decorations from the workspace metadata.
        
        final String plugin = CommonFrameworksPlugin.PLUGIN_ID;
        decorations = new ClasspathDecorationsManager( plugin );
    }

    protected final IPath path;
    protected final IJavaProject owner;
    protected final IProject project;
    private final IPath[] paths;
    private final PathType[] pathTypes;
    private final List entries;
    private final IClasspathEntry[] cpentries;
    
    public FlexibleProjectContainer( final IPath path,
                                     final IJavaProject owner,
                                     final IProject project,
                                     final IPath[] paths,
                                     final PathType[] types )
    {
        this.path = path;
        this.owner = owner;
        this.project = project;
        this.paths = paths;
        this.pathTypes = types;
        
        if( ! isFlexibleProject( this.project ) )
        {
            // Silently noop if the referenced project is not a flexible
            // project. Should I be doing something else here?
            
            this.entries = Collections.EMPTY_LIST;
            this.cpentries = new IClasspathEntry[ 0 ];
            
            return;
        }
        
        this.entries = computeClasspathEntries();
        this.cpentries = new IClasspathEntry[ this.entries.size() ];
        
        for( int i = 0, n = this.entries.size(); i < n; i++ )
        {
        	IPath entryPath = (IPath) this.entries.get( i );
        	if(ResourcesPlugin.getWorkspace().getRoot().findMember(entryPath).getType() == IResource.PROJECT)
        		this.cpentries[ i ] = JavaCore.newProjectEntry(entryPath, false);
        	else
        		this.cpentries[ i ] = newLibraryEntry( entryPath );
        }
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
        if( delta == null )
        {
            return false;
        }
        
        final List currentEntries = computeClasspathEntries();
        return ! this.entries.equals( currentEntries );
    }
    
    public abstract void refresh();
    
    static ClasspathDecorationsManager getDecorationsManager()
    {
        return decorations;
    }
    
    private List computeClasspathEntries()
    {
        final List entries = new ArrayList();
        
        final IVirtualComponent vc 
            = ComponentCore.createComponent( this.project );
        
        
        IVirtualReference[] refs = vc.getReferences();
		IVirtualComponent comp = null;
		Set jarsHandled = new HashSet();
		String jarName = null;
		for (int i = 0; i < refs.length; i++) {
			comp = refs[i].getReferencedComponent();
			if (!refs[i].getRuntimePath().equals(paths[0].makeAbsolute()))
				continue;
			jarName = refs[i].getArchiveName();
			if(null != jarName){
				jarsHandled.add(jarName);
			}
			if (comp.isBinary()) {
				VirtualArchiveComponent archiveComp = (VirtualArchiveComponent) comp;
				java.io.File diskFile = archiveComp.getUnderlyingDiskFile();
				if (diskFile.exists()) {
					entries.add(new Path(diskFile.getAbsolutePath()));
				} else {
					IFile iFile = archiveComp.getUnderlyingWorkbenchFile();
					if (!entries.contains(iFile.getFullPath()))
						entries.add(iFile.getFullPath());
				}
			} else {
				IProject project = comp.getProject();
				entries.add(project.getFullPath());	
			}
		}
        
        for( int i = 0; i < this.paths.length; i++ )
        {
            final IVirtualFolder rootFolder = vc.getRootFolder();
            final IVirtualFolder vf = rootFolder.getFolder( paths[ i ] );
            
            if( this.pathTypes[ i ] == PathType.LIB_DIRECTORY )
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
                    
                    if(!jarsHandled.contains(p.lastSegment()) &&  isJarFile( r.getLocation().toFile() ) )
                    {
                    	jarsHandled.add(p.lastSegment());
                        entries.add( p );
                    }
                }
            }
            else
            {
                final IContainer[] uf = vf.getUnderlyingFolders();
                
                for( int j = 0; j < uf.length; j++ )
                {
                    final IPath p = uf[ j ].getFullPath();
                    
                    if(!jarsHandled.contains(p.lastSegment()) && ! isSourceDirectory( p ) )
                    {
                    	jarsHandled.add(p.lastSegment());
                        entries.add( p );
                    }
                }
            }
        }
        

        return entries;
    }
    
    private IClasspathEntry newLibraryEntry( final IPath p )
    {
        IPath srcpath = null;
        IPath srcrootpath = null;
        IClasspathAttribute[] attrs = {};
        IAccessRule[] access = {};
        
        final ClasspathDecorations dec 
            = decorations.getDecorations( getPath().toString(), p.toString() );
        
        if( dec != null )
        {
            srcpath = dec.getSourceAttachmentPath();
            srcrootpath = dec.getSourceAttachmentRootPath();
            attrs = dec.getExtraAttributes();
        }
        
        return JavaCore.newLibraryEntry( p, srcpath, srcrootpath, access, attrs,
                                         false );
        
    }
    
    private boolean isSourceDirectory( final IPath aPath )
    {
        if( isJavaProject( this.project ) )
        {
            try
            {
                final IJavaProject jproject = JavaCore.create( this.project );
	            final IClasspathEntry[] cp = jproject.getRawClasspath();
	            
	            for( int i = 0; i < cp.length; i++ )
	            {
	                final IClasspathEntry cpe = cp[ i ];
	                
	                if( cpe.getEntryKind() == IClasspathEntry.CPE_SOURCE &&
	                    cpe.getPath().equals( aPath ) )
	                {
	                    return true;
	                }
	            }
            }
            catch( JavaModelException e )
            {
                CommonFrameworksPlugin.log( e );
            }
        }
    
        return false;    
    }
    
    private static boolean isJavaProject( final IProject pj )
    {
        try
        {
            return pj.getNature( JavaCore.NATURE_ID ) != null;
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
            return pj.getNature( IModuleConstants.MODULE_NATURE_ID ) != null;
        }
        catch( CoreException e )
        {
            return false;
        }
    }
    
    private static boolean isJarFile( final File f )
    {
        if( f.isFile() )
        {
            final String fname = f.getName();
            
            if( fname.endsWith( ".jar" ) || fname.endsWith( ".zip" ) )
            {
                return true;
            }
        }
        
        return false;
    }
    
    private static class Listener
    
        implements IResourceChangeListener
        
    {
        public void resourceChanged( final IResourceChangeEvent event )
        {
            // Locate all of the flexible project containers.
            
            final ArrayList containers = new ArrayList();
            
            final IProject[] projects 
                = ResourcesPlugin.getWorkspace().getRoot().getProjects();
            
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
