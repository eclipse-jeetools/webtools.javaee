/*
 * Created on Nov 6, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.appclient.operations;

import junit.framework.Assert;
import junit.framework.Test;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.project.facet.IAppClientFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
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
        return DataModelFactory.createDataModel(IAppClientFacetInstallDataModelProperties.class);
    }
    
    public IDataModel getComponentCreationDataModelWithEar() {
        IDataModel model =  getComponentCreationDataModel();
        FacetDataModelMap map = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
        IDataModel facetDM = map.getFacetDataModel(IAppClientFacetInstallDataModelProperties.APPLICATION_CLIENT);
        facetDM.setBooleanProperty( IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR, true );
        return model;
    } 
    public void testUsingPublicAPI() throws Exception {
		IDataModel dataModel = DataModelFactory.createDataModel(IAppClientFacetInstallDataModelProperties.class);

		String projName = "TestAPIAppClientProject";//$NON-NLS-1$
		String appVersionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.J2EE_1_4_ID);
		IProjectFacet appFacet = ProjectFacetsManager.getProjectFacet(IAppClientFacetInstallDataModelProperties.APPLICATION_CLIENT);
		IProjectFacetVersion appFacetVersion = appFacet.getVersion(appVersionString); //$NON-NLS-1$

		addAppProjectProperties(dataModel, projName, appFacetVersion);
		
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);

		validateAppProjectProperties(projName, appFacetVersion);
		
		validateAppDescriptorProperties(projName);		
    }

    public void testUsingPublicAPIApp50() throws Exception {
		IDataModel dataModel = DataModelFactory.createDataModel(IAppClientFacetInstallDataModelProperties.class);

		String projName = "TestAPIAppClientProject";//$NON-NLS-1$
		String appVersionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.JEE_5_0_ID);
		IProjectFacet appFacet = ProjectFacetsManager.getProjectFacet(IAppClientFacetInstallDataModelProperties.APPLICATION_CLIENT);
		IProjectFacetVersion appFacetVersion = appFacet.getVersion(appVersionString); //$NON-NLS-1$

		addAppProjectProperties(dataModel, projName, appFacetVersion);
		
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);

		validateAppProjectProperties(projName, appFacetVersion);
		
    }

    public void testUsingPublicAPIApp50WithAddToEar() throws Exception {
		IDataModel dataModel = DataModelFactory.createDataModel(IAppClientFacetInstallDataModelProperties.class);

		String projName = "TestAPIAppClientProject";//$NON-NLS-1$
		String appVersionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.JEE_5_0_ID);
		IProjectFacet appFacet = ProjectFacetsManager.getProjectFacet(IAppClientFacetInstallDataModelProperties.APPLICATION_CLIENT);
		IProjectFacetVersion appFacetVersion = appFacet.getVersion(appVersionString); //$NON-NLS-1$

		addAppProjectProperties(dataModel, projName, appFacetVersion);
		
		String earProjName =  projName + "EAR"; //$NON-NLS-1$
		
		addEARProperties(dataModel, earProjName);
		
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);

		validateAppProjectProperties(projName, appFacetVersion);
		
		validateEARProjectProperties(earProjName, appVersionString);

    }
	private void addEARProperties(IDataModel dataModel, String earProjName) {
		dataModel.setBooleanProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, true);
		
		dataModel.setProperty(IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME, earProjName);
	}

	private void validateEARProjectProperties(String earProjName, String earFacetVersionString) throws CoreException {
		// Test if ear exists
		IProject earProj = ResourcesPlugin.getWorkspace().getRoot().getProject(earProjName);
		Assert.assertTrue(earProj.exists());		
		IVirtualComponent earComponent = ComponentCore.createComponent(earProj);
		Assert.assertNotNull(earComponent);
		if (earComponent != null)
		Assert.assertNotNull(earComponent.getName());
		IVirtualReference[] references = earComponent.getReferences();
		Assert.assertNotNull(references);
		
		// Test if ear facet version is right
		IProjectFacet earFacet = ProjectFacetsManager.getProjectFacet(IAppClientFacetInstallDataModelProperties.ENTERPRISE_APPLICATION);
		IProjectFacetVersion earFacetVersion = earFacet.getVersion(earFacetVersionString); //$NON-NLS-1$
		
		IFacetedProject facetedEARProject = ProjectFacetsManager.create(earProj);
		Assert.assertTrue(facetedEARProject.hasProjectFacet(earFacetVersion));
	}

    private void validateAppDescriptorProperties(String projName) {
		// Test if op worked
		IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		
		
		// does underlying file for deployment descriptor exist
		IVirtualComponent component = ComponentCore.createComponent(proj);
		IFile deploymentDescriptorFile = component.getRootFolder().getFile(J2EEConstants.APP_CLIENT_DD_URI).getUnderlyingFile();
		
		Assert.assertTrue(deploymentDescriptorFile.exists());

		// is it a valid artifact

		
		AppClientArtifactEdit appClient = AppClientArtifactEdit.getAppClientArtifactEditForRead(proj);
		Assert.assertNotNull(appClient);
		if (appClient != null)
		Assert.assertNotNull(appClient.getApplicationClient());
	}
  
	private void validateAppProjectProperties(String projName,
			IProjectFacetVersion appFacetVersion) throws CoreException {
		// Test if op worked
		IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		Assert.assertTrue(proj.exists());
		IVirtualComponent component = ComponentCore.createComponent(proj);
		Assert.assertNotNull(component);
		if (component != null)
		Assert.assertNotNull(component.getName());
		Assert.assertTrue(proj.exists(new Path("/appcc333")));

		// Test if facet is right version
		IFacetedProject facetedProject = ProjectFacetsManager.create(proj);
		Assert.assertTrue(facetedProject.hasProjectFacet(appFacetVersion));
	}

	private void addAppProjectProperties(IDataModel dataModel, String projName, IProjectFacetVersion appFacetVersion){

		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel appmodel = (IDataModel) map.get(IAppClientFacetInstallDataModelProperties.APPLICATION_CLIENT);
		appmodel.setProperty(IFacetInstallDataModelProperties.FACET_VERSION, appFacetVersion);
		appmodel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER,"appcc333"); //$NON-NLS-1$
    }

}
