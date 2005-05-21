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
package org.eclipse.jst.j2ee.internal.ejb.project;



import java.util.Vector;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;

/**
 * For accessing the EJBFileCache from within eclipse, this utility class should be used. These
 * methods cannot be put on the EJBFileCache class itself because that cache must be run outside of
 * the eclipse environment, so it does not have access to the eclipse classes.
 */
public class EJBFileCacheUtil {
	private static final IContainer[] NO_CONTAINERS = new IContainer[0];

	public static boolean endsIn(IPath relPath, IPath fullPath) {
		if ((relPath == null) || (fullPath == null) || (relPath == Path.EMPTY) || (fullPath == Path.EMPTY)) {
			return false;
		}
		String[] fullSeg = fullPath.segments();
		String[] relSeg = relPath.segments();
		if (relSeg.length > fullSeg.length) {
			// If the relPath is longer than the full path, then
			// the relPath cannot be a child of the full path.
			return false;
		}

		for (int fullIdx = (fullSeg.length - 1), relIdx = (relSeg.length - 1); (fullIdx >= 0) && (relIdx >= 0); fullIdx--, relIdx--) {
			if (!(fullSeg[fullIdx].equals(relSeg[relIdx]))) {
				return false;
			}
		}

		return true;
	}

	public static IResource getAbsoluteChild(IPath relPath, IContainer container) {
		Vector children = new Vector();
		getAllChildren(container, children);
		for (int c = 0; c < children.size(); c++) {
			IResource child = (IResource) children.elementAt(c);
			if (endsIn(relPath, child.getFullPath())) {
				// Either relPath is the end of the IResource's full path,
				// or relPath == IResource exactly (i.e., absolute path
				// masquerading as a relative path).
				return child;
			}
		}
		return null;
	}

	/**
	 * Given an IResource, and an empty Vector, put a copy of all of the IResource's children in the
	 * Vector (and also a copy of the IResource itself).
	 * 
	 * resource and allChildren must not be null.
	 */
	public static void getAllChildren(IResource resource, Vector allChildren) {
		if (!resource.exists()) {
			return;
		}

		allChildren.addElement(resource);
		if (resource instanceof IContainer) {
			try {
				IResource[] children = ((IContainer) resource).members();
				for (int i = 0; i < children.length; i++) {
					getAllChildren(children[i], allChildren);
				}
			} catch (CoreException exc) {
				Logger.getLogger().logWarning(exc);
			}
		}
	}

	/**
	 * Given an IPath, if the IPath is absolute, and is a part of the IContainer, return an IPath
	 * which is relative to the container. If the IPath is not part of the IContainer, return null.
	 */
	private static IPath getContainerRelativePath(IPath path, IContainer container) {
		if ((path == null) || (container == null)) {
			return null;
		}

		if (path.isAbsolute()) {
			IPath containerPath = container.getFullPath();
			int matchingSegments = containerPath.matchingFirstSegments(path);
			if (matchingSegments == containerPath.segmentCount()) {
				return path.removeFirstSegments(matchingSegments);
			}
		}

		return null;
	}

	/**
	 * If the IProject is associated with an EJBNatureRuntime, return the IJavaProject which
	 * represents it.
	 */
	public static IJavaProject getJavaProject(IProject project) {
		EJBNatureRuntime ejbNature = null;
		if (project == null) {
			return null;
		}

		try {
			if (!(project.hasNature(IEJBNatureConstants.NATURE_ID))) {
				//
				// Catch-22.
				//
				// When an EJBProject is created for the first time, the IProject
				// needs to have an EJBNatureRuntime configured on it. The call to
				// create an EJBNatureRuntime triggers a build. The build triggers
				// a call to each Validator's validate method.
				//
				// In order to load the information from EJBModel.ejbxmi and Map.mapxmi,
				// the EJB Validator needs the EJBNatureRuntime associated with a project.
				// However, since the given project is in the process of creating an
				// EJBNatureRuntime, it won't have a nature associated with it until
				// the build is complete. Thus, the call to getRuntime(getProject()) results
				// in a CoreException (complaining that the project doesn't have a nature).
				//
				// For now, until a better way of handling this is found, I'll just
				// check that the project has an EJBNature configured on it. If not,
				// return, because we can't validate.
				return null;
			}
			ejbNature = EJBNatureRuntime.getRuntime(project);
			if (ejbNature == null) {
				return null;
			}
		} catch (CoreException exc) {
			return null;
		}
		return JemProjectUtilities.getJavaProject(project);
	}

