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
package org.eclipse.jst.j2ee.internal.migration.actions;


import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.migration.ComposedMigrationConfig;
import org.eclipse.jst.j2ee.internal.migration.J2EEMigrationOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.migration.J2EEMigrationStatus;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModel;
import org.eclipse.wst.internal.common.ui.WTPWizard;

/**
 *  
 */
public class J2EEMigrationWizard extends WTPWizard {
	public static final String CLIENT_MODULE_PAGE_NAME = "clientModulePage"; //$NON-NLS-1$
	// //$NON-NLS-1$
	public static final String EAR_PAGE_NAME = "earPage"; //$NON-NLS-1$
	public static final String EJB_BEAN_PAGE_NAME = "ejbBeanPage"; //$NON-NLS-1$
	public static final String EJB_MODULE_PAGE_NAME = "ejbModulePage"; //$NON-NLS-1$
	public static final String WEB_MODULE_PAGE_NAME = "webModulePage"; //$NON-NLS-1$
	public static final String CONNECTOR_PAGE_NAME = "connectorPage"; //$NON-NLS-1$
	public static final String WELCOME_PAGE_NAME = "welcomePage"; //$NON-NLS-1$
	protected ClientMigrationWizardPage clientPage;
	protected ComposedMigrationConfig composedConfig;
	protected EARMigrationWizardPage earPage;
	protected EJBBeanMigrationWizardPage ejbBeanPage;
	protected EJBModuleMigrationWizardPage ejbModulePage;
	protected J2EEMigrationOperation migrationOperation;
	protected IStructuredSelection selection;
	protected WebMigrationWizardPage webMigPage;
	protected MigrationWelcomeWizardPage welcomePage;
	protected ConnectorMigrationWizardPage connectorPage;
	protected IWorkbench workbench;
	protected int j2eeVersion = 12;

	/**
	 * Constructor for J2EEMigrationWizard.
	 */
	public J2EEMigrationWizard() {
		super();
		model = composedConfig;
		setWindowTitle(IMigrationWizardConstants.MIGRATION_WIZARD_WINDOW_TITLE);
	}

	/**
	 * Create the wizard pages
	 */
	public void addPages() {
		welcomePage = new MigrationWelcomeWizardPage(WELCOME_PAGE_NAME);
		addPage(welcomePage);

		earPage = new EARMigrationWizardPage(EAR_PAGE_NAME, composedConfig);
		addPage(earPage);

		ejbModulePage = new EJBModuleMigrationWizardPage(EJB_MODULE_PAGE_NAME, composedConfig);
		addPage(ejbModulePage);

		ejbBeanPage = new EJBBeanMigrationWizardPage(EJB_BEAN_PAGE_NAME, composedConfig);
		addPage(ejbBeanPage);

		webMigPage = new WebMigrationWizardPage(WEB_MODULE_PAGE_NAME, composedConfig);
		addPage(webMigPage);

		clientPage = new ClientMigrationWizardPage(CLIENT_MODULE_PAGE_NAME, composedConfig);
		addPage(clientPage);

		if (j2eeVersion == J2EEVersionConstants.J2EE_1_3_ID) {
			connectorPage = new ConnectorMigrationWizardPage(CONNECTOR_PAGE_NAME, composedConfig);
			addPage(connectorPage);
		}
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 */
	public boolean canFinish() {
		return (getContainer().getCurrentPage() != welcomePage) && super.canFinish();
	}

	//    /**
	//	 * Method refreshJ2EEView.
	//	 * @param project
	//	 */
	//	private void refreshJ2EEView(IProject project) {
	//		J2EERoot root = J2EERoot.instance();
	//		EObject obj = (EObject) root.getNodeFor(project);
	//		if (obj == null)
	//			return;
	//		AdapterFactory factory =
	// ((ComposedAdapterFactory)root.getAdapterFactory()).getFactoryForType(obj);
	//		if (factory != null) {
	//			Iterator adapters = obj.eAdapters().iterator();
	//			while (adapters.hasNext()) {
	//				Adapter adapter = (Adapter) adapters.next();
	//				if (adapter instanceof ItemProviderAdapter &&
	//					((ItemProviderAdapter)adapter).getAdapterFactory() == factory) {
	//					//Send a bogus fire to update the viewer.
	//					((ItemProviderAdapter)adapter).fireNotifyChanged(new
	// NotificationImpl(Notification.ADD,obj,null, -1));
	//				}
	//			}
	//		}
	//	}

	//	private void refreshJ2EEView(List projects) {
	//		if (projects.isEmpty() || !J2EERoot.hasInstance()) return;
	//		IProject project = null;
	//		for (int i = 0; i < projects.size(); i++) {
	//			project = (IProject) projects.get(i);
	//			refreshJ2EEView(project);
	//		}
	//	}

	//	private void refreshJ2EEView() {
	//		BusyIndicator.showWhile(getShell().getDisplay(), new Runnable() {
	//	        public void run() {
	//	        	primRefreshJ2EEView();
	//	        }
	//		});
	//	}
	//	private void primRefreshJ2EEView() {
	//		List projects = getMigrationConfig().getAllSelectedProjects();
	//		refreshJ2EEView(projects);
	//	}


	private boolean confirmFinish() {
		return ejbBeanPage.confirmAllRequiredBeansSelected();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		return composedConfig;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizard#createOperation()
	 */
	protected WTPOperation createOperation() {
		setMigrationOperation(new J2EEMigrationOperation(getMigrationConfig(), null));
		return migrationOperation;
	}

	/**
	 * Dispose of our migration configuration.
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#dispose()
	 */
	public void dispose() {
		super.dispose();
		if (composedConfig != null)
			composedConfig.dispose();
	}

	public ComposedMigrationConfig getMigrationConfig() {
		return composedConfig;
	}


	/**
	 * @return Returns the migrationOperation.
	 */
	public J2EEMigrationOperation getMigrationOperation() {
		return migrationOperation;
	}

	public IWizardPage getNextPage(IWizardPage aPage) {
		IWizardPage nextPage = aPage;
		do {
			nextPage = super.getNextPage(nextPage);
		} while (nextPage != null && !shouldBeVisible(nextPage));
		return nextPage;
	}


	//	/**
	//	 * Log the exception and throw up a dialog
	//	 *
	//	 * @param ex
	//	 */
	//	private void handleExceptionInFinish(Exception ex) {
	//		Logger.getLogger().logError(ex);
	//		MessageDialog.openError(getShell(), IJ2EEMigrationConstants.MIGRATION_ERRORS,
	// IJ2EEMigrationConstants.MIGRATION_ERRORS_REPORT);
	//	}

	/**
	 * Initializes this creation wizard using the passed workbench and object selection.
	 * 
	 * @param workbench
	 *            the current workbench
	 * @param selection
	 *            the current object selection
	 * @see com.ibm.itp.core.api.plugins.IExecutableExtension
	 */
	public void init(org.eclipse.ui.IWorkbench aWorkbench, org.eclipse.jface.viewers.IStructuredSelection aSelection) {
		workbench = aWorkbench;
		selection = aSelection;
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.MIGRATION_WIZARD_BANNER));
	}

