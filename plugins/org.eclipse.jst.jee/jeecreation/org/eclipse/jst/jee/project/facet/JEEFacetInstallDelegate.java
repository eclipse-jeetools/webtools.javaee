/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.project.facet;

import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.IAddComponentToEnterpriseApplicationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.J2EEFacetInstallDelegate;
import org.eclipse.jst.jee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public abstract class JEEFacetInstallDelegate extends J2EEFacetInstallDelegate {

	//TODO Overriding to add to component, but not add add to the DD yet (until we have model support)
    protected void addToEar(IVirtualComponent earComp, IVirtualComponent j2eeComp, String moduleURI ){
		final IDataModel dataModel = DataModelFactory.createDataModel(new AddComponentToEnterpriseApplicationDataModelProvider());
		Map map = (Map)dataModel.getProperty(IAddComponentToEnterpriseApplicationDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP);
		map.put(j2eeComp, moduleURI);
		
		dataModel.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComp);
			
		List modList = (List) dataModel.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
		modList.add(j2eeComp);
		dataModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, modList);
		try {
			dataModel.getDefaultOperation().execute(null, null);
		} catch (ExecutionException e) {
			Logger.getLogger().logError(e);
		}
    }
    
    protected void installAndAddModuletoEAR( String j2eeVersionText,
    			String				earProjectName,
    			IRuntime			runtime,
    			IProject			moduleProject,
    			String				moduleURI,
    			IProgressMonitor	monitor ){
    	
		installEARFacet(j2eeVersionText,
					earProjectName,
					runtime,
					monitor);
		
		final IVirtualComponent c = ComponentCore.createComponent( moduleProject );
		final IProject earProject = ResourcesPlugin.getWorkspace().getRoot().getProject(earProjectName);
		final IVirtualComponent earComp = ComponentCore.createComponent( earProject );
		addToEar( earComp, c, moduleURI );
    }
  
}
