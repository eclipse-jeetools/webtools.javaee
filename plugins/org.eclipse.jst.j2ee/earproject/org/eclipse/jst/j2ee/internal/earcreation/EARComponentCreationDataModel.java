/*
 * Created on Mar 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.earcreation;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.application.operations.AddModuleToEARDataModel;
import org.eclipse.jst.j2ee.application.operations.EARComponentCreationOperation;
import org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EECreationDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.frameworks.operations.WTPPropertyDescriptor;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.ServerCore;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

public class EARComponentCreationDataModel extends J2EEComponentCreationDataModel {

	public WTPOperation getDefaultOperation() {
		return new EARComponentCreationOperation(this);
	}

	/**
	 * @return Returns the default J2EE spec level based on the Global J2EE Preference
	 */
	protected Integer getDefaultJ2EEModuleVersion() {
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


	protected void init() {
		super.init();
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		return super.doSetProperty(propertyName, propertyValue);
	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
	}

	protected AddModuleToEARDataModel createModuleNestedModel() {
		return null;
	}

	private Object updateAddToEar() {
		//IRuntime type = getServerTargetDataModel().getRuntimeTarget();
//		Boolean ret = Boolean.FALSE;
//		IRuntime type = getProjectDataModel().getServerTargetDataModel().getRuntimeTarget();
//		if (type == null)
//			return Boolean.TRUE;
//		IRuntimeType rType = type.getRuntimeType();
//		if (rType == null)
//			return Boolean.TRUE;
//		return ret;
		//return new Boolean(!rType.getVendor().equals(APACHE_VENDER_NAME));
		return null;
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(DD_FOLDER)) {
			return IPath.SEPARATOR + this.getModuleName() + IPath.SEPARATOR + "META_INF";
		} else if (propertyName.equals(UI_SHOW_EAR_SECTION)) {
			return Boolean.FALSE;
		}
		return super.getDefaultProperty(propertyName);
	}		

	protected WTPPropertyDescriptor doGetPropertyDescriptor(String propertyName) {
		if (propertyName.equals(J2EE_MODULE_VERSION)) {
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
			return new WTPPropertyDescriptor(propertyValue, description);
		}
		return super.doGetPropertyDescriptor(propertyName);
	}

	protected WTPPropertyDescriptor[] getValidJ2EEModuleVersionDescriptors() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		WTPPropertyDescriptor[] descriptors = null;
		switch (highestJ2EEPref) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				descriptors = new WTPPropertyDescriptor[1];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), 
						J2EEVersionConstants.VERSION_1_2_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_3_ID :
				descriptors = new WTPPropertyDescriptor[2];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), 
						J2EEVersionConstants.VERSION_1_2_TEXT);
				descriptors[1] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_3_ID), 
						J2EEVersionConstants.VERSION_1_3_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				descriptors = new WTPPropertyDescriptor[3];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), 
						J2EEVersionConstants.VERSION_1_2_TEXT);
				descriptors[1] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_3_ID), 
						J2EEVersionConstants.VERSION_1_3_TEXT);
				descriptors[2] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_4_ID), 
						J2EEVersionConstants.VERSION_1_4_TEXT);
				break;
		}
		return descriptors;
	}

	protected int convertModuleVersionToJ2EEVersion(int moduleVersion) {
		return moduleVersion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEModuleCreationDataModel#getModuleType()
	 */
	protected EClass getModuleType() {
		return CommonarchiveFactoryImpl.getPackage().getEARFile();
	}

	protected String getModuleExtension() {
		return ".ear"; //$NON-NLS-1$
	}

	protected Boolean basicIsEnabled(String propertyName) {
		if (USE_ANNOTATIONS.equals(propertyName)) {
			if (getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
				return Boolean.FALSE;
			return Boolean.TRUE;
		}
		return super.basicIsEnabled(propertyName);
	}

	protected IStatus doValidateProperty(String propertyName) {
		// validate server target
		String projectName = getStringProperty(J2EECreationDataModel.PROJECT_NAME);
		if (projectName != null && projectName.length()!= 0) {
			IProject project = ProjectUtilities.getProject(projectName);
			if (project != null) {
				IRuntime runtime = ServerCore.getProjectProperties(project).getRuntimeTarget();
				if (runtime != null) {
					IRuntimeType type = runtime.getRuntimeType();
					String typeId = type.getId();
					if (typeId.startsWith("org.eclipse.jst.server.tomcat")) {
						String msg = EARCreationResourceHandler.getString(EARCreationResourceHandler.SERVER_TARGET_NOT_SUPPORT_EAR);
						return WTPCommonPlugin.createErrorStatus(msg);
					}
				}
			}
		}
		return super.doValidateProperty(propertyName);
	}

	public void propertyChanged(WTPOperationDataModelEvent event) {
		super.propertyChanged(event);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EECreationDataModel#getModuleID()
	 */
	protected String getModuleID() {
		return IModuleConstants.JST_EAR_MODULE;
	}
}