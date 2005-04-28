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
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.EjbCommonDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.MessageDrivenBeanDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.NewEJBJavaClassDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.SessionBeanDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.operations.AddEjbOperation;
import org.eclipse.jst.j2ee.ejb.annotation.ui.internal.EjbAnnotationsUiPlugin;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModel;
import org.eclipse.jst.j2ee.internal.wizard.NewJavaClassWizardPage;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditOperationDataModel;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModelListener;


public class AddEjbWizard extends NewEjbWizard {
	protected ChooseEjbTypeWizardPage chooseEjbTypeWizardPage = null;
	protected NewJavaClassWizardPage  newJavaClassWizardPage = null;
	protected AddSessionBeanWizardPage addSessionBeanWizardPage = null;
	protected AddMessageDrivenBeanWizardPage addMessageDrivenBeanWizardPage = null;
	protected NewEjbClassOptionsWizardPage newEjbClassOptionsWizardPage = null;
	
	private static final String PAGE_ZERO = "pageZero"; //$NON-NLS-1$
	private static final String PAGE_ONE = "pageOne"; //$NON-NLS-1$
	private static final String PAGE_TWO = "pageTwo"; //$NON-NLS-1$
	private static final String PAGE_THREE = "pageThree"; //$NON-NLS-1$
	private static final String PAGE_FOUR = "pageFour"; //$NON-NLS-1$
	private static final String PAGE_FIVE = "pageFive"; //$NON-NLS-1$
	/**
	 * @param model
	 */
	public AddEjbWizard(EjbCommonDataModel model) {
		super(model);
		setWindowTitle(IEJBAnnotationConstants.ADD_EJB_WIZARD_WINDOW_TITLE);
		setDefaultPageImageDescriptor(EjbAnnotationsUiPlugin.getDefault().getImageDescriptor("icons/full/wizban/newejb_wiz_ban.gif")); //$NON-NLS-1$
	}
	
