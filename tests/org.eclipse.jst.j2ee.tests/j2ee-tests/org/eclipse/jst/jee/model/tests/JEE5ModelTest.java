package org.eclipse.jst.jee.model.tests;
import junit.framework.Assert;
import junit.framework.Test;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jem.util.emf.workbench.ProjectResourceSet;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.archive.emftests.GeneralEMFPopulationTest;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.earcreation.IEarFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.CreationConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.project.facet.IAppClientFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.javaee.application.internal.util.ApplicationResourceImpl;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.internal.util.EjbResourceImpl;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.internal.util.WebResourceImpl;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.impl.WTPResourceFactoryRegistry;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.IHeadlessRunnableWithProgress;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wst.project.facet.IProductConstants;
import org.eclipse.wst.project.facet.ProductManager;

public class JEE5ModelTest extends GeneralEMFPopulationTest {
	
	private static final String PROJECTNAME = "TestNewModels";
	public JEE5ModelTest(String name) {
		super(name);
	}
	
	
    public static Test suite() {
        return new SimpleTestSuite(JEE5ModelTest.class);
    }
    
    
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		if(workspace.getRoot().getProject(PROJECTNAME).isAccessible()) return;
        final IProjectDescription description = workspace
                .newProjectDescription(PROJECTNAME);
        description.setLocation(null);


