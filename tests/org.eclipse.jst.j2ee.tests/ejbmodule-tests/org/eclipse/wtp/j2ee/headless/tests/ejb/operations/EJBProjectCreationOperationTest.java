
package org.eclipse.wtp.j2ee.headless.tests.ejb.operations;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest;

public class EJBProjectCreationOperationTest extends ModuleProjectCreationOperationTest {

	 /**
	 * @param name
	 */
	public EJBProjectCreationOperationTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public static Test suite() {
        return new TestSuite(EJBProjectCreationOperationTest.class);
    }

    /* (non-Javadoc)
     * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest#getProjectCreationDataModel()
     */
    public IDataModel getComponentCreationDataModel() {
    	return DataModelFactory.createDataModel(new EjbFacetProjectCreationDataModelProvider());
    }
    
    public void testFindFilesUtility() {
    	IFile file = null;
    	IResource eclipseFile = null;
    	try {
    		testDefaults();
    		IProject project = J2EEProjectUtilities.getAllProjectsInWorkspaceOfType(J2EEProjectUtilities.EJB)[0];
    		IVirtualComponent comp = ComponentCore.createComponent(project);
    		try {
    			if (!project.isSynchronized(IResource.DEPTH_INFINITE))
    				project.refreshLocal(IResource.DEPTH_INFINITE, null);
    		} catch (CoreException e) {
    			Logger.getLogger().logError(e);
    		}
    		eclipseFile = project.findMember(new Path("ejbModule/META-INF/ejb-jar.xml"));
    		//file = ComponentUtilities.findFile(comp, new Path("META-INF/ejb-jar.xml")); //$NON-NLS-1$
    		file = comp.getRootFolder().getFile(new Path("META-INF/ejb-jar.xml")).getUnderlyingFile(); //$NON-NLS-1$
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	Assert.assertTrue(eclipseFile != null && file != null && file.exists());
    }
  
    public IDataModel getComponentCreationDataModelWithEar() {
        IDataModel model =  DataModelFactory.createDataModel(new EjbFacetProjectCreationDataModelProvider());
        FacetDataModelMap map = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
        IDataModel facetDM = map.getFacetDataModel(J2EEProjectUtilities.EJB);
        facetDM.setBooleanProperty( IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR, true );
        return model;
    }     
    public void testUsingPublicAPI() throws Exception {
    	String projName = "TestAPIEjbProject";//$NON-NLS-1$
    	IProjectFacet ejbFacet = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EJB_MODULE);
		
		IDataModel dataModel = DataModelFactory.createDataModel(IEjbFacetInstallDataModelProperties.class);
		
		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel appmodel = (IDataModel) map.get(IModuleConstants.JST_EJB_MODULE);
		IProjectFacetVersion ejb21 = ejbFacet.getVersion("2.1"); //$NON-NLS-1$
		appmodel.setProperty(IFacetInstallDataModelProperties.FACET_VERSION, ejb21);
		appmodel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER,"ejb333"); //$NON-NLS-1$
        
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		// Test if op worked
		IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		EJBArtifactEdit jar = EJBArtifactEdit.getEJBArtifactEditForRead(proj);
		Assert.assertNotNull(jar);
		if (jar != null)
		Assert.assertNotNull(jar.getEJBJar());
		Assert.assertTrue(proj.exists(new Path("/ejb333")));
		
    }
}
