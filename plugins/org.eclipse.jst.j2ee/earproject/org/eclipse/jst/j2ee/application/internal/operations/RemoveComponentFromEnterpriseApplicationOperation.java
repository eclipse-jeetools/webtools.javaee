/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.j2ee.model.IEARModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.jee.application.ICommonApplication;
import org.eclipse.jst.jee.application.ICommonModule;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.RemoveReferenceComponentOperation;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class RemoveComponentFromEnterpriseApplicationOperation extends RemoveReferenceComponentOperation {


	public RemoveComponentFromEnterpriseApplicationOperation(IDataModel model) {
		super(model);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		try {
			J2EEComponentClasspathUpdater.getInstance().pauseUpdates();
			updateEARDD(monitor);
			super.execute(monitor, info);
			return OK_STATUS;
		} finally {
			J2EEComponentClasspathUpdater.getInstance().resumeUpdates();
		}
	}

	protected void updateEARDD(final IProgressMonitor monitor) {
		final IVirtualComponent comp = (IVirtualComponent) model.getProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT);
		if (!comp.getProject().isAccessible())
			return;
		J2EEComponentClasspathUpdater.getInstance().queueUpdateEAR(comp.getProject());
		final IEARModelProvider earModel = (IEARModelProvider)ModelProviderManager.getModelProvider(comp.getProject());
		earModel.modify(new Runnable() {
			public void run() {
				ICommonApplication application = (ICommonApplication)earModel.getModelObject();
				if (application == null)
					return;
				List list = (List) model.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						IVirtualComponent wc = (IVirtualComponent) list.get(i);
						IVirtualComponent moduleComponent = wc.getComponent();
						IProject earProject = comp.getProject();
						IProject componentProject = moduleComponent.getProject();
						if (earProject.equals(componentProject))
							continue;
						if(!moduleComponent.isBinary()){
							J2EEComponentClasspathUpdater.getInstance().queueUpdateModule(moduleComponent.getProject());
						}
						String moduleURI = getModuleURI(earModel, wc);
						removeModule(application, moduleURI); 
						IVirtualFile vFile = comp.getRootFolder().getFile(moduleURI);
						IFile iFile = vFile.getUnderlyingFile();
						if(iFile.exists()){
							try {
								iFile.delete(true, monitor);
							} catch (CoreException e) {
								e.printStackTrace();
							}
						}
					}
				}
			
			}
		}, null);
	}
	
	protected String getModuleURI(final IEARModelProvider earModule, final IVirtualComponent targetComponent) {
		return earModule.getModuleURI(targetComponent);
	}

	protected void removeModule(ICommonApplication application, String moduleURI) {
		ICommonModule module = application.getFirstEARModule(moduleURI);
		application.getEARModules().remove(module);
	}

}
