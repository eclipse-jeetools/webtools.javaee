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

package org.eclipse.jst.j2ee.refactor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jem.util.logger.proxy.Logger;

/**
 * Maintains a cache of project dependencies as represented by references in the .project file.
 * 
 * XXX DependencyManagerGraph is being used instead now that inter-project dependencies have
 * dependent-module entries, however, this type of dependency representation might be useful if
 * we want to capture dependencies on projects not represented in the .component file.
 */
public class ProjectDependencyCache {

	private static ProjectDependencyCache cache;
	
	public synchronized static ProjectDependencyCache getCache() {
		if (cache == null) {
			try {
				cache = new ProjectDependencyCache();
			} catch (CoreException ce) {
				Logger.getLogger().logError(ce);
			}
		}
		return cache;
	}
	
	/*
	 * Map from target IProject to List of dependent IProjects
	 */
	private final Map dependencyCache = new HashMap();
	
	/*
	 * Map from dependent IProject to List of target IProject
	 */
	private final Map referenceCache = new HashMap();
	
	/**
	 * Creates and initializes the cache.
	 */
	protected ProjectDependencyCache() throws CoreException {
		final IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for (int i = 0; i < projects.length; i++) {
			refreshDependencies(projects[i]);
		}
	}
	
	public synchronized IProject[] getDependentProjects(final IProject project) {
		List dependencies = (List) dependencyCache.get(project);
		if (dependencies == null) {
			return new IProject[0];
		}
		return (IProject[]) dependencies.toArray(new IProject[dependencies.size()]);		
	}
	
	public synchronized void refreshDependencies(final IProject project) throws CoreException {
		List oldRefs = (List) referenceCache.get(project);
		if (oldRefs == null) {
			oldRefs = new ArrayList();
		}
		final IProject[] refProjects = project.getDescription().getReferencedProjects();
		final List newRefs = new ArrayList();
		for (int j = 0; j < refProjects.length; j++) {
			addDependency(refProjects[j], project);
			newRefs.add(refProjects[j]);
			oldRefs.remove(refProjects[j]);
		}
		referenceCache.put(project, newRefs);
		
		// need to cull the stale refs from the reverse map
		Iterator i = oldRefs.iterator();
		while (i.hasNext()) {
			removeDependency((IProject) i.next(), project);
		}
	}
	
	public synchronized void refreshDependenciesForTarget(final IProject target) throws CoreException {
		final IProject[] dependents= getDependentProjects(target);
		for (int i = 0; i < dependents.length; i++) {
			refreshDependencies(dependents[i]);
		}
	}
	
	public synchronized void removeProject(final IProject project) throws CoreException {
		// remove from the dependency cache
		dependencyCache.remove(project);
		// remove from the reference cache and clear the from the cache of all dependent projects
		List dependencies = (List) referenceCache.remove(project);
		if (dependencies != null) {
			final Iterator i = dependencies.iterator();
			while (i.hasNext()) {
				removeDependency((IProject) i.next(), project); 
			}
		}
	}
	
	public synchronized void replaceProject(final IProject originalProject, final IProject newProject) throws CoreException {
		// rename dependencyCache entry
		dependencyCache.put(newProject, dependencyCache.remove(originalProject));
		
		// rename reference cache and clear the from the cache of all dependent projects
		List dependencies = (List) referenceCache.remove(originalProject);
		referenceCache.put(newProject, dependencies);
		if (dependencies != null) {
			final Iterator i = dependencies.iterator();
			while (i.hasNext()) {
				final IProject ref = (IProject) i.next();
				removeDependency(ref, originalProject); 
				addDependency(ref, newProject);
			}
		}
	}
	
	private void removeDependency(final IProject target, final IProject dependent) {
		List dependentProjects = (List) dependencyCache.get(target);
		if (dependentProjects != null) {
			dependentProjects.remove(dependent);
		}
	}
	
	private void addDependency(final IProject target, final IProject dependent) {
		List dependentProjects = (List) dependencyCache.get(target);
		if (dependentProjects == null) {
			dependentProjects = new ArrayList();
			dependencyCache.put(target, dependentProjects);
		}
		if (!dependentProjects.contains(dependent)) {
			dependentProjects.add(dependent);
		}
	}
}
