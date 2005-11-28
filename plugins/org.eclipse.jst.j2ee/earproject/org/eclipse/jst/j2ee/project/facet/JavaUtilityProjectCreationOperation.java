package org.eclipse.jst.j2ee.project.facet;



import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.operation.FacetProjectCreationOperation;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public class JavaUtilityProjectCreationOperation extends AbstractDataModelOperation implements IJavaUtilityProjectCreationDataModelProperties{


	
	public JavaUtilityProjectCreationOperation(IDataModel model) {
		super(model);
	}
	

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		
		IStatus stat = OK_STATUS;
		
		String projectName = model.getStringProperty( IJavaUtilityProjectCreationDataModelProperties.PROJECT_NAME );
		String earProjectName = model.getStringProperty( IJavaUtilityProjectCreationDataModelProperties.EAR_PROJECT_NAME );;
		String javaSourceFolder = model.getStringProperty( IJavaUtilityProjectCreationDataModelProperties.SOURCE_FOLDER );
		
		org.eclipse.wst.common.project.facet.core.runtime.IRuntime runtime = (IRuntime) model.getProperty(IJavaUtilityProjectCreationDataModelProperties.RUNTIME);
		
		IDataModel dm = DataModelFactory.createDataModel(new UtilityProjectCreationDataModelProvider());
		
		
		//IDataModel pdm = dm.getNestedModel( IFacetProjectCreationDataModelProperties.NESTED_PROJECT_DM );
		//pdm.setStringProperty( IProjectCreationPropertiesNew.PROJECT_LOCATION, model.getStringProperty( IJavaUtilityProjectCreationDataModelProperties.PROJECT_LOCATION ) );
		
		FacetDataModelMap map = (FacetDataModelMap) dm.getProperty(UtilityProjectCreationDataModelProvider.FACET_DM_MAP);
		
		IDataModel javadm = map.getFacetDataModel( IModuleConstants.JST_JAVA );
		IDataModel utildm = map.getFacetDataModel( J2EEProjectUtilities.UTILITY );
		
		
		javadm.setProperty( JavaFacetInstallDataModelProvider.FACET_PROJECT_NAME,
				projectName);
		
		
		javadm.setProperty( JavaFacetInstallDataModelProvider.SOURCE_FOLDER_NAME,
				javaSourceFolder);
		

		utildm.setProperty( IUtilityFacetInstallDataModelProperties.EAR_PROJECT_NAME, earProjectName);
		
		utildm.setProperty( IUtilityFacetInstallDataModelProperties.FACET_RUNTIME, runtime );
		dm.setProperty(UtilityProjectCreationDataModelProvider.FACET_RUNTIME, runtime);

		FacetProjectCreationOperation op = new FacetProjectCreationOperation(dm);
		try {
			stat = op.execute( monitor, null );
		} catch (ExecutionException e) {
			Logger.getLogger().logError(e);
		}
		return stat;
	}
	
}
