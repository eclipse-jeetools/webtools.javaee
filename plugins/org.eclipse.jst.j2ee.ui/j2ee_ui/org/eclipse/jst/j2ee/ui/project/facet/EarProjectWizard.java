/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ui.project.facet;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.project.facet.EARFacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectTemplate;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;
import org.eclipse.wst.project.facet.IProductConstants;
import org.eclipse.wst.project.facet.ProductManager;
import org.eclipse.wst.web.ui.internal.wizards.NewProjectDataModelFacetWizard;
import org.osgi.framework.Bundle;

public class EarProjectWizard extends NewProjectDataModelFacetWizard {

	public EarProjectWizard(IDataModel model){
		super(model);
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_COMPONENT_WIZ_TITLE));
	}
	
	public EarProjectWizard(){
		super();
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_COMPONENT_WIZ_TITLE));
	}
	
	protected IDataModel createDataModel() {
		return DataModelFactory.createDataModel(new EARFacetProjectCreationDataModelProvider());
	}

	public void setRuntimeInDataModel(IRuntime runtime){
		model.setProperty(FACET_RUNTIME, runtime);
	}
	
	protected ImageDescriptor getDefaultPageImageDescriptor() {
		final Bundle bundle = Platform.getBundle("org.eclipse.jst.j2ee.ui");
		final URL url = bundle.getEntry("icons/ear-wiz-banner.gif");

		return ImageDescriptor.createFromURL(url);
	}

	protected IFacetedProjectTemplate getTemplate() {
		return ProjectFacetsManager.getTemplate("template.jst.ear");
	}

	protected IWizardPage createFirstPage() {
		return new EarProjectFirstPage(model, "first.page"); //$NON-NLS-1$ 
	}
	
	protected String getFinalPerspectiveID() {
        return ProductManager.getProperty(IProductConstants.FINAL_PERSPECTIVE_EAR);
	}

}
