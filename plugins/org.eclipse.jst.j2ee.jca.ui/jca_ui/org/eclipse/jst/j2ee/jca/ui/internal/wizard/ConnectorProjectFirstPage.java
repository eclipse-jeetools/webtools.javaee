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
package org.eclipse.jst.j2ee.jca.ui.internal.wizard;

import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.wizard.J2EEComponentFacetCreationWizardPage;
import org.eclipse.jst.j2ee.jca.ui.internal.util.JCAUIMessages;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class ConnectorProjectFirstPage extends J2EEComponentFacetCreationWizardPage {

	public ConnectorProjectFirstPage(IDataModel dataModel, String pageName) {
		super(dataModel, pageName);
		setTitle(JCAUIMessages.JCA_MODULE_MAIN_PG_TITLE);
		setDescription(JCAUIMessages.JCA_MODULE_MAIN_PG_DESC);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.JCA_PROJECT_WIZARD_BANNER));
	}

	protected String getModuleFacetID() {
		return J2EEProjectUtilities.JCA;
	}

}
