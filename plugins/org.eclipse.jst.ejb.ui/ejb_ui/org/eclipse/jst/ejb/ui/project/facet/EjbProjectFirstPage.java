/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.project.facet;

import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.wizard.J2EEComponentFacetCreationWizardPage;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EjbProjectFirstPage extends J2EEComponentFacetCreationWizardPage {

	protected String getModuleFacetID() {
		return J2EEProjectUtilities.EJB;
	}

	public EjbProjectFirstPage(IDataModel model, String pageName) {
		super(model, pageName);
		setTitle(EJBUIMessages.EJB_PROJECT_MAIN_PG_TITLE);
		setDescription(EJBUIMessages.EJB_PROJECT_MAIN_PG_DESC);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EJB_PROJECT_WIZARD_BANNER));
		setInfopopID(IJ2EEUIContextIds.NEW_EJB_WIZARD_P1);
	}

}