	public AddEjbWizard() {
	    this(null);
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
	    if (model != null)
	        return model;
	    model = new EjbCommonDataModel();
		model.addNestedModel("NewEJBJavaClassDataModel", new NewEJBJavaClassDataModel());
		NewEJBJavaClassDataModel nestedModel = ((EjbCommonDataModel)model).getJavaClassModel();
		
		final SessionBeanDataModel sessionBeanDataModel = new SessionBeanDataModel();
		model.addNestedModel("SessionBeanDataModel", sessionBeanDataModel); //$NON-NLS-1$
		sessionBeanDataModel.addNestedModel("NewEJBJavaClassDataModel", nestedModel);
		
		nestedModel.setProperty(NewJavaClassDataModel.SUPERCLASS, sessionBeanDataModel.getEjbSuperclassName());
		nestedModel.setProperty(NewJavaClassDataModel.INTERFACES, sessionBeanDataModel.getEJBInterfaces());
		nestedModel.setBooleanProperty(NewJavaClassDataModel.MODIFIER_ABSTRACT,true);
		
		final MessageDrivenBeanDataModel messageDrivenBeanDataModel = new MessageDrivenBeanDataModel();
		model.addNestedModel("MessageDrivenBeanDataModel", messageDrivenBeanDataModel); //$NON-NLS-1$
		messageDrivenBeanDataModel.addNestedModel("NewEJBJavaClassDataModel", nestedModel);
		
		IProject project = getDefaultEjbProject();
		if (project != null) {
		    model.setProperty(ArtifactEditOperationDataModel.PROJECT_NAME, project.getName());
		    sessionBeanDataModel.setProperty(ArtifactEditOperationDataModel.PROJECT_NAME, project.getName());
		    messageDrivenBeanDataModel.setProperty(ArtifactEditOperationDataModel.PROJECT_NAME, project.getName());
			nestedModel.setProperty(ArtifactEditOperationDataModel.PROJECT_NAME, project.getName());
		}
		
		nestedModel.addListener(new WTPOperationDataModelListener(){

			public void propertyChanged(WTPOperationDataModelEvent event) {
				if( NewJavaClassDataModel.CLASS_NAME.equals(event.getPropertyName()))
				{
					String className = (String)event.getProperty();
					int i = className.toLowerCase().indexOf("bean");
					if(i < 0 )
						i= className.toLowerCase().indexOf("ejb");
					if( i >= 0)
						className = className.substring(0,i);
					if( className.length() > 0 ){
						sessionBeanDataModel.setProperty(SessionBeanDataModel.EJB_NAME,className);
						sessionBeanDataModel.setProperty(SessionBeanDataModel.JNDI_NAME,className);
						sessionBeanDataModel.setProperty(SessionBeanDataModel.DISPLAY_NAME,className);
						messageDrivenBeanDataModel.setProperty(MessageDrivenBeanDataModel.EJB_NAME,className);
						messageDrivenBeanDataModel.setProperty(MessageDrivenBeanDataModel.DISPLAY_NAME,className);
						messageDrivenBeanDataModel.setProperty(MessageDrivenBeanDataModel.JNDI_NAME,className);
					}
				}
			}});
		
		model.addListener(new WTPOperationDataModelListener(){

			public void propertyChanged(WTPOperationDataModelEvent event) {
				 if( EjbCommonDataModel.ANNOTATIONPROVIDER.equals(event.getPropertyName()))
				{
					String provider = (String)event.getProperty();
					sessionBeanDataModel.setProperty( EjbCommonDataModel.ANNOTATIONPROVIDER, provider);
					messageDrivenBeanDataModel.setProperty( EjbCommonDataModel.ANNOTATIONPROVIDER, provider);
				}
			}});		
		return model;
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizard#createOperation()
	 */
	protected WTPOperation createBaseOperation() {
		return new AddEjbOperation((EjbCommonDataModel)model) ;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void doAddPages() {
		
		
		chooseEjbTypeWizardPage = new ChooseEjbTypeWizardPage(model,PAGE_ONE);
		chooseEjbTypeWizardPage.setInfopopID(IEJBUIContextIds.ANNOTATION_EJB_PAGE_ADD_ADD_WIZARD_0);
		addPage(chooseEjbTypeWizardPage);

		
		NewJavaClassDataModel nestedModel = (NewJavaClassDataModel)model.getNestedModel("NewEJBJavaClassDataModel"); //$NON-NLS-1$
		newJavaClassWizardPage = new NewJavaClassWizardPage(
				nestedModel, 
				PAGE_TWO,
				IEJBAnnotationConstants.NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC,
				IEJBAnnotationConstants.ADD_EJB_WIZARD_PAGE_TITLE,
				IModuleConstants.JST_EJB_MODULE);
		newJavaClassWizardPage.setInfopopID(IEJBUIContextIds.ANNOTATION_EJB_PAGE_ADD_ADD_WIZARD_2);
		addPage(newJavaClassWizardPage);
		
		
		addSessionBeanWizardPage = new AddSessionBeanWizardPage((SessionBeanDataModel) model.getNestedModel("SessionBeanDataModel"), PAGE_THREE);
		addSessionBeanWizardPage.setInfopopID(IEJBUIContextIds.ANNOTATION_EJB_PAGE_ADD_ADD_WIZARD_1);
		addPage(addSessionBeanWizardPage);
		addSessionBeanWizardPage.setPageComplete(false);
		
		addMessageDrivenBeanWizardPage = new AddMessageDrivenBeanWizardPage((MessageDrivenBeanDataModel) model.getNestedModel("MessageDrivenBeanDataModel"), PAGE_FOUR);
		addSessionBeanWizardPage.setInfopopID(IEJBUIContextIds.ANNOTATION_EJB_PAGE_ADD_ADD_WIZARD_1);
		addPage(addMessageDrivenBeanWizardPage);
		addMessageDrivenBeanWizardPage.setPageComplete(false);

		newEjbClassOptionsWizardPage = new NewEjbClassOptionsWizardPage(
				nestedModel, 
				PAGE_FIVE,
				IEJBAnnotationConstants.NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC,
				IEJBAnnotationConstants.ADD_EJB_WIZARD_PAGE_TITLE);
		newEjbClassOptionsWizardPage.setInfopopID(IEJBUIContextIds.ANNOTATION_EJB_PAGE_ADD_ADD_WIZARD_3);
		addPage(newEjbClassOptionsWizardPage);
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizard#runForked()
	 */
	protected boolean runForked() {
		return false;
	}
	
	public boolean canFinish() {
		if( "SessionBean".equals(chooseEjbTypeWizardPage.getEJBType())){
			if ( newJavaClassWizardPage != null && newJavaClassWizardPage.isPageComplete() && addSessionBeanWizardPage != null && addSessionBeanWizardPage.isPageComplete() ) {
				return true;
			}
		}else if( "MessageDrivenBean".equals(chooseEjbTypeWizardPage.getEJBType())){
			if ( newJavaClassWizardPage != null && newJavaClassWizardPage.isPageComplete() && addMessageDrivenBeanWizardPage != null && addMessageDrivenBeanWizardPage.isPageComplete() ) {
				return true;
			}
		}
		return false;
	}

	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage nextPage = super.getNextPage(page);
		if( page == newJavaClassWizardPage && "SessionBean".equals(chooseEjbTypeWizardPage.getEJBType())){
			return addSessionBeanWizardPage;
		} if( page == newJavaClassWizardPage){
			return addMessageDrivenBeanWizardPage;
		}
				
		if( page == addMessageDrivenBeanWizardPage || page == addSessionBeanWizardPage)
			return newEjbClassOptionsWizardPage;
		
		return nextPage;
	}
	
	public IWizardPage getPreviousPage(IWizardPage page) {
		IWizardPage previousPage = super.getPreviousPage(page);
		
		if( page == addMessageDrivenBeanWizardPage && "MessageDrivenBean".equals(chooseEjbTypeWizardPage.getEJBType())){
			return newJavaClassWizardPage;
		} 
		return previousPage;
	}
		
}