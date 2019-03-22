/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.project.facet;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.project.facet.IUtilityFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.J2EEModuleFacetInstallDataModelProvider;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectWorkingCopy;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class UtilityFacetInstallDataModelProvider extends J2EEModuleFacetInstallDataModelProvider implements IUtilityFacetInstallDataModelProperties {
	public UtilityFacetInstallDataModelProvider()
	{
		super();
	}

	@Override
	public Object getDefaultProperty(String propertyName) {
		if (FACET_ID.equals(propertyName)) {
			return UTILITY;
		} else if (propertyName.equals(MODULE_URI)) {
			String projectName = model.getStringProperty(FACET_PROJECT_NAME).replace(' ', '_');
			return projectName + IJ2EEModuleConstants.JAR_EXT;
		}
		else if( propertyName.equals( CONFIG_FOLDER ) )
		{
		    final IFacetedProjectWorkingCopy fpjwc 
		        = (IFacetedProjectWorkingCopy) getProperty( FACETED_PROJECT_WORKING_COPY );
		    
		    if( this.javaFacetInstallConfig != null )
		    {
		        final List<IPath> sourceFolders = this.javaFacetInstallConfig.getSourceFolders();
		        
		        if( ! sourceFolders.isEmpty() )
		        {
		            return sourceFolders.get( 0 ).toPortableString();
		        }
		    }
		    else
		    {
    		    final IFacetedProject fpj = fpjwc.getFacetedProject();
    		    
    		    if( fpj.hasProjectFacet( JavaFacet.FACET ) )
    		    {
    		        try
    		        {
        		        final IJavaProject jpj = JavaCore.create( fpj.getProject() );
        		        
        		        for( IClasspathEntry cpe : jpj.getRawClasspath() )
        		        {
        		            if( cpe.getEntryKind() == IClasspathEntry.CPE_SOURCE )
        		            {
        		                return cpe.getPath().removeFirstSegments( 1 ).toPortableString();
        		            }
        		        }
    		        }
    		        catch( CoreException e )
    		        {
    		            J2EEPlugin.logError(e);
    		        }
    		    }
		    }
		}
		return super.getDefaultProperty(propertyName);
	}

	@Override
	protected int convertFacetVersionToJ2EEVersion(IProjectFacetVersion version) {
		return 0;
	}
}
