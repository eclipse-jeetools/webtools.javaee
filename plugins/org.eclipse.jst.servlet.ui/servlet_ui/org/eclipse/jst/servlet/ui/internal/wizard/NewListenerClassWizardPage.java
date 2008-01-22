/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.war.ui.util.WebListenerGroupItemProvider;
import org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class NewListenerClassWizardPage extends NewWebClassWizardPage {

	public NewListenerClassWizardPage(IDataModel model, String pageName, String pageDesc, String pageTitle, String moduleType) {
		super(model, pageName, pageDesc, pageTitle, moduleType);
	}
	
	@Override
	protected String getUseExistingCheckboxText() {
		return IWebWizardConstants.USE_EXISTING_LISTENER_CLASS;
	}
	
	@Override
	protected String getUseExistingProperty() {
		return INewWebClassDataModelProperties.USE_EXISTING_CLASS;
	}
	
	@Override
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = super.createTopLevelComposite(parent);
		
		existingButton.setEnabled(false);
		
		return composite;
	}
	
	@Override
	protected IProject getExtendedSelectedProject(Object selection) {
		if (selection instanceof WebListenerGroupItemProvider) {
			WebApp webApp = (WebApp)((WebListenerGroupItemProvider)selection).getParent();
			return ProjectUtilities.getProject(webApp);
		}
		
		return super.getExtendedSelectedProject(selection);
	}
	
	@Override
	protected void handleClassButtonSelected() {
	}
	
}
