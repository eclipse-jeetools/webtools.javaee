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
package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.core.internal.registry.ConfigurationElement;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebProjectCreationOperation;
import org.eclipse.jst.j2ee.internal.wizard.J2EEArtifactCreationWizard;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleCreationWizard;
import org.eclipse.jst.servlet.ui.internal.plugin.ServletUIPlugin;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * <p>
 * Wizard used to create J2EE Web module structures in Eclipse Projects.
 * </p>
 */
public class WebModuleCreationWizard extends J2EEModuleCreationWizard implements IExecutableExtension, INewWizard {
	
	/**
	 * <p>
	 * The Wizard ID of the ConnectorModuleCreationWizard. Used for internal purposes and activities management.
	 * </p>
	 */
	public static final String WIZARD_ID =  "org.eclipse.jst.j2ee.WebModuleCreationWizard"; //$NON-NLS-1$
	
	private IConfigurationElement configData;
	
	/**
	 * {@inheritDoc}
	 */
	public WebModuleCreationWizard() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param model used to initialize the wizard and to interface with the operation
	 */
	public WebModuleCreationWizard(WebProjectCreationDataModel model) {
		super(model);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Overridden to return a {@see WebProjectCreationDataModel} and defaults
	 * the value of {@see J2EEModuleCreationDataModel#ADD_TO_EAR} to <b>true</b>
	 * </p>
	 * 
	 * @return Returns the specific operation data model for the creation of J2EE Web modules
	 */
	protected final WTPOperationDataModel createDefaultModel() {
		WebProjectCreationDataModel aModel = new WebProjectCreationDataModel();
		aModel.setBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR, true);
		return aModel;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Overridden to return an {@see WebProjectCreationOperation}. 
	 * </p>
	 * 
	 * @return Returns the specific operation for the creation of J2EE Web modules
	 */
	protected final WTPOperation createBaseOperation() {
		return new WebProjectCreationOperation(getWEBProjectCreationDataModel());
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
	 * Adds a {@see JCAProjectCreationPage} as the {@see J2EEModuleCreationWizard#MAIN_PG}.
	 * </p>
	 */
	protected void doAddPages() {
		WEBProjectCreationPage page = new WEBProjectCreationPage(getWEBProjectCreationDataModel(), MAIN_PG);
		page.setInfopopID("org.eclipse.jst.j2ee.internal.internal.internal.ui.webw1000"); //$NON-NLS-1$
		addPage(page);
		super.doAddPages();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Determines the final perspective that should be activated when the wizard completes.
	 * </p>
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#postPerformFinish()
	 */
	protected void postPerformFinish() {
		String finalPerspective = model.getStringProperty(J2EEProjectCreationDataModel.FINAL_PERSPECTIVE);
		if (finalPerspective == null || finalPerspective.equals("")) { //$NON-NLS-1$
			BasicNewProjectResourceWizard.updatePerspective(configData);
		} else {
			IConfigurationElement newConfig = new ConfigurationElement() {
				public String getAttribute(String aName) {
					if (aName.equals("finalPerspective")) //$NON-NLS-1$
						return model.getStringProperty(J2EEProjectCreationDataModel.FINAL_PERSPECTIVE);
					return super.getAttribute(aName);
				}
			};
			BasicNewProjectResourceWizard.updatePerspective(newConfig);
		}
		BasicNewResourceWizard.selectAndReveal(((J2EEProjectCreationDataModel) model).getTargetProject(), J2EEUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow());
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.extensions.ExtendableWizard#getWizardID()
	 */
	public String getWizardID() {
		return WIZARD_ID;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jst.j2ee.internal.wizard.J2EEArtifactCreationWizard#getPluginId()
	 */
	public String getPluginId() { 
		return ServletUIPlugin.PLUGIN_ID;
	}
	
	private WebProjectCreationDataModel getWEBProjectCreationDataModel() {
		return (WebProjectCreationDataModel) model;
	}

}