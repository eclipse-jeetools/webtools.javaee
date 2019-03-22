/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleExportPage;
import org.eclipse.jst.servlet.ui.IWebUIContextIds;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class WebComponentExportPage extends J2EEModuleExportPage {
	/**
	 * @param model
	 * @param pageName
	 */
	public WebComponentExportPage(IDataModel model, String pageName, IStructuredSelection selection) {
		super(model, pageName, selection);
		setTitle(WEBUIMessages.WEB_EXPORT_MAIN_PG_TITLE);
		setDescription(WEBUIMessages.WEB_EXPORT_MAIN_PG_DESC);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.WEB_EXPORT_WIZARD_BANNER));
		setInfopopID(IWebUIContextIds.WAR_EXPORT_PAGE1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEImportPage#getProjectImportLabel()
	 */
	@Override
	protected String getComponentLabel() {
		return WEBUIMessages.WEB_PROJECT_LBL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEImportPage#getFilterExpression()
	 */
	@Override
	protected String[] getFilterExpression() {
		return new String[]{"*.war"}; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEExportPage#isMetaTypeSupported(java.lang.Object)
	 */
	@Override
	protected boolean isMetaTypeSupported(Object o) {
		return o instanceof org.eclipse.jst.j2ee.webapplication.WebApp;
	}

	@Override
	protected String getComponentID() {
		return "JST_WEB"; //$NON-NLS-1$
	}

}
