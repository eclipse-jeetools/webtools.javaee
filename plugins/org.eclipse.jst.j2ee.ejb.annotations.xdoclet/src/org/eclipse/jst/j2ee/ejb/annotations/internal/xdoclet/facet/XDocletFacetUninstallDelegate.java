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

package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.action.XDocletActionDelegate;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.J2EEFacetInstallDelegate;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public final class XDocletFacetUninstallDelegate extends J2EEFacetInstallDelegate implements IDelegate {


	public void execute(final IProject project, final IProjectFacetVersion fv, final Object cfg, final IProgressMonitor monitor)
			throws CoreException {
		if (monitor != null) {
			monitor.beginTask("", 1);
		}

		try {
			
			if (project != null
					&& (J2EEProjectUtilities.isEJBProject(project.getProject()) || J2EEProjectUtilities.isDynamicWebProject(project
							.getProject()))) {
				try {
					ProjectUtilities.removeFromBuildSpec(XDocletActionDelegate.BUILDERID, project.getProject());
				} catch (CoreException e) {
				}
			}			

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
