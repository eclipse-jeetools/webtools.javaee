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
/*
 * Created on Sep 13, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.RuntimeClasspathEntry;
import org.eclipse.jst.j2ee.internal.common.ClasspathModel;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;


/**
 * @author schacher Helper class used to compute all the dependencies for a JAR being added to an
 *         EAR
 */
class JARDependencyTraverser {

	private IProject archiveProject;
	private IProject earProject;
	private Map result;
	private EAREditModel earEditModel;

	public JARDependencyTraverser(IProject anArchiveProject, IProject anEarProject) {
		super();
		this.archiveProject = anArchiveProject;
		this.earProject = anEarProject;
	}

	//Returns a map where the keys are instances of IResource, and the values
	// are uris within an EAR
	public Map run() {
		EARNatureRuntime[] earNatures = J2EEProjectUtilities.getReferencingEARProjects(archiveProject);
		if (earNatures == null || earNatures.length == 0 || (earNatures.length == 1 && earNatures[0].getProject() == earProject))
			return Collections.EMPTY_MAP;

		result = new HashMap();
		EARNatureRuntime runtime = EARNatureRuntime.getRuntime(earProject);
		if (runtime == null)
			return Collections.EMPTY_MAP;

		earEditModel = runtime.getEarEditModelForRead(this);
		try {
			for (int i = 0; i < earNatures.length; i++) {
				if (earNatures[i].getProject() != earProject) {
					traverseClasspath(earNatures[i]);
				}
			}
			return result;
		} finally {
			if (earEditModel != null)
				earEditModel.releaseAccess(this);
		}
	}

	private void traverseClasspath(EARNatureRuntime anEARNature) {
		ClasspathModel model = new ClasspathModel(null);
		model.setProject(archiveProject);
		//TODO enable this line when this class in migrated to use EARArtifactEdit
		//model.setSelectedEARNature(anEARNature);
		ClassPathSelection cpSelection = model.getClassPathSelection();
		Archive anArchive = model.getArchive();
		RuntimeClasspathEntry[] entries = anArchive.getDependencyClassPath();
		Archive referencedArchive = null;
		for (int i = 0; i < entries.length; i++) {
			RuntimeClasspathEntry entry = entries[i];
			referencedArchive = entry.getReferencedArchive();
			if (entry.isWebLib() || referencedArchive == null)
				continue;
			String uri = referencedArchive.getURI();
			if (uri == null)
				continue;
			ClasspathElement elmt = cpSelection.getClasspathElement(uri);
			if (elmt == null)
				continue;
			IResource resource = elmt.getResource();
			if (resource != null)
				addResult(resource, uri);
		}
	}

	private void addResult(IResource res, String uri) {
		if (result.containsKey(res))
			return;
		boolean exists = false;
		switch (res.getType()) {
			case IResource.FILE :
				exists = containsJARFile((IFile) res, uri);
				break;
			case IResource.PROJECT :
				exists = earEditModel.hasMappingToProject((IProject) res);
				break;
			default :
				return;
		}
		if (!exists)
			result.put(res, uri);
	}


	/**
	 * @param file
	 * @param uri
	 * @return
	 */
	private boolean containsJARFile(IFile file, String uri) {
		IPath path = null;
		try {
			path = new Path(uri);
		} catch (IllegalArgumentException tooBad) {
			return true;
		}
		IFile existing = earProject.getFile(path);
		return (existing != null && existing.exists());

	}


}