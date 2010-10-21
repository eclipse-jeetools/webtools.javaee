/******************************************************************************
 * Copyright (c) 2010, 2008 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 *    Paul Fullbright - [324111] Need better enablement behavior for WTP library providers
 ******************************************************************************/

package org.eclipse.jst.j2ee.internal.common.classpath;

import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.common.project.facet.core.libprov.user.UserLibraryProviderInstallOperation;
import org.eclipse.jst.common.project.facet.core.libprov.user.UserLibraryProviderInstallOperationConfig;

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
        
        IClasspathEntry cpe = super.createClasspathEntry( cfg, libraryName );
        cpe = JavaCore.newContainerEntry( cpe.getPath(), null, cfg.getClasspathAttributes(), false );
        
        return cpe;
    }
}
