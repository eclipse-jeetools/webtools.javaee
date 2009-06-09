package org.eclipse.jst.jee.project.facet;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateDeploymentFilesDataModelOperation extends
		AbstractDataModelOperation {

	public CreateDeploymentFilesDataModelOperation(IDataModel model) {
		super(model);
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		IProject targetProject = (IProject) model.getProperty(ICreateDeploymentFilesDataModelProperties.TARGET_PROJECT);
		createDeploymentFiles(targetProject, monitor);
		return OK_STATUS;
	}

	protected void createDeploymentFiles(IProject project, IProgressMonitor monitor) {
		// do nothing
	}

}
