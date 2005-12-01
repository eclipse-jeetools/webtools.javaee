/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.dialogs;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;

/**
 * This class was derived from JavaSearchScope as that class did not have a
 * provision to exclude classpath entries that are not exported A Java-specific
 * scope for searching relative to one or more java elements.
 */
public class TypeJavaSearchScope implements IJavaSearchScope {

	private boolean includeExportedClassPathEntriesOnly = true;

	private ArrayList elements;

	/*
	 * The paths of the resources in this search scope (or the classpath
	 * entries' paths if the resources are projects)
	 */
	private IPath[] paths;
	private boolean[] pathWithSubFolders;
	private int pathsCount;

	private IPath[] enclosingProjectsAndJars;

	public TypeJavaSearchScope() {
		this.initialize();

		// disabled for now as this could be expensive
		// JavaModelManager.getJavaModelManager().rememberScope(this);
	}

	private void addEnclosingProjectOrJar(IPath path) {
		int length = this.enclosingProjectsAndJars.length;
		for (int i = 0; i < length; i++) {
			if (this.enclosingProjectsAndJars[i].equals(path))
				return;
		}
		System.arraycopy(this.enclosingProjectsAndJars, 0, this.enclosingProjectsAndJars = new IPath[length + 1], 0, length);
		this.enclosingProjectsAndJars[length] = path;
	}

	/**
	 * Method addProject. This method adds all the classpath entries for the
	 * current project to the search scope.
	 * 
	 * @param javaProject
	 * @param includesPrereqProjects
	 * @param visitedProjects
	 * @throws JavaModelException
	 */
	public void addProject(IJavaProject javaProject, boolean includesPrereqProjects, HashSet visitedProjects) throws JavaModelException {
		IProject project = javaProject.getProject();
		if (!project.isAccessible() || !visitedProjects.add(project))
			return;

		this.addEnclosingProjectOrJar(project.getFullPath());

		IClasspathEntry[] entries = javaProject.getResolvedClasspath(true);
		IJavaModel model = javaProject.getJavaModel();
		for (int i = 0, length = entries.length; i < length; i++) {
			IClasspathEntry entry = entries[i];
			switch (entry.getEntryKind()) {
				case IClasspathEntry.CPE_LIBRARY :
					IPath path = entry.getPath();
					this.add(path, true);
					this.addEnclosingProjectOrJar(path);
					break;
				case IClasspathEntry.CPE_PROJECT :
					if (includesPrereqProjects) {
						this.add(model.getJavaProject(entry.getPath().lastSegment()), true, visitedProjects);
					}
					break;
				case IClasspathEntry.CPE_SOURCE :
					this.add(entry.getPath(), true);
					break;
			}
		}
	}

	/**
	 * Method add. This method filters out all the classpath entries of the
	 * project which are not exported.
	 * 
	 * @param javaProject
	 * @param includesPrereqProjects
	 * @param visitedProjects
	 * @throws JavaModelException
	 */
	public void add(IJavaProject javaProject, boolean includesPrereqProjects, HashSet visitedProjects) throws JavaModelException {
		IProject project = javaProject.getProject();
		if (!project.isAccessible() || !visitedProjects.add(project))
			return;

		this.addEnclosingProjectOrJar(project.getFullPath());

		IClasspathEntry[] entries = javaProject.getResolvedClasspath(true);
		IJavaModel model = javaProject.getJavaModel();
		for (int i = 0, length = entries.length; i < length; i++) {
			IClasspathEntry entry = entries[i];
			if (includeExportedClassPathEntriesOnly()) {
				if (!entry.isExported() && entry.getEntryKind() != IClasspathEntry.CPE_SOURCE)
					continue;
			}
			switch (entry.getEntryKind()) {
				case IClasspathEntry.CPE_LIBRARY :
					IPath path = entry.getPath();
					this.add(path, true);
					this.addEnclosingProjectOrJar(path);
					break;
				case IClasspathEntry.CPE_PROJECT :
					if (includesPrereqProjects) {
						this.add(model.getJavaProject(entry.getPath().lastSegment()), true, visitedProjects);
					}
					break;
				case IClasspathEntry.CPE_SOURCE :
					this.add(entry.getPath(), true);
					break;
			}
		}
	}
	public void add(IJavaElement element) throws JavaModelException {
		IPackageFragmentRoot root = null;
		switch (element.getElementType()) {
			case IJavaElement.JAVA_MODEL :
				// a workspace sope should be used
				break;
			case IJavaElement.JAVA_PROJECT :
				this.add((IJavaProject) element, true, new HashSet(2));
				break;
			case IJavaElement.PACKAGE_FRAGMENT_ROOT :
				root = (IPackageFragmentRoot) element;
				this.add(root.getPath(), true);
				break;
			case IJavaElement.PACKAGE_FRAGMENT :
				root = (IPackageFragmentRoot) element.getParent();
				if (root.isArchive()) {
					this.add(root.getPath().append(new Path(element.getElementName().replace('.', '/'))), false);
				} else {
					IResource resource = element.getUnderlyingResource();
					if (resource != null && resource.isAccessible()) {
						this.add(resource.getFullPath(), false);
					}
				}
				break;
			default :
				// remember sub-cu (or sub-class file) java elements
				if (element instanceof IMember) {
					if (this.elements == null) {
						this.elements = new ArrayList();
					}
					this.elements.add(element);
				}
				this.add(this.fullPath(element), true);

				// find package fragment root including this java element
				IJavaElement parent = element.getParent();
				while (parent != null && !(parent instanceof IPackageFragmentRoot)) {
					parent = parent.getParent();
				}
				if (parent instanceof IPackageFragmentRoot) {
					root = (IPackageFragmentRoot) parent;
				}
		}

		if (root != null) {
			if (root.getKind() == IPackageFragmentRoot.K_BINARY) {
				this.addEnclosingProjectOrJar(root.getPath());
			} else {
				this.addEnclosingProjectOrJar(root.getJavaProject().getProject().getFullPath());
			}
		}
	}

