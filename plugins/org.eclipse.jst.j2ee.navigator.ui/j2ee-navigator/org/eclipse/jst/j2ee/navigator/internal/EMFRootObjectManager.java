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
package org.eclipse.jst.j2ee.navigator.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.J2EEEditModel;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelEvent;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelListener;

/**
 * <p>
 * The following class is experimental until fully documented.
 * </p>
 */
public class EMFRootObjectManager implements EditModelListener, IResourceChangeListener, IResourceDeltaVisitor {

	

	private final Map projectEditModels = new HashMap();
	private final Map projectRootObjects = new HashMap();
	
	/**
	 * 
	 */
	public EMFRootObjectManager() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	public void dispose() {
		if (projectEditModels == null)
			return;
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		J2EEEditModel editModel = null;
		Object[] keys = projectEditModels.keySet().toArray();
		for (int i = 0; i < keys.length; i++) {
			editModel = (J2EEEditModel) projectEditModels.remove(keys[i]);
			if (editModel != null) {
				editModel.removeListener(this);
				editModel.releaseAccess(this);
			}
		}
	}

	public Object getRootObject(IProject project) {
		if (project == null || !project.isAccessible())
			return null;

		Object rootObject = null; 
		synchronized (projectRootObjects) {
			rootObject = projectRootObjects.get(project);

			if (rootObject == null || ((EObject) rootObject).eResource() == null) {
				J2EEEditModel editModel = getEditModelForProject(project);
				if (editModel != null) {
					rootObject = editModel.getPrimaryRootObject();
					projectRootObjects.put(project, rootObject);
				}
			}
		}
		return rootObject;
	}
	

	protected J2EEEditModel getEditModelForProject(IProject project) {
		if (project == null)
			return null;
		J2EEEditModel editModel = null;
		synchronized (projectEditModels) {
			editModel = (J2EEEditModel) projectEditModels.get(project);
			if (editModel == null && project.isAccessible()) {
				//System.out.println("getEditModelForProject " + project.getName());
				J2EENature nature = J2EENature.getRegisteredRuntime(project);
				if (nature != null) {
					editModel = nature.getJ2EEEditModelForRead(this);
					if (editModel != null) {
						editModel.addListener(this);
						addMapping(project, editModel);
					}
				}
			}
		}
		return editModel;
	}
	
	protected void addMapping(IProject project, J2EEEditModel editModel) {
		projectEditModels.put(project, editModel);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emfworkbench.integration.EditModelListener#editModelChanged(org.eclipse.wst.common.internal.emfworkbench.integration.EditModelEvent)
	 */
	public void editModelChanged(EditModelEvent anEvent) { 

	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	public void resourceChanged(IResourceChangeEvent event) { 

	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
	 */
	public boolean visit(IResourceDelta delta) throws CoreException { 
		return false;
	}

}
