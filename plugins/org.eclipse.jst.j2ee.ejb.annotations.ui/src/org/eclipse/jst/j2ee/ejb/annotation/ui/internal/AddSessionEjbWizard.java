/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.ejb.annotation.ui.internal;


import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.EjbCommonDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.NewEJBJavaClassDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.SessionBeanDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.operations.AddSessionBeanOperation;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;


public class AddSessionEjbWizard extends NewEjbWizard {
	private static final String PAGE_ONE = "pageOne"; //$NON-NLS-1$
	private static final String PAGE_TWO = "pageTwo"; //$NON-NLS-1$
	private static final String PAGE_THREE = "pageThree"; //$NON-NLS-1$
	/**
	 * @param model
	 */
	public AddSessionEjbWizard(EjbCommonDataModel model) {
		super(model);
		setWindowTitle(IEJBAnnotationConstants.ADD_SESSION_EJB_WIZARD_WINDOW_TITLE);
		setDefaultPageImageDescriptor(EjbAnnotationsUiPlugin.getDefault().getImageDescriptor("icons/full/wizban/newejb_wiz_ban.gif")); //$NON-NLS-1$
	}
	
	public AddSessionEjbWizard() {
	    this(null);
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
	    if (model != null)
	        return model;
	    model = new SessionBeanDataModel();
		NewEJBJavaClassDataModel nestedModel = new NewEJBJavaClassDataModel();
		model.addNestedModel("NewEJBJavaClassDataModel", nestedModel); //$NON-NLS-1$
		//nestedModel.setProperty(NewServletClassDataModel.EDITING_DOMAIN, domain);
		nestedModel.setProperty(NewEJBJavaClassDataModel.SUPERCLASS, ((SessionBeanDataModel)model).getEjbSuperclassName());
		nestedModel.setProperty(NewEJBJavaClassDataModel.INTERFACES, ((SessionBeanDataModel)model).getEJBInterfaces());
		nestedModel.setBooleanProperty(NewEJBJavaClassDataModel.MODIFIER_ABSTRACT,true);
		nestedModel.setParentDataModel(model);
		
		IProject project = getDefaultEjbProject();
		if (project != null) {
		    model.setProperty(NewJavaClassDataModel.PROJECT_NAME, project.getName());
		    nestedModel.setProperty(NewJavaClassDataModel.PROJECT_NAME, project.getName());
		}
		return model;
	}
	/* (non-Javadoc)
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizard#createOperation()
	 */
	protected WTPOperation createBaseOperation() {
		return new AddSessionBeanOperation((SessionBeanDataModel)model) ;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void doAddPages() {
		AddSessionBeanWizardPage page1 = new AddSessionBeanWizardPage((SessionBeanDataModel) model, PAGE_ONE);
		page1.setInfopopID(IEJBUIContextIds.ANNOTATION_EJB_PAGE_ADD_ADD_WIZARD_1);
		addPage(page1);
		NewEJBJavaClassDataModel nestedModel = (NewEJBJavaClassDataModel)model.getNestedModel("NewEJBJavaClassDataModel"); //$NON-NLS-1$
		NewEJBJavaClassDestinationWizardPage page2 = new NewEJBJavaClassDestinationWizardPage(
				nestedModel, 
				PAGE_TWO,
				IEJBAnnotationConstants.NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC,
				IEJBAnnotationConstants.ADD_SESSION_EJB_WIZARD_PAGE_TITLE);
		page2.setInfopopID(IEJBUIContextIds.ANNOTATION_EJB_PAGE_ADD_ADD_WIZARD_2);
		addPage(page2);
		NewEjbClassOptionsWizardPage page3 = new NewEjbClassOptionsWizardPage(
				nestedModel, 
				PAGE_THREE,
				IEJBAnnotationConstants.NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC,
				IEJBAnnotationConstants.ADD_SESSION_EJB_WIZARD_PAGE_TITLE);
		page3.setInfopopID(IEJBUIContextIds.ANNOTATION_EJB_PAGE_ADD_ADD_WIZARD_3);
		addPage(page3);
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizard#runForked()
	 */
	protected boolean runForked() {
		return false;
	}
	
	public boolean canFinish() {
		AddSessionBeanWizardPage firstPage = (AddSessionBeanWizardPage)getPage(PAGE_ONE);
		NewEJBJavaClassDestinationWizardPage secondPage = (NewEJBJavaClassDestinationWizardPage)getPage(PAGE_TWO);
		if (firstPage != null && firstPage.isPageComplete() && secondPage.isPageComplete() ) {
			return true;
		}
		return false;//super.canFinish();
	}
}