	/**
	 * Adds the given path to this search scope. Remember if subfolders need to
	 * be included as well.
	 */
	private void add(IPath path, boolean withSubFolders) {
		if (this.paths.length == this.pathsCount) {
			System.arraycopy(this.paths, 0, this.paths = new IPath[this.pathsCount * 2], 0, this.pathsCount);
			System.arraycopy(this.pathWithSubFolders, 0, this.pathWithSubFolders = new boolean[this.pathsCount * 2], 0, this.pathsCount);
		}
		this.paths[this.pathsCount] = path;
		this.pathWithSubFolders[this.pathsCount++] = withSubFolders;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IJavaSearchScope#encloses(String)
	 */
	public boolean encloses(String resourcePathString) {
		IPath resourcePath;
		int separatorIndex = resourcePathString.indexOf(JAR_FILE_ENTRY_SEPARATOR);
		if (separatorIndex != -1) {
			resourcePath = new Path(resourcePathString.substring(0, separatorIndex)).append(new Path(resourcePathString.substring(separatorIndex + 1)));
		} else {
			resourcePath = new Path(resourcePathString);
		}
		return this.encloses(resourcePath);
	}

	/**
	 * Returns whether this search scope encloses the given path.
	 */
	private boolean encloses(IPath path) {
		for (int i = 0; i < this.pathsCount; i++) {
			if (this.pathWithSubFolders[i]) {
				if (this.paths[i].isPrefixOf(path)) {
					return true;
				}
			} else {
				IPath scopePath = this.paths[i];
				if (scopePath.isPrefixOf(path) && (scopePath.segmentCount() == path.segmentCount() - 1)) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IJavaSearchScope#encloses(IJavaElement)
	 */
	public boolean encloses(IJavaElement element) {
		if (this.elements != null) {
			for (int i = 0, length = this.elements.size(); i < length; i++) {
				IJavaElement scopeElement = (IJavaElement) this.elements.get(i);
				IJavaElement searchedElement = element;
				while (searchedElement != null) {
					if (searchedElement.equals(scopeElement)) {
						return true;
					} 
					searchedElement = searchedElement.getParent();
				}
			}
			return false;
		}
		return this.encloses(this.fullPath(element));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IJavaSearchScope#enclosingProjectsAndJars()
	 */
	public IPath[] enclosingProjectsAndJars() {
		return this.enclosingProjectsAndJars;
	}
	private IPath fullPath(IJavaElement element) {
		if (element instanceof IPackageFragmentRoot) {
			return ((IPackageFragmentRoot) element).getPath();
		} 
		IJavaElement parent = element.getParent();
		IPath parentPath = parent == null ? null : this.fullPath(parent);
		IPath childPath;
		if (element instanceof IPackageFragment) {
			childPath = new Path(element.getElementName().replace('.', '/'));
		} else if (element instanceof IOpenable) {
			childPath = new Path(element.getElementName());
		} else {
			return parentPath;
		}
		return parentPath == null ? childPath : parentPath.append(childPath);
	}

	protected void initialize() {
		this.paths = new IPath[1];
		this.pathWithSubFolders = new boolean[1];
		this.pathsCount = 0;
		this.enclosingProjectsAndJars = new IPath[0];
	}
	/**
	 * Gets the includeExportedClassPathEntriesOnly.
	 * 
	 * @return Returns a boolean
	 */
	public boolean includeExportedClassPathEntriesOnly() {
		return includeExportedClassPathEntriesOnly;
	}

	/**
	 * Sets the includeExportedClassPathEntriesOnly.
	 * 
	 * @param includeExportedClassPathEntriesOnly
	 *            The includeExportedClassPathEntriesOnly to set
	 */
	public void setIncludeExportedClassPathEntriesOnly(boolean includeExportedClassPathEntriesOnly) {
		this.includeExportedClassPathEntriesOnly = includeExportedClassPathEntriesOnly;
	}
	/**
	 * @see IJavaSearchScope#includesBinaries()
	 * @deprecated
	 */
	public boolean includesBinaries() {
		return true;
	}

	/**
	 * @see IJavaSearchScope#includesClasspaths()
	 * @deprecated
	 */
	public boolean includesClasspaths() {
		return true;
	}

	/**
	 * @see IJavaSearchScope#setIncludesBinaries(boolean)
	 * @deprecated
	 */
	public void setIncludesBinaries(boolean includesBinaries) {
		//Default nothing
	}

	/**
	 * @see IJavaSearchScope#setIncludesClasspaths(boolean)
	 * @deprecated
	 */
	public void setIncludesClasspaths(boolean includesClasspaths) {
		//Default nothing
	}

}
