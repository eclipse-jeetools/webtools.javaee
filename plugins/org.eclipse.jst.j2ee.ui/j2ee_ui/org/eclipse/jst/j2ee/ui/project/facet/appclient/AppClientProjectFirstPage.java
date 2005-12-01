/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ui.project.facet.appclient;

import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.wizard.J2EEComponentFacetCreationWizardPage;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AppClientProjectFirstPage extends J2EEComponentFacetCreationWizardPage {

	public AppClientProjectFirstPage(IDataModel dataModel, String pageName) {
		super(dataModel, pageName);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_PROJECT_MAIN_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_PROJECT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.APP_CLIENT_PROJECT_WIZARD_BANNER));
	}

	protected String getModuleFacetID() {
		return J2EEProjectUtilities.APPLICATION_CLIENT;
	}

}
