/******************************************************************************
 * Copyright (c) 2009 Red Hat, IBM
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Rob Stryker - initial implementation and ongoing maintenance
 *    Chuck Bridgham - additional support
 ******************************************************************************/
package org.eclipse.jst.j2ee.internal.ui;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.j2ee.internal.componentcore.JavaEEModuleHandler;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.internal.IModuleHandler;
import org.eclipse.wst.common.componentcore.internal.impl.TaskModel;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.componentcore.ui.propertypage.AddModuleDependenciesPropertiesPage;
import org.eclipse.wst.common.componentcore.ui.propertypage.IReferenceWizardConstants;
import org.eclipse.wst.common.componentcore.ui.propertypage.ModuleAssemblyRootPage;
import org.eclipse.wst.common.componentcore.ui.propertypage.IReferenceWizardConstants.ProjectConverterOperationProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class J2EEModuleDependenciesPropertyPage extends
		AddModuleDependenciesPropertiesPage {

	public J2EEModuleDependenciesPropertyPage(IProject project,
			ModuleAssemblyRootPage page) {
		super(project, page);
	}
	
	@Override
	public boolean postHandleChanges(IProgressMonitor monitor) {
		return true;
	}
	
	@Override
	protected void handleRemoved(ArrayList<IVirtualReference> removed) {
		super.handleRemoved(removed);
		J2EEComponentClasspathUpdater.getInstance().queueUpdateEAR(rootComponent.getProject());
	}
	
	@Override
	protected String getModuleAssemblyRootPageDescription() {
		if (JavaEEProjectUtilities.isEJBProject(project))
			return Messages.J2EEModuleDependenciesPropertyPage_3;
		if (JavaEEProjectUtilities.isApplicationClientProject(project))
			return Messages.J2EEModuleDependenciesPropertyPage_4;
		if (JavaEEProjectUtilities.isJCAProject(project))
			return Messages.J2EEModuleDependenciesPropertyPage_5;
		return super.getModuleAssemblyRootPageDescription();
	}

	@Override
	protected IModuleHandler getModuleHandler() {
		if(moduleHandler == null)
			moduleHandler = new JavaEEModuleHandler();
		return moduleHandler;
	}
	
	@Override
	protected void setCustomReferenceWizardProperties(TaskModel model) {
		model.putObject(IReferenceWizardConstants.PROJECT_CONVERTER_OPERATION_PROVIDER, getConverterProvider());
	}
	
	public ProjectConverterOperationProvider getConverterProvider() {
		return new ProjectConverterOperationProvider() {
			public IDataModelOperation getConversionOperation(IProject project) {
				return J2EEProjectUtilities.createFlexJavaProjectForProjectOperation(project);
			}
		};
	}

//	
//	@Override
//	protected IDataModelProvider getAddReferenceDataModelProvider(IVirtualComponent component) {
//		return new AddComponentToEnterpriseApplicationDataModelProvider();
//	}
//	
//	protected void addToManifest(ArrayList<IVirtualComponent> components) {
//		StringBuffer newComps = getCompsForManifest(components);
//		if(newComps.toString().length() > 0) {
//			UpdateManifestOperation op = createManifestOperation(newComps.toString());
//			try {
//				op.run(new NullProgressMonitor());
//			} catch (InvocationTargetException e) {
//				J2EEUIPlugin.logError(e);
//			} catch (InterruptedException e) {
//				J2EEUIPlugin.logError(e);
//			}	
//		}
//	}
//
//	protected void addOneComponent(IVirtualComponent component, IPath path, String archiveName) throws CoreException {
//		//Find the Ear's that contain this component
//		IProject[] earProjects = EarUtilities.getReferencingEARProjects(rootComponent.getProject());
//		for (int i = 0; i < earProjects.length; i++) {
//			IProject project = earProjects[i];
//			
//			IDataModelProvider provider = getAddReferenceDataModelProvider(component);
//			IDataModel dm = DataModelFactory.createDataModel(provider);
//			
//			dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, ComponentCore.createComponent(project));
//			dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, Arrays.asList(component));
//			
//			//[Bug 238264] the uri map needs to be manually set correctly
//			Map<IVirtualComponent, String> uriMap = new HashMap<IVirtualComponent, String>();
//			uriMap.put(component, archiveName);
//			dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP, uriMap);
//	        dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH, path);
//	
//			IStatus stat = dm.validateProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
//			if (stat != OK_STATUS)
//				throw new CoreException(stat);
//			try {
//				dm.getDefaultOperation().execute(new NullProgressMonitor(), null);
//			} catch (ExecutionException e) {
//				ModuleCoreUIPlugin.logError(e);
//			}	
//		}
//	}
//	
//
//	protected StringBuffer getCompsForManifest(ArrayList<IVirtualComponent> components) {
//		StringBuffer newComps = new StringBuffer();
//		for (Iterator iterator = components.iterator(); iterator.hasNext();) {
//			IVirtualComponent comp = (IVirtualComponent) iterator.next();
//			String archiveName = new Path(derivedRefsObjectToRuntimePath.get(comp)).lastSegment();
//			newComps.append(archiveName);
//			newComps.append(' ');
//		}
//		return newComps;
//	}
//	
//	protected UpdateManifestOperation createManifestOperation(String newComps) {
//		return new UpdateManifestOperation(project.getName(), newComps, false);
//	}
//
//	private void removeFromManifest(ArrayList<IVirtualComponent> removed) {
//		String sourceProjName = project.getName();
//		IProgressMonitor monitor = new NullProgressMonitor();
//		IFile manifestmf = J2EEProjectUtilities.getManifestFile(project);
//		ArchiveManifest mf = J2EEProjectUtilities.readManifest(project);
//		if (mf == null)
//			return;
//		IDataModel updateManifestDataModel = DataModelFactory.createDataModel(new UpdateManifestDataModelProvider());
//		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.PROJECT_NAME, sourceProjName);
//		updateManifestDataModel.setBooleanProperty(UpdateManifestDataModelProperties.MERGE, false);
//		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.MANIFEST_FILE, manifestmf);
//		String[] cp = mf.getClassPathTokenized();
//		List cpList = new ArrayList();
//		
//		for (int i = 0; i < cp.length; i++) {
//			boolean foundMatch = false;
//			for (Iterator iterator = removed.iterator(); iterator.hasNext();) {
//				IVirtualComponent comp = (IVirtualComponent) iterator.next();
//				String cpToRemove = new Path(derivedRefsOldComponentToRuntimePath.get(comp)).lastSegment();
//				if (cp[i].equals(cpToRemove))
//					foundMatch = true;
//			}
//			if (!foundMatch)
//				cpList.add(cp[i]);
//		}
//		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.JAR_LIST, cpList);
//		try {
//			updateManifestDataModel.getDefaultOperation().execute(monitor, null );
//		} catch (ExecutionException e) {
//			J2EEUIPlugin.logError(e);
//		}
//	}

}
