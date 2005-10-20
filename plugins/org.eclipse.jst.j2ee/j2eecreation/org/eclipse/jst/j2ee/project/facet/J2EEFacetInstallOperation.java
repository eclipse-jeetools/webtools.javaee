package org.eclipse.jst.j2ee.project.facet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.earcreation.EarFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.ManifestFileCreationAction;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.IFacetedProject.Action.Type;

public class J2EEFacetInstallOperation extends AbstractDataModelOperation {
	
	public J2EEFacetInstallOperation(IDataModel model) {
		super(model);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void installEARFacet(String j2eeVersionText, String earProjectName, IProgressMonitor monitor){

		IProject project = ProjectUtilities.getProject(earProjectName); 
		if( project.exists())
			return;
		
		IFacetedProject facetProj;
		try {
			facetProj = ProjectFacetsManager.create(earProjectName,
					null, monitor);
		
			IDataModel earFacetInstallDataModel = DataModelFactory.createDataModel(new EarFacetInstallDataModelProvider());
			earFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, earProjectName);
			earFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, j2eeVersionText);
			
			Set actions = new HashSet();
			actions.add(new IFacetedProject.Action((Type) earFacetInstallDataModel.getProperty(IFacetDataModelProperties.FACET_TYPE),
				(IProjectFacetVersion) earFacetInstallDataModel.getProperty(IFacetDataModelProperties.FACET_VERSION),
				earFacetInstallDataModel));


			facetProj.modify(actions, null);
		} catch (CoreException e) {
			Logger.getLogger().logError(e);
		}		
	}
	
    protected void createManifest(IProject project, String configFolder, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
    	
        IContainer container = project.getFolder(configFolder);
        IFile file = container.getFile(new Path(J2EEConstants.MANIFEST_URI));

        try {
            ManifestFileCreationAction.createManifestFile(file, project);
        } catch (CoreException e) {
            Logger.getLogger().log(e);
        } catch (IOException e) {
            Logger.getLogger().log(e);
        }
    }		
}
