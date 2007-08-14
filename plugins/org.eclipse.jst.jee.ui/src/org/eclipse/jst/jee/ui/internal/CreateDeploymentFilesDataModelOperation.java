package org.eclipse.jst.jee.ui.internal;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.javaee.applicationclient.ApplicationClient;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebFactory;
import org.eclipse.jst.javaee.web.WelcomeFileList;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateDeploymentFilesDataModelOperation extends
		AbstractDataModelOperation {

	public CreateDeploymentFilesDataModelOperation(IDataModel model) {
		super(model);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		IProject targetProject = (IProject) model.getProperty(ICreateDeploymentFilesDataModelProperties.TARGET_PROJECT);
		if(J2EEProjectUtilities.isJEEProject(targetProject)){
			createDeploymentFiles(targetProject, monitor);
		}
		return OK_STATUS;
	}

	private void createDeploymentFiles(IProject project, IProgressMonitor monitor) {
		final IVirtualComponent component = ComponentCore.createComponent(project);
		final IModelProvider provider = ModelProviderManager.getModelProvider(project);
		if(J2EEProjectUtilities.isEARProject(project)){
			provider.modify(new Runnable(){
				public void run() {
					Application application = (Application) provider.getModelObject();
					
					// Add the display-name tag
					DisplayName displayName = (DisplayName) JavaeeFactory.eINSTANCE.createDisplayName();
					displayName.setValue(component.getProject().getName());
					application.getDisplayNames().add(displayName);
				}
			}, null);
			IVirtualReference[] componentReferences = J2EEProjectUtilities.getJ2EEModuleReferences(component);
			if(componentReferences != null && componentReferences.length > 0){					
				final IDataModel dataModel = DataModelFactory.createDataModel(new AddComponentToEnterpriseApplicationDataModelProvider());
				dataModel.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, component);
				List modList = (List) dataModel.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
				for(int i = 0; i < componentReferences.length; i++) {
					IVirtualComponent referencedComponent = componentReferences[i].getReferencedComponent();
					modList.add(referencedComponent);
				}
				dataModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, modList);
				try {
					dataModel.getDefaultOperation().execute(monitor, null);
				} catch (ExecutionException e) {
					Logger.getLogger().logError(e);
				}
			}	
		} else if(J2EEProjectUtilities.isDynamicWebProject(project)){
			provider.modify(new Runnable(){
				public void run() {
					WebApp webApp = (WebApp) provider.getModelObject();
					
					// Add the display-name tag
					DisplayName displayName = (DisplayName) JavaeeFactory.eINSTANCE.createDisplayName();
					displayName.setValue(component.getProject().getName());
					webApp.getDisplayNames().add(displayName);
					
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
					WelcomeFileList welcomeFileList = (WelcomeFileList) WebFactory.eINSTANCE.createWelcomeFileList();
					welcomeFileList.getWelcomeFiles().addAll(welcomeFiles); 
					webApp.getWelcomeFileLists().add(welcomeFileList);
				}
			}, null);
		} else if(J2EEProjectUtilities.isEJBProject(project)){
			provider.modify(new Runnable(){
				public void run() {
					try {
						EJBJar ejbJar = (EJBJar) provider.getModelObject();
						
						// Add the display-name tag
						DisplayName displayName = (DisplayName) JavaeeFactory.eINSTANCE.createDisplayName();
						displayName.setValue(component.getProject().getName());
						ejbJar.getDisplayNames().add(displayName);
					} catch (NullPointerException e) {
						Logger.getLogger().logError(e);
					}
				}
			}, null);
		} else if(J2EEProjectUtilities.isApplicationClientProject(project)){
			provider.modify(new Runnable(){
				public void run() {
					ApplicationClient applicationClient = (ApplicationClient) provider.getModelObject();
					
					// Add the display-name tag
					DisplayName displayName = (DisplayName) JavaeeFactory.eINSTANCE.createDisplayName();
					displayName.setValue(component.getProject().getName());
					applicationClient.getDisplayNames().add(displayName);
				}
			}, null);
		}
	}

}
