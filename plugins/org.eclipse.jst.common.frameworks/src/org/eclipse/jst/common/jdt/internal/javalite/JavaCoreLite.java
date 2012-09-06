/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.jdt.internal.javalite;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IAccessRule;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IElementChangedListener;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.core.JavaModelManager;

public final class JavaCoreLite {

	public static String NATURE_ID = JavaCore.NATURE_ID;

	private static Object lock = new Object();

	private static JavaCoreLite INSTANCE = null;

	private class JavaCoreLiteListener implements IElementChangedListener, IResourceChangeListener {
		public void elementChanged(ElementChangedEvent event) {
			IJavaElementDelta delta = event.getDelta();
			IJavaElementDelta[] children = delta.getAffectedChildren();
			for (int i = 0; i < children.length; i++) {
				processDelta(children[i]);
			}
		}

		private void processDelta(IJavaElementDelta delta) {
			int flags = delta.getFlags();
			if ((flags & IJavaElementDelta.F_CLOSED) != 0) {
				IJavaElement element = delta.getElement();
				IJavaProject javaProject = (IJavaProject) element;
				IProject project = javaProject.getProject();
				synchronized (lock) {
					initializedJavaProjects.remove(project);
					javaProjectLiteCache.remove(project);
				}
			} else if ((flags & IJavaElementDelta.F_CLASSPATH_CHANGED) != 0) {
				IJavaElement element = delta.getElement();
				if (element.getElementType() == IJavaElement.JAVA_PROJECT) {
					IJavaProject javaProject = (IJavaProject) element;
					IProject project = javaProject.getProject();
					synchronized (lock) {
						if (!initializedJavaProjects.contains(project) && javaProjectLiteCache.containsKey(project)) {
							JavaProjectLite javaProjectLite = javaProjectLiteCache.get(project);
							javaProjectLite.flushClasspath();
						}
					}
				}
			} else if ((flags & IJavaElementDelta.F_RESOLVED_CLASSPATH_CHANGED) != 0) {
				IJavaElement element = delta.getElement();
				if (element.getElementType() == IJavaElement.JAVA_PROJECT) {
					IJavaProject javaProject = (IJavaProject) element;
					IProject project = javaProject.getProject();
					synchronized (lock) {
						if (!initializedJavaProjects.contains(project)) {
							initializedJavaProjects.add(project);
							if (javaProjectLiteCache.containsKey(project)) {
								JavaProjectLite javaProjectLite = javaProjectLiteCache.get(project);
								javaProjectLite.markJavaProjectInitialized();
							}
						}
					}
				}
			}
		}

		public void resourceChanged(IResourceChangeEvent event) {
			switch (event.getType()) {
			case IResourceChangeEvent.PRE_CLOSE:
			case IResourceChangeEvent.PRE_DELETE:
				IResource resource = event.getResource();
				if (resource.getType() == IResource.PROJECT) {
					IProject project = (IProject) resource;
					synchronized (lock) {
						initializedJavaProjects.remove(project);
						javaProjectLiteCache.remove(project);
					}
				}
			}
		}
	}

	private Set<IProject> initializedJavaProjects = new HashSet<IProject>();
	private Map<IProject, JavaProjectLite> javaProjectLiteCache = new HashMap<IProject, JavaProjectLite>();
	private JavaCoreLiteListener listener = null;

	private static JavaCoreLite getInstance() {
		synchronized (lock) {
			if (INSTANCE == null) {
				INSTANCE = new JavaCoreLite();
			}
		}
		return INSTANCE;
	}

	private JavaCoreLite() {
		listener = new JavaCoreLiteListener();
		JavaCore.addElementChangedListener(listener);
		ResourcesPlugin.getWorkspace().addResourceChangeListener(listener, IResourceChangeEvent.PRE_CLOSE | IResourceChangeEvent.PRE_DELETE);
	}

	private IJavaProjectLite createImpl(IProject project) {
		synchronized (lock) {
			if (javaProjectLiteCache.containsKey(project)) {
				return javaProjectLiteCache.get(project);
			}
		}

		IJavaProject javaProject = JavaCore.create(project);
		if (javaProject != null) {
			boolean javaProjectInitialized = false;
			synchronized (lock) {
				javaProjectInitialized = initializedJavaProjects.contains(project);
			}
			if (!javaProjectInitialized) {
				if (isInitialized(javaProject)) {
					synchronized (lock) {
						initializedJavaProjects.add(project);
					}
					javaProjectInitialized = true;
				}
			}
			JavaProjectLite javaProjectLite = new JavaProjectLite(javaProject, javaProjectInitialized);
			synchronized (lock) {
				javaProjectLiteCache.put(project, javaProjectLite);
			}
			return javaProjectLite;
		}
		return null;
	}

	static boolean isInitialized(IJavaProject javaProject) {
		if (javaProject.isOpen()) {
			JavaModelManager.PerProjectInfo projectInfo = JavaModelManager.getJavaModelManager().getPerProjectInfo(javaProject.getProject(), false);
			if (projectInfo != null) {
				if (projectInfo.getResolvedClasspath() != null) {
					return true;
				}
			}
		}
		return false;
	}

	public static final IJavaProjectLite create(IProject project) {
		if (project == null) {
			throw new NullPointerException();
		}
		return getInstance().createImpl(project);
	}

	public static final IJavaProjectLite create(IJavaProject javaProject) {
		if (javaProject == null) {
			throw new NullPointerException();
		}
		return create(javaProject.getProject());
	}

	public static ClasspathContainerInitializer getClasspathContainerInitializer(String containerID) {
		return JavaCore.getClasspathContainerInitializer(containerID);
	}

	public static IClasspathEntry newProjectEntry(IPath path) {
		return JavaCore.newProjectEntry(path);
	}

	public static IClasspathEntry newProjectEntry(IPath path, boolean isExported) {
		return JavaCore.newProjectEntry(path, isExported);
	}

	public static IClasspathEntry newProjectEntry(IPath path, IAccessRule[] accessRules, boolean combineAccessRules, IClasspathAttribute[] extraAttributes, boolean isExported) {
		return JavaCore.newProjectEntry(path, accessRules, combineAccessRules, extraAttributes, isExported);
	}

	public static IClasspathEntry newLibraryEntry(IPath path, IPath sourceAttachmentPath, IPath sourceAttachmentRootPath) {
		return JavaCore.newLibraryEntry(path, sourceAttachmentPath, sourceAttachmentRootPath);
	}

	public static IClasspathEntry newLibraryEntry(IPath path, IPath sourceAttachmentPath, IPath sourceAttachmentRootPath, boolean isExported) {
		return JavaCore.newLibraryEntry(path, sourceAttachmentPath, sourceAttachmentRootPath, isExported);
	}

	public static IClasspathEntry newLibraryEntry(IPath path, IPath sourceAttachmentPath, IPath sourceAttachmentRootPath, IAccessRule[] accessRules, IClasspathAttribute[] extraAttributes, boolean isExported) {
		return JavaCore.newLibraryEntry(path, sourceAttachmentPath, sourceAttachmentRootPath, accessRules, extraAttributes, isExported);
	}
}
