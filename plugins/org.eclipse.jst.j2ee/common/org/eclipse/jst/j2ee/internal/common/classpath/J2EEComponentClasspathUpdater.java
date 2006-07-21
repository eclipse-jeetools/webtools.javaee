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
package org.eclipse.jst.j2ee.internal.common.classpath;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.jdt.internal.classpath.FlexibleProjectContainer;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.componentcore.util.EARVirtualComponent;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class J2EEComponentClasspathUpdater implements IResourceChangeListener, IResourceDeltaVisitor {

	private static J2EEComponentClasspathUpdater instance = null;

	private int pauseCount = 0;

	private Set moduleUpdatesRequired = new HashSet();

	private Set earUpdatesRequired = new HashSet();

	private IPath WEB_APP_LIBS_PATH = new Path("org.eclipse.jst.j2ee.internal.web.container");

	public static J2EEComponentClasspathUpdater getInstance() {
		if (instance == null) {
			init();
		}
		return instance;
	}

	public static void init() {
		if (instance == null) {
			instance = new J2EEComponentClasspathUpdater();
			ResourcesPlugin.getWorkspace().addResourceChangeListener(instance, IResourceChangeEvent.POST_CHANGE);
		}
	}
	
	/**
	 * Pauses updates; any caller of this method must ensure through a
	 * try/finally block that resumeUpdates is subsequently called.
	 */
	public void pauseUpdates() {
		synchronized (this) {
			pauseCount++;
		}
	}

	public void resumeUpdates() {
		try {
			Object[] earProjects = null;
			synchronized (this) {
				if (pauseCount == 1) {
					earProjects = earUpdatesRequired.toArray();
					earUpdatesRequired.clear();
				}
			}
			if (earProjects != null) {
				for (int i = 0; i < earProjects.length; i++) {
					updateEAR((IProject) earProjects[i]);
				}
			}
		} finally {
			Object[] projects = null;
			synchronized (this) {
				if (pauseCount > 0) {
					pauseCount--;
				}
				if (pauseCount > 0) {
					return;
				}
				projects = moduleUpdatesRequired.toArray();
				moduleUpdatesRequired.clear();
			}
			for (int i = 0; i < projects.length; i++) {
				updateModule((IProject) projects[i]);
			}
		}
	}

	public void queueUpdate(IProject project) {
		if (J2EEProjectUtilities.isEARProject(project)) {
			// TODO streamline the ear section so it only changes if the refs
			// have changed
			queueUpdateEAR(project);
		} else if (J2EEProjectUtilities.isApplicationClientProject(project) || J2EEProjectUtilities.isEJBProject(project) || J2EEProjectUtilities.isDynamicWebProject(project)
				|| J2EEProjectUtilities.isJCAProject(project) || J2EEProjectUtilities.isUtilityProject(project)) {
			// Hari: update the project only if the tree is not locked.
			if (false == ResourcesPlugin.getWorkspace().isTreeLocked())
				queueUpdateModule(project);
		}
	}

	public void queueUpdateModule(IProject project) {
		synchronized (this) {
			if (pauseCount > 0) {
				if (!moduleUpdatesRequired.contains(project)) {
					moduleUpdatesRequired.add(project);
				}
				return;
			}
		}
		updateModule(project);
	}

	public void queueUpdateEAR(IProject earProject) {
		synchronized (this) {
			if (pauseCount > 0) {
				if (!earUpdatesRequired.contains(earProject)) {
					earUpdatesRequired.add(earProject);
				}
				return;
			}
		}
		updateEAR(earProject);
	}

	private void updateEAR(IProject earProject) {
		EARArtifactEdit edit = null;
		try {
			edit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
			IVirtualReference[] refs = edit.getComponentReferences();
			IVirtualComponent comp = null;
			for (int i = 0; i < refs.length; i++) {
				comp = refs[i].getReferencedComponent();
				if (!comp.isBinary()) {
					queueUpdateModule(comp.getProject());
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	private void updateModule(final IProject project) {
		final IWorkspaceRunnable workspaceRunnable = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) {
				IClasspathContainer container = getWebAppLibrariesContainer(project, false);
				if (container != null && container instanceof FlexibleProjectContainer) {
					((FlexibleProjectContainer) container).refresh();
				}
				IProject[] earProjects = J2EEProjectUtilities.getReferencingEARProjects(project);
				if (earProjects.length == 0) {
					removeContainerFromModuleIfNecessary(project);
					return;
				}
				container = addContainerToModuleIfNecessary(project);
				if (container != null && container instanceof J2EEComponentClasspathContainer) {
					((J2EEComponentClasspathContainer) container).refresh();
				}
			}
		};
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		if (workspace.isTreeLocked()) {
			Runnable r = new Runnable() {
				public void run() {
					while (workspace.isTreeLocked()) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
						}
					}
					try {
						workspace.run(workspaceRunnable, new NullProgressMonitor());
					} catch (CoreException e) {
						Logger.getLogger().logError(e);
					}
				}
			};
			Thread t = new Thread(r);
			t.start();
		} else {
			try {
				workspaceRunnable.run(new NullProgressMonitor());
			} catch (CoreException e) {
				Logger.getLogger().logError(e);
			}
		}
	}

	public IClasspathContainer getWebAppLibrariesContainer(IProject webProject, boolean create) {
		IJavaProject jproj = JavaCore.create(webProject);
		IClasspathContainer container = null;
		IClasspathEntry entry = create ? null : getExistingContainer(jproj, WEB_APP_LIBS_PATH);
		if (entry != null || create) {
			try {
				container = JavaCore.getClasspathContainer(WEB_APP_LIBS_PATH, jproj);
			} catch (JavaModelException e) {
				Logger.getLogger().logError(e);
			}
		}
		return container;
	}

	private IClasspathContainer addContainerToModuleIfNecessary(IProject moduleProject) {
		IJavaProject jproj = JavaCore.create(moduleProject);
		IClasspathEntry entry = getExistingContainer(jproj, J2EEComponentClasspathContainer.CONTAINER_PATH);
		if (entry == null) {
			try {
				entry = JavaCore.newContainerEntry(J2EEComponentClasspathContainer.CONTAINER_PATH, true);
				addToClasspath(jproj, entry);
			} catch (CoreException e) {
				Logger.getLogger().logError(e);
			}
		}
		IClasspathContainer container = null;
		try {
			container = JavaCore.getClasspathContainer(J2EEComponentClasspathContainer.CONTAINER_PATH, jproj);
		} catch (JavaModelException e) {
			Logger.getLogger().logError(e);
		}
		return container;
	}

	private void removeContainerFromModuleIfNecessary(IProject moduleProject) {
		IJavaProject jproj = JavaCore.create(moduleProject);
		IClasspathEntry entry = getExistingContainer(jproj, J2EEComponentClasspathContainer.CONTAINER_PATH);
		if (entry != null) {
			try {
				removeFromClasspath(jproj, entry);
			} catch (CoreException e) {
				Logger.getLogger().logError(e);
			}
		}
	}

	private void addToClasspath(final IJavaProject jproj, final IClasspathEntry entry) throws CoreException {
		final IClasspathEntry[] current = jproj.getRawClasspath();
		final IClasspathEntry[] updated = new IClasspathEntry[current.length + 1];
		System.arraycopy(current, 0, updated, 0, current.length);
		updated[current.length] = entry;
		jproj.setRawClasspath(updated, null);
	}

	private void removeFromClasspath(final IJavaProject jproj, final IClasspathEntry entry) throws CoreException {
		final IClasspathEntry[] current = jproj.getRawClasspath();
		final IClasspathEntry[] updated = new IClasspathEntry[current.length - 1];
		boolean removed = false;
		for (int i = 0; i < current.length; i++) {
			if (!removed) {
				if (current[i] == entry) {
					removed = true;
				} else {
					updated[i] = current[i];
				}
			} else {
				updated[i - 1] = current[i];
			}
		}
		jproj.setRawClasspath(updated, null);
	}

	/**
	 * Returns the existing classpath container if it is already on the
	 * classpath. This will not create a new container.
	 * 
	 * @param jproj
	 * @param classpathContainerID
	 * @return
	 */
	private IClasspathEntry getExistingContainer(IJavaProject jproj, IPath classpathContainerPath) {
		try {
			IClasspathEntry[] cpes;
			cpes = jproj.getRawClasspath();
			for (int j = 0; j < cpes.length; j++) {
				final IClasspathEntry cpe = cpes[j];
				if (cpe.getEntryKind() == IClasspathEntry.CPE_CONTAINER) {
					if (cpe.getPath().equals(classpathContainerPath)) {
						return cpe; // entry found
					}
				}
			}
		} catch (JavaModelException e) {
			Logger.getLogger().logError(e);
		}
		// entry not found
		return null;
	}

	/*
	 * Needs to notice changes to MANIFEST.MF in any J2EE projects, changes to
	 * .component in any J2EE Projects, and any archive changes in EAR projects
	 */
	public void resourceChanged(IResourceChangeEvent event) {
		try {
			pauseUpdates();
			event.getDelta().accept(this);
		} catch (CoreException e){
			Logger.getLogger().logError(e);
		} finally {
			resumeUpdates();
		}
	}
	
	/*
	 * Check for the following changes:
	 * 1. changes to the component definition in .settings/org.eclipse.wst.common.component for all J2EE modules
	 * 2. 
	 */
	public boolean visit(IResourceDelta delta) {
		IResource resource = delta.getResource();
		if(resource instanceof IWorkspaceRoot){
			return true;
		} else if(resource instanceof IProject) {
			return ModuleCoreNature.isFlexibleProject((IProject)resource);
		} else if(resource instanceof IFolder){
			if(resource.getName().equals(".settings")){
				return true;
			}
			IVirtualComponent comp = ComponentCore.createComponent(resource.getProject());
			
			if(!(comp instanceof J2EEModuleVirtualComponent) && !(comp instanceof EARVirtualComponent)){
				return false;
			}
			
			IVirtualFolder rootFolder = comp.getRootFolder();
			IContainer [] realRoots = rootFolder.getUnderlyingFolders();
			IResource aRealRoot;
			
			for(int i=0;i<realRoots.length; i++){
				aRealRoot = realRoots[i];
				while(aRealRoot != null){
					if(aRealRoot.equals(resource)){
						return true;
					}
					aRealRoot = aRealRoot.getParent();
				}
			}
			
			if(comp instanceof EARVirtualComponent){
				return false;
			}
			IVirtualFolder metaFolder = rootFolder.getFolder("META-INF");
			IContainer [] realMetas = metaFolder.getUnderlyingFolders();
			for(int i=0;i<realMetas.length; i++){
				if(realMetas[i].equals(resource)){
					return true;
				}
			}
			return false;
		} 
		if(resource.getName().equals("org.eclipse.wst.common.component")){
			queueUpdate(resource.getProject());
		} else if(resource.getName().equals("MANIFEST.MF")){
			if(resource.equals(J2EEProjectUtilities.getManifestFile(resource.getProject()))){
				queueUpdateModule(resource.getProject());
			}
		} else if(resource.getName().toLowerCase().endsWith(".jar")){
			IVirtualComponent comp = ComponentCore.createComponent(resource.getProject());
			if(comp instanceof EARVirtualComponent){
				queueUpdateEAR(resource.getProject());
			}
		}
		return false;
	}

}
