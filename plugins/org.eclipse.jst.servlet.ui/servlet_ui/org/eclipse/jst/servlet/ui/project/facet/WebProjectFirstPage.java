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

package org.eclipse.jst.servlet.ui.project.facet;

import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.wizard.J2EEComponentFacetCreationWizardPage;
import org.eclipse.jst.servlet.ui.IWebUIContextIds;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class WebProjectFirstPage extends J2EEComponentFacetCreationWizardPage {

	public WebProjectFirstPage(IDataModel model, String pageName) {
		super(model, pageName);
		setTitle(WEBUIMessages.WEB_PROJECT_MAIN_PG_TITLE);
		setDescription(WEBUIMessages.WEB_PROJECT_MAIN_PG_DESC);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.WEB_PROJECT_WIZARD_BANNER));
		setInfopopID(IWebUIContextIds.NEW_DYNAMIC_WEB_PROJECT_PAGE1);
	}

	protected String getModuleFacetID() {
		return J2EEProjectUtilities.DYNAMIC_WEB;
	}

}