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

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.common.operations.NewJavaClassDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.web.operations.AddServletOperation;
import org.eclipse.jst.j2ee.web.operations.NewServletClassDataModel;
import org.eclipse.jst.servlet.ui.IWebUIContextIds;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;

/**
 * New servlet wizard
 */
public class AddServletWizard extends NewWebWizard {
	private static final String PAGE_ONE = "pageOne"; //$NON-NLS-1$
	private static final String PAGE_TWO = "pageTwo"; //$NON-NLS-1$
	private static final String PAGE_THREE = "pageThree"; //$NON-NLS-1$
	/**
	 * @param model
	 */
	public AddServletWizard(NewServletClassDataModel model) {
		super(model);
		setWindowTitle(IWebWizardConstants.ADD_SERVLET_WIZARD_WINDOW_TITLE);
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor("newservlet_wiz")); //$NON-NLS-1$
	}
	
	public AddServletWizard() {
	    this(null);
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
	    if (model != null)
	        return model;
	    model = new NewServletClassDataModel();
		model.setProperty(NewJavaClassDataModel.SUPERCLASS, NewServletClassDataModel.SERVLET_SUPERCLASS);
		model.setProperty(NewJavaClassDataModel.INTERFACES, ((NewServletClassDataModel)model).getServletInterfaces());
		
		IProject project = getDefaultWebProject();
		if (project != null)
		    model.setProperty(EditModelOperationDataModel.PROJECT_NAME, project.getName());
		return model;
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizard#createOperation()
	 */
	protected WTPOperation createBaseOperation() {
		return new AddServletOperation((NewServletClassDataModel)model) ;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void doAddPages() {
		AddServletWizardPage page1 = new AddServletWizardPage((NewServletClassDataModel) model, PAGE_ONE);
		page1.setInfopopID(IWebUIContextIds.WEBEDITOR_SERVLET_PAGE_ADD_SERVLET_WIZARD_1);
		addPage(page1);
		NewWebJavaClassDestinationWizardPage page2 = new NewWebJavaClassDestinationWizardPage(
				model, 
				PAGE_TWO,
				IWebWizardConstants.NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC,
				IWebWizardConstants.ADD_SERVLET_WIZARD_PAGE_TITLE);
		page2.setInfopopID(IWebUIContextIds.WEBEDITOR_SERVLET_PAGE_ADD_SERVLET_WIZARD_2);
		addPage(page2);
		NewServletClassOptionsWizardPage page3 = new NewServletClassOptionsWizardPage(
				model, 
				PAGE_THREE,
				IWebWizardConstants.NEW_JAVA_CLASS_OPTIONS_WIZARD_PAGE_DESC,
				IWebWizardConstants.ADD_SERVLET_WIZARD_PAGE_TITLE);
		page3.setInfopopID(IWebUIContextIds.WEBEDITOR_SERVLET_PAGE_ADD_SERVLET_WIZARD_3);
		addPage(page3);
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizard#runForked()
	 */
	protected boolean runForked() {
		return false;
	}
	
	public boolean canFinish() {
		AddServletWizardPage firstPage = (AddServletWizardPage)getPage(PAGE_ONE);
		NewWebJavaClassDestinationWizardPage secondPage = (NewWebJavaClassDestinationWizardPage)getPage(PAGE_TWO);
		if (firstPage != null && firstPage.isPageComplete() && secondPage.isPageComplete() ) {
			return true;
		}
		return false;//super.canFinish();
	}
}