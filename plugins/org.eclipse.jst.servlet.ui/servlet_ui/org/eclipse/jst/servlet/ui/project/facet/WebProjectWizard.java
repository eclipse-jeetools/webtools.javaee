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
package org.eclipse.jst.servlet.ui.project.facet;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.wizard.J2EEFacetWizard;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectTemplate;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.osgi.framework.Bundle;

public class WebProjectWizard extends J2EEFacetWizard {

	public WebProjectWizard(IDataModel model) {
		super(model);
	}
	
	public WebProjectWizard() {
		super();
	}
	
	protected IDataModel createDataModel() {
		return DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
	}

	protected IFacetedProjectTemplate getTemplate() {
		return ProjectFacetsManager.getTemplate("template.jst.web"); //$NON-NLS-1$
	}

	protected IWizardPage createFirstPage() {
		return new WebProjectFirstPage(model, "first.page"); //$NON-NLS-1$
	}
	
	protected ImageDescriptor getDefaultPageImageDescriptor() {
		final Bundle bundle = Platform.getBundle("org.eclipse.jst.servlet.ui"); //$NON-NLS-1$
		final URL url = bundle.getEntry("icons/full/ctool16/web-wiz-banner.gif"); //$NON-NLS-1$
		return ImageDescriptor.createFromURL(url);
	}
}
