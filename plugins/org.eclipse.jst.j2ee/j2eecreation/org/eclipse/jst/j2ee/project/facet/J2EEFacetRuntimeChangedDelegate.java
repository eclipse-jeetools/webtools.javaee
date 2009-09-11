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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.common.project.facet.core.ClasspathHelper;
import org.eclipse.jst.common.project.facet.core.IClasspathProvider;
import org.eclipse.jst.common.project.facet.core.internal.FacetCorePlugin;
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
		ProjectScope scope = new ProjectScope(project);
		IEclipsePreferences pluginRoot = scope.getNode(FacetCorePlugin.PLUGIN_ID);
		boolean classpathNodeExists = false;
		try {
			classpathNodeExists = pluginRoot.nodeExists("classpath.helper");
		} catch (BackingStoreException e) {
			org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
		}
		return classpathNodeExists;
	}
	private void removeOnlyCPEntries(final IProject project, final IProjectFacetVersion fv, final IJavaProject jproj, final List cp,
			IRuntime oldRuntime) throws CoreException {
		IFacetedProject fproj = ProjectFacetsManager.create(project);
		IRuntime runtime = (oldRuntime != null) ? oldRuntime : fproj.getPrimaryRuntime();
	
		if (runtime != null) {
			IClasspathProvider cpprov = (IClasspathProvider) runtime.getAdapter(IClasspathProvider.class);
			List cpentries = cpprov.getClasspathEntries(fv);
			boolean realCPChanged = false;
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

}
