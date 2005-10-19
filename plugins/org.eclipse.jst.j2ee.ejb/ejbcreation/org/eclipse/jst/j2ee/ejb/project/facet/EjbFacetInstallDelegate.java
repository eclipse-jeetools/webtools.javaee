package org.eclipse.jst.j2ee.ejb.project.facet;



import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;


public class EjbFacetInstallDelegate implements IDelegate{

	public void execute(IProject project,
			IProjectFacetVersion fv, Object config,
			IProgressMonitor monitor) throws CoreException {

    	IDataModel model = (IDataModel)config;
    	try {
			model.getDefaultOperation().execute(monitor, null);
		} catch (ExecutionException e) {
			Logger.getLogger().logError(e);
		}
	}

}
