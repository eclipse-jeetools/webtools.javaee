/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.eclipse.core.internal.resources.ProjectDescription;
import org.eclipse.core.internal.resources.ProjectDescriptionReader;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.SaveFilter;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.wst.common.componentcore.internal.operation.ComponentCreationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.frameworks.internal.operations.WTPPropertyDescriptor;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.xml.sax.InputSource;

//TODO delete
/**
 * @deprecated
 *
 */
public abstract class J2EEArtifactImportDataModel extends WTPOperationDataModel {
	/**
	 * Required, type String
	 */
	public static final String FILE_NAME = "J2EEImportDataModel.FILE_NAME"; //$NON-NLS-1$
	/**
	 * Optional, type Archive, used when a file is available as an object rather than a url (i.e.
	 * using the FILE_NAME property will not work).
	 */
	public static final String FILE = "J2EEImportDataModel.FILE"; //$NON-NLS-1$
	/**
	 * Optional, type Boolean defaults to false
	 */
	public static final String OVERWRITE_PROJECT = "J2EEImportDataModel.OVERWRITE_PROJECT"; //$NON-NLS-1$
	/**
	 * Optional, type Boolean defaults to false
	 */
	public static final String DELETE_BEFORE_OVERWRITE_PROJECT = "J2EEImportDataModel.DELETE_BEFORE_OVERWRITE_PROJECT"; //$NON-NLS-1$
	/**
	 * Optional, type SaveFilter, default is null
	 */
	public static final String SAVE_FILTER = "J2EEImportDataModel.SAVE_FILTER"; //$NON-NLS-1$
	/**
	 * Optional, type Boolean, default false this is used only when importing modules in with an ear
	 */
	public static final String PRESERVE_PROJECT_METADATA = "J2EEImportDataModel.IS_LIBRARY"; //$NON-NLS-1$
	/**
	 * Do not set this property.
	 */
	public static final String BINARY = "J2EEImportDataModel.BINARY"; //$NON-NLS-1$
	public static final String OVERWRITE_HANDLER = "J2EEImportDataModel.OVERWRITE_HANDLER"; //$NON-NLS-1$
	public static final String PROJECT_NAME = "J2EEImportDataModel.PROJECT_NAME"; //$NON-NLS-1$
	public static final String NESTED_MODEL_J2EE_PROJECT_CREATION = "J2EEImportDataModel.NESTED_MODEL_J2EE_PROJECT_CREATION"; //$NON-NLS-1$
	/**
	 * Optional - Should the archive be closed on dispose?
	 * 
	 * @type - Boolean, default is True
	 */
	public static final String CLOSE_ARCHIVE_ON_DISPOSE = "J2EEImportDataModel.closeArchiveOnDispose"; //$NON-NLS-1$
	/**
	 * Optiona - type String, default = getArchiveFile().getURI() or "" if getArchivefile() is null
	 * This is used when adding a module to the ear.
	 */
	public static final String URI_FOR_MODULE_MAPPING = "J2EEImportDataModel.URI_FOR_MODULE_MAPPING"; //$NON-NLS-1$

	public static final String FILE_SELECTION_HISTORY = "J2EEImportDataModel.FILE_SELECTION_HISTORY"; //$NON-NLS-1$

	private static final String DEFAULT_PROJECT_NAME = "J2EEImportDataModel.DEFAULT_PROJECT_NAME"; //$NON-NLS-1$

	/**
	 * Extended attributes
	 */
	public static final String SERVER_TARGET_ID = FlexibleProjectCreationDataModel.SERVER_TARGET_ID;

