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
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBProjectCreationOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleProjectCreationWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EJBProjectWizard extends J2EEModuleProjectCreationWizard {
	private static final String CLIENT_PG = "client"; //$NON-NLS-1$
	protected EJBClientCreationWizardPage clientPage;

	public EJBProjectWizard(EJBProjectCreationDataModel dataModel) {
		super(dataModel);
	}

	public EJBProjectWizard() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		EJBProjectCreationDataModel aModel = new EJBProjectCreationDataModel();
		aModel.setBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR, true);
		return aModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#createOperation()
	 */
	protected WTPOperation createBaseOperation() {
		return new EJBProjectCreationOperation(getEJBProjectCreationDataModel());
	}

	/**
	 *  
	 */
	private EJBProjectCreationDataModel getEJBProjectCreationDataModel() {
		return (EJBProjectCreationDataModel) model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle(EJBUIMessages.getResourceString(EJBUIMessages.EJB_PROJECT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EJB_PROJECT_WIZARD_BANNER));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage nextPage = super.getNextPage(page);
		if (nextPage != null && nextPage.getName().equals(CLIENT_PG) && !((EJBProjectCreationDataModel) model).getBooleanProperty(EJBProjectCreationDataModel.CREATE_CLIENT))
			return super.getNextPage(nextPage);
		return nextPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#getPreviousPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	public IWizardPage getPreviousPage(IWizardPage page) {
		IWizardPage previousPage = super.getPreviousPage(page);
		if (previousPage != null && previousPage.getName().equals(CLIENT_PG) && !((EJBProjectCreationDataModel) model).getBooleanProperty(EJBProjectCreationDataModel.CREATE_CLIENT)) {
			IWizardPage temp = getPage(MAIN_PG);
			if (temp != null)
				return temp;
		}
		return previousPage;
	}

	public void doAddPages() {
		addPage(new EJBProjectCreationPage(getEJBProjectCreationDataModel(), MAIN_PG));
		clientPage = new EJBClientCreationWizardPage(getEJBProjectCreationDataModel().getNestEJBClientProjectDM(), CLIENT_PG);
		addPage(clientPage);
		super.doAddPages();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#canFinish()
	 */
	public boolean canFinish() {
		if (!getEJBProjectCreationDataModel().getBooleanProperty(EJBProjectCreationDataModel.CREATE_CLIENT)) {
			clientPage.setPageComplete(true);
		}
		return super.canFinish();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.extensions.ExtendableWizard#getWizardID()
	 */
	public String getWizardID() {
		return "org.eclipse.jst.j2ee.internal.EJBProjectWizard"; //$NON-NLS-1$
	}

	public String getPluginId() {
		return "org.eclipse.jst.j2ee.ejb.ui"; //$NON-NLS-1$
	}

	public String getLocalId() {
		return "org.eclipse.jst.j2ee.internal.ejb.ui.util.ejbProjectWizard"; //$NON-NLS-1$
	}
}