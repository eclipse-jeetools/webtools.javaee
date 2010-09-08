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

import static org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties.USE_EXISTING_CLASS;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.USE_EXISTING_LISTENER_CLASS;

import org.eclipse.core.resources.IProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.war.ui.util.WebListenerGroupItemProvider;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.jee.ui.internal.navigator.web.GroupListenerItemProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.WebAppProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class NewListenerClassWizardPage extends NewWebClassWizardPage {

	public NewListenerClassWizardPage(IDataModel model, String pageName, String pageDesc, String pageTitle, String moduleType) {
		super(model, pageName, pageDesc, pageTitle, moduleType);
	}
	
	@Override
	protected String getUseExistingCheckboxText() {
		return USE_EXISTING_LISTENER_CLASS;
	}
	
	@Override
	protected String getUseExistingProperty() {
		return USE_EXISTING_CLASS;
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
			WebApp webApp = (WebApp)((WebListenerGroupItemProvider) selection).getParent();
			return ProjectUtilities.getProject(webApp);
		}else if(selection instanceof WebAppProvider){
			return ((WebAppProvider) selection).getProject();
		} else if(selection instanceof GroupListenerItemProvider){
			org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) ((GroupListenerItemProvider) selection).getJavaEEObject();
			return ProjectUtilities.getProject(webApp);
		}
		
		return super.getExtendedSelectedProject(selection);
	}
	
	@Override
	protected void handleClassButtonSelected() {
	}

	@Override
	protected boolean isProjectValid(IProject project) {
		boolean result = super.isProjectValid(project);
		if (!result) 
			return false;

		if (JavaEEProjectUtilities.isWebFragmentProject(project))
			return true;
		
		// get the version of the web facet
		IProjectFacetVersion facetVersion = JavaEEProjectUtilities.getProjectFacetVersion(project, IJ2EEFacetConstants.DYNAMIC_WEB);
		if (facetVersion == null) 
			return false;
		
		// convert the version to an integer
		int version = J2EEVersionUtil.convertVersionStringToInt(facetVersion.getVersionString());
		
		// only web 2.3 and greater projects are valid
		return  version > J2EEVersionConstants.SERVLET_2_2;
	}
	
}
