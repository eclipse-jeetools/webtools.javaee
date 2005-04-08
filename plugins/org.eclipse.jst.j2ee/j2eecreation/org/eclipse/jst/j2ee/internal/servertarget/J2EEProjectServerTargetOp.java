package org.eclipse.jst.j2ee.internal.servertarget;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wst.server.core.internal.ResourceManager;

public class J2EEProjectServerTargetOp extends AbstractDataModelOperation {

    /**
     * @param operationDataModel
     */
    public J2EEProjectServerTargetOp(IDataModel model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        String serverTargetId = model.getStringProperty(IJ2EEProjectServerTargetDataModelProperties.RUNTIME_TARGET_ID);
		IRuntime runtime = ResourceManager.getInstance().getRuntime(serverTargetId);
		IProject project = null;
        String name = model.getStringProperty(IJ2EEProjectServerTargetDataModelProperties.PROJECT_NAME);
        if (name != null && name.length() > 0)
			project = ResourcesPlugin.getWorkspace().getRoot().getProject(name);
		if (runtime != null && project != null) {
			try {
				ServerCore.getProjectProperties(project).setRuntimeTarget(runtime, monitor);
				if (model.getBooleanProperty(IJ2EEProjectServerTargetDataModelProperties.UPDATE_MODULES) 
						&& project.hasNature(IModuleConstants.MODULE_NATURE_ID)) {
					ServerTargetHelper.setNewServerTargetForEARModules(runtime, project);
					ServerTargetHelper.setNewServerTargetForEARUtilityJars(runtime, project);
				}
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

}
