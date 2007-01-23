/*
 * Created on Oct 27, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 * Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ear.operations;

import junit.framework.Assert;
import junit.framework.Test;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.earcreation.IEarFacetInstallDataModelProperties;
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

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
public class EARProjectCreationOperationTest extends org.eclipse.wst.common.tests.OperationTestCase {
    public EARProjectCreationOperationTest(String string) {
		super(string);
	}

	public static Test suite() {
        return new SimpleTestSuite(EARProjectCreationOperationTest.class);
    }

	public void testUsingPublicAPI() throws Exception {
    	String projName = "TestAPIEarProject";//$NON-NLS-1$
    	IProjectFacet earFacet = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EAR_MODULE);
		
		IDataModel dataModel = DataModelFactory.createDataModel(IEarFacetInstallDataModelProperties.class);
		
		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel earmodel = (IDataModel) map.get(IModuleConstants.JST_EAR_MODULE);
		IProjectFacetVersion ear14 = earFacet.getVersion("1.4"); //$NON-NLS-1$
		earmodel.setProperty(IFacetInstallDataModelProperties.FACET_VERSION, ear14);
		earmodel.setStringProperty(IEarFacetInstallDataModelProperties.CONTENT_DIR,"ear333"); //$NON-NLS-1$
        
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		// Test if op worked
		IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		EARArtifactEdit ear = EARArtifactEdit.getEARArtifactEditForRead(proj);
		Assert.assertNotNull(ear);
		if (ear != null)
		Assert.assertNotNull(ear.getApplication());
		Assert.assertTrue(proj.exists(new Path("/ear333")));
		
    }

}
