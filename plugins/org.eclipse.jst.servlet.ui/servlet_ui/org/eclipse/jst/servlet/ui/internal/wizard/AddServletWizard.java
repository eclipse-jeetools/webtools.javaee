/*******************************************************************************
 * Copyright (c) 2003, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 * Kaloyan Raev, kaloyan.raev@sap.com
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.operations.NewServletClassDataModelProvider;
import org.eclipse.jst.servlet.ui.IWebUIContextIds;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

/**
 * New servlet wizard
 */
public class AddServletWizard extends NewWebArtifactWizard {
	
	public AddServletWizard() {
	    this(null);
	}
	
	public AddServletWizard(IDataModel model) {
		super(model);
	}

	@Override
	protected String getTitle() {
		return IWebWizardConstants.ADD_SERVLET_WIZARD_WINDOW_TITLE;
	}

	@Override
	protected ImageDescriptor getImage() {
		return J2EEUIPlugin.getDefault().getImageDescriptor("newservlet_wiz"); //$NON-NLS-1$
	}
	
	@Override
	public void doAddPages() {
		NewServletClassWizardPage page1 = new NewServletClassWizardPage(
				getDataModel(), 
				PAGE_ONE,
				IWebWizardConstants.NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC,
				IWebWizardConstants.ADD_SERVLET_WIZARD_PAGE_TITLE, J2EEProjectUtilities.DYNAMIC_WEB);
		page1.setInfopopID(IWebUIContextIds.WEBEDITOR_SERVLET_PAGE_ADD_SERVLET_WIZARD_1);
		addPage(page1);
		AddServletWizardPage page2 = new AddServletWizardPage(getDataModel(), PAGE_TWO);
		page2.setInfopopID(IWebUIContextIds.WEBEDITOR_SERVLET_PAGE_ADD_SERVLET_WIZARD_2);
		addPage(page2);
		NewServletClassOptionsWizardPage page3 = new NewServletClassOptionsWizardPage(
				getDataModel(), 
				PAGE_THREE,
				IWebWizardConstants.NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC,
				IWebWizardConstants.ADD_SERVLET_WIZARD_PAGE_TITLE);
		page3.setInfopopID(IWebUIContextIds.WEBEDITOR_SERVLET_PAGE_ADD_SERVLET_WIZARD_3);
		addPage(page3);
	}
	
	@Override
	protected void postPerformFinish() throws InvocationTargetException {
		boolean isServlet = getDataModel().getBooleanProperty(INewServletClassDataModelProperties.IS_SERVLET_TYPE);
		if (isServlet) {
			// open new servlet class in java editor
			openJavaClass();
		} else {
			// open new jsp file in jsp editor
			openWebFile();
		}
	}

	@Override
	protected IDataModelProvider getDefaultProvider() {
		return new NewServletClassDataModelProvider();
	}
	
}
