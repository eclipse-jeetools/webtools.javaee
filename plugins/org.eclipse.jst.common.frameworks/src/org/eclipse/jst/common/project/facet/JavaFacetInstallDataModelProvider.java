/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.project.facet;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.project.facet.core.JavaFacetInstallConfig;
import org.eclipse.jst.common.project.facet.core.JavaFacetInstallConfig.ChangeEvent;
import org.eclipse.wst.common.componentcore.datamodel.FacetInstallDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.util.IEventListener;

public class JavaFacetInstallDataModelProvider extends FacetInstallDataModelProvider implements IJavaFacetInstallDataModelProperties {

    private final JavaFacetInstallConfig installConfig;
    
	public JavaFacetInstallDataModelProvider() {
		this( new JavaFacetInstallConfig() );
	}
	
	public JavaFacetInstallDataModelProvider( final JavaFacetInstallConfig installConfig )
	{
	    this.installConfig = installConfig;
	    
	    final IEventListener<JavaFacetInstallConfig.ChangeEvent> listener
	        = new IEventListener<JavaFacetInstallConfig.ChangeEvent>()
        {
            public void handleEvent( final ChangeEvent event )
            {
                final IDataModel dm = getDataModel();

                if( event.getType() == JavaFacetInstallConfig.ChangeEvent.Type.SOURCE_FOLDERS_CHANGED )
                {
                    String val = null;
                    
                    if( installConfig.getSourceFolders().size() > 0 )
                    {
                        val = installConfig.getSourceFolders().get( 0 ).toPortableString();
                    }
                    
                    dm.setProperty( SOURCE_FOLDER_NAME, val );
                }
                else if( event.getType() == JavaFacetInstallConfig.ChangeEvent.Type.DEFAULT_OUTPUT_FOLDER_CHANGED )
                {
                    final String val = installConfig.getDefaultOutputFolder().toPortableString();
                    dm.setProperty( DEFAULT_OUTPUT_FOLDER_NAME, val );
                }
            }
        };
        
        this.installConfig.addListener( listener );
	}

	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(JAVA_FACET_INSTALL_CONFIG);
		propertyNames.add(SOURCE_FOLDER_NAME);
        propertyNames.add(DEFAULT_OUTPUT_FOLDER_NAME);
		return propertyNames;
	}

	public boolean propertySet( final String propertyName, 
	                            final Object propertyValue )
    {
        if( propertyName.equals( SOURCE_FOLDER_NAME ) )
        {
            final List<IPath> sourceFolders;
            
            if( propertyValue == null )
            {
                sourceFolders = Collections.emptyList();
            }
            else
            {
                sourceFolders = Collections.<IPath>singletonList( new Path( (String) propertyValue ) );
            }
            
            this.installConfig.setSourceFolders( sourceFolders );
            
            return true;
        }
        else if( propertyName.equals( DEFAULT_OUTPUT_FOLDER_NAME ) )
        {
            this.installConfig.setDefaultOutputFolder( new Path( (String) propertyValue ) );
            return true;
        }
        else if( propertyName.equals( JAVA_FACET_INSTALL_CONFIG ) )
        {
            return false;
        }
        
        return super.propertySet( propertyName, propertyValue );
    }

    public Object getDefaultProperty(String propertyName) {
		if (FACET_ID.equals(propertyName)) {
			return IModuleConstants.JST_JAVA;
		}
		else if( JAVA_FACET_INSTALL_CONFIG.equals( propertyName ) )
		{
		    return this.installConfig;
		}
		else if( SOURCE_FOLDER_NAME.equals( propertyName ) )
		{
		    if( this.installConfig.getSourceFolders().isEmpty() )
		    {
		        return null;
		    }
		    else
		    {
		        return this.installConfig.getSourceFolders().get( 0 ).toPortableString();
		    }
		}
		else if( DEFAULT_OUTPUT_FOLDER_NAME.equals( propertyName ) )
		{
		    final IPath outputFolder = this.installConfig.getDefaultOutputFolder();
		    
		    if( outputFolder == null )
		    {
		        return null;
		    }
		    else
		    {
		        return outputFolder.toPortableString();
		    }
		}
        
		return super.getDefaultProperty(propertyName);
	}

}
