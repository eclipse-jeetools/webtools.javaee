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
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.J2EEEditModel;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.SWTException;
import org.eclipse.wst.common.emfworkbench.integration.EditModelEvent;
import org.eclipse.wst.common.emfworkbench.integration.EditModelListener;
import org.eclipse.wst.common.navigator.views.INavigatorExtensionSite;

import com.ibm.wtp.common.logger.proxy.Logger;


/**
 * @author Administrator
 * 
 *  
 */
public class J2EERootObjectManager implements EditModelListener, IResourceChangeListener, IResourceDeltaVisitor {


	private Map projectEditModels;

	private Map projectRootObjects;

	private INavigatorExtensionSite extensionSite;

	private J2EENavigatorContentExtension containingExtension;

	/**
	 * @return Returns the containingExtension.
	 */
	protected J2EENavigatorContentExtension getContainingExtension() {
		return this.containingExtension;
	}

	public J2EERootObjectManager(J2EENavigatorContentExtension containingExtension, INavigatorExtensionSite extensionSite) {
		this.containingExtension = containingExtension;
		this.extensionSite = extensionSite;
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	public void dispose() {
		if (projectEditModels == null)
			return;
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		J2EEEditModel editModel = null;
		Object[] keys = getProjectEditModels().keySet().toArray();
		for (int i = 0; i < keys.length; i++) {
			editModel = (J2EEEditModel) getProjectEditModels().remove(keys[i]);
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
		Map localRoots = getProjectRootObjects();
		synchronized (localRoots) {
			rootObject = localRoots.get(project);

			if (rootObject == null || ((EObject) rootObject).eResource() == null) {
				J2EEEditModel editModel = getEditModelForProject(project);
				if (editModel != null) {
					rootObject = editModel.getPrimaryRootObject();
					localRoots.put(project, rootObject);
				}
			}
		}
		return rootObject;
	}

	public void editModelChanged(EditModelEvent anEvent) {
		IProject affectedProject = anEvent.getEditModel().getProject();
		switch (anEvent.getEventCode()) {
			case EditModelEvent.UNLOADED_RESOURCE :
			case EditModelEvent.REMOVED_RESOURCE : {
				Object oldRootObject = getProjectRootObjects().remove(affectedProject);
				if (oldRootObject != null) {
					getExtensionSite().notifyElementReplaced(getContainingExtension(), affectedProject);
				}
			}
				break;
			case EditModelEvent.SAVE : {
				Object oldRootObject = getProjectRootObjects().get(affectedProject);
				if (oldRootObject == null) {
					//newChildren =
					// containingExtension.getContentProvider().getChildren(affectedProject);
					getExtensionSite().notifyElementReplaced(getContainingExtension(), affectedProject);
				}
			}
				break;
			case EditModelEvent.PRE_DISPOSE :
				disposeCache(affectedProject);
				break;
			default :
				break;
		}
	}

	protected void disposeCache(IProject affectedProject) {
		synchronized (getProjectEditModels()) {
			getProjectRootObjects().remove(affectedProject);
			//System.out.println("REMOVING edit model: " + affectedProject.getName());
			J2EEEditModel editModel = (J2EEEditModel) getProjectEditModels().remove(affectedProject);
			//System.out.println("REMOVED edit model " + affectedProject.getName());
			if (editModel != null) {
				editModel.removeListener(this);
				editModel.releaseAccess(this);
				//System.out.println("RELEASED edit model " + affectedProject.getName());
			}
		}
	}

	protected J2EEEditModel getEditModelForProject(IProject project) {
		if (project == null)
			return null;
		J2EEEditModel editModel = null;
		synchronized (getProjectEditModels()) {
			editModel = (J2EEEditModel) getProjectEditModels().get(project);
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
		getProjectEditModels().put(project, editModel);
	}

	/**
	 * @return Returns the projectEditModels.
	 */
	protected Map getProjectEditModels() {
		if (projectEditModels == null)
			projectEditModels = new HashMap();
		return projectEditModels;
	}

	/**
	 * @return Returns the extensionSite.
	 */
	protected INavigatorExtensionSite getExtensionSite() {
		return extensionSite;
	}

	/**
	 * @return Returns the projectRootObjects.
	 */
	protected Map getProjectRootObjects() {
		if (projectRootObjects == null)
			projectRootObjects = new HashMap();
		return projectRootObjects;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	public void resourceChanged(IResourceChangeEvent event) {
		final IResourceDelta delta = event.getDelta();

		if (delta != null) {
			try {
				delta.accept(J2EERootObjectManager.this);
			} catch (CoreException e) {
				Logger.getLogger().logError(e);
			} catch (SWTException swte) {
				Logger.getLogger().logError(swte);
			} catch (SWTError swte) {
				Logger.getLogger().logError(swte);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
	 */
	public boolean visit(IResourceDelta delta) throws CoreException {
		IResource resource = delta.getResource();
		if (resource != null) {
			switch (resource.getType()) {
				case IResource.ROOT :
					return true;
				case IResource.PROJECT :
					if (delta.getKind() == IResourceDelta.REMOVED)
						disposeCache((IProject) resource);
					return false;
			}
		}
		return false;
	}

}
