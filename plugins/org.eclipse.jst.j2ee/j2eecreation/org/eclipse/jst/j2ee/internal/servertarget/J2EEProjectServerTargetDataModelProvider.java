package org.eclipse.jst.j2ee.internal.servertarget;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EECreationResourceHandler;
import org.eclipse.jst.j2ee.project.datamodel.properties.IJ2EEProjectServerTargetDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerUtil;
import org.eclipse.wst.server.core.internal.ResourceManager;

public class J2EEProjectServerTargetDataModelProvider extends AbstractDataModelProvider implements IJ2EEProjectServerTargetDataModelProperties {

	private static final String DEFAULT_TARGET_ID = "org.eclipse.jst.server.core.runtimeType"; //$NON-NLS-1$

	public void init() {
		model.setProperty(RUNTIME_TARGET_ID, getDefaultServerTargetID());
		super.init();
	}

	public IDataModelOperation getDefaultOperation() {
		return new J2EEProjectServerTargetOp(model);
	}

	public Collection getPropertyNames() {
		Collection propertyNames = super.getPropertyNames();
		propertyNames.add(PROJECT_NAME);
		propertyNames.add(RUNTIME_TARGET_ID);
		propertyNames.add(J2EE_VERSION_ID);
		propertyNames.add(DEPLOYMENT_TYPE_ID);
		propertyNames.add(UPDATE_MODULES);
		return propertyNames;
	}

	public IProject getProject() {
		String name = (String) getProperty(PROJECT_NAME);
		if (name != null && name.length() > 0)
			return ResourcesPlugin.getWorkspace().getRoot().getProject(name);
		return null;
	}

	public IRuntime getRuntimeTarget() {
		String serverTargetId = (String) getProperty(RUNTIME_TARGET_ID);
		return ResourceManager.getInstance().getRuntime(serverTargetId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultProperty(java.lang.String)
	 */
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(RUNTIME_TARGET_ID))
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
	 * This should only be used by clients that need to interact with the Server tooling directly.
	 * The return value corresponds to the type id associated with a runtime target.
	 * 
	 * @return
	 */
	public String computeTypeId() {
		int type = -1;
		if (getDataModel().isPropertySet(DEPLOYMENT_TYPE_ID))
			type = getIntProperty(DEPLOYMENT_TYPE_ID);
		// else {
		// J2EENature nature = J2EENature.getRegisteredRuntime(getProject());
		// if (nature != null)
		// type = nature.getDeploymentDescriptorType();
		// else
		// type = getIntProperty(DEPLOYMENT_TYPE_ID);
		// }
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
		if (getDataModel().isPropertySet(J2EE_VERSION_ID))
			version = getIntProperty(J2EE_VERSION_ID);
		// else {
		// J2EENature nature = J2EENature.getRegisteredRuntime(getProject());
		// if (nature != null)
		// version = nature.getJ2EEVersion();
		// else
		// version = getIntProperty(J2EE_VERSION_ID);
		// }
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
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doGetValidPropertyValues(java.lang.String)
	 */
	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		if (propertyName.equals(RUNTIME_TARGET_ID))
			return getValidServerTargetDescriptors();
		return super.getValidPropertyDescriptors(propertyName);
	}

	private DataModelPropertyDescriptor[] getValidServerTargetDescriptors() {
		List targets = getValidServerTargets();
		DataModelPropertyDescriptor[] descriptors = null;
		if (!targets.isEmpty()) {
			int serverTargetListSize = targets.size();
			descriptors = new DataModelPropertyDescriptor[serverTargetListSize];
			for (int i = 0; i < targets.size(); i++) {
				IRuntime runtime = (IRuntime) targets.get(i);
				descriptors[i] = new DataModelPropertyDescriptor(runtime.getId(), runtime.getName());
			}
		} else {
			descriptors = new DataModelPropertyDescriptor[0];
		}
		return descriptors;
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		if (propertyName.equals(J2EE_VERSION_ID)) {
			IRuntime target = getServerTargetByID(getStringProperty(RUNTIME_TARGET_ID));
			if (target == null)
				setProperty(RUNTIME_TARGET_ID, null);
			model.notifyPropertyChange(RUNTIME_TARGET_ID, IDataModel.VALID_VALUES_CHG);
		} else if (RUNTIME_TARGET_ID.equals(propertyName)) {
			setProperty(RUNTIME_TARGET_ID, propertyValue);
		}
		return true;
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
	private List getValidServerTargets() {
		List validServerTargets = null;
		// TODO: api is needed from the server target helper to get all server targets
		// validServerTargets = ServerTargetHelper.getServerTargets(IServerTargetConstants.EAR_TYPE,
		// IServerTargetConstants.J2EE_14);
		validServerTargets = getServerTargets("", ""); //$NON-NLS-1$  //$NON-NLS-2$
		if (validServerTargets != null && validServerTargets.isEmpty())
			validServerTargets = null;
		if (validServerTargets == null)
			return Collections.EMPTY_LIST;
		return validServerTargets;
	}

	public static List getServerTargets(String type, String version) {
		List targets = Arrays.asList(ServerUtil.getRuntimes(type, version));
		return targets;
	}

	/**
	 * @return
	 */
	// private List getValidServerTargets() {
	// List validServerTargets = null;
	// String type = computeTypeId();
	// if (type != null) {
	// String version = computeVersionId();
	// if (version != null) {
	// validServerTargets = ServerTargetHelper.getServerTargets(type, version);
	// if (validServerTargets != null && validServerTargets.isEmpty())
	// validServerTargets = null;
	// }
	// }
	// if (validServerTargets == null)
	// return Collections.EMPTY_LIST;
	// return validServerTargets;
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	public IStatus validate(String propertyName) {
		if (propertyName.equals(RUNTIME_TARGET_ID))
			return validateServerTarget();
		return super.validate(propertyName);
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

}
