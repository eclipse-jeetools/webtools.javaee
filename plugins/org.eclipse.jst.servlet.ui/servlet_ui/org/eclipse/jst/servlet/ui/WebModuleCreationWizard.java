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
package org.eclipse.jst.servlet.ui;

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationOperation;
import org.eclipse.jst.j2ee.ui.J2EEModuleCreationWizard;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.jst.servlet.ui.internal.wizard.WEBProjectCreationPage;
import org.eclipse.ui.INewWizard;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;

/**
 * <p>
 * Wizard used to create J2EE Web module structures in Eclipse Projects.
 * </p>
 * <p>
 * This wizard is not exposed as API to be extended.
 * </p>
 */
public class WebModuleCreationWizard extends J2EEModuleCreationWizard implements IExecutableExtension, INewWizard {
	
	/**
	 * <p>
	 * The Wizard ID of the ConnectorModuleCreationWizard. Used for internal purposes and activities management.
	 * </p>
	 */
	public static final String WIZARD_ID =  WebModuleCreationWizard.class.getName();
	 
	
	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, 
	 * no model instance, and no operation instance. The model and 
	 * operation will be created as needed.
	 * </p>
	 */
	public WebModuleCreationWizard() {
		super();
	}

	/**
	 * <p>
	 * The model is used to prepopulate the wizard controls
	 * and interface with the operation.
	 * </p>
	 * @param model The model parameter is used to pre-populate wizard controls and interface with the operation
	 */
	public WebModuleCreationWizard(WebModuleCreationDataModel model) {
		super(model);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Overridden to return a {@link WebProjectCreationDataModel} and defaults
	 * the value of {@link J2EEModuleCreationDataModel#ADD_TO_EAR} to <b>true</b>
	 * </p>
	 * 
	 * @return Returns the specific operation data model for the creation of J2EE Web modules
	 */
	protected WTPOperationDataModel createDefaultModel() {
		WebModuleCreationDataModel aModel = new WebModuleCreationDataModel();
		aModel.setBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR, true);
		return aModel;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Overridden to return an {@link WebProjectCreationOperation}. 
	 * </p>
	 * 
	 * @return Returns the specific operation for the creation of J2EE Web modules
	 */
	protected WTPOperation createBaseOperation() {
		return new WebModuleCreationOperation(getSpecificDataModel());
	}

	/** 
	 * {@inheritDoc}   
	 * 
	 * <p>
	 * Sets up the dialog window title and default page image. 
	 * </p> 
	 * 
	 * @see J2EEArtifactCreationWizard#doInit()
	 */
	protected void doInit() {
		setWindowTitle(WEBUIMessages.getResourceString(WEBUIMessages.WEB_PROJECT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.WEB_PROJECT_WIZARD_BANNER));
	} 
	 
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Adds a {@link JCAProjectCreationPage} as the {@link J2EEModuleCreationWizard#MAIN_PG}.
	 * </p>
	 */
	protected void doAddPages() {
		WEBProjectCreationPage page = new WEBProjectCreationPage(getSpecificDataModel(), MAIN_PG);
		page.setInfopopID("org.eclipse.jst.j2ee.ui.webw1000"); //$NON-NLS-1$
		addPage(page);
		super.doAddPages();
	} 
	
	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.extensions.ExtendableWizard#getWizardID()
	 */
	public String getWizardID() {
		return WIZARD_ID;
	} 
	
	private WebModuleCreationDataModel getSpecificDataModel() {
		return (WebModuleCreationDataModel) getModel();
	}

}