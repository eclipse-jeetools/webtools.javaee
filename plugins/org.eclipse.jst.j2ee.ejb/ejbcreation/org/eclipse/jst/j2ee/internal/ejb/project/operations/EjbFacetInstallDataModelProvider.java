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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.J2EEModuleFacetInstallDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModelProviderNew;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class EjbFacetInstallDataModelProvider
		extends J2EEModuleFacetInstallDataModelProvider 
		implements IEjbFacetInstallDataModelProperties{

	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(CONFIG_FOLDER);
		names.add(CREATE_CLIENT);
		names.add(CLIENT_NAME);
		names.add(CLIENT_SOURCE_FOLDER);
		names.add(CLIENT_URI);
		return names;
	}
	
	public Object getDefaultProperty(String propertyName) {
		if(propertyName.equals(FACET_ID)){
			return EJB;
		}else if (propertyName.equals(CREATE_CLIENT)) {
			return getProperty(ADD_TO_EAR);
		} else if (propertyName.equals(CLIENT_SOURCE_FOLDER)) {
			return J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.EJB_CONTENT_FOLDER);
		}else if (propertyName.equals(CONFIG_FOLDER)){
			return J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.EJB_CONTENT_FOLDER);
		}else if (propertyName.equals(CLIENT_NAME)){
			String clientProjectName = model.getStringProperty(FACET_PROJECT_NAME);
			clientProjectName = clientProjectName + "Client";
			
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
			
			return clientProjectName; //$NON-NLS-1$ 
		}else if (propertyName.equals(CLIENT_URI)){
			String clientProjectURI = model.getStringProperty(CLIENT_NAME).replace(' ', '_');
			return clientProjectURI + ".jar"; //$NON-NLS-1$ 
		} else if (propertyName.equals(MODULE_URI)) {
			String projectName = model.getStringProperty(FACET_PROJECT_NAME).replace(' ', '_');
			return projectName + IJ2EEModuleConstants.JAR_EXT; 
		} else if (propertyName.equals(IJ2EEFacetInstallDataModelProperties.GENERATE_DD)) {
			IProjectFacetVersion facetVersion = (IProjectFacetVersion)getProperty(FACET_VERSION);
			if(facetVersion == EJB_30){
				return Boolean.valueOf(J2EEPlugin.getDefault().getJ2EEPreferences().getBoolean(J2EEPreferences.Keys.EJB_GENERATE_DD));
			}
			return Boolean.TRUE;
		}
		return super.getDefaultProperty(propertyName);
	}
	
	protected int convertFacetVersionToJ2EEVersion(IProjectFacetVersion version) {
		if (EJB_11.equals(version)) {
			return J2EEVersionConstants.J2EE_1_2_ID;
		} else if (EJB_20.equals(version)) {
			return J2EEVersionConstants.J2EE_1_3_ID;
		}
		return J2EEVersionConstants.J2EE_1_4_ID;
	}
		
	public boolean isPropertyEnabled(String propertyName) {
		if ( CLIENT_NAME.equals(propertyName )) {
			return getBooleanProperty(CREATE_CLIENT);
		}else if( CLIENT_URI.equals(propertyName )){
			return getBooleanProperty(CREATE_CLIENT);
		}else if(CREATE_CLIENT.equals(propertyName)){
			return getBooleanProperty(ADD_TO_EAR);
		}
		return super.isPropertyEnabled(propertyName);
	}

	
	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean status = super.propertySet(propertyName, propertyValue);
		
		if (propertyName.equals(CREATE_CLIENT)){
	    	model.notifyPropertyChange(CLIENT_NAME, IDataModel.ENABLE_CHG);
	    	model.notifyPropertyChange(CLIENT_URI, IDataModel.ENABLE_CHG);
	    }else if (propertyName.equals(FACET_PROJECT_NAME)){
	    	model.setStringProperty(CLIENT_NAME, (String)model.getDefaultProperty(CLIENT_NAME));
	    	model.setStringProperty(CLIENT_URI, (String)model.getDefaultProperty(CLIENT_URI));
	    }else if(propertyName.equals(ADD_TO_EAR)){
	    	boolean addToEar = ((Boolean)propertyValue).booleanValue();
	    	if(!addToEar && isPropertySet(CREATE_CLIENT)){
	    		model.setBooleanProperty(CREATE_CLIENT, false);
	    	} else {
	    		model.notifyPropertyChange(CREATE_CLIENT, IDataModel.DEFAULT_CHG);
		    	model.notifyPropertyChange(CLIENT_NAME, IDataModel.ENABLE_CHG);
		    	model.notifyPropertyChange(CLIENT_URI, IDataModel.ENABLE_CHG);
	    	}
	    	model.notifyPropertyChange(CREATE_CLIENT, IDataModel.ENABLE_CHG);

	    }

		return status;
	}	
	
	public IStatus validate(String propertyName) {
		if (propertyName.equals(CLIENT_NAME)) {
			//should only validate on CLIENT_NAME if going to create a client
			boolean createCleint = model.getBooleanProperty(CREATE_CLIENT);
			if(createCleint) {
				String projectName = model.getStringProperty( CLIENT_NAME );
				IStatus status = ProjectCreationDataModelProviderNew.validateName( projectName );
				return status;
			} else {
				return OK_STATUS;
			}
		}else if( propertyName.equals(CLIENT_URI)){
			//should only validate on CLIENT_URI if going to create a client
			boolean createCleint = model.getBooleanProperty(CREATE_CLIENT);
			if(createCleint) {
				String clientURI = model.getStringProperty( CLIENT_URI );
				IStatus status = ProjectCreationDataModelProviderNew.validateName( clientURI );
				return status;
			} else {
				return OK_STATUS;
			}
		}
		return super.validate(propertyName);
	}

}
