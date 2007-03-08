
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
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
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
		IDataModel dataModel = DataModelFactory.createDataModel(IEjbFacetInstallDataModelProperties.class);

		String projName = "TestAPIEjbProject";//$NON-NLS-1$
		String ejbVersionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.EJB_2_1_ID);
		IProjectFacet ejbFacet = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EJB_MODULE);
		IProjectFacetVersion ejbFacetVersion = ejbFacet.getVersion(ejbVersionString); //$NON-NLS-1$

		addEJBProjectProperties(dataModel, projName, ejbFacetVersion);
		
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);

		validateEJBProjectProperties(projName, ejbFacetVersion);

		validateEJBDescriptorProperties(projName);
		
    }


 
	public void testUsingPublicAPIEJB30() throws Exception {
		
		IDataModel dataModel = DataModelFactory.createDataModel(IEjbFacetInstallDataModelProperties.class);

		String projName = "TestAPIEjbProject";//$NON-NLS-1$
		String ejbVersionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.EJB_3_0_ID);
		IProjectFacet ejbFacet = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EJB_MODULE);
		IProjectFacetVersion ejbFacetVersion = ejbFacet.getVersion(ejbVersionString); //$NON-NLS-1$

		addEJBProjectProperties(dataModel, projName, ejbFacetVersion);
		
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);

		validateEJBProjectProperties(projName, ejbFacetVersion);

	}
	private void validateEJBDescriptorProperties(String projName) {
		// Test if op worked
		IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		EJBArtifactEdit jar = EJBArtifactEdit.getEJBArtifactEditForRead(proj);
		Assert.assertNotNull(jar);
		if (jar != null)
		Assert.assertNotNull(jar.getEJBJar());
	}

	private void validateEJBProjectProperties(String projName,
			IProjectFacetVersion ejbFacetVersion) throws CoreException {
		// Test if op worked
		IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		Assert.assertTrue(proj.exists());
		IVirtualComponent component = ComponentCore.createComponent(proj);
		Assert.assertNotNull(component);
		if (component != null)
		Assert.assertNotNull(component.getName());
		Assert.assertTrue(proj.exists(new Path("/ejb333")));

		// Test if ejb facet is right version
		IFacetedProject facetedEJBProject = ProjectFacetsManager.create(proj);
		Assert.assertTrue(facetedEJBProject.hasProjectFacet(ejbFacetVersion));
	}

	public void testUsingPublicAPIEJB30WithAddToEar() throws Exception {
		IDataModel dataModel = DataModelFactory.createDataModel(IEjbFacetInstallDataModelProperties.class);
	
		String projName = "TestAPIEjbProject";//$NON-NLS-1$
		String ejbVersionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.EJB_3_0_ID);
		IProjectFacet ejbFacet = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EJB_MODULE);
		IProjectFacetVersion ejbFacetVersion = ejbFacet.getVersion(ejbVersionString); //$NON-NLS-1$
	
		addEJBProjectProperties(dataModel, projName, ejbFacetVersion);
	
		String earProjName =  projName + "EAR"; //$NON-NLS-1$
		
		addEARProperties(dataModel, earProjName);
	    
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
	
		validateEJBProjectProperties(projName, ejbFacetVersion);
		
		validateEARProjectProperties(earProjName, J2EEVersionUtil.getJ2EETextVersion(J2EEVersionUtil.convertEJBVersionStringToJ2EEVersionID(ejbVersionString)));
	
	}

	public void testUsingPublicAPIEJB30WithCreateEJBClient() throws Exception {
		IDataModel dataModel = DataModelFactory.createDataModel(IEjbFacetInstallDataModelProperties.class);
	
		String projName = "TestAPIEjbProject";//$NON-NLS-1$
		String ejbVersionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.EJB_3_0_ID);
		IProjectFacet ejbFacet = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EJB_MODULE);
		IProjectFacetVersion ejbFacetVersion = ejbFacet.getVersion(ejbVersionString); //$NON-NLS-1$
	
		addEJBProjectProperties(dataModel, projName, ejbFacetVersion);
	
		
		String ejbClientName =  projName + "Client"; //$NON-NLS-1$

		addEJBClientProperties(dataModel, ejbClientName);

		String earProjName =  projName + "EAR"; //$NON-NLS-1$

		addEARProperties(dataModel, earProjName);
	    
		
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);

		validateEJBProjectProperties(projName, ejbFacetVersion);
		
		validateEJBClientProjectProperties(ejbClientName);
		
		validateEARProjectProperties(earProjName, J2EEVersionUtil.getJ2EETextVersion(J2EEVersionUtil.convertEJBVersionStringToJ2EEVersionID(ejbVersionString)));
	
	}


	private void validateEJBClientProjectProperties(String ejbClientName) {
		// Test if op worked
		IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(ejbClientName);
		Assert.assertTrue(proj.exists());
		
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
		IProjectFacet earFacet = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EAR_MODULE);
		IProjectFacetVersion earFacetVersion = earFacet.getVersion(earFacetVersionString); //$NON-NLS-1$
		
		IFacetedProject facetedEARProject = ProjectFacetsManager.create(earProj);
		Assert.assertTrue(facetedEARProject.hasProjectFacet(earFacetVersion));
	}

	private void addEARProperties(IDataModel dataModel, String earProjName) {
		dataModel.setBooleanProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR, true);
		
		dataModel.setProperty(IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME, earProjName);
	}

	private void addEJBProjectProperties(IDataModel dataModel, String projName, IProjectFacetVersion ejbFacetVersion){

		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel appmodel = (IDataModel) map.get(IModuleConstants.JST_EJB_MODULE);
		appmodel.setProperty(IFacetInstallDataModelProperties.FACET_VERSION, ejbFacetVersion);
		appmodel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER,"ejb333"); //$NON-NLS-1$
    }

	private void addEJBClientProperties(IDataModel dataModel,
			String ejbClientName) {
		FacetDataModelMap map = (FacetDataModelMap) dataModel
		.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel appmodel = (IDataModel) map.get(IModuleConstants.JST_EJB_MODULE);

		appmodel.setBooleanProperty(IEjbFacetInstallDataModelProperties.CREATE_CLIENT, true);
		appmodel.setStringProperty(IEjbFacetInstallDataModelProperties.CLIENT_NAME, ejbClientName);
	}

}
