/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ui.project.facet;

import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.wizard.J2EEComponentFacetCreationWizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class UtilityProjectFirstPage extends J2EEComponentFacetCreationWizardPage {

	public UtilityProjectFirstPage(IDataModel dataModel, String pageName) {
		super(dataModel, pageName);
		setTitle(Resources.pageTitle);
		setDescription(Resources.pageDescription);
		setInfopopID(IJ2EEUIContextIds.NEW_UTILITY_WIZARD_P1);
	}

	private static final class Resources extends NLS {
		public static String pageTitle;
		public static String pageDescription;

		static {
			initializeMessages(UtilityProjectFirstPage.class.getName(), Resources.class);
		}
	}

	@Override
	protected String getModuleFacetID() {
		return J2EEProjectUtilities.UTILITY;
	}
	
}
