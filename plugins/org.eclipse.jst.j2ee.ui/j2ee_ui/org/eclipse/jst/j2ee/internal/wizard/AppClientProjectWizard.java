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
 * Created on Nov 7, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientProjectCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientProjectCreationOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AppClientProjectWizard extends J2EEModuleProjectCreationWizard {
	public AppClientProjectWizard(AppClientProjectCreationDataModel dataModel) {
		super(dataModel);
	}

	public AppClientProjectWizard() {
		super();
	}

	protected WTPOperationDataModel createDefaultModel() {
		AppClientProjectCreationDataModel aModel = new AppClientProjectCreationDataModel();
		aModel.setBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR, true);
		return aModel;
	}

	protected WTPOperation createBaseOperation() {
		return new AppClientProjectCreationOperation(getAppClientProjectCreationDataModel());
	}

	private AppClientProjectCreationDataModel getAppClientProjectCreationDataModel() {
		return (AppClientProjectCreationDataModel) model;
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_PROJECT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.APP_CLIENT_PROJECT_WIZARD_BANNER));
	}

	public void doAddPages() {
		addPage(new AppClientProjectCreationPage(getAppClientProjectCreationDataModel(), MAIN_PG));
		super.doAddPages();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.extensions.ExtendableWizard#getWizardID()
	 */
	public String getWizardID() {
		return "org.eclipse.jst.j2ee.internal.internal.internal.AppClientProjectWizard"; //$NON-NLS-1$
	}

	public String getLocalId() {
		return "org.eclipse.jst.j2ee.internal.internal.internal.appclientProjectWizard"; //$NON-NLS-1$
	}

}