/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;


import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.j2ee.internal.web.util.WebArtifactEdit;
import org.eclipse.wst.common.modulecore.ModuleCore;


/*
 * This class listens for renames of a web project's web content folder. If the folder is renamed,
 * the .websettings files is updated as well as the Java output folder.
 */
public class WebContentResourceChangeListener implements IResourceChangeListener {

	/**
	 * @see IResourceChangeListener#resourceChanged
	 */
	public void resourceChanged(final IResourceChangeEvent event) {
		processDelta(event.getDelta());
	}


	private void processDelta(IResourceDelta delta) {
		// Get the affected resource
		IResource resource = delta.getResource();

		switch (resource.getType()) {
			case IResource.ROOT :
				// Iterate over changed projects under the root
				IResourceDelta[] affectedChildren = delta.getAffectedChildren(IResourceDelta.CHANGED);
				for (int i = 0; i < affectedChildren.length; i++) {
					processDelta(affectedChildren[i]);
				}
				break;

			case IResource.PROJECT :
				// Only process web projects
			/*	IBaseWebNature nature = ModuleCore.getModuleCoreNature(org.eclipse.emf.common.util.URI.createURI("ADD MODULE URI"));
				if (nature != null)
					processWebProject(delta, (IProject) resource, nature);*/
				return;

			default :
				return;
		}
	}


	/*
	 * If renaming web content folder, then updated project properties.
	 */
	private void processWebProject(IResourceDelta delta, IProject project) {
		IResourceDelta[] changedChildren = delta.getAffectedChildren(IResourceDelta.CHANGED);
		if (changedChildren.length == 1)
			WebPropertiesUtil.synch(project, new NullProgressMonitor());
		IResourceDelta[] removedChildren = delta.getAffectedChildren(IResourceDelta.REMOVED);
		if (removedChildren.length != 1 || !removedChildren[0].getResource().equals(getModuleServerRoot(project)))
			return;
		IResourceDelta[] addedChildren = delta.getAffectedChildren(IResourceDelta.ADDED);
		if (addedChildren.length != 1)
			return;
		IPath newPath = addedChildren[0].getProjectRelativePath();
		if (newPath.segmentCount() != 1)
			return;
		String newName = newPath.segment(0);
		try {
			WebPropertiesUtil.updateWebContentNamePropertiesOnly(project, newName, new NullProgressMonitor());
		} catch (CoreException e) {
			//Ignore
		}
	}
	
	protected IResource getModuleServerRoot(IProject project) {
		WebArtifactEdit webEdit = null;
		try {
			webEdit = (WebArtifactEdit) ModuleCore.getFirstArtifactEditForRead(project);
			return (IResource) webEdit.getDeploymentDescriptorResource();
		} finally {
			if (webEdit != null)
				webEdit.dispose();
		}
	}	

}