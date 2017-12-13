/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.J2EEModuleFacetInstallDataModelProvider;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModelProviderNew;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class EjbFacetInstallDataModelProvider
		extends J2EEModuleFacetInstallDataModelProvider 
		implements IEjbFacetInstallDataModelProperties{

	@Override
	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(CONFIG_FOLDER);
		names.add(CREATE_CLIENT);
		names.add(CLIENT_NAME);
		names.add(CLIENT_SOURCE_FOLDER);
		names.add(CLIENT_URI);
		names.add(ALLOW_EJB_CLIENT);
		return names;
	}
	
	@Override
	public Object getDefaultProperty(String propertyName) {
		if(propertyName.equals(FACET_ID)){
			return EJB;
		}else if (propertyName.equals(CREATE_CLIENT)) {
			return getProperty(ADD_TO_EAR);
		} else if (propertyName.equals(CLIENT_SOURCE_FOLDER)) {
			return J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.EJB_CONTENT_FOLDER);
		}else if (propertyName.equals(CONFIG_FOLDER)){
			if (model.isPropertySet(FACET_PROJECT_NAME))
			{
				String projectName = model.getStringProperty(FACET_PROJECT_NAME);
				IProject project = (projectName.length() > 0) ? ResourcesPlugin.getWorkspace().getRoot().getProject(projectName) : null;
				
				if (project != null && project.exists()) {
					if (ModuleCoreNature.isFlexibleProject(project))
					{
						IVirtualComponent c = ComponentCore.createComponent(project, true);
						IVirtualFolder ejbroot = c.getRootFolder();
						IPath configFolderPath = J2EEModuleVirtualComponent.getDefaultDeploymentDescriptorFolder(ejbroot);
						if (configFolderPath != null && project.getFolder(configFolderPath).exists())
						{
							return configFolderPath.toString();
						}
					}
				}
			}
			return J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.EJB_CONTENT_FOLDER);
		}else if (propertyName.equals(CLIENT_NAME)){
			String clientProjectName = model.getStringProperty(FACET_PROJECT_NAME);
			clientProjectName = clientProjectName + "Client"; //$NON-NLS-1$
			
			//check that the default name does not already exist, if it does try 1-9, if none of thouse work use orginal default
			IStatus status = ProjectCreationDataModelProviderNew.validateName(clientProjectName);
			int append = 0;
			while(!status.isOK() && append <= 9) {
				append++;
				status = ProjectCreationDataModelProviderNew.validateName(clientProjectName + append);
			}
			if(append > 0 && append <= 9) {
				clientProjectName = clientProjectName + append;
			}
			
			return clientProjectName; 
		}else if (propertyName.equals(CLIENT_URI)){
			String clientProjectURI = model.getStringProperty(CLIENT_NAME).replace(' ', '_');
			return clientProjectURI + IJ2EEModuleConstants.JAR_EXT; 
		} else if (propertyName.equals(MODULE_URI)) {
			String projectName = model.getStringProperty(FACET_PROJECT_NAME).replace(' ', '_');
			return projectName + IJ2EEModuleConstants.JAR_EXT; 
		} else if (propertyName.equals(IJ2EEFacetInstallDataModelProperties.GENERATE_DD)) {
			if (getBooleanProperty(CREATE_CLIENT)) {
				return Boolean.TRUE;
			}
			String facetVersion = getStringProperty(FACET_VERSION_STR);
			if (J2EEVersionUtil.convertVersionStringToInt(facetVersion) >= J2EEVersionConstants.EJB_3_0_ID) {
				return J2EEPlugin.getDefault().getJ2EEPreferences().getBoolean(J2EEPreferences.Keys.EJB_GENERATE_DD);
			}
			return Boolean.TRUE;
		}
		return super.getDefaultProperty(propertyName);
	}
	
	@Override
	protected int convertFacetVersionToJ2EEVersion(IProjectFacetVersion version) {
		return J2EEVersionUtil.convertEJBVersionStringToJ2EEVersionID(version.getVersionString());
	}
		
	@Override
	public boolean isPropertyEnabled(String propertyName) {
		if (CLIENT_NAME.equals(propertyName)) {
			return getBooleanProperty(CREATE_CLIENT);
		} else if (CLIENT_URI.equals(propertyName)) {
			return getBooleanProperty(CREATE_CLIENT);
		} else if (CREATE_CLIENT.equals(propertyName)) {
			return getBooleanProperty(ADD_TO_EAR)|| getBooleanProperty(ALLOW_EJB_CLIENT);
		} else if (GENERATE_DD.equals(propertyName)) {
			return !getBooleanProperty(CREATE_CLIENT);
		}
		return super.isPropertyEnabled(propertyName);
	}

	
	@Override
	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean status = super.propertySet(propertyName, propertyValue);
		
		if (propertyName.equals(CREATE_CLIENT)) {
	    	model.notifyPropertyChange(CLIENT_NAME, IDataModel.ENABLE_CHG);
	    	model.notifyPropertyChange(CLIENT_URI, IDataModel.ENABLE_CHG);
	    	model.notifyPropertyChange(GENERATE_DD, IDataModel.ENABLE_CHG);
	    	if ((Boolean) propertyValue && isPropertySet(GENERATE_DD)) {
    			model.setBooleanProperty(GENERATE_DD, true);
    		} else {
    	    	model.notifyPropertyChange(GENERATE_DD, IDataModel.DEFAULT_CHG);
    		}
		} else if (propertyName.equals(CLIENT_NAME)) {
			String clientProjectURI = model.getStringProperty(CLIENT_NAME).replace(' ', '_');
			model.setStringProperty(CLIENT_URI, clientProjectURI + IJ2EEModuleConstants.JAR_EXT); 
	    } else if (propertyName.equals(FACET_PROJECT_NAME)) {
	    	model.setStringProperty(CLIENT_NAME, (String) model.getDefaultProperty(CLIENT_NAME));
	    	model.setStringProperty(CLIENT_URI, (String) model.getDefaultProperty(CLIENT_URI));
	    } else if (propertyName.equals(ADD_TO_EAR)) {
	    	boolean addToEar = (Boolean) propertyValue;
	    	if (!addToEar && isPropertySet(CREATE_CLIENT)) {
	    		model.setBooleanProperty(CREATE_CLIENT, false);
	    	} else {
	    		model.notifyPropertyChange(CREATE_CLIENT, IDataModel.DEFAULT_CHG);
		    	model.notifyPropertyChange(CLIENT_NAME, IDataModel.ENABLE_CHG);
		    	model.notifyPropertyChange(CLIENT_URI, IDataModel.ENABLE_CHG);
		    	model.notifyPropertyChange(GENERATE_DD, IDataModel.ENABLE_CHG);
		    	if ((Boolean) getProperty(CREATE_CLIENT) && isPropertySet(GENERATE_DD)) {
	    			model.setBooleanProperty(GENERATE_DD, true);
	    		} else {
	    	    	model.notifyPropertyChange(GENERATE_DD, IDataModel.DEFAULT_CHG);
	    		}
	    	}
	    	model.notifyPropertyChange(CREATE_CLIENT, IDataModel.ENABLE_CHG);
        } else if (propertyName.equals(CONFIG_FOLDER)) 
        {
            if( this.javaFacetInstallConfig != null )
            {
                final IPath sourceFolder
                    = propertyValue == null ? null : new Path( (String) propertyValue );
                
                this.javaFacetInstallConfig.setSourceFolder( sourceFolder );
            }
        }
        else if (FACET_PROJECT_NAME.equals(propertyName)) {
				if (!model.isPropertySet(CONFIG_FOLDER)) {
					model.notifyPropertyChange(CONFIG_FOLDER, IDataModel.DEFAULT_CHG);
				}
		}
        else if (ALLOW_EJB_CLIENT.equals(propertyName))
        {
        	model.notifyPropertyChange(CREATE_CLIENT, IDataModel.DEFAULT_CHG);
        }

		return status;
	}	
	
	@Override
	public IStatus validate(String propertyName) {
		if (propertyName.equals(CLIENT_NAME)) {
			//should only validate on CLIENT_NAME if going to create a client
			boolean createCleint = model.getBooleanProperty(CREATE_CLIENT);
			if(createCleint) {
				String projectName = model.getStringProperty( CLIENT_NAME );
				IStatus status = ProjectCreationDataModelProviderNew.validateName( projectName );
				return status;
			}
			return OK_STATUS;
		}else if( propertyName.equals(CLIENT_URI)){
			//should only validate on CLIENT_URI if going to create a client
			boolean createCleint = model.getBooleanProperty(CREATE_CLIENT);
			if(createCleint) {
				String clientURI = model.getStringProperty( CLIENT_URI );
				IStatus status = ProjectCreationDataModelProviderNew.validateName( clientURI );
				return status;
			}
			return OK_STATUS;
		}
		return super.validate(propertyName);
	}

}
