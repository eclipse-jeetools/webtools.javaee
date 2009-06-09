package org.eclipse.jst.jee.project.facet;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebFactory;
import org.eclipse.jst.javaee.web.WelcomeFileList;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class WebCreateDeploymentFilesOperation extends
		CreateDeploymentFilesDataModelOperation {


	public WebCreateDeploymentFilesOperation(IDataModel model) {
		super(model);
	}
	
	@Override
	protected void createDeploymentFiles(IProject project, IProgressMonitor monitor) {
		final IModelProvider provider = ModelProviderManager.getModelProvider(project);

			provider.modify(new Runnable(){
				public void run() {
					WebApp webApp = (WebApp) provider.getModelObject();
					
					// welcome file list
					List<String> welcomeFiles = Arrays.asList(
							"index.html", //$NON-NLS-1$
							"index.htm", //$NON-NLS-1$
							"index.jsp", //$NON-NLS-1$
							"default.html", //$NON-NLS-1$
							"default.htm", //$NON-NLS-1$
							"default.jsp" //$NON-NLS-1$
					);
					
					// Add the welcome-file-list tag
					WelcomeFileList welcomeFileList = WebFactory.eINSTANCE.createWelcomeFileList();
					welcomeFileList.getWelcomeFiles().addAll(welcomeFiles); 
					webApp.getWelcomeFileLists().add(welcomeFileList);
				}
			}, IModelProvider.FORCESAVE);
				
	}

}
