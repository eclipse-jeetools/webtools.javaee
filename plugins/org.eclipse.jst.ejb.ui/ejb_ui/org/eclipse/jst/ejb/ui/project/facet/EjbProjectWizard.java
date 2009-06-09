/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.project.facet;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectTemplate;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.project.facet.IProductConstants;
import org.eclipse.wst.project.facet.ProductManager;
import org.eclipse.wst.web.ui.internal.wizards.NewProjectDataModelFacetWizard;
import org.osgi.framework.Bundle;

public class EjbProjectWizard extends NewProjectDataModelFacetWizard {

	public EjbProjectWizard(IDataModel model){
		super(model);
		setWindowTitle(EJBUIMessages.KEY_1);
	}
	
	public EjbProjectWizard(){
		super();
		setWindowTitle(EJBUIMessages.KEY_1);
	}
	
	@Override
	protected IDataModel createDataModel() {
		return DataModelFactory.createDataModel(new EjbFacetProjectCreationDataModelProvider());
	}

	@Override
	protected IFacetedProjectTemplate getTemplate() {
		return ProjectFacetsManager.getTemplate("template.jst.ejb"); //$NON-NLS-1$
	}

	@Override
	protected IWizardPage createFirstPage() {
		return new EjbProjectFirstPage(model, "first.page"); //$NON-NLS-1$
	}
	
	@Override
	protected ImageDescriptor getDefaultPageImageDescriptor() {
		final Bundle bundle = Platform.getBundle("org.eclipse.jst.ejb.ui"); //$NON-NLS-1$
		final URL url = bundle.getEntry("icons/full/wizban/ejbproject_wiz.gif"); //$NON-NLS-1$
		return ImageDescriptor.createFromURL(url);
	}
	
	@Override
	protected String getFinalPerspectiveID() {
        return ProductManager.getProperty(IProductConstants.FINAL_PERSPECTIVE_EJB);
	}
	
}