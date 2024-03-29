/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Sep 29, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.plugin;

import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.internal.moduleextension.EarModuleExtensionImpl;
import org.eclipse.jst.j2ee.internal.moduleextension.WebModuleExtension;
import org.eclipse.jst.j2ee.internal.project.J2EEJavaProjectInfo;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;



public class WebModuleExtensionImpl extends EarModuleExtensionImpl implements WebModuleExtension {

	/**
	 * 
	 */
	public WebModuleExtensionImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.WebModuleExtension#createProjectInfo()
	 */
	@Override
	public J2EEJavaProjectInfo createProjectInfo() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.WebModuleExtension#addWLPProjects(org.eclipse.core.resources.IProject,
	 *      java.util.Set)
	 */
	@Override
	public void addWLPProjects(IProject aProject, Set projectsToBuild) {
		// J2EEWebNatureRuntime nature = J2EEWebNatureRuntime.getRuntime(aProject);
		// if (nature == null)
		// return;
		// ILibModule[] libModules = nature.getLibModules();
		// for (int i = 0; i < libModules.length; i++) {
		// IProject p = libModules[i].getProject();
		// if (p.isAccessible())
		// projectsToBuild.add(libModules[i].getProject());
		// }
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.WebModuleExtension#setContextRootForModuleMapping(org.eclipse.jst.j2ee.internal.internal.application.WebModule,
	 *      org.eclipse.core.resources.IProject)
	 */
	@Override
	public void setContextRootForModuleMapping(WebModule webModule, IProject nestedProject) throws CoreException {
		// TODO migrate to flex project
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.WebModuleExtension#hasRuntime(org.eclipse.core.resources.IProject)
	 */
	@Override
	public boolean hasRuntime(IProject project) {
		// return J2EEWebNatureRuntimeUtilities.hasJ2EERuntime(project);
		// To do: work based on module
		return false;
	}

	// public J2EEImportOperationOLD createImportOperation(IProject
	// nestedProject, WARFile warFile, EARImportConfiguration importConfig){
	// WarImportOperation op = new WarImportOperation(nestedProject, warFile);
	// //op.setServerTarget(info.getServerTarget());
	// HashMap opMap = importConfig.createProjectOption;
	// if(opMap != null && !opMap.isEmpty())
	// op.createWLProjectOptions = opMap;
	// return op;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.WebModuleExtension#getContentFolder(org.eclipse.core.resources.IProject,
	 *      org.eclipse.jst.j2ee.internal.internal.commonarchivecore.File)
	 */

	@Override
	public String getContentFolder(IProject project, IFile webSettingsFile) {
		return null;
		// To do: Needs work here, no content folder exists now
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.WebModuleExtension#compareWebContextRoot(org.eclipse.jst.j2ee.internal.internal.application.Module,
	 *      org.eclipse.core.resources.IProject)
	 */
	@Override
	public boolean compareWebContextRoot(Module module, IProject project) throws CoreException {
		String contextRoot = ((WebModule) module).getContextRoot();
		if (contextRoot != null)
			return ((contextRoot).equals(getServerContextRoot(project)));
		return false;
	}

	protected String getServerContextRoot(IProject project) {
		// TODO migrate to flex project
		return ""; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.EarModuleExtension#createProjectDataModel()
	 */
	@Override
	public IDataModel createProjectDataModel() {
		return DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.EarModuleExtension#createImportDataModel()
	 */
	@Override
	public IDataModel createImportDataModel() {
		return DataModelFactory.createDataModel(new WebComponentImportDataModelProvider());
	}

}
