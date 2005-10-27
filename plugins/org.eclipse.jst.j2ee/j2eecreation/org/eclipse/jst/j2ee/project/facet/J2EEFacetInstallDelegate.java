/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project.facet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.earcreation.EarFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.ManifestFileCreationAction;
import org.eclipse.jst.server.core.FacetUtil;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.IFacetedProject.Action.Type;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerUtil;

public abstract class J2EEFacetInstallDelegate {

	protected void installEARFacet(String j2eeVersionText, String earProjectName, IProgressMonitor monitor){

		IProject project = ProjectUtilities.getProject(earProjectName); 
		if( project.exists())
			return;
		
		IFacetedProject facetProj;
		try {
			facetProj = ProjectFacetsManager.create(earProjectName,
					null, monitor);
		
			IDataModel earFacetInstallDataModel = DataModelFactory.createDataModel(new EarFacetInstallDataModelProvider());
			earFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, earProjectName);
			earFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, j2eeVersionText);
			
			Set actions = new HashSet();
			actions.add(new IFacetedProject.Action((Type) earFacetInstallDataModel.getProperty(IFacetDataModelProperties.FACET_TYPE),
				(IProjectFacetVersion) earFacetInstallDataModel.getProperty(IFacetDataModelProperties.FACET_VERSION),
				earFacetInstallDataModel));


			facetProj.modify(actions, null);
		} catch (CoreException e) {
			Logger.getLogger().logError(e);
		}		
	}
	
    protected void createManifest(IProject project, String configFolder, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
    	
        IContainer container = project.getFolder(configFolder);
        IFile file = container.getFile(new Path(J2EEConstants.MANIFEST_URI));

        try {
            ManifestFileCreationAction.createManifestFile(file, project);
        } catch (CoreException e) {
            Logger.getLogger().log(e);
        } catch (IOException e) {
            Logger.getLogger().log(e);
        }
    }

	protected void setRuntime(IProject proj, IDataModel model) throws CoreException {
		String runtime = model.getStringProperty(IJ2EEFacetInstallDataModelProperties.RUNTIME_TARGET_ID);
		try {
			if (runtime != null) {
				IRuntime run = getRuntimeByID(runtime);
				org.eclipse.wst.common.project.facet.core.runtime.IRuntime facetRuntime = null;
				try {
					if (run != null)
						facetRuntime = FacetUtil.getRuntime(run);
				}
				catch (IllegalArgumentException ex)
				{}
				if (facetRuntime != null)
					ProjectFacetsManager.create(proj).setRuntime(facetRuntime, null);
			}
			} catch (IllegalArgumentException e) {
			Logger.getLogger().logError(e);
		}
	}

	protected IRuntime getRuntimeByID(String id) {
		IRuntime[] targets = ServerUtil.getRuntimes("", "");
		for (int i = 0; i < targets.length; i++) {
			IRuntime target = targets[i];
			if (id.equals(target.getId()))
				return target;
		}
		return null;
	}
}
