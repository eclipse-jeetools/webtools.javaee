/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.ejb.ui.context.ids.IEJBUIContextIds;
import org.eclipse.jst.j2ee.ejb.internal.operations.NewSessionBeanClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

public class AddSessionBeanWizard extends NewEnterpriseBeanWizard {


	private static final String PAGE_ONE = "pageOne"; //$NON-NLS-1$
	private static final String PAGE_TWO = "pageTwo"; //$NON-NLS-1$
	
	private AddSessionBeanWizardPage page2;
	private NewSessionBeanClassWizardPage page1;

	public AddSessionBeanWizard(IDataModel model) {
		super(model);
		setWindowTitle(IEjbWizardConstants.ADD_SESSION_BEAN_WIZARD_PAGE_TITLE);
		setDefaultPageImageDescriptor(getWizBan());
	}

	private ImageDescriptor getWizBan() {
		URL url = (URL) J2EEPlugin.getDefault().getImage("session_bean_wiz"); //$NON-NLS-1$
		return ImageDescriptor.createFromURL(url);
	}

	public AddSessionBeanWizard() {
		this(null);
	}

	@Override
	protected void doAddPages() {
		page1 = new NewSessionBeanClassWizardPage(
				getDataModel(),
				PAGE_ONE,
				IEjbWizardConstants.NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC,
				IEjbWizardConstants.ADD_SESSION_BEAN_WIZARD_PAGE_TITLE, 
				J2EEProjectUtilities.EJB);
		page1.setInfopopID(IEJBUIContextIds.EJB_SESSION_BEAN_WIZARD_ADD_SESSION_BEAN_PAGE_1);
		addPage(page1);
		page2 = new AddSessionBeanWizardPage(getDataModel(), PAGE_TWO);
		page2.setInfopopID(IEJBUIContextIds.EJB_SESSION_BEAN_WIZARD_ADD_SESSION_BEAN_PAGE_2);
		addPage(page2);
	}

	@Override
	protected IDataModelProvider getDefaultProvider() {
		return new NewSessionBeanClassDataModelProvider();
	}

}
