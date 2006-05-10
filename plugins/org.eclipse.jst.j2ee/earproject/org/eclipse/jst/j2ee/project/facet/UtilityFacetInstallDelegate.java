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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.project.facet.WtpUtils;
import org.eclipse.jst.common.project.facet.core.ClasspathHelper;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.FacetDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public final class UtilityFacetInstallDelegate extends J2EEFacetInstallDelegate implements IDelegate {

	public void execute(final IProject project, final IProjectFacetVersion fv, final Object cfg, final IProgressMonitor monitor) throws CoreException {
		if (monitor != null) {
			monitor.beginTask("", 1); //$NON-NLS-1$
		}

		try {

			final IDataModel model = (IDataModel) cfg;

			// Add WTP natures.

			WtpUtils.addNatures(project);

			// Setup the flexible project structure.

			final IVirtualComponent c = ComponentCore.createComponent(project);

			c.create(0, null);

			final IVirtualFolder jsrc = c.getRootFolder();
			final IJavaProject jproj = JavaCore.create(project);

			addJ2EEModuleClasspathContainer(jproj);
			
			final IClasspathEntry[] cp = jproj.getRawClasspath();

			for (int i = 0; i < cp.length; i++) {
				final IClasspathEntry cpe = cp[i];

				if (cpe.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
					IPath path = cpe.getPath().removeFirstSegments(1);
					if (path.isEmpty()) {
						path = new Path("/"); //$NON-NLS-1$
					}
					jsrc.createLink(path, 0, null);
				}
			}

			final IWorkspace ws = ResourcesPlugin.getWorkspace();

			final IVirtualFolder root = c.getRootFolder();

			IContainer container = null;

			if (root.getProjectRelativePath().segmentCount() == 0) {
				container = project;
			} else {
				container = project.getFolder(root.getProjectRelativePath());
			}

			try {
				if (container != null)
					createManifest(project, container, monitor);
			} catch (InvocationTargetException e) {
				Logger.getLogger().logError(e);
			} catch (InterruptedException e) {
				Logger.getLogger().logError(e);
			}


			ClasspathHelper.removeClasspathEntries(project, fv);

			if (!ClasspathHelper.addClasspathEntries(project, fv)) {
				// TODO: Support the no runtime case.
				// ClasspathHelper.addClasspathEntries( project, fv, <something> );
			}

			try {
				((IDataModelOperation) model.getProperty(FacetDataModelProvider.NOTIFICATION_OPERATION)).execute(monitor, null);
			} catch (ExecutionException e) {
				Logger.getLogger().logError(e);
			}
			if (monitor != null) {
				monitor.worked(1);
			}
		} catch (Exception e) {
			Logger.getLogger().logError(e);
		} finally {
			if (monitor != null) {
				monitor.done();
			}
		}
	}
}
