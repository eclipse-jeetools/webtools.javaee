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

package org.eclipse.jst.common.project.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.osgi.service.prefs.BackingStoreException;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class JavaFacetUtils
{
    private static final IProjectFacetVersion JAVA_13
        = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_JAVA).getVersion( "1.3" );
    
    private static final IProjectFacetVersion JAVA_14
        = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_JAVA).getVersion( "1.4" );

    private static final IProjectFacetVersion JAVA_50
        = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_JAVA).getVersion( "5.0" );
    
    public static void setCompilerLevel( final IProject project,
                                         final IProjectFacetVersion f )
    
        throws CoreException
        
    {
        final IScopeContext context = new ProjectScope( project );
        
        final IEclipsePreferences prefs 
            = context.getNode( JavaCore.PLUGIN_ID );
        
        if( f == JAVA_13 )
        {
            prefs.put( JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_3 );
            prefs.put( JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_1 );
            prefs.put( JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_3 );
            prefs.put( JavaCore.COMPILER_PB_ASSERT_IDENTIFIER, JavaCore.IGNORE );
            prefs.put( JavaCore.COMPILER_PB_ENUM_IDENTIFIER, JavaCore.IGNORE );
        }
        else if( f == JAVA_14 )
        {
            prefs.put( JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_4 );
            prefs.put( JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_2 );
            prefs.put( JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_3 );
            prefs.put( JavaCore.COMPILER_PB_ASSERT_IDENTIFIER, JavaCore.WARNING );
            prefs.put( JavaCore.COMPILER_PB_ENUM_IDENTIFIER, JavaCore.WARNING );
        }
        else if( f == JAVA_50 )
        {
            prefs.put( JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_5 );
            prefs.put( JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_5 );
            prefs.put( JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5 );
            prefs.put( JavaCore.COMPILER_PB_ASSERT_IDENTIFIER, JavaCore.ERROR );
            prefs.put( JavaCore.COMPILER_PB_ENUM_IDENTIFIER, JavaCore.ERROR );
        }
        else
        {
            throw new IllegalStateException();
        }

        try
        {
            prefs.flush();
        }
        catch( BackingStoreException e )
        {
            // TODO: Handle this.
        }
    }

}
