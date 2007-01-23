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
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetUtils;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
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
    	String projName = "TestAPIWebProject";//$NON-NLS-1$
		
		IDataModel dataModel = DataModelFactory.createDataModel(IWebFacetInstallDataModelProperties.class);
		
		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel webmodel = (IDataModel) map.get(IModuleConstants.JST_WEB_MODULE);
		webmodel.setProperty(IFacetInstallDataModelProperties.FACET_VERSION, WebFacetUtils.WEB_24);
		webmodel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER,"web333"); //$NON-NLS-1$
        webmodel.setStringProperty(IWebFacetInstallDataModelProperties.SOURCE_FOLDER, "src444");
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		// Test if op worked
		IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		WebArtifactEdit web = WebArtifactEdit.getWebArtifactEditForRead(proj);
		Assert.assertNotNull(web);
		if (web != null)
		Assert.assertNotNull(web.getWebApp());
		Assert.assertTrue(proj.exists(new Path("/web333")));
		Assert.assertTrue(proj.exists(new Path("/src444")));
		
    }
    
}
