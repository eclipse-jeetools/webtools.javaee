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
 * Created on Oct 31, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.application.operations.EARProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.EARProjectCreationOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModel;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class ApplicationProjectWizard extends J2EEProjectCreationWizard {
	private static final String MODULES_PG = "modules"; //$NON-NLS-1$
	private static final String MAIN_PG = "main"; //$NON-NLS-1$

	/**
	 * @param model
	 */
	public ApplicationProjectWizard(EARProjectCreationDataModel model) {
		super(model);
	}

	/**
	 *  
	 */
	public ApplicationProjectWizard() {
		super();
	}

	protected void preFillSelectedEARProject() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	protected void doAddPages() {
		addPage(new ApplicationProjectCreationPage(getApplicationModel(), MAIN_PG));
		if (!model.getBooleanProperty(EARProjectCreationDataModel.UI_SHOW_FIRST_PAGE_ONLY)) {
			addPage(new ApplicationModuleProjectsPage(getApplicationModel(), MODULES_PG));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		return new EARProjectCreationDataModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizard#createOperation()
	 */
	protected WTPOperation createBaseOperation() {
		return new EARProjectCreationOperation(getApplicationModel());
	}

	/**
	 * @return
	 */
	private EARProjectCreationDataModel getApplicationModel() {
		return (EARProjectCreationDataModel) model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_PROJECT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_WIZ_BANNER));
		setHelpAvailable(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.extensions.ExtendableWizard#getWizardID()
	 */
	public String getWizardID() {
		return "org.eclipse.jst.j2ee.internal.ApplicationProjectWizard"; //$NON-NLS-1$
	}

	public String getLocalId() {
		return "org.eclipse.jst.j2ee.internal.earProjectWizard"; //$NON-NLS-1$
	}
}