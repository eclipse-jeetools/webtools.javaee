/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.deployables;


import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IModuleArtifact;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * Creates the Object adapter for ear projects.
 */
public class EnterpriseApplicationDeployableObjectAdapter  {

	/**
	 * Constructor for EnterpriseApplicationDeployableObjectAdapter.
	 */
	public EnterpriseApplicationDeployableObjectAdapter() {
		super();
	}// EnterpriseApplicationDeployableObjectAdapter

	/**
	 * Gets the object for a type of object.
	 * 
	 * @param Object
	 *            obj - Object to adapt.
	 */
	public IModuleArtifact getModuleObject(Object obj) {
		if (obj instanceof Application)
			return getModuleObject((Application) obj);
		if (obj instanceof IProject)
			return getModuleObject((IProject) obj);
		if (obj instanceof IFile)
			return getModuleObject((IFile) obj);
		return null;
	}// getDeployableObject

	/**
	 * Gets the deployable object for ear instance.
	 * 
	 * @param Application
	 *            application - EAR instance.
	 */
	protected IModuleArtifact getModuleObject(Application application) {
		IModule dep = getModule(application);
		return createModuleObject(dep);
	}// getDeployableObject

	/**
	 * Gets the deployable object for project instances.
	 * 
	 * @param IProject
	 *            project - Project instance.
	 * @return IModuleObject
	 */
	protected IModuleArtifact getModuleObject(IProject project) {
		org.eclipse.wst.server.core.IModule dep = getModule(project);
		return createModuleObject(dep);
	}// getModuleObject

	/**
	 * Gets the deployable object for file instances.
	 * 
	 * @param IFile
	 *            file - File instance.
	 * @return IModuleObject
	 */
	protected IModuleArtifact getModuleObject(IFile file) {
		if (file.getProjectRelativePath().toString().endsWith(ArchiveConstants.APPLICATION_DD_URI)) {
			return createModuleObject(getModule(file.getProject()));
		}// if
		return null;
	}// getModuleObject

	/**
	 * Gets the deployable object.
	 * 
	 * @param EObject
	 *            refObject - The current refObject.
	 * @return IModule
	 */
	protected org.eclipse.wst.server.core.IModule getModule(EObject refObject) {
		IProject proj = ProjectUtilities.getProject(refObject);
		return getModule(proj);
	}// getModule

	/**
	 * Get the deployable object.
	 * 
	 * @param EObject
	 *            refObject - The current refObject.
	 * @return IModule
	 */
	protected IModule getModule(IProject project) {
		EARNatureRuntime nature = getNature(project);
		if (nature != null) {
			return nature.getModule();
		}// if
		return null;
	}// getModule

	/**
	 * Gets the ear nature.
	 * 
	 * @param IProject
	 *            project - The current project.
	 * @return EARNatureRuntime
	 */
	protected EARNatureRuntime getNature(IProject project) {
		if (project != null) {
			return EARNatureRuntime.getRuntime(project);
		}// if
		return null;
	}// getNature

	/**
	 * Creates the deployable object.
	 * 
	 * @param IModuleObject
	 *            deployable - The current module object.
	 */
	protected IModuleArtifact createModuleObject(IModule module) {
		/*if (module != null) {
			return new NullModuleObject(module);
		}*/// switch to NullModuleArtifact when released
		return null;
	}// createDeployableObject

    /* (non-Javadoc)
     * @see org.eclipse.wst.server.core.IModuleArtifactAdapter#getId()
     */
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.wst.server.core.IModuleArtifactAdapter#getObjectClassName()
     */
    public String getObjectClassName() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.wst.server.core.IModuleArtifactAdapter#isPluginActivated()
     */
    public boolean isPluginActivated() {
        // TODO Auto-generated method stub
        return false;
    }


}// EnterpriseApplicationDeployableObjectAdapter
