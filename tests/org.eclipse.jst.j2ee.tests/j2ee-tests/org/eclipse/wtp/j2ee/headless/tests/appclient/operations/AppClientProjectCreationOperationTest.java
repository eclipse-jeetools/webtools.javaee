/*
 * Created on Nov 6, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.appclient.operations;

import junit.framework.Assert;
import junit.framework.Test;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IAppClientFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest;

/**
 * @author jsholl
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AppClientProjectCreationOperationTest extends ModuleProjectCreationOperationTest {

    public static String DEFAULT_PROJECT_NAME = "SimpleAppClient";
    
    /**
	 * @param name
	 */
	public AppClientProjectCreationOperationTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public static Test suite() {
        return new SimpleTestSuite(AppClientProjectCreationOperationTest.class);
    }

    public IDataModel getComponentCreationDataModel() {
        return DataModelFactory.createDataModel(new AppClientFacetProjectCreationDataModelProvider());
    }
    
    public IDataModel getComponentCreationDataModelWithEar() {
        IDataModel model =  DataModelFactory.createDataModel(new AppClientFacetProjectCreationDataModelProvider());
        FacetDataModelMap map = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
        IDataModel facetDM = map.getFacetDataModel(J2EEProjectUtilities.APPLICATION_CLIENT);
        facetDM.setBooleanProperty( IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR, true );
        return model;
    } 
    public void testUsingPublicAPI() throws Exception {
    	String projName = "TestAPIAppClientProject";//$NON-NLS-1$
    	IProjectFacet appClientFacet = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_APPCLIENT_MODULE);
		
		IDataModel dataModel = DataModelFactory.createDataModel(IAppClientFacetInstallDataModelProperties.class);
		
		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel appmodel = (IDataModel) map.get(IModuleConstants.JST_APPCLIENT_MODULE);
		IProjectFacetVersion app14 = appClientFacet.getVersion("1.4"); //$NON-NLS-1$
		appmodel.setProperty(IFacetInstallDataModelProperties.FACET_VERSION, app14);
		appmodel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER,"appcc333"); //$NON-NLS-1$
        
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		// Test if op worked
		IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		AppClientArtifactEdit appClient = AppClientArtifactEdit.getAppClientArtifactEditForRead(proj);
		Assert.assertNotNull(appClient);
		if (appClient != null)
		Assert.assertNotNull(appClient.getApplicationClient());
		Assert.assertTrue(proj.exists(new Path("/appcc333")));
		
    }
}
