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
package org.eclipse.jst.j2ee.jca.ui.internal.wizard;

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.J2EEComponentCreationWizard;
import org.eclipse.jst.j2ee.jca.ui.internal.util.JCAUIMessages;
import org.eclipse.ui.INewWizard;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

/** 
 * <p>
 * Wizard used to create J2EE Connector module structures in Eclipse Projects.
 * </p>
 */
public final class ConnectorComponentCreationWizard extends J2EEComponentCreationWizard implements IExecutableExtension, INewWizard {

	/**
	 * <p>
	 * The Wizard ID of the ConnectorModuleCreationWizard. Used for internal purposes and activities management.
	 * </p>
	 */
	public static final String WIZARD_ID = ConnectorComponentCreationWizard.class.getName();
	
	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, 
	 * no model instance, and no operation instance. The model and 
	 * operation will be created as needed.
	 * </p>
	 */
	public ConnectorComponentCreationWizard() {
		super();
	}
	
	/**
	 * <p>
	 * The model is used to prepopulate the wizard controls
	 * and interface with the operation.
	 * </p>
	 * @param model The model parameter is used to pre-populate wizard controls and interface with the operation
	 */
	public ConnectorComponentCreationWizard(IDataModel model) {
		super(model);
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
		setWindowTitle(JCAUIMessages.getResourceString(JCAUIMessages.JCA_MODULE_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.JCA_PROJECT_WIZARD_BANNER));
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Adds a {@link ConnectorComponentCreationWizardPage} as the {@link J2EEComponentCreationWizard#MAIN_PG}.
	 * </p>
	 */
	public void doAddPages() {
		addPage(new ConnectorComponentCreationWizardPage(getDataModel(), MAIN_PG));
		super.doAddPages();
	}

    protected IDataModelProvider getDefaultProvider() {
        return new ConnectorComponentCreationDataModelProvider();
    }
}