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
 * Created on Oct 27, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 * Comments
 */
package org.eclipse.jst.j2ee.internal.servertarget;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.internal.common.CommonEditResourceHandler;
import org.eclipse.jst.j2ee.internal.project.J2EECreationResourceHandler;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModel;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ServerTargetDataModel extends WTPOperationDataModel {

	private static final String DEFAULT_TARGET_ID = "org.eclipse.jst.server.core.runtimeType"; //$NON-NLS-1$
	/**
	 * required, type String
	 */
	public static final String PROJECT_NAME = "ServerTargetDataModel.PROJECT_NAME"; //$NON-NLS-1$
	/**
	 * required, not defaulted. If null, will not run.
	 */
	public static final String RUNTIME_TARGET_ID = "ServerTargetDataModel.RUNTIME_TARGET_ID"; //$NON-NLS-1$
	/**
	 * Used to provide a better display name for the server target ID. The model will translate this
	 * property to the SERVER_TARGET_ID property.
	 */
	public static final String RUNTIME_TARGET_NAME = "ServerTargetDataModel.RUNTIME_TARGET_NAME"; //$NON-NLS-1$
	/**
	 * Optional - This needs to be set if the PROJECT_NAME does not exist.
	 * 
	 * @link J2EEVersionConstants
	 */
	public static final String J2EE_VERSION_ID = "ServerTargetDataModel.J2EE_VERSION_ID"; //$NON-NLS-1$

	/**
	 * optional, default true, type Boolean. Set this to true if the operation is supposed to update
	 * all dependent modules and projects in an ear if the passed project name is an ear project
	 */
	public static final String UPDATE_MODULES = "ServerTargetDataModel.UPDATE_MODULES"; //$NON-NLS-1$

	/**
	 * Optional - This needs to be set if the PROJECT_NAME does not exist.
	 * 
	 * @link XMLResource#APP_CLIENT_TYPE
	 * @link XMLResource#APPLICATION_TYPE
	 * @link XMLResource#EJB_TYPE
	 * @link XMLResource#RAR_TYPE
	 * @link XMLResource#WEB_APP_TYPE
	 */
	public static final String DEPLOYMENT_TYPE_ID = "ServerTargetDataModel.DD_TYPE_ID"; //$NON-NLS-1$

	private static final String NONE_SERVER_TARGET_STRING = CommonEditResourceHandler.getString("NONE_SERVER_TARGET_STRING_UI_"); //$NON-NLS-1$

	//private List validServerTargets;

	public ServerTargetDataModel() {
		super();
		//TODO add listening mechanism to & fire change events when new server targets are
		// added/removed
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new ServerTargetOperation(this);
	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(PROJECT_NAME);
		addValidBaseProperty(RUNTIME_TARGET_ID);
		addValidBaseProperty(RUNTIME_TARGET_NAME);
		addValidBaseProperty(J2EE_VERSION_ID);
		addValidBaseProperty(DEPLOYMENT_TYPE_ID);
		addValidBaseProperty(UPDATE_MODULES);
	}

	public IProject getProject() {
		String name = (String) getProperty(PROJECT_NAME);
		if (name != null && name.length() > 0)
			return ResourcesPlugin.getWorkspace().getRoot().getProject(name);
		return null;
	}

	public IRuntime getRuntimeTarget() {
		String serverTargetId = (String) getProperty(RUNTIME_TARGET_ID);
		return ServerCore.getResourceManager().getRuntime(serverTargetId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(RUNTIME_TARGET_NAME))
			return convertIdToName(getStringProperty(RUNTIME_TARGET_ID));
		else if (propertyName.equals(RUNTIME_TARGET_ID))
			return getDefaultServerTargetID();
		else if (propertyName.equals(J2EE_VERSION_ID))
			return getDefaultVersionID();
		else if (propertyName.equals(UPDATE_MODULES)) {
			return Boolean.TRUE;
		}
		return super.getDefaultProperty(propertyName);
	}

	private Integer getDefaultVersionID() {
		return new Integer(J2EEVersionConstants.J2EE_1_4_ID);
	}

	/**
	 * @return
	 */
	private Object getDefaultServerTargetID() {
		List targets = getValidServerTargets();
		if (!targets.isEmpty()) {
			IRuntime target = null;
			for (int i = targets.size() - 1; i < targets.size() && i >= 0; i--) {
				target = (IRuntime) targets.get(i);
				if (DEFAULT_TARGET_ID.equals(target.getId()))
					return target.getId();
			}
			if (target != null)
				return target.getId();
		}
		return null;
	}

	/**
	 * @param string
	 * @return
	 */
	private String convertIdToName(String id) {
		IRuntime target = getServerTargetByID(id);
		List validServerTargets = getValidServerTargets();
		if (target == null && validServerTargets != null && !validServerTargets.isEmpty())
			target = (IRuntime) validServerTargets.get(0);
		if (target != null)
			return target.getName();
		return NONE_SERVER_TARGET_STRING;
	}

	/**
	 * This should only be used by clients that need to interact with the Server tooling directly.
	 * The return value corresponds to the type id associated with a runtime target.
	 * 
	 * @return
	 */
	public String computeTypeId() {
		int type = -1;
		if (isSet(DEPLOYMENT_TYPE_ID))
			type = getIntProperty(DEPLOYMENT_TYPE_ID);
		else {
			J2EENature nature = J2EENature.getRegisteredRuntime(getProject());
			if (nature != null)
				type = nature.getDeploymentDescriptorType();
			else
				type = getIntProperty(DEPLOYMENT_TYPE_ID);
		}
		return computeTypeId(type);
	}

	/**
	 * @param deploymentDescriptorType2
	 * @return
	 */
	private String computeTypeId(int deploymentDescriptorType) {
		switch (deploymentDescriptorType) {
			case XMLResource.APPLICATION_TYPE :
				return IServerTargetConstants.EAR_TYPE;
			case XMLResource.APP_CLIENT_TYPE :
				return IServerTargetConstants.APP_CLIENT_TYPE;
			case XMLResource.EJB_TYPE :
				return IServerTargetConstants.EJB_TYPE;
			case XMLResource.WEB_APP_TYPE :
				return IServerTargetConstants.WEB_TYPE;
			case XMLResource.RAR_TYPE :
				return IServerTargetConstants.CONNECTOR_TYPE;
		}
		return null;
	}

	/**
	 * This is only to be used by clients that need to interact with the Server tooling directly.
	 * The return value corresponds to the version id associated with a runtime target.
	 */
	public String computeVersionId() {
		int version = -1;
		if (isSet(J2EE_VERSION_ID))
			version = getIntProperty(J2EE_VERSION_ID);
		else {
			J2EENature nature = J2EENature.getRegisteredRuntime(getProject());
			if (nature != null)
				version = nature.getJ2EEVersion();
			else
				version = getIntProperty(J2EE_VERSION_ID);
		}
		return computeVersionId(version);
	}

	/**
	 * @param version
	 * @return
	 */
	private String computeVersionId(int version) {
		switch (version) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				return IServerTargetConstants.J2EE_12;
			case J2EEVersionConstants.J2EE_1_3_ID :
				return IServerTargetConstants.J2EE_13;
			case J2EEVersionConstants.J2EE_1_4_ID :
				return IServerTargetConstants.J2EE_14;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doGetValidPropertyValues(java.lang.String)
	 */
	protected Object[] doGetValidPropertyValues(String propertyName) {
		if (propertyName.equals(RUNTIME_TARGET_NAME))
			return getValidServerTargetNames();
		if (propertyName.equals(RUNTIME_TARGET_ID))
			return getValidServerTargetIds();
		return super.doGetValidPropertyValues(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		if (propertyName.equals(RUNTIME_TARGET_NAME)) {
			String id = convertNameToID((String) propertyValue);
			super.setProperty(RUNTIME_TARGET_ID, id);
			return false;
		}
		super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(J2EE_VERSION_ID)) {
			IRuntime target = getServerTargetByID(getStringProperty(RUNTIME_TARGET_ID));
			if (target == null)
				setProperty(RUNTIME_TARGET_ID, null);
			notifyValidValuesChange(RUNTIME_TARGET_NAME);
		}
		return true;
	}

	/**
	 * @param serverTargetLabel
	 */
	private String convertNameToID(String serverTargetLabel) {
		IRuntime target = getServerTargetByLabel(serverTargetLabel);
		return target != null ? target.getId() : null;
	}

	private IRuntime getServerTargetByLabel(String serverTargetLabel) {
		List targets = getValidServerTargets();
		IRuntime target;
		for (int i = 0; i < targets.size(); i++) {
			target = (IRuntime) targets.get(i);
			if (serverTargetLabel.equals(target.getName()))
				return target;
		}
		return null;
	}

	private IRuntime getServerTargetByID(String id) {
		List targets = getValidServerTargets();
		IRuntime target;
		for (int i = 0; i < targets.size(); i++) {
			target = (IRuntime) targets.get(i);
			if (id.equals(target.getId()))
				return target;
		}
		return null;
	}

	/**
	 * @return
	 */
	private String[] getValidServerTargetNames() {
		List targets = getValidServerTargets();
		String[] names = null;
		if (!targets.isEmpty()) {
			int serverTargetListSize = targets.size();
			names = new String[serverTargetListSize];
			for (int i = 0; i < targets.size(); i++) {
				IRuntime runtime = (IRuntime) targets.get(i);
				names[i] = runtime.getName();
			}
		} else {
			names = new String[0];
		}
		return names;
	}

	private String[] getValidServerTargetIds() {
		List targets = getValidServerTargets();
		String[] names = null;
		if (!targets.isEmpty()) {
			int serverTargetListSize = targets.size();
			names = new String[serverTargetListSize];
			for (int i = 0; i < targets.size(); i++) {
				names[i] = ((IRuntime) targets.get(i)).getId();
			}
		} else {
			names = new String[0];
		}
		return names;
	}

	/**
	 * @return
	 */
	private List getValidServerTargets() {
		List validServerTargets = null;
		String type = computeTypeId();
		if (type != null) {
			String version = computeVersionId();
			if (version != null) {
				validServerTargets = ServerTargetHelper.getServerTargets(type, version);
				if (validServerTargets != null && validServerTargets.isEmpty())
					validServerTargets = null;
			}
		}
		if (validServerTargets == null)
			return Collections.EMPTY_LIST;
		return validServerTargets;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(RUNTIME_TARGET_ID))
			return validateServerTarget();
		return super.doValidateProperty(propertyName);
	}

	/**
	 * @return
	 */
	private IStatus validateServerTarget() {
		List targets = getValidServerTargets();
		if (targets.isEmpty()) {
			return J2EEPlugin.newErrorStatus(J2EECreationResourceHandler.getString("ServerTargetDataModel_UI_7"), null); //$NON-NLS-1$
		}
		IRuntime target = getRuntimeTarget();
		if (target == null) {
			return J2EEPlugin.newErrorStatus(J2EECreationResourceHandler.getString("ServerTargetDataModel_UI_8"), null); //$NON-NLS-1$
		} else if (!targets.contains(target)) {
			return J2EEPlugin.newErrorStatus(J2EECreationResourceHandler.getString("ServerTargetDataModel_UI_9"), null); //$NON-NLS-1$
		}
		return OK_STATUS;
	}

	/**
	 *  
	 */
	public void resetValidServerTargets() {
		//validServerTargets = null;
	}
}