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
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.wizard.NewJavaClassWizardPage;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.jee.ui.internal.navigator.AbstractDDNode;
import org.eclipse.jst.jee.ui.internal.navigator.ejb.GroupEJBProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class NewEnterpriseBeanClassWizardPage extends NewJavaClassWizardPage {
	
	public NewEnterpriseBeanClassWizardPage(IDataModel model, String pageName,
			String pageDesc, String pageTitle, String moduleType) {
		super(model, pageName, pageDesc, pageTitle, moduleType);
	}
	
	@Override
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = super.createTopLevelComposite(parent);
		
		// bug 303917
		//projectNameLabel.setText(EJBUIMessages.EJB_PROJECT_LBL);
		
		return composite;
	}

	@Override
	protected boolean isProjectValid(IProject project) {
		boolean result = super.isProjectValid(project);
		// bug 241670 - 3.x EJBs can be created in 3.x EJB project, or Web 3.0 or Web Fragment 3.0
		boolean isJEEProject = J2EEProjectUtilities.isJEEProject(project);
		if (isJEEProject)
		{
			if (!result)
			{
				result = JavaEEProjectUtilities.isWebFragmentProject(project);
				if (!result)
				{
					IProjectFacetVersion webFacetVersion = JavaEEProjectUtilities.getProjectFacetVersion(project, IJ2EEFacetConstants.DYNAMIC_WEB);
					if (webFacetVersion != null)
					{
						int version = J2EEVersionUtil.convertVersionStringToInt(webFacetVersion.getVersionString());
						result = (version >= J2EEVersionConstants.VERSION_3_0);
					}
				}
			}
		}
		else
		{
			result = false;
		}
		return result;
	}
	
	@Override
	protected IProject getExtendedSelectedProject(Object selection) {
		if (selection instanceof GroupEJBProvider) {
			String projectName = ((GroupEJBProvider) selection).getProjectName();
			return ProjectUtilities.getProject(projectName);
		} else if (selection instanceof AbstractDDNode) {
			Object adapterNode = ((AbstractDDNode) selection).getAdapterNode();
			return ProjectUtilities.getProject(adapterNode);
		}
		
		return super.getExtendedSelectedProject(selection);
	}
	
}
