/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.ejb.annotation.ui.internal.wizards;


import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBeanClassDataModelProperties;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.SessionBeanDataModelProvider;
import org.eclipse.jst.j2ee.ejb.annotation.ui.internal.EjbAnnotationsUiPlugin;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;


public class AddEjbWizard extends NewEjbWizard {
	protected ChooseEjbTypeWizardPage chooseEjbTypeWizardPage = null;
	private static final String PAGE_ONE = "pageOne"; //$NON-NLS-1$
	private AddSessionEjbWizard sessionEjbWizard;
	private AddMessageDrivenEjbWizard messageDrivenEjbWizard;
	
	
	/**
	 * @param model
	 */
	public AddEjbWizard(IDataModel model) {
		super(model);
		setWindowTitle(IEJBAnnotationConstants.ADD_EJB_WIZARD_WINDOW_TITLE);
		setDefaultPageImageDescriptor(EjbAnnotationsUiPlugin.getDefault().getImageDescriptor("icons/full/wizban/newejb_wiz_ban.gif")); //$NON-NLS-1$
	}
	
	public AddEjbWizard() {
	    this(null);
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		this.setForcePreviousAndNextButtons(true);  
		// Otherwise number of pages will be used (1) without the nested wizards
		this.sessionEjbWizard =  new AddSessionEjbWizard();
		this.messageDrivenEjbWizard = new AddMessageDrivenEjbWizard();
		sessionEjbWizard.init(workbench, selection);
		sessionEjbWizard.addPages();
		messageDrivenEjbWizard.init(workbench, selection);
		messageDrivenEjbWizard.addPages();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void doAddPages() {
		chooseEjbTypeWizardPage = new ChooseEjbTypeWizardPage(getDataModel(),PAGE_ONE);
		chooseEjbTypeWizardPage.setInfopopID(IEJBUIContextIds.ANNOTATION_EJB_PAGE_ADD_ADD_WIZARD_0);
		addPage(chooseEjbTypeWizardPage);

	}
	

	
	public boolean canFinish() {
		if( "SessionBean".equals(chooseEjbTypeWizardPage.getEJBType())){
			if (sessionEjbWizard != null && sessionEjbWizard.canFinish()) {
				return true;
			}
		}else if( "MessageDrivenBean".equals(chooseEjbTypeWizardPage.getEJBType())){
			if (messageDrivenEjbWizard != null && messageDrivenEjbWizard.canFinish()) {
				return true;
			}
		}
		return false;
	}

	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage nextPage = super.getNextPage(page);
		IWizard wizard = this;
		if( page == chooseEjbTypeWizardPage && "SessionBean".equals(chooseEjbTypeWizardPage.getEJBType())){
			wizard = sessionEjbWizard;
			sessionEjbWizard.getDataModel().setProperty(IEnterpriseBeanClassDataModelProperties.ANNOTATIONPROVIDER,getDataModel().getProperty(IEnterpriseBeanClassDataModelProperties.ANNOTATIONPROVIDER));
		} else if( page == chooseEjbTypeWizardPage && "MessageDrivenBean".equals(chooseEjbTypeWizardPage.getEJBType())){
			wizard = messageDrivenEjbWizard;
			sessionEjbWizard.getDataModel().setProperty(IEnterpriseBeanClassDataModelProperties.ANNOTATIONPROVIDER,getDataModel().getProperty(IEnterpriseBeanClassDataModelProperties.ANNOTATIONPROVIDER));
		}
		if( wizard != this  && wizard !=null)
			nextPage = wizard.getStartingPage();
		return nextPage;
	}
	
	public IWizardPage getPreviousPage(IWizardPage page) {
		IWizardPage previousPage = super.getPreviousPage(page);
		return previousPage;
	}
	
	protected IDataModelProvider getDefaultProvider() {
		return new SessionBeanDataModelProvider();
	}

	/**
	 * Subclasses should override to return false if the running operation cannot be run forked.
	 * 
	 * @return
	 */
	protected boolean runForked() {
		return true;
	}
		
}