/*
 * Created on Nov 7, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest;


public class WebProjectCreationOperationTest extends ModuleProjectCreationOperationTest {
    
    /**
	 * @param name
	 */
	public WebProjectCreationOperationTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public static Test suite() {
        return new TestSuite(WebProjectCreationOperationTest.class);
    }

    /* (non-Javadoc)
     * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest#getProjectCreationDataModel()
     */
    public IDataModel getComponentCreationDataModel() {
        return DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
    }
    
    public IDataModel getComponentCreationDataModelWithEar() {
        IDataModel model =  DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
        FacetDataModelMap map = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
        IDataModel facetDM = map.getFacetDataModel(J2EEProjectUtilities.DYNAMIC_WEB);
        facetDM.setBooleanProperty( IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR, true );
        return model;
    } 
    public void testUsingPublicAPI() throws Exception {
		IDataModel dataModel = DataModelFactory.createDataModel(IWebFacetInstallDataModelProperties.class);

		String projName = "TestAPIWebProject";//$NON-NLS-1$
		String webVersionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.WEB_2_4_ID);
		IProjectFacet webFacet = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_WEB_MODULE);
		IProjectFacetVersion webFacetVersion = webFacet.getVersion(webVersionString); //$NON-NLS-1$

		addWebProjectProperties(dataModel, projName, webFacetVersion);
		
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);

		validateWebProjectProperties(projName, webFacetVersion);
		
		validateWebDescriptorProperties(projName);
		
    }

   
    public void testUsingPublicAPIWeb25() throws Exception {
		IDataModel dataModel = DataModelFactory.createDataModel(IWebFacetInstallDataModelProperties.class);

		String projName = "TestAPIWebProject";//$NON-NLS-1$
		String webVersionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.WEB_2_5_ID);
		IProjectFacet webFacet = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_WEB_MODULE);
		IProjectFacetVersion webFacetVersion = webFacet.getVersion(webVersionString); //$NON-NLS-1$

		addWebProjectProperties(dataModel, projName, webFacetVersion);
		
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);

		validateWebProjectProperties(projName, webFacetVersion);

    }
	public void testUsingPublicAPIWeb25WithAddToEar() throws Exception {
		IDataModel dataModel = DataModelFactory.createDataModel(IWebFacetInstallDataModelProperties.class);
	
		String projName = "TestAPIWebProject";//$NON-NLS-1$
		String webVersionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.WEB_2_5_ID);
		IProjectFacet webFacet = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_WEB_MODULE);
		IProjectFacetVersion webFacetVersion = webFacet.getVersion(webVersionString); //$NON-NLS-1$
	
		addWebProjectProperties(dataModel, projName, webFacetVersion);
	
		String earProjName =  projName + "EAR"; //$NON-NLS-1$
		
		addEARProperties(dataModel, earProjName);
	    
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
	
		validateWebProjectProperties(projName, webFacetVersion);
		
		validateEARProjectProperties(earProjName,  J2EEVersionUtil.getJ2EETextVersion(J2EEVersionUtil.convertWebVersionStringToJ2EEVersionID(webVersionString)));
	
	}
	private void addEARProperties(IDataModel dataModel, String earProjName) {
		dataModel.setBooleanProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, true);
		
		dataModel.setProperty(IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME, earProjName);
	}

	private void addWebProjectProperties(IDataModel dataModel, String projName, IProjectFacetVersion web25){

		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel webmodel = (IDataModel) map.get(IModuleConstants.JST_WEB_MODULE);
		webmodel.setProperty(IFacetInstallDataModelProperties.FACET_VERSION, web25);
		webmodel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER,"web333"); //$NON-NLS-1$
        webmodel.setStringProperty(IWebFacetInstallDataModelProperties.SOURCE_FOLDER, "src444");
    }
	
	private void validateWebDescriptorProperties(String projName) {
		// Test if op worked
		IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		WebArtifactEdit web = WebArtifactEdit.getWebArtifactEditForRead(proj);
		Assert.assertNotNull(web);
		if (web != null)
		Assert.assertNotNull(web.getWebApp());
	}

	private void validateWebProjectProperties(String projName,
			IProjectFacetVersion webFacetVersion) throws CoreException {
		// Test if op worked
		IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		Assert.assertTrue(proj.exists());
		IVirtualComponent component = ComponentCore.createComponent(proj);
		Assert.assertNotNull(component);
		if (component != null)
		Assert.assertNotNull(component.getName());
		Assert.assertTrue(proj.exists(new Path("/web333")));
		Assert.assertTrue(proj.exists(new Path("/src444")));

		// Test if web facet version is correct
		IFacetedProject facetedProject = ProjectFacetsManager.create(proj);
		Assert.assertTrue(facetedProject.hasProjectFacet(webFacetVersion));
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
		
		// Test if ear facet is right
		IProjectFacet earFacet = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EAR_MODULE);
		IProjectFacetVersion earFacetVersion = earFacet.getVersion(earFacetVersionString); //$NON-NLS-1$
		
		IFacetedProject facetedProject = ProjectFacetsManager.create(earProj);
		Assert.assertTrue(facetedProject.hasProjectFacet(earFacetVersion));
	}


}
    

