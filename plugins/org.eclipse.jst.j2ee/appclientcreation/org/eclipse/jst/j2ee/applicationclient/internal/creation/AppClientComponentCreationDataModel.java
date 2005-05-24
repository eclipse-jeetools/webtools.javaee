/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.applicationclient.internal.creation;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPPropertyDescriptor;

//TODO delete
/**
 * @deprecated
 *
 */
public class AppClientComponentCreationDataModel extends J2EEComponentCreationDataModel {
	/**
	 * Boolean, default=true. If this is true and CREATE_DEFAULT_FILES is true, then a default main
	 * class will be generated during project creation.
	 */
	public static final String CREATE_DEFAULT_MAIN_CLASS = "FlexibleAppClientCreationDataModel.CREATE_DEFAULT_MAIN_CLASS"; //$NON-NLS-1$
	
	
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(CREATE_DEFAULT_MAIN_CLASS);
	}
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(CREATE_DEFAULT_MAIN_CLASS)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(ADD_TO_EAR)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(MANIFEST_FOLDER)) {
			return IPath.SEPARATOR + this.getModuleName()+IPath.SEPARATOR + "appClientModule"+IPath.SEPARATOR + J2EEConstants.META_INF;
		}
		if (propertyName.equals(DD_FOLDER)) {
			return IPath.SEPARATOR + this.getModuleName()+IPath.SEPARATOR + "appClientModule"+IPath.SEPARATOR + J2EEConstants.META_INF;
		}		
		if (propertyName.equals(J2EEComponentCreationDataModel.JAVASOURCE_FOLDER)) {
			return IPath.SEPARATOR + this.getModuleName()+IPath.SEPARATOR + "appClientModule";
		}		
		return super.getDefaultProperty(propertyName);
	}

    protected Integer getDefaultComponentVersion() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		return new Integer(highestJ2EEPref);
    }

    protected WTPPropertyDescriptor[] getValidComponentVersionDescriptors() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		WTPPropertyDescriptor[] descriptors = null;
		switch (highestJ2EEPref) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				descriptors = new WTPPropertyDescriptor[1];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), J2EEVersionConstants.VERSION_1_2_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_3_ID :
				descriptors = new WTPPropertyDescriptor[2];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), J2EEVersionConstants.VERSION_1_2_TEXT);
				descriptors[1] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_3_ID), J2EEVersionConstants.VERSION_1_3_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				descriptors = new WTPPropertyDescriptor[3];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_2_ID), J2EEVersionConstants.VERSION_1_2_TEXT);
				descriptors[1] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_3_ID), J2EEVersionConstants.VERSION_1_3_TEXT);
				descriptors[2] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.J2EE_1_4_ID), J2EEVersionConstants.VERSION_1_4_TEXT);
				break;
		}
		return descriptors;
    }

    protected int convertModuleVersionToJ2EEVersion(int moduleVersion) {
        return moduleVersion;
    }


    protected EClass getComponentType() {
        return CommonarchiveFactoryImpl.getPackage().getApplicationClientFile();
    }

    protected String getComponentExtension() {
		return ".jar"; //$NON-NLS-1$
    }


    public WTPOperation getDefaultOperation() {
        return new AppClientComponentCreationOperation(this);
    }
    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EECreationDataModel#getModuleID()
     */
    protected String getComponentID() {
        return IModuleConstants.JST_APPCLIENT_MODULE;
    }

}
