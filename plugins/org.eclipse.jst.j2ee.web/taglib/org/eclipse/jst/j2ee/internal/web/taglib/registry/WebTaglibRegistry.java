/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.taglib.registry;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.internal.web.locator.WebLibModuleTaglibLocator;
import org.eclipse.jst.j2ee.internal.web.locator.WebProjectServerTargetTaglibLocator;
import org.eclipse.jst.j2ee.internal.web.locator.WebProjectTaglibLocator;
import org.eclipse.jst.j2ee.internal.web.locator.WebXMLTaglibLocator;
import org.eclipse.jst.j2ee.internal.web.taglib.ITaglibInfo;
import org.eclipse.jst.j2ee.internal.web.taglib.ITaglibLocator;
import org.eclipse.jst.j2ee.web.modulecore.util.WebArtifactEdit;
import org.eclipse.wst.common.modulecore.ModuleCore;


public class WebTaglibRegistry extends AbstractTaglibRegistry {

	private ITaglibLocator libModuleLocator;
	private ITaglibLocator serverTargetLocator;
	private ITaglibLocator webProjectTaglibLocator;

	/**
	 * Constructor for WebTaglibRegistry.
	 * 
	 * @param project
	 */
	public WebTaglibRegistry(IProject project) {
		super(project);
	}

	protected void initialize() {
		// the reason these two locators are not part of the list is they are per file locator but
		// per project
		this.libModuleLocator = new WebLibModuleTaglibLocator(this.project);
		this.serverTargetLocator = new WebProjectServerTargetTaglibLocator(this.project);
		this.webProjectTaglibLocator = new WebProjectTaglibLocator(this.project);
		this.locators = new ITaglibLocator[]{this.webProjectTaglibLocator, new WebXMLTaglibLocator(this.project)};
	}

	protected boolean isWebXMLFile(IPath filePath) {
		IPath webAppDDPath = getWebDeploymentDescriptorPath();
		return webAppDDPath.removeFirstSegments(1).equals(filePath);
	} 

	protected boolean requiresFullUpdate(IResourceDelta delta) {
		// TODO figure out context root change to update the tag libs
		// If the context root is updated, a full refresh is required.
		//if (delta != null)
		//	return delta.getResource().getFullPath().equals(getWTPModuleFile());
		return false;
	}

	/*
	 * (non-Javadoc) we need to return the Resource/folder from where taglib search begins and for a
	 * web project we can make it faster by searching from WEB-INF folder
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.web.taglib.registry.AbstractTaglibRegistry#getRefreshRoot()
	 */
	protected IResource getRefreshRoot() {
		return project;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.web.taglib.registry.AbstractTaglibRegistry#shouldProcessDirectory()
	 */
	protected boolean shouldProcessDirectory(IResource resource) {
		IContainer tagsFolder = getTagsFolder();
		if (tagsFolder != null && tagsFolder.getProjectRelativePath().equals(resource.getProjectRelativePath())) {
			return true;
		}
		return false;

	}

	private IContainer getTagsFolder() {
		IContainer webinf = (IContainer) getRefreshRoot();
		if (webinf != null) {
			IResource tagsFolder = webinf.findMember("tags"); //$NON-NLS-1$
			if (tagsFolder != null && tagsFolder instanceof IContainer)
				return (IContainer) tagsFolder;
		}
		return null;
	}

	/*
	 * (non-Javadoc) return true only if the resource is a parent of WEB-INF or is within WEB-INF
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.web.taglib.registry.AbstractTaglibRegistry#canVisitResource(org.eclipse.core.resources.IResource)
	 */
	protected boolean canVisitResource(IResource resource) {
		if (resource != null && resource.getType() != IResource.ROOT && this.project != resource.getProject())
			return false;

		IResource webinf = getRefreshRoot();
		// resource within WEB-INF
		if (webinf != null && resource != null && webinf.getProjectRelativePath().isPrefixOf(resource.getProjectRelativePath()))
			return true;
		// resource parent of WEB-INF
		else if (webinf != null && resource != null && resource.getProjectRelativePath().isPrefixOf(webinf.getProjectRelativePath()))
			return true;
		// some other branch
		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.web.taglib.registry.AbstractTaglibRegistry#processDirectory(org.eclipse.core.resources.IResource)
	 */
	protected void processDirectory(IResource resource) {
		Vector existingTaglibs = getExistingTaglibs(resource.getProjectRelativePath());
		ITaglibInfo[] newTaglibs = this.webProjectTaglibLocator.search(resource);

		for (int i = 0; i < newTaglibs.length; i++) {
			ITaglibInfo newTaglib = newTaglibs[i];
			int existingIndex = existingTaglibs.indexOf(newTaglib);
			if (existingIndex >= 0) {
				// If its an exact match, leave it in alone the taglibs registry
				// but fire a taglibChangedEvent.
				if (!newTaglib.isWebXMLEntry())
					fireTaglibChanged(newTaglib);
				existingTaglibs.remove(existingIndex);
			} else {
				// The taglib did not exist previously in the list, add it and
				// fire a taglibAdded event.
				addTaglib(newTaglib);
				fireTaglibAdded(newTaglib);
			}
		}

		for (Iterator iter = existingTaglibs.iterator(); iter.hasNext();) {
			ITaglibInfo remainingTaglib = (ITaglibInfo) iter.next();
			this.taglibs.remove(remainingTaglib);
			fireTaglibRemoved(remainingTaglib);
		}

	}

	protected Vector primGetTaglibs() {
		// Check to see if there are any lib modules, if so, we must check to see
		// if there are any taglibs in these and concat the results.
		Vector results = new Vector();
		results.addAll(super.primGetTaglibs());

		results.addAll(Arrays.asList(this.libModuleLocator.search(this.project)));
		results.addAll(Arrays.asList(this.serverTargetLocator.search(this.project)));
		return results;
	}
	
	protected IPath getWebDeploymentDescriptorPath() {
		WebArtifactEdit webEdit = null;
		try {
			webEdit = (WebArtifactEdit) ModuleCore.getFirstArtifactEditForRead(project);
			return webEdit.getDeploymentDescriptorPath();
		} finally {
			if (webEdit != null)
				webEdit.dispose();
		}
	}
}