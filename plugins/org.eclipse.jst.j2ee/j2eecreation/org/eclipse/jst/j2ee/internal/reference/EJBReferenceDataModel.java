/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.reference;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.applicationclient.creation.IApplicationClientNatureConstants;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.application.impl.ApplicationImpl;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EECreationResourceHandler;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleNature;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

import org.eclipse.jem.util.emf.workbench.ProjectUtilities;


public class EJBReferenceDataModel extends ReferenceDataModel {
	/**
	 * Required, type EnterpriseBean
	 */
	public static final String TARGET = "EJBReferenceDataModel.TARGET"; //$NON-NLS-1$
	/**
	 * Required, type String
	 */
	public static final String LOCAL_REMOTE_TYPE = "EJBReferenceDataModel.LOCAL_REMOTE_TYPE"; //$NON-NLS-1$
	/**
	 * Required, type Boolean
	 */
	public static final String IS_LOCAL = "EJBReferenceDataModel.IS_LOCAL"; //$NON-NLS-1$
	/**
	 * Optional, type Boolean
	 */
	public static final String TARGET_IN_DIFFERENT_EAR = "EJBReferenceDataModel.TARGET_IN_DIFFERENT_EAR"; //$NON-NLS-1$

	/**
	 * Required, type String
	 */
	public static final String LINK = "EJBReferenceDataModel.LINK"; //$NON-NLS-1$
	/**
	 * Required, type String
	 */
	public static final String REF_TYPE = "EJBReferenceDataModel.REF_TYPE"; //$NON-NLS-1$
	/**
	 * Required, type String
	 */
	public static final String HOME_INTERFACE = "EJBReferenceDataModel.HOME_INTERFACE"; //$NON-NLS-1$
	/**
	 * Required, type String
	 */
	public static final String REMOTE_INTERACE = "EJBReferenceDataModel.REMOTE_INTERACE"; //$NON-NLS-1$
	/**
	 * Optional, type String
	 */
	public static final String CREATE_VALID_DEFAULT_NAME = "EJBReferenceDataModel.CREATE_VALID_DEFAULT_NAME"; //$NON-NLS-1$

	/**
	 * Optional, type Boolean
	 */
	public static final String IS_DEFAULT_LOCAL_OR_REMOTE = "EJBReferenceDataModel.IS_DEFAULT_LOCAL_OR_REMOTE"; //$NON-NLS-1$

	/**
	 * Required, type Boolean
	 */
	public static final String IN_WORKSPACE = "EJBReferenceDataModel.IN_WORKSPACE"; //$NON-NLS-1$

	/**
	 * Required, type EAR
	 */
	public static final String TARGET_EAR = "EJBReferenceDataModel.TARGET_EAR"; //$NON-NLS-1$

	/**
	 * Required, type boolean
	 */
	public static final String TARGET_ALSO_EXIST_IN_SAME_EAR = "EJBReferenceDataModel.ALSO_EXIST_IN_SAME_EAR"; //$NON-NLS-1$

	/**
	 * Required, type boolean
	 */
	public static final String TARGET_IN_SAME_PROJECT = "EJBReferenceDataModel.TARGET_IN_SAME_PROJECT"; //$NON-NLS-1$

	/**
	 * Required, type project
	 */
	public static final String TARGET_ALSO_IN_EAR = "EJBReferenceDataModel.TARGET_ALSO_IN_EAR"; //$NON-NLS-1$



	public static final String TARGET_AND_SOURCE_HAVE_SAME_EARS = "EJBReferenceDataModel.TARGET_AND_SOURCE_HAVE_SAME_EARS"; //$NON-NLS-1$


	public static final String EMPTY_STRING = ""; //$NON-NLS-1$
	private static final String LOCAL_STRING = "Local"; //$NON-NLS-1$
	private static final String REMOTE_STRING = "Remote"; //$NON-NLS-1$

