/******************************************************************************
 * Copyright (c) 2008 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.common.project.facet;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.common.frameworks.CommonFrameworksPlugin;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.events.IFacetedProjectEvent;
import org.eclipse.wst.common.project.facet.core.events.IFacetedProjectListener;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class JavaFacetRuntimeChangedListener 

    implements IFacetedProjectListener
    
{
    public void handleEvent( final IFacetedProjectEvent event )
    {
        final IFacetedProject fproj = event.getProject();
        
        if( fproj.hasProjectFacet( JavaFacetUtils.JAVA_FACET ) )
        {
            final IProjectFacetVersion fv = fproj.getInstalledVersion( JavaFacetUtils.JAVA_FACET );
            
            try
            {
                JavaFacetUtils.resetClasspath( fproj.getProject(), fv, fv );
            }
            catch( CoreException e )
            {
                CommonFrameworksPlugin.log( e );
            }
        }
    }

}
