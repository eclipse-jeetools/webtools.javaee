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

import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.common.project.facet.core.libprov.user.UserLibraryProviderInstallOperation;
import org.eclipse.jst.common.project.facet.core.libprov.user.UserLibraryProviderInstallOperationConfig;
import org.eclipse.jst.j2ee.classpathdep.ClasspathDependencyUtil;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectBase;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public class WtpUserLibraryProviderInstallOperation

    extends UserLibraryProviderInstallOperation
    
{
    private static final IProjectFacet WEB_FACET 
        = ProjectFacetsManager.getProjectFacet( IJ2EEFacetConstants.DYNAMIC_WEB );
    
    @Override
    protected IClasspathEntry createClasspathEntry( final UserLibraryProviderInstallOperationConfig config,
                                                    final String libraryName )
    {
        final WtpUserLibraryProviderInstallOperationConfig cfg
            = (WtpUserLibraryProviderInstallOperationConfig) config;
        
        final IFacetedProjectBase fproj = cfg.getFacetedProject();
        final boolean isWebProject = fproj.hasProjectFacet( WEB_FACET );
        
        final IClasspathAttribute attr;
        
        if( cfg.isIncludeWithApplicationEnabled() )
        {
            attr = JavaCore.newClasspathAttribute( IClasspathDependencyConstants.CLASSPATH_COMPONENT_DEPENDENCY,
                                                   ClasspathDependencyUtil.getDefaultRuntimePath( isWebProject ).toString() );
        }
        else
        {
            attr = JavaCore.newClasspathAttribute( IClasspathDependencyConstants.CLASSPATH_COMPONENT_NON_DEPENDENCY, "" ); //$NON-NLS-1$
        }
        
        IClasspathEntry cpe = super.createClasspathEntry( config, libraryName );
        cpe = JavaCore.newContainerEntry( cpe.getPath(), null, new IClasspathAttribute[]{ attr }, false );
        
        return cpe;
    }
    
}
