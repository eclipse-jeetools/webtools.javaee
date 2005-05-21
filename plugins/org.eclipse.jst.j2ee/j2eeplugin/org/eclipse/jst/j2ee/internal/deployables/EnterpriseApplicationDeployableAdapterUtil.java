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

import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IModuleArtifact;
import org.eclipse.wst.server.core.ServerUtil;
import org.eclipse.wst.server.core.util.NullModuleArtifact;

/**
 * Creates the Object adapter for ear projects.
 */
public class EnterpriseApplicationDeployableAdapterUtil {

	/**
	 * Constructor for EnterpriseApplicationDeployableObjectAdapter.
	 */
	public EnterpriseApplicationDeployableAdapterUtil() {
		super();
	}// EnterpriseApplicationDeployableObjectAdapter

	/**
	 * Gets the object for a type of object.
	 * 
	 * @param Object
	 *            obj - Object to adapt.
	 */
	public static IModuleArtifact getModuleObject(Object obj) {
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
	protected static IModuleArtifact getModuleObject(Application application) {
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
	protected static IModuleArtifact getModuleObject(IProject project) {
		org.eclipse.wst.server.core.IModule dep = getModule(project,null);
		return createModuleObject(dep);
	}// getModuleObject

	/**
	 * Gets the deployable object for file instances.
	 * 
	 * @param IFile
	 *            file - File instance.
	 * @return IModuleObject
	 */
	protected static IModuleArtifact getModuleObject(IFile file) {
		if (file.getProjectRelativePath().toString().endsWith(ArchiveConstants.APPLICATION_DD_URI)) {
			{
				IVirtualResource[] resources = ComponentCore.createResources(file);
				IVirtualComponent component = null;
				if (resources[0] != null || resources.length <= 0)
					component = resources[0].getComponent();
				return createModuleObject(getModule(file.getProject(), component));
			}
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
	protected static org.eclipse.wst.server.core.IModule getModule(EObject refObject) {
		IProject proj = ProjectUtilities.getProject(refObject);
		Resource servResource = refObject.eResource();
		IVirtualResource[] resources = null;
		try {
			IResource eclipeServResoruce = (IResource) WorkbenchResourceHelper.getFile(servResource);
			resources = ComponentCore.createResources(eclipeServResoruce);
		} catch (Exception e) {
			e.printStackTrace();
		}
		IVirtualComponent component = null;
		if (resources[0] != null)
			component = resources[0].getComponent();
		return getModule(proj,component);
	}// getModule



	/**
	 * Gets the ear nature.
	 * 
	 * @param IProject
	 *            project - The current project.
	 * @return EARNatureRuntime
	 */
	protected static EARNatureRuntime getNature(IProject project) {
		if (project != null) {
			return EARNatureRuntime.getRuntime(project);
		}// if
		return null;
	}// getNature



	protected static IModule getModuleProject(IProject project, Iterator iterator) {
		IModule deployable = null;
		while (iterator.hasNext()) {
			Object next = iterator.next();
			if (next instanceof IModule) {
				deployable = (IModule) next;
				if (deployable.getProject().equals(project))
					return deployable;
			}
		}
		return null;
	}

	protected static IModule getModule(IProject project, IVirtualComponent component) {
		IModule deployable = null;
		Iterator iterator = Arrays.asList(ServerUtil.getModules("j2ee.ear")).iterator();
		String componentName = null;
		if (component != null)
			componentName = component.getName();
		else
			return getModuleProject(project, iterator);
		while (iterator.hasNext()) {
			Object next = iterator.next();
			if (next instanceof IModule) {
				deployable = (IModule) next;
				if (deployable.getName().equals(componentName)) {
					return deployable;
				}
			}
		}
		return null;
	}

	/**
	 * Creates the deployable object.
	 * 
	 * @param IModuleObject
	 *            deployable - The current module object.
	 */
	protected static IModuleArtifact createModuleObject(IModule module) {

		if (module != null) {
			return new NullModuleArtifact(module);
		}

		return null;
	}// createDeployableObject

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.server.core.IModuleArtifactAdapter#getId()
	 */
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.server.core.IModuleArtifactAdapter#getObjectClassName()
	 */
	public String getObjectClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.server.core.IModuleArtifactAdapter#isPluginActivated()
	 */
	public boolean isPluginActivated() {
		// TODO Auto-generated method stub
		return false;
	}

}// EnterpriseApplicationDeployableObjectAdapter
