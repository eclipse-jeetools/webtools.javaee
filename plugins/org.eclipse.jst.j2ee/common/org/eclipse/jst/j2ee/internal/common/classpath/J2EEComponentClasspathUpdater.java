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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.jdt.internal.classpath.FlexibleProjectContainer;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.ComponentcorePackage;
import org.eclipse.wst.common.componentcore.internal.GlobalComponentChangeListener;
import org.eclipse.wst.common.componentcore.internal.GlobalComponentChangeNotifier;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelEvent;

public class J2EEComponentClasspathUpdater extends AdapterImpl implements GlobalComponentChangeListener, IResourceChangeListener {

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
			GlobalComponentChangeNotifier.getInstance().addListener(instance);
			ResourcesPlugin.getWorkspace().addResourceChangeListener(instance, IResourceChangeEvent.POST_CHANGE);
		}
	}

	/**
	 * Pauses updates; any caller of this method must ensure through a try/finally block that
	 * resumeUpdates is subsequently called.
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

	private final String MODULE_URI = "module:/resource";

	public void notifyChanged(Notification notification) {
		if (notification.getFeature() == ComponentcorePackage.eINSTANCE.getWorkbenchComponent_ReferencedComponents()) {
			switch (notification.getEventType()) {
				case Notification.REMOVE :
					ReferencedComponent oldRef = (ReferencedComponent) notification.getOldValue();
					URI handle = oldRef.getHandle();
					if (handle.toString().startsWith(MODULE_URI)) {
						String projectName = handle.segment(2);
						IProject project = ProjectUtilities.getProject(projectName);
						queueUpdate(project);
					}
			}
		}
	}

	public void editModelChanged(EditModelEvent anEvent) {
		switch (anEvent.getEventCode()) {
			case EditModelEvent.SAVE :
			case EditModelEvent.LOADED_RESOURCE :
				IProject project = anEvent.getEditModel().getProject();
				queueUpdate(project);
				break;
		}
	}

	public void queueUpdate(IProject project) {
		if (J2EEProjectUtilities.isEARProject(project)) {
			// TODO streamline the ear section so it only changes if the refs have changed
			queueUpdateEAR(project);
		} else if (J2EEProjectUtilities.isApplicationClientProject(project) || J2EEProjectUtilities.isEJBProject(project) || J2EEProjectUtilities.isDynamicWebProject(project) || J2EEProjectUtilities.isJCAProject(project) || J2EEProjectUtilities.isUtilityProject(project)) {
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
		trackEAR(earProject, true);
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
				if (container != null) {
					((FlexibleProjectContainer) container).refresh();
				}
				IProject[] earProjects = J2EEProjectUtilities.getReferencingEARProjects(project);
				if (earProjects.length == 0) {
					removeContainerFromModuleIfNecessary(project);
					return;
				}
				container = addContainerToModuleIfNecessary(project);
				if (container != null) {
					((J2EEComponentClasspathContainer) container).refresh();
				}
				IResource manifest = J2EEProjectUtilities.getManifestFile(project);
				trackManifest(manifest);
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
				entry = JavaCore.newContainerEntry(J2EEComponentClasspathContainer.CONTAINER_PATH);
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
	 * Returns the existing classpath container if it is already on the classpath. This will not
	 * create a new container.
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

	public void trackManifest(IResource manifest) {
		if (manifest == null) return;
		synchronized (knownManifests) {
			if (manifest.exists()) {
				Long timeStamp = new Long(manifest.getLocalTimeStamp());
				knownManifests.put(manifest, timeStamp);
			} else {
				knownManifests.remove(manifest);
			}
		}
	}

	public void trackEAR(IProject earProject) {
		trackEAR(earProject, false);
	}


	private void trackEAR(IProject earProject, boolean forceUpdate) {
		if (earProject.exists()) {
			if (forceUpdate || !knownEARs.containsKey(earProject)) {
				try {
					IVirtualComponent earComponent = ComponentCore.createComponent(earProject);
					IVirtualResource[] resources = earComponent.getRootFolder().members();
					Set archiveSet = (Set) knownEARs.get(earProject);
					if (null == archiveSet) {
						archiveSet = new HashSet();
						knownEARs.put(earProject, archiveSet);
					} else {
						archiveSet.clear();
					}
					for (int i = 0; i < resources.length; i++) {
						if (resources[i].getName().endsWith(".jar")) {
							archiveSet.add(resources[i].getName());
						}
					}
				} catch (CoreException e) {
					knownEARs.remove(earProject);
				}
			}
		} else {
			knownEARs.remove(earProject);
		}
	}


	/* IResource to timestamps */
	private Map knownManifests = new Hashtable();
	/* IProject to Sets of archive names */
	private Map knownEARs = new Hashtable();

	public void resourceChanged(IResourceChangeEvent event) {
		try {
			pauseUpdates();
			List manifestsToRemove = null;
			List modulesToUpdate = null;
			synchronized (knownManifests) {
				Iterator iterator = knownManifests.keySet().iterator();
				while (iterator.hasNext()) {
					IResource resource = (IResource) iterator.next();
					if (resource.exists()) {
						long currentTimeStamp = resource.getLocalTimeStamp();
						Long lastTimeStamp = (Long) knownManifests.get(resource);
						if (lastTimeStamp.longValue() != currentTimeStamp) {
							if (modulesToUpdate == null) {
								modulesToUpdate = new ArrayList();
							}
							modulesToUpdate.add(resource.getProject());
						}
					} else {
						if (manifestsToRemove == null) {
							manifestsToRemove = new ArrayList();
						}
						manifestsToRemove.add(resource);
					}
				}
			}
			if (manifestsToRemove != null) {
				synchronized (knownManifests) {
					for (int i = 0, size = manifestsToRemove.size(); i < size; i++) {
						knownManifests.remove(manifestsToRemove.get(i));
					}
				}
			}

			if (null != modulesToUpdate) {
				for (int i = 0, size = modulesToUpdate.size(); i < size; i++) {
					queueUpdateModule((IProject) modulesToUpdate.get(i));
				}
			}
			List earsToRemove = null;
			IProject[] earProjects = null;

			synchronized (knownEARs) {
				Set keySet = knownEARs.keySet();
				earProjects = new IProject[keySet.size()];
				Iterator iterator = keySet.iterator();
				for (int i = 0; iterator.hasNext(); i++) {
					earProjects[i] = (IProject) iterator.next();
				}
			}

			for (int i = 0; i < earProjects.length; i++) {
				if (earProjects[i].exists()) {
					Set archiveSet = (Set) knownEARs.get(earProjects[i]);
					if (archiveSet != null) {
						IVirtualComponent earComponent = ComponentCore.createComponent(earProjects[i]);
						IVirtualResource[] resources;
						try {
							resources = earComponent.getRootFolder().members();
							boolean requiresUpdate = false;
							int archivesFound = 0;
							for (int j = 0; j < resources.length && !requiresUpdate; j++) {
								String name = resources[j].getName();
								if (name.endsWith(".jar")) {
									if (!archiveSet.contains(resources[j].getName())) {
										requiresUpdate = true;
									} else {
										archivesFound++;
									}
								}
							}
							if (requiresUpdate || archivesFound < archiveSet.size()) {
								queueUpdateEAR(earProjects[i]);
							}
						} catch (CoreException e) {
							if (earsToRemove == null) {
								earsToRemove = new ArrayList();
							}
							earsToRemove.add(earProjects[i]);
						}
					}
				} else {
					if (earsToRemove == null) {
						earsToRemove = new ArrayList();
					}
					earsToRemove.add(earProjects[i]);
				}
			}

			if (earsToRemove != null) {
				synchronized (knownEARs) {
					for (int i = 0; i < earsToRemove.size(); i++) {
						knownEARs.remove(earsToRemove.get(i));
					}
				}
			}
		} finally {
			resumeUpdates();
		}
	}

}
