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
package org.eclipse.jst.j2ee.application.operations;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.frameworks.operations.WTPPropertyDescriptor;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * This dataModel is a common super class used for to create Flexibile Modules.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public abstract class FlexibleJ2EEModuleCreationDataModel extends FlexibleJ2EECreationDataModel implements IAnnotationsDataModel {

	/**
	 * type Integer
	 */
	public static final String J2EE_MODULE_VERSION = "FlexibleJ2EEModuleCreationDataModel.J2EE_MODULE_VERSION"; //$NON-NLS-1$

	/**
	 * This corresponds to the J2EE versions of 1.2, 1.3, 1.4, etc. Each subclass will convert this
	 * version to its corresponding highest module version supported by the J2EE version and set the
	 * J2EE_MODULE_VERSION property.
	 * 
	 * type Integer
	 */
	public static final String J2EE_VERSION = "FlexibleJ2EEModuleCreationDataModel.J2EE_VERSION"; //$NON-NLS-1$

	/**
	 * type Boolean, default false
	 */
	public static final String ADD_TO_EAR = "FlexibleJ2EEModuleCreationDataModel.ADD_TO_EAR"; //$NON-NLS-1$

	/**
	 * type String
	 */
	public static final String EAR_MODULE_NAME = "FlexibleJ2EEModuleCreationDataModel.EAR_MODULE_NAME"; //$NON-NLS-1$
	
	/**
	 * type boolean
	 */	
	private static final String NESTED_MODEL_ADD_TO_EAR = "FlexibleJ2EEModuleCreationDataModel.NESTED_MODEL_ADD_TO_EAR"; //$NON-NLS-1$
	
	/**
	 * type boolean
	 */		
	private static final String NESTED_MODEL_JAR_DEPENDENCY = "FlexibleJ2EEModuleCreationDataModel.NESTED_MODEL_JAR_DEPENDENCY"; //$NON-NLS-1$
	

	private AddModuleToEARDataModel addModuleToEARDataModel;

	private UpdateManifestDataModel jarDependencyDataModel;

	private ClassPathSelection cachedSelection;

	protected void init() {
		super.init();

		setProperty(J2EE_MODULE_VERSION, getDefaultProperty(J2EE_MODULE_VERSION));

	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(J2EE_MODULE_VERSION);
		addValidBaseProperty(J2EE_VERSION);
		addValidBaseProperty(EAR_MODULE_NAME);
		addValidBaseProperty(ADD_TO_EAR);
		addValidBaseProperty(USE_ANNOTATIONS);

	}

	protected void initNestedModels() {
		super.initNestedModels();
		
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
		} else if (propertyName.equals(USE_ANNOTATIONS)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(J2EE_MODULE_VERSION)) {
			return getDefaultJ2EEModuleVersion();
		} else {
			return super.getDefaultProperty(propertyName);
		}
	}

	protected abstract Integer getDefaultJ2EEModuleVersion();


	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		if (propertyName.equals(PROJECT_NAME)) {
			//should  use MODULE_NAME?
		}
		boolean returnValue = super.doSetProperty(propertyName, propertyValue);

		if (propertyName.equals(J2EE_VERSION)) {
			Integer modVersion = convertJ2EEVersionToModuleVersion((Integer) propertyValue);
			setProperty(J2EE_MODULE_VERSION, modVersion);
			return false;
		}
		if (propertyName.equals(EAR_MODULE_NAME)) {
			getAddModuleToApplicationDataModel().setProperty(AddModuleToEARDataModel.PROJECT_NAME, propertyValue);
		} 

		if(propertyName.equals(MODULE_NAME)){
			
		}
		if (propertyName.equals(PROJECT_NAME)) {
//			IProject project = getTargetProject();
//			getAddModuleToApplicationDataModel().setProperty(AddModuleToEARDataModel.ARCHIVE_PROJECT, project);
//			if (!isSet(EAR_MODULE_NAME)) {
//				String earProjectName = getStringProperty(EAR_PROJECT_NAME);
//				notifyListeners(EAR_PROJECT_NAME, earProjectName, earProjectName);
//
//			}
//			jarDependencyDataModel.setProperty(UpdateManifestDataModel.PROJECT_NAME, propertyValue);
		}

		if (propertyName.equals(ADD_TO_EAR)) {
			notifyEnablementChange(ADD_TO_EAR);
		}

		return returnValue;
	}


	protected Boolean basicIsEnabled(String propertyName) {
		Boolean enabled = super.basicIsEnabled(propertyName);
		if (enabled.booleanValue()) {
			if (propertyName.equals(EAR_MODULE_NAME)) {
				enabled = (Boolean) getProperty(ADD_TO_EAR);
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


	protected final AddModuleToEARDataModel getAddModuleToApplicationDataModel() {
		return addModuleToEARDataModel;
	}

	protected WTPPropertyDescriptor[] doGetValidPropertyDescriptors(String propertyName) {
		if (propertyName.equals(J2EE_MODULE_VERSION)) {
			return getValidJ2EEModuleVersionDescriptors();
		} else if (propertyName.equals(EAR_MODULE_NAME)) {
			int j2eeVersion = getJ2EEVersion();
			
			//To do: change logic to  get the ear modules
			
//			IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
//			ArrayList projectList = new ArrayList();
//			int earVersion = 0;
//			EARNatureRuntime earNature = null;
//			for (int i = 0; i < projects.length; i++) {
//				try {
//					earNature = (EARNatureRuntime) projects[i].getNature(IEARNatureConstants.NATURE_ID);
//					if (earNature != null) {
//						earVersion = earNature.getJ2EEVersion();
//						if (j2eeVersion <= earVersion) {
//							projectList.add(projects[i].getName());
//						}
//					}
//				} catch (CoreException e) {
//				}
//			}
//			WTPPropertyDescriptor[] descriptors = new WTPPropertyDescriptor[projectList.size()];
//			for (int i = 0; i < descriptors.length; i++) {
//				descriptors[i] = new WTPPropertyDescriptor(projectList.get(i));
//			}
//			return descriptors;
		}
		return super.doGetValidPropertyDescriptors(propertyName);
	}

	public final int getJ2EEVersion() {
		return convertModuleVersionToJ2EEVersion(getIntProperty(J2EE_MODULE_VERSION));
	}

	protected IStatus doValidateProperty(String propertyName) {
		if (EAR_MODULE_NAME.equals(propertyName) && getBooleanProperty(ADD_TO_EAR)) {
			return validateEARModuleNameProperty();
		} else if (J2EE_MODULE_VERSION.equals(propertyName)) {
			return validateJ2EEModuleVersionProperty();
		} 

		return super.doValidateProperty(propertyName);
	}

	private IStatus validateJ2EEModuleVersionProperty() {
		int j2eeVersion = getIntProperty(J2EE_MODULE_VERSION);
		if (j2eeVersion == -1)
			return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.J2EE_SPEC_LEVEL_NOT_FOUND));
		return OK_STATUS;
	}

	private IStatus validateEARModuleNameProperty() {
//		IProject earProject = applicationCreationDataModel.getTargetProject();
//		if (null != earProject && earProject.exists()) {
//			if (earProject.isOpen()) {
//				try {
//					EARNatureRuntime earNature = (EARNatureRuntime) earProject.getNature(IEARNatureConstants.NATURE_ID);
//					if (earNature == null) {
//						return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_NOT_EAR, new Object[]{earProject.getName()}));
//					} else if (earNature.getJ2EEVersion() < getJ2EEVersion()) {
//						String earVersion = EnterpriseApplicationCreationDataModel.getVersionString(earNature.getJ2EEVersion());
//						return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.INCOMPATABLE_J2EE_VERSIONS, new Object[]{earProject.getName(), earVersion}));
//					}
//					return OK_STATUS;
//				} catch (CoreException e) {
//					return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, -1, null, e);
//				}
//			}
//			return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_ClOSED, new Object[]{earProject.getName()}));
//		} else if (null != earProject && null != getTargetProject()) {
//			if (earProject.getName().equals(getTargetProject().getName())) {
//				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.SAME_MODULE_AND_EAR_NAME, new Object[]{earProject.getName()}));
//			} else if (!CoreFileSystemLibrary.isCaseSensitive()) {
//				if (earProject.getName().toLowerCase().equals(getTargetProject().getName().toLowerCase())) {
//					return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.SAME_MODULE_AND_EAR_NAME, new Object[]{earProject.getName()}));
//				}
//			}
//		}
//		IStatus status = applicationCreationDataModel.validateProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME);
//		if (status.isOK()) {
//			status = applicationCreationDataModel.validateProperty(EnterpriseApplicationCreationDataModel.PROJECT_LOCATION);
//		}
//		return status;
		IStatus status = null;
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



	public final ClassPathSelection getClassPathSelection() {
//		boolean createNew = false;
//		if (null == cachedSelection || !getApplicationCreationDataModel().getTargetProject().getName().equals(cachedSelection.getEARFile().getURI())) {
//			createNew = true;
//		}
//		// close an existing cachedSelection
//		if (createNew && cachedSelection != null) {
//			EARFile earFile = cachedSelection.getEARFile();
//			if (earFile != null)
//				earFile.close();
//		}
//
//		if (createNew && getTargetProject() != null) {
//			cachedSelection = ClasspathSelectionHelper.createClasspathSelection(getTargetProject(), getModuleExtension(), getApplicationCreationDataModel().getTargetProject(), getModuleType());
//		}
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
		if (event.getDataModel() == addModuleToEARDataModel && event.getFlag() == WTPOperationDataModelEvent.PROPERTY_CHG && event.getPropertyName().equals(AddModuleToEARDataModel.PROJECT_NAME)) {
//ToDo:			
//			applicationCreationDataModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, event.getProperty());
		}
	}

}