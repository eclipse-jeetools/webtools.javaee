/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Nov 5, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 * Comments
 */
package org.eclipse.jst.j2ee.application.operations;

import java.util.ArrayList;

import org.eclipse.core.internal.localstore.CoreFileSystemLibrary;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.commonarchivecore.EARFile;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;
import org.eclipse.wst.common.framework.operation.ProjectCreationDataModel;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModelEvent;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModelListener;
import org.eclipse.wst.common.internal.jdt.integration.JavaProjectCreationDataModel;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;
import org.eclispe.wst.common.framework.plugin.WTPCommonMessages;
import org.eclispe.wst.common.framework.plugin.WTPCommonPlugin;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class J2EEModuleCreationDataModel extends J2EEProjectCreationDataModel implements IAnnotationsDataModel {

	/**
	 * type Integer
	 */
	public static final String J2EE_MODULE_VERSION = "J2EEModuleCreationDataModel.J2EE_MODULE_VERSION"; //$NON-NLS-1$

	/**
	 * type String
	 */
	public static final String J2EE_MODULE_VERSION_LBL = "J2EEModuleCreationDataModel.J2EE_MODULE_VERSION_LBL"; //$NON-NLS-1$

	/**
	 * This corresponds to the J2EE versions of 1.2, 1.3, 1.4, etc. Each subclass will convert this
	 * version to its corresponding highest module version supported by the J2EE version and set the
	 * J2EE_MODULE_VERSION property.
	 * 
	 * type Integer
	 */
	public static final String J2EE_VERSION = "J2EEModuleCreationDataModel.J2EE_VERSION"; //$NON-NLS-1$

	/**
	 * type Boolean, default false
	 */
	public static final String ADD_TO_EAR = "J2EEModuleCreationDataModel.EAR_CREATE"; //$NON-NLS-1$

	/**
	 * type String
	 */
	public static final String EAR_PROJECT_NAME = "J2EEModuleCreationDataModel.EAR_PROJECT_NAME"; //$NON-NLS-1$

	public static final String JAR_LIST = UpdateManifestDataModel.JAR_LIST;

	public static String JAR_LIST_TEXT_UI = UpdateManifestDataModel.JAR_LIST_TEXT_UI;

	private static final String NESTED_MODEL_EAR_CREATION = "J2EEModuleCreationDataModel.NESTED_MODEL_EAR_CREATION"; //$NON-NLS-1$

	private static final String NESTED_MODEL_ADD_TO_EAR = "J2EEModuleCreationDataModel.NESTED_MODEL_ADD_TO_EAR"; //$NON-NLS-1$

	private static final String NESTED_MODEL_JAR_DEPENDENCY = "J2EEModuleCreationDataModel.NESTED_MODEL_JAR_DEPENDENCY"; //$NON-NLS-1$

	/**
	 * type Boolean; default true, UI only
	 */
	public static final String UI_SHOW_EAR_SECTION = "J2EEModuleCreationDataModel.UI_SHOW_EAR_SECTION"; //$NON-NLS-1$

	protected EARProjectCreationDataModel earProjectCreationDataModel;

	protected AddModuleToEARDataModel addModuleToEARDataModel;

	protected UpdateManifestDataModel jarDependencyDataModel;

	protected void init() {
		super.init();
		getJavaProjectCreationDataModel().setProperty(ProjectCreationDataModel.PROJECT_NATURES, new String[]{j2eeNatureID});
		//set it so it pushes it down to ServerTargeting
		setProperty(J2EE_MODULE_VERSION, getDefaultProperty(J2EE_MODULE_VERSION));
		earProjectCreationDataModel.addListener(this);
	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(J2EE_MODULE_VERSION);
		addValidBaseProperty(J2EE_MODULE_VERSION_LBL);
		addValidBaseProperty(J2EE_VERSION);
		addValidBaseProperty(EAR_PROJECT_NAME);
		addValidBaseProperty(ADD_TO_EAR);
		addValidBaseProperty(UI_SHOW_EAR_SECTION);
		addValidBaseProperty(USE_ANNOTATIONS);
		addValidBaseProperty(PROJECT_LOCATION);
	}

	protected void initNestedModels() {
		super.initNestedModels();
		earProjectCreationDataModel = new EARProjectCreationDataModel();
		addNestedModel(NESTED_MODEL_EAR_CREATION, earProjectCreationDataModel);
		addModuleToEARDataModel = createModuleNestedModel();
		if (addModuleToEARDataModel != null)
			addNestedModel(NESTED_MODEL_ADD_TO_EAR, addModuleToEARDataModel);
		jarDependencyDataModel = new UpdateManifestDataModel();
		addNestedModel(NESTED_MODEL_JAR_DEPENDENCY, jarDependencyDataModel);
	}

	/**
	 *  
	 */
	protected AddModuleToEARDataModel createModuleNestedModel() {
		return new AddModuleToEARDataModel();
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(ADD_TO_EAR)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(J2EE_MODULE_VERSION_LBL)) {
			return convertVersionIDtoLabel(getIntProperty(J2EE_MODULE_VERSION));
		} else if (propertyName.equals(EAR_PROJECT_NAME)) {
			return getDefaultEARName(getStringProperty(PROJECT_NAME));
		} else if (propertyName.equals(UI_SHOW_EAR_SECTION)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(USE_ANNOTATIONS)) {
			return Boolean.FALSE;
		} else
			return super.getDefaultProperty(propertyName);
	}

	protected String getDefaultEARName(String baseName) {
		return baseName + "EAR"; //TODO //$NON-NLS-1$
	}

	public void notifyUpdatedEARs() {
		String earProjectName = getStringProperty(EAR_PROJECT_NAME);
		notifyListeners(EAR_PROJECT_NAME, WTPOperationDataModelListener.VALID_VALUES_CHG, earProjectName, earProjectName);
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		if (propertyName.equals(PROJECT_NAME)) {
			if (!isSet(EAR_PROJECT_NAME)) {
				boolean disableNotification = isNotificationEnabled();
				try {
					if (disableNotification) {
						setNotificationEnabled(false);
					}
					String earProjectName = getDefaultEARName((String) propertyValue);
					earProjectCreationDataModel.setProperty(EARProjectCreationDataModel.PROJECT_NAME, earProjectName);
					getAddModuleToEARDataModel().setProperty(AddModuleToEARDataModel.PROJECT_NAME, earProjectName);
				} finally {
					if (disableNotification) {
						setNotificationEnabled(true);
					}
				}
			}
		}
		boolean returnValue = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(J2EE_MODULE_VERSION_LBL)) {
			Integer id = convertVersionLabeltoID((String) propertyValue);
			setProperty(J2EE_MODULE_VERSION, id);
			return true;
		}
		if (propertyName.equals(J2EE_MODULE_VERSION)) {
			int j2eeVersion = getJ2EEVersion();
			boolean shouldModifyServerTarget = true;
			if (getBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR)) {
				String earProjectName = getStringProperty(J2EEModuleCreationDataModel.EAR_PROJECT_NAME);
				IProject earProject = getProjectHandleFromName(earProjectName);
				if (null != earProject && earProject.exists()) {
					shouldModifyServerTarget = false;
				}
			}
			if (shouldModifyServerTarget) {
				serverTargetDataModel.setIntProperty(ServerTargetDataModel.J2EE_VERSION_ID, j2eeVersion);
				earProjectCreationDataModel.setIntProperty(EARProjectCreationDataModel.EAR_VERSION, j2eeVersion);
			}
			notifyUpdatedEARs();
			return true;
		}
		if (propertyName.equals(J2EE_VERSION)) {
			Integer modVersion = convertJ2EEVersionToModuleVersion((Integer) propertyValue);
			setProperty(J2EE_MODULE_VERSION, modVersion);
			return false;
		}
		if (propertyName.equals(EAR_PROJECT_NAME)) {
			if (checkForNewEARProjectName((String) propertyValue))
				earProjectCreationDataModel.enableValidation();
			else
				earProjectCreationDataModel.disableValidation();
			earProjectCreationDataModel.setProperty(EARProjectCreationDataModel.PROJECT_NAME, propertyValue);
			getAddModuleToEARDataModel().setProperty(AddModuleToEARDataModel.PROJECT_NAME, propertyValue);
		} else if (PROJECT_LOCATION.equals(propertyName)) {
			projectDataModel.setProperty(ProjectCreationDataModel.PROJECT_LOCATION, propertyValue);
		}


		if (propertyName.equals(PROJECT_NAME)) {
			//if (getBooleanProperty(ADD_TO_EAR)) {
			IProject project = getTargetProject();
			getAddModuleToEARDataModel().setProperty(AddModuleToEARDataModel.ARCHIVE_PROJECT, project);
			if (!isSet(EAR_PROJECT_NAME)) {
				String earProjectName = getStringProperty(EAR_PROJECT_NAME);
				notifyListeners(EAR_PROJECT_NAME, earProjectName, earProjectName);
				synchUPServerTargetWithEAR();
			}
			//}
			jarDependencyDataModel.setProperty(UpdateManifestDataModel.PROJECT_NAME, propertyValue);
		}

		if (ADD_TO_EAR.equals(propertyName) || EAR_PROJECT_NAME.equals(propertyName)) {
			synchUPServerTargetWithEAR();
		}

		if (IS_ENABLED.equals(propertyName) || ADD_TO_EAR.equals(propertyName)) {
			notifyEnablementChange(ADD_TO_EAR);
		}

		return returnValue;
	}

	/**
	 * @return
	 */
	private boolean checkForNewEARProjectName(String projectName) {
		IProject project = getProjectHandleFromName(projectName);
		if (project != null && project.exists())
			return false;
		return true;
	}

	protected void synchUPServerTargetWithEAR() {
		if (getBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR)) {
			String earProjectName = getStringProperty(J2EEModuleCreationDataModel.EAR_PROJECT_NAME);
			IProject earProject = getProjectHandleFromName(earProjectName);
			if (null != earProject && earProject.exists() && earProject.isAccessible()) {
				EARNatureRuntime earNature = EARNatureRuntime.getRuntime(earProject);
				if (earNature != null) {
					int j2eeVersion = earNature.getJ2EEVersion();
					serverTargetDataModel.setIntProperty(ServerTargetDataModel.J2EE_VERSION_ID, j2eeVersion);
					earProjectCreationDataModel.setIntProperty(EARProjectCreationDataModel.EAR_VERSION, j2eeVersion);
					IRuntime target = ServerCore.getProjectProperties(earProject).getRuntimeTarget();
					if (null != target) {
						setProperty(SERVER_TARGET_ID, target.getId());
					}
				}
				notifyUpdatedEARs();

			} else {
				int j2eeVersion = getJ2EEVersion();
				serverTargetDataModel.setIntProperty(ServerTargetDataModel.J2EE_VERSION_ID, j2eeVersion);
				earProjectCreationDataModel.setIntProperty(EARProjectCreationDataModel.EAR_VERSION, j2eeVersion);
				notifyUpdatedEARs();
			}
		} else {
			int j2eeVersion = getJ2EEVersion();
			serverTargetDataModel.setIntProperty(ServerTargetDataModel.J2EE_VERSION_ID, j2eeVersion);
			earProjectCreationDataModel.setIntProperty(EARProjectCreationDataModel.EAR_VERSION, j2eeVersion);
			notifyUpdatedEARs();
		}
		notifyEnablementChange(SERVER_TARGET_ID);
	}

	protected Boolean basicIsEnabled(String propertyName) {
		Boolean enabled = super.basicIsEnabled(propertyName);
		if (enabled.booleanValue()) {
			if (propertyName.equals(EAR_PROJECT_NAME)) {
				enabled = (Boolean) getProperty(ADD_TO_EAR);
			} else if (propertyName.equals(SERVER_TARGET_ID) || propertyName.equals(ServerTargetDataModel.RUNTIME_TARGET_NAME)) {
				if (!getBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR)) {
					return Boolean.TRUE;
				}
				String earProjectName = getStringProperty(J2EEModuleCreationDataModel.EAR_PROJECT_NAME);
				IProject earProject = getProjectHandleFromName(earProjectName);
				enabled = new Boolean(null == earProject || !earProject.exists());
			}
		}
		return enabled;
	}

	/**
	 * Subclasses should override to convert the j2eeVersion to a module version id. By default we
	 * return the j2eeVersion which is fine if no conversion is necessary.
	 * 
	 * @param integer
	 * @return
	 */
	protected Integer convertJ2EEVersionToModuleVersion(Integer j2eeVersion) {
		return j2eeVersion;
	}

	/**
	 * @return
	 */
	public EARProjectCreationDataModel getEarProjectCreationDataModel() {
		return earProjectCreationDataModel;
	}

	/**
	 * @return
	 */
	public AddModuleToEARDataModel getAddModuleToEARDataModel() {
		return addModuleToEARDataModel;
	}

	public JavaProjectCreationDataModel getJavaProjectCreationDataModel() {
		return (JavaProjectCreationDataModel) getProjectDataModel();
	}

	public void initProjectModel() {
		projectDataModel = new JavaProjectCreationDataModel();
	}

	protected String j2eeNatureID;

	public String getJ2EENatureID() {
		return j2eeNatureID;
	}

	protected Object[] doGetValidPropertyValues(String propertyName) {
		if (propertyName.equals(J2EE_MODULE_VERSION_LBL)) {
			return getValidJ2EEVersionLabels();
		} else if (propertyName.equals(EAR_PROJECT_NAME)) {
			int j2eeVersion = getJ2EEVersion();
			IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			ArrayList projectList = new ArrayList();
			int earVersion = 0;
			EARNatureRuntime earNature = null;
			for (int i = 0; i < projects.length; i++) {
				try {
					earNature = (EARNatureRuntime) projects[i].getNature(IEARNatureConstants.NATURE_ID);
					if (earNature != null) {
						earVersion = earNature.getJ2EEVersion();
						if (j2eeVersion <= earVersion) {
							projectList.add(projects[i].getName());
						}
					}
				} catch (CoreException e) {
				}
			}
			String[] projectNames = new String[projectList.size()];
			for (int i = 0; i < projectNames.length; i++) {
				projectNames[i] = (String) projectList.get(i);
			}
			return projectNames;
		}
		return super.doGetValidPropertyValues(propertyName);
	}

	public int getJ2EEVersion() {
		return convertModuleVersionToJ2EEVersion(getIntProperty(J2EE_MODULE_VERSION));
	}

	protected IStatus doValidateProperty(String propertyName) {
		if (EAR_PROJECT_NAME.equals(propertyName) && getBooleanProperty(ADD_TO_EAR)) {
			return validateEARProjectNameProperty();
		} else if (J2EE_MODULE_VERSION.equals(propertyName)) {
			return validateJ2EEModuleVersionProperty();
		} else if (NESTED_MODEL_VALIDATION_HOOK.equals(propertyName)) {
			IStatus status = serverTargetDataModel.validateProperty(ServerTargetDataModel.RUNTIME_TARGET_ID);
			if (!status.isOK()) {
				return status;
			}
			if (getBooleanProperty(ADD_TO_EAR)) {
				status = earProjectCreationDataModel.validateProperty(ServerTargetDataModel.RUNTIME_TARGET_ID);
				if (!status.isOK() || serverTargetDataModel.getRuntimeTarget() != earProjectCreationDataModel.getServerTargetDataModel().getRuntimeTarget()) {
					return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString(EARCreationResourceHandler.SERVER_TARGET_NOT_SUPPORT_EAR));
				}
			}
		}

		return super.doValidateProperty(propertyName);
	}

	/**
	 * @return
	 */
	protected IStatus validateJ2EEModuleVersionProperty() {
		int j2eeVersion = getIntProperty(J2EE_MODULE_VERSION);
		if (j2eeVersion == -1)
			return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.J2EE_SPEC_LEVEL_NOT_FOUND));
		return OK_STATUS;
	}

	/**
	 * @return
	 */
	protected IStatus validateEARProjectNameProperty() {
		IProject earProject = earProjectCreationDataModel.getTargetProject();
		if (null != earProject && earProject.exists()) {
			if (earProject.isOpen()) {
				try {
					EARNatureRuntime earNature = (EARNatureRuntime) earProject.getNature(IEARNatureConstants.NATURE_ID);
					if (earNature == null) {
						return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_NOT_EAR, new Object[]{earProject.getName()}));
					} else if (earNature.getJ2EEVersion() < getJ2EEVersion()) {
						String earVersion = EARProjectCreationDataModel.convertEARVersionToLabel(earNature.getJ2EEVersion());
						return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.INCOMPATABLE_J2EE_VERSIONS, new Object[]{earProject.getName(), earVersion}));
					}
					return OK_STATUS;
				} catch (CoreException e) {
					return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, -1, null, e);
				}
			}
			return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_ClOSED, new Object[]{earProject.getName()}));
		} else if (null != earProject && null != getTargetProject()) {
			if (earProject.getName().equals(getTargetProject().getName())) {
				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.SAME_MODULE_AND_EAR_NAME, new Object[]{earProject.getName()}));
			} else if (!CoreFileSystemLibrary.isCaseSensitive()) {
				if (earProject.getName().toLowerCase().equals(getTargetProject().getName().toLowerCase())) {
					return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.SAME_MODULE_AND_EAR_NAME, new Object[]{earProject.getName()}));
				}
			}
		}
		IStatus status = earProjectCreationDataModel.validateProperty(EARProjectCreationDataModel.PROJECT_NAME);
		if (status.isOK()) {
			status = earProjectCreationDataModel.validateProperty(EARProjectCreationDataModel.PROJECT_LOCATION);
		}
		return status;
	}

	protected abstract String convertVersionIDtoLabel(int id);

	protected abstract Integer convertVersionLabeltoID(String label);

	protected abstract Object[] getValidJ2EEVersionLabels();

	protected abstract int convertModuleVersionToJ2EEVersion(int moduleVersion);

	protected abstract EClass getModuleType();

	protected abstract String getModuleExtension();

	/**
	 * @return
	 */
	public UpdateManifestDataModel getUpdateManifestDataModel() {
		return jarDependencyDataModel;
	}

	private ClassPathSelection cachedSelection;

	public ClassPathSelection getClassPathSelection() {
		boolean createNew = false;
		if (null == cachedSelection || !getEarProjectCreationDataModel().getTargetProject().getName().equals(cachedSelection.getEARFile().getURI())) {
			createNew = true;
		}
		// close an existing cachedSelection
		if (createNew && cachedSelection != null) {
			EARFile earFile = cachedSelection.getEARFile();
			if (earFile != null)
				earFile.close();
		}

		if (createNew && getTargetProject() != null) {
			cachedSelection = ClasspathSelectionHelper.createClasspathSelection(getTargetProject(), getModuleExtension(), getEarProjectCreationDataModel().getTargetProject(), getModuleType());
		}
		return cachedSelection;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#dispose()
	 */
	public void dispose() {
		if (cachedSelection != null)
			cachedSelection.getEARFile().close();
		super.dispose();
	}

	public void propertyChanged(WTPOperationDataModelEvent event) {
		super.propertyChanged(event);
		if (event.getDataModel() == getServerTargetDataModel() && event.getFlag() == WTPOperationDataModelEvent.PROPERTY_CHG && event.getPropertyName().equals(ServerTargetDataModel.RUNTIME_TARGET_ID)) {
			earProjectCreationDataModel.setProperty(event.getPropertyName(), event.getNewValue());
			notifyListeners(NESTED_MODEL_VALIDATION_HOOK, null, null);
		} else if (event.getDataModel() == earProjectCreationDataModel && event.getFlag() == WTPOperationDataModelEvent.PROPERTY_CHG && event.getPropertyName().equals(EARProjectCreationDataModel.PROJECT_NAME)) {
			synchUPServerTargetWithEAR();
		} else if (event.getDataModel() == addModuleToEARDataModel && event.getFlag() == WTPOperationDataModelEvent.PROPERTY_CHG && event.getPropertyName().equals(AddModuleToEARDataModel.PROJECT_NAME)) {
			earProjectCreationDataModel.setProperty(EARProjectCreationDataModel.PROJECT_NAME, event.getNewValue());
		}
	}

}