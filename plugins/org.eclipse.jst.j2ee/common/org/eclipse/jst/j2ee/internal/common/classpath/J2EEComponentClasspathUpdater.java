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
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.GlobalComponentChangeNotifier;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelEvent;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelListener;

public class J2EEComponentClasspathUpdater implements EditModelListener {

	private static J2EEComponentClasspathUpdater instance = null;
	private int pauseCount = 0;
	private Set updatesRequired = new HashSet();

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


	protected static void updateModule(IProject project) {
		final IJavaProject jproj = JavaCore.create(project);
		IClasspathEntry[] cpes;
		try {
			cpes = jproj.getRawClasspath();
			for (int j = 0; j < cpes.length; j++) {
				final IClasspathEntry cpe = cpes[j];

				if (cpe.getEntryKind() == IClasspathEntry.CPE_CONTAINER) {
					final IClasspathContainer cont = JavaCore.getClasspathContainer(cpe.getPath(), jproj);

					if (cont instanceof J2EEComponentClasspathContainer) {
						((J2EEComponentClasspathContainer) cont).update();
					}
				}
			}
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
