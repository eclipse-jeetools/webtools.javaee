/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.SaveFilter;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.project.datamodel.properties.IFlexibleJavaProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IComponentCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelListener;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public abstract class J2EEArtifactImportDataModelProvider extends AbstractDataModelProvider implements IJ2EEComponentImportDataModelProperties, IDataModelListener {

	private static final String DEFAULT_COMPONENT_NAME = "J2EEImportDataModel.DEFAULT_PROJECT_NAME"; //$NON-NLS-1$

	/**
	 * Extended attributes
	 */
	public static final String SERVER_TARGET_ID = IFlexibleJavaProjectCreationDataModelProperties.SERVER_TARGET_ID;

	private IDataModel componentCreationDM;
	private OpenFailureException cachedOpenFailureException = null;

	public String[] getPropertyNames() {
		return new String[]{FILE_NAME, FILE, SAVE_FILTER, OVERWRITE_HANDLER, CLOSE_ARCHIVE_ON_DISPOSE, COMPONENT, COMPONENT_NAME, PROJECT_NAME, DEFAULT_COMPONENT_NAME, FILE_SELECTION_HISTORY};
	}

	public void init() {
		super.init();
		componentCreationDM = createJ2EEComponentCreationDataModel();
		componentCreationDM.setBooleanProperty(IComponentCreationDataModelProperties.CREATE_DEFAULT_FILES, false);
		componentCreationDM.addListener(this);
		model.addNestedModel(NESTED_MODEL_J2EE_COMPONENT_CREATION, componentCreationDM);
	}

	private IProject getProject() {
		String projName = model.getStringProperty(COMPONENT_NAME);
		return ProjectUtilities.getProject(projName);
	}


	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(COMPONENT_NAME)) {
			return componentCreationDM.getProperty(IComponentCreationDataModelProperties.COMPONENT_NAME);
		} else if (propertyName.equals(PROJECT_NAME)) {
			return componentCreationDM.getProperty(IComponentCreationDataModelProperties.PROJECT_NAME);
		} else if (propertyName.equals(COMPONENT)) {
			return ComponentCore.createComponent(getProject(), getStringProperty(COMPONENT_NAME));
		} else if (propertyName.equals(CLOSE_ARCHIVE_ON_DISPOSE)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(DEFAULT_COMPONENT_NAME)) {
			return Boolean.TRUE;
		}
		return super.getDefaultProperty(propertyName);
	}

	private boolean settingFileName = false;

	public boolean propertySet(String propertyName, Object propertyValue) {
		if (propertyName.equals(FILE)) {
			if (!settingFileName) {
				setProperty(FILE_NAME, null);
			}
			updateDefaultComponentName();
			return true;
		} else if (propertyName.equals(COMPONENT_NAME)) {
			componentCreationDM.setProperty(IComponentCreationDataModelProperties.COMPONENT_NAME, propertyValue);
		} else if (propertyName.equals(SAVE_FILTER) && getArchiveFile() != null) {
			getArchiveFile().setSaveFilter(getSaveFilter());
		} else if (FILE_NAME.equals(propertyName)) {
			try {
				cachedOpenFailureException = null;
				handleArchiveSetup((String) propertyValue);
			} catch (OpenFailureException oe) {
				cachedOpenFailureException = oe;
			}
		}
		return true;
	}

	private void updateDefaultComponentName() {
		Archive archive = getArchiveFile();
		if (null != archive && getBooleanProperty(DEFAULT_COMPONENT_NAME)) {
			Path path = new Path(archive.getURI());
			String defaultProjectName = path.segment(path.segmentCount() - 1);
			if (defaultProjectName.indexOf('.') > 0) {
				defaultProjectName = defaultProjectName.substring(0, defaultProjectName.lastIndexOf('.'));
				if (defaultProjectName.indexOf('.') > 0) {
					defaultProjectName = defaultProjectName.replace('.', '_');
				}
			}
			componentCreationDM.setProperty(IComponentCreationDataModelProperties.COMPONENT_NAME, defaultProjectName);
			model.notifyPropertyChange(COMPONENT_NAME, IDataModel.DEFAULT_CHG);
			setBooleanProperty(DEFAULT_COMPONENT_NAME, true);
		}
	}

	private boolean handleArchiveSetup(String fileName) throws OpenFailureException {
		Archive archive = getArchiveFile();
		if (archive != null) {
			archive.close();
		}
		String uri = getStringProperty(FILE_NAME);
		if (!archiveExistsOnFile())
			return false;
		archive = openArchive(uri);
		if (null != archive) {
			archive.setSaveFilter(getSaveFilter());
		}
		try {
			settingFileName = true;
			setProperty(FILE, archive);
		} finally {
			settingFileName = false;
		}
		return archive != null;
	}

	protected abstract Archive openArchive(String uri) throws OpenFailureException;

	private boolean closeModuleFile() {
		if (null != getArchiveFile()) {
			getArchiveFile().close();
		}
		return true;
	}

	public IStatus validate(String propertyName) {
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
		return OK_STATUS;
	}

	protected int getJ2EEVersion() {
		return 0;
	}

	protected abstract IDataModel createJ2EEComponentCreationDataModel();

	/*
	 * @see XMLResource#APP_CLIENT_TYPE
	 * @see XMLResource#APPLICATION_TYPE
	 * @see XMLResource#EJB_TYPE
	 * @see XMLResource#WEB_APP_TYPE
	 * @see XMLResource#RAR_TYPE
	 */
	protected abstract int getType();

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
		setProperty(FILE, archiveFile);
	}

	protected final Archive getArchiveFile() {
		return (Archive) getProperty(FILE);
	}

	protected final ArchiveOptions getArchiveOptions() {
		ArchiveOptions opts = new ArchiveOptions();
		opts.setIsReadOnly(true);
		return opts;
	}

	private SaveFilter getSaveFilter() {
		return (SaveFilter) getProperty(SAVE_FILTER);
	}

	public void propertyChanged(DataModelEvent event) {
	}
}