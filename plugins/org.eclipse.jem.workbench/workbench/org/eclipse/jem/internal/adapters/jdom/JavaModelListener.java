/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.adapters.jdom;
/*
 *  $RCSfile: JavaModelListener.java,v $
 *  $Revision: 1.5 $  $Date: 2004/08/27 15:35:09 $ 
 */

import java.util.*;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.*;

/**
 * Insert the type's description here.
 * Creation date: (10/31/2000 1:13:12 PM)
 * @author: Administrator
 */
public abstract class JavaModelListener implements IElementChangedListener {
	
/**
 * JavaModelListener constructor comment.
 */
public JavaModelListener() {
	this(ElementChangedEvent.POST_CHANGE);
}

public JavaModelListener(int eventsToListen) {
	JavaCore.addElementChangedListener(this, eventsToListen);
}
/**
 * One or more attributes of one or more elements maintained by
 * the Java model have changed. The specific details of the change
 * are described by the given <code>ElementChangedEvent</code>.
 *
 * @see ElementChangedEvent
 */
public void elementChanged(ElementChangedEvent event) {
	processDelta((IJavaElementDelta) event.getSource());
}
/**
 * Generically dispatch the children of the delta.
 *
 */
protected void processChildren(IJavaElement element, IJavaElementDelta delta) {
	IJavaElementDelta[] children = delta.getAffectedChildren();
	for (int i = 0; i < children.length; i++) {
		processDelta(children[i]);
	}
}
/**
 * Source context has been changed.
 * Creation date: (8/17/2001 3:58:31 PM)
 * @param param org.eclipse.jdt.core.IJavaElementDelta
 */
protected void processContentChanged(IJavaElementDelta delta) {
	  // override to implement specific behavior
}
/**
 * Dispatch the detailed handling of an element changed event.
 *
 * @see ElementChangedEvent
 */
public void processDelta(IJavaElementDelta delta) {
	IJavaElement element = delta.getElement();
	
	switch (element.getElementType()) {
		case IJavaElement.JAVA_MODEL :
			processJavaElementChanged((IJavaModel) element, delta);
			break;
		case IJavaElement.JAVA_PROJECT :
			processJavaElementChanged((IJavaProject) element, delta);
			break;
		case IJavaElement.PACKAGE_FRAGMENT_ROOT :
			processJavaElementChanged((IPackageFragmentRoot) element, delta);
			break;
		case IJavaElement.PACKAGE_FRAGMENT :
			processJavaElementChanged((IPackageFragment) element, delta);
			break;
		case IJavaElement.COMPILATION_UNIT :
			processJavaElementChanged((ICompilationUnit) element, delta);
			processContentChanged(delta) ;   
			break;
		case IJavaElement.CLASS_FILE :
			processJavaElementChanged((IClassFile) element, delta);
			break;
		case IJavaElement.TYPE :
			processJavaElementChanged((IType) element, delta);
			break;
		// Note: if we are to update the Method/Field adapters, we should process the
		//       IJavaElement.METHOD and IJavaElement.FIELD 
	}	
}
/**
 * Handle the change for a single element, children will be handled separately.
 *
 */
protected void processJavaElementChanged(IClassFile element, IJavaElementDelta delta) {
	// override to implement specific behavior
}
/**
 * Handle the change for a single element, children will be handled separately.
 *
 */
protected void processJavaElementChanged(ICompilationUnit element, IJavaElementDelta delta) {
	processChildren(element, delta);
}
/**
 * Handle the change for a single element, children will be handled separately.
 *
 */
protected void processJavaElementChanged(IJavaModel element, IJavaElementDelta delta) {
	processChildren(element, delta);
}
/**
 * Handle the change for a single element, children will be handled separately.
 *
 */
protected void processJavaElementChanged(IJavaProject element, IJavaElementDelta delta) {
	processChildren(element, delta);
}
/**
 * Handle the change for a single element, children will be handled separately.
 *
 */
protected void processJavaElementChanged(IPackageFragment element, IJavaElementDelta delta) {
	processChildren(element, delta);
}
/**
 * Handle the change for a single element, children will be handled separately.
 *
 */
protected void processJavaElementChanged(IPackageFragmentRoot element, IJavaElementDelta delta) {
	processChildren(element, delta);
}
/**
 * Handle the change for a single element, children will be handled separately.
 *
 */
protected void processJavaElementChanged(IType element, IJavaElementDelta delta) {
	// override to implement specific behavior
}

protected boolean isClassPathChange(IJavaElementDelta delta) {
	int flags = delta.getFlags();
	return (delta.getKind() == IJavaElementDelta.CHANGED && ((flags & IJavaElementDelta.F_ADDED_TO_CLASSPATH) != 0) || ((flags & IJavaElementDelta.F_REMOVED_FROM_CLASSPATH) != 0) || ((flags & IJavaElementDelta.F_REORDER) != 0));
}

/**
 * Method isClasspathResourceChange.
 * @param delta
 * @return boolean
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
 * Is this path also a classpath change type of resource. If so, return true.
 * Subclasses may override. Default is false.
 * @param path
 * @return
 * 
 * @since 1.0.0
 */
protected boolean isAlsoClasspathChange(IPath path) {
	return false;
}

/**
 * This method will check to see if a <code>javaProject</code> is a project in the
 * classpath of the adapterFactory java project.
 */
protected boolean isInClasspath(IJavaProject javaProject) {
	IJavaProject adapterJavaProject = getJavaProject();
	if (javaProject.equals(adapterJavaProject))
		return true;
	return isInClasspath(javaProject, adapterJavaProject, true, new HashSet());
}

/**
 * Get the java project that we are interested in.
 * @return
 * 
 * @since 1.0.0
 */
protected abstract IJavaProject getJavaProject();

/*
 * test to see if the testProject is in the classpath (including from any referenced projects) of the target project.
 * Keep track of those already visited so as not to visit again.
 * TODO This should be made private.
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
 * See if the testProject is in the classpath of any of the list of projects or in any project that an entry
 * in the list may of visited.
 * TODO This should be made private.
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

protected IJavaProject getJavaProject(IClasspathEntry entry) {
	IProject proj = getWorkspaceRoot().getProject(entry.getPath().segment(0));
	if (proj != null)
		return (IJavaProject) JavaCore.create(proj);
	return null;
}

protected IWorkspaceRoot getWorkspaceRoot() {
	return ResourcesPlugin.getWorkspace().getRoot();
}
}