        // create the new project operation
        IHeadlessRunnableWithProgress op = new IHeadlessRunnableWithProgress() {
            public void run(IProgressMonitor monitor)
           {
                try {
					createProject(description, workspace.getRoot().getProject(PROJECTNAME), monitor);
				} catch (OperationCanceledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        };
        

        // run the new project creation operation
        try {
        	op.run(new NullProgressMonitor());
        } catch (InterruptedException e) {
            return;
        }
	}
    private void createProject(IProjectDescription description,
            IProject projectHandle, IProgressMonitor monitor)
            throws CoreException, OperationCanceledException {
        try {
            monitor.beginTask("", 2000); //$NON-NLS-1$

            projectHandle.create(description, new SubProgressMonitor(monitor,
                    1000));

            if (monitor.isCanceled())
                throw new OperationCanceledException();

            projectHandle.open(IResource.BACKGROUND_REFRESH, new SubProgressMonitor(monitor, 1000));

        } finally {
            monitor.done();
        }
    }



//    public void testNewEJBModelPopulation() throws Exception {
//		
//		EMFAttributeFeatureGenerator.reset();
//		String newModelPathURI = "/testejbmodel.xml";
//		URI uri = URI.createPlatformResourceURI("/" + getProject().getName() + newModelPathURI,false);
//		ResourceSet resSet = getResourceSet();
//		registerFactory(uri, resSet, new EjbResourceFactoryImpl());
//
//		EjbResourceImpl ejbRes = (EjbResourceImpl) resSet.createResource(uri);
//		
//		setVersion(J2EEVersionConstants.JEE_5_0_ID);
//		
//		EJBJarDeploymentDescriptor DDroot = EjbFactory.eINSTANCE.createEJBJarDeploymentDescriptor();
//		EJBJar jar = EjbFactory.eINSTANCE.createEJBJar();
//		ejbRes.getContents().add((EObjectImpl)DDroot);
//		DDroot.setEjbJar(jar);
//		populateRoot((EObjectImpl)jar);
//		
//		ejbRes.save(null);
//		
//
//	}
    
//    public void testReadNewWebModel() throws Exception {
//		
//    	String projName = "TestEE5WebProject";//$NON-NLS-1$
//    	createWebProject(projName);
//    	
//    	
//    	EMFAttributeFeatureGenerator.reset();
//		String modelPathURI = J2EEConstants.WEBAPP_DD_URI;
//		URI uri = URI.createURI("web333/" + modelPathURI);
//		ProjectResourceSet resSet = getResourceSet(projName);
//		
//
//		WebResourceImpl webRes = (WebResourceImpl) resSet.getResource(uri,true);
//		
//		if (webRes.getContents().size() > 0) {
//			WebAppDeploymentDescriptor ddRoot = (WebAppDeploymentDescriptor)webRes.getContents().get(0);
//			Assert.assertTrue(ddRoot.getWebApp() != null);
//		}
//	
//		
//
//	}
public void testEJBModel() throws Exception {
		
	String projName = "TestEE5EjbProject";//$NON-NLS-1$
	createEjbProject(projName);
	
	EMFAttributeFeatureGenerator.reset();
	String modelPathURI = J2EEConstants.EJBJAR_DD_URI;
	URI uri = URI.createURI(CreationConstants.DEFAULT_EJB_SOURCE_FOLDER + "/" + modelPathURI);
	ProjectResourceSet resSet = getResourceSet(projName);
	
	EjbResourceImpl ejbRes = (EjbResourceImpl) resSet.getResource(uri,true);
	Assert.assertTrue(ejbRes.getContents().size() > 0);
	
	if (ejbRes.getContents().size() > 0) {
		EJBJar jar = ejbRes.getEjbJar();
		populateRoot((EObjectImpl)jar);
		ejbRes.save(null);
	}

}
//public void testAppClientModel() throws Exception {
//	
//	String projName = "TestEE5AppClientProject";//$NON-NLS-1$
//	createAppClientProject(projName);
//	
//	EMFAttributeFeatureGenerator.reset();
//	String modelPathURI = J2EEConstants.APP_CLIENT_DD_URI;
//	URI uri = URI.createURI(CreationConstants.DEFAULT_APPCLIENT_SOURCE_FOLDER + "/" + modelPathURI);
//	ProjectResourceSet resSet = getResourceSet(projName);
//	
//	ApplicationclientResourceImpl appClientRes = (ApplicationclientResourceImpl) resSet.getResource(uri,true);
//	Assert.assertTrue(appClientRes.getContents().size() > 0);
//	
//	if (appClientRes.getContents().size() > 0) {
//		ApplicationClient client = appClientRes.getApplicationClient();
//		populateRoot((EObjectImpl)client);
//		appClientRes.save(null);
//	}
//
//}
public void testEarModel() throws Exception {
	
	String projName = "TestEE5EarProject";//$NON-NLS-1$
	createEarProject(projName);
	
	EMFAttributeFeatureGenerator.reset();
	String modelPathURI = J2EEConstants.APPLICATION_DD_URI;
	URI uri = URI.createURI(ProductManager.getProperty(IProductConstants.APPLICATION_CONTENT_FOLDER) + "/" + modelPathURI);
	ProjectResourceSet resSet = getResourceSet(projName);
	
	ApplicationResourceImpl earRes = (ApplicationResourceImpl) resSet.getResource(uri,true);
	Assert.assertTrue(earRes.getContents().size() > 0);
	
	if (earRes.getContents().size() > 0) {
		Application ear = earRes.getApplication();
		populateRoot((EObjectImpl)ear);
		earRes.save(null);
	}

}
public void testWarModel() throws Exception {
	
	String projName = "TestEE5WarProject";//$NON-NLS-1$
	createWebProject(projName);
	
	EMFAttributeFeatureGenerator.reset();
	String modelPathURI = J2EEConstants.WEBAPP_DD_URI;
	URI uri = URI.createURI("web333/" + modelPathURI);
	ProjectResourceSet resSet = getResourceSet(projName);
	
	WebResourceImpl webRes = (WebResourceImpl) resSet.getResource(uri,true);
	Assert.assertTrue(webRes.getContents().size() > 0);
	
	if (webRes.getContents().size() > 0) {
		WebApp ear = webRes.getWebApp();
		populateRoot((EObjectImpl)ear);
		webRes.save(null);
	}

}

	private ProjectResourceSet getResourceSet(String projName) {
		IProject proj = getProject(projName);
		return (ProjectResourceSet)WorkbenchResourceHelperBase.getResourceSet(proj);
	}


	private IProject createWebProject(String projName) throws ExecutionException {
		IDataModel dataModel = DataModelFactory.createDataModel(IWebFacetInstallDataModelProperties.class);
		String webVersionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.WEB_2_5_ID);
		IProjectFacet webFacet = ProjectFacetsManager.getProjectFacet(IWebFacetInstallDataModelProperties.DYNAMIC_WEB);
		IProjectFacetVersion webFacetVersion = webFacet.getVersion(webVersionString); //$NON-NLS-1$
		addWebProjectProperties(dataModel, projName, webFacetVersion);
		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		map.add(setupJavaInstallAction(projName,CreationConstants.DEFAULT_WEB_SOURCE_FOLDER));
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		IProject webProj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		return webProj;
	}
	private IProject createEjbProject(String projName) throws ExecutionException {
		IDataModel dataModel = DataModelFactory.createDataModel(IEjbFacetInstallDataModelProperties.class);
		String versionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.EJB_3_0_ID);
		IProjectFacet facet = ProjectFacetsManager.getProjectFacet(IEjbFacetInstallDataModelProperties.EJB);
		IProjectFacetVersion facetVersion = facet.getVersion(versionString); //$NON-NLS-1$
		addVersionProperties(dataModel, projName, facetVersion,IJ2EEFacetInstallDataModelProperties.EJB);
		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		map.add(setupJavaInstallAction(projName,CreationConstants.DEFAULT_EJB_SOURCE_FOLDER));
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		IProject webProj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		return webProj;
	}
	private IProject createEarProject(String projName) throws ExecutionException {
		IDataModel dataModel = DataModelFactory.createDataModel(IEarFacetInstallDataModelProperties.class);
		String versionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.JEE_5_0_ID);
		IProjectFacet facet = ProjectFacetsManager.getProjectFacet(IEarFacetInstallDataModelProperties.ENTERPRISE_APPLICATION);
		IProjectFacetVersion facetVersion = facet.getVersion(versionString); //$NON-NLS-1$
		addVersionProperties(dataModel, projName, facetVersion,IJ2EEFacetInstallDataModelProperties.ENTERPRISE_APPLICATION);
		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		IProject webProj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		return webProj;
	}
	private IProject createAppClientProject(String projName) throws ExecutionException {
		IDataModel dataModel = DataModelFactory.createDataModel(IAppClientFacetInstallDataModelProperties.class);
		String versionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.JEE_5_0_ID);
		IProjectFacet facet = ProjectFacetsManager.getProjectFacet(IAppClientFacetInstallDataModelProperties.APPLICATION_CLIENT);
		IProjectFacetVersion facetVersion = facet.getVersion(versionString); //$NON-NLS-1$
		addVersionProperties(dataModel, projName, facetVersion,IJ2EEFacetInstallDataModelProperties.APPLICATION_CLIENT);
		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		map.add(setupJavaInstallAction(projName,CreationConstants.DEFAULT_APPCLIENT_SOURCE_FOLDER));
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		IProject webProj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		return webProj;
	}
	protected IDataModel setupJavaInstallAction(String aProjectName, String srcFolder) {
		IDataModel dm = DataModelFactory.createDataModel(new JavaFacetInstallDataModelProvider());
		dm.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, aProjectName);
		String jVersion = "5.0";
		dm.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, jVersion); //$NON-NLS-1$
		dm.setStringProperty(IJavaFacetInstallDataModelProperties.SOURCE_FOLDER_NAME, srcFolder); //$NON-NLS-1$
		return dm;
	}
    private void addWebProjectProperties(IDataModel dataModel, String projName, IProjectFacetVersion web25){

		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel webmodel = (IDataModel) map.get(IWebFacetInstallDataModelProperties.DYNAMIC_WEB);
		webmodel.setProperty(IFacetInstallDataModelProperties.FACET_VERSION, web25);
		webmodel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER,"web333"); //$NON-NLS-1$
        webmodel.setStringProperty(IWebFacetInstallDataModelProperties.SOURCE_FOLDER, "src444");
    }
    
    private void addVersionProperties(IDataModel dataModel, String projName, IProjectFacetVersion fv, String facetString){

		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel model = (IDataModel) map.get(facetString);
		model.setBooleanProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD, true);
		model.setProperty(IFacetInstallDataModelProperties.FACET_VERSION, fv);
		
    }


