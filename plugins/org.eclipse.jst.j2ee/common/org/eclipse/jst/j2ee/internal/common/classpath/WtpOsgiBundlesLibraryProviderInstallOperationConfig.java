/******************************************************************************
 * Copyright (c) 2008 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.jst.j2ee.internal.common.classpath;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.common.project.facet.core.libprov.ILibraryProvider;
import org.eclipse.jst.common.project.facet.core.libprov.LibraryProviderFramework;
import org.eclipse.jst.common.project.facet.core.libprov.osgi.OsgiBundlesContainer;
import org.eclipse.jst.common.project.facet.core.libprov.osgi.OsgiBundlesLibraryProviderInstallOperationConfig;
import org.eclipse.jst.j2ee.classpathdep.ClasspathDependencyUtil;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectBase;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public class WtpOsgiBundlesLibraryProviderInstallOperationConfig

    extends OsgiBundlesLibraryProviderInstallOperationConfig
    
{
    private static final String CLASS_NAME 
        = WtpOsgiBundlesLibraryProviderInstallOperationConfig.class.getName();
    
    public static final String PROP_INCLUDE_WITH_APPLICATION_ENABLED 
        = CLASS_NAME + ".INCLUDE_WITH_APPLICATION_ENABLED"; //$NON-NLS-1$

    private static final IProjectFacet WEB_FACET 
        = ProjectFacetsManager.getProjectFacet( IJ2EEFacetConstants.DYNAMIC_WEB );

    private boolean includeWithApplication = true;
    
    public boolean isIncludeWithApplicationEnabled()
    {
        return this.includeWithApplication;
    }
    
    public void setIncludeWithApplicationEnabled( final boolean includeWithApplication )
    {
        final boolean oldValue = this.includeWithApplication;
        this.includeWithApplication = includeWithApplication;
        notifyListeners( PROP_INCLUDE_WITH_APPLICATION_ENABLED, oldValue, this.includeWithApplication );
    }

    @Override
    public synchronized void init( final IFacetedProjectBase fproj,
                                   final IProjectFacetVersion fv,
                                   final ILibraryProvider provider )
    {
        super.init( fproj, fv, provider );
        
        this.includeWithApplication = true;
        
        final IProject project = fproj.getProject();
        
        if( project != null )
        {
            final IProjectFacet f = fv.getProjectFacet();
            
            final ILibraryProvider currentProvider 
                = LibraryProviderFramework.getCurrentProvider( project, f );
            
            if( currentProvider == provider )
            {
                final IPath path = OsgiBundlesContainer.CONTAINER_PATH.append( f.getId() );
                final IJavaProject jproj = JavaCore.create( project );
                
                try
                {
                    for( IClasspathEntry cpe : jproj.getRawClasspath() )
                    {
                        if( path.equals( cpe.getPath() ) )
                        {
                            this.includeWithApplication = true;
                            
                            for( IClasspathAttribute attr : cpe.getExtraAttributes() )
                            {
                                if( attr.getName().equals( IClasspathDependencyConstants.CLASSPATH_COMPONENT_NON_DEPENDENCY ) )
                                {
                                    this.includeWithApplication = false;
                                }
                            }
                        }
                    }
                }
                catch( CoreException e )
                {
                    throw new RuntimeException( e );
                }
            }
        }
    }
    
    @Override
    public IClasspathAttribute[] getClasspathAttributes()
    {
        final IFacetedProjectBase fproj = getFacetedProject();
        final boolean isWebProject = fproj.hasProjectFacet( WEB_FACET );
        
        final IClasspathAttribute attr;
        
        if( isIncludeWithApplicationEnabled() )
        {
            attr = JavaCore.newClasspathAttribute( IClasspathDependencyConstants.CLASSPATH_COMPONENT_DEPENDENCY,
                                                   ClasspathDependencyUtil.getDefaultRuntimePath( isWebProject ).toString() );
        }
        else
        {
            attr = JavaCore.newClasspathAttribute( IClasspathDependencyConstants.CLASSPATH_COMPONENT_NON_DEPENDENCY, "" );
        }
        
        return new IClasspathAttribute[]{ attr };
    }
    
}
