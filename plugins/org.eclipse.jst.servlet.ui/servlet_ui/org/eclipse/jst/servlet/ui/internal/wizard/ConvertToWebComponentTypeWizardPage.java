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
package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class ConvertToWebComponentTypeWizardPage extends WebComponentCreationWizardPage {

	/**
	 * @param model
	 * @param pageName
	 */
	protected ConvertToWebComponentTypeWizardPage(IDataModel model, String pageName) {
		// TODO use flexible project
		//super(model, pageName);
		super(model, pageName);
		setDescription(WEBUIMessages.getResourceString(WEBUIMessages.WEB_CONVERT_MAIN_PG_DESC)); //$NON-NLS-1$
		setTitle(WEBUIMessages.getResourceString(WEBUIMessages.WEB_CONVERT_MAIN_PG_TITLE)); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.j2ee.ui.wizard.J2EEProjectCreationPage#createProjectNameGroup(org.eclipse.swt.widgets.Composite)
	 */
	protected void createProjectNameGroup(Composite parent) {
		//super.createProjectNameGroup(parent);
		// projectNameGroup.projectNameField.setEnabled(false);
	}
}