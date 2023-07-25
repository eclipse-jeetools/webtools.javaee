/*******************************************************************************
 * Copyright (c) 2007, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.project.facet;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.jee.JEEPlugin;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EarCreateDeploymentFilesOperation extends
		CreateDeploymentFilesDataModelOperation {


	public EarCreateDeploymentFilesOperation(IDataModel model) {
		super(model);
	}
	
	@Override
	protected void createDeploymentFiles(IProject project, final IProgressMonitor monitor) {
		final IVirtualComponent component = ComponentCore.createComponent(project);
		final IModelProvider provider = ModelProviderManager.getModelProvider(project);
			provider.modify(new Runnable(){
				@Override
				public void run() {
					IVirtualReference[] componentReferences = J2EEProjectUtilities.getJ2EEModuleReferences(component);
					if(componentReferences != null && componentReferences.length > 0){					
						final IDataModel dataModel = DataModelFactory.createDataModel(new AddComponentToEnterpriseApplicationDataModelProvider());
						dataModel.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, component);
						Map<IVirtualComponent, String> uriMap = new HashMap<IVirtualComponent, String>();
						for(int i = 0; i < componentReferences.length; i++) {
							IVirtualComponent referencedComponent = componentReferences[i].getReferencedComponent();
							if (componentReferences[i].getRuntimePath().toString().equals("/")){ //$NON-NLS-1$
								uriMap.put(referencedComponent, componentReferences[i].getArchiveName());
							} else {
								uriMap.put(referencedComponent, componentReferences[i].getRuntimePath().append((new Path(componentReferences[i].getArchiveName())).lastSegment()).toString());
							}
						}
						dataModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP, uriMap);
						try {
							dataModel.getDefaultOperation().execute(monitor, null);
						} catch (ExecutionException e) {
							JEEPlugin.logError(e);
						}
					}
				}
			}, IModelProvider.FORCESAVE);
				
		
	}

}
