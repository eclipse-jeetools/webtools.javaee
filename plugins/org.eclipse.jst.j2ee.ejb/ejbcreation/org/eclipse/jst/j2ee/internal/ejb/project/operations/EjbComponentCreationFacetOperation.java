package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJavaComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.J2EEComponentCreationFacetOperation;
import org.eclipse.wst.common.componentcore.datamodel.FacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IComponentCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EjbComponentCreationFacetOperation extends J2EEComponentCreationFacetOperation {

	
	public EjbComponentCreationFacetOperation(IDataModel model) {
		super(model);
	}
	
	
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		IDataModel dm = DataModelFactory.createDataModel(new FacetProjectCreationDataModelProvider());
		String projectName = model.getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME);
		dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projectName);
		List facetDMs = new ArrayList();
		facetDMs.add(setupJavaInstallAction());
		facetDMs.add(setupEjbInstallAction());
		dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_DM_LIST, facetDMs);
		IStatus stat =  dm.getDefaultOperation().execute(monitor, info);

		if( stat.isOK()){
			String earProjectName = (String) model.getProperty(IJ2EEComponentCreationDataModelProperties.EAR_COMPONENT_NAME);
			IProject earProject = ProjectUtilities.getProject( earProjectName );
			if (earProject != null && earProject.exists())
				stat = addtoEar(projectName, earProjectName);
		}

		return stat;
	}

	protected IDataModel setupEjbInstallAction() {
		String versionStr = model.getPropertyDescriptor(IJ2EEComponentCreationDataModelProperties.COMPONENT_VERSION).getPropertyDescription();
		IDataModel ejbFacetInstallDataModel = DataModelFactory.createDataModel(new EjbFacetInstallDataModelProvider());
		ejbFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, model.getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME));
		ejbFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, versionStr);
		ejbFacetInstallDataModel.setProperty(IEjbFacetInstallDataModelProperties.CONFIG_FOLDER,
		model.getStringProperty(IJavaComponentCreationDataModelProperties.JAVASOURCE_FOLDER));
		if (model.getBooleanProperty(IJ2EEComponentCreationDataModelProperties.ADD_TO_EAR))
			ejbFacetInstallDataModel.setProperty(IEjbFacetInstallDataModelProperties.EAR_PROJECT_NAME, model.getProperty(IJ2EEComponentCreationDataModelProperties.EAR_COMPONENT_NAME));

		return ejbFacetInstallDataModel;
	}
}


