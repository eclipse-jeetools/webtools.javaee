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
package org.eclipse.jst.j2ee.internal.jca.operations;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPPropertyDescriptor;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

public class ConnectorComponentCreationDataModel extends J2EEComponentCreationDataModel {

    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationDataModel#getDefaultJ2EEModuleVersion()
     */
    protected Integer getDefaultJ2EEModuleVersion() {
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

	protected WTPPropertyDescriptor[] getValidJ2EEModuleVersionDescriptors() {
		int highestJ2EEPref = J2EEPlugin.getDefault().getJ2EEPreferences().getHighestJ2EEVersionID();
		WTPPropertyDescriptor[] descriptors = null;
		switch (highestJ2EEPref) {
			case J2EEVersionConstants.J2EE_1_3_ID :
				descriptors = new WTPPropertyDescriptor[1];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.JCA_1_0_ID), J2EEVersionConstants.VERSION_1_0_TEXT);
				break;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				descriptors = new WTPPropertyDescriptor[2];
				descriptors[0] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.JCA_1_0_ID), J2EEVersionConstants.VERSION_1_0_TEXT);
				descriptors[1] = new WTPPropertyDescriptor(new Integer(J2EEVersionConstants.JCA_1_5_ID), J2EEVersionConstants.VERSION_1_5_TEXT);
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
	
	protected EClass getModuleType() {
		return CommonarchiveFactoryImpl.getPackage().getRARFile();
	}

	protected String getModuleExtension() {
		return ".rar"; //$NON-NLS-1$
	}

	protected WTPPropertyDescriptor doGetPropertyDescriptor(String propertyName) {
		if (propertyName.equals(J2EE_MODULE_VERSION)) {
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
			return new WTPPropertyDescriptor(propertyValue, description);
		}
		return super.doGetPropertyDescriptor(propertyName);
	}


    public WTPOperation getDefaultOperation() {
        return new ConnectorComponentCreationOperation(this);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EECreationDataModel#getModuleID()
     */
    protected String getModuleID() {
        return IModuleConstants.JST_CONNECTOR_MODULE;
    }
}
