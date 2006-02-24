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
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public class EjbClientJarCreationDataModelProvider
	extends AbstractDataModelProvider 
	implements IEjbClientJarCreationDataModelProperties{

	public EjbClientJarCreationDataModelProvider() {
		super();
	}
	
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(PROJECT_NAME);
		propertyNames.add(EJB_PROJECT_NAME);
		propertyNames.add(CLIENT_URI);
		return propertyNames;
	}
	
	public IDataModelOperation getDefaultOperation() {
		return new EjbClientJarCreationOperation(model);
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(CLIENT_URI)){
			String projectName = model.getStringProperty(EJB_PROJECT_NAME);
			return projectName + "Client.jar"; //$NON-NLS-1$ 
		}
		if (propertyName.equals(PROJECT_NAME)){
			String projectName = model.getStringProperty(EJB_PROJECT_NAME);
			return projectName + "Client"; //$NON-NLS-1$ 
		}
		return super.getDefaultProperty(propertyName);
	}
	
	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean status = super.propertySet(propertyName, propertyValue);
		if (propertyName.equals(PROJECT_NAME)){
			String projectName = model.getStringProperty(PROJECT_NAME);			
			setProperty(CLIENT_URI, projectName + "Client.jar");//$NON-NLS-1$			
		}
		return status;
	}
	
    public IStatus validate(String propertyName) {

        IStatus status = OK_STATUS;
        if(status.isOK()) {
        	if(propertyName.equals(PROJECT_NAME)){
        		String ejbProjectName = getStringProperty(EJB_PROJECT_NAME);
	        	String clientProjectName = getStringProperty(PROJECT_NAME);
	        	if(ejbProjectName.equals(clientProjectName)){
	        		return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.CLIENT_SAME_NAME_AS_EJB);
	        	}
        	}
	        if(propertyName.equals(PROJECT_NAME)){
	        	if(hasExistingClientJar())
	            	return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.EJB_JAR_already_has_client_Jar_); 
	            status = checkForValidProjectName(getStringProperty(PROJECT_NAME));
//	            if(status.isOK())
//                status = validateClientJarUri();	            
	        }

//	        } else if (propertyName.equals(CLIENT_URI)){
//	            status = validateClientJarUri();
//	        }
        }
        return status;
    }	
    
	private IStatus checkForValidProjectName(String aProjectName) {
		return ResourcesPlugin.getWorkspace().validateName(aProjectName, IResource.PROJECT);
	}
	
	protected IProject getEJBProject() {
		String projectName = getStringProperty(EJB_PROJECT_NAME);
		return ProjectUtilities.getProject( projectName );
	}
	
    public  boolean hasExistingClientJar() {
    	IProject project = getEJBProject();
		if( project.exists() && project.isAccessible()){
			EJBArtifactEdit edit = null;
			try {
					edit = EJBArtifactEdit.getEJBArtifactEditForRead(project);
					if (edit != null && edit.hasEJBClientJARProject()){
						return true;
					}
					return false;
			} finally {
				if(edit != null)
					edit.dispose();
					  
			}
		}
		return false;
    }	
}
