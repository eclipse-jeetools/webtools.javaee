/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
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
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationOperation;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.internal.earcreation.UpdateModuleReferencesInEARProjectCommand;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEJavaProjectInfo;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WARImportDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationOperation;
import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntime;
import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntimeUtilities;
import org.eclipse.jst.j2ee.internal.web.operations.WebEditModel;
import org.eclipse.jst.j2ee.internal.web.operations.WebProjectInfo;
import org.eclipse.jst.j2ee.moduleextension.EarModuleExtensionImpl;
import org.eclipse.jst.j2ee.moduleextension.WebModuleExtension;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.web.internal.operation.ILibModule;
import org.eclipse.wst.web.internal.operation.WebSettings;


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
	public J2EEJavaProjectInfo createProjectInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void initializeEjbReferencesToModule(J2EENature moduleNature, UpdateModuleReferencesInEARProjectCommand command) {
		WebEditModel editModel;
		try {
			editModel = ((J2EEWebNatureRuntime) moduleNature).getWebAppEditModelForWrite(this);
		} catch (Exception e) {
			return;
		}
		boolean foundRef = false;
		try {
			WebApp webApp = editModel.getWebApp();
			if (webApp != null) {
				foundRef = command.initializeEjbReferencesToModule(webApp.getEjbRefs());
				foundRef = command.initializeEjbReferencesToModule(webApp.getEjbLocalRefs());
			}
			if (foundRef)
				command.addNestedEditModel(editModel);
		} finally {
			if (!foundRef)
				editModel.releaseAccess(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.WebModuleExtension#addWLPProjects(org.eclipse.core.resources.IProject,
	 *      java.util.Set)
	 */
	public void addWLPProjects(IProject aProject, Set projectsToBuild) {
		J2EEWebNatureRuntime nature = J2EEWebNatureRuntime.getRuntime(aProject);
		if (nature == null)
			return;
		ILibModule[] libModules = nature.getLibModules();
		for (int i = 0; i < libModules.length; i++) {
			IProject p = libModules[i].getProject();
			if (p.isAccessible())
				projectsToBuild.add(libModules[i].getProject());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.WebModuleExtension#createWebProjectInfo(org.eclipse.jst.j2ee.internal.internal.commonarchivecore.Archive)
	 */
	public J2EEJavaProjectInfo createWebProjectInfo(Archive anArchive) {
		WebProjectInfo info = new WebProjectInfo();
		//Moved this code below to EARProjectSaveStrategyImpl, to defer the
		// work
		//and avoid hangs in the wizard right after finish pressed
		/*
		 * boolean isVersion2_2 = !ArchiveUtil.isJ2EE13FastCheck((WARFile)anArchive); if
		 * (isVersion2_2) { info.setJSPLevel(IJ2EEWebNature.JSPLEVEL_1_1);
		 * info.setServletLevel(IJ2EEWebNature.SERVLETLEVEL_2_2);
		 */
		return info;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.WebModuleExtension#setContextRootForModuleMapping(org.eclipse.jst.j2ee.internal.internal.application.WebModule,
	 *      org.eclipse.core.resources.IProject)
	 */
	public void setContextRootForModuleMapping(WebModule webModule, IProject nestedProject) throws CoreException {
		J2EEWebNatureRuntime wnr = J2EEWebNatureRuntime.getRuntime(nestedProject);
		if (wnr != null) {
			wnr.setContextRoot(webModule.getContextRoot());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.WebModuleExtension#hasRuntime(org.eclipse.core.resources.IProject)
	 */
	public boolean hasRuntime(IProject project) {
		return J2EEWebNatureRuntimeUtilities.hasJ2EERuntime(project);
	}

	//    public J2EEImportOperationOLD createImportOperation(IProject
	// nestedProject, WARFile warFile, EARImportConfiguration importConfig){
	//		WarImportOperation op = new WarImportOperation(nestedProject, warFile);
	//		//op.setServerTarget(info.getServerTarget());
	//		HashMap opMap = importConfig.createProjectOption;
	//		if(opMap != null && !opMap.isEmpty())
	//			op.createWLProjectOptions = opMap;
	//		return op;
	//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.WebModuleExtension#getContentFolder(org.eclipse.core.resources.IProject,
	 *      org.eclipse.jst.j2ee.internal.internal.commonarchivecore.File)
	 */

	public String getContentFolder(IProject project, IFile webSettingsFile) {
		String contentFolder = null;
		WebSettings webSettings = new WebSettings(project, webSettingsFile);
		if (webSettings != null) {
			contentFolder = webSettings.getWebContentName();
		}
		return contentFolder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.WebModuleExtension#compareWebContextRoot(org.eclipse.jst.j2ee.internal.internal.application.Module,
	 *      org.eclipse.core.resources.IProject)
	 */
	public boolean compareWebContextRoot(Module module, IProject project) throws CoreException {
		J2EEWebNatureRuntime webNature = (J2EEWebNatureRuntime) project.getNature(IWebNatureConstants.J2EE_NATURE_ID);
		String contextRoot = ((WebModule) module).getContextRoot();
		if (contextRoot != null) {
			return ((contextRoot).equals(webNature.getContextRoot()));
		}
		return false;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.EarModuleExtension#getNatureID()
	 */
	public String getNatureID() {
		return IWebNatureConstants.J2EE_NATURE_ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.EarModuleExtension#createProjectCreationOperation(org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEModuleCreationDataModel)
	 */
	public J2EEModuleCreationOperation createProjectCreationOperation(J2EEModuleCreationDataModel dataModel) {
		return new WebModuleCreationOperation((WebModuleCreationDataModel) dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.EarModuleExtension#createProjectDataModel()
	 */
	public J2EEModuleCreationDataModel createProjectDataModel() {
		return new WebModuleCreationDataModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.EarModuleExtension#createImportDataModel()
	 */
	public J2EEModuleImportDataModel createImportDataModel() {
		return new WARImportDataModel();
	}
}