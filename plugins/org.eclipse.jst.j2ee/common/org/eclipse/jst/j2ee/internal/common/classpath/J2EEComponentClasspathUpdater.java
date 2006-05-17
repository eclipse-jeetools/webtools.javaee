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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
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
import org.eclipse.wst.common.componentcore.GlobalComponentChangeListener;
import org.eclipse.wst.common.componentcore.GlobalComponentChangeNotifier;
import org.eclipse.wst.common.componentcore.internal.ComponentcorePackage;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelEvent;

public class J2EEComponentClasspathUpdater extends AdapterImpl implements GlobalComponentChangeListener {

	private static J2EEComponentClasspathUpdater instance = null;
	private int pauseCount = 0;
	private Set updatesRequired = new HashSet();
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
		Object[] projects = null;
		synchronized (this) {
			if (pauseCount > 0) {
				pauseCount--;
			}
			if (pauseCount > 0) {
				return;
			}
			projects = updatesRequired.toArray();
			updatesRequired.clear();
		}
		for (int i = 0; i < projects.length; i++) {
			updateModule((IProject) projects[i]);
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
			//Hari: update the project only if the tree is not locked.
			if(false == ResourcesPlugin.getWorkspace().isTreeLocked())
			queueUpdateModule(project);
		}
	}

	public void queueUpdateModule(IProject project) {
		synchronized (this) {
			if (pauseCount > 0) {
				if (!updatesRequired.contains(project)) {
					updatesRequired.add(project);
				}
				return;
			}
		}
		updateModule(project);

	}

	public void queueUpdateEAR(IProject earProject) {
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

	private void updateModule(IProject project) {
		IClasspathContainer container = getWebAppLibrariesContainer(project);
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
			((J2EEComponentClasspathContainer) container).update();
		}
	}

	public IClasspathContainer getWebAppLibrariesContainer(IProject webProject) {
		IJavaProject jproj = JavaCore.create(webProject);
		IClasspathContainer container = null;
		try {
			container = JavaCore.getClasspathContainer(WEB_APP_LIBS_PATH, jproj);
		} catch (JavaModelException e) {
			Logger.getLogger().logError(e);
		}
		return container;
	}

	private IClasspathContainer addContainerToModuleIfNecessary(IProject moduleProject) {
		IJavaProject jproj = JavaCore.create(moduleProject);
		IClasspathEntry entry = getJ2eeComponentClasspathEntry(jproj);
		if (entry == null) {
			try {
				entry = JavaCore.newContainerEntry(new Path(J2EEComponentClasspathContainer.CONTAINER_ID));
				addToClasspath(jproj, entry);
			} catch (CoreException e) {
				Logger.getLogger().logError(e);
			}
		}
		IClasspathContainer container = null;
		try {
			container = JavaCore.getClasspathContainer(entry.getPath(), jproj);
		} catch (JavaModelException e) {
			Logger.getLogger().logError(e);
		}
		return container;
	}

	private void removeContainerFromModuleIfNecessary(IProject moduleProject) {
		IJavaProject jproj = JavaCore.create(moduleProject);
		IClasspathEntry entry = getJ2eeComponentClasspathEntry(jproj);
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


	private IClasspathEntry getJ2eeComponentClasspathEntry(IJavaProject jproj) {
		try {
			IClasspathEntry[] cpes;
			cpes = jproj.getRawClasspath();
			for (int j = 0; j < cpes.length; j++) {
				final IClasspathEntry cpe = cpes[j];
				if (cpe.getEntryKind() == IClasspathEntry.CPE_CONTAINER) {
					if (cpe.getPath().equals(J2EEComponentClasspathContainer.CONTAINER_PATH)) {
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

}
