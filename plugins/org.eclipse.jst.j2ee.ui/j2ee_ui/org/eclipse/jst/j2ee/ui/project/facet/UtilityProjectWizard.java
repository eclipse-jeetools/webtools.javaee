/******************************************************************************
 * Copyright (c) 2005, 2019 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.j2ee.ui.project.facet;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.project.facet.UtilityProjectCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectTemplate;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.project.facet.IProductConstants;
import org.eclipse.wst.project.facet.ProductManager;
import org.eclipse.wst.web.ui.internal.wizards.NewProjectDataModelFacetWizard;
import org.osgi.framework.Bundle;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public class UtilityProjectWizard extends NewProjectDataModelFacetWizard {

	public UtilityProjectWizard(IDataModel model) {
		super(model);
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.JAVAUTIL_COMPONENT_WIZ_TITLE));
	}
	
	public UtilityProjectWizard(){
		super();
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.JAVAUTIL_COMPONENT_WIZ_TITLE));
	}

	@Override
	protected IDataModel createDataModel() {
		return DataModelFactory.createDataModel(new UtilityProjectCreationDataModelProvider());
	}

	@Override
	protected ImageDescriptor getDefaultPageImageDescriptor() {
		final Bundle bundle = Platform.getBundle("org.eclipse.jst.j2ee.ui"); //$NON-NLS-1$
		final URL url = bundle.getEntry("icons/util-wiz-banner.gif"); //$NON-NLS-1$
		return ImageDescriptor.createFromURL(url);
	}

	@Override
	protected IFacetedProjectTemplate getTemplate() {
		return ProjectFacetsManager.getTemplate("template.jst.utility"); //$NON-NLS-1$
	}

	@Override
	protected IWizardPage createFirstPage() {
		return new UtilityProjectFirstPage(model, "first.page"); //$NON-NLS-1$ 
	}
	
	@Override
	protected String getFinalPerspectiveID() {
        return ProductManager.getProperty(IProductConstants.FINAL_PERSPECTIVE_UTILITY);
	}

}
