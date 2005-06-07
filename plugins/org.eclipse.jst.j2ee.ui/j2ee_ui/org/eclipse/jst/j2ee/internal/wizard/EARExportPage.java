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
 * Created on Dec 3, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class EARExportPage extends J2EEExportPage {
	/**
	 * @param model
	 * @param pageName
	 */
	public EARExportPage(IDataModel model, String pageName, IStructuredSelection selection) {
		super(model, pageName, selection);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_EXPORT_MAIN_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_EXPORT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_EXPORT_WIZARD_BANNER));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEImportPage#getProjectImportLabel()
	 */
	protected String getProjectLabel() {
		return J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_PROJECT_FOR_MODULE_CREATION);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEImportPage#getFilterExpression()
	 */
	protected String[] getFilterExpression() {
		return new String[]{"*.ear"}; //$NON-NLS-1$
	}

	/**
	 * @return
	 */
	protected boolean shouldShowProjectFilesCheckbox() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEExportPage#isMetaTypeSupported(java.lang.Object)
	 */
	protected boolean isMetaTypeSupported(Object o) {
		return o instanceof Application;
	}

	protected String[] getValidationPropertyNames() {
		return new String[]{IJ2EEComponentExportDataModelProperties.COMPONENT_NAME, IJ2EEComponentExportDataModelProperties.ARCHIVE_DESTINATION, IJ2EEComponentExportDataModelProperties.OVERWRITE_EXISTING};
	}

	protected String getInfopopID() {
		return IJ2EEUIContextIds.EXPORT_EAR_WIZARD_P1;
	}

    protected String getCompnentID() {
        return "JST_EAR";  //$NON-NLS-1$
    }
}