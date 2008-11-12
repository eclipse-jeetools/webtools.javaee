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

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.common.project.facet.core.internal.ClasspathUtil;
import org.eclipse.jst.common.project.facet.core.libprov.ILibraryProvider;
import org.eclipse.jst.common.project.facet.core.libprov.LibraryProviderFramework;
import org.eclipse.jst.common.project.facet.core.libprov.user.UserLibraryProviderInstallOperationConfig;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectBase;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public class WtpUserLibraryProviderInstallOperationConfig

    extends UserLibraryProviderInstallOperationConfig
    
{
    private static final String CLASS_NAME 
        = WtpUserLibraryProviderInstallOperationConfig.class.getName();
    
    public static final String PROP_INCLUDE_WITH_APPLICATION_ENABLED 
        = CLASS_NAME + ".INCLUDE_WITH_APPLICATION_ENABLED"; //$NON-NLS-1$

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
                final List<IClasspathEntry> entries;
                
                try
                {
                    entries = ClasspathUtil.getClasspathEntries( project, f );
                }
                catch( CoreException e )
                {
                    throw new RuntimeException( e );
                }
                
                this.includeWithApplication = getIncludeWithApplicationSetting( entries );
            }
        }
    }
    
    private static boolean getIncludeWithApplicationSetting( final List<IClasspathEntry> entries )
    {
        for( IClasspathEntry cpe : entries )
        {
            if( cpe.getEntryKind() == IClasspathEntry.CPE_CONTAINER )
            {
                final IPath path = cpe.getPath();
                
                if( path.segmentCount() >= 2 && path.segment( 0 ).equals( JavaCore.USER_LIBRARY_CONTAINER_ID ) )
                {
                    for( IClasspathAttribute attr : cpe.getExtraAttributes() )
                    {
                        if( attr.getName().equals( IClasspathDependencyConstants.CLASSPATH_COMPONENT_NON_DEPENDENCY ) )
                        {
                            return false;
                        }
                    }
                }
            }
        }
        
        return true;
    }
    
    
}
