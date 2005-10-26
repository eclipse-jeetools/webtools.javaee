/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.project.facet;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.FacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


public  class JavaProjectMigrationOperation extends AbstractDataModelOperation implements IJavaProjectMigrationDataModelProperties{

	private static String WTP_MODULE_FILE_NAME = ".wtpmodules"; //$NON-NLS-1$
	
    public JavaProjectMigrationOperation(IDataModel model) {
        super(model);
    }

    public IStatus execute(IProgressMonitor monitor, IAdaptable info) {
			
		IDataModel jdm = DataModelFactory.createDataModel(new JavaFacetInstallDataModelProvider());
		
		jdm.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME,
			model.getStringProperty(PROJECT_NAME));
	
		jdm.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, "1.4"); //$NON-NLS-1$
	

		IDataModel udm = DataModelFactory.createDataModel(new UtilityFacetInstallDataModelProvider());
		try{
			udm.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME,
					model.getStringProperty(PROJECT_NAME));
			udm.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, "1.0"); //$NON-NLS-1$
		}catch(Exception e){
			Logger.getLogger().logError(e);
		}
			
			
		IDataModel dm = DataModelFactory.createDataModel(new FacetProjectCreationDataModelProvider());
		dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME,
				model.getStringProperty(PROJECT_NAME));
		List facetDMs = new ArrayList();
		facetDMs.add( jdm );
		facetDMs.add( udm );
		dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_DM_LIST, facetDMs);

		try {
			dm.getDefaultOperation().execute(monitor, null);
		} catch (ExecutionException e) {
			Logger.getLogger().logError(e);
		}
		return OK_STATUS;
    }

	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}		
	
}