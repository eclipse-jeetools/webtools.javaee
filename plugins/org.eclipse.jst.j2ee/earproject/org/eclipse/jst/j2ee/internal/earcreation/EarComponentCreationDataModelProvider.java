package org.eclipse.jst.j2ee.internal.earcreation;

import java.util.Collections;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.EARComponentCreationOp;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.datamodel.properties.IEarComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.componentcore.internal.operation.ComponentCreationDataModel;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.ServerCore;

public class EarComponentCreationDataModelProvider extends J2EEComponentCreationDataModelProvider
		implements IEarComponentCreationDataModelProperties {
	
	/**
	 * 
	 */
	public IDataModelOperation getDefaultOperation() {
		return new EARComponentCreationOp(model);
	}

	/**
	 * @return Returns the default J2EE spec level based on the Global J2EE Preference
	 */
	protected Integer getDefaultComponentVersion() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		switch (highestJ2EEPref) {
			case (J2EEVersionConstants.J2EE_1_4_ID) :
				return new Integer(J2EEVersionConstants.J2EE_1_4_ID);
			case (J2EEVersionConstants.J2EE_1_3_ID) :
				return new Integer(J2EEVersionConstants.J2EE_1_3_ID);
			case (J2EEVersionConstants.J2EE_1_2_ID) :
				return new Integer(J2EEVersionConstants.J2EE_1_2_ID);
			default :
				return new Integer(J2EEVersionConstants.J2EE_1_4_ID);
		}
	}

 	public String[] getPropertyNames() {
		String[] props = new String[]{J2EE_COMPONENT_LIST};
		return combineProperties(super.getPropertyNames(), props);
	}
	
	/**
	 * 
	 */
	protected AddComponentToEnterpriseApplicationDataModel createModuleNestedModel() {
		return new AddComponentToEnterpriseApplicationDataModel();
	}

	/**
	 * 
	 */
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(DD_FOLDER)) {
			return IPath.SEPARATOR + this.getModuleName() + IPath.SEPARATOR + "META_INF"; //$NON-NLS-1$
		} else if (propertyName.equals(UI_SHOW_EAR_SECTION)) {
			return Boolean.FALSE;
		}
		else if (propertyName.equals(J2EE_COMPONENT_LIST)) {
			return Collections.EMPTY_LIST;
		}
		return super.getDefaultProperty(propertyName);
	}		

	/**
	 * 
	 */
	public DataModelPropertyDescriptor getPropertyDescriptor(String propertyName) {
		if (propertyName.equals(COMPONENT_VERSION)) {
			Integer propertyValue = (Integer) getProperty(propertyName);
			String description = null;
			switch (propertyValue.intValue()) {
				case J2EEVersionConstants.WEB_2_2_ID :
					description = J2EEVersionConstants.VERSION_2_2_TEXT;
					break;
				case J2EEVersionConstants.WEB_2_3_ID :
					description = J2EEVersionConstants.VERSION_2_3_TEXT;
					break;
				case J2EEVersionConstants.WEB_2_4_ID :
				default :
					description = J2EEVersionConstants.VERSION_2_4_TEXT;
					break;
			}
			return new DataModelPropertyDescriptor(propertyValue, description);
		}
		return super.getPropertyDescriptor(propertyName);
	}

	/**
	 * 
	 */
	public DataModelPropertyDescriptor[] getValidComponentVersionDescriptors() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		DataModelPropertyDescriptor[] descriptors = null;
		switch (highestJ2EEPref) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				descriptors = new DataModelPropertyDescriptor[1];
				descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), 
						J2EEVersionConstants.VERSION_1_2_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_3_ID :
				descriptors = new DataModelPropertyDescriptor[2];
				descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), 
						J2EEVersionConstants.VERSION_1_2_TEXT);
				descriptors[1] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_3_ID), 
						J2EEVersionConstants.VERSION_1_3_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				descriptors = new DataModelPropertyDescriptor[3];
				descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), 
						J2EEVersionConstants.VERSION_1_2_TEXT);
				descriptors[1] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_3_ID), 
						J2EEVersionConstants.VERSION_1_3_TEXT);
				descriptors[2] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_4_ID), 
						J2EEVersionConstants.VERSION_1_4_TEXT);
				break;
		}
		return descriptors;
	}

	/**
	 * 
	 */
	protected int convertModuleVersionToJ2EEVersion(int moduleVersion) {
		return moduleVersion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEModuleCreationDataModel#getModuleType()
	 */
	protected EClass getComponentType() {
		return CommonarchiveFactoryImpl.getPackage().getEARFile();
	}

	protected String getComponentExtension() {
		return ".ear"; //$NON-NLS-1$
	}

	public IStatus validate(String propertyName) {
		if (propertyName.equals(ComponentCreationDataModel.PROJECT_NAME)) {
			// validate server target
			String projectName = getDataModel().getStringProperty(ComponentCreationDataModel.PROJECT_NAME);
			if (projectName != null && projectName.length() != 0) {
				IProject project = ProjectUtilities.getProject(projectName);
				if (project != null) {
					IRuntime runtime = ServerCore.getProjectProperties(project)
							.getRuntimeTarget();
					if (runtime != null) {
						IRuntimeType type = runtime.getRuntimeType();
						String typeId = type.getId();
						if (typeId.startsWith("org.eclipse.jst.server.tomcat")) { //$NON-NLS-1$
							String msg = EARCreationResourceHandler
									.getString(EARCreationResourceHandler.SERVER_TARGET_NOT_SUPPORT_EAR);
							return WTPCommonPlugin.createErrorStatus(msg);
						}
					}
				}
			}
		}
		return super.validate(propertyName);
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean returnValue = super.propertySet(propertyName, propertyValue);

		if (propertyName.equals(COMPONENT_NAME)){
			setProperty(COMPONENT_DEPLOY_NAME, getDataModel().getStringProperty(COMPONENT_NAME));
		} 
		return returnValue;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EECreationDataModel#getModuleID()
	 */
	protected String getComponentID() {
		return IModuleConstants.JST_EAR_MODULE;
	}

}
