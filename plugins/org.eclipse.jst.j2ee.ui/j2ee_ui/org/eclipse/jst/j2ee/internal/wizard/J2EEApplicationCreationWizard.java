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
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jst.j2ee.application.operations.J2EEApplicationCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEApplicationCreationOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/** 
 * <p>
 * Provides a user interface for the creation of J2EE Enterprise Application 
 * Projects.
 * </p> 
 */
public class J2EEApplicationCreationWizard extends J2EEArtifactCreationWizard {
	private static final String MODULES_PG = "modules"; //$NON-NLS-1$
	private static final String MAIN_PG = "main"; //$NON-NLS-1$

	/**
	 *  {@inheritDoc}
	 */
	public J2EEApplicationCreationWizard() {
		super();
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @param model used to collect information and interface with the WTP Operation
	 */
	public J2EEApplicationCreationWizard(J2EEApplicationCreationDataModel model) {
		super(model);
	}
 
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	protected void doAddPages() {
		addPage(new ApplicationProjectCreationPage(getApplicationModel(), MAIN_PG));
		if (!model.getBooleanProperty(J2EEApplicationCreationDataModel.UI_SHOW_FIRST_PAGE_ONLY)) {
			addPage(new ApplicationModuleProjectsPage(getApplicationModel(), MODULES_PG));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Creates a new {@see EARProjectCreationDataModel}
	 * </p>
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		return new J2EEApplicationCreationDataModel();
	}

	/**
	 * @inheritDoc
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#createOperation()
	 */
	protected WTPOperation createBaseOperation() {
		return new J2EEApplicationCreationOperation(getApplicationModel());
	}

	/**
	 * @return
	 */
	private J2EEApplicationCreationDataModel getApplicationModel() {
		return (J2EEApplicationCreationDataModel) model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	protected void doInit() { 
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_PROJECT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_WIZ_BANNER));
		setHelpAvailable(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.extensions.ExtendableWizard#getWizardID()
	 */
	public String getWizardID() {
		return "org.eclipse.jst.j2ee.J2EEApplicationCreationWizard"; //$NON-NLS-1$
	} 
}