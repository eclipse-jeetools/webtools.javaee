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
/*
 * Created on Jun 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.web.taglib;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.server.core.internal.RuntimeClasspathContainer;
import org.eclipse.wst.server.core.IRuntime;

/**
 * @author admin
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class ServerJarsUtil {

	public static final String baseV6 = "com.ibm.ws.ast.st.runtime.v60"; //$NON-NLS-1$



	public static IPath[] getServerJars(IProject project) {
		IPath ret[] = new IPath[0];
		IJavaProject javaProject = null;
		try {
			javaProject = (IJavaProject) project.getNature(JavaCore.NATURE_ID);

			List list = new ArrayList();
			IClasspathEntry[] cp = javaProject.getRawClasspath();
			int size = cp.length;
			for (int i = 0; i < size; i++) {

				if (cp[i].getPath().segment(0).equals(RuntimeClasspathContainer.SERVER_CONTAINER)) {
					IClasspathContainer classpathContainer = JavaCore.getClasspathContainer(cp[i].getPath(), javaProject);
					for (int j = 0; j < classpathContainer.getClasspathEntries().length; j++) {
						IClasspathEntry entry = classpathContainer.getClasspathEntries()[j];
						if (entry.getEntryKind() == IClasspathEntry.CPE_LIBRARY) {
							list.add(entry.getPath());
						}
					}
				}

			}

			ret = (IPath[]) list.toArray(new IPath[list.size()]);
		} catch (Exception e) {
			//Do nothing
		}

		return ret;
	}

	public static boolean isTargetedAtWASV6(IRuntime serverTarget) {
		boolean allowed = false;
		String id = getServerId(serverTarget);
		if (id != null) {
			//		System.out.println("Server id = " + id);
			//		System.out.print("Server type = " +
			// serverTarget.getRuntimeType());
			if (serverTarget != null) {
				if (baseV6.equals(id)) {
					allowed = true;
				}
			}
		}
		return allowed;
	}

	private static String getServerId(IRuntime serverTarget) {
		String id = null;
		if (serverTarget != null && serverTarget.getRuntimeType() != null) {
			id = serverTarget.getRuntimeType().getId();
		}
		return id;
	}

}