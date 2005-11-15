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
import org.eclipse.jst.ejb.ui.project.facet.EjbProjectWizard;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.wizard.NewJavaClassWizardPage;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


public class NewEjbClassWizardPage extends NewJavaClassWizardPage {

	public NewEjbClassWizardPage(IDataModel model, String pageName, String pageDesc, String pageTitle, String moduleType) {
		super(model, pageName, pageDesc, pageTitle, moduleType);
	}
	protected Composite createTopLevelComposite(Composite parent) {
		 Composite composite =  super.createTopLevelComposite(parent);
		 return composite;
	}
	
	protected void createNewComponent() {
		IDataModel aModel = DataModelFactory.createDataModel(new EjbFacetProjectCreationDataModelProvider());
		FacetDataModelMap map = (FacetDataModelMap) aModel.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel ejbModel = map.getFacetDataModel(J2EEProjectUtilities.EJB);
		ejbModel.setBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR, false);
		ejbModel.setBooleanProperty(IEjbFacetInstallDataModelProperties.CREATE_CLIENT, false);
		EjbProjectWizard componentCreationWizard = new EjbProjectWizard(aModel);
			
		WizardDialog dialog = new WizardDialog(getShell(), componentCreationWizard);
		if (Window.OK == dialog.open()) {
			String newProjectName = aModel.getStringProperty( IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME);
			this.setProjectName(newProjectName);
		}
	}
	
}
