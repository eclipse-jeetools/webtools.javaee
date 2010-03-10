/******************************************************************************
 * Copyright (c) 2009 Red Hat
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Rob Stryker - initial implementation and ongoing maintenance
 ******************************************************************************/
package org.eclipse.jst.servlet.ui.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.ui.J2EEModuleDependenciesPropertyPage;
import org.eclipse.jst.j2ee.internal.ui.preferences.Messages;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.ui.ModuleCoreUIPlugin;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.DependencyPageExtensionManager;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.DependencyPageExtensionManager.ReferenceExtension;
import org.eclipse.wst.common.componentcore.ui.propertypage.ModuleAssemblyRootPage;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

public class WebDependencyPropertyPage extends J2EEModuleDependenciesPropertyPage {

//	protected Button addWebLibRefButton;
//	private boolean addingWebLib = true;
	public WebDependencyPropertyPage(IProject project,
			ModuleAssemblyRootPage page) {
		super(project, page);
	}

	
	@Override
	protected void createPushButtons() {
		
		addMappingButton = createPushButton(getAddFolderLabel());
		addReferenceButton = createPushButton(getAddReferenceLabel());
		//addWebLibRefButton = createPushButton(getAddWebLibRefLabel());
		editReferenceButton = createPushButton(getEditReferenceLabel());
		removeButton = createPushButton(getRemoveSelectedLabel());
		
	}

	protected String getAddWebLibRefLabel() {
		
		return Messages.WebDependencyPropertyPage_0;
	}
	
	@Override
	protected String getModuleAssemblyRootPageDescription() {
		
		return Messages.WebDependencyPropertyPage_1;
	}


	@Override
	protected StringBuffer getCompsForManifest(ArrayList<IVirtualComponent> components) {
		StringBuffer newComps = new StringBuffer();
		for (Iterator iterator = components.iterator(); iterator.hasNext();) {
			IVirtualComponent comp = (IVirtualComponent) iterator.next();
			String runtimePath = objectToRuntimePath.get(comp);
			if (runtimePath == null)
				runtimePath = derivedRefsObjectToRuntimePath.get(comp);
			if (runtimePath.indexOf(J2EEConstants.WEB_INF_LIB) == -1) {
				String archiveName = new Path(runtimePath).lastSegment();
				newComps.append(archiveName);
				newComps.append(' ');
			}
		}
		return newComps;
	}

	@Override
	protected ReferenceExtension[] filterReferenceTypes(ReferenceExtension[] defaults) {
		// Replace the default one with our own custom one, in class CustomWebProjectReferenceWizardFragment
		for( int i = 0; i < defaults.length; i++ ) {
			if( defaults[i].getId().equals("org.eclipse.wst.common.componentcore.ui.newProjectReference")) { //$NON-NLS-1$
				defaults[i] = DependencyPageExtensionManager.getManager().findReferenceExtension("org.eclipse.jst.servlet.ui.internal.CustomWebProjectReferenceWizardFragment"); //$NON-NLS-1$
			}
		}
		return defaults;
	}
	protected IDataModelProvider getAddWebReferenceDataModelProvider(IVirtualComponent component) {
		return new CreateReferenceComponentsDataModelProvider();
	}


	@Override
	protected void insertIntoTable(boolean consumed, IVirtualComponent[] compArr, String[] pathArr) {
		
		for( int i = 0; i < compArr.length; i++ ) {
			if (pathArr[i].indexOf(J2EEConstants.WEB_INF_LIB) == -1)
				derivedRefsObjectToRuntimePath.put(compArr[i],getRuntimePath(compArr[i], pathArr[i]));
			else
				objectToRuntimePath.put(compArr[i],getRuntimePath(compArr[i], pathArr[i]));
			if( consumed ) 
				consumedReferences.add(compArr[i]);
		}
	}
	@Override
	protected void removeComponents(ArrayList<IVirtualComponent> removed) {
		Iterator<IVirtualComponent> i = removed.iterator();
		while(i.hasNext()) {
			removeOneComponent(i.next());
		}
	}


	@Override
	protected void addOneComponent(IVirtualComponent component) throws CoreException {
		
	
		String runtimePath = objectToRuntimePath.get(component);
		if (runtimePath == null)
			runtimePath = derivedRefsObjectToRuntimePath.get(component);
		if (runtimePath.indexOf(J2EEConstants.WEB_INF_LIB) != -1) {
			addWebRef(component);
			return;
		}


			String path, archiveName;
			path = new Path(derivedRefsObjectToRuntimePath.get(component)).removeLastSegments(1).toString();
			archiveName = new Path(derivedRefsObjectToRuntimePath.get(component)).lastSegment();

			
			//Find the Ear's that contain this component
			IProject[] earProjects = EarUtilities.getReferencingEARProjects(rootComponent.getProject());
			for (int i = 0; i < earProjects.length; i++) {
				IProject project = earProjects[i];
				
				IDataModelProvider provider = getAddReferenceDataModelProvider(component);
				IDataModel dm = DataModelFactory.createDataModel(provider);
				
				dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, ComponentCore.createComponent(project));
				dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, Arrays.asList(component));
				
				//[Bug 238264] the uri map needs to be manually set correctly
				Map<IVirtualComponent, String> uriMap = new HashMap<IVirtualComponent, String>();
				uriMap.put(component, archiveName);
				dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP, uriMap);
		        dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH, path);
		
				IStatus stat = dm.validateProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
				if (stat != OK_STATUS)
					throw new CoreException(stat);
				try {
					dm.getDefaultOperation().execute(new NullProgressMonitor(), null);
				} catch (ExecutionException e) {
					ModuleCoreUIPlugin.logError(e);
				}	
			}
		
	}


	private void addWebRef(IVirtualComponent component) throws CoreException {
		String path, archiveName;
		path = new Path(objectToRuntimePath.get(component)).removeLastSegments(1).toString();
		archiveName = new Path(objectToRuntimePath.get(component)).lastSegment();

		IDataModelProvider provider = getAddWebReferenceDataModelProvider(component);
		IDataModel dm = DataModelFactory.createDataModel(provider);
		
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, rootComponent);
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, Arrays.asList(component));
		
		//[Bug 238264] the uri map needs to be manually set correctly
		Map<IVirtualComponent, String> uriMap = new HashMap<IVirtualComponent, String>();
		uriMap.put(component, archiveName);
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP, uriMap);
        dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH, path);

		IStatus stat = dm.validateProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
		if (stat != OK_STATUS)
			throw new CoreException(stat);
		try {
			dm.getDefaultOperation().execute(new NullProgressMonitor(), null);
		} catch (ExecutionException e) {
			ModuleCoreUIPlugin.logError(e);
		}	
	}
}
