/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.ui.internal.wizards;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.ejb.ui.internal.wizard.EJBComponentCreationWizard;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.wizard.NewJavaClassWizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditOperationDataModel;


public class NewEjbClassWizardPage extends NewJavaClassWizardPage {

	public NewEjbClassWizardPage(ArtifactEditOperationDataModel model, String pageName, String pageDesc, String pageTitle, String moduleType) {
		super(model, pageName, pageDesc, pageTitle, moduleType);
		this.setHasNewModuleButton(true);
	}
	protected Composite createTopLevelComposite(Composite parent) {
		 Composite composite =  super.createTopLevelComposite(parent);
		 return composite;
	}
	
	protected void createNewComponent() {
		EjbComponentCreationDataModel aModel = new EjbComponentCreationDataModel();
		aModel.setBooleanProperty(J2EEComponentCreationDataModel.ADD_TO_EAR, false);
		aModel.setBooleanProperty(EjbComponentCreationDataModel.CREATE_CLIENT, false);
		EJBComponentCreationWizard componentCreationWizard = new EJBComponentCreationWizard(aModel);
			
		WizardDialog dialog = new WizardDialog(getShell(), componentCreationWizard);
		if (Window.OK == dialog.open()) {
			String newProjectName = aModel.getStringProperty(ArtifactEditOperationDataModel.PROJECT_NAME);
			this.setProjectName(newProjectName);
		}
	}
}
