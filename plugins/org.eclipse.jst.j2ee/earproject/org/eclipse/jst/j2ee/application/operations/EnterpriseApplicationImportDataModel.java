/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.application.operations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.core.internal.localstore.CoreFileSystemLibrary;
import org.eclipse.core.internal.resources.ProjectDescription;
import org.eclipse.core.internal.resources.ProjectDescriptionReader;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientModuleImportDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.IApplicationClientNatureConstants;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.FileImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.WARFileImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.EnterpriseApplicationImportOperation;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.project.IConnectorNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.jst.j2ee.moduleextension.EarModuleManager;
import org.eclipse.jst.j2ee.moduleextension.EjbModuleExtension;
import org.eclipse.jst.j2ee.moduleextension.JcaModuleExtension;
import org.eclipse.jst.j2ee.moduleextension.WebModuleExtension;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModelListener;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.xml.sax.InputSource;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * This dataModel is used for to import Enterprise Applications(from EAR files) into the workspace.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public final class EnterpriseApplicationImportDataModel extends J2EEArtifactImportDataModel implements IAnnotationsDataModel {
	/**
	 * Optional, type Boolean, default true, This flag is set to allow the EAR project to be
	 * imported or not. If it is not imported, it is still possible for the nested projects (moduel &
	 * utility projects) to be imported.
	 */
	public static final String IMPORT_EAR_PROJECT = "EARImportDataModel.IMPORT_EAR_PROJECT"; //$NON-NLS-1$

	/**
	 * Optional, type Boolean, default false, This flag is set to allow nested projects (module
	 * projects & utility projects to be overwritten or not.
	 */
	public static final String OVERWRITE_NESTED_PROJECTS = "EARImportDataModel.OVERWRITE_NESTED_PROJECTS"; //$NON-NLS-1$

	/*
	 * Optional, type IPath default is ear location
	 */
	public static final String NESTED_MODULE_ROOT = "EARImportDataModel.NESTED_MODULE_ROOT"; //$NON-NLS-1$

	/*
	 * Optional, A List containing utililty jars;
	 */
	public static final String UTILITY_LIST = "EARImportDataModel.UTILITY_LIST"; //$NON-NLS-1$

	public static final String SELECTED_MODELS_LIST = "EARImportDataModel.SELECTED_MODELS_LIST"; //$NON-NLS-1$

	private static final String EJB_CLIENT_LIST = "EARImportDataModel.EJB_CLIENT_LIST"; //$NON-NLS-1$

	/**
	 * Booleam, default is true. When all the module projects are added to the ear, this controls
	 * whether their server targets will be set to be the same as the one set on the ear.
	 */
	public static final String SYNC_SERVER_TARGETS_WITH_EAR = "EARImportDataModel.SYNC_SERVER_TARGETS_WITH_EAR"; //$NON-NLS-1$

	/**
	 * Optional. This is a list of data models. This list must contain all non-utilty projects in
	 * the ear to be imported
	 */
	private static final String MODULE_MODELS_LIST = "EARImportDataModel.MODULE_MODELS_LIST"; //$NON-NLS-1$

	/**
	 * Optional. This is a list of data models. This list must contain all utility jars selected to
	 * be imported
	 */
	private static final String UTILITY_MODELS_LIST = "EARImportDataModel.UTILITY_MODELS_LIST"; //$NON-NLS-1$

	/**
	 * This is only to force validation for the nested projects; do not set.
	 */
	public static final String NESTED_PROJECTS_VALIDATION = "EARImportDataModel.NESTED_PROJECTS_VALIDATION"; //$NON-NLS-1$


	private WTPOperationDataModelListener nestedListener = new WTPOperationDataModelListener() {
		public void propertyChanged(WTPOperationDataModelEvent event) {
			if (event.getPropertyName().equals(J2EEArtifactImportDataModel.PROJECT_NAME)) {
				notifyListeners(NESTED_PROJECTS_VALIDATION);
			}
		}
	};

	private Hashtable ejbJarToClientJarModels = new Hashtable();

	private Hashtable clientJarToEjbJarModels = new Hashtable();

	/**
	 * Imports the specified Enterprise Application archive file into the specified Enterprise
	 * Application project.
	 * 
	 * @param earFileName
	 *            The path to the EAR file.
	 * @param earProjectName
	 *            The name of the Enterprise Application project where the Enterprise Application
	 *            should be imported.
	 * @since WTP 1.0
	 */
	public static void importArchive(String earFileName, String earProjectName) {
		EnterpriseApplicationImportDataModel dataModel = new EnterpriseApplicationImportDataModel();
		dataModel.setProperty(FILE_NAME, earFileName);
		dataModel.setProperty(PROJECT_NAME, earProjectName);
		try {
			dataModel.getDefaultOperation().run(null);
		} catch (InvocationTargetException e) {
			Logger.getLogger().logError(e);
		} catch (InterruptedException e) {
			Logger.getLogger().logError(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEImportDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(NESTED_MODULE_ROOT);
		addValidBaseProperty(UTILITY_LIST);
		addValidBaseProperty(MODULE_MODELS_LIST);
		addValidBaseProperty(EJB_CLIENT_LIST);
		addValidBaseProperty(UTILITY_MODELS_LIST);
		addValidBaseProperty(NESTED_PROJECTS_VALIDATION);
		addValidBaseProperty(SELECTED_MODELS_LIST);
		addValidBaseProperty(OVERWRITE_NESTED_PROJECTS);
		addValidBaseProperty(IMPORT_EAR_PROJECT);
		addValidBaseProperty(SYNC_SERVER_TARGETS_WITH_EAR);
		addValidBaseProperty(USE_ANNOTATIONS);
	}

	protected Object getDefaultProperty(String propertyName) {
		if (NESTED_MODULE_ROOT.equals(propertyName)) {
			return getLocation().toOSString();
		} else if (MODULE_MODELS_LIST.equals(propertyName) || UTILITY_LIST.equals(propertyName) || UTILITY_MODELS_LIST.equals(propertyName) || SELECTED_MODELS_LIST.equals(propertyName) || EJB_CLIENT_LIST.equals(propertyName)) {
			return Collections.EMPTY_LIST;
		} else if (OVERWRITE_NESTED_PROJECTS.equals(propertyName)) {
			return Boolean.FALSE;
		} else if (IMPORT_EAR_PROJECT.equals(propertyName)) {
			return Boolean.TRUE;
		} else if (SYNC_SERVER_TARGETS_WITH_EAR.equals(propertyName)) {
			return Boolean.TRUE;
		} else if (USE_ANNOTATIONS.equals(propertyName)) {
			return Boolean.FALSE;
		}
		return super.getDefaultProperty(propertyName);
	}

	public void propertyChanged(WTPOperationDataModelEvent event) {
		super.propertyChanged(event);
		if (event.getPropertyName().equals(J2EEArtifactImportDataModel.PROJECT_NAME)) {
			changeModuleCreationLocationForNameChange(getProjectModels());
		}
		if (event.getPropertyName().equals(J2EEArtifactImportDataModel.SERVER_TARGET_ID)) {
			changeModuleServerTargets((List) getProperty(MODULE_MODELS_LIST));
		}
	}

	/**
	 * @param list
	 */
	private void changeModuleServerTargets(List projectModels) {
		J2EEArtifactImportDataModel nestedModel = null;
		for (int i = 0; i < projectModels.size(); i++) {
			nestedModel = (J2EEArtifactImportDataModel) projectModels.get(i);
			nestedModel.setProperty(J2EEArtifactImportDataModel.SERVER_TARGET_ID, getProperty(ServerTargetDataModel.RUNTIME_TARGET_ID));
		}
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		if (OVERWRITE_NESTED_PROJECTS.equals(propertyName)) {
			List projectModels = getProjectModels();
			J2EEArtifactImportDataModel nestedModel = null;
			for (int i = 0; i < projectModels.size(); i++) {
				nestedModel = (J2EEArtifactImportDataModel) projectModels.get(i);
				nestedModel.setProperty(J2EEArtifactImportDataModel.OVERWRITE_PROJECT, propertyValue);
			}
		}
		boolean doSet = super.doSetProperty(propertyName, propertyValue);
		if (NESTED_MODULE_ROOT.equals(propertyName)) {
			changeModuleCreationLocationForAll(getProjectModels(), (String) propertyValue);
		} else if (FILE_NAME.equals(propertyName)) {
			setProperty(MODULE_MODELS_LIST, getModuleModels());
			setBooleanProperty(PRESERVE_PROJECT_METADATA, false);
			setProperty(UTILITY_LIST, null);
			EnterpriseApplicationCreationDataModel earProjectModel = (EnterpriseApplicationCreationDataModel) getJ2eeArtifactCreationDataModel();
			if (getModuleFile() != null) {
				earProjectModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, ArchiveUtil.getFastSpecVersion(getModuleFile()));
			}
			notifyValidValuesChange(PROJECT_NAME);
			if (getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
				setBooleanProperty(USE_ANNOTATIONS, false);
			notifyEnablementChange(USE_ANNOTATIONS);
		} else if (UTILITY_LIST.equals(propertyName)) {
			updateUtilityModels((List) propertyValue);
		} else if (PRESERVE_PROJECT_METADATA.equals(propertyName)) {
			if (getBooleanProperty(propertyName)) {
				setProperty(UTILITY_LIST, getUtilitiesForMetaDataImport(getEARFile()));
			}
			List projectModels = getProjectModels();
			J2EEArtifactImportDataModel nestedModel = null;
			for (int i = 0; i < projectModels.size(); i++) {
				nestedModel = (J2EEArtifactImportDataModel) projectModels.get(i);
				nestedModel.setProperty(J2EEArtifactImportDataModel.PRESERVE_PROJECT_METADATA, propertyValue);
			}
		} else if (SERVER_TARGET_ID.equals(propertyName)) {
			List projectModels = (List) getProperty(MODULE_MODELS_LIST);
			J2EEArtifactImportDataModel nestedModel = null;
			for (int i = 0; i < projectModels.size(); i++) {
				nestedModel = (J2EEArtifactImportDataModel) projectModels.get(i);
				nestedModel.setProperty(J2EEArtifactImportDataModel.SERVER_TARGET_ID, propertyValue);
			}
		} else if (USE_ANNOTATIONS.equals(propertyName)) {
			List projectModels = (List) getProperty(MODULE_MODELS_LIST);
			J2EEArtifactImportDataModel nestedModel = null;
			for (int i = 0; i < projectModels.size(); i++) {
				nestedModel = (J2EEArtifactImportDataModel) projectModels.get(i);
				if (nestedModel.getJ2eeArtifactCreationDataModel() instanceof J2EEModuleCreationDataModelOld)
					((J2EEModuleCreationDataModelOld) nestedModel.getJ2eeArtifactCreationDataModel()).setProperty(J2EEModuleCreationDataModelOld.USE_ANNOTATIONS, propertyValue);
			}
		} else if (MODULE_MODELS_LIST.equals(propertyName)) {
			List newList = new ArrayList();
			newList.addAll(getProjectModels());
			setProperty(SELECTED_MODELS_LIST, newList);
		} else if (PROJECT_NAME.equals(propertyName)) {
			List nestedModels = (List) getProperty(MODULE_MODELS_LIST);
			J2EEArtifactImportDataModel nestedModel = null;
			for (int i = 0; i < nestedModels.size(); i++) {
				nestedModel = (J2EEModuleImportDataModel) nestedModels.get(i);
				nestedModel.setProperty(J2EEModuleImportDataModel.EAR_PROJECT, propertyValue);
			}
			nestedModels = (List) getProperty(UTILITY_MODELS_LIST);
			for (int i = 0; i < nestedModels.size(); i++) {
				nestedModel = (J2EEUtilityJarImportDataModel) nestedModels.get(i);
				nestedModel.setProperty(J2EEUtilityJarImportDataModel.EAR_PROJECT, propertyValue);
			}
			IProject project =  ProjectCreationDataModel.getProjectHandleFromProjectName(getStringProperty(PROJECT_NAME));
			if (null != project && project.exists()) {
				IRuntime target = ServerCore.getProjectProperties(project).getRuntimeTarget();
				if (null != target) {
					setProperty(SERVER_TARGET_ID, target.getId());
				}
			}
		}
		if (OVERWRITE_PROJECT.equals(propertyName) || PROJECT_NAME.equals(propertyName) || DELETE_BEFORE_OVERWRITE_PROJECT.equals(propertyName)) {
			notifyEnablementChange(ServerTargetDataModel.RUNTIME_TARGET_ID);
		}
		return doSet;
	}

	private List getUtilitiesForMetaDataImport(EARFile earFile) {
		List list = getAllUtilitiesExceptEJBClients(earFile);
		for (int i = list.size() - 1; i > -1; i--) {
			Archive archive = (Archive) list.get(i);
			if (!archive.containsFile(".project")) { //$NON-NLS-1$
				list.remove(archive);
			}
		}
		return list;
	}

	public List getAllUtilitiesExceptEJBClients(EARFile earFile) {
		List clientList = (List) getProperty(EJB_CLIENT_LIST);
		List list = getAllUtilities(earFile);
		for (int i = list.size() - 1; i > -1; i--) {
			FileImpl file = (FileImpl) list.get(i);
			boolean shouldRemove = false;
			for (int j = 0; j < clientList.size() && !shouldRemove; j++) {
				J2EEUtilityJarImportDataModel model = (J2EEUtilityJarImportDataModel) clientList.get(j);
				if (model.getArchiveFile() == file) {
					shouldRemove = true;
				}
			}

			if (shouldRemove) {
				list.remove(i);
			}
		}
		return list;
	}

	public static List getAllUtilities(EARFile earFile) {
		List files = earFile.getFiles();
		List utilJars = new ArrayList();
		for (int i = 0; i < files.size(); i++) {
			FileImpl file = (FileImpl) files.get(i);
			if (file.isArchive() && !file.isModuleFile() && file.getURI().endsWith(".jar")) { //$NON-NLS-1$
				utilJars.add(file);
			}
			if (file.isWARFile()) {
				List webLib = ((WARFileImpl) file).getLibArchives();
				if (!webLib.isEmpty())
					utilJars.addAll(webLib);
			}
		}
		return utilJars;
	}

	protected boolean forceResetOnPreserveMetaData() {
		return false;
	}

	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(NESTED_MODEL_VALIDATION_HOOK)) {
			return OK_STATUS;
		}
		if (propertyName.equals(NESTED_PROJECTS_VALIDATION) || (propertyName.equals(PRESERVE_PROJECT_METADATA) && getBooleanProperty(PRESERVE_PROJECT_METADATA))) {
			boolean overwrite = getBooleanProperty(OVERWRITE_NESTED_PROJECTS);
			String earProjectName = getStringProperty(PROJECT_NAME);
			List subProjects = getSelectedModels();
			J2EEArtifactImportDataModel subDataModel = null;
			String tempProjectName = null;
			Archive tempArchive = null;
			IStatus tempStatus = null;
			Hashtable projects = new Hashtable(4);
			for (int i = 0; i < subProjects.size(); i++) {
				subDataModel = (J2EEArtifactImportDataModel) subProjects.get(i);
				tempProjectName = subDataModel.getStringProperty(J2EEArtifactImportDataModel.PROJECT_NAME);
				IStatus status = ProjectCreationDataModel.validateProjectName(tempProjectName);
				if (!status.isOK()) {
					return status;
				}
				tempArchive = subDataModel.getArchiveFile();
				if (!overwrite && subDataModel.getProject().exists()) {
					return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString("EARImportDataModel_UI_0", new Object[]{tempProjectName, tempArchive.getURI()})); //$NON-NLS-1$
				}
				tempStatus = subDataModel.validateDataModel();
				if (!tempStatus.isOK()) {
					return tempStatus;
				}
				if (tempProjectName.equals(earProjectName)) {
					return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString("EARImportDataModel_UI_1", new Object[]{tempProjectName, tempArchive.getURI()})); //$NON-NLS-1$
				} else if (!CoreFileSystemLibrary.isCaseSensitive()) {
					if (tempProjectName.toLowerCase().equals(earProjectName.toLowerCase())) {
						return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString("EARImportDataModel_UI_1", new Object[]{tempProjectName, tempArchive.getURI()})); //$NON-NLS-1$
					}
				}
				if (projects.containsKey(tempProjectName)) {
					return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString("EARImportDataModel_UI_2", new Object[]{tempProjectName, tempArchive.getURI(), ((Archive) projects.get(tempProjectName)).getURI()})); //$NON-NLS-1$
				} else if (!CoreFileSystemLibrary.isCaseSensitive()) {
					String lowerCaseProjectName = tempProjectName.toLowerCase();
					String currentKey = null;
					Enumeration keys = projects.keys();
					while (keys.hasMoreElements()) {
						currentKey = (String) keys.nextElement();
						if (currentKey.toLowerCase().equals(lowerCaseProjectName)) {
							return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString("EARImportDataModel_UI_2", new Object[]{tempProjectName, tempArchive.getURI(), ((Archive) projects.get(currentKey)).getURI()})); //$NON-NLS-1$
						}
					}
				}
				projects.put(tempProjectName, tempArchive);
			}
		} else if (propertyName.equals(PROJECT_NAME) && !getBooleanProperty(IMPORT_EAR_PROJECT)) {
			return OK_STATUS;
		}
		//TODO: check context root is not inside current working
		// directory...this is invalid
		return super.doValidateProperty(propertyName);
	}

	private void changeModuleCreationLocationForAll(List projects, String property) {
		J2EEArtifactImportDataModel model = null;
		for (int i = 0; null != projects && i < projects.size(); i++) {
			model = (J2EEArtifactImportDataModel) projects.get(i);
			IPath newPath = new Path(property);
			newPath = newPath.append((String) model.getProperty(J2EEArtifactCreationDataModelOld.PROJECT_NAME));
			model.setProperty(J2EEArtifactCreationDataModelOld.PROJECT_LOCATION, newPath.toOSString());
		}
	}

	private void changeModuleCreationLocationForNameChange(List projects) {
		J2EEArtifactImportDataModel model = null;
		for (int i = 0; null != projects && i < projects.size(); i++) {
			model = (J2EEArtifactImportDataModel) projects.get(i);
			if (isSet(NESTED_MODULE_ROOT)) {
				IPath newPath = new Path((String) getProperty(NESTED_MODULE_ROOT));
				newPath = newPath.append((String) model.getProperty(J2EEArtifactCreationDataModelOld.PROJECT_NAME));
				model.setProperty(J2EEArtifactCreationDataModelOld.PROJECT_LOCATION, newPath.toOSString());
			} else {
				model.setProperty(J2EEArtifactCreationDataModelOld.PROJECT_LOCATION, null);
			}
		}
	}

	private IPath getLocation() {
		return ResourcesPlugin.getWorkspace().getRoot().getLocation();
	}

	private void trimSelection() {
		boolean modified = false;
		List selectedList = getSelectedModels();
		List allList = getProjectModels();
		for (int i = selectedList.size() - 1; i > -1; i--) {
			if (!allList.contains(selectedList.get(i))) {
				modified = true;
				selectedList.remove(i);
			}
		}
		if (modified) {
			List newList = new ArrayList();
			newList.addAll(selectedList);
			setProperty(SELECTED_MODELS_LIST, newList);
		}
	}

	private void updateUtilityModels(List utilityJars) {
		updateUtilityModels(utilityJars, SELECTED_MODELS_LIST, UTILITY_MODELS_LIST);
	}

	private void updateUtilityModels(List utilityJars, String selectedProperty, String listTypeProperty) {
		boolean allSelected = true;
		List selectedList = (List) getProperty(selectedProperty);
		List allList = getProjectModels();
		if (selectedList.size() == allList.size()) {
			for (int i = 0; i < selectedList.size() && allSelected; i++) {
				if (!selectedList.contains(allList.get(i)) || !allList.contains(selectedList.get(i))) {
					allSelected = false;
				}
			}
		} else {
			allSelected = false;
		}
		List utilityModels = (List) getProperty(listTypeProperty);
		Archive currentArchive = null;
		J2EEArtifactImportDataModel currentUtilityModel = null;
		boolean utilityJarsModified = false;
		//Add missing
		for (int i = 0; null != utilityJars && i < utilityJars.size(); i++) {
			currentArchive = (Archive) utilityJars.get(i);
			boolean added = false;
			for (int j = 0; utilityModels != null && j < utilityModels.size() && !added; j++) {
				currentUtilityModel = (J2EEArtifactImportDataModel) utilityModels.get(j);
				if (currentUtilityModel.getArchiveFile() == currentArchive) {
					added = true;
				}
			}
			if (!added) {
				if (!isSet(listTypeProperty)) {
					utilityModels = new ArrayList();
					setProperty(listTypeProperty, utilityModels);
				}
				J2EEUtilityJarImportDataModel model = new J2EEUtilityJarImportDataModel();
				model.setProperty(J2EEUtilityJarImportDataModel.FILE, currentArchive);
				model.setProperty(J2EEUtilityJarImportDataModel.EAR_PROJECT, getStringProperty(PROJECT_NAME));
				model.getJ2eeArtifactCreationDataModel().setBooleanProperty(J2EEArtifactCreationDataModelOld.ADD_SERVER_TARGET, false);
				model.setProperty(PRESERVE_PROJECT_METADATA, getProperty(PRESERVE_PROJECT_METADATA));
				model.setProperty(OVERWRITE_PROJECT, getProperty(OVERWRITE_NESTED_PROJECTS));
				utilityModels.add(model);
				model.addListener(nestedListener);
				utilityJarsModified = true;
			}
		} //Remove extras
		for (int i = utilityModels.size() - 1; i >= 0; i--) {
			currentUtilityModel = (J2EEArtifactImportDataModel) utilityModels.get(i);
			currentArchive = currentUtilityModel.getArchiveFile();
			if (null == utilityJars || !utilityJars.contains(currentArchive)) {
				currentUtilityModel.removeListener(nestedListener);
				currentUtilityModel.dispose();
				utilityModels.remove(currentUtilityModel);
				utilityJarsModified = true;
			}
		}
		allList = getProjectModels();
		if (allSelected) {
			List newList = new ArrayList();
			newList.addAll(allList);
			setProperty(SELECTED_MODELS_LIST, newList);
		} else {
			trimSelection();
		}
		if (utilityJarsModified) {
			notifyListeners(NESTED_PROJECTS_VALIDATION);
		}
	}

	private List getModuleModels() {
		if (getArchiveFile() == null)
			return Collections.EMPTY_LIST;
		List moduleFiles = getEARFile().getModuleFiles();
		List moduleModels = new ArrayList();
		List clientJarArchives = new ArrayList();
		J2EEArtifactImportDataModel model;
		String earProjectName = getProject().getName();
		List defaultModuleNames = new ArrayList();
		List collidingModuleNames = null;
		Hashtable ejbJarsWithClients = new Hashtable();
		for (int i = 0; i < moduleFiles.size(); i++) {
			model = null;
			ModuleFile temp = (ModuleFile) moduleFiles.get(i);
			if (temp.isApplicationClientFile()) {
				model = new AppClientModuleImportDataModel();
			} else if (temp.isWARFile()) {
				WebModuleExtension webExt = EarModuleManager.getWebModuleExtension();
				if (webExt != null) {
					model = webExt.createImportDataModel();
					WebModule webModule = (WebModule) getEARFile().getModule(temp.getURI(), null);
					if (null != webModule && null != webModule.getContextRoot()) {
						model.setProperty(AddWebModuleToEARDataModel.CONTEXT_ROOT, webModule.getContextRoot());
					}
				}
			} else if (temp.isEJBJarFile()) {
				EjbModuleExtension ejbExt = EarModuleManager.getEJBModuleExtension();
				if (ejbExt != null) {
					model = ejbExt.createImportDataModel();
				}
				EJBJar jar = ((EJBJarFile) temp).getDeploymentDescriptor();
				if (jar != null) {
					if (jar.getEjbClientJar() != null) {
						String clientName = jar.getEjbClientJar();
						try {
							Archive clientArchive = (Archive) getEARFile().getFile(clientName);
							clientJarArchives.add(clientArchive);
							ejbJarsWithClients.put(model, clientArchive);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
			} else if (temp.isRARFile()) {
				JcaModuleExtension rarExt = EarModuleManager.getJCAModuleExtension();
				if (rarExt != null) {
					model = rarExt.createImportDataModel();
				}
			}
			if (model != null) {
				model.setProperty(FILE, temp);
				model.setProperty(J2EEModuleImportDataModel.EAR_PROJECT, earProjectName);
				model.setBooleanProperty(J2EEModuleCreationDataModelOld.ADD_TO_EAR, false);
				model.setProperty(OVERWRITE_PROJECT, getProperty(OVERWRITE_PROJECT));
				model.setProperty(PRESERVE_PROJECT_METADATA, getProperty(PRESERVE_PROJECT_METADATA));
				model.setProperty(SERVER_TARGET_ID, getProperty(ServerTargetDataModel.RUNTIME_TARGET_ID));
				model.addListener(this);
				model.addListener(nestedListener);
				moduleModels.add(model);
				String moduleName = model.getStringProperty(J2EEModuleImportDataModel.PROJECT_NAME);
				if (defaultModuleNames.contains(moduleName)) {
					if (collidingModuleNames == null) {
						collidingModuleNames = new ArrayList();
					}
					collidingModuleNames.add(moduleName);
				} else {
					defaultModuleNames.add(moduleName);
				}
			}
		}
		updateUtilityModels(clientJarArchives, EJB_CLIENT_LIST, EJB_CLIENT_LIST);
		List clientModelList = (List) getProperty(EJB_CLIENT_LIST);
		Enumeration ejbModels = ejbJarsWithClients.keys();
		ejbJarToClientJarModels.clear();
		clientJarToEjbJarModels.clear();
		while (ejbModels.hasMoreElements()) {
			Object ejbModel = ejbModels.nextElement();
			Object archive = ejbJarsWithClients.get(ejbModel);
			Object clientModel = null;
			for (int i = 0; clientModel == null && i < clientModelList.size(); i++) {
				if (((J2EEArtifactImportDataModel) clientModelList.get(i)).getArchiveFile() == archive) {
					clientModel = clientModelList.get(i);
				}
			}
			ejbJarToClientJarModels.put(ejbModel, clientModel);
			clientJarToEjbJarModels.put(clientModel, ejbModel);
		}

		for (int i = 0; collidingModuleNames != null && i < moduleModels.size(); i++) {
			model = (J2EEModuleImportDataModel) moduleModels.get(i);
			String moduleName = model.getStringProperty(J2EEModuleImportDataModel.PROJECT_NAME);
			if (collidingModuleNames.contains(moduleName)) {
				ModuleFile module = model.getModuleFile();
				String suffix = null;
				if (module.isApplicationClientFile()) {
					suffix = "_AppClient"; //$NON-NLS-1$
				} else if (module.isWARFile()) {
					suffix = "_WEB"; //$NON-NLS-1$
				} else if (module.isEJBJarFile()) {
					suffix = "_EJB"; //$NON-NLS-1$
				} else if (module.isRARFile()) {
					suffix = "_JCA"; //$NON-NLS-1$
				}
				if (defaultModuleNames.contains(moduleName + suffix)) {
					int count = 1;
					for (; defaultModuleNames.contains(moduleName + suffix + count) && count < 10; count++);
					suffix += count;
				}
				model.setProperty(J2EEModuleImportDataModel.PROJECT_NAME, moduleName + suffix);
			}
		}
		return moduleModels;
	}

	protected J2EEArtifactCreationDataModelOld createJ2EEProjectCreationDataModel() {
		return new EnterpriseApplicationCreationDataModel();
	}

	protected int getType() {
		return XMLResource.APPLICATION_TYPE;
	}

	protected boolean openArchive(String uri) throws OpenFailureException {
		setArchiveFile(CommonarchiveFactory.eINSTANCE.openEARFile(getArchiveOptions(), uri));
		if (getArchiveFile() == null)
			return false;
		return true;
	}

	public final EARFile getEARFile() {
		return (EARFile) getModuleFile();
	}

	public WTPOperation getDefaultOperation() {
		return new EnterpriseApplicationImportOperation(this);
	}

	public boolean handlesArchive(Archive anArchive) {
		if (getBooleanProperty(PRESERVE_PROJECT_METADATA)) {
			if (anArchive.containsFile(".project")) { //$NON-NLS-1$
				return true;
			}
		}
		List temp = new ArrayList();
		List tempList = (List) getProperty(MODULE_MODELS_LIST);
		if (null != tempList) {
			temp.addAll(tempList);
		}
		tempList = (List) getProperty(EJB_CLIENT_LIST);
		if (null != tempList) {
			temp.addAll(tempList);
		}
		tempList = getSelectedModels();
		for (int i = 0; i < tempList.size(); i++) {
			if (!temp.contains(tempList.get(i))) {
				temp.add(tempList.get(i));
			}
		}
		J2EEArtifactImportDataModel importDM = null;
		for (int i = 0; i < temp.size(); i++) {
			importDM = (J2EEArtifactImportDataModel) temp.get(i);
			if (importDM.getArchiveFile() == anArchive) {
				return true;
			}
		}
		return false;
	}

	public List getProjectModels() {
		List temp = new ArrayList();
		List tempList = (List) getProperty(MODULE_MODELS_LIST);
		if (null != tempList) {
			temp.addAll(tempList);
		}
		tempList = (List) getProperty(UTILITY_MODELS_LIST);
		if (null != tempList) {
			temp.addAll(tempList);
		}
		tempList = (List) getProperty(EJB_CLIENT_LIST);
		if (null != tempList) {
			temp.addAll(tempList);
		}
		return temp;
	}

	public List getUnhandledProjectModels() {
		List handled = removeHandledModels(getProjectModels(), getProjectModels(), false);
		List all = getProjectModels();
		all.removeAll(handled);
		return all;
	}

	public List getSelectedModels() {
		return (List) getProperty(SELECTED_MODELS_LIST);
	}

	private List removeHandledModels(List listToPrune, List modelsToCheck, boolean addModels) {
		List newList = new ArrayList();
		newList.addAll(listToPrune);
		J2EEArtifactImportDataModel model = null;
		for (int i = 0; i < modelsToCheck.size(); i++) {
			model = (J2EEArtifactImportDataModel) modelsToCheck.get(i);
			model.extractHandled(newList, addModels);
		}
		return newList;
	}

	public List getHandledSelectedModels() {
		List selectedModels = getSelectedModels();
		return removeHandledModels(selectedModels, selectedModels, true);
	}

	public int getJ2EEVersion() {
		EARFile ef = getEARFile();
		return null == ef ? J2EEVersionConstants.J2EE_1_2_ID : ArchiveUtil.getFastSpecVersion(ef);
	}

	protected Boolean basicIsEnabled(String propertyName) {
		Boolean enabled = super.basicIsEnabled(propertyName);
		if (null != enabled && !enabled.booleanValue()) {
			return enabled;
		} else if (propertyName.equals(ServerTargetDataModel.RUNTIME_TARGET_ID)) {
			IProject project = ProjectCreationDataModel.getProjectHandleFromProjectName(getStringProperty(PROJECT_NAME));
			if (null == project || !project.exists()) {
				return Boolean.TRUE;
			}
			boolean createNew = getBooleanProperty(OVERWRITE_PROJECT) && getBooleanProperty(DELETE_BEFORE_OVERWRITE_PROJECT);
			if (!createNew) {
				return Boolean.FALSE;
			}
		} else if (propertyName.equals(PRESERVE_PROJECT_METADATA)) {
			EARFile earFile = getEARFile();
			if (null == earFile || !earFile.containsFile(EAREditModel.MODULE_MAP_URI)) {
				return Boolean.FALSE;
			}
			List moduleModels = (List) getProperty(MODULE_MODELS_LIST);
			ProjectDescriptionReader reader = new ProjectDescriptionReader();
			String[] natures = new String[]{IEJBNatureConstants.NATURE_ID, IApplicationClientNatureConstants.NATURE_ID, IWebNatureConstants.J2EE_NATURE_ID, IConnectorNatureConstants.NATURE_ID};
			for (int i = 0; i < moduleModels.size(); i++) {
				J2EEArtifactImportDataModel model = (J2EEArtifactImportDataModel) moduleModels.get(i);
				if (model.getArchiveFile().containsFile(ProjectDescription.DESCRIPTION_FILE_NAME)) {
					try {
						File dotProject = model.getArchiveFile().getFile(ProjectDescription.DESCRIPTION_FILE_NAME);
						ProjectDescription description = reader.read(new InputSource(dotProject.getInputStream()));
						boolean foundNature = false;
						for (int j = 0; !foundNature && j < natures.length; j++) {
							foundNature = description.hasNature(natures[j]);
						}
						if (!foundNature) {
							return Boolean.FALSE;
						}
					} catch (FileNotFoundException e) {
						return Boolean.FALSE;
					} catch (IOException e) {
						return Boolean.FALSE;
					}
				} else {
					return Boolean.FALSE;
				}
			}
		} else if (propertyName.equals(USE_ANNOTATIONS)) {
			if (getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
				return Boolean.FALSE;
			return Boolean.TRUE;
		}
		return Boolean.TRUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEImportDataModel#dispose()
	 */
	public void dispose() {
		super.dispose();
		List list = getProjectModels();
		for (int i = 0; i < list.size(); i++) {
			((WTPOperationDataModel) list.get(i)).dispose();
		}
		EARFile earFile = getEARFile();
		if (earFile != null)
			earFile.close();
	}

	public J2EEArtifactImportDataModel getMatchingEJBJarOrClient(J2EEArtifactImportDataModel model) {
		if (clientJarToEjbJarModels.containsKey(model)) {
			return (J2EEArtifactImportDataModel) clientJarToEjbJarModels.get(model);
		} else if (ejbJarToClientJarModels.containsKey(model)) {
			return (J2EEArtifactImportDataModel) ejbJarToClientJarModels.get(model);
		} else {
			return null;
		}
	}
}