	protected void init() {
		super.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(TARGET);
		addValidBaseProperty(LOCAL_REMOTE_TYPE);
		addValidBaseProperty(IS_LOCAL);
		addValidBaseProperty(TARGET_IN_DIFFERENT_EAR);
		addValidBaseProperty(LINK);
		addValidBaseProperty(REF_TYPE);
		addValidBaseProperty(HOME_INTERFACE);
		addValidBaseProperty(REMOTE_INTERACE);
		addValidBaseProperty(CREATE_VALID_DEFAULT_NAME);
		addValidBaseProperty(IN_WORKSPACE);
		addValidBaseProperty(IS_DEFAULT_LOCAL_OR_REMOTE);
		addValidBaseProperty(TARGET_EAR);
		addValidBaseProperty(TARGET_ALSO_EXIST_IN_SAME_EAR);
		addValidBaseProperty(TARGET_IN_SAME_PROJECT);
		addValidBaseProperty(TARGET_ALSO_IN_EAR);
		addValidBaseProperty(TARGET_AND_SOURCE_HAVE_SAME_EARS);
		super.initValidBaseProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.reference.ReferenceDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean status = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(OWNER) || propertyName.equals(TARGET)) {
			computeTargetInEAR();
		}
		if (propertyName.equals(TARGET)) {
			if (getBooleanProperty(CREATE_VALID_DEFAULT_NAME)) {
				setProperty(REF_NAME, calculateDefaultName());
				setBooleanProperty(CREATE_VALID_DEFAULT_NAME, true);
			}
			this.setProperty(REF_TYPE, this.calculateRefType());
			notifyDefaultChange(REF_TYPE);
			this.setProperty(LINK, this.calculateLink());
			notifyDefaultChange(LINK);
			// set the default property of interface type if the
			// target is not null
			setProperty(LOCAL_REMOTE_TYPE, getDefaultLocalRemote());
			notifyDefaultChange(LOCAL_REMOTE_TYPE);
			notifyEnablementChange(LOCAL_REMOTE_TYPE);

			this.setProperty(HOME_INTERFACE, calculateHomeInterface());
			notifyDefaultChange(HOME_INTERFACE);
			this.setProperty(REMOTE_INTERACE, calculateRemoteInterface());
			notifyDefaultChange(REMOTE_INTERACE);
		} else if (propertyName.equals(REF_NAME)) {
			String val = (propertyValue != null) ? ((String) propertyValue).trim() : EMPTY_STRING;
			if (EMPTY_STRING.equals(val)) {
				setBooleanProperty(CREATE_VALID_DEFAULT_NAME, true);
			} else {
				setBooleanProperty(CREATE_VALID_DEFAULT_NAME, false);
			}
		} else if (propertyName.equals(LOCAL_REMOTE_TYPE)) {
			this.setProperty(IS_LOCAL, this.calculateLocalValue());
			notifyDefaultChange(IS_LOCAL);
			this.setProperty(HOME_INTERFACE, calculateHomeInterface());
			notifyDefaultChange(HOME_INTERFACE);
			this.setProperty(REMOTE_INTERACE, calculateRemoteInterface());
			notifyDefaultChange(REMOTE_INTERACE);
		} else if (propertyName.equals(TARGET_IN_DIFFERENT_EAR)) {
			this.setProperty(LINK, this.calculateLink());
			notifyDefaultChange(LINK);
		} else if (propertyName.equals(IN_WORKSPACE)) {
			if (!getBooleanProperty(IN_WORKSPACE))
				setBooleanProperty(TARGET_IN_DIFFERENT_EAR, true);
			notifyEnablementChange(LOCAL_REMOTE_TYPE);
			/*
			 * String defaultLocalRemote = getDefaultLocalRemote(); if
			 * (!defaultLocalRemote.equals(this.getStringProperty(LOCAL_REMOTE_TYPE))){
			 * setProperty(LOCAL_REMOTE_TYPE,defaultLocalRemote); }
			 */
		} else if (propertyName.equals(TARGET_EAR)) {
			setProperty(TARGET, null);
		}
		return status;
	}

	/**
	 * @return
	 */
	private void computeTargetInEAR() {
		if (isSet(OWNER) && isSet(TARGET)) {
			setBooleanProperty(TARGET_ALSO_EXIST_IN_SAME_EAR, false);
			setBooleanProperty(TARGET_IN_SAME_PROJECT, false);
			setProperty(TARGET_ALSO_IN_EAR, null);
			setBooleanProperty(TARGET_AND_SOURCE_HAVE_SAME_EARS, false);
			EnterpriseBean target = (EnterpriseBean) getProperty(TARGET);
			IProject sourceProject = getTargetProject();
			IProject targetProject = J2EEProjectUtilities.getProject(target);
			boolean foundEAR = false;
			if (targetProject.equals(sourceProject)) {
				setBooleanProperty(TARGET_IN_SAME_PROJECT, true);
				setBooleanProperty(TARGET_AND_SOURCE_HAVE_SAME_EARS, true);
				foundEAR = true;
			} else {
				EARNatureRuntime[] sourceEARs = J2EEProjectUtilities.getReferencingEARProjects(sourceProject);
				EARNatureRuntime[] targetEARs = J2EEProjectUtilities.getReferencingEARProjects(targetProject);
				ApplicationImpl app = (ApplicationImpl) getProperty(TARGET_EAR);
				IProject earProject = app != null ? J2EEProjectUtilities.getProject(app) : null;

				// this code is for smart creation of client jar
				// if the source and target belongs to all the ears to which
				// either one of them belongs then dont generate the client jar.
				boolean bSameEARsForSourceAndTarget = true;
				if (sourceEARs.length == targetEARs.length) {
					for (int i = 0; bSameEARsForSourceAndTarget && i < sourceEARs.length; i++) {
						boolean bFind = false;
						for (int j = 0; !bFind && j < targetEARs.length; ++j) {
							if (sourceEARs[i] == targetEARs[j])
								bFind = true;
						}
						bSameEARsForSourceAndTarget = bFind;
					}
				} else {
					bSameEARsForSourceAndTarget = false;
				}

				if (bSameEARsForSourceAndTarget) {
					foundEAR = true;
					setBooleanProperty(TARGET_AND_SOURCE_HAVE_SAME_EARS, true);
				} else {
					setBooleanProperty(TARGET_AND_SOURCE_HAVE_SAME_EARS, false);
					for (int i = 0; !foundEAR && i < sourceEARs.length; i++) {
						for (int j = 0; !foundEAR && j < targetEARs.length; j++) {
							if (sourceEARs[i] == targetEARs[j]) {
								if (earProject != null) {
									// this is for the Reference UI where the TARGET EAR is set
									// the user is given the warning in this case
									if (sourceEARs[i].getProject() != null && sourceEARs[i].getProject() == earProject)
										foundEAR = true;
									else {
										setBooleanProperty(TARGET_ALSO_EXIST_IN_SAME_EAR, true);
										setProperty(TARGET_ALSO_IN_EAR, sourceEARs[i].getProject());
									}
								} else {
									// for non UI datamodel if only the projects are known we will
									// go by
									// the best case scenario.
									foundEAR = true;
								}
							}
						}
					}
				}
			}
			setBooleanProperty(TARGET_IN_DIFFERENT_EAR, !foundEAR);
		}
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(IS_LOCAL)) {
			return calculateLocalValue();
		}
		if (propertyName.equals(REF_NAME)) {
			return calculateDefaultName();
		}
		if (propertyName.equals(REF_TYPE)) {
			return calculateRefType();
		}

		if (propertyName.equals(LOCAL_REMOTE_TYPE)) {
			return getDefaultLocalRemote();
		}

		if (propertyName.equals(LINK)) {
			return calculateLink();
		}
		if (propertyName.equals(HOME_INTERFACE)) {
			return calculateHomeInterface();
		}
		if (propertyName.equals(REMOTE_INTERACE)) {
			return calculateRemoteInterface();
		}
		if (propertyName.equals(TARGET_IN_DIFFERENT_EAR)) {
			return Boolean.FALSE;
		}
		if (propertyName.equals(CREATE_VALID_DEFAULT_NAME)) {
			return Boolean.TRUE;
		}
		if (propertyName.equals(IN_WORKSPACE)) {
			return Boolean.TRUE;
		}

		if (propertyName.equals(TARGET_ALSO_EXIST_IN_SAME_EAR)) {
			return Boolean.FALSE;
		}

		if (propertyName.equals(TARGET_IN_SAME_PROJECT)) {
			return Boolean.FALSE;
		}

		if (propertyName.equals(TARGET_ALSO_IN_EAR)) {
			return null;
		}
		return super.getDefaultProperty(propertyName);
	}

	/**
	 * @return
	 */
	private String calculateRemoteInterface() {
		EnterpriseBean targetBean = (EnterpriseBean) getProperty(TARGET);
		if (targetBean != null) {
			if (getBooleanProperty(IS_LOCAL))
				return targetBean.getLocalInterfaceName();
			return targetBean.getRemoteInterfaceName();
		}
		return EMPTY_STRING;
	}

	/**
	 * @return
	 */
	private Object calculateHomeInterface() {
		EnterpriseBean targetBean = (EnterpriseBean) getProperty(TARGET);
		if (targetBean != null) {
			if (getBooleanProperty(IS_LOCAL))
				return targetBean.getLocalHomeInterfaceName();
			return targetBean.getHomeInterfaceName();
		}
		return EMPTY_STRING;
	}

	/**
	 * @return
	 */
	private Object calculateLink() {
		EnterpriseBean targetBean = (EnterpriseBean) getProperty(TARGET);
		if (getBooleanProperty(TARGET_IN_DIFFERENT_EAR))
			return EMPTY_STRING; //$NON-NLS-1$
		if (targetBean != null)
			return computeLink();
		return EMPTY_STRING;
	}

	/**
	 * @return
	 */
	private String calculateRefType() {
		EnterpriseBean targetBean = (EnterpriseBean) getProperty(TARGET);
		return (targetBean != null && targetBean.isEntity()) ? "Entity" : "Session"; //$NON-NLS-1$ //$NON-NLS-2$);

	}

	/**
	 * @return
	 */
	private Boolean calculateLocalValue() {
		String type = getStringProperty(LOCAL_REMOTE_TYPE);
		if (type == null)
			return Boolean.TRUE;
		if (type.equals(LOCAL_STRING)) //$NON-NLS-1$
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

	protected boolean isTargetJ2EEVersion12() {
		EObject target = (EObject) getProperty(TARGET);
		if (target != null && target instanceof EnterpriseBean) {
			return (((EnterpriseBean) getProperty(TARGET)).getVersionID() <= J2EEVersionConstants.EJB_1_1_ID);
		}
		return false;
	}

	private boolean isOwnerJ2EEVersion12() {
		if ((ownerType == EJB_TYPE && ((EnterpriseBean) getProperty(OWNER)).getVersionID() <= J2EEVersionConstants.EJB_1_1_ID) || (ownerType == APP_CLIENT_TYPE && ((ApplicationClient) getProperty(OWNER)).getVersionID() <= J2EEVersionConstants.J2EE_1_2_ID) || (ownerType == WEB_TYPE && ((WebApp) getProperty(OWNER)).getVersionID() <= J2EEVersionConstants.WEB_2_2_ID)) {
			return true;
		}
		return false;
	}

	/**
	 *  
	 */
	private String getDefaultLocalRemote() {
		EnterpriseBean target = (EnterpriseBean) getProperty(TARGET);
		String type = REMOTE_STRING; //$NON-NLS-1$
		if (ownerType == APP_CLIENT_TYPE) {
			type = REMOTE_STRING;
		} else if (getBooleanProperty(IN_WORKSPACE)) {
			if (target != null) {
				if (isOwnerJ2EEVersion12()) {
					type = REMOTE_STRING;
				} else if (!getBooleanProperty(TARGET_IN_DIFFERENT_EAR)) {
					if (target.hasLocalClient())
						type = LOCAL_STRING; //$NON-NLS-1$
					else
						type = REMOTE_STRING;
				} else {
					if (target.hasRemoteClient())
						type = REMOTE_STRING; //$NON-NLS-1$
					else
						type = LOCAL_STRING;
				}
			}
		} else
			type = REMOTE_STRING; //$NON-NLS-1$
		return type;
	}


	/**
	 * @return
	 */
	private Object calculateDefaultName() {
		EnterpriseBean targetBean = (EnterpriseBean) getProperty(TARGET);
		if (targetBean != null) {
			if (getBooleanProperty(CREATE_VALID_DEFAULT_NAME))
				return calculateUniqueDefaultName(targetBean.getName());
			return "ejb/" + targetBean.getName(); //$NON-NLS-1$
		}
		return null;
	}

	/**
	 * @return
	 */
	private Object calculateUniqueDefaultName(String beanName) {
		String refName = "ejb/" + beanName; //$NON-NLS-1$
		if (validateReferenceDoesNotExist(refName)) {
			return refName;
		}
		for (int i = 1; i < 20; i++) {
			refName = "ejb/" + beanName + "_" + i; //$NON-NLS-1$ //$NON-NLS-2$
			if (validateReferenceDoesNotExist(refName))
				return refName;
		}
		return refName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.reference.ReferenceDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		IStatus status = super.doValidateProperty(propertyName);
		if (status.isOK()) {
			if (propertyName.equals(TARGET) && getBooleanProperty(IN_WORKSPACE)) {
				Object target = getProperty(TARGET);
				if (target == null || !(target instanceof EnterpriseBean))
					return WTPCommonPlugin.createErrorStatus(J2EECreationResourceHandler.getString("EJBReferenceDataModel_UI_12")); //$NON-NLS-1$
				else if (!getBooleanProperty(TARGET_IN_SAME_PROJECT) && getBooleanProperty(TARGET_IN_DIFFERENT_EAR) && getBooleanProperty(TARGET_ALSO_EXIST_IN_SAME_EAR)) {
					IProject ownerProject = getOwnerProject();
					IProject project = (IProject) getProperty(TARGET_ALSO_IN_EAR);
					if (project != null && ownerProject != null) {
						EARNatureRuntime earRuntime = EARNatureRuntime.getRuntime(project);
						return WTPCommonPlugin.createWarningStatus(J2EECreationResourceHandler.getString("TARGET_ALSO_EXIST_IN_SAME_EAR", new String[]{earRuntime.getApplication().getDisplayName(), ownerProject.getName()})); //$NON-NLS-1$
					}

				}
			} else if (propertyName.equals(LOCAL_REMOTE_TYPE)) {
				if (ownerType == APP_CLIENT_TYPE && getBooleanProperty(IS_LOCAL)) {
					return WTPCommonPlugin.createErrorStatus(J2EECreationResourceHandler.getString("APP_CLIENT_ONLY_HAVE_REMOTE")); //$NON-NLS-1$
				} else if (isOwnerJ2EEVersion12() && getBooleanProperty(IS_LOCAL)) {
					return WTPCommonPlugin.createErrorStatus(J2EECreationResourceHandler.getString("J2EE_1_2_ONLY_HAVE_REMOTE")); //$NON-NLS-1$
				} else if (getBooleanProperty(IS_LOCAL) && (!getBooleanProperty(IN_WORKSPACE) || getBooleanProperty(TARGET_IN_DIFFERENT_EAR))) {
					return WTPCommonPlugin.createWarningStatus(J2EECreationResourceHandler.getString("USE_REMOTE_FOR_DIFFERENT_EAR")); //$NON-NLS-1$
				} else if (!getBooleanProperty(IS_LOCAL) && getBooleanProperty(IN_WORKSPACE) && !getBooleanProperty(TARGET_IN_DIFFERENT_EAR) && ownerType != APP_CLIENT_TYPE && !this.isOwnerJ2EEVersion12() && !this.isTargetJ2EEVersion12()) {
					EnterpriseBean target = (EnterpriseBean) getProperty(TARGET);
					if (target.getVersionID() > J2EEVersionConstants.EJB_1_1_ID)
						return WTPCommonPlugin.createWarningStatus(J2EECreationResourceHandler.getString("USE_LOCAL_FOR_DIFFERENT_EAR")); //$NON-NLS-1$
				}
			} else if ((propertyName.equals(HOME_INTERFACE) && (getProperty(HOME_INTERFACE) == null || getStringProperty(HOME_INTERFACE).length() == 0)) || (propertyName.equals(REMOTE_INTERACE) && (getProperty(REMOTE_INTERACE) == null || getStringProperty(REMOTE_INTERACE).length() == 0))) {
				if (getBooleanProperty(IN_WORKSPACE)) {
					if (getBooleanProperty(IS_LOCAL)) {
						return WTPCommonPlugin.createErrorStatus(J2EECreationResourceHandler.getString("EJBReferenceDataModel_UI_10")); //$NON-NLS-1$
					}
					return WTPCommonPlugin.createErrorStatus(J2EECreationResourceHandler.getString("EJBReferenceDataModel_UI_11")); //$NON-NLS-1$
				}
				if (getBooleanProperty(IS_LOCAL)) {
					if (propertyName.equals(HOME_INTERFACE))
						return WTPCommonPlugin.createErrorStatus(J2EECreationResourceHandler.getString("Local_home_cannot_be_empty_UI_")); //$NON-NLS-1$
					return WTPCommonPlugin.createErrorStatus(J2EECreationResourceHandler.getString("Local_cannot_be_empty_UI_")); //$NON-NLS-1$
				}
				if (propertyName.equals(HOME_INTERFACE))
					return WTPCommonPlugin.createErrorStatus(J2EECreationResourceHandler.getString("Home_cannot_be_empty_UI_")); //$NON-NLS-1$
				return WTPCommonPlugin.createErrorStatus(J2EECreationResourceHandler.getString("Remote_cannot_be_empty_UI_")); //$NON-NLS-1$

			}
		}
		return status;
	}

	public String computeLink() {
		EnterpriseBean targetBean = (EnterpriseBean) getProperty(TARGET);
		if (targetBean == null)
			return EMPTY_STRING; //$NON-NLS-1$
		IProject targetModuleProject = ProjectUtilities.getProject(targetBean.getEjbJar());
		if (targetModuleProject == null)
			return EMPTY_STRING; //$NON-NLS-1$

		IProject ownerProject = getOwnerProject();
		if (targetModuleProject.equals(ownerProject)) {
			return targetBean.getName();
		}
		if ((ownerType == EJB_TYPE && ((EnterpriseBean) getProperty(OWNER)).getVersionID() <= J2EEVersionConstants.EJB_1_1_ID) || (ownerType == APP_CLIENT_TYPE && ((ApplicationClient) getProperty(OWNER)).getVersionID() == J2EEVersionConstants.J2EE_1_2_ID)) {
			return targetBean.getName();
		}
		return calculateLinkForEARRelativeBean(targetBean, ownerProject, targetModuleProject);
	}

	/**
	 * @return
	 */
	public IProject getOwnerProject() {
		IProject ownerProject = null;
		if (ownerType == EJB_TYPE) {
			EnterpriseBean ownerBean = (EnterpriseBean) getProperty(OWNER);
			ownerProject = ProjectUtilities.getProject(ownerBean.getEjbJar());
		} else if (ownerType == APP_CLIENT_TYPE || ownerType == WEB_TYPE) {
			ownerProject = ProjectUtilities.getProject(getProperty(OWNER));
		}
		return ownerProject;
	}

	private String calculateLinkForEARRelativeBean(EnterpriseBean bean, IProject ownerProject, IProject targetModuleProject) {
		J2EEModuleNature targetNature = (J2EEModuleNature) J2EENature.getRuntime(targetModuleProject, IEJBNatureConstants.NATURE_ID);
		if (targetNature == null)
			return EMPTY_STRING; //$NON-NLS-1$
		EARNatureRuntime[] runtimes = targetNature.getReferencingEARProjects();
		if (runtimes.length < 1)
			return EMPTY_STRING; //$NON-NLS-1$
		Module targetModule = runtimes[0].getModule(targetModuleProject);

		J2EEModuleNature ownerNature = null;
		Module ownerModule = null;
		if (ownerType == EJB_TYPE) {
			ownerNature = (J2EEModuleNature) J2EEModuleNature.getRuntime(ownerProject, IEJBNatureConstants.NATURE_ID);
		} else if (ownerType == APP_CLIENT_TYPE) {
			ownerNature = (J2EEModuleNature) J2EEModuleNature.getRuntime(ownerProject, IApplicationClientNatureConstants.NATURE_ID);
		} else if (ownerType == WEB_TYPE) {
			ownerNature = (J2EEModuleNature) J2EEModuleNature.getRuntime(ownerProject, IWebNatureConstants.J2EE_NATURE_ID);
		}
		if (ownerNature == null)
			return EMPTY_STRING; //$NON-NLS-1$
		runtimes = ownerNature.getReferencingEARProjects();
		if (runtimes.length < 1)
			return EMPTY_STRING; //$NON-NLS-1$
		ownerModule = runtimes[0].getModule(ownerProject);

		if (ownerModule == null || targetModule == null)
			return EMPTY_STRING; //$NON-NLS-1$
		return J2EEProjectUtilities.computeRelativeText(ownerModule.getUri(), targetModule.getUri(), bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new EJBReferenceCreationOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#basicIsEnabled(java.lang.String)
	 */
	protected Boolean basicIsEnabled(String propertyName) {
		if (propertyName.equals(LOCAL_REMOTE_TYPE)) {
			if (this.getBooleanProperty(IN_WORKSPACE)) {
				if (getProperty(TARGET) == null)
					return Boolean.FALSE;
				else if (ownerType == APP_CLIENT_TYPE || isOwnerJ2EEVersion12() || isTargetJ2EEVersion12())
					return Boolean.FALSE;
				else
					return Boolean.TRUE;
			}
			if (ownerType == APP_CLIENT_TYPE || isOwnerJ2EEVersion12())
				return Boolean.FALSE;
			return Boolean.TRUE;
		}
		return super.basicIsEnabled(propertyName);
	}

	public boolean shouldCreateClientJar() {
		boolean isInWorkspace = getBooleanProperty(IN_WORKSPACE);
		boolean isInSameProject = getBooleanProperty(TARGET_IN_SAME_PROJECT);
		boolean isTargetAndSourceHaveSameEars = getBooleanProperty(TARGET_AND_SOURCE_HAVE_SAME_EARS);
		if (isInWorkspace) {
			if (isInSameProject)
				return false;
			else if (isTargetAndSourceHaveSameEars)
				return false;
			else
				return true;
		}
		return false;
	}

}