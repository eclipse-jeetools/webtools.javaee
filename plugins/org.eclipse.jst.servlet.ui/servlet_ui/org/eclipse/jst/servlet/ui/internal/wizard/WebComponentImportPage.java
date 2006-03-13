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

import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleImportPage;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class WebComponentImportPage extends J2EEModuleImportPage {
	/**
	 * @param model
	 * @param pageName
	 */
	public WebComponentImportPage(IDataModel model, String pageName) {
		super(model, pageName);
		setTitle(WEBUIMessages.WEB_IMPORT_MAIN_PG_TITLE);
		setDescription(WEBUIMessages.WEB_IMPORT_MAIN_PG_DESC);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.WEB_IMPORT_WIZARD_BANNER));

	}

	protected String getFileImportLabel() {
		return WEBUIMessages.WEB_FILE_LBL;
	}

	protected String[] getFilterExpression() {
		return new String[]{"*.war"}; //$NON-NLS-1$
	}

	protected String getProjectImportLabel() {
		return WEBUIMessages.EJB_PROJECT_LBL;
	}

	protected void createAnnotationsStandaloneGroup(Composite composite) {
		// TODO: implement with new import wizards
		// new AnnotationsStandaloneGroup(composite, getWebDataModel(), false);
	}

	protected String getModuleFacetID(){
		return J2EEProjectUtilities.DYNAMIC_WEB;
	}	
}