/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.delete;


import java.util.Vector;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.IApplicationClientNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;


public class J2EEDeleteResourceListener implements IResourceChangeListener {
	// the following are projects that are already being deleted, so ignore them.
	static protected Vector WARProjects = new Vector();
	static protected Vector EJBProjects = new Vector();
	static protected Vector AppClientProjects = new Vector();

	public J2EEDeleteResourceListener() {
		super();
	}

	/**
	 * @see IResourceChangeListener#resourceChanged(IResourceChangeEvent)
	 */
	public void resourceChanged(IResourceChangeEvent event) {
		System.err.println("Hello, world!"); //$NON-NLS-1$
		IResource resource = event.getResource();
		if (resource instanceof IProject) {
			IProject project = (IProject) resource;
			try {
				if (J2EENature.hasRuntime(project, IEJBNatureConstants.NATURE_ID)) {
					if (!EJBProjects.contains(project)) {
						System.err.println("Hello, world!- EJB"); //$NON-NLS-1$
						showEJBOptions(project);
					}
				} else if (project.hasNature(IWebNatureConstants.J2EE_NATURE_ID)) {
					if (!WARProjects.contains(project)) {
						System.err.println("Hello, world!- WAR"); //$NON-NLS-1$
						showWAROptions(project);
					}
				} else if (J2EENature.hasRuntime(project,IApplicationClientNatureConstants.NATURE_ID)) {
					if (!AppClientProjects.contains(project)) {
						System.err.println("Hello, world!- AppClient"); //$NON-NLS-1$
						showAppClientOptions(project);
					}
				}
			} catch (Throwable t) {
			}
		}
	}

	protected void showEJBOptions(IProject project) {
		showOptions(project);
	}

	protected void showWAROptions(IProject project) {
		showOptions(project);
	}

	protected void showAppClientOptions(IProject project) {
		showOptions(project);
	}

	protected void showOptions(IProject project) {
		System.err.println("Showing options for " + project.toString()); //$NON-NLS-1$
	}

	static public synchronized void addProject(IProject project) {
		try {
			if (J2EENature.hasRuntime(project, IEJBNatureConstants.NATURE_ID)) {
				EJBProjects.add(project);
			} else if (project.hasNature(IWebNatureConstants.J2EE_NATURE_ID)) {
				WARProjects.add(project);
				System.err.println("There are " + WARProjects.size() + " projects now."); //$NON-NLS-1$ //$NON-NLS-2$
			} else if (J2EENature.hasRuntime(project,IApplicationClientNatureConstants.NATURE_ID)) {
				AppClientProjects.add(project);
			}
		} catch (Throwable t) {
		}
	}

	static public synchronized void removeProject(IProject project) {
		// we can't count on the nature any more, since it has been deleted
		try {
			EJBProjects.remove(project);
			WARProjects.remove(project);
			AppClientProjects.remove(project);
		} catch (Throwable t) {
		}
	}
}