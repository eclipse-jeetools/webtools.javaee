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
 * Created on Mar 16, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;


/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public abstract class J2EEModuleProjectCreationWizard extends J2EEProjectCreationWizard {
	protected static final String MAIN_PG = "main"; //$NON-NLS-1$
	protected static final String MODULE_PG = "module"; //$NON-NLS-1$

	/**
	 * @param model
	 */
	public J2EEModuleProjectCreationWizard(J2EEModuleCreationDataModel model) {
		super(model);
	}

	/**
	 *  
	 */
	public J2EEModuleProjectCreationWizard() {
		super();
	}

	protected void addModulesPageIfNecessary() {
		if (model.getBooleanProperty(J2EEModuleCreationDataModel.UI_SHOW_EAR_SECTION)) {
			addPage(new J2EEModulesDependencyPage((J2EEModuleCreationDataModel) model, MODULE_PG));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	protected void doAddPages() {
		addModulesPageIfNecessary();
	}

	public IWizardPage getPreviousPage(IWizardPage page) {
		IWizardPage previous = super.getPreviousPage(page);
		if (previous != null && previous.getName().equals(MODULE_PG)) {
			if (!shouldShowModulesPage()) {
				previous = super.getPreviousPage(previous);
			}
		}
		return previous;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage next = super.getNextPage(page);
		if (next != null && next.getName().equals(MODULE_PG)) {
			if (!shouldShowModulesPage()) {
				next = super.getNextPage(next);
			}
		}
		return next;
	}

	/**
	 * @return
	 */
	private boolean shouldShowModulesPage() {
		return model.getBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR) && shouldShowModulesPageForEAR();
	}

	/**
	 * @return
	 */
	private boolean shouldShowModulesPageForEAR() {
		IProject earProject = model.getProjectHandle(J2EEModuleCreationDataModel.EAR_PROJECT_NAME);
		if (null != earProject && earProject.exists() && earProject.isAccessible())
			return !((J2EEModuleCreationDataModel) model).getClassPathSelection().getClasspathElements().isEmpty();
		return false;
	}
}