	private void initConfigFromProjects(List projects) {
		try {
			if (projects.size() == 1) {
				IProject p = (IProject) projects.get(0);
				if (EARNatureRuntime.getRuntime(p) != null) {
					composedConfig = ComposedMigrationConfig.createComposedConfigForEAR(p);
					return;
				}
			}
			//one or many J2EE projects
			composedConfig = ComposedMigrationConfig.createComposedConfigForModules(projects);
		} finally {
			model = composedConfig;
		}

	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#needsProgressMonitor()
	 */
	public boolean needsProgressMonitor() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizard#postPerformFinish()
	 */
	protected void postPerformFinish() {
		// TODO Auto-generated method stub
		//super.postPerformFinish();
		showMigrationReport(getMigrationOperation().getMigrationStatus());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.ui.wizard.WTPWizard#prePerformFinish()
	 */
	protected boolean prePerformFinish() {
		if (!confirmFinish())
			return false;
		return super.prePerformFinish();
	}

	/**
	 * @param migrationOperation
	 *            The migrationOperation to set.
	 */
	public void setMigrationOperation(J2EEMigrationOperation migrationOperation) {
		this.migrationOperation = migrationOperation;
	}

	public void setProjects(List projects) {
		initConfigFromProjects(projects);
	}

	protected boolean shouldBeVisible(IWizardPage aPage) {
		if (aPage.getName() == WELCOME_PAGE_NAME)
			return true;
		else if (aPage.getName() == EAR_PAGE_NAME)
			return composedConfig.isEAR();
		else if (aPage.getName() == EJB_MODULE_PAGE_NAME)
			return composedConfig.hasEJBJarChildren();
		else if (aPage.getName() == EJB_BEAN_PAGE_NAME)
			return composedConfig.hasSelectedEJBJarChildren() && ejbModulePage.isAddLocalClientSelected() && ejbBeanPage.hasApplicableBeans();
		else if (aPage.getName() == WEB_MODULE_PAGE_NAME)
			return composedConfig.hasWebChildren();
		else if (aPage.getName() == CLIENT_MODULE_PAGE_NAME)
			return composedConfig.hasAppClientChildren();
		else if (aPage.getName() == CONNECTOR_PAGE_NAME)
			return composedConfig.hasConnectorChildren();
		return true;
	}

	private void showMigrationReport(J2EEMigrationStatus status) {
		if (status != null)
			MigrationStatusDialog.openDialog(getShell(), null, null, status);
	}

	/**
	 * @return Returns the j2eeVersion.
	 */
	public int getJ2eeVersion() {
		return j2eeVersion;
	}

	/**
	 * @param version
	 *            The j2eeVersion to set.
	 */
	public void setJ2eeVersion(int version) {
		j2eeVersion = version;
	}
}