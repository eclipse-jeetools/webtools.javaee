/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.ejb.annotation.ui.internal.cmp.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.ContainerManagedEntityBeanDataModelProvider;
import org.eclipse.jst.j2ee.ejb.annotation.ui.internal.EjbAnnotationsUiPlugin;
import org.eclipse.jst.j2ee.ejb.annotation.ui.internal.wizards.IEJBUIContextIds;
import org.eclipse.jst.j2ee.ejb.annotation.ui.internal.wizards.NewEjbClassOptionsWizardPage;
import org.eclipse.jst.j2ee.ejb.annotation.ui.internal.wizards.NewEjbClassWizardPage;
import org.eclipse.jst.j2ee.ejb.annotation.ui.internal.wizards.NewEjbWizard;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.IDMPageHandler;
import org.eclipse.wst.rdb.internal.core.connection.ConnectionInfo;

public class AddContainerManagedEntityEjbWizard extends NewEjbWizard {
	protected NewEjbClassWizardPage newJavaClassWizardPage = null;
	protected AddContainerManagedEntityBeanWizardPage addEntityBeanWizardPage = null;
	protected NewEjbClassOptionsWizardPage newEjbClassOptionsWizardPage = null;
	private ConnectionSelectionPage connectionPage;
	private ChooseTableWizardPage tablePage;

	public static final String PAGE_ONE_NAME = "entity.pageOne"; //$NON-NLS-1$
	public static final String PAGE_TWO_NAME = "entity.pageTwo"; //$NON-NLS-1$
	public static final String PAGE_THREE_NAME = "entity.pageThree"; //$NON-NLS-1$
	public static final String CMP_TABLE_PAGE = "org.eclipse.jst.ejb.ui.cmp.TablePage";
	public static final String JDBCPAGE = "org.eclipse.jst.ejb.ui.cmp.NewCWJDBCPage";
	public static final String CONNECTION_PAGE = "org.eclipse.jst.ejb.ui.cmp.ConnectionPage";

	/**
	 * @param model
	 */
	public AddContainerManagedEntityEjbWizard(IDataModel model) {
		super(model);
		setWindowTitle(IEJBAnnotationConstants.ADD_EJB_WIZARD_WINDOW_TITLE);
		setDefaultPageImageDescriptor(EjbAnnotationsUiPlugin.getDefault().getImageDescriptor("icons/full/wizban/newejb_wiz_ban.gif")); //$NON-NLS-1$
	}

	public AddContainerManagedEntityEjbWizard() {
		this(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		IProject project = getDefaultEjbProject();
		if (project != null) {
			getDataModel().setProperty(IArtifactEditOperationDataModelProperties.PROJECT_NAME, project.getName());
		}
	}

	protected IDataModelProvider getDefaultProvider() {
		return new ContainerManagedEntityBeanDataModelProvider();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void doAddPages() {
		newJavaClassWizardPage = new NewEjbClassWizardPage(getDataModel(), PAGE_ONE_NAME,
				IEJBAnnotationConstants.NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC,
				IEJBAnnotationConstants.ADD_EJB_WIZARD_PAGE_TITLE, J2EEProjectUtilities.EJB);
		newJavaClassWizardPage.setInfopopID(IEJBUIContextIds.ANNOTATION_EJB_PAGE_ADD_ADD_WIZARD_2);
		addPage(newJavaClassWizardPage);

		addEntityBeanWizardPage = new AddContainerManagedEntityBeanWizardPage(getDataModel(), PAGE_TWO_NAME);
		addEntityBeanWizardPage.setInfopopID(IEJBUIContextIds.ANNOTATION_EJB_PAGE_ADD_ADD_WIZARD_4);
		addPage(addEntityBeanWizardPage);
		addEntityBeanWizardPage.setPageComplete(false);

		// This adds the JDBC connection configuration page which allows user to
		// configure the properties for a new JDBC connection.
		connectionPage = new ConnectionSelectionPage(getDataModel(), AddContainerManagedEntityEjbWizard.CONNECTION_PAGE);
		addPage(connectionPage);

		// This adds the JDBC connection configuration page which allows user to
		// configure the properties for a new JDBC connection.
		tablePage = new ChooseTableWizardPage(getDataModel(), AddContainerManagedEntityEjbWizard.CMP_TABLE_PAGE);
		addPage(tablePage);
		tablePage.setWizard(this);

		newEjbClassOptionsWizardPage = new NewEjbClassOptionsWizardPage(getDataModel(), PAGE_THREE_NAME,
				IEJBAnnotationConstants.NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC, IEJBAnnotationConstants.ADD_EJB_WIZARD_PAGE_TITLE);
		newEjbClassOptionsWizardPage.setInfopopID(IEJBUIContextIds.ANNOTATION_EJB_PAGE_ADD_ADD_WIZARD_3);
		addPage(newEjbClassOptionsWizardPage);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizard#runForked()
	 */
	protected boolean runForked() {
		return true;
	}

	public ConnectionInfo getConnectionInfo() {
		return connectionPage.getSelectedConnection();
	}

	public void updateTables() {
		if (tablePage != null)
			tablePage.updateTables();
	}

	public boolean isJavaBean() {
		if (addEntityBeanWizardPage != null)
			return addEntityBeanWizardPage.isJavaBean();
		return false;
	}

	public boolean canFinish() {
		if (newJavaClassWizardPage != null 
				&& newJavaClassWizardPage.isPageComplete() 
				&& addEntityBeanWizardPage != null
				&& connectionPage != null
				&& tablePage != null
				&& addEntityBeanWizardPage.isPageComplete()
				&& (addEntityBeanWizardPage.isJavaBean() || connectionPage.isPageComplete()) 
				&& tablePage.isPageComplete()) {
			return true;
		}

		return false;
	}

	public IWizardPage getNextPage(IWizardPage page) {

		IWizardPage next = super.getNextPage(page);
		if (next == tablePage)
			this.updateTables();
		return next;
	}

	public String getNextPage(String currentPageName, String expectedNextPageName) {
		if (null != expectedNextPageName && expectedNextPageName.equals(CONNECTION_PAGE) && addEntityBeanWizardPage.isJavaBean()) {
			return IDMPageHandler.PAGE_AFTER + CONNECTION_PAGE;
		}
		return super.getNextPage(currentPageName, expectedNextPageName);
	}

	public String getPreviousPage(String currentPageName, String expectedPreviousPageName) {
		if (expectedPreviousPageName.equals(CONNECTION_PAGE) && addEntityBeanWizardPage.isJavaBean()) {
			return IDMPageHandler.PAGE_BEFORE + CONNECTION_PAGE;
		}
		return super.getPreviousPage(currentPageName, expectedPreviousPageName);
	}

	public boolean testConnection() {
		return true;
	}
	
	public IProject getDefaultEjbProject() {
		return super.getDefaultEjbProject();
	}

}