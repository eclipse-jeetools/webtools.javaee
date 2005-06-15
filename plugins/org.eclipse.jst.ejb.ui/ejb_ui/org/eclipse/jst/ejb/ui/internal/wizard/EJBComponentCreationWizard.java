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
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.ejb.datamodel.properties.IEjbComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.J2EEComponentCreationWizard;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

/** 
 * <p>
 * Wizard used to create J2EE Enterprise Java Bean 
 * (EJB) module structures in Eclipse Projects.
 * </p>
 */
public final class EJBComponentCreationWizard extends J2EEComponentCreationWizard implements IEjbComponentCreationDataModelProperties{
	
	/**
	 * <p>
	 * The Wizard ID of the ConnectorModuleCreationWizard. Used for 
	 * internal purposes and activities management.
	 * </p>
	 */
	public static final String WIZARD_ID = EJBComponentCreationWizard.class.getName();
	
	/**
	 * <p>
	 * The identifer for the EJB Client page
	 * </p>
	 */
	protected static final String CLIENT_PG = "client"; //$NON-NLS-1$
	
	/* A convenience reference to the client page. Initialized in doAddPages() */
	private EJBClientComponentCreationWizardPage clientPage;

	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, 
	 * no model instance, and no operation instance. The model and 
	 * operation will be created as needed.
	 * </p>
	 */
	public EJBComponentCreationWizard() {
		super();
	}

	/**
	 * <p>
	 * The model is used to prepopulate the wizard controls
	 * and interface with the operation.
	 * </p>
	 * @param model The model parameter is used to pre-populate wizard controls and interface with the operation
	 */
	public EJBComponentCreationWizard(IDataModel model) {
		super(model);
	}

	/**
	 * {@inheritDoc}   
	 * 
	 * <p>
	 * Sets up the dialog window title and default page image. 
	 * </p> 
	 * 
	 */
	protected void doInit() { 
		setWindowTitle(EJBUIMessages.getResourceString(EJBUIMessages.EJB_PROJECT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EJB_PROJECT_WIZARD_BANNER));
		preFillSelectedEARProject();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Skips the EJB Client settings page based on the return value of shouldSkipClientPage()
	 * </p>
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage nextPage = super.getNextPage(page);
		if (nextPage != null && nextPage.getName().equals(CLIENT_PG) && shouldSkipClientPage())
			return super.getNextPage(nextPage);
		return nextPage;
	}


	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Skips the EJB Client settings page based on the return value of shouldSkipClientPage()
	 * </p>
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#getPreviousPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	public IWizardPage getPreviousPage(IWizardPage page) {
		IWizardPage previousPage = super.getPreviousPage(page);
		if (previousPage != null && previousPage.getName().equals(CLIENT_PG) && shouldSkipClientPage()) {
			IWizardPage temp = getPage(MAIN_PG);
			if (temp != null)
				return temp;
		}
		return previousPage;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Adds a {@link EJBComponentCreationWizardPage} as the {@link J2EEComponentCreationWizard#MAIN_PG} 
	 * and a {@link EJBClientCreationWizardPage} as the {@link #CLIENT_PG}.
	 * </p>
	 */
	public void doAddPages() {
		addPage(new EJBComponentCreationWizardPage(getDataModel(), MAIN_PG));
		clientPage = new EJBClientComponentCreationWizardPage((IDataModel)getDataModel().getProperty(NESTED_MODEL_EJB_CLIENT_CREATION), CLIENT_PG);
		addPage(clientPage);
		super.doAddPages();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#canFinish()
	 * @return true if the parent Wizard class is ready and EJB Client Creation settings are complete
	 */
	public boolean canFinish() {
		if (!getDataModel().getBooleanProperty(CREATE_CLIENT)) {
			clientPage.setPageComplete(true);
		}
		return super.canFinish();
	}
	/**
	 * @return true if the client page should be skipped (based on the value of {@see EJBProjectCreationDataModel#CREATE_CLIENT}.
	 */
	protected final boolean shouldSkipClientPage() {
		return !getDataModel().getBooleanProperty(CREATE_CLIENT);
	}

    protected IDataModelProvider getDefaultProvider() {
        return new EjbComponentCreationDataModelProvider();
    }
}