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
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.EnterpriseBeanClassDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.MessageDrivenBeanDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.operations.AddEjbOperation;
import org.eclipse.jst.j2ee.ejb.annotation.ui.internal.EjbAnnotationsUiPlugin;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditOperationDataModel;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModelListener;

public class AddMessageDrivenEjbWizard extends NewEjbWizard {
	protected NewEjbClassWizardPage newJavaClassWizardPage = null;
	protected AddMessageDrivenBeanWizardPage addMessageDrivenBeanWizardPage = null;
	protected NewEjbClassOptionsWizardPage newEjbClassOptionsWizardPage = null;

	private static final String PAGE_TWO = "pageTwo"; //$NON-NLS-1$
	private static final String PAGE_THREE = "pageThree"; //$NON-NLS-1$
	private static final String PAGE_FOUR = "pageFour"; //$NON-NLS-1$

	/**
	 * @param model
	 */
	public AddMessageDrivenEjbWizard(EnterpriseBeanClassDataModel model) {
		super(model);
		setWindowTitle(IEJBAnnotationConstants.ADD_EJB_WIZARD_WINDOW_TITLE);
		setDefaultPageImageDescriptor(EjbAnnotationsUiPlugin.getDefault().getImageDescriptor("icons/full/wizban/newejb_wiz_ban.gif")); //$NON-NLS-1$
	}

	public AddMessageDrivenEjbWizard() {
		this(null);
	}

	private void initializeEjbNameListener(final EnterpriseBeanClassDataModel dataModel) {
		dataModel.addListener(new WTPOperationDataModelListener() {

			public void propertyChanged(WTPOperationDataModelEvent event) {
				if (EnterpriseBeanClassDataModel.CLASS_NAME.equals(event.getPropertyName())) {
					String className = (String) event.getProperty();
					int i = className.toLowerCase().indexOf("bean");
					if (i < 0)
						i = className.toLowerCase().indexOf("ejb");
					if (i >= 0)
						className = className.substring(0, i);
					if (className.length() > 0) {
						dataModel.setProperty(EnterpriseBeanClassDataModel.EJB_NAME, className);
						dataModel.setProperty(EnterpriseBeanClassDataModel.JNDI_NAME, className);
						dataModel.setProperty(EnterpriseBeanClassDataModel.DISPLAY_NAME, className);
					}
				}
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		if (model != null)
			return model;
		this.model = new MessageDrivenBeanDataModel();
		IProject project = getDefaultEjbProject();
		if (project != null) {
			model.setProperty(ArtifactEditOperationDataModel.PROJECT_NAME, project.getName());
		}
		return model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizard#createOperation()
	 */
	protected WTPOperation createBaseOperation() {
		return new AddEjbOperation((EnterpriseBeanClassDataModel) model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void doAddPages() {
		newJavaClassWizardPage = new NewEjbClassWizardPage((ArtifactEditOperationDataModel) model, PAGE_TWO,
				IEJBAnnotationConstants.NEW_JAVA_CLASS_DESTINATION_WIZARD_PAGE_DESC,
				IEJBAnnotationConstants.ADD_EJB_WIZARD_PAGE_TITLE, IModuleConstants.JST_EJB_MODULE);
		newJavaClassWizardPage.setInfopopID(IEJBUIContextIds.ANNOTATION_EJB_PAGE_ADD_ADD_WIZARD_2);
		addPage(newJavaClassWizardPage);

		addMessageDrivenBeanWizardPage = new AddMessageDrivenBeanWizardPage((EnterpriseBeanClassDataModel) model, PAGE_THREE);
		addMessageDrivenBeanWizardPage.setInfopopID(IEJBUIContextIds.ANNOTATION_EJB_PAGE_ADD_ADD_WIZARD_1);
		addPage(addMessageDrivenBeanWizardPage);
		addMessageDrivenBeanWizardPage.setPageComplete(false);

		newEjbClassOptionsWizardPage = new NewEjbClassOptionsWizardPage((ArtifactEditOperationDataModel) model, PAGE_FOUR,
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
		return false;
	}

	public boolean canFinish() {
		if (newJavaClassWizardPage != null && newJavaClassWizardPage.isPageComplete() && addMessageDrivenBeanWizardPage != null
				&& addMessageDrivenBeanWizardPage.isPageComplete()) {
			return true;
		}

		return false;
	}

}