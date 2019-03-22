/******************************************************************************
 * Copyright (c) 2005, 2006 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.common.project.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.common.project.facet.core.internal.JavaFacetUtil;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * This class is deprecated. JavaFacet class should be used instead.
 * 
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

@Deprecated

public final class JavaFacetUtils
{
    public static final IProjectFacet JAVA_FACET = JavaFacet.FACET;
    public static final IProjectFacetVersion JAVA_13 = JavaFacet.VERSION_1_3;
    public static final IProjectFacetVersion JAVA_14 = JavaFacet.VERSION_1_4;
    public static final IProjectFacetVersion JAVA_50 = JavaFacet.VERSION_1_5;
    public static final IProjectFacetVersion JAVA_60 = JavaFacet.VERSION_1_6;
    
    public static String getCompilerLevel()
    {
        return JavaFacetUtil.getCompilerLevel();
    }

    public static String getCompilerLevel( final IProject project )
    {
        return JavaFacetUtil.getCompilerLevel( project );
    }
    
    public static void setCompilerLevel( final IProject project,
                                         final IProjectFacetVersion fv )
    
        throws CoreException
        
    {
        JavaFacetUtil.setCompilerLevel( project, fv );
    }

    public static void setCompilerLevel( final IProject project,
                                         final String level )
    
        throws CoreException
        
    {
        JavaFacetUtil.setCompilerLevel( project, level );
    }
    
    public static void scheduleFullBuild( final IProject project )
    {
        JavaFacetUtil.scheduleFullBuild( project );
    }
    
    public static void resetClasspath( final IProject project,
                                       final IProjectFacetVersion oldver,
                                       final IProjectFacetVersion newver )
    
        throws CoreException
        
    {
        JavaFacetUtil.resetClasspath( project, oldver, newver );
    }
    
    public static IProjectFacetVersion compilerLevelToFacet( final String ver )
    {
        return JavaFacet.FACET.getVersion( ver );
    }

    public static String facetToCompilerLevel( final IProjectFacetVersion fv )
    {
        return fv.getVersionString();
    }
    
}
