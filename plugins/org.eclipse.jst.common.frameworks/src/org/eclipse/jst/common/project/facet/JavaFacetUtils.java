/******************************************************************************
 * Copyright (c) 2005, 2006 BEA Systems, Inc.
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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.common.project.facet.core.internal.JavaFacetUtil;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class JavaFacetUtils
{
    public static final IProjectFacet JAVA_FACET = JavaFacet.FACET;
    public static final IProjectFacetVersion JAVA_13 = JavaFacet.JAVA_13;
    public static final IProjectFacetVersion JAVA_14 = JavaFacet.JAVA_14;
    public static final IProjectFacetVersion JAVA_50 = JavaFacet.JAVA_50;
    public static final IProjectFacetVersion JAVA_60 = JavaFacet.JAVA_60;
    
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
        return JavaFacetUtil.compilerLevelToFacet( ver );
    }

    public static String facetToCompilerLevel( final IProjectFacetVersion fv )
    {
        return JavaFacetUtil.facetToCompilerLevel( fv );
    }
    
}
