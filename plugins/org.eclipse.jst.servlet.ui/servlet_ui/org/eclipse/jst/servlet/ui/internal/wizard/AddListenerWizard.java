/*******************************************************************************
 * Copyright (c) 2007, 2019 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.ADD_LISTENER_WIZARD_PAGE_TITLE;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.ADD_LISTENER_WIZARD_WINDOW_TITLE;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.operations.NewListenerClassDataModelProvider;
import org.eclipse.jst.servlet.ui.IWebUIContextIds;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

/**
 * New application lifecycle listener wizard
 */
public class AddListenerWizard extends NewWebArtifactWizard {
	
	public AddListenerWizard() {
	    this(null);
	}
	
	public AddListenerWizard(IDataModel model) {
		super(model);
	}

	@Override
	protected String getTitle() {
		return ADD_LISTENER_WIZARD_WINDOW_TITLE;
	}

	@Override
	protected ImageDescriptor getImage() {
		return getImageFromJ2EEPlugin("newlistener_wiz"); //$NON-NLS-1$
	}
	
	@Override
	public void doAddPages() {
		NewListenerClassWizardPage page1 = new NewListenerClassWizardPage(
				getDataModel(), 
				PAGE_ONE,
				NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC,
				ADD_LISTENER_WIZARD_PAGE_TITLE, J2EEProjectUtilities.DYNAMIC_WEB);
		page1.setInfopopID(IWebUIContextIds.WEBEDITOR_LISTENER_PAGE_ADD_LISTENER_WIZARD_1);
		addPage(page1);
		AddListenerWizardPage page2 = new AddListenerWizardPage(getDataModel(), PAGE_TWO);
		page2.setInfopopID(IWebUIContextIds.WEBEDITOR_LISTENER_PAGE_ADD_LISTENER_WIZARD_2);
		addPage(page2);
		NewListenerClassOptionsWizardPage page3 = new NewListenerClassOptionsWizardPage(
				getDataModel(), 
				PAGE_THREE,
				NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC,
				ADD_LISTENER_WIZARD_PAGE_TITLE);
		page3.setInfopopID(IWebUIContextIds.WEBEDITOR_LISTENER_PAGE_ADD_LISTENER_WIZARD_3);
		addPage(page3);
	}
	
	@Override
	protected void postPerformFinish() throws InvocationTargetException {
		// open new listener class in java editor
		openJavaClass();
	}

	@Override
	protected IDataModelProvider getDefaultProvider() {
		return new NewListenerClassDataModelProvider();
	}
	
}
