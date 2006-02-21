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

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectValidator;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class JavaFacetValidator

    implements IFacetedProjectValidator
    
{
    public static final String MARKER_ID
        = "org.eclipse.jst.common.frameworks.javaVersionMismatch"; //$NON-NLS-1$
    
    public static final String ATTR_FACET_VERSION = "facetVersion"; //$NON-NLS-1$
    public static final String ATTR_COMPILER_LEVEL = "compilerLevel"; //$NON-NLS-1$

    public void validate( final IFacetedProject fproj ) 
    
        throws CoreException
        
    {
        final String level 
            = JavaFacetUtils.getCompilerLevel( fproj.getProject() );
        
        final IProjectFacetVersion fv
            = fproj.getInstalledVersion( JavaFacetUtils.JAVA_FACET );
        
        if( JavaFacetUtils.compilerLevelToFacet( level ) != fv )
        {
            final IMarker marker
                = fproj.createErrorMarker( MARKER_ID, Resources.versionsDontMatch );
            
            marker.setAttribute( ATTR_FACET_VERSION, fv.getVersionString() );
            marker.setAttribute( ATTR_COMPILER_LEVEL, level );
        }
    }
    
    private static final class Resources
    
        extends NLS
        
    {
        public static String versionsDontMatch;
        
        static
        {
            initializeMessages( JavaFacetValidator.class.getName(), 
                                Resources.class );
        }
    }

}
