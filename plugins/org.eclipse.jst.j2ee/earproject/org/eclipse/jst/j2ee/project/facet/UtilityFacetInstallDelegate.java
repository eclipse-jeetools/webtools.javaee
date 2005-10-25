/******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.j2ee.project.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.common.project.facet.WtpUtils;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public final class UtilityFacetInstallDelegate implements IDelegate {
	
	public void execute(final IProject project, final IProjectFacetVersion fv, final Object cfg, final IProgressMonitor monitor) throws CoreException {
		if (monitor != null) {
			monitor.beginTask("", 1); //$NON-NLS-1$
		}

		try {


			// Add WTP natures.

			WtpUtils.addNatures(project);

			// Setup the flexible project structure.

			final IVirtualComponent c = ComponentCore.createComponent(project);

			c.create(0, null);

			final IVirtualFolder jsrc = c.getRootFolder();
			final IJavaProject jproj = JavaCore.create(project);
			final IClasspathEntry[] cp = jproj.getRawClasspath();

			for (int i = 0; i < cp.length; i++) {
				final IClasspathEntry cpe = cp[i];

				if (cpe.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
					jsrc.createLink(cpe.getPath().removeFirstSegments(1), 0, null);
				}
			}

			
//			final UtilityFacetInstallConfig config;
//
//			if (cfg != null) {
//				config = (UtilityFacetInstallConfig) cfg;
//			} else {
//				config = new UtilityFacetInstallConfig();
//				config.setEarProjectName(null);
//			}			
//			// Associate with an EAR, if necessary.
//
//			final String earProjectName = config.getEarProjectName();
//
//			if (earProjectName != null) {
//				final IWorkspace ws = ResourcesPlugin.getWorkspace();
//
//				final IProject earproj = ws.getRoot().getProject(earProjectName);
//
//				EarUtil.addModuleReference(earproj, project, null);
//			}

			if (monitor != null) {
				monitor.worked(1);
			}
		} finally {
			if (monitor != null) {
				monitor.done();
			}
		}
	}
}
