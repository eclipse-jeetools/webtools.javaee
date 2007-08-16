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
/*
 * Created on Dec 3, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.client.ApplicationClient;
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
public class AppClientExportPage extends J2EEModuleExportPage {
	/**
	 * @param model
	 * @param pageName
	 */
	public AppClientExportPage(IDataModel model, String pageName, IStructuredSelection selection) {
		super(model, pageName, selection);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_EXPORT_MAIN_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_EXPORT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.APP_CLIENT_EXPORT_WIZARD_BANNER));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEImportPage#getProjectImportLabel()
	 */
	protected String getComponentLabel() {
		return J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_IMPORT_PROJECT_LABEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEImportPage#getFilterExpression()
	 */
	protected String[] getFilterExpression() {
		return new String[]{"*.jar"}; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEExportPage#isMetaTypeSupported(java.lang.Object)
	 */
	protected boolean isMetaTypeSupported(Object o) {
		return o instanceof ApplicationClient;
	}

	protected String getInfopopID() {
		return IJ2EEUIContextIds.EXPORT_APPCLIENT_WIZARD_P1;
	}
	
    protected String getCompnentID() {
        return "JST_APPCLIENT"; //$NON-NLS-1$
    }

}
