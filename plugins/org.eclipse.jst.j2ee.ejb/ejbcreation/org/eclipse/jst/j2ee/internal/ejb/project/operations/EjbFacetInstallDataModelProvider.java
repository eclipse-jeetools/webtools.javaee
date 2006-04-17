/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
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
import org.eclipse.jst.j2ee.internal.common.CreationConstants;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.J2EEModuleFacetInstallDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModelProviderNew;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class EjbFacetInstallDataModelProvider
		extends J2EEModuleFacetInstallDataModelProvider 
		implements IEjbFacetInstallDataModelProperties{

	public static final IProjectFacetVersion EJB_11 = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EJB_MODULE).getVersion("1.1"); //$NON-NLS-1$
	public static final IProjectFacetVersion EJB_20 = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EJB_MODULE).getVersion("2.0"); //$NON-NLS-1$
	public static final IProjectFacetVersion EJB_21 = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EJB_MODULE).getVersion("2.1"); //$NON-NLS-1$	
	
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
			return J2EEProjectUtilities.EJB;
		}else if (propertyName.equals(CREATE_CLIENT)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(CLIENT_SOURCE_FOLDER)) {
			return CreationConstants.DEFAULT_EJB_SOURCE_FOLDER;
		}else if (propertyName.equals(CONFIG_FOLDER)){
			return CreationConstants.DEFAULT_EJB_SOURCE_FOLDER;
		}else if (propertyName.equals(CLIENT_NAME)){
			String projectName = model.getStringProperty(FACET_PROJECT_NAME);
			return projectName + "Client"; //$NON-NLS-1$ 
		}else if (propertyName.equals(CLIENT_URI)){
			String projectName = model.getStringProperty(FACET_PROJECT_NAME);
			return projectName + "Client.jar"; //$NON-NLS-1$ 
		} else if (propertyName.equals(MODULE_URI)) {
			String projectName = model.getStringProperty(FACET_PROJECT_NAME);
			return projectName + IJ2EEModuleConstants.JAR_EXT; 
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
	    	model.setStringProperty(CLIENT_NAME, (String)propertyValue + "Client");
	    	model.setStringProperty(CLIENT_URI, (String)propertyValue + "Client.jar");
	    }else if(propertyName.equals(ADD_TO_EAR)){
	    	model.setBooleanProperty(CREATE_CLIENT, ((Boolean)propertyValue).booleanValue());
	    	model.notifyPropertyChange(CREATE_CLIENT, IDataModel.ENABLE_CHG);
	    }
		return status;
	}	
	
	public IStatus validate(String propertyName) {
		if (propertyName.equals(CLIENT_NAME)) {
			String projectName = model.getStringProperty( CLIENT_NAME );
			IStatus status = ProjectCreationDataModelProviderNew.validateName( projectName );
			if (!status.isOK())
				return status;
		}else if( propertyName.equals(CLIENT_URI)){
				String clientURI = model.getStringProperty( CLIENT_URI );
				IStatus status = ProjectCreationDataModelProviderNew.validateName( clientURI );
				if (!status.isOK())
					return status;
		
		}
		return super.validate(propertyName);
	}

}
