/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
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
import org.eclipse.jst.j2ee.ejb.internal.operations.NewMessageDrivenBeanClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

public class AddMessageDrivenBeanWizard extends NewEnterpriseBeanWizard {


	private static final String PAGE_ONE = "pageOne"; //$NON-NLS-1$
	private static final String PAGE_TWO = "pageTwo"; //$NON-NLS-1$
	
	private AddMessageDrivenBeanWizardPage page2;
	private NewMessageDrivenBeanClassWizardPage page1;

	public AddMessageDrivenBeanWizard(IDataModel model) {
		super(model);
		setWindowTitle(IEjbWizardConstants.ADD_MESSAGE_DRIVEN_BEANS_WIZARD_PAGE_TITLE);
		setDefaultPageImageDescriptor(getWizBan());
	}

	private ImageDescriptor getWizBan() {
		URL url = (URL) J2EEPlugin.getDefault().getImage("message_bean_wiz"); //$NON-NLS-1$
		return ImageDescriptor.createFromURL(url);
	}

	public AddMessageDrivenBeanWizard() {
		this(null);
	}

	@Override
	protected void doAddPages() {
		page1 = new NewMessageDrivenBeanClassWizardPage(
				getDataModel(),
				PAGE_ONE,
				IEjbWizardConstants.NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC,
				IEjbWizardConstants.ADD_MESSAGE_DRIVEN_BEANS_WIZARD_PAGE_TITLE, 
				J2EEProjectUtilities.EJB);
		page1.setInfopopID(IEJBUIContextIds.EJB_MESSAGE_BEAN_WIZARD_ADD_MESSAGE_BEAN_PAGE_1);
		addPage(page1);
		page2 = new AddMessageDrivenBeanWizardPage(getDataModel(), PAGE_TWO);
		page2.setInfopopID(IEJBUIContextIds.EJB_MESSAGE_BEAN_WIZARD_ADD_MESSAGE_BEAN_PAGE_2);
		addPage(page2);
	}

	@Override
	protected IDataModelProvider getDefaultProvider() {
		return new NewMessageDrivenBeanClassDataModelProvider();
	}

}
