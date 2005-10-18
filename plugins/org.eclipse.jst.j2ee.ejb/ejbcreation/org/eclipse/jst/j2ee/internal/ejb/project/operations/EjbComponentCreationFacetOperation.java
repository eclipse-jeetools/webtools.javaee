package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.FacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IFacetDataModelPropeties;
import org.eclipse.jst.j2ee.project.facet.IFacetProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;
import org.eclipse.wst.common.project.facet.core.runtime.RuntimeManager;

public class EjbComponentCreationFacetOperation extends AbstractDataModelOperation {

	
	public EjbComponentCreationFacetOperation(IDataModel model) {
		super(model);
	}
	
	
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		IDataModel dm = DataModelFactory.createDataModel(new FacetProjectCreationDataModelProvider());
		String projectName = model.getStringProperty(IJ2EEComponentCreationDataModelProperties.PROJECT_NAME);
		dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projectName);
		List facetDMs = new ArrayList();
		facetDMs.add(setupJavaInstallAction());
		facetDMs.add(setupEjbInstallAction());
		dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_DM_LIST, facetDMs);
		return dm.getDefaultOperation().execute(monitor, info);
	}

	protected IDataModel setupEjbInstallAction() {
		String versionStr = model.getPropertyDescriptor(IJ2EEComponentCreationDataModelProperties.COMPONENT_VERSION).getPropertyDescription();
		IDataModel ejbFacetInstallDataModel = DataModelFactory.createDataModel(new EjbFacetInstallDataModelProvider());
		ejbFacetInstallDataModel.setProperty(IEjbFacetInstallDataModelProperties.FACET_PROJECT_NAME, model.getStringProperty(IJ2EEComponentCreationDataModelProperties.PROJECT_NAME));
		ejbFacetInstallDataModel.setProperty(IEjbFacetInstallDataModelProperties.FACET_VERSION_STR, versionStr);
		ejbFacetInstallDataModel.setProperty(IEjbFacetInstallDataModelProperties.CONFIG_FOLDER,
				model.getStringProperty(IJ2EEComponentCreationDataModelProperties.JAVASOURCE_FOLDER));

		return ejbFacetInstallDataModel;
	}

	protected IDataModel setupJavaInstallAction() {
		IDataModel dm = DataModelFactory.createDataModel(new JavaFacetInstallDataModelProvider());
		dm.setProperty(IFacetDataModelPropeties.FACET_PROJECT_NAME, model.getStringProperty(IJ2EEComponentCreationDataModelProperties.PROJECT_NAME));
		dm.setProperty(IEjbFacetInstallDataModelProperties.FACET_VERSION_STR, "1.4");
		dm.setProperty(JavaFacetInstallDataModelProvider.SOURC_FOLDER_NAME, model.getStringProperty(IJ2EEComponentCreationDataModelProperties.JAVASOURCE_FOLDER));
		return dm;
	}

	protected void setRuntime(IFacetedProject facetProj) throws CoreException {
		String runtimeID = model.getStringProperty(IJ2EEComponentCreationDataModelProperties.RUNTIME_TARGET_ID);
		try {
			IRuntime runtime = RuntimeManager.getRuntime(runtimeID);
			facetProj.setRuntime(runtime, null);
		} catch (IllegalArgumentException e) {
			Logger.getLogger().logError(e);
		}
	}	
}


