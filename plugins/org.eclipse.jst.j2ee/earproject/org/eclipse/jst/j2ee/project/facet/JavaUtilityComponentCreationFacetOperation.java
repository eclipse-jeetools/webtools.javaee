package org.eclipse.jst.j2ee.project.facet;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.wst.common.componentcore.datamodel.FacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


public class JavaUtilityComponentCreationFacetOperation 
	extends J2EEComponentCreationFacetOperation{

	public JavaUtilityComponentCreationFacetOperation(IDataModel model){
		super(model);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		IStatus result = OK_STATUS;
		try{	
			IDataModel dm = DataModelFactory.createDataModel(new FacetProjectCreationDataModelProvider());
			dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME,
					(String)model.getProperty(JavaUtilityComponentCreationDataModelProvider.PROJECT_NAME));
			List facetDMs = new ArrayList();
			facetDMs.add( setupJavaInstallAction() );
			facetDMs.add( setupUtilityInstallAction(model) );
			dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_DM_LIST, facetDMs);
			result = dm.getDefaultOperation().execute(monitor, null);
		}catch(ExecutionException e){
			Logger.getLogger().logError(e);
		}
		return result;
	}
	
	protected IDataModel setupUtilityInstallAction(IDataModel aDM){
		IDataModel dm = DataModelFactory.createDataModel(new UtilityFacetInstallDataModelProvider());
		try{
			dm.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME,
					(String)model.getProperty(JavaUtilityComponentCreationDataModelProvider.PROJECT_NAME));
			dm.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, "1.0"); //$NON-NLS-1$
		}catch(Exception e){
			Logger.getLogger().logError(e);
		}
		
		return dm;
	}	
	
}
