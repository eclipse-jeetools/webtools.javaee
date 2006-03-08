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

package org.eclipse.jst.j2ee.internal.plugin;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.ui.IActionFilter;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class FacetedProjectActionFilter

    implements IActionFilter
    
{
    public boolean testAttribute( final Object target, 
                                  final String name, 
                                  final String value )
    {
    	
        if( name.equals( "facet" ) ) //$NON-NLS-1$
        {
        	IProject project = (IProject)target;
        	IFacetedProject fproj = null;
        	if(project.isAccessible()){
				try {
					fproj = ProjectFacetsManager.create( project );
				} catch (CoreException e1) {
					Logger.getLogger().logError(e1);
				}
        	}

            if( fproj != null ){
	            final int colon = value.indexOf( ':' );
	            final String fid;
	            final String vexprstr;
	            
	            if( colon == -1 || colon == value.length() - 1 )
	            {
	                fid = value;
	                vexprstr = null;
	            }
	            else
	            {
	                fid = value.substring( 0, colon );
	                vexprstr = value.substring( colon + 1 );
	            }
	            
	            if( ! ProjectFacetsManager.isProjectFacetDefined( fid ) )
	            {
	                return false;
	            }
	            
	            final IProjectFacet f = ProjectFacetsManager.getProjectFacet( fid );
	            
	            if( ! fproj.hasProjectFacet( f ) )
	            {
	                return false;
	            }
	            
	            if( vexprstr == null )
	            {
	                return true;
	            }
	            else
	            {
	                final IProjectFacetVersion fv = fproj.getInstalledVersion( f );
	                
	                try
	                {
	                    if( f.getVersions( vexprstr ).contains( fv ) )
	                    {
	                        return true;
	                    }
	                }
	                catch( CoreException e )
	                {
	                	//EJBUIPlugin.getDefault().log
	                }
	            }
	            
	            return false;
	        }
	        else
	        {
	            return false;
	        }
        }
        return true;
    }
    
}