	/**
	 * Return the folders (or project) which contain the .java source files.
	 */
	public static IContainer[] getProjectOutputContainers(IProject project) {
		if (project == null) {
			return NO_CONTAINERS;
		}

		IJavaProject jp = getJavaProject(project);
		if (jp == null) {
			return NO_CONTAINERS;
		}

		return getProjectOutputContainers(jp);
	}

	/**
	 * Return the folders (or project) which contains the .class output.
	 */
	public static IContainer[] getProjectOutputContainers(IJavaProject javaProject) {
		if (javaProject == null) {
			return NO_CONTAINERS;
		}

		IProject project = javaProject.getProject();

		IPath outputPath = null;
		try {
			outputPath = javaProject.getOutputLocation();
		} catch (JavaModelException exc) {
			return NO_CONTAINERS;
		}

		if (outputPath == null) {
			return NO_CONTAINERS;
		}

		IPath relOutputPath = getContainerRelativePath(outputPath, project);
		if (relOutputPath == null) {
			return NO_CONTAINERS;
		}

		IResource outputResource = project.findMember(relOutputPath);
		if (!(outputResource instanceof IContainer)) {
			return NO_CONTAINERS;
		}

		return new IContainer[]{(IContainer) outputResource};
	}

	/**
	 * Return the folders (or project) which contain the .java source files.
	 */
	public static IContainer[] getProjectSourceContainers(IProject project) {
		if (project == null) {
			return NO_CONTAINERS;
		}

		IJavaProject jp = getJavaProject(project);
		if (jp == null) {
			return NO_CONTAINERS;
		}

		return getProjectSourceContainers(jp);
	}

	/**
	 * Return the folders (or project) which contain the .java source files.
	 */
	public static IContainer[] getProjectSourceContainers(IJavaProject javaProject) {
		if (javaProject == null) {
			return NO_CONTAINERS;
		}
		IProject project = javaProject.getProject();

		IClasspathEntry[] classpath = null;
		try {
			classpath = javaProject.getResolvedClasspath(true); // true means ignore unresolved
			// (missing) variables, instead of
			// throwing an exception
		} catch (JavaModelException exc) {
			return NO_CONTAINERS;
		}

		if (classpath == null) {
			return NO_CONTAINERS;
		}

		// Traverse the classpath, and calculate a list of just the
		// IFolders and IProjects (i.e., IContainers) which contain source
		Vector icontainers = new Vector(); // the list of IContainers
		for (int i = 0; i < classpath.length; i++) {
			IClasspathEntry entry = classpath[i];
			if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				IPath entryPath = entry.getPath();
				IPath relEntryPath = getContainerRelativePath(entryPath, project);
				if (relEntryPath == null) {
					// classpath entry refers to something which doesn't exist
					continue;
				}

				IResource outputResource = project.findMember(relEntryPath);
				if (outputResource == null) {
					// classpath entry refers to something which doesn't exist
					continue;
				}

				if (outputResource instanceof IContainer) {
					icontainers.addElement(outputResource);
				}
			}
		}

		//  following line causes a ClassCastException, so construct an array of IContainers
		// explicitly
		//	return (IContainer[])icontainers.toArray();
		IContainer[] containers = new IContainer[icontainers.size()];
		for (int c = 0; c < icontainers.size(); c++) {
			containers[c] = (IContainer) icontainers.get(c);
		}
		return containers;
	}
}