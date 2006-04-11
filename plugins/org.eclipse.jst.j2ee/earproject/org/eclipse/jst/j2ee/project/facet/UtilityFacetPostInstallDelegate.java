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
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public final class UtilityFacetPostInstallDelegate extends J2EEFacetInstallDelegate implements IDelegate {

	public void execute(final IProject project, final IProjectFacetVersion fv, final Object cfg, final IProgressMonitor monitor) throws CoreException {
		if (monitor != null) {
			monitor.beginTask("", 1); //$NON-NLS-1$
		}

		try {

			final IDataModel model = (IDataModel) cfg;


			// Associate with an EAR, if necessary.
			if (model.getBooleanProperty(IUtilityFacetInstallDataModelProperties.ADD_TO_EAR)) {
				final String earProjectName = model.getStringProperty(IUtilityFacetInstallDataModelProperties.EAR_PROJECT_NAME);
				if (earProjectName != null && earProjectName != "") { //$NON-NLS-1$
//					IProject earProject = ProjectUtilities.getProject(earProjectName);
//					if (earProject.exists()) {
//						IVirtualComponent earComp = ComponentCore.createComponent(earProject);
//
//						IDataModel dataModel = DataModelFactory.createDataModel(new AddComponentToEnterpriseApplicationDataModelProvider());
//						dataModel.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComp);
//						List modList = (List) dataModel.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
//						modList.add(c);
//						dataModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, modList);
//						try {
//							dataModel.getDefaultOperation().execute(null, null);
//						} catch (ExecutionException e) {
//							Logger.getLogger().logError(e);
//						}
//					}
					

					//final String moduleURI = model.getStringProperty(IJ2EEModuleFacetInstallDataModelProperties.MODULE_URI);
					
					final String moduleURI = project.getName() + ".jar";
						
					installAndAddModuletoEAR( J2EEVersionConstants.VERSION_1_4_TEXT,
								earProjectName,
								(IRuntime) model.getProperty(IJ2EEFacetInstallDataModelProperties.FACET_RUNTIME),
								project,
								moduleURI,
								monitor );					
				}
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
