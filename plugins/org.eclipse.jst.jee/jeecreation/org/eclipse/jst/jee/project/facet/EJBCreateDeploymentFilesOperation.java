package org.eclipse.jst.jee.project.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EJBCreateDeploymentFilesOperation extends
		CreateDeploymentFilesDataModelOperation {


	public EJBCreateDeploymentFilesOperation(IDataModel model) {
		super(model);
	}
	
	protected void createDeploymentFiles(IProject project, IProgressMonitor monitor) {
		final IVirtualComponent component = ComponentCore.createComponent(project);
		final IModelProvider provider = ModelProviderManager.getModelProvider(project);
		provider.modify(new Runnable(){
			public void run() {
			}
		}, IModelProvider.FORCESAVE);
	}

}
