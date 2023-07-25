/*******************************************************************************

 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.web.project.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public final class WebFragmentFacetPostInstallDelegate extends WebFacetInstallDelegateForWebFragmentProjects implements IDelegate {

	@Override
	public void execute(final IProject project, final IProjectFacetVersion fv, final Object cfg, final IProgressMonitor monitor) throws CoreException {
		if (monitor != null) {
			monitor.beginTask("", 1); //$NON-NLS-1$
		}
		try {
			final IDataModel model = (IDataModel) cfg;
			if (model.getBooleanProperty(IWebFragmentFacetInstallDataModelProperties.ADD_TO_WAR)) {
				final String warProjectName = model.getStringProperty(IWebFragmentFacetInstallDataModelProperties.WAR_PROJECT_NAME);
				if (warProjectName != null && warProjectName != "") //$NON-NLS-1$
				{
					final String moduleURI = model.getStringProperty(IJ2EEModuleFacetInstallDataModelProperties.MODULE_URI);
					
					installAndAddModuletoWAR( J2EEVersionConstants.VERSION_3_0_TEXT,
								warProjectName,
								(IRuntime) model.getProperty(IJ2EEFacetInstallDataModelProperties.FACET_RUNTIME),
								project,
								moduleURI,
								monitor );					
				}
			}
		} catch (Exception e) {
			J2EEPlugin.logError(e);
		} finally {
			if (monitor != null) {
				monitor.done();
			}
		}
	}
}
