/** Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.application.operations.AddComponentToEnterpriseApplicationDataModel;
import org.eclipse.jst.j2ee.application.operations.EARComponentCreationOperation;
import org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.frameworks.operations.WTPPropertyDescriptor;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.operation.ComponentCreationDataModel;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.ServerCore;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public class EARComponentCreationDataModel extends J2EEComponentCreationDataModel {
	
	/**
	 * Requred. This must be a list of WorkbenchComponent. 
	 */
	public static final String J2EE_COMPONENT_LIST = "EARComponentCreationDataModel.J2EE_COMPONENT_LIST"; //$NON-NLS-1$

	/**
	 * 
	 */
	public WTPOperation getDefaultOperation() {
		return new EARComponentCreationOperation(this);
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

	/**
	 * 
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(J2EE_COMPONENT_LIST);
	}
	
	/**
	 * @param workbenchComp
	 */
	protected void setEARComponentIfJ2EEModuleCreationOnly(WorkbenchComponent workbenchComp, Object propertyValue) {
	//Overwritting super class method to do nothing for EAR component creation only needed for all other j2ee modules
	}
	
	protected void initNestedModels() {
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
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(DD_FOLDER)) {
			return IPath.SEPARATOR + this.getModuleName() + IPath.SEPARATOR + "META_INF"; //$NON-NLS-1$
		} else if (propertyName.equals(UI_SHOW_EAR_SECTION)) {
			return Boolean.FALSE;
		}
		return super.getDefaultProperty(propertyName);
	}		

	/**
	 * 
	 */
	protected WTPPropertyDescriptor doGetPropertyDescriptor(String propertyName) {
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
			return new WTPPropertyDescriptor(propertyValue, description);
		}
		return super.doGetPropertyDescriptor(propertyName);
	}

	/**
	 * 
	 */
	protected WTPPropertyDescriptor[] getValidComponentVersionDescriptors() {
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
		String projectName = getStringProperty(ComponentCreationDataModel.PROJECT_NAME);
		if (projectName != null && projectName.length()!= 0) {
			IProject project = ProjectUtilities.getProject(projectName);
			if (project != null) {
				IRuntime runtime = ServerCore.getProjectProperties(project).getRuntimeTarget();
				if (runtime != null) {
					IRuntimeType type = runtime.getRuntimeType();
					String typeId = type.getId();
					if (typeId.startsWith("org.eclipse.jst.server.tomcat")) { //$NON-NLS-1$
						String msg = EARCreationResourceHandler.getString(EARCreationResourceHandler.SERVER_TARGET_NOT_SUPPORT_EAR);
						return WTPCommonPlugin.createErrorStatus(msg);
					}
				}
			}
		} else if(propertyName.equals(NESTED_MODEL_VALIDATION_HOOK))
            		return OK_STATUS;
		return super.doValidateProperty(propertyName);
	}

	public void propertyChanged(WTPOperationDataModelEvent event) {
		super.propertyChanged(event);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EECreationDataModel#getModuleID()
	 */
	protected String getComponentID() {
		return IModuleConstants.JST_EAR_MODULE;
	}
}