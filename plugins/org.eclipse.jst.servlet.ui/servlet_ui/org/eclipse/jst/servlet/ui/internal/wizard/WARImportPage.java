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
package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.jst.j2ee.application.operations.J2EEArtifactCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.wizard.AnnotationsStandaloneGroup;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleImportPage;
import org.eclipse.jst.servlet.ui.WebModuleCreationWizard;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.ui.WTPWizard;


/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class WARImportPage extends J2EEModuleImportPage {
	/**
	 * @param model
	 * @param pageName
	 */
	public WARImportPage(J2EEModuleImportDataModel model, String pageName) {
		super(model, pageName);
		setTitle(WEBUIMessages.getResourceString(WEBUIMessages.WEB_IMPORT_MAIN_PG_TITLE));
		setDescription(WEBUIMessages.getResourceString(WEBUIMessages.WEB_IMPORT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.WEB_IMPORT_WIZARD_BANNER));

	}

	protected String getFileImportLabel() {
		return WEBUIMessages.getResourceString(WEBUIMessages.WEB_FILE_LBL);
	}

	protected String[] getFilterExpression() {
		return new String[]{"*.war"}; //$NON-NLS-1$
	}

	protected J2EEArtifactCreationDataModel getNewProjectCreationDataModel() {
		return getWebDataModel().getJ2eeArtifactCreationDataModel();
	}

	protected WTPWizard getNewProjectWizard(J2EEArtifactCreationDataModel aModel) {
		return new WebModuleCreationWizard((WebModuleCreationDataModel) aModel);
	}

	private WebModuleImportDataModel getWebDataModel() {
		return (WebModuleImportDataModel) model;
	}

	protected String getProjectImportLabel() {
		return WEBUIMessages.getResourceString(WEBUIMessages.EJB_PROJECT_LBL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEModuleImportPage#createAnnotationsStandaloneGroup(org.eclipse.swt.widgets.Composite)
	 */
	protected void createAnnotationsStandaloneGroup(Composite composite) {
		new AnnotationsStandaloneGroup(composite, getWebDataModel(), false);
	}

}