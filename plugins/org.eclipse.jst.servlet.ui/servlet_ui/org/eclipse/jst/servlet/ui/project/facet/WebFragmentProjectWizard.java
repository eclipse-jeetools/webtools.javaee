/*******************************************************************************

 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.servlet.ui.project.facet;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.j2ee.web.project.facet.WebFragmentFacetProjectCreationDataModelProvider;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectTemplate;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.web.ui.internal.wizards.NewProjectDataModelFacetWizard;
import org.osgi.framework.Bundle;


public class WebFragmentProjectWizard extends NewProjectDataModelFacetWizard {

	public WebFragmentProjectWizard(IDataModel modelid) {
		super(modelid);
		setWindowTitle(WEBUIMessages.WEBFRAGMENT_MODULE_WIZ_TITLE);
	}
	
	public WebFragmentProjectWizard(){
		super();
		setWindowTitle(WEBUIMessages.WEBFRAGMENT_MODULE_WIZ_TITLE);
	}

	@Override
	protected IDataModel createDataModel() {
		return DataModelFactory.createDataModel(new WebFragmentFacetProjectCreationDataModelProvider());
	}

	@Override
	protected ImageDescriptor getDefaultPageImageDescriptor() {
		final Bundle bundle = Platform.getBundle("org.eclipse.jst.servlet.ui"); //$NON-NLS-1$
		final URL url = bundle.getEntry("icons/full/ctool16/webfragment_wizban.gif"); //$NON-NLS-1$
		return ImageDescriptor.createFromURL(url);
	}

	@Override
	protected IFacetedProjectTemplate getTemplate() {
		return ProjectFacetsManager.getTemplate("template.jst.webfragment"); //$NON-NLS-1$
	}

	@Override
	protected IWizardPage createFirstPage() {
		return new WebFragmentProjectFirstPage(model, "first.page"); //$NON-NLS-1$ 
	}

}
