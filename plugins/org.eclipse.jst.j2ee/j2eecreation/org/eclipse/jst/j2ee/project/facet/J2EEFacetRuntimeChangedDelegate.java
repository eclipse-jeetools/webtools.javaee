/******************************************************************************
 * Copyright (c) 2005, 2011 BEA Systems, Inc. and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.j2ee.project.facet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.common.project.facet.core.ClasspathHelper;
import org.eclipse.jst.common.project.facet.core.IClasspathProvider;
import org.eclipse.jst.common.project.facet.core.internal.FacetCorePlugin;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.IRuntimeChangedEvent;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;
import org.osgi.service.prefs.BackingStoreException;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class J2EEFacetRuntimeChangedDelegate 

    implements IDelegate
    
{
	public static final String LEGACY_METADATA_FILE_NAME 
    = ".settings/org.eclipse.jst.common.project.facet.core.prefs"; //$NON-NLS-1$
	private static final String OWNER_PROJECT_FACETS_ATTR = "owner.project.facets"; //$NON-NLS-1$
    @Override
	public void execute( final IProject project, 
                         final IProjectFacetVersion fv,
                         final Object cfg,
                         final IProgressMonitor monitor )
    
        throws CoreException
        
    {
        if( monitor != null )
        {
            monitor.beginTask( "", 11 ); //$NON-NLS-1$
        }
        
        try
        {
        	IRuntimeChangedEvent event = (IRuntimeChangedEvent)cfg;
        	// Using our remove Utility to first check for missing preferences
            removeClasspathEntries( project, fv,  event.getOldRuntime());
            
            if( ! ClasspathHelper.addClasspathEntries( project, fv ) )
            {
                // TODO: Support the no runtime case.
                // ClasspathHelper.addClasspathEntries( project, fv, <something> );
            }
            
            if( monitor != null )
            {
                monitor.worked( 1 );
            }
            
            // Cascade this runtime change to projects referenced by this project
            
            // Compile the list of projects referenced by this project.
            final Set<IProject> childProjects = new HashSet<IProject>();
            
            final IVirtualComponent projectVC = ComponentCore.createComponent( project );                       
            
            Map<String, Object> options = new HashMap<String, Object>();
            options.put(IVirtualComponent.REQUESTED_REFERENCE_TYPE, IVirtualComponent.HARD_REFERENCES);
            final IVirtualReference[] vrefs = projectVC.getReferences(options);
                       	            
            for( int i = 0; i < vrefs.length; i++ ) {
                final IVirtualReference vref = vrefs[ i ];
                final IVirtualComponent vc = vref.getReferencedComponent();
                childProjects.add( vc.getProject() );
            }
            
            if( monitor != null ) {
                monitor.worked( 1 );
            }
            
            // Attempt to change the runtime for each of the referenced projects.                
            updateProjectRuntime( project, childProjects, submon( monitor, 9 ) );
        }
        finally
        {
            if( monitor != null )
            {
                monitor.done();
            }
        }
    }
    private void removeClasspathEntries(IProject project, IProjectFacetVersion fv, IRuntime oldRuntime) throws CoreException {
		IJavaProject jproj = JavaCore.create(project);
		List cp = getClasspath(jproj);
		boolean hasPrefs = hasClasspathPreferencesNode(project);

		// In the case where no prefs exists... make sure the entries of the
		// oldRuntime are removed before continuing
		if (!hasPrefs) {
			removeOnlyCPEntries(project, fv, jproj, cp, oldRuntime);
		} else
			ClasspathHelper.removeClasspathEntries(project, fv);
	}
    private boolean hasClasspathPreferencesNode(IProject project) {
    	
    	IProject pj = project.getProject();
        IFile legacyMetadataFile = pj.getFile( LEGACY_METADATA_FILE_NAME );
        // First check if file exists
        if( legacyMetadataFile.exists() ) {
        	ProjectScope scope = new ProjectScope(project);
        	IEclipsePreferences pluginRoot = scope.getNode(FacetCorePlugin.PLUGIN_ID);
        	try {
        		return pluginRoot.nodeExists("classpath.helper"); //$NON-NLS-1$
        	} catch (BackingStoreException e) {
        		org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
        	}
        }
        //Now check classpath entries for new attributes
        IJavaProject jproj = JavaCore.create( project );
        return hasClasspathPreferencesNode(jproj);
		
	}
	private boolean hasClasspathPreferencesNode(IJavaProject jproj) {
		
		boolean attributeFound = false;
		IClasspathEntry[] entries = null;
		try {
			entries = jproj.getRawClasspath();
		} catch (JavaModelException e) {
			org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
		}
		int i = 0;
		if (entries != null) {
			while (!attributeFound && i < entries.length) {
				IClasspathEntry cpe = entries[i];
				attributeFound = hasOwnerAttribute( cpe );
				i++;
			} 
		}
		return attributeFound;
		
	}
	private boolean hasOwnerAttribute(IClasspathEntry cpe) {
		 if( cpe != null )
	        {
	            for( IClasspathAttribute attr : cpe.getExtraAttributes() )
	            {
	                if( attr.getName().equals( OWNER_PROJECT_FACETS_ATTR ) )
	                	return true;
	            }
	        }
		 return false;
	}
	private void removeOnlyCPEntries(final IProject project, final IProjectFacetVersion fv, final IJavaProject jproj, final List cp,
			IRuntime oldRuntime) throws CoreException {
		IFacetedProject fproj = ProjectFacetsManager.create(project);
		IRuntime runtime = (oldRuntime != null) ? oldRuntime : fproj.getPrimaryRuntime();
	
		if (runtime != null) {
			IClasspathProvider cpprov = runtime.getAdapter(IClasspathProvider.class);
			List cpentries = cpprov.getClasspathEntries(fv);
			boolean realCPChanged = false;
			if (cpentries != null) {
			for (Iterator itr = cpentries.iterator(); itr.hasNext();) {
				IClasspathEntry cpentry = (IClasspathEntry) itr.next();
				IPath path = cpentry.getPath();
				boolean contains = cp.contains(cpentry);
	
				if (contains) {
					for (Iterator itr2 = cp.iterator(); itr2.hasNext();) {
						final IClasspathEntry realEntry = (IClasspathEntry) itr2.next();
	
						if (realEntry.getPath().equals(path)) {
							itr2.remove();
							realCPChanged = true;
							break;
						}
					}
				}
			}
			}
			if (realCPChanged) {
				IClasspathEntry[] newcp = (IClasspathEntry[]) cp.toArray(new IClasspathEntry[cp.size()]);
				jproj.setRawClasspath(newcp, null);
			}
		}
	}
	private List getClasspath(final IJavaProject jproj)
	
	throws CoreException
	
	{
		ArrayList list = new ArrayList();
		IClasspathEntry[] cp = jproj.getRawClasspath();
	
		for (int i = 0; i < cp.length; i++) {
			list.add(cp[i]);
		}
	
		return list;
	}
	
    private static IProgressMonitor submon( final IProgressMonitor parent, final int ticks ) {
        return ( parent == null ? null : new SubProgressMonitor( parent, ticks ) );
    }
    
    public static void updateProjectRuntime( final IProject project,
            final IProject childProject,
            final IProgressMonitor monitor ) throws CoreException {
    	if( monitor != null ) {
    		monitor.beginTask( "", 1 ); //$NON-NLS-1$
    	}

    	try
    	{
    		final IFacetedProject facetedProject = ProjectFacetsManager.create( project );

    		final IRuntime projectRuntime = facetedProject.getRuntime();

    		final IFacetedProject childFacetedProject = ProjectFacetsManager.create( childProject );

    		if( childFacetedProject != null && 
    				! equals(projectRuntime, childFacetedProject.getRuntime()))
    		{
    			boolean supports = true;

    			if( projectRuntime != null )
    			{
    				for( Iterator itr = childFacetedProject.getProjectFacets().iterator(); itr.hasNext(); ) {
    					final IProjectFacetVersion fver = (IProjectFacetVersion) itr.next();

    					if(!projectRuntime.supports(fver)) {
    						supports = false;
    						break;
    					}
    				}
    			}

    			if( supports ) {
    				childFacetedProject.setRuntime(projectRuntime, submon( monitor, 1 ) );
    			}
    		}
    	}
    	finally {
    		if( monitor != null ) {
    			monitor.done();
    		}
    	}
    }

    public static void updateProjectRuntime( final IProject project,
    		final Set<IProject> childProjects,
    		final IProgressMonitor monitor ) throws CoreException {        
    	if( monitor != null ) {
    		monitor.beginTask( "", childProjects.size() ); //$NON-NLS-1$
    	}

    	try {
    		for(final IProject childProject : childProjects) {
    			updateProjectRuntime( project, childProject,
    					submon( monitor, 1 ) );
    		}
    	}
    	finally {
    		if( monitor != null ) {
    			monitor.done();
    		}
    	}
    }
    
    private static boolean equals( final Object obj1, final Object obj2 ) {
        if( obj1 == obj2 ) {
            return true;
        } else if( obj1 == null || obj2 == null ) {
            return false;
        } else {
            return obj1.equals( obj2 );
        }
    }
}
