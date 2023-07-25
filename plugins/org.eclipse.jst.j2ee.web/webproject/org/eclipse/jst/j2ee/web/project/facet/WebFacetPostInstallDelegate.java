/******************************************************************************
 * Copyright (c) 2005, 2019 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.j2ee.web.project.facet;

import static org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR;
import static org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME;
import static org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties.MODULE_URI;
import static org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties.FACETED_PROJECT_WORKING_COPY;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.project.facet.J2EEFacetInstallDelegate;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectWorkingCopy;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public final class WebFacetPostInstallDelegate extends J2EEFacetInstallDelegate implements IDelegate {

	@Override
	public void execute(final IProject project, final IProjectFacetVersion fv, final Object cfg, final IProgressMonitor monitor) throws CoreException {
		if (monitor != null) {
			monitor.beginTask("", 1); //$NON-NLS-1$
		}

		try {
			final IDataModel model = (IDataModel) cfg;

			// Associate with an EAR, if necessary.
			if ( model.getBooleanProperty(ADD_TO_EAR) ) {
				final String earProjectName = model.getStringProperty(EAR_PROJECT_NAME);
				
				if ( earProjectName != null && !earProjectName.equals("") ) { //$NON-NLS-1$
					
					String ver = fv.getVersionString();
					
					String j2eeVersionText = J2EEVersionUtil.convertVersionIntToString(J2EEVersionUtil.convertWebVersionStringToJ2EEVersionID(ver));
					final String moduleURI = model.getStringProperty(MODULE_URI);
					
					final IFacetedProjectWorkingCopy fpjwc
					    = (IFacetedProjectWorkingCopy) model.getProperty( FACETED_PROJECT_WORKING_COPY );
					
					installAndAddModuletoEAR( j2eeVersionText,
								earProjectName,
								fpjwc.getPrimaryRuntime(),
								project,
								moduleURI,
								monitor );
					
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
