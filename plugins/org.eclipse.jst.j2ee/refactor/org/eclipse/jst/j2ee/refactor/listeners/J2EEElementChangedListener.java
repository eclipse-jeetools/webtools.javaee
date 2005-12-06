/*******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * rfrost@bea.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.refactor.listeners;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IElementChangedListener;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

/**
 * Implementation of <code>IElementChangedListener that updates mappings for src folders
 * in the .component file in response to changes in a project's Java classpath. 
 */
public class J2EEElementChangedListener implements IElementChangedListener {

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IElementChangedListener#elementChanged(org.eclipse.jdt.core.ElementChangedEvent)
	 */
	public void elementChanged(final ElementChangedEvent event) {
		processJavaElementDelta(event.getDelta());
	}
	
	private void processJavaElementDelta(final IJavaElementDelta delta) {
		final int kind = delta.getKind();
		if (kind == IJavaElementDelta.CHANGED) {
			final int flags = delta.getFlags();
			final IJavaElement element = delta.getElement();
			if ((flags & IJavaElementDelta.F_CHILDREN) == IJavaElementDelta.F_CHILDREN) {
				if (element instanceof IJavaModel) {
					final IJavaElementDelta[] children = delta.getChangedChildren();
					for (int i = 0; i < children.length; i++) {
						// handle all of the IJavaProject children
						processJavaElementDelta(children[i]);
					}					
				} else if (element instanceof IJavaProject
						&& (flags & IJavaElementDelta.F_CLASSPATH_CHANGED) != 0) {
					final IJavaProject jproject = (IJavaProject) element;
					final IProject project = jproject.getProject();
					// make certain this is a J2EE project
					if (ModuleCoreNature.getModuleCoreNature(project) != null) {
						try {
							alterMapping(delta.getAffectedChildren(), jproject, project);
						} catch (CoreException ce) {
							Logger.getLogger().logError(ce);
						}
					}
				}
			}
		}
	}

	/*
	 * Adds or removes a resource mapping from the .component file.
	 */
	private void alterMapping(final IJavaElementDelta[] children, final IJavaProject jproject,
			final IProject project) throws CoreException {
		for (int i = 0; i < children.length; i++) {
			final IJavaElementDelta delta = children[i];
			final IJavaElement element = delta.getElement();
			final int kind = delta.getKind();
			final int flags = delta.getFlags();
			if (element instanceof IPackageFragmentRoot) {
				final IPackageFragmentRoot root = (IPackageFragmentRoot) element;
				int cpeKind = IPackageFragmentRoot.K_SOURCE;
				try {
					cpeKind = root.getKind();
				} catch (JavaModelException jme) {
					// this is thrown if the folder corresponding to the CPE has been deleted
					// since it could represent another error, we need to set to binary to 
					// avoid adding mappings for binary folders
					cpeKind = IPackageFragmentRoot.K_BINARY;
				}

				final IPath path = root.getPath().removeFirstSegments(1);
				WorkspaceJob job= null;
				// kind and flags for CP additions are somewhat sporadic; either:
				// -kind is ADDED and flags are 0
				//   or
				// -kind is CHANGED and flags are F_ADDED_TO_CLASSPATH
				if (kind == IJavaElementDelta.ADDED || 
						(flags & IJavaElementDelta.F_ADDED_TO_CLASSPATH) == IJavaElementDelta.F_ADDED_TO_CLASSPATH) {
					// can only add if we know it is a src folder
					if (cpeKind == IPackageFragmentRoot.K_SOURCE) {
						job = new WorkspaceJob("J2EEComponentLinkAdditionJob") {
							public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
								getDestinationFolder(project).createLink(path, 0, monitor);
								return Status.OK_STATUS;
							}
						};
					}
				// getting a kind = REMOVED and flags = 0 for removal of the folder (w/o removing the CPE), probably
			    // should not be generated
				} else if ((flags & IJavaElementDelta.F_REMOVED_FROM_CLASSPATH) == IJavaElementDelta.F_REMOVED_FROM_CLASSPATH) {						
					job = new WorkspaceJob("J2EEComponentMappingRemovalJob") {							
						public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
							getDestinationFolder(project).removeLink(path, 0, monitor);
							return Status.OK_STATUS;
						}
					};
				}
				if (job != null) {
					job.setRule(project);
					job.schedule();
				}
			}
		}
	}

	/*
	 * Retrieves the IVirtualFolder to which Java src folders should be mapped
	 */
	private IVirtualFolder getDestinationFolder(final IProject project) throws CoreException {
		final IVirtualComponent c = ComponentCore.createComponent(project);
		c.create(0, null);
		final IVirtualFolder root = c.getRootFolder();
		if (J2EEProjectUtilities.isDynamicWebProject(project)) {
			// web projects map to WEB-INF/classes
			return root.getFolder(new Path(J2EEConstants.WEB_INF_CLASSES));
		}
		// all other J2EE project types (that are Java projects) map 
		// Java src folders to the project root
		return root;
	}
	
}
