/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.ejb.annotation.ui.internal.wizards;


import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBeanClassDataModelProperties;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.SessionBeanDataModelProvider;
import org.eclipse.jst.j2ee.ejb.annotation.ui.internal.EjbAnnotationsUiPlugin;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelListener;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

public class AddSessionEjbWizard extends NewEjbWizard {
	protected NewEjbClassWizardPage  newJavaClassWizardPage = null;
	protected AddSessionBeanWizardPage addSessionBeanWizardPage = null;
	protected NewEjbClassOptionsWizardPage newEjbClassOptionsWizardPage = null;
	
	public static final String PAGE_ONE_NAME = "session.pageOne"; //$NON-NLS-1$
	public static final String PAGE_TWO_NAME = "session.pageTwo"; //$NON-NLS-1$
	public static final String PAGE_THREE_NAME = "session.pageThree"; //$NON-NLS-1$
	
	
	/**
	 * @param model
	 */
	public AddSessionEjbWizard(IDataModel model) {
		super(model);
		
		setWindowTitle(IEJBAnnotationConstants.ADD_EJB_WIZARD_WINDOW_TITLE);
		setDefaultPageImageDescriptor(EjbAnnotationsUiPlugin.getDefault().getImageDescriptor("icons/full/wizban/newejb_wiz_ban.gif")); //$NON-NLS-1$
	}
	
	public AddSessionEjbWizard() {
	    this(null);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench,selection);
		IProject project = getDefaultEjbProject();
		if (project != null) {
		    getDataModel().setProperty(IArtifactEditOperationDataModelProperties.PROJECT_NAME, project.getName());
		}
		initializeEjbNameListener(getDataModel());
	}
	
	protected IDataModelProvider getDefaultProvider() {
		return new SessionBeanDataModelProvider();
	}
	
	private void initializeEjbNameListener(final IDataModel dataModel) {
		dataModel.addListener(new IDataModelListener(){

			public void propertyChanged(DataModelEvent event) {
				if( INewJavaClassDataModelProperties.CLASS_NAME.equals(event.getPropertyName()))
				{
					String className = (String)event.getProperty();
					int i = className.toLowerCase().indexOf("bean");
					if(i < 0 )
						i= className.toLowerCase().indexOf("ejb");
					if( i >= 0)
						className = className.substring(0,i);
					if( className.length() > 0 ){
						dataModel.setProperty(IEnterpriseBeanClassDataModelProperties.EJB_NAME,className);
						dataModel.setProperty(IEnterpriseBeanClassDataModelProperties.JNDI_NAME,className);
						dataModel.setProperty(IEnterpriseBeanClassDataModelProperties.DISPLAY_NAME,className);
					}
				}
			}});
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void doAddPages() {
		newJavaClassWizardPage = new NewEjbClassWizardPage(
				getDataModel(), 
				PAGE_ONE_NAME,
				IEJBAnnotationConstants.NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC,
				IEJBAnnotationConstants.ADD_EJB_WIZARD_PAGE_TITLE,
				J2EEProjectUtilities.EJB);
		newJavaClassWizardPage.setInfopopID(IEJBUIContextIds.ANNOTATION_EJB_PAGE_ADD_ADD_WIZARD_2);
		addPage(newJavaClassWizardPage);
		
		
		addSessionBeanWizardPage = new AddSessionBeanWizardPage(getDataModel(), PAGE_TWO_NAME);
		addSessionBeanWizardPage.setInfopopID(IEJBUIContextIds.ANNOTATION_EJB_PAGE_ADD_ADD_WIZARD_1);
		addPage(addSessionBeanWizardPage);
		addSessionBeanWizardPage.setPageComplete(false);

		newEjbClassOptionsWizardPage = new NewEjbClassOptionsWizardPage(
				getDataModel(), 
				PAGE_THREE_NAME,
				IEJBAnnotationConstants.NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC,
				IEJBAnnotationConstants.ADD_EJB_WIZARD_PAGE_TITLE);
		newEjbClassOptionsWizardPage.setInfopopID(IEJBUIContextIds.ANNOTATION_EJB_PAGE_ADD_ADD_WIZARD_3);
		addPage(newEjbClassOptionsWizardPage);
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizard#runForked()
	 */
	protected boolean runForked() {
		return true;
	}
	
	public boolean canFinish() {
		if ( newJavaClassWizardPage != null && newJavaClassWizardPage.isPageComplete() && addSessionBeanWizardPage != null && addSessionBeanWizardPage.isPageComplete() ) {
				return true;
			}


		return false;
	}
}