/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.defect.tests;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;

public class ClasspathContainerThreading {

	public final int LOOPS = 10;
	private final List threads = new ArrayList();

	class EARRunnable implements Runnable {
		IProject project;

		public EARRunnable(IProject earProject) {
			this.project = earProject;
		}

		public void run() {
			try {
				for (int i = 0; i < LOOPS; i++) {
					IVirtualComponent comp = ComponentCore.createComponent(project);
					IVirtualFile vFile = comp.getRootFolder().getFile("foo.jar");
					IFile iFile = vFile.getUnderlyingFile();
					InputStream in = new StringBufferInputStream("foo.jar");
					iFile.create(in, true, null);
					Thread.sleep(10);
					iFile.delete(true, null);
					Thread.sleep(10);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				synchronized (threads) {
					threads.remove(Thread.currentThread());
				}
			}
		}
	};

	class ForceUpdateRunnable implements Runnable {
		IProject project;

		public ForceUpdateRunnable(IProject project) {
			this.project = project;
		}

		public void run() {
			try {
				for (int i = 0; i < LOOPS * 2; i++) {
					J2EEComponentClasspathUpdater.getInstance().queueUpdate(project);
					Thread.sleep(10);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				synchronized (threads) {
					threads.remove(Thread.currentThread());
				}
			}
		}
	};

	class ModuleRunnable implements Runnable {
		IProject project;

		public ModuleRunnable(IProject project) {
			this.project = project;
		}

		public void run() {
			try {
				for (int i = 0; i < LOOPS; i++) {
					ArchiveManifest manifest = J2EEProjectUtilities.readManifest(project);
					manifest.addEntry("foo.jar");
					J2EEProjectUtilities.writeManifest(project, manifest);
					Thread.sleep(10);
					manifest = J2EEProjectUtilities.readManifest(project);
					manifest.removeEntry("foo.jar");
					J2EEProjectUtilities.writeManifest(project, manifest);
					Thread.sleep(10);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				synchronized (threads) {
					threads.remove(Thread.currentThread());
				}
			}
		}
	};


	public ClasspathContainerThreading() {

	}

	public void testDeadlock() {
		IProject[] allProjects = J2EEProjectUtilities.getAllProjects();
		List earProjects = new ArrayList();
		List modulesProjects = new ArrayList();
		for (int i = 0; i < allProjects.length; i++) {
			if (JavaEEProjectUtilities.isEARProject(allProjects[i])) {
				earProjects.add(allProjects[i]);
			} else {
				IVirtualComponent comp = ComponentCore.createComponent(allProjects[i]);
				if (comp != null) {
					modulesProjects.add(allProjects[i]);
				}
			}
		}



		for (int i = 0; i < earProjects.size(); i++) {
			threads.add(new Thread(new EARRunnable((IProject) earProjects.get(i))));
			threads.add(new Thread(new ForceUpdateRunnable((IProject) earProjects.get(i))));
		}
		for (int i = 0; i < modulesProjects.size(); i++) {
			threads.add(new Thread(new ModuleRunnable((IProject) modulesProjects.get(i))));
			threads.add(new Thread(new ForceUpdateRunnable((IProject) modulesProjects.get(i))));
		}

		synchronized (threads) {
			for (int i = 0; i < threads.size(); i++) {
				((Thread) threads.get(i)).start();
			}
		}

		boolean shouldContinue = true;
		while (shouldContinue) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
			synchronized (threads) {
				shouldContinue = !threads.isEmpty();
			}
		}


	}

}