//	public void testNewEARModelPopulation() throws Exception {
//		
//		EMFAttributeFeatureGenerator.reset();
//		
//		String newModelPathURI = "/testearmodel.xml";
//		URI uri = URI.createPlatformResourceURI("/" + getProject().getName() + newModelPathURI,false);
//		ResourceSet resSet = getResourceSet();
//		registerFactory(uri, resSet, new ApplicationResourceFactoryImpl());
//	
//		ApplicationResourceImpl ejbRes = (ApplicationResourceImpl) resSet.createResource(uri);
//		
//		setVersion(J2EEVersionConstants.JEE_5_0_ID);
//		
//		ApplicationDeploymentDescriptor DDroot = ApplicationFactory.eINSTANCE.createApplicationDeploymentDescriptor();
//		Application ear = ApplicationFactory.eINSTANCE.createApplication();
//		ejbRes.getContents().add((EObjectImpl)DDroot);
//		DDroot.setApplication(ear);
//		populateRoot((EObjectImpl)ear);
//		
//		ejbRes.save(null);
//		
//	
//	}
	public IProject getProject() {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECTNAME);
	}
	public IProject getProject(String projName) {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
	}


	private void registerFactory(URI uri, ResourceSet resSet, Resource.Factory factory) {
		WTPResourceFactoryRegistry registry = (WTPResourceFactoryRegistry) resSet.getResourceFactoryRegistry();
		registry.registerLastFileSegment(uri.lastSegment(), factory);
	}
	private ResourceSet getResourceSet() {
		ResourceSet set = new ResourceSetImpl();
		set.setResourceFactoryRegistry(WTPResourceFactoryRegistry.INSTANCE);
		return set;
	}



	
	protected void tearDown() throws Exception {
		// Don't delete these files
	}

	

}
