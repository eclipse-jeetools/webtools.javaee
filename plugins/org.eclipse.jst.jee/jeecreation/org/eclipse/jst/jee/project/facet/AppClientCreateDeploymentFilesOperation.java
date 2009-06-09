package org.eclipse.jst.jee.project.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AppClientCreateDeploymentFilesOperation extends
		CreateDeploymentFilesDataModelOperation {


	public AppClientCreateDeploymentFilesOperation(IDataModel model) {
		super(model);
	}
	
	@Override
	protected void createDeploymentFiles(IProject project, IProgressMonitor monitor) {
		final IModelProvider provider = ModelProviderManager.getModelProvider(project);
		provider.modify(new Runnable(){
			public void run() {
			}
		}, IModelProvider.FORCESAVE);
	}

}
