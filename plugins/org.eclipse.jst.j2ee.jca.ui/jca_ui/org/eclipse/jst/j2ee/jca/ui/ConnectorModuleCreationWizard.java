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
package org.eclipse.jst.j2ee.jca.ui;

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleCreationOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.jca.ui.internal.util.JCAUIMessages;
import org.eclipse.jst.j2ee.jca.ui.internal.wizard.JCAProjectCreationPage;
import org.eclipse.jst.j2ee.ui.J2EEModuleCreationWizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/** 
 * <p>
 * Wizard used to create J2EE Connector module structures in Eclipse Projects.
 * </p>
 */
public final class ConnectorModuleCreationWizard extends J2EEModuleCreationWizard implements IExecutableExtension, INewWizard {

	/**
	 * <p>
	 * The Wizard ID of the ConnectorModuleCreationWizard. Used for internal purposes and activities management.
	 * </p>
	 */
	public static final String WIZARD_ID = ConnectorModuleCreationWizard.class.getName();
	
	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, 
	 * no model instance, and no operation instance. The model and 
	 * operation will be created as needed.
	 * </p>
	 */
	public ConnectorModuleCreationWizard() {
		super();
	}
	
	/**
	 * <p>
	 * The model is used to prepopulate the wizard controls
	 * and interface with the operation.
	 * </p>
	 * @param model The model parameter is used to pre-populate wizard controls and interface with the operation
	 */
	public ConnectorModuleCreationWizard(ConnectorModuleCreationDataModel model) {
		super(model);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Overridden to return a {@link ConnectorProjectCreationDataModel} and defaults
	 * the value of {@see J2EEModuleCreationDataModel#ADD_TO_EAR} to <b>true</b>
	 * </p>
	 * 
	 * @return Returns the specific operation data model for the creation of J2EE Connector modules
	 */
	protected final WTPOperationDataModel createDefaultModel() {
		ConnectorModuleCreationDataModel aModel = new ConnectorModuleCreationDataModel();
		aModel.setBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR, true);
		return aModel;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Overridden to return a {@link ConnectorProjectCreationOperation}. 
	 * </p>
	 * 
	 * @return Returns the specific operation for the creation of J2EE Connector modules
	 */
	protected final WTPOperation createBaseOperation() {
		return new ConnectorModuleCreationOperation(getSpecificDataModel());
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
		setWindowTitle(JCAUIMessages.getResourceString(JCAUIMessages.JCA_PROJECT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.JCA_PROJECT_WIZARD_BANNER));
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Adds a {@link JCAProjectCreationPage} as the {@link J2EEModuleCreationWizard#MAIN_PG}.
	 * </p>
	 */
	public void doAddPages() {
		addPage(new JCAProjectCreationPage(getSpecificDataModel(), MAIN_PG));
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
 

	private ConnectorModuleCreationDataModel getSpecificDataModel() {
		return (ConnectorModuleCreationDataModel) getModel();
	}

}