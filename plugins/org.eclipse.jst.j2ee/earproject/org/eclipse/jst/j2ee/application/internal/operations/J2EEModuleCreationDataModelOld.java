/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Nov 5, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.ArrayList;

import org.eclipse.core.internal.localstore.CoreFileSystemLibrary;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.frameworks.internal.operations.WTPPropertyDescriptor;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * This dataModel is a common super class used for to create J2EE Modules.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public abstract class J2EEModuleCreationDataModelOld extends J2EEArtifactCreationDataModelOld implements IAnnotationsDataModel {

	/**
	 * type Integer
	 */
	public static final String J2EE_MODULE_VERSION = "J2EEModuleCreationDataModel.J2EE_MODULE_VERSION"; //$NON-NLS-1$

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
	public static final String ADD_TO_EAR = "J2EEModuleCreationDataModel.ADD_TO_EAR"; //$NON-NLS-1$

	/**
	 * type String
	 */
	public static final String EAR_PROJECT_NAME = "J2EEModuleCreationDataModel.EAR_PROJECT_NAME"; //$NON-NLS-1$

	/**
	 * type boolean
	 */
	public static final String IS_FLEXIBLE_PROJECT = "J2EEModuleCreationDataModel.IS_FLEXIBLE_PROJECT"; //$NON-NLS-1$

	public static final String JAR_LIST = UpdateManifestDataModel.JAR_LIST;

	public static String JAR_LIST_TEXT_UI = UpdateManifestDataModel.JAR_LIST_TEXT_UI;

	private static final String NESTED_MODEL_APPLICATION_CREATION = "J2EEModuleCreationDataModel.NESTED_MODEL_APPLICATION_CREATION"; //$NON-NLS-1$

	private static final String NESTED_MODEL_ADD_TO_EAR = "J2EEModuleCreationDataModel.NESTED_MODEL_ADD_TO_EAR"; //$NON-NLS-1$

	private static final String NESTED_MODEL_JAR_DEPENDENCY = "J2EEModuleCreationDataModel.NESTED_MODEL_JAR_DEPENDENCY"; //$NON-NLS-1$

	/**
	 * type Boolean; default true, UI only
	 */
	public static final String UI_SHOW_EAR_SECTION = "J2EEModuleCreationDataModel.UI_SHOW_EAR_SECTION"; //$NON-NLS-1$

	private EnterpriseApplicationCreationDataModel applicationCreationDataModel;

	private AddModuleToEARDataModel addModuleToEARDataModel;

	private UpdateManifestDataModel jarDependencyDataModel;

	private String J2EENatureID;

	protected void init() {
		super.init();
		//getJavaProjectCreationDataModel().setProperty(ProjectCreationDataModel.PROJECT_NATURES,
		// new String[]{J2EENatureID});
		//set it so it pushes it down to ServerTargeting
		setProperty(J2EE_MODULE_VERSION, getDefaultProperty(J2EE_MODULE_VERSION));
		applicationCreationDataModel.addListener(this);
	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(J2EE_MODULE_VERSION);
		addValidBaseProperty(J2EE_VERSION);
		addValidBaseProperty(EAR_PROJECT_NAME);
		addValidBaseProperty(ADD_TO_EAR);
		addValidBaseProperty(UI_SHOW_EAR_SECTION);
		addValidBaseProperty(USE_ANNOTATIONS);
		addValidBaseProperty(PROJECT_LOCATION);
		addValidBaseProperty(IS_FLEXIBLE_PROJECT);
	}

	protected void initNestedModels() {
		super.initNestedModels();
		applicationCreationDataModel = new EnterpriseApplicationCreationDataModel();
		addNestedModel(NESTED_MODEL_APPLICATION_CREATION, applicationCreationDataModel);
		addModuleToEARDataModel = createModuleNestedModel();
		if (addModuleToEARDataModel != null)
			addNestedModel(NESTED_MODEL_ADD_TO_EAR, addModuleToEARDataModel);
		jarDependencyDataModel = new UpdateManifestDataModel();
		addNestedModel(NESTED_MODEL_JAR_DEPENDENCY, jarDependencyDataModel);
	}

	protected AddModuleToEARDataModel createModuleNestedModel() {
		return new AddModuleToEARDataModel();
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(ADD_TO_EAR)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(EAR_PROJECT_NAME)) {
			return getDefaultEARName(getStringProperty(PROJECT_NAME));
		} else if (propertyName.equals(UI_SHOW_EAR_SECTION)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(USE_ANNOTATIONS)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(J2EE_MODULE_VERSION)) {
			return getDefaultJ2EEModuleVersion();
		} else if (propertyName.equals(IS_FLEXIBLE_PROJECT)) {
			return Boolean.FALSE;
		} else {
			return super.getDefaultProperty(propertyName);
		}
	}

	protected abstract Integer getDefaultJ2EEModuleVersion();

	private String getDefaultEARName(String baseName) {
		return baseName + "EAR"; //TODO //$NON-NLS-1$
	}

	public final void notifyUpdatedEARs() {
		notifyListeners(EAR_PROJECT_NAME, WTPOperationDataModelEvent.VALID_VALUES_CHG);
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
					applicationCreationDataModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, earProjectName);
					getAddModuleToApplicationDataModel().setProperty(AddModuleToEARDataModel.PROJECT_NAME, earProjectName);
				} finally {
					if (disableNotification) {
						setNotificationEnabled(true);
					}
				}
			}
		}
		boolean returnValue = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(J2EE_MODULE_VERSION)) {
			int j2eeVersion = getJ2EEVersion();
			boolean shouldModifyServerTarget = true;
			if (getBooleanProperty(J2EEModuleCreationDataModelOld.ADD_TO_EAR)) {
				String earProjectName = getStringProperty(J2EEModuleCreationDataModelOld.EAR_PROJECT_NAME);
				IProject earProject = ProjectCreationDataModel.getProjectHandleFromProjectName(earProjectName);
				if (null != earProject && earProject.exists()) {
					shouldModifyServerTarget = false;
				}
			}
			if (shouldModifyServerTarget) {
				getServerTargetDataModel().setIntProperty(ServerTargetDataModel.J2EE_VERSION_ID, j2eeVersion);
				applicationCreationDataModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, j2eeVersion);
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
				applicationCreationDataModel.enableValidation();
			else
				applicationCreationDataModel.disableValidation();
			applicationCreationDataModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, propertyValue);
			getAddModuleToApplicationDataModel().setProperty(AddModuleToEARDataModel.PROJECT_NAME, propertyValue);
		} else if (PROJECT_LOCATION.equals(propertyName)) {
			getProjectDataModel().setProperty(ProjectCreationDataModel.PROJECT_LOCATION, propertyValue);
		}


		if (propertyName.equals(PROJECT_NAME)) {
			IProject project = getTargetProject();
			//getAddModuleToApplicationDataModel().setProperty(AddModuleToEARDataModel.ARCHIVE_PROJECT, project);
			if (!isSet(EAR_PROJECT_NAME)) {
				notifyListeners(EAR_PROJECT_NAME);
				synchUPServerTargetWithEAR();
			}
			jarDependencyDataModel.setProperty(UpdateManifestDataModel.PROJECT_NAME, propertyValue);
		}

		if (ADD_TO_EAR.equals(propertyName) || EAR_PROJECT_NAME.equals(propertyName)) {
			synchUPServerTargetWithEAR();
		}

		if (IS_ENABLED.equals(propertyName) || ADD_TO_EAR.equals(propertyName)) {
			notifyEnablementChange(ADD_TO_EAR);
		}
		if (IS_FLEXIBLE_PROJECT.equals(propertyName)) {
			if (((Boolean) propertyValue).booleanValue())
				setProperty(ADD_TO_EAR, Boolean.FALSE);
			else
				setProperty(ADD_TO_EAR, Boolean.TRUE);
			notifyEnablementChange(ADD_TO_EAR);
		}
		return returnValue;
	}

	private boolean checkForNewEARProjectName(String projectName) {
		IProject project = ProjectCreationDataModel.getProjectHandleFromProjectName(projectName);
		if (project != null && project.exists())
			return false;
		return true;
	}

	private void synchUPServerTargetWithEAR() {
		if (getBooleanProperty(J2EEModuleCreationDataModelOld.ADD_TO_EAR)) {
			String earProjectName = getStringProperty(J2EEModuleCreationDataModelOld.EAR_PROJECT_NAME);
			IProject earProject = ProjectCreationDataModel.getProjectHandleFromProjectName(earProjectName);
			if (null != earProject && earProject.exists() && earProject.isAccessible()) {
				EARNatureRuntime earNature = EARNatureRuntime.getRuntime(earProject);
				if (earNature != null) {
					int j2eeVersion = earNature.getJ2EEVersion();
					getServerTargetDataModel().setIntProperty(ServerTargetDataModel.J2EE_VERSION_ID, j2eeVersion);
					applicationCreationDataModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, j2eeVersion);
					IRuntime target = ServerCore.getProjectProperties(earProject).getRuntimeTarget();
					if (null != target) {
						setProperty(SERVER_TARGET_ID, target.getId());
					}
				}
				notifyUpdatedEARs();

			} else {
				int j2eeVersion = getJ2EEVersion();
				getServerTargetDataModel().setIntProperty(ServerTargetDataModel.J2EE_VERSION_ID, j2eeVersion);
				applicationCreationDataModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, j2eeVersion);
				notifyUpdatedEARs();
			}
		} else {
			int j2eeVersion = getJ2EEVersion();
			getServerTargetDataModel().setIntProperty(ServerTargetDataModel.J2EE_VERSION_ID, j2eeVersion);
			applicationCreationDataModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, j2eeVersion);
			notifyUpdatedEARs();
		}
		notifyEnablementChange(SERVER_TARGET_ID);
	}

	protected Boolean basicIsEnabled(String propertyName) {
		Boolean enabled = super.basicIsEnabled(propertyName);
		if (enabled.booleanValue()) {
			if (propertyName.equals(EAR_PROJECT_NAME)) {
				enabled = (Boolean) getProperty(ADD_TO_EAR);
			} else if (propertyName.equals(SERVER_TARGET_ID)) {
				if (!getBooleanProperty(J2EEModuleCreationDataModelOld.ADD_TO_EAR)) {
					return Boolean.TRUE;
				}
				String earProjectName = getStringProperty(J2EEModuleCreationDataModelOld.EAR_PROJECT_NAME);
				IProject earProject = ProjectCreationDataModel.getProjectHandleFromProjectName(earProjectName);
				enabled = new Boolean(null == earProject || !earProject.exists());
			} else if (propertyName.equals(ADD_TO_EAR)) {
				if (getBooleanProperty(IS_FLEXIBLE_PROJECT))
					return Boolean.FALSE;
				else
					return Boolean.TRUE;
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
	public final EnterpriseApplicationCreationDataModel getApplicationCreationDataModel() {
		return applicationCreationDataModel;
	}

	protected final AddModuleToEARDataModel getAddModuleToApplicationDataModel() {
		return addModuleToEARDataModel;
	}

	public final JavaProjectCreationDataModel getJavaProjectCreationDataModel() {
		return (JavaProjectCreationDataModel) getProjectDataModel();
	}

	protected void initProjectModel() {
		setProjectDataModel(new JavaProjectCreationDataModel());
	}


	protected final void setJ2EENatureID(String J2EENatureID) {
		this.J2EENatureID = J2EENatureID;
	}

	public final String getJ2EENatureID() {
		return J2EENatureID;
	}

	protected WTPPropertyDescriptor[] doGetValidPropertyDescriptors(String propertyName) {
		if (propertyName.equals(J2EE_MODULE_VERSION)) {
			return getValidJ2EEModuleVersionDescriptors();
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
			WTPPropertyDescriptor[] descriptors = new WTPPropertyDescriptor[projectList.size()];
			for (int i = 0; i < descriptors.length; i++) {
				descriptors[i] = new WTPPropertyDescriptor(projectList.get(i));
			}
			return descriptors;
		}
		return super.doGetValidPropertyDescriptors(propertyName);
	}

	public final int getJ2EEVersion() {
		return convertModuleVersionToJ2EEVersion(getIntProperty(J2EE_MODULE_VERSION));
	}

	protected IStatus doValidateProperty(String propertyName) {
		if (EAR_PROJECT_NAME.equals(propertyName) && getBooleanProperty(ADD_TO_EAR)) {
			return validateEARProjectNameProperty();
		} else if (J2EE_MODULE_VERSION.equals(propertyName)) {
			return validateJ2EEModuleVersionProperty();
		} else if (NESTED_MODEL_VALIDATION_HOOK.equals(propertyName)) {
			IStatus status = getServerTargetDataModel().validateProperty(ServerTargetDataModel.RUNTIME_TARGET_ID);
			if (!status.isOK()) {
				return status;
			}
			if (getBooleanProperty(ADD_TO_EAR)) {
				status = applicationCreationDataModel.validateProperty(ServerTargetDataModel.RUNTIME_TARGET_ID);
				if (!status.isOK() || getServerTargetDataModel().getRuntimeTarget() != applicationCreationDataModel.getServerTargetDataModel().getRuntimeTarget()) {
					return WTPCommonPlugin.createErrorStatus(EARCreationResourceHandler.getString(EARCreationResourceHandler.SERVER_TARGET_NOT_SUPPORT_EAR));
				}
			}
		}

		return super.doValidateProperty(propertyName);
	}

	private IStatus validateJ2EEModuleVersionProperty() {
		int j2eeVersion = getIntProperty(J2EE_MODULE_VERSION);
		if (j2eeVersion == -1)
			return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.SPEC_LEVEL_NOT_FOUND));
		return OK_STATUS;
	}

	private IStatus validateEARProjectNameProperty() {
		IProject earProject = applicationCreationDataModel.getTargetProject();
		if (null != earProject && earProject.exists()) {
			if (earProject.isOpen()) {
				try {
					EARNatureRuntime earNature = (EARNatureRuntime) earProject.getNature(IEARNatureConstants.NATURE_ID);
					if (earNature == null) {
						return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_NOT_EAR, new Object[]{earProject.getName()}));
					} else if (earNature.getJ2EEVersion() < getJ2EEVersion()) {
						String earVersion = EnterpriseApplicationCreationDataModel.getVersionString(earNature.getJ2EEVersion());
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
		IStatus status = applicationCreationDataModel.validateProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME);
		if (status.isOK()) {
			status = applicationCreationDataModel.validateProperty(EnterpriseApplicationCreationDataModel.PROJECT_LOCATION);
		}
		return status;
	}

	protected abstract WTPPropertyDescriptor[] getValidJ2EEModuleVersionDescriptors();

	protected abstract int convertModuleVersionToJ2EEVersion(int moduleVersion);

	protected abstract EClass getModuleType();

	protected abstract String getModuleExtension();

	/**
	 * @return
	 */
	public final UpdateManifestDataModel getUpdateManifestDataModel() {
		return jarDependencyDataModel;
	}

	private ClassPathSelection cachedSelection;

	public final ClassPathSelection getClassPathSelection() {
		boolean createNew = false;
		if (null == cachedSelection || !getApplicationCreationDataModel().getTargetProject().getName().equals(cachedSelection.getEARFile().getURI())) {
			createNew = true;
		}
		// close an existing cachedSelection
		if (createNew && cachedSelection != null) {
			EARFile earFile = cachedSelection.getEARFile();
			if (earFile != null)
				earFile.close();
		}

		if (createNew && getTargetProject() != null) {
			cachedSelection = ClasspathSelectionHelper.createClasspathSelection(getTargetProject(), getModuleExtension(), getApplicationCreationDataModel().getTargetProject(), getModuleType());
		}
		return cachedSelection;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#dispose()
	 */
	public void dispose() {
		if (cachedSelection != null)
			cachedSelection.getEARFile().close();
		super.dispose();
	}

	public void propertyChanged(WTPOperationDataModelEvent event) {
		super.propertyChanged(event);
		if (event.getDataModel() == getServerTargetDataModel() && event.getFlag() == WTPOperationDataModelEvent.PROPERTY_CHG && event.getPropertyName().equals(ServerTargetDataModel.RUNTIME_TARGET_ID)) {
			applicationCreationDataModel.setProperty(event.getPropertyName(), event.getProperty());
			notifyListeners(NESTED_MODEL_VALIDATION_HOOK);
		} else if (event.getDataModel() == applicationCreationDataModel && event.getFlag() == WTPOperationDataModelEvent.PROPERTY_CHG && event.getPropertyName().equals(EnterpriseApplicationCreationDataModel.PROJECT_NAME)) {
			synchUPServerTargetWithEAR();
		} else if (event.getDataModel() == addModuleToEARDataModel && event.getFlag() == WTPOperationDataModelEvent.PROPERTY_CHG && event.getPropertyName().equals(AddModuleToEARDataModel.PROJECT_NAME)) {
			applicationCreationDataModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, event.getProperty());
		}
	}

}