	private J2EEComponentCreationDataModel j2eeArtifactCreationDataModel;
	private Archive archiveFile;
	private OpenFailureException cachedOpenFailureException = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(FILE_NAME);
		addValidBaseProperty(FILE);
		addValidBaseProperty(OVERWRITE_PROJECT);
		addValidBaseProperty(DELETE_BEFORE_OVERWRITE_PROJECT);
		addValidBaseProperty(SAVE_FILTER);
		addValidBaseProperty(PRESERVE_PROJECT_METADATA);
		addValidBaseProperty(BINARY);
		addValidBaseProperty(OVERWRITE_HANDLER);
		addValidBaseProperty(PROJECT_NAME);
		addValidBaseProperty(CLOSE_ARCHIVE_ON_DISPOSE);
		addValidBaseProperty(URI_FOR_MODULE_MAPPING);
		addValidBaseProperty(FILE_SELECTION_HISTORY);
		addValidBaseProperty(DEFAULT_PROJECT_NAME);
		super.initValidBaseProperties();
	}

	protected void initNestedModels() {
		super.initNestedModels();
		j2eeArtifactCreationDataModel = createJ2EEProjectCreationDataModel();
		j2eeArtifactCreationDataModel.setBooleanProperty(ComponentCreationDataModel.CREATE_DEFAULT_FILES, false);
		j2eeArtifactCreationDataModel.addListener(this);
		addNestedModel(NESTED_MODEL_J2EE_PROJECT_CREATION, j2eeArtifactCreationDataModel);
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(BINARY)) {
			return getProperty(PRESERVE_PROJECT_METADATA);
		}
		if (propertyName.equals(PRESERVE_PROJECT_METADATA) || propertyName.equals(OVERWRITE_PROJECT) || propertyName.equals(DELETE_BEFORE_OVERWRITE_PROJECT)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(PROJECT_NAME)) {
			return j2eeArtifactCreationDataModel.getProperty(FlexibleProjectCreationDataModel.PROJECT_NAME);
		} else if (propertyName.equals(CLOSE_ARCHIVE_ON_DISPOSE)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(URI_FOR_MODULE_MAPPING)) {
			if (null == getArchiveFile()) {
				return ""; //$NON-NLS-1$
			}
			return getArchiveFile().getURI();
		} else if (propertyName.equals(DEFAULT_PROJECT_NAME)) {
			return Boolean.TRUE;
		}
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		if (propertyName.equals(BINARY)) {
			throw new RuntimeException("Property " + BINARY + " may not be set."); //$NON-NLS-1$  //$NON-NLS-2$
		}
		if (propertyName.equals(FILE)) {
			setProperty(FILE_NAME, null);
			super.doSetProperty(propertyName, propertyValue);
			archiveFile = (Archive) propertyValue;
			updateDefaultProjectName();
			return true;
		}
		boolean doSet = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(FILE_SELECTION_HISTORY)) {
			notifyValidValuesChange(FILE_NAME);
		}

		if (propertyName.equals(PRESERVE_PROJECT_METADATA) && forceResetOnPreserveMetaData()) {
			if (getBooleanProperty(PRESERVE_PROJECT_METADATA) && null != getArchiveFile()) {
				if (getArchiveFile().containsFile(IProjectDescription.DESCRIPTION_FILE_NAME)) {
					try {
						File dotProject = getArchiveFile().getFile(IProjectDescription.DESCRIPTION_FILE_NAME);
						ProjectDescriptionReader reader = new ProjectDescriptionReader();
						ProjectDescription description = reader.read(new InputSource(dotProject.getInputStream()));
						setProperty(PROJECT_NAME, description.getName());
					} catch (FileNotFoundException e) {
						//Ignore
					} catch (IOException e) {
						//Ignore
					}
				}
			}
		}

		//		if (propertyName.equals(PRESERVE_PROJECT_METADATA) && forceResetOnPreserveMetaData() &&
		// isSet(PROJECT_NAME)) {
		//				if (null != moduleFile) {
		//				
		//				
		//				Path path = new Path(moduleFile.getURI());
		//				String defaultProjectName = path.segment(path.segmentCount() - 1);
		//				if (defaultProjectName.indexOf(".") > 0) { //$NON-NLS-1$
		//					defaultProjectName = defaultProjectName.substring(0, defaultProjectName.indexOf("."));
		// //$NON-NLS-1$
		//				}
		//				if (!getBooleanProperty(PRESERVE_PROJECT_METADATA)) {
		//					String baseName = defaultProjectName;
		//					IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		//					for (int i = 1; i < 10 && root.getProject(defaultProjectName).exists(); i++) {
		//						defaultProjectName = baseName + Integer.toString(i);
		//					}
		//				}
		//				setProperty(PROJECT_NAME, defaultProjectName);
		//			}
		//		}
		//        if (propertyName.equals(OVERWRITE_PROJECT)) {
		//            updateDefaultProjectName();
		//        }
		if (propertyName.equals(PROJECT_NAME)) {
			j2eeArtifactCreationDataModel.setProperty(ComponentCreationDataModel.PROJECT_NAME, propertyValue);
		} else if (propertyName.equals(SAVE_FILTER) && archiveFile != null) {
			archiveFile.setSaveFilter(getSaveFilter());
		}
		if (doSet) {
			if (FILE_NAME.equals(propertyName)) {
				boolean success = false;
				try {
					cachedOpenFailureException = null;
					success = handleArchiveSetup((String) propertyValue);
				} catch (OpenFailureException oe) {
					cachedOpenFailureException = oe;
				}
				if (success)
					updateDefaultProjectName();
			}
		}
		return doSet;
	}

	protected boolean forceResetOnPreserveMetaData() {
		return true;
	}

	private void updateDefaultProjectName() {
		if (null != archiveFile && getBooleanProperty(DEFAULT_PROJECT_NAME)) {
			Path path = new Path(archiveFile.getURI());
			String defaultProjectName = path.segment(path.segmentCount() - 1);
			if (defaultProjectName.indexOf('.') > 0) {
				defaultProjectName = defaultProjectName.substring(0, defaultProjectName.lastIndexOf('.'));
				if (defaultProjectName.indexOf('.') > 0) {
					defaultProjectName = defaultProjectName.replace('.', '_');
				}
			}
			if (!getBooleanProperty(OVERWRITE_PROJECT)) {
				String baseName = defaultProjectName;
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				for (int i = 1; i < 10 && root.getProject(defaultProjectName).exists(); i++) {
					defaultProjectName = baseName + Integer.toString(i);
				}
			}
			j2eeArtifactCreationDataModel.setProperty(ComponentCreationDataModel.PROJECT_NAME, defaultProjectName);
			notifyDefaultChange(PROJECT_NAME);
			setBooleanProperty(DEFAULT_PROJECT_NAME, true);
		}
	}

	private boolean handleArchiveSetup(String fileName) throws OpenFailureException {
		boolean archiveStatus = true;
		if (archiveFile != null) {
			archiveFile.close();
			archiveFile = null;
		}
		String uri = getStringProperty(FILE_NAME);
		if (!archiveExistsOnFile())
			return false;
		archiveStatus = openArchive(uri);
		archiveFile.setSaveFilter(getSaveFilter());
		return archiveStatus;
	}

	protected abstract boolean openArchive(String uri) throws OpenFailureException;

	private boolean closeModuleFile() {
		if (null != archiveFile) {
			archiveFile.close();
			archiveFile = null;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (NESTED_MODEL_VALIDATION_HOOK.equals(propertyName)) {
			return OK_STATUS;
		}
		if (PROJECT_NAME.equals(propertyName)) {
			IProject proj = j2eeArtifactCreationDataModel.getProject();
			IStatus status = ProjectCreationDataModel.validateProjectName(proj.getName());
			if (!status.isOK()) {
				return status;
			}
//			if (!getBooleanProperty(DELETE_BEFORE_OVERWRITE_PROJECT)) {
//				//now we have an existing project we are going to overwrite
//				String[] natures = (String[]) projectDataModel.getProperty(ProjectCreationDataModel.PROJECT_NATURES);
//				for (int i = 0; null != natures && i < natures.length; i++) {
//					try {
//						if (!proj.hasNature(natures[i])) {
//							return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString("importWrongType", new Object[]{project.getName()}));//$NON-NLS-1$
//						}
//					} catch (CoreException e) {
//					}
//				}
//				J2EENature nature = J2EENature.getRegisteredRuntime(project);
//				if (null != nature && nature.getJ2EEVersion() < getJ2EEVersion()) {
//					return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString("importWrongVersion", new Object[]{project.getName()}));//$NON-NLS-1$
//				}
//			} else {
				String name = getStringProperty(PROJECT_NAME);
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				return workspace.validateName(name, IResource.PROJECT);
//			}
//			return OK_STATUS;
		}
		if (FILE_NAME.equals(propertyName)) {
			String fileName = (String) getProperty(FILE_NAME);
			if (fileName != null && fileName.equals("")) { //$NON-NLS-1$
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.ARCHIVE_FILE_NAME_EMPTY_ERROR, new Object[]{ArchiveUtil.getModuleFileTypeName(getType())})); //$NON-NLS-1$);
			} else if (cachedOpenFailureException != null) {
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(cachedOpenFailureException.getMessage())); //$NON-NLS-1$);
			} else if (fileName != null && !archiveExistsOnFile()) {
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.FILE_DOES_NOT_EXIST_ERROR, new Object[]{ArchiveUtil.getModuleFileTypeName(getType())}));
			}
		}
		return super.doValidateProperty(propertyName);
	}

	protected int getJ2EEVersion() {
		return 0;
	}

	protected abstract J2EEComponentCreationDataModel createJ2EEProjectCreationDataModel();

	/*
	 * @see XMLResource#APP_CLIENT_TYPE
	 * @see XMLResource#APPLICATION_TYPE
	 * @see XMLResource#EJB_TYPE
	 * @see XMLResource#WEB_APP_TYPE
	 * @see XMLResource#RAR_TYPE
	 */
	protected abstract int getType();

	public final J2EEComponentCreationDataModel getJ2eeArtifactCreationDataModel() {
		return j2eeArtifactCreationDataModel;
	}

	private boolean archiveExistsOnFile() {
		String jarName = (String) getProperty(FILE_NAME);
		if (jarName != null && jarName.length() > 0) {
			java.io.File file = new java.io.File(jarName);
			return file.exists() && !file.isDirectory();
		}
		return false;
	}

	public void dispose() {
		if (getBooleanProperty(CLOSE_ARCHIVE_ON_DISPOSE))
			closeModuleFile();
		super.dispose();
	}

	protected final void setArchiveFile(Archive archiveFile) {
		this.archiveFile = archiveFile;
	}

	public final Archive getArchiveFile() {
		return archiveFile;
	}

	public final ModuleFile getModuleFile() {
		return (ModuleFile) archiveFile;
	}

	public IProject getProject() {
		return j2eeArtifactCreationDataModel.getTargetProject();
	}

	protected final ArchiveOptions getArchiveOptions() {
		ArchiveOptions opts = new ArchiveOptions();
		opts.setIsReadOnly(true);
		return opts;
	}

	private SaveFilter getSaveFilter() {
		return (SaveFilter) getProperty(SAVE_FILTER);
	}

	public void propertyChanged(WTPOperationDataModelEvent event) {
		if (event.getDataModel().equals(j2eeArtifactCreationDataModel) && event.getPropertyName().equals(ComponentCreationDataModel.PROJECT_NAME)) {
			setProperty(PROJECT_NAME, j2eeArtifactCreationDataModel.getStringProperty(ComponentCreationDataModel.PROJECT_NAME));
			setBooleanProperty(DEFAULT_PROJECT_NAME, false);
		}
		super.propertyChanged(event);
	}

	public void extractHandled(List newList, boolean addModels) {
		//Default
	}

	protected WTPPropertyDescriptor[] doGetValidPropertyDescriptors(String propertyName) {
//		if (propertyName.equals(PROJECT_NAME)) {
//			int j2eeVersion = getJ2EEVersion();
//			IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
//			ArrayList projectList = new ArrayList();
//			String[] natureIds = (String[]) j2eeArtifactCreationDataModel.getProjectDataModel().getProperty(ProjectCreationDataModel.PROJECT_NATURES);
//			String j2eeNatureID = null;
//			for (int j = 0; null == j2eeNatureID && j < natureIds.length; j++) {
//				if (IEARNatureConstants.NATURE_ID.equals(natureIds[j]) || IEJBNatureConstants.NATURE_ID.equals(natureIds[j]) || IWebNatureConstants.J2EE_NATURE_ID.equals(natureIds[j]) || IConnectorNatureConstants.NATURE_ID.equals(natureIds[j]) || IApplicationClientNatureConstants.NATURE_ID.equals(natureIds[j])) {
//					j2eeNatureID = natureIds[j];
//				}
//			}
//			J2EENature j2eeNature = null;
//			for (int i = 0; i < projects.length && j2eeNatureID != null; i++) {
//				try {
//					j2eeNature = (J2EENature) projects[i].getNature(j2eeNatureID);
//					if (j2eeNature != null) {
//						if (j2eeVersion <= j2eeNature.getJ2EEVersion()) {
//							projectList.add(projects[i].getName());
//						}
//					}
//				} catch (CoreException e) {
//				}
//			}
//			String[] projectNames = new String[projectList.size()];
//			for (int i = 0; i < projectNames.length; i++) {
//				projectNames[i] = (String) projectList.get(i);
//			}
//			return WTPPropertyDescriptor.createDescriptors(projectNames);
//		} else if (propertyName.equals(FILE_NAME)) {
//			String[] sourceNames = (String[]) getProperty(FILE_SELECTION_HISTORY);
//			return WTPPropertyDescriptor.createDescriptors(sourceNames);
//		}
		return super.doGetValidPropertyDescriptors(propertyName);
	}
}