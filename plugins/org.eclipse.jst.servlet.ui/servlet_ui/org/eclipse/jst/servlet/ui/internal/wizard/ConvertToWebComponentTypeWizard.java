/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationOperation;
import org.eclipse.jst.j2ee.internal.web.operations.ConvertWebProjectDataModel;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/*
 * Licensed Material - Property of IBM (C) Copyright IBM Corp. 2000, 2002 - All
 * Rights Reserved. US Government Users Restricted Rights - Use, duplication or
 * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */

public class ConvertToWebComponentTypeWizard extends WebComponentCreationWizard {
	private static final String ConvertToWebModuleTypeWizard = "org.eclipse.jst.servlet.ui.internal.wizard.convertWebProjectTypeWizard"; //$NON-NLS-1$

	public ConvertToWebComponentTypeWizard(ConvertWebProjectDataModel dataModel) {
		// TODO use flexible project
		//super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.util.ui.wizard.WTPWizard#createDefaultModel()
	 */
	private static final String PAGE_ONE = "page1"; //$NON-NLS-1$

	protected WTPOperationDataModel createDefaultModel() {
		// TODO Auto-generated method stub
		return new ConvertWebProjectDataModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.util.ui.wizard.WTPWizard#createOperation()
	 */
	protected WTPOperation createBaseOperation() {
		// TODO Auto-generated method stub
		return new WebModuleCreationOperation((WebModuleCreationDataModel) model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void doAddPages() {
		//TODO use flexible project
		//addPage(new ConvertToWebModuleTypeWizardPage((WebModuleCreationDataModel) model, PAGE_ONE));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.web.ui.wizard.WEBProjectWizard#getWizardID()
	 */
	public String getWizardID() {
		return ConvertToWebModuleTypeWizard;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.web.ui.wizard.WEBProjectWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void doInit(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle(WEBUIMessages.getResourceString(WEBUIMessages.WEB_CONVERT_MAIN_PG_TITLE)); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.util.ui.wizard.WTPWizard#prePerformFinish()
	 */
	protected boolean prePerformFinish() {
		IProjectDescription desc;
		try {
			desc = ((WebModuleCreationDataModel) model).getProjectDataModel().getProject().getDescription();
			desc.setBuildSpec(new ICommand[0]);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return super.prePerformFinish();
	}
}