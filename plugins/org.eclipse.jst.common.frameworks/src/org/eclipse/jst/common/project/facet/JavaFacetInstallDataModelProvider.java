/*******************************************************************************
 * Copyright (c) 2003, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.common.project.facet.core.JavaFacetInstallConfig;
import org.eclipse.jst.common.project.facet.core.JavaFacetInstallConfig.ChangeEvent;
import org.eclipse.wst.common.componentcore.datamodel.FacetInstallDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.util.IEventListener;

public class JavaFacetInstallDataModelProvider extends FacetInstallDataModelProvider implements IJavaFacetInstallDataModelProperties {

    private final JavaFacetInstallConfig installConfig;
    
	public JavaFacetInstallDataModelProvider() 
	{
		this( new JavaFacetInstallConfig() );
	}
	
	public JavaFacetInstallDataModelProvider( final JavaFacetInstallConfig installConfig )
	{
	    this.installConfig = installConfig;
	}

	@Override
	public Set getPropertyNames() 
	{
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(JAVA_FACET_INSTALL_CONFIG);
		propertyNames.add(SOURCE_FOLDER_NAME);
        propertyNames.add(DEFAULT_OUTPUT_FOLDER_NAME);
		return propertyNames;
	}

    @Override
    public void init()
    {
        super.init();
        
        final IDataModel dm = getDataModel();
        
        dm.setProperty( FACET_ID, JavaFacet.ID );
        dm.setProperty( JAVA_FACET_INSTALL_CONFIG, this.installConfig );
        
        String sourceFolderName = null;
        
        if( ! this.installConfig.getSourceFolders().isEmpty() )
        {
            sourceFolderName = this.installConfig.getSourceFolders().get( 0 ).toPortableString();
        }
        
        dm.setProperty( SOURCE_FOLDER_NAME, sourceFolderName );
        
        String defaultOutputFolderName = null;
        
        if( this.installConfig.getDefaultOutputFolder() != null )
        {
            defaultOutputFolderName = this.installConfig.getDefaultOutputFolder().toPortableString();
        }
        
        dm.setProperty( DEFAULT_OUTPUT_FOLDER_NAME, defaultOutputFolderName );
        
        final IEventListener<JavaFacetInstallConfig.ChangeEvent> listener
            = new IEventListener<JavaFacetInstallConfig.ChangeEvent>()
        {
            @Override
			public void handleEvent( final ChangeEvent event )
            {
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
	
	@Override
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

}
