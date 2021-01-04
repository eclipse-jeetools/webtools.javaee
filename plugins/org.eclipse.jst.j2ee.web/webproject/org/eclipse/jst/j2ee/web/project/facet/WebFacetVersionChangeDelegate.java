/******************************************************************************
 * Copyright (c) 2005, 2021 BEA Systems, Inc. and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.j2ee.web.project.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * Exists now to avoid preventing the user from changing the jst.web version
 */

public final class WebFacetVersionChangeDelegate implements IDelegate {
	public void execute(final IProject project, final IProjectFacetVersion fv, final Object cfg, final IProgressMonitor monitor) throws CoreException {
		// TODO: Trace this change to the project
		monitor.beginTask("", 1); //$NON-NLS-1$

		try {
			// Find the version that's currently installed.
//			final IFacetedProject fproj = ProjectFacetsManager.create(project);
//			final IProjectFacetVersion oldver = fproj.getInstalledVersion(fv.getProjectFacet());
			monitor.worked(1);
		}
		finally {
			monitor.done();
		}
	}
}
