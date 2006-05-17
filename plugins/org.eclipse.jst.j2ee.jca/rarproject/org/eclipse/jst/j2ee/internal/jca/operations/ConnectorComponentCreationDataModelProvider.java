/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.jca.operations;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.CreationConstants;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.jca.project.facet.ConnectorFacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.internal.DoNotUseMeThisWillBeDeletedPost15;

/**
 * This has been slated for removal post WTP 1.5. Do not use this class/interface
 * 
 * @deprecated
 * @see ConnectorFacetProjectCreationDataModelProvider
 */
public class ConnectorComponentCreationDataModelProvider extends J2EEComponentCreationDataModelProvider implements IConnectorComponentCreationDataModelProperties, DoNotUseMeThisWillBeDeletedPost15 {

	public ConnectorComponentCreationDataModelProvider() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationDataModel#getDefaultJ2EEModuleVersion()
	 */
	protected Integer getDefaultComponentVersion() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		switch (highestJ2EEPref) {
			case (J2EEVersionConstants.J2EE_1_4_ID) :
				return new Integer(J2EEVersionConstants.JCA_1_5_ID);
			case (J2EEVersionConstants.J2EE_1_3_ID) :
				return new Integer(J2EEVersionConstants.JCA_1_0_ID);
			case (J2EEVersionConstants.J2EE_1_2_ID) :
				return null;
			default :
				return new Integer(J2EEVersionConstants.JCA_1_5_ID);
		}
	}

	protected DataModelPropertyDescriptor[] getValidComponentVersionDescriptors() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		DataModelPropertyDescriptor[] descriptors = null;
		switch (highestJ2EEPref) {
			case J2EEVersionConstants.J2EE_1_3_ID :
				descriptors = new DataModelPropertyDescriptor[1];
				descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.JCA_1_0_ID), J2EEVersionConstants.VERSION_1_0_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				descriptors = new DataModelPropertyDescriptor[2];
				descriptors[0] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.JCA_1_0_ID), J2EEVersionConstants.VERSION_1_0_TEXT);
				descriptors[1] = new DataModelPropertyDescriptor(new Integer(J2EEVersionConstants.JCA_1_5_ID), J2EEVersionConstants.VERSION_1_5_TEXT);
				break;
		}
		return descriptors;
	}

	protected int convertModuleVersionToJ2EEVersion(int moduleVersion) {
		switch (moduleVersion) {
			case J2EEVersionConstants.JCA_1_0_ID :
				return J2EEVersionConstants.J2EE_1_3_ID;
			case J2EEVersionConstants.JCA_1_5_ID :
				return J2EEVersionConstants.J2EE_1_4_ID;
		}
		return 0;
	}

	protected Integer convertJ2EEVersionToModuleVersion(Integer j2eeVersion) {
		switch (j2eeVersion.intValue()) {
			case J2EEVersionConstants.J2EE_1_3_ID :
				return new Integer(J2EEVersionConstants.JCA_1_0_ID);
			case J2EEVersionConstants.J2EE_1_4_ID :
				return new Integer(J2EEVersionConstants.JCA_1_5_ID);
		}
		return super.convertJ2EEVersionToModuleVersion(j2eeVersion);
	}

	protected EClass getComponentType() {
		return CommonarchivePackage.eINSTANCE.getRARFile();
	}

	protected String getComponentExtension() {
		return ".rar"; //$NON-NLS-1$
	}

	public DataModelPropertyDescriptor getPropertyDescriptor(String propertyName) {
		if (propertyName.equals(COMPONENT_VERSION)) {
			Integer propertyValue = (Integer) getProperty(propertyName);
			String description = null;
			switch (propertyValue.intValue()) {
				case J2EEVersionConstants.JCA_1_0_ID :
					description = J2EEVersionConstants.VERSION_1_0_TEXT;
					break;
				case J2EEVersionConstants.JCA_1_5_ID :
				default :
					description = J2EEVersionConstants.VERSION_1_5_TEXT;
					break;
			}
			return new DataModelPropertyDescriptor(propertyValue, description);
		}
		return super.getPropertyDescriptor(propertyName);
	}


	public IDataModelOperation getDefaultOperation() {
		//return new ConnectorComponentCreationOperation(model);
		return new ConnectorComponentCreationFacetOperation(model);
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(MANIFEST_FOLDER)) {
			return IPath.SEPARATOR + CreationConstants.DEFAULT_CONNECTOR_SOURCE_FOLDER  + IPath.SEPARATOR + J2EEConstants.META_INF;
		}
		if (propertyName.equals(ADD_TO_EAR)) {
			return Boolean.TRUE;
		}
		if (propertyName.equals(DD_FOLDER)) {
			return IPath.SEPARATOR + CreationConstants.DEFAULT_CONNECTOR_SOURCE_FOLDER + IPath.SEPARATOR + J2EEConstants.META_INF;
		}
		if (propertyName.equals(JAVASOURCE_FOLDER)) {
			return CreationConstants.DEFAULT_CONNECTOR_SOURCE_FOLDER;
		} else if (propertyName.equals(MODULE_URI)) {
			return getProject().getName()+IJ2EEModuleConstants.RAR_EXT;
		}

		return super.getDefaultProperty(propertyName);
	}

	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		return super.getValidPropertyDescriptors(propertyName);
	}

	public IStatus validate(String propertyName) {
		return super.validate(propertyName);
	}
	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean doSet = super.propertySet(propertyName, propertyValue);
		if (propertyName.equals(JAVASOURCE_FOLDER)){
			//unless MANIFEST folder is opened up, it is set as same as Java source folder
			setProperty(MANIFEST_FOLDER, getProperty(JAVASOURCE_FOLDER)+ "/" + J2EEConstants.META_INF); //$NON-NLS-1$
		}		
		return doSet;
	}	
	
	protected String getJ2EEProjectType() {
		return J2EEProjectUtilities.JCA;
	}
}
