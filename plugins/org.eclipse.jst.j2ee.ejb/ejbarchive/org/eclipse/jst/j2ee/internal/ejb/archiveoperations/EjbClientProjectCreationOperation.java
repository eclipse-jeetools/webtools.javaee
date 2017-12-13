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
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.j2ee.ejb.archiveoperations.IEjbClientProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJavaUtilityProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IUtilityFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.JavaUtilityProjectCreationOperation;
import org.eclipse.jst.j2ee.project.facet.UtilityProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.ModulecorePlugin;
import org.eclipse.wst.common.componentcore.internal.operation.FacetProjectCreationOperation;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public class EjbClientProjectCreationOperation
	extends JavaUtilityProjectCreationOperation
	implements IEjbClientProjectCreationDataModelProperties{

	public EjbClientProjectCreationOperation(IDataModel model) {
		super(model);
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		IStatus stat = OK_STATUS;
		
		String projectName = model.getStringProperty( IJavaUtilityProjectCreationDataModelProperties.PROJECT_NAME );
		String earProjectName = model.getStringProperty( IJavaUtilityProjectCreationDataModelProperties.EAR_PROJECT_NAME );
		String javaSourceFolder = model.getStringProperty( IJavaUtilityProjectCreationDataModelProperties.SOURCE_FOLDER );
		String defaultOutputFolder = model.getStringProperty( IEjbClientProjectCreationDataModelProperties.DEFAULT_OUTPUT_FOLDER );
		org.eclipse.wst.common.project.facet.core.runtime.IRuntime runtime = (IRuntime) model.getProperty(IJavaUtilityProjectCreationDataModelProperties.RUNTIME);
		
		IDataModel dm = null;
		try{
			dm = DataModelFactory.createDataModel(new UtilityProjectCreationDataModelProvider());
		
			FacetDataModelMap map = (FacetDataModelMap) dm.getProperty(UtilityProjectCreationDataModelProvider.FACET_DM_MAP);
			
			IDataModel javadm = map.getFacetDataModel( IModuleConstants.JST_JAVA );
			IDataModel utildm = map.getFacetDataModel( J2EEProjectUtilities.UTILITY );
			
			
			javadm.setProperty( JavaFacetInstallDataModelProvider.FACET_PROJECT_NAME,
					projectName);
			
			
			javadm.setProperty( JavaFacetInstallDataModelProvider.SOURCE_FOLDER_NAME,
					javaSourceFolder);
			
			if( defaultOutputFolder == null || defaultOutputFolder.length() == 0 ){
				defaultOutputFolder = javaSourceFolder;
			}
			
			javadm.setProperty(IJavaFacetInstallDataModelProperties.DEFAULT_OUTPUT_FOLDER_NAME,
					defaultOutputFolder);			
	
			//		 if the parent data model has set these properties we will use it, or else default to the utility facet install
			if(model.isPropertySet(ADD_TO_EAR))
				utildm.setProperty(IUtilityFacetInstallDataModelProperties.ADD_TO_EAR, model.getProperty(ADD_TO_EAR));
	
			if(model.isPropertySet(CLIENT_URI))
				utildm.setProperty(IUtilityFacetInstallDataModelProperties.MODULE_URI, model.getProperty(CLIENT_URI));
			
	
			utildm.setProperty( IUtilityFacetInstallDataModelProperties.EAR_PROJECT_NAME, earProjectName);
			
			utildm.setProperty( IUtilityFacetInstallDataModelProperties.FACET_RUNTIME, runtime );
			dm.setProperty(UtilityProjectCreationDataModelProvider.FACET_RUNTIME, runtime);
			
			//Ensure Java DM has latest supported version
			try {
				if (runtime != null)
				{
					IProjectFacetVersion oldVersion = (IProjectFacetVersion) javadm.getProperty(IFacetDataModelProperties.FACET_VERSION);
					IProjectFacetVersion newVersion = JavaFacet.FACET.getLatestSupportedVersion(runtime);
					if (newVersion != null && (oldVersion == null || oldVersion.getVersionString().compareTo(newVersion.getVersionString()) < 0 || !runtime.supports(oldVersion))) {
						javadm.setProperty(IFacetDataModelProperties.FACET_VERSION, newVersion);
					}
				}
			} catch (CoreException e) {
				ModulecorePlugin.logError(e);
			}
	
			FacetProjectCreationOperation op = new FacetProjectCreationOperation(dm);
			try {
				stat = op.execute( monitor, null );
			} catch (ExecutionException e) {
				EjbPlugin.logError(e);
			}
		} finally {
			if(dm != null){
				dm.dispose();
			}
		}
		return stat;
	}
}
