package org.eclipse.jst.j2ee.project.facet;



import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.operation.FacetProjectCreationOperation;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class UtilityProjectCreationOperation {

	private String projectName;
	private String earProjectName;
	private org.eclipse.wst.common.project.facet.core.runtime.IRuntime runtime;
	private String javaSourceFolder;
	
	public UtilityProjectCreationOperation( String projectName, 
										String earProjectName,
										org.eclipse.wst.common.project.facet.core.runtime.IRuntime runtime,
										String javaSourceFolder){
		
		this.projectName = projectName;
		this.earProjectName = earProjectName;
		this.runtime = runtime;
		this.javaSourceFolder = javaSourceFolder;
	}
	
	
	public void execute(IProgressMonitor monitor){
		
		
		IDataModel dm = DataModelFactory.createDataModel(new UtilityProjectCreationDataModelProvider());
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
			op.execute( monitor, null );
		} catch (ExecutionException e) {
			Logger.getLogger().logError(e);
		}
	}
	
}
