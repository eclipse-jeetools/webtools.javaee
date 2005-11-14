package org.eclipse.jst.ejb.ui.project.facet;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectTemplate;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.web.ui.internal.wizards.NewProjectDataModelFacetWizard;
import org.osgi.framework.Bundle;

public class EjbProjectWizard extends NewProjectDataModelFacetWizard {

	public EjbProjectWizard(IDataModel model){
		super(model);
	}
	
	public EjbProjectWizard(){
		super();
	}
	
	protected IDataModel createDataModel() {
		return DataModelFactory.createDataModel(new EjbFacetProjectCreationDataModelProvider());
	}

	protected IFacetedProjectTemplate getTemplate() {
		return ProjectFacetsManager.getTemplate("template.jst.ejb"); //$NON-NLS-1$
	}

	protected IWizardPage createFirstPage() {
		return new EjbProjectFirstPage(model, "first.page"); //$NON-NLS-1$
	}
	
	protected ImageDescriptor getDefaultPageImageDescriptor() {
		final Bundle bundle = Platform.getBundle("org.eclipse.jst.ejb.ui"); //$NON-NLS-1$
		final URL url = bundle.getEntry("icons/full/ctool16/newejbprj_wiz.gif"); //$NON-NLS-1$
		return ImageDescriptor.createFromURL(url);
	}
	
}