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

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public class WtpUserLibraryProviderInstallOperation

    extends UserLibraryProviderInstallOperation
    
{
    @Override
    protected IClasspathEntry createClasspathEntry( final UserLibraryProviderInstallOperationConfig config,
                                                    final String libraryName )
    {
        final WtpUserLibraryProviderInstallOperationConfig cfg
            = (WtpUserLibraryProviderInstallOperationConfig) config;
        
        final IClasspathAttribute attr;
        
        if( cfg.isCopyOnPublishEnabled() )
        {
            attr = JavaCore.newClasspathAttribute( IClasspathDependencyConstants.CLASSPATH_COMPONENT_DEPENDENCY,
                                                   ClasspathDependencyUtil.getDefaultRuntimePath( true ).toString() );
        }
        else
        {
            attr = JavaCore.newClasspathAttribute( IClasspathDependencyConstants.CLASSPATH_COMPONENT_NON_DEPENDENCY, "" );
        }
        
        IClasspathEntry cpe = super.createClasspathEntry( config, libraryName );
        cpe = JavaCore.newContainerEntry( cpe.getPath(), null, new IClasspathAttribute[]{ attr }, false );
        
        return cpe;
    }
    
}
