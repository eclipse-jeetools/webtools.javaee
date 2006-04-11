/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jca.project.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.J2EEFacetInstallDelegate;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public class ConnectorFacetPostInstallDelegate extends J2EEFacetInstallDelegate implements IDelegate {

	public static final String CONNECTOR_XML_TEMPLATE_10		= "rartp10.xml";								//$NON-NLS-1$
	public static final String CONNECTOR_XML_TEMPLATE_15		= "rartp15.xml"; //$NON-NLS-1$
	
	public void execute(IProject project, IProjectFacetVersion fv, Object config, IProgressMonitor monitor) throws CoreException {
		if (monitor != null) {
			monitor.beginTask("", 1); //$NON-NLS-1$
		}

		try {
			IDataModel model = (IDataModel) config;

			final IVirtualComponent c = ComponentCore.createComponent(project);

			// Associate with an EAR, if necessary.

			final String earProjectName = (String) model.getProperty(IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME);
			if (model.getBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR)) {
				if (earProjectName != null && !earProjectName.equals("")) { //$NON-NLS-1$

					String ver = fv.getVersionString();
					String j2eeVersionText = J2EEVersionUtil.convertVersionIntToString
					(J2EEVersionUtil.convertConnectorVersionStringToJ2EEVersionID(ver));
					
					final String moduleURI = model.getStringProperty(IJ2EEModuleFacetInstallDataModelProperties.MODULE_URI);
					
					IFacetedProject facetedProject = ProjectFacetsManager.create(project);
					installAndAddModuletoEAR( j2eeVersionText,
								earProjectName,
								(IRuntime) model.getProperty(IJ2EEFacetInstallDataModelProperties.FACET_RUNTIME),
								project,
								moduleURI,
								monitor );


				}
			}

			if (monitor != null) {
				monitor.worked(1);
			}
		}

		finally {
			if (monitor != null) {
				monitor.done();
			}
		}

	}

}
