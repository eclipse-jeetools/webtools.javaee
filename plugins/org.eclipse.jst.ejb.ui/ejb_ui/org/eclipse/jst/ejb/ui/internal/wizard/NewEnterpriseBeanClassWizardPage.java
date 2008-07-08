/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.wizard.NewJavaClassWizardPage;
import org.eclipse.jst.jee.ui.internal.navigator.AbstractDDNode;
import org.eclipse.jst.jee.ui.internal.navigator.ejb.GroupEJBProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class NewEnterpriseBeanClassWizardPage extends NewJavaClassWizardPage {
	
	public NewEnterpriseBeanClassWizardPage(IDataModel model, String pageName,
			String pageDesc, String pageTitle, String moduleType) {
		super(model, pageName, pageDesc, pageTitle, moduleType);
	}
	
	@Override
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = super.createTopLevelComposite(parent);
		
		projectNameLabel.setText(EJBUIMessages.EJB_PROJECT_LBL);
		
		return composite;
	}

	@Override
	protected boolean isProjectValid(IProject project) {
		boolean result = super.isProjectValid(project);
		result = result && J2EEProjectUtilities.isJEEProject(project);
		return result;
	}
	
	@Override
	protected IProject getExtendedSelectedProject(Object selection) {
		if (selection instanceof GroupEJBProvider) {
			String projectName = ((GroupEJBProvider) selection).getProjectName();
			return ProjectUtilities.getProject(projectName);
		} else if (selection instanceof AbstractDDNode) {
			Object adapterNode = ((AbstractDDNode) selection).getAdapterNode();
			return ProjectUtilities.getProject((EObject)adapterNode);
		}
		
		return super.getExtendedSelectedProject(selection);
	}
	
}
