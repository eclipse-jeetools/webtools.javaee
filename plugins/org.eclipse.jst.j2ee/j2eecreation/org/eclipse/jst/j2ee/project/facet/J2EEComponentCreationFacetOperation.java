package org.eclipse.jst.j2ee.project.facet;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.datamodel.properties.IJavaComponentCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IComponentCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;
import org.eclipse.wst.common.project.facet.core.runtime.RuntimeManager;

public class J2EEComponentCreationFacetOperation extends AbstractDataModelOperation {

	
	public J2EEComponentCreationFacetOperation(IDataModel model) {
		super(model);
	}
	
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected IDataModel setupJavaInstallAction() {
		IDataModel dm = DataModelFactory.createDataModel(new JavaFacetInstallDataModelProvider());
		dm.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, model.getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME));
		dm.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, "1.4"); //$NON-NLS-1$
		dm.setProperty(IJavaFacetInstallDataModelProperties.SOURC_FOLDER_NAME, model.getStringProperty(IJavaComponentCreationDataModelProperties.JAVASOURCE_FOLDER));
		return dm;
	}
	
	protected IStatus addtoEar(String projectName, String earProjectName){
		
		IStatus stat = OK_STATUS;
		IProject moduleProject = ProjectUtilities.getProject( projectName );
		IProject earProject = ProjectUtilities.getProject( earProjectName );

		IVirtualComponent comp = ComponentCore.createComponent( moduleProject );
		IVirtualComponent earComp = ComponentCore.createComponent( earProject );
		if( comp != null && comp.exists() && earComp != null && earComp.exists()){
			
			IDataModel dataModel = DataModelFactory.createDataModel( new AddComponentToEnterpriseApplicationDataModelProvider());
			dataModel.setProperty( ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComp );
	        List modList = (List) dataModel.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
	        modList.add(comp);
	        dataModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, modList);
	        try {
				stat = dataModel.getDefaultOperation().execute(null, null);
			} catch (ExecutionException e) {
				Logger.getLogger().logError(e);
			}
		}	
		return stat;
	}
	
	protected void setRuntime(IFacetedProject facetProj) throws CoreException {
		String runtimeID = model.getStringProperty(IJavaComponentCreationDataModelProperties.RUNTIME_TARGET_ID);
		try {
			IRuntime runtime = RuntimeManager.getRuntime(runtimeID);
			facetProj.setRuntime(runtime, null);
		} catch (IllegalArgumentException e) {
			Logger.getLogger().logError(e);
		}
	}		
}
