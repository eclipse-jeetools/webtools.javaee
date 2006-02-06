/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.workbench.utility;

/*
 * $RCSfile: JavaModelListener.java,v $ $Revision: 1.1 $ $Date: 2006/02/06 23:37:59 $
 */

import java.util.*;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.*;

/**
 * An element change listener to listen for Java Model changes. It breaks the notification up into individual method calls to make it easier to walk
 * the notification tree.
 * 
 * @since 1.2.0
 */
public abstract class JavaModelListener implements IElementChangedListener {

	/**
	 * Construct with listening only for {@link ElementChangedEvent#POST_CHANGE} events.
	 * 
	 * 
	 * @since 1.2.0
	 */
	public JavaModelListener() {
		this(ElementChangedEvent.POST_CHANGE);
	}

	/**
	 * Construct with supplying the type of events to listen for.
	 * 
	 * @param eventsToListen
	 *            or'd together event listening types. See {@link ElementChangedEvent} for the types.
	 * 
	 * @since 1.2.0
	 */
	public JavaModelListener(int eventsToListen) {
		JavaCore.addElementChangedListener(this, eventsToListen);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jdt.core.IElementChangedListener#elementChanged(org.eclipse.jdt.core.ElementChangedEvent)
	 */
	public void elementChanged(ElementChangedEvent event) {
		processDelta((IJavaElementDelta) event.getSource());
	}

	/**
	 * Generally dispatch the children of the delta. Normally this method should not be overridden.
	 * 
	 * @param element
	 * @param delta
	 * 
	 * @since 1.2.0
	 */
	protected void processChildren(IJavaElement element, IJavaElementDelta delta) {
		IJavaElementDelta[] children = delta.getAffectedChildren();
		for (int i = 0; i < children.length; i++) {
			processDelta(children[i]);
		}
	}

	/**
	 * Source content has changed. The default is to do nothing. Subclasses may override to perform own functions.
	 * 
	 * @param delta
	 * 
	 * @since 1.2.0
	 */
	protected void processContentChanged(IJavaElementDelta delta) {
	}

	/**
	 * Dispatch the java element delta. This method should normally not be overridden.
	 * 
	 * @param delta
	 * 
	 * @since 1.2.0
	 */
	public void processDelta(IJavaElementDelta delta) {
		IJavaElement element = delta.getElement();

		switch (element.getElementType()) {
			case IJavaElement.JAVA_MODEL:
				processJavaElementChanged((IJavaModel) element, delta);
				break;
			case IJavaElement.JAVA_PROJECT:
				processJavaElementChanged((IJavaProject) element, delta);
				break;
			case IJavaElement.PACKAGE_FRAGMENT_ROOT:
				processJavaElementChanged((IPackageFragmentRoot) element, delta);
				break;
			case IJavaElement.PACKAGE_FRAGMENT:
				processJavaElementChanged((IPackageFragment) element, delta);
				break;
			case IJavaElement.COMPILATION_UNIT:
				processJavaElementChanged((ICompilationUnit) element, delta);
				processContentChanged(delta);
				break;
			case IJavaElement.CLASS_FILE:
				processJavaElementChanged((IClassFile) element, delta);
				break;
			case IJavaElement.TYPE:
				processJavaElementChanged((IType) element, delta);
				break;
			// Note: if we are to update the Method/Field adapters, we should process the
			// IJavaElement.METHOD and IJavaElement.FIELD
		}
	}

	/**
	 * Process the classfile changed event. The default is to do nothing. It will not walk any children of the delta either. Subclasses may override
	 * to perform their own functions.
	 * 
	 * @param element
	 * @param delta
	 * 
	 * @since 1.2.0
	 */
	protected void processJavaElementChanged(IClassFile element, IJavaElementDelta delta) {
	}

	/**
	 * Process the compilation unit changed event. The default is to process the children of the delta. Subclasses may override to perform their own
	 * functions. If they wish to walk the children they should then also call
	 * <code>super.processJavaElementChanged(ICompilationUnit, IJavaElementDelta)</code>.
	 * 
	 * @param element
	 * @param delta
	 * 
	 * @since 1.2.0
	 */
	protected void processJavaElementChanged(ICompilationUnit element, IJavaElementDelta delta) {
		processChildren(element, delta);
	}

	/**
	 * Process the java model changed event. The default is to process the children of the delta. Subclasses may override to perform their own
	 * functions. If they wish to walk the children they should then also call
	 * <code>super.processJavaElementChanged(IJavaModel, IJavaElementDelta)</code>.
	 * 
	 * @param element
	 * @param delta
	 * 
	 * @since 1.2.0
	 */
	protected void processJavaElementChanged(IJavaModel element, IJavaElementDelta delta) {
		processChildren(element, delta);
	}

	/**
	 * Process the java project changed event. The default is to process the children of the delta. Subclasses may override to perform their own
	 * functions. If they wish to walk the children they should then also call
	 * <code>super.processJavaElementChanged(IJavaProject, IJavaElementDelta)</code>.
	 * 
	 * @param element
	 * @param delta
	 * 
	 * @since 1.2.0
	 */
	protected void processJavaElementChanged(IJavaProject element, IJavaElementDelta delta) {
		processChildren(element, delta);
	}

	/**
	 * Process the package fragment changed event. The default is to process the children of the delta. Subclasses may override to perform their own
	 * functions. If they wish to walk the children they should then also call
	 * <code>super.processJavaElementChanged(IPackageFragment, IJavaElementDelta)</code>.
	 * 
	 * @param element
	 * @param delta
	 * 
	 * @since 1.2.0
	 */
	protected void processJavaElementChanged(IPackageFragment element, IJavaElementDelta delta) {
		processChildren(element, delta);
	}

	/**
	 * Process the package fragment root changed event. The default is to process the children of the delta. Subclasses may override to perform their
	 * own functions. If they wish to walk the children they should then also call
	 * <code>super.processJavaElementChanged(IPackageFragmentRoot, IJavaElementDelta)</code>.
	 * 
	 * @param element
	 * @param delta
	 * 
	 * @since 1.2.0
	 */
	protected void processJavaElementChanged(IPackageFragmentRoot element, IJavaElementDelta delta) {
		processChildren(element, delta);
	}

	/**
	 * Process the IType changed event. The default is to do nothing. It will not walk any children of the delta either. Subclasses may override to
	 * perform their own functions.
	 * 
	 * @param element
	 * @param delta
	 * 
	 * @since 1.2.0
	 */
	protected void processJavaElementChanged(IType element, IJavaElementDelta delta) {
	}

	/**
	 * Answers whether this element delta is a classpath change. I.e. something added/removed/moved around for the classpath. This should only be
	 * called for a delta that is for an {@link IPackageFragmentRoot}. Should be called from an override of
	 * {@link #processJavaElementChanged(IPackageFragmentRoot, IJavaElementDelta)}
	 * 
	 * @param delta
	 * @return <code>true</code> if it is classpath change or <code>false</code> if not.
	 * 
	 * @since 1.2.0
	 */
	protected boolean isClassPathChange(IJavaElementDelta delta) {
		int flags = delta.getFlags();
		return (delta.getKind() == IJavaElementDelta.CHANGED && ((flags & IJavaElementDelta.F_ADDED_TO_CLASSPATH) != 0)
				|| ((flags & IJavaElementDelta.F_REMOVED_FROM_CLASSPATH) != 0) || ((flags & IJavaElementDelta.F_REORDER) != 0));
	}

	/**
	 * Answer whether the change is this is a raw classpath change, or if a file that is in the root of the project was changed that also can mean a
	 * raw classpath change. This must only be called for a delta that is for an {@link IJavaProject}.
	 * <p>
	 * See {@link IJavaElementDelta.#F_CLASSPATH_CHANGED} and {@link #isAlsoClasspathChange(IPath)} for details.
	 * 
	 * @param delta
	 * @return
	 * 
	 * @since 1.2.0
	 */
	protected boolean isClasspathResourceChange(IJavaElementDelta delta) {
		if ((delta.getFlags() & IJavaElementDelta.F_CLASSPATH_CHANGED) != 0)
			return true;
		IResourceDelta[] resources = delta.getResourceDeltas();
		if (resources == null)
			return false;
		IPath path = null;
		for (int i = 0; i < resources.length; i++) {
			if (resources[i].getKind() == IResourceDelta.CHANGED) {
				path = resources[i].getProjectRelativePath();
				if (isAlsoClasspathChange(path))
					return true;
			}
		}
		return false;
	}

	/**
	 * Answers whether the file specified by the path is one that when changed can result in a classpath change. This is called from
	 * {@link #isClasspathResourceChange(IJavaElementDelta)}. The file must be in the root of the project. The default is <code>false</code>.
	 * Subclasses may override.
	 * 
	 * @param path
	 * @return <code>true</code> if this file being changed would result in a classpath change, <code>false</code> if not.
	 * 
	 * @since 1.2.0
	 */
	protected boolean isAlsoClasspathChange(IPath path) {
		return false;
	}

	/**
	 * Answers whether the given java project is in the classpath (including recursive). This currently will not walk through containers (such as the
	 * PDE container). Only through the direct and recursive project references. The java project is determined by subclasses, see
	 * {@link #getJavaProject()}.
	 * 
	 * @param javaProject
	 * @return <code>true</code> if project is in classpath or <code>false</code> if not.
	 * 
	 * @since 1.2.0
	 */
	protected boolean isInClasspath(IJavaProject javaProject) {
		IJavaProject listenerJavaProject = getJavaProject();
		if (javaProject.equals(listenerJavaProject))
			return true;
		return isInClasspath(javaProject, listenerJavaProject, true, new HashSet());
	}

	/**
	 * The java project for this listener. Subclasses must provide a java project.
	 * 
	 * @return the java project. <code>null</code> is not valid.
	 * 
	 * @since 1.2.0
	 */
	protected abstract IJavaProject getJavaProject();

	/*
	 * test to see if the testProject is in the classpath (including from any referenced projects) of the target project. Keep track of those already
	 * visited so as not to visit again. Too late to make private. But it should not be overridden.
	 */
	protected boolean isInClasspath(IJavaProject testProject, IJavaProject targetProject, boolean isFirstLevel, Set visited) {
		if (visited.contains(targetProject))
			return false;
		visited.add(targetProject);
		IClasspathEntry[] entries = null;
		try {
			entries = targetProject.getRawClasspath();
		} catch (JavaModelException e) {
			return false;
		}
		IClasspathEntry entry, resEntry;
		IJavaProject proj = null;
		List projects = null;
		for (int i = 0; i < entries.length; i++) {
			entry = entries[i];
			if (entry.getEntryKind() == IClasspathEntry.CPE_PROJECT) {
				resEntry = JavaCore.getResolvedClasspathEntry(entry);
				proj = getJavaProject(entry);
				if (isFirstLevel || resEntry.isExported()) {
					if (proj.equals(testProject))
						return true;
					else {
						if (projects == null)
							projects = new ArrayList();
						projects.add(proj);
					}
				}
			}
		}
		return isInClasspath(testProject, projects, false, visited);
	}

	/*
	 * See if the testProject is in the classpath of any of the list of projects or in any project that an entry in the list may of visited. Too late
	 * to make private. But it should not be overridden.
	 */
	protected boolean isInClasspath(IJavaProject testProject, List someJavaProjects, boolean isFirstLevel, Set visited) {
		if (someJavaProjects == null)
			return false;
		int size = someJavaProjects.size();
		IJavaProject javaProj = null;
		for (int i = 0; i < size; i++) {
			javaProj = (IJavaProject) someJavaProjects.get(i);
			return isInClasspath(testProject, javaProj, isFirstLevel, visited);
		}
		return false;
	}

	/**
	 * Get the java project that classpath entry is for. This should only be called on classpath entries of type {@link IClasspathEntry#CPE_PROJECT}
	 * 
	 * @param entry
	 *            classpath entry of type {@link IClasspathEntry#CPE_PROJECT}.
	 * @return the java project for this entry. The project might not actually exist. That is not verified.
	 * 
	 * @since 1.2.0
	 */
	protected IJavaProject getJavaProject(IClasspathEntry entry) {
		IProject proj = getWorkspaceRoot().getProject(entry.getPath().segment(0));
		if (proj != null)
			return JavaCore.create(proj);
		return null;
	}

	/**
	 * Get the workspace root. A utility method.
	 * @return the workspace root.
	 * 
	 * @since 1.2.0
	 */
	protected IWorkspaceRoot getWorkspaceRoot() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}
}
