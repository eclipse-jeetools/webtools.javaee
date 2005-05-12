/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.taglib;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.internal.web.taglib.registry.JavaTaglibRegistry;
import org.eclipse.jst.j2ee.internal.web.taglib.registry.WebTaglibRegistry;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;


public class TaglibRegistryManager implements ITaglibRegistryManager {



	protected HashMap registries = new HashMap();
	protected IResourceChangeListener resourceListener;

	private class ResourceChangeListener implements IResourceChangeListener, IResourceDeltaVisitor {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
		 */
		public void resourceChanged(IResourceChangeEvent event) {
			if (event.getSource() instanceof IWorkspace) {
				IResourceDelta delta = event.getDelta();
				switch (event.getType()) {
					case IResourceChangeEvent.POST_CHANGE :
						if (delta != null) {
							try {
								delta.accept(this);
							} catch (CoreException e) {
								//Do nothing
							}
						}
						break;
					case IResourceChangeEvent.PRE_CLOSE :
					case IResourceChangeEvent.PRE_DELETE :
						removeTaglibRegistry((IProject) event.getResource());
						break;
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
		 */
		public boolean visit(IResourceDelta delta) throws CoreException {
			if (delta != null) {
				TaglibRegistryManager.this.resourceChanged(delta);
				return true;
			}
			return false;
		}

	}

	public TaglibRegistryManager() {
		super();
		initialize();
	}

	protected ITaglibRegistry createNewRegistry(IProject project) {
		ITaglibRegistry registry = null;
		WebArtifactEdit webEdit = null;
		try {
			if (project != null && project.isAccessible()) { // ensure web library project not closed
				//TODO migrate to flex project
				//webEdit = (WebArtifactEdit) StructureEdit.getFirstArtifactEditForRead(project);
				if (webEdit!=null) {
					registry = new WebTaglibRegistry(project);
				} else if (project.hasNature(JavaCore.NATURE_ID)) {
					registry = new JavaTaglibRegistry(project);
				}
			}
		} catch (CoreException e) {
			//Do nothing
		} finally {
			if (webEdit != null)
				webEdit.dispose();
		}
		if (registry != null) {
			this.registries.put(project, registry);
		}
		return registry;
	}

	protected void initialize() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		this.resourceListener = new ResourceChangeListener();
		workspace.addResourceChangeListener(this.resourceListener);
	}

	protected void resourceChanged(IResourceDelta delta) {
		if (delta.getResource() instanceof IProject)
			return;

		Collection registryCol = this.registries.values();
		for (Iterator iter = registryCol.iterator(); iter.hasNext();) {
			ITaglibRegistry registry = (ITaglibRegistry) iter.next();
			if (delta.getResource().getProject() == registry.getProject()) {
				registry.processResourceChanged(delta);
			}
		}
	}

	protected void removeTaglibRegistry(IProject project) {
		this.registries.remove(project);
	}

	/**
	 * @see ITaglibRegistryManager#getTaglibRegistry(IProject)
	 */
	public ITaglibRegistry getTaglibRegistry(IProject project) {
		ITaglibRegistry registry = (ITaglibRegistry) this.registries.get(project);
		if (registry == null) {
			registry = createNewRegistry(project);
		}
		return registry;
	}

	/**
	 * @see ITaglibRegistryManager#dispose()
	 */
	public void dispose() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		workspace.removeResourceChangeListener(this.resourceListener);
		this.registries = null;
	}

	/**
	 * @see Object#toString()
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		Collection regCol = this.registries.values();
		for (Iterator iter = regCol.iterator(); iter.hasNext();) {
			ITaglibRegistry registry = (ITaglibRegistry) iter.next();
			buf.append("=== Registry for " + registry.getProject() + "===\n"); //$NON-NLS-1$ //$NON-NLS-2$
			buf.append(registry);
			buf.append("\n"); //$NON-NLS-1$
		}
		return buf.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.taglib.ITaglibRegistryManager#isTaglibRegistryExists(org.eclipse.core.resources.IProject)
	 */
	public boolean isTaglibRegistryExists(IProject project) {
		return this.registries.containsKey(project);
